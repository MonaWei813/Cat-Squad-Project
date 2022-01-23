#This file controld the functionalities for agent account
from flask_restx import Namespace, Resource, abort
from flask import request
from util.helper import *
import db.init_db as db
from db.init_db import *
from util.models import *
import re
import hashlib
import os

api = Namespace('agent', description= 'Agent Services')

@api.route('/addsavedresume')
class addsavedresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (resume_token, user_token) = unpack(request.json,'resume_token','user_token')   
   
        session = db.get_session()
        newsave = SavedResume(resume_token = resume_token,user_token = user_token)
        session.add(newsave)
        session.commit()
        session.close()

        return {}

@api.route('/deletesavedresume')
class deletesavedresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (resume_token, user_token) = unpack(request.json,'resume_token','user_token')    
        session = db.get_session()
        session.query(db.SavedResume).filter_by(user_token = user_token,resume_token = resume_token).delete()
        session.commit()
        session.close()
        return {}

@api.route('/showsavedresume')
class showsavedresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,resume_token) = unpack(request.json,'user_token','resume_token')    

        session = db.get_session()
        saves = session.query(db.SavedResume).filter_by(user_token = user_token).all()

        result = {}
        temp = 1
        for s in saves:
            j = session.query(db.Resume).filter_by(resume_token = s.resume_token).first()
            if(j.status == "activity"):
                result.update({temp:{'resume_token': s.resume_token, 'label': j.label, 'education_degree':j.education_degree,\
                'major': j.major, 'skill': j.skill1+", "+j.skill2+", "+j.skill3}})
                temp = temp + 1
        session.commit()
        session.close()
        return result

@api.route('/checkresumesaved')
class checksavedresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,resume_token) = unpack(request.json,'user_token','resume_token')    

        session = db.get_session()
        saves = session.query(db.SavedResume).filter_by(user_token = user_token,resume_token = resume_token).all()
        if not saves:
            session.commit()
            session.close()
            return {"status": "notexist"}

        session.commit()
        session.close()
        return {"status":"exist"}

@api.route('/searchResume')
class searchResume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(searchresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (skill,status) = unpack(request.json,'skill','status')
        
        if skill == "empty":
            abort(400,'Missing inputs')

        result = {}
        temp = 1

        session = db.get_session()

        resume = session.query(db.Resume).filter_by(skill1 = skill, status = status).all()
        for r in resume:
            result.update({temp:{'resume_token': r.resume_token, 'label': r.label, 'education_degree':r.education_degree,\
                'major': r.major, 'skill1': r.skill1,'skill2': r.skill2,'skill3': r.skill3}})
            temp = temp + 1

        resume = session.query(db.Resume).filter_by(skill2 = skill, status = status).all()
        for r in resume:
            result.update({temp:{'resume_token': r.resume_token, 'label': r.label, 'education_degree':r.education_degree,\
                'major': r.major, 'skill1': r.skill1,'skill2': r.skill2,'skill3': r.skill3}})
            temp = temp + 1

        resume = session.query(db.Resume).filter_by(skill3 = skill, status = status).all()
        for r in resume:
            result.update({temp:{'resume_token': r.resume_token, 'label': r.label, 'education_degree':r.education_degree,\
                'major': r.major, 'skill1': r.skill1,'skill2': r.skill2,'skill3': r.skill3}})
            temp = temp + 1

        if len(result) == 0:
            abort(405, 'it has no result')

        session.commit()
        session.close()
        return result

@api.route('/ShowAllResume')
class ShowAllResume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing Words')

    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def get(self): 
        
        session = db.get_session()
        resume = session.query(db.Resume).filter_by(status = 'activity',PDFpath = None).all()
        result = {}
        temp = 1
        for r in resume:
            result.update({temp:{'resume_token': r.resume_token, 'label': r.label, 'education_degree':r.education_degree,\
                'major': r.major, 'skill': r.skill1+', '+r.skill2+', '+r.skill3}})
            temp = temp + 1
        session.commit()
        session.close()
        return result

@api.route('/resumedetail')
class resumedetail(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(resume_detail(api))
    @api.doc(description = 
        '''This is used to autshenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,resume_token) = unpack(request.json,'user_token','resume_token')    
        session = db.get_session()

        #find the database in Job table
        resume= session.query(db.Resume).filter_by(resume_token = resume_token).first()
        if not resume:
            abort(403, 'you have no permission to delete the job')
        if resume.status == "delete":
            abort(403, 'it has been delete')
        return {
            "label": resume.label,
            "first_name": resume.first_name,
            "last_name": resume.last_name,
            "year": resume.year,
            "month": resume.month,
            "day": resume.day,
            "sex": resume.sex,
            "mobile_contact": resume.mobile_contact,
            "email_address": resume.email_address,
            "education_degree": resume.education_degree,
            "major": resume.major,
            "skill1": resume.skill1,
            "skill2": resume.skill2,
            "skill3": resume.skill3,
            "description": resume.description,
            "PDFpath": resume.PDFpath
        }


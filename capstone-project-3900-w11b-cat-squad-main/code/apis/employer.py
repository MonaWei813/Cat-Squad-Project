#This file controld the functionalities for employer account
from flask_restx import Namespace, Resource, abort
from flask import request
from util.helper import *
import db.init_db as db
from db.init_db import *
from util.models import *
import re
import hashlib
import os

api = Namespace('employer', description= 'Employer Services')
@api.route('/Addjob')
class Addjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(addjob_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, label,company_name, letter_require,email_address,employer_type,requirement_degree\
        ,requirement_skill,job_time,salary, job_location, description) = unpack(request.json,'user_token','label','company_name',\
            'letter_require','email_address','employer_type','requirement_degree','requirement_skill','job_time',\
            'salary','job_location' ,'description')
        if label == ''or company_name =='' or email_address =='' or salary =='' or requirement_degree =='' \
            or requirement_skill ==',,' or description == '':
            abort(400, 'missing input')
        if employer_type == 'empty' or job_time == 'empty' or job_location == 'empty':
            abort(400, 'missing input')
        if check_email(email_address) != True:
            abort(403, 'missing email address')
        # connect the database
        session = db.get_session()
        #find the database in Job table
        job = session.query(db.Job)
        
        length = job.count()
        job_token = length + 1
        status = 'activity'
        #get the new job elemnet
        newjob = Job(user_token = user_token,job_token = job_token,label = label,company_name = company_name,\
                    letter_require  = letter_require,email_address  =  email_address,employer_type = employer_type,\
                    requirement_degree = requirement_degree, requirement_skill =  requirement_skill,\
                    job_time = job_time, salary = salary, job_location = job_location, description = description,\
                    status = status)
        session.add(newjob)
        session.commit()
        session.close()
        # until here, up is get the new element, and add it into database

        return {
            'token': job_token,
            'status': status
        }

@api.route('/Deletejob')
class Deletejob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(deletejob_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, job_token) = unpack(request.json,'user_token','job_token')
        session = db.get_session()
        job = session.query(db.Job).filter_by(user_token = user_token, job_token = job_token).first()
        if not job:
            abort(403, 'you have no permission to delete the job')
        if job.status == "delete":
            abort(403, 'it has been delete')
        job.status = "delete"
        status = job.status
        session.commit()
        session.close()
        return {
            'token': job_token,
            'status': status
        }

@api.route('/modifyjob')
class Modifyjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(modifyjob_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token,job_token, label,company_name, letter_require,email_address,employer_type,requirement_degree\
        ,requirement_skill,job_time,salary, job_location, description) = unpack(request.json,'user_token','job_token','label','company_name',\
            'letter_require','email_address','employer_type','requirement_degree','requirement_skill','job_time',\
            'salary','job_location' ,'description')

        if label == ''or company_name =='' or email_address =='' or salary =='' or requirement_degree =='' \
            or requirement_skill ==',,' or description == '':
            abort(400, 'missing input')
        if employer_type == 'empty' or job_time == 'empty' or job_location == 'empty':
            abort(400, 'missing input')
        if check_email(email_address) != True:
            abort(406, 'missing email address')
        # connect the database
        session = db.get_session()

        #find the database in Job table
        job= session.query(db.Job).filter_by(user_token = user_token, job_token = job_token).first()
        if not job:
            abort(403, 'you have no permission to delete the job')
        if job.status == "delete":
            abort(403, 'it has been delete')

        #find the result, and change element information which you want
        job.label = label
        job.company_name = company_name
        job.letter_require = letter_require
        job.email_address = email_address
        job.employer_type = employer_type
        job.requirement_degree = requirement_degree
        job.requirement_skill = requirement_skill
        job.job_time = job_time
        job.salary = salary
        job.job_location = job_location
        job.description = description

        status = job.status
        session.commit()
        session.close()
        #until here, up is how to chang inforamtion and how to update
        
        return {
            'token': job_token,
            'status': status
        }
@api.route('/jobdetail')
class jobdetail(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(job_detail(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,job_token) = unpack(request.json,'user_token','job_token')    
        session = db.get_session()

        #find the database in Job table
        job= session.query(db.Job).filter_by(user_token = user_token, job_token = job_token).first()
        if not job:
            abort(403, 'you have no permission to delete the job')
        if job.status == "delete":
            abort(403, 'it has been delete')
        return {
            "label": job.label,
            "company_name": job.company_name,
            "letter_require": job.letter_require,
            "email_address": job.email_address,
            "employer_type": job.employer_type,
            "requirement_degree": job.requirement_degree,
            "requirement_skill": job.requirement_skill,
            "job_time": job.job_time,
            "salary": job.salary,
            "job_location": job.job_location,
            "description": job.description
        }
        



@api.route('/DealApply')
class DealApply(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(dealapply(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (apply_token, result) = unpack(request.json,'apply_token', 'result')

        session = db.get_session()
        temp = session.query(db.Apply).filter_by(apply_token = apply_token).first()
        temp.result = result
        temp.status = 'marked'

        user = session.query(db.User).filter_by(token = temp.applyuser_token).first()
        result_email = user.email

        status = temp.status 
        session.commit()
        session.close()
        
        return{
            'apply_token': apply_token,
            'status': status,
            "result_email ":result_email 
        }
@api.route('/ShowApply')
class ShowApply(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(Receiveapply(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, status) = unpack(request.json,'user_token', 'status')

        session = db.get_session()

        apply= session.query(db.Apply).filter_by(receiveruser_token = user_token, status = status).all()
        result  = {}
        temp = 1
        for a in apply:
            job = session.query(db.Job).filter_by(job_token = a.job_token).first()
            resume = session.query(db.Resume).filter_by(resume_token = a.resume_token).first()

            result.update({temp:{'apply_token':a.apply_token,"status": a.status, \
                "result":a.result,"resume_token":a.resume_token\
                ,"job_token":a.job_token,"label": job.label,}})
    
            temp = temp + 1
        session.commit()
        session.close()
        return result
        
#show user job for status activity or delete 
@api.route('/ShowUserJob')
class ShowUserJob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing Words')
    @api.expect(Userjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self): 
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token,status) = unpack(request.json,'user_token','status')

        print("----------------------")
        print(user_token)
        session = db.get_session()
        job = session.query(db.Job).filter_by(user_token = user_token, status = status ).all()

        result = {}
        temp =  1

        for j in job:
            result.update({temp:{'job_token': j.job_token, 'label': j.label, 'company_name':j.company_name,\
                'requirement_degree': j.requirement_degree, 'requirement_skill': j.requirement_skill, 'status': j.status}})
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
        
        user = session.query(db.User).filter_by(token = resume.user_token).first()
        if not resume:
            abort(403, 'you have no permission to delete the job')
        if resume.status == "delete":
            abort(403, 'it has been delete')
        label = resume.label
        first_name =  resume.first_name
        last_name = resume.last_name
        year = resume.year
        month = resume.month
        day = resume.day
        sex = resume.sex
        mobile_contact = resume.mobile_contact
        email_address = resume.email_address
        education_degree = resume.education_degree
        major = resume.major
        skill1 = resume.skill1
        skill2 = resume.skill2
        skill3 = resume.skill3
        description =  resume.description
        PDFpath = resume.PDFpath
        user_email = user.email
        session.commit()
        session.close()

        return {
            "label": label,
            "first_name": first_name,
            "last_name": last_name,
            "year": year,
            "month": month,
            "day": day,
            "sex": sex,
            "mobile_contact": mobile_contact,
            "email_address": email_address,
            "education_degree": education_degree,
            "major": major,
            "skill1": skill1,
            "skill2": skill2,
            "skill3": skill3,
            "description": description,
            "PDFpath": PDFpath,
            "user_email":user_email
        }

""" checking emails """

class Validator(object):
    """doc string placeholder"""
    def __init__(self, email):
        self.email = email

    def check_for_symbol(self):
        """doc string placeholder"""
        if re.search("[@.]", self.email) is None:
            return False  #not a emial style
        else:
            return True

    def check_length(self):
        """doc string placeholder"""
        if len(self.email) >= 12:
            return True
        else:
            return False

def check_email(email):
    """doc string placeholder"""
    validator = Validator(email)
    symbol = validator.check_for_symbol()
    if symbol is False:
        return False

    length = validator.check_length()
    if length is False:
        return False

    if length and symbol is True:
        return True

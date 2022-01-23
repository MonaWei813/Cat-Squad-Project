#This file controld the functionalities for jobseeker account
from flask_restx import Namespace, Resource, abort
from flask import request
from util.helper import *
import db.init_db as db
from db.init_db import *
from util.models import *
import re
import hashlib
import os
import re

api = Namespace('jobseeker', description= 'Jobseeker Services')
@api.route('/Addresume')
class Addresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(addresume_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token, label,first_name,last_name, year, month,day,\
        sex, mobile_contact,email_address,education_degree,\
        major,skill1,skill2,skill3,description) = unpack(request.json,'user_token','label','first_name','last_name',\
        'year','month','day','sex','mobile_contact','email_address','education_degree','major','skill1','skill2','skill3','description')

        
        if label == ''or first_name =='' or last_name =='' or mobile_contact=='' or email_address =='' \
            or education_degree ==',,' or major == '' or skill1 == '' or  skill2 == ''  or skill3 == '' or description == '':
            abort(400, 'missing input')

        if year == 'empty' or month == 'empty' or day == 'empty':
            abort(400, 'missing input')

        if check_email(email_address) != True:
            abort(403,'Invalid email_Address')

        session = db.get_session()
        resume = session.query(db.Resume)
        length = resume.count()
        resume_token = length + 1
        status = 'activity'
        newresume = Resume(user_token = user_token,resume_token = resume_token,label = label,first_name = first_name,\
                    last_name = last_name, year = year, month = month,day = day,sex = sex, mobile_contact = mobile_contact,\
                    email_address =email_address, education_degree = education_degree,major = major,skill1 = skill1, \
                    skill2 = skill2,skill3 = skill3,description = description,status = status)
        session.add(newresume)
        session.commit()
        session.close()
        return {
            'token': resume_token,
            'status': status
        }

@api.route('/AddresumePDF')
class AddresumePDF(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(addresumePDF_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token, PDFpath,label) = unpack(request.json,'user_token','PDFpath','label')

        if label == "":
            abort(405,'The label cannot be empty!')
        if PDFpath == "":
            abort(406,'The PDFpath cannot be empty!')
        
        session = db.get_session()
        resume = session.query(db.Resume)
        length = resume.count()
        resume_token = length + 1
        status = 'activity'
        newresume = Resume(user_token = user_token,PDFpath = PDFpath,status = status,label = label,resume_token = resume_token)
        session.add(newresume)
        session.commit()
        session.close()
        return {
            'token': resume_token,
            'status': status
        }

@api.route('/Deleteresume')
class Deleteresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(deleteresume_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, resume_token) = unpack(request.json,'user_token','resume_token')
        session = db.get_session()
        resume = session.query(db.Resume).filter_by(user_token = user_token, resume_token = resume_token).first()
        if not resume:
            abort(403, 'you have no permission to delete the resume')
        if resume.status == "delete":
            abort(403, 'it has been delete')
        resume.status = "delete"
        status = resume.status
        session.commit()
        session.close()
        return {
            'token': resume_token,
            'status': status
        }

@api.route('/modifyresume')
class Modifyresume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(modifyresume_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, resume_token, label,first_name,last_name, year, month,day,\
        sex, mobile_contact,email_address,education_degree,\
        major,skill1,skill2,skill3,description) = unpack(request.json,'user_token','resume_token','label','first_name',\
            'last_name','year','month','day','sex','mobile_contact','email_address','education_degree','major','skill1',\
            'skill2','skill3','description')

        if label == ''or first_name =='' or last_name =='' or mobile_contact=='' or email_address =='' \
            or education_degree ==',,' or major == '' or skill1 == '' or  skill2 == ''  or skill3 == '' or description == '':
            abort(400, 'missing input')

        if year == 'empty' or month == 'empty' or day == 'empty':
            abort(400, 'missing input')
            
        if check_email(email_address) != True:
            abort(403,'Invalid email_Address')
            
        session = db.get_session()
        resume = session.query(db.Resume).filter_by(user_token = user_token, resume_token = resume_token).first()
        if not resume:
            abort(403, 'you have no permission to delete the resume')
        if resume.status == "delete":
            abort(403, 'it has been delete')
        resume.label = label
        resume.first_name = first_name
        resume.last_name  = last_name
        resume.year = year
        resume.month = month
        resume.day = day
        resume.sex = sex
        resume.mobile_contact = mobile_contact
        resume.email_address = email_address
        resume.education_degree = education_degree
        resume.major = major
        resume.skill1 = skill1
        resume.skill2 = skill2
        resume.skill3 = skill3
        resume.description = description
        status = resume.status
        session.commit()
        session.close()
        return {
            'token': resume_token,
            'status': status
        }

@api.route('/resumedetail')
class resumedetail(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(resume_detail(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,resume_token) = unpack(request.json,'user_token','resume_token')    
        session = db.get_session()

        #find the database in Job table
        resume= session.query(db.Resume).filter_by(user_token = user_token, resume_token = resume_token).first()
        if not resume:
            abort(403, 'you have no permission to see the resume')
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


@api.route('/ShowResume')
class ShowResume(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(Userresume(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token,status) = unpack(request.json,'user_token','status')
        session = db.get_session()
        resume = session.query(db.Resume).filter_by(user_token = user_token, status = status).all()
        if not resume:
            abort(403, 'you have no permission to delete the resume')
        result = {}
        temp =  1

        for r in resume:
            result.update({temp:{'resume_token': r.resume_token, 'label': r.label, 'status': r.status,'PDFpath': r.PDFpath}})
            temp = temp + 1
        session.commit()
        session.close()
        return result

@api.route('/Resumecheck')
class Resumecheck(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(resume_check(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token,resume_token,apply_token) = unpack(request.json,'user_token','resume_token','apply_token')
        session = db.get_session()
        resume = session.query(db.Resume).filter_by(resume_token = resume_token).first()
        if not resume:
            abort(403, 'you have no permission to delete the resume')
        if resume.PDFpath != None:
            return{
                "check": "yes",
                "PDFpath": resume.PDFpath,
                "resume_token":resume_token,
                'apply_token':apply_token
            }
            
        return{
            "check": "no",
            "PDFpath": resume.PDFpath,
            "resume_token":resume_token,
            'apply_token':apply_token
        }

@api.route('/CreateApply')
class CreateApply(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(createapply(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (job_token,resume_token, user_token) = unpack(request.json, 'job_token', 'resume_token','user_token')

        session = db.get_session()
        temp = session.query(db.Apply).filter_by(applyuser_token = user_token, job_token = job_token).all()

        job = session.query(db.Job).filter_by(job_token = job_token).first()

        label = job.label
        if not temp:
            apply = session.query(db.Apply)
            length = apply.count()
            apply_token = length + 1
            status = 'activity'

            newapply = Apply(job_token = job_token, resume_token = resume_token, status = status, applyuser_token = user_token, \
                receiveruser_token = job.user_token,apply_token = apply_token)

            user = session.query(db.User).filter_by(token = job.user_token).first()
            user_email = user.email
            session.add(newapply)

            session.commit()
            session.close()
        else:
            abort(403, 'You have been apply this, please try another apply')
        return{
            'apply_token': apply_token,
            'job_label': label,
            'status': status,
            'user_email':user_email
        }
        
@api.route('/ShowApply')
class ShowApply(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(apply(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (user_token, status) = unpack(request.json,'user_token', 'status')
        session = db.get_session()
        apply = session.query(db.Apply).filter_by(applyuser_token = user_token).all()
        result = {}
        temp = 1
        for a in apply:
            job = session.query(db.Job).filter_by(job_token = a.job_token).first()
            resume = session.query(db.Resume).filter_by(resume_token = a.resume_token).first()

            result.update({temp:{'apply_token':a.apply_token,"status": a.status, \
                "result":a.result,"resume_token":a.resume_token\
                ,"job_token":a.job_token,"label": job.label}})
                
            temp = temp + 1
        session.commit()
        session.close()
        return result

@api.route('/ShowAllJob')
class ShowAllJob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing Words')

    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def get(self): 
        
        session = db.get_session()
        job = session.query(db.Job).filter_by(status = 'activity').all()
        result = {}
        temp = 1
        for j in job:
            result.update({temp:{'job_token': j.job_token, 'label': j.label, 'company_name':j.company_name,\
                'requirement_degree': j.requirement_degree, 'requirement_skill': j.requirement_skill}})
            temp = temp + 1
        session.commit()
        session.close()
        return result
            
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
        job= session.query(db.Job).filter_by(job_token = job_token).first()
        if not job:
            abort(403, 'you have no permission to delete the job')
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

@api.route('/searchjob')
class searchjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(searchjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (job_location, job_time,employer_type, company_name) = unpack(request.json,'location','job_time','employer_type','company_name')    
        session = db.get_session()
        job = session.query(db.Job).filter_by(status = 'activity').all()
        if company_name == "empty" and employer_type != "empty" and job_time != "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(job_location = job_location, job_time = job_time,\
            employer_type = employer_type, status = 'activity').all()

        elif company_name != "empty" and employer_type == "empty" and job_time != "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(job_location = job_location, job_time = job_time,\
            status = 'activity', company_name = company_name).all()

        elif company_name != "empty" and employer_type != "empty" and job_time == "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(employer_type = employer_type, job_location = job_location,\
            status = 'activity', company_name = company_name).all()
        
        elif company_name != "empty" and employer_type != "empty" and job_time != "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(employer_type = employer_type, job_time = job_time,\
            status = 'activity', company_name = company_name).all()
        
        elif company_name == "empty" and employer_type == "empty" and job_time != "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(job_time = job_time,job_location = job_location,\
            status = 'activity').all()

        elif company_name == "empty" and employer_type != "empty" and job_time == "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(employer_type = employer_type,job_location = job_location,\
            status = 'activity').all()

        elif company_name == "empty" and employer_type != "empty" and job_time != "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(employer_type = employer_type,job_time = job_time,\
            status = 'activity').all()

        elif company_name != "empty" and employer_type == "empty" and job_time == "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(company_name = company_name,job_location = job_location,\
            status = 'activity').all()

        elif company_name != "empty" and employer_type == "empty" and job_time != "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(company_name = company_name,job_time = job_time,\
            status = 'activity').all()

        elif company_name != "empty" and employer_type != "empty" and job_time == "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(company_name = company_name,employer_type= employer_type,\
            status = 'activity').all()
        
        elif company_name == "empty" and employer_type == "empty" and job_time == "empty" and job_location !=  "empty":
            job= session.query(db.Job).filter_by(job_location = job_location, status = 'activity').all()

        elif company_name == "empty" and employer_type == "empty" and job_time != "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(job_time = job_time, status = 'activity').all()

        elif company_name == "empty" and employer_type != "empty" and job_time == "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(employer_type  = employer_type , status = 'activity').all()

        elif company_name != "empty" and employer_type == "empty" and job_time == "empty" and job_location ==  "empty":
            job= session.query(db.Job).filter_by(company_name  = company_name , status = 'activity').all()
        else:
            abort(406, 'please enter some search option')

        if not job:
            abort(405, 'it has no result')

        result = {}
        temp = 1
        for j in job:
            result.update({temp:{'job_token': j.job_token, 'label': j.label, 'company_name':j.company_name,\
                'requirement_degree': j.requirement_degree, 'requirement_skill': j.requirement_skill}})
            temp = temp + 1
        session.commit()
        session.close()
        return result
    
@api.route('/addsavedjob')
class addsavedjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (job_token, user_token) = unpack(request.json,'job_token','user_token')   

        session = db.get_session()
        newsave = SavedJob(job_token = job_token,user_token = user_token)

        session.add(newsave)
        session.commit()
        session.close()

        return {}

@api.route('/deletesavedjob')
class deletesavedjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (job_token, user_token) = unpack(request.json,'job_token','user_token')    
        session = db.get_session()
        session.query(db.SavedJob).filter_by(user_token = user_token,job_token = job_token).delete()
        session.commit()
        session.close()
        return {}

@api.route('/showsavedjob')
class showsavedjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,job_token) = unpack(request.json,'user_token','job_token')    

        session = db.get_session()
        saves = session.query(db.SavedJob).filter_by(user_token = user_token).all()

        result = {}
        temp = 1
        for s in saves:
            j = session.query(db.Job).filter_by(job_token = s.job_token).first()
            if(j.status == "activity"):
                result.update({temp:{'job_token': s.job_token, 'label': j.label, 'company_name':j.company_name,\
                'requirement_degree': j.requirement_degree, 'requirement_skill': j.requirement_skill}})
                temp = temp + 1
        session.commit()
        session.close()
        return result

@api.route('/checksaved')
class checksavedjob(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(savedjob(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (user_token,job_token) = unpack(request.json,'user_token','job_token')    

        session = db.get_session()
        saves = session.query(db.SavedJob).filter_by(user_token = user_token,job_token = job_token).all()
        if not saves:
            session.commit()
            session.close()
            return {"status": "notexist"}

        session.commit()
        session.close()
        return {"status":"exist"}


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

        

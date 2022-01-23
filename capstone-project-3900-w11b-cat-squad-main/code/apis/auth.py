#This file controls authentication process
from flask_restx import Namespace, Resource, abort
from flask import request
from util.helper import *
import db.init_db as db
from db.init_db import *
from util.models import *
import re
import hashlib
import os
import random


import smtplib
import ssl
from email.mime.image import MIMEImage
#creat email object
from email.mime.multipart import MIMEMultipart
#header
from email.header import Header
#build text
from email.mime.text import MIMEText

api = Namespace('auth', description= 'Authentication Services')

@api.route('/login')
class Login(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(login_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (email, password) = unpack(request.json,'email','password')
        if  email == '' or password == '':
            abort(400,'Missing inputs')
        session = db.get_session()
        user = session.query(db.User).filter_by(email = email).first()
        if not user:
            abort(403, 'Invalid email/password')
        else:
            password_bytes = password.encode()
            hash_password = hashlib.sha256(password_bytes).hexdigest()
            if user.password == hash_password:
                t = user.token
                session.commit()
                user_id = user.id
                session.close()
                return {
                'token': t,
                'usertype': user.usertype
                }
            else:
                abort(403, 'Invalid email/password')

@api.route('/signup')
class Signup(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(signup_details(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
            
        (email, firstname, lastname, password,repassword, usertype) \
        = unpack(request.json,'email','first_name','last_name','password','repassword','usertype')
        if email  == '' or firstname == '' or lastname == '' or password == '' or repassword == '':
            abort(400, 'missing inputs!')
        session = db.get_session()
        user = session.query(db.User).filter_by(email = email).first()
        if not user:
            if check_email(email) == False:
                abort(403, 'Invalid email address')
            if password != repassword:
                abort(405, 'please enter the password again')


            password_bytes = password.encode()
            hash_password = hashlib.sha256(password_bytes).hexdigest()
            token_byte = email.encode()
            hash_token = hashlib.sha256(token_byte).hexdigest()
            newuser = User(email=email,token=hash_token,first_name=firstname,last_name=lastname,password = hash_password, usertype = usertype)
            session.add(newuser)
            session.commit()
            user_id = newuser.id
            session.close()
            return {
                'token': hash_token,
                'usertype': usertype
            }
        else:
            abort(401, 'Exist email try to another email address')

@api.route('/send_code')
class Send_code(Resource):
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(send_code(api))
    @api.doc(description = 
        '''This is used to authenticate a verfied accound, it used email and password
        to access enter the main page'''
    )
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (email,password) = unpack(request.json,'email','password')
        session = db.get_session()
        user = session.query(db.User).filter_by(email = email).first()
        print("----------------------")
        if check_email(email) == False:
            abort(403, 'Invalid email address')
        if not user:
            abort(401, 'You have no exist account by this email')

        user.reset_code = generate_verification_code()
        print(user.reset_code)

        token = user.token
        code = user.reset_code 

        session.commit()
        session.close()
        return {
            'token': token,
            'reset_code': code
        }
        
@api.route('/reset_password')
class reset_password(Resource): 
    @api.response(200,'success')
    @api.response(400,'Missing username/password')
    @api.response(403,'Invalid Username/password')
    @api.expect(reset_password(api))   
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (email, password,repassword, code) = unpack(request.json,'email','password','repassword','code')
        if email =='' or password == '' or repassword == '' or code  =='':
            abort(400,'Missing input')

        session = db.get_session()
        user = session.query(db.User).filter_by(email = email).first()

        print("----------------------")
        if check_email(email) == False:
            abort(403, 'Invalid email address')
        if not user:
            abort(401, 'You have no exist account by this email')
        if password != repassword:
            abort(405, 'please enter the password again')
        if user.reset_code != code:
            abort(406, 'please enter correct code')


        password_bytes = password.encode()
        hash_password = hashlib.sha256(password_bytes).hexdigest()
        user.password = hash_password
        user.reset_code = None

        recode = user.reset_code
        token = user.token

        session.commit()
        session.close()
        return{
            'token': token,
            'reset_code': recode
        }

@api.route('/show_auth_detail')
class showauthdetail(Resource): 
    @api.response(200,'success')
    @api.response(400,'Missing input')
    @api.response(403,'Invalid input')
    @api.expect(showauth_detail(api))   

    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')

        (token,email) = unpack(request.json,'token','email')
        session = db.get_session()
        user = session.query(db.User).filter_by(token = token).first()
        firstname = user.first_name
        lastname = user.last_name
        showemail = user.email
        session.commit()
        session.close()

        return{

            'token': token,
            'email': showemail,
            'firstname':firstname,
            'lastname':lastname
        }
@api.route('/edit_auth_detail')
class editauthdetail(Resource): 
    @api.response(200,'success')
    @api.response(400,'Missing input')
    @api.response(403,'Invalid input')
    @api.expect(editauth_detail(api))
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (token, firstname, lastname) = unpack(request.json,'token','firstname','lastname') 
        if firstname == '' or lastname == '':
            abort(400, 'Missing input')
        session = db.get_session()
        user = session.query(db.User).filter_by(token = token).first()
        user.first_name = firstname
        user.last_name = lastname
        session.commit()
        session.close()
        return{
            'token': token
        }
@api.route('/change_password')
class change_password(Resource): 
    @api.response(200,'success')
    @api.response(400,'Missing input')
    @api.response(403,'Invalid input')
    @api.expect(change_password(api))
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (token, oldpassword, newpassword,repassword) = \
            unpack(request.json,'token','oldpassword','newpassword','repassword')

        session = db.get_session()
        user = session.query(db.User).filter_by(token = token).first()  
        if newpassword == '' or repassword == '' or oldpassword == '':
            abort(400, 'Missing input')

        if newpassword != repassword:
            abort(405, 'please enter the password again')
        print(oldpassword)
        
        old_password_bytes = oldpassword.encode()
        old_hash_password = hashlib.sha256(old_password_bytes).hexdigest()
        
        if user.password != old_hash_password:
            abort(403, 'please  enter  correct  password!')

        password_bytes = newpassword.encode()
        hash_password = hashlib.sha256(password_bytes).hexdigest()
        user.password = hash_password
        session.commit()
        session.close()

        return{
            'token': token
        }
@api.route('/send_email')
class send_email(Resource): 
    @api.response(200,'success')
    @api.response(400,'Missing input')
    @api.response(403,'Invalid input')
    @api.expect(sendemail(api))
    def post(self):
        if not request.json:
            abort(400, 'Malformed Request')
        (email, textin1,textin2,head) = unpack(request.json,'email','textin1','textin2','head')

        send_email(email, textin1,textin2, head)

        return {}
        

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

def generate_verification_code():
    ''' generate_6_nums_verification_code '''
    code_list = []
    for i in range(10): # 0-9Nums
        code_list.append(str(i))
    for i in range(65, 91): # A-Z
        code_list.append(chr(i))
    for i in range(97, 123): # a-z
        code_list.append(chr(i))

    myslice = random.sample(code_list, 6)  # Take 6 element from list,return
    verification_code = ''.join(myslice) # list to string
    # print code_list
    # print type(myslice)
    return verification_code

def send_email(To_addr,textin1,textin2,head):
    context = ssl.create_default_context()
    con = smtplib.SMTP_SSL('smtp.qq.com',465,context=context)

    From_addr = '764695722@qq.com'
    #To_addr = '764695722@qq.com'
    con.login(From_addr,'kquhnrdibwmmbbaf')

        #3 header , text
    msgRoot = MIMEMultipart('related')

        #setting header
    subject = Header(head,'utf-8').encode() #encode to binary
    msgRoot['Subject'] = subject


        #setting sender
    Sender = From_addr + "<" + From_addr + ">"

    msgRoot['From'] = Sender
    msgRoot['To'] = To_addr #'email1;email2; ....'
    msgAlternative = MIMEMultipart()
    msgRoot.attach(msgAlternative)

    mail_msg = """<p><img src="cid:image1" height="75" width="150" aligen = "left"></p>
    <div class="container text-center">
        <p>{0}</p>
        <p>{1}</p>
        <br>
        <p>Thanks and Regards,</p>
        <p>Cat Squad Team</p>
        <br>
        <br>
        <br>
        <br><br><br><br>
    <div>
    <section class="text-center content-section" >
        <div class="container text-center" style="background: rgba(165, 163, 163, 0.55);">
            <center>
                <h5>Wanted is a free online job market. 
                <p>We offer three types of user account. </p>
                <p>For jobseekers, the account will help you to promote yourself and find your dream job. </p>
                <p>For employers, this website will offer a chance to recruit great employees. </p>
                <p>And for the agents, you can get access to both jobseekers and employers' information, and help them to connect to each other.</p></h5>
                <br><br>
                <center>
                    <p><i>Copyright © CAT SQUAD TEAM 2021</i></p>
                    <p style="font-size: small;"><a href="https://github.com/unsw-cse-comp3900-9900-21T1/capstone-project-3900-w11b-cat-squad/tree/main">Google+</a>||<a href="https://github.com/unsw-cse-comp3900-9900-21T1/capstone-project-3900-w11b-cat-squad/tree/main">Twitter</a>||<a href="https://github.com/unsw-cse-comp3900-9900-21T1/capstone-project-3900-w11b-cat-squad/tree/main">Github</a></p>
                </center>
            </center>
        </div>
    <section>

    """

    msgAlternative.attach(MIMEText(mail_msg.format(textin1,textin2), 'html', 'utf-8'))

    fp = open('apis/campingforemail.jpg', 'rb')
    msgImage = MIMEImage(fp.read())
    msgImage.add_header('Content-ID', '<image1>')
    msgRoot.attach(msgImage)

    fp.close()
    #msg.attach(text) #add text to message

    con.sendmail(From_addr,To_addr,msgRoot.as_string())
    con.quit()
    return
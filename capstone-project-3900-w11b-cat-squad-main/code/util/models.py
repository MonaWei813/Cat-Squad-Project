from flask_restx import fields

def auth_details(api):
    return api.parser().add_argument('Authorization', help="Your Authorization Token in the form 'AUTH_TOKEN'", location='headers')

def login_details(api):
    return api.model('login_details', {
        'email': fields.String(required=True, example='SampleEmail'),
        'password': fields.String(required=True, example='SamplePassword')
    })

def signup_details(api):
    return api.model('signup_details', {
        'email': fields.String(required=True, example='SampleEmail'),
        'first_name': fields.String(required=True, example='Peter Snow'),
        'last_name': fields.String(required=True, example='Peter Snow'),
        'password': fields.String(required=True, example='SamplePassword'),
        'repassword':fields.String(required=True, example='SamplePassword'),
        'usertype' : fields.String(required=True, example='Sampleusetype')
    })

def send_code(api):
    return api.model('send_dcode', {
        'email': fields.String(required=True, example='764695722@qq.com'),
        'password': fields.String(required=True, example='sample')
    })
def reset_password(api):
    return api.model('reset_password', {
        'email': fields.String(required=True, example='764695722@qq.com'),
        'code':fields.String(required=True, example='sample'),
        'password': fields.String(required=True, example='sample'),
        'repassword': fields.String(required=True, example='sample')
    })
def showauth_detail(api):
    return api.model('showauth_detail', {
        'token': fields.String(required=True, example='764695722@qq.com'),
        'email': fields.String(required=True, example='764695722@qq.com')
    })
def editauth_detail(api):
    return api.model('editauth_detail', {
        'token': fields.String(required=True, example='764695722@qq.com'),
        'firstname': fields.String(required=True, example='764695722@qq.com'),
        'lastname': fields.String(required=True, example='764695722@qq.com')
    })
def change_password(api):
    return api.model('editauth_detail', {
        'token': fields.String(required=True, example='764695722@qq.com'),
        'oldpassword': fields.String(required=True, example='764695722@qq.com'),
        'newpassword': fields.String(required=True, example='764695722@qq.com'),
        'repassword': fields.String(required=True, example='764695722@qq.com')
    })
def addresume_details(api):
    return api.model('addresume_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'label':fields.String(required=True, example='SampleEmail'),
        'first_name': fields.String(required=True, example='Peter Snow'),
        'last_name': fields.String(required=True, example='SamplePassword'),
        'year':fields.String(required=True, example="1997"),
        'month' : fields.String(required=True, example="12"),
        'day' : fields.String(required=True, example="17"),
        'sex' : fields.String(required=True, example='Female'),
        'mobile_contact' :fields.String(required=True, example='0431181629'),
        'email_address' : fields.String(required=True, example='Sampleusetype'),
        'education_degree' : fields.String(required=True, example='Sampleusetype'),
        'major' : fields.String(required=True, example='Sampleusetype'),
        'skill1' : fields.String(required=True, example='Sampleusetype'),
        'skill2' : fields.String(required=True, example='Sampleusetype'),
        'skill3' : fields.String(required=True, example='Sampleusetype'),
        'description' : fields.String(required=True, example='Sampleusetype')

    })
def addresumePDF_details(api):
    return api.model('addresumePDF_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'label':fields.String(required=True, example='SampleEmail'),
        'PDFpath':fields.String(required=True, example='SampleEmail')
    })
def deleteresume_details(api):
    return api.model('deleteresume_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'resume_token': fields.Integer(required=True, example=1)
    })
def modifyresume_details(api):
    return api.model('modifyresume_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'resume_token': fields.Integer(required=True, example=1),
        'label':fields.String(required=True, example='SampleEmail'),
        'first_name': fields.String(required=True, example='Peter Snow'),
        'last_name': fields.String(required=True, example='SamplePassword'),
        'year':fields.Integer(required=True, example=1997),
        'month' : fields.Integer(required=True, example=12),
        'day' : fields.Integer(required=True, example=17),
        'sex' : fields.String(required=True, example='Female'),
        'mobile_contact' :fields.String(required=True, example='0431181629'),
        'email_address' : fields.String(required=True, example='Sampleusetype'),
        'education_degree' : fields.String(required=True, example='Sampleusetype'),
        'major' : fields.String(required=True, example='Sampleusetype'),
        'skill1' : fields.String(required=True, example='Sampleusetype'),
        'skill2' : fields.String(required=True, example='Sampleusetype'),
        'skill3' : fields.String(required=True, example='Sampleusetype'),
        'description' : fields.String(required=True, example='Sampleusetype')
    })

def resume_detail(api):
        return api.model('showresumedetail', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'resume_token': fields.Integer(required=True, example=1)
        })
def resume_check(api):
        return api.model('resumecheck', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'resume_token': fields.Integer(required=True, example=1),
        'apply_token':fields.Integer(required=True, example=1)
        })


def addjob_details(api):
    return api.model('addjob_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'label':fields.String(required=True, example='SampleEmail'),
        'company_name': fields.String(required=True, example='Peter Snow'),
        'letter_require' :fields.String(required=True, example='0431181629'),
        'email_address' : fields.String(required=True, example='Sampleusetype'),
        'employer_type': fields.String(required=True, example='SamplePassword'),
        'requirement_degree':fields.String(required=True, example='1997'),
        'requirement_skill' : fields.String(required=True, example='12'),
        'job_time' : fields.String(required=True, example='17'),
        'salary' : fields.String(required=True, example='Female'),
        'job_location' : fields.String(required=True, example='Sampleusetype'),
        'description' : fields.String(required=True, example='Sampleusetype')

    })

def deletejob_details(api):
    return api.model('deletejob_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'job_token': fields.Integer(required=True, example=1)
    })
def modifyjob_details(api):
    return api.model('modifyjob_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'job_token': fields.Integer(required=True, example=1),
        'label':fields.String(required=True, example='SampleEmail'),
        'company_name': fields.String(required=True, example='Peter Snow'),
        'letter_require' :fields.String(required=True, example='0431181629'),
        'email_address' : fields.String(required=True, example='Sampleusetype'),
        'employer_type': fields.String(required=True, example='SamplePassword'),
        'requirement_degree':fields.String(required=True, example='1997'),
        'requirement_skill' : fields.String(required=True, example='12'),
        'job_time' : fields.String(required=True, example='17'),
        'salary' : fields.String(required=True, example='Female'),
        'job_location' : fields.String(required=True, example='Sampleusetype'),
        'description' : fields.String(required=True, example='Sampleusetype')
    })
def job_detail(api):
        return api.model('modifyjob_details', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'job_token': fields.Integer(required=True, example=1)
        })
def createapply(api):
    return api.model('CreateApply', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'job_token': fields.Integer(required=True, example=1),
        'resume_token': fields.Integer(required=True, example=1)
    })
def dealapply(api):
    return api.model('DealApply', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'apply_token': fields.Integer(required=True, example=1),
        'result': fields.String(required=True, example='Female')
    })
def Userjob(api):
    return api.model('UserJob', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='activity')
    })
def Userresume(api):
    return api.model('showresume', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='activity')
    })

def Receiveapply(api):
    return api.model('showreceiveapply', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='activity')
    })

def apply(api):
    return api.model('showapply', {
        'user_token': fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='activity')
    })

def searchjob(api):
    return api.model('searchjob', {
        'location': fields.String(required=True, example='SampleEmail'),
        'job_time':fields.String(required=True, example='SampleEmail'),
        'employer_type':fields.String(required=True, example='SampleEmail'),
        'company_name':  fields.String(required=True, example='SampleEmail')
    })

def savedjob(api):
    return api.model('savejob', {
        'job_token':fields.Integer(required=True, example=1),
        'user_token':fields.String(required=True, example='SampleEmail')
    })


def savedresume(api):
    return api.model('saveresume', {
        'resume_token':fields.Integer(required=True, example=1),
        'user_token':fields.String(required=True, example='SampleEmail')
    })
def searchresume(api):
    return api.model('searchresume', {
        'skill':fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='SampleEmail')
    })
def showresume(api):
    return api.model('showresume', {
        'user_token':fields.String(required=True, example='SampleEmail'),
        'status':fields.String(required=True, example='SampleEmail')
    })

def sendemail(api):
    return api.model('sendemail', {
        'email':fields.String(required=True, example='SampleEmail'),
        'textin1':fields.String(required=True, example='SampleEmail'),
        'textin2':fields.String(required=True, example='SampleEmail'),
        'head':fields.String(required=True, example='SampleEmail')
    })



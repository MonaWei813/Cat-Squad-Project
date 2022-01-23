'''This file create the database tables and entries'''
from sqlalchemy import *
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import os
import hashlib


engine = create_engine('sqlite:///db/dataBase.db?check_same_thread=False', echo = True)
Base = declarative_base()

class User(Base):
    __table__ = Table('User',
                        Base.metadata,
                        Column('id', Integer, primary_key= True,autoincrement=True),
                        Column('token',VARCHAR(64)),
                        Column('email', VARCHAR(64)),
                        Column('first_name', VARCHAR(64)),
                        Column('last_name', VARCHAR(64)),
                        Column('password', VARCHAR(64)),
                        Column('usertype',VARCHAR(64)),
                        Column('reset_code',VARCHAR(64)))
    def __repr__(self):
        return 'User:\nemail: %s\nPassword: %s' % (self.email, self.password)

class Resume(Base):
    __table__ = Table('Resume',
                        Base.metadata,
                        Column('id', Integer, primary_key= True,autoincrement=True),
                        Column('user_token', VARCHAR(64)),
                        Column('resume_token', Integer),
                        Column('label',VARCHAR(64)),
                        Column('first_name',VARCHAR(64)),
                        Column('last_name',VARCHAR(64)),
                        Column('year',Integer),
                        Column('month',Integer),
                        Column('day',Integer),
                        Column('sex',VARCHAR(64)),
                        Column('mobile_contact', VARCHAR(64)),
                        Column('email_address',VARCHAR(64)),
                        Column('education_degree',VARCHAR(64)),
                        Column('major',String(255)),
                        Column('skill1',String(255)),
                        Column('skill2',String(255)),
                        Column('skill3',String(255)),
                        Column('description',TEXT),
                        Column('status',VARCHAR(64)),
                        Column('PDFpath',VARCHAR(64)))
                
    def __repr__(self):
        return 'Resume Table'

class Job(Base):
    __table__ = Table('Job',
                        Base.metadata,
                        Column('id', Integer, primary_key= True,autoincrement=True),
                        Column('user_token', VARCHAR(64)),
                        Column('job_token',Integer),
                        Column('label',VARCHAR(64)),
                        Column('company_name',String(255)),
                        Column('letter_require', VARCHAR(64)),
                        Column('email_address',VARCHAR(64)),
                        Column('employer_type',VARCHAR(64)),
                        Column('requirement_degree',VARCHAR(64)),
                        Column('requirement_skill',String(255)),
                        Column('job_time',VARCHAR(64)),
                        Column('salary',VARCHAR(64)),
                        Column('job_location',String(255)),
                        Column('description',Text),
                        Column('status',VARCHAR(64)))
    def __repr__(self):
        return 'Job Table'
class Apply(Base):
    __table__ = Table('Apply',
                        Base.metadata,
                        Column('id', Integer, primary_key= True,autoincrement=True),
                        Column('apply_token',Integer),
                        Column('job_token',Integer),
                        Column('resume_token', Integer),
                        Column('applyuser_token',VARCHAR(64)),
                        Column('receiveruser_token',VARCHAR(64)),
                        Column('status',VARCHAR(64)),
                        Column('result',VARCHAR(64)))
    def __repr__(self):
        return 'Apply table'

class SavedJob(Base):
    __table__ = Table('SavedJob',
                    Base.metadata,
                    Column('id', Integer, primary_key= True,autoincrement=True),
                    Column('job_token',Integer),
                    Column('user_token', VARCHAR(64)))
    def __repr__(self):
        return 'SavedJob Table'

class SavedResume(Base):
    __table__ = Table('SavedResume',
                    Base.metadata,
                    Column('id', Integer, primary_key= True,autoincrement=True),
                    Column('resume_token',Integer),
                    Column('user_token', VARCHAR(64)))
    def __repr__(self):
        return 'SavedResume Table'


def init_db():
    # drop all the tables and create new tables
    Base.metadata.drop_all(engine)
    Base.metadata.create_all(engine)
    # create session
    Session = sessionmaker(engine)
    session = Session()
    # init all data
    init_user(session)
    session.close()

def get_session():
    Session = sessionmaker(engine)
    return Session()

def init_user(session):
    with open('db/init_users.csv') as f:
        temp = 1
        for line in f.readlines():
            line = line.strip().split(',')
            password_bytes = line[3].encode()
            hash_password = hashlib.sha256(password_bytes).hexdigest()
            token_byte = line[0].encode()
            hash_token = hashlib.sha256(token_byte).hexdigest()
            user = User(email=line[0],token=hash_token,first_name=line[1],last_name=line[2],password = hash_password,usertype = line[4])


            if line[4] == "JobSeeker" and temp == 1:
                resume1 = Resume(user_token = hash_token, resume_token = 1, label = "Resume for technological development",\
                    first_name = "Mengyuan",last_name = "Silvia",year = 1997, month = "December",day = 17, sex = "Female",
                    mobile_contact = "0431181629", email_address = "lluviagibran@gmail.com",education_degree = "Bachlor",
                    major  = "computer science",skill1 = "c",skill2 = "c++", skill3 = "python",description = \
                    "Be responsible for the overall processing,following-up and coordinating of Sales Order, \
                    Act as liaison between Sales and Production, Finance on various order management issues",\
                    status = "activity")
                session.add(resume1)
                resume2 = Resume(user_token = hash_token, resume_token = 2, label = "Resume for part-time job",\
                    first_name = "Mengyuan",last_name = "Silvia",year = 1997, month = "December",day = 17, sex = "Female",
                    mobile_contact = "0431181629", email_address = "lluviagibran@gmail.com",education_degree = "Bachlor",
                    major  = "computer science",skill1 = "Outlook English Talent Competition in Shanghai area",skill2 = "College English Test Band 6", \
                    skill3 = "Financial English Certificate Test",description = "Be responsible for the overall processing,following-up and coordinating of Sales Order, \
                    Act as liaison between Sales and Production, Finance on various order management issues. Won the advanced collective award, and won the excellent organizer. \
                        Recruitment & Publicity, volunteer training. Dealing with emergencies, management organization and coordination",\
                    status = "activity")
                session.add(resume2)

            if line[4] == "Employer" and temp == 3:
                job1 = Job(user_token = hash_token, job_token = 1, label = "Recruitment of IT technical staff",
                company_name = "SoftWare", letter_require = "Yes", email_address = "velacstudio@gmail.com",employer_type = "Engineering",\
                requirement_degree = "College degree or above", requirement_skill = "English skill,c++,python", job_time = "Full Time",\
                salary = "$25k",job_location= "Sydney", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit.",status = "activity")
                session.add(job1)

                job2 = Job(user_token = hash_token, job_token = 2, label = "Recruitment of translator",
                company_name = "English Training", letter_require = "Yes", email_address = "velacstudio@gmail.com",employer_type = "Education & Training",\
                requirement_degree = "Master", requirement_skill = "English skill,French skill,German skill", job_time = "Part Time",\
                salary = "$20K",job_location= "Other", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit. Conduct English teaching according to British education system.",status = "activity")
                session.add(job2)

            if line[4] == "Employer" and temp == 5:
                job1 = Job(user_token = hash_token, job_token = 3, label = "Recruitment of market",
                company_name = "Target", letter_require = "Yes", email_address = "velacstudio@gmail.com",employer_type = "Engineering",\
                requirement_degree = "Doctor or above", requirement_skill = "Team work,c++,python", job_time = "Full Time",\
                salary = "$35k",job_location= "Sydney", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit.",status = "activity")
                session.add(job1)

                job2 = Job(user_token = hash_token, job_token = 4, label = "Recruitment of translator",
                company_name = "BigW", letter_require = "No", email_address = "velacstudio@gmail.com",employer_type = "Banking & Financial Service",\
                requirement_degree = "No degree requirement", requirement_skill = "English skill,French skill,German skill", job_time = "Part Time",\
                salary = "$15k",job_location= "Other", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit. Conduct English teaching according to British education system.",status = "activity")
                session.add(job2)

            if line[4] == "Employer" and temp == 6:
                job1 = Job(user_token = hash_token, job_token = 5, label = "Recruitment of network security",
                company_name = "BaiDu", letter_require = "Yes", email_address = "velacstudio@gmail.com",employer_type = "Engineering",\
                requirement_degree = "Doctor", requirement_skill = "Team work,c++,python", job_time = "Full Time",\
                salary = "$65k",job_location= "Brisbane", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit.",status = "activity")
                session.add(job1)

                job2 = Job(user_token = hash_token, job_token = 6, label = "Recruitment of Web design",
                company_name = "Google", letter_require = "Yes", email_address = "velacstudio@gmail.com",employer_type = "Engineering",\
                requirement_degree = "Bachelor", requirement_skill = "python,java,html", job_time = "Graduate Program",\
                salary = "$65k",job_location= "Sydney", description = "Related work experience in international organizations. Patient, careful, supportive. \
                Has strong team work spirit. Conduct English teaching according to British education system.",status = "activity")
                session.add(job2)

            if line[4] == "JobSeeker" and temp == 7:
                resume1 = Resume(user_token = hash_token, resume_token = 3, label = "Resume for Web Design",\
                    first_name = "Tom",last_name = "Jacket",year = 1997, month = "December",day = 17, sex = "Male",
                    mobile_contact = "044554629", email_address = "lluviagibran@gmail.com",education_degree = "Bachlor",
                    major  = "computer engineering",skill1 = "javascript",skill2 = "team work", skill3 = "python",description = \
                    "Be responsible for the overall processing,following-up and coordinating of Sales Order, \
                    Act as liaison between Sales and Production, Finance on various order management issues",\
                    status = "activity")
                session.add(resume1)
                resume2 = Resume(user_token = hash_token, resume_token = 4, label = "Resume for network security",\
                    first_name = "Tom",last_name = "Jacket",year = 1997, month = "December",day = 17, sex = "Male",
                    mobile_contact = "044554629", email_address = "lluviagibran@gmail.com",education_degree = "Master",
                    major  = "computer engineering",skill1 = "c",skill2 = "python", \
                    skill3 = "java",description = "Be responsible for the overall processing,following-up and coordinating of Sales Order, \
                    Act as liaison between Sales and Production, Finance on various order management issues. Won the advanced collective award, and won the excellent organizer. \
                        Recruitment & Publicity, volunteer training. Dealing with emergencies, management organization and coordination",\
                    status = "activity")
                session.add(resume2)

            temp = temp + 1

            session.add(user)
            #print(user.id)
            #if(line[4] == 'JobSeeker'):
                #jobseeker = JobSeeker(token=user.token)
                #session.add(jobseeker)
            #if (user.username == "admin"):
            #   user.token = '123'
            #session.add(user)
    session.commit()
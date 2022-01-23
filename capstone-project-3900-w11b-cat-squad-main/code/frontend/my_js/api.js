// it is the main to connect the backend and get the information from frontend
const API_URL = 'http://localhost:5000'

const getJSON = (path, options) =>
    fetch(path, options)
        .then(res => {
            return {
              status: res.status,
              object: res.json()
            }
        })
        .catch(err => console.warn(`API_ERROR: ${err.message}`));

export default class API {

    /**
     * Defaults to teh API URL
     * @param {string} url
     */
    constructor(url = API_URL) {
        this.url = url;
    }

    makeAPIRequest(path, request) {
        return getJSON(`${this.url}/${path}`, request);
    }

    //-------------------------------------------------------------------
    //------------------- Authentication Services -----------------------
    // Function 1: Login
    login(email, password) {
        console.log(1222);
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": email,
                "password": password
            })
        };
        return this.makeAPIRequest("auth/login", request);
    }
    signup(email, first_name, last_name, password, repassword, usertype){
        console.log(1222);
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": email,  
                "first_name": first_name,   
                "last_name": last_name,
                "password": password,
                "repassword": repassword,
                "usertype": usertype
            })
        }; 
        return this.makeAPIRequest("auth/signup", request);
    }
    //it function for jobseeker that can show the all of job which activity
    showalljob(){
        console.log(1222);
        let request = {
            method: "get",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        };
        return this.makeAPIRequest("jobseeker/ShowAllJob", request);
    }

    //it function for show employer, their jobs
    showuserjob(user_token,status){
        console.log(1222);
        console.log(user_token);
        console.log(status);
        console.log("check the succee");
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "status": status
            })
        }; 
        return this.makeAPIRequest("employer/ShowUserJob", request);
    }
    showresume(user_token,status){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "status": status
            })
        }; 
        return this.makeAPIRequest("jobseeker/ShowResume", request);
    }
    apply(user_token,status){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "status": status
            })
        }; 
        return this.makeAPIRequest("jobseeker/ShowApply", request);
    }
    receiveapply(user_token,status){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "status": status
            })
        }; 
        return this.makeAPIRequest("employer/ShowApply", request);
    }
    addresume(user_token,label, first_name,last_name, year, month, day, sex, 
        mobile_contact,email_address,education_degree,major, skill1,skill2,skill3,description){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
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
                "description": description
            })
        }; 
        return this.makeAPIRequest("jobseeker/Addresume", request);
    }
    addresumePDF(user_token,label, PDFpath){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "label": label,
                "PDFpath": PDFpath
            })
        }; 
        return this.makeAPIRequest("jobseeker/AddresumePDF", request);
    }



    addjob(user_token, label,company_name, letter_require,email_address,employer_type,requirement_degree
    ,requirement_skill,job_time,salary, job_location, description){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "label": label,
                "company_name": company_name,
                "letter_require": letter_require,
                "email_address": email_address,
                "employer_type": employer_type,
                "requirement_degree": requirement_degree,
                "requirement_skill": requirement_skill,
                "job_time": job_time,
                "salary": salary,
                "job_location": job_location,
                "description": description
            })
        }; 
        return this.makeAPIRequest("employer/Addjob", request);
    }
    jobdetail(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("employer/jobdetail", request);
    }
    applyjobdetail(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("jobseeker/jobdetail", request);
    }
    resumedetail(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token": resume_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("jobseeker/resumedetail", request);
    }
    applyresumedetail(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token": resume_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("employer/resumedetail", request);
    }
    modifyresume(user_token, resume_token, label,first_name,last_name, year, month,day,
        sex, mobile_contact,email_address,education_degree,
        major,skill1,skill2,skill3,description) {
            let request = {
                method: "post",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "user_token": user_token,
                    "resume_token": resume_token,
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
                    "skill1": skill1,
                    "skill2": skill2,
                    "skill3": skill3,
                    "major": major,
                    "description": description
                })
            }; 
            return this.makeAPIRequest("jobseeker/modifyresume", request);
    }
    modifyjob(user_token,job_token, label,company_name, letter_require,email_address,employer_type,requirement_degree
        ,requirement_skill,job_time,salary, job_location, description){
            let request = {
                method: "post",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "user_token": user_token,
                    "job_token": job_token,
                    "label": label,
                    "company_name": company_name,
                    "letter_require": letter_require,
                    "email_address": email_address,
                    "employer_type": employer_type,
                    "requirement_degree": requirement_degree,
                    "requirement_skill": requirement_skill,
                    "job_time": job_time,
                    "salary": salary,
                    "job_location": job_location,
                    "description": description
                })
            }; 
            return this.makeAPIRequest("employer/modifyjob", request);
    }
    jobdelete(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("employer/Deletejob", request);
    }
    resumedelete(user_token,resume_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token": resume_token,
                "user_token": user_token
            })
        }; 
        return this.makeAPIRequest("jobseeker/Deleteresume", request);
    }
    
    createapply(job_token,resume_token, user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token": user_token,
                "resume_token": resume_token
            })
        }; 
        return this.makeAPIRequest("jobseeker/CreateApply", request);
    }
    dealapply(user_token,apply_token, result){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "apply_token": apply_token,
                "result": result
            })
        }; 
        return this.makeAPIRequest("employer/DealApply", request);
    }
    searchjob(job_location, job_time,employer_type, company_name){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "location": job_location,
                "job_time": job_time,
                "employer_type": employer_type,
                "company_name": company_name
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/searchjob", request);
    }
    addsavedjob(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/addsavedjob", request);
    }
    deletesavedjob(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/deletesavedjob", request);
    }
    showsavedjob(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/showsavedjob", request);
    }
    checksavedjob(job_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "job_token": job_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/checksaved", request);
    }
    resumecheck(user_token, resume_token,apply_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token": user_token,
                "resume_token":resume_token,
                "apply_token": apply_token
            })
        
        }; 
        return this.makeAPIRequest("jobseeker/Resumecheck", request);
    }
    send_code(email,password){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": email,
                "password": password
            })
        
        }; 
        return this.makeAPIRequest("auth/send_code", request);
    }
    reset_password(email,code,password,repassword){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": email,
                "code": code,
                "password": password,
                "repassword": repassword
            })

        }; 
        return this.makeAPIRequest("auth/reset_password", request);
    }
    show_authdetail(token,email){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "token": token,
                "email": email
            })

        }; 
        return this.makeAPIRequest("auth/show_auth_detail", request);
    }
    edit_authdetail(token,firstname,lastname){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "token": token,
                "firstname": firstname,
                "lastname": lastname
            })

        }; 
        return this.makeAPIRequest("auth/edit_auth_detail", request);
    }
    change_password(token,oldpassword,newpassword,repassword){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "token": token,
                "oldpassword":oldpassword,
                "newpassword": newpassword,
                "repassword":repassword
            })

        }; 
        return this.makeAPIRequest("auth/change_password", request);
    }
    addsavedresume(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token": resume_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("agent/addsavedresume", request);
    }
    deletesavedresume(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token": resume_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("agent/deletesavedresume", request);
    }
    showsavedresume(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token":user_token,
                "resume_token": resume_token
            })
        
        }; 
        return this.makeAPIRequest("agent/showsavedresume", request);
    }
    checksavedresume(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "user_token":user_token,
                "resume_token": resume_token
            })
        
        }; 
        return this.makeAPIRequest("agent/checkresumesaved", request);
    }
    showallresume(){
        console.log(1222);
        let request = {
            method: "get",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        };
        return this.makeAPIRequest("agent/ShowAllResume", request);
    }
    searchresume(skill,status){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "skill":skill,
                "status":status
            })
        
        }; 
        return this.makeAPIRequest("agent/searchResume", request);
    }
    showresumedetail(resume_token,user_token){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "resume_token":resume_token,
                "user_token":user_token
            })
        
        }; 
        return this.makeAPIRequest("agent/resumedetail", request);
    }
    send_email(email,textin1,textin2,head){
        let request = {
            method: "post",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email":email,
                "textin1":textin1,
                "textin2":textin2,
                "head":head
            })
        
        }; 
        return this.makeAPIRequest("auth/send_email", request);
    }
}
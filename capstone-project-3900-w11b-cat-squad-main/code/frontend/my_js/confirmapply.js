//The file main to show the resumes list about the user, it will get the information from localstorage about the 
//user_token and job_token to get the resumes list according this list, and saved the apply information to the database
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const user_token = localStorage.getItem("token");
const job_token = localStorage.getItem("job_token");
console.log(user_token);
console.log(job_token);
console.log("--------------");
const status = "activity";


api.showresume(user_token,status)
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("Please add a resume to apply this job.");
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("You have no resume , please add it!");
    }

})
.then((object) => {

    var back = document.getElementById("back");
    back.addEventListener("click",function(){
        window.location.href = "JobseekerHomepage.html";
    });
    var select = document.getElementById("select");

    for(var i = 1; object[i] != undefined; i++){
        select.options.add(new Option(object[i].label, object[i].resume_token));
    }
    var  apply_btn = document.getElementById("confirm");
    apply_btn.addEventListener("click",function(){
        var obj=document.getElementById('select'); 
        var index=obj.selectedIndex;
        var val = obj.options[index].value;
        if(val == "Choose your resume"){
            alert("You need to choose one resume!");
        }else{
            api.createapply(job_token,val, user_token)
            .then((response) => {
                // Case 1: Successfully Login
                if (response.status === 200){
                    return response.object;
                }
                // Case 2: Missing inputs
                else if (response.status === 400){
                    alert("Missing inputs");
                }
                // Case 3: Invalid inputs
                else if (response.status === 403){
                    alert("You have no resume , please add it!");
                }
            
            })
            .then((object) => {
              
                console.log(object);
                var r = confirm("Job Applied!");
                if(r == true){
                    window.location.href = "appliedjobs.html";
                    var textin1 = "Dear recruiter,";
                    var textin2 ="You just received an job application. Login your account to check it out.";
                    var head = "You received a new application!";
                    api.send_email(object.user_email,textin1,textin2,head)        
                    .then((response) => {
                        if (response.status === 200){
                            return response.object;
                        }
                        else if (response.status === 400){
                            alert("Missing inputs");
                            
                        }
                        else if (response.status === 403){
                            alert("Invalid inputs");
                        }
                    })
                }else{
                    window.location.href = "appliedjobs.html";
                    var textin1 = "Dear recruiter,";
                    var textin2 ="You just received an job application. Login your account to check it out.";
                    var head = "You received a new application!";
                    api.send_email(object.user_email,textin1,textin2,head)        
                    .then((response) => {
                        if (response.status === 200){
                            return response.object;
                        }
                        else if (response.status === 400){
                            alert("Missing inputs");
                            
                        }
                        else if (response.status === 403){
                            alert("Invalid inputs");
                        }
                    })
                }

            })
        }
    });
    
});

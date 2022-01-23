// it is make a decision for the apply to employer.
//it is main to control the resume is the pdf resume.
// and send email to the applyer.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const user_token = localStorage.getItem("token");
const resume_token = localStorage.getItem("resume_token");

const pdf = document.getElementById("pdf");
pdf.src = localStorage.getItem("PDFpath");
const tab = document.getElementById("new");
tab.href = localStorage.getItem("PDFpath");

api.applyresumedetail(resume_token,user_token)
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
        alert("Invalid inputs");
    }

})
.then((object) => {
    var user_emial = object.user_email;
    var pass_btn = document.getElementById("Pass");
    pass_btn.addEventListener("click",function(){
        const apply_token = localStorage.getItem("apply_token");
        const result = "pass";
        console.log(apply_token,result);
        api.dealapply(user_token,apply_token, result)
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
                alert("Invalid inputs");
            }
        
        })
        .then((object) => {
            var r = confirm("Application completed");
            if(r == true){
                window.location.href = "receivedapplication.html";
            }else{
                window.location.href = "receivedapplication.html";
            }
            var textin1 = "Dear jobseeker,";
            var textin2 ="Great news! Your application has been accepted. Please note that every company has their own recruiting process. You may receive email updates from the company regarding the next step.";
            var head = "Application Result Released!";
            api.send_email(user_emial,textin1,textin2,head)        
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
        })
    });
    
    var back = document.getElementById("Reject");
    back.addEventListener("click",function(){
        const apply_token = localStorage.getItem("apply_token");
        const result = "reject";
        api.dealapply(user_token,apply_token, result)
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
                alert("Invalid inputs");
            }
        
        })
        .then((object) => {
            var r = confirm("Application completed");
            if(r == true){
                window.location.href = "receivedapplication.html";
            }else{
                window.location.href = "receivedapplication.html";
            }
            var textin1 = "Dear jobseeker,";
            var textin2 ="Not so lucky this time as the application will not be process further. But hey, don't be sad. YOU ARE GOOD. Try to provide more attractive resume or search for more suitable jobs at our website.";
            var head = "Application Result Released!";

            api.send_email(user_emial,textin1,textin2,head)        
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
        })
    });
    
});

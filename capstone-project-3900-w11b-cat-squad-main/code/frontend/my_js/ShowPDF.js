// it is the main to show PDF resume for the jobseekers.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const resume_token = window.localStorage.getItem("resume_token");
const user_token = window.localStorage.getItem("token");
const pdf = document.getElementById("pdf");
api.resumedetail(resume_token,user_token)
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
    console.log(object.PDFpath);
    pdf.src = object.PDFpath;
    
    document.getElementById("back").addEventListener("click",function(){
        window.location.href = "resume.html";
    });
    let delete_btn = document.getElementById("delete");
    delete_btn.addEventListener("click",function(){
        console.log(delete_btn);
        let user_token = localStorage.getItem("token");
        let resume_token = localStorage.getItem("resume_token");
        console.log("eneter in delete function");
        api.resumedelete(user_token,resume_token)
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
            console.log(object.status);
            console.log("enter in delete");
            var v = confirm("Deleted");
            if(v == true){
                window.location.href = "resume.html";
            }else{
                window.location.href = "resume.html";
            }
        })
    })
});


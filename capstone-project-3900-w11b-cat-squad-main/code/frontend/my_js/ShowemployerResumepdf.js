// it is the main to show the pdf resume for employer.
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

});
const back = document.getElementById("back");
back.addEventListener("click",function(){
    window.location.href = "historyApplication.html";
});
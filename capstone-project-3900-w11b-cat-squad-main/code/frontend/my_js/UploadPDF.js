// it is the main to control the upload PDF for jobseekers.
import API from "./api.js";

const api  = new API();

const submit = document.getElementById("submit");
const user_token = window.localStorage.getItem("token");
submit.addEventListener("click",function(){
    let label = document.getElementById("pdftitle").value;
    let resume = document.getElementById("resume").files[0].name;
    console.log(resume);
    console.log(label);
    console.log(user_token);
    api.addresumePDF(user_token,label, resume)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            return response.object;
        }
        // Case 2: Missing inputs
        else if (response.status === 400){
            alert("Missing Fields");
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid field(s)");

        }
        else if (response.status === 405){
            alert("The title cannot be empty!");
        }
        else if (response.status === 406){
            alert("The document cannot be empty!");
        }
    })
    .then((object) => {
        localStorage.setItem("resume_token",object.token);
        alert("Resume uploaded");
        window.location.href = "ResumePDF.html";
    
    })
    label.reset;
    resume.reset;
    
});
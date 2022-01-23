// it is the main to show the history apply list wich is passed for Employer.
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
console.log(user_token);

console.log("--------------");

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
    document.getElementById("p1").innerHTML = `<b>Label: </b> ${object.label}`;
    document.getElementById("p2").innerHTML = `<b>First Name: </b> ${object.first_name}`;
    document.getElementById("p3").innerHTML = `<b>Last Name: </b> ${object.last_name}`;
    document.getElementById("p4").innerHTML = `<b>Mobile Contact: </b> ${object.mobile_contact}`;
    document.getElementById("p5").innerHTML = `<b>Email Address: </b> ${object.email_address}   `+`<a href=mailto:${object.email_address} style="padding-right: 20px; font-size: 36px; margin: right; id = "contact" >&#x1f4e7;</a>`;
    document.getElementById("p6").innerHTML = `<b>Birth Date: </b> ${object.day}/${object.month}/${object.year}`;
    document.getElementById("p7").innerHTML = `<b>Sex: </b> ${object.sex}`;
    document.getElementById("p8").innerHTML = `<b>Education Degree: </b> ${object.education_degree}`;
    document.getElementById("p9").innerHTML = `<b>Major: </b> ${object.major}`;
    document.getElementById("p10").innerHTML = `<b>Skill: </b> ${object.skill1}, ${object.skill2}, ${object.skill3}`;
    document.getElementById("p11").innerHTML = `<b>Description: </b> ${object.description}`;
    
    const back = document.getElementById("back");
    back.addEventListener("click",function(){
        window.location.href = "historyApplication.html";
    });
});

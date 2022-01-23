// This file control job ad posting function
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
const post = document.getElementById("post");
console.log("generate button");
console.log(post);
post.addEventListener("click", function(){

    let radio = document.getElementsByName("tigger");
    let letter_require = null;
    for (var i=0; i < radio.length; i++) {
        if (radio[i].checked) {
            letter_require = radio[i].value;
        }
    }

    let form1 = document.getElementById("form");
    console.log(form1);


    let user_token = localStorage.getItem("token");

    let label = document.getElementById("job_title").value;
    let company_name = document.getElementById("company_name").value;
    let email_address = document.getElementById("email").value;
    let employer_type = document.getElementById("employer_type").value;
    let requirement_degree = document.getElementById("require_degree").value;
    let job_time = document.getElementById("job_time").value;
    let salary = document.getElementById("salary").value;
    let job_location = document.getElementById("location").value;
    let temp = ",";
    let requirement_skill = document.getElementById("skill1").value +temp+ document.getElementById("skill2").value +temp+ document.getElementById("skill3").value;

    let description = document.getElementById("detail").value;

    api.addjob(user_token, label,company_name, letter_require,email_address,employer_type,requirement_degree
        ,requirement_skill,job_time,salary, job_location, description)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            form1.reset();
            return response.object;
        }
        // Case 2: Missing inputs
        else if (response.status === 400){
            alert("Missing inputs");
            
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            document.getElementById("email").value = '';
            alert("Invalid email, please enter correct email!");

        }

    })
    .then((object) => {
        sessionStorage.setItem("currPage", "classList");
        localStorage.setItem("job_token",object.token);
        window.location.href = "jobposted.html";
    });
});



// This file controls generating resume function 
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}

const api  = new API();
const generate = document.getElementById("Generate");
console.log("generate button");
console.log(generate);
generate.addEventListener("click", function(){

    let radio = document.getElementsByName("tigger");
    let sex = null;
    for (var i=0; i < radio.length; i++) {
        if (radio[i].checked) {
            sex = radio[i].value;
            console.log(sex);
        }
    }
    let form1 = document.getElementById("form");
    let form3 = document.getElementById("form3");
    console.log(form1);

    let user_token = localStorage.getItem("token");
    let label = document.getElementById("label").value;
    let first_name = document.getElementById("firstname").value;
    let last_name = document.getElementById("secondname").value;
    let year = document.getElementById("year").value;
    let month = document.getElementById("month").value;
    let day = document.getElementById("day").value;
    let mobile_contact = document.getElementById("contactnum").value;
    let email_address = document.getElementById("email").value;
    let education_degree = document.getElementById("degree").value;
    let major = document.getElementById("major").value;
    let skill1 = document.getElementById("skill1").value
    let skill2 = document.getElementById("skill2").value 
    let skill3 = document.getElementById("skill3").value;

    let description = document.getElementById("detail").value;

    api.addresume(user_token,label, first_name,last_name, year, month, day, sex,
        mobile_contact,email_address,education_degree,major, skill1,skill2,skill3,description)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            form1.reset();
            form3.reset();
            window.location.reload();
            return response.object;
        }
        // Case 2: Missing inputs
        else if (response.status === 400){
            alert("Missing Fields");
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid email");
            document.getElementById("email").value = "";
        }

    })
    .then((object) => {
        sessionStorage.setItem("currPage", "classList");
        localStorage.setItem("resume_token",object.token);
        window.location.href = "Resumeposted.html";

    });
});



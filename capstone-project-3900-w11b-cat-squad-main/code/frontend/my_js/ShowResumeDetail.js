// it is the mian to show the resume detail and can edit or delete it for the jobseekers.
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
console.log(resume_token);
console.log("--------------");


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

    let radio = document.getElementsByName("tigger");
    for (var i=0; i < radio.length; i++) {
        if (radio[i].value == object.sex) {
            radio[i].checked = true;
        }
    }
    document.getElementById("back").addEventListener("click",function(){
        window.location.href = "resume.html";
    });
    document.getElementById("label").value = object.label;
    document.getElementById("firstname").value  = object.first_name;
    document.getElementById("secondname").value = object.last_name;
    document.getElementById("year").value = object.year;
    document.getElementById("month").value = object.month;
    document.getElementById("day").value = object.day;
    document.getElementById("contactnum").value = object.mobile_contact;
    document.getElementById("email").value = object.email_address;
    document.getElementById("degree").value = object.education_degree;
    document.getElementById("major").value = object.major;

    document.getElementById("detail").value = object.description;
    document.getElementById("skill1").value = object.skill1;
    document.getElementById("skill2").value =object.skill2;
    document.getElementById("skill3").value = object.skill3;


    document.getElementById("label").readOnly = true;
    document.getElementById("firstname").readOnly  = true;
    document.getElementById("secondname").readOnly = true;
    document.getElementById("year").disabled = true;
    document.getElementById("month").disabled= true;
    document.getElementById("day").disabled= true;
    document.getElementById("contactnum").readOnly = true;
    document.getElementById("email").readOnly = true;
    document.getElementById("degree").readOnly = true;
    document.getElementById("major").readOnly =  true;
    document.getElementById("skill1").readOnly = true;
    document.getElementById("skill2").readOnly = true;
    document.getElementById("skill3").readOnly = true;
    document.getElementById("detail").readOnly = true;

/*
    document.getElementById("skill1").value = object.skill1;
    document.getElementById("skill2").value =object.skill2;
    document.getElementById("skill3").value = object.skill3;
*/

    radio = document.getElementsByName("tigger");
    for (var i=0; i < radio.length; i++) {
        if (radio[i].value == object.letter_require) {
            radio[i].checked = true;
        }
        radio[i].disabled = true;
    }

    var edit = document.getElementById("edit");

    edit.addEventListener("click",function(){
        if(edit.innerHTML == "edit"){
            document.getElementById("label").readOnly = false;
            document.getElementById("firstname").readOnly  = false;
            document.getElementById("secondname").readOnly = false;
            document.getElementById("year").disabled = false;
            document.getElementById("month").disabled = false;
            document.getElementById("day").disabled = false;
            document.getElementById("contactnum").readOnly = false;
            document.getElementById("email").readOnly = false;
            document.getElementById("degree").readOnly = false;
            document.getElementById("major").readOnly =  false;
            
            document.getElementById("skill1").readOnly = false;
            document.getElementById("skill2").readOnly = false;
            document.getElementById("skill3").readOnly = false;
        
            document.getElementById("detail").readOnly = false;

            let radio = document.getElementsByName("tigger");
            console.log("is is radio ");
            console.log(radio);
            for (var i=0; i < radio.length; i++) {
                radio[i].disabled = false;
                console.log(radio[i].disabled);
            }
            radio.disabled = false;
            edit.innerHTML = "save";
            console.log("eedit.innet:" + edit.innerHTML);
            edit.style.color = "Green";
        }
        else{
            let radio = document.getElementsByName("tigger");
            let letter_require = null;
            for (var i=0; i < radio.length; i++) {
                if (radio[i].checked) {
                    letter_require = radio[i].value;
                }
            }
            let user_token = localStorage.getItem("token");
            let resume_token = localStorage.getItem("resume_token");
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
            let skill1 = document.getElementById("skill1").value;
            let skill2 = document.getElementById("skill2").value;
            let skill3 = document.getElementById("skill3").value;
            let description = document.getElementById("detail").value;

            radio = document.getElementsByName("tigger");
            let sex = null;
            for (var i=0; i < radio.length; i++) {
                if (radio[i].checked) {
                    sex = radio[i].value;
                }
            }
            api.modifyresume(user_token, resume_token, label,first_name,last_name, year, month,day,
                sex, mobile_contact,email_address,education_degree,
                major,skill1,skill2,skill3,description) 
            .then((response) => {
                // Case 1: Successfully Login
                if (response.status === 200){
                    document.getElementById("label").readOnly = true;
                    document.getElementById("firstname").readOnly  = true;
                    document.getElementById("secondname").readOnly = true;
                    document.getElementById("year").disabled= true;
                    document.getElementById("month").disabled = true;
                    document.getElementById("day").disabled = true;
                    document.getElementById("contactnum").readOnly = true;
                    document.getElementById("email").readOnly = true;
                    document.getElementById("degree").readOnly = true;
                    document.getElementById("major").readOnly =  true;
    
                    document.getElementById("skill1").readOnly = true;
                    document.getElementById("skill2").readOnly = true;
                    document.getElementById("skill3").readOnly = true;
    
                    document.getElementById("detail").readOnly = true;
                    
    
                    radio = document.getElementsByName("tigger");
                    for (var i=0; i < radio.length; i++) {
                        radio[i].disabled = true;
                    }
                    edit.innerHTML = "edit";
                    edit.style.color = "White";
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
                localStorage.setItem("job_token",object.token);
            });

        }

        
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
function pushHistory() { 
    var state = { 
    title: "title", 
    url: "#"
    }; 

    window.history.pushState(state, "title", "#"); 
}
pushHistory();
window.addEventListener("popstate", function(e) { 
    if(edit.innerHTML == "save"){
        var v = confirm("Are you sure you want to go back? Your changes will not be saved.");
        if(v == true){
            history.back();
        }
    }
    }, false);

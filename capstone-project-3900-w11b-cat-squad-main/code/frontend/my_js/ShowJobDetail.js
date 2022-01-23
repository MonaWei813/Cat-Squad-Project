// it is the  main to show job detail and can edit or delete it for employer.
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

api.jobdetail(job_token,user_token)
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
    document.getElementById("back").addEventListener("click",function(){
        window.location.href = "jobad.html";
    });

    document.getElementById("job_title").value = object.label;
    document.getElementById("company_name").value = object.company_name;
    document.getElementById("email").value = object.email_address;
    document.getElementById("location").value = object.job_location;
    document.getElementById("employer_type").value = object.employer_type;
    document.getElementById("salary").value = object.salary;
    document.getElementById("job_time").value = object.job_time;
    document.getElementById("require_degree").value = object.requirement_degree;
    var temp = object.requirement_skill;
    document.getElementById("require_degree")
    var m = temp.split(",");
    console.log(temp);
    document.getElementById("skill1").value = m[0];
    document.getElementById("skill2").value = m[1];
    document.getElementById("skill3").value = m[2];

    document.getElementById("skill1").readOnly = true;
    document.getElementById("skill2").readOnly = true;
    document.getElementById("skill3").readOnly = true;
    document.getElementById("job_title").readOnly = true;
    document.getElementById("company_name").readOnly = true;
    document.getElementById("email").readOnly = true;
    document.getElementById("location").disabled = true;
    document.getElementById("employer_type").disabled = true;
    document.getElementById("salary").readOnly = true;
    document.getElementById("job_time").disabled = true;
    document.getElementById("require_degree").readOnly = true;

    let radio = document.getElementsByName("tigger");

    for (var i=0; i < radio.length; i++) {
        if (radio[i].value == object.letter_require) {
            radio[i].checked = true;
        }
        radio[i].disabled = true;
    }

    document.getElementById("detail").value = object.description;
    document.getElementById("detail").readOnly = true;

    var edit = document.getElementById("edit");

    edit.addEventListener("click",function(){
        if(edit.innerHTML == "edit"){
            document.getElementById("skill1").readOnly = false;
            document.getElementById("skill2").readOnly = false;
            document.getElementById("skill3").readOnly = false;
            document.getElementById("job_title").readOnly = false;
            document.getElementById("company_name").readOnly = false;
            document.getElementById("email").readOnly = false;
            document.getElementById("location").disabled = false;
            document.getElementById("employer_type").disabled = false;
            document.getElementById("salary").readOnly = false;
            document.getElementById("job_time").disabled = false;
            document.getElementById("require_degree").readOnly = false; 
            document.getElementById("detail").readOnly = false;

            let radio = document.getElementsByName("tigger");
            console.log("it is radio ");
            console.log(radio);
            for (var i=0; i < radio.length; i++) {
                radio[i].disabled = false;
                console.log(radio[i].disabled);
            }
            radio.disabled = false;
            edit.innerHTML = "save";
            console.log("edit.innet:" + edit.innerHTML);
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
            let job_token = localStorage.getItem("job_token");
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

            api.modifyjob(user_token,job_token, label,company_name, letter_require,email_address,employer_type,requirement_degree
                ,requirement_skill,job_time,salary, job_location, description)
            .then((response) => {
                // Case 1: Successfully Login
                if (response.status === 200){
                    document.getElementById("skill1").readOnly = true;
                    document.getElementById("skill2").readOnly = true;
                    document.getElementById("skill3").readOnly = true;
                    document.getElementById("job_title").readOnly = true;
                    document.getElementById("company_name").readOnly = true;
                    document.getElementById("email").readOnly = true;
                    document.getElementById("location").disabled = true;
                    document.getElementById("employer_type").disabled = true;
                    document.getElementById("salary").readOnly = true;
                    document.getElementById("job_time").disabled= true;
                    document.getElementById("require_degree").readOnly = true;
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
                else if (response.status === 406){
                    document.getElementById("email").value = '';
                    alert("Invalid email");
                }
            
            })
            .then((object) => {
                localStorage.setItem("job_token",object.token);
            })
 

        }

        
    });
    let delete_btn = document.getElementById("delete");
    delete_btn.addEventListener("click",function(){
        let user_token = localStorage.getItem("token");
        let job_token = localStorage.getItem("job_token");
        api.jobdelete(job_token,user_token)
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
                alert("Invalid email");
            }
        
        })
        .then((object) => {
            console.log(object.status);
            var v = confirm("Deleted!");
            if(v == true){
                window.location.href = "jobad.html";
            }else{
                window.location.href = "jobad.html";
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
        var v = confirm("Are you sure you want to go back? Your changes will not be saved!");
        if(v == true){
            history.back();
        }
    }
    }, false);

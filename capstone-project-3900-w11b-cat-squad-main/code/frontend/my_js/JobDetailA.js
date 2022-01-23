//it is the main show the job detail for the jobseekers.
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
/*
function myfunction(id, labelname,label){
    var rows = document.getElementById(id).rows.length;
    var x=document.getElementById(id).insertRow(rows);
    var y=x.insertCell(0); 
    y.innerHTML= `${labelname}: `+ label;
}*/

api.applyjobdetail(job_token,user_token)
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
    document.getElementById("p1").innerHTML = `Company Name: ${object.company_name}`;
    document.getElementById("p2").innerHTML = `Email Address: ${object.email_address}`;
    document.getElementById("p3").innerHTML = `Classification: ${object.employer_type}`;
    document.getElementById("p4").innerHTML = `Required Degree: ${object.requirement_degree}`;
    document.getElementById("p5").innerHTML = `Most Wanted Skills: ${object.requirement_skill}`;
    document.getElementById("p6").innerHTML = `Employment Type: ${object.job_time}`;
    document.getElementById("p7").innerHTML = `Job Location: ${object.job_location}`;
    document.getElementById("p8").innerHTML = `Salary: ${object.salary}`;
    document.getElementById("p9").innerHTML = `Cover Letter: ${object.letter_require}`;
    document.getElementById("p10").innerHTML = `Description: ${object.description}`;
    const save = document.getElementById("saved");
    api.checksavedjob(job_token,user_token)
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
        if(object.status =="exist"){
            save.style.color = "darkorange";
        }else{
            save.style.color = "#ccc";
        }
    })
    console.log(save);
    save.addEventListener("click",function(){
        if(save.style.color == "darkorange"){
            var v = confirm("Do you want to remove this job from saved jobs?");
            if(v == true){
                save.style.color = "#ccc";
                api.deletesavedjob(job_token,user_token)
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
            }

        }else{
            save.style.color = "darkorange";
            api.addsavedjob(job_token,user_token)
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
            confirm("Job saved!");

        }
            
            
    });

    var status = "activity";
    api.apply(user_token,status)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            return response.object;
        }
    
    })

    var back = document.getElementById("back");
    back.addEventListener("click",function(){
        window.location.href = "JobseekerHomepage.html";
    });
    
});

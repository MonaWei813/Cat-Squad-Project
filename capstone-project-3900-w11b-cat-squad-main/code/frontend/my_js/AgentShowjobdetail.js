// The file is main to control show all of job details to Agent
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
    document.getElementById("p1").innerHTML = `<b>Company Name: </b>${object.company_name}`;
    document.getElementById("p2").innerHTML = `<b>Email Address:</b> ${object.email_address}` +'&nbsp;&nbsp;&nbsp;<a href= ${object.email_address}` style="padding-right: 20px; font-size: 36px;" id = "contact" >&#x1f4e7;</a>';
    document.getElementById("p3").innerHTML = `<b>Classification: </b>${object.employer_type}`;
    document.getElementById("p4").innerHTML = `<b>Required Degree: </b>${object.requirement_degree}`;
    document.getElementById("p5").innerHTML = `<b>Most Wanted Skills: </b>${object.requirement_skill}`;
    document.getElementById("p6").innerHTML = `<b>Employment Type: </b>${object.job_time}`;
    document.getElementById("p7").innerHTML = `<b>Job Location: </b>${object.job_location}`;
    document.getElementById("p8").innerHTML = `<b>Salary: </b>${object.salary}`;
    document.getElementById("p9").innerHTML = `<b>Cover Letter: </b>${object.letter_require}`;
    document.getElementById("p10").innerHTML = `<b>Description: </b>${object.description}`;
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

    var back = document.getElementById("back");
    back.addEventListener("click",function(){
        window.location.href = "AgentHomePage2.html";
    });
    const contact = document.getElementById("contact");
    contact.href = `mailto:${object.email_address}`;
    
});

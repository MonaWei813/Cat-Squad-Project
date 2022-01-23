//it is the main to show user resume lists which made by user for jobseekers.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const user_token = localStorage.getItem("token");
const status = 'activity';
console.log(user_token);
console.log(status);
console.log("--------------");

api.showresume(user_token,status)
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
        alert("Please add your resume.");
    }

})
.then((object) => {
    console.log(document.getElementById('myTable'));
    document.getElementById('myTable').rules="rows";
    document.getElementById('myTable').style.fontSize="15px";
    document.getElementById('myTable').style.color="Black";
    document.getElementById('myTable').style.lineHeight="30px";
    document.getElementById('myTable').style.width = "750px";
    document.getElementById('myTable').style.margin = "01em";
    document.getElementById("myTable").style.borderSpacing ="9px";
    console.log("object: "+object);
    var i = 1;
    if(object == undefined){
        document.getElementById("myTable").createCaption().innerHTML = "You have no resume!";
    }else{
        for(i = 1; object[i] != undefined; i++){
            console.log(object[i].PDFpath);
            if(object[i].PDFpath != null){
                console.log("enter in loop");
                var rows = document.getElementById("myTable").rows.length;
                var x=document.getElementById('myTable').insertRow(rows);
                console.log(object);
                var y=x.insertCell(0);
                var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
                y.innerHTML= heading;
                var b = document.getElementById(`${object[i].resume_token}`);
                console.log("enter in PDF not null")
                b.onclick = (function close(b){
                    return function () {
                        localStorage.setItem("resume_token", b.id);
                        window.location.href = "ResumePDF.html";
                    }
                })(b);
                
            }else{
                console.log("enter in loop");
                var rows = document.getElementById("myTable").rows.length;
                var x=document.getElementById('myTable').insertRow(rows);
                console.log(object);
                var y=x.insertCell(0);
                var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
                y.innerHTML= heading;
                var b = document.getElementById(`${object[i].resume_token}`);
                b.onclick = (function close(b){
                    return function () {
                        localStorage.setItem("resume_token", b.id);
                        window.location.href = "Resumeposted.html";
                    }
                })(b);
            }
 

            

        }
    }
/*
    var x=document.getElementById('myTable').createCaption();
    x.innerHTML="Result for all of jobs!";
    x.style.fontSize = "30px";*/

});

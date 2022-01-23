//The file  main to agent show saved jobseeker lists to Agnet.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const user_token = window.localStorage.getItem("token");
const resume_token = "";
api.showsavedresume(resume_token,user_token)
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
        //login_form.reset();
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("No saved jobseekers!");
   
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("No saved jobseekers!");
    }

})
.then((object) => {
    console.log(object);
    document.getElementById('myTable').rules="rows";
    document.getElementById('myTable').style.fontSize="15px";
    document.getElementById('myTable').style.color="Black";
    document.getElementById('myTable').style.lineHeight="30px";
    document.getElementById('myTable').style.width = "750px";
    document.getElementById('myTable').style.margin = "01em";
    document.getElementById("myTable").style.borderSpacing ="9px";

    var i = 1;
    for(i = 1; object[i] != undefined; i++){
        var rows = document.getElementById("myTable").rows.length;
        var x=document.getElementById('myTable').insertRow(rows);
        var y=x.insertCell(0);

        var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
        y.innerHTML=heading+"<br/>"+"<b>Education Degree:</b>" +object[i].education_degree +"<br/>"+
          "<b>Major: </b>"+object[i].major+"<br/>"+"<b>Skills: </b>"+object[i].skill+"<br/>";
        var b = document.getElementById(`${object[i].resume_token}`);
        b.onclick = (function close(b){
            return function () {
                localStorage.setItem("resume_token", b.id);
                window.location.href = "Agentjobseekerdetail.html";
                console.log(b.id);
            }
        })(b);

    }

});

// it is the main to show the jobs for the jobseekers, that can apply function
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}

const api  = new API();
console.log("--------------");
const user_token = window.localStorage.getItem("token");
const job_token = " ";
api.showsavedjob(job_token,user_token)
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
        //login_form.reset();
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("Missing input");
   
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("Invalid input");
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
        var heading = `<a id = "${object[i].job_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
        y.innerHTML=heading+"<br/>"+"<b>Company name :</b>" +object[i].company_name +"<br/>"+
          "<b>Required degree: </b>"+object[i].requirement_degree+"<br/>"+"<b>Most wanted skills: </b>"+object[i].requirement_skill+"<br/>";
        var b = document.getElementById(`${object[i].job_token}`);
        b.onclick = (function close(b){
            return function () {
                localStorage.setItem("job_token", b.id);
                window.location.href = "job.html";
                console.log(b.id);
            }
        })(b);

    }

});

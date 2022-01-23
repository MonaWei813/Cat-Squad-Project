//it is the main to show the apply list for the jobseekers.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}

const api  = new API();
console.log("--------------");
const user_token = localStorage.getItem("token");
const status = "activity";
console.log(user_token,status);

api.apply(user_token,status)
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
        //login_form.reset();
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("Missing Username/Password");
   
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("Invalid Username/Password");
    }

})
.then((object) => {
    console.log(object);
    document.getElementById('myTable').rules="rows";
    document.getElementById('myTable').style.fontSize="15px";
    document.getElementById('myTable').style.color="Black";
    document.getElementById('myTable').style.lineHeight="30px";
    document.getElementById('myTable').style.width = "750px";
    document.getElementById('myTable').style.height= "100px";
    document.getElementById('myTable').style.margin = "01em";
    document.getElementById("myTable").style.borderSpacing ="9px";

    var i = 1;
    for(i = 1; object[i] != undefined; i++){
        var rows = document.getElementById("myTable").rows.length;
        var x=document.getElementById('myTable').insertRow(rows);
        var y=x.insertCell(0);
        var heading = `<a id = "${object[i].job_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
        if(object[i].status == "marked"){
            if(object[i].result == "pass"){
                y.innerHTML = heading+"<br/>"+"<b>Status :</b>" +"Application complete" +"<br/>"+"<b>Congratulations! Your application is accepted! The Employer will contact you shortly!</b>"+"<br/>";
            }else{
                y.innerHTML = heading+"<br/>"+"<b>Status :</b>" +"Application complete" +"<br/>"+"<b> Sorry! Your application was not accpeted!</b>"+"<br/>";
            }
        }else{
            console.log("it is activity");
            y.innerHTML = heading+"<br/>"+"<b>Status :</b>" +"Processing" +"<br/>"+"<b>Please be patient. Your application is under review. </b>"+"<br/>";
        }

        var b = document.getElementById(`${object[i].job_token}`);
        b.value = object[i].apply_token;
        console.log(b);
  
        if(b != null){
            b.onclick = (function close(b){
                return function () {
                    localStorage.setItem("job_token", b.id);
                    localStorage.setItem("apply_token", b.value);
                    window.location.href = "ShowJobDetailA.html";
                }
            })(b);
        }
    }

    /*
    var x=document.getElementById('myTable').createCaption();
    x.innerHTML="Result for all of jobs!";
    x.style.fontSize = "30px";*/

});

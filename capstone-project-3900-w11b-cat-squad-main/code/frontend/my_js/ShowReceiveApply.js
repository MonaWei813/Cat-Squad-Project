//Show received Apply that waiting for the employer to make desicion.
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

api.receiveapply(user_token,status)
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
    document.getElementById('myTable').style.margin = "01em";
    document.getElementById("myTable").style.borderSpacing ="9px";

    var i = 1;
    for(i = 1; object[i] != undefined; i++){
        var rows = document.getElementById("myTable").rows.length;
        var x=document.getElementById('myTable').insertRow(rows);
        var y=x.insertCell(0);
        if(object[i].status == "activity"){
            var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\"> Apply for ${object[i].label}</a>`;
            y.innerHTML = heading+"<br/>"+"<b>Status :</b>" +"Processing" +"<br/>"+"<b>Waiting for your decision! </b>"+"<br/>";
        }

        var apply_token = object[i].apply_token
        var b = document.getElementById(`${object[i].resume_token}`);
        console.log(b);
   
        api.resumecheck(user_token,b.id,apply_token)
        .then((response) => {
                // Case 1: Successfully Login
            if (response.status === 200){
                return response.object;
            }

        })
        .then((object) => {
            if(object.check == "no"){
                var btn = document.getElementById(object.resume_token);
                btn.onclick = (function close(btn){
                    return function () {
                        localStorage.setItem("resume_token", btn.id);
                        localStorage.setItem("apply_token", object.apply_token);
                        window.location.href = "dealapply.html";
                    }
                })(btn);
            }else{
                console.log(object.resume_token);
                var btn = document.getElementById(object.resume_token);
                btn.onclick = (function close(btn){
                    return function () {
                        localStorage.setItem("resume_token", btn.id);
                        localStorage.setItem("apply_token", object.apply_token);
                        localStorage.setItem("PDFpath",object.PDFpath);
                        window.location.href = "dealapplypdf.html";
                    }
                })(btn);
            
            }
            
        }); 

    }

    /*
    var x=document.getElementById('myTable').createCaption();
    x.innerHTML="Result for all of jobs!";
    x.style.fontSize = "30px";*/

});

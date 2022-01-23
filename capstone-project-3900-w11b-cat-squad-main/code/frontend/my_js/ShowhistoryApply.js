//it is the mian to show history apply lists for jobseeker.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
const user_token = localStorage.getItem("token");
const status = "marked";
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

    if(object != undefined){
        var i = 1;
        for(i = 1; object[i] != undefined; i++){
            var rows = document.getElementById("myTable").rows.length;
            var x=document.getElementById('myTable').insertRow(rows);
            var y=x.insertCell(0);
            if(object[i].status == "marked" && object[i].result== "pass"){
                var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\"> Apply for ${object[i].label}</a>`;
                y.innerHTML = heading+"<br/>"+"<b>Status :</b>" +"Completed" +"<br/>"+"<b>Decison made </b>"+"<br/>" ;
            
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
                                window.location.href = "showresume.html";
                            }
                        })(btn);
                    }else{
                        var btn = document.getElementById(object.resume_token);
                        btn.onclick = (function close(btn){
                            return function () {
                                localStorage.setItem("resume_token", btn.id);
                                localStorage.setItem("apply_token", object.apply_token);
                                localStorage.setItem("PDFpath",object.PDFpath);
                                window.location.href = "showresumepdf.html";
                            }
                        })(btn);
                    
                    }
        
                });
            }
    
        }
    }


    /*
    var x=document.getElementById('myTable').createCaption();
    x.innerHTML="Result for all of jobs!";
    x.style.fontSize = "30px";*/

});

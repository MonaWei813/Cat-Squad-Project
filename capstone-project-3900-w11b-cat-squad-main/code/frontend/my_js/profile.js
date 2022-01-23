// it is main to control show profile  details.
import API from "./api.js";

const api  = new API();
const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const email = " ";
api.show_authdetail(token,email)
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("Missing input");
        form1.reset();
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("Invalid input");
        form1.reset();

    }

})
.then((object) => {
    document.getElementById("email").value =  object.email;
    document.getElementById("firstname").value =  object.firstname;
    document.getElementById("lastname").value = object.lastname;
});
const edit_btn = document.getElementById("edit");
const firstname = document.getElementById("firstname");
const lastname = document.getElementById("lastname");

edit_btn.addEventListener("click",function(){
    console.log(edit_btn.value)
    if(edit_btn.value == "save"){
        api.edit_authdetail(token,firstname.value,lastname.value)
        .then((response) => {
            // Case 1: Successfully Login
            if (response.status === 200){
                console.log(firstname.value);
                firstname.disabled = true;
                lastname.disabled = true;
                edit_btn.value = "edit";
                edit_btn.innerHTML = "Edit";
                edit_btn.style.color = "white";
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

    }else{
        edit_btn.value = "save";
        firstname.disabled = false;
        lastname.disabled = false;
        edit_btn.innerHTML = "Save";
        edit_btn.style.color = "green";
    }
})
const change_btn = document.getElementById("reset");
const oldpassword = document.getElementById("op");
const newpassword = document.getElementById("np");
const repassword = document.getElementById("nrp");
const formd = document.getElementById("formd");
change_btn.addEventListener("click",function(){
    if (change_btn.value =="save"){
        api.change_password(token,oldpassword.value,newpassword.value,repassword.value)
        .then((response) => {
            // Case 1: Successfully Login
            if (response.status === 200){
                console.log(firstname.value);
                newpassword.disabled = true;
                repassword.disabled = true;
                oldpassword.disabled = true;
                change_btn.value = "changepassowrd";
                change_btn.innerHTML = "Change Password";
                change_btn.style.color = "white";
                oldpassword.value = "";
                newpassword.value = "";
                repassword.value = "";
            }
            // Case 2: Missing inputs
            else if (response.status === 400){
                alert("Missing input");
                oldpassword.value = "";
                newpassword.value = "";
                repassword.value = "";
            }
            // Case 3: Invalid inputs
            else if (response.status === 403){
                alert("Invalid input");
                oldpassword.value = "";
                newpassword.value = "";
                repassword.value = "";
            }
            else if (response.status === 405){
                alert("The retyped new password does not match");
                newpassword.value = "";
                repassword.value = "";
            }
        })
    }
    else{
        change_btn.value = "save";
        newpassword.disabled = false;
        repassword.disabled = false;
        oldpassword.disabled = false;
        change_btn.innerHTML = "Save";
        change_btn.style.color = "green";
    }
})



function pushHistory() { 
    var state = { 
    title: "title", 
    url: "#"
    }; 

    window.history.pushState(state, "title", "#"); 
}
pushHistory();
window.addEventListener("popstate", function(e) { 
    if(edit_btn.value == "save" ||change_btn.value =="save"){
        var v = confirm("Are you sure you want to go back? Your changes will not be saved!");
        if(v == true){
            history.back();
        }
    }
    }, false);
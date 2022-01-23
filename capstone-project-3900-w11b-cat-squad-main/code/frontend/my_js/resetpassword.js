// it is main to control change password in the profile page.
import API from "./api.js";

const api  = new API();
const submit = document.getElementById("submit");
const email =  document.getElementById("emailaddress");
const code =  document.getElementById("code");
const password =  document.getElementById("password");
const repassword =  document.getElementById("repassword");
const form = document.getElementById("form");


submit.addEventListener("click",function(){
    
    api.reset_password(email.value,code.value,password.value,repassword.value)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            confirm("Password has been reset.");
            window.location.href = "Signin.html";
        }
        // Case 2: Missing inputs
        else if (response.status === 401){
            alert("No existing account matches this email, please sign up.");
            form.reset();
        }
        else if (response.status === 405){
            alert("please enter the password again");
            form.reset();
        }
        else if (response.status === 406){
            alert("please enter the comfirmation code");
            form.reset();
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid inputs");
            form.reset();
        }
        else if (response.status === 400){
            alert("Missing inputs");
            form.reset();
        }
    
    })
})
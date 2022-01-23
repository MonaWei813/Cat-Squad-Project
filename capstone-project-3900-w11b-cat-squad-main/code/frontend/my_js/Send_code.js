// it is main to send code for the forget password and then change passsword needs the code.
import API from "./api.js";

const api  = new API();
const send = document.getElementById("send");
const email =  document.getElementById("emailaddress");
const form = document.getElementById("form");
const password = "password";
send.addEventListener("click",function(){
    console.log(email.value);
    api.send_code(email.value,password)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            return response.object;
        }
        // Case 2: Missing inputs
        else if (response.status === 401){
            alert("No existing account matches this email, please sign up.");
            form.reset();
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid inputs");
            form.reset();
        }
    
    })
    .then((object)=>{
        window.location.href = "Resetpassword.html";
        const textin1 = "Dear User,"
        const textin2 = `Your reset Code is:  ${object.reset_code}`;
        const head = "Reset code";

        api.send_email(email.value,textin1,textin2,head)
        .then((response) => {
            if (response.status === 200){
                return response.object;
            }
            else if (response.status === 400){
                alert("Missing inputs");
            }
            else if (response.status === 403){
                alert("Invalid inputs");
            }
        })
    })

})

//this is for the login file
import API from "./api.js";

const api  = new API();

const login_btn = document.getElementById("login_btn");
login_btn.addEventListener("click", function(){
    let login_form = document.getElementById("login_form");
    let email= null;
    console.log(login_form.elements[0].value, login_form.elements[1].value);

    api.login(login_form.elements[0].value, login_form.elements[1].value)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            email = login_form.elements[0].value;
            localStorage.setItem("email",email);
            return response.object;
            //login_form.reset();
        }
        // Case 2: Missing inputs
        else if (response.status === 400){
            alert("Missing Username/Password");
            login_form.reset();
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid Username/Password");
            login_form.reset();
        }

    })
    .then((object) => {
        // Store token and user_id for future usage
        localStorage.setItem("token", object.token);
        sessionStorage.setItem("currPage", "classList");
    
        if(object.usertype == 'JobSeeker'){
            window.location.href = "JobseekerHomePage.html";
        }
        else if(object.usertype == 'Employer'){
            window.location.href = "EmployerHomePage.html";
        }
        else if(object.usertype == 'Agent'){
            window.location.href = "AgentHomePage.html";
        }

    });
});


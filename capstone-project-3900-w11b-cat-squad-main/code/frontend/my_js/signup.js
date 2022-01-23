// it is the mian to contorl signup.
import API from "./api.js";

const api  = new API();
const signup_btn = document.getElementById("signup_btn");
signup_btn.addEventListener("click", function(){
    let radio = document.getElementsByName("tigger");
    let usertype = null;
    for (var i=0; i < radio.length; i++) {
        if (radio[i].checked) {
            usertype = radio[i].value;
        }
    }
    let signup_form = document.getElementById("signup_form");
    let email= null;
    console.log(usertype);
    api.signup(signup_form.elements[0].value, signup_form.elements[1].value,signup_form.elements[2].value,signup_form.elements[3].value,signup_form.elements[4].value,usertype)
    .then((response) => {
        // Case 1: Successfully Login
        if (response.status === 200){
            email = signup_form.elements[0].value;
            localStorage.setItem("email",email);

            return response.object;
            
        }
        // Case 2: Missing inputs
        else if (response.status === 400){
            alert("Missing inputs");
            signup_form.reset();
        }
        // Case 3: Invalid inputs
        else if (response.status === 403){
            alert("Invalid email");
            signup_form.reset();
        }
        else if (response.status === 401){
            alert("Existing account, please sign in.");
            signup_form.reset();
        }
        else if (response.status === 405){
            alert("please enter the password again");
            signup_form.reset();
        }

    })
    .then((object) => {
        // Store token and user_id for future usage
        localStorage.setItem("token", object.token);
        sessionStorage.setItem("currPage", "classList");
        const textin1 = "Hello there,"
        const textin2 = "ARE you ready for the exciting adventure ahead?  Login your account to start explore the vast job market! We will bring the best of you to the world.";
        const head = "Weclome to join us!";

        if(object.usertype == 'JobSeeker'){
            window.location.href = "JobseekerHomePage.html";
        }
        else if(object.usertype == 'Employer'){
            window.location.href = "EmployerHomePage.html";
        }
        else if(object.usertype == 'Agent'){
            window.location.href = "AgentHomePage.html";
        }
        api.send_email(email,textin1,textin2,head)
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

    });
});



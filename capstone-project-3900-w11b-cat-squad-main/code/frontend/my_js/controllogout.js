//control the logout it main to clear the localstorage information and it can avoid the information leak.
const logout = document.getElementById("logout");
logout.addEventListener("click",function(){
    window.localStorage.clear();
    logout.href = "index.html";
})
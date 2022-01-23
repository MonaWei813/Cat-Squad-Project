// The file is main to control show all of job to Agent, and control the search function
//get the search value from frontend
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
api.showalljob()
.then((response) => {
    // Case 1: Successfully Login
    if (response.status === 200){
        return response.object;
        //login_form.reset();
    }
    // Case 2: Missing inputs
    else if (response.status === 400){
        alert("Missing inputs");
   
    }
    // Case 3: Invalid inputs
    else if (response.status === 403){
        alert("Invalid inputs");
    }

})
.then((object) => {
    console.log(object);
    document.getElementById('myTable').rules="rows";
    document.getElementById('myTable').style.fontSize="15px";
    document.getElementById('myTable').style.color="Black";
    document.getElementById('myTable').style.lineHeight="30px";
    document.getElementById('myTable').style.width = "900px";
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
                window.location.href = "Agentjobdetail.html";
                console.log(b.id);
            }
        })(b);

    }

    const search_btn = document.getElementById("search");
    search_btn.addEventListener("click",function(){
        const job_location = document.getElementById("location");
        const job_time = document.getElementById("job_time");
        const employer_type = document.getElementById("employer_type");
        const company_name = document.getElementById("search-field");
        if(company_name.value ==""){
            company_name.value = "empty";
        }
        
        console.log("enter in search button");
        api.searchjob(job_location.value, job_time.value,employer_type.value, company_name.value)
        .then((response) => {
            // Case 1: Successfully Login
            if (response.status === 200){
                return response.object;
                //login_form.reset();
            }
            // Case 2: Missing inputs
            else if (response.status === 400){
                alert("Missing Username/Password");
                job_location.options[0].selected = "true";
                job_time.options[0].selected = "true";
                employer_type.options[0].selected = "true";
                company_name.value = "";
            }
            // Case 3: Invalid inputs
            else if (response.status === 403){
                alert("Invalid Username/Password");
                job_location.options[0].selected = "true";
                job_time.options[0].selected = "true";
                employer_type.options[0].selected = "true";
                company_name.value = "";
            }
            else if (response.status === 406){
                alert("Please enter at least one option!");
                job_location.options[0].selected = "true";
                job_time.options[0].selected = "true";
                employer_type.options[0].selected = "true";
                company_name.value = "";
                window.location.reload();
            }
            else if (response.status === 405){
                alert("No matching result");
                job_location.options[0].selected = "true";
                job_time.options[0].selected = "true";
                employer_type.options[0].selected = "true";
                company_name.value = "";
                window.location.reload();
            }
        })
        .then((object) => {
            if(object != undefined){
                const table = document.getElementById('myTable');
                 var rowNum=table.rows.length;
                 for (i=0;i<rowNum;i++)
                 {
                     table.deleteRow(i);
                     rowNum=rowNum-1;
                     i=i-1;
                 }
                var head = document.getElementById("head");
                head.innerHTML= "Result  For Search!";
                document.getElementById('myTable').rules="rows";
                document.getElementById('myTable').style.fontSize="15px";
                document.getElementById('myTable').style.color="Black";
                document.getElementById('myTable').style.lineHeight="30px";
                document.getElementById('myTable').style.width = "900px";
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
                            window.location.href = "Agentjobdetail.html";
                            console.log(b.id);
                        }
                    })(b);
                }

            }
            job_location.options[0].selected = "true";
            job_time.options[0].selected = "true";
            employer_type.options[0].selected = "true";
            company_name.value = "";
        
        })


    })
});

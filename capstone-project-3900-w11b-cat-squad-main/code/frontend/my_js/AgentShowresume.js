// The file is main to show all of jobseeker resume details to agent.
import API from "./api.js";

const token = window.localStorage.getItem("token");
console.log(token)
if(token == null){
    window.location.href = "index.html";
}
const api  = new API();
console.log("--------------");
api.showallresume()
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

        var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
        y.innerHTML=heading+"<br/>"+"<b>Education Degree:</b>" +object[i].education_degree +"<br/>"+
        "<b>Major: </b>"+object[i].major+"<br/>"+"<b>Skills: </b>"+object[i].skill+"<br/>";
        var b = document.getElementById(`${object[i].resume_token}`);

        b.onclick = (function close(b){
            return function () {
                localStorage.setItem("resume_token", b.id);
                window.location.href = "Agentjobseekerdetail.html";
                console.log(b.id);
            }
        })(b);

    }

    const search_btn = document.getElementById("search");
    search_btn.addEventListener("click",function(){
        const skill = document.getElementById("search-field");
        if(skill.value ==""){
            skill.value = "empty";
        }
        console.log(skill);
        console.log("enter in search button");
        console.log(skill.value);
        api.searchresume(skill.value,'activity')
        .then((response) => {
            // Case 1: Successfully Login
            if (response.status === 200){
                return response.object;
                //login_form.reset();
            }
            // Case 2: Missing inputs
            else if (response.status === 400){
                alert("Please enter one option to search!");
                skill.value = "";
                window.location.reload();
            }
            // Case 3: Invalid inputs
            else if (response.status === 403){
                alert("Invalid inputs");
                skill.value = "";
                window.location.reload();
            }
            else if (response.status === 406){
                alert("Please enter one option to search!");
                skill.value = "";
                window.location.reload();
            }
            else if (response.status === 405){
                alert("No resume result");
                skill.value = "";
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
            
                    var heading = `<a id = "${object[i].resume_token}" style = \"text-decoration:underline; cursor:pointer; color:blue; visited {color:purple;text-decoration:none;}\">${object[i].label}</a>`;
                    y.innerHTML=heading+"<br/>"+"<b>Education Degree:</b>" +object[i].education_degree +"<br/>"+
                      "<b>Major: </b>"+object[i].major+"<br/>"+"<b>Skills: </b>"+object[i].skill1+","+object[i].skill2+","+object[i].skill3+"<br/>";
                    var b = document.getElementById(`${object[i].resume_token}`);

                    b.onclick = (function close(b){
                        return function () {
                            localStorage.setItem("resume_token", b.id);
                            window.location.href = "Agentjobseekerdetail.html";
                            console.log(b.id);
                        }
                    })(b);
            
                }

            }
            skill.value = "";
        });


    })
});

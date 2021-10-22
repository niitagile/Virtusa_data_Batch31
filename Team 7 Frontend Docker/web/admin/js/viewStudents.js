const baseURL = "http://localhost:3000";

async function getStudentsOfBranch(branchId){
    try{
        const res = await fetch(`${baseURL}/branch/students/${branchId}`, {
            headers: {
                token: localStorage.getItem("admin_token")
            }
        });
        if(res.status===200){
            const students = res.json();
            return students;
        }
        else if(res.status===401){
            alert("Unauthorized");
            location.replace("../login.html");
        }
        else{
            alert("something went wrong");
        }
    } catch(err){
        console.log(err);
    }
}



function renderStudents(students){
    const container = document.getElementById("students-container");
    students.forEach(student=>{
        const component = createStudentComponent(student);
        container.appendChild(component);
    });
}

function createStudentComponent(student){
    const {rollNumber, name, email, phone} = student;
    const tr = document.createElement("tr");
    const th = document.createElement("th");
    th.scope = "row";
    th.innerText = rollNumber;
    const td1 = document.createElement("td");
    td1.innerText = name;
    const td2 = document.createElement("td");
    td2.innerText = email;
    const td3 = document.createElement("td");
    td3.innerText = phone;
    const td4 = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.innerText = "Edit";
    editBtn.type = "button";
    editBtn.classList.add("btn");
    editBtn.classList.add("btn-info");
    editBtn.addEventListener("click", ()=> {window.location.replace(`./editStudent.html?rollNo=${rollNumber}`)});
    td4.appendChild(editBtn);
    tr.appendChild(th);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    return tr;
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const branchId = urlParams.get('branchId');
    const students = await getStudentsOfBranch(branchId);
    renderStudents(students);
}

main();
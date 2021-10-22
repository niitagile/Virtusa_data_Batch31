const baseURL = "http://localhost:3000";

async function getBranch(accId){
    try{
        const res = await fetch(`${baseURL}/accountant/branch/${accId}`, {
            headers: {
                token: localStorage.getItem("acc_token")
            }
        });
        if(res.status===200){
            const branch = res.json();
            return branch;
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

async function getStudentsOfBranch(branchId){
    try{
        const res = await fetch(`${baseURL}/branch/students/${branchId}`, {
            headers: {
                token: localStorage.getItem("acc_token")
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
    const viewBtn = document.createElement("button");
    viewBtn.innerText = "View Complete Details";
    viewBtn.type = "button";
    viewBtn.classList.add("btn");
    viewBtn.classList.add("btn-info");
    viewBtn.addEventListener("click", ()=> {window.location.replace(`viewStudent.html?rollNo=${rollNumber}`)});
    td4.appendChild(viewBtn);
    tr.appendChild(th);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    return tr;
}

async function main(){
    const branch = await getBranch(localStorage.getItem("acc_id"));
    document.getElementById("branch-name").innerText = branch.name;
    const students = await getStudentsOfBranch(branch.id);
    renderStudents(students);
}

main();
const baseURL = "http://localhost:3000";

async function getStudentByRollNo(rollNo){
    const res = await fetch(`${baseURL}/student/${rollNo}`, {
        headers: {
            token: localStorage.getItem("acc_token")
        }
    });
    if(res.status===200){
        const student = await res.json();
        return student;
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }
}

async function editStudent(){
    const student = {
        rollNumber: this.rollNumber,
        name: document.getElementById("studentName").value,
        email: document.getElementById("studentEmail").value,
        phone: document.getElementById("studentPhone").value
    }
    await hitEditStudentRequest(student);
}


async function hitEditStudentRequest(student){
    try{
        const res = await fetch(`${baseURL}/student/`,{
            method: 'PUT',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("acc_token")
            },
            body: JSON.stringify(student)
        });
        if(res.status===200){
            alert("Student updated successfully");
            location.replace("viewStudents.html");
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

function prefillEditForm(student){
    const {name, email, phone} = student;
    document.getElementById("studentName").value = name;
    document.getElementById("studentEmail").value = email;
    document.getElementById("studentPhone").value = phone;  
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const rollNo = urlParams.get('rollNo');
    const student = await getStudentByRollNo(rollNo);
    prefillEditForm(student);
    document.getElementById("submit-btn").addEventListener("click",()=>{
        editStudent.call({rollNumber: rollNo});
    });
}

main();
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

async function getEnrolledCourse(rollNo){
    const res = await fetch(`${baseURL}/student/course/${rollNo}`, {
        headers: {
            token: localStorage.getItem("acc_token")
        }
    });
    if(res.status===200){
        const course = await res.json();
        return course;
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }
}

async function getFeeDue(rollNo){
    const res = await fetch(`${baseURL}/student/fee/${rollNo}`, {
        headers: {
            token: localStorage.getItem("acc_token")
        }
    });
    if(res.status===200){
        const fee = await res.json();
        return fee;
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }   
}

function renderPersonalDetails(student, course){
    const {rollNumber, name, email,phone} = student;
    document.getElementById("studentRollNumber").innerText = rollNumber;
    document.getElementById("studentName").innerText = name;
    document.getElementById("studentEmail").innerText = email;
    document.getElementById("studentPhone").innerText = phone;
    document.getElementById("studentCourse").innerText = course.name;
}

function renderPaymentDetails(paymentDetails){
    const container = document.getElementById("payment-details-container");
    paymentDetails.forEach(detail=>{
        const component = createPaymentDetailComponent(detail);
        container.appendChild(component);
    });
}

function createPaymentDetailComponent(paymentDetail){
    const {id, date, amount} = paymentDetail;
    const tr = document.createElement("tr");
    const th = document.createElement("th");
    th.scope = "row";
    th.innerText = date;
    const td1 = document.createElement("td");
    td1.innerText = amount;
    const td2 = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.innerText = "Edit";
    editBtn.type = "button";
    editBtn.classList.add("btn");
    editBtn.classList.add("btn-info");
    editBtn.addEventListener("click", ()=> {window.location.replace(`editPaymentDetail.html?id=${id}`)});
    td2.appendChild(editBtn);
    const td3 = document.createElement("td");
    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "Delete";
    deleteBtn.type = "button";
    deleteBtn.classList.add("btn");
    deleteBtn.classList.add("btn-info");
    deleteBtn.addEventListener("click", ()=>{deletePaymentDetail(id)});
    td3.appendChild(deleteBtn);
    tr.appendChild(th);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    return tr;

}

async function deletePaymentDetail(id){
    try{
        const res = await fetch(`${baseURL}/paymentdetail/${id}`, {
            method: 'DELETE',
            headers: {
                token: localStorage.getItem("acc_token")
            }
        
        });
        if(res.status===200){
            alert("Deleted Successfully");
            location.reload();
        }
        else if(res.status===401){
            alert("Unauthorized");
            location.replace("../login.html");
        }
        else{
            alert("something went wrong");
        }
    }
    catch(err){
        console.log(err);
    }
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const rollNo = urlParams.get('rollNo');
    const student = await getStudentByRollNo(rollNo);
    const course = await getEnrolledCourse(rollNo);
    const feeDue = await getFeeDue(rollNo);
    renderPersonalDetails(student, course);
    renderPaymentDetails(student.paymentDetails);
    document.getElementById("fee-due").innerText = feeDue;
    document.getElementById("edit-student-btn").addEventListener("click", ()=>{
        location.replace(`editStudent.html?rollNo=${rollNo}`);
    });
    document.getElementById("add-payment-btn").addEventListener("click", ()=>{
        location.replace(`addPaymentDetail.html?rollNo=${rollNo}`);
    });
} 

main();
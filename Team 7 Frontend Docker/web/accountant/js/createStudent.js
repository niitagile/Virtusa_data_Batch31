const baseURL = "http://localhost:3000";

async function createStudent(){
    const student = {
        rollNumber: document.getElementById("rollNumber").value,
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
    }

    const branch = await getBranch(localStorage.getItem("acc_id"));
    const courseId = document.getElementById("courseId").value;

    await hitCreateStudentRequest(student, branch.id, courseId)
}

async function hitCreateStudentRequest(student, branchId, courseId){
    const res = await fetch(`${baseURL}/student/${branchId}/${courseId}`, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
            token: localStorage.getItem("acc_token")
        },
        body: JSON.stringify(student)
    });
    if (res.status === 200) {
        alert("Added successfully");
        window.location.replace(`viewStudents.html`);
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }
}

async function getBranch(accId){
    try{
        const res = await fetch(`${baseURL}/accountant/branch/${accId}`,{
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


async function getCourses(){
    const res = await fetch(`${baseURL}/course/`, {
        headers: {
            token: localStorage.getItem("acc_token")
        }
    });
    if(res.status===200){
        const courses = await res.json();
        return courses;
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }
}

function renderCourses(courses){
    const container = document.getElementById("courseId");
    courses.forEach(course=>{
        const component = createCourseComponent(course);
        container.appendChild(component);
    });
}

function createCourseComponent(course){
    const {id, name} = course;
    const option = document.createElement("option");
    option.value = id;
    option.innerText = name;
    return option;
}

async function main(){
    const courses = await getCourses();
    renderCourses(courses);
    document.getElementById("submit-btn").addEventListener("click", ()=>{
        createStudent();
    });
}

main();
const baseURL = "http://localhost:3000";

async function getCourses(){
    try{
        const res = await fetch(`${baseURL}/course/`, {
            headers: {
                token: localStorage.getItem("admin_token")
            }
        });
        if(res.status===200){
            const courses = res.json();
            return courses;
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



function renderCourses(courses){
    const container = document.getElementById("courses-container");
    courses.forEach(course=>{
        const component = createCourseComponent(course);
        container.appendChild(component);
    });
}

function createCourseComponent(course){
    const {id, name, duration, fee} = course;
    const tr = document.createElement("tr");
    const th = document.createElement("th");
    th.scope = "row";
    th.innerText = name;
    const td1 = document.createElement("td");
    td1.innerText = duration;
    const td2 = document.createElement("td");
    td2.innerText = fee;
    const td3 = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.innerText = "Edit";
    editBtn.type = "button";
    editBtn.classList.add("btn");
    editBtn.classList.add("btn-info");
    editBtn.addEventListener("click", ()=> {window.location.replace(`editCourse.html?id=${id}`)});
    td3.appendChild(editBtn);
    tr.appendChild(th);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    return tr;
}

async function main(){
    const courses = await getCourses();
    renderCourses(courses);
}

main();
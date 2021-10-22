const baseURL = "http://localhost:3000";

async function getCourseById(id){
    const res = await fetch(`${baseURL}/course/${id}`,{
        headers: {
            token: localStorage.getItem("admin_token")
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

async function editCourse(){
    const course = {
        id: this.id,
        name: document.getElementById("courseName").value,
        duration: document.getElementById("courseDuration").value,
        fee: document.getElementById("courseFee").value
    }
    await hitEditCourseRequest(course);
}


async function hitEditCourseRequest(course){
    try{
        const res = await fetch(`${baseURL}/course/`,{
            method: 'PUT',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("admin_token")
            },
            body: JSON.stringify(course)
        });
        if(res.status===200){
            alert("Course updated successfully");
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

function prefillEditForm(course){
    const {name, duration, fee} = course;
    document.getElementById("courseName").value = name;
    document.getElementById("courseDuration").value = duration;
    document.getElementById("courseFee").value = fee;  
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id');
    const course = await getCourseById(id);
    prefillEditForm(course);
    document.getElementById("submit-btn").addEventListener("click",()=>{
        editCourse.call({id});
    });
}

main();
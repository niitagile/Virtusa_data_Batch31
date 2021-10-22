const baseURL = "http://localhost:3000";

async function createCourse(){
    const course = {
        name: document.getElementById("courseName").value,
        duration: document.getElementById("courseDuration").value,
        fee: document.getElementById("courseFee").value,
    }
    await hitCreateCourseRequest(course);
}


async function hitCreateCourseRequest(course){
    try{
        const res = await fetch(`${baseURL}/course/`,{
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("admin_token")
            },
            body: JSON.stringify(course)
        });
        if(res.status===200){
            alert("Course added successfully");
            window.location.replace(`viewCourses.html`);
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
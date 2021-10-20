const baseURL = "http://localhost:3000";

async function login(){
    const data = {
        role : document.getElementById("role").value,
        username : document.getElementById("username").value,
        password : document.getElementById("password").value
    }
    console.log(data);
    const res = await doLogin(data);
    console.log(res);
    const {id, token, role} = res;
    console.log(id, token, role);
    if(role==="admin"){
        localStorage.setItem("admin_id", id);
        localStorage.setItem("admin_token", token);
        location.replace("admin/index.html");
    }

    if(role==="accountant"){
        localStorage.setItem("acc_id", id);
        localStorage.setItem("acc_token", token);
        location.replace("accountant/viewStudents.html");
    }
}

async function doLogin(data){
    const res = await fetch(`${baseURL}/login/`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    
    if(res.status===200){
        const loginResp = await res.json();
        return loginResp;
    } else if(res.status===400){
        alert("Invalid credentials");
    }
}



function main(){
    document.getElementById("login-btn").addEventListener("click", login);
}

main();
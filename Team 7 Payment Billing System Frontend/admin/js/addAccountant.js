const baseURL = "http://localhost:3000";

async function addAccountant(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const branchId = urlParams.get('branchId');
    const accountant = {
        name: document.getElementById("accName").value,
        salary: document.getElementById("accSalary").value,
        username: document.getElementById("accUsername").value,
        password: document.getElementById("accPassword").value
    }
    await hitAddAccountantRequest(accountant, branchId);
}


async function hitAddAccountantRequest(accountant, branchId){
    try{
        const res = await fetch(`${baseURL}/accountant/${branchId}`,{
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("admin_token")
            },
            body: JSON.stringify(accountant)
        });
        if(res.status===200){
            alert("Accountant added successfully");
            window.location.replace(`./branchDetails.html?branchId=${branchId}`);
        }
        else if(res.status===401){
            alert("unauthorized");
            location.replace("../login.html")
        }else{
            alert("something went wrong");
        }
    } catch(err){
        console.log(err);
    }
    
}
const baseURL = "http://localhost:3000";


async function getAccountantById(id){
    const res = await fetch(`${baseURL}/accountant/${id}`, {
        headers: {
            token: localStorage.getItem("admin_token")
        }
    });
    if(res.status===200){
        const accountant = await res.json();
        return accountant;
    }
    else if(res.status===401){
        alert("Unauthorized");
        location.replace("../login.html");
    }
    else{
        alert("something went wrong");
    }
}

async function editAccountant(){
    console.log(this.id);
    const accountant = {
        id: this.id,
        name: document.getElementById("accName").value,
        salary: document.getElementById("accSalary").value
    }
    await hitEditAccountantRequest(accountant);
}


async function hitEditAccountantRequest(accountant){
    try{
        const res = await fetch(`${baseURL}/accountant/`,{
            method: 'PUT',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("admin_token")
            },
            body: JSON.stringify(accountant)
        });
        if(res.status===200){
            alert("Accountant updated successfully");
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

function prefillEditForm(accountant){
 const {name, salary} = accountant;
 document.getElementById("accName").value = name;
 document.getElementById("accSalary").value = salary;   
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id');
    const accountant = await getAccountantById(id);
    prefillEditForm(accountant);
    document.getElementById("submit-btn").addEventListener("click",()=>{
        editAccountant.call({id});
    });
}

main();
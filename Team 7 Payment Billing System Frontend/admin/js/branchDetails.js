const baseURL = "http://localhost:3000";


async function getBranchById(branchId){
    try{
        const res = await fetch(`${baseURL}/branch/${branchId}`,{
            headers: {
                token: localStorage.getItem("admin_token")
            }
        });
        if(res.status===200){
            return res.json();
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

function renderView(data){
    const {id, name, address, students, accountants} = data;
    document.getElementById("branchName").innerText = name;
    document.getElementById("branchAddress").innerText = address;
    document.getElementById("add-accountant-btn").href = `addAccountant.html?branchId=${id}`;
    document.getElementById("edit-branch-btn").href = `editBranch.html?branchId=${id}`;
    document.getElementById("view-students-btn").href = `viewStudents.html?branchId=${id}`;
    renderAccountants(accountants);
}

function renderAccountants(accountants){
    const container = document.getElementById("accountants");
    accountants.forEach(accountant=>{
        const component = createAccountantComponent(accountant);
        container.appendChild(component);
    });
}

function createAccountantComponent(accountant){
    const {id, name, salary} = accountant;
    const li = document.createElement("li");
    li.classList.add("list-group-item");
    const h4 = document.createElement("h3");
    h4.innerText = name;
    const p = document.createElement("h4");
    p.innerText = `Salary: ${salary}`;
    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "Delete";
    deleteBtn.type = "button";
    deleteBtn.classList.add("btn");
    deleteBtn.classList.add("btn-danger");
    deleteBtn.style.marginRight = "5px";
    deleteBtn.addEventListener("click",()=>{deleteAccountant(id)});
    const editBtn = document.createElement("button");
    editBtn.innerText = "Edit";
    editBtn.type = "button";
    editBtn.classList.add("btn");
    editBtn.classList.add("btn-info");
    editBtn.addEventListener("click", ()=> {window.location.replace(`./editAccountant.html?id=${id}`)});
    li.appendChild(h4);
    li.appendChild(p);
    li.appendChild(deleteBtn);
    li.appendChild(editBtn);
    return li;
}

async function deleteAccountant(id){
    const isConfirm = confirm("Are you sure?")
    if(isConfirm){
        const res = await fetch(`${baseURL}/accountant/${id}`, {
            method: 'DELETE', 
            mode: 'cors',
            headers: {
                token: localStorage.getItem("admin_token")
            }
        
        });
        if(res.status===200){
            alert("Deleted Successfully");
            location.reload();
        }  else if(res.status===401){
            alert("Unauthorized");
            location.replace("../login.html");
        }
        else{
            alert("something went wrong");
        }
    }
}

async function main(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const branchId = urlParams.get('branchId');
    const res = await getBranchById(branchId);
    renderView(res);
}

main();
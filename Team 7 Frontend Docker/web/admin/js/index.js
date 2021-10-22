const baseURL = "http://localhost:3000";

async function getAllBranches(){
    try{
        const res = await fetch(`${baseURL}/branch/`,{
            headers: {
                token: localStorage.getItem("admin_token")
            }
        });
        const data = await handleFetchAPIResponse(res);
        return data;
    } catch(err){
        console.log(err);
    }
}

function renderBranches(branches){
    const branchesContainer = document.getElementById("branches");
    branches.forEach(branch=>{
        const component = createBranchComponent(branch);
        branchesContainer.appendChild(component);
    })
    
}

function createBranchComponent(branch){ 
    const {id, name, address} = branch;
    const a = document.createElement("a");
    a.classList.add("list-group-item");
    a.href = `branchDetails.html?branchId=${id}`;
    const div = document.createElement("div");
    const h3 = document.createElement("h3");
    h3.innerText = name;
    const p = document.createElement("p");
    p.innerText = address;
    div.appendChild(h3);
    div.appendChild(p);
    a.appendChild(div);
    return a;
}




async function handleFetchAPIResponse(res){
    if(res.status===401){
        alert("You are not authorized to access this resource. Please login again");
        location.replace("../login.html");
    } else if(res.status!=200){
        console.log(res);
        throw new Error("Something went wrong");
    } else{
        const data = await res.json();
        return data;
    }
}


async function main(){
    const res = await getAllBranches();
    renderBranches(res);
}

main();
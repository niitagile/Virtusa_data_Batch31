const baseURL = "http://localhost:3000";

async function createBranch(){
    const branchName = document.getElementById("branchName").value;
    const branchAddress = document.getElementById("branchAddress").value;
    const branchObj = {
        name: branchName,
        address: branchAddress
    }
    console.log(branchObj);
    await hitCreateBranchRequest(branchObj);
}


async function hitCreateBranchRequest(branch){
    try{
        const res = await fetch(`${baseURL}/branch/`,{
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                token: localStorage.getItem("admin_token")
            },
            body: JSON.stringify(branch)
        });
        if(res.status===200){
            alert("Branch created successfully");
            window.location.replace("./index.html");
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
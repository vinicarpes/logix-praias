function selectRole(){
    if (selectedRole.value === "admin" || selectedRole.value === "bm"){
        optGvc.setAttributeNode(disabled);
    }else{
        for (opt in optBm){
            opt.setAttributeNode(disabled);
        }

    }
}


const optBm = document.getElementsByName("optBm");
const selectedRole = document.getElementById("permission");
const optGvc = document.getElementById("optGvc");
selectedRole.addEventListener("change", selectRole);
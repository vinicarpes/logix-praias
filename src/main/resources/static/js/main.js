function setAlertParamInputType(){
    let objSelect = document.getElementById("alert-type");
    let objInput = document.getElementById("alert-ref");
    objSelect.value = objSelect.value;

    if (objSelect.value == "quantity"){
        objInput.setAttribute("type", "number");
    }else if(objSelect.value == "due"){
        objInput.setAttribute("type", "date");
    }

    objInput.removeAttribute("disabled");
}

let objSelect = document.getElementById("alert-type");
objSelect.addEventListener("change", setAlertParamInputType)



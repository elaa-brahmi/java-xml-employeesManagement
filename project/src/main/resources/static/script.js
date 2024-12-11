function filterEmployees(filterInput){
    console.log(filterInput);
    const filter =filterInput.value.toLowerCase();
    const taskAssignment = filterInput.closest(".taskAssignment");
    const options = taskAssignment.querySelectorAll(".employeeSelect option");
    options.forEach(option => {
        const speciality = option.getAttribute("data-speciality").toLowerCase();
        option.style.display = speciality.includes(filter) ? "block" : "none";

    });
}

function filterEquipments(filterInput) {
    const filter = filterInput.value.toLowerCase();
    const taskAssignment = filterInput.closest(".taskAssignment");
    const options = taskAssignment.querySelectorAll(".equipmentSelect option");
    options.forEach(option => {
        const name = option.getAttribute("data-name").toLowerCase();
        option.style.display = name.includes(filter) ? "block" : "none";
    });
}
let taskindex=0;
function addTask(){
    const template=document.getElementById("taskAssignmentTemplate");
    const tasksContainer=document.getElementById("tasksContainer");
    //clone the task
    const newTask=template.cloneNode(true);
    newTask.style.display="block";
    newTask.id="";
    newTask.querySelectorAll("input,select").forEach((input)=>{
        input.name=input.name.replace("tasks[0]",`tasks[${taskindex}]`);
    })
    newTask.querySelectorAll("input,select").forEach((input)=>{
        input.fi=input.name.replace("tasks[0]",`tasks[${taskindex}]`);
    })
    tasksContainer.appendChild(newTask);
    taskindex++;
}
function removeTask(button){
    const taskAssignment=button.parentElement;
    taskAssignment.remove();
}
function selectit(option){

const op=option.getAttribute("data-speciality");
const input=document.querySelector(".employeeFilter");
input.value=op;
console.log(input.value,option);
}
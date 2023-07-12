function checkBoxBtn(){
    let agree = document.getElementById('agree');
    let checkBtn = document.getElementById('newBtn');
    if(agree.checked == true){
        checkBtn.style.display="block";
    }else{
        checkBtn.style.display="none";
    }
}
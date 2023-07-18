
let num = document.getElementById("checkNum");
let reModal = document.getElementById("rePasswordModal");
let newModal = document.getElementById("newPasswordModal");

const nextModalBtn = document.getElementById("modalNextBtn");
    
nextModalBtn.addEventListener("click", changeModal);
function changeModal(){


    if(num.value ==="1234"){
        
        reModal.style.display="none";
        newModal.style.display="block";
        
    }else{
        
        let inputText = document.getElementById("inputText");
        let inputModalP ="<p id='inputModalP'>인증번호 불일치</p>";
        inputText.innerHTML=inputModalP;
       
    }
} 

const modal = document.getElementById("newPasswordModal");
var closeModalBtn = document.getElementById("newModalNoBtn");
closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
    modalBg.style.display="none"
});

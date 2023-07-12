// function showBtn(){
//     let btn = document.getElementById('btn');
//     let btnValue= btn.value;
//      if(btnValue == 1234){

//          let newBtn ="<input type='button' value='다음' id='newBtn'>";
//          btn.innerHTML=newBtn;

//      }else if(btnValue == null){
        
//          alert("인증번호를 입력해주세요");
      
//      }else if(btnValue != 1234){
//          alert("인증번호가 다릅니다.");
//     }
// }
function showBtn(){
        
        let btn = document.getElementById('btn');

         let newBtn ="<input type='button' value='다음' id='newBtn'>";
         btn.innerHTML=newBtn;
}

function checkBtn(obj){
    let btn = document.getElementById('checkBtn');
    let newBtn ="<input type='button' value='다음' id='newBtn'>";
    
    if(obj.checked){
        
    }
    btn.innerHTML=newBtn;
}
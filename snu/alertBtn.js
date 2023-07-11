function alertBtn(){
    let btn = document.getElementsByClassName("btn");
    
    // if(btn.value != null){
    //     alert("인증번호를 전송했습니다.");
    // }else{
    //     alert("전화번호를 입력해주세요");
    // }
    var phone = document.getElementsByClassName("btn").value;


    var regPhone= /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

    if (regPhone.test(phone) == true) {

    alert("휴대전화 번호가 맞습니다.");

    }else{
        alert("정확한 번호 형식을 맞춰주세요");
    }

}

/*비밀번호 불일치경우 라이브하게 상태 보여주게하기*/
function checkPasswordMatch() {
    var password = document.getElementById("password").value;
    var passwordConfirm = document.getElementById("password_confirm").value;
    var passwordCheck = document.getElementById("passwordCheck");

    //비밀번호 최소 6자리이상 입력
    if (password.length < 6) {
        passwordCheck.innerHTML = "6자리 이상 입력해주세요";
        passwordCheck.style.color = "red";
        passwordCheck.style.display = "block";
        return;
    }

    if (password === passwordConfirm && password !== "" && passwordConfirm !== "") {
        passwordCheck.innerHTML = "비밀번호 일치";
        passwordCheck.style.color = "blue";
        passwordCheck.style.display = "block"; // 보이도록 설정
    } else if (password !== passwordConfirm) {
        passwordCheck.innerHTML = "비밀번호 불일치";
        passwordCheck.style.color = "red";
        passwordCheck.style.display = "block"; // 보이도록 설정
    } else {
        passwordCheck.innerHTML = ""; // 비어있도록 설정
        passwordCheck.style.display = "none"; // 숨김 처리
    }
}


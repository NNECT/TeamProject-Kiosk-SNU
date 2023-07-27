let buttonState2 = {
    checkPhoneNumber :false,
    checkCode : false
};

document.getElementById("changePwdPhone").addEventListener("input", e => {
    document.getElementById("changePwdText1").innerHTML ="";
    proxyButtonState.checkPhoneNumber = false;
});
document.getElementById("checkCode").addEventListener("input", e => {
    document.getElementById("changePwdText2").innerHTML ="";
    proxyButtonState.checkCode = false;
});

let buttonStateHandler2 = {
    set: function (target, key, value){
        target[key] = value;
        console.log(target.checkPhoneNumber)
        console.log(target.checkCode)
        if(target.checkCode && target.checkPhoneNumber)
            document.getElementById('editPassword_checkPhone').style.display = 'block';
        else
            document.getElementById('editPassword_checkPhone').style.display = 'none';
        return true;
    }
};



//비밀번호 변경 첫번째 모달창 - 전화번호 체크
let proxyButtonState2 = new Proxy(buttonState2, buttonStateHandler2);

function changePwdCodeCheck(){
    let codeInput = document.getElementById('checkCode').value;
    const codeCheck = document.getElementById('changePwdText2');

    $.ajax({
        type:"post",
        url : "../ajax/checkCode",
        data : JSON.stringify({
            "code" : codeInput,
            "phoneNumber" : document.getElementById('changePwdPhone').value
        }),
        dataType :"JSON",
        async:false,
        contentType : "application/json; charset=utf-8",
        success:function (response){
            if (response.result === "success") {
                codeCheck.innerHTML = "인증 성공";
                codeCheck.style.color = "blue";
                proxyButtonState2.checkCode = true;
            }else {
                codeCheck.innerHTML = "인증 실패";
                codeCheck.style.color = "red";
                proxyButtonState2.checkCode = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function changePwdCodeSend() {
    let phoneNumber = document.getElementById("changePwdPhone").value;
    const codeSendCheck = document.getElementById('changePwdText1');
    if (phoneNumber.length < 11 || phoneNumber.length > 12) {
        codeSendCheck.innerHTML = "다시 입력해주세요";
        codeSendCheck.style.color = "red";
        return;
    }
    $.ajax({
        type: "POST",
        url: "../ajax/changePhcodeSend",
        data: JSON.stringify({
            "phoneNumber": phoneNumber
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.result === "success") {
                codeSendCheck.innerHTML = "전송됨";
                codeSendCheck.style.color = "green";
                proxyButtonState2.checkPhoneNumber = true;
            } else {
                codeSendCheck.innerHTML = "전송 실패";
                codeSendCheck.style.color = "red";
                proxyButtonState2.checkPhoneNumber = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

//비밀번호 변경 두 번째 모달창 - 바꿀 패스워드 체크
let buttonState3 = {
    checkPassword :false
};

let buttonStateHandler3 = {
    set: function (target, key, value){
        target[key] = value;
        console.log(target.checkPassword)
        if(target.checkPassword)
            document.getElementById('changePwdBtn').style.display = 'block';
        else
            document.getElementById('changePwdBtn').style.display = 'none';
        return true;
    }
};

let proxyButtonState3 = new Proxy(buttonState3, buttonStateHandler3);

document.getElementById("changePassword1").addEventListener("input", passwordCheck);
document.getElementById("changePassword2").addEventListener("input", passwordCheck);

function passwordCheck() {
    let password = document.getElementById("changePassword1").value;
    let password_confirm = document.getElementById("changePassword2").value;
    const passwordCheck = document.getElementById("changePasswordText");
    if (password === "" || password_confirm === "") {
        passwordCheck.innerHTML = "";
        proxyButtonState3.checkPassword = false;
    } else if (password === password_confirm) {
        passwordCheck.innerHTML = "일치";
        passwordCheck.style.color = "blue";
        proxyButtonState3.checkPassword = true;
    } else {
        passwordCheck.innerHTML = "불일치";
        passwordCheck.style.color = "red";
        proxyButtonState3.checkPassword = false;
    }
}



// function changePassword() {
//     const password = document.getElementById("changePassword1").value;
//     const confirmPassword = document.getElementById("changePassword2").value;
//
//     if (password !== confirmPassword) {
//         document.getElementById("changePasswordText").innerHTML = "비밀번호가 일치하지 않습니다.";
//         return;
//     }
//
//     // 비밀번호 변경을 위한 Ajax 요청
//     $.ajax({
//         type: "POST",
//         url: "/inside/mypage/changepwd",
//         data: JSON.stringify({
//             password: password
//         }),
//         dataType: "json",
//         contentType: "application/json; charset=utf-8",
//         success: function (response) {
//             if (response.result === "success") {
//                 alert("비밀번호가 성공적으로 변경되었습니다.");
//                 // 모달창 닫기
//                 showModal('newPasswordModal');
//             } else {
//                 alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
//             }
//         },
//         error: function (request, status, error) {
//             alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
//         }
//     });
// }


let buttonState = {
    checkPassword :false,
    checkPhoneNumber :false,
    checkCode : false
};

let buttonStateHandler = {
    set: function (target, key, value){
        target[key] = value;
        if(target.checkCode && target.checkPhoneNumber && target.checkPassword)
            document.getElementById('phoneNumberChangeBtn').style.display = 'block';
        else
            document.getElementById('phoneNumberChangeBtn').style.display = 'none';
        return true;
    }
};

let proxyButtonState = new Proxy(buttonState, buttonStateHandler);

document.getElementById("password").addEventListener("input", e => {
    document.getElementById("passwordCheck").innerHTML ="";
    proxyButtonState.checkPassword = false;
});
document.getElementById("phoneNumber").addEventListener("input", e => {
    document.getElementById("codeSendCheck").innerHTML ="";
    proxyButtonState.checkPhoneNumber = false;
});
document.getElementById("code").addEventListener("input", e => {
    document.getElementById("codeCheck").innerHTML ="";
    proxyButtonState.checkCode = false;
});



function codeCheck(){
    let codeInput = document.getElementById('codeInput').value;
    const codeCheck = document.getElementById('codeCheck');
    let phoneNumber = document.getElementById('phoneNumberInput').value;
    $.ajax({
        type:"post",
        url : "../ajax/checkCode",
        data : JSON.stringify({
            "code" : codeInput,
            "phoneNumber" : phoneNumber
        }),
        dataType :"JSON",
        async:false,
        contentType : "application/json; charset=utf-8",
        success:function (response){
            if (response.result === "success") {
                codeCheck.innerHTML = "인증 성공";
                codeCheck.style.color = "blue";
                proxyButtonState.checkCode = true;
            }else {
                codeCheck.innerHTML = "인증 실패";
                codeCheck.style.color = "red";
                proxyButtonState.checkCode = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

//010-1234-1234 형식
// function formatPhoneNumber(phoneNumber) {
//     // phoneNumber의 숫자만 남기고, 나머지는 제거합니다.
//     const cleanPhoneNumber = phoneNumber.replace(/\D/g, '');
//
//     const formattedPhoneNumber = cleanPhoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
//
//     return formattedPhoneNumber;
// }

function codeSend() {
    let phoneNumber = document.getElementById("phoneNumberInput").value;
    const codeSendCheck = document.getElementById('codeSendCheck');
    if (phoneNumber.length < 11 || phoneNumber.length > 12) {
        codeSendCheck.innerHTML = "다시 입력해주세요";
        codeSendCheck.style.color = "red";
        return;
    }
    //010-1234-1234 형식
    // phoneNumber = formatPhoneNumber(phoneNumber);
    console.log(phoneNumber)
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
                proxyButtonState.checkPhoneNumber = true;
            } else {
                codeSendCheck.innerHTML = "전송 실패";
                codeSendCheck.style.color = "red";
                proxyButtonState.checkPhoneNumber = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function passwordCheck_phone() {
    let password = document.getElementById("passwordInput").value;
    const passwordCheck = document.getElementById("passwordCheck")
    if (password == null) {
        passwordCheck.innerHTML = "비밀번호를 올바르게 입력해주세요";
        passwordCheck.style.color = "red";
        return;
    }
    $.ajax({
        type: "POST",
        url: "../ajax/checkpwd",
        data: JSON.stringify({
            "password": crypt.encrypt(password)
        }),
        dataType: "JSON",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.result == "success") {
                passwordCheck.innerHTML = "올바른 비밀번호 입니다.";
                passwordCheck.style.color = "blue";
                proxyButtonState.checkPassword = true;
            } else {
                passwordCheck.innerHTML = "비밀번호가 일치하지 않습니다.";
                passwordCheck.style.color = "red";
                proxyButtonState.checkPassword = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

window.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("changeTel").addEventListener("submit", function (e) {
        e.preventDefault();

        let phoneNumber = document.getElementById("phoneNumberInput").value;

        const form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "../inside/mypage/changephone");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("hidden", "true");

        const phoneNumberInput = document.createElement("input");
        phoneNumberInput.setAttribute("type", "password");
        phoneNumberInput.setAttribute("name", "phoneNumber");
        phoneNumberInput.setAttribute("value", phoneNumber);
        form.appendChild(phoneNumberInput);

        document.body.appendChild(form);
        form.submit();
    });
})

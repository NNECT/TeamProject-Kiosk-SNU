
let buttonState = {
    usableUsername: false,
    usablePhoneNumber: false,
    usablePassword: false,
    serviceAgreement: false
};

let buttonStateHandler = {
    set: function(target, key, value) {
        target[key] = value;
        if (target.usableUsername && target.usablePhoneNumber && target.usablePassword && target.serviceAgreement) {
            document.getElementById('newBtn').style.display = 'block';
        } else {
            document.getElementById('newBtn').style.display = 'none';
        }
        return true;
    }
};

let proxyButtonState = new Proxy(buttonState, buttonStateHandler);

document.getElementById("username").addEventListener("input", e => {
    document.getElementById("usernameCheck").innerHTML = "";
    proxyButtonState.usableUsername = false;
});
document.getElementById("phoneNumber").addEventListener("input", e => {
    document.getElementById("codeSendCheck").innerHTML = "";
    document.getElementById("codeCheck").innerHTML = "";
    proxyButtonState.usablePhoneNumber = false;
});
document.getElementById("agree").addEventListener("click", e => {
    proxyButtonState.serviceAgreement = e.target.checked;
});

document.getElementById("phoneNumber").addEventListener("input", e => {
    let x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
    e.target.value = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
});
document.getElementById("password").addEventListener("input", passwordConfirmCheck);
document.getElementById("password_confirm").addEventListener("input", passwordConfirmCheck);

function passwordConfirmCheck(e) {
    let password = document.getElementById("password").value;
    let password_confirm = document.getElementById("password_confirm").value;
    const passwordCheck = document.getElementById("passwordCheck");
    if (password === "" || password_confirm === "") {
        passwordCheck.innerHTML = "";
        proxyButtonState.usablePassword = false;
    } else if (password === password_confirm) {
        passwordCheck.innerHTML = "일치";
        passwordCheck.style.color = "blue";
        proxyButtonState.usablePassword = true;
    } else {
        passwordCheck.innerHTML = "불일치";
        passwordCheck.style.color = "red";
        proxyButtonState.usablePassword = false;
    }
}

function usernameCheck() {
    let username = document.getElementById("username").value;
    const usernameCheck = document.getElementById("usernameCheck");

    if (username.length < 6 || username.length > 30) {
        usernameCheck.innerHTML = "사용 불가";
        usernameCheck.style.color = "red";
        proxyButtonState.usableUsername = false;
        return;
    }

    $.ajax({
        type: "POST",
        url: "../ajax/username",
        data: JSON.stringify({
            "username": username
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.msg === "true") {
                usernameCheck.innerHTML = "사용 가능";
                usernameCheck.style.color = "blue";
                proxyButtonState.usableUsername = true;
            } else {
                usernameCheck.innerHTML = "사용중";
                usernameCheck.style.color = "red";
                proxyButtonState.usableUsername = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function codeSend() {
    let phoneNumber = document.getElementById("phoneNumber").value;
    const codeSendCheck = document.getElementById("codeSendCheck");

    if (phoneNumber.length < 12 || phoneNumber.length > 13) {
        codeSendCheck.innerHTML = "사용 불가";
        codeSendCheck.style.color = "red";
        return;
    }

    $.ajax({
        type: "POST",
        url: "../ajax/codeSend",
        data: JSON.stringify({
            "codePurpose": "join",
            "codePhoneNumber": phoneNumber
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.result === "success") {
                codeSendCheck.innerHTML = "전송됨";
                codeSendCheck.style.color = "green";
            } else {
                codeSendCheck.innerHTML = "전송 실패";
                codeSendCheck.style.color = "red";
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function codeCheck() {
    const codeCheck = document.getElementById("codeCheck");

    $.ajax({
        type: "POST",
        url: "../ajax/codeCheck",
        data: JSON.stringify({
            "codePurpose": "join",
            "codePhoneNumber": document.getElementById("phoneNumber").value,
            "code": document.getElementById("code").value
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.result === "success") {
                codeCheck.innerHTML = "인증 성공";
                codeCheck.style.color = "blue";
                proxyButtonState.usablePhoneNumber = true;
            } else {
                codeCheck.innerHTML = "인증 실패";
                codeCheck.style.color = "red";
                proxyButtonState.usablePhoneNumber = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}
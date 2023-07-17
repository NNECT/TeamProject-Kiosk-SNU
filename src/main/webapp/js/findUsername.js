let buttonState = {
    usablePhoneNumber: false,
    usableCode: "before"
};

let buttonStateHandler = {
    set: function (target, key, value) {
        target[key] = value;
        const codeSendCheck = document.getElementById("codeSendCheck");


        if (target.usablePhoneNumber && target.usableCode === "success") {
            document.getElementById('newBtn').style.display = 'block';
            document.getElementById('changeIcon').innerHTML = '<img src="../img/inside/findStatus/succesCheck.png" width="150">';
        } else if (target.usablePhoneNumber && target.usableCode === "fail") {
            //연락처는 맞지만 코드가 틀리면 fail 이미지
            document.getElementById('newBtn').style.display = 'none';
            document.getElementById('changeIcon').innerHTML = '<img src="../img/inside/findStatus/failCheck.png" width="140">';
        } else if (!target.usablePhoneNumber) {
            //연락처가 맞지 않으면 그대로 진행중 유지
            //(만약)전화번호 인증 이후에 인증번호 입력도중, 연락처 입력으로 다시 돌아오는 경우(수정발생) 진행중 이미지로 다시 변경
            document.getElementById('newBtn').style.display = 'none';
            document.getElementById('changeIcon').innerHTML = '<img src="../img/snu_loding.gif" width="90">';
        }

        return true;
    }
};

let proxyButtonState = new Proxy(buttonState, buttonStateHandler);

document.getElementById("phoneNumber").addEventListener("input", e => {
    document.getElementById("codeSendCheck").innerHTML = "";
    document.getElementById("codeCheck").innerHTML = "";
    proxyButtonState.usablePhoneNumber = false;
});

document.getElementById("phoneNumber").addEventListener("input", e => {
    let x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
    e.target.value = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
});

function codeSend() {
    let phoneNumber = document.getElementById("phoneNumber").value;
    const codeSendCheck = document.getElementById("codeSendCheck");

    if (phoneNumber.length < 12 || phoneNumber.length > 13) {
        codeSendCheck.innerHTML = "사용 불가";
        codeSendCheck.style.color = "red";
        return;
    }

    $.ajax({
        type: "Post",
        url: "../ajax/codeSend",
        data: JSON.stringify({
            "codePurpose": "findUsername",
            "codePhoneNumber": phoneNumber
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.result === "success") {
                codeSendCheck.innerHTML = "전송됨";
                codeSendCheck.style.color = "green";
                proxyButtonState.usablePhoneNumber = true;
            } else if (response.result === "wrongNumber") {
                codeSendCheck.innerHTML = "잘못된 번호";
                codeSendCheck.style.color = "red";
                proxyButtonState.usablePhoneNumber = false;
            } else {
                codeSendCheck.innerHTML = "전송 실패";
                codeSendCheck.style.color = "red";
                proxyButtonState.usablePhoneNumber = false;
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
            "codePurpose": "findUsername",
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
                proxyButtonState.usableCode = "success";
            } else {
                codeCheck.innerHTML = "인증 실패";
                codeCheck.style.color = "red";
                proxyButtonState.usableCode = "fail";
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });

}


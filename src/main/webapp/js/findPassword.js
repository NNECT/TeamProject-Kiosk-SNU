
let buttonState = {
    usablePhoneNumber: false

};

let buttonStateHandler = {
    set: function(target, key, value) {
        target[key] = value;
        if (target.usablePhoneNumber) {
            document.getElementById('newBtn').style.display = 'block';
        } else {
            document.getElementById('newBtn').style.display = 'none';
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

function codeSend(){
    let phoneNumber = document.getElementById("phoneNumber").value;
    let username = document.getElementById("username").value;
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
            "codePurpose": "findPassword",
            "codePhoneNumber": phoneNumber,
            "username" : username
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (response){
            if(response.result === "success"){
                codeSendCheck.innerHTML = "전송됨";
                codeSendCheck.style.color = "green";
            }else if(response.result === "wrongNumber"){
                codeSendCheck.innerHTML = "잘못된 번호";
                codeSendCheck.style.color = "red";
            }else if(response.result === "notFoundUsername"){
                codeSendCheck.innerHTML = "잘못된 아이디"
                codeSendCheck.style.color = "red";
            }else{
                codeSendCheck.innerHTML = "전송 실패";
                codeSendCheck.style.color = "red";
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });

}

function codeCheck(){
    const codeCheck = document.getElementById("codeCheck");

    $.ajax({
        type: "POST",
        url: "../ajax/codeCheck",
        data: JSON.stringify({
            "codePurpose": "findPassword",
            "codePhoneNumber" : document.getElementById("phoneNumber").value,
            "code": document.getElementById("code").value
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function(response){
            if(response.result === "success"){
                codeCheck.innerHTML = "인증 성공";
                codeCheck.style.color = "blue";
                proxyButtonState.usablePhoneNumber = true;
            } else{
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

document.getElementById("newBtn").onclick = function() {
    document.getElementById("findPassword").submit();
};


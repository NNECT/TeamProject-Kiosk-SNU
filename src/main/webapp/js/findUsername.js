let changeIcon = document.getElementById('changeIcon')

let buttonState = {
    usablePhoneNumber: false,
    usableCode : false
};

let buttonStateHandler = {
    set: function(target, key, value) {
        target[key] = value;
        const codeSendCheck = document.getElementById("codeSendCheck");

        if (target.usablePhoneNumber) {

           if(codeSendCheck.innerHTML == "전송됨" && target.usableCode==null){
                document.getElementById('newBtn').style.display = 'none';
                let ingIcon = "<img src='../img/snu_loding.gif' width='90'>"
                changeIcon.innerHTML = ingIcon;

               //

            }else if(target.usableCode){

                    document.getElementById('newBtn').style.display = 'block';
                     let succesIcon="<img src='../img/success&fail/succesCheck.png' width='150'>"
                     changeIcon.innerHTML = succesIcon;

            }else if(target.usableCode===false){

                     document.getElementById('newBtn').style.display = 'none';
                     let failIcon = "<img src='../img/success&fail/failCheck.png' width='140'>"
                     changeIcon.innerHTML = failIcon;
            }

        } else if(target.usablePhoneNumber === false){
            if(codeSendCheck.innerHTML === "잘못된 번호"){
                document.getElementById('newBtn').style.display = 'none';
                let failIcon = "<img src='../img/success&fail/failCheck.png' width='140'>"
                changeIcon.innerHTML = failIcon;
            }

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
        success: function (response){
            if(response.result === "success"){
                codeSendCheck.innerHTML = "전송됨";
                codeSendCheck.style.color = "green";
                proxyButtonState.usablePhoneNumber = true;
            }else if(response.result === "wrongNumber"){
                codeSendCheck.innerHTML = "잘못된 번호";
                codeSendCheck.style.color = "red";
                proxyButtonState.usablePhoneNumber = false;
            }else{
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

function codeCheck(){
    const codeCheck = document.getElementById("codeCheck");

    $.ajax({
        type: "POST",
        url: "../ajax/codeCheck",
        data: JSON.stringify({
            "codePurpose": "findUsername",
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
                proxyButtonState.usableCode = true;
            } else{
                codeCheck.innerHTML = "인증 실패";
                codeCheck.style.color = "red";
                proxyButtonState.usableCode = false;
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });

}


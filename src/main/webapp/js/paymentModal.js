
let cardModal = document.getElementById("cardModal");
let barcodeModal = document.getElementById("barCodeModal");

function selectedCheck(publicKey) {
    for (const elm of document.getElementsByName("radio-button")) {
        if (!elm.checked) continue;

        let code = '';

        if (elm.value === "creditCard") {
            cardModal.style.display = "block";
            barcodeModal.style.display = "none";
            for(let i = 0; i < 16; i++) {
                code += Math.floor(Math.random() * 10); // 0-9 random number
            }
        } else if (elm.value === "kakaoPay" || elm.value === "naverPay") {
            cardModal.style.display = "none";
            barcodeModal.style.display = "block";
            for(let i = 0; i < 20; i++) {
                code += Math.floor(Math.random() * 10); // 0-9 random number
            }
        } else {
            continue;
        }

        const crypt = new JSEncrypt();
        crypt.setPublicKey(publicKey);

        $.ajax({
            type: "POST",
            url: "../ajax/payment",
            data: JSON.stringify({
                "paymentType": elm.value,
                "paymentCode": crypt.encrypt(code)
            }),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    window.location.href = "../outside/paymentSuccess";
                } else {
                    alert("결제에 실패하였습니다.");
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
}


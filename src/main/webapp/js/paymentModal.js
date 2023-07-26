
let cardModal = document.getElementById("cardModal");
let barcodeModal = document.getElementById("barCodeModal");

function selectedCheck() {
    //라디오 네임으로만 처리하ㄹ
    for (const elm of document.getElementsByName("radio-button")) {
        if (elm.checked) {
            if(elm.value === "creditCard"){
                cardModal.style.display="block"
                barcodeModal.style.display="none"
                //dispaly=block일때 10초 뒤 이동
                let time = 8;
                let timer = setInterval(function () {
                    time--;
                    if (time === 0) {
                        clearInterval(timer);
                        location.href = "snu_end.html";
                    }
                }, 800);
            }else if(elm.value === "kakaoPay" || elm.value === "naverPay"){
                barcodeModal.style.display="block"
                cardModal.style.display="none"
                //dispaly=block일때 10초 뒤 이동
                let time = 8;
                let timer = setInterval(function () {
                    time--;
                    if (time === 0) {
                        clearInterval(timer);
                        location.href = "snu_end.html";
                    }
                }, 800);
            }
            
        }
    }
   
}


var paymentElements = document.querySelectorAll(".paymentList");
var paymentDate = document.getElementById("paymentDate").value;
var paymentLogData = [];

paymentElements.forEach(function (element) {
    var paymentLogValue = element.value;
    console.log(paymentLogValue);
    try {
        var paymentLog = JSON.parse(paymentLogValue);
        console.log(paymentLog.result);
        paymentLogData.push(paymentLog);
    } catch (error) {
        console.error("JSON 파싱 오류:", error);
        console.log("유효하지 않은 JSON 데이터:", paymentLogValue);
    }
});

function displayPaymentLogData(paymentLogData) {
    var tableBody = document.getElementById("paymentTableBody");

    // 기존 내용 초기화
    tableBody.innerHTML = "";

    // paymentLogData를 순회하며 테이블에 새로운 행을 추가합니다.
    paymentLogData.forEach(function (paymentLog) {
        var row = document.createElement("tr");


        var dateTimeCell = document.createElement("td");
        dateTimeCell.textContent = paymentDate;
        row.appendChild(dateTimeCell);

        var typeCell = document.createElement("td");
        if(paymentLog.type == "naverPay")
            typeCell.textContent = "네이버페이";
        else if(paymentLog.type == "creditCard")
            typeCell.textContent = "신용카드";
        else
            typeCell.textContent = "카카오페이";
        row.appendChild(typeCell);

        var payCell = document.createElement("td");
        payCell.textContent = paymentLog.pay+"원";
        row.appendChild(payCell);
        console.log(paymentLog.breakdown[0].type);
        var payThings = document.createElement("td");
        if((paymentLog.breakdown[0].type == "Time Ticket" && paymentLog.breakdown[0].unit == "hour") &&
            paymentLog.breakdown[1].type == "Locker Ticket") {
            payThings.innerHTML = "시간권  "+paymentLog.breakdown[0].time+"시간<br> 사물함"+paymentLog.breakdown[1].time +"일";
        }
        else if((paymentLog.breakdown[0].type == "Time Ticket" && paymentLog.breakdown[0].unit == "day")
            && paymentLog.breakdown[1].type == "Locker Ticket") {
            payThings.innerHTML = "정기권  "+paymentLog.breakdown[0].time+"일<br> 사물함"+paymentLog.breakdown[1].time +"일";
        }else if((paymentLog.breakdown[0].type == "Time Ticket" && paymentLog.breakdown[0].unit == "day")){
            payThings.innerHTML = "시간권  "+paymentLog.breakdown[0].time+"일";
        }
        else{
            payThings.innerHTML = "시간권  "+paymentLog.breakdown[0].time+"일";
        }

        row.appendChild(payThings);

        // 기타 필요한 칼럼들도 동일하게 추가 가능

        tableBody.appendChild(row);
    });
}

// 결제내역 데이터를 화면에 출력하는 함수 호출
displayPaymentLogData(paymentLogData);
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/26
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_payment.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_payModal.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_newPassword_page</title>
</head>
<body>
<div id="body">
    <a href="<c:url value="/inside/payment/cancel"/>"><img id="beforBtn" src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>
    <div id="whiteWrap">
        <section>
            <ul><!--메뉴-->
                <li id="pay">결제내역</li>
            </ul>
            <hr id="line"><!--선-->
            <c:forEach var="i" begin="0" end="${ticketList.size() - 1}" step="1">
                <div class="payBox"><!--선택항목-->
                    <span class="product">${ticketList[i]}</span>
                    <span class="time">${timeList[i]}</span>
                    <span class="price"><fmt:formatNumber value="${priceList[i]}" pattern="#,##0"/>원</span>
                </div>
            </c:forEach>
            <div name="dot"><img src="<c:url value="/img/dotted.png"/>"></div><!--선-->
            <div class="resultBox">
                <span class="text">총금액</span>
                <span></span>
                <span class="result price"><fmt:formatNumber value="${totalPrice}" pattern="#,##0"/>원</span>
            </div>
            <div class="resultBox">
                <span class="text">보유포인트</span>
                <span ></span>
                <span class="result point"><fmt:formatNumber value="${sessionScope.author.point}" pattern="#,##0"/></span>
            </div>
            <div class="resultBox">
                <span class="text">총결제금액</span>
                <span ></span>
                <span class="result point"><fmt:formatNumber value="${totalPrice < sessionScope.author.point ? 0 : totalPrice - sessionScope.author.point}" pattern="#,##0"/>원</a></span>
            </div>
            <div name="dot"><img src="<c:url value="/img/dotted.png"/>"></div>
        </section>
        <section>
            <ul>
                <li id="payChoose">결제수단을 선택해주세요</li>
            </ul>
            <div id="cardBox"><!--결제선택 radio-->
                <label>
                    <div class="card c1">
                        <input type="radio" name="radio-button" class="radio-input" value="creditCard">
                        <img src="<c:url value="/img/card/cardImg.png"/>" alt="" id="credit">
                        <p class="cardInfo">신용카드</p>
                    </div>
                </label>
                <label>
                    <div class="card c2">
                        <input type="radio" name="radio-button" class="radio-input" value="kakaoPay">
                        <img src="<c:url value="/img/card/kakaoPay.png"/>" alt="" id="kakao">
                        <p class="cardInfo">카카오페이</p>
                    </div>
                </label>
                <label>
                    <div class="card c3">
                        <input type="radio" name="radio-button" class="radio-input " value="naverPay">
                        <img src="<c:url value="/img/card/naverpay.png"/>" alt="" id="naver">
                        <p class="cardInfo">네이버페이</p>
                    </div>
                </label>
            </div><!--결제선택 radio-->
        </section>
    </div>
    <input type="button" value="결제하기" id="payChooseBtn" onclick="selectedCheck('${publicKey}', '../inside/paymentSuccess')"><!--결제버튼-->
    <div id="cardModal">
        <img class="img" src="<c:url value="/img/card/card.gif"/>" alt="">
        <a href="<c:url value="/inside/payment/cancel"/>"><input type="button" value="취소" class="cancel"></a>
    </div>
    <div id="barCodeModal">
        <img class="img" src="<c:url value="/img/card/barCode.gif"/>" alt="">
        <a href="<c:url value="/inside/payment/cancel"/>"><input type="button" value="취소" class="cancel"></a>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="<c:url value="/js/payRadioBox.js"/>"></script>
<script src="<c:url value="/js/paymentModal.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");
</script>
</body>
</html>

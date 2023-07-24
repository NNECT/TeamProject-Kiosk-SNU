<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-07-12
  Time: 오전 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_payment.css"/>">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
  </style>
  <title>snu_newPassword_page</title>
</head>
<body>
<div id="whiteWrap">
  <section>
    <ul>
      <li id="pay">결제내역</li>
      <li id="homeLi"><a href="start.jsp"><img id="homeImg" src="<c:url value="/img/bluehome.png"/>"></a></li>
    </ul>
    <hr id="line">
    <form id="payFrm">
      <div id="payBox">
        <span id="product">상품</span>
        <span id="time">시간</span>
        <span class="price">가격</span>
      </div>
      <div id="payBox">
        <span id="product">상품</span>
        <span id="time">시간</span>
        <span class="price">가격</span>
      </div>
      <div id="dot"><img src="<c:url value="/img/dotted.png"/>"></div>
      <div id="resultBox">
        <span class="text">총금액</span>
        <span></span>
        <span class="result price">가격</span>
      </div>
      <div id="resultBox">
        <span class="text">보유포인트</span>
        <span ></span>
        <span class="result point">가격</span>
      </div>
      <div id="dot"><img src="<c:url value="/img/dotted.png"/>"></div>

      <button id="payBtn">  <!--결제금액-->원 결제하기</button>
    </form>
  </section>
</div>
</body>
</html>

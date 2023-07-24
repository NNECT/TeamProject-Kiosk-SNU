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
  <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_having.css"/>">
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
      <li id="pay">보유포인트</li>
      <li id="homeLi"><a href="start.jsp"><img id="homeImg" src="<c:url value="/img/bluehome.png"/>"></a></li>
    </ul>
    <hr id="line">
    <form id="payFrm">
      <div id="infoBox">
        <span class="product">상품명</span>
        <span class="seat">자리</span>
        <span class="paidTime">결제 기간/시간</span>
      </div>
      <c:if test="${remainDays > 0}">
        <div class="havingBox">
          <span class="product">정기권</span>
          <span></span>
          <span class="price">${remainDays}일</span>
        </div>
      </c:if>
      <c:if test="${remainTime > 0}">
        <div class="havingBox">
          <span class="product">시간권</span>
          <span></span>
          <span class="price">${remainTime}분</span>
        </div>
      </c:if>
      <c:if test="${remainLocker > 0}">
        <div class="havingBox">
          <span class="product">사물함</span>
          <span id="seat">${locker}</span>
          <span class="price">${remainLocker}일</span>
        </div>
      </c:if>

      <button id="payBtn" onclick="window.location.href='<c:url value="/outside/ticket/seat"/>'; return false;"> 이용권 구매하기</button><!--추가결제시 바로 결제페이지로 이동 -->
      <c:if test="${remainDays > 0 or remainTime > 0}">
        <button id="nextBtn"> 다음</button><!--다음결제시 바로 마지막페이지로 이동 -->
      </c:if>
    </form>
  </section>
</div>
</body>
</html>

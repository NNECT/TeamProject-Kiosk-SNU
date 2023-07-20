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
  <link rel="stylesheet" href="<c:url value="/css/snu_common_bg.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/snu_challenge.css"/>">
  <script src="hiddenBtn.js"></script>
  <script src="alertBtn.js"></script>
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
  </style>
  <title>snu_challenge_page</title>
</head>
<body>
<!--background-->
<section id="circleWrap1">
  <div id="bigCircle1"></div><!--원-->
  <div id="smallCircle1"></div><!--원-->
</section>
<section id="circleWrap2">
  <div id="bigCircle2"></div><!--원-->
  <div id="smallCircle2"></div><!--원-->
</section>
<!--background-->
<form action="">
  <section id="wrap"><!---->
    <div id="infoWrap">
      <p id="snuTitle">스누와함께 <span>챌린지</span>하고 <span>포인트</span>받아가세요!</p>
      <p id="challInfo">도전하고 싶은 챌린지를 선택해주세요</p>
    </div>
    <div class="box challeng1">
      <div>
        <div class="iconCircle c1">
          <img src="<c:url value="/img/challengeIcon/bolt.png"/>"  width="75">
        </div>
      </div>
      <p class="cTitle num1">벼락치기</p>
      <p class="cContent num1">24시간안에 6시간<br> 이상 사용하면</p>
      <p class="cPoint num1"><!--숫자만 변경 --><span id="point1">100</span><!--숫자만 변경 -->point</p>
    </div>
    <div class="box challeng2 ">
      <div>
        <div class="iconCircle c2">
          <img src="<c:url value="/img/challengeIcon/owl.png"/>" width="85">
        </div>
      </div>
      <p class="cTitle num2">올빼미</p>
      <p class="cContent num2">22시부터 6시까지<br>5시간 이상 사용하면</p>
      <p class="cPoint num2"><!--숫자만 변경 --><span id="point2">300</span><!--숫자만 변경 -->point</p>
    </div>
    <div class="box challeng3">
      <div>
        <div class="iconCircle c3">
          <img src="<c:url value="/img/challengeIcon/4days.png"/>" width="70">
        </div>
      </div>
      <p class="cTitle num3">작심삼일 박살</p>
      <p class="cContent num3">연속 4일 출석하면</p>
      <p class="cPoint num3"><!--숫자만 변경 --><span id="point3">500</span><!--숫자만 변경 -->point</p>
    </div>
    <c:forEach var ="challenge" items="${list}">
    <div class="box challenge" style="background-color: ${challenge.color}">
      <div>
        <div class="iconCircle c3">
          <img src="<c:url value="${challenge.imgageSrc}"/>" width="70">
        </div>
      </div>
      <p class="cTitle num3">${challenge.title}</p>
      <p class="cContent num3">${challenge.description}</p>
      <p class="cPoint num3"><!--숫자만 변경 --><span id="point">${challenge.rewardPoint}</span><!--숫자만 변경 -->point</p>
    </div>
    </c:forEach>
  </section>


  <div id="buttonWrap">
    <input type="button" value="다음에" id="nextBtn">
    <input type="button" value="시작하기" id="startBtn">
  </div>

</form>
</body>
</html>

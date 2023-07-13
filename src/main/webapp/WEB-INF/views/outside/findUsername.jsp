<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/12
  Time: 11:45 AM
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
    <link rel="stylesheet" href="<c:url value="/css/snu_common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_foundId.css"/>">
    <script src="hiddenBtn.js"></script>
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_foundId_page</title>
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
<section id="whiteBox">
    <form action="">
        <div id="menu"><!--매뉴창-->
            <ul>
                <li><a><img src="<c:url value="/img/start/home.png"/>" width="40px" height="40px"></a></li>
                <li><a href="/outside/foundPassword.html">비밀번호찾기</a></li>
                <li><a href="/outside/login.html">로그인</a></li>
                <li><a href="/outside/newMember.html">회원가입</a></li>
            </ul>
            <a href="/outside/finishId.html"><div id="showBtn"></div></a>
        </div>
        <div id="wrap"><!--wrap-->
            <p id="title">아이디 찾기</p>

            <div><!--아이콘 변경란-->
                <img src="<c:url value="/img/snu_loding.gif"/>" width="90" >
            </div>
            <div id="input"><!--입력란-->
                <p>전화번호를 입력해주세요</p>
                <input class="input tel" type="tel">
                <input type="button" class="btn " value="인증" onclick="alertBtn()" >
                <p>인증번호를 입력해주세요</p>
                <input class="input check" type="text"  id="checkCode" value="1234">
                <!-- <input type="button"  id="btn" class="btn" onclick="showBtn()" value="확인"> -->
                <button type="button" id="btn" class="btn" onclick="showBtn()" value="1234">확인</button>
            </div>
        </div>
    </form>
</section>
</body>
</html>

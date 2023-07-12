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
    <link rel="stylesheet" href="<c:url value="/css/snu_common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_newMember.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
    <script src="<c:url value="/js/hiddenBtn.js"/>"></script>
    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_foundPassword_page</title>
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
    <div id="menu"><!--매뉴창-->
        <ul>
            <li><a><img src="<c:url value="/img/start/home.png"/>" width="40px" height="40px"></a></li>
            <li><a href="void:javascript(0)">아이디찾기</a></li>
            <li><a href="void:javascript(0)">비밀번호찾기</a></li>
            <li><a href="/outside/login">로그인</a></li>
        </ul>
        <a href="snu_joinFinish.html"><div id="checkBtn"></div></a>
    </div>
    <div id="wrap"><!--wrap-->
        <p id="title">회원가입</p>
        <div id="input"><!--입력란-->
            <p>아이디를 입력해주세요</p>
            <input id="usernameBox" class="input id" type="text">
            <input type="button"  class="btn " value="인증" onclick="idCheck()">
            <p>전화번호를 입력해주세요</p>
            <input class="input tel" type="text">
            <input type="button"  class="btn " value="인증">
            <p>인증번호를 입력해주세요</p>
            <input class="input check" type="text">
            <input type="button"  class="btn " value="확인">
            <p>비밀번호를 입력해주세요</p>
            <input class="input password" type="password">
            <p>비밀번호를 다시 입력해주세요</p>
            <input class="input password" type="password">
        </div>
        <input type="checkbox" id="agree"><span id="agreeText" onclick="showBtn(this)">개인정보 수집동의</span>
        <span id="info">상세보기</span>
    </div>
</section>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script>
    function idCheck() {
        $.ajax({
            type: "POST",
            url: "../ajax/username",
            data: JSON.stringify({
                "username": document.getElementById("usernameBox").value
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.msg === "true") {
                    alert("사용 가능한 아이디입니다.:" + response.msg);
                } else {
                    alert("이미 사용중인 아이디입니다.:" + response.msg);
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
</script>
</body>
</html>
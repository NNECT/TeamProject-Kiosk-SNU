<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/12
  Time: 12:00 PM
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
    <link rel="stylesheet" href="<c:url value="/css/snu_foundPassword.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
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
<form id="findPassword" action="<c:url value="/outside/findPassword"/>" method="post">
    <section id="whiteBox">
        <div id="menu"><!--매뉴창-->
            <ul>
                <li><a href="<c:url value="/outside/logout"/>"><img src="<c:url value="/img/start/home.png"/>" width="40px"
                                                             height="40px"></a></li>
                <li>
                    <a href="<c:url value="/outside/login?type=${sessionScope.selectType}&number=${sessionScope.selectNumber}" />">로그인</a>
                </li>
                <li><a href="<c:url value="/outside/findUsername" />">아이디찾기</a></li>
                <li><a href="<c:url value="/outside/findPassword" />">비밀번호찾기</a></li>
            </ul>
            <div id="checkBtn"><input type='button' value='다음' id='newBtn' style="display: none"></div>
        </div>
        <div id="wrap"><!--wrap-->
            <p id="title">비밀번호 찾기</p>

            <div id="changeIcon"><!--아이콘 변경란-->
                <img src="<c:url value="/img/snu_loding.gif"/>" width="90">
            </div>
            <div id="input"><!--입력란-->
                <p>
                    <label for="username">아이디를 입력해주세요</label>
                    <span id="usernameCheck" style="float: right; color: blue; margin-right: 105px"></span>
                </p>
                <input id="username" class="input id" type="text" name="username" required minlength="6" maxlength="30" >

                <p>
                    <label for="phoneNumber">전화번호를 입력해주세요</label>
                    <span id="codeSendCheck" style="float: right; color: blue; margin-right: 105px"></span>
                </p>
                <input id="phoneNumber" class="input tel" type="text" maxlength="13"
                       style="letter-spacing: 6px; text-align: center">
                <input type="button" class="btn " value="인증" onclick="codeSend()">

                <p>
                    <label for="code">인증번호를 입력해주세요</label>
                    <span id="codeCheck" style="float: right; color: blue; margin-right: 105px"></span>
                </p>
                <input id="code" class="input check" type="text" maxlength="4"
                       style="letter-spacing: 2px; text-align: center">
                <input type="button" class="btn " value="확인" onclick="codeCheck()">
            </div>
        </div>
    </section>
</form>

<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/findPassword.js"/>"></script>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(60, "<c:url value="/outside/logout"/>");
</script>
</body>
</html>

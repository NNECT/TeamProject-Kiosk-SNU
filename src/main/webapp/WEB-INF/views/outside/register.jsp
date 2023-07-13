<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li><a href="<c:url value="/outside/logout"/>"><img src="<c:url value="/img/start/home.png"/>" width="40px" height="40px"></a></li>
            <li><a href="<c:url value="/outside/login?type=${sessionScope.selectType}&number=${sessionScope.selectNumber}" />">로그인</a></li>
            <li><a href="<c:url value="/outside/findUsername" />">아이디찾기</a></li>
            <li><a href="<c:url value="/outside/findPassword" />">비밀번호찾기</a></li>
        </ul>
        <div id="checkBtn"><input type='button' value='가입' id='newBtn' style="display: none"></div>
    </div>
    <div id="wrap"><!--wrap-->
        <p id="title">회원가입</p>
        <div id="input"><!--입력란-->
            <p>
                <label for="username">아이디를 입력해주세요</label>
                <span id="usernameCheck" style="float: right; color: blue; margin-right: 105px"></span>
            </p>
            <input id="username" class="input username" type="text">
            <input type="button"  class="btn " value="인증" onclick="usernameCheck()">

            <p>
                <label for="phoneNumber">전화번호를 입력해주세요</label>
                <span id="codeSendCheck" style="float: right; color: blue; margin-right: 105px"></span>
            </p>
            <input id="phoneNumber" class="input phoneNumber" type="text" maxlength="13" style="letter-spacing: 6px; text-align: center">
            <input type="button"  class="btn " value="인증" onclick="codeSend()">

            <p>
                <label for="code">인증번호를 입력해주세요</label>
                <span id="codeCheck" style="float: right; color: blue; margin-right: 105px"></span>
            </p>
            <input id="code" class="input check" type="text" maxlength="4" style="letter-spacing: 2px; text-align: center">
            <input type="button"  class="btn " value="확인" onclick="codeCheck()">

            <p><label for="password">비밀번호를 입력해주세요</label></p>
            <input id="password" class="input password" type="password">

            <p>
                <label for="password_confirm">비밀번호를 다시 입력해주세요</label>
                <span id="passwordCheck" style="float: right; color: blue; margin-right: 105px"></span>
            </p>
            <input id="password_confirm" class="input password" type="password">
        </div>
        <input type="checkbox" id="agree"><span id="agreeText"><label for="agree">개인정보 수집동의</label></span>
        <span id="info">상세보기</span>
    </div>
</section>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="<c:url value="/js/register.js"/>"></script>
<script>
    const crypt = new JSEncrypt();
    crypt.setPublicKey("${publicKey}");

    document.getElementById("newBtn").addEventListener("click", registerSubmit);
    function registerSubmit(e) {
        if (!document.getElementById("agree").checked) return;

        const form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "<c:url value="/outside/register" />");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("hidden", "true");

        const usernameInput = document.createElement("input");
        usernameInput.setAttribute("type", "text");
        usernameInput.setAttribute("name", "username");
        usernameInput.setAttribute("value", document.getElementById("username").value);
        form.appendChild(usernameInput);

        const passwordInput = document.createElement("input");
        passwordInput.setAttribute("type", "password");
        passwordInput.setAttribute("name", "password");
        passwordInput.setAttribute("value", crypt.encrypt(document.getElementById("password").value));
        form.appendChild(passwordInput);

        const phoneNumberInput = document.createElement("input");
        phoneNumberInput.setAttribute("type", "text");
        phoneNumberInput.setAttribute("name", "phoneNumber");
        phoneNumberInput.setAttribute("value", document.getElementById("phoneNumber").value);
        form.appendChild(phoneNumberInput);

        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>
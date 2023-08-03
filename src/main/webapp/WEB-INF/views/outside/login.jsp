<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/12
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/snu_common_bg.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_login.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/loginModal.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_login_page</title>
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
<section id="whiteBox"><!--흰 박스-->
    <img src="<c:url value="/img/login/snu_logo.png"/>" width="70" height="70">
    <p><strong>SNU</strong> 스터디</p>
    <form id="sudo-login-form">
        <input class="input" id="username" type="text" name="username" placeholder="아이디를 입력해주세요"
               required minlength="6" maxlength="30"><br>
        <input class="input" id="password" type="password" name="password" placeholder="비밀번호를 입력해주세요" required><br>
        <input id="loginBtn" type="submit" value="로그인">
    </form>
    <div id="box">
        <a href="<c:url value="/outside/register" />">회원가입</a>
        <a href="<c:url value="/outside/findUsername" />">아이디찾기</a>
        <a href="<c:url value="/outside/findPassword" />">비밀번호찾기</a>
    </div>
    <!-- 로그인실패 모달 -->
    <div id="loginFail">
        <div id="modalContent">
            <p id="modalP">
                로그인 실패<br>
                <strong>아이디와 비밀번호</strong>를
                <br><strong>확인</strong>해주세요
            </p>
        </div>
        <div id="modalBtn">
            <input type="button" id="modalCheckBtn" value="확인">
        </div>
    </div>

    <!--중복 자리선택시 모달-->
    <div id="seatModal">
        <div class="modalContent">
            <p class="modalP">

                이미<strong class="modalStrong">${duplicatedNum}번자리</strong>를 <strong class="modalStrong">사용중</strong>입니다.<br>

                현재 선택하신 자리를 사용하고 싶으시면 <br>
                <strong class="modalStrong">내부키오스크에서 로그아웃 후 진행</strong><br>해주세요.
            </p>
        </div>
        <div class="modalBtn">
            <input type="button" id="modalYesBtn" value="확인">
        </div>
    </div>

</section>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(60, "<c:url value="/outside/logout"/>");

    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("sudo-login-form").addEventListener("submit", function (e) {
            e.preventDefault();

            const crypt = new JSEncrypt();
            crypt.setPublicKey("${publicKey}");

            const username = document.getElementById("username").value;
            const password = crypt.encrypt(document.getElementById("password").value);

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "<c:url value="/outside/login" />");
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("hidden", "true");

            const usernameInput = document.createElement("input");
            usernameInput.setAttribute("type", "text");
            usernameInput.setAttribute("name", "username");
            usernameInput.setAttribute("value", username);
            form.appendChild(usernameInput);

            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("name", "password");
            passwordInput.setAttribute("value", password);
            form.appendChild(passwordInput);

            document.body.appendChild(form);
            form.submit();
        });


    });
    //로그인 실패 모달창
    var loginFail = "${loginFail}";
    if (loginFail === "loginFail") {
        //로그인 fail되면 모달창 활성화
        var loginFailModal = document.getElementById("loginFail");
        loginFailModal.style.display = "block";
        //모달창 확인버튼 눌러주면 다시 비활성화
        var modalCheckBtn = document.getElementById("modalCheckBtn");
        modalCheckBtn.addEventListener("click", function () {
            loginFailModal.style.display = "none";
        });
    }

    var duplicatedID = "${duplicatedID}";
    if (duplicatedID === "duplicatedID") {
        //중복로그인 check
        var seatModal = document.getElementById("seatModal");
        seatModal.style.display = "block";
        //모달창 확인버튼 눌러주면 다시 비활성화
        var modalBtn = document.getElementById("modalYesBtn");
        modalBtn.addEventListener("click", function () {
            seatModal.style.display = "none";
        });
    }
</script>


</body>
</html>

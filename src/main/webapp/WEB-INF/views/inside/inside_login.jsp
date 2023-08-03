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
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_login.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_loginModal.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_start_page</title>
</head>
<body>
<div id="body">
    <section id="circleWrap1">
        <div id="bigCircle1"></div><!--원-->
        <div id="smallCircle1"></div><!--원-->
    </section>
    <section id="circleWrap2">
        <div id="bigCircle2"></div><!--원-->
        <div id="smallCircle2"></div><!--원-->
    </section>

    <section id="allWrap"><!--전체 감싸는 박스-->
        <form id="inside-login-form">
            <div id="login"><!--클릭란-->
                <p id="seatNum"><strong>${sessionScope.insideNumber}번</strong></p>
                <div id="line">
                    <hr>
                </div>
                <p id="userName">${sessionScope.author.username}님</p>

                <div id="Timer"><!--남은시간 나타내는 곳-->
                    <span id="saveTime">남은 시간:</span>
                    <span id="time">-</span>
                </div>

                <input type="password" id="password" placeholder="비밀번호를 입력하세요"><br>
                <input type="submit" id="loginBtn" value="로그인">
            </div>
        </form>
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
        <!-- 모달 -->
    </section>
</div>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("inside-login-form").addEventListener("submit", function (e) {
            e.preventDefault();

            const crypt = new JSEncrypt();
            crypt.setPublicKey("${publicKey}");

            const password = crypt.encrypt(document.getElementById("password").value);

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "<c:url value="/inside/login" />");
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("hidden", "true");

            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("name", "password");
            passwordInput.setAttribute("value", password);
            form.appendChild(passwordInput);

            document.body.appendChild(form);
            form.submit();
        });

        new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", document.getElementById("saveTime"), document.getElementById("time"), "<c:url value="/inside/logout" />");
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
</script>
</body>
</html>

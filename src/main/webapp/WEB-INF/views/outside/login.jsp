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
  <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_common_bg.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/snu_login.css"/>">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
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
    <input class="input" id="username" type="text" name="username" placeholder="아이디를 입력해주세요"><br>
    <input class="input" id="password" type="password" name="password" placeholder="비밀번호를 입력해주세요"><br>
    <input id="loginBtn" type="submit" value="로그인">
  </form>
  <div id="box">
    <a href="<c:url value="/outside/foundUsername" />">아이디찾기</a>
    <a href="<c:url value="/outside/foundPassword" />">비밀번호찾기</a>
    <a href="<c:url value="/outside/register" />">회원가입</a>
  </div>
</section>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script>
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
</script>
</body>
</html>

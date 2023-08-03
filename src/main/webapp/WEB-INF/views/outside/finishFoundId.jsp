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
    <link rel="stylesheet" href="<c:url value="/css/snu_common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_finishId.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_finishId_page</title>
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
        <form>
            <img src="<c:url value="/img/id.png" />" width="110" height="110">
            <p>회원님의 아이디는</p>
            <p id="showId">${username}</p>
            <p>입니다.다시 로그인 해주세요</p>
            <a href="<c:url value="/outside/login?type=${sessionScope.selectType}&number=${sessionScope.selectNumber}" />"><input type="button"  id="loginBtn" value="로그인하러가기" ></a>
        </form>
    </section>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(60, "<c:url value="/outside/logout"/>");
</script>
</body>
</html>

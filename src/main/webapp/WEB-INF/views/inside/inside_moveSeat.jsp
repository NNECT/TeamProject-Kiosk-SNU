<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_moveSeat.css"/>">
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
        <p id="MyTimer">10</p>
        <p id="title">자리이동 완료!</p><!--입실완료면 '입실완료!'-->
        <p id="time">
            10초뒤 로그아웃됩니다.
            <br>선택하신 자리로 이동해주세요.
        </p>

    </section>

</div>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(10, "<c:url value="/inside/index"/>", document.getElementById('MyTimer'), 2);
</script>
</body>
</html>

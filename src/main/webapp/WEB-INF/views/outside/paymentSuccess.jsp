<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/snu_end.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_start_page</title>
</head>
<body>
<section id="circleWrap1">
    <div id="bigCircle1"></div><!--원-->
    <div id="smallCircle1"></div><!--원-->
</section>
<section id="circleWrap2">
    <div id="bigCircle2"></div><!--원-->
    <div id="smallCircle2"></div><!--원-->
</section>

<section id="allWrap"><!--전체 감싸는 박스-->

    <img src="<c:url value="/img/check.png"/>" alt="">

    <p id="title">결제완료!</p><!--입실완료면 '입실완료!'-->
    <p id="time"><span id="MyTimer">3</span></p>
    <p id="introduce"><strong>챌린지 참여</strong>하고 <strong>최대 500point</strong>받으세요!</p>
    <a href="<c:url value='/outside/end'/>"><input type="button" id="moveBtn" value="바로 입실"></a>
</section>

<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(3, "<c:url value="/outside/challenge/list"/>", document.getElementById('MyTimer'), 1);
</script>
</body>
</html>
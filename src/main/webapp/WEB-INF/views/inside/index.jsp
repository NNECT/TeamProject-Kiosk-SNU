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
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_start.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
    <style>
        body {font-family: 'SUITE', sans-serif;}
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
        <a href="<c:url value="/inside/login"/>">
            <div id="start"><!--클릭란-->
                <p id="snuStudy"><strong>${sessionScope.insideNumber}번</strong></p>
                <div id="iconWrap">
                    <div id="circle1"></div><!--원1-->
                    <div id="circle2"></div><!--원2-->
                    <img id="finger" src="<c:url value="/img/inside/start/cursor-finger.png"/>" width="60" height="60">
                </div>
                <p id="explain">시작하려면 클릭하세요</p>
            </div>
        </a>
    </section>
</div>
</body>

</html>
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
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_logout.css"/>">

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
        <form action="javascript: void(0)">
            <div id="alarm"><!--클릭란-->
                <div id="icon">
                    <img src="<c:url value="/img/inside/endicon/check-circle.png"/>">
                </div>
                <c:choose>
                    <c:when test="${not empty challenge}">
                        <p id="explain">0000챌린지</p><!--문구 변경 -->
                        <p id="SorF">!성공!</p><!--문구 변경 -->
                    </c:when>
                    <c:otherwise>
                        <p id="explain">퇴실하시겠습니까?</p>
                        <p id="SorF">사용시간 ${usedTime}분</p>
                    </c:otherwise>
                </c:choose>
                <div id="btn">
                    <div id="noBox">
                        <input type="button" class="no btn" value="취소"><!--홈화면으로이동 좌석사용가능-->
                    </div>
                    <div id="yesBox">
                        <input type="button" class="yes btn" value="퇴실"><!--홈화면으로이동 로그인유지,좌석사용중-->
                    </div>
                </div>
            </div>
        </form>
    </section>
</div>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");

    const noBtn = document.querySelector('.no');
    const yesBtn = document.querySelector('.yes');

    window.addEventListener("DOMContentLoaded", function () {
        noBtn.addEventListener('click', () => {
            location.href = "<c:url value="/inside/menu"/>"
        });
        yesBtn.addEventListener('click', () => {
            location.href = "<c:url value="/inside/logout"/>"
        });
    });
</script>
</body>
</html>
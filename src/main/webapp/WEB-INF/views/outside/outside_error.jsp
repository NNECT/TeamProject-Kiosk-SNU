<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/css/snu_error.css"/>">
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
                
                <img src="<c:url value="/img/start/triangle-warning.png"/>" alt="">
                
                <p id="title">404 ERROR</p>
                <p id="time"><span id="MyTimer">5</span>초뒤 홈화면으로 이동합니다.</p>
                <p id="introduce">*에러가 반복적으로 발생하면 02-1111-1111로 연락바랍니다.</p>
            </section>

            <script>
                let time = 5;
                let timer = setInterval(function () {
                    time--;
                    document.getElementById('MyTimer').innerText = String(time).padStart(2, '0');
                    if (time === 0) {
                        clearInterval(timer);
                        location.href = "<c:url value="/outside/start"/>";
                    }
                }, 500);
            </script>
          
        </body>
 </html>
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
                
                <img src="../snu/img/endIcon.png" alt="">
                
                <p id="title">결제완료!</p><!--입실완료면 '입실완료!'-->
                <p id="time"><span id="MyTimer">10</span>초뒤 홈화면으로 이동합니다.</p>
               <a href="../snu/snu_start.html"><input type="button" id="moveBtn" value="홈화면으로 이동"></a>
            </section>

            <script type="text/javascript">
                function startTimer(duration, display) {
                  var timer = duration, minutes, seconds;
                  var interval = setInterval(function () {
                    //minutes = parseInt(timer / 60, 10)
                    seconds = parseInt(timer % 60, 10);
                
                    //minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;
                
                    //display.textContent = minutes + ":" + seconds;
                    display.textContent = seconds;
                
                    if (--timer < 0) {
                      timer = duration;
                    }
                    if(timer === 0) {
                      clearInterval(interval);
                      display.textContent = "0";
                    }
                  }, 1000);
                }
                
                window.onload = function () {
                  /* 기본값 10(분)입니다. */
                  var seconds = 10;
                
                  var fiveSeconds = (seconds) - 1,
                    display = document.querySelector('#MyTimer');
                  startTimer(fiveSeconds, display);
                };
             </script>   
           <script type="text/javascript"> 
            setTimeout('gotoPage()', 10000); // 3초 후 실행
            
            function gotoPage(){
                location.href="snu_start.html"  // 이동주소
            }
            </script>
        </body>
 </html>
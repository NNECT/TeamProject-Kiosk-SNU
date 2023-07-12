<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_start.css"/>">
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
  <div id="warning"><!--공지란-->
    <img id="warningIcon" src="<c:url value="/img/start/triangle-warning.png"/>" width="35" height="35">
    <div id="warningText"><strong> 공지공지공지공지</strong></div><!--공지나타나는 곳-->
  </div>
  <a href="<c:url value="/outside/select"/>">
    <div id="start"><!--클릭란-->
      <p id="snuStudy"><strong>SNU</strong> 스터디</p>
      <div id="iconWrap">
        <div id="circle1"></div><!--원1-->
        <div id="circle2"></div><!--원2-->
        <img id="finger"src="<c:url value="/img/start/cursor-finger.png"/>" width="60" height="60">
      </div>
      <p id="explain">시작하려면 클릭하세요</p>
    </div>
  </a>
</section>
</body>
</html>
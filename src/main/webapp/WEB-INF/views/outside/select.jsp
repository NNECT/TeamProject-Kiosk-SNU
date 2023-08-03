<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,max mun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_sit.css"/>">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
  </style>
  <title>snu_seat_page</title>
</head>
<body>
<p id="title">좌석을 선택해주세요</p>
<div id="seatWrap">
  <div id="info"><!--자리설명-->
    <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
    <div class="box g"></div>&nbsp;<span class="gray">사용중</span>
    <div class="box r"></div>&nbsp;<span class="red">사용 불가능</span>
  </div>
  <section>
    <form>
      <c:import url="../common/select.jsp"/>
    </form>
    <div id="door"><!--출입문-->
      <p>출입문</p>
    </div>
  </section>
  <div id="locker"><!--사물함-->
    <p>사물함</p>
  </div>
</div>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
  timeoutRedirect(60, "<c:url value="/outside/logout"/>");

  window.addEventListener('DOMContentLoaded', (event) => {
    const seats = document.querySelectorAll('.seat');
    const seatStatus = ${seatStatusMap};
    seats.forEach((seat, index) => {
      if(seatStatus[Number(seat.id)] === 1) {
        seat.classList.remove('r');
        seat.classList.add('b');
        seat.innerHTML = "<a href='<c:url value="/outside/login"/>?type=seat&number=" + seat.id + "'>" + seat.id + "</a>"
      } else if (seatStatus[Number(seat.id)] === 0) {
        seat.classList.remove('r');
        seat.classList.add('g');
        seat.innerHTML = seat.id;
      } else {
        seat.innerHTML = seat.id;
      }
    });

    const rooms = document.querySelectorAll('.room');
    const roomStatus = ${roomStatusMap};
    rooms.forEach((room, index) => {
      const roomId = Number(room.id.substring(1));
        if(roomStatus[roomId] === 1) {
            room.classList.remove('r');
            room.classList.add('b');
            room.innerHTML = "<a href='<c:url value="/outside/login"/>?type=room&number=" + roomId + "'>" + "R"+ roomId + "</a>"
        } else if (roomStatus[roomId] === 0) {
            room.classList.remove('r');
            room.classList.add('g');
            room.innerHTML = "R"+roomId;
        } else {
            room.innerHTML = "R"+roomId;
        }
    });
  });
</script>
</body>
</html>
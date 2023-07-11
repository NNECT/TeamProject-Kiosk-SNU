<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-07-11
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/css/snu_sit.css"/>">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
  </style>
  <title>snu_site_page</title>
</head>
<body>
<p id="title">좌석을 선택해주세요</p>
<div id="info"><!--자리설명-->
  <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
  <div class="box g"></div>&nbsp;<span class="gray">사용 불가능</span>
</div>
<section>
  <form>
    <table>
      <tr>
        <td class="seat g" id="1"></td>
        <td class="seat g" id="14"></td>
        <td></td>
        <td></td>
        <td class="seat g" id="15"></td>
        <td class="seat g" id="16"></td>
        <td class="seat g" id="17"></td>
        <td class="seat g" id="18"></td>
        <td class="seat g" id="19"></td>
        <td class="seat g" id="20"></td>
        <td class="seat g" id="21"></td>
        <td class="seat g" id="22"></td>
        <td class="seat g" id="23"></td>
      </tr>
      <tr>
        <td class="seat g" id="2"></td>
        <td class="seat g" id="13"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="seat g" id="3"></td>
        <td class="seat g" id="12"></td>
        <td></td>
        <td></td>
        <td class="seat g" id="32"></td>
        <td class="seat g" id="31"></td>
        <td class="seat g" id="30"></td>
        <td class="seat g" id="29"></td>
        <td class="seat g" id="28"></td>
        <td class="seat g" id="27"></td>
        <td class="seat g" id="26"></td>
        <td class="seat g" id="25"></td>
        <td class="seat g" id="24"></td>
      </tr>
      <tr>
        <td class="seat g" id="4"></td>
        <td class="seat g" id="11"></td>
        <td></td>
        <td></td>
        <td class="seat g" id="33"></td>
        <td class="seat g" id="34"></td>
        <td class="seat g" id="35"></td>
        <td class="seat g" id="36"></td>
        <td class="seat g" id="37"></td>
        <td class="seat g" id="38"></td>
        <td class="seat g" id="39"></td>
        <td class="seat g" id="40"></td>
        <td class="seat g" id="41"></td>
      </tr>
      <tr>
        <td class="seat g" id="5"></td>
        <td class="seat g" id="10"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="seat g" id="6"></td>
        <td class="seat g" id="9"></td>
        <td></td><td></td><td></td><td></td><td></td>
        <td colspan="3" rowspan="3" class="room"><a href="snu_login.html">room1</a></td>
        <td colspan="3" rowspan="3" class="room"><a href="snu_login.html">room2</a></td>
      </tr>
      <tr>
        <td class="seat g" id="7"></td>
        <td class="seat g" id="8"></td>
        <td></td><td></td><td></td><td></td><td></td>
      </tr>
      <tr>
        <td></td><td></td><td></td><td colspan="2"></td><td></td><td></td>
      </tr>
    </table>
  </form>
  <div id="door"><!--출입문-->
    <p>출입문</p>
  </div>
</section>
<div id="locker"><!--사물함-->
  <p>사물함</p>
</div>
<script>
  window.addEventListener('DOMContentLoaded', (event) => {
    const seats = document.querySelectorAll('.seat');
    const status = ${seatStatusMap};
    seats.forEach((seat, index) => {
      if(status[Number(seat.id)] === 1) {
        seat.classList.remove('g');
        seat.classList.add('b');
        seat.innerHTML = "<a href='/outside/login?type=seat&number=" + seat.id + "'>" + seat.id + "</a>"
      } else {
        seat.innerHTML = seat.id;
      }
    });
  });
</script>
</body>
</html>
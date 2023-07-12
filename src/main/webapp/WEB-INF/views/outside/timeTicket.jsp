<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-07-12
  Time: 오전 9:20
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
  <link rel="stylesheet" href="<c:url value="/css/snu_ticket.css"/>">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
  </style>
  <title>snu_newPassword_page</title>
</head>
<body>
<div id="whiteWrap">
  <section>
    <ul>
      <li id="timeTicket">시간권</li>
      <li id="commutationTicket"><a href="/outside/commutationTicket .html">정기권</a></li>
    </ul>
    <hr>
    <form action=""><!--티켓영역다 -->
      <table>
        <tr>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
        </tr>
        <tr>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
          <td>
            <p class="t" name="time">3시간</p>
            <p class="p" name="price">6,000원</p>
          </td>
        </tr>
      </table>
      <input id="nextBtn" type="submit" value="다음">
    </form>
  </section>
</div>
</body>
</html>

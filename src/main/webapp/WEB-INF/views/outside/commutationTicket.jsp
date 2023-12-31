<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    .radio-input {display: none;}
  </style>
  <title>snu_newPassword_page</title>
</head>
<body>
<div id="whiteWrap">
  <section>
    <ul>
      <li id="unselectedTicket"><a href="<c:url value="/outside/ticket/seat/timeTicket"/>">시간권</a></li>
      <li id="selectedTicket">정기권</li>
      <li id="homeLi"><a href="<c:url value="/outside/logout"/>"><img id="homeImg" src="<c:url value="/img/bluehome.png"/>"></a></li>
    </ul>
    <hr>
    <form action="<c:url value="/outside/ticket/seat/commutationTicket"/>" method="post" onsubmit="return selectedCheck()">
      <table>
        <tr>
          <c:forEach var="i" begin="0" end="2" step="1">
            <c:choose>
              <c:when test="${commutationTicketList.size() > i}">
                <td class="radio-box">
                  <input type="radio" name="radio-button" class="radio-input" value="${commutationTicketList[i].id}">
                  <p class="t btn-text">${commutationTicketList[i].day}일</p>
                  <p class="p btn-text"><fmt:formatNumber value="${commutationTicketList[i].price}" pattern="#,##0"/>원</p>
                </td>
              </c:when>
              <c:otherwise>
                <td class="radio-box"></td>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </tr>
        <tr>
          <c:forEach var="i" begin="3" end="5" step="1">
            <c:choose>
              <c:when test="${commutationTicketList.size() > i}">
                <td class="radio-box">
                  <input type="radio" name="radio-button" class="radio-input" value="${commutationTicketList[i].id}">
                  <p class="t btn-text">${commutationTicketList[i].day}일</p>
                  <p class="p btn-text"><fmt:formatNumber value="${commutationTicketList[i].price}" pattern="#,##0"/>원</p>
                </td>
              </c:when>
              <c:otherwise>
                <td class="radio-box"></td>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </tr>
      </table>
      <input id="nextBtn" type="submit" value="다음">
    </form>
  </section>
</div>
<script src="<c:url value="/js/radioBox.js"/>"></script>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
  timeoutRedirect(60, "<c:url value="/outside/logout"/>");
</script>
</body>
</html>

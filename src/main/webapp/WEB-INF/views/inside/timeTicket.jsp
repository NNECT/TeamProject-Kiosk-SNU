<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_timeTicket.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_timeTicket_page</title>
</head>
<body>
<div id="body">
    <a href="<c:url value="/inside/menu"/>"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

    <div id="whiteWrap">
        <section>
            <ul>
                <li class="currentTicketButton">시간권</li>
                <li class="otherTicketButton"><a href="<c:url value="/inside/ticket/seat/commutationTicket"/>">정기권</a></li>
                <li class="otherTicketButton"><a href="<c:url value="/inside/ticket/locker"/>">사물함</a></li>
            </ul>
            <hr>
            <form action="<c:url value="/inside/ticket/seat/timeTicket"/>" method="post">
                <table>
                    <tr>
                        <c:forEach var="i" begin="0" end="2" step="1">
                            <c:choose>
                                <c:when test="${timeTicketList.size() > i}">
                                    <td class="radio-box">
                                        <input type="radio" name="radio-button" class="radio-input" value="${timeTicketList[i].id}">
                                        <p class="t btn-text">${timeTicketList[i].time}시간</p>
                                        <p class="p btn-text"><fmt:formatNumber value="${timeTicketList[i].price}" pattern="#,##0"/>원</p>
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
                                <c:when test="${timeTicketList.size() > i}">
                                    <td class="radio-box">
                                        <input type="radio" name="radio-button" class="radio-input" value="${timeTicketList[i].id}">
                                        <p class="t btn-text">${timeTicketList[i].time}시간</p>
                                        <p class="p btn-text"><fmt:formatNumber value="${timeTicketList[i].price}" pattern="#,##0"/>원</p>
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
</div>
<script src="<c:url value="/js/radioBox.js"/>"></script>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");
</script>
</body>
</html>
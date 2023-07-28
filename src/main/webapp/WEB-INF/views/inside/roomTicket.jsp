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
            </ul>
            <hr>
            <form action="<c:url value="/inside/ticket/room"/>" method="post">
                <table>
                    <tr>
                        <c:forEach var="i" begin="1" end="3" step="1">
                            <td class="radio-box">
                                <input type="radio" name="radio-button" class="radio-input" value="${i}">
                                <p class="t btn-text">${i}시간</p>
                                <p class="p btn-text"><fmt:formatNumber value="${room.roomType_price * i}" pattern="#,##0"/>원</p>
                            </td>
                        </c:forEach>
                    </tr>
                </table>
                <input id="nextBtn" type="submit" value="다음">
            </form>
        </section>
    </div>
</div>
<script src="<c:url value="/js/radioBox.js"/>"></script>
</body>
</html>
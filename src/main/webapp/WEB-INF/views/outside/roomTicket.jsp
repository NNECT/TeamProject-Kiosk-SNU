<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/snu_roomTicket.css"/>">
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
            <li id="homeLi"><a href="<c:url value="/outside/logout"/>"><img id="homeImg" src="<c:url value="/img/bluehome.png"/>"></a></li>
        </ul>
        <hr>
        <form action="<c:url value="/outside/ticket/room"/>" method="post" onsubmit="return selectedCheck()">
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
<script src="<c:url value="/js/radioBox.js"/>"></script>
<script src="<c:url value="/js/timeoutRedirect.js"/>"></script>
<script>
    timeoutRedirect(60, "<c:url value="/outside/logout"/>");
</script>
</body>
</html>

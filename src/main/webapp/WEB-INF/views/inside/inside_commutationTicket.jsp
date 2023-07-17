<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_commutationTicket.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_commutationTicket_page</title>
</head>
<body>
<div id="body">
    <a href="/inside/inside_select.jsp"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>
    <div id="whiteWrap">
        <section>
            <ul>
                <li id="timeTicket"><a href="inside_timeTicket.jsp">시간권</a></li>
                <li id="commutationTicket">정기권</li>
            </ul>
            <hr>
            <form>
                <table>
                    <tr>
                        <td class="radio-box">
                            <input type="radio" name="radio-button" class="radio-input" value="">
                            <p class="t btn-text">3일</p>
                            <p class="p btn-text">30,000원</p>
                        </td>
                        <td class="radio-box">
                            <input type="radio" name="radio-button" class="radio-input" value="">
                            <p class="t btn-text">7일</p>
                            <p class="p btn-text">50,000원</p>
                        </td>
                        <td class="radio-box">
                            <input type="radio" name="radio-button" class="radio-input" value="">
                            <p class="t btn-text">15일</p>
                            <p class="p btn-text">90,000원</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="radio-box">
                            <input type="radio" name="radio-button" class="radio-input" value="">
                            <p class="t btn-text">30일</p>
                            <p class="p btn-text">160,000원</p>
                        </td>

                    </tr>
                </table>
                <input id="nextBtn" type="submit" value="다음">
            </form>
        </section>
    </div>
    <script src="/js/radioBox.js"></script>
</div>
</body>

</html>

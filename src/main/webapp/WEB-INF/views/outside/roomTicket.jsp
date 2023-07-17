<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </ul>
        <hr>
        <form>
            <table>
                <tr>
                    <td class="radio-box">
                        <input type="radio" name="radio-button" class="radio-input" value="">
                        <p class="t btn-text" name="time">1시간</p>
                        <p class="p btn-text" name="price">6,000원</p>
                    </td>
                    <td class="radio-box">
                        <input type="radio" name="radio-button" class="radio-input" value="">
                        <p class="t btn-text" name="time">2시간</p>
                        <p class="p btn-text" name="price">6,000원</p>
                    </td>
                    <td class="radio-box">
                        <input type="radio" name="radio-button" class="radio-input" value="">
                        <p class="t btn-text" name="time">3시간</p>
                        <p class="p btn-text" name="price">6,000원</p>
                    </td>
                </tr>

                </tr>
            </table>
            <input id="nextBtn" type="submit" value="다음">
        </form>
    </section>
</div>
<script src="/js/radioBox.js"></script>
</body>
</html>
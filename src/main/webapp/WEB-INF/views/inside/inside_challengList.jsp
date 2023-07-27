<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_challengList.css"/>">

    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_mypage_page</title>
</head>
<body>
<div id="body">
    <a href="inside_menu.jsp"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

    <section id="allWrap"><!--전체 감싸는 박스-->
        <p id="title">마이페이지</p>
        <hr>
        <div id="tableWrap">
            <table>
                <tr id="trTitle">
                    <th id="date">날짜</th>
                    <th id="name">이름</th>
                    <th id="result">결과</th>
                    <th id="point">받은포인트</th>
                </tr>
                <tr id="content"><!--반복분 부분-->
                    <td class="date">2000-11-11</td>
                    <td class="name">작심삼일</td>
                    <td class="result">성공</td>
                    <td class="point">500point</td>
                </tr>
            </table>
        </div>
    </section>
</div>
</body>

</html>
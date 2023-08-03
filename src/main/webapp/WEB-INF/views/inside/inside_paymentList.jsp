<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/12
  Time: 11:45 AM
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
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_paymentList.css"/>">

    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_paymentList_page</title>
</head>
<body>
<div id="body">
    <a href="inside_menu.jsp"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

    <section id="allWrap"><!--전체 감싸는 박스-->
        <p id="title">결제내역</p>
        <hr>
        <div id="tableWrap">
            <table>
                <tr id="trTitle">
                    <th id="date">날짜</th><th id="name">상품이름</th><th id="price">금액</th><th id="print">사용포인트</th><th id="pay">결제금액</th>
                </tr>
                <tr id="content"><!--반복분 부분-->
                    <td class="date">2000-11-11</td><td class="name">시간권/3시간</td><td class="price">6,000</td><td class="point">500point</td><td class="pay">5,500</td>
                </tr>
            </table>
        </div>
    </section>
</div>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");
</script>
</body>
</html>

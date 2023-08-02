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
        <link rel="stylesheet" href="<c:url value="/css/inside/inside_noticeList.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_notice_page</title>
    </head>
        <body>
            <div id="body">
                <a href="<c:url value="/inside/menu"/>"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

                <section id="allWrap"><!--전체 감싸는 박스-->
                    <p id="title">공지목록</p>
                    <hr>
                    <div id="tableWrap">
                        <table>
                            <tr id="trTitle">
                                <th id="number">번호</th><th id="name">제목</th><th id="writer">작성자</th><th id="date">등록일</th>
                            </tr>
                            <c:forEach var="notice" items="${noticeList}">
                            <tr id="content"><!--반복분 부분-->
                                <td class="number">${notice.id}</td>
                                <td class="name"><a href="${pageContext.request.contextPath}/inside/insideNotice?id=${notice.id}" style="text-decoration: none">${notice.title}</a></td>
                                <td class="writer">관리자</td>
                                <td class="date" style="font-size: 15px;">${notice.dateTime}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </section>
            </div>
        </body>
    
 </html>
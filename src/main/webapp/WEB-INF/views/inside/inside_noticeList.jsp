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
        <link rel="stylesheet" href="<c:url value="/css/inside/snu_noticeList.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_notice_page</title>
    </head>
        <body>
            <div id="body">
                <a href="inside_menu.jsp"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

                <section id="allWrap"><!--전체 감싸는 박스-->
                    <p id="title">공지목록</p>
                    <hr>
                    <div id="tableWrap">
                        <table>
                            <tr id="trTitle">
                                <th id="number">번호</th><th id="name">제목</th><th id="writer">작성자</th><th id="date">등록일</th>
                            </tr>
                            <tr id="content"><!--반복분 부분-->
                                <a><!--각 공지페이지로 넘어가는 부분-->
                                    <td class="number">1</td><td class="name">24자리 사용금지</td><td class="writer">관리자</td><td class="date">2023-02-11</td>
                                </a>
                            </tr>
                        </table>
                    </div>
                </section>
            </div>
        </body>
    
 </html>
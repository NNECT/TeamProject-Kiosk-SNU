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
        <link rel="stylesheet" href="<c:url value="/css/inside/snu_notice.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_notice_page</title>
    </head>
        <body>
            <div id="body">
                <a href="inside_noticeList.jsp"><img src="<c:url value="/img/beforBtn.png"/>" alt=""></a>

                <section id="allWrap"><!--전체 감싸는 박스-->
                    <p id="title">공지사항</p>
                    <hr>
                    <div id="tableWrap">
                        <table>
                            <div id="trTitle">
                                <div id="noticeTitle">23번자리 사용금지</div><div id="writer"><span>작성자:</span>관리자</div><div id="date"><span>등록일:</span>2023-03-15</div>
                            </div>
                            <div id="content"><!--반복분 부분-->
                                <p>ddddddddddddddd</p>
                            </div>
                        </table>
                    </div>
                </section>
            </div>
        </body>
    
 </html>
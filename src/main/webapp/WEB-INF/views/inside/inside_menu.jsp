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
        <link rel="stylesheet" href="<c:url value="/css/inside/inside_menu.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_start_page</title>
    </head>
        <body>
            <div id="body">
            <section id="allWrap"><!--전체 감싸는 박스-->
                    <div id="login"><!--클릭란-->
                        <p id="seatNum"><strong>${sessionScope.insideNumber}번</strong></p>
                        <div id="line">
                            <hr>
                        </div>
                            <p id="userName">${sessionScope.author.username}님</p>
                        <table>
                            <tr>
                                <td>
                                    <a href="">
                                        <div id="menuBox"><img src="<c:url value="/img/inside/menu/clock.png"/>" alt=""></div></a>
                                    <p id="menuName">연장</p>
                                </td>
                                <td>
                                    <a href="inside_logout.jsp">
                                        <div id="menuBox"><img src="<c:url value="/img/inside/menu/exit (1).png"/>" alt=""></div></a>
                                    <p id="menuName">퇴실</p>
                                </td>
                                <td>
                                    <a href="inside_select.jsp">
                                        <div id="menuBox"><img src="<c:url value="/img/inside/menu/marker.png"/>" alt=""></div></a>
                                    <p id="menuName">자리이동</p>
                                </td>
                                <td>
                                    <a href="inside_myPage.jsp">
                                        <div id="menuBox"><img src="<c:url value="/img/inside/menu/user.png"/>" alt=""></div></a>
                                    <p id="menuName">마이페이지</p>
                                </td>
                                <td>
                                    <a href="inside_noticeList.jsp">
                                        <div id="menuBox"><img src="<c:url value="/img/inside/menu/triangle-warning (1).png"/>" alt=""></div></a>
                                    <p id="menuName">공지</p> 
                                </td>
                            </tr>
                        </table>   
                    </div>
                
            </section>
        </div>
        </body>
    
 </html>
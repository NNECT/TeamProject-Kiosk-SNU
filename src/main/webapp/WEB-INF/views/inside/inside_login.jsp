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
        <link rel="stylesheet" href="<c:url value="/css/inside/inside_login.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_start_page</title>
    </head>
        <body>
            <div id="body">
            <section id="circleWrap1">
                <div id="bigCircle1"></div><!--원-->
                <div id="smallCircle1"></div><!--원-->
            </section>
            <section id="circleWrap2">
                <div id="bigCircle2"></div><!--원-->
                <div id="smallCircle2"></div><!--원-->
            </section>

            <section id="allWrap"><!--전체 감싸는 박스-->
                <form action="">
                    <div id="login"><!--클릭란-->
                        <p id="seatNum"><strong>24번</strong></p>
                        <div id="line">
                            <hr>
                        </div>
                            <p id="userName">김감자님</p>    
                        
                        <input type="password" id="password" placeholder="비밀번호를 입력하세요"><br>
                        <input type="submit" id="loginBtn" value="로그인">
                    </div>
                </form>
            </section>
        </div>
        </body>
    
 </html>
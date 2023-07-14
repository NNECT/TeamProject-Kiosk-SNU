<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="<c:url value="/css/snu_common_bg.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_commonId.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_foundId.css"/>">
    <script src="hiddenBtn.js"></script>
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_foundId_page</title>
</head>
<body>
<!--background-->
<section id="circleWrap1">
    <div id="bigCircle1"></div><!--원-->
    <div id="smallCircle1"></div><!--원-->
</section>
<section id="circleWrap2">
    <div id="bigCircle2"></div><!--원-->
    <div id="smallCircle2"></div><!--원-->
</section>
<!--background-->
<section id="whiteBox">
    <form action="">
        <div id="menu"><!--매뉴창-->
            <ul>
                <li><a href="<c:url value="/outside"/>"><img src="<c:url value="/img/start/home.png"/>" width="40px" height="40px"></a></li>
                <li><a href="<c:url value="/outside/login?type=${sessionScope.selectType}&number=${sessionScope.selectNumber}" />">로그인</a></li>
                <li><a href="<c:url value="/outside/findUsername" />">아이디찾기</a></li>
                <li><a href="<c:url value="/outside/findPassword" />">비밀번호찾기</a></li>
            </ul>
            <div id="checkBtn"><input type='button' value='다음' id='newBtn' style="display: none"></div>
        </div>
        <div id="wrap"><!--wrap-->
            <p id="title">아이디 찾기</p>

            <div><!--아이콘 변경란-->
                <img src="<c:url value="/img/snu_loding.gif"/>" width="90">
            </div>
            <div id="input"><!--입력란-->
                <p>
                    <label for="phoneNumber">전화번호를 입력해주세요</label>
                    <span id="codeSendCheck" style="float: right; color: blue; margin-right: 105px"></span>
                </p>
                <input id="phoneNumber" class="input tel" type="text" maxlength="13"
                       style="letter-spacing: 6px; text-align: center">
                <input type="button" class="btn " value="인증" onclick="codeSend()">

                <p>
                    <label for="code">인증번호를 입력해주세요</label>
                    <span id="codeCheck" style="float: right; color: blue; margin-right: 105px"></span>
                </p>
                <input id="code" class="input check" type="text" maxlength="4"
                       style="letter-spacing: 2px; text-align: center">
                <input type="button" class="btn " value="확인" onclick="codeCheck()">
            </div>
        </div>
    </form>
</section>

<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/findUsername.js"/>"></script>

<script>
    document.getElementById("newBtn").addEventListener("click", findUsernameSubmit);
    function findUsernameSubmit(){

        const form = document.createElement("form");
        form.setAttribute("method","post");
        form.setAttribute("action","<c:url value="/outside/findUsername" />");
        form.setAttribute("charset","UTF-8");
        form.setAttribute("hidden","true");

        const phoneNumberInput = document.createElement("input");
        phoneNumberInput.setAttribute("type","text");
        phoneNumberInput.setAttribute("name","phoneNumber");
        phoneNumberInput.setAttribute("value",document.getElementById("phoneNumber").value);
        form.appendChild(phoneNumberInput);

        document.body.appendChild(form);
        form.submit();
    }


</script>

</body>
</html>

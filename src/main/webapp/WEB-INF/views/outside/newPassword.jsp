<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongsuji
  Date: 2023/07/12
  Time: 12:00 PM
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
    <link rel="stylesheet" href="<c:url value="/css/snu_newPassword.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_newPassword_page</title>
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
    <div id="menu"><!--매뉴창-->
        <ul>
            <li><a href="<c:url value="/outside"/>"><img src="<c:url value="/img/start/home.png"/>" width="40px"
                                                         height="40px"></a></li>
            <li>
                <a href="<c:url value="/outside/login?type=${sessionScope.selectType}&number=${sessionScope.selectNumber}" />">로그인</a>
            </li>
            <li><a href="<c:url value="/outside/findUsername" />">아이디찾기</a></li>
            <li><a href="<c:url value="/outside/findPassword" />">비밀번호찾기</a></li>
        </ul>
        <div id="checkBtn"><input type='button' value='다음' id='newBtn' style="display: block"></div>
    </div>
    <div id="wrap"><!--wrap-->
        <p id="title">비밀번호 재설정</p>
        <form action="">
            <div id="input"><!--입력란-->
                <p>새비밀번호를 입력해주세요</p>
                <input class="input newPassword" type="password" name="">
                <p>다시 입력해주세요</p>
                <input class="input password" type="password" name="">
                <input type="button" id="checkBtn" value="비밀번호 확인">
            </div>
        </form>
        <div id="showBtn">
            <hr>
            <a href=""><input type="button" id="loginBtn" value="로그인페이지로 이동"></a>
        </div>
    </div>
</section>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="<c:url value="/js/newPassword.js"/>"></script>
<script>
    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("newBtn").addEventListener("click", newPasswordConfirm);
    });

    function newPasswordConfirm() {

        /*비밀번호 일치라면 진행*/
        var passwordCheck = document.getElementById("passwordCheck").innerHTML;
        if (passwordCheck === "비밀번호 일치") {
            const crypt = new JSEncrypt();
            crypt.setPublicKey("${publicKey}");

            /*controller에서 넘어온 author 값을 username 넘겨주기*/
            const username = "${sessionScope.findPasswordUser.username}";
            const password = crypt.encrypt(document.getElementById("password").value);

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "<c:url value="/outside/newPassword" /> ");
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("hidden", "true");

            const usernameInput = document.createElement("input");
            usernameInput.setAttribute("type", "text");
            usernameInput.setAttribute("name", "username");
            usernameInput.setAttribute("value", username);
            form.appendChild(usernameInput);

            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("name", "password");
            passwordInput.setAttribute("value", password);
            form.appendChild(passwordInput);

            document.body.appendChild(form);
            form.submit();
        }
        /*비밀번호 일치가 아니라면 무반응*/
        //다음 버튼을 누르면 비밀번호 변경 완료 이미지가 보이고 2초 정도? 뒤에 로그인 페이지로 넘어가면 좋을 듯 - 수지님과 얘기해보기 (ajax사용필요할듯?)
        //아니면 비번 변경 완료페이지 만들지?? (아이디 찾기 완료 페이지 재활용 가능할듯)


    }


</script>
</body>
</html>

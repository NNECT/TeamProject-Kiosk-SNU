<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/snu_common_bg.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_common.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/snu_newMember.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_foundPassword_page</title>
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
            <li><a><img src="<c:url value="/img/start/home.png"/>" width="40px" height="40px"></a></li>
            <li><a href="snu_foundId.html">아이디찾기</a></li>
            <li><a href="snu_foundPassword.html">비밀번호찾기</a></li>
            <li><a href="snu_login.html">로그인</a></li>
        </ul>
        <div id="checkBtn"><input type='button' value='다음' id='newBtn'></div>
    </div>
    <div id="wrap"><!--wrap-->
        <p id="title">회원가입</p>
        <div id="input"><!--입력란-->
            <p><label for="username">아이디를 입력해주세요</label><span id="usernameCheck" style="float: right; color: blue; margin-right: 105px"></span></p>
            <input id="username" class="input username" type="text">
            <input type="button"  class="btn " value="인증" onclick="usernameCheck()">
            <p><label for="phoneNumber">전화번호를 입력해주세요</label></p>
            <input id="phoneNumber" class="input phoneNumber" type="text" maxlength="13" style="letter-spacing: 6px; text-align: center">
            <input type="button"  class="btn " value="인증" onclick="codeSend()">
            <p><label for="code">인증번호를 입력해주세요</label><span id="codeCheck" style="float: right; color: blue; margin-right: 105px"></span></p>
            <input id="code" class="input check" type="text" maxlength="4" style="letter-spacing: 2px; text-align: center">
            <input type="button"  class="btn " value="확인" onclick="codeCheck()">
            <p><label for="password">비밀번호를 입력해주세요</label></p>
            <input id="password" class="input password" type="password">
            <p><label for="password_confirm">비밀번호를 다시 입력해주세요</label></p>
            <input id="password_confirm" class="input password" type="password">
        </div>
        <input type="checkbox" id="agree" onclick="checkBoxBtn()"><span id="agreeText" >개인정보 수집동의</span>
        <span id="info">상세보기</span>
    </div>
</section>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/hiddenBtn.js"/>"></script>
<script>
    document.getElementById("phoneNumber").addEventListener("input", e => {
        let x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
        e.target.value = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
    });

    function usernameCheck() {
        console.log("usernameCheck");
        $.ajax({
            type: "POST",
            url: "../ajax/username",
            data: JSON.stringify({
                "username": document.getElementById("username").value
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.msg === "true") {
                    alert("사용 가능한 아이디입니다.:" + response.msg);
                    const usernameCheck = document.getElementById("usernameCheck");
                    usernameCheck.innerHTML = "사용 가능";
                    usernameCheck.style.color = "blue";
                } else {
                    alert("이미 사용중인 아이디입니다.:" + response.msg);
                    const usernameCheck = document.getElementById("usernameCheck");
                    usernameCheck.innerHTML = "사용 불가";
                    usernameCheck.style.color = "red";
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }

    function codeSend() {
        $.ajax({
            type: "POST",
            url: "../ajax/codeSend",
            data: JSON.stringify({
                "codePurpose": "join",
                "phoneNumber": document.getElementById("phoneNumber").value
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert("인증번호가 전송되었습니다.");
                } else {
                    alert("인증번호 전송에 실패하였습니다.");
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }

    function codeCheck() {
        $.ajax({
            type: "POST",
            url: "../ajax/codeCheck",
            data: JSON.stringify({
                "codePurpose": "join",
                "code": document.getElementById("code").value
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert("인증되었습니다.");
                    const codeCheck = document.getElementById("codeCheck");
                    codeCheck.innerHTML = "인증 성공";
                    codeCheck.style.color = "blue";
                } else {
                    alert("인증번호가 일치하지 않습니다.");
                    const codeCheck = document.getElementById("codeCheck");
                    codeCheck.innerHTML = "인증 실패";
                    codeCheck.style.color = "red";
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
</script>
</body>
</html>
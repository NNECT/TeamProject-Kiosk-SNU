<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-07-12
  Time: 오전 9:20
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
    <link rel="stylesheet" href="<c:url value="/css/snu_challenge.css"/>">
    <script src="hiddenBtn.js"></script>
    <script src="alertBtn.js"></script>
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>
    <style>
        body {
            font-family: 'SUITE', sans-serif;
            box-sizing: border-box;
        }

        .border-check {
            box-shadow: 0 0 10px gray; /* 그림자 효과 추가 */
        }
    </style>

    <title>snu_challenge_page</title>
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
<form action="">
    <section id="wrap"><!---->
        <div id="infoWrap">
            <p id="snuTitle">스누와함께 <span>챌린지</span>하고 <span>포인트</span>받아가세요!</p>
            <p id="challInfo">도전하고 싶은 챌린지를 선택해주세요</p>
        </div>

        <c:forEach var="challenge" items="${list}">
            <c:choose>
                <c:when test="${challenge.active}">
                    <div class="box challenge border" STYLE="background-color: ${challenge.backgroundColor}"
                         id="${challenge.id}" onclick="checkChallenge(${challenge.id})">
                        <div>
                            <div class="iconCircle c1" id="circle"
                                 style="background-color: ${challenge.descriptionColor}">
                                <img src="<c:url value="${challenge.imageSrc}"/>" width="72">
                            </div>
                        </div>
                        <p class="cTitle" style="color: ${challenge.titleColor}">${challenge.title}</p>
                        <p class="cContent" style="color: ${challenge.titleColor}">${challenge.description}</p>
                        <p class="cPoint" style="color: ${challenge.titleColor}"><!--숫자만 변경 --><span
                                id="point">${challenge.rewardPoint}</span><!--숫자만 변경 -->point</p>
                    </div>
                </c:when>
            </c:choose>
        </c:forEach>
    </section>


    <div id="buttonWrap">
        <input type="button" value="다음에" id="nextBtn">
        <input type="button" value="시작하기" id="startBtn">
    </div>

</form>

<script>
    let selectedChallengeId = null;

    function checkChallenge(id) {
        const challenge = document.getElementById(id);

        // 클릭한 챌린지가 이미 선택된 상태인 경우 선택 해제
        if (selectedChallengeId === id) {
            selectedChallengeId = null;
            challenge.classList.remove('border-check');
        } else {
            // 이전에 선택된 챌린지가 있으면 선택 해제하고
            const prevSelectedChallenge = document.getElementById(selectedChallengeId);
            if (prevSelectedChallenge) {
                prevSelectedChallenge.classList.remove('border-check');
            }

            // 클릭한 챌린지 선택
            selectedChallengeId = id;
            challenge.classList.add('border-check');
        }
    }

</script>
</body>
</html>

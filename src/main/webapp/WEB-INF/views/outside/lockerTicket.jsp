<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/snu_lockerTicket.css"/>">
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_locker_page</title>
</head>
<body>
<div id="whiteWrap">
    <section>
        <ul>
            <li id="lockerTicket">사물함</li>
            <li id="homeLi"><a href="<c:url value="/outside/logout"/>"><img id="homeImg" src="<c:url value="/img/bluehome.png"/>"></a></li>
        </ul>
        <hr>

        <form id="lockerFrm" action="<c:url value="/outside/locker"/>" method="post" onsubmit="return selectedLockerCheck()">
            <p>사물함을 선택해주세요</p>
            <table id="locker">
                <tr>
                    <td class="locker r" id="20"></td>
                    <td class="locker r" id="19"></td>
                    <td colspan="8" class="none">
                </tr>
                <tr>

                    <td class="locker r" id="18"></td>
                    <td class="locker r" id="17"></td>
                    <td class="locker r" id="16"></td>
                    <td class="locker r" id="15"></td>
                    <td class="locker r" id="14"></td>
                    <td class="locker r" id="13"></td>
                    <td class="locker r" id="12"></td>
                    <td class="locker r" id="11"></td>
                    <td class="locker r" id="10"></td>
                </tr>
                <tr>
                    <td class="locker r" id="9" ></td>
                    <td class="locker r" id="8" ></td>
                    <td class="locker r" id="7" ></td>
                    <td class="locker r" id="6" ></td>
                    <td class="locker r" id="5" ></td>
                    <td class="locker r" id="4" ></td>
                    <td class="locker r" id="3" ></td>
                    <td class="locker r" id="2" ></td>
                    <td class="locker r" id="1" ></td>
                </tr>
            </table>


            <div id="info"><!--자리설명-->
                <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;
                <div class="box g"></div>&nbsp;<span class="gray">사용 중</span>&nbsp;
                <div class="box rBox"></div>&nbsp;<span class="red">사용 불가능</span>&nbsp;
                <div class="box m"></div>&nbsp;<span class="my">
                <c:choose>
                    <c:when test="${hasLocker}">
                        내 사물함
                    </c:when>
                    <c:otherwise>
                        선택한 사물함
                    </c:otherwise>
                </c:choose>
                </span>
            </div>
            <table id="ticket">
                <tr>
                    <c:forEach var="lockerTicket" items="${lockerTicketList}">
                        <td class="radio-box">
                            <input type="radio" name="radio-button" class="radio-input" value="${lockerTicket.id}">
                            <p class="t rText btn-text">${lockerTicket.day}일</p>
                            <p class="p rText btn-text"><fmt:formatNumber value="${lockerTicket.price}" pattern="#,##0"/>원</p>
                        </td>
                    </c:forEach>
                    <td class="radio-box">
                        <input type="radio" name="radio-button" class="radio-input" value="0">
                        <p class="t btn-text">사용X</p>
                        <p class="p btn-text"></p>
                    </td>
                </tr>
            </table>

            <input id="beforeBtn" type="button" value="이전">
            <input id="nextBtn" type="submit" value="다음">
        </form>
    </section>
</div>
<script src="<c:url value="/js/radioBox.js"/>"></script>
<script>
    window.addEventListener('DOMContentLoaded', (event) => {
        const lockers = document.querySelectorAll('.locker');
        const status = ${lockerStatusMap};
        lockers.forEach((locker, index) => {
            if(status[Number(locker.id)] === 1) {
                locker.classList.remove('r');
                locker.classList.add('b');
                <c:choose>
                <c:when test="${hasLocker}">
                locker.innerHTML = locker.id;
                </c:when>
                <c:otherwise>
                locker.innerHTML = '<label>' + locker.id + '<input type="radio" name="locker-radio" class="locker-radio-input" value="' + locker.id + '"></label>';
                </c:otherwise>
                </c:choose>
            } else if (status[Number(locker.id)] === 0) {
                locker.classList.remove('r');
                locker.classList.add('g');
                locker.innerHTML = locker.id;
            } else if (status[Number(locker.id)] === 2) {
                locker.classList.remove('r');
                locker.classList.add('m');
                locker.innerHTML = locker.id;
            } else {
                locker.innerHTML = locker.id;
            }
        });

        // 'locker' 클래스를 가진 요소 중에서 input type이 radio인 요소를 선택
        const radioLockers = Array.from(document.querySelectorAll('.locker')).filter(locker => locker.querySelector('input[type="radio"]'));
        radioLockers.forEach((box) => {
            const radioButton = box.querySelector('input[type="radio"]');
            box.addEventListener('click', (e) => {
                // 라디오 버튼을 선택
                radioButton.checked = true;

                // 모든 박스의 클래스를 'b'로 되돌림
                radioLockers.forEach((b) => {
                    if (b.querySelector('input[type="radio"]').checked === false) {
                        b.classList.remove('m');
                        b.classList.add('b');
                    }
                });

                // 클릭한 박스만 'm' 클래스를 추가
                if (radioButton.checked === true) {
                    box.classList.remove('b');
                    box.classList.add('m');
                }
            });
        });
    });

    function selectedLockerCheck() {
        // 사용권 버튼이 선택되었는지 확인
        let chk = false;

        // 각각의 사용권 버튼을 확인
        for (const elm of document.getElementsByName("radio-button")) {
            if (elm.checked) {
                // 선택된 사용권 버튼의 값이 0이면 사용X를 선택한 것이므로 즉시 true를 반환
                if (elm.value === "0") return true;

                // 이외의 버튼은 자리 선택 여부를 확인해야 하므로 chk를 true로 변경
                chk = true;
                break;
            }
        }

        // 사용권이 선택되지 않았으면 false를 반환
        if (!chk) return false;

        // 자리 존재 여부 확인
        const lockerRadios = document.getElementsByClassName("locker-radio-input");
        if (lockerRadios.length > 0) {
            for (const elm of lockerRadios) {
                // 자리가 선택되었으면 true를 반환
                if (elm.checked) return true;
            }
            return false;
        }

        // 자리가 선택되지 않았을 때,
        <c:choose>
        <c:when test="${hasLocker}">
        return true; // 이미 사물함을 사용 중이므로 true를 반환
        </c:when>
        <c:otherwise>
        return false; // 자리가 선택되지 않았으므로 false를 반환
        </c:otherwise>
        </c:choose>
    }
</script>
</body>
</html>




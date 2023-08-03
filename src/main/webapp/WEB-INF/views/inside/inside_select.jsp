<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_sit.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/inside/seatModal.css"/>">

    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

    <style>
        body {font-family: 'SUITE', sans-serif;}
    </style>
    <title>snu_insideSeat_page</title>
</head>
<body>
<div id="body">
    <a href="<c:url value="/inside/menu"/>"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

    <p id="title" style="border: none;">좌석을 선택해주세요</p>
    <div id="seatWrap">
        <div id="info"><!--자리설명-->
            <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
            <div class="box g"></div>&nbsp;<span class="gray">사용중</span>
            <div class="box r"></div>&nbsp;<span class="red">사용 불가능</span>
            <div class="box m"></div>&nbsp;<span class="my">내자리</span>
        </div>
        <section>
            <form>
                <c:import url="../common/select.jsp"/>
            </form>
            <div id="door"><!--출입문-->
                <p>출입문</p>
            </div>
        </section>
        <div id="locker"><!--사물함-->
            <p>사물함</p>
        </div>
    </div>
    <!-- 이동질문 모달영역 -->
    <div id="modalbg">
        <div id="seatModal">
            <div id="modalContent">
                <p id="modalP">
                    <strong id="modalStrong"></strong>로<br>
                    이동하시겠습니까?
                </p>
            </div>
            <div id="modalBtn">
                <input type="button" id="modalNoBtn" value="취소">
                <input type="button" id="modalYesBtn" value="이동">
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");

    const modalBg = document.getElementById('modalbg');
    const seatModal = document.getElementById('seatModal');
    const modalNoBtn = document.getElementById('modalNoBtn')
    const modalYesBtn = document.getElementById('modalYesBtn')
    const modalStrong = document.getElementById("modalStrong")

    let number = 0;

    window.addEventListener('DOMContentLoaded', (event) => {
        const seats = document.querySelectorAll('.seat');
        const status = ${seatStatusMap};


        seats.forEach((seat, index) => {
            if(status[Number(seat.id)] === 1) {

                //번호 클릭하면 modal창 나옴
                seat.addEventListener('click',() => {
                    modalStrong.textContent = seat.id+"번 자리";
                    modalBg.style.display = 'block';
                    seatModal.style.display = 'block';
                    number = seat.id;
                });

                seat.classList.remove('r');
                seat.classList.add('b');
                //seat.id 좌석번호를 get으로 보냄
                seat.innerHTML = seat.id;
            } else if (status[Number(seat.id)] === 0) {
                seat.classList.remove('r');
                seat.classList.add('g');
                seat.innerHTML = seat.id;
            } else if(status[Number(seat.id)]===2){
                //내자리인경우
                seat.classList.remove('r');
                seat.classList.add('m');
                seat.innerHTML = seat.id;
            } else {
                seat.innerHTML = seat.id;
            }
        });

        //모달창에서 취소 누르면 다시 뒤로
        modalNoBtn.addEventListener('click', () => {
            modalBg.style.display = 'none';
            seatModal.style.display = 'none';
        });

        //이동 누르면 다음으로
        modalYesBtn.addEventListener('click', modalYesFunction);
    });

    function modalYesFunction(e) {
        modalYesProcess(number);
    }

    function modalYesProcess(n) {
        location.href = "<c:url value="/inside/move"/>?type=seat&number=" + n;
    }
</script>
</body>
</html>

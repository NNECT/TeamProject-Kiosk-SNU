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

    <p id="title">좌석을 선택해주세요</p>
    <div id="seatWrap">
        <div id="info"><!--자리설명-->
            <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
            <div class="box g"></div>&nbsp;<span class="gray">사용중</span>
            <div class="box r"></div>&nbsp;<span class="red">사용 불가능</span>
            <div class="box m"></div>&nbsp;<span class="my">내자리</span>
        </div>
        <section>
            <form>
                <table>
                    <tr>
                        <td class="seat r" id="1"></td>
                        <td class="seat r" id="14"></td>
                        <td></td>
                        <td></td>
                        <td class="seat r" id="15"></td>
                        <td class="seat r" id="16"></td>
                        <td class="seat r" id="17"></td>
                        <td class="seat r" id="18"></td>
                        <td class="seat r" id="19"></td>
                        <td class="seat r" id="20"></td>
                        <td class="seat r" id="21"></td>
                        <td class="seat r" id="22"></td>
                        <td class="seat r" id="23"></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="2"></td>
                        <td class="seat r" id="13"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="3"></td>
                        <td class="seat r" id="12"></td>
                        <td></td>
                        <td></td>
                        <td class="seat r" id="32"></td>
                        <td class="seat r" id="31"></td>
                        <td class="seat r" id="30"></td>
                        <td class="seat r" id="29"></td>
                        <td class="seat r" id="28"></td>
                        <td class="seat r" id="27"></td>
                        <td class="seat r" id="26"></td>
                        <td class="seat r" id="25"></td>
                        <td class="seat r" id="24"></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="4"></td>
                        <td class="seat r" id="11"></td>
                        <td></td>
                        <td></td>
                        <td class="seat r" id="33"></td>
                        <td class="seat r" id="34"></td>
                        <td class="seat r" id="35"></td>
                        <td class="seat r" id="36"></td>
                        <td class="seat r" id="37"></td>
                        <td class="seat r" id="38"></td>
                        <td class="seat r" id="39"></td>
                        <td class="seat r" id="40"></td>
                        <td class="seat r" id="41"></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="5"></td>
                        <td class="seat r" id="10"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="6"></td>
                        <td class="seat r" id="9"></td>
                        <td></td><td></td><td></td><td></td><td></td>
                        <td colspan="3" rowspan="3" class="room r" id="r1"><a href="snu_login.html">room1</a></td>
                        <td colspan="3" rowspan="3" class="room r" id="r2"><a href="snu_login.html">room2</a></td>
                    </tr>
                    <tr>
                        <td class="seat r" id="7"></td>
                        <td class="seat r" id="8"></td>
                        <td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td colspan="2"></td><td></td><td></td>
                    </tr>
                </table>
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
    <script>
        window.addEventListener('DOMContentLoaded', (event) => {
            const seats = document.querySelectorAll('.seat');
            const status = ${seatStatusMap};
            seats.forEach((seat, index) => {
                if(status[Number(seat.id)] === 1) {
                    var modalBg = document.getElementById('modalbg');
                    var seatModal = document.getElementById('seatModal');
                    var modalNoBtn = document.getElementById('modalNoBtn')
                    var modalYesBtn = document.getElementById('modalYesBtn')
                    var modalStrong = document.getElementById("modalStrong")

                    //번호 클릭하면 modal창 나옴
                    seat.addEventListener('click',() => {
                        modalStrong.textContent = seat.id+"번 자리";
                        modalBg.style.display = 'block';
                        seatModal.style.display = 'block';
                    });

                    //모달창에서 취소 누르면 다시 뒤로
                    modalNoBtn.addEventListener('click', () => {
                        modalBg.style.display = 'none';
                        seatModal.style.display = 'none';
                    });

                    //이동 누르면 다음으로
                    modalYesBtn.addEventListener('click', () => {
                        // modalBg.style.display = 'none';
                        // seatModal.style.display = 'none';
                        location.href = "<c:url value="/inside/move"/>?type=seat&number=" + seat.id;
                    });

                    seat.classList.remove('r');
                    seat.classList.add('b');
                    //seat.id 좌석번호를 get으로 보냄
                    seat.innerHTML = seat.id;
                } else if (status[Number(seat.id)] === 0) {
                    seat.classList.remove('r');
                    seat.classList.add('g');
                    seat.innerHTML = seat.id;
                } else {
                    seat.innerHTML = seat.id;
                }
            });
       });

        //자리이동 confirm 모달창

    </script>

</div>
</body>

</html>

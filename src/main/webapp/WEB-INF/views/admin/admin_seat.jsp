<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%--헤더--%>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>
<%--admin_seat CSS--%>
<link rel="stylesheet" href="<c:url value="/css/admin/admin_seat.css"/>">
<%--폰트--%>
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
<style>
    /* 스타일링을 위한 CSS 코드 */
    .modal {
        display: block;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.5);
    }

    .modal-content {
        background-color: white;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 400px;
        position: relative;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        position: absolute;
        top: 0;
        right: 0;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;

    }
</style>
<!-- 메인 컨텐츠 -->
<div id="seatWrap">
    <div id="info"><!--자리설명-->
        <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
        <div class="box g"></div>&nbsp;<span class="gray">사용중</span>
        <div class="box r"></div>&nbsp;<span class="red">사용 불가능</span>
    </div>
    <section>
        <form id="adminSeatForm">
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
<%--모달창--%>
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>회원 정보</h2>
        <p>아이디: [아이디 정보]</p>
        <p>입실시간: [입실시간 정보]</p>
        <h2>현재 실시간 사용상태</h2>
        <p>[사용 가능]</p>
        <button onclick="activateSeat()">좌석 활성화</button>
        <button onclick="deactivateSeat()">좌석 비활성화</button>
        <button onclick="endUsage()">사용 종료</button>
    </div>
</div>


<script>
    /*관리자페이지는 좌석상태에 상관없이 모두 클릭 가능*/
    window.addEventListener('DOMContentLoaded', (event) => {
        const seats = document.querySelectorAll('.seat');
        const seatStatus = ${seatStatusMap};
        seats.forEach((seat, index) => {
            if (seatStatus[Number(seat.id)] === 1) {
                seat.classList.remove('r');
                seat.classList.add('b');
                seat.innerHTML = "<a href='<c:url value=""/>?type=seat&number=" + seat.id + "'>" + seat.id + "</a>"
            } else if (seatStatus[Number(seat.id)] === 0) {
                seat.classList.remove('r');
                seat.classList.add('g');
                seat.innerHTML = "<a href='<c:url value=""/>?type=seat&number=" + seat.id + "'>" + seat.id + "</a>"
            } else {
                seat.innerHTML = "<a href='<c:url value=""/>?type=seat&number=" + seat.id + "'>" + seat.id + "</a>"
            }
        });

        const rooms = document.querySelectorAll('.room');
        const roomStatus = ${roomStatusMap};
        rooms.forEach((room, index) => {
            const roomId = Number(room.id.substring(1));
            if (roomStatus[roomId] === 1) {
                room.classList.remove('r');
                room.classList.add('b');
                room.innerHTML = "<a href='<c:url value=""/>?type=room&number=" + roomId + "'>" + "R"+roomId + "</a>"
            } else if (roomStatus[roomId] === 0) {
                room.classList.remove('r');
                room.classList.add('g');
                room.innerHTML = "<a href='<c:url value=""/>?type=room&number=" + roomId + "'>" + "R"+roomId + "</a>"
            } else {
                room.innerHTML = "<a href='<c:url value=""/>?type=room&number=" + roomId + "'>" + "R"+roomId + "</a>"
            }
        });
    });
/*모달창*/
    var modal = document.getElementById('myModal');

    function openModal() {
        modal.style.display = 'block';
    }

    function closeModal() {
        modal.style.display = 'none';
    }

    function activateSeat() {
        // 사용자에게 한 번 더 확인하는 confirm 창 띄우기
        if (confirm('좌석을 정말로 활성화하시겠습니까?')) {
            // 사용자가 확인을 눌렀을 경우, 좌석 활성화 기능 구현
            alert('좌석이 활성화되었습니다.');
        }
    }

    function deactivateSeat() {
        // 사용자에게 한 번 더 확인하는 confirm 창 띄우기
        if (confirm('좌석을 정말로 비활성화하시겠습니까?')) {
            // 사용자가 확인을 눌렀을 경우, 좌석 비활성화 기능 구현
            alert('좌석이 비활성화되었습니다.');
        }
    }

    function endUsage() {
        // 사용자에게 한 번 더 확인하는 confirm 창 띄우기
        if (confirm('사용을 종료하시겠습니까?')) {
            // 사용자가 확인을 눌렀을 경우, 사용 종료 기능 구현
            alert('사용이 종료되었습니다.');
        }
    }

</script>
<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>

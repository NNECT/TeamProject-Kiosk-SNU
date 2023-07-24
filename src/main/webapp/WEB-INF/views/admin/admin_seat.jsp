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
</script>
<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>

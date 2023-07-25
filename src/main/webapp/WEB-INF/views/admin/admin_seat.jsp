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
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
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
        <h2 id="userStatus">회원 정보</h2>
        <p id="username">${accountID} </p>
        <p id="startDateTime">입실시간: ${startDateTime}</p>
        <h2 id="seatStatus">현재 실시간 사용상태</h2>
        <p id="usable">${usable}</p>
        <button id="activateSeat" onclick="activateSeat()">자리 활성화</button>
        <button id="deactivateSeat" onclick="deactivateSeat()">자리 비활성화</button>
        <button id="endUsage" onclick="endUsage()">사용 종료</button>
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
                seat.innerHTML = "<a href='<c:url value="/admin/adminseat" />?type=seat&number=" + seat.id + "'>" + seat.id + "</a>";
            } else if (seatStatus[Number(seat.id)] === 0) {
                seat.classList.remove('r');
                seat.classList.add('g');
                seat.innerHTML = "<a href='<c:url value="/admin/adminseat" />?type=seat&number=" + seat.id + "'>" + seat.id + "</a>";
            } else {
                seat.innerHTML = "<a href='<c:url value="/admin/adminseat" />?type=seat&number=" + seat.id + "'>" + seat.id + "</a>";
            }
        });

        const rooms = document.querySelectorAll('.room');
        const roomStatus = ${roomStatusMap};
        rooms.forEach((room, index) => {
            const roomId = Number(room.id.substring(1));
            if (roomStatus[roomId] === 1) {
                room.classList.remove('r');
                room.classList.add('b');
                room.innerHTML = "<a href='<c:url value="/admin/adminseat"/>?type=room&number=" + roomId + "'>" + "R" + roomId + "</a>";
            } else if (roomStatus[roomId] === 0) {
                room.classList.remove('r');
                room.classList.add('g');
                room.innerHTML = "<a href='<c:url value="/admin/adminseat"/>?type=room&number=" + roomId + "'>" + "R" + roomId + "</a>";
            } else {
                room.innerHTML = "<a href='<c:url value="/admin/adminseat"/>?type=room&number=" + roomId + "'>" + "R" + roomId + "</a>";
            }
        });
    });
    /*모달창*/
    var modal = document.getElementById('myModal');

    <c:if test="${usable eq '사용중'}">
    modal.style.display = 'block';
    document.getElementById('deactivateSeat').style.display = 'none';
    document.getElementById('activateSeat').style.display = 'none';
    </c:if>

    // 로그인중인 고객 있는 경우 설정창 숨겨주기
    <c:if test="${accountID eq '현재 로그인중인 고객이 있습니다. 로그인이 완료되면 설정해주세요.'}">
    modal.style.display = 'block';
    document.getElementById('userStatus').style.display = 'none';
    document.getElementById('startDateTime').style.display = 'none';
    document.getElementById('usable').style.display = 'none';
    document.getElementById('seatStatus').style.display = 'none';
    const buttons = document.querySelectorAll('.modal-content button');
    buttons.forEach(button => {
        button.style.display = 'none';
    });
    </c:if>

    <c:if test="${usable eq '사용불가능'}">
    modal.style.display = 'block';
    document.getElementById('userStatus').style.display = 'none';
    document.getElementById('startDateTime').style.display = 'none';
    document.getElementById('deactivateSeat').style.display = 'none';
    document.getElementById('endUsage').style.display = 'none';
    </c:if>
    <c:if test="${usable eq '사용가능'}">
    modal.style.display = 'block';
    document.getElementById('userStatus').style.display = 'none';
    document.getElementById('startDateTime').style.display = 'none';
    document.getElementById('activateSeat').style.display = 'none';
    document.getElementById('endUsage').style.display = 'none';
    </c:if>

    function closeModal() {
        modal.style.display = 'none';
    }


    <c:if test="${not empty number}">

    function activateSeat() {
        if (confirm('자리를 활성화 시키시겠습니까?')) {
            <c:if test="${type eq 'seat'}">
            seatActivateControl();
            </c:if>
            <c:if test="${type eq 'room'}">
            roomActivateControl();
            </c:if>
        }
    }

    function seatActivateControl() {

        $.ajax({
            type: "POST",
            url: "/ajax/seatActivation",
            data: JSON.stringify({
                "codePurpose": "seatActivate",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('자리가 활성화되었습니다.');
                    modal.style.display = 'none';

                    const seatId = ${number};
                    const seat = document.getElementById(seatId);
                    seat.classList.remove('r');
                    seat.classList.add('b');

                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });

    }
    function roomActivateControl() {

        $.ajax({
            type: "POST",
            url: "/ajax/roomActivation",
            data: JSON.stringify({
                "codePurpose": "roomActivate",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('자리가 활성화되었습니다.');
                    modal.style.display = 'none';

                    const roomId = ${number};
                    const room = document.getElementById("r"+roomId);
                    room.classList.remove('r');
                    room.classList.add('b');

                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });

    }

    function deactivateSeat() {
        if (confirm('자리를 비활성화 시키시겠습니까?')) {
            <c:if test="${type eq 'seat'}">
            seatDeactivateControl();
            </c:if>
            <c:if test="${type eq 'room'}">
            roomDeactivateControl();
            </c:if>
        }

    }

    function seatDeactivateControl() {
        $.ajax({
            type: "POST",
            url: "/ajax/seatActivation",
            data: JSON.stringify({
                "codePurpose": "seatDeactivate",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('자리가 비활성화되었습니다.');
                    modal.style.display = 'none';

                    const seatId = ${number};
                    const seat = document.getElementById(seatId);
                    seat.classList.remove('b');
                    seat.classList.add('r');

                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });

    }

    function roomDeactivateControl() {
        $.ajax({
            type: "POST",
            url: "/ajax/roomActivation",
            data: JSON.stringify({
                "codePurpose": "roomDeactivate",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('자리가 비활성화되었습니다.');
                    modal.style.display = 'none';

                    const roomId = ${number};
                    const room = document.getElementById("r"+roomId);
                    room.classList.remove('b');
                    room.classList.add('r');

                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });

    }

    function endUsage() {
        if (confirm('자리사용을 종료시키시겠습니까?')) {
            <c:if test="${type eq 'seat'}">
            seatEndUsageControl();
            </c:if>
            <c:if test="${type eq 'room'}">
            roomEndUsageControl();
            </c:if>
        }
    }

    function seatEndUsageControl() {
        $.ajax({
            type: "POST",
            url: "/ajax/seatActivation",
            data: JSON.stringify({
                "codePurpose": "seatEndUsage",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('사용이 종료되었습니다. 해당 자리는 사용 불가로 변경됩니다.');
                    modal.style.display = 'none';

                    const seatId = ${number};
                    const seat = document.getElementById(seatId);
                    seat.classList.remove('g');
                    seat.classList.add('r');

                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }

    function roomEndUsageControl() {
        $.ajax({
            type: "POST",
            url: "/ajax/roomActivation",
            data: JSON.stringify({
                "codePurpose": "roomEndUsage",
                "number": ${number},
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.result === "success") {
                    alert('사용이 종료되었습니다. 해당 자리는 사용 불가로 변경됩니다.');
                    modal.style.display = 'none';

                    const roomId = ${number};
                    const room = document.getElementById("r"+roomId);
                    room.classList.remove('g');
                    room.classList.add('r');
                }
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
    </c:if>


</script>
<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>

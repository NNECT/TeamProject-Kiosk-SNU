<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp" />

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">요금설정</h2>
                    <hr>
                    <form id="sudo-form">
                        <div class="row">
                            <div class="col-sm-6">
                                <h4 class="mt-4">사용권 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <c:forEach items="${timeTicket}" var="timeTicket">
                                        <div class="form-group row">
                                            <label for="hourlyPrice${timeTicket.id}" class="col-sm-6 col-form-label">${timeTicket.time}시간 사용권:</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <input type="text" class="form-control text-right hourlyPrice" id="hourlyPrice${timeTicket.id}" name="${timeTicket.id}" value="${timeTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">원</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <h4 class="mt-4">정기권 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <c:forEach items="${commutationTicket}" var="commutationTicket">
                                        <div class="form-group row">
                                            <label for="monthlyPrice${commutationTicket.id}" class="col-sm-6 col-form-label">정기권 ${commutationTicket.day}일:</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <input type="text" class="form-control text-right monthlyPrice" id="monthlyPrice${commutationTicket.id}" name="${commutationTicket.id}" value="${commutationTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">원</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <h4 class="mt-4">사물함 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <c:forEach items="${lockerTicket}" var="lockerTicket">
                                        <div class="form-group row">
                                            <label for="lockerPrice${lockerTicket.id}" class="col-sm-6 col-form-label">${lockerTicket.day}일 사물함 요금:</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <input type="text" class="form-control text-right lockerPrice" id="lockerPrice${lockerTicket.id}" name="${lockerTicket.id}" value="${lockerTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">원</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <h4 class="mt-4">룸 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <c:forEach items="${roomTicket}" var="roomTicket">
                                        <div class="form-group row">
                                            <label for="roomPrice${roomTicket.id}" class="col-sm-6 col-form-label">1시간 룸 요금:</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <input type="text" class="form-control text-right roomPrice" id="roomPrice${roomTicket.id}" name="${roomTicket.id}" value="${roomTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">원</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">변경</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>

    // 수정이 완료되었을 때 알림 창 띄우기
    function showNotification() {
        alert("수정이 완료되었습니다");
    }

    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("sudo-form").addEventListener("submit", function (e) {
            e.preventDefault();

            let timeTicket = document.querySelectorAll(".hourlyPrice");
            let commutationTicket = document.querySelectorAll(".monthlyPrice");
            let lockerTicket = document.querySelectorAll(".lockerPrice");
            let roomType = document.querySelectorAll(".roomPrice");

            let timeTicketJson = {};
            let commutationTicketJson = {};
            let lockerTicketJson = {};
            let roomTypeJson = {};

            timeTicket.forEach((item) => {
                timeTicketJson[item.name] = item.value;
            });
            commutationTicket.forEach((item) => {
                commutationTicketJson[item.name] = item.value;
            });
            lockerTicket.forEach((item) => {
                lockerTicketJson[item.name] = item.value;
            });
            roomType.forEach((item) => {
                roomTypeJson[item.name] = item.value;
            });

            const data = {
                timeTicketMap: timeTicketJson,
                commutationTicketMap: commutationTicketJson,
                lockerTicketMap: lockerTicketJson,
                roomTypeMap: roomTypeJson
            };

            fetch('<c:url value="/admin/adminpaysetting" />', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            }).then(response => response.json()).then(data => {
                showNotification();
                window.location.reload();
            }).catch((error) => {
                console.error('Error:', error);
            });
        });
    });
</script>
</body>
</html>

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
                    <form action="${pageContext.request.contextPath}/admin/adminpaysetting" method="post">
                        <div class="row">
                            <div class="col-sm-6">
                                <h4 class="mt-4">사용권 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <c:forEach items="${timeTicket}" var="timeTicket">
                                    <div class="form-group row">
                                        <label for="hourlyPrice" class="col-sm-6 col-form-label">${timeTicket.time}시간 사용권:</label>
                                        <input type="hidden" name="timeTicketTime" value="${timeTicket.time}">
                                        <input type="hidden" name="timeTicketId" value="${timeTicket.id}">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice" name="hourlyPrice" value="${timeTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
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
                                        <label for="monthlyPrice" class="col-sm-6 col-form-label">정기권 ${commutationTicket.day}일:</label>
                                        <input type="hidden" name="commutationTicketDay" value="${commutationTicket.day}">
                                        <input type="hidden" name="commutationTicketId" value="${commutationTicket.id}">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="monthlyPrice" name="monthlyPrice" value="${commutationTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
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
                                        <label for="lockerPrice" class="col-sm-6 col-form-label">${lockerTicket.day}일 사물함 요금:</label>
                                        <input type="hidden" name="lockerTicketDay" value="${lockerTicket.day}">
                                        <input type="hidden" name="lockerTicketId" value="${lockerTicket.id}">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="lockerPrice" name="lockerPrice" value="${lockerTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
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
                                        <label for="roomPrice" class="col-sm-6 col-form-label">1시간 룸 요금:</label>
                                        <input type="hidden" name="roomTicketId" value="${roomTicket.id}">
                                        <input type="hidden" name="roomTicketName" value="${roomTicket.name}">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="roomPrice" name="roomPrice" value="${roomTicket.price}" placeholder="요금을 입력하세요" maxlength="20">
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

<!-- 메뉴 클릭 시 해당 페이지로 이동 -->
</body>
</html>

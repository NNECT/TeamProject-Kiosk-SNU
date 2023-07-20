<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">회원정보 수정</h2>
                    <hr>
                    <form id ="form">
                        <table class="table">
                            <tr>
                                <th>회원번호:</th>
                                <td><input type="text" name ="id" value="${member.id}" readonly></td>
                            </tr>
                            <tr>
                                <th>아이디:</th>
                                <td><input type="text" name ="username" value="${member.username}" readonly></td>
                            </tr>
                            <tr>
                                <th>비밀번호:</th>
                                <td><input type="password" name="password" value="${member.password}"></td>
                            </tr>
                            <tr>
                                <th>전화번호:</th>
                                <td><input type="text" name ="phoneNumber" value="${member.phoneNumber}"></td>
                            </tr>
                            <tr>
                                <th>사용권:</th>
                                <td><input type="text" name="usageTicket" value="${memberSubscription}" readonly></td>
                            </tr>
                            <tr>
                                <th>남은 시간:</th>
                                <td><input type="text" name="usageTicket" value="${member.remainTime}" readonly></td>
                            </tr>
                            <tr>
                                <th>사물함:</th>
                                <td><input type="text" name="usageLocker" value="${memberLockerStatus}" readonly></td>
                            </tr>
                            <tr>
                                <th>참여중인 챌린지:</th>
                                <td><input type="text" name="participationChallenge" value="${memberChallengeProgress}" readonly></td>
                            </tr>
                            <tr>
                                <th>포인트:</th>
                                <td><input type="text" name="point" value="${member.point}" readonly></td>
                            </tr>
                        </table>
                        <button id="edit">수정</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value="/js/updateMember.js"/>"></script>
</body>
</html>

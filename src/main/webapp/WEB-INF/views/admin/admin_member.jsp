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
                    <h2 class="card-title">회원목록</h2>
                    <div class="text-right">
                        <input type="text" placeholder="아이디 검색하기" id="username" name="id">
                    </div>
                    <hr>
                    <table class="table">
                        <thead>
                        <tr style="background-color: lightblue;">
                            <th>전화번호</th>
                            <th>아이디</th>
                            <th>등록일</th>
                        </tr>
                        </thead>
                        <tr id="list" onclick="showMemberDetails()">
                            <td>010-1234-5678</td>
                            <td>abc123</td>
                            <td>2023-07-01</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 모달 창 -->
<div id="memberModal" class="modal" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">회원 정보</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table">
                    <tr>
                        <th>회원 이름:</th>
                        <td id="memberName"></td>
                    </tr>
                    <tr>
                        <th>아이디:</th>
                        <td id="memberId"></td>
                    </tr>
                    <tr>
                        <th>전화번호:</th>
                        <td id="memberPhone"></td>
                    </tr>
                    <tr>
                        <th>정기권:</th>
                        <td id="memberSubscription"></td>
                    </tr>
                    <tr>
                        <th>사물함 사용여부:</th>
                        <td id="memberLockerStatus"></td>
                    </tr>
                    <tr>
                        <th>챌린지 참여내역:</th>
                        <td id="memberChallengeProgress"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>

    function showMemberDetails() {
        // 선택된 회원의 정보
        var member = {
            name: "John Doe",       //이 부분도 DB에서 값을 가져와서 name : $.{name} 이런식으로 처리 가능
            id: "john123",
            phone: "010-1234-5678",
            subscription: "정기권",
            lockerStatus: "이용 중",
            challengeProgress: "작심삼일을 넘어라"
        };

        // 모달 창에 회원 정보를 채웁니다.
        $("#memberName").text(member.name);
        $("#memberId").text(member.id);
        $("#memberPhone").text(member.phone);
        $("#memberSubscription").text(member.subscription);
        $("#memberLockerStatus").text(member.lockerStatus);
        $("#memberChallengeProgress").text(member.challengeProgress);

        // 모달 창을 띄웁니다.
        $("#memberModal").modal("show");
    }
</script>
</body>
</html>

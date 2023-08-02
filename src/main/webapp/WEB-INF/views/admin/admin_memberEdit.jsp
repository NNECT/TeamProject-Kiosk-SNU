<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 90vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">회원정보 수정</h2>
                    <hr>
                    <form id ="form" action="${pageContext.request.contextPath}/admin/adminmemberedit?id=${member.id}" method="post">
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
                                <td><button type="submit" name="password" id="passwordReset">비밀번호 초기화</button></td>
                                <input type="hidden" name="password" value="${member.password}">
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
                                <td><input type="text" name="usageTicket" value="${member.remainTime}"></td>
                            </tr>
                            <tr>
                                <th>포인트:</th>
                                <td><input type="text" name="point" value="${member.point}"></td>
                            </tr>
                        </table>
                        <button id="edit" type="submit">수정</button>
                    </form>
                    <a href="<c:url value="/admin/adminmember"/>" style="float: right">돌아가기</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>

    // 수정 버튼을 눌렀을 때 알림 창 띄우기
    $('#edit').on("click", function (e) {
        const confirmed = confirm("수정하시겠습니까?");
        if (confirmed){
            alert("수정되었습니다.");
        }else{
            e.preventDefault();
        }
    });

    // "비밀번호 리셋" 버튼 클릭 이벤트
    $('#passwordReset').on("click", function (e) {
        const confirmed = confirm("비밀번호를 초기화하시겠습니까?");
        if (confirmed){
            location.href = "<c:url value='/admin/memberPasswordReset'/>?id=${member.id}";
            alert("비밀번호가 초기화되었습니다.");
        }else{
            e.preventDefault();
        }
    });

</script>
</body>
</html>

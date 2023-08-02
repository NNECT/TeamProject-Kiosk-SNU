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
                    <h2 class="card-title">스터디카페 관리자 페이지</h2>
                    <hr>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="<c:url value='/admin/adminseat' />"><span class="h3">좌석 관리</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/adminmember' />"><span class="h3">회원 관리</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/adminlocker' />"><span class="h3">사물함 관리</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/adminpaysetting' />"><span class="h3">요금 설정</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/adminnotification' />"><span class="h3">공지기록</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/challenge/list' />"><span class="h3">챌린지</span></a></li>
                        <li class="list-group-item"><a href="<c:url value='/admin/sales' />"><span class="h3">매출현황</span></a></li>
                    </ul>
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
    /*마이페이지에서 비밀번호 변경 성공 이후 경우*/
    var changePasswordSuccess = "${changePasswordSuccess}";
    if(changePasswordSuccess === "changePasswordSuccess"){
        //비밀번호 변경 완료 alert
        alert("비밀번호 변경이 완료되었습니다.")
    }
</script>
</body>
</html>

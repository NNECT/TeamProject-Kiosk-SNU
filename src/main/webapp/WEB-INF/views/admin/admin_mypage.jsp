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
                    <h2 class="card-title">관리자 페이지 비밀번호 변경</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}/admin" method="POST">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="username" class="text-center">현재 비밀번호</label>
                                <input type="text" id="username" name="username" required autofocus>
                            </li>
                            <li class="list-group-item">
                                <label for="nowPassword">변경 비밀번호</label>
                                <input type="password" id="nowPassword" name="password" required>
                            </li>
                            <li class="list-group-item">
                                <label for="changePassword">변경 비밀번호</label>
                                <input type="password" id="changePassword" name="password" required>
                            </li>
                            <li class="list-group-item">
                                <button type="submit" class="btn btn-primary">변경하기</button>
                            </li>
                        </ul>
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

</body>
</html>

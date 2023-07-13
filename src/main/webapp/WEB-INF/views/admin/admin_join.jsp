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
                    <h2 class="card-title">회원가입</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}" method="POST">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="username">아이디</label>
                                <input type="text" id="username" name="username" required autofocus>
                            </li>
                            <li class="list-group-item">
                                <label for="password1">비밀번호</label>
                                <input type="password" id="password1" name="password" required>
                            </li>
                            <li class="list-group-item">
                                <label for="password2">비밀번호 확인</label>
                                <input type="password" id="password2" name="password" required>
                            </li>
                            <li class="list-group-item">
                                <button type="submit" class="btn btn-primary">회원가입</button>
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

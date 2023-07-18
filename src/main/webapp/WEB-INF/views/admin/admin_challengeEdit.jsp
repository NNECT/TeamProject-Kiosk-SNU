<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>스터디 카페 관리자 페이지</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="bg-light">
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp" />

<!-- 메인 컨텐츠 -->
<div class="container mt-4">
    <div class="row">
        <div class="col-md-4">
            <form>
                <div class="form-group">
                    <label for="title">챌린지명:</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="startDate">시작 날짜:</label>
                    <input type="text" class="form-control" id="startDate" name="startDate" required>
                </div>
                <div class="form-group">
                    <label for="endDate">종료 날짜:</label>
                    <input type="text" class="form-control" id="endDate" name="endDate" required>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <form>
                <div class="form-group">
                    <label for="description">챌린지 설명:</label>
                    <textarea class="form-control" id="description" name="description" required></textarea>
                </div>
                <div class="form-group">
                    <label for="reward">성공시 리워드:</label>
                    <input type="text" class="form-control" id="reward" name="reward" required>
                </div>
                <div class="form-group form-check">
                    <input class="form-check-input" type="checkbox" id="current" name="current">
                    <label class="form-check-label" for="current">현재의 챌린지로 등록</label>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <!-- 추가 챌린지 항목을 위한 + 버튼 -->
            <button type="button" class="btn btn-primary mt-3">+</button>
            <!-- 챌린지 수정 버튼 -->
            <button type="submit" class="btn btn-primary mt-3">수정</button>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- DatePicker 스크립트 -->
<script>
    $(document).ready(function() {
        $("#startDate").datepicker();
        $("#endDate").datepicker();
    });
</script>
</body>
</html>

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
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">챌린지 추가</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}/admin/challenge/edit" method="POST">
                        <input type="text" value="${id}" name="id" hidden/>
                        <div class="form-group">
                            <label for="title">챌린지명 :</label>
                            <input type="text" class="form-control" id="title" name="title" value="${challenge.title}" required>
                        </div>
                        <div class="form-group">
                            <label for="description">챌린지 설명:</label>
                            <textarea class="form-control" id="description" name="description" required>${challenge.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="titleColor">제목 색상 :</label>
                            <input class="form-control" id="titleColor" name="titleColor" value="${challenge.titleColor}" required>
                        </div>
                        <div class="form-group">
                            <label for="descriptionColor">설명 색상 :</label>
                            <input class="form-control" id="descriptionColor" name="descriptionColor" value="${challenge.descriptionColor}" required>
                        </div>
                        <div class="form-group">
                            <label for="backgroundColor">배경 색상 :</label>
                            <input class="form-control" id="backgroundColor" name="backgroundColor" value="${challenge.backgroundColor}" required>
                        </div>
                        <div class="form-group">
                            <label for="imageSrc">이미지 주소 :</label>
                            <input class="form-control" id="imageSrc" name="imageSrc" value="${challenge.imageSrc}" required>
                        </div>
                        <div class="form-group">
                            <label for="activeStartTime">활성 시작시간 (시 : 분) :</label>
                            <input class="form-control" id="activeStartTime" name="StartTime" value="${challenge.activeStartTime}" >
                        </div>
                        <div class="form-group">
                            <label for="activeEndTime">활성 종료시간 (시 : 분) :</label>
                            <input class="form-control" id="activeEndTime" name="EndTime" value="${challenge.activeEndTime}" >
                        </div>
                        <div class="form-group">
                            <label for="periodDays">챌린지 적용기간(날) :</label>
                            <input class="form-control" id="periodDays" name="periodDays" value="${challenge.periodDays}" >
                        </div>
                        <div class="form-group">
                            <label for="periodHours">챌린지 적용기간(시간) :</label>
                            <input class="form-control" id="periodHours" name="periodHours" value="${challenge.periodHours}" >
                        </div>
                        <div class="form-group">
                            <label for="goalDay">챌린지 달성 조건(일) :</label>
                            <input class="form-control" id="goalDay" name="goalDay" value="${challenge.goalDay}" >
                        </div>
                        <div class="form-group">
                            <label for="goalHour">챌린지 달성 조건(시간) :</label>
                            <input class="form-control" id="goalHour" name="goalHour" value="${challenge.goalHour}" >
                        </div>
                        <div class="form-group">
                            <label for="rewardPoint">챌린지 리워드(포인트) :</label>
                            <input class="form-control" id="rewardPoint" name="rewardPoint" value="${challenge.rewardPoint}" required>
                        </div>
                        <div class="form-group form-check">
                            <input class="form-check-input" type="checkbox" id="active" ${challenge.active ? 'checked' : ''} name="active">
                            <label class="form-check-label" for="active">현재의 챌린지로 등록</label>
                        </div>
                        <input type="hidden" value="${challenge.active ? 'true' : 'false'}" name="_active"/>

                        <div class="text-right">
                            <button type="submit" class="btn btn-primary mt-3">수정</button>
                        </div>
                    </form>
                    <button type="button" id="list" class="btn btn-primary mt-3">목록</button>
                    <button type="button" id="remove" class="btn btn-danger mt-3">삭제</button>
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
    function convertNewlinesToBreaks() {
        const textareaElement = document.getElementById("description");
        const textareaValue = textareaElement.value;
        const replacedValue = textareaValue.replace(/\n/g, "<br>");
        textareaElement.innerHTML = replacedValue;
    }

    // 목록 버튼을 클릭하면 리스트 페이지로 이동합니다.
    $("#list").on("click", function() {
        location.href = "<c:url value='/admin/challenge/list'/>";
    });

    // 챌린지 추가 버튼을 클릭하면 개행 문자를 <br> 태그로 변환합니다.
    $("#convertNewlinesBtn").on("click", function() {
        convertNewlinesToBreaks();
    });

    // 삭제 버튼을 클릭하면 해당 챌린지를 삭제합니다.
    $("#remove").on("click", function() {
        if (!confirm("해당 챌린지를 삭제하시겠습니까?")) return;
        let form = $('form');
        form.attr("action", "<c:url value='/admin/challenge/remove'/>?id=${id}");
        form.attr("method", "post");
        form.submit();
    });
</script>


</body>
</html>

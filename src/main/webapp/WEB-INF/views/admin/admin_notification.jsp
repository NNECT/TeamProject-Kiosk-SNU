<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스터디카페 관리자 페이지</title>
    <c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/adminHome.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css" ></head>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
</head>

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                     <h2 class="card-title" style="margin-bottom: 20px">공지사항</h2>
                     <!-- 공지사항 목록 -->
                    <table id="example" class="display" style="width:100%;">
                        <thead>
                        <tr style="background-color: lightblue;">
                            <th style="text-align: center">번호</th>
                            <th style="text-align: center">제목</th>
                            <th style="text-align: center">외부공지 체크여부</th>
                            <th style="text-align: center">공지삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${noticeList}" var="notice">
                            <tr>
                                <td>${notice.id}</td>
                                <td><a href="${pageContext.request.contextPath}/admin/adminnotificationedit?id=${notice.id}">${notice.title}</a></td>
                                <td>${notice.outside ? 'O' : 'X'}</td>
                                <td><a href="" class="btn btn-success btn-danger deleteBtn" id="${notice.id}">삭제</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- 공지사항 작성 버튼 -->
                    <div style="padding-right: 20px">
                        <a href="${pageContext.request.contextPath}/admin/adminnotificationwrite" class="btn btn-primary mt-3 float-right">공지 작성</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value="/js/deleteNotice.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    new DataTable('#example');
</script>
<script>
    <c:if test="${noticeRegistered}">
    window.addEventListener('DOMContentLoaded', (event) => {
        window.alert("등록되었습니다.");
    });
    </c:if>
</script>
</body>
</html>

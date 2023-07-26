<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스터디카페 관리자 페이지</title>
    <c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/adminHome.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="/css/admin/admin_report.css">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
</head>



<body>
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-10">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">룸현황</h2>
                    <table id="seatReportTable" class="display">
                        <thead>
                        <tr style="background-color: lightblue;">
                            <th style="text-align: center">룸번호</th>
                            <th style="text-align: center">사용자번호</th>
                            <th style="text-align: center">사용자이름</th>
                            <th style="text-align: center">시작시간</th>
                            <th style="text-align: center">종료시간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${roomReport}" var="roomReport">
                            <tr>
                                <td>${roomReport.seat_seatNumber}</td>
                                <td>${roomReport.account_id}</td>
                                <td>${roomReport.account_username}</td>
                                <td id="startDateTime">${roomReport.getStartDateTimeString()}</td>
                                <td id="endDateTime">${roomReport.getEndDateTimeString()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>룸번호</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="/js/adminReport.js"></script>


</body>
</html>




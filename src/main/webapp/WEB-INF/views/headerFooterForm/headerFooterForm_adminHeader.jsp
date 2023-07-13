<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loggedIn" value="${loggedIn}" scope="request" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스터디카페 관리자 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/adminHome.css">

</head>
<body>

<!-- 상단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">스터디카페 관리자</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <c:choose>
                    <%-- 로그인 상태라면 네비게이션 바에 로그아웃, 마이페이지를 한 줄에 표시 --%>
                    <c:when test="${loggedIn}">
                        <span class="nav-link ml-auto">
                            <a href="${pageContext.request.contextPath}/admin/adminlogout">로그아웃</a>
                            <span class="text-muted mx-2">|</span>
                            <a href="${pageContext.request.contextPath}/admin/adminmypage">마이페이지</a>
                        </span>
                    </c:when>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>

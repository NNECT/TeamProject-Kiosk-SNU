<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-06-26
  Time: 오전 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <a href="<c:url value="/outside"/>"><h1>외부 키오스크 페이지</h1></a>
    <a href="<c:url value="/inside"/>"><h1>내부 키오스크 페이지</h1></a>
    <a href="<c:url value="/admin/adminlogin"/>"><h1>관리자 페이지</h1></a>
    테스트용 파일<br>
<%--    ${user1.username}<br>--%>
<%--    ${user1.password}<br>--%>
<%--    ${user1.phoneNumber}<br>--%>
<%--    ${usage.get(0).endDate}<br>--%>
</body>
</html>

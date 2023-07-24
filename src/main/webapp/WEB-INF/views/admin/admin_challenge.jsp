<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>챌린지 목록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 40px;
            text-align: center;
        }

        h1 {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<script>
    let msg = "${msg}"
    if(msg == "good") alert("삭제되었습니다.")
    if(msg == "error") alert("삭제에 실패하였습니다.")
</script>
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">스터디카페 관리자 페이지</h2>
                    <hr>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>챌린지명</th>
                            <th>현재 챌린지 여부</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="challenge" items="${list}">
                            <tr>
                                <td><a href="<c:url value='/admin/challenge/read'/>?id=${challenge.id}">${challenge.title}</a></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${challenge.active}">
                                            활성화
                                        </c:when>
                                        <c:otherwise>
                                            비활성화
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                        <a href="${pageContext.request.contextPath}/admin/challenge/write" class="btn btn-primary mt-3">+</a>
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

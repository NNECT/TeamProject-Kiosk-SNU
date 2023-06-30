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
                    <h2 class="card-title">회원목록</h2>
                    <div class = "text-right">
                    <input type="text" placeholder = "이름 검색하기" id="userId" name="id">
                    </div>
                    <hr>
                    <table class="table">
                        <thead>
                            <tr style ="background-color: lightblue;">
                                <th>이름</th>
                                <th>전화번호</th>
                                <th>아이디</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>홍길동</td>
                                <td>010-1234-5678</td>
                                <td>25</td>
                                <td>등록일</td>
                            </tr>
                        </tbody>
                    </table>
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

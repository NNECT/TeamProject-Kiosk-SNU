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
                    <h2 class="card-title">스터디카페 관리자 페이지</h2>
                    <hr>

                    <!-- 공지사항 게시판 -->
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">공지사항</h5>
                            <hr>
                            <!-- 공지사항 목록 -->
                            <ul class="list-group">
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <a href="#">에어컨 관련 공지</a>
                                    <a href="#" class="btn btn-success">공지 수정</a>
                                    <a href="#" class="btn btn-success btn-danger">공지 삭제</a>
                                    <input type ="checkbox" value = "">
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <a href="#">에어컨 관련 공지</a>
                                    <a href="#" class="btn btn-success">공지 수정</a>
                                    <a href="#" class="btn btn-success btn-danger">공지 삭제</a>
                                    <input type ="checkbox" value = "">
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <a href="#">화장실 관련 공지</a>
                                    <a href="#" class="btn btn-success">공지 수정</a>
                                    <a href="#" class="btn btn-success btn-danger">공지 삭제</a>
                                    <input type ="checkbox" value = "">
                                </li>
                                <!-- 필요한 만큼 공지사항 목록을 추가할 수 있습니다. -->
                            </ul>
                            <!-- 공지사항 작성 버튼 -->
                            <a href="${pageContext.request.contextPath}/admin/adminnotificationwrite" class="btn btn-primary mt-3 float-right">공지 작성</a>

                            
                        </div>
                    </div>
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

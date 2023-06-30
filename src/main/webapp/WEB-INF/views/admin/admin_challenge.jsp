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
                    <hr>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>이름</th>
                                <th>전화번호</th>
                                <th>나이</th>
                                <th>잔여시간/잔여일수</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>홍길동</td>
                                <td>010-1234-5678</td>
                                <td>25</td>
                                <td>56시간 2분</td>
                            </tr>
                            <tr>
                                <td>강감찬</td>
                                <td>010-9876-5432</td>
                                <td>32</td>
                                <td>12시간 30분</td>
                            </tr>
                            <tr>
                                <td>이순신</td>
                                <td>010-5555-8888</td>
                                <td>48</td>
                                <td>37시간 20분</td>
                            </tr>
                            <tr>
                                <td>배고파</td>
                                <td>010-1111-2222</td>
                                <td>20</td>
                                <td>5일</td>
                            </tr>
                            <tr>
                                <td>배고파</td>
                                <td>010-3333-4444</td>
                                <td>29</td>
                                <td>23일</td>
                            </tr>
                            <tr>
                                <td>정말배고파</td>
                                <td>010-7777-9999</td>
                                <td>37</td>
                                <td>18일</td>
                            </tr>
                            <tr>
                                <td>배고프고졸려</td>
                                <td>010-7777-9999</td>
                                <td>37</td>
                                <td>18일</td>
                            </tr>
                            <tr>
                                <td>졸려배고파</td>
                                <td>010-7777-9999</td>
                                <td>37</td>
                                <td>18일</td>
                            </tr>
                            <tr>
                                <td>너무배고파</td>
                                <td>010-7777-9999</td>
                                <td>37</td>
                                <td>18일</td>
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

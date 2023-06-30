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

                    <h2 class="card-title">사물함 현황</h2>
                    <table class="table table-bordered">
					    <tbody>
					       <% for (int row = 1; row <= 6; row++) { %>
							    <tr>
							        <% for (int col = 1; col <= 5; col++) { %>
							            <% if (row % 2 == 0 && col % 2 == 0) { %>
							                <td style="width: 50px; height: 50px; text-align: center; border: 1px solid black;"><%= row %>-<%= col %><br> (사용중)</td>
							            <% } else { %>
							                <td style="width: 50px; height: 50px; text-align: center; border: 1px solid black;"><%= row %>-<%= col %></td>
							            <% } %>
							        <% } %>
							    </tr>
							<% } %>

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

<!-- 메뉴 클릭 시 해당 페이지로 이동 -->
<script>
    $(document).ready(function () {
        // 자리관리 클릭 시
        $(".list-group-item:eq(0)").click(function () {
            $("#content").load("realtime-seat-management.jsp");
        });
        // 회원관리 클릭 시
        $(".list-group-item:eq(1)").click(function () {
            $("#content").load("member-management.jsp");
        });
        // 사물함관리 클릭 시
        $(".list-group-item:eq(2)").click(function () {
            $("#content").load("realtime-locker-management.jsp");
        });
        // 매출현황 클릭 시
        $(".list-group-item:eq(3)").click(function () {
            $("#content").load("seat-statistics.jsp");
        });
        // 공지기록 클릭 시
        $(".list-group-item:eq(4)").click(function () {
            $("#content").load("notice-record.jsp");
        });
        // 채팅 클릭 시
        $(".list-group-item:eq(5)").click(function () {
            $("#content").load("chat.jsp");
        });
    });
</script>

</body>
</html>

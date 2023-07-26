<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,max-mun-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<c:url value="/css/admin/admin_locker.css"/>">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/adminHome.css">

	<title>스터디카페 관리자 페이지</title>
</head>
<body>

<!-- 상단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">스터디카페 관리자</a>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<c:choose>
					<%-- 로그인 상태라면 네비게이션 바에 로그아웃, 마이페이지를 한 줄에 표시 --%>
					<c:when test="${loggedIn}">
                        <span class="nav-link ml-auto">
                            <a href="${pageContext.request.contextPath}/admin/adminlogout">로그아웃</a>
                            <span class="text-muted mx-2">|</span>
                            <a href="${pageContext.request.contextPath}/admin/adminChangePassword">비밀번호변경</a>
                        </span>
					</c:when>
				</c:choose>
			</li>
		</ul>
	</div>
</nav>


<div class="container">
	<div class="row justify-content-center align-items-center" style="height: 30vh;">
		<div class="col-md-8">
			<div class="card">
				<div class="card-body text-center">
					<h2 class="card-title">사물함 현황</h2>
					<div id="whiteWrap">
						<section>
							<form id="lockerFrm">
								<table id="locker">
									<tr>
										<td class="locker r" id="20"></td>
										<td class="locker r" id="19"></td>
										<td colspan="8" class="none">
									</tr>
									<tr>
										<td class="locker r" id="18"></td>
										<td class="locker r" id="17"></td>
										<td class="locker r" id="16"></td>
										<td class="locker r" id="15"></td>
										<td class="locker r" id="14"></td>
										<td class="locker r" id="13"></td>
										<td class="locker r" id="12"></td>
										<td class="locker r" id="11"></td>
										<td class="locker r" id="10"></td>
									</tr>
									<tr>
										<td class="locker r" id="9" ></td>
										<td class="locker r" id="8" ></td>
										<td class="locker r" id="7" ></td>
										<td class="locker r" id="6" ></td>
										<td class="locker r" id="5" ></td>
										<td class="locker r" id="4" ></td>
										<td class="locker r" id="3" ></td>
										<td class="locker r" id="2" ></td>
										<td class="locker r" id="1" ></td>
									</tr>
								</table>
								<div id="info"><!--자리설명-->
									<div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;
									<div class="box g"></div>&nbsp;<span class="gray">사용 중</span>&nbsp;
									<div class="box rBox"></div>&nbsp;<span class="red">사용 불가능</span>&nbsp;
								</div>

							</form>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 모달 창 -->
<div id="lockerModal" class="modal" style="display: none; margin-top: 130px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">사물함 정보</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<th class="col-4">사물함번호:</th>
						<td class="col-8" id="lockerId"></td>
					</tr>
					<tr>
						<th class="col-4">회원번호:</th>
						<td class="col-8" id="memberId"></td>
					</tr>
					<tr>
						<th>아이디:</th>
						<td id="memberName"></td>
					</tr>
					<tr>
						<th>전화번호:</th>
						<td id="memberPhone"></td>
					</tr>
					<tr>
						<th>잔여일:</th>
						<td id="memberRemainTime"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center">
							<button type="button" id="lockerActivationBtn" class="lockerActivationBtn"></button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="<c:url value="/js/radioBox.js"/>"></script>
<script>
	window.addEventListener('DOMContentLoaded', (event) => {
		const lockers = document.querySelectorAll('.locker');
		const status = ${lockerStatusMap};
		lockers.forEach((locker, index) => {
			if(status[Number(locker.id)] === 1) {
				locker.classList.remove('r');
				locker.classList.add('b');
				locker.innerHTML = locker.id;
			} else if (status[Number(locker.id)] === 0) {
				locker.classList.remove('r');
				locker.classList.add('g');
				locker.innerHTML = locker.id;
			} else {
				locker.innerHTML = locker.id;
			}

			locker.addEventListener('click', (e) => {
				const lockerId = e.target.id;
				console.log(lockerId);
				showLockerModal(lockerId);
			});
		});

		//모달 창
		function showLockerModal(lockerId) {
			$.ajax({
				type: "POST",
				url: "../ajax/getLocker",
				data: JSON.stringify({ lockerId: lockerId }),
				dataType: "json",
				async: false,
				contentType: "application/json; charset=utf-8",
				success: function (response) {
					if (response.result === "success") {
						$("#lockerId").text(lockerId);
						$("#memberId").text(response.memberId);
						$("#memberName").text(response.memberName);
						$("#memberPhone").text(response.memberPhone);
						$("#memberRemainTime").text(response.memberRemainTime);

						// 활성화 버튼
						const lockerActivationBtn = $("#lockerActivationBtn");
						lockerActivationBtn.text(response.lockerActivation ? '비활성화' : '활성화');
						lockerActivationBtn.val(response.lockerActivation ? '비활성화' : '활성화');
						if (response.memberRemainTime==="") {
							lockerActivationBtn.show(); // 활성화 상태인 경우 보여줍니다.
							lockerActivationBtn.off("click").on("click", function () {
								const lockerActivation = response.lockerActivation;
								const confirmMsg = lockerActivation ? "해당 사물함을 비활성화하시겠습니까?" : "해당 사물함을 활성화하시겠습니까?";
								if (confirm(confirmMsg)) {
									updateLockerUsable(lockerId, !lockerActivation);
								}
							});
						} else {
							lockerActivationBtn.hide(); // 비활성화 상태인 경우 숨깁니다.
						}

						// 모달 창을 띄웁니다.
						$("#lockerModal").modal("show");
					}
				},
				error: function (request, status, error) {
					alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				}
			});
		}

		// 사물함 usable 상태 업데이트 함수
		function updateLockerUsable(lockerId, usable) {
			$.ajax({
				type: "POST",
				url: "../ajax/updateLockerUsable",
				data: JSON.stringify({
					lockerId: lockerId,
					usable: usable }),
				dataType: "json",
				async: false,
				contentType: "application/json; charset=utf-8",
				success: function (response) {
					if (response.result === "success") {
						const lockerActivationBtn = $("#lockerActivationBtn");
						lockerActivationBtn.val(response.lockerActivation ? '비활성화' : '활성화');
						$("#lockerModal").modal("hide");
						alert("처리되었습니다");
					}
				},
				error: function (request, status, error) {
					alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				}
			});
		}

	});
</script>

</body>
</html>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/css/snu_lockerTicket.css"/>">
	<title>snu_locker_page</title>
</head>
<body>
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
						<th class="col-6">사물함번호:</th>
						<td class="col-6" id="lockerId"></td>
					</tr>
					<tr>
						<th class="col-6">회원번호:</th>
						<td class="col-6" id="memberId"></td>
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
						<th>남은 시간:</th>
						<td id="memberRemainTime"></td>
					</tr>
					<tr>
						<td>
							<button type="button" id="endUse" class="float-right endUseLocker">사용종료</button>
						</td>
					</tr>
					<tr>
						<th>사물함 비활성화</th>
						<td><input type="checkbox" id="lockerDeactivation"></td>
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

		// 모달 창 보여주는 함수
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

						// 모달 창을 띄웁니다.
						$("#lockerModal").modal("show");
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




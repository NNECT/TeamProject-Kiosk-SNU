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
                    <h4>공지사항 수정</h4>
                    <form action="${pageContext.request.contextPath}/admin/adminnotification?id=${notice.id}" method="post">
                        <div class="form-group">
                            <label for="title"></label>
                            <textarea class="form-control form-control-lg" id="title" name="title" rows="1">${notice.title}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="notice">공지 내용</label>
                            <textarea class="form-control form-control-lg" id="notice" name="content" rows="5">${notice.content}</textarea>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary ">수정</button>
                        </div>
                        <div class="float-right">
                            <span><em>외부 키오스크 공지시 체크</em></span>
                            <input type="checkbox" id="OutsideNotification" name="outside">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    const outsideCheckbox = document.getElementById("OutsideNotification");
    const isOutside = ${notice.outside};

    if (isOutside){
        outsideCheckbox.checked = true;
    }else {
        outsideCheckbox.checked = false;
    }

    // 수정이 완료되었을 때 알림 창 띄우기
    function showNotification() {
        alert("수정되었습니다");
    }

    // 수정 버튼을 눌렀을 때 알림 창 띄우기
    const submitButton = document.querySelector("button[type='submit']");
    submitButton.addEventListener("click", showNotification);

</script>

</body>
</html>

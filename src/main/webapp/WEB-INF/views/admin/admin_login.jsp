<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp"/>
<%--팝오버창 구현 위해 부트스트랩 5.2.3 적용--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">스터디카페 관리자 페이지</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}/admin/adminLogin" method="POST"
                          id="admin-login-form">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="username"
                                       class="text-center">아이디&nbsp;&nbsp;&nbsp;</label>
                                <input type="text" id="username" name="username" required autofocus>
                            </li>
                            <li class="list-group-item">
                                <label for="password">비밀번호</label>
                                <input type="password" id="password" name="password" required>
                            </li>
                            <li class="list-group-item">
                                <%--<button type="submit" class="btn btn-primary">로그인&nbsp;</button>--%>
                                <button type="submit" class="btn btn-lg btn-primary" data-bs-toggle="popover"
                                        data-bs-title="로그인실패"
                                        data-bs-content="관리자 정보를 확인해주세요">
                                    로그인
                                </button>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"></script>
<%--팝오버 구현위해 부트스트랩 script 부분 적용--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<%--암호화--%>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script>
    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("admin-login-form").addEventListener("submit", function (e) {
            e.preventDefault();

            const crypt = new JSEncrypt();
            crypt.setPublicKey("${publicKey}");

            const username = document.getElementById("username").value;
            const password = crypt.encrypt(document.getElementById("password").value);

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "<c:url value="/admin/adminLogin" />");
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("hidden", "true");

            const usernameInput = document.createElement("input");
            usernameInput.setAttribute("type", "text");
            usernameInput.setAttribute("name", "username");
            usernameInput.setAttribute("value", username);
            form.appendChild(usernameInput);

            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("name", "password");
            passwordInput.setAttribute("value", password);
            form.appendChild(passwordInput);

            document.body.appendChild(form);
            form.submit();
        });
        var loginFail = "${loginFail}";
        if (loginFail === "loginFail") {
            // loginFail 반환값 들어오면 로그인 실패 팝오버 나옴
            const popoverTrigger = document.querySelector("[data-bs-toggle='popover']");
            const popover = new bootstrap.Popover(popoverTrigger, {
                title: "로그인실패",
                content: "관리자 정보를 확인해주세요",
                trigger: "manual",
            });
            popover.show();

            // 클릭하면 팝오버 비활성화
            document.addEventListener("click", function hidePopoverOnClick(event) {
                const target = event.target;
                if (!popoverTrigger.contains(target)) {
                    popover.hide();
                    document.removeEventListener("click", hidePopoverOnClick);
                }
            });
        }
    });



</script>
</body>
</html>

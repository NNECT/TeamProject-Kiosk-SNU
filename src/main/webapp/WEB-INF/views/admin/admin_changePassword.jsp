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
                    <h2 class="card-title">관리자 페이지 비밀번호 변경</h2>
                    <hr>
                    <form id="sudo-form">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="prePassword" class="text-center">현재 비밀번호</label>
                                <input type="password" id="prePassword" name="prePassword" required autofocus>
                            </li>
                            <li class="list-group-item">
                                <label for="password">새로운 비밀번호</label>
                                <%--dto 설정값으로 name 과 id 조금 헷갈릴 수 있음--%>
                                <input type="password" id="password" name="password" onkeyup="checkPasswordMatch()" required>
                            </li>
                            <li class="list-group-item">
                                <label for="password_confirm">다시 입력해주세요</label>
                                <input type="password" id="password_confirm" name="password_confirm" onkeyup="checkPasswordMatch()" required>
                                <span id="passwordCheck"></span>
                            </li>
                            <li class="list-group-item">
                                <button type="submit" class="btn btn-primary">변경하기</button>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("sudo-form").addEventListener("submit", newPasswordConfirm);
    });
    function newPasswordConfirm(e) {
        e.preventDefault();

        var passwordCheck = document.getElementById("passwordCheck").innerHTML;
        if (passwordCheck === "비밀번호 일치") {
            const crypt = new JSEncrypt();
            crypt.setPublicKey("${publicKey}");

            /*controller에서 넘어온 admin 값을 username 넘겨주기*/
            const username = "${sessionScope.admin.username}";
            const password = crypt.encrypt(document.getElementById("password").value);
            const prePassword = crypt.encrypt(document.getElementById("prePassword").value);

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "${pageContext.request.contextPath}/admin/adminmypage");
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

            const prePasswordInput = document.createElement("input");
            prePasswordInput.setAttribute("type", "prePassword");
            prePasswordInput.setAttribute("name", "prePassword");
            prePasswordInput.setAttribute("value", prePassword);
            form.appendChild(prePasswordInput);

            document.body.appendChild(form);
            form.submit();
        }
    }


    function checkPasswordMatch() {
        var newPassword = document.getElementById("password").value;
        var passwordConfirm = document.getElementById("password_confirm").value;
        var passwordCheck = document.getElementById("passwordCheck");

        if (newPassword === passwordConfirm && newPassword !== "" && passwordConfirm !== "") {
            passwordCheck.innerHTML = "비밀번호 일치";
            passwordCheck.style.color = "blue";
            passwordCheck.style.display = "block"; // 보이도록 설정
        } else if (newPassword !== passwordConfirm) {
            passwordCheck.innerHTML = "비밀번호 불일치";
            passwordCheck.style.color = "red";
            passwordCheck.style.display = "block"; // 보이도록 설정
        } else {
            passwordCheck.innerHTML = ""; // 비어있도록 설정
            passwordCheck.style.display = "none"; // 숨김 처리
        }
    }

    //비밀번호 잘못입력
    var checkFail = "${checkFail}";
    if(checkFail === "checkFail"){
        //비밀번호 잘못 입력하면 alert
        alert("이전 비밀번호를 잘못 입력하셨습니다.")
    }

</script>

</body>
</html>

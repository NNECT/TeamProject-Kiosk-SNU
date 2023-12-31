<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/css/inside/inside_mypage.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/inside/modalMyPagePassword.css"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/css/inside/inside_challengList.css"/>">--%>
    <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
    <style>
        body {
            font-family: 'SUITE', sans-serif;
        }
    </style>
    <title>snu_myPage</title>
</head>
<body>
<div class="body">
    <a href="<c:url value="/inside/menu"/>"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>

    <section id="allWrap"><!--전체 감싸는 박스-->
        <p id="title">마이페이지</p>

        <hr>
        <div id="contentBox">
            <div class="tr">
                <p class="content id">아이디</p>
                <div class="border">
                    <span class="showText">${sessionScope.author.username}</span>
                </div>
            </div>
            <div class="tr">
                <p class="content tel">전화번호</p>
                <div class="border">
                    <span class="showText">${sessionScope.author.phoneNumber}</span>
                    <input type="button" class="btn telBtn" id="openModal" onclick="showModal('modalPage')" value="변경">
                </div>
            </div>
            <div class="tr">
                <div class="fullborder">
                    <p class="content pass">비밀번호</p>
                    <!-- 수정된 부분: onclick 함수에 모달 ID를 전달하도록 수정 -->
                    <input type="button" class="btn passBtn" id="reOpenModal" onclick="showModal ('rePasswordModal')"
                           value="재설정">
                </div>
            </div>
            <div class="tr">
                <p class="content ticket">사용권</p>
                <div class="border">
                    <span class="showText"></span>
                    <input type="button" class="btn ticketBtn" value="결제내역" onclick="showModal('paymentModal')">
                </div>
            </div>
            <div class="tr">
                <p class="content challenge">챌린지</p>
                <div class="border">
                    <span class="showText"></span>
                    <input type="button" class="btn challBtn" value="참여내역" onclick="showModal('challengeModal')">
                </div>
            </div>
            <div class="tr">
                <div class="fullborder">
                    <p class="content point">보유포인트</p>
                    <span class="showText">${sessionScope.author.point}</span>
                </div>
            </div>
        </div>
    </section>
</div>

<!--전화번호 변경 모달영역-->
<div class="modalBg"></div>
<div id="modalPage">
<%--    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="xIcon" >--%>
    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="modalNoBtn" id="cancelTel">
    <form id="changeTel">
        <div class="modalContent">
            <p class="modalP">
                ${sessionScope.author.username}님의 회원정보 <span class="modalSpan">전화번호</span>를<br>
                수정하기 위해 인증절차가 필요합니다.
            </p>
            <hr class="modalHr">
            <input type="password" name="" id="passwordInput" class="first" placeholder="비밀번호를 입력해주세요">
            <input type="button" class="modalCheckBtn" id=password value="확인" onclick="passwordCheck_phone()"><br>
            <span id="passwordCheck"></span>
            <!--페스워드 확인이 되야 전화번호 변경가능-->
            <input type="text" id="phoneNumberInput" class="first" placeholder="변경할 전화번호를 입력해주세요" maxlength="13">
            <input type="button" class="modalCheckBtn" id=phoneNumber value="전송" onclick="codeSend()"><br>
            <span id="codeSendCheck"></span><!--안내문구--><br>
            <input type="text" id="codeInput" class="first" placeholder="인증번호를 입력해주세요">
            <input type="button" class="modalCheckBtn" id=code value="확인" onclick="codeCheck()"><br>
            <span id="codeCheck"></span><!--안내문구-->
        </div>
        <div class="modalBtn" id="changePhone">
<%--            <input type="button" class="modalNoBtn" value="취소" id="cancelTel">--%>
            <input type="submit" id="phoneNumberChangeBtn" name="" style="display: none" class="modalYesBtn" value="변경">
        </div>
    </form>
</div>
<!--모달영역-->
<!--비밀번호변경_모달영역1-->
<div id="rePasswordModal">
    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="modalNoBtn" id="cancelBtn">
    <form>
        <div class="modalContent">
            <p class="modalP">
                <span class="modalSpan">비밀번호</span>를 변경하려면 전화번호<br>
                인증이 필요합니다.
            </p>
            <hr class="modalHr">
            <input type="text" name="" id="changePwdPhone" class="first" placeholder="전화번호를 입력해주세요" maxlength="13">
            <input type="button" class="modalCheckBtn" value="인증" onclick="changePwdCodeSend()"><br>
            <span id="changePwdText1"></span><!--안내문구--><br>

            <!--인증번호 확인이 되야 전화번호 변경가능-->
            <input type="text" id="checkCode" class="first" placeholder="인증번호를 입력해주세요">
            <input type="button" class="modalCheckBtn" value="인증" onclick="changePwdCodeCheck()"><br>
            <span id="changePwdText2"></span><!--안내문구-->
        </div>
        <div class="modalBtn">
            <input type="button" id="editPassword_checkPhone" name="" class="modalYesBtn" value="다음"
                   style="display: none" onclick="showNextModal()">
        </div>
    </form>
</div>
<!--모달영역-->

<!-- 비밀번호변경 모달-->
<div id="newPasswordModal">
    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="modalNoBtn">
    <form id="finish">
        <div class="modalContent">
            <p class="modalP">
                <span class="modalSpan">변경할 비밀번호</span>를 입력해주세요<br>
            </p>
            <hr class="modalHr">
            <%--최소 6자리, 이전 비번과 동일비번 불가능--%>
            <input type="password" name="" id="changePassword1" class="third" placeholder="새 비밀번호"><br>
            <input type="password" name="" id="changePassword2" class="third" placeholder="비밀번호 확인">
            <span id="changePasswordText"></span><!--안내문구-->
        </div>
        <div id="modalBtn">
        <input type="submit" id="changePwdBtn" class="finishModalBtn" value="변경">

        </div>
    </form>
</div>
<%--결제내역 모달--%>
<div class="modalPage" id="paymentModal">
    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="modalNoBtn">
    <form>
        <div class="modalContent2">
            <p class="modalP">
                <span class="modalSpan">결제 내역</span><br>
            </p>
<%--            <hr class="modalHr">--%>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>결제일</th>
                        <th>결제 방식</th>
                        <th>결제금액</th>
                        <th>결제정보</th>
                    </tr>
                    </thead>
                    <c:forEach var="payment" items="${paymentList}">
                        <input type="hidden" value='${payment.log}' class="paymentList">
                        <input type="hidden" value="${payment.dateTime}" id="paymentDate">
                        <tr id="p${payment.id}"><!--반복분 부분-->
                            <tbody id="paymentTableBody"></tbody>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </form>
<%--    <input type="button" class="modalCloseBtn" value="닫기" id="cancelPayment">--%>
</div>
<%--챌린지 모달--%>
<div class="modalPage2" id="challengeModal">
    <img src="<c:url value="/img/inside/xIcon.png"/>" alt="x" class="modalNoBtn" id="cancelPwd">
    <form>
        <div class="modalContent2">
            <p class="modalP">
                <span class="modalSpan">참여 챌린지 내역</span>
            </p>
<%--            <hr class="modalHr">--%>
            <div>
                <table>
                    <tr>
                        <th>날짜</th>
                        <th>챌린지</th>
                        <th>결과</th>
                        <th>받은포인트</th>
                    </tr>
                    <c:forEach var="challengeList" items="${challengeList}">
                        <tr><!--반복분 부분-->
                            <td>${challengeList.getStartDateTimeString()}</td>
                            <td>${challengeList.challenge_title}</td>
                            <td>${challengeList.result ? "성공":"실패"}</td>
                            <td>${challengeList.rewardPoint}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </form>
<%--    <input type="button"  class="modalCloseBtn" value="닫기" id="cancelChallenge">--%>
</div>
<%--
<script src="<c:url value="/js/modalNewPass.js"/>"></script>
<script src="<c:url value="/js/newPassword.js"/>"></script>
--%>
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="<c:url value="/js/modal.js"/>"></script>
<script src="<c:url value="/js/insideTimeChecker.js"/>"></script>
<script>
    new InsideTimeChecker("${sessionScope.author.id}", "${sessionScope.insideType}", "${sessionScope.insideNumber}", null, null, "<c:url value="/inside/logout" />");

    var checkPWfail = "${checkPWfail}";
    if (checkPWfail === "checkPWfail") {
        alert("이전 비밀번호와 동일한 비밀번호를 사용하실 수 없습니다.")
    }

    const crypt = new JSEncrypt();
    crypt.setPublicKey("${publicKey}");
    window.addEventListener("DOMContentLoaded", (event) => {
        document.getElementById("finish").addEventListener("submit", function (e) {
            e.preventDefault();

            const form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "<c:url value="/inside/mypage/changepwd" />");
            form.setAttribute("charset", "UTF-8");
            form.setAttribute("hidden", "true");

            const passwordInput = document.createElement("input");
            passwordInput.setAttribute("type", "password");
            passwordInput.setAttribute("name", "password");
            passwordInput.setAttribute("value", crypt.encrypt(document.getElementById("changePassword2").value));
            form.appendChild(passwordInput);

            document.body.appendChild(form);
            form.submit();
        });
    })

</script>
<script src="<c:url value="/js/editPhoneNumber.js"/>"></script>
<script src="<c:url value="/js/insideEditPassword_checkPhone.js"/>"></script>
<script src="<c:url value='/js/inside_PaymentLog.js'/>"></script>
</body>
</html>

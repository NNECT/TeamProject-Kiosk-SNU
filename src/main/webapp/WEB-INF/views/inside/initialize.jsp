<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

  <style>
    body {font-family: 'SUITE', sans-serif;}
    #body{ position: relative;}
    form{
      position: absolute;
      top: 200px;
      left: 50%;
      transform: translate(-50%,0);
    }
    input{
      width: 200px;
      height: 35px;
      border: 1px solid gray;
      border-radius: 8px;
    }
    p{
      margin-bottom: 5px;
    }
    .btn{
      margin-top: 10px;
      width: 95px;
    }
  </style>
  <title>snu_mypage_page</title>
</head>
<body>
<div id="body">
  <form>
    <table>
      <tr>
        <td colspan="2">
          <label>
            <p>번호</p>
            <input type="text" name="" id="number">
          </label>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <label>
            <p>관리자 아이디</p>
            <input type="text" name="" id="username">
          </label>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <label>
            <p>비밀번호</p>
            <input type="password" name="" id="password">
          </label>
          <br>
          <br>
        </td>
      </tr>
      <tr>
        <td style="text-align: center;">
          <input type="button" value="자리" class="btn" id="seat">
        </td>
        <td style="text-align: center;">
          <input type="button" value="룸" class="btn" id="room">
        </td>
      </tr>
    </table>

  </form>
</div>
<script src="<c:url value="/js/jsencrypt.min.js"/>"></script>
<script>
  window.addEventListener("DOMContentLoaded", (event) => {
    document.querySelectorAll(".btn").forEach((elm) => {
      elm.addEventListener("click", function (e) {
        const crypt = new JSEncrypt();
        crypt.setPublicKey("${publicKey}");

        const number = document.getElementById("number").value;
        const username = document.getElementById("username").value;
        const password = crypt.encrypt(document.getElementById("password").value);
        const type = e.target.id;

        const form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "<c:url value="/inside"/>");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("hidden", "true");

        const numberInput = document.createElement("input");
        numberInput.setAttribute("type", "text");
        numberInput.setAttribute("name", "number");
        numberInput.setAttribute("value", number);

        const usernameInput = document.createElement("input");
        usernameInput.setAttribute("type", "text");
        usernameInput.setAttribute("name", "username");
        usernameInput.setAttribute("value", username);

        const passwordInput = document.createElement("input");
        passwordInput.setAttribute("type", "password");
        passwordInput.setAttribute("name", "password");
        passwordInput.setAttribute("value", password);

        const typeInput = document.createElement("input");
        typeInput.setAttribute("type", "text");
        typeInput.setAttribute("name", "type");
        typeInput.setAttribute("value", type);

        form.appendChild(numberInput);
        form.appendChild(usernameInput);
        form.appendChild(passwordInput);
        form.appendChild(typeInput);

        document.body.appendChild(form);
        form.submit();
      })
    });
  });
</script>
</body>
</html>

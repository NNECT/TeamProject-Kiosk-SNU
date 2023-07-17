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
    #btn{
      margin-top: 10px;
    }
  </style>
  <title>snu_mypage_page</title>
</head>
<body>
<div id="body">
  <form action="">
    <table>
      <tr>
        <label for="">
          <p>좌석번호</p>
          <input type="text" name="" id="seatNumber" >
        </label>
      </tr>
      <tr>
        <label for="">
          <p>관리자 아이디</p>
          <input type="text" name="" id="id">
        </label>
      </tr>
      <tr>
        <label for="">
          <p>비밀번호</p>
          <input type="password" name="" id="password">
        </label>
      </tr>
      <br>
      <br>
      <tr>
        <input type="submit" value="좌석등록" id="Btn">
      </tr>
    </table>

  </form>
</div>

</html>

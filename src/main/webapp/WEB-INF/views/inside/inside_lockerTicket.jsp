<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/css/inside/inside_lockerTicket.css"/>">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_timeTicket_page</title>
    </head>
        <body>
            <div id="body">
              <a href="/inside/inside_menu.jsp"><img src="<c:url value="/img/inside/beforBtn.png"/>" alt=""></a>
             
              <div id="whiteWrap">
                <section>
                    <ul>
                        <li id="lockerTicket">사물함</li>
                    </ul>
                    <hr>
                    
                    <form id="lockerFrm">
                        <p>사물함을 선택해주세요</p>
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
                                <td class="locker r" id="9"></td>
                                <td class="locker r" id="8"></td>
                                <td class="locker r" id="7"></td>
                                <td class="locker r" id="6"></td>
                                <td class="locker r" id="5"></td>
                                <td class="locker r" id="4"></td>
                                <td class="locker r" id="3"></td>
                                <td class="locker r" id="2"></td>
                                <td class="locker r" id="1"></td>
                            </tr>
                        </table>
                        
                        
                        <div id="info"><!--자리설명-->
                            <div class="box b"></div>&nbsp;<span class="blue">사용 가능</span>&nbsp;&nbsp;
                            <div class="box g"></div>&nbsp;<span class="gray">사용 중</span>
                            <div class="box rBox"></div>&nbsp;<span class="red">사용 불가능</span>
                            <div class="box m"></div>&nbsp;<span class="my">내 사물함</span>
                    </div>
                        <table id="ticket">
                            <td class="radio-box">
                                <input type="radio" name="radio-button" class="radio-input" value="">
                                <p class="t btn-text" name="time" class="rText">30일</p>
                                <p class="p btn-text" name="price" class="rText">6,000원</p>    
                            </td>
                            <td class="radio-box">
                                <input type="radio" name="radio-button" class="radio-input" value="">
                                <p class="t btn-text" name="time">사용X</p><br>
                                <p class="p btn-text" name="price"></p><br>
                                
                            </td>
                        </table>
                        
                        <input id="beforeBtn" type="submit" value="이전">
                        <input id="nextBtn" type="submit" value="다음">
                    </form>
                </section>
            </div>
            <script src="/js/radioBox.js"></script>
            <script>
                // <!-- select테이블 코드를 그대로 가져와서 locker로만 변경했습니다 -->
                window.addEventListener('DOMContentLoaded', (event) => {
                    const lockers = document.querySelectorAll('.locker');
                    const status = ${lockerStatusMap};
                    lockers.forEach((locker, index) => {
                    if(status[Number(locker.id)] === 1) {
                        locker.classList.remove('r');
                        locker.classList.add('b');
                        locker.innerHTML = "<a href='<c:url value="/outside/login"/>?type=locker&number=" + locker.id + "'>" + locker.id + "</a>"
                    } else if (status[Number(locker.id)] === 0) {
                        locker.classList.remove('r');
                        locker.classList.add('g');
                        locker.innerHTML = locker.id;
                    } else {
                        locker.innerHTML = locker.id;
                    }
                    });
                });
                </script>
                 
        
        </div>
        </body>
    
 </html>
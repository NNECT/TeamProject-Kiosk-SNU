<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../inside/css/snu_mypage.css">
        <link rel="stylesheet" href="../inside/css/modalCommon.css">
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_mypage_page</title>
    </head>
        <body>
            <div id="body">
                <a href="inside_menu.jsp"><img src="../inside/img/beforBtn.png" alt=""></a>

                <section id="allWrap"><!--전체 감싸는 박스-->
                    <p id="title">마이페이지</p>
                    
                    <hr>
                    <div id="contentBox">
                        <div id="tr">
                            <p class="content id">아이디</p>
                            <div id="border">
                                <span class="showText">aaaaa</span>
                            </div>
                        </div>
                        <div id="tr">
                            <p class="content tel">전화번호</p>
                            <div id="border"> 
                                <span class="showText">aaaa-a-a-a-a-a-a</span>
                                <input type="button" class="btn telBtn"  id="opeanModal"  onclick="showModal()" value="변경">
                            </div> 
                            <script src="./js/modal.js"></script>   
                        </div> 
                        
                        <div id="tr">
                            <div id="fullborder">  
                                <p class="content pass">비밀번호</p>
                                <input type="button" class="btn passBtn" id="reOpeanModal" onclick="rePasswordModal()" value="재설정">
                            </div>       
                        </div>
                        
                        <div id="tr">
                            <p class="content ticket">사용권</p>
                            <div id="border">
                                <span class="showText"></span>
                                <input type="button" class="btn ticketBtn" value="결제내역">
                            </div>
                        </div>
                        <div id="tr">
                            <p class="content challenge">첼린지</p>
                            <div id="border">
                                <span class="showText"></span>
                                <input type="button" class="btn challBtn" value="참여내역">
                            </div> 
                        </div>
                        <div id="tr">
                            <div id="fullborder">
                                <p class="content point">보유포인트</p>
                                <span class="showText"></span>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <!--전화번호 변경 모달영역-->
            <div id="modalBg"></div> 
            <div id="modalPage">
                <div id="modalContent">
                    <p id="modalP">
                        00님의 회원정보 <span id="modalSpan">전화번호</span>를<br>
                        수정하기 위해 인증절차가 필요합니다.
                    </p>
                    <hr id="modalHr">
                    <input type="password" name="" id="modelPassword" class="first" placeholder="비밀번호를 입력해주세요">
                    <input type="button" id="modalCheckBtn" value="확인"><br>
                    <!--페스워드 확인이 되야 전화번호 변경가능-->
                    <input type="text" id="modalTel"  class="second" placeholder="변경할 전화번호를 입력해주세요">
                </div>
                <div id="modalBtn">
                    <input type="button" id="modalNoBtn" value="취소">
                    <input type="submit" name="" id="modalYesBtn" value="변경">
                </div>
            </div>
        <!--모달영역-->

        <!--비밀번호변경_모달영역1-->
        <div id="rePasswordModal">
            <div id="modalContent">
                <p id="modalP">
                    <span id="modalSpan">비밀번호</span>를 변경하려면 전화번호<br>
                    인증이 필요합니다.
                </p>
                <hr id="modalHr">
                <input type="text" name="" id="modelTel" class="first" placeholder="전화번호를 입력해주세요">
                <input type="button" id="modalCheckBtn" value="인증"><br>
                <!--인증번호 확인이 되야 전화번호 변경가능-->
                <input type="text" id="checkNum" class="second" placeholder="인증번호를 입력해주세요">
            </div>
            <div id="modalBtn">
                <input type="button" id="reModalNoBtn" value="취소">
                <input type="submit" name="" id="modalYesBtn" value="다음">
            </div>
        </div>
        <!--모달영역-->
        </body>
    
 </html>
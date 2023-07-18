<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=deevice-width, initial-scale=1,minimum-scale=1,maxmun-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../inside/css/snu_ticket.css">
        
        <link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
    body {font-family: 'SUITE', sans-serif;}
</style>
        <title>snu_timeTicket_page</title>
    </head>
        <body>
            <div id="body">
              <a href="../inside/snu_seat.html"><img src="../inside/img/beforBtn.png" alt=""></a>
             
              <div id="whiteWrap">
                <section>
                    <ul>
                        <li id="timeTicket">시간권</li>
                        <li id="commutationTicket"><a href="snu_commutationTicket .html">정기권</a></li>
                    </ul>
                    <hr>
                    <form>
                        <table>
                            <tr>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">3시간</p>
                                    <p class="p btn-text">6,000원</p>
                                </td>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">10시간</p>
                                    <p class="p btn-text">15,000원</p>
                                </td>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">30시간</p>
                                    <p class="p btn-text">45,000원</p>
                                </td>
                            </tr>
                            <tr>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">50시간</p>
                                    <p class="p btn-text">60,000원</p>
                                </td>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">100시간</p>
                                    <p class="p btn-text">110,000원</p>
                                </td>
                                <td class="radio-box">
                                    <input type="radio" name="radio-button" class="radio-input" value="">
                                    <p class="t btn-text">150시간</p>
                                    <p class="p btn-text">160,000원</p>
                                </td>
                            </tr>
                        </table>
                        <input id="nextBtn" type="submit" value="다음">
                    </form>
                </section>
            </div>
        <script src="../snu/jsp/radioBox.js"></script>
        
        </div>
        </body>
    
 </html>
function timeoutRedirect(limitTime, redirectUrl, timerElement = null, cipher = 2) {
    let time = limitTime;
    let timer = setInterval(function () {
        time--;
        if (timerElement !== null) timerElement.innerText = String(time).padStart(cipher, '0');
        if (time === 0) {
            clearInterval(timer);
            location.href = redirectUrl;
        }
    }, 1000);
}

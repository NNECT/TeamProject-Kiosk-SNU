class InsideTimeChecker {
    constructor(authorId, insideType, insideNumber, timeDescElement, timeElement, logoutUrl) {
        this.AUTHOR_ID = authorId;
        this.INSIDE_TYPE = insideType;
        this.INSIDE_NUMBER = insideNumber;
        this.LOGOUT_URL = logoutUrl;
        this.timeElement = timeElement;
        this.timer = setInterval(() => this.setRemainTime(), 20000);
        this.commutationTicket = false;

        this.setRemainTime();
        if (timeDescElement !== null && this.commutationTicket) {
            timeDescElement.innerHTML = "정기권 기한:";
            clearInterval(this.timer);
        }
    }

    setRemainTime() {
        const remainTime = this.remainTimeCheck();
        if (remainTime === null) {
            return "00시간 00분";
        }
        if (this.timeElement !== null) this.timeElement.innerHTML = remainTime;
        if (remainTime === "00시간 00분") {
            clearInterval(this.timer);
            location.href = this.LOGOUT_URL;
        }
    }

    remainTimeCheck() {
        let value = null;
        const self = this;
        $.ajax({
            type: "POST",
            url: "../ajax/remainTime",
            data: JSON.stringify({
                "authorId": this.AUTHOR_ID,
                "insideType": this.INSIDE_TYPE,
                "insideNumber": this.INSIDE_NUMBER
            }),
            dataType: "json",
            async: false,
            contentType: "application/json; charset=UTF-8",
            success: function (response) {
                if (response.result === "fail") {
                    alert("남은 시간을 불러오는데 실패했습니다.");
                    return;
                }
                if (response.commutationTicket === "true") {
                    self.commutationTicket = true;
                }
                value = response.remainTime;
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });

        return value;
    }
}
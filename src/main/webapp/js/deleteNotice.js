
// 시작시 각 버튼에 이벤트 리스너를 등록
window.addEventListener("DOMContentLoaded", (event) => {
    document.querySelectorAll(".deleteBtn").forEach(button => {
        button.addEventListener("click", (e) => {
            // a 태그 기본 링크 기능 중지
            e.preventDefault();

            //확인창
            if(confirm("정말 삭제하시겠습니까?")){
                // 확인 버튼을 눌렀을 때만 삭제 요청
                deleteNotification(e.target);
            }
        });
    });
});

function deleteNotification(button) {
    const notificationId = button.id;
    $.ajax({
        type: "POST",
        url: "../ajax/deleteNotification",
        data: JSON.stringify({
            "noticeId": notificationId
        }),
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function(response) {
            if(response.result==="success"){
                button.parentElement.remove();
            }
        },
        error: function(request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

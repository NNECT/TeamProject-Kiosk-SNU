function showModal(modalId) {
    const modalBg = document.querySelector(".modalBg");
    const modal = document.getElementById(modalId);

    // 모달창 열기
    modal.style.display = "block";
    modalBg.style.display = "block";

    // 모달창 닫기
    const closeModalBtn = modal.querySelector(".modalNoBtn");
    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none";
        modalBg.style.display = "none";
    });
}





function showNextModal() {
    const currentModal = document.getElementById("rePasswordModal");
    const nextModal = document.getElementById("newPasswordModal");
    const modalBg = document.querySelector(".modalBg");

    // 현재 모달창 닫기
    currentModal.style.display = "none";
    modalBg.style.display = "none";

    // 다음 모달창 열기
    nextModal.style.display = "block";
    modalBg.style.display = "block";

    // 다음 모달창 닫기 버튼 이벤트 설정
    const closeModalBtn = nextModal.querySelector(".modalNoBtn");
    closeModalBtn.addEventListener("click", () => {
        nextModal.style.display = "none";
        modalBg.style.display = "none";
    });
}






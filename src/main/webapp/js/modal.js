function showModal(){
    const modalBg =document.getElementById("modalBg");
    const modal = document.getElementById("modalPage");
    const openModalBtn = document.getElementById("opeanModal");
    var closeModalBtn = document.getElementById("modalNoBtn");

    // 모달창 열기
    openModalBtn.addEventListener("click", () => {
    modal.style.display = "block";
    modalBg.style.display="block"
    });

    // 모달창 닫기
    closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
    modalBg.style.display="none"
    });
}

function rePasswordModal(){
    let modalBg =document.getElementById("modalBg");
    let modal = document.getElementById("rePasswordModal");
    let openModalBtn = document.getElementById("reOpeanModal");
    var closeModalBtn = document.getElementById("reModalNoBtn");

    // 모달창 열기
    openModalBtn.addEventListener("click", () => {
    modal.style.display = "block";
    modalBg.style.display="block"
    });

    // 모달창 닫기
    closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
    modalBg.style.display="none"
    });
}





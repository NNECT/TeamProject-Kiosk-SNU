window.addEventListener('DOMContentLoaded', (event) => {
    const defaultBackgroundColor = "#fff";
    const defaultTextColor = "#285FF5";
    const selectedBackgroundColor = "#285FF5";
    const selectedTextColor = "#fff";

    const radioBoxes = document.querySelectorAll('.radio-box');
    radioBoxes.forEach(radioBox => {
        radioBox.addEventListener('click', () => {
            // Find and check the radio input
            const radioInput = radioBox.querySelector('.radio-input');
            radioInput.checked = true;
            radioInput.dispatchEvent(new Event('change'));
        });
    });

    const radioInputs = document.querySelectorAll('.radio-input');
    radioInputs.forEach(radioInput => {
        radioInput.addEventListener('change', (event) => {
            // All boxes back to normal
            document.querySelectorAll('.radio-box').forEach(box => {
                box.style.backgroundColor = defaultBackgroundColor;
                box.querySelectorAll('.btn-text').forEach(text => {
                    text.style.color = defaultTextColor;
                });
            });
            // Selected box to blue and text to white
            if (event.target.checked) {
                event.target.parentElement.style.backgroundColor = selectedBackgroundColor;
                event.target.parentElement.querySelectorAll('.btn-text').forEach(text => {
                    text.style.color = selectedTextColor;
                });
            }
        });
    });
});

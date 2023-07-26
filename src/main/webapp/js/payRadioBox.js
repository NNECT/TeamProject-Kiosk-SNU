window.addEventListener('DOMContentLoaded', (event) => {
    const defaultBackgroundColor = "#fff";
    const selectedBackgroundColor = "#EDF2FF";

    const radioBoxes = document.querySelectorAll('.card');
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
            document.querySelectorAll('.card').forEach(box => {
                box.style.backgroundColor = defaultBackgroundColor;
                
            });
            // Selected box to blue and text to white
            if (event.target.checked) {
                event.target.parentElement.style.backgroundColor = selectedBackgroundColor;
                
            }
        });
    });
});


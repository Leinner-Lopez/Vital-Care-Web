const model = document.getElementById("Model");
const modelText = document.getElementById("model-text")
const cancelConfirmButton = document.getElementById("cancelConfirmButton");
const acceptConfirmButton = document.getElementById("acceptConfirmButton");
const fondoDialogo = document.getElementById("fondoDialogo");

function verificarFechaCita(link) {
    const fechaCita = link.getAttribute("data-fechaCita");
    const linkHref = link.getAttribute("data-href");
    const fechaActual = new Date();
    const fechaCitaMedica = new Date(fechaCita);
    if (fechaCitaMedica < fechaActual) {
        fondoDialogo.style.display = 'block';
        model.showModal();
        modelText.textContent = 'No puedes reprogramar esta cita medica';
        cancelConfirmButton.style.display = 'none';
        acceptConfirmButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
        };
        return false;
    }
    else {
        fondoDialogo.style.display = 'block';
        model.showModal();
        cancelConfirmButton.style.display = 'inline';
        modelText.textContent = '¿Estas seguro de reprogramar esta cita medica?';
        cancelConfirmButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
        };
        acceptConfirmButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
            if (linkHref) {
                window.location.href = linkHref;
            }
        };
        return false;
    }
}
function eliminarCita(link) {
    const linkHref = link.getAttribute("data-href");
    fondoDialogo.style.display = 'block';
    model.showModal();
    cancelConfirmButton.style.display = 'inline';
    modelText.textContent = '¿Quieres Eliminar esta cita medica?';
    acceptConfirmButton.onclick = function () {
        fondoDialogo.style.display = 'none';
        model.close();
        if (linkHref) {
            window.location.href = linkHref;
        }
    }
    cancelConfirmButton.onclick = function () {
        fondoDialogo.style.display = 'none';
        model.close();
    }
    return false;
}
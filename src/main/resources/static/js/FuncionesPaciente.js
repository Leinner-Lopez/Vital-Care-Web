function functionPaciente(link) {
    const Editar = link.getAttribute("data-href-update");
    const Eliminar = link.getAttribute("data-href-delete");
    const confirmButton = document.getElementById("acceptConfirmButton");
    const cancelButton = document.getElementById("cancelConfirmButton");
    const model = document.getElementById("Model");
    const fondoDialogo = document.getElementById("fondoDialogo");
    const texto = document.getElementById("model-text");
    if (Editar) {
        fondoDialogo.style.display = 'block';
        texto.textContent = '¿Estás seguro de Editar este Paciente?';
        model.showModal();
        confirmButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
            window.location.href = Editar;
        };
        cancelButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
        };
    } else {
        fondoDialogo.style.display = 'block';
        texto.textContent = '¿Estás seguro de Eliminar este Paciente?';
        model.showModal();
        confirmButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
            window.location.href = Eliminar;
        };
        cancelButton.onclick = function () {
            fondoDialogo.style.display = 'none';
            model.close();
        };
    }
    return false;
}
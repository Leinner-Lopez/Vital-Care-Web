function verificarDisponibilidad(link) {
    const disponibilidadFinal = link.getAttribute(
        "data-disponibilidad-final"
    );
    const fechaActual = new Date();
    const dialog = document.getElementById("expiredDialog");
    const fondoDialog = document.getElementById("fondoDialogo");
    const acceptButton = document.getElementById("acceptButton");
    const fechaDisponibilidad = new Date(disponibilidadFinal);
    if (fechaDisponibilidad < fechaActual) {
        fondoDialog.style.display='block';
        dialog.showModal();
        acceptButton.onclick = function(){
            fondoDialog.style.display='none';
            dialog.close();
        };
        return false;
    }
    return true;
}
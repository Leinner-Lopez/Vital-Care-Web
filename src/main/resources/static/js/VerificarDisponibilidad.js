function verificarDisponibilidad(link) {
    const disponibilidadFinal = link.getAttribute(
        "data-disponibilidad-final"
    );
    const fechaActual = new Date();
    const fechaDisponibilidad = new Date(disponibilidadFinal);
    if (fechaDisponibilidad < fechaActual) {
        alert("La disponibilidad del médico ha expirado.");
        return false;
    }
    return true;
}
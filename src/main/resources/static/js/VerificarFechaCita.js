function verificarFechaCita(link) {
    const confirmar = confirm("¿Estas seguro(a) de que deseas reprogramar esta cita medica?")
    if (confirmar) {
        const fechaCita = link.getAttribute(
            "data-fechaCita"
        );
        const fechaActual = new Date();
        const fechaCitaMedica = new Date(fechaCita);
        if (fechaCitaMedica < fechaActual) {
            alert("No puedes reprogramar esta cita medica.");
            return false;
        }
        return true;
    }
    return false;
}
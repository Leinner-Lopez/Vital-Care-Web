// Obtiene la fecha y hora actual en formato compatible con datetime-local
const now = new Date();
const offset = now.getTimezoneOffset();
const localDate = new Date(now.getTime() - offset * 60 * 1000);
const minDateTime = localDate.toISOString().slice(0, 16);

document.getElementById("FechaInicial").min = minDateTime;
document.getElementById("FechaFinal").min = minDateTime;

// Establece la fecha mínima para fechaFinal
document
    .querySelector("form")
    .addEventListener("submit", function (event) {
        var fechaInicial = new Date(document.getElementById("FechaInicial").value);
        var fechaFinal = new Date(document.getElementById("FechaFinal").value);
        if (fechaFinal <= fechaInicial) {
            event.preventDefault();
            alert("La fecha final debe ser posterior a la fecha inicial.");
        }
    });
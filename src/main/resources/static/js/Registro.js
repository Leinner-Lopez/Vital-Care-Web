const confirmButton = document.getElementById("acceptConfirmButton");
const model = document.getElementById("Model");
const fondoDialogo = document.getElementById("fondoDialogo");
document.querySelector("form").addEventListener("submit", function (event) {
    var password = document.getElementById("Contraseña").value;
    var cpassword = document.getElementById("CContraseña").value;
    if (password !== cpassword) {
        fondoDialogo.style.display = 'block';
        model.showModal();
        confirmButton.onclick = function(){
            fondoDialogo.style.display = 'none';
            model.close();
        };
        event.preventDefault();
    }
});
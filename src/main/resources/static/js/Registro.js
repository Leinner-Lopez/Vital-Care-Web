document.querySelector("form").addEventListener("submit", function (event) {
    var password = document.getElementById("Contraseña").value;
    var cpassword = document.getElementById("CContraseña").value;
    if (password !== cpassword){
        alert("Las contraseñas no coinciden");
        event.preventDefault();
    }
});
// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});


async function iniciarSesion() { //Esta funcion se llamas desde el HTML --- > onClick(iniciarSesion);

    let datos = {};

    //Obtenemos los datos de las cajas de texto con un id--> document.getElementById('txtEmail').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },

        body: JSON.stringify(datos)

      });
    const respuesta = await request.text(); // ---> En espera de una respuesta

    if(respuesta != 'FAIL'){

    /*
    En caso de que respuesta sea true ---> redireccionamos ---> window.location.href = 'usuarios.html';
        Guardamos en el localStorage el token --> localStorage.token = respuesta;
        Guardamos en el localStorage el email --> localStorage.email = datos.email;
    */

        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'; // --> Redireccion a usuarios.html

    } else {
        alert("Usuario o contraseña incorrecto. intente de nuevo!"); //Alerta que indica si la contrraseña es correcta o no!
    }
}





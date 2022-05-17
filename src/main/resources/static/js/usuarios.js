// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarUsuarios();
    $('#usuarios').DataTable();

    actualizarEmailDelUsuario();

});


//outerHTML funciona para que se cambie el HTML a la hora de cargar

function actualizarEmailDelUsuario(){
    document.getElementById('txt-email-usuarioid').outerHTML = localStorage.email;
}



async function cargarUsuarios(){

  /*Peticiones AJAX

  Estas son peticiones con ajax por metodo GET a "api/usuarios"

  */

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token  //Se pasa el token del local Storage
        }

      });
      const usuarios = await request.json();
      let listadoHTML = "";

    for ( let usuario of usuarios){

      let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>';
      let telefonoTexto = usuario.telefono == null ? '-': usuario.telefono;

      let usuarioHTML = ' <tr><td>'+ usuario.id +'</td><td>'
                        + usuario.nombre +' '+ usuario.apellido +' </td><td>'
                        + usuario.email +'</td> <td>'
                        + telefonoTexto + '</td> <td>' + botonEliminar + '</td> </tr>';

        listadoHTML += usuarioHTML;
    }
      document.querySelector('#usuarios tbody').outerHTML = listadoHTML;

}

async function eliminarUsuario(id){

    if(!confirm ('¿Deseas eliminar el usuario ?')){
        return;
    }

    const request = await fetch('api/usuarios/' + id, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': localStorage.token  //Se pasa el token del local Storage
            }
          });


   location.reload();
}


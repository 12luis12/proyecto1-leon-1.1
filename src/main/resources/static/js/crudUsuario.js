// Call the dataTables jQuery plugin
$(document).ready(function() {
  
  $('#tableUsuario').DataTable();
    cargarUsuarios();
  
});



async function cargarUsuarios() {
  const request = await fetch('api/usuarios/listaUsuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();


  let listadoHtml = '';
  for (let usuario of usuarios) {
     let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.idUsuario + ')" class="btn btn-danger btn-circle btn-sm">eliminar<i class="fas fa-trash"></i></a>';
     let botonEditar = '<a href="#" onclick="abrirModalActualizar(' + usuario.idUsuario + ')" class="btn btn-info btn-circle btn-sm">Actualizar<i class="fas fa-trash"></i></a>';

  
    let usuarioHtml = '<tr><td>'+usuario.idUsuario+'</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
                    + usuario.correo+'</td><td>'+usuario.login
                    + '</td><td>'+usuario.password
                    + '</td><td>' + botonEliminar + '</td><td>' + botonEditar + '</td></tr>';
    listadoHtml += usuarioHtml;
  }

document.querySelector('#tableUsuario  tbody').outerHTML = listadoHtml;

}



async function eliminarUsuario(id) {

  if (!confirm('¿Desea eliminar este usuario?')) {
    return;
  }

 const request = await fetch('api/usuarios/eliminar/'+ id, {
    method: 'DELETE',
    headers: getHeaders()
  });

    if (request.ok) {  // Verifica si la solicitud fue exitosa
    location.reload();  // Recarga la página si se eliminó con éxito
  } else {
    console.error('Error al eliminar el usuario');
    // Puedes mostrar un mensaje de error al usuario aquí si lo deseas
  }
}


async function abrirModalActualizar(id) {
  // Fetch los datos del usuario
  const response = await fetch('api/usuarios/'+ id, {
    method: 'GET',
    headers: getHeaders()
  });

  if (response.ok) {
    const usuario = await response.json();

    // Llena el formulario con los datos del usuario
    document.getElementById('id_ID').value = usuario.idUsuario;
    document.getElementById('id_act_nombre').value = usuario.nombre;
    document.getElementById('id_act_apellido').value = usuario.apellido;
    document.getElementById('id_act_correo').value = usuario.correo;
    document.getElementById('id_act_login').value = usuario.login;
   // document.getElementById('id_act_password').value = usuario.password;

    // Muestra el modal
    $('#idModalActualizaUsuario').modal('show');
  } else {
    console.error('Error al obtener los datos del usuario:', response.statusText);
    alert('Error al obtener los datos del usuario.');
  }
}


async function actualizarUsuario() {
  // Obtén el ID del usuario del campo oculto
  const id = document.getElementById('id_ID').value;

  // Recoge los datos del formulario
  let usuario = {
	idUsuario:id,
    nombre: document.getElementById('id_act_nombre').value,
    apellido: document.getElementById('id_act_apellido').value,
    correo: document.getElementById('id_act_correo').value,
    login: document.getElementById('id_act_login').value,
   
  };


 const password = document.getElementById('id_act_password').value;
  if (password) {
    usuario.password = password;
  }


  try {
    // Realiza la solicitud PUT para actualizar el usuario
    const request = await fetch('api/usuarios/actualizaUsuario/', {
      method: 'PUT',
      headers: getHeaders(),
      body: JSON.stringify(usuario)
    });

    if (request.ok) {
      // Recarga la página o actualiza la tabla después de una actualización exitosa
      location.reload();
    } else {
      // Maneja errores en la actualización
      console.error('Error al actualizar el usuario:', request.statusText);
      alert('Error al actualizar el usuario. Intenta de nuevo.');
    }
  } catch (error) {
    // Maneja errores de la red o problemas inesperados
    console.error('Error de red:', error);
    alert('Error de red. Intenta de nuevo.');
  }
}


function registrar() {
	$('#idModalRegistraUsuario').modal("show")
}

async function registrarUsuario() {
  // Obtén el ID del usuario del campo oculto
   let RepetirPassword=document.getElementById('id_reg_repetir_password').value;
   let pass=document.getElementById('id_reg_password').value;
  // Recoge los datos del formulario
  if(RepetirPassword!==pass){
	alert('la constraseña que pusiste es diferente ');
	return;
  }
  let usuario = {
      // Incluye el ID del usuario en el objeto
    nombre: document.getElementById('id_reg_nombre').value,
    apellido: document.getElementById('id_reg_apellido').value,
    correo: document.getElementById('id_reg_correo').value,
    login: document.getElementById('id_reg_login').value,
    password:pass,
   
  };

  try {
    // Realiza la solicitud PUT para actualizar el usuario
    const response = await fetch('api/usuarios/registrarUsuarios', {
      method: 'POST',
      headers: getHeaders(),
      body: JSON.stringify(usuario)
    });

    if (response.ok) {
      // Recarga la página o actualiza la tabla después de una actualización exitosa
      location.reload();
    } else {
      // Maneja errores en la actualización
      console.error('Error al actualizar el usuario:', response.statusText);
      alert('Error al actualizar el usuario. Intenta de nuevo.');
    }
  } catch (error) {
    // Maneja errores de la red o problemas inesperados
    console.error('Error de red:', error);
    alert('Error de red. Intenta de nuevo.');
  }
}




function getHeaders() {
	 const token = localStorage.getItem('token');
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    //'Authorization':localStorage.token
    'Authorization': token ? `Bearer ${token}` : '' 
  };
}



//verificar si ya existe asigando precios al origen y destino seleccionado--------------------------------------------------

document.addEventListener('DOMContentLoaded', function () {
    const idUbigeoorigen = document.getElementById('idUbigeoorigen');
    const idUbigeodestino = document.getElementById('idUbigeodestino');
    const btnRegistrar = document.getElementById('idRegistrarPrecio');

    idUbigeoorigen.addEventListener('change', verificarPrecios1);
    idUbigeodestino.addEventListener('change', verificarPrecios1);

    function verificarPrecios1() {
        const valorUbigeoorigen = idUbigeoorigen.value;
        const valorUbigeodestino = idUbigeodestino.value;

      

        // Verificar que ambos selects tengan valores seleccionados
        if (valorUbigeoorigen && valorUbigeodestino) {
            // Asegúrate de que la URL está correctamente construida
            const url = `/api/boletas/verificarIdUbigeoorigenIdUbigeodestino/${valorUbigeoorigen}-${valorUbigeodestino}`;
            console.log("Request URL:", url); // Imprime la URL para verificar

            fetch(url)
                .then(response => {
                    console.log("Response Status:", response.status); // Imprime el código de estado
                    return response.json();
                })
                .then(data => {
                    console.log("Respuesta del servidor:", data); // Imprime la respuesta del servidor

                    // Verificar la respuesta del servidor
                    if (data === true) {
                        btnRegistrar.disabled = true;
                         alert("YAAAAAAAAAAAAAAAAAA Existe precio asiganado en esta ruta SI O SI TIENES QUE SELECCIONAR UNA NUEVA RUTA ");
                    } else {
                        btnRegistrar.disabled = false;
                       
                    }
                })
                .catch(error => console.error('Error:', error));
        } else {
            btnRegistrar.disabled = true;
        }
    }
});










// Call the dataTables jQuery plugin
$(document).ready(function() {
  
  $('#tablePrecio').DataTable();
    cargarPrecio();
  
});



async function cargarPrecio() {
  const request = await fetch('servicio/precio/listaPrecios', {
    method: 'GET',
    headers: getHeaders()
  });
  const precios = await request.json();


  let listadoHtml = '';
  for (let precio of precios) {
     let botonEliminar = '<a href="#" onclick="eliminarPrecio(' + precio.idPrecio + ')" class="btn btn-danger btn-circle btn-sm">ELIMINAR<i class="fas fa-trash"></i></a>';
     let botonEditar = '<a href="#" onclick="abrirModalActualizarPrecio(' + precio.idPrecio + ')" class="btn btn-info btn-circle btn-sm">ACTUALIZAR<i class="fas fa-trash"></i></a>';

  
    let usuarioHtml = '<tr><td>'+precio.idPrecio+ '</td><td><span style="color: red;">'+precio.ubigeoorigen.departamento+'</span><span style="color: green;">-'+precio.ubigeoorigen.provincia +'</span>  <span style="color: orange;">-'+precio.ubigeoorigen.municipio +'</span>  <span style="color: blue;">-'+precio.ubigeoorigen.almacen +'</td><td><span style="color: red;">'
    +precio.ubigeodestino.departamento+'</span><span style="color: green;">-'+precio.ubigeodestino.provincia +'</span>  <span style="color: orange;">-'+precio.ubigeodestino.municipio +'</span>  <span style="color: blue;">-'+precio.ubigeodestino.almacen +'</td><td> ' 
    + precio.normalMinima + '</td><td>'
    + precio.normalM3+'</td><td>'+precio.normalKgAdicional
    + '</td><td>'+precio.tiempoNormal+ '</td><td>'+precio.expresMinimo+ '</td><td>'+precio.expresM3+ '</td><td>'+precio.expresKgAdicional+ '</td><td>'+precio.tiempoExpres+ '</td><td>' + botonEliminar + '</td><td>' + botonEditar + '</td></tr>';
    listadoHtml += usuarioHtml;
  }

document.querySelector('#tablePrecio  tbody').outerHTML = listadoHtml;

}










function bootonregistrarP() {
	$('#idModalRegistraPrecio').modal("show")
}

async function crudRegistraPrecio() {
    try {
        // Obtener los valores seleccionados de los `select`
        const normalMinima = document.getElementById("id_reg_normalMinima").value;
        const normalM3 = document.getElementById("id_reg_normalM3").value;
        const normalKgAdicional = document.getElementById("id_reg_normalKgAdicional").value;
        const tiempoNormal = document.getElementById("id_reg_tiempoNormal").value;
        
        const expresMinimo = document.getElementById("id_reg_expresMinimo").value;
        const expresM3 = document.getElementById("id_reg_expresM3").value;
        const expresKgAdicional = document.getElementById("id_reg_expresKgAdicional").value;
        const tiempoExpres = document.getElementById("id_reg_tiempoExpres").value;
       
        const idUbigeoorigen = document.getElementById("idUbigeoorigen").value;
        const idUbigeodestino = document.getElementById("idUbigeodestino").value;

        // Validar los campos antes de enviar
        if (!validarCampos([normalMinima, normalM3, normalKgAdicional, tiempoNormal, expresMinimo, expresM3, expresKgAdicional, tiempoExpres])) {
            document.getElementById("resultado3").innerText = "Todos los campos deben ser numeros validos y no deben estar vacios.";
            return;
        }

        // Verificar si ambos `idUbigeoorigen` e `idUbigeodestino` tienen un valor seleccionado
        if (!idUbigeoorigen || !idUbigeodestino) {
            throw new Error('Debe seleccionar tanto origen como destino');
        }

        // Crear el JSON para enviar al API
        const data = {
            normalMinima: parseFloat(normalMinima),
            normalM3: parseFloat(normalM3),
            normalKgAdicional: parseFloat(normalKgAdicional),
            tiempoNormal: parseFloat(tiempoNormal),
            expresMinimo: parseFloat(expresMinimo),
            expresM3: parseFloat(expresM3),
            expresKgAdicional: parseFloat(expresKgAdicional),
            tiempoExpres: parseFloat(tiempoExpres),
            ubigeoorigen: {
                idUbigeoorigen: parseInt(idUbigeoorigen)
            },
            ubigeodestino: {
                idUbigeodestino: parseInt(idUbigeodestino)
            }
        };

        // Realizar la petición PUT a la API
        const response = await fetch('/api/boletas/resgistraActualizaPrecio', {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(data)
        });

        // Verificar si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al actualizar el Material');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Material actualizada:', responseData);

        // Mostrar un mensaje de éxito
        document.getElementById("resultado3").innerText = "Material actualizada correctamente";

        // Recargar la página
        location.reload();
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        document.getElementById("resultado3").innerText = "Hubo un problema al actualizar el Material";
    }
}

// Función para validar que los campos contengan números y no estén vacíos
function validarCampos(campos) {
    for (let campo of campos) {
        // Comprobar si el campo está vacío o no es un número
        if (!campo || isNaN(campo)) {
            return false; // Si algún campo no es válido, retornar false
        }
    }
    return true; // Si todos los campos son válidos, retornar true
}

// Función para obtener los encabezados con el token de autorización
function getHeaders() {
    const token = localStorage.getItem('token');
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : '' 
    };
}






$(document).ready(function() {
    // Asegúrate de que btnRegistrar esté definido y accesible
    const btnRegistrar = document.getElementById('idRegistrarPrecio'); // O usa jQuery: $('#idBtnRegistrar2')[0]

    // Función para limpiar el formulario del modal
    function limpiarFormulario() {
        $('#crudFormRegistrarPrecios')[0].reset(); // Suponiendo que el formulario tiene el ID 'formRegistrarPrecios'
    }

    // Función para validar que los campos no estén vacíos y contengan números
    function validarFormulario() {
        let isValid = true;
        let errorMessage = 'DEBE COMPLETAR TODOS LOS CAMPOS CON NUMEROS SI OSI TIENE QUE SER NUMEROS.';

        // Obtener los valores de los campos a validar
        const normalMinima = $('#id_reg_normalMinima').val();
        const normalM3 = $('#id_reg_normalM3').val();
        const normalKgAdicional = $('#id_reg_normalKgAdicional').val();
        const tiempoNormal = $('#id_reg_tiempoNormal').val();

        const expresMinimo = $('#id_reg_expresMinimo').val();
        const expresM3 = $('#id_reg_expresM3').val();
        const expresKgAdicional = $('#id_reg_expresKgAdicional').val();
        const tiempoExpres = $('#id_reg_tiempoExpres').val();

        // Verificar si los campos están vacíos o no contienen números válidos
        if (!normalMinima || isNaN(normalMinima) || 
            !normalM3 || isNaN(normalM3) || 
            !normalKgAdicional || isNaN(normalKgAdicional) || 
            !tiempoNormal || isNaN(tiempoNormal) || 
            !expresMinimo || isNaN(expresMinimo) || 
            !expresM3 || isNaN(expresM3) || 
            !expresKgAdicional || isNaN(expresKgAdicional) || 
            !tiempoExpres || isNaN(tiempoExpres)) {
            
            isValid = false;
        }

        if (!isValid) {
            // Mostrar la ventana de alerta con el mensaje de error
            alert(errorMessage);
        }

        return isValid;
    }

    // Cerrar el modal con el botón 'botonPrecio'
    $('#idCancelarRegistrarPrecio').click(function() {
        $('#idModalRegistraPrecio').modal('hide');
        limpiarFormulario(); // Limpia el formulario al cerrar el modal
    });

    // Cerrar el modal, habilitar el botón y limpiar el formulario con el botón 'guardarPrecio'
    $('#idRegistrarPrecio').click(function() {
        if (validarFormulario()) {
            $('#idModalRegistraPrecio').modal('hide'); // Cerrar solo si la validación es correcta
            $(btnRegistrar).prop('disabled', false); // Habilita el botón
            limpiarFormulario(); // Limpia el formulario al guardar
        }
    });
});




async function eliminarPrecio(id) {
  // Mostrar un cuadro de confirmación
  if (!confirm('Realmente Desea eliminar el Registro numero '+id)) {
    return;  // Si el usuario cancela, no se ejecuta nada
  }

  try {
    // Realizar la solicitud DELETE al servidor
    const response = await fetch('servicio/precio/eliminaPrecio/' + id, {
      method: 'DELETE',
      headers: getHeaders()  // Asegúrate de definir esta función que retorna los headers necesarios
    });

    // Verificar si la solicitud fue exitosa
    if (response.ok) {
      alert('El Registro  numero '+id +'fue eliminado correctamente ');
      location.reload();  // Recargar la página si la eliminación es exitosa
    } else {
      throw new Error('No se pudo eliminar el registro '+id);
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Hubo un problema al eliminar el precio');
  }
}





//------------------------actualizar----------------------------------------------------------------------


async function abrirModalActualizarPrecio(id) {
  // Fetch los datos del precio
  const response = await fetch('servicio/precio/' + id, {
    method: 'GET',
    headers: getHeaders()
  });

  if (response.ok) {
    const precio = await response.json();

    // Llena el formulario con los datos del precio
    document.getElementById('idPrecio').value = precio.idPrecio;
    document.getElementById('id_act_normalMinima').value = precio.normalMinima;
    document.getElementById('id_act_normalM3').value = precio.normalM3;
    document.getElementById('id_act_normalKgAdicional').value = precio.normalKgAdicional;
    document.getElementById('id_act_tiempoNormal').value = precio.tiempoNormal;
    document.getElementById('id_act_expresMinimo').value = precio.expresMinimo;
    document.getElementById('id_act_expresM3').value = precio.expresM3;
    document.getElementById('id_act_expresKgAdicional').value = precio.expresKgAdicional;
    document.getElementById('id_act_tiempoExpres').value = precio.tiempoExpres;
    
    document.getElementById('id_idUbigeoorigen').value = precio.ubigeoorigen.idUbigeoorigen;
    document.getElementById('id_idUbigeodestino').value = precio.ubigeodestino.idUbigeodestino;

    // Crear el HTML para ubigeoorigen
   let usuarioHtml = '<tr><td><strong>Departamento: </strong></td></tr>' +
                  '<tr><td><span style="color: red;">' + precio.ubigeoorigen.departamento + '</span></td></tr>' +
                  '<tr><td><strong>Provincia: </strong></td></tr>' +
                  '<tr><td><span style="color:  green;">' + precio.ubigeoorigen.provincia + '</span></td></tr>' +
                  '<tr><td><strong>Municipio:  </strong></td></tr>' +
                  '<tr><td><span style="color: orange;">' + precio.ubigeoorigen.municipio + '</span></td></tr>' +
                  '<tr><td><strong>Almacen:  </strong></td></tr>' +
                  '<tr><td><span style="color: blue;">' + precio.ubigeoorigen.almacen + '</span></td></tr>';

// Crear el HTML para ubigeodestino en líneas separadas
let usuarioHtml1 = '<tr><td><strong>Departamento: </strong></td></tr>' +
                   '<tr><td><span style="color: red;">' + precio.ubigeodestino.departamento + '</span></td></tr>' +
                   '<tr><td><strong>Provincia: </strong></td></tr>' +
                   '<tr><td><span style="color: green;">' + precio.ubigeodestino.provincia + '</span></td></tr>' +
                   '<tr><td><strong>Municipio: </strong></td></tr>' +
                   '<tr><td><span style="color: orange;">' + precio.ubigeodestino.municipio + '</span></td></tr>' +
                   '<tr><td><strong>Almacen: </strong></td></tr>' +
                   '<tr><td><span style="color: blue;">' + precio.ubigeodestino.almacen + '</span></td></tr>';

    // Verificar que los elementos existen antes de asignar HTML
    const descripcionUbigeoorigen = document.getElementById("descripcion_ubigeoorigen");
    const descripcionUbigeodestino = document.getElementById("descripcion_ubigeodestino");

    if (descripcionUbigeoorigen) {
      descripcionUbigeoorigen.innerHTML ='ORIGEN:  '+ usuarioHtml;
    } else {
      console.error('El elemento "descripcion_ubigeoorigen" no existe en el DOM.');
    }

    if (descripcionUbigeodestino) {
      descripcionUbigeodestino.innerHTML = 'DESTINO:  '+usuarioHtml1;
    } else {
      console.error('El elemento "descripcion_ubigeodestino" no existe en el DOM.');
    }

    // Muestra el modal
    $('#idModalActualizarPrecio').modal('show');
  } else {
    console.error('Error al obtener los datos del precio:', response.statusText);
    alert('Error al obtener los datos del precio.');
  }
}


async function actualizarPrecio() {
  // Obtén el ID del usuario del campo oculto
  const id = document.getElementById('idPrecio').value;
  
  const idubigeoorigen = document.getElementById('id_idUbigeoorigen').value;
  const idubigeodestino = document.getElementById('id_idUbigeodestino').value;

  // Recoge los datos del formulario
  let precio = {
	idPrecio:id,
	//idUbigeoorigen:idubigeoorigen,
	//idUbigeodestino:idubigeodestino,
    normalMinima: document.getElementById('id_act_normalMinima').value,
    normalM3: document.getElementById('id_act_normalM3').value,
    normalKgAdicional: document.getElementById('id_act_normalKgAdicional').value,
    tiempoNormal: document.getElementById('id_act_tiempoNormal').value,
    
    
     expresMinimo: document.getElementById('id_act_expresMinimo').value,
    expresM3: document.getElementById('id_act_expresM3').value,
    expresKgAdicional: document.getElementById('id_act_expresKgAdicional').value,
    tiempoExpres: document.getElementById('id_act_tiempoExpres').value,
    
    
    
      ubigeoorigen: {
                idUbigeoorigen: parseInt(idubigeoorigen)
            },
            ubigeodestino: {
                idUbigeodestino: parseInt(idubigeodestino)
            }
   
  };


 


  try {
    // Realiza la solicitud PUT para actualizar el usuario
    const request = await fetch('/api/boletas/resgistraActualizaPrecio', {
      method: 'POST',
      headers: getHeaders(),
      body: JSON.stringify(precio)
    });

    if (request.ok) {
		alert("El registro se actualizo correctamente");
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


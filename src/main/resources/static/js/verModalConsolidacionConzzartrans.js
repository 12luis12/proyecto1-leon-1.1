function mostrarModalConsolidacion(id){
	 $("#idModalConsolidacion").modal("show");
	
	cargarMaterialesDeCmm(id);
	 
}

	
	
async function cargarMaterialesDeCmm(idBoleta) {
	
	await cargarAcumulador(idBoleta);
  console.log("valor idBoleta:", idBoleta); // Usar console.log en lugar de alert para evitar interrupciones en la UI

  try {
    const [materiales, boletas] = await Promise.all([
      fetch(`/servicio/Material/listaMaterial/${idBoleta}`, {
        method: 'GET',
        headers: getHeaders()
      }).then(response => {
        if (!response.ok) {
          throw new Error('Error en listaMaterial');
        }
        return response.json();
      }),
      fetch(`/api/boletas/listaParaTablaGeneral/${idBoleta}`, {
        method: 'GET',
        headers: getHeaders()
      }).then(response => {
        if (!response.ok) {
          throw new Error('Error en listaParaTablaGeneral');
        }
        return response.json();
      })
    ]);

    // Limpiar el contenido del tbody antes de agregar nuevos datos
    let tbody = document.querySelector('#tableConsolidacion tbody');
    tbody.innerHTML = ''; // Esto elimina los registros antiguos

    let listadoHtml = '';
   
    // Si no hay materiales, mostramos un mensaje
  if (!materiales || Object.keys(materiales).length === 0) {
      listadoHtml = '<tr><td colspan="7" class="text-center">NO Existe Registrado  materiales para este C.M.M</td></tr>';
      console.log("No existen materiales para este registro.");
      

      descripcionGeneral(boletas);
      
    } else {
      let contador = 1;
      //cargarAcumulador(boletas);
      for (let material of materiales) {
        let botonEditar = '<button href="#" onclick="cargarMaterialEnLosCampos(' + material.idMaterial + ')" class="btn btn-warning btn-circle btn-xs" style="font-size: 8px; padding: 3px;">Actualizar<i class="fas fa-edit" style="font-size: 10px; margin-left: 5px;"></i></button>';
        let usuarioHtml = `<tr>
          <td>${contador}</td>
          <td>${material.descripcion}</td>
          <td>${parseFloat(material.peso).toFixed(2)}</td>
          <td>${parseFloat(material.volumen).toFixed(2)}</td>
          <td>${parseFloat(material.cantidad).toFixed(2)}</td>
          <td>${material.observaciones}</td>
          <td>${botonEditar}</td>
        </tr>`;
         
         descripcionGeneral(boletas);
         
        listadoHtml += usuarioHtml;
        contador++;
        
      }
      
    }

    // Actualiza el contenido del tbody
    tbody.innerHTML = listadoHtml;

    // Lógica adicional para boletas
    
       
   
    habilitarcampos(boletas);
   

  } catch (error) {
    console.error('Error en la función cargarMaterialesDeCmm:', error);
  }
}




//-----------------------------------------------------------------------------------------------


async function cargarAcumulador(idBoleta) {
    try {
        // Haciendo la petición GET a tu API
        const response = await fetch(`/api/boletas/buscarAcumulador/${idBoleta}`, {
            method: 'GET', 
            headers: getHeaders(),
        });

        // Verificando si la respuesta es correcta
        if (!response.ok) {
            console.error("Error en la respuesta del servidor:", response.status);
            return; // Salimos de la función en caso de error
        }

        // Asumimos que los datos llegan en formato JSON
        const data = await response.json();

   

        // Asignar el texto sin etiquetas HTML al campo de entrada (textarea)
      
        document.getElementById('act_1_acumulador').value = data.acumulador || 'No disponible';

    } catch (error) {
        console.error("Error en la solicitud:", error);
    }
}



//____________________----------------------------------------------------------------------------------

function habilitarcampos(boletas){
	  const actualizarMaterialBtn = document.getElementById('actualizarMaterialBtn');
	  const descripcionElement = document.getElementById('act_1_descripcion');
      const pesoElement = document.getElementById('act_1_peso');
      const cantidadElement = document.getElementById('act_1_cantidad');
      const volumenElement = document.getElementById('act_1_volumen');
     // const estadoElement = document.getElementById('act_1_estado');
      const observacionesElement = document.getElementById('act_1_observaciones');
       const btnActualizarConsolidcion = document.getElementById('btnActualizarConsolidcion');
     
 
	
	if(boletas.botonConsolidacion===2){
		  
		 
             	descripcionElement.disabled = false;
		        pesoElement.disabled = false;
		        cantidadElement.disabled = false;
		        volumenElement.disabled = false;
		        observacionesElement.disabled = false;
		        btnActualizarConsolidcion.disabled = false;
         
			  
	}else{
		
		descripcionElement.disabled = true;
        pesoElement.disabled = true;
        cantidadElement.disabled = true;
        volumenElement.disabled = true;
        observacionesElement.disabled = true;
		btnActualizarConsolidcion.disabled = true;
	}
	
	
}



 function descripcionGeneral(boletas) {
	
	  document.getElementById('labelflujo1').innerHTML = ` <span style="color: Gray; font-size: 16px;">Flujo</span> 
								  : <span style="color: blue; font-size: 16px;">${boletas.flujoConF || 'No disponible'}</span>
								`;
   document.getElementById('labelCmm1').innerHTML = ` <span style="color: Gray; font-size: 16px;">CMM</span> 
								  : <span style="color: blue; font-size: 16px;">${boletas.cmmConT || 'No disponible'}</span>
								`;
	  document.getElementById('Unidadsolicitante1').innerHTML = ` <span style="color: Gray; font-size: 12px;">Uni soli</span> 
								  : <span style="color: Black; font-size: 12px;">${boletas.unidadsolicitante.nombreCompleto || 'No disponible'}</span>
								`;
   document.getElementById('numguia1').innerHTML = ` <span style="color: Gray; font-size: 12px;">Guia</span> 
								  : <span style="color: red; font-size: 12px;">${boletas.numguiaConNg || 'No disponible'}</span>
								`;		
							
   document.getElementById('proforma1').innerHTML = ` <span style="color: Gray; font-size: 12px;">Proforma</span> 
								  : <span style="color: red; font-size: 12px;">${boletas.proformaConPF || 'No disponible'}</span>
								`;						
   document.getElementById('origen1').innerHTML = ` <span style="color: Gray; font-size: 11px;">Origen</span> 
								  : <span style="color: green; font-size: 11px;">${boletas.ubigeoorigen.almacenDepartamento || 'No disponible'}</span>
								`;
								
  document.getElementById('destino1').innerHTML = ` <span style="color: Gray; font-size: 11px;">destino</span> 
								  : <span style="color: green; font-size: 11px;">${boletas.ubigeodestino.almacenDepartamento || 'No disponible'}</span>
								`;
								
								
//document.getElementById('act_1_acumulador').value = boletas.acumulador || 'No disponible';






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
//------------------------------limpiar formulario---------------------------------

function limpiarFormulariModalConsolidacion() {
	  $("#act_1_idBoleta").val("");
	    $("#act_1_idMaterial").val("");
	    
    $("#act_1_descripcion").val("");
    $("#act_1_peso").val("");
   $("#act_1_cantidad").val("");
    $("#act_1_volumen").val("");
    $("#act_1_observaciones").val("")
    $('input[name="cobrarpor"]').prop('checked', false);
}

function limpiarFormulariModalConsolidacion2() {
	$("#act_1_idBoleta").val("");
	    $("#act_1_idMaterial").val("");
    $("#act_1_descripcion").val("");
    $("#act_1_peso").val("");
   $("#act_1_cantidad").val("");
    $("#act_1_volumen").val("");
    $("#act_1_observaciones").val("")
    $('input[name="cobrarpor"]').prop('checked', false);
      $('#idModalConsolidacion').modal('hide');
      location.reload();
}





async function cargarMaterialEnLosCampos(id) {
  try {
    //alert("el id en cargarrrrrrrrrrrr en guardar"+id);
    const response = await fetch('/servicio/Material/buscarMaterial2/' + id, {
      method: 'GET',
      headers: getHeaders()
    });

    if (response.ok) {
      const material = await response.json();

      // Verifica si los elementos existen antes de asignarles valores
      const idMaterialElement = document.getElementById('act_1_idMaterial');
      const idBoletaElement = document.getElementById('act_1_idBoleta');
      const descripcionElement = document.getElementById('act_1_descripcion');
      const pesoElement = document.getElementById('act_1_peso');
      const cantidadElement = document.getElementById('act_1_cantidad');
      const volumenElement = document.getElementById('act_1_volumen');
      const estadoElement = document.getElementById('act_1_estado');
      const observacionesElement = document.getElementById('act_1_observaciones');
      
      

      // Asigna valores a los campos solo si existen
      if (idMaterialElement) idMaterialElement.value = material.idMaterial || '';
      if (idBoletaElement) idBoletaElement.value = material.boleta.idBoleta || '';
      if (descripcionElement) descripcionElement.value = material.descripcion || '';
      if (pesoElement) pesoElement.value = material.peso || '';
      if (cantidadElement) cantidadElement.value = material.cantidad || '';
      if (volumenElement) volumenElement.value = material.volumen || '';
      if (estadoElement) estadoElement.value = material.estado || '';
      if (observacionesElement) observacionesElement.value = material.observaciones || '';

      // Configura los radio buttons
      $('input[name="cobrarpor"]').prop('checked', false); // Desmarca todos los radio buttons
      if (material.cobrarpor === 0) {
        $('#act_1_flexRadioDefault13').prop('checked', true);
      } else if (material.cobrarpor === 1) {
        $('#act_1_flexRadioDefault23').prop('checked', true);
      }
    
      // Muestra el modal (si es necesario)
      // $('#idModalMaterialActualiza1').modal('show');
    } else {
      console.error('Error al obtener los datos del material:', response.status, response.statusText);
      alert('Error al obtener los datos del material. Código: ' + response.status);
    }
  } catch (error) {
    console.error('Error en la solicitud:', error);
    alert('Error en la solicitud al servidor.');
  }
}

//----------------guardar la actualizacion----------------------------------------------------


//________________________________________________
async function guardarMaterialActualizadoEnModalConsolidacion() {
	
	 var validator = $('#id_form_consolidacion').data('bootstrapValidator');
    validator.validate();
    
     if (!validator.isValid()) {
    alert("Por favor, corrija los errores del formulario antes de guardar.");
    return;
  }
  // Obtén el ID del usuario del campo oculto
   var idMaterial = $("#act_1_idMaterial").val();
    var idBoleta = $("#act_1_idBoleta").val();
    var descripcion = $("#act_1_descripcion").val();
    var peso = $("#act_1_peso").val();
    var cantidad = $("#act_1_cantidad").val();
    var volumen = $("#act_1_volumen").val();
    var estado = $("#act_1_estado").val();
    var observaciones = $("#act_1_observaciones").val();
   var cobrarpor = $("input[name='cobrarpor']:checked").val();
  //alert("boletaaaaaaaaaaaaaaaaa en guardar"+idBoleta);
  
  // Recoge los datos del formulario
  let usuario = {
	 idMaterial: idMaterial,
         boleta: {
            idBoleta: idBoleta
        },
        descripcion: descripcion,
        peso: peso,
        cantidad: cantidad,
        volumen: volumen,
        estado: estado,
        observaciones: observaciones,
        cobrarpor: parseInt(cobrarpor, 10)
   
  };

  // Verifica si idBoleta es nulo o 0
  if (!idBoleta || idBoleta === "0") {
    alert("No se puede introducir nuevos materiales , seleccione uno material en la tabla de abajo y actualiza ");
    return;
  }


  try {
    // Realiza la solicitud PUT para actualizar el usuario
    const request = await fetch('/servicio/Material/registrarMaterial', {
      method: 'POST',
      headers: getHeaders(),
      body: JSON.stringify(usuario)
    });

    if (request.ok) {
           alert("Material actualizado correctamente.");
			
			await  copiaMaterialLosCamposAcumuladoryDescripcionABoleta1(idBoleta);
			await cargarMaterialesDeCmm(idBoleta);
			await actualizaElPrecioFinal2(idBoleta);
			//actualizaElPrecioFinal(idBoleta);
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
//---------------------------------------------------------------------------------------------
async function copiaMaterialLosCamposAcumuladoryDescripcionABoleta1(idBoleta) {
    try {
        const response = await fetch(`/api/boletas/copiaMaterialesAboleta1/${idBoleta}`, {
            method: 'GET', // Keep it as GET since it’s fetching data
            headers: getHeaders(),
        });

        // Check for successful response
        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        console.log("Ajax Success Response:", data);
        
        // You can return data or handle it as needed
        return data; // Return data if further processing is required
    } catch (error) {
        console.error("Error in the request:", error); // Use console.error for error logs
    }
}

//-----------------------------------------------------------------------------------------------
async function actualizaElPrecioFinal2(idBoleta) {
    try {
        const response = await fetch(`servicio/precio/calcularPrecioFinal/${idBoleta}`, {
            method: 'GET', // Cambiado a GET
            headers: getHeaders(),
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        console.log("Ajax Success Response:", data);
    } catch (error) {
        console.log("Error en la solicitud:", error);
    }
}





//__--------------------------------------------------------------------------------------------------
$(document).ready(function() {
	
	 $('#id_form_consolidacion').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
      descripcion: {
        selector: "#act_1_descripcion",
        validators: {
          notEmpty: {
            message: 'Descripcion del material es obligatorio'
          },
          stringLength: {
            min: 5,
            max: 255,
            message: 'El nombre es de 5 a 255 caracteres'
          }
        }
      },
      observaciones: {
        selector: "#act_1_observaciones",
        validators: {
          notEmpty: {
            message: 'Las observaciones son obligatorias'
          },
          stringLength: {
            min: 0,
            max: 255,
            message: 'Las observaciones son de 0 a 255 caracteres'
          }
        }
      },
      peso: {
        selector: "#act_1_peso",
        validators: {
          notEmpty: {
            message: 'El peso es obligatorio'
          },
          regexp: {
            regexp: /^[0-9]+([.][0-9]+)?$/,
            message: 'Ingresar peso con caracteres numéricos'
          }
        }
      },
      cantidad: {
        selector: "#act_1_cantidad",
        validators: {
          notEmpty: {
            message: 'La cantidad es obligatoria'
          },
          regexp: {
            regexp: /^[0-9]+([.][0-9]+)?$/,
            message: 'Ingresar cantidad con caracteres numéricos'
          }
        }
      },
      volumen: {
        selector: "#act_1_volumen",
        validators: {
          notEmpty: {
            message: 'El volumen es obligatorio'
          },
          regexp: {
            regexp: /^[0-9]+([.][0-9]+)?$/,
            message: 'Ingresar volumen con caracteres numéricos'
          }
        }
      }
     
    }
  });
	    
     }); 

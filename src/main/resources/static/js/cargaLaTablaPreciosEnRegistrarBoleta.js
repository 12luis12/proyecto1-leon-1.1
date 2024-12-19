// Call the dataTables jQuery plugin
$(document).ready(function() {
  
  $('#table_Material').DataTable();
    cargarMateriales();
  
});



async function cargarMateriales() {
	
  const request = await fetch('servicio/Material/consultarMaterialEstado', {
    method: 'GET',
    headers: getHeaders()
  });
  const materiales = await request.json();


  let listadoHtml = '';
  for (let material of materiales) {
     let botonEliminar = '<a href="#" onclick="eliminarMaterial(' + material.idMaterial + ')" class="btn btn-danger btn-circle btn-sm">eliminar<i class="fas fa-trash"></i></a>';
     let botonEditar = '<a href="#" onclick="actualizaMaterial1(' + material.idMaterial + ')" class="btn btn-info btn-circle btn-sm">Actualizar<i class="fas fa-trash"></i></a>';

  
    let materialHtml = '<tr><td>'+material.idMaterial+'</td><td>' + material.descripcion + '</td><td> ' + material.cantidad + '</td><td>'
                    + material.peso+'</td><td>'+material.volumen
                    + '</td><td>'+material.observaciones+ '</td><td>'+material.estado
                    + '</td><td>'+material.boleta.idBoleta+ '</td><td>'
                    + botonEliminar + '</td><td>' + botonEditar + '</td></tr>';
                    
                    descripcion(material);
                    
                    //en l parte de arriba
                    // document.getElementById('labelCmm').textContent = material.boleta.cmm || 'No disponible';
                  // document.getElementById('labelCmm').innerHTML = `El valor de <span style="color: blue;">CMM</span> es: <span style="color: green;">${material.boleta.cmm || 'No disponible'}</span>`;
                  

    listadoHtml += materialHtml;
    
    // valoridBoleta = '${precio.boleta.idBoleta}';
    valoridBoleta = material.boleta.idBoleta;
  }

document.querySelector('#table_Material  tbody').outerHTML = listadoHtml;

}

//parte de arriba--donde sale CMM Flujo y otro------------------------------------------------------------------------------------
 function descripcion(material) {
	  document.getElementById('labelflujo').innerHTML = ` <span style="color: Gray; font-size: 16px;">Flujo</span> 
								  : <span style="color: Black; font-size: 16px;">${material.boleta.flujo || 'No disponible'}</span>
								`;
   document.getElementById('labelCmm').innerHTML = ` <span style="color: Gray; font-size: 16px;">CMM</span> 
								  : <span style="color: Black; font-size: 16px;">${material.boleta.cmm || 'No disponible'}</span>
								`;
	  document.getElementById('Unidadsolicitante').innerHTML = ` <span style="color: Gray; font-size: 16px;">Flujo</span> 
								  : <span style="color: Black; font-size: 16px;">${material.boleta.unidadsolicitante.nombreCompleto || 'No disponible'}</span>
								`;
   document.getElementById('numguia').innerHTML = ` <span style="color: Gray; font-size: 16px;">CMM</span> 
								  : <span style="color: Black; font-size: 16px;">${material.boleta.numguia || 'No disponible'}</span>
								`;		
							
   document.getElementById('proforma').innerHTML = ` <span style="color: Gray; font-size: 16px;">Proforma</span> 
								  : <span style="color: Black; font-size: 16px;">${material.boleta.proforma || 'No disponible'}</span>
								`;						
   document.getElementById('origen').innerHTML = ` <span style="color: Gray; font-size: 16px;">Origen</span> 
								  : <span style="color: green; font-size: 16px;">${material.boleta.ubigeoorigen.almacenDepartamento || 'No disponible'}</span>
								`;
								
  document.getElementById('destino').innerHTML = ` <span style="color: Gray; font-size: 16px;">destino</span> 
								  : <span style="color: green; font-size: 16px;">${material.boleta.ubigeodestino.almacenDepartamento || 'No disponible'}</span>
								`;
  }
//parte de arriba--------------------------------------------------------------------------------------
//---------------------eliminar-----------------------------------------------------------------------------

async function eliminarMaterial(id) {
  // Mostrar un cuadro de confirmación
  if (!confirm('Realmente Desea eliminar el Registro numero '+id)) {
    return;  // Si el usuario cancela, no se ejecuta nada
  }

  try {
    // Realizar la solicitud DELETE al servidor
    const response = await fetch('servicio/Material/eliminaMaterial/' + id, {
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

//---------------------------actualiza material ----------------------------------------------------------------------------

function guardarActualizacionMaterial6() {
    var idMaterial = $("#act_idMaterial").val();
    var idBoleta = $("#act_idBoleta").val();
    var descripcion = $("#act_descripcion").val();
    var peso = $("#act_peso").val();
    var cantidad = $("#act_cantidad").val();
    var volumen = $("#act_volumen").val();
    var estado = $("#act_estado").val();
    var observaciones = $("#act_observaciones").val();
   var cobrarpor = $("input[name='cobrarpor']:checked").val();
    // var tipotransporte = $("input[name='tipotransporte']:checked").val();
     
    //  var cobrarpor = parseInt($("input[name='cobrarpor']:checked").val(), 10);
   
  

    $.ajax({
      
        type: "POST",
         url: "/guardarMaterialActualizado",
        data: JSON.stringify( {
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
        
        
    }),
        contentType: "application/json",
        success: function (response) {
			alert("Material actualizado correctamente.");
			copiaMaterialLosCamposAcumuladoryDescripcionABoleta(idBoleta);
            $("#idModalMaterialActualiza1").modal("hide");
           // $("#idModalListaMaterial").modal("hide");
           // muestraMaterialactualizado(idBoleta);
           // $("#idModalListaMaterial").modal("show");
          //  recargaTablaMaterial(idBoleta);
             location.reload();
            
        },
        error: function () {
            alert("Error al actualizar el material.");
        }
    });
}
    
async function actualizaMaterial1(id) {
  try {
    // Fetch los datos del material
    const response = await fetch('/servicio/Material/buscarMaterial2/' + id, {
      method: 'GET',
      headers: getHeaders()
    });

    if (response.ok) {
      const material = await response.json();

      // Llena el formulario con los datos del material
      document.getElementById('act_idMaterial').value = material.idMaterial || '';
      document.getElementById('act_idBoleta').value = material.boleta.idBoleta || '';
      document.getElementById('act_descripcion').value = material.descripcion || '';
      document.getElementById('act_peso').value = material.peso || '';
      document.getElementById('act_cantidad').value = material.cantidad || '';
      document.getElementById('act_volumen').value = material.volumen || '';
      document.getElementById('act_estado').value = material.estado || '';
      document.getElementById('act_observaciones').value = material.observaciones || '';
      
      // document.getElementById('labelCmm').textContent = material.boleta.cmm || 'No disponible';


      // Configura los radio buttons
     $('input[name="cobrarpor"]').prop('checked', false); // Desmarca todos los radio buttons
      if (material.cobrarpor === 0) {
        $('#act_flexRadioDefault11').prop('checked', true);
      } else if (material.cobrarpor === 1) {
        $('#act_flexRadioDefault22').prop('checked', true);
      }

      // Muestra el modal
      $('#idModalMaterialActualiza1').modal('show');
    } else {
      console.error('Error al obtener los datos del material:', response.status, response.statusText);
      alert('Error al obtener los datos del material. Código: ' + response.status);
    }
  } catch (error) {
    console.error('Error en la solicitud:', error);
    alert('Error en la solicitud al servidor.');
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




 $(document).ready(function() {
	
	 $('#id_form_actualiza_material').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
      descripcion: {
        selector: "#act_descripcion",
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
        selector: "#act_observaciones",
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
        selector: "#act_peso",
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
        selector: "#act_cantidad",
        validators: {
          notEmpty: {
            message: 'La cantidad es obligatoria'
          },
          regexp: {
            regexp: /^[0-9]+$/,
            message: 'Ingresar cantidad con caracteres numéricos'
          }
        }
      },
      volumen: {
        selector: "#act_volumen",
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
	  $('#idBtnLimpiaActuMaterial').on('click', function() {
		
          $('#idModalMaterialActualiza1').modal('hide');
           
    });  
     }); 

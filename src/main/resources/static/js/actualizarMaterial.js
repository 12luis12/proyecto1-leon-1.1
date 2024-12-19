
function guardarActualizacionMaterial6() {
    var idMaterial = $("#idMaterial").val();
    var idBoleta = $("#idBoleta").val();
    var descripcion = $("#descripcion").val();
    var peso = $("#peso").val();
    var cantidad = $("#cantidad").val();
    var volumen = $("#volumen").val();
    var estado = $("#estado").val();
    var observaciones = $("#observaciones").val();
    var cobrarpor = $("input[name='cobrarpor']:checked").val();
     var tipotransporte = $("input[name='tipotransporte']:checked").val();
  

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
        cobrarpor: cobrarpor,
        tipotransporte: tipotransporte
        
    }),
        contentType: "application/json",
        success: function (response) {
			alert("Material actualizado correctamente.");
			copiaMaterialLosCamposAcumuladoryDescripcionABoleta(idBoleta);
            $("#idModalMaterialActualiza1").modal("hide");
           // $("#idModalListaMaterial").modal("hide");
           // muestraMaterialactualizado(idBoleta);
           // $("#idModalListaMaterial").modal("show");
            recargaTablaMaterial(idBoleta);
            
        },
        error: function () {
            alert("Error al actualizar el material.");
        }
    });
}
    
function actualizaMaterial(id) {
    // Realizar la solicitud AJAX para obtener los detalles del material
    $.ajax({
        url: 'buscarMaterial', // Endpoint para obtener el material
        type: 'GET',
        data: { id: id },
        success: function (data) {
			//alert(data);
            // Rellenar el formulario del modal con los datos recibidos buscarMaterial1
            $('#idMaterial').val(data.idMaterial);
             $('#idBoleta').val(data.boleta.idBoleta);
             //----------------------------------
             //$('#botonConsolidacion').val(data.boleta.botonConsolidacion);
             //$('#idMaterial').val(data.id);
            $('#descripcion').val(data.descripcion);
            $('#peso').val(data.peso);
            $('#cantidad').val(data.cantidad);
            $('#volumen').val(data.volumen);
            $('#estado').val(data.estado);
            $('#observaciones').val(data.observaciones);
            // Mostrar el modal
            
             if (data.cobrarpor === 0) {
                $('#flexRadioDefault11').prop('checked', true);
            } else if (data.cobrarpor === 1) {
                $('#flexRadioDefault22').prop('checked', true);
            }
           // $('#idModalMaterialActualiza1').modal('show');
            
            
             if (data.tipotransporte === 0) {
                $('#flexRadioDefault1').prop('checked', true);
            } else if (data.tipotransporte === 1) {
                $('#flexRadioDefault2').prop('checked', true);
            }
          
             if (data.boleta.botonConsolidacion === 2 || data.boleta.botonConsolidacion === 0) {
				    disableFormFields();
				    $('#idModalMaterialActualiza1').modal('show');
				}else{
					  $('#idModalMaterialActualiza1').modal('show');
					   
				}
            
            
        },
        error: function (error) {
            alert('Error al obtener los detalles del material.');
        }
    });
}


function disableFormFields() {
    var formElements = document.querySelectorAll('#id_form_actualiza_material input, #id_form_actualiza_material textarea, #id_form_actualiza_material select');
    formElements.forEach(function(element) {
        element.disabled = true;
    });
     document.querySelector('#idModalMaterialActualiza1 button.btn-primary').disabled = true;
}





                    function recargaTablaMaterial(id) {
					
                        var var_material = id;
                        $("#id_table_lista_material tbody").empty();
                        //Se añade los clientes a la tabla
                        $.getJSON("consultaMaterial", { "filtro": var_material }, function (data) {
                            $.each(data, function (index, item) {
                                $('#id_table_lista_material').append("<tr><td>" + item.descripcion + "</td><td>" + item.peso + "</td><td>" + item.volumen + "</td><td>" + item.cantidad + "</td><td>" + item.observaciones + "</td><td><button type='button' onclick='actualizaMaterial(" + item.idMaterial +");' class='btn btn-default' aria-label='Left Align' > <img src='images/imagen4.png' width=\"auto\" height='auto' /></button></td><tr>");
                               // $('#id_table_lista_material').append("<tr><td>" + item.descripcion + "</td><td>" + item.peso + "</td><td>" + item.volumen + "</td><td>" + item.cantidad + "</td><td>" + item.observaciones + "</td><td><button type='button' onclick='f_elimina_seleccion(" + item.idMaterial +");' class='btn btn-default' aria-label='Left Align' > <img src='images/imagen4.png' width=\"auto\" height='auto' /></button></td><tr>");
                                
                            });
                        });
                        
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
        selector: "#descripcion",
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
        selector: "#observaciones",
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
        selector: "#peso",
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
        selector: "#cantidad",
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
        selector: "#volumen",
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

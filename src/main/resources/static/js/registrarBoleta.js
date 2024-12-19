// Inicializa el validador de Bootstrap en el formulario
$(document).ready(function() {
    $("#id_form_registra_boleta").bootstrapValidator({
        message: "Este valor no es válido",
        feedbackIcons: {
            valid: "glyphicon glyphicon-ok",
            invalid: "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh",
        },
        fields: {
           flujo: {
				    selector: "#flujo",
				    validators: {
				        notEmpty: {
				            message: "El flujo es obligatorio",
				        },
				       
				        regexp: {
				            regexp: /^[0-9]{6}$/,
				            message: "El flujo debe contener exactamente 6 digitos numericos",
				        },
				    },
				},
            idCliente: {
                selector: "#idCliente",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona un cliente",
                    },
                    greaterThan: {
                        value: 0,
                        inclusive: true,
                        message: "Selecciona al menos una opción",
                    },
                },
            },
            idUbigeodestino: {
                selector: "#idUbigeodestino",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona un destino",
                    },
                },
            },
            
             fechaOrigen: {
                        validators: {
                            notEmpty: {
                                message: 'La fecha tiene que ser seleccionada',
                            },
                        },
                    },
            idUbigeoorigen: {
                selector: "#idUbigeoorigen",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona un origen",
                    },
                    greaterThan: {
                        value: 0,
                        inclusive: true,
                        message: "Selecciona al menos una opción",
                    },
                },
            },
            idClientedestino: {
                selector: "#idClientedestino",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona un cliente de destino",
                    },
                    greaterThan: {
                        value: 0,
                        inclusive: true,
                        message: "Selecciona al menos una opción",
                    },
                },
            },
            idUnidadsolicitante: {
                selector: "#idUnidadsolicitante",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona una unidad solicitante",
                    },
                    greaterThan: {
                        value: 0,
                        inclusive: true,
                        message: "Selecciona al menos una opción",
                    },
                },
            },
            tipoTransporte: {
                selector: "#tipoTransporte",
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona el tipo de transporte",
                    },
                    greaterThan: {
                        value: 0,
                        inclusive: true,
                        message: "Selecciona al menos una opción",
                    },
                },
            },
            prioridadtransporte: {
                selector: 'input[name="prioridadtransporte"]',
                validators: {
                    notEmpty: {
                        message: "Por favor, selecciona una prioridad de transporte",
                    },
                },
            },
            cmm: {
                selector: "#cmm",
                validators: {
                    notEmpty: {
                        message: "El CMM es obligatorio",
                    },
                   
                     regexp: {
			            regexp: /^[0-9]{4}$/,
			            message: "El flujo debe contener exactamente 6 digitos numericos",
			        },
                },
            },
        },
    });
});

async function BtnRegistrarBoleta() {
    try {
        // Obtener el formulario
        const form = document.getElementById("id_form_registra_boleta");
        
        const idBtnRegistrarMaterial = document.getElementById('idBtnRegistrarMaterial');
        const idBtnRegistrarBoleta = document.getElementById('idBtnRegistrarBoleta');
        // Validar el formulario usando BootstrapValidator
        const validator = $(form).data('bootstrapValidator');
        validator.validate();
        if (!validator.isValid()) {
            document.getElementById("resultado3").innerText = "Por favor, complete el formulario correctamente.";
            return;
        }

        // Obtener los valores seleccionados de los `select`
        const idUnidadsolicitante = document.getElementById("idUnidadsolicitante").value;
        const idCliente = document.getElementById("idCliente").value;
        const idClientedestino = document.getElementById("idClientedestino").value;
        const idUsuario = document.getElementById("idUsuario").value;
        const idUsuario2 = document.getElementById("idUsuario2").value;
        const tipoTransporte = document.getElementById("tipoTransporte").value;
        const flujo = document.getElementById("flujo").value;
        const cmm = document.getElementById("cmm").value;
        const botonConsolidacion = document.getElementById("botonConsolidacion").value;
        const estadoFirma = document.getElementById("estadoFirma").value;
        const fechaOrigen = document.getElementById("fechaOrigen").value;

        // Validar radio button seleccionado
        const radios = document.getElementsByName("prioridadtransporte");
        let selectedTransporte = null;
        for (const radio of radios) {
            if (radio.checked) {
                selectedTransporte = radio.value;
                break;
            }
        }

        if (!selectedTransporte) {
            document.getElementById("resultado3").innerText = "Debe seleccionar un tipo de transporte.";
            return;
        }

        const observacionOrigenEntel = document.getElementById("observacionOrigenEntel").value;
        const observacionDestinoEntel = document.getElementById("observacionDestinoEntel").value;
        const idUbigeoorigen = document.getElementById("idUbigeoorigen").value;
        const idUbigeodestino = document.getElementById("idUbigeodestino").value;

        // Crear el JSON para enviar al API
        const data = {
            flujo: parseFloat(flujo),
            cmm: parseFloat(cmm),
            botonConsolidacion: parseFloat(botonConsolidacion),
            estadoFirma: estadoFirma,
            fechaOrigen: fechaOrigen,
            tipoTransporte: tipoTransporte,
            prioridadtransporte: selectedTransporte,
            observacionOrigenEntel: observacionOrigenEntel,
            observacionDestinoEntel: observacionDestinoEntel,
            usuario: {
               
                idUsuario: parseInt(idUsuario)
            },
            
             usuario2: {
                idUsuario: parseInt(idUsuario2),
                
            },
            clientedestino: {
                idClientedestino: parseInt(idClientedestino)
            },
            cliente: {
                idCliente: parseInt(idCliente)
            },
            unidadsolicitante: {
                idUnidadsolicitante: parseInt(idUnidadsolicitante)
            },
            ubigeoorigen: {
                idUbigeoorigen: parseInt(idUbigeoorigen)
            },
            ubigeodestino: {
                idUbigeodestino: parseInt(idUbigeodestino)
            }
        };

        // Realizar la petición POST a la API para registrar la boleta
        const response = await fetch('/api/boletas/registrar', {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(data)
        });

        // Verificar si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al registrar la boleta');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Boleta registrada:', responseData);

        // Mostrar un mensaje de éxito
        //  document.getElementById("resultado3").innerText = "Boleta registrada correctamente";
        alert("EL FORMULARIO BOLETA SE REGISTRO EXITOSAMENTEEEEEEEEEEEEEEEEEEEEEEEEEE");
       // idBtnRegistrarMaterial.disabled=true;
        //idBtnRegistrarBoleta.disabled=true;
        $('#idModalBoletaRegistra').modal('hide');
        // Recargar la página
       // location.reload();
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        //document.getElementById("resultado3").innerText = "Hubo un problema al registrar la boleta";
        alert("hubo un problema al registrar la boleta ");
    }
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




//-------------------------------verifica si existe flujo---------------------------------------------------------------------
$(document).ready(function () {
	

		
    $("#flujo").on("keyup", function () {
        var flujo = $("#flujo").val();
        // Si el campo flujo está vacío, limpiar todos los campos y salir
        if (flujo === "") {
             $("#cmm").val("");
			   $("#numguia").val("");
			    $("#observacionOrigen").val("");
			    $("#observacionDestino").val("")
			    $("#idUnidadsolicitante").empty();
			    $("#idCliente").empty();
			    $("#idClientedestino").empty();
			    $("#idUbigeoorigen").empty();		  	    
			     $("#idUbigeodestino").empty();
			      $('input[name="prioridadtransporte"]').prop('checked', false);
			         // Limpiar el formulario cuando se queda valido cuando no hay ningun carater en el campo al dar click en el boton guardar
                    var validator = $('#id_form_registra_boleta').data('bootstrapValidator');
                    validator.resetForm();
                    //---------------------------------------------------------------------------------------------
               
            return;
        }
        $.ajax({
            type: "GET",
            url: "/buscarFlujo",
            data: { flujo: flujo },
            success: function (response) {
                if (response != null && response.length > 0) {
                    var firstBoleta = response[0];
                    $("#cmm").val(firstBoleta.cmm);
                    $("#numguia").val(firstBoleta.numguiaConNg);
                    $("#observacionOrigen").val(firstBoleta.observacionOrigen);
                    $("#observacionDestino").val(firstBoleta.observacionDestino);
                     $("#observacionDestino").val(firstBoleta.observacionDestino);
                     
                     
                      if (firstBoleta.prioridadtransporte === 0) {
                          $('#flexRadioDefault1').prop('checked', true);
                        } else if (firstBoleta.prioridadtransporte === 1) {
                            $('#flexRadioDefault2').prop('checked', true);
                         }
                        
                          // $("#id_departamento").prop('disabled', false);
                    // para el select de unidadsolicitante
                    var selectUnidad = $("#idUnidadsolicitante");
                    selectUnidad.empty();
                     $.each(response, function (index, boleta) {
                        var unidad = boleta.unidadsolicitante;
                        if (unidad) {
                            selectUnidad.append(
                                '<option value="' + unidad.idUnidadsolicitante + '">' + unidad.nombreCompleto + "</option>"
                            );
                        }
                    });

                    // para el select de contacto origen
                    var selectCliente = $("#idCliente");
                    selectCliente.empty();
                    var validator = $('#id_form_registra_boleta').data('bootstrapValidator');
                    validator.resetForm();
                    $.each(response, function (index, boleta) {
                        var unidad = boleta.cliente;
                        if (unidad) {
                            selectCliente.append(
                                '<option value="' + unidad.idCliente + '">' + unidad.nombreCompleto + "</option>"
                                
                            );
                        }
                    });

                    // para el select de contacto destino
                    var selectDestino = $("#idClientedestino");
                    selectDestino.empty();
                    $.each(response, function (index, boleta) {
                        var unidad = boleta.clientedestino;
                        if (unidad) {
                            selectDestino.append(
                                '<option value="' + unidad.idClientedestino + '">' + unidad.nombreCompleto + "</option>"
                            );
                          //  alert("El CMM ya esta registrado porvafor registre otro cmm con distinto Num Flujo y distinto Num CMM");
                        }
                    });
                     $('#idBtnRegistrar2').prop('disabled', true);
                     $("#id_departamento").prop('disabled', true);
                  alert("El CMM ya esta registrado porvafor registre otro cmm con distinto Num Flujo y distinto Num CMM");
                       
                } else {
                    $("#cmm").val("");
				   $("#numguia").val("");
				    $("#observacionOrigen").val("");
				    $("#observacionDestino").val("")
				    $("#idUnidadsolicitante").empty();
				    $("#idCliente").empty();
				    $("#idClientedestino").empty();
				     $("#idUbigeoorigen").empty();
				  	  
				    $("#idUbigeodestino").empty();
                    $('#idBtnRegistrar2').prop('disabled', false);
                     $("#id_departamento").prop('disabled', false);
                      $('input[name="prioridadtransporte"]').prop('checked', false);
               }
            },
        });
    });
    
 
    
    
    
    
      $('#idBtnlimpia2').on('click', function() {
		 // $('#idModalClienteRegistra').modal("hide");
        limpiarFormulario();
        var validator = $('#id_form_registra_boleta').data('bootstrapValidator');
        validator.resetForm();
          $('#idModalBoletaRegistra').modal('hide');
          limpiarSelect("#id_departamento");
          limpiarSelect("#id_departamento1");
          $('#idBtnRegistrar2').prop('disabled', false);
           $("#id_departamento").prop('disabled', false);
    });  
    // Li
    // Validar campos al cambiar su valor
    $("#flujo, #cmm").on("input", function () {
        var validator = $("#id_form_registra_boleta").data("bootstrapValidator");
        validator.revalidateField($(this));
        $('#idBtnRegistrar2').prop('disabled', false);
         $("#id_departamento").prop('disabled', false);
    });
    //esto para que cuando escoda un nuevo departamento el validador verde se apague en alamcen oriegen
     $("#id_departamento").on("change", function () {
		 $("#idUbigeoorigen").html('<option value="">[Seleccione Provincia]</option>');

        // Revalidar el campo en el formulario (si estás utilizando Bootstrap Validator)
        var validator = $('#id_form_registra_boleta').data('bootstrapValidator');
        if (validator) {
            validator.revalidateField($("#idUbigeoorigen"));
        }

		 });
		 
		 //esto para que cuando escoda un nuevo departamento el validador verde se apague en alamcen destino
		  $("#id_departamento1").on("change", function () {
		 $("#idUbigeodestino").html('<option value="">[Seleccione Provincia]</option>');
        // $('#idBtnRegistrar2').prop('disabled', false);
        // Revalidar el campo en el formulario (si estás utilizando Bootstrap Validator)
        var validator = $('#id_form_registra_boleta').data('bootstrapValidator');
        if (validator) {
            validator.revalidateField($("#idUbigeodestino"));
            
        }

		 });
});

function limpiarFormulario() {
    $("#flujo").val("");
    $("#cmm").val("");
   $("#numguia").val("");
    $("#observacionOrigen").val("");
    $("#observacionDestino").val("")
    $("#idUnidadsolicitante").empty();
    $("#idCliente").empty();
    $("#idClientedestino").empty();
    
    $("#idUbigeoorigen").empty();
  
  
    $("#idUbigeodestino").empty();
     $('input[name="prioridadtransporte"]').prop('checked', false);
}
function limpiarSelect(selector) {
    $(selector).val('');  // Establece el valor del select en una cadena vacía
    $(selector).change(); // Dispara el evento change para que otros scripts puedan reaccionar si es necesario
}
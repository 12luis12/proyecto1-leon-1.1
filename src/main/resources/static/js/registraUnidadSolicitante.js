$(document).ready(function(){
    $('#id_reg_nombre,#id_reg_apellido').on('keyup', function(){
        var id_reg_nombre = $('#id_reg_nombre').val();
        var id_reg_apellido = $('#id_reg_apellido').val();
           $.ajax({
            type: 'GET',
            url: '/buscarUnidadsolicitante',
            data: {nombre: id_reg_nombre,apellido:id_reg_apellido},
            success: function(response){
                if(response != null){
					//$('#id_reg_nombre').val(response.nombre);
                    //$('#id_reg_apellido').val(response.apellido);
                    $('#id_reg_correo').val(response.correo);
                    $('#id_reg_celular').val(response.celular);
                     $('#unidadSolicitante').val(response.unidadSolicitante);
                     $('#proyecto').val(response.proyecto);
                   
                      if ($('#id_reg_correo').val() && $('#id_reg_celular').val()) {
                        $('#idBtnRegistraUnidadS').prop('disabled', true);
                    } else {
                        $('#idBtnRegistraUnidadS').prop('disabled', false);
                    }
                }else{
					// Limpiar los campos si no se encuentra una unidad solicitante
                    limpiarFormulario3();
                    // Activar el botón guardar
                    $('#idBtnRegistraUnidadS').prop('disabled', false);
				}
            },
              error: function() {
                // En caso de error, también limpiar los campos y habilitar el botón
                limpiarFormulario3();
                $('#idBtnRegistraUnidadS').prop('disabled', false);
            }
        });
    });

    
    $('#idBtnRegistraUnidadS').on('click', function(){
		//var idUnidadsolicitante = $('#idUnidadsolicitante').val();
       // var dni = $('#dni').val();
        var id_reg_nombre = $('#id_reg_nombre').val();
        var id_reg_apellido = $('#id_reg_apellido').val();
        var id_reg_correo = $('#id_reg_correo').val();
        var id_reg_celular = $('#id_reg_celular').val();
        var unidadSolicitante = $('#unidadSolicitante').val();
        var proyecto = $('#proyecto').val();
        var validator = $('#id_form_registra_unidadSolicitante').data('bootstrapValidator');//para validar
         validator.validate();//para validar
         if (validator.isValid()) {//para validar
        $.ajax({
            type: 'POST',
            url: '/guardarUnidadsolicitante',
            data: JSON.stringify({
			       
                    nombre: id_reg_nombre,
                    apellido: id_reg_apellido,
                    correo: id_reg_correo,
                    celular: id_reg_celular,
                    unidadSolicitante: unidadSolicitante,
                    proyecto: proyecto
            }),
           contentType: 'application/json',
            success: function(response){
                alert('Unidad Solicitante se  guardado o actualizo  correctamente');
                // Aquí puedes hacer lo que necesites después de guardar el cliente
                 $('#idModalUnidadsolicitanteRegistra2').modal("hide");
                  limpiarFormulario3();
                  $('#idBtnRegistraUnidadS').prop('disabled', false);
               // mostrarMensaje(response.mensaje);
        	 
                
            },
            error: function() {
                    alert('Hubo un error al guardar la Unidad Solicitante');
                    // Reactivar el botón guardar en caso de error
                    $('#idBtnRegistraMaterial').prop('disabled', false);
                }
        });
        }
		
    });
      
    


   $('#id_form_registra_unidadSolicitante').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombre: {
                validators: {
                    notEmpty: {
                        message: 'El nombre es obligatorio'
                    },
                    stringLength: {
                        min: 5,
                        max: 40,
                        message: 'El nombre es de 5 a 40 caracteres'
                    }
                }
            },
           
            apellido: {
                validators: {
                    notEmpty: {
                        message: 'Los apellidos son obligatorios'
                    },
                    stringLength: {
                        min: 2,
                        max: 40,
                        message: 'Los apellidos son de 2 a 40 caracteres'
                    }
                }
            },
            celular: {
                validators: {
                    notEmpty: {
                        message: 'El celular es obligatorio'
                    },
                    stringLength: {
                        max: 8,
                        min: 8,
                        message: 'El celular es de 8 dígitos'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'Ingresar celular con caracteres numéricos'
                    }
                }
            },
            correo: {
                validators: {
                    notEmpty: {
                        message: 'El correo es obligatorio'
                    },
                    emailAddress: {
                        message: 'El correo no es válido'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
                        message: 'Ingresar un correo válido'
                    }
                }
            }
        }
    });
    // Validar campos al cambiar su valor
  $('#id_reg_nombre, #id_reg_apellido').on('input', function() {
        var validator = $('#id_form_registra_unidadSolicitante').data('bootstrapValidator');
       // validator.revalidateField($(this));
        validator.revalidateField($(this).attr('name'));
    });
     $('#idBtnLimpiaUnidadS').on('click', function() {
		 // $('#idModalClienteRegistra').modal("hide");
        limpiarFormulario3();
        var validator = $('#id_form_registra_unidadSolicitante').data('bootstrapValidator');
        validator.resetForm();
          $('#idModalUnidadsolicitanteRegistra2').modal('hide');
           $('#idBtnRegistraUnidadS').prop('disabled', false);
    });  
    // Limpiar el formulario cuando se queda valido cuando no hay ningun carater en el campo al dar click en el boton guardar
    $('#idBtnRegistraUnidadS').on('click', function() {
        limpiarFormulario3();
        var validator = $('#id_form_registra_unidadSolicitante').data('bootstrapValidator');
        validator.resetForm();
         $('#idBtnRegistraUnidadS').prop('disabled', false);
      
    });  
});

function limpiarFormulario3(){
	       //  $("#dni").val("");
	        $("#idUnidadsolicitante").val("");
			$("#id_reg_nombre").val("");
			$("#id_reg_apellido").val("");
			
			$("#id_reg_celular").val("");
			$("#id_reg_correo").val("")
			$("#unidadSolicitante").val("");
			$("#proyecto").val("")
} 

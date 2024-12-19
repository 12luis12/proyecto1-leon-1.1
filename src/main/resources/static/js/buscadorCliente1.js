$(document).ready(function(){
    $('#nombre, #apellido').on('keyup', function(){
        var nombre = $('#nombre').val();
        var apellido = $('#apellido').val();
        $.ajax({
            type: 'GET',
            url: '/buscar',
            data: {nombre: nombre, apellido: apellido},
            success: function(response){
                if(response != null){
					$('#idCliente').val(response.idCliente);
                    $('#telefono').val(response.telefono);
                    $('#celular').val(response.celular);
                    $('#correo').val(response.correo);
                      if ( $('#celular').val()) {
                        $('#idBtnRegistra1').prop('disabled', true);
                    } else {
                        $('#idBtnRegistra1').prop('disabled', false);
                    }
                }else{
					// Limpiar los campos si no se encuentra una unidad solicitante
                    limpiarFormulario2();
                    // Activar el botón guardar
                    $('#idBtnRegistra1').prop('disabled', false);
				}
            },
            error: function() {
                // En caso de error, también limpiar los campos y habilitar el botón
                limpiarFormulario2();
                $('#idBtnRegistra1').prop('disabled', false);
            }
        });
    });

    
    $('#idBtnRegistra1').on('click', function(){
		var idCliente = $('#idCliente').val();
        var nombre = $('#nombre').val();
        var apellido = $('#apellido').val();
        var telefono = $('#telefono').val();
        var celular = $('#celular').val();
        var correo = $('#correo').val();
        var validator = $('#id_form_registra_cliente').data('bootstrapValidator');//para validar
         validator.validate();//para validar
         if (validator.isValid()) {//para validar
        $.ajax({
            type: 'POST',
            url: '/guardar',
            data: JSON.stringify({idCliente:idCliente,nombre: nombre, apellido: apellido, telefono: telefono , celular:celular, correo:correo}),
           contentType: 'application/json',
            success: function(response){
                alert('Cliente guardado o actualizo  correctamente');
                // Aquí puedes hacer lo que necesites después de guardar el cliente
                 limpiarFormulario2();
                 $('#idModalClienteRegistra').modal("hide");
                 $('#idBtnRegistra1').prop('disabled', false);
               // mostrarMensaje(response.mensaje);
        	 
                
            },
            error: function() {
                    alert('Hubo un error al guardar la Unidad Solicitante');
                    // Reactivar el botón guardar en caso de error
                    $('#idBtnRegistra1').prop('disabled', false);
                }
        });
        }
		
    });
     
    
    
});


//validaciones de la tabla cliente
$(document).ready(function() {
    $('#id_form_registra_cliente').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        		nombre:{
                    selector: "#nombre",
                    validators:{
                        notEmpty: {
                             message: 'El nombre es obligatorio'
                        },
                        stringLength: {
                            min: 5,
                            max: 40,
                            message: 'El nombre es de 5 a 40 caracteres'
                        },
                    }
                },
                apellido:{
                    selector: "#apellido",
                    validators:{
                        notEmpty: {
                             message: 'Los apellidos son obligatorios'
                        },
                        stringLength: {
                        	min: 2,
                            max: 40,
                            message: 'Los apellidos son de 2 a 40 caracteres '
                        },
                    }
                },
                celular:{
                    selector: "#celular",
                    validators:{
                        notEmpty: {
                             message: 'El el celular es obligatorio'
                        },
                        stringLength: {
                        	max: 8,
                        	min: 8,
                            message: 'El el celular es de 8 digitos'
                        },
                        regexp: {
                            regexp: /^[0-9]/,
                            message: 'Ingresar celular con caracteres numericos'
                        }
                    }
                },
                
                correo:{
                    selector: "#correo",
                    validators:{
                        notEmpty: {
                             message: 'El correo es obligatorio'
                        },
                        emailAddress: {
                            message: 'El correo no es valido'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
                            message: 'Ingresar un correo valido'
                        }
                    }
                },
               
              
        }   
    });
    // Validar campos al cambiar su valor
  $('#nombre, #apellido, #correo').on('input', function() {
        var validator = $('#id_form_registra_cliente').data('bootstrapValidator');
        validator.revalidateField($(this));
    });

   // Limpiar el formulario cuando se queda valido cuando no hay ningun carater en el campo al dar click en el boton cerrar
    $('#idBtnlimpia1').on('click', function() {
		 // $('#idModalClienteRegistra').modal("hide");
        limpiarFormulario2();
        var validator = $('#id_form_registra_cliente').data('bootstrapValidator');
        validator.resetForm();
          $('#idModalClienteRegistra').modal('hide');
           $('#idBtnRegistra1').prop('disabled', false);
    });  
    // Limpiar el formulario cuando se queda valido cuando no hay ningun carater en el campo al dar click en el boton guardar
    $('#idBtnRegistra1').on('click', function() {
        limpiarFormulario2();
        var validator = $('#id_form_registra_cliente').data('bootstrapValidator');
        validator.resetForm();
         $('#idBtnRegistra1').prop('disabled', false);
      
    });  
});
function limpiarFormulario2(){
	       $("#idCliente").val("");
			$("#nombre").val("");
			$("#apellido").val("");
			$("#telefono").val("");
			$("#celular").val("");
			$("#correo").val("")
} 

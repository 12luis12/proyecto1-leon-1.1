function cambiarDeEstado1a2(id) {
    // Mostrar un cuadro de confirmación antes de continuar
    var confirmar = confirm('Estas seguro de que deseas consolidar este CMM Una vez consolidado, no podra ser modificado.');

    // Si el usuario hace clic en "Aceptar", continúa con la función
    if (confirmar) {
        $.ajax({
            url: '/guardaConsolidacion?id=' + id, // Asegúrate de que el parámetro id se incluye correctamente
            type: 'PUT',
            success: function (data) {
                alert('EL CMM FUE CONSOLIDADO CORRECTAMENTE, EL CMM YA NO PUEDE SER MODIFICADO. Pongase en contacto con su administrador si cree que hubo algun error al registrar.');
                recarga1();

                if (botonConsolidacion === 2) {
                    var form = document.getElementById('id_form_actualiza_material');
                    var elements = form.elements;
                    for (var i = 0; i < elements.length; i++) {
                        elements[i].disabled = true;
                    }
                }
            },
            error: function (error) {
                alert('Error actualizando el estado');
            }
        });
    } else {
        // Si el usuario cancela, no hacer nada o realizar alguna acción alternativa
        alert('La consolidacion fue cancelada.');
    }
}







	
function muestrapdfdelcmm(id) {
    // Asignar el valor de id al campo oculto en el formulario
    
    $("#id_boleta").val(id);
    
    // Enviar el formulario
    $("#id_form1").attr('action', 'generateProformaPDF');
    $("#id_form1").submit();


}
function muestrapdfcomprobante(id) {
    // Asignar el valor de id al campo oculto en el formulario
    
    $("#id_boleta").val(id);
    
    // Enviar el formulario
    $("#id_form1").attr('action', 'generaComprobanteDeEntregaPDF');
    $("#id_form1").submit();


}

function muestrapdfconypfb(id) {
    // Asignar el valor de id al campo oculto en el formulario
    
    $("#id_boleta").val(id);
    
    // Enviar el formulario
    $("#id_form1").attr('action', 'generaYPFB');
    $("#id_form1").submit();


}


function recarga1(){
	$.getJSON("consultaPorCmm1",function(data){
		agregarGrilla(data);
	});
	
	
	
}

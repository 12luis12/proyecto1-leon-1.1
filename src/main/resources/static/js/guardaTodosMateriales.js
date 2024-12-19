$(document).ready(function(){     
    $("#idBtnNuevoCmm").click(function(){
        // Primera solicitud AJAX
        // Segunda solicitud AJAX
        $.ajax({
            type: 'GET',
            url: '/actualizaCampoEstadoMaterial1',
            success: function(response) {
                console.log("Ajax Success Response 2:", response);
                // Verifica si la solicitud Ajax se completó con éxito
                if (response === 'success') {
					
                    // Redirige a la página verIntranetHome en la misma pestaña
                    window.location.href = '/verIntranetHome';
                    
                } else {
                    console.log("Error en la segunda solicitud: " + response);
                }
            },
            error: function(xhr, status, error) {
                console.log("Error en la segunda solicitud: " + status + " " + error);
            }
        });
    });
});
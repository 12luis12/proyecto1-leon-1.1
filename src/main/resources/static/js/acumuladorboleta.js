function copiaMaterialLosCamposAcumuladoryDescripcionABoleta(id){
	
	 var idBoleta = id; // esto se ejecuta cuando al registrar por primera vez el cmm
    //  alert(idBoleta);
        // Primera solicitud AJAX
        $.ajax({
            type: 'GET',
            url: '/copiaMaterialesAboleta',
            data: { idBoleta: idBoleta },
            success: function(response) {
                console.log("Ajax Success Response 1:", response);
                // Verifica si la solicitud Ajax se completó con éxito
                if (response && response.length > 0) {
                    console.log("Primera solicitud completada con éxito");
                } else {
                    console.log("Error en la primera solicitud: " + response);
                }
            },
            error: function(xhr, status, error) {
                console.log("Error en la primera solicitud: " + status + " " + error);
            }
        });
	
}
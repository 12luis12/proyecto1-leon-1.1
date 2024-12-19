
     function recargarSelectUltimoRegistro() {
		   //este es para cuando se introduce un nuevo cliente entonces automaticamente se recar
		   //en opcion contacto origen
            // Realizamos una petición Ajax para obtener los nuevos datos del select
            var valorSeleccionado = $('#idBoleta').val();
            $.ajax({
                type: 'GET',
                url: '/consultarBoletaUltimoRegistro', // Aquí colocarías la URL de tu endpoint que devuelve las opciones
                success: function(data) {
                    // Limpiamos el select actual
                    $('#idBoleta').empty();

                    // Iteramos sobre los datos obtenidos y agregamos nuevas opciones al select
                    $.each(data, function(key, value) {
                        $('#idBoleta').append("<option value="+value +">"+ value +"</option>");
                    });
                    $('#idBoleta').val(valorSeleccionado);
                }
            });
        }
        
   
    
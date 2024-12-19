   function recargarSelectClientedestino() {
		   //este es para cuando se introduce un nuevo cliente entonces automaticamente se recar
		   //en opcion contacto origen
            // Realizamos una petición Ajax para obtener los nuevos datos del select
            var valorSeleccionado = $('#idClientedestino').val();
            $.ajax({
                type: 'GET',
                url: '/cargaClientedestino', // Aquí colocarías la URL de tu endpoint que devuelve las opciones
                success: function(data) {
                    // Limpiamos el select actual
                    $('#idClientedestino').empty();

                    // Iteramos sobre los datos obtenidos y agregamos nuevas opciones al select
                    $.each(data, function(key, value) {
                        $('#idClientedestino').append("<option value="+value.idClientedestino +">"+ value.nombreCompleto +"</option>");
                    });
                    $('#idClientedestino').val(valorSeleccionado);
                }
            });
        }
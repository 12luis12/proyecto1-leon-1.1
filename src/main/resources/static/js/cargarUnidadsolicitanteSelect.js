
     function recargarSelectunidadsolicitante() {
		   //este es para cuando se introduce un nuevo cliente entonces automaticamente se recar
		   //en opcion contacto origen
            // Realizamos una petición Ajax para obtener los nuevos datos del select
            var valorSeleccionado = $('#idUnidadsolicitante').val();
            $.ajax({
                type: 'GET',
                url: '/cargaUnidadsolicitante', // Aquí colocarías la URL de tu endpoint que devuelve las opciones
                success: function(data) {
                    // Limpiamos el select actual
                    $('#idUnidadsolicitante').empty();

                    // Iteramos sobre los datos obtenidos y agregamos nuevas opciones al select
                    $.each(data, function(key, value) {
                        $('#idUnidadsolicitante').append("<option value="+value.idUnidadsolicitante +">"+ value.nombreCompleto +"</option>");
                    });
                    $('#idUnidadsolicitante').val(valorSeleccionado);
                }
            });
        }
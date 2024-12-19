
     function recargarSelect() {
		   //este es para cuando se introduce un nuevo cliente entonces automaticamente se recar
		   //en opcion contacto origen
            // Realizamos una petición Ajax para obtener los nuevos datos del select
            var valorSeleccionado = $('#idCliente').val();
            $.ajax({
                type: 'GET',
                url: '/cargaCliente', // Aquí colocarías la URL de tu endpoint que devuelve las opciones
                success: function(data) {
                    // Limpiamos el select actual
                    $('#idCliente').empty();

                    // Iteramos sobre los datos obtenidos y agregamos nuevas opciones al select
                    $.each(data, function(key, value) {
                        $('#idCliente').append("<option value="+value.idCliente +">"+ value.nombreCompleto +"</option>");
                    });
                    $('#idCliente').val(valorSeleccionado);
                }
            });
        }
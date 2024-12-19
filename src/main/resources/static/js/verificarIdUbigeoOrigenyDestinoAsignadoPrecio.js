document.addEventListener('DOMContentLoaded', function () {
    const idUbigeoorigen = document.getElementById('idUbigeoorigen');
    const idUbigeodestino = document.getElementById('idUbigeodestino');
    const idBtnRegistrar2 = document.getElementById('idBtnRegistrar2');

    idUbigeoorigen.addEventListener('change', verificarPrecios);
    idUbigeodestino.addEventListener('change', verificarPrecios);

    function verificarPrecios() {
        const valorUbigeoorigen = idUbigeoorigen.value;
        const valorUbigeodestino = idUbigeodestino.value;

      

        // Verificar que ambos selects tengan valores seleccionados
        if (valorUbigeoorigen && valorUbigeodestino) {
            // Asegúrate de que la URL está correctamente construida
            const url = `/api/boletas/verificarIdUbigeoorigenIdUbigeodestino/${valorUbigeoorigen}-${valorUbigeodestino}`;
            console.log("Request URL:", url); // Imprime la URL para verificar

            fetch(url)
                .then(response => {
                    console.log("Response Status:", response.status); // Imprime el código de estado
                    return response.json();
                })
                .then(data => {
                    console.log("Respuesta del servidor:", data); // Imprime la respuesta del servidor

                    // Verificar la respuesta del servidor
                    if (data === true) {
                        idBtnRegistrar2.disabled = false;
                    } else {
                        idBtnRegistrar2.disabled = true;
                        $('#modalRegistrarPreciosEnBoleta').modal('show'); // Mostrar ventana modal
                    }
                })
                .catch(error => console.error('Error:', error));
        } else {
            idBtnRegistrar2.disabled = true;
        }
    }
});






$(document).ready(function() {
    // Asegúrate de que btnRegistrar esté definido y accesible
    const btnRegistrar = document.getElementById('idBtnRegistrar2'); // O usa jQuery: $('#idBtnRegistrar2')[0]

    // Función para limpiar el formulario del modal
    function limpiarFormulario() {
        $('#formRegistrarPrecios')[0].reset(); // Suponiendo que el formulario tiene el ID 'formRegistrarPrecios'
    }

    // Función para validar que los campos no estén vacíos y contengan números
    function validarFormulario() {
        let isValid = true;
        let errorMessage = 'DEBE COMPLETAR TODOS LOS CAMPOS CON NUMEROS SI OSI TIENE QUE SER NUMEROS.';

        // Obtener los valores de los campos a validar
        const normalMinima = $('#normalMinima').val();
        const normalM3 = $('#normalM3').val();
        const normalKgAdicional = $('#normalKgAdicional').val();
        const tiempoNormal = $('#tiempoNormal').val();

        const expresMinimo = $('#expresMinimo').val();
        const expresM3 = $('#expresM3').val();
        const expresKgAdicional = $('#expresKgAdicional').val();
        const tiempoExpres = $('#tiempoExpres').val();

        // Verificar si los campos están vacíos o no contienen números válidos
        if (!normalMinima || isNaN(normalMinima) || 
            !normalM3 || isNaN(normalM3) || 
            !normalKgAdicional || isNaN(normalKgAdicional) || 
            !tiempoNormal || isNaN(tiempoNormal) || 
            !expresMinimo || isNaN(expresMinimo) || 
            !expresM3 || isNaN(expresM3) || 
            !expresKgAdicional || isNaN(expresKgAdicional) || 
            !tiempoExpres || isNaN(tiempoExpres)) {
            
            isValid = false;
        }

        if (!isValid) {
            // Mostrar la ventana de alerta con el mensaje de error
            alert(errorMessage);
        }

        return isValid;
    }

    // Cerrar el modal con el botón 'botonPrecio'
    $('#botonCancelarPrecioEnBoleta').click(function() {
        $('#modalRegistrarPreciosEnBoleta').modal('hide');
        limpiarFormulario(); // Limpia el formulario al cerrar el modal
    });

    // Cerrar el modal, habilitar el botón y limpiar el formulario con el botón 'guardarPrecio'
    $('#guardarPrecioEnBoleta').click(function() {
        if (validarFormulario()) {
            $('#modalRegistrarPreciosEnBoleta').modal('hide'); // Cerrar solo si la validación es correcta
            $('#btnRegistrar').prop('disabled', false); // Habilita el botón
            limpiarFormulario(); // Limpia el formulario al guardar
        }
    });
});

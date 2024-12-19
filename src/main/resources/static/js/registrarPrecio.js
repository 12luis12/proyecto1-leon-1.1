async function registraPreciosEnBoleta() {
    try {
		const idBtnRegistrar2 = document.getElementById('idBtnRegistrar2');
        // Obtener los valores seleccionados de los `select`
        const normalMinima = document.getElementById("normalMinima").value;
        const normalM3 = document.getElementById("normalM3").value;
        const normalKgAdicional = document.getElementById("normalKgAdicional").value;
        const tiempoNormal = document.getElementById("tiempoNormal").value;
        
        const expresMinimo = document.getElementById("expresMinimo").value;
        const expresM3 = document.getElementById("expresM3").value;
        const expresKgAdicional = document.getElementById("expresKgAdicional").value;
        const tiempoExpres = document.getElementById("tiempoExpres").value;
       
        const idUbigeoorigen = document.getElementById("idUbigeoorigen").value;
        const idUbigeodestino = document.getElementById("idUbigeodestino").value;

        // Validar los campos antes de enviar
        if (!validarCampos([normalMinima, normalM3, normalKgAdicional, tiempoNormal, expresMinimo, expresM3, expresKgAdicional, tiempoExpres])) {
            document.getElementById("resultado3").innerText = "Todos los campos deben ser números válidos y no deben estar vacíos.";
            return;
        }

        // Verificar si ambos `idUbigeoorigen` e `idUbigeodestino` tienen un valor seleccionado
        if (!idUbigeoorigen || !idUbigeodestino) {
            throw new Error('Debe seleccionar tanto origen como destino');
        }

        // Crear el JSON para enviar al API
        const data = {
            normalMinima: parseFloat(normalMinima),
            normalM3: parseFloat(normalM3),
            normalKgAdicional: parseFloat(normalKgAdicional),
            tiempoNormal: parseFloat(tiempoNormal),
            expresMinimo: parseFloat(expresMinimo),
            expresM3: parseFloat(expresM3),
            expresKgAdicional: parseFloat(expresKgAdicional),
            tiempoExpres: parseFloat(tiempoExpres),
            ubigeoorigen: {
                idUbigeoorigen: parseInt(idUbigeoorigen)
            },
            ubigeodestino: {
                idUbigeodestino: parseInt(idUbigeodestino)
            }
        };

        // Realizar la petición PUT a la API
        const response = await fetch('/api/boletas/resgistraActualizaPrecio', {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(data)
        });

        // Verificar si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al actualizar el Material');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Material actualizada:', responseData);

        // Mostrar un mensaje de éxito
        document.getElementById("resultado3").innerText = "Material actualizada correctamente";
        idBtnRegistrar2.disabled = false;
       

        // Recargar la página
       // location.reload();
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        document.getElementById("resultado3").innerText = "Hubo un problema al actualizar el Material";
    }
}

// Función para validar que los campos contengan números y no estén vacíos
function validarCampos(campos) {
    for (let campo of campos) {
        // Comprobar si el campo está vacío o no es un número
        if (!campo || isNaN(campo)) {
            return false; // Si algún campo no es válido, retornar false
        }
    }
    return true; // Si todos los campos son válidos, retornar true
}

// Función para obtener los encabezados con el token de autorización
function getHeaders() {
    const token = localStorage.getItem('token');
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : '' 
    };
}

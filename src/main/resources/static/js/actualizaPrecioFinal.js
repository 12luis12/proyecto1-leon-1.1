function actualizaElPrecioFinal(id){
	
 var idBoleta = id; // esto se ejcuta cuando al actualizar material , hace que se vuelba a calcular  el precio final

        // Primera solicitud AJAX
        $.ajax({
            type: 'GET',
            url: '/calcularPrecioServicio',
            // url: '/servicio/precio/calcularPrecioFinal',
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


async function actualizaElPrecioFinal1(idBoleta) {
    try {
        const response = await fetch(`servicio/precio/calcularPrecioFinal/${idBoleta}`, {
            method: 'GET', // Cambiado a GET
            headers: getHeaders(),
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        console.log("Ajax Success Response:", data);
    } catch (error) {
        console.log("Error en la solicitud:", error);
    }
}






function getHeaders() {
    const token = localStorage.getItem('token');
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : ''
    };
}

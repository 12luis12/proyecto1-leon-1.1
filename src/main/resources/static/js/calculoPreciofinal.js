async function idBtncalcularPreciofinal(id) {
	

	const idBoleta=parseInt(id);
		if (isNaN(idBoleta)) {
  alert("ID de boleta no es un número válido");
  return;
}
 //alert("ID de boleta es un numerooooooo"+idBoleta);
	const idBtnNuevoCmm = document.getElementById('idBtnNuevoCmm');
	
  try {
    if (!confirm('¿Desea calcular el precio para esta boleta?')) {
      return;
    }

    // Llamada al servidor usando GET con el idBoleta como parámetro
    const request = await fetch('/servicio/precio/calcularPrecioFinal?idBoleta=${idBoleta}' , {
      method: 'GET',
      headers: getHeaders()
    });

    if (request.ok) {
      const boletas = await request.json();  // Parsear la respuesta JSON

      // Procesar la respuesta, por ejemplo, mostrar el precio calculado
      const precioServicio = boletas[0].precioServicio;
      alert(`Precio calculado: ${precioServicio}`);
       idBtnNuevoCmm.disabled = false;

      // Si quieres recargar la página o hacer algo más después de la respuesta exitosa
      // location.reload();  // Si es necesario recargar la página
    } else {
      console.error('Error al calcular el precio');
      alert('Hubo un error al calcular el precio.');  // Muestra un mensaje de error
    }
  } catch (error) {
    console.error('Error en la solicitud', error);
    alert('Ocurrió un error inesperado.');
  }
}

function getHeaders() {
	 const token = localStorage.getItem('token');
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    //'Authorization':localStorage.token
    'Authorization': token ? `Bearer ${token}` : '' 
  };
}
async function cargarDatos1() {
    try {
        // Realiza la petición al API REST
        const response = await fetch('api/boletas/listaBoletasSinAsignarOrigen');
        
        // Verifica si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }
        
        // Convierte la respuesta a formato JSON
        const data = await response.json();

        // Obtiene el elemento select
        const select = document.getElementById('miSelect');
        select.innerHTML = ''; // Limpia las opciones existentes

        // Añade la opción "Seleccionar" al inicio
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.text = 'Seleccionar';
        defaultOption.disabled = true; // Hace que no se pueda seleccionar esta opción
        defaultOption.selected = true; // Hace que esta opción sea la seleccionada por defecto
        select.appendChild(defaultOption);

        // Recorre los datos y crea opciones para el select
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.idBoleta; // Ajusta según el formato de tu API
            option.text = `CMM: ${item.cmm} | Origen: ${item.departamentoUbigeoOrigen} | Destino: ${item.departamentoUbigeoDestino}`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Hubo un problema con la solicitud Fetch:', error);
    }
}

// Llama a la función para cargar los datos al cargar la página
cargarDatos1();

// Función para mostrar la selección del select
function mostrarSeleccion1(event) {
    event.preventDefault(); // Evita el envío del formulario
    const select = document.getElementById("miSelect");
    const selectedOption = select.options[select.selectedIndex].text;
    document.getElementById("resultado3").innerText = "Has seleccionado: " + selectedOption;
}


//-------------------------------------------------------------------------------------------------------------

// Función para obtener los encabezados con el token de autorización
function getHeaders() {
    const token = localStorage.getItem('token');
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : '' 
    };
}

// Función para cargar datos en el select
async function cargarDatos2() {
    try {
        // Realiza la petición al API REST con los encabezados de autorización
        const response = await fetch('api/usuarios/listaUsuarios', {
            method: 'GET',
            headers: getHeaders() // Incluye los encabezados
        });

        // Verifica si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }

        // Convierte la respuesta a formato JSON
        const data = await response.json();

        // Obtiene el elemento select
        const select = document.getElementById('miSelect1');
        select.innerHTML = ''; // Limpia las opciones existentes
        
        
         const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.text = 'Seleccionar';
        defaultOption.disabled = true; // Hace que no se pueda seleccionar esta opción
        defaultOption.selected = true; // Hace que esta opción sea la seleccionada por defecto
        select.appendChild(defaultOption);

        // Recorre los datos y crea opciones para el select
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.idUsuario; // Ajusta según el formato de tu API
            option.text = `Nombre: ${item.nombreCompleto} `;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Hubo un problema con la solicitud Fetch:', error);
    }
}

// Llama a la función para cargar los datos al cargar la página
window.onload = function() {
    cargarDatos1();
    cargarDatos2();
    cargarDatosParaDesasignar1();
};

// Función para mostrar la selección del select
function mostrarSeleccionUsuario1(event) {
    event.preventDefault(); // Evita el envío del formulario
    const select = document.getElementById("miSelect1");
    const selectedOption = select.options[select.selectedIndex].text;
    document.getElementById("resultado3").innerText = "Has seleccionado: " + selectedOption;
}
///____________________para actualizar------------------------------------------------------------------------------------------


// Función para actualizar la boleta al hacer clic en el botón
async function updateBoleta1() {
    try {
        // Obtener los valores seleccionados de los `select`
        const idUsuario = document.getElementById("miSelect1").value;
        const idBoleta = document.getElementById("miSelect").value;

        // Verificar si ambos select tienen un valor seleccionado
        if (!idUsuario || !idBoleta) {
            // Mostrar un mensaje de error si alguno de los select no tiene una selección
            document.getElementById("resultado3").innerText = "Por favor, seleccione tanto una CMM como un Usuario de Destino";
            return; // Detener la ejecución de la función
        }

        // Crear el JSON para enviar al API
        const data = {
            idBoleta: parseInt(idBoleta),
            usuario: {
                idUsuario: parseInt(idUsuario)
            }
        };

        // Realizar la petición PUT a la API
        const response = await fetch('api/boletas/actualizaBoletaSoloCampoIdUsuario', {
            method: 'PUT',
            headers: getHeaders(), // Incluir encabezados con token si es necesario
            body: JSON.stringify(data)
        });

        // Verifica si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al actualizar la boleta');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Boleta actualizada:', responseData);

        // Mostrar un mensaje de éxito
        document.getElementById("resultado3").innerText = "Boleta actualizada correctamente";

        // Recargar la página
        location.reload();
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        document.getElementById("resultado3").innerText = "Hubo un problema al actualizar la boleta";
    }
}

//----------------------desasignar el campo idUsuario2=0-----------------------------------------------------------------

async function updateBoletaDesasignar1() {
    try {
        // Obtener el valor seleccionado del `select`
        const select = document.getElementById("miSelect2");
        const idBoleta = select.value;

        // Verificar si se ha seleccionado un elemento
        if (!idBoleta) {
            // Mostrar un mensaje de error si no se ha seleccionado ningún elemento
            document.getElementById("resultado33").innerText = "Por favor, seleccione un  opcion para poder desasignar.";
            return; // Detener la ejecución de la función
        }

        // Crear el JSON para enviar al API
        const data = {
            idBoleta: parseInt(idBoleta),
            //usuario: 1 // Asignar null directamente
             usuario: { idUsuario: 1 } 
        };

        // Realizar la petición PUT a la API
        const response = await fetch('api/boletas/actualizaBoletaSoloCampoIdUsuario', {
            method: 'PUT',
            headers: getHeaders(), // Incluir encabezados con token si es necesario
            body: JSON.stringify(data)
        });

        // Verifica si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al actualizar la boleta');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Boleta actualizada:', responseData);

        // Mostrar un mensaje de éxito
        document.getElementById("resultado33").innerText = "Boleta actualizada correctamente";

        // Recargar la página
        location.reload();
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        document.getElementById("resultado33").innerText = "Hubo un problema al actualizar la boleta";
    }
}


//-----------------------cargar el select para desasignar--------------------------------------------------------------------------

async function cargarDatosParaDesasignar1() {
    try {
        // Realiza la petición al API REST
        const response = await fetch('api/boletas/listaUsuarioAsignadosOrigen');
        
        // Verifica si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }
        
        // Convierte la respuesta a formato JSON
        const data = await response.json();

        // Obtiene el elemento select
        const select = document.getElementById('miSelect2');
        select.innerHTML = ''; // Limpia las opciones existentes
         const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.text = 'Seleccionar';
        defaultOption.disabled = true; // Hace que no se pueda seleccionar esta opción
        defaultOption.selected = true; // Hace que esta opción sea la seleccionada por defecto
        select.appendChild(defaultOption);

        // Recorre los datos y crea opciones para el select
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.idBoleta; // Ajusta según el formato de tu API
            option.text = `Nombre: ${item.nombreCompletoUsuario  || 'No disponible'}  |CMM: ${item.cmm} | Origen: ${item.departamentoUbigeoOrigen} | Destino: ${item.departamentoUbigeoDestino}`;
    
           
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Hubo un problema con la solicitud Fetch:', error);
    }
}

// Llama a la función para cargar los datos al cargar la página


// Función para mostrar la selección del select
function mostrarSeleccionDesasignar1(event) {
    event.preventDefault(); // Evita el envío del formulario
    const select = document.getElementById("miSelect2");
    const selectedOption = select.options[select.selectedIndex].text;
    document.getElementById("resultado33").innerText = "Has seleccionado: " + selectedOption;
}

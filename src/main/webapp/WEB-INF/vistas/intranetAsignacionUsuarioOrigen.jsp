<jsp:include page="intranetValida.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <script type="text/javascript" src="js/asignarCmmAUsuarioOrigen.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario con Selects y Labels</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px;
            border: 2px solid #ddd;
            border-radius: 5px;
            width: fit-content;
            margin: 0 auto;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            margin-right: 20px;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        select {
            width: 350px; /* Ajusta el ancho según sea necesario */
            padding: 10px; /* Ajusta el padding para cambiar el tamaño */
            font-size: 16px; /* Cambia el tamaño de la fuente a 16px */
            color: red; /* Cambia el color del texto a rojo */
            border: 1px solid #ccc; /* Añade un borde para mejor visibilidad */
            border-radius: 20px; /* Redondea los bordes del select */
        }
        button {
            padding: 15px 30px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Asignacion de CMM a un Usuario Origen</h1>

    <form id="formAsignarOrigen" class="form-container">
        <div class="form-group">
            <label for="miSelect1">Usuario destino:</label>
            <select id="miSelect1" name="miSelect1" required>
                <option value="">Cargando.....</option>
                <!-- Opciones se llenarán con JavaScript -->
            </select>
        </div>
        <div class="form-group">
            <label for="miSelect">CMM que ya esta firmado en origen :</label>
            <select id="miSelect" name="miSelect" required>
                <option value="">Cargando...</option>
                <!-- Opciones se llenarán con JavaScript -->
            </select>
        </div>
        <button type="button" onclick="mostrarSeleccion1(event); updateBoleta1()">Asignar</button>
        <p id="resultado3"></p>
    </form>
    
    <h1>Relacion de desasigacion</h1>
    
    <form id="formDesasignarOrigen" class="form-container">
        <div class="form-group">
            <label for="miSelect2">Elija una opcion para poder desaser <br>la asigancion de del Cmm al Usuario <br>esta opcion siempre se debe hacer<br> cuando creas que te equivocaste  caso <br>contrario no se debe  tocar</label>
            <select id="miSelect2" name="miSelect2" required>
                <option value="">Cargando.....</option>
                <!-- Opciones se llenarán con JavaScript -->
            </select>
        </div>
        <button type="button" onclick="mostrarSeleccionDesasignar1(event); updateBoletaDesasignar1()">Desasignar</button>
        <p id="resultado33"></p>
    </form>
</body>
</html>

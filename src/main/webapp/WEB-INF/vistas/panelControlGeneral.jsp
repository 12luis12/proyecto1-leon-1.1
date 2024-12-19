<jsp:include page="intranetValida.jsp" />
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="css/panelControl.css" />
    <title>Panel de Control</title>
   
</head>
<body>

<div class="container">
    <div class="sidebar">
        <h2>Menú de Navegación</h2>
        <a href="vercrudBoleta" target="content-frame">Precio</a>  <a href="pagina2.html" target="content-frame">cliente</a>
        <a href="pagina3.html" target="content-frame">material</a>
        <a href="pagina4.html" target="content-frame">unidad solicitante</a>
        <a href="pagina5.html" target="content-frame">Boleta</a>
        <a href="pagina5.html" target="content-frame">camion</a>
    </div>
    <div class="content">
    <iframe name="content-frame" src="vercrudBoleta"></iframe>
    </div>
</div>

</body>
</html>

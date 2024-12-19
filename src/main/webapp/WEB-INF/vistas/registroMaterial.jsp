<jsp:include page="intranetValida.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<!-- Bootstrap -->
<!--<link rel="stylesheet" href="css/bootstrap.min.css" />--> 
<!-- Custom CSS -->
<link rel="stylesheet" href="css/interfaz.css" />
<title>CONZZARTRANS S.R.L</title>
</head>
<body>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<div class="container-fluid">
  <a class="navbar-brand" href="#">CONZZARTRANS S.R.L</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav ms-auto">
      <a class="nav-link" href="#">Home</a>
      <a class="nav-link" href="#">Features</a>
      <a class="nav-link" href="#">Pricing</a>
    </div>
  </div>
</div>
</nav>

<div class="container my-3">
 <div class="col-md-23">
   <!--Formulario1-->
   
  
<form accept-charset="UTF-8" action="consultarMaterialEstado1" class="simple_form"> <!--formulario-->
<div class="row" >
	<div class="col-ms-2" >
			<input class="form-control" id="id_nombre_filtro1" value="0" name="filtro" placeholder="Ingrese nombre Cliente" type="hidden" maxlength="30"/>
	</div>
	
	<div class="col-ms-4">
	     <button type="submit" class="btn btn-success" id="validateBtnw1" style="width: 150px">FILTRA</button>
		<button type="button" data-toggle='modal'  onclick="registrar1();"class="btn btn-info" style="width: 150px">REGISTRA</button>
	</div>
	<div class="col-ms-4">
	 <c:if test="${sessionScope.MENSAJE !=null}">
		 <div class="alert alert-success" id="success-alert">
			  <button type ="button" class="close" data-dismiss="alert">x</button>
			    ${sessionScope.MENSAJE}
		 </div>
	  </c:if>
	  <c:remove var="MENSAJE"/>
	</div>
</div>


<div class="row" > 
	<div class="col-ms-12">
			<div class="content" >
	
				<table id="tableCliente" class="table  table-striped table-bordered " >
					<thead>
						<tr>
						<th style="width: 15%" >Descripcion</th>
						<th style="width: 8%">Cantidad</th>
						<th style="width: 8%">Peso</th>
						<th style="width: 8%">Volmen M3</th>
						<th style="width: 15%">Observaciones</th>
						<th style="width: 10%">Estado</th>
						</tr>
					</thead>
						<tbody>
						 <c:forEach items="${sessionScope.materiales}" var="x">
						   <tr>
						   <td>${x.descripcion}</td>
						     <td>${x.cantidad}</td>
						     <td>${x.peso}</td>
						     <td>${x.volumen}</td>
						     <td>${x.observaciones}</td>
						     <td>${x.estado}</td>
						   </tr>
						   </c:forEach>
						</tbody>
					</table>
			     </div>	
	        </div>
        </div>
     </form><!--fin formulario-->
  
</div>
<!--/ppppp-->
<!--modal registrar-->

<div class="modal fade" id="idModalRegistra1">
  <div class="modal-dialog" style="width: 60%">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header" style="padding: 45px 60px">
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
        <h4>
          <span class="glyphicon glyphicon-ok-sign"></span> Registrar el
          Material
        </h4>
      </div>
      <div class="modal-body" style="padding: 20px 10px">
        <form id="id_form_registra" accept-charset="UTF-8" action="registraMaterial"
          class="form-horizontal" method="post">
          <div class="panel-group" id="steps">
            <!-- Step 1 -->
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos Ubigeo Origen</a>
                </h4>
              </div>
              <div id="stepOne" class="panel-collapse collapse in">
                <div class="panel-body">
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="id_reg_descripcion">descripcion del material</label>
                    <div class="col-lg-6">
                    <textarea class="form-control" id="id_reg_descripcion" name="descripcion" placeholder="Descripcion " rows="3"></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="id_reg_peso">Peso Kg</label>
                    <div class="col-lg-3">
                      <input class="form-control" id="id_reg_peso" name="peso" placeholder="peso kg"
                        type="text" />
                    </div>
                  </div>
                  
                  <div class="form-group">
                  <label class="col-lg-3 control-label" for="id_reg_cantidad">cantidad</label>
                  <div class="col-lg-3">
                    <input class="form-control" id="id_reg_cantidad" name="cantidad" placeholder="cantidad"
                      type="text" />
                  </div>
                  <div class="form-group">
                    <label class="col-lg-2 control-label" for="id_reg_volumen">Volumen M3</label>
                    <div class="col-lg-2">
                      <input class="form-control" id="id_reg_volumen" name="volumen" placeholder="volumen"
                        type="text" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="id_reg_observaciones">observaciones</label>
                    <div class="col-lg-6">
                    <textarea class="form-control" id="id_reg_observacionesn" name="observaciones" placeholder="Observacion " rows="3"></textarea>
                    </div>
                  </div>
                  <!--aqui va ir el idBoleta---------------------------------->
                  
		                  <div class="form-group">
		                  <label class="col-lg-2 control-label" for="id_reg_estado"></label>
		                  <div class="col-lg-2">
		                    <input class="form-control" id="id_reg_estado" value="0" name="estado" placeholder="oculto"
		                      type="hidden" />
		                  </div>
		                </div>
                  
                  
	                  <div class="form-group">
	                  <label class="col-lg-2 control-label" for="id_reg_idBoleta"></label>
	                  <div class="col-lg-2">
	                    <input class="form-control" id="id_reg_idBoleta" value="4" name="boleta.idBoleta" placeholder="oculto"type="hidden" />
	                  </div>
	                </div>
	                <!--/aqui va ir el idBoleta----------------------------------->
                  <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-3">
                      <button type="submit" class="btn btn-primary" id="idBtnRegistra">
                        REGISTRA
                      </button>
                      <button type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">
                        CANCELAR
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!--/modal registrar-->

</div>

<script type="text/javascript">
  $("#success-alert").fadeTo(2000,500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
  });
  </script>

<script type="text/javascript">




/*

$.getJSON("cargaCliente", {}, function(data){
	$.each(data, function(index,item){
		$("#id_cliente").append("<option value="+item.idCliente +">"+ item.nombre +"</option>");
		
	});
});

$.getJSON("cargaClientedestino", {}, function(data){
	$.each(data, function(index,item){
		$("#id_clientedestino").append("<option value="+item.idClientedestino +">"+ item.nombre +"</option>");
		
	});
});
*/
function registrar() {
$("#idModalRegistra").modal('show')
}

$(document).ready(function(){
	$('#tableMaterial').DataTable();
});

</script>




</body>
</html>

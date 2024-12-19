<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="intranetValida.jsp"/>
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<script type="text/javascript" src="js/crudPrecio.js"></script>


<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>CONZZARTRANS S.R.L </title>
</head>
<body> 

 <div class="container">
 <h3>Crud Asignacion precio a las Rutas Seleccionadas </h3>
		 <div class="col-md-23" >  <!--**********-->
		 
		 
    <form  accept-charset="UTF-8" action="listarUsuario" > 
    <div class="row">
    <div class="col-md-12 d-flex justify-content-center">
      <button type="button" class="btn btn-success" id="idBtlimpiar" onclick="bootonregistrarP();">Registrar</button>
    </div>
  </div>


     <div class="row">
     <div class="col-md-12">
         <div class="content ">

         <table id="tablePrecio" class="table table-bordered">
         <thead class="header-1">
             <tr>
                <th style="width: 2%">idPrecio</th>
                 <th style="width: 20%">Origen</th>
                 <th style="width: 20%">Destino</th>
                 <th style="width: 7%">kg Normal</th>
                 <th style="width: 7%">M3</th>
                  <th style="width: 7%">kg adicional</th>
                  <th style="width: 6%">tiempo</th>
                  <th style="width: 7%">kg expres</th>
                  <th style="width: 7%">M3</th>
                   <th style="width: 7%">kg adicional</th>
                   <th style="width: 6%">tiempo</th>
                   <th style="width: 1%">Elimnar</th>
                   <th style="width: 1%">Actualizar</th>
                 
             </tr>
         </thead>
         <tbody>
           
         </tbody>
     </table>

         </div>
     </div>
     </div>
     </form><!--fin formulario-->
                  
		  </div><!--**********-->
 <!----------------------------registro precio------------------------------------->
 <div class="modal fade" id="idModalRegistraPrecio">
 <div class="modal-dialog" style="width: 70%">
     <!-- Modal content-->
     <div class="modal-content">
         <div class="modal-header" style="padding: 35px 50px">
             
             <h4><span class="glyphicon glyphicon-ok-sign"></span> Registrar Precio</h4>
         </div>
         <div class="modal-body" style="padding: 20px 10px;">
             <form id="crudFormRegistrarPrecios" accept-charset="UTF-8" action="registrarUsuario" class="form-horizontal" method="post">
                 <div class="panel-group" id="steps">
                     <!-- Step 1 -->
                     <div class="panel panel-default">
                         <div class="panel-heading">
                             <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Asignacion de precios a los a origenes y destinos</a></h4>
                         </div>
                         <div id="stepOne" class="panel-collapse collapse in">
                             <div class="panel-body">
                                 <!-- Agrupación de campos en una fila -->
                                 <div class="form-group form-inline text-center">
                                     <!-- Ubigeo Origen -->
                                     <div class="form-group col-md-3">
                                         <label for="id_departamento">DEPARTAMENTO Ori</label>
                                         <select id="id_departamento" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Departamento]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="id_provincia">PROVINCIA Ori</label>
                                         <select id="id_provincia" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Provincia]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="id_municipio">MUNICIPIO Ori</label>
                                         <select id="id_municipio" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Municipio]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="idUbigeoorigen">ALMACEN Ori</label>
                                         <select id="idUbigeoorigen" name="ubigeoorigen.idUbigeoorigen" class='form-control green-text'>
                                             <option value="">[Seleccione Almacen]</option>
                                         </select>
                                     </div>
                                 </div>

                                 <!-- Ubigeo Destino -->
                                 <div class="form-group form-inline text-center">
                                     <div class="form-group col-md-3">
                                         <label for="id_departamento1">DEPARTAMENTO Dest</label>
                                         <select id="id_departamento1" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Departamento]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="id_provincia1">PROVINCIA Dest</label>
                                         <select id="id_provincia1" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Provincia]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="id_municipio1">MUNICIPIO Dest</label>
                                         <select id="id_municipio1" name="" class='form-control green-text'>
                                             <option value="">[Seleccione Municipio]</option>
                                         </select>
                                     </div>

                                     <div class="form-group col-md-3">
                                         <label for="idUbigeodestino">ALMACEN Dest</label>
                                         <select id="idUbigeodestino" name="ubigeodestino.idUbigeodestino" class='form-control green-text'>
                                             <option value="">[Seleccione Almacen]</option>
                                         </select>
                                     </div>
                                 </div>

                                 <!-- Otros campos como Nombre, Apellido, etc. -->
                                 <div class="form-group text-center">
                                 <!-- Primera fila: Labels -->
                                 <div class="row">
                                     <div class="col-md-2">
                                         <label for="id_reg_normalMinima">Kg normal</label>
                                     </div>
                                     <div class="col-md-2">
                                         <label for="id_reg_normalM3">normal m3</label>
                                     </div>
                                     <div class="col-md-1">
                                         <label for="id_reg_normalKgAdicional">kg adicional</label>
                                     </div>
                                     <div class="col-md-1">
                                         <label for="id_reg_tiempoNormal">tiempo</label>
                                     </div>
                                     <div class="col-md-2">
                                         <label for="id_reg_expresMinimo">kg expres</label>
                                     </div>
                                     <div class="col-md-2">
                                         <label for="id_reg_expresM3">expres m3</label>
                                     </div>
                                     <div class="col-md-1">
                                         <label for="id_reg_expresKgAdicional">kg adicional</label>
                                     </div>
                                     <div class="col-md-1">
                                         <label for="id_reg_tiempoExpres">tiempo</label>
                                     </div>
                                 </div>

                                 <!-- Segunda fila: Inputs -->
                                 <div class="row">
                                     <div class="col-md-2">
                                         <input class="form-control input-sm" id="id_reg_normalMinima" name="normalMinima" placeholder="kg normal" type="text" />
                                     </div>
                                     <div class="col-md-2">
                                         <input class="form-control input-sm" id="id_reg_normalM3" name="normalM3" placeholder="normal m3" type="text" />
                                     </div>
                                     <div class="col-md-1">
                                         <input class="form-control input-sm" id="id_reg_normalKgAdicional" name="normalKgAdicional" placeholder="kg adicional" type="text" />
                                     </div>
                                     <div class="col-md-1">
                                         <input class="form-control input-sm" id="id_reg_tiempoNormal" name="tiempoNormal" placeholder="tiempo" type="text" />
                                     </div>
                                     <div class="col-md-2">
                                         <input class="form-control input-sm" id="id_reg_expresMinimo" name="expresMinimo" placeholder="kg expres" type="text" />
                                     </div>
                                     <div class="col-md-2">
                                         <input class="form-control input-sm" id="id_reg_expresM3" name="expresM3" placeholder="expres m3" type="text" />
                                     </div>
                                     <div class="col-md-1">
                                         <input class="form-control input-sm" id="id_reg_expresKgAdicional" name="expresKgAdicional" placeholder="kg adicional" type="text" />
                                     </div>
                                     <div class="col-md-1">
                                         <input class="form-control input-sm" id="id_reg_tiempoExpres" name="tiempoExpres" placeholder="tiempo" type="text" />
                                     </div>
                                 </div>
                             </div>
                             <div id="resultado3"></div>

                                 <!-- Botones -->
                                 <div class="form-group text-center">
                                     <button type="button" class="btn btn-primary" id="idRegistrarPrecio" onclick="crudRegistraPrecio()" disabled >REGISTRAR</button>
                                     <button type="button" class="btn btn-warning" style="width: 100px" id="idCancelarRegistrarPrecio">CANCELAR</button>
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

<!----------------------------/registro precio------------------------------------->
<!----------------------------Actualiza precio------------------------------------->
<div class="modal fade" id="idModalActualizarPrecio">
<div class="modal-dialog" style="width: 70%">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header" style="padding: 35px 50px">
            
            <h4><span class="glyphicon glyphicon-ok-sign"></span> Actualizar Precio a la ruta</h4>
        </div>
        <div class="modal-body" style="padding: 20px 10px;">
            <form id="crudFormActualizarPrecios" accept-charset="UTF-8" action="registrarUsuario" class="form-horizontal" method="post">
                <div class="panel-group" id="steps">
                    <!-- Step 1 -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Actualizacion de precios a los a origenes y destinos</a></h4>
                        </div>
                        <div id="stepOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <!-- Agrupación de campos en una fila -->
                                <div class="form-group form-inline text-center">
                                    <div id="descripcion_ubigeoorigen"></div>
                                </div>

                                <!-- Ubigeo Destino -->
                                <div class="form-group form-inline text-center">
                                   <div id="descripcion_ubigeodestino"></div>
                                </div>

                                <!-- Otros campos como Nombre, Apellido, etc. -->
                                <div class="form-group text-center">
                                <!-- Primera fila: Labels -->
                                <div class="row">
                                
                                <div class="col-md-1">
                                <label for="idPrecio">id</label>
                                </div>
                                    <div class="col-md-2">
                                        <label for="id_act_normalMinima">Kg normal</label>
                                    </div>
                                    <div class="col-md-1">
                                        <label for="id_act_normalM3">normal m3</label>
                                    </div>
                                    <div class="col-md-1">
                                        <label for="id_act_normalKgAdicional">kg adicional</label>
                                    </div>
                                    <div class="col-md-1">
                                        <label for="id_act_tiempoNormal">tiempo</label>
                                    </div>
                                    <div class="col-md-2">
                                        <label for="id_act_expresMinimo">kg expres</label>
                                    </div>
                                    <div class="col-md-2">
                                        <label for="id_act_expresM3">expres m3</label>
                                    </div>
                                    <div class="col-md-1">
                                        <label for="id_act_expresKgAdicional">kg adicional</label>
                                    </div>
                                    <div class="col-md-1">
                                        <label for="id_act_tiempoExpres">tiempo</label>
                                    </div>
                                </div>

                                <!-- Segunda fila: Inputs -->
                                <div class="row">
	                                
	                                <div >
	                                <input class="form-control input-sm" id="id_idUbigeoorigen" name="idUbigeoorigen"   type="hidden" />
	                                </div>
	                                <div>
	                                   <input class="form-control input-sm" id="id_idUbigeodestino" name="idUbigeodestino" type="hidden" />
	                                </div>
                                
                                
                                
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="idPrecio" name="idPrecio" readonly="readonly" placeholder="kg normal" type="text" />
                                    </div>
                                    <div class="col-md-2">
                                        <input class="form-control input-sm" id="id_act_normalMinima" name="normalMinima" placeholder="kg normal" type="text" />
                                    </div>
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="id_act_normalM3" name="normalM3" placeholder="normal m3" type="text" />
                                    </div>
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="id_act_normalKgAdicional" name="normalKgAdicional" placeholder="kg adicional" type="text" />
                                    </div>
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="id_act_tiempoNormal" name="tiempoNormal" placeholder="tiempo" type="text" />
                                    </div>
                                    <div class="col-md-2">
                                        <input class="form-control input-sm" id="id_act_expresMinimo" name="expresMinimo" placeholder="kg expres" type="text" />
                                    </div>
                                    <div class="col-md-2">
                                        <input class="form-control input-sm" id="id_act_expresM3" name="expresM3" placeholder="expres m3" type="text" />
                                    </div>
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="id_act_expresKgAdicional" name="expresKgAdicional" placeholder="kg adicional" type="text" />
                                    </div>
                                    <div class="col-md-1">
                                        <input class="form-control input-sm" id="id_act_tiempoExpres" name="tiempoExpres" placeholder="tiempo" type="text" />
                                    </div>
                                </div>
                            </div>
                            <div id="resultado3"></div>

                                <!-- Botones -->
                                <div class="form-group text-center">
                                    <button type="button" class="btn btn-primary" id="idActualizarPrecio" onclick="actualizarPrecio()"  >ACTUALIZAR</button>
                                    <button type="button" class="btn btn-warning" style="width: 100px" id="idCancelarActualizarPrecio" data-dismiss="modal">CANCELAR</button>
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
<!----------------------------/Actualiza precio------------------------------------->

		<!--esteeeeeeeeeee-->

		<div class="modal fade" id="idModalElimina">
		<div class="modal-dialog" style="width: 60%">
		    <!--Model content-->
		    <div class="modal-content">
		        <div class="modal-header" style="padding: 35px 50px">
		            <button type="button" class="close " data-dismiss="modal">&times; </button>
		            <h4><span class="glyphicon glypicon-or-sing"></sp<n> Elimiar Personal </h4>
		        </div>
		        <div class="modal-footer">
		            <button type="button" id="idBtnElimina" name="modalDe" class="btn btn-primary" style="width: 100px"> Eliminar</button>
		            <button type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">Cancelar</button>
		        </div>
		    </div>
		</div>
		</div>


		<!--esteeeeeeeeeee-->
</div>


</script>
<!--aqui esta todo relacionado con el ubigeo*************************************-->

<script type="text/javascript">
$.getJSON("listaDepartamentos",{},function(data){
	 $.each(data, function(i,item){
		 $("#id_departamento").append("<option value='"+ item +"'>"+item +"</option>");
	 });
});
$("#id_departamento").change(function(){
	var var_dep=$("#id_departamento").val();
	$("#id_provincia").empty();
	$("#id_provincia").append(" <option value=''>[Seleccione Provincia]</option>");
	
	$("#id_municipio").empty();
	$("#id_municipio").append(" <option value=''>[Seleccione Municipio]</option>");
	
	$("#idUbigeoorigen").empty();
	$("#idUbigeoorigen").append(" <option value=''>[Seleccione Almacen]</option>");
	
	$.getJSON("listaProvincias",{"departamento":var_dep},function(data){
		 $.each(data, function(i,item){
			 $("#id_provincia").append("<option value='"+ item +"'>"+item +"</option>");
		 });
	});
});

$("#id_provincia").change(function(){
	 var var_dep=$("#id_departamento").val();
	 var var_pro=$("#id_provincia").val();
	 
	 $("#id_municipio").empty();
		$("#id_municipio").append(" <option value=''>[Seleccione Municipio]</option>");
		$("#idUbigeoorigen").empty();
		$("#idUbigeoorigen").append(" <option value=''>[Seleccione Almacen]</option>");
		
	$.getJSON("listaMunicipios",{"departamento":var_dep,"provincia":var_pro},function(data){
		 $.each(data, function(i,item){
			 $("#id_municipio").append("<option value='"+ item +"'>"+item +"</option>");
		 });
	});
});



$("#id_municipio").change(function(){
	
	 var var_dep=$("#id_departamento").val();
	 var var_pro=$("#id_provincia").val();
	 var var_mun=$("#id_municipio").val();
	 
	 $("#idUbigeoorigen").empty();
		$("#idUbigeoorigen").append(" <option value=''>[Seleccione Almacen]</option>");
		$.getJSON("listaAlmacenes",{"departamento":var_dep,"provincia":var_pro,"municipio":var_mun},function(data){
		 $.each(data, function(i,item){
			 $("#idUbigeoorigen").append("<option value='"+ item.idUbigeoorigen +"'>"+ item.almacen +"</option>");
		 });
	});
	
});




<!--AQUI EL DESTINO -->
$.getJSON("listaDepartamentos1",{},function(data){
	 $.each(data, function(i,item){
		 $("#id_departamento1").append("<option value='"+ item +"'>"+item +"</option>");
	 });
});

$("#id_departamento1").change(function(){
	var var_dep=$("#id_departamento1").val();
	$("#id_provincia1").empty();
	$("#id_provincia1").append(" <option value=''>[Seleccione Provincia]</option>");
	
	$("#id_municipio1").empty();
	$("#id_municipio1").append(" <option value=''>[Seleccione Municipio]</option>");
	
	$("#idUbigeodestino").empty();
	$("#idUbigeodestino").append(" <option value=''>[Seleccione Almacen]</option>");
	
	$.getJSON("listaProvincias1",{"departamento":var_dep},function(data){
		 $.each(data, function(i,item){
			 $("#id_provincia1").append("<option value='"+ item +"'>"+item +"</option>");
		 });
	});
});

$("#id_provincia1").change(function(){
	 var var_dep=$("#id_departamento1").val();
	 var var_pro=$("#id_provincia1").val();
	 
	 $("#id_municipio1").empty();
		$("#id_municipio1").append(" <option value=''>[Seleccione Municipio]</option>");
		
		$("#idUbigeodestino").empty();
		$("#idUbigeodestino").append(" <option value=''>[Seleccione Almacen]</option>");
		
		
	$.getJSON("listaMunicipios1",{"departamento":var_dep,"provincia":var_pro},function(data){
		 $.each(data, function(i,item){
			 $("#id_municipio1").append("<option value='"+ item +"'>"+item +"</option>");
		 });
	});
});



$("#id_municipio1").change(function(){
	
	 var var_dep=$("#id_departamento1").val();
	 var var_pro=$("#id_provincia1").val();
	 var var_mun=$("#id_municipio1").val();
	 
	 $("#idUbigeodestino").empty();
		$("#idUbigeodestino").append(" <option value=''>[Seleccione Almacen]</option>");
		
	 
	$.getJSON("listaAlmacenes1",{"departamento":var_dep,"provincia":var_pro,"municipio":var_mun},function(data){
		 $.each(data, function(i,item){
			 $("#idUbigeodestino").append("<option value='"+ item.idUbigeodestino +"'>"+ item.almacen +"</option>");
		 });
	});
	
});


</script>
    
</body>
</html>
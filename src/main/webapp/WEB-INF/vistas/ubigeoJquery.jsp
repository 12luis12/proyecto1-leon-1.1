<jsp:include page="intranetValida.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="private">
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Pragma" content="no-cache">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>CONZZARTRANS S.R.L </title>
</head>
<body> 

 <div class="container">
		       <form  id="id_form" method="post"  >
		       <div class="col-md-8" style ="margin-top:2%">
		        <div class="row">
		               <h4>ORIGEN</h4>
						<div class="form-group col-md-3">
						 	 <label class="control-label" for="id_departamento" >DEPARTAMENTO</label>
							 <select id="id_departamento" name="" class='form-control'>
							 <option value="">[Seleccione Departamento]</option>
							 </select>
						 </div>
						
						<div class="form-group col-md-3">
							 <label class="control-label" for="id_provincia" >PROVINCIA</label>
							 <select id="id_provincia" name="" class='form-control '>
							 <option value="">[Seleccione Provincia]</option>
							 </select>
						 </div>
					    
					    <div class="form-group col-md-3">
					    	 <label class="control-label" for="id_municipio">MUNICIPIO</label>
							 <select id="id_municipio" name="" class='form-control '>
							 <option value="">[Seleccione Minicipio]</option>
							 </select>
					   </div>
					   <div class="form-group col-md-3">
					    	 <label class="control-label" for="id_almacen" placeholder=".col-xs-2">ALMACEN</label>
							 <select id="id_almacen" name="ubigeoorigen.idUbigeoorigen" class='form-control'>
							 <option value="">[Seleccione Almacen]</option>
							 </select>
						</div>
				     </div>    <!--div row -->
				    <!--AQUI PONER DESTINO inicio-->
				    <div class="row">
				    <h4>DESTINO</h4>
					<div class="form-group col-md-3">
					 	 <label class="control-label" for="id_departamento1" >DEPARTAMENTO</label>
						 <select id="id_departamento1" name="" class='form-control'>
						 <option value="">[Seleccione Departamento]</option>
						 </select>
					  </div>
					
					<div class="form-group col-md-3">
						 <label class="control-label" for="id_provincia1" >PROVINCIA</label>
						 <select id="id_provincia1" name="" class='form-control'>
						 <option value="">[Seleccione Provincia]</option>
						 </select>
					 </div>
				    
				    <div class="form-group col-md-3">
				    	 <label class="control-label" for="id_municipio1">MUNICIPIO</label>
						 <select id="id_municipio1" name="" class='form-control'>
						 <option value="">[Seleccione Minicipio]</option>
						 </select>
				   </div>
				   <div class="form-group col-md-3">
				    	 <label class="control-label" for="id_almacen1" placeholder=".col-xs-2">ALMACEN</label>
						 <select id="id_almacen1" name="ubigeodestino.idUbigeodestino" class='form-control'>
						 <option value="">[Seleccione Almacen]</option>
						 </select>
			       </div>
				   </div><!--div row -->
				    <!--/AQUI PONER DESTINO-->
				   
				   <div class="col-md-12" style ="margin-top:2%">
			        <div class="row">
			            <h4>PRECIOS</h4>
			            <div class="form-group col-md-3">
							<label class="control-label" for="id_normalMinima">precio</label>
							<input class="form-control" type="text" id="id_normalMinima" name="normalMinima" placeholder="Ingrese el nombre" maxlength="40">    
					     </div>
					    
			        </div><!--div row -->
			        <div class="row">
				        <div class="form-group col-md-12" align="center">
				        	<button id="id_registrar" type="button" class="btn btn-primary"> Registrar</button>
				        </div>
			        </div><!--div row -->
			        </div>
				</form>	
</div>






<script type="text/javascript">
$("#id_registrar").click(function(){
	$.ajax({
		type:'POST',
		data: $("#id_form").serialize(),
		url: 'registroCliente',
		success: function(data){
			mostrarMensaje(data.MENSAJE);
		},
		error: function(){
			mostrarMensaje(MSG_ERROR);
		}
	});
});



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
	
	$("#id_almacen").empty();
	$("#id_almacen").append(" <option value=''>[Seleccione Almacen]</option>");
	
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
		
		$("#id_almacen").empty();
		$("#id_almacen").append(" <option value=''>[Seleccione Almacen]</option>");
		
		
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
	 
	 $("#id_almacen").empty();
		$("#id_almacen").append(" <option value=''>[Seleccione Almacen]</option>");
		
	 
	$.getJSON("listaAlmacenes",{"departamento":var_dep,"provincia":var_pro,"municipio":var_mun},function(data){
		 $.each(data, function(i,item){
			 $("#id_almacen").append("<option value='"+ item.idUbigeoorigen +"'>"+ item.almacen +"</option>");
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
	
	$("#id_almacen1").empty();
	$("#id_almacen1").append(" <option value=''>[Seleccione Almacen]</option>");
	
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
		
		$("#id_almacen1").empty();
		$("#id_almacen1").append(" <option value=''>[Seleccione Almacen]</option>");
		
		
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
	 
	 $("#id_almacen1").empty();
		$("#id_almacen1").append(" <option value=''>[Seleccione Almacen]</option>");
		
	 
	$.getJSON("listaAlmacenes1",{"departamento":var_dep,"provincia":var_pro,"municipio":var_mun},function(data){
		 $.each(data, function(i,item){
			 $("#id_almacen1").append("<option value='"+ item.idUbigeodestino +"'>"+ item.almacen +"</option>");
		 });
	});
	
});

<!--DESTINO 1-->

</script>
</body>
</html> 







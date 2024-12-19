<jsp:include page="intranetValida.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>CONZZARTRANS S.R.L </title>
</head>
<body> 

 <div class="container">
 <h3>Crud Ubigeo Origen</h3>
		 <div class="col-md-23" >  <!--**********-->
                    <form id="idFormElimina" action="eliminaCrudUbigeoorigen">
                       <input type="hidden" id="id_elimina" name="id"/>
                    </form>
                   <form accept-charset="UTF-8" action="consultaCrudUbigeoorigen" class="simple_form"> <!--formulario-->
				   <div class="row" >
						<div class="col-md-2" >
								<input class="form-control" id="id_nombre_filtro"  name="filtro" placeholder="Ingrese el Departaemnto" type="text" maxlength="30"/>
						</div>
						<div class="col-md-2" >
							<button type="submit" class="btn btn-primary" id="validateBtnw1" style="width: 150px">FILTRA</button>
						</div>
						<div class="col-md-2">
							<button type="button" data-toggle='modal'  onclick="registrar();"class="btn btn-warning" style="width: 150px">REGISTRA</button>
						</div>
						
						<div class="col-md-4">
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
						<div class="col-md-12">
								<div class="content" >
						
									<table id="tableUbigeoorigen" class="table table-striped table-bordered" >
										<thead>
											<tr>
												<th style="width: 5%" >ID</th>
												<th style="width: 8%">Departamento</th>
												<th style="width: 8%">Provincia</th>
												<th style="width: 8%">Municipio</th>
												<th style="width: 8%">Almacen</th>
												<th style="width: 10%">Actualiza</th>
												<th style="width: 10%">Elimina</th>
											</tr>
										</thead>
											<tbody>
											 <c:forEach items="${sessionScope.ubigeoorigenes}" var="x">
											   <tr>
											     <td>${x.idUbigeoorigen}</td>
											     <td>${x.departamento}</td>
											     <td>${x.provincia}</td>
											     <td>${x.municipio}</td>
											     <td>${x.almacen}</td>
											     <td>
											         <button type='button' data-toggle='modal' onclick="editar('${x.idUbigeoorigen}','${x.departamento}','${x.provincia}','${x.municipio}','${x.almacen}');" class='btn btn-success' style='background-color:hsla(233,100%,100%,0);'>
											          <img src='images/edit.gif' width="auto" height='auto'/>
											          </button>
											      </td>
											     <td>
											         <button type='button' data-toggle='modal' onclick="eliminar('${x.idUbigeoorigen}');" class='btn btn-success' style='background-color:hsl(233,100%,100%,0);'>
											          <img src='images/delete.gif' width="auto" height='auto'/>
										             </button>
											     </td>
											   </tr>
											   </c:forEach>
											</tbody>
										</table>
								</div>	
						</div>
					</div>
				</form><!--fin formulario-->
		  </div><!--**********-->
  
  	 <div class="modal fade" id="idModalRegistra" >
			<div class="modal-dialog" style="width: 60%">
		
				<!-- Modal content-->
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Registrar Ubigeo Origen</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_registra" accept-charset="UTF-8" action="registrarActualizarCrudUbigeoorigen" class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        <!-- Step 1 -->
		                        <div class="panel panel-default">
		                            <div class="panel-heading">
		                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos Ubigeo Origen</a></h4>
		                            </div>
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_departamento">Departamento</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_reg_departamento" name="departamento" placeholder="Ingr Depto" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_provincia">Provincia</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_reg_provincia" name="provincia" placeholder="Ingr provincia" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_municipio">Municipios</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_reg_municipio" name="municipio" placeholder="Ingr Municipio" type="text" />
		                                        </div>
		                                    </div>		   
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_almacen">Almacenes</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_reg_almacen" name="almacen" placeholder="Ingr Almacen" type="text" />
		                                        </div>
		                                    </div>   
		                                      
		                                    
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="submit" class="btn btn-primary" id="idBtnRegistra">REGISTRA</button>
		                                        	<button type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">CANCELAR</button>
		                    						
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
  
		 <div class="modal fade" id="idModalActualiza" >
			<div class="modal-dialog" style="width: 60%">
		
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Ubigeo Origen</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_actualiza" accept-charset="UTF-8"  action="registrarActualizarCrudUbigeoorigen" class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        <!-- Step 1 -->
		                        <div class="panel panel-default">
		                            <div class="panel-heading">
		                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos Ubigeo Origen</a></h4>
		                            </div>
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_ID">ID</label>
		                                        <div class="col-lg-6">
		                                           <input class="form-control" id="id_ID" readonly="readonly" name="idUbigeoorigen" type="text" />
		                                        </div>
		                                     </div>
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_departamento">Departamento</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_departamento" name="departamento" placeholder="Ingrese el Departamento" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_provincia"> Provincias</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_provincia" name="provincia" placeholder="Ingrese las provincias" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_municipio"> Municipios</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_municipio" name="municipio" placeholder="Ingrese los municipios" type="text" />
		                                        </div>
		                                    </div>		   
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_almacen">Almacen</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_almacen" name="almacen" placeholder="Ingrese el alamcen" type="text" />
		                                        </div>
		                                    </div>   
		                                    		                                    
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="submit" class="btn btn-primary" id="idBtnActualiza">ACTUALIZA</button>
		                                        	<button type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">CANCELAR</button>
		                    						
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
		<!--esteeeeeeeeeee-->

		<div class="modal fade" id="idModalElimina">
		<div class="modal-dialog" style="width: 60%">
		    <!--Model content-->
		    <div class="modal-content">
		        <div class="modal-header" style="padding: 35px 50px">
		            <button type="button" class="close " data-dismiss="modal">&times; </button>
		            <h4><span class="glyphicon glypicon-or-sing"></sp<n> Elimiar la Ruta </h4>
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

<script type="text/javascript">
$("#success-alert").fadeTo(2000,500).slideUp(500, function(){
	$("#success-alert").slideUp(500);
});
</script>




<script type="text/javascript">


$("#idBtnElimina").click(function(){
           $("#idFormElimina").submit();
       });

 function eliminar(id) {
           $('#idModalElimina').modal('show');
           $('#id_elimina').val(id);
       }

function registrar() {
	$('#idModalRegistra').modal("show")
}




function editar(id,departamento,provincia,municipio,almacen){	
	$('input[id=id_ID]').val(id);
	$('input[id=id_act_departamento]').val(departamento);
	$('input[id=id_act_provincia]').val(provincia);
	$('input[id=id_act_municipio]').val(municipio);
	$('input[id=id_act_almacen]').val(almacen);
	
	$('#idModalActualiza').modal("show");
}

$(document).ready(function(){
	$('#tableUbigeoorigen').DataTable();
});

function limpiarFormulario(){	
	$('#id_reg_departamento').val('');
	$('#id_reg_provincia').val('');
	$('#id_reg_municipio').val('');
	$('#id_reg_alamcen').val('');
	
}







</script>

<script type="text/javascript">
	$('#id_form_registra').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	"departamento": {
        		selector : '#id_reg_departamento',
                validators: {
                    notEmpty: {
                        message: 'El Departamento es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El nombre es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"provincia": {
        		selector : '#id_reg_provincia',
                validators: {
                    notEmpty: {
                        message: 'La provincia es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El nombre es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"municipio": {
        		selector : '#id_reg_municipio',
                validators: {
                    notEmpty: {
                        message: 'El Municipio es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El municipio es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"almacen": {
        		selector : '#id_reg_alamcen',
                validators: {
                    notEmpty: {
                        message: 'El Almacen es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El Almacen es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
            
           
        	
        }   
    });
</script>

<script type="text/javascript">
	$('#id_form_actualiza').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	"departamento": {
        		selector : '#id_act_departamento',
                validators: {
                    notEmpty: {
                        message: 'El departamento es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El departamento es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
        	"provincia": {
        		selector : '#id_act_provincia',
                validators: {
                    notEmpty: {
                        message: 'El provincia es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El provincia es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
        	"municipio": {
        		selector : '#id_act_municipio',
                validators: {
                    notEmpty: {
                        message: 'El municipio es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El municipio es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
            
        	"almacen": {
        		selector : '#id_act_almacen',
                validators: {
                    notEmpty: {
                        message: 'El almacen es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El almacen es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
            
            
          
        }   
    });
</script>
    
</body>
</html> 
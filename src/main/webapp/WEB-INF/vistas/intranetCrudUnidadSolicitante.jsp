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
 <h3>Crud Cliente </h3>
		 <div class="col-md-23" >  <!--**********-->
                    <form id="idFormElimina" action="eliminaCrudUnidadsolicitante">
                       <input type="hidden" id="id_elimina" name="id"/>
                    </form>
                   <form accept-charset="UTF-8" action="consultaCrudUnidadsolicitante" class="simple_form"> <!--formulario-->
				   <div class="row" >
						<div class="col-md-2" >
								<input class="form-control" id="id_nombre_filtro"  name="filtro" placeholder="Ingrese nombre Cliente" type="text" maxlength="30"/>
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
						
									<table id="tableCliente" class="table table-striped table-bordered" >
										<thead>
											<tr>
												<th style="width: 5%" >ID</th>
												<th style="width: 15%">Nombre Unidad Solicitante</th>
												<th style="width: 8%">Unidad Solicitante</th>
												<th style="width: 8%">Poyecto</th>
												<th style="width: 10%">ACTUALIZAR</th>
												<th style="width: 10%">ELIMINAR</th>
											</tr>
										</thead>
											<tbody>
											 <c:forEach items="${sessionScope.unidadsolicitantes}" var="x">
											   <tr>
											     <td>${x.idUnidadsolicitante}</td>
											     <td>${x.nombre}</td>
											     <td>${x.unidadSolicitante}</td>
											     <td>${x.proyecto}</td>
											       <td>
											         <button type='button' data-toggle='modal' onclick="editar('${x.idUnidadsolicitante}','${x.nombre}','${x.unidadSolicitante}','${x.proyecto}');" class='btn btn-success' style='background-color:hsla(233,100%,100%,0);'>
											          <img src='images/edit.gif' width="auto" height='auto'/>
											          </button>
											      </td>
											     <td>
											         <button type='button' data-toggle='modal' onclick="eliminar('${x.idUnidadsolicitante}');" class='btn btn-success' style='background-color:hsl(233,100%,100%,0);'>
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
		  <div class="modal fade" id="idModalUnidadSolicitanteRegistra" tabindex="-1" role="dialog">
	        <div class="modal-dialog modal-sm">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                    <h4 class="modal-title" id="myModal-label">Unidad Solicitante</h4>
	                </div>
	                <div class="modal-body">
	                    <form id="id_form_registra" accept-charset="UTF-8" action="registrarActualizarCrudUnidadSolicitante" class="form-horizontal"     method="post">
	                        <div class="panel-group" id="steps">
	                            <!-- Step 1 -->
	                            <div class="panel panel-default">
	                                <div class="panel-heading">
	                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">registro de la unidad solictante </a></h4>
	                                </div>
	                                <div id="stepOne" class="panel-collapse collapse in">
	                                    <div class="panel-body">
	                                         <div class="form-group">
	                                            <label class="col-lg-3 control-label" for="id_reg_nombreUnidadSolicitante">Nombrede Unidad Solicitante  </label>
	                                            <div class="col-lg-12">
	                                                <input class="form-control" id="id_reg_nombreUnidadSolicitante" name="nombre" placeholder="Nombre de la persona de la unidad Solicitante" type="text" />
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-lg-3 control-label" for="id_reg_unidadSolicitante">Unidad Solicitante</label>
	                                            <div class="col-lg-12">
	                                                <input class="form-control" id="id_reg_unidadSolicitante" name="unidadSolicitante" placeholder="unidad Solicitante" type="text" />
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-lg-3 control-label" for="id_reg_proyecto">proyecto</label>
	                                            <div class="col-lg-12">
	                                                <input class="form-control" id="id_reg_proyecto" name="proyecto" placeholder="proyecto" type="text" />
	                                            </div>
	                                        </div>		   
	                                          
	                                        
	                                        <div class="modal-footer">
	                                            <button  type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">Cerrar</button>
	                                            <button type="submit" class="btn btn-primary" id="idBtnRegistra">Guardar</button>
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
  	<!----------------------------------------------------------->
  	<div class="modal fade" id="idModalUnidadSolicitanteActualiza" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModal-label">Unidad Solicitante</h4>
            </div>
            <div class="modal-body">
                <form id="id_form_actualiza" accept-charset="UTF-8" action="registrarActualizarCrudUnidadSolicitante" class="form-horizontal"     method="post">
                    <div class="panel-group" id="steps">
                        <!-- Step 1 -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">registro de la unidad solictante </a></h4>
                            </div>
                            <div id="stepOne" class="panel-collapse collapse in">
                                <div class="panel-body">
		                                <div class="form-group">
			                                <label class="col-lg-3 control-label" for="id_ID">ID</label>
			                                <div class="col-lg-6">
			                                   <input class="form-control" id="id_ID" readonly="readonly" name="idUnidadsolicitante" type="text" />
			                                </div>
		                                 </div>
                                     <div class="form-group">
                                        <label class="col-lg-3 control-label" for="id_act_nombreUnidadSolicitante">Nombrede Unidad Solicitante  </label>
                                        <div class="col-lg-12">
                                            <input class="form-control" id="id_act_nombreUnidadSolicitante" name="nombre" placeholder="Nombre de la persona de la unidad Solicitante" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-3 control-label" for="id_act_unidadSolicitante">Unidad Solicitante</label>
                                        <div class="col-lg-12">
                                            <input class="form-control" id="id_act_unidadSolicitante" name="unidadSolicitante" placeholder="unidad Solicitante" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-3 control-label" for="id_act_proyecto">proyecto</label>
                                        <div class="col-lg-12">
                                            <input class="form-control" id="id_act_proyecto" name="proyecto" placeholder="proyecto" type="text" />
                                        </div>
                                    </div>		   
                                      
                                    
                                    <div class="modal-footer">
                                        <button  type="button" class="btn btn-warning" style="width: 100px" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary" id="idBtnActualiza">Actualizar</button>
                                    </div>                             
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </form> 
            </div>
            
        </div>
    </div>
</div
	<!----------------------------------------------------------->
  
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
	$('#idModalUnidadSolicitanteRegistra').modal("show")
}




function editar(id,nombre,unidadSolicitante,proyecto){	
	$('input[id=id_ID]').val(id);
	$('input[id=id_act_nombreUnidadSolicitante]').val(nombre);
	$('input[id=id_act_unidadSolicitante]').val(unidadSolicitante);
	$('input[id=id_act_proyecto]').val(proyecto);
	
	
	$('#idModalUnidadSolicitanteActualiza').modal("show");
}

$(document).ready(function(){
	$('#tableCliente').DataTable();
});

function limpiarFormulario(){	
	$('#id_reg_nombreUnidadSolicitante').val('');
	$('#id_reg_unidadSolicitante').val('');
	$('#id_reg_proyecto').val('');
	
	
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
        	"nombre": {
        		selector : '#id_reg_nombre',
                validators: {
                    notEmpty: {
                        message: 'El Nombre es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El nombre es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"apellido": {
        		selector : '#id_reg_apellido',
                validators: {
                    notEmpty: {
                        message: 'el apellido es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El apellido es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"telefono": {
        		selector : '#id_reg_telefono',
                validators: {
                    notEmpty: {
                        message: 'El telefono es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El telefono es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
        	"celular": {
        		selector : '#id_reg_celular',
                validators: {
                    notEmpty: {
                        message: 'El celular es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El celular es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 100
                    }
                }
            },
            
        	"correo": {
        		selector : '#id_reg_correo',
                validators: {
                    notEmpty: {
                        message: 'El correo es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El correo  es de 5 a 100 caracteres',
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
        	"nombre": {
        		selector : '#id_act_nombre',
                validators: {
                    notEmpty: {
                        message: 'El nombre es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El nombrees de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
        	"apellido": {
        		selector : '#id_act_apellido',
                validators: {
                    notEmpty: {
                        message: 'El apellido es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El apellido es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
        	"telefono": {
        		selector : '#id_act_telefono',
                validators: {
                    notEmpty: {
                        message: 'El telefono es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El telefono es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
            
        	"celular": {
        		selector : '#id_act_celular',
                validators: {
                    notEmpty: {
                        message: 'El celular es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El celular es de 5 a 100 caracteres',
                    	min : 5,
                    	max : 40
                    }
                }
            },
        	"correo": {
        		selector : '#id_act_correo',
                validators: {
                    notEmpty: {
                        message: 'El correo es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El correo es de 5 a 100 caracteres',
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
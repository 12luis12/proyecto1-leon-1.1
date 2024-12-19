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

<script type="text/javascript" src="js/crudUsuario.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>CONZZARTRANS S.R.L </title>
</head>
<body> 

 <div class="container">
 <h3>Crud Cliente </h3>
		 <div class="col-md-23" >  <!--**********-->
		 
		 
    <form  accept-charset="UTF-8" action="listarUsuario" > 
     <div class="row">
	         <div class="col-md-1">
		       <button type="button" class="btn btn-success" id="idBtlimpiar" onclick="registrar();">Registrar</button>
		     </div>
	 </div>

     <div class="row">
     <div class="col-md-12">
         <div class="content ">

         <table id="tableUsuario" class="table table-bordered">
         <thead class="header-1">
             <tr>
                 <th style="width: 2%">id</th>
                 <th style="width: 10%">nombre apellido</th>
                 <th style="width: 2%">correo</th>
                 <th style="width: 10%">usuario</th>
                  <th style="width: 5%">password</th>
                  <th style="width: 3%">ELIMINAR</th>
                 <th style="width: 3%">EDITAR</th>
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
  
  	 <div class="modal fade" id="idModalRegistraUsuario" >
			<div class="modal-dialog" style="width: 60%">
		
				<!-- Modal content-->
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Registrar Usuario</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_registra_usuario" accept-charset="UTF-8" action="registrarUsuario" class="form-horizontal"     method="post">
		                    <div class="panel-group" id="steps">
		                        <!-- Step 1 -->
		                        <div class="panel panel-default">
		                            <div class="panel-heading">
		                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos Ubigeo Origen</a></h4>
		                            </div>
		                            <div id="stepOne" class="panel-collapse collapse in">
		                                <div class="panel-body">
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_nombre">nombre</label>
		                                        <div class="col-lg-8">
													<input class="form-control" id="id_reg_nombre" name="nombre" placeholder="nombre" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_apellido">apellido</label>
		                                        <div class="col-lg-3">
													<input class="form-control" id="id_reg_apellido" name="apellido" placeholder="apellido" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_login">login</label>
		                                        <div class="col-lg-3">
													<input class="form-control" id="id_reg_login" name="login" placeholder="login" type="text" />
		                                        </div>
		                                    </div>		   
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_password">password</label>
		                                        <div class="col-lg-3">
													<input class="form-control" id="id_reg_password" name="password" placeholder="pasword" type="password" />
		                                        </div>
		                                    </div>   
		                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label" for="id_reg_repetir_password"> repetir password</label>
	                                        <div class="col-lg-3">
												<input class="form-control" id="id_reg_repetir_password" name="RepetirPassword" placeholder="repetir pasword" type="password" />
	                                        </div>
	                                    </div> 
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_reg_correo">correo</label>
		                                        <div class="col-lg-3">
													<input class="form-control" id="id_reg_correo" name="correo" placeholder="correo" type="text" />
		                                        </div>
	                                        </div>  
	                                     
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="button" class="btn btn-primary" onclick="registrarUsuario()">REGISTRA</button>
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
  
		 <div class="modal fade" id="idModalActualizaUsuario" >
			<div class="modal-dialog" style="width: 60%">
		
				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Ubigeo Origen</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						<form id="id_form_actualiza_usuario" accept-charset="UTF-8"  action="registrarActualizarCrudUsuario" class="form-horizontal"     method="post">
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
		                                           <input class="form-control" id="id_ID" readonly="readonly" name="idUsuario" type="text" />
		                                        </div>
		                                     </div>
		                                     <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_nombre">nombre</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_nombre" name="nombre" placeholder="Ingrese nombre" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_apellido"> apellido</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_apellido" name="apellido" placeholder="Ingrese apellido" type="text" />
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_num_login"> login</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_login" name="login" placeholder="Ingrese login" type="text" />
		                                        </div>
		                                    </div>		   
		                                    <div class="form-group">
		                                        <label class="col-lg-3 control-label" for="id_act_password">password</label>
		                                        <div class="col-lg-6">
													<input class="form-control" id="id_act_password" name="password" placeholder="deja en blanco si no quieres cambiar la contraseña " type="password" />
		                                        </div>
		                                    </div> 
		                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label" for="id_num_correo"> correo</label>
	                                        <div class="col-lg-6">
												<input class="form-control" id="id_act_correo" name="correo" placeholder="Ingrese correo" type="text" />
	                                        </div>
	                                    </div>	
	                                                         		                                    
		                                    <div class="form-group">
		                                        <div class="col-lg-9 col-lg-offset-3">
		                                        	<button type="button" href="#" class="btn btn-primary" onclick="actualizarUsuario()">ACTUALIZA</button>
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
    
</body>
</html>
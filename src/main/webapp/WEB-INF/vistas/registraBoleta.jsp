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

<script type="text/javascript" src="js/buscadorCliente.js"></script>
<script type="text/javascript" src="js/buscadorCliente1.js"></script>
<script type="text/javascript" src="js/registrarBoleta.js"></script>
<script type="text/javascript" src="js/cargarUnidadsolicitanteSelect.js"></script>
<script type="text/javascript" src="js/cargaselectClientedestino.js"></script>
<script type="text/javascript" src="js/selectUltimoRegistro.js"></script>
<script type="text/javascript" src="js/registraUnidadSolicitante.js"></script>
<script type="text/javascript" src="js/validarMaterial.js"></script>
<script type="text/javascript" src="js/guardaTodosMateriales.js"></script>
<script type="text/javascript" src="js/calculoPreciofinal.js"></script>
<script type="text/javascript" src="js/acumuladorboleta.js"></script>
<script type="text/javascript" src="js/verificarIdUbigeoOrigenyDestinoAsignadoPrecio.js"></script>
<script type="text/javascript" src="js/registrarPrecio.js"></script>
<script type="text/javascript" src="js/cargaLaTablaPreciosEnRegistrarBoleta.js"></script>
<script type="text/javascript" src="js/manejoBotones.js"></script>


<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<!-- Bootstrap -->
<!--<link rel="stylesheet" href="css/bootstrap.min.css" />--> 
<!-- Custom CSS -->
<link rel="stylesheet" href="css/interfaz.css" />
<link rel="stylesheet" href="css/colorverde.css" />
<link rel="stylesheet" href="css/aliniar.css" />
<link rel="stylesheet" href="css/colorCampostext.css" />

<title>CONZZARTRANS S.R.L</title>
</head>
<body>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarNavAltMarkup"
      aria-expanded="false">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">CONZZARTRANS S.R.L</a>
  </div>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Home</a></li>
      <li><a href="#">Features</a></li>
      <li><a href="#">Pricing</a></li>
    </ul>
    <form class="navbar-form navbar-right" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Buscar">
      </div>
      <button type="submit" class="btn btn-default">Buscar</button>
    </form>
  </div>
</div>
</nav>


<div class="container">
<h3>FORMULARIO DE REGISTRO DE UN CMM Y SUS RESPECTIVOS MATERIALES A TRANSPORTAR </h3>
 <div class="col-md-23">
   <!--Formulario1-->
   
  
<form accept-charset="UTF-8" action="consultarMaterialEstado" class="simple_form"> <!--formulario-->

<div class="row" >

<div class="form-group row">
<label class="col-lg-3 control-label" id="labelflujo" for="flujo">flujo</label>
<label class="col-lg-3 control-label" id="labelCmm" for="cmm">Cmm</label>
<label class="col-lg-3 control-label" id="Unidadsolicitante" for="Us">Unidad solicitante</label>
<label class="col-lg-3 control-label" id="numguia" for="guia">numguia</label>

</div>

<div class="form-group row">
<label class="col-lg-3 control-label" id="origen" for="origen">origen</label>
<label class="col-lg-3 control-label" id="destino" for="destino">destino</label>
<label class="col-lg-3 control-label" id="proforma" for="proforma">proforma</label>
</div>
	<div class="form-group">
		<div class="col-lg-2 col-lg-offset-6">
		<button type="button" id="idBtnRegistrarBoleta" data-toggle='modal'  onclick="registraBoleta()"class="btn btn-info" style="width: 150px">Registrar Boleta 1</button>
		</div>
	</div>
	<div class="row" >
	
	<div class="col-ms-4">
	  <button type="button" data-toggle='modal' id="idBtnRegistrarMaterial" onclick="registraMaterial()"class="btn btn-warning" style="width: 150px "  >Registrar Material 2</button>
	</div>
	
</div>


<div class="row" > 
	<div class="col-ms-12">
			<div class="content" >
				<table id="table_Material" class="table  table-striped table-bordered " >
					<thead>
						<tr>
						<th style="width: 5%" >ID</th>
						<th style="width: 15%" >Descripcion</th>
						<th style="width: 8%">Cantidad</th>
						<th style="width: 8%">Peso</th>
						<th style="width: 8%">Volmen M3</th>
						<th style="width: 15%">Observaciones</th>
						<th style="width: 10%">Estado</th>
						<th style="width: 10%">IdBoleta</th>
						<th style="width: 10%">eliminar</th>
						<th style="width: 10%">actualizar</th>
						</tr>
					</thead>
						<tbody>
										  
						</tbody>
					</table>
			     </div>	
	        </div>
        </div>
        <div class="form-group">
        <div class="col-lg-12 col-lg-offset-3">
          <button type="button" class="btn btn-primary" id="idBtnNuevoCmm"onclick="idBtncalcularPreciofinal(valoridBoleta);copiaMaterialLosCamposAcumuladoryDescripcionABoleta(valoridBoleta)" style="width: 150px" >terminar  registro 3</button>
        </div> 
      </div>
     </form><!--fin formulario-->
  
</div>
<!--/ppppp-->
<!--modal registrar  Material -->
<div class="modal fade" id="idModalMaterialRegistra">
  <div class="modal-dialog" style="width: 60%">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header" style="padding: 45px 60px">
       
        </button>
        <h4>
          <span class="glyphicon glyphicon-ok-sign"></span> Registrar el
          Material
        </h4>
      </div>
      <div class="modal-body" style="padding: 20px 10px">
        <form id="id_form_registra_material" accept-charset="UTF-8" action="registraMaterial"
          class="form-horizontal" method="post">
          <div class="panel-group" id="steps">
            <!-- Step 1 -->
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos de Material</a>
                </h4>
              </div>
              <div id="stepOne" class="panel-collapse collapse in">
                <div class="panel-body">
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="descripcion">descripcion del material</label>
                    <div class="col-lg-6">
                    <textarea class="form-control" id="descripcion" name="descripcion" placeholder="Descripcion " rows="3"></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="peso">Peso Kg</label>
                    <div class="col-lg-3">
                      <input class="form-control" id="peso" name="peso" placeholder="peso kg"
                        type="text" />
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="cantidad">cantidad</label>
                     <div class="col-lg-3">
                    <input class="form-control" id="cantidad" name="cantidad" placeholder="cantidad"
                      type="text" />
                  </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="volumen">Volumen M3</label>
                    <div class="col-lg-3">
                      <input class="form-control" id="volumen" name="volumen" placeholder="volumen"
                        type="text" />
                    </div>
                  </div>
                  
                  <div class="form-group row">
                  <label class="col-lg-3 control-label" for="flexRadioDefault11">Cobrar por Kg</label>
                  <div class="col-lg-4">
                    <input class="form-control" type="radio" name="cobrarpor" id="flexRadioDefault11" value="0"checked>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-lg-3 control-label" for="flexRadioDefault22">Cobrar por M3</label>
                  <div class="col-lg-4">
                    <input class="form-control" type="radio" name="cobrarpor" id="flexRadioDefault22" value="1" >
                  </div>
                </div>
                  
                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label" for="observaciones">observaciones</label>
                    <div class="col-lg-6">
                    <textarea class="form-control" id="observaciones" name="observaciones" placeholder="Observacion " rows="3"></textarea>
                    </div>
                  </div>
                  
                  
                  <!--aqui va ir el idBoleta---------------------------------->
                  
		                  <div class="form-group">
		                  <label class="col-lg-3 control-label" for="id_reg_estado"></label>
		                  <div class="col-lg-6">
		                    <input class="form-control" id="id_reg_estado" value="0" name="estado" placeholder="oculto"
		                      type="hidden" />
		                  </div>
		                </div>
                  
                  
		                
		                <div class="form-group">
		                  <label class="col-lg-3 control-label" for="idBoleta">id boleta</label>
		                  <div class="col-lg-6">
		                  <select id="idBoleta" name="boleta.idBoleta"class="form-control" onclick="recargarSelectUltimoRegistro()">
		                    <option value=" ">[Sleccione]</option>
		                    </select>
		                  </div>
		                </div>
		                
	                <!--/aqui va ir el idBoleta----------------------------------->
                  <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-3">
                      <button type="button" class="btn btn-primary" id="idBtnRegistraMaterial" onclick="RegistrarFormularioMaterial()" >Registra</button>
                      <button type="button" class="btn btn-warning" style="width: 100px" id="idBtnLimpiaMaterial">Cancelar</button>
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
<!--/modal registrar Material-->
</div>

<!-----------------------------------------------modal Actualizar  Material -->
<div class="modal fade" id="idModalMaterialActualiza1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 40%">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding: 45px 60px">
            <h4>
            <span class="glyphicon glyphicon-ok-sign"></span> Actualiza el
            Material
          </h4>
        </div>
        <div class="modal-body" style="padding: 20px 10px">
          <form id="id_form_actualiza_material" accept-charset="UTF-8" action="guardarMaterialActualizado" class="form-horizontal" method="post">
            <div class="panel-group" id="steps">
              <!-- Step 1 -->
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos de Material a Actualizar</a>
                  </h4>
                </div>
                <div id="stepOne" class="panel-collapse collapse in">
                  <div class="panel-body">
                    <div class="form-group">
                        <div class="col-lg-6">
                            <input class="form-control" id="act_idMaterial" name="idMaterial" placeholder="material" type="hidden" />
                        </div>
                    </div>
                    <div class="form-group">
                    <div class="col-lg-6">
                        <input class="form-control" id="act_idBoleta" name="boleta.idBoleta" placeholder=" " type="hidden"  />
                    </div>
                    </div>

	                  <div class="form-group">
	                     <div class="col-lg-6">
	                    <input class="form-control" id="act_estado" value="estado" name="estado" placeholder="oculto" type="hidden" />
	                  </div>
	                </div>
	         
                    <div class="form-group">
                      <label class="col-lg-3 control-label" for="descripcion">descripcion del material</label>
                      <div class="col-lg-9">
                      <textarea class="form-control" id="act_descripcion" name="descripcion" placeholder="Descripcion " rows="3"></textarea>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-lg-3 control-label" for="peso">Peso Kg</label>
                      <div class="col-lg-4">
                        <input class="form-control" id="act_peso" name="peso" placeholder="peso kg"
                          type="text" />
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-lg-3 control-label" for="cantidad">cantidad</label>
                       <div class="col-lg-4">
                      <input class="form-control" id="act_cantidad" name="cantidad" placeholder="cantidad"
                        type="text" />
                    </div>
                    </div>
                    <div class="form-group">
                      <label class="col-lg-3 control-label" for="volumen">Volumen M3</label>
                      <div class="col-lg-4">
                        <input class="form-control" id="act_volumen" name="volumen" placeholder="volumen"
                          type="text" />
                      </div>
                    </div>
                    
                    <div class="form-group row">
                    <label class="col-lg-3 control-label" for="flexRadioDefault11">Cobrar por Kg</label>
                    <div class="col-lg-4">
                      <input class="form-control" type="radio" name="cobrarpor" id="act_flexRadioDefault11" value="0">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-lg-3 control-label" for="flexRadioDefault22">Cobrar por M3</label>
                    <div class="col-lg-4">
                      <input class="form-control" type="radio" name="cobrarpor" id="act_flexRadioDefault22" value="1" >
                    </div>
                  </div>
                                       
                    <div class="form-group">
                      <label class="col-lg-3 control-label" for="observaciones">observaciones</label>
                      <div class="col-lg-9">
                      <textarea class="form-control" id="act_observaciones" name="observaciones" placeholder="Observacion " rows="3"></textarea>
                      </div>
                    </div>
                    <div id="mensaje"></div>
                    <div class="form-group">
                      <div class="col-lg-9 col-lg-offset-3">
                        
                        <button type="button" class="btn btn-warning" style="width: 100px" id="idBtnLimpiaActuMaterial">Cancelar</button>
                        <button type="button"  class="btn btn-primary" onclick="guardarActualizacionMaterial6();actualizaElPrecioFinal(document.getElementById('idBoleta').value);">Registra</button>
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
  <!-------------------------------------modal Actualizar  Material -->





<!----------------------------modal registro de Boleta-->
<div class="modal fade" id="idModalBoletaRegistra" tabindex="-1" role="dialog">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModal-label">
                Contacto Origen o Destino
            </h4>
        </div>
        <div class="modal-body">
            <form id="id_form_registra_boleta" accept-charset="UTF-8" action="guardarBoleta"
                class="form-horizontal " method="post">
                <div class="panel-group" id="steps">
                    <!-- Step 1 -->
                    <div class="panel panel-default">

                        <!---****  row2 *****--->
                        <div class="form-row">
                            <!---****  2.1 *****--->

                            <div class="form-group col-md-2">
                            <label for="idUnidadsolicitante1"> </label>
                                        <button type="button" data-toggle='modal' onclick="registraUnidadsolicitante();"
                                            class="btn btn-success" style="width: 75px">U.solic</button>

                            </div>
                            <div class="form-group col-md-3">
                            <label for="idUnidadsolicitante">Unidad Solicitante</label>
                                        <select id="idUnidadsolicitante" name="unidadsolicitante.idUnidadsolicitante"
                                            onclick="recargarSelectunidadsolicitante()" class="form-control green-text">
                                            <option value="">[Seleccione Unidad Solicitante]</option>
                                        </select>

                           </div>
                           <div class="form-group col-md-2">
                           <label for="idalgo"> </label>
                           <button type="button" data-toggle='modal' onclick="registraCliente();"
                               class="btn btn-info" style="width: 75px">Contacto</button>

                          </div>
                          <div class="form-group col-md-3">
                          <label for="idCliente">Contacto Origen</label>
                          <select id="idCliente" name="cliente.idCliente" class="form-control green-text"
                              onclick="recargarSelect()">
                              <option value="">[Seleccione]</option>
                          </select>

                      </div>
                      <div class="form-group col-md-3">
                      <label for="idClientedestino">Contacto Destino</label>
                                  <select id="idClientedestino" name="clientedestino.idClientedestino"
                                      class="form-control green-text" onclick="recargarSelectClientedestino()">
                                      <option value="">[Seleccione]</option>
                                  </select>

                  </div>



                            <!---****  /2.1 *****--->
                        </div>
                        <!---****  /row2 *****--->

                        <!------------------------------------------------------------------------------------------------------------------------------------------------->

                        <!---****  row3 *****--->
                        <div class="form-row">
                             
		                        <div>
			                      <input type="hidden" class="form-control green-text" id="idUsuario" name="usuario.idUsuario" value="1" />
		                       </div>
		                       <div>
			                      <input type="hidden" class="form-control green-text" id="idUsuario2" name="usuario2.idUsuario" value="1" />
		                       </div>
                        
                        
                            <div class="form-group col-md-2">
                                 <label for="flujo">flujo</label>
                                <input type="text" class="form-control green-text" id="flujo" name="flujo" placeholder="No Flujo" />
                            </div>
                            <div class="form-group col-md-2">
                                <label for="cmm">No cmm</label>
                                <input type="text" class="form-control green-text " id="cmm" name="cmm" placeholder="No c.m.m" />
                            </div>
                            <div class="form-group col-md-2">
                                <label for="numguia">No guia</label>
                                <input type="text" class="form-control green-text" id="numguia" name="numguia" placeholder="No guia" readonly="readonly"/>
                            </div>
                            <div class="form-group col-md-1">
	                            
	                            <input type="hidden" class="form-control green-text" id="botonConsolidacion" name="botonConsolidacion" value="0" />
                            </div>
                            <div class="form-group col-md-1">
                            
                            <input type="hidden" class="form-control green-text" id="estadoFirma" name="estadoFirma" value="0" />
                           </div>
                            <div class="form-group col-md-3">
                                <label for="fechaOrigen">fecha horigen </label>
                                <input type="date" class="form-control green-text" id="fechaOrigen"
                                    name="fechaOrigen" placeholder="fecha Destino" />
                            </div>

                            <div class="form-group col-md-3">
                                <label for="tipoTransporte">tipo transporte</label>
                                <select id="tipoTransporte" class="form-control green-text" name="tipoTransporte">
                                    <option value=" ">[Tipo de transporte]</option>
                                    <option value="1">Interdepartamental</option>
                                    <option value="2">Urbano</option>
                                    <option value="3">Rural</option>
                                </select>
                            </div>
                        </div>
                        <!---****  /row3 *****--->

                        <!---****  /row4 *****--->
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="observacionOrigenEntel" class="form-label">observacion Origen</label>
                                <textarea class="form-control green-text" id="observacionOrigenEntel"
                                    name="observacionOrigenEntel" placeholder="Alguna Observacion en Origen"
                                    rows="3"></textarea>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="observacionDestinoEntel" class="form-label">observacion destino</label>
                                <textarea class="form-control green-text" id="observacionDestinoEntel"
                                    name="observacionDestinoEntel" placeholder="Alguna Observacion en destino"
                                    rows="3"></textarea>
                            </div>
                        </div>
                        <!---****  /row4 *****--->
                        <!---****  /Ubigeo origen  ***************************************--->

                        <div class="form-row">
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
                                <label for="id_municipio">MUNICIPIO</label>
                                <select id="id_municipio" name="" class='form-control green-text'>
                                    <option value="">[Seleccione Minicipio]</option>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="idUbigeoorigen">ALMACEN</label>
                                <select id="idUbigeoorigen" name="ubigeoorigen.idUbigeoorigen"
                                    class='form-control green-text'>
                                    <option value="">[Seleccione Almacen]</option>
                                </select>
                            </div>
                        </div>
                        <!---****  /obigeo origen *****--->
                        <!---****  ubigeo destino************************ *****--->

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="id_departamento1">DEPARTAMENTO</label>
                                <select id="id_departamento1" name="" class='form-control green-text'>
                                    <option value="">[Seleccione Departamento]</option>
                                </select>
                            </div>

                            <div class="form-group col-md-3">
                                <label for="id_provincia1">PROVINCIA</label>
                                <select id="id_provincia1" name="" class='form-control green-text'>
                                    <option value="">[Seleccione Provincia]</option>
                                </select>
                            </div>

                            <div class="form-group col-md-3">
                                <label for="id_municipio1">MUNICIPIO</label>
                                <select id="id_municipio1" name="" class='form-control green-text'>
                                    <option value="">[Seleccione Minicipio]</option>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="idUbigeodestino" placeholder=".col-xs-2">ALMACEN</label>
                                <select id="idUbigeodestino" name="ubigeodestino.idUbigeodestino"
                                    class='form-control green-text'>
                                    <option value="">[Seleccione Almacen]</option>
                                </select>
                            </div>
                           
                            
                            
                            <div class="form-group col-md-3">
                            <label class="col-lg-3 control-label" for="flexRadioDefault1">Transporte por Carretera</label>
                            <div class="col-lg-8">
                              <input class="form-control" type="radio" name="prioridadtransporte" id="flexRadioDefault1" value="0">
                            </div>
                          </div>
                          <div class="form-group col-md-3">
                            <label class="col-lg-3 control-label" for="flexRadioDefault2">Transporte por Avion </label>
                            <div class="col-lg-8">
                              <input class="form-control" type="radio" name="prioridadtransporte" id="flexRadioDefault2" value="1" >
                            </div>
                          </div>

                        </div>
                        <div class="row">
                        
                        <div class="col-md-6 text-right">
                        <button type="button" class="btn btn-default" id="idBtnlimpia2" data-dismiss="modal">Cerrar</button>
                        
                            <button type="button" class="btn btn-primary" id="idBtnRegistrar2" onclick="BtnRegistrarBoleta()" >Guardar</button>
                        </div>
                    </div
                    </div>

                </div>


            </form>
        </div>
    </div>
</div>
</div>
<!---------------------------/modal Boleta-->

<!---modal formulario precio---------------------------------------->

<div class="modal fade" id="modalRegistrarPreciosEnBoleta" tabindex="-1" role="dialog">
<div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
        <div class="modal-header" style="padding: 15px 10px">
            <h5 class="modal-title" id="modalRegistrarPreciosLabel">Registrar Precios</h5>
        </div>
        <div class="modal-body">
            <!-- Formulario para registrar precios -->
            <form id="formRegistrarPrecios"  class="formulario-amarillo">
                <div class="form-row">
                    <!-- Campos en una sola línea -->
                    <div class="form-group col-md-2">
                        <label for="normalMinima">Minimo 1 a 500 kg (Normal)</label>
                        <input type="text" id="normalMinima" name="normalMinima" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="normalM3">Cobrar por m3 (Normal)</label>
                        <input type="text" id="normalM3" name="normalM3" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="normalKgAdicional">kg adicional</label>
                        <input type="text" id="normalKgAdicional" name="normalKgAdicional" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="tiempoNormal">Tiempo entrega</label>
                        <input type="text" id="tiempoNormal" name="tiempoNormal" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="expresMinimo">Minimo de 1 a 500 kg (Expres)</label>
                        <input type="text" id="expresMinimo" name="expresMinimo" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="expresM3">Cobrar por m3 (Expres)</label>
                        <input type="text" id="expresM3" name="expresM3" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="expresKgAdicional">kg adicional</label>
                        <input type="text" id="expresKgAdicional" name="expresKgAdicional" class="form-control campo-gris">
                    </div>
                    <div class="form-group col-md-1">
                        <label for="tiempoExpres">Tiempo expres</label>
                        <input type="text" id="tiempoExpres" name="tiempoExpres" class="form-control campo-gris">
                    </div>
                </div>
                <div id="resultado3"></div>

                <!-- Botones alineados a la derecha -->
                <div class="text-right">
                    <button type="button" id="guardarPrecioEnBoleta"class="btn btn-primary" onclick="registraPreciosEnBoleta()" >Guardar</button>
                    <button type="button" class="btn btn-default" id="botonCancelarPrecioEnBoleta">Cerrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>


<!---/modal formulario precio---------------------------------------->

<!----------------modal para registrar cliente---------------------->
<div class="modal fade" id="idModalClienteRegistra" tabindex="-1" role="dialog">
<div class="modal-dialog modal-md">
    <div class="modal-content">
        <div class="modal-header">
             <h4 class="modal-title" id="myModal-label">Contacto Origen o Destino</h4>
        </div>
        <div class="modal-body">
            <form id="id_form_registra_cliente" accept-charset="UTF-8" action="registrarActualizarCrudCliente1" class="form-horizontal"     method="post">
                <div class="panel-group" id="steps">
                    <!-- Step 1 -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Registra el persona de contacto origen o destino </a></h4>
                        </div>
                        <div id="stepOne" class="panel-collapse collapse in">
                            <div class="panel-body">
			                          <div class="form-group">
			                            <div class="col-lg-6">
			                                <input class="form-control" id="idCliente" name="idCliente" placeholder="nombre" type="hidden"/>
			                            </div>
			                        </div>
			                        
			                        
                                 <div class="form-group ">
                                    <label class="col-lg-3 control-label" for="nombre">nombre</label>
                                    <div class="col-lg-6">
                                        <input class="form-control" id="nombre" name="nombre" placeholder="nombre" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" for="apellido">apellido</label>
                                    <div class="col-lg-6">
                                        <input class="form-control" id="apellido" name="apellido" placeholder="apellido" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                <label class="col-lg-3 control-label" for="celular">celular</label>
                                <div class="col-lg-6">
                                    <input class="form-control" id="celular" name="celular" placeholder="celular" type="text" />
                                 </div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" for="telefono">telefono</label>
                                    <div class="col-lg-6">
                                        <input class="form-control" id="telefono" name="telefono" placeholder="telefono" type="text" />
                                    </div>
                                </div>		   
                                 
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" for="correo">correo</label>
                                    <div class="col-lg-6">
                                        <input class="form-control" id="correo" name="correo" placeholder="correo" type="text" />
                                    </div>
                                </div>  
                                <div id="mensaje"></div>
                                <div class="modal-footer">
                                <button  type="button" class="btn btn-default" style="width: 100px" id="idBtnlimpia1">Cerrar</button>
                                <button type="button" class="btn btn-primary" id="idBtnRegistra1">GuardarActualizar</button>
                               
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
<!-----------/modal para registrar cliente ------------------------------->


<!-----------/modal para registrar unidad Solicitante ------------------------------->
<div class="modal fade" id="idModalUnidadsolicitanteRegistra2" tabindex="-1" role="dialog">
<div class="modal-dialog modal-md">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModal-label">Unidad Solicitante</h4>
        </div>
        <div class="modal-body">
            <form id="id_form_registra_unidadSolicitante" accept-charset="UTF-8" action="guardarUnidadsolicitante" class="form-horizontal"     method="post">
                <div class="panel-group" id="steps">
                    <!-- Step 1 -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Registra de Unidad Solicitante </a></h4>
                        </div>
                        <div id="stepOne" class="panel-collapse collapse in">
                            <div class="panel-body">
		                            <div class="form-group">
		                            <div class="col-sm-10">
		                                <input class="form-control" id="idUnidadsolicitante" name="idUnidadsolicitante" placeholder="idUnidadsolicitante" type="hidden"/>
		                              </div>
		                            </div>

		                            
                                 <div class="form-group">
                                    <label class="col-lg-3 control-label" for="id_reg_nombre">Nombre  </label>
                                    <div class="col-sm-10">
                                        <input class="form-control" id="id_reg_nombre" name="nombre" placeholder="Nombre " type="text" />
                                    </div>
                                </div>
	                                <div class="form-group">
	                                <label class="col-lg-3 control-label" for="id_reg_apellido">Apellido  </label>
	                                <div class="col-sm-10">
	                                    <input class="form-control" id="id_reg_apellido" name="apellido" placeholder="Apellido" type="text" />
	                                </div>
	                              </div>
	                              <div class="form-group">
		                            <label class="col-lg-3 control-label" for="id_reg_celular">Celular  </label>
		                            <div class="col-sm-10">
		                                <input class="form-control" id="id_reg_celular" name="celular" placeholder="Celular" type="text" />
		                            </div>
		                           </div>
	                              	                           
		                           <div class="form-group">
		                            <label class="col-lg-3 control-label" for="id_reg_correo">Correo</label>
		                            <div class="col-sm-10">
		                                <input class="form-control"type="email" id="id_reg_correo" name="correo" placeholder="Correo"  />
		                            </div>
		                           </div>
		                           
		                           
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" for="unidadSolicitante">U.Solicitante</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" id="unidadSolicitante" name="unidadSolicitante" placeholder="unidad Solicitante" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" for="proyecto">proyecto</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" id="proyecto" name="proyecto" placeholder="proyecto" type="text" />
                                    </div>
                                </div>	
                              
                                <div class="modal-footer">
                                <button  type="button" class="btn btn-default" style="width: 100px" id="idBtnLimpiaUnidadS">Cancelar</button>
                                <button type="button" class="btn btn-primary" id="idBtnRegistraUnidadS">Guardar</button>
                               
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
<!-----------/modal para registrar unidad solicitante  ------------------------------->



<script type="text/javascript">
  $("#success-alert").fadeTo(2000,500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
  });
  </script>

<script type="text/javascript">


/*$(document).ready(function() {
    // Deshabilitar botón 1 y habilitar botón 2 y botón 3
    $('#btn1').prop('disabled', true);
    $('#btn2, #registrarboleta2').prop('disabled', false);

    // Habilitar botón 1 y deshabilitar botón 2 al dar clic en botón 3
    $('#registrarboleta2').on('click', function() {
        $('#btn1').prop('disabled', false);
        $('#btn2').prop('disabled', true);
    });
});*/





/*$.getJSON("consultarBoletaUltimoRegistro", {}, function(data){
	
	$.each(data, function(index,item){
		
		$("#idBoleta").append("<option value="+item+ ">" +item+"</option>");
		
		
	});
	
});*/


//$.getJSON("cargaCliente", {}, function(data){
	//$.each(data, function(index,item){
	//	$("#id_cliente").append("<option value="+item.idCliente +">"+ item.nombre +"</option>");
		
//	});
//});

/*$.getJSON("cargaClientedestino", {}, function(data){
	$.each(data, function(index,item){
		$("#id_clientedestino").append("<option value="+item.idClientedestino +">"+ item.nombreCompleto +"</option>");
		
	});
});*/

/*$.getJSON("cargaUnidadsolicitante", {}, function(data){
	$.each(data, function(index,item){
		$("#id_unidadsolicitante").append("<option value="+item.idUnidadsolicitante +">"+ item.nombreUnidadSolicitante +"</option>");
		
	});
});*/


$('#idBtnRegistraBoleta1').click(function () {
    $('#actualizaCampoEstadoMaterial1').val();
});


/*$("#idBtnRegistraMaterial").click(function(){
	$.ajax({
		type:'POST',
		url: 'actualizaCampoEstadoMaterial',
		
	});
});
*/

function registraBoleta() {
$("#idModalBoletaRegistra").modal('show')
}

function registraCliente() {
	$('#idModalClienteRegistra').modal("show")
}

function registraMaterial() {
$("#idModalMaterialRegistra").modal('show')
}
function registraUnidadsolicitante() {
$("#idModalUnidadsolicitanteRegistra2").modal('show')
}
$(document).ready(function(){
	$('#tableMaterial').DataTable();
});

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

<!--aqui esta todo relacionado con el ubigeo*************************************-->

</body>
</html>

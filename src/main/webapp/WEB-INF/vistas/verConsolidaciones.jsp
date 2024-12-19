<jsp:include page="intranetValida.jsp" />
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <!DOCTYPE html>
    <html lang="esS">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/bootstrapValidator.js"></script>
        <script type="text/javascript" src="js/global.js"></script>
        
        <script type="text/javascript" src="js/actualizarMaterial.js"></script>
        <script type="text/javascript" src="js/actualizaPrecioFinal.js"></script>
        <script type="text/javascript" src="js/muestraTablaConPrecioActualizado.js"></script>
        <script type="text/javascript" src="js/cambiaDeEstado.js"></script>
        <script type="text/javascript" src="js/verModalConsolidacion.js"></script>
      
        <script type="text/javascript" src="js/muestraTablaPrincipalConsolidaciones1.js"></script>
        <script type="text/javascript" src="js/acumuladorboleta.js"></script>
        
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
        <link rel="stylesheet" href="css/bootstrapValidator.css" />
        <link rel="stylesheet" href="css/paraElModalConsolidacion.css" />
       
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/pintardeColorlasFilas.css">
        <link rel="stylesheet" href="css/novarconsolidacion.css">
        
        <title>CONZZARTRANS S.R.L </title>

    </head>

    <body>
    <nav class="navbar navbar-custom">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarNav" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">CONZZARTRANS S.R.L</a>
        </div>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Inicio <span class="sr-only">(actual)</span></a></li>
                <li><a href="#">Servicios</a></li>
                <li><a href="#">Precios</a></li>
                <li><a href="#">Contacto</a></li>
            </ul>
        </div>
    </div>
</nav>
        <div class="container">
            <h3>CONZZARTRANS S.R.L</h3>
            <h3>Consolidacion de precios de transporte pesado Enetel S.A</h3>
            <div class="col-md-12"> <!--**********-->
            <form id="id_form1" method="GET">
            <!-- Campo oculto para el idBoleta -->
            <input type="hidden" id="id_boleta" name="idBoleta" value="">
            </form>
            <form  id="id_form" > 

            <div class="row">
                <div class="col-md-2">
                  <input class="form-control" id="id_busca_cmm" name="cmm"placeholder="No CMM" type="text" maxlength="30" />
                </div>
                <div class="col-lg-3">
                   <div class="form-group">
                    <label class="col-lg-4 control-label" for="id_fechadesde">Desde</label>
                    <div class="col-lg-8">
                        <input type="date" id="id_fechadesde" name="fechaDesde" placeholder="Fecha inicio" class="form-control">
                    </div>
                    </div>
                </div>

        <div class="col-lg-3">
            <div class="form-group">
                <label class="col-lg-4 control-label" for="id_fechahasta">Hasta</label>
                <div class="col-lg-8">
                    <input type="date" id="id_fechahasta" name="fechaHasta" placeholder="Fecha final" class="form-control">
                </div>
            </div>
        </div>

           
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" id="idBtnBuscar">Buscar</button>
        </div>
        <div class="col-md-1">
          <button type="button" class="btn btn-success" id="idBtlimpiar" onclick="limpiarfecha();">Limpiar</button>
        </div>
        <div class="col-md-1">
            <div class="form-group">
            <button type="button" class="btn btn-primary" id="id_reporte" >PDF</button>
            </div>
        </div>
        </div>

        <div class="row">
        <div class="col-md-12">
            <div class="content ">

            <table id="tableGeneral" class="table table-bordered">
            <thead class="header-1">
                <tr>
                    <th style="width: 2%">No FLUJO</th>
                    <th style="width: 2%">C.M.M</th>
                    <th style="width: 2%">PROFORMA</th>
                    <th style="width: 10%">ORIGEN</th>
                    <th style="width: 10%">DESTINO</th>
                    <th style="width: 5%">FECHA</th>
                    <th style="width: 35%">DETALLE</th>
                    <th style="width: 1%"></th>
                    <th style="width: 5%">PRECIO</th>
                    <th style="width: 1%"></th>
                    <th style="width: 9%">UNIDAD SOLICIT</th>
                    <th style="width: 1%"></th>
                   
                    <th style="width: 3%">COSOLIDAR</th>
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

            <!---/modal precio-->
            <div class="modal fade" id="idModalPrecio" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" style="width:60%">
                    <div class="modal-content">
                        <div class="modal-header" style="padding: 15px 10px">
                           
                            <h4 class="modal-title" id="myModal-label">Precio Fijado en el Contrato</h4>
                        </div>
                        <div class="modal-body" style="padding: 20px 10px">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <table id="id_table_precio" class="table table-striped table-bordered ">
                                        <thead>
                                            <tr>

                                                <th class="danger" style="width: 12%">Minimo 1 a 500 Kg</th>
                                                <th class="danger" style="width:  12%">Por m3</th>
                                                <th class="danger" style="width: 12%">Por Kg adicional</th>
                                                <th style="width: 12%">tiempo de entregra( Dias)</th>
                                                <th class="danger" style="width: 12%">Minimo 1 a 500 Kg (Expres)</th>
                                                <th class="danger" style="width: 12%">por m3 (Expres)</th>
                                                <th class="danger" style="width: 12%">por Kg adicional(expres)</th>
                                                <th style="width: 18%">tiempo de entrega (dias) expres</th>

                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>

                        </div>
                    </div>
                </div>
            </div>
            <!---/modal precio---------------->
            
            
            
            
            <!---MODAL LISTA DE MATERIALES-->

            <div class="modal fade" id="idModalListaMaterial" data-backdrop="static" data-keyboard="false" >
            <div class="modal-dialog" style="width:60%">
                <div class="modal-content">
                    <div class="modal-header" style="padding: 15px 10px">
                       
                        <h4 class="modal-title" id="myModal-label">Material del cmm  x</h4>
                    </div>
                    <div class="modal-body" style="padding: 20px 10px;">
                    <div class="form-group">
                    <div class="col-lg-12">
                    <table id="id_table_lista_material" class="table table-striped table-bordered " >
                    <thead>
                        <tr >
                            
        					<th style="width: 40%">Descripcion</th>
        					<th style="width: 10%">peso kg</th>
        	                 <th style="width: 10%">Volumen</th>
        				    <th style="width: 11%">Cantidad</th>
        				    <th style="width: 24%">Observacion</th>
        				    <th style="width: 5%">Actualizar</th>					   
                       </tr>
                    </thead>
                        <tbody>
                         
                        </tbody>
                    </table>
                    </div>
                    </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="idBtnMuestraTablaConPrecioActualizado" >Cerrarr</button>
                        
                    </div>
                </div>
            </div>
        </div>
            <!---/MODAL LISTA DE MATERIALES-->


            <!---modal detalle general-------------------->
            <div class="modal fade" id="idBuscaCliente" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" style="width:80%">
                    <div class="modal-content">
                        <div class="modal-header" style="padding: 15px 10px">
                            
                            <h4 class="modal-title" id="myModal-label">Informe General Del Servicio de Transporte </h4>
                        </div>
                        <div class="modal-body">
                            <table id="id_table_cliente" class="table table-striped table-bordered ">
                                <thead>
                                    <tr class="table-success">
                                        <th style="width: 3%">No Flujo</th>
                                        <th style="width: 3%">C.M.M</th>
                                        <th style="width: 3%">No GUIA</th>
                                        <th style="width: 3%">No PROFORMA</th>
                                        <th style="width:  5%">FECHA ORIGEN</th>
                                        <th style="width: 5%">FECHA DESTINO</th>
                                        <th style="width: 10%">PERSONA CONT ORIGEN</th>
                                        <th style="width: 10%">PERSONA CONT DESTINO</th>
                                        <th style="width: 10%">UNIDAD SOLICITANTE</th>
                                     </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <table id="id_table_cliente1" class="table table-striped table-bordered ">
                                <thead>
                                    <tr class="table-success">
                                        <th style="width: 3%">CIUDAD ORIGEN</th>
                                        <th style="width: 3%">CIUDAD DESTINO</th>
                                        <th style="width: 8%">PRECIO EN Bs</th>
                                        <th style="width:  5%">TIPO TRANSPORTE</th>
                                        <th style="width: 20%">MATERIAL TRANSPORTADO</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>

            <!---/modal detalle general--------------------->
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
                                        <input class="form-control" id="idMaterial" name="idMaterial" placeholder="material" type="hidden" />
                                    </div>
                                </div>
                                <div class="form-group">
                                <div class="col-lg-6">
                                    <input class="form-control" id="idBoleta" name="boleta.idBoleta" placeholder=" " type="hidden"  />
                                </div>
                                </div>

      		                  <div class="form-group">
      		                     <div class="col-lg-6">
      		                    <input class="form-control" id="estado" value="estado" name="estado" placeholder="oculto" type="hidden" />
      		                  </div>
      		                </div>
      		              <div class="form-group row "style="display: none;">
      	                  <label class="col-lg-3 control-label" for="flexRadioDefault1">transporte Normal</label>
      	                  <div class="col-lg-4">
      	                    <input class="form-control" type="radio" name="tipotransporte" id="flexRadioDefault1" value="0">
      	                  </div>
      	                </div>
      	                <div class="form-group row" style="display: none;">
      	                  <label class="col-lg-3 control-label" for="flexRadioDefault2">transporte Expres</label>
      	                  <div class="col-lg-4">
      	                    <input class="form-control" type="radio" name="tipotransporte" id="flexRadioDefault2" value="1" >
      	                  </div>
      	                </div>
      		                
      		                
      		                
      		                
                                <div class="form-group">
                                  <label class="col-lg-3 control-label" for="descripcion">descripcion del material</label>
                                  <div class="col-lg-9">
                                  <textarea class="form-control" id="descripcion" name="descripcion" placeholder="Descripcion " rows="3"></textarea>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-3 control-label" for="peso">Peso Kg</label>
                                  <div class="col-lg-4">
                                    <input class="form-control" id="peso" name="peso" placeholder="peso kg"
                                      type="text" />
                                  </div>
                                </div>
                                
                                <div class="form-group">
                                  <label class="col-lg-3 control-label" for="cantidad">cantidad</label>
                                   <div class="col-lg-4">
                                  <input class="form-control" id="cantidad" name="cantidad" placeholder="cantidad"
                                    type="text" />
                                </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-3 control-label" for="volumen">Volumen M3</label>
                                  <div class="col-lg-4">
                                    <input class="form-control" id="volumen" name="volumen" placeholder="volumen"
                                      type="text" />
                                  </div>
                                </div>
                                
                                <div class="form-group row">
                                <label class="col-lg-3 control-label" for="flexRadioDefault11">Cobrar por Kg</label>
                                <div class="col-lg-4">
                                  <input class="form-control" type="radio" name="cobrarpor" id="flexRadioDefault11" value="0">
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
                                  <div class="col-lg-9">
                                  <textarea class="form-control" id="observaciones" name="observaciones" placeholder="Observacion " rows="3"></textarea>
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
  <!------------/modal consolidacion------------------------------------>
  <div id="idModalConsolidacion" class="modal fade" tabindex="-1" role="dialog"data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title">Formulario de Consolidaciones</h3>
      </div>
      <div class="modal-body">
        <form id="id_form_consolidacion" action="nose aun" class="form-horizontal" method="post">
          <div class="form-frame">

            <!-- Hidden inputs -->
            <div class="form-group">
              <div class="col-lg-6">
                <input class="form-control" id="act_1_idMaterial" name="idMaterial" type="hidden" />
              </div>
              <div class="col-lg-6">
                <input class="form-control" id="act_1_idBoleta" name="boleta.idBoleta" type="hidden" />
              </div>
            </div>

            <!-- Labels for the first row -->
            <div class="form-group">
              <div class="row">
                <label class="col-lg-3 control-label" id="labelflujo1" for="flujo">flujo</label>
                <label class="col-lg-3 control-label" id="labelCmm1" for="cmm">Cmm</label>
                <label class="col-lg-3 control-label" id="Unidadsolicitante1" for="Us">Unidad solicitante</label>
                <label class="col-lg-3 control-label" id="numguia1" for="guia">numguia</label>
                <label class="col-lg-3 control-label" id="proforma1" for="proforma">proforma</label>
              </div>
            </div>

            <!-- Labels for the second row -->
            <div class="form-group">
              <div class="row">
                <label class="col-lg-3 control-label" id="origen1" for="origen">origen</label>
                <label class="col-lg-3 control-label" id="destino1" for="destino">destino</label>
                
              </div>
            </div>

            <!-- Material and Observations -->
            <div class="form-group">
              <div class="row">
                <label for="descripcion" class="col-sm-6 control-label">Material</label>
                <label for="observaciones" class="col-sm-6 control-label">Observaciones</label>
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <textarea id="act_1_descripcion" class="form-control" name="descripcion" placeholder="Descripcion Material"></textarea>
                </div>
                <div class="col-sm-6">
                  <textarea id="act_1_observaciones" name="observaciones" class="form-control" placeholder="Observaciones"></textarea>
                </div>
              </div>
            </div>

            <!-- Peso, Cantidad, and Volumen inputs -->
            <div class="form-group">
              <div class="col-sm-4">
                <label for="peso" class="control-label">Peso Kg</label>
                <input  type="number" step="0.01" id="act_1_peso" name="peso" class="form-control" placeholder="Peso Kg" />
              </div>
              <div class="col-sm-4">
                <label for="cantidad" class="control-label">Cantidad</label>
                <input type="number" step="0.01" id="act_1_cantidad" name="cantidad" class="form-control" placeholder="Cantidad" />
              </div>
              <div class="col-sm-4">
                <label for="volumen" class="control-label">Volumen M3</label>
                <input type="number" step="0.01" id="act_1_volumen" name="volumen" class="form-control" placeholder="Volumen m3" />
              </div>
            </div>

            <!-- Radio buttons for Cobrar por -->
            <div class="form-group radio-buttons">
              <div class="col-sm-12">
                <label class="radio-inline">
                  <input type="radio" name="cobrarpor" id="act_1_flexRadioDefault13" value="0"> Cobrar por Kg
                </label>
                <label class="radio-inline" style="margin-left: 20px;">
                  <input type="radio" name="cobrarpor" id="act_1_flexRadioDefault23" value="1"> Cobrar por M3
                </label>
              </div>
            </div>

            <!-- Action buttons -->
            <div class="form-group">
              <div class="col-sm-12">
                <button type="button" class="btn btn-success btn-limpiar" onclick="limpiarFormulariModalConsolidacion()">Limpiar</button>
                <button type="button" class="btn btn-primary btn-guardar" id="btnActualizarConsolidcion"onclick="guardarMaterialActualizadoEnModalConsolidacion();limpiarFormulariModalConsolidacion()">Actualizar Material</button>
              </div>
            </div>
          </div>
        </form>

        <!-- Contenedor de la tabla en formato responsive -->
        <div class="full-width-item">
          <h3>Materiales relacionados con cmm:</h3>
          <div class="table-responsive">
            <table id="tableConsolidacion" class="table table-bordered">
              <thead class="header-1">
                <tr>
                  <th style="width: 5%">No</th>
                  <th style="width: 30%">Material</th>
                  <th style="width: 10%">Peso</th>
                  <th style="width: 10%">Volumen</th>
                  <th style="width: 10%">Cantidad</th>
                  <th style="width: 25%">Observacion</th>
                  <th style="width: 10%">Editar</th>
                </tr>
              </thead>
              <tbody>
                <!-- Aquí van las filas de la tabla -->
              </tbody>
            </table>
          </div>
          <button type="button" class="btn btn-primary btn-guardar"onclick="limpiarFormulariModalConsolidacion2()">Cerrar Formulario</button>
        </div>
      </div>
    </div>
  </div>
</div>


<!------------/modal consolidacion------------------------------------>
            <div>
                <script type="text/javascript">
                    $("#success-alert").fadeTo(2000, 500).slideUp(500, function () {
                        $("#success-alert").slideUp(500);
                    });
                </script>
                <script type="text/javascript">
                    function registrar() {
                        $('#idModalRegistra').modal("show")
                    }

                   


                    function editar(id, departamento, provincia, municipio, almacen) {
                        $('input[id=id_ID]').val(id);
                        $('input[id=id_act_departamento]').val(departamento);
                        $('input[id=id_act_provincia]').val(provincia);
                        $('input[id=id_act_municipio]').val(municipio);
                        $('input[id=id_act_almacen]').val(almacen);

                        $('#idModalActualiza').modal("show");
                    }

                   // $(document).ready(function () {
                     //   $('#tableGeneral').DataTable();
                    //});
                    //--------------------actualiza material--------------------
                     // function actualizaMaterial(id) {
                    //$("#idModalMaterialActualiza").modal("show");
                      //}
                    //----------------------------------------------
                    
                    
                    //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
                    function mostrarPrecio(id) {
                        muestraprecios1(id);
                        $("#idModalPrecio").modal("show");
                    }
                    function muestraprecios1(id) {
                        var var_precio = id;
                        // alert(id);
                        //limpiar la tabla
                        $("#id_table_precio tbody").empty();

                        //Se añade los clientes a la tabla
                        $.getJSON("consultaPrecio", { "filtro": var_precio }, function (data) {
                            $.each(data, function (index, item) {
                                $('#id_table_precio').append("<tr><td>" + parseFloat(item.normalMinima).toFixed(2) + "</td><td>" +parseFloat( item.normalM3).toFixed(2) + "</td><td>" + parseFloat(item.normalKgAdicional).toFixed(2) + "</td><td>" + item.tiempoNormal + "</td><td>" + parseFloat(item.expresMinimo).toFixed(2) + "</td><td>" + parseFloat(item.expresM3).toFixed(2) + "</td><td>" + parseFloat(item.expresKgAdicional).toFixed(2) + "</td><td>" + item.tiempoExpres + "</td></tr>");
                            });
                        });
                    }
                    //:::::::::::::::::::::::::::::::::..
                    
                    function mostrarDetalleMaterialdeuncliente(id){
                    	muestraMaterial(id)
                    	//alert(id);
                        $("#idModalListaMaterial").modal("show");

                    }
                    function muestraMaterial(id) {
                        var var_material = id;
                        $("#id_table_lista_material tbody").empty();
                        //Se añade los clientes a la tabla
                        $.getJSON("consultaMaterial", { "filtro": var_material }, function (data) {
                            $.each(data, function (index, item) {
                                $('#id_table_lista_material').append("<tr><td>" + item.descripcion + "</td><td>" + item.peso + "</td><td>" + item.volumen + "</td><td>" + item.cantidad + "</td><td>" + item.observaciones + "</td><td><button type='button' onclick='actualizaMaterial(" + item.idMaterial +");' class='btn btn-default' aria-label='Left Align' > <img src='images/imagen4.png' width=\"auto\" height='auto' /></button></td><tr>");
                               // $('#id_table_lista_material').append("<tr><td>" + item.descripcion + "</td><td>" + item.peso + "</td><td>" + item.volumen + "</td><td>" + item.cantidad + "</td><td>" + item.observaciones + "</td><td><button type='button' onclick='f_elimina_seleccion(" + item.idMaterial +");' class='btn btn-default' aria-label='Left Align' > <img src='images/imagen4.png' width=\"auto\" height='auto' /></button></td><tr>");
                                
                            });
                        });
                    }

      //::::::::::::::::modal consolidacion ::::::::::::::::::::::::::::::::::::
                    function mostrarDetalleGeneral(id) {
                        muestraCliente(id);
                        $("#idBuscaCliente").modal("show");
                       
                        
                       
                    }
                    function muestraCliente(id) {
                        var var_cliente = id;
                        // alert(id);
                        //limpiar la tabla
                        $("#id_table_cliente tbody").empty();

                        //Se añade los clientes a la tabla
                        $.getJSON("consultaListaGeneral", { "filtro": var_cliente }, function (data) {
                            $.each(data, function (index, item) {
                                $('#id_table_cliente').append('<tr><td>' + item.flujoConF + '</td><td>' + item.cmmConT + '</td><td>' + item.numguiaConNg + '</td><td>' + item.proformaConPF + '</td><td>' + item.fechaOrigen + '</td><td>' + item.fechaDestino + '</td><td>' + item.cliente.datosCompletoCliente + '</td><td>' + item.clientedestino.datosCompletoClientedestino + '</td><td>' + item.unidadsolicitante.datosCompletoUnidadSolicitante + '</td></tr>');
                            });
                        });

                        //---tabla numero --------------------------------
                        $("#id_table_cliente1 tbody").empty();

                        //Se añade los clientes a la tabla
                        var idBoleta = id; // Asumiendo que var_cliente es el idBoleta

                        $.getJSON("servicio/Material/listaMaterial/" + idBoleta, function(data) {
                            var origen, destino, precioServicio, tipoTransporteTexto;
                            var agrupaciones = ''; // Variable para acumular todas las agrupaciones (descripcion+peso+volumen+cantidad+observacion)

                            // Recorremos cada item de la respuesta
                            $.each(data, function(index, item) {
                                // Solo la primera vez capturamos los datos únicos
                                if (index === 0) {
                                    origen = item.boleta.ubigeoorigen.almacenDepartamento;
                                    destino = item.boleta.ubigeodestino.almacenDepartamento;
                                    precioServicio = item.boleta.precioServicio + " Bs";

                                    switch (item.boleta.tipoTransporte) {
                                        case 0:
                                            tipoTransporteTexto = "Local";
                                            break;
                                        case 1:
                                            tipoTransporteTexto = "Interprovincial";
                                            break;
                                        case 2:
                                            tipoTransporteTexto = "Interdepartamental";
                                            break;
                                        default:
                                            tipoTransporteTexto = "Desconocido"; // Texto por defecto
                                            break;
                                    }
                                }

                                // Agrupamos cada conjunto de datos (descripcion + peso + volumen + cantidad + observacion)
                                var grupo = 'Descripcion: ' + item.descripcion + ', '
                                          + 'Peso: ' + item.peso + ', '
                                          + 'Volumen: ' + item.volumen + ', '
                                          + 'Cantidad: ' + item.cantidad + ', '
                                          + 'Observacion: ' + item.observaciones+ '<br>';

                                // Acumulamos todos los grupos en una sola variable, separándolos con un salto de línea o un separador
                                agrupaciones += grupo + '<br>'; // Usamos '<br>' para mostrar cada agrupación en una nueva línea dentro de la celda
                            });

                            // Después de recorrer todos los items, mostramos una sola fila con los datos únicos y la agrupación acumulada
                            $('#id_table_cliente1').append('<tr>'
                                + '<td>' + origen + '</td>'
                                + '<td>' + destino + '</td>'
                                + '<td>' + precioServicio + '</td>'
                                + '<td>' + tipoTransporteTexto + '</td>'
                                + '<td>' + agrupaciones.trim() + '</td>'  // Mostrar todas las agrupaciones acumuladas
                            + '</tr>');
                        });


                    }

                    $(document).ready(function () {
                        $('#tableMaterial').DataTable();
                    });


                /*    function mostrarDetalleGeneraljjj(id) {



                        mostrarDetalleGeneraljj1(id);
                        $('#idModalDetalleGeneral').modal("show")



                    }*/


                    function limpiarFormulario() {
                        $('#id_reg_departamento').val('');
                        $('#id_reg_provincia').val('');
                        $('#id_reg_municipio').val('');
                        $('#id_reg_alamcen').val('');

                    }

                </script>

    </body>
    </html>
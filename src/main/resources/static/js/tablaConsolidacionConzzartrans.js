$(document).ready(function () {
    cargarTodosLosRegistros1();
    $("#idBtnBuscar").click(async function () {
        var id_fechadesde = $('#id_fechadesde').val();
        var id_fechahasta = $('#id_fechahasta').val();

        // Habilitar o deshabilitar el botón "Reporte" dependiendo de si se ingresaron las fechas
        if (id_fechadesde && id_fechahasta) {
            $('#id_reporte').prop('disabled', false);
        } else {
            $('#id_reporte').prop('disabled', true);
        }

        var param = { "fecha_desde": id_fechadesde, "fecha_hasta": id_fechahasta };

        try {
            // Realizar la solicitud asíncrona con fetch
            const response = await fetch(`/api/boletas/consultaPorCmmRangoFecha2?fecha_desde=${id_fechadesde}&fecha_hasta=${id_fechahasta}`);
            const data = await response.json();
            agregarGrilla(data); // Función para actualizar la tabla con los datos recibidos
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    });

    // Evento para el botón "Reporte"
    $('#id_reporte').click(function () {
        // Cambiar la acción del formulario
        $("#id_form").attr('action', 'consultaBoletaPdf');
        // Enviar el formulario
        $("#id_form").submit();
    });

    // Evento para buscar por CMM mientras se escribe
    $('#id_busca_cmm').on('keyup', async function () {
        var id_busca_cmm = $('#id_busca_cmm').val();

        // Deshabilitar o habilitar el botón "Reporte" según el valor de CMM
        if (id_busca_cmm) {
            $('#id_reporte').prop('disabled', true);
        } else {
            $('#id_reporte').prop('disabled', false);
        }

        var param = { "cmm": id_busca_cmm };

        try {
            // Realizar la solicitud asíncrona con fetch
            const response = await fetch(`/api/boletas/consultaPorCmmRangoFecha2?cmm=${id_busca_cmm}`);
            const data = await response.json();
            agregarGrilla(data); // Función para actualizar la tabla con los datos recibidos
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    });

    // Inicialmente deshabilitar el botón "Reporte"
    $('#id_reporte').prop('disabled', true);
});

//--------------------------------------------------------------------------------------------------
 async function cargarTodosLosRegistros1() {
        try {
            // Realizar la solicitud asíncrona con fetch para cargar todos los registros
            const response = await fetch(`/api/boletas/consultaPorCmmRangoFecha2`);
            const data = await response.json();
            agregarGrilla(data); // Función para actualizar la tabla con los datos recibidos
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

//-----------------------------------------------------------------------------------------------------------

function limpiarfecha(){
  
    $('#id_fechahasta').val('');
    $('#id_fechadesde').val('');
     $('#id_busca_cmm').val('');
    $('#tableGeneral').DataTable().clear();	
	$('#tableGeneral').DataTable().destroy();	
	 $('#id_reporte').prop('disabled', true);
	
}

function limpiarfecha2(){
  
    $('#id_fechahasta').val('');
    $('#id_fechadesde').val('');
     $('#id_busca_cmm').val('');
    	
	
}




function agregarGrilla(lista){
  var table = $('#tableGeneral').DataTable();
  table.clear().destroy();
  
  $('#tableGeneral').DataTable({
      data: lista,
      searching: false,
      ordering: true,
      processing: true,
      pageLength: 50,
      lengthChange: false,
      columns: [
          { data: "flujoConF" },
          { data: "cmmConT" },
          { 
      data: "proformaConPF",
              render: function(data, type, row) {
                  return data ? data : ''; // Maneja el caso en que no exista el campo
              }
           },
            { 
              data: "ubigeoorigen.almacenDepartamento", 
              render: function(data, type, row) {
                  return '<small>' + data + '</small>'; 
              }
          },
          { 
              data: "ubigeodestino.almacenDepartamento", 
              render: function(data, type, row) {
                  return '<small>' + data + '</small>'; 
              }
          },
            { 
      data: "fechaDestino",
              render: function(data, type, row) {
                  return data ? data : ''; // Maneja el caso en que no exista el campo
              }
          
           },
          {
       data: "descripcion",
               render: function(data, type, row) {
                  return data ? data : ''; // Maneja el caso en que no exista el campo
              }
           },
          {
              data: null,
              render: function(data, type, row) {
                  return `<img src='images/imagen5.ico' width='auto' height='auto'
                              onclick="mostrarDetalleGeneral('${row.idBoleta}');"
                              data-toggle="tooltip" title="Edit client"
                              style="cursor: pointer;" />`;
              }
          },
          { data: "precionConBs" },
        {
        data: null,
        render: function(data, type, row) {
            return `
               
                <img src="images/imagen4.ico" width="auto" height="auto"
                     onclick="mostrarModalConsolidacion('${row.idBoleta}');"
                     style="cursor: pointer;" />
            `;
        }
    },
          { data: "unidadsolicitante.nombreCompleto" },
          {
              data: null,
              render: function(data, type, row) {
                  return `<img src="images/imagen9.ico" width="auto" height="auto"
                              title="Editar Material"
                              onclick="mostrarPrecio('${row.idBoleta}');"
                              style="cursor: pointer;" />`;
              }
          },
          {
              data: null,
              render: function(data, type, row) {
                  var estado = "";
                  var estado1 = "proforma";
                  var estado2 = "comprote";
                  var estado3 = "DEVOLVER";
                  var estado4 = "CONSOLIDAR";
                  var estado5 = "ESPERANDO";
                  var botonConsolidacion = row.botonConsolidacion;

                  if (botonConsolidacion == 0) {
                      estado = "EN PROCESO";
                  } else if (botonConsolidacion == 1) {
                      estado = " CONSOLIDAR";
                  } else if (botonConsolidacion == 2) {
                      estado = "ENVIADO A CONZZARTRANS";
                  }else if (botonConsolidacion == 3) {
                    estado = "ESPERANDO";
                  }else if (botonConsolidacion == 4) {
                    estado = "CONSOLIDADO";
                  }

                  var html = '';
                  if (botonConsolidacion == 1) {
                      html += 'ESTADO <button type="button" data-toggle="modal"  class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                      html += '<img src="images/imagen3.png" width="10px" height="auto" title="Editar Material" />';
                      html += '<span style="background-color: transparent;">' + estado5 + '</span></button>';
                  } else if (botonConsolidacion == 2) {
                        html += 'ESTADO <button type="button" data-toggle="modal" onclick="cambiarDeEstado2a3(\'' + row.idBoleta + '\');" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
						html += '<img src="images/imagen3.png" width="10px" height="auto" title="Cambiar a Estado 2 a 3" />';
						html += '<span style="background-color: transparent;">' + estado3 + '</span></button>';
						
						html += ' <button type="button" data-toggle="modal" onclick="cambiarDeEstado2a4(\'' + row.idBoleta + '\');" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
						html += '<img src="images/imagen4.png" width="10px" height="auto" title="Cambiar a Estado 2 a 4" />';
						html += '<span style="background-color: transparent;">' + estado4 + '</span></button>';

                  }  else if (botonConsolidacion == 3) {
                      html += 'ESTADO <button type="button" data-toggle="modal"  class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                      html += '<img src="images/imagen3.png" width="10px" height="auto" title="Editar Material" />';
                      html += '<span style="background-color: transparent;">' + estado5 + '</span></button>';
	                }  else if (botonConsolidacion == 4) {
	                  html += '<div>ESTADO</div><div>' + estado + '</div>';
	                  html += '<button type="button" data-toggle="modal" onclick="muestrapdfdelcmm(\'' + row.idBoleta + '\');" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
	                  html += '<img src="images/pdf.png" width="25px" height="auto" title="Editar Material" />';
	                  html += '<span style="background-color: transparent;">' + estado1 + '</span></button>';
	                  html += '<span onclick="muestrapdfcomprobante(\'' + row.idBoleta + '\');" style="cursor: pointer;">';
	                  html += '<img src="images/pdf.png" width="25px" height="auto" title="Comprobante" />';
	                  html += '<span>' + estado2 + '</span></span>';
	              } 
                   else {
                      html += 'ESTADO <button type="button" data-toggle="modal" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                      html += '<img src="images/imagen3.png" width="10px" height="auto" title="Editar Material" />';
                      html += '<span style="background-color: transparent;">' + estado + '</span></button>';
                  }

                  return html;
              }
          }
      ],
      rowCallback: function(row, data) {
          $(row).removeClass('celeste rojo verde ');

          if (data.botonConsolidacion == 0) {
              $(row).addClass('celeste');
          } else if (data.botonConsolidacion == 1) {
              $(row).addClass('rojo');
          } else if (data.botonConsolidacion == 2) {
              $(row).addClass('verde_claro');
          }else if (data.botonConsolidacion == 3) {
            $(row).addClass('rojo_claro');
         }else if (data.botonConsolidacion == 4) {
          $(row).addClass('verde');
         }
      }
  });
}

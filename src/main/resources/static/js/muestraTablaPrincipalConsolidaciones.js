$(document).ready(function(){
$("#idBtnBuscar").click(function(){
	var id_fechadesde= $('#id_fechadesde').val();
	var id_fechahasta= $('#id_fechahasta').val();
	 if (id_fechadesde && id_fechahasta  ) {
            $('#id_reporte').prop('disabled', false);
        } else {
            $('#id_reporte').prop('disabled', true);
        }  
          
	var param={"fecha_desde":id_fechadesde,"fecha_hasta":id_fechahasta};
	$.getJSON("consultaPorCmmRangoFecha",param,function(data){
		agregarGrilla(data);
	});
	  
});


$('#id_reporte').click(function(){
       // event.preventDefault(); // Prevenir el envío del formulario por defecto
        
        // Cambiar la acción del formulario
        $("#id_form").attr('action', 'consultaBoletaPdf');
        // Enviar el formulario
        $("#id_form").submit();
         
        
    });
    

  $('#id_busca_cmm').on('keyup', function(){
        var id_busca_cmm = $('#id_busca_cmm').val();  
       
        if (id_busca_cmm ) {
            $('#id_reporte').prop('disabled', true);
        } else {
            $('#id_reporte').prop('disabled', false);
        }      
	  var param={"cmm":id_busca_cmm};
	   
	  $.getJSON("consultaPorCmmRangoFecha",param,function(data){
		agregarGrilla(data);
		
	});
	   
	 
  });

    $('#id_reporte').prop('disabled', true);
 });

 
 //limpiarfecha();






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
            { data: "ubigeoorigen.almacenDepartamento" },
            { data: "ubigeodestino.almacenDepartamento" },
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
			                 onclick="mostrarDetalleMaterialdeuncliente('${row.idBoleta}');"
			                 style="cursor: pointer; margin-right: 10px;" />
			            <img src="images/imagen5.ico" width="auto" height="auto"
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
                    var botonConsolidacion = row.botonConsolidacion;

                    if (botonConsolidacion == 0) {
                        estado = "EN PROCESO";
                    } else if (botonConsolidacion == 1) {
                        estado = "REALIZADO";
                    } else if (botonConsolidacion == 2) {
                        estado = "CONSOLIDADO";
                    }

                    var html = '';
                    if (botonConsolidacion == 1) {
                        html += 'ESTADO <button type="button" data-toggle="modal" onclick="cambiarDeEstado1(\'' + row.idBoleta + '\');" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                        html += '<img src="images/imagen3.png" width="10px" height="auto" title="Editar Material" />';
                        html += '<span style="background-color: transparent;">' + estado + '</span></button>';
                    } else if (botonConsolidacion == 2) {
                        html += '<div>ESTADO</div><div>' + estado + '</div>';
                        html += '<button type="button" data-toggle="modal" onclick="muestrapdfdelcmm(\'' + row.idBoleta + '\');" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                        html += '<img src="images/pdf.png" width="25px" height="auto" title="Editar Material" />';
                        html += '<span style="background-color: transparent;">' + estado1 + '</span></button>';
                        html += '<span onclick="muestrapdfcomprobante(\'' + row.idBoleta + '\');" style="cursor: pointer;">';
                        html += '<img src="images/pdf.png" width="25px" height="auto" title="Comprobante" />';
                        html += '<span>' + estado2 + '</span></span>';
                    } else {
                        html += 'ESTADO <button type="button" data-toggle="modal" class="btn btn-active btn-xs" style="background-color: transparent; border: none;">';
                        html += '<img src="images/imagen3.png" width="10px" height="auto" title="Editar Material" />';
                        html += '<span style="background-color: transparent;">' + estado + '</span></button>';
                    }

                    return html;
                }
            }
        ],
        rowCallback: function(row, data) {
            $(row).removeClass('celeste rojo verde');

            if (data.botonConsolidacion == 0) {
                $(row).addClass('celeste');
            } else if (data.botonConsolidacion == 1) {
                $(row).addClass('rojo');
            } else if (data.botonConsolidacion == 2) {
                $(row).addClass('verde');
            }
        }
    });
}

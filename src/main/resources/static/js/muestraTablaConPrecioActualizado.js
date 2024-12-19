 $(document).ready(function() {
        $('#idBtnMuestraTablaConPrecioActualizado').click(function() {
            recarga1();
        });
    });
    
    
  function recarga1(){
	$.getJSON("consultaPorCmm1",function(data){
		agregarGrilla(data);
	});
	
	
	
}
 $(document).ready(function(){
        $("#idBtnRegistraMaterial").click(function(){
            var observaciones = $("#observaciones").val();
            var descripcion = $("#descripcion").val();
           var idBoleta = parseInt($("#idBoleta").val(), 10);
          
           
          

            var material = {
                observaciones: observaciones,
                
                descripcion:descripcion,
               boleta: {
                    idBoleta: idBoleta
                }
            };
            
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/registrarCampoAcumulador",
                data: JSON.stringify(material),
                success: function(){
					
                    alert("Copia guardada en segundo plano.");
                   
                },
                error: function(){
                    alert("Error al guardar la copia.");
                }
            });
        });
      
    });
    
     
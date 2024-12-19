$(document).ready(function() {
  $('#id_form_registra_material').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
      descripcion: {
        selector: "#descripcion",
        validators: {
          notEmpty: {
            message: 'El nombre es obligatorio'
          },
          stringLength: {
            min: 5,
            max: 255,
            message: 'El nombre es de 5 a 255 caracteres'
          }
        }
      },
      observaciones: {
        selector: "#observaciones",
        validators: {
          notEmpty: {
            message: 'Las observaciones son obligatorias'
          },
          stringLength: {
            min: 0,
            max: 255,
            message: 'Las observaciones son de 0 a 255 caracteres'
          }
        }
      },
      peso: {
        selector: "#peso",
        validators: {
          notEmpty: {
            message: 'El peso es obligatorio'
          },
          regexp: {
            regexp: /^[0-9]+([.][0-9]+)?$/,
            message: 'Ingresar peso con caracteres numéricos'
          }
        }
      },
      cantidad: {
        selector: "#cantidad",
        validators: {
          notEmpty: {
            message: 'La cantidad es obligatoria'
          },
          regexp: {
            regexp: /^[0-9]+$/,
            message: 'Ingresar cantidad con caracteres numéricos'
          }
        }
      },
      volumen: {
        selector: "#volumen",
        validators: {
          notEmpty: {
            message: 'El volumen es obligatorio'
          },
          regexp: {
            regexp: /^[0-9]+([.][0-9]+)?$/,
            message: 'Ingresar volumen con caracteres numéricos'
          }
        }
      },
      idBoleta: {
        selector: "#idBoleta",
        validators: {
          notEmpty: {
            message: 'Por favor, selecciona un ID de boleta'
          }
        }
      }
    }
  });
  
    $('#idBtnLimpiaMaterial').on('click', function() {
		 // $('#idModalClienteRegistra').modal("hide");
        limpiarFormulario4();
        var validator = $('#id_form_registra_material').data('bootstrapValidator');
        validator.resetForm();
          $('#idModalMaterialRegistra').modal('hide');
           
    });  
});

//--------------------------------------------registrar Material-----------------------------
async function RegistrarFormularioMaterial() {
    try {
        // Obtener el formulario
        const form = document.getElementById("id_form_registra_material");
        
       const idBtnNuevoCmm = document.getElementById('idBtnNuevoCmm');
      // const idBtnRegistrarMaterial = document.getElementById('idBtnRegistrarMaterial');
        // Validar el formulario usando BootstrapValidator
        const validator = $(form).data('bootstrapValidator');
        validator.validate();
        if (!validator.isValid()) {
            document.getElementById("resultado3").innerText = "Por favor, complete el formulario correctamente.";
            return;
        }

        // Obtener los valores seleccionados de los `select`
        const descripcion = document.getElementById("descripcion").value;
        const peso = document.getElementById("peso").value;
        const cantidad = document.getElementById("cantidad").value;
        const volumen = document.getElementById("volumen").value;
        const observaciones = document.getElementById("observaciones").value;
       const id_reg_estado = document.getElementById("id_reg_estado").value;
       const idBoleta = document.getElementById("idBoleta").value;
       
        // Validar radio button seleccionado
        const radios = document.getElementsByName("cobrarpor");
        let selectedTransporte = null;
        for (const radio of radios) {
            if (radio.checked) {
                selectedTransporte = radio.value;
                break;
            }
        }

        if (!selectedTransporte) {
            document.getElementById("resultado3").innerText = "Debe seleccionar un tipo de cobro.";
            return;
        }
        // Crear el JSON para enviar al API
        const data = {
            descripcion: descripcion,
            peso:parseFloat(peso),
            cantidad:parseFloat(cantidad),
            volumen:parseFloat(volumen),
            observaciones: observaciones,
            estado: id_reg_estado,
           cobrarpor:selectedTransporte,
            boleta: {
               
                idBoleta: parseInt(idBoleta)
            }
                    
        };

        // Realizar la petición POST a la API para registrar la boleta
        const response = await fetch('/api/boletas/registrarMaterial', {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(data)
        });

        // Verificar si la respuesta es correcta
        if (!response.ok) {
            throw new Error('Error al registrar Material');
        }

        // Convierte la respuesta a formato JSON
        const responseData = await response.json();
        console.log('Material registrada:', responseData);

        // Mostrar un mensaje de éxito
        //  document.getElementById("resultado3").innerText = "Boleta registrada correctamente";
        alert("EL FORMULARIO Material SE REGISTRO EXITOSAMENTEEEEEEEEEEEEEEEEEEEEEEEEEE");
        
        // idBtnRegistrarMaterial.disabled = false;
        $('#idModalMaterialRegistra').modal('hide');
        // Recargar la página
        location.reload();
        //idBtnNuevoCmm.disabled = false
    } catch (error) {
        console.error('Error:', error);
        // Mostrar un mensaje de error
        //document.getElementById("resultado3").innerText = "Hubo un problema al registrar la boleta";
        alert("hubo un problema al registrar la material ");
    }
}

// Función para obtener los encabezados con el token de autorización
function getHeaders() {
    const token = localStorage.getItem('token');
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : ''
    };
}
//______________________________________________________________________________________________________________
function limpiarFormulario4(){
	       $("#descripcion").val("");
			$("#observaciones").val("");
			$("#peso").val("");
			$("#cantidad").val("");
			$("#volumen").val("");
			$("#idBoleta").val("")
} 
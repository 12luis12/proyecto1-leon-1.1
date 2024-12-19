document.addEventListener("DOMContentLoaded", function () {
      // manejo de botones de registraBoleta.jsp 
      var idBtnRegistrarBoleta = document.getElementById("idBtnRegistrarBoleta");
      var idBtnRegistrarMaterial = document.getElementById("idBtnRegistrarMaterial");
      var idBtnNuevoCmm = document.getElementById("idBtnNuevoCmm");
       var idBtnRegistrar2 = document.getElementById("idBtnRegistrar2");
      var idBtnRegistraMaterial = document.getElementById("idBtnRegistraMaterial");

      // Recuperar el estado de los botones desde localStorage
      var buttonState = localStorage.getItem("buttonState");

      // Configurar el estado inicial al cargar la página
      if (buttonState === "step2") {
        idBtnRegistrarBoleta.disabled = true;
        idBtnRegistrarMaterial.disabled = false;
        idBtnNuevoCmm.disabled = false;
      } else {
        // Estado inicial
        idBtnRegistrarBoleta.disabled = false;
        idBtnRegistrarMaterial.disabled = true;
        idBtnNuevoCmm.disabled = true;
      }

      // Evento de click en el botón 11 boton formulario boleta
      idBtnRegistrar2.addEventListener("click", function () {
        idBtnRegistrarBoleta.disabled = true;
        idBtnRegistrarMaterial.disabled = false;
        idBtnNuevoCmm.disabled = true;

        // Guardar el estado en localStorage para la próxima recarga
        localStorage.setItem("buttonState", "step1");
      });

      // Evento de click en el botón 22 formulario registro material 
      idBtnRegistraMaterial.addEventListener("click", function () {
        idBtnRegistrarBoleta.disabled = true;
        idBtnRegistrarMaterial.disabled = false;
        idBtnNuevoCmm.disabled = false;

        // Guardar el estado para que persista después de la recarga
        localStorage.setItem("buttonState", "step2");

        // Recargar la página
        location.reload();
      });

      // Evento de click en el botón 33
      idBtnNuevoCmm.addEventListener("click", function () {
        idBtnRegistrarBoleta.disabled = false;
        idBtnRegistrarMaterial.disabled = true;
        idBtnNuevoCmm.disabled = true;

        // Reiniciar el estado a su valor inicial
        localStorage.removeItem("buttonState");
      });
    });
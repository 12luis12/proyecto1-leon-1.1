$(document).ready(function() {
   // on ready
});

async function iniciarSesion() {
  let datos = {};
  datos.login = document.getElementById('txtLogin').value;
  datos.password = document.getElementById('txtPassword').value;

  try {
    const response = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });

    if (response.ok) {
      // Parse the JSON if the response was successful (status 200-299)
      const respuesta = await response.json();
      const token = respuesta.tokenJWT; // Extract the token from the response
      localStorage.setItem('token', token); // Store the token in localStorage
      localStorage.setItem('login', datos.login); // Store the login in localStorage
      // Handle successful login, e.g., redirect to home page
      window.location.href = '/verIntranetHome';
    } else {
      // Handle error response (e.g., 401 Unauthorized)
      const errorMessage = await response.text();
      alert("Error: " + errorMessage);
    }
  } catch (error) {
    console.error("Error during login: ", error);
    alert("Hubo un problema al intentar iniciar sesión. Inténtalo de nuevo.");
  }
}

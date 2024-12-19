<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="js/login.js"></script>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrapValidator.css">
    <link rel="stylesheet" href="css/fondologin.css">

    <!-- Custom CSS -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
        }
        .futuristic-menu {
            background-color: #FFCD00;
            padding: 10px 20px;
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
        }
        .futuristic-menu h1 {
            color: #0A0A49;
            margin: 0;
            font-size: 20px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 4px;
            display: flex;
            align-items: center;
        }
        .futuristic-menu h1 img {
            margin-right: 10px;
            height: 40px;
        }
        .futuristic-menu ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
        }
        .futuristic-menu ul li {
            margin-left: 20px;
        }
        .futuristic-menu ul li a {
            color: #0A0A49;
            text-decoration: none;
            font-size: 28px;
            transition: color 0.3s ease;
        }
        .futuristic-menu ul li a:hover {
            color: #000000;
        }
        .futuristic-menu .search-bar {
            margin-left: 40px;
        }
        .futuristic-menu .search-bar input[type="text"] {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 16px;
        }

        /* Custom styles for input fields and button */
        .form-control {
            background-color: #EAE9E9; /* Plomo */
            color: #333;
        }
        .btn-custom-blue {
            background-color: #0A0A49; /* Azul */
            color: white;
            border: none;
            width: 100px;
        }
        .btn-custom-blue:hover {
            background-color: #0056b3;
        }
        .form-group {
            margin-bottom: 20px; /* Separar campos y botón */
        }
    </style>

    <title>CONZZARTRANS S.R.L</title>
</head>
<body>

    <!-- Futuristic Menu Bar -->
    <div class="futuristic-menu">
        <h1>
            CONZZARTRANS S.R.L
        </h1>
        <ul>
            <li><a href="#">Inicio</a></li>
            <li><a href="#">Servicios</a></li>
            <li><a href="#">Nosotros</a></li>
            <li><a href="#">Contacto</a></li>
            <li class="search-bar">
                <input type="text" placeholder="Buscar...">
            </li>
        </ul>
    </div>

    <div class="top-content" style="padding-top: 80px;"> <!-- Adjusted padding to avoid overlap with menu -->
        <div class="inner-bg">
            <div class="container">
                <c:if test="${requestScope.mensaje != null}">
                    <div class="alert alert-danger fade in" id="success-alert">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>${requestScope.mensaje}</strong>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3 form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                              
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form id="id_form" action="login" method="post" class="login-form">
                                <div class="form-group">
                                    <label class="col-lg-4 control-label" for="form-username">Usuario</label>
                                    <div class="col-lg-6">
                                        <input type="text" name="login" placeholder="Ingrese Usuario" class="form-username form-control" id="txtLogin" maxlength="20" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-4 control-label" for="form-password">Contrasena</label>
                                    <div class="col-lg-6">
                                        <input type="password" name="password" placeholder="Ingrese Contrasena" class="form-password form-control" id="txtPassword" maxlength="20" value="">
                                    </div>
                                </div>
                                <div class="row">
                                <div class="col-sm-12 text-center"> <!-- Centrar en el contenedor completo -->
                                    <div class="form-group">
                                        <button type="button" onclick="iniciarSesion()" class="btn btn-custom-blue">INGRESAR</button>
                                    </div>
                                </div>
                            </div>

                            </form>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $("#success-alert").fadeTo(1000, 500).slideUp(500, function(){
            $("#success-alert").slideUp(500);
        });

        $(document).ready(function() {
            $('#id_form').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    login: {
                        validators: {
                            notEmpty: {
                                message: 'El usuario es obligatorio'
                            },
                            stringLength: {
                                message: 'El usuario es de 3 a 20 caracteres',
                                min: 3,
                                max: 20
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: 'La contraseña es obligatoria'
                            },
                            stringLength: {
                                message: 'La contraseña es de 3 a 20 caracteres',
                                min: 3,
                                max: 20
                            }
                        }
                    }
                }
            });

            $('#validateBtn').click(function() {
                $('#id_form').bootstrapValidator('validate');
            });
        });
    </script>

</body>
</html>

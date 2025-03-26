<%@include file="/inc/tags.jsp" %>
<!DOCTYPE html>

<f:view>

<html>

<head>

<meta HTTP-EQUIV="Content-Type" CONTENT="text/html" CHARSET="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Tarjeta Fiel | Ingresar</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bower_components/bootstrap/dist/css/bootstrap.min.css" type="text/css" />
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bower_components/font-awesome/css/font-awesome.min.css" type="text/css" />
<!-- Ionicons -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bower_components/Ionicons/css/ionicons.min.css" type="text/css" />
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/dist/css/AdminLTE.min.css" type="text/css" />
<!-- iCheck -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/plugins/iCheck/square/blue.css" type="text/css" />


<!-- Google Font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</head>



<body class="hold-transition login-page" onload="cargarMsjError()">
  <div class="login-box">
    <div class="login-logo">
      <a href="login"><b>Tarjeta </b>FIEL</a>
    </div>
    <div class="login-box-body">
      <p class="login-box-msg">Ingrese sus credenciales</p>

      <h:form id="form">
        <div class="form-group has-feedback">
          <x:inputHidden value="#{LoginBean.username}" id="txtUsernameHide"/>
          <input type="text" class="form-control" placeholder="Usuario" id="txtUsername" name="user" property="" required/>
          <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <x:inputHidden value="#{LoginBean.clave}" id="txtPasswordHide"/>
          <input type="password" class="form-control" placeholder="Contraseña" id="txtPassword" name="password" required/>
          <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
          <div class="col-xs-8">
          </div>
          <!-- /.col -->
          <div class="col-xs-4">
            <h:commandButton action="#{LoginBean.ingreso}" onclick="iniciarSesion()" styleClass="btn btn-primary btn-block btn-flat" value="Ingresar"/>
          </div>
        </div>
        
        <a target="_blank" href="tarjetafiel/transacciones/asistencia">Asistencia</a>

      </h:form>

      

      <div id="div1" style="display: none">
          &nbsp;
          <br>
          <h:outputText value="Su estado no le permite ingresar, " style="font-size: 12px;color: red"/>
          <br>
          <h:outputText value="consulte con el administrador del sistema." style="font-size: 12px;color: red"/>
          &nbsp;
      </div>
      
      <div id="div2" style="display: none">
          &nbsp;
          <br>
          <h:outputText value="La clave es incorrecta. A los tres intentos se bloqueará¡ su cuenta." style="font-size: 12px;color: red" />
          &nbsp;
      </div>

      <div id="div3" style="display: none">
          &nbsp;
          <br>
          <h:outputText value="El nombre de operador ingresado no existe." style="font-size: 12px;color: red"/>
          &nbsp;
      </div>
      
      <div id="div4" style="display: none">
          &nbsp;
          <br>
          <h:outputText value="Su cuenta se encuentra bloqueada. Contacte a el administrador del sistema." style="font-size: 12px;color: red"/>
          &nbsp;
      </div>
      
      <div id="div5" style="display: none">
          &nbsp;
          <br>
          <h:outputText value="Falló la conexión con la base. Espere unos minutos, o contacte a el administrador del sistema." style="font-size: 12px;color: red"/>
          &nbsp;
      </div>
      


    </div>
  </div>


  <!-- jQuery 3 -->
  <script src="<%=request.getContextPath()%>/assets/bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
  <!-- Bootstrap 3.3.7 -->
  <script src="<%=request.getContextPath()%>/assets/bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
  <!-- iCheck -->
  <script src="<%=request.getContextPath()%>/assets/plugins/iCheck/icheck.min.js" type="text/javascript"></script>


  <script type="text/javascript">
    function iniciarSesion(){
      document.getElementById("form:txtUsernameHide").value = document.getElementById("txtUsername").value;
      document.getElementById("form:txtPasswordHide").value = document.getElementById("txtPassword").value;
    }

    function cargarMsjError() {
      var e1 = ${LoginBean.estado};
    	var div1 = document.getElementById("div1");
	    var e2 = ${LoginBean.estadoDos};
	    var div2 = document.getElementById("div2");
	    var e3 = ${LoginBean.estadoTres};
	    var div3 = document.getElementById("div3");
	    var e4 = ${LoginBean.estadoCuatro};
	    var div4 = document.getElementById("div4");
	    var e5 = ${LoginBean.estadoCinco};
	    var div5 = document.getElementById("div5");
	    if (e1) {
	        div1.style.display = "block";
	    }
	    if (e2) {
	        div2.style.display = "block";
	    }
	    if (e3) {
	        div3.style.display = "block";
	    }
	    if (e4) {
	        div4.style.display = "block";
	    }
	    if (e5) {
	        div5.style.display = "block";
	    }
	}
  </script>

<script>
	
</script>


</body>

</html>

</f:view>
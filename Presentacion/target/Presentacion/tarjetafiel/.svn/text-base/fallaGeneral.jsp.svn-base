<%@include file="/inc/tags.jsp" %>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel | Falla General"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    500 Página de Error
  </h1>
</section>

    <!-- Main content -->
    <section class="content">

      <div class="error-page">
        <h2 class="headline text-red">500</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-red"></i> Oops! Algo salió mal.</h3>
          <p>
            Se ha producido una falla general. Comuníquese con el Área de Sistemas.
          </p>
        </div>
      </div>
      <!-- /.error-page -->

    </section>
    <!-- /.content -->

</div>
<!-- ./Main content -->
<%@include file="/inc/footer.jsp" %>
<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %> 
</body>
</html>
</f:view>

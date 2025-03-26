<%@ include file="/inc/tags.jsp" %>

<h:form id="acciones" style="display: none">
  <h:commandButton action="#{AutorizacionTelefonicaBean.borrar}" id="limpiarAutorizacionTelefonica"/>
</h:form>

<!-- wrapper -->
<div class="wrapper">

<header class="main-header">
    <!-- Logo -->
    <a href="/Presentacion/inicio.jsf" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>T</b>F</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Tarjeta</b> FIEL</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>


      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
           
           <!-- Autorizacion Telefonica -->
           <li><a href="#" onclick="abrirPopUp('tarjetafiel/transacciones/autorizacionTelefonica.jsf','Autorizaciones','limpiarAutorizacionTelefonica')">Autorización Telefónica</a></li>
          
          <!-- User Account -->
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span class="hidden-xs"><%=request.getSession().getAttribute("nombreOperador")%></span>
            </a>
          <ul class="dropdown-menu">
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="row">
                  <div class="col-xs-4">
                  </div>
                  <div class="col-xs-4">
                      <a class="btn btn-default btn-flat" onclick="callLink('mainMenu:cerrarSesion')" >Salir</a>
                  </div>
                  <div class="col-xs-4">
                  </div>
                </div>
              </li>
            </ul>
          </li>

        </ul>
      </div>
    </nav>

</header>


<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree" id="sideMenu">
      </ul>

    </section>
    <!-- /.sidebar -->
  </aside>
  

  <!-- Main content -->
<div class="content-wrapper">




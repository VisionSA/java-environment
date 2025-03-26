<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{SucursalBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amSucursalForm').submit();
		};
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini"onbeforeunload="ShowWait('amSucursalForm');" onload="if('${SucursalBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${SucursalBean.tituloCorto}
    <small>${SucursalBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
	<secure:check/>

	<h:form id="amSucursalForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!SucursalBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{SucursalBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{SucursalBean.popup.nombreIcono}" />
							<h:outputText value="#{SucursalBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{SucursalBean.irANuevoSucursal}" 
								onclick="clickLink('nuevoSucursal');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{SucursalBean.irAModificarSucursal}" 
								onclick="clickLink('modificarSucursal');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{SucursalBean.irAListarSucursal}" 
								onclick="clickLink('listarSucursal');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoSucursal" action="#{SucursalBean.irANuevoSucursal}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarSucursal" action="#{SucursalBean.irAModificarSucursal}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarSucursal" action="#{SucursalBean.irAListarSucursal}" forceId="true" style="display: block;"/>
					
					<h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Sucursal">
					
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
							
							<h:outputText value="Nombre:" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{SucursalBean.sucursal.nombre}"
							styleClass="bordeceldatext" maxlength="100" size="100"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGrid>
						
						<h:panelGrid columns="1" id="panelDoS" width="600" align="center">
							
							<s:fieldset legend="Domicilio" id="domicilio">
								<h:panelGrid columns="2" id="granPanelDomicilio" align="center">
									<h:panelGrid columns="4" id="panelDomicilio" width="600">
											<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
											<h:outputText id="outTxtCalle" value="#{SucursalBean.sucursal.domicilio.calleNombre}" />
											<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
											<h:outputText id="outTxtNumero" value="#{SucursalBean.sucursal.domicilio.calleNumero}" />
											<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
											<h:outputText id="outTxtBarrio" value="#{SucursalBean.sucursal.domicilio.barrio.descripcion}" />
											<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
											<h:outputText id="outTxtLocalidad" value="#{SucursalBean.sucursal.domicilio.localidad.nombre}" />
											
									</h:panelGrid>
									<x:commandLink id="agregarDomicilioLink" action="#{SucursalBean.agregarDomicilioPopup}" >
												<x:graphicImage value="/img/icon/home_24.gif" title="Editar el domicilio." border="0" />
									</x:commandLink>
								</h:panelGrid>
							</s:fieldset>
							
						</h:panelGrid>
						
					</s:fieldset>
					</h:panelGrid>
					<f:verbatim>
						<br>
					</f:verbatim>
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{SucursalBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{SucursalBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>
	</h:form>
</center>
<div class="box-header"></div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{SucursalBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

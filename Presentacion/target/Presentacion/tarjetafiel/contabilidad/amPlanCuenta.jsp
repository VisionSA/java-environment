<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{MonedaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amMonedaForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amMonedaForm');" onload="if('${MonedaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${MonedaBean.tituloCorto}
    <small>${MonedaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="amMonedaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!MonedaBean.popup.mostrar}">
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
								dialogTitle="#{MonedaBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{MonedaBean.popup.nombreIcono}" />
							<h:outputText value="#{MonedaBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{MonedaBean.irANuevoMoneda}" 
								onclick="clickLink('nuevoMoneda');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{MonedaBean.irAModificarMoneda}" 
								onclick="clickLink('modificarMoneda');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{MonedaBean.irAListarMoneda}" 
								onclick="clickLink('listarMoneda');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoMoneda" action="#{MonedaBean.irANuevoMoneda}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarMoneda" action="#{MonedaBean.irAModificarMoneda}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarMoneda" action="#{MonedaBean.irAListarMoneda}" forceId="true" style="display: block;"/>
					
					<h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Moneda">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
							  <h:outputText id="outNroContable" value="Nro Contable: " styleClass="texto"/>
		           	  <h:inputText id="NroContable" value="#{PlanCuenta.numeroContable}" 
	                			 	 size="10" maxlength="10" styleClass="bordeceldainferior" style=" width : 139px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
	                <h:outputText id="outDescripcion" value="Descripcion: " styleClass="texto"/>
		           	  <h:inputText id="Descripcion" value="#{PlanCuenta.titulo}" 
	                			 	 size="10" maxlength="10" styleClass="bordeceldainferior" style=" width : 139px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
		              </h:panelGrid>
		               
		              
		              <h:panelGroup columns="1" align="left" width="77">
		               <h:panelGrid columns="4" align="left" width="77">
                          <h:outputText value="Modulo: "/>
                          <h:selectManyCheckbox value="#{PlanCuentaBean.selectedItems}">
                              <f:selectItem itemLabel="Contabilidad" itemValue="Contabilidad" />
                              <f:selectItem itemLabel="Fondo" itemValue="Fondo" />
                             </h:selectManyCheckbox>
                         
                          <h:outputText value="Tipo Cuenta: "/>
                          <h:selectOneRadio value="#{PlanCuentaBean.selectedItems}">
                              <f:selectItem itemLabel="Contabilidad" itemValue="Contabilidad" />
                              <f:selectItem itemLabel="Fondo" itemValue="Fondo" />
                          </h:selectManyCheckbox>
                         	
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{MonedaBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{MonedaBean.cancelar}" styleClass="botones" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

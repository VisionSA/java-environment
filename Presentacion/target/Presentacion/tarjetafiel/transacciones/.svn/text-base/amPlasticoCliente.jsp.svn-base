<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{PlasticoClienteBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amPlasticoClienteForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amPlasticoClienteForm');" onload="if('${PlasticoClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

	<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PlasticoClienteBean.tituloCorto}
    <small>${PlasticoClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>
<center>
	<secure:check/>

	<h:form id="amPlasticoClienteForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!PlasticoClienteBean.popup.mostrar}">
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
								dialogTitle="#{PlasticoClienteBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{PlasticoClienteBean.popup.nombreIcono}" />
							<h:outputText value="#{PlasticoClienteBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{PlasticoClienteBean.irANuevoPlasticoCliente}" 
								onclick="clickLink('nuevoPlasticoCliente');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{PlasticoClienteBean.irAModificarPlasticoCliente}" 
								onclick="clickLink('modificarPlasticoCliente');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{PlasticoClienteBean.irAListarPlasticoCliente}" 
								onclick="clickLink('listarPlasticoCliente');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoPlasticoCliente" action="#{PlasticoClienteBean.irANuevoPlasticoCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarPlasticoCliente" action="#{PlasticoClienteBean.irAModificarPlasticoCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarPlasticoCliente" action="#{PlasticoClienteBean.irAListarPlasticosCliente}" forceId="true" style="display: block;"/>

                    <h:panelGrid id="paPrinci" columns="1" align="center">
					     <h:panelGrid id="panelPrincipalUno" columns="4" width="350" align="center">
						<h:outputText value="Estado:" styleClass="texto"/>
						<h:selectOneMenu id="lstestado" value="#{PlasticoClienteBean.plasticoCliente.estado}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{PlasticoClienteBean.estadoItems}"/>
						</h:selectOneMenu>
						<h:outputText value="Cliente:" styleClass="texto"/>
						<h:selectOneMenu id="lstCliente" value="#{PlasticoClienteBean.idClienteSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{PlasticoClienteBean.clienteItems}"/>
						</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGrid id="sec" columns="4" align="center">
						
						<h:outputText value="Número:" styleClass="texto"/>
						<h:inputText id="numeroFiltro" value="#{PlasticoClienteBean.plasticoCliente.numero}"
						styleClass="bordeceldatext" maxlength="2560" size="2560" onkeypress="return soloEnteros(this,event);"
						style="width: 240px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="Pin:" styleClass="texto"/>
						<h:inputText id="pinFiltro" value="#{PlasticoClienteBean.plasticoCliente.pin}"
						styleClass="bordeceldatext" maxlength="2560" size="2560" onkeypress="return soloEnteros(this,event);"
						style="width: 240px" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGrid>
						<s:fieldset legend="Fechas:" id="fec">
						<h:panelGrid id="panFechas" columns="6" align="center">
						<h:outputText value="Ultima Modificacion:" styleClass="texto"/>
						<x:inputCalendar id="ultimaModif" monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
				        currentDayCellClass="currentDayCell" value="#{PlasticoClienteBean.ultimaModificacion}" renderAsPopup="true"
						styleClass="bordeceldainferior" style="width: 90px"
						popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
						helpText="DD/MM/YYYY" 
						forceId="true"/>
						<h:outputText value="Vigencia Desde:" styleClass="texto"/>
						<x:inputCalendar id="FechaInicio" monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
				        currentDayCellClass="currentDayCell" value="#{PlasticoClienteBean.fechaDesde}" renderAsPopup="true"
						styleClass="bordeceldainferior" style="width: 90px"
						popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
						helpText="DD/MM/YYYY" 
						forceId="true"/>
						<h:outputText value="Vigencia Hasta:" styleClass="texto"/>
						<x:inputCalendar id="FechaHasta" monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
				        currentDayCellClass="currentDayCell" value="#{PlasticoClienteBean.fechaHasta}" renderAsPopup="true"
						styleClass="bordeceldainferior" style="width: 90px"
						popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
						helpText="DD/MM/YYYY" 
						forceId="true"/>
						</h:panelGrid>
						</s:fieldset>
					</h:panelGrid>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{PlasticoClienteBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{PlasticoClienteBean.cancelar}" styleClass="botones" />
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

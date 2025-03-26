<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{PartidoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amPartidoForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amPartidoForm');" onload="if('${PartidoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PartidoBean.tituloCorto}
    <small>${PartidoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<secure:check/>
	<h:form id="amPartidoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!PartidoBean.popup.mostrar}">
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
								dialogTitle="#{PartidoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{PartidoBean.popup.nombreIcono}" />
							<h:outputText value="#{PartidoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{PartidoBean.irANuevoPartido}" 
								onclick="clickLink('nuevoPartido');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{PartidoBean.irAModificarPartido}" 
								onclick="clickLink('modificarPartido');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{PartidoBean.irAListarPartido}" 
								onclick="clickLink('listarPartido');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoPartido" action="#{PartidoBean.irANuevoPartido}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarPartido" action="#{PartidoBean.irAModificarPartido}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarPartido" action="#{PartidoBean.irAListarPartido}" forceId="true" style="display: block;"/>
					
					<h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Partido">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							
							<h:outputText value="País" styleClass="texto"/>
							<h:selectOneMenu id="lstPais" value="#{PartidoBean.idPaisSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px" binding="#{PartidoBean.pais}"
								valueChangeListener="#{PartidoBean.cambiarProvincias}" onchange="submit();">
								<f:selectItems id="itemPais" value="#{PartidoBean.paisItems}"/>
							</h:selectOneMenu>
							
							<h:outputText value="Provincia:" styleClass="texto"/>
							<h:selectOneMenu id="lstProvincia" value="#{PartidoBean.idProvinciaSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{PartidoBean.provinciaItems}"/>
							</h:selectOneMenu>
							
							<h:outputText value="Descripción Partio:" styleClass="texto"/>
							 <h:inputText id="descripcionFiltro" value="#{PartidoBean.partido.descripcion}"
							styleClass="bordeceldatext" maxlength="50" size="50"
							style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText value="Informe Ambiental:" styleClass="texto"/>
							<h:selectBooleanCheckbox value="#{PartidoBean.informeAmbiental}" id="informeAmbiental" immediate="true"/>

							<h:outputText value="Partido lejano:" styleClass="texto"/>
							<h:selectBooleanCheckbox value="#{PartidoBean.lejano}" id="esLejano" immediate="true"/>

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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{PartidoBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{PartidoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{PartidoBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

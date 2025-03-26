<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ImpuestoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amImpuestoForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amImpuestoForm');" onload="if('${ImpuestoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ImpuestoBean.tituloCorto}
    <small>${ImpuestoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amImpuestoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ImpuestoBean.popup.mostrar}" id="popup">
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
								dialogTitle="#{ImpuestoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500" id="mensajes">
							<x:graphicImage value="/img/#{ImpuestoBean.popup.nombreIcono}" />
							<h:outputText value="#{ImpuestoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500" id="nuevoGridImpuesto">
							<x:commandButton action="#{ImpuestoBean.irANuevoImpuesto}" 
								onclick="clickLink('nuevoImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{ImpuestoBean.irAModificarImpuesto}" 
								onclick="clickLink('modificarImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{ImpuestoBean.irAListarImpuesto}" 
								onclick="clickLink('listarImpuesto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoImpuesto" action="#{ImpuestoBean.irANuevoImpuesto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarImpuesto" action="#{ImpuestoBean.irAModificarImpuesto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarImpuesto" action="#{ImpuestoBean.irAListarImpuesto}" forceId="true" style="display: block;"/>
					
					<h:panelGrid columns="1" id="uno" width="850" align="center">
					<s:fieldset legend="Alta Impuesto" id="fieldAltaImpuesto">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
							
							<h:outputText value="Descripción:" styleClass="texto" id="outputDescripcion" />
							<h:inputText id="descripcionFiltro" value="#{ImpuestoBean.impuesto.descripcion}"
							styleClass="bordeceldatext" maxlength="150" size="150"
							style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText value="Tipo impuesto:" styleClass="texto" id="outputTipoImp" />
							<h:selectOneMenu id="lstTipoImp" value="#{ImpuestoBean.idTipoImpSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								binding="#{ImpuestoBean.tipoImpHtml}" valueChangeListener="#{ImpuestoBean.cambiarCategorias}"
								onblur="apagar(this);" style="width: 200px" onchange="submit();">
								<f:selectItems value="#{ImpuestoBean.tipoImpItems}" id="selectedTipoImp" />
							</h:selectOneMenu>
							
							<h:outputText value="Categoría:" styleClass="texto" id="outputCategoria" />
							<h:selectOneMenu id="lstCategoria" value="#{ImpuestoBean.idCategoriaSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ImpuestoBean.categoriaItems}" id="selectedCategoria" />
							</h:selectOneMenu>
							
							<h:outputText value="Provincia:" styleClass="texto" id="outputProvincia" />
							<h:selectOneMenu id="lstProvincia" value="#{ImpuestoBean.idProvinciaSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px" binding="#{ImpuestoBean.provinciaHtml}"
								valueChangeListener="#{ImpuestoBean.cargarPartido}" onchange="submit();">
								<f:selectItems value="#{ImpuestoBean.provinciaItems}" id="selectedProvincia"/>
							</h:selectOneMenu>
							
							<h:outputText value="Partido:" styleClass="texto" id="outputLocalidad" />
							<h:selectOneMenu id="lstLocalidad" value="#{ImpuestoBean.idPartidoSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ImpuestoBean.partidoItems}" id="selectedLocalidad"/>
							</h:selectOneMenu>
														
							<h:outputText value="Ganacia Imponible:" styleClass="texto" id="outputGanancia"/>
							<h:selectBooleanCheckbox value="#{ImpuestoBean.ganaciaImpuesta}" id="ganImp"/>
							
							<h:outputText value="Percepción:" styleClass="texto" id="outputPercepcion" />
							<h:selectBooleanCheckbox value="#{ImpuestoBean.percerpcion}" id="percepcion"/>
							
							<h:outputText value="Importe Minimo:" styleClass="texto" id="outputImporteMinimo" />
							<h:inputText id="importeminimoFiltro" value="#{ImpuestoBean.impuesto.importeMinimo}"
							styleClass="bordeceldainferior" maxlength="10" size="10"
							style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText value="Alicuota:" styleClass="texto" id="outputAlicuota" />
							<h:panelGroup>
								<h:inputText id="porcalicuotaFiltro" value="#{ImpuestoBean.impuesto.porcAlicuota}"
								styleClass="bordeceldainferior" maxlength="5" size="5"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloDecimales(InputText, evt);"/>
								<h:outputText value="%" id="porcentaje" style="texto"/>
							</h:panelGroup>							
							
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ImpuestoBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ImpuestoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ImpuestoBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

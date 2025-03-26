<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{BarrioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amBarrioForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amBarrioForm');" onload="if('${BarrioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${BarrioBean.tituloCorto}
    <small>${BarrioBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
<secure:check/>

	<h:form id="amBarrioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!BarrioBean.popup.mostrar}">
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
								dialogTitle="#{BarrioBean.tituloCorto}">
						<h:panelGrid columns="2" width="500" id="panG">
							<x:graphicImage id="im" value="/img/#{BarrioBean.popup.nombreIcono}" />
							<h:outputText id="outMe" value="#{BarrioBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500" id="barrioN">
							<x:commandButton id="cmdNuevo" action="#{BarrioBean.irANuevoBarrio}" 
								onclick="clickLink('nuevoBarrio');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton id="cmdModificado" action="#{BarrioBean.irAModificarBarrio}" 
								onclick="clickLink('modificarBarrio');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton id="cmdListado" action="#{BarrioBean.irAListarBarrio}" 
								onclick="clickLink('listarBarrio');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoBarrio" action="#{BarrioBean.irANuevoBarrio}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarBarrio" action="#{BarrioBean.irAModificarBarrio}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarBarrio" action="#{BarrioBean.irAListarBarrio}" forceId="true" style="display: block;"/>
					
					<h:panelGrid columns="1" id="uno" width="850" align="center">
					<s:fieldset legend="Barrio" id="fieldP">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
						
							<h:outputText id="outPais" value="País:" styleClass="texto"/>
							<h:selectOneMenu id="lstPais" value="#{BarrioBean.idPaisSeleccionado}"
								binding="#{BarrioBean.pais}" valueChangeListener="#{BarrioBean.cambiarProvincias}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200" onchange="submit()">
								<f:selectItems id="itemsPais" value="#{BarrioBean.paisItem}"/>
							</h:selectOneMenu>
							
							<h:outputText id="outProvi" value="Provincia:" styleClass="texto"/>
							<h:selectOneMenu id="lstProvincia" value="#{BarrioBean.idProvinciaSeleccionada}"
								binding="#{BarrioBean.provincia}" valueChangeListener="#{BarrioBean.cambiarPartidos}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200" onchange="submit()">
								<f:selectItems id="itemsProvincia" value="#{BarrioBean.provinciaItem}"/>
							</h:selectOneMenu>
							
							<h:outputText id="outPartido" value="Partido:" styleClass="texto"/>
							<h:selectOneMenu id="lstPartido" value="#{BarrioBean.idPartidoSeleccionada}"
								binding="#{BarrioBean.partido}" valueChangeListener="#{BarrioBean.cambiarLocalidades}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200" onchange="submit()">
								<f:selectItems id="itemsPartido" value="#{BarrioBean.partidoItem}"/>
							</h:selectOneMenu>

							<h:outputText id="outLoca" value="Localidad:" styleClass="texto"/>
							<h:selectOneMenu id="lstLocalidad" value="#{BarrioBean.idLocalidadSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200">
								<f:selectItems id="itemsLocalidad" value="#{BarrioBean.localidadItems}"/>
							</h:selectOneMenu>
							
							<h:outputText id="outBarrio" value="Descripción Barrio:" styleClass="texto"/>
							<h:inputText id="descripcionFiltro" value="#{BarrioBean.barrio.descripcion}"
								styleClass="bordeceldatext" maxlength="50" size="50"
								style=" width : 200px;" onfocus="encender(this);" onblur="apagar(this);"/>
							
							<h:outputText id="ti" value="Riesgoso:" styleClass="texto"/>
							<h:selectBooleanCheckbox value="#{BarrioBean.esRiesgoso}" id="riesgoso"/>
														
							<h:outputText id="vi" value="Villa:" styleClass="texto"/>
							<h:selectBooleanCheckbox value="#{BarrioBean.esVilla}" id="villa"/>
							
						</h:panelGrid>
					</s:fieldset>
					</h:panelGrid>
					
					<f:verbatim>
						<br id="br">
					</f:verbatim>
					
					<f:verbatim><hr id="linea" align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{BarrioBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{BarrioBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{BarrioBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

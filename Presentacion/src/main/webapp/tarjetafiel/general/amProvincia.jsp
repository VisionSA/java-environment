<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{ProvinciaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amProvinciaForm').submit();
		}
	</s:script>
</head>
<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amProvinciaForm');" onload="if('${ProvinciaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

	<section class="content-header">
  <h1>
    ${ProvinciaBean.tituloCorto}
    <small>${ProvinciaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
<secure:check/>
	<h:form id="amProvinciaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ProvinciaBean.popup.mostrar}">
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
								dialogTitle="#{ProvinciaBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ProvinciaBean.popup.nombreIcono}" />
							<h:outputText value="#{ProvinciaBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ProvinciaBean.irANuevoProvincia}" 
								onclick="clickLink('nuevoProvincia');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
							<x:commandButton action="#{ProvinciaBean.irAModificarProvincia}" 
								onclick="clickLink('modificarProvincia');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
							<x:commandButton action="#{ProvinciaBean.irAListarProvincia}" 
								onclick="clickLink('listarProvincia');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoProvincia" action="#{ProvinciaBean.irANuevoProvincia}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarProvincia" action="#{ProvinciaBean.irAModificarProvincia}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarProvincia" action="#{ProvinciaBean.irAListarProvincia}" forceId="true" style="display: block;"/>
						
					<h:panelGrid columns="1" align="center" width="850" id="uno">
						<s:fieldset legend="Provincia">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							
							<h:outputText value="Pa�s:" styleClass="texto"/>
							<h:selectOneMenu id="lstPais" value="#{ProvinciaBean.idPaisSeleccionada}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ProvinciaBean.paisItems}"/>
							</h:selectOneMenu>
							
							<h:outputText value="Nombre Provincia:" styleClass="texto"/>
							<h:inputText id="nombreFiltro" value="#{ProvinciaBean.provincia.nombre}"
								styleClass="bordeceldatext" maxlength="100" size="100"
								style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>
							
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ProvinciaBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ProvinciaBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ProvinciaBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

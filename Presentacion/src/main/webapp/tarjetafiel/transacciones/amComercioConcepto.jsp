<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{ComercioConceptoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amComercioConceptoForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amComercioConceptoForm');" onload="if('${ComercioConceptoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amComercioConceptoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ComercioConceptoBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
					<jsp:include page="/inc/navigation_test.jsp" />
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp" >
						<jsp:param name="tituloLargo" value="${ComercioConceptoBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${ComercioConceptoBean.tituloCorto}"/>
					</jsp:include>
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{ComercioConceptoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ComercioConceptoBean.popup.nombreIcono}" />
							<h:outputText value="#{ComercioConceptoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{ComercioConceptoBean.irANuevoComercioConcepto}" 
								onclick="clickLink('nuevoComercioConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{ComercioConceptoBean.irAModificarComercioConcepto}" 
								onclick="clickLink('modificarComercioConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{ComercioConceptoBean.irAListarComercioConcepto}" 
								onclick="clickLink('listarComercioConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoComercioConcepto" action="#{ComercioConceptoBean.irANuevoComercioConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarComercioConcepto" action="#{ComercioConceptoBean.irAModificarComercioConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarComercioConcepto" action="#{ComercioConceptoBean.irAListarComercioConcepto}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="2" width="350">
						<h:outputText value="idComercio:" styleClass="texto"/>
						<h:selectOneMenu id="lstComercio" value="#{ComercioConceptoBean.idComercioSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{ComercioConceptoBean.comercioItems}"/>
						</h:selectOneMenu>
						<h:outputText value="idConcepto:" styleClass="texto"/>
						<h:selectOneMenu id="lstConcepto" value="#{ComercioConceptoBean.idConceptoSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{ComercioConceptoBean.conceptoItems}"/>
						</h:selectOneMenu>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ComercioConceptoBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ComercioConceptoBean.cancelar}" styleClass="botones" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
		</x:panelLayout>
	</h:form>
</center>
</body>
</html>
</f:view>

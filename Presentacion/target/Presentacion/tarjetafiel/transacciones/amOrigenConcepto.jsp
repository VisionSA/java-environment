<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{OrigenConceptoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amOrigenConceptoForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amOrigenConceptoForm');" onload="if('${OrigenConceptoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amOrigenConceptoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!OrigenConceptoBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${OrigenConceptoBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${OrigenConceptoBean.tituloCorto}"/>
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
								dialogTitle="#{OrigenConceptoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{OrigenConceptoBean.popup.nombreIcono}" />
							<h:outputText value="#{OrigenConceptoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{OrigenConceptoBean.irANuevoOrigenConcepto}" 
								onclick="clickLink('nuevoOrigenConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{OrigenConceptoBean.irAModificarOrigenConcepto}" 
								onclick="clickLink('modificarOrigenConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{OrigenConceptoBean.irAListarOrigenConcepto}" 
								onclick="clickLink('listarOrigenConcepto');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoOrigenConcepto" action="#{OrigenConceptoBean.irANuevoOrigenConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarOrigenConcepto" action="#{OrigenConceptoBean.irAModificarOrigenConcepto}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarOrigenConcepto" action="#{OrigenConceptoBean.irAListarOrigenConcepto}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="2" width="350">
						<h:outputText value="generaCodAutorizacion:" styleClass="texto"/>
						<h:inputText id="generacodautorizacionFiltro" value="#{OrigenConceptoBean.origenConcepto.generaCodAutorizacion}"
						styleClass="bordeceldatext" maxlength="1" size="1"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="idConcepto:" styleClass="texto"/>
						<h:selectOneMenu id="lstConcepto" value="#{OrigenConceptoBean.idConceptoSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{OrigenConceptoBean.conceptoItems}"/>
						</h:selectOneMenu>
						<h:outputText value="idOrigenes:" styleClass="texto"/>
						<h:selectOneMenu id="lstOrigenen" value="#{OrigenConceptoBean.idOrigenenSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{OrigenConceptoBean.origenenItems}"/>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{OrigenConceptoBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{OrigenConceptoBean.cancelar}" styleClass="botones" />
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

<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{FormaPagoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amFormaPagoForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amFormaPagoForm');" onload="if('${FormaPagoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amFormaPagoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!FormaPagoBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${FormaPagoBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${FormaPagoBean.tituloCorto}"/>
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
								dialogTitle="#{FormaPagoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{FormaPagoBean.popup.nombreIcono}" />
							<h:outputText value="#{FormaPagoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{FormaPagoBean.irANuevoFormaPago}" 
								onclick="clickLink('nuevoFormaPago');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{FormaPagoBean.irAModificarFormaPago}" 
								onclick="clickLink('modificarFormaPago');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{FormaPagoBean.irAListarFormaPago}" 
								onclick="clickLink('listarFormaPago');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoFormaPago" action="#{FormaPagoBean.irANuevoFormaPago}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarFormaPago" action="#{FormaPagoBean.irAModificarFormaPago}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarFormaPago" action="#{FormaPagoBean.irAListarFormaPago}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="2" width="350">
						<h:outputText value="activo:" styleClass="texto"/>
						<h:inputText id="activoFiltro" value="#{FormaPagoBean.formaPago.activo}"
						styleClass="bordeceldatext" maxlength="1" size="1"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="descripcion:" styleClass="texto"/>
						<h:inputText id="descripcionFiltro" value="#{FormaPagoBean.formaPago.descripcion}"
						styleClass="bordeceldatext" maxlength="50" size="50"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{FormaPagoBean.grabar}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{FormaPagoBean.cancelar}" styleClass="btn btn-primary btn-flat" />
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

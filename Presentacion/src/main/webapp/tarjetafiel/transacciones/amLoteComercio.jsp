<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{LoteComercioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amLoteComercioForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amLoteComercioForm');" onload="if('${LoteComercioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amLoteComercioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LoteComercioBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${LoteComercioBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${LoteComercioBean.tituloCorto}"/>
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
								dialogTitle="#{LoteComercioBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{LoteComercioBean.popup.nombreIcono}" />
							<h:outputText value="#{LoteComercioBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{LoteComercioBean.irANuevoLoteComercio}" 
								onclick="clickLink('nuevoLoteComercio');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{LoteComercioBean.irAModificarLoteComercio}" 
								onclick="clickLink('modificarLoteComercio');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{LoteComercioBean.irAListarLoteComercio}" 
								onclick="clickLink('listarLoteComercio');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoLoteComercio" action="#{LoteComercioBean.irANuevoLoteComercio}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarLoteComercio" action="#{LoteComercioBean.irAModificarLoteComercio}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarLoteComercio" action="#{LoteComercioBean.irAListarLoteComercio}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="2" width="350">
						<h:outputText value="cantCupones:" styleClass="texto"/>
						<h:inputText id="cantcuponesFiltro" value="#{LoteComercioBean.loteComercio.cantCupones}"
						styleClass="bordeceldatext" maxlength="2560" size="2560"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaRecepcion:" styleClass="texto"/>
						<h:inputText id="fecharecepcionFiltro" value="#{LoteComercioBean.loteComercio.fechaRecepcion}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="montoTotal:" styleClass="texto"/>
						<h:inputText id="montototalFiltro" value="#{LoteComercioBean.loteComercio.montoTotal}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="codComercio:" styleClass="texto"/>
						<h:selectOneMenu id="lstCodComercio" value="#{LoteComercioBean.idCodComercioSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							onblur="apagar(this);" >
							<f:selectItems value="#{LoteComercioBean.codcomercioItems}"/>
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{LoteComercioBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{LoteComercioBean.cancelar}" styleClass="botones" />
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

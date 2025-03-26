<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{LiqClienteBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amLiqClienteForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amLiqClienteForm');" onload="if('${LiqClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amLiqClienteForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LiqClienteBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${LiqClienteBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${LiqClienteBean.tituloCorto}"/>
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
								dialogTitle="#{LiqClienteBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{LiqClienteBean.popup.nombreIcono}" />
							<h:outputText value="#{LiqClienteBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{LiqClienteBean.irANuevoLiqCliente}" 
								onclick="clickLink('nuevoLiqCliente');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
							<x:commandButton action="#{LiqClienteBean.irAModificarLiqCliente}" 
								onclick="clickLink('modificarLiqCliente');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
							<x:commandButton action="#{LiqClienteBean.irAListarLiqCliente}" 
								onclick="clickLink('listarLiqCliente');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoLiqCliente" action="#{LiqClienteBean.irANuevoLiqCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarLiqCliente" action="#{LiqClienteBean.irAModificarLiqCliente}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarLiqCliente" action="#{LiqClienteBean.irAListarLiqCliente}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelPrincipalUno" columns="2" width="350">
						<h:outputText value="fechaCierre:" styleClass="texto"/>
						<h:inputText id="fechacierreFiltro" value="#{LiqClienteBean.liqCliente.fechaCierre}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaCierreAnterior:" styleClass="texto"/>
						<h:inputText id="fechacierreanteriorFiltro" value="#{LiqClienteBean.liqCliente.fechaCierreAnterior}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaLiq:" styleClass="texto"/>
						<h:inputText id="fechaliqFiltro" value="#{LiqClienteBean.liqCliente.fechaLiq}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaProxCierre:" styleClass="texto"/>
						<h:inputText id="fechaproxcierreFiltro" value="#{LiqClienteBean.liqCliente.fechaProxCierre}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaProximoVto:" styleClass="texto"/>
						<h:inputText id="fechaproximovtoFiltro" value="#{LiqClienteBean.liqCliente.fechaProximoVto}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="fechaVtoAnterior:" styleClass="texto"/>
						<h:inputText id="fechavtoanteriorFiltro" value="#{LiqClienteBean.liqCliente.fechaVtoAnterior}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="intComp:" styleClass="texto"/>
						<h:inputText id="intcompFiltro" value="#{LiqClienteBean.liqCliente.intComp}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="intPunitorios:" styleClass="texto"/>
						<h:inputText id="intpunitoriosFiltro" value="#{LiqClienteBean.liqCliente.intPunitorios}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="pago3cuotas:" styleClass="texto"/>
						<h:inputText id="pago3cuotasFiltro" value="#{LiqClienteBean.liqCliente.pago3cuotas}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="pago6cuotas:" styleClass="texto"/>
						<h:inputText id="pago6cuotasFiltro" value="#{LiqClienteBean.liqCliente.pago6cuotas}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="pagoMinimo:" styleClass="texto"/>
						<h:inputText id="pagominimoFiltro" value="#{LiqClienteBean.liqCliente.pagoMinimo}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="primerVtoFecha:" styleClass="texto"/>
						<h:inputText id="primervtofechaFiltro" value="#{LiqClienteBean.liqCliente.primerVtoFecha}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="primerVtoMonto:" styleClass="texto"/>
						<h:inputText id="primervtomontoFiltro" value="#{LiqClienteBean.liqCliente.primerVtoMonto}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="segundoVtoFecha:" styleClass="texto"/>
						<h:inputText id="segundovtofechaFiltro" value="#{LiqClienteBean.liqCliente.segundoVtoFecha}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="segundoVtoMonto:" styleClass="texto"/>
						<h:inputText id="segundovtomontoFiltro" value="#{LiqClienteBean.liqCliente.segundoVtoMonto}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="seguroDeudor:" styleClass="texto"/>
						<h:inputText id="segurodeudorFiltro" value="#{LiqClienteBean.liqCliente.seguroDeudor}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="tasaAdelanto:" styleClass="texto"/>
						<h:inputText id="tasaadelantoFiltro" value="#{LiqClienteBean.liqCliente.tasaAdelanto}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="tasaRefinanc:" styleClass="texto"/>
						<h:inputText id="tasarefinancFiltro" value="#{LiqClienteBean.liqCliente.tasaRefinanc}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="tercerVtoFecha:" styleClass="texto"/>
						<h:inputText id="tercervtofechaFiltro" value="#{LiqClienteBean.liqCliente.tercerVtoFecha}"
						styleClass="bordeceldatext" maxlength="4" size="4"
						style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="tercerVtoMonto:" styleClass="texto"/>
						<h:inputText id="tercervtomontoFiltro" value="#{LiqClienteBean.liqCliente.tercerVtoMonto}"
						styleClass="bordeceldatext" maxlength="2562" size="2562"
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{LiqClienteBean.grabar}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{LiqClienteBean.cancelar}" styleClass="btn btn-primary btn-flat" />
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

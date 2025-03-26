<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{CtaCteClienteBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCtaCteClienteForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amCtaCteClienteForm');" onload="if('${CtaCteClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amCtaCteClienteForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CtaCteClienteBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${CtaCteClienteBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${CtaCteClienteBean.tituloCorto}"/>
					</jsp:include>
					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog"
									dialogVar="viewDialog"
									styleClass="viewDialog"
									dialogTitle="#{CtaCteClienteBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{CtaCteClienteBean.popup.nombreIcono}" />
								<h:outputText value="#{CtaCteClienteBean.popup.mensaje}" styleClass="texto"/>
							</h:panelGrid>
							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{CtaCteClienteBean.irANuevoCtaCteCliente}" 
									onclick="clickLink('nuevoCtaCteCliente');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="botones" title="Agregar nueva."/>
								<x:commandButton action="#{CtaCteClienteBean.irAModificarCtaCteCliente}" 
									onclick="clickLink('modificarCtaCteCliente');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="botones" title="Seguir modificando."/>
								<x:commandButton action="#{CtaCteClienteBean.irAListarCtaCteCliente}" 
									onclick="clickLink('listarCtaCteCliente');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="botones" title="Ir al listado."/>
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="nuevoCtaCteCliente" action="#{CtaCteClienteBean.irANuevoCtaCteCliente}" forceId="true" style="display: block;"/>
						<x:commandLink id="modificarCtaCteCliente" action="#{CtaCteClienteBean.irAModificarCtaCteCliente}" forceId="true" style="display: block;"/>
						<x:commandLink id="listarCtaCteCliente" action="#{CtaCteClienteBean.irAListarCtaCteCliente}" forceId="true" style="display: block;"/>
						
						<s:fieldset legend="Cuenta Corriente Cliente" id="fieldPrincipalUno">
							<h:panelGrid id="panelPrincipalUno" columns="4" width="600" align="center">
							
								<h:outputText value="Comprobante: " styleClass="texto" id="comprobante"/>
								<h:inputText id="comprobanteFiltro" value="#{CtaCteClienteBean.ctaCteCliente.comprobante}" styleClass="bordeceldainferior" 
									maxlength="8" size="8" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Id Parent: " styleClass="texto" id="idParent"/>
								<h:inputText id="idparentFiltro" value="#{CtaCteClienteBean.ctaCteCliente.idParent}" styleClass="bordeceldainferior" maxlength="20" 
									size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Contabilizado: " styleClass="texto" id="contabilizado"/>
								<x:selectBooleanCheckbox value="#{CtaCteClienteBean.contabilizado}" id="contabilizadoBoolean" forceId="true"/>
								
								<h:outputText value="Impacto: " styleClass="texto" id="estadoImpacto"/>
								<x:selectBooleanCheckbox value="#{CtaCteClienteBean.estadoImpacto}" id="estadoimpactoBoolean" forceId="true"/>
								
								<h:outputText value="Cta. Contable Debe: " styleClass="texto" id="ctaCtabDebe"/>
								<h:inputText id="ctacontabledebeFiltro" value="#{CtaCteClienteBean.ctaCteCliente.ctacontabledebe}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Cta. Contable Haber: " styleClass="texto" id="ctacontablehaber"/>
								<h:inputText id="ctacontablehaberFiltro" value="#{CtaCteClienteBean.ctaCteCliente.ctacontablehaber}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
								
								<h:outputText value="Fecha Contable: " styleClass="texto" id="fechaContable"/>
								<x:inputCalendar id="contableFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
				                	currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaContable}" renderAsPopup="true"
						            styleClass="bordeceldainferior" style="width: 100px" immediate="true"
				        		    popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
				        		    
				        		<h:outputText value="Fecha Facturación: " styleClass="texto" id="fechaFacturacion"/>
								<x:inputCalendar id="contableFacturacion" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
				                	currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaFacturacion}" renderAsPopup="true"
						            styleClass="bordeceldainferior" style="width: 100px" immediate="true"
				        		    popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
								
								<h:outputText value="Cliente: " styleClass="texto" id="cliente"/>
								<h:selectOneMenu id="lstCliente" value="#{CtaCteClienteBean.idClienteSeleccionada}"	styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style="width: 150">
									<f:selectItems value="#{CtaCteClienteBean.clienteItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Concepto Detalle: " styleClass="texto" id="conceptoDetalle"/>
								<h:selectOneMenu id="lstConceptoDetalle" value="#{CtaCteClienteBean.idConceptoDetalleSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 150">
									<f:selectItems value="#{CtaCteClienteBean.conceptoDetalleItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Liquidación Cliente: " styleClass="texto" id="liqCleinte"/>
								<h:selectOneMenu id="lstLiqCliente" value="#{CtaCteClienteBean.idLiqClienteSeleccionada}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style="width: 150">
									<f:selectItems value="#{CtaCteClienteBean.liqClienteItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Id Operador: " styleClass="texto" id="operador"/>
								<h:inputText id="idoperadorFiltro" value="#{CtaCteClienteBean.ctaCteCliente.idOperador}" styleClass="bordeceldainferior" maxlength="4" 
									size="4" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Id Origen: " styleClass="texto" id="idOrigen"/>
								<h:inputText id="idorigenFiltro" value="#{CtaCteClienteBean.ctaCteCliente.idOrigen}" styleClass="bordeceldainferior" maxlength="20" 
									size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
																
								<h:outputText value="Id Tranascción: " styleClass="texto" id="transacciones"/>
								<h:inputText id="idtranasccionesFiltro" value="#{CtaCteClienteBean.ctaCteCliente.idTranascciones}" styleClass="bordeceldainferior" maxlength="20" 
									size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Importe:" styleClass="texto" id="importe"/>
								<h:inputText id="importeFiltro" value="#{CtaCteClienteBean.ctaCteCliente.importe}" styleClass="bordeceldainferior" maxlength="20" 
									size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Nro. Cuota: " styleClass="texto" id="nroCuota"/>
								<h:inputText id="nrocuotaFiltro" value="#{CtaCteClienteBean.ctaCteCliente.nroCuota}" styleClass="bordeceldainferior" maxlength="4" 
									size="4" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" immediate="true" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Nro. Origen: " styleClass="texto" id="nroOrigen"/>
								<h:inputText id="nroorigenFiltro" value="#{CtaCteClienteBean.ctaCteCliente.nroOrigen}" styleClass="bordeceldainferior" maxlength="8" 
									size="8" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Signo: " styleClass="texto" id="signo"/>
								<h:inputText id="signoFiltro" value="#{CtaCteClienteBean.ctaCteCliente.signo}" styleClass="bordeceldainferior" maxlength="4" 
									size="4" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
							</h:panelGrid>
						</s:fieldset>
						
						<f:verbatim><br></f:verbatim>
						
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
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{CtaCteClienteBean.grabar}" styleClass="botones"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CtaCteClienteBean.cancelar}" styleClass="botones" />
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

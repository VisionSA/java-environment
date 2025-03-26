<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{CtaCteComercioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCtaCteComercioForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amCtaCteComercioForm');" onload="if('${CtaCteComercioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
	<secure:check/>

	<h:form id="amCtaCteComercioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CtaCteComercioBean.popup.mostrar}">
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
						<jsp:param name="tituloLargo" value="${CtaCteComercioBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${CtaCteComercioBean.tituloCorto}"/>
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
									dialogTitle="#{CtaCteComercioBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{CtaCteComercioBean.popup.nombreIcono}" />
								<h:outputText value="#{CtaCteComercioBean.popup.mensaje}" styleClass="texto"/>
							</h:panelGrid>
							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{CtaCteComercioBean.irANuevoCtaCteComercio}" 
									onclick="clickLink('nuevoCtaCteComercio');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="botones" title="Agregar nueva."/>
								<x:commandButton action="#{CtaCteComercioBean.irAModificarCtaCteComercio}" 
									onclick="clickLink('modificarCtaCteComercio');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="botones" title="Seguir modificando."/>
								<x:commandButton action="#{CtaCteComercioBean.irAListarCtaCteComercio}" 
									onclick="clickLink('listarCtaCteComercio');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="botones" title="Ir al listado."/>
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="nuevoCtaCteComercio" action="#{CtaCteComercioBean.irANuevoCtaCteComercio}" forceId="true" style="display: block;"/>
						<x:commandLink id="modificarCtaCteComercio" action="#{CtaCteComercioBean.irAModificarCtaCteComercio}" forceId="true" style="display: block;"/>
						<x:commandLink id="listarCtaCteComercio" action="#{CtaCteComercioBean.irAListarCtaCteComercio}" forceId="true" style="display: block;"/>
						
						<s:fieldset legend="Cuenta Corriente Comercio" id="fieldPrincipalUno">
							<h:panelGrid id="panelPrincipalUno" columns="4" width="600" align="center">
															
								<h:outputText value="Cta.Contable Debe: " styleClass="texto" id="ctaContableDebe"/>
								<h:inputText id="ctacontabledebeFiltro" value="#{CtaCteComercioBean.ctaCteComercio.ctacontabledebe}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Cta. Contable Haber: " styleClass="texto" id="ctaContableHaber"/>
								<h:inputText id="ctacontablehaberFiltro" value="#{CtaCteComercioBean.ctaCteComercio.ctacontablehaber}" styleClass="bordeceldainferior" 
									maxlength="10" size="10" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								
								<h:outputText value="Parent: " styleClass="texto" id="idParent"/>
								<h:inputText id="idparentFiltro" value="#{CtaCteComercioBean.ctaCteComercio.idParent}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Fecha Contable: " styleClass="texto" id="fechaContable"/>
								<x:inputCalendar id="contableFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
				                	currentDayCellClass="currentDayCell" value="#{CtaCteComercioBean.fechaContable}" renderAsPopup="true"
						            styleClass="bordeceldainferior" style="width: 100px" immediate="true"
				        		    popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
																
								<h:outputText value="Fecha Facturacion: " styleClass="texto" id="fechaFacturacion"/>
								<x:inputCalendar id="facturacionFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
				                	currentDayCellClass="currentDayCell" value="#{CtaCteComercioBean.fechaFacturacion}" renderAsPopup="true"
						            styleClass="bordeceldainferior" style="width: 100px" immediate="true"
				        		    popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
				        		    
								<h:outputText value="Fecha Lote: " styleClass="texto" id="fechaLote"/>
								<x:inputCalendar id="loteFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
				                	currentDayCellClass="currentDayCell" value="#{CtaCteComercioBean.fechaLote}" renderAsPopup="true"
						            styleClass="bordeceldainferior" style="width: 100px" immediate="true"
				        		    popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
								
								<h:outputText value="Concepto: " styleClass="texto" id="idConceptoDetalle"/>
								<h:selectOneMenu id="lstConceptoDetalle" value="#{CtaCteComercioBean.idConceptoDetalleSeleccionada}" styleClass="lista"
									immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
									<f:selectItems value="#{CtaCteComercioBean.conceptoDetalleItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Liquidación: " styleClass="texto" id="liqComercio"/>
								<h:selectOneMenu id="lstLiqComercio" value="#{CtaCteComercioBean.idLiqComercioSeleccionada}" styleClass="lista" 
									immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
									<f:selectItems value="#{CtaCteComercioBean.liqComercioItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Código Comercio: " styleClass="texto" id="codComercio"/>
								<h:selectOneMenu id="lstCodComercio" value="#{CtaCteComercioBean.idCodComercioSeleccionada}" styleClass="lista" 
									immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
									<f:selectItems value="#{CtaCteComercioBean.codComercioItems}"/>
								</h:selectOneMenu>
								
								<h:outputText value="Id Lote Comercio: " styleClass="texto" id="idLoteComercio"/>
								<h:inputText id="idlotecomercioFiltro" value="#{CtaCteComercioBean.ctaCteComercio.idLoteComercio}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Id Operador: " styleClass="texto" id="idOperador"/>
								<h:inputText id="idoperadorFiltro" value="#{CtaCteComercioBean.ctaCteComercio.idOperador}" styleClass="bordeceldainferior" 
									maxlength="4" size="4" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Id Origen: " styleClass="texto" id="idOrigen"/>
								<h:inputText id="idorigenFiltro" value="#{CtaCteComercioBean.ctaCteComercio.idOrigen}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="id Tranascción:" styleClass="texto" id="idTranasccion"/>
								<h:inputText id="idtranasccionesFiltro" value="#{CtaCteComercioBean.ctaCteComercio.idTranascciones}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Importe:" styleClass="texto" id="importe"/>
								<h:inputText id="importeFiltro" value="#{CtaCteComercioBean.ctaCteComercio.importe}" styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Motivo Impacto: " styleClass="texto" id="motivoImpacto"/>
								<h:inputText id="motivoimpactoFiltro" value="#{CtaCteComercioBean.ctaCteComercio.motivoImpacto}" styleClass="bordeceldainferior" maxlength="20" size="20"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
									
								<h:outputText value="Nro. Cuota: " styleClass="texto" id="nroCuota"/>
								<h:inputText id="nrocuotaFiltro" value="#{CtaCteComercioBean.ctaCteComercio.nroCuota}" styleClass="bordeceldainferior" maxlength="4" size="4"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Nro. Origen: " styleClass="texto" id="nroOrigen"/>
								<h:inputText id="nroorigenFiltro" value="#{CtaCteComercioBean.ctaCteComercio.nroOrigen}" styleClass="bordeceldainferior" maxlength="8" size="8"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Signo: " styleClass="texto" id="signo"/>
								<h:inputText id="signoFiltro" value="#{CtaCteComercioBean.ctaCteComercio.signo}" styleClass="bordeceldainferior" maxlength="4" size="4"
									style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
								
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
							<x:commandButton id="buttonGrabar" value="Guardar" action="#{CtaCteComercioBean.grabar}" styleClass="botones"/>
							<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CtaCteComercioBean.cancelar}" styleClass="botones" />
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

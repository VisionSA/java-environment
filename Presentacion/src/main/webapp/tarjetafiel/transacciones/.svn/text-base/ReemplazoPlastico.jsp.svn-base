<%--@I5275--%><%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html>
	<head>
	<title><h:outputText
		value="Tarjeta Fiel - Reimpresion Plastico" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('popupCierreCuentaClienteForm').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>

	</head>

	<%@include file="/inc/head.inc"%>

	<body onbeforeunload="ShowWait('popupCierreCuentaClienteForm');"
		onload="${ReemplazoPlasticoBean.popupReport}; 
		if('${ReemplazoPlasticoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}" >
	
	<center><secure:check /> 
	<h:form	id="popupCierreCuentaClienteForm" enctype="multipart/form-data">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">
			
			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />		
				</f:subview>
			</f:facet>
			
			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp">
						<jsp:param name="tituloLargo"
							value="${ReemplazoPlasticoBean.tituloLargo}" />
						<jsp:param name="tituloCorto"
							value="${ReemplazoPlasticoBean.tituloCorto}" />
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
			                   dialogTitle="#{ReemplazoPlasticoBean.tituloCorto}">
					    	<h:panelGrid columns="2" width="500">
						    	<x:graphicImage value="/img/#{ReemplazoPlasticoBean.popup.nombreIcono}" />
						        <h:outputText value=" #{ReemplazoPlasticoBean.popup.mensaje}" styleClass="texto"/>
					        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					        	<h:panelGrid columns="2" width="300">
					        		<x:commandButton action="#{ReemplazoPlasticoBean.irASalir}" 
					        		 	onclick="clickLink('listoCierre');dojo.widget.byId('viewDialog').hide();window.close();"
					        		 	value="Aceptar" styleClass="botones"/>
					        	</h:panelGrid>					        		 
							</h:panelGrid>
							
						</s:modalDialog>
						<x:commandLink id="listoCierre" action="#{ReemplazoPlasticoBean.irASalir}" forceId="true" style="display: block;"/>
						<x:commandLink id="listoCierreComprobante" action="#{ReemplazoPlasticoBean.generarComprobante}" forceId="true" style="display: block;"/>
						
						<h:panelGroup id="PanelDatos">
							<s:fieldset legend="Datos del Cliente Seleccionado" id="primerField" >
								<h:panelGrid id="panelCliente" columns="2" width="400" align="left">
									
									<h:outputText value="Numero de Cuenta:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.nroCuenta}" styleClass="texto" />
								
									<h:outputText value="Apellido:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.cliente.individuo.apellido}" styleClass="texto" />
									
									<h:outputText value="Nombre:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.cliente.individuo.nombres}" styleClass="texto" />
									
									<h:outputText value="Condicion:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.condicion}" styleClass="texto" />
									
									<h:outputText value="Tipo Documento:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.cliente.individuo.tipoDocumento.descripcion}" styleClass="texto" />
									
									<h:outputText value="Nro Documento:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.cliente.individuo.nroDocumento}" styleClass="texto" />
									
									<h:outputText value="Cuil:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.cliente.individuo.cuil}" styleClass="texto" />
									
									
								</h:panelGrid>
							</s:fieldset>
<%--@I5275--%>							<h:panelGrid id="panelBotones1" columns="2" width="100" align="right">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
<%--@F5275--%>								<h:commandButton id="buttonCerrar" value="Cerrar" rendered="#{!ReemplazoPlasticoBean.hayReimpresion}" action="#{ReemplazoPlasticoBean.cancelar}"  
								styleClass="botones" />
							</h:panelGrid>
								
							<s:fieldset legend="Datos del Plastico a Reimprimir" id="segundoField" rendered="#{ReemplazoPlasticoBean.hayReimpresion}">
								<h:panelGrid id="panelPlastico" columns="2" width="650" align="left">
									
									<h:outputText value="Numero:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.plastico.numero}" styleClass="texto" />
								
<%--@I5636--%>									<h:outputText value="Vigencia Desde:" styleClass="texto" />
<%--@F5636--%>									<h:outputText value="#{ReemplazoPlasticoBean.plastico.vigenciaDesde}" styleClass="texto" />									
									<h:outputText value="Vigencia Hasta:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.plastico.vigenciaHasta}" styleClass="texto" />
									
									<h:outputText value="Estado:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.plastico.estadoPlastico.descripcion}" styleClass="texto" />
									
									<h:outputText value="Lugar:" styleClass="texto" />
									<h:outputText value="#{ReemplazoPlasticoBean.plastico.plasticoLugar.descripcion}" styleClass="texto" />
									
									<h:outputText value="Motivo Reimpresion:" styleClass="texto"/>
									<h:panelGroup id="panMot" >
										<h:selectOneMenu id="lstMotivos" value="#{ReemplazoPlasticoBean.idMotivoLugar}" immediate="true"
	       					 					styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
	       					 					valueChangeListener="#{ReemplazoPlasticoBean.cambiarLugar}" binding="#{ReemplazoPlasticoBean.motivoHtml}"
												onchange="submit()">
		       								<f:selectItems value="#{ReemplazoPlasticoBean.listaMotivosItem}"/>
					       				</h:selectOneMenu>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="Lugar Plastico:" styleClass="texto"/>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="#{ReemplazoPlasticoBean.descripcionLugar}" style="COLOR: blue" styleClass="texto"/>
					       			</h:panelGroup>	
								
								</h:panelGrid>
							</s:fieldset> 
							
							<h:panelGrid id="panelBotones2" columns="2" width="100" align="right">
								<h:commandButton id="buttonEjecutar" value="Ejecutar Reimpresion" action="#{ReemplazoPlasticoBean.ejecutarReimpresion}" 
								styleClass="botones" rendered="#{ReemplazoPlasticoBean.hayReimpresion}"/>
								<h:commandButton id="buttonCancelar" value="Cancelar" action="#{ReemplazoPlasticoBean.cancelar}"  
								styleClass="botones" rendered="#{ReemplazoPlasticoBean.hayReimpresion}" />
									
							</h:panelGrid>  
						</h:panelGroup>		
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>
	</h:form></center>
	</body>
	</html>
</f:view>
<%--@F5275--%>




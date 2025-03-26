<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{CliClienteBeanModificacionCuenta.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCliClienteFormModCuenta').submit();
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

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amCliClienteFormModCuenta');" onload="if('${CliClienteBeanModificacionCuenta.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>

	<h:form id="amCliClienteFormModCuenta">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CliClienteBeanModificacionCuenta.popup.mostrar}">
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
					
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp" >
						<jsp:param name="tituloLargo" value="${CliClienteBeanModificacionCuenta.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${CliClienteBeanModificacionCuenta.tituloCorto}"/>
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
								dialogTitle="#{CliClienteBeanModificacionCuenta.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{CliClienteBeanModificacionCuenta.popup.nombreIcono}" />
							<h:outputText value="#{CliClienteBeanModificacionCuenta.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{CliClienteBeanModificacionCuenta.irAContinuar}" 
								onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
								value="Continuar" styleClass="botones" title="Continuar en la pantalla."/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton action="#{CliClienteBeanModificacionCuenta.irASalir}" 
								onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
								value="Salir" styleClass="botones" title="Salir."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="continuar" action="#{CliClienteBeanModificacionCuenta.irAContinuar}" forceId="true" style="display: block;"/>
					<x:commandLink id="salir" action="#{CliClienteBeanModificacionCuenta.irASalir}" forceId="true" style="display: block;"/>

					<h:panelGrid id="panelSolic" columns="3">
						<h:outputText value="Solicitud Nro:" styleClass="texto" rendered="false"/>
						<h:outputText value="#{CliClienteBeanModificacionCuenta.nroSolicitud}" styleClass="texto" rendered="false"/>
						<x:commandButton id="buttonVerIndividuo" value="Ver Solicitud" action="#{CliClienteBeanModificacionCuenta.irAIndividuo}" styleClass="botones" />
					</h:panelGrid>

					<h:panelGrid id="panelDatos" columns="3">
						<h:outputText value="Titular de la cuenta:" styleClass="texto"/>
						<h:outputText value="#{CliClienteBeanModificacionCuenta.individuoEvaluacion.apellido}, #{CliClienteBeanModificacionCuenta.individuoEvaluacion.nombres}" styleClass="texto"/>
						
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					</h:panelGrid>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					<h:panelGrid columns="1" width="650" align="center" id="panelAltaGA">
						<s:fieldset legend="Garantes" id="garantes">
							<h:panelGrid columns="1" width="650" align="center" id="panelInternoAltaGarante">
			
								<c:if test="${!empty CliClienteBeanModificacionCuenta.listGarante}">
									<h:dataTable value="#{CliClienteBeanModificacionCuenta.listGarante}"
										id="tablaUno" styleClass="standardTable" 
										headerClass="dataTable_Header"
										footerClass="standardTable_Header" 
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="garante" style=" width : 300px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{garante.individuoEvaluacion.apellido}"/>
										</h:column>
										
										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
                                            <h:outputText value="#{garante.individuoEvaluacion.nombres}" />
										</h:column>

										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Accion" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
											<h:outputText value="#{garante.accion}" />
										</h:column>
										
									</h:dataTable>
								</c:if>
			
							</h:panelGrid>

						</s:fieldset>
					
						<s:fieldset legend="Adicionales" id="adicionales">
							<h:panelGrid columns="1" width="650" align="center" id="panelAltaAdicional">
			
							    	<c:if test="${!empty CliClienteBeanModificacionCuenta.listAdicional}">
									<h:dataTable value="#{CliClienteBeanModificacionCuenta.listAdicional}" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										id="tablaDos" styleClass="standardTable"
										var="adicional" style=" width : 300px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.individuoEvaluacion.apellido}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{adicional.individuoEvaluacion.nombres}"/>
										</h:column>

										<h:column>
										    <f:facet name="header">
										        <h:outputText value="Accion" styleClass="texto" style="font: bold;color: white" />
										    </f:facet>
											<h:outputText value="#{adicional.accion}" />
										</h:column>
										
									</h:dataTable>
								</c:if>
			
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{CliClienteBeanModificacionCuenta.grabar}" styleClass="botones" rendered="#{CliClienteBeanModificacionCuenta.verGrabar}"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CliClienteBeanModificacionCuenta.cancelar}" styleClass="botones" />
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

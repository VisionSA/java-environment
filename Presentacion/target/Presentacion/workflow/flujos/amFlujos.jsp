<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html>
	<head>
	<title><h:outputText value="#{FlujosBean.tituloLargo}" /></title>
	    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('amFlujosForm').submit();
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

	<body onbeforeunload="ShowWait('amFlujosForm');"
		onload="if('${FlujosBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<center><h:form id="amFlujosForm">

		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!FlujosBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
					<jsp:include page="/inc/navigation_test.jsp" />
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp" >
	            		<jsp:param name="tituloLargo" value="${FlujosBean.tituloLargo}"/>
    	        		<jsp:param name="tituloCorto" value="${FlujosBean.tituloCorto}"/>
        	    	</jsp:include>

					<h:panelGrid columns="1" align="center" id="PanelPricipalProceso">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
						
						<h:panelGrid id="panelPrincipalUno" columns="5">
							
							<h:outputText id="outTareaOrigen" value="Tarea Origen: " styleClass="text"/>
							<h:outputText id="outputTareaOrigen" value="#{FlujosBean.tituloTarea}" styleClass="textoblue"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>											
							<h:outputText id="outdescripcion" value="Descripción: " styleClass="text"/>
							<h:inputText id="uotputTareaDescripcion" value="#{FlujosBean.descripcion}" size="100" maxlength="100"
								styleClass="bordeceldatext" style=" width : 200px;" onfocus="encender(this);" onblur="apagar(this);"/>

							<h:outputText id="outTareaDestino" value="Tarea Destino: " styleClass="text"/>
							<h:selectOneMenu id="listaTareas" value="#{FlujosBean.idTareaSelect}" 
	       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
	       					 	onblur="apagar(this);">
		        				<f:selectItems value="#{FlujosBean.tareaSelect}"/>
		       				</h:selectOneMenu>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:panelGroup>
								<x:selectBooleanCheckbox  value="#{FlujosBean.flujoRetorno}" />
								<h:outputText value="Flujo de retorno" styleClass="textoblues"/>
							</h:panelGroup>
							<x:commandButton id="buttonAgragraFlujo" value="Agregar Flujo" action="#{FlujosBean.grabarFlujo}" styleClass="botones"/>
						</h:panelGrid>
						
						<s:layoutingTitlePane id="listadoFlujos" label=" Lista de Flujos"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane">
							
								<h:dataTable value="#{FlujosBean.tareaFlujosList}" id="tablaFlujos"
									styleClass="standardTable" headerClass="standardTable_Header"
									footerClass="standardTable_Header"
									rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
									var="flujoTabla" style="width: 900px">
									
									<h:column>
										<f:facet name="header">
											<h:outputText value="Tarea Destino" styleClass="texto"
												style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{flujoTabla.tituloTareaDestino}" styleClass="texto" style="align: center"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto"
												style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{flujoTabla.descripcionFlujo}" styleClass="texto" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Flujo de retorno" styleClass="texto"
												style="font: bold;color: white;" />
										</f:facet>
										<x:selectBooleanCheckbox  value="#{flujoTabla.flujo.retorno}" disabled="true"/>
									</h:column>
									
									<h:column>
										<x:commandLink action="#{FlujosBean.mostrarCondicion}" id="mostraCondicionesLink">
											<f:param id="idFlujoTabla" name="idFlujoTabla" value="#{flujoTabla.flujo.idFlujo}"/>
											<x:graphicImage value="/img/icon/OrderView.gif" title="Mostrar Condiciones Asociadas." border="0"/>
										</x:commandLink>
									</h:column>
											
									<h:column>
										<x:commandLink id="agregarCondicionesLink" action="#{FlujosBean.irACondiciones}">
											<f:param id="idFlujoTabla" name="idFlujoTabla" value="#{flujoTabla.flujo.idFlujo}"/>
											<x:graphicImage value="/img/cat_pad.gif" title="Administrar Condiciones." border="0"/>
										</x:commandLink>
									</h:column>
											                        
			                        <h:column>
										<x:commandLink action="#{FlujosBean.eliminarFlujo}" id="eliminarFlujoLink">
											<f:param id="idFlujoTabla" name="idFlujoTabla" value="#{flujoTabla.flujo.idFlujo}"/>
											<x:graphicImage value="/img/borrar.gif" title="Eliminar el flujo." border="0" 
											onclick="confirm('Se eliminara el flujo y todas sus condiciones asociadas. \n\t Confirma la eliminación del flujo?.');"/>
										</x:commandLink>
									</h:column>
									
								</h:dataTable>

						</s:layoutingTitlePane>
						
						<f:verbatim>
							<br>
						</f:verbatim>

						<c:if test="${!empty FlujosBean.flujosCondicionesList}">
							<s:layoutingTitlePane id="lstCondiciones"
								label="Lista Condiciones" containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">

									<h:dataTable value="#{FlujosBean.flujosCondicionesList}"
										id="tablaCondiciones" styleClass="standardTable"
										headerClass="standardTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="condTab" style="width: 900px">

										<h:column>
											<f:facet name="header">
												<h:outputText id="idTipoCondicon" value="Tipo Condición" styleClass="texto" 
													style="font: bold;color: white;" />
											</f:facet>
											<h:outputText id="outputTipoCondicion" value="#{condTab.tipoCondicion.descripcion}" 
												styleClass="texto"	style="align: center" />
										</h:column>
										
										<h:column>
											<h:outputText id="outParenIni" value="#{condTab.armarParentesisiInic}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText id="idValorUno" value="Primer Valor" styleClass="texto" 
													style="font: bold;color: white;" />
											</f:facet>
											<h:outputText id="outputValorUno" value="#{condTab.atributoUno}" styleClass="texto"
												style="align: center" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText id="idOperador" value="Conector" styleClass="texto" 
													style="font: bold;color: white;" />
											</f:facet>
											<h:outputText id="outputOperador" value="#{condTab.flujoCondicion.operadorCondicion.descripcion}" styleClass="texto" style="align: center" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText id="idValor" value="Segundo Valor" styleClass="texto" 
													style="font: bold;color: white;" />
											</f:facet>
											<h:outputText id="outputValor" value="#{condTab.segundoValor}" styleClass="texto" style="align: center"/>
										</h:column>
										
										<h:column>
											<h:outputText id="outParenFin" value="#{condTab.armarParentesisiFin}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText id="idTipoUnion" value="Tipo Union" styleClass="texto" 
													style="font: bold;color: white;" />
											</f:facet>
											<h:outputText id="outputTipoUnion" value="#{condTab.tipoUnion}" styleClass="texto"
												style="align: center" />
										</h:column>

									</h:dataTable>

							</s:layoutingTitlePane>
						</c:if>

						<c:if test="${empty FlujosBean.flujosCondicionesList}">
							<s:layoutingTitlePane id="lstCondiciones" label="Lista Condiciones"
								containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">
								<h:outputText id="noTieneCondiciones" value="No hay  Condiciones para mostrar." styleClass="text" style="COLOR: #ff0000;"/>
							</s:layoutingTitlePane>
						</c:if>
			
						<f:verbatim>
							<hr align="center" width="700">
						</f:verbatim>
												
						<h:panelGrid columns="10" width="727" id="panelBotonera">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton id="buttonCancelar" value="Volver a Proceso" action="#{FlujosBean.cancelar}" styleClass="botones" style=" width : 150px;"/>
						</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

			<%@include file="/inc/page_footer.jsp"%>

		</x:panelLayout>
	</h:form></center>
	</body>
	</html>
</f:view>

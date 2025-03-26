<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
   	<title><h:outputText value="#{EvaluacionReglasBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('reglasForm').submit();
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

<body  onbeforeunload="ShowWait('reglasForm');" onload="if('${EvaluacionReglasBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
<h:form id="reglasForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!EvaluacionReglasBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
	<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
	<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog" styleClass="viewDialog" dialogTitle="#{EvaluacionReglasBean.tituloCorto}">
		<h:panelGrid columns="3" width="500">
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<x:graphicImage value="/img/#{EvaluacionReglasBean.popup.nombreIcono}" />
			<h:outputText value=" #{EvaluacionReglasBean.popup.mensaje}" styleClass="texto"/>
		</h:panelGrid>
						        
		<h:panelGrid columns="7" width="500">
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<x:commandButton action="#{EvaluacionReglasBean.aceptarPopup}" onclick="clickLink('aceptarPopup');dojo.widget.byId('viewDialog').hide();"
				value="Aceptar" styleClass="botones" title="Continuar con el alta del individuo."/>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<x:commandButton action="#{EvaluacionReglasBean.rechazarPopup}" onclick="clickLink('rechazarPopup');dojo.widget.byId('viewDialog').hide();"
				value="Rechazar" styleClass="botones" title="Continuar."/>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			<x:commandButton action="#{EvaluacionReglasBean.cancelarPopup}" onclick="clickLink('cancelarPopup');dojo.widget.byId('viewDialog').hide();"
				value="Cancelar" styleClass="botones" title="Continuar."/>
			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
		</h:panelGrid>
	</s:modalDialog>
	
	<x:commandLink id="aceptarPopup" action="#{EvaluacionReglasBean.aceptarPopup}" forceId="true" style="display: block;"/>
	<x:commandLink id="rechazarPopup" action="#{EvaluacionReglasBean.rechazarPopup}" forceId="true" style="display: block;"/>
	<x:commandLink id="cancelarPopup" action="#{EvaluacionReglasBean.cancelarPopup}" forceId="true" style="display: block;"/>
	
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
            		<jsp:param name="tituloLargo" value="${EvaluacionReglasBean.tituloLargo}"/>
            		<jsp:param name="tituloCorto" value="${EvaluacionReglasBean.tituloCorto}"/>
            	</jsp:include>
            	
           	<%-- INCLUDE PARA LOS ERRORES --%> 	
			<h:panelGroup id="errores">
				<jsp:include page="/inc/error.jsp" />
			</h:panelGroup>
            	
            	<h:panelGrid columns="1" align="center" id="PanelPrincipal" width="900">
					 <s:fieldset id="fieldSubTitulo">
					 	
					 	<h:panelGrid id="panelIndividuo" columns="6" width="350" align="center">
							<h:outputText value="#{EvaluacionReglasBean.subtitulo}" styleClass="texto"/>
							<h:outputText value="#{EvaluacionReglasBean.nombreCompleto}" styleClass="textoblue"/>
							<x:commandButton id="buttonVerIndividuo" value="Ver Individuo" action="#{EvaluacionReglasBean.irAIndividuo}" styleClass="botones" />
							<x:commandButton id="buttonAceptar2" value="Aceptar" action="#{EvaluacionReglasBean.aceptar}" styleClass="botones" />
							<x:commandButton id="buttonRechazar2" value="Rechazar" action="#{EvaluacionReglasBean.rechazar}" 
									styleClass="botones" onclick="return confirm('Confirma el rechazo de a las reglas para este individuo.');"/>
							<x:commandButton id="buttonSalir2" value="Salir" action="#{EvaluacionReglasBean.cerrar}" styleClass="botones" />
						</h:panelGrid>
					 
                        <s:fieldset legend="Reglas Automáticas" id="reglasAutomaticas">
                        	<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: auto; HEIGHT: 300px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:panelGrid columns="1" id="panelReglasAutomaticas" width="800" align="center">
									<h:dataTable value="#{EvaluacionReglasBean.reglasAutomaticas}"
										id="tablaReglasAutomaticas" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="reglasAutomaticas" style=" width : 700px;" align="center">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Regla" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{reglasAutomaticas.esquemaIndividuo.esquemaRegla.regla.descripcion}"/>
										</h:column>
													
									    <h:column>
											<f:facet name="header">
												<h:outputText value="Estado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="SI" rendered="#{reglasAutomaticas.seleccionado}" style="color: green;"/>
											<h:outputText value="NO" rendered="#{!reglasAutomaticas.seleccionado}" style="color: red;"/>
										</h:column>
													
										<h:column>
											<x:graphicImage value="/img/icon/Alert16.gif" title="Restrictivo" border="0" rendered="#{reglasAutomaticas.ponderacion}"/>
										</h:column>		
																			
									</h:dataTable>
									
								</h:panelGrid>
							</x:div>
						</s:fieldset>
					 	<f:verbatim>
					 		<br>
					 	</f:verbatim>
					    <s:fieldset legend="Reglas Manuales" id="reglasManuales">
					    	<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: auto; HEIGHT: 300px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:panelGrid columns="1" id="panelReglasManuales" width="800" align="center">
									<h:dataTable value="#{EvaluacionReglasBean.reglasManuales}"
										id="tablaReglasManuales" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="reglasManuales" style=" width : 700px;" align="center">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Regla" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{reglasManuales.esquemaIndividuo.esquemaRegla.regla.descripcion}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="SI" rendered="#{reglasManuales.seleccionado}" style="color: green;"/>
											<h:outputText value="NO" rendered="#{!reglasManuales.seleccionado}" style="color: red;"/>
											<h:selectBooleanCheckbox value="#{reglasManuales.seleccionado}" id="seleccionadoManual"> </h:selectBooleanCheckbox>
										</h:column>
															
										<h:column>
											<h:graphicImage value="/img/icon/Alert16.gif" rendered="#{reglasManuales.ponderacion}" title="Restrictivo"/>
										</h:column>						
																			
									</h:dataTable>
									
								</h:panelGrid>
							</x:div>
						</s:fieldset>
						<f:verbatim>
							<br>
						</f:verbatim>
						<h:panelGrid columns="8" width="727" id="panelRefresh">
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<x:commandButton id="buttonRefresh" title="Grabar y refrescar reglas." action="#{EvaluacionReglasBean.refresh}" image="/img/icon/reload_alt32.png"/>
						</h:panelGrid>
						
						<s:fieldset legend="Estado de los individuos según evaluación de reglas" id="estadoIndividuo">
					    	<h:panelGrid columns="1" id="panelEstadoIndividuo" width="800" align="center">
								<h:dataTable value="#{EvaluacionReglasBean.estadosIndividuos}"
									id="tablaEstadosIndividuo" styleClass="standardTable" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
									var="estadoIndividuo" style=" width : 700px;" align="center">
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Individuo" styleClass="texto" style="font: bold;color: white;" id="outTipoIndividuo"/>
											</f:facet>
											<h:outputText value="#{estadoIndividuo.descripcionTipoIndividuo}" id="tipoIndividuo"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido y Nombre Individuo" styleClass="texto" style="font: bold;color: white;" id="outDatosPersonales"/>
											</f:facet>
											<h:outputText value="#{estadoIndividuo.datosPersonales}" id="datosPersonales"/>
										</h:column>
															
										<h:column>
											<f:facet name="header">
												<h:outputText value="Supero Evaluación Reglas" styleClass="texto" style="font: bold;color: white;" id="outDatosPersonales"/>
											</f:facet>
											<h:graphicImage value="/img/icon/ok.png" rendered="#{estadoIndividuo.estado}" id="imagenOk" title="Evaluación Reglas Aceptadas."/>
											<h:graphicImage value="/img/icon/stop.png" rendered="#{!estadoIndividuo.estado}" id="imagenStop" title="Evaluación Reglas Rechazada."/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado de Solicitud Individuo" styleClass="texto" style="font: bold;color: white;" id="outDatosPersonales"/>
											</f:facet>
											<h:graphicImage value="/img/icon/ok.png" rendered="#{estadoIndividuo.estadoSolicitud}" id="imagenOk" title="Individuo Aceptado"/>
											<h:graphicImage value="/img/icon/stop.png" rendered="#{!estadoIndividuo.estadoSolicitud}" id="imagenStop" title="Individuo Rechazado"/>
										</h:column>		
								</h:dataTable>
								
								<h:outputText value="Nota:" id="outNota" styleClass="textoblue"/>
								<h:panelGroup>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="El estado de la solicitud será aprobada si el titular es aceptado, en caso contrario la solicitud será rechazada automaticamente." id="outNota1" styleClass="textoblue"/>									
								</h:panelGroup>
								<f:verbatim>
									<br>
								</f:verbatim>
								<h:panelGrid columns="1" align="center" width="400">
									<h:panelGroup>
										<h:outputText value="Estado de la solicitud: " id="outNota2" styleClass="textoblue"/>
										<f:verbatim>&nbsp;</f:verbatim>
										<h:graphicImage value="/img/icon/ok.png" rendered="#{EvaluacionReglasBean.evaluarSolicitud}" id="imagenOk" title="Solicitud Aceptada."/>
										<h:graphicImage value="/img/icon/stop.png" rendered="#{!EvaluacionReglasBean.evaluarSolicitud}" id="imagenStop" title="Solicitud Rechazada"/>
									</h:panelGroup>
								</h:panelGrid>
							</h:panelGrid>
					    </s:fieldset>
					    					 
					    <h:panelGrid columns="10" width="727" id="panelBotonera">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="buttonAceptar" value="Aceptar" action="#{EvaluacionReglasBean.aceptar}" styleClass="botones" />
								<x:commandButton id="buttonRechazar" value="Rechazar" action="#{EvaluacionReglasBean.rechazar}" 
									styleClass="botones" onclick="return confirm('Confirma el rechazo de a las reglas para este individuo.');"/>
								<x:commandButton id="buttonSalir" value="Salir" action="#{EvaluacionReglasBean.cerrar}" styleClass="botones" />
						</h:panelGrid>
					 
					 </s:fieldset>
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

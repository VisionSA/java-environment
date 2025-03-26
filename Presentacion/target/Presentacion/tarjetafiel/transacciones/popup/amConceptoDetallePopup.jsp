<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Agregar Detalle"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
    <s:script language="javascript">
       function modificarFechaCierre() 
  		 {
  		    if (document.getElementById('amConceptoDetalleForm:activoFiltroChex').checked == true) {
  		        document.getElementById('amConceptoDetalleForm:pa').style.display = '';
  		 	} else {
  		 	    document.getElementById('FechaCierre').value = "";
  		 	    document.getElementById('amConceptoDetalleForm:pa').style.display = 'none';
  		 	}
  		 	return null;
  		 }
      
    
		function recargar() {
			document.getElementById('amConceptoDetalleForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
	</s:script>

</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amConceptoDetalleForm');" onload="modificarFechaCierre();">
       
<center>
	

	<h:form id="amConceptoDetalleForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ConceptoDetalleBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

		

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
		                 <h:panelGrid id="titu" align="center">
		                 	<h:outputText value="ALTA DE DETALLE" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					
					<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
					    <s:fieldset id="fieldPrincipal" legend="Detalle de Concepto">
					         <h:panelGrid columns="6" id="datosPrinci" align="center">
					         	    <h:outputText value="Nombre:" styleClass="texto"/>
									<h:inputText id="nombreFiltro" value="#{ConceptoDetalleBean.conceptoDetalle.nombre}"
									styleClass="bordeceldatext" maxlength="50" size="50"
									style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
									<h:outputText value="Concepto:" styleClass="texto" rendered="#{!ConceptoDetalleBean.conceptoSeleccionable}"/>
									<h:selectOneMenu id="lstConcepto" value="#{ConceptoDetalleBean.idConceptoSeleccionada}" rendered="#{!ConceptoDetalleBean.conceptoSeleccionable}"
										styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoDetalleBean.conceptoSeleccionado}"
										onblur="apagar(this);" >
									<f:selectItems value="#{ConceptoDetalleBean.conceptoItems}"/>
									</h:selectOneMenu>
									<h:outputText value="Activo:" styleClass="texto"/>
									<h:selectBooleanCheckbox id="activoFiltro" value="#{ConceptoDetalleBean.estado}"
									styleClass="bordeceldatext" />
									<h:selectOneRadio value="#{ConceptoDetalleBean.conceptoDetalle.impacta}" rendered="#{ConceptoDetalleBean.verComoImpacta}">
									   <f:selectItem itemLabel="Impacta X Cuota" itemValue="C"  />
								     	<f:selectItem itemLabel="Impacta General" itemValue="G" />
								    </h:selectOneRadio>
					         </h:panelGrid>
					         <s:fieldset id="cuenta" legend="Cuenta">
					                <h:panelGrid id="panelDeCuenta" columns="7" align="center">
					                		<h:outputText value="Debe:" styleClass="texto"/>
											<h:inputText id="ctacontabledebeFiltro" value="#{ConceptoDetalleBean.debe}"
											styleClass="bordeceldatext" disabled="true" style="width: 250;"/>
											<x:commandLink id="buscarCuentaLinkDebe" action="#{ConceptoDetalleBean.buscarCuentaDebe}">
												<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
											</x:commandLink>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<h:outputText value="Haber:" styleClass="texto"/>
											<h:inputText id="ctacontablehaberFiltro" value="#{ConceptoDetalleBean.haber}"
											styleClass="bordeceldatext" size="15" disabled="true" style="width: 250;"/>
											<x:commandLink id="buscarCuentaLinkHaber" action="#{ConceptoDetalleBean.buscarCuentaHaber}">
												<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
											</x:commandLink>
					                </h:panelGrid>
					         </s:fieldset>
					       	<c:if test="${(ConceptoDetalleBean.esAltaConcepto)||(!ConceptoDetalleBean.esAltaConcepto && ConceptoDetalleBean.alta)}" >
					         <s:fieldset id="fechas" legend="Fecha de Vigencia">
					                <h:panelGrid id="panelDeFechas" columns="6" align="center">
					                		<h:outputText value="Desde:" styleClass="texto"/>
											<x:inputCalendar id="FechaInicio" monthYearRowClass="yearMonthHeader"
											weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
				                			currentDayCellClass="currentDayCell" value="#{ConceptoDetalleBean.fechaDesde}" renderAsPopup="true"
						              		styleClass="bordeceldainferior" style="width: 90px"
						                	popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
						                	helpText="DD/MM/YYYY" 
						                	forceId="true"/>
											
											<h:panelGrid columns="2" id="pa" style="display:hidden"><h:outputText value="Hasta:" styleClass="texto"/> <x:inputCalendar id="FechaCierre" monthYearRowClass="yearMonthHeader" rendered="true"
											weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
				                			currentDayCellClass="currentDayCell" value="#{ConceptoDetalleBean.fechaHasta}" renderAsPopup="true"
						              		styleClass="bordeceldainferior" style="width: 90px"
						                	popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
						                	helpText="DD/MM/YYYY" 
						                	forceId="true"/></h:panelGrid>
											
						                	<h:outputText value="Incluir fecha cierre" styleClass="texto"/>
						                	<h:selectBooleanCheckbox id="activoFiltroChex" value="#{ConceptoDetalleBean.noIncluyeFechaCierre}" binding="#{ConceptoDetalleBean.incluyeFechaCierre}" onclick="modificarFechaCierre();"/>
						                	
					                </h:panelGrid>
					         </s:fieldset>
					         </c:if>
					         
					       	<c:if test="${!ConceptoDetalleBean.esAltaConcepto && !ConceptoDetalleBean.alta}">
					         <s:fieldset id="fechas" legend="Fecha de Vigencia">
					                <h:panelGrid id="panelDeFechas" columns="6" align="center">
					                     
					                		<h:outputText value="Desd:" styleClass="texto"/>
					                		<h:outputText value="#{ConceptoDetalleBean.fechaDesde}" />
											<h:outputText value="Hast:" styleClass="texto"/>
											<c:if test="${ConceptoDetalleBean.fechaHasta!=null}">
											  <h:outputText value="#{ConceptoDetalleBean.fechaHasta}"/>
											  <h:outputText value=""/>
											  <h:outputText value=""/>
											</c:if>
											<c:if test="${ConceptoDetalleBean.fechaHasta==null}">
												<h:panelGrid columns="2" id="pa" style="display:hidden"><h:outputText value="Hast:" styleClass="texto"/> <x:inputCalendar id="FechaCierre" monthYearRowClass="yearMonthHeader" rendered="true"
												weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
					                			currentDayCellClass="currentDayCell" value="#{ConceptoDetalleBean.fechaHasta}" renderAsPopup="true"
							              		styleClass="bordeceldainferior" style="width: 90px"
							                	popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
							                	helpText="DD/MM/YYYY" 
							                	forceId="true"/>
							               	</h:panelGrid>
							              
						                	<h:outputText value="Incluir fecha cierre" styleClass="texto"/>
						                	<h:selectBooleanCheckbox id="activoFiltroChex" value="#{ConceptoDetalleBean.noIncluyeFechaCierre}" binding="#{ConceptoDetalleBean.incluyeFechaCierre}" onclick="modificarFechaCierre();"/>
						                  </c:if>	
											
						            </h:panelGrid>
					         </s:fieldset>
					         </c:if>
					         
					         <h:panelGrid id="lastPan" columns="6" align="center">
					         		<h:outputText value="Orden:" styleClass="texto"/>
									<h:inputText id="ordenFiltro" value="#{ConceptoDetalleBean.conceptoDetalle.orden}"
									styleClass="bordeceldatext" maxlength="4" size="4" onkeypress="return soloEnteros(this,event);"
									style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
									<h:outputText value="Parent:" styleClass="texto"/>
									<h:selectOneMenu id="lstParent" value="#{ConceptoDetalleBean.parentElegido}" onchange="submit();"
										styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoDetalleBean.menuParent}"
										onblur="apagar(this);" >
									<f:selectItems value="#{ConceptoDetalleBean.listaParent}"/>
									</h:selectOneMenu>
									
									
									
									<h:outputText value="Prioridad:" styleClass="texto"/>
									<h:selectOneMenu id="lstPrioridad" value="#{ConceptoDetalleBean.prioridadElegida}"
										styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoDetalleBean.menuPrioridad}"
										onblur="apagar(this);" >
									<f:selectItems value="#{ConceptoDetalleBean.listaPrioridad}"/>
									</h:selectOneMenu>
									<h:outputText value="Prioridad de la Imputacion:" styleClass="texto"/>
									<h:inputText id="prioridadimputacionFiltro" value="#{ConceptoDetalleBean.conceptoDetalle.prioridadImputacion}"
									styleClass="bordeceldatext" maxlength="2560" size="2560" onkeypress="return soloEnteros(this,event);"
									style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
									<h:outputText value="Signo:" styleClass="texto"/>
									<h:selectOneMenu id="lstSigno" value="#{ConceptoDetalleBean.signoElegido}"
										styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoDetalleBean.menuSigno}"
										onblur="apagar(this);" >
									<f:selectItems value="#{ConceptoDetalleBean.listaSigno}"/>
									</h:selectOneMenu>
									<h:outputText value="Tipo:" styleClass="texto"/>
									<h:selectOneMenu id="lstTipo" value="#{ConceptoDetalleBean.tipoElegido}"
										styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConceptoDetalleBean.menuTipo}"
										onblur="apagar(this);" >
									<f:selectItems value="#{ConceptoDetalleBean.listaTipo}"/>
									</h:selectOneMenu>
					         </h:panelGrid>
					    </s:fieldset>
					    
					    
					    
					</h:panelGrid>
					
					
					   <h:panelGrid id="panReglas" columns="1" align="center">
					   <%-- GESTIONAR LAS REGLAS DE DETALLE --%>

								<s:fieldset legend="Reglas" id="detalles">
								<h:panelGrid columns="2" id="panelReglas" width="650" align="center">
									<h:panelGroup id="panelGroupDetalle">
									<h:dataTable value="#{ConceptoDetalleBean.listReglas}"
										id="tablaDetallesConceptoRegla" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="reg" style=" width :400px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Clase" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:inputText style="width: 400px;" id="detReg" value="#{reg.conceptoDetalleRegla.detalle}"/>
										</h:column>
										
										
										<h:column>
											<x:commandLink action="#{ConceptoDetalleBean.eliminarRegla}" id="eliminarReglaLink">
												<f:param id="idReglaElim" name="idReglaElim" value="#{reg.indice}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar una regla." border="0" id="botonImagenTresReg" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<c:if test="${empty ConceptoDetalleBean.listReglas}">
										<h:panelGroup id="panelGroupBotonesREg">
											<x:commandLink id="agregarReglaLink" action="#{ConceptoDetalleBean.agregarRegla}">
												<x:graphicImage value="/img/cat_pad.gif" title="Agregar Regla." border="0" id="botonImagenCuatroReg"/>
											</x:commandLink>
										</h:panelGroup>
                                    </c:if>
								</h:panelGrid>
							</s:fieldset>
					       </h:panelGrid>
					
					
					 <h:panelGrid id="panTarget" columns="1" align="center">
					   <%--GESTIONAR LAS REGLAS DE Target--%>

								<s:fieldset legend="Target" id="Target">
								<h:panelGrid columns="2" id="panelTarget" width="650" align="center">
									<h:panelGroup id="panelGroupTarget">
									<h:dataTable value="#{ConceptoDetalleBean.listTarget}"
										id="tablaDetallesTarget" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="tar" style=" width :600px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{tar.indice}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Detalle" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:inputText style="width: 150px;" id="detTar" value="#{tar.conceptoDetalleTarget.detalle}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Sql" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:inputTextarea rows="2" style="width: 400px;" id="sqlTar" value="#{tar.conceptoDetalleTarget.sql}"/>
										</h:column>
										
										
										<h:column>
											<x:commandLink action="#{ConceptoDetalleBean.eliminarTarget}" id="eliminarTargetLink">
												<f:param id="idTargetElim" name="idTargetElim" value="#{tar.indice}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar una regla." border="0" id="botonImagenTresTar" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</h:panelGroup>
									
									<h:panelGroup id="panelGroupBotonesDelTarget">
										<x:commandLink id="agregarTargetLink" action="#{ConceptoDetalleBean.agregarTarget}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar Target." border="0" id="botonImagenCuatroTar"/>
										</x:commandLink>
									</h:panelGroup>

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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ConceptoDetalleBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ConceptoDetalleBean.cancelar}" styleClass="botones" />
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

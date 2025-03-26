<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Lista de Precios"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
    <s:script language="javascript">
		function recargar() {
			document.getElementById('amListaPreciosForm').submit();
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

<body onbeforeunload="ShowWait('amListaPreciosForm');">
     
      
<center>
	

	<h:form id="amListaPreciosForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ComercioListaPrecioBean.popup.mostrar}">
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
		                 	<h:outputText value="ANEXAR LISTA DE PRECIOS A COMERCIO" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

                    <s:fieldset legend="Comercio Lista Precio" id="fieldPrincipalUno">
						<h:panelGrid id="panelPrincipalUno" columns="1" width="600" align="center">
						    <h:panelGrid columns="2" id="seleccion" align="center">
							    <h:outputText value="Lista Precio: " styleClass="texto" id="listaPrecio"/>
								<h:selectOneMenu id="lstListaPrecio" value="#{ComercioListaPrecioBean.idListaPrecioSeleccionada}" binding="#{ComercioListaPrecioBean.listaPrecioSeleccionadaMenu}"
									styleClass="lista" onchange="submit();" valueChangeListener="#{ComercioListaPrecioBean.mostrarListaPrecio}" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ComercioListaPrecioBean.listaPrecioItems}"/>
								</h:selectOneMenu>
						    </h:panelGrid>
						    <f:verbatim>&nbsp;</f:verbatim>
						    
						    <h:panelGrid columns="3" id="versionesExistentes" align="center" width="700">
                        	<h:outputText value="Versiones Anteriores:" styleClass="texto"/>
                        	<h:outputText value="Version Actual:" styleClass="texto"/>
                        	<h:outputText value="Versiones Futuras:" styleClass="texto"/>
                        	
						        
		                            <h:dataTable id="tblVersionesAnt" value="#{ComercioListaPrecioBean.listaPrecio.versionesAnteriores}" var="verAnt">
		                            	 <h:column>
			                            	<x:commandLink id="verVersionesAnt" action="#{ComercioListaPrecioBean.editarVersionAnterior}" value="Versión: #{verAnt.version} - Fecha Inicio: #{verAnt.diaMesAnioVigencia}" >
												<f:param id="idVerAnterior" name="idVerAnterior" value="#{verAnt.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
                             

						        
						            <x:commandLink id="verNuevosLink" action="#{ComercioListaPrecioBean.editarVersionVigente}" value="Versión: #{ComercioListaPrecioBean.listaPrecio.versionVigente.version} - Fecha Inicio: #{ComercioListaPrecioBean.listaPrecio.versionVigente.diaMesAnioVigencia}" >
										<f:param id="idVerActual" name="idVerActual" value="1"/>
									</x:commandLink>
						     
						        

						        
		                            <h:dataTable id="tblVersionesFut" value="#{ComercioListaPrecioBean.listaPrecio.versionesFuturas}" var="verFut">
		                                <h:column>
			                            	<x:commandLink id="verVersionesFut" action="#{ComercioListaPrecioBean.editarVersionFutura}" value="Versión: #{verFut.version} - Fecha Inicio: #{verFut.diaMesAnioVigencia}" >
												<f:param id="idVerFutura" name="idVerFutura" value="#{verFut.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
		                        
		                        

                        </h:panelGrid>
                        <h:panelGrid columns="1" align="center" id="fiell">
						<s:fieldset legend="Lista Precio" id="fieldPrincipalDos" style=" width : 700px;">     
						      <h:panelGrid id="panDescripcion" columns="6" align="center" style=" width : 650px;">
						            <h:outputText value="Arancel Comercio:" styleClass="texto"/>
									<h:outputText value="#{ComercioListaPrecioBean.versionEditada.comArancel}" styleClass="textoblue"/>
						            <h:outputText value="Fecha Vigencia:" styleClass="texto"/>
						            <x:inputCalendar id="fechaVigencia" monthYearRowClass="yearMonthHeader" disabled="true"
										weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
										currentDayCellClass="currentDayCell" value="#{ComercioListaPrecioBean.versionEditada.fechaVigencia}" renderAsPopup="true"
										styleClass="bordeceldainferior" style="width: 90px"
										popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
										helpText="DD/MM/YYYY" 
										forceId="true"/>
									<h:outputText value="Versión: " styleClass="texto"/>	
									<h:outputText value="#{ComercioListaPrecioBean.versionEditada.version}" styleClass="textoblue"/>	
						      </h:panelGrid>
						      <h:panelGrid columns="8" id="cicloDias" align="center">
						             <h:outputText value="Ciclo:" styleClass="texto"/>
						             <h:outputText value="#{ComercioListaPrecioBean.versionEditada.comCiclo}" styleClass="textoblue"/>
						             <h:outputText value="Días:" styleClass="texto"/>
						              <h:outputText value="#{ComercioListaPrecioBean.versionEditada.comDias}" styleClass="textoblue"/>
						             <h:outputText value="Fechas:" styleClass="texto"/>
						              <h:outputText value="#{ComercioListaPrecioBean.versionEditada.fechaLiquidacion}" styleClass="textoblue"/>
								      <h:outputText value="Difiere Liquidación" styleClass="texto" />
						              <h:selectBooleanCheckbox value="#{ComercioListaPrecioBean.versionEditada.difiereLiquidacionBoolean}" id="checkDifiere" disabled="true"/>
						              
						      </h:panelGrid>
						      <h:panelGrid columns="1" id="panelDelCalendario" align="center">
						      	
		                            <h:dataTable id="tblDelCalendario" value="#{ComercioListaPrecioBean.diasDePago}" var="dias" align="center">
		                            	<h:column>
			                            	<h:outputText value="Día Liquidacion: #{dias.fechaLiquidacion}" />
										</h:column>
										<h:column>
			                            	<h:outputText value="Día Pago: #{dias.fechaPago}" />
										</h:column>
		                            </h:dataTable>
                             	
						       
						   
						      
						      
						      </h:panelGrid>

						</s:fieldset>
						</h:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:panelGrid id="tablaItem" columns="1" align="center">
                             		
                             	
								<h:dataTable value="#{ComercioListaPrecioBean.items}" id="tablaDeItems"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 700px;">
						            <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Dias Diferimiento Comercio" styleClass="texto" style="font: bold;color: white;"/>
						                 </f:facet>
						                 <h:outputText id="diasDif" value="#{item.listaPrecioItem.comDiasDiferimiento}"/>
						            </h:column>		
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Comercio % TNA" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:outputText id="tnaComercio" value="#{item.listaPrecioItem.comTNA}"/>
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TEA comercio" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:outputText id="TEAcomercio" value="#{item.listaPrecioItem.comTEA}" />
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="Comercio % directo" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet>
						                  <h:outputText id="accesKey" value="#{item.listaPrecioItem.comPorcentajeDirecto}" />
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Comercio Cuota" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:outputText id="comCuotas" value="#{item.listaPrecioItem.comCuotas}"/>
						            </h:column>						
						            
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="Dias Diferimiento Cliente" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet> 
						                  <h:outputText id="cliDias" value="#{item.listaPrecioItem.cliDiasDiferimiento}" />
						            </h:column>
						            
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="Cliente % TNA" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet>
						                  <h:outputText id="tnaCliente" value="#{item.listaPrecioItem.cliTNA}"/>
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TEA cliente" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:outputText id="TEAcliente" value="#{item.listaPrecioItem.cliTEA}" />
						            </h:column>
						                        
						            
								</h:dataTable>
					        
                    							
						    
						
						</h:panelGrid>
						    
						    <h:panelGrid columns="1" id="oculta"  align="center">
						    
							
							
							
							
							<f:verbatim>&nbsp;</f:verbatim>
							<h:panelGrid columns="4" id="generales" align="center">
							<h:outputText value="Fecha De Carga: " styleClass="texto" id="fechaDesde"/>
							<x:inputCalendar id="FechaDesde" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
								popupButtonStyleClass="standard_bold" currentDayCellClass="currentDayCell"  disabled="true"
								value="#{ComercioListaPrecioBean.fechaCarga}" renderAsPopup="true"
				                styleClass="bordeceldainferior" style="width: 150px" immediate="true"
		        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
							<h:outputText value="Introduzca el número de Código Posnet: " styleClass="texto" />
							<h:selectOneMenu disabled="#{!ComercioListaPrecioBean.agregar}" id="lstCodigoDePosnet" value="#{ComercioListaPrecioBean.idCodigoPosnet}" binding="#{ComercioListaPrecioBean.listaCodigoPosnetMenu}"
								styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ComercioListaPrecioBean.listaCodigosPosnetItems}"/>
							</h:selectOneMenu>
							
							
							
							
							</h:panelGrid>
						</h:panelGrid>
						</h:panelGrid>
						</s:fieldset>
						
					   
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{ComercioListaPrecioBean.grabarDesdePopup}" actionListener="#{ComercioListaPrecioBean.recargarYCerrarPopup}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ComercioListaPrecioBean.cancelarDesdePopup}" onclick="window.close();" styleClass="botones" />
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

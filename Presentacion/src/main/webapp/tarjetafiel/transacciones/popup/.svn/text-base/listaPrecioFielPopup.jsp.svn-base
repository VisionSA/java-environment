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
	<h:panelGroup rendered="#{!ListaPrecioFielPopupBean.popup.mostrar}">
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
		                 	<h:outputText value="ANEXAR LISTA DE PRECIOS A CONCEPTO" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

                    <s:fieldset legend="Comercio Lista Precio" id="fieldPrincipalUno">
						<h:panelGrid id="panelPrincipalUno" columns="1" width="600" align="center">
						    <h:panelGrid columns="2" id="seleccion" align="center">
							    <h:outputText value="Lista Precio: " styleClass="texto" id="listaPrecio"/>
								<h:selectOneMenu id="lstListaPrecio" value="#{ListaPrecioFielPopupBean.idListaPrecioSeleccionada}" binding="#{ListaPrecioFielPopupBean.listaPrecioSeleccionadaMenu}"
									styleClass="lista" onchange="submit();" valueChangeListener="#{ListaPrecioFielPopupBean.mostrarListaPrecio}" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ListaPrecioFielPopupBean.listaPrecioItems}"/>
								</h:selectOneMenu>
						    </h:panelGrid>
						    <f:verbatim>&nbsp;</f:verbatim>
						    
						    <h:panelGrid columns="3" id="versionesExistentes" align="center" width="700">
                        	<h:outputText value="Versiones Anteriores:" styleClass="texto"/>
                        	<h:outputText value="Version Actual:" styleClass="texto"/>
                        	<h:outputText value="Versiones Futuras:" styleClass="texto"/>
                        	
						     <c:choose>
						        <c:when test="${!empty ListaPrecioFielPopupBean.listaPrecio.versionesAnteriores}">
		                            <h:dataTable id="tblVersionesAnt" value="#{ListaPrecioFielPopupBean.listaPrecio.versionesAnteriores}" var="verAnt">
		                            	 <h:column>
			                            	<x:commandLink id="verVersionesAnt" action="#{ListaPrecioFielPopupBean.editarVersionAnterior}" value="Versión: #{verAnt.version} - Fecha Inicio: #{verAnt.diaMesAnioVigencia}" >
												<f:param id="idVerAnterior" name="idVerAnterior" value="#{verAnt.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
                                </c:when>
						        <c:otherwise>
						        	<h:outputText value="No existen versiones anteriores." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
                            <c:choose>
						        <c:when test="${null != ListaPrecioBean.listaPrecio.versionVigente}">
						            <x:commandLink id="verNuevosLink" rendered="#{ListaPrecioFielPopupBean.hayVersionVigente}" action="#{ListaPrecioFielPopupBean.editarVersionVigente}" value="Versión: #{ListaPrecioFielPopupBean.listaPrecio.versionVigente.version} - Fecha Inicio: #{ListaPrecioFielPopupBean.listaPrecio.versionVigente.diaMesAnioVigencia}" >
										<f:param id="idVerActual" name="idVerActual" value="1"/>
									</x:commandLink>
							     </c:when>
							     <c:otherwise>
						            <h:outputText value="No existe una versión actual." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
                            <c:choose>
						        <c:when test="${!empty ListaPrecioFielPopupBean.listaPrecio.versionesFuturas}">
						        
		                            <h:dataTable id="tblVersionesFut" value="#{ListaPrecioFielPopupBean.listaPrecio.versionesFuturas}" var="verFut">
		                                <h:column>
			                            	<x:commandLink id="verVersionesFut" action="#{ListaPrecioFielPopupBean.editarVersionFutura}" value="Versión: #{verFut.version} - Fecha Inicio: #{verFut.diaMesAnioVigencia}" >
												<f:param id="idVerFutura" name="idVerFutura" value="#{verFut.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
		                        </c:when>
						        <c:otherwise>
						            <h:outputText value="No existen versiones futuras." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
		                        

                        </h:panelGrid>
                        <h:panelGrid columns="1" align="center" id="fiell">
						<s:fieldset legend="Lista Precio Fiel" id="fieldPrincipalUnoFiel" style=" width : 700px;" rendered="#{ListaPrecioFielPopupBean.hayListaSeleccionada}">     
						      <h:panelGrid id="panDescripcionFiel" columns="9" align="center" style=" width : 650px;"> 
						            <h:outputText value="TNA:" styleClass="texto"/>
									<h:inputText id="idTnaFiel" value="#{ListaPrecioFielPopupBean.versionEditada.tnaFiel}" onkeypress="return soloDecimalesPrecisos(this,event,2);"
									styleClass="bordeceldatext"	style="height : 22px; width : 112px;" onfocus="encender(this);" onblur="apagar(this);"/>
									<h:outputText value="TEM:" styleClass="texto"/>
									<h:inputText id="idTemFiel" value="#{ListaPrecioFielPopupBean.versionEditada.temFiel}" onkeypress="return soloDecimalesPrecisos(this,event,2);"
									styleClass="bordeceldatext"	style="height : 22px; width : 112px;" onfocus="encender(this);" onblur="apagar(this);"/>
						            <h:outputText value="Fecha Vigencia:" styleClass="texto"/>
						            <x:inputCalendar id="fechaVigenciaFiel" monthYearRowClass="yearMonthHeader" disabled="#{!ListaPrecioFielPopupBean.alta}"
										weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
										currentDayCellClass="currentDayCell" value="#{ListaPrecioFielPopupBean.fechaVigencia}" renderAsPopup="true"
										styleClass="bordeceldainferior" style="width: 90px"
										popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
										helpText="DD/MM/YYYY" 
										forceId="true"/>
									
								    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Version: " styleClass="texto"/>	
									<h:outputText value="#{ListaPrecioFielPopupBean.versionEditada.version}" styleClass="textoblue"/>	
						      </h:panelGrid>
						</s:fieldset>
						</h:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:panelGrid id="tablaItem" columns="1" align="center" rendered="#{ListaPrecioFielPopupBean.hayListaSeleccionada}">
                             		
                             	
								<h:dataTable value="#{ListaPrecioBean.items}" id="tablaDeItemsFiel"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 300px;">
						            <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Nro.Cuota" styleClass="texto" style="font: bold;color: white;"/>
						                 </f:facet>
						                 <h:inputText id="nrosCuota"
						                  value="#{item.listaPrecioItem.nroCuotaFiel}" style=" width : 50px;" styleClass="bordeceldatext" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);"/>
						            </h:column>		
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Días Diferimiento" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:inputText id="diasDiferimientoFiel"
						                     value="#{item.listaPrecioItem.diasDiferimientoFiel}" 
						                     style=" width : 76px;"  styleClass="bordeceldatext" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);" onkeyup="teaComercio(this.value, this);" />
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TNA" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:inputText id="tnaFiel" value="#{item.listaPrecioItem.tnaXCuotaFiel}" style="width: 80px"  styleClass="textoblue"  onkeypress="return soloDecimalesPrecisos(this, event, 2);"/>
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="TEM" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet>
						                  <h:inputText id="temFiel" 
						                   value="#{item.listaPrecioItem.temXCuotaFiel}" style="width: 60px" styleClass="bordeceldatext" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
								</h:dataTable>
						
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						
						<c:if test="${!empty ListaPrecioBean.detallesFiel}">			
								<h:dataTable value="#{ListaPrecioBean.detallesFiel}" id="tablaDeDetallesFiel"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 300px;">
						            <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Descripción" styleClass="texto" style="font: bold;color: white;"/>
						                 </f:facet>
						                 <h:inputText id="descripcionDetalles"
						                  value="#{item.listaPrecioDetalle.descripcion}" style=" width : 207px;" styleClass="bordeceldatext"  onfocus="encender(this);" onblur="apagar(this);"/>
						            </h:column>		
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Monto" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:inputText id="montoDetalles"
						                     value="#{item.listaPrecioDetalle.monto}" 
						                     style=" width : 60px;"  styleClass="bordeceldatext" onkeypress="return soloDecimalesPrecisos(this,event,2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Porcentaje" styleClass="texto" style="font: bold;color: white;"/>
						                     </f:facet>
						                     <h:inputText id="porcentajeDetalle" value="#{item.listaPrecioDetalle.porcentaje}" style="width: 60px"  styleClass="textoblue"  onkeypress="return soloDecimalesPrecisos(this, event, 2);"/>
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="% Mínimo" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet>
						                  <h:inputText id="porcenMInimoDetalle" 
						                   value="#{item.listaPrecioDetalle.porcentajeMinimo}" style="width: 60px" styleClass="bordeceldatext" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="% Máximo" styleClass="texto" style="font: bold;color: white;"/>
						                  </f:facet>
						                  <h:inputText id="porcenMaximoDetalle" 
						                   value="#{item.listaPrecioDetalle.porcentajeMaximo}" style="width: 60px" styleClass="bordeceldatext" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						                        
								</h:dataTable>
					        </c:if>
						</h:panelGrid>
						    
						    <h:panelGrid columns="1" id="oculta"  align="center">
						    
							
							
							
							
							<f:verbatim>&nbsp;</f:verbatim>
							
						</h:panelGrid></h:panelGrid>
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
						<x:commandButton id="buttonGrabar" value="Aceptar" action="#{ListaPrecioFielPopupBean.grabarDesdePopup}" actionListener="#{ListaPrecioFielPopupBean.recargarYCerrarPopup}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ListaPrecioFielPopupBean.cancelarDesdePopup}" onclick="window.close();" styleClass="botones" />
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

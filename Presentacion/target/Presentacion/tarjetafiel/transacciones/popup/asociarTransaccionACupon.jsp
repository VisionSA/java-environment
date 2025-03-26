<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Asociar Cupon a una transaccion"/></title>
    
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
</head>
<%@include file="/inc/head.inc"%>

<body>
<script languaje="javascript">
	      
	    function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
         
				
    </script>

<center>
<h:form id="asociarTransForm" >
	
	<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="body">
	<h:panelGroup id="asociarTran">
	
		<h:outputText value="Asociar Cupón a Transacción" style="FONT-SIZE: large;" styleClass="texto" /> 
	    <%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>     
	
	    	<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog" 
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{ConciliacionCuponesBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ConciliacionCuponesBean.popup.nombreIcono}" />
							<h:outputText value="#{ConciliacionCuponesBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						
						
						<h:panelGrid columns="2" width="120" align="center" >
							<x:commandButton action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.rechazarCupon}" 
								onclick="clickLink('rechazarCupon');dojo.widget.byId('viewDialog').hide();"
								value="Si" styleClass="botones" title="Rechaza definitivamente el cupon."/>
							<x:commandButton action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.cancelarRechazarCupon}" 
								onclick="clickLink('cancelarRechazarCupon');dojo.widget.byId('viewDialog').hide();"
								value="No" styleClass="botones" title="Cancela el rechazo definitivo."/>
						</h:panelGrid>
						
						
				 </s:modalDialog>
				   <x:commandLink id="rechazarCupon" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.rechazarCupon}" forceId="true" style="display: block;"/>
				   <x:commandLink id="cancelarRechazarCupon" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.cancelarRechazarCupon}" forceId="true" style="display: block;"/>	
		
		
		<%-- Comienzan los cambios --%>
		
		<h:panelGrid columns="1" id="datos0" width="1000" align="center">
		            <h:panelGrid id="filtroLote" columns="3" width="450" cellspacing="0" cellpadding="0" align="center" >
		                 <h:outputText value="Nro Lote:" style="width: 250px;" id="outFiltroNroLote"/>
						<h:inputText   
							value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.nroLoteFiltro}"
							maxlength="11" disabled="true"
							style="width: 120px; display:hidden" id="nroLoteFiltroAsociacion"
							onkeypress="return soloEnteros(this,event);" />
						<x:commandLink value="" title="Buscar Cupones"
							actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.buscarCuponesNoAsoc}"
							id="busquedaAsociacion">
							<x:graphicImage id="imgnBusquedaAsociacion" value="/img/icon/srch_32.gif"
								style=" width : 32px;"></x:graphicImage>
						</x:commandLink>

		  </h:panelGrid>
		  
		  <s:fieldset legend="Seleccione Cupon" id="fldSelect">
		       <h:panelGrid columns="1" width="650" 	id="panelListNoConc" >
									<c:if test="${empty ConciliacionCuponesBean.popupAsociarCuponesATransaccion.listCuponesNoAsociados}">
										<h:outputText value="No existen cupones no asociados a una transaccion." id="outMsg"
											styleClass="texto" style="color:green" />
									</c:if>


									<c:if test="${!empty ConciliacionCuponesBean.popupAsociarCuponesATransaccion.listCuponesNoAsociados}">
										<h:dataTable 
											value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.listCuponesNoAsociados}"
											id="tablaNoConciliados" styleClass="standardTable"
											headerClass="dataTable_Header"
											footerClass="standardTable_Header"
											rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="obj" style=" width : 570px;">
											
											
											
											<h:column>
												<f:facet name="header">
													<h:outputText value="Lote" styleClass="texto"  id="outLote_h"
														style="font: bold;color: white;" />
												</f:facet>
												<h:outputText value="#{obj.loteComercio.idLoteComercio}"
													style=" width : 150px;" styleClass="textoblue"   id="outLote" />
											</h:column>

											<h:column>
												<f:facet name="header">
													<h:outputText value="Cupon" styleClass="texto"
														style="font: bold;color: white;"  id="outCupon_h" />
												</f:facet>
												<h:outputText value="#{obj.nroCupon}"
													style=" width : 150px;" styleClass="textoblue" id="outCupon"/>
											</h:column>
											
											<h:column>
												<f:facet name="header">
													<h:outputText value="Fecha" styleClass="texto"
														style="font: bold;color: white;" id="outFecha_h"/>
												</f:facet>
												<h:outputText value="#{obj.fechaReal}"
													style=" width : 150px;" styleClass="textoblue" id="outFecha"/>

											</h:column>

                                            <h:column>
												<x:commandLink value="" title="Seleccionar Cupon" 	id="selectCupon"
													actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.seleccionarCupon}">
												<x:graphicImage id="selectCuponImg" value="/img/select_16x16.gif"  
													style="width : 18px; height : 18px;"></x:graphicImage>
												  <f:param name="idLoteItem" value="#{obj.idLoteComercioItem}" id="paramIdLote"/>
										      </x:commandLink>
											</h:column>
                                             
                                            <h:column>
												<x:commandLink value="" title="Descartar Cupon" 	id="rechCupon" onclick="confirm('¿Confirma descartar el cupon definitivamente?')"
													actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.rechazarCupon}">
												<x:graphicImage id="rechCuponImg" value="/img/trashcan_empty_22x22.png"  
													style="width : 18px; height : 18px;"></x:graphicImage>
												  <f:param name="idLoteItem" value="#{obj.idLoteComercioItem}" id="paramIdLoteRech"/>
										      </x:commandLink>
											</h:column>                                             
											
										</h:dataTable>
										
										<h:panelGrid id="botoneraPaginador" columns="7" align="center" rendered="#{!ConciliacionCuponesBean.hayRechazadosAutomaticos}">
						                        	<h:commandLink id="botonPrimeraPagina" action="#{ConciliacionCuponesBean.primerRegistroRechazados}" rendered="#{ConciliacionCuponesBean.paginador.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0"/>
						                        	</h:commandLink>
						                        	<h:commandLink id="botonPaginaAnterior" action="#{ConciliacionCuponesBean.anteriorRegistroRechazados}" rendered="#{ConciliacionCuponesBean.paginador.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0"/>
						                        	</h:commandLink>
						                        	<h:outputText value="Página " id="outPagina" />
						                        	<h:selectOneMenu  id="lstDeEjerciciosPorsucu" value="#{ConciliacionCuponesBean.paginador.idPaginaSeleccionada}" binding="#{ConciliacionCuponesBean.paginador.pagSeleccionada}"
			       					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ConciliacionCuponesBean.cambiarPagina}"
			       					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       					 						 <f:selectItems value="#{ConciliacionCuponesBean.paginador.comboDePaginas}" id="selectEjerDeSucumA" />
			       									</h:selectOneMenu>
			       									<h:outputText value="#{ConciliacionCuponesBean.paginador.estado}" id="outEstadoPag"/>
						                        	<h:commandLink id="botonPaginaSiguiente" action="#{ConciliacionCuponesBean.siguienteRegistroRechazados}" rendered="#{ConciliacionCuponesBean.paginador.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" id="img_next"/>
						                        	</h:commandLink>
						                            <h:commandLink id="botonUltimaPagina" action="#{ConciliacionCuponesBean.ultimoRegistroRechazados}" rendered="#{ConciliacionCuponesBean.paginador.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" id="img_last" />
						                        	</h:commandLink>
						                </h:panelGrid>
						            </c:if>
						            </h:panelGrid>
						
		  </s:fieldset>
		   
				
		   
		  <s:fieldset legend="Cupon Seleccionado"  id="fldCuponSel" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.mostrarPanelCuponSeleccionado}">
                 
				<c:if test="${!empty ConciliacionCuponesBean.listacuponSeleccionado}">
    
		       	<h:dataTable id="listado" styleClass="standardTable"
							headerClass="dataTable_Header"
							rows="1" var="obj"  
							value="#{ConciliacionCuponesBean.listacuponSeleccionado}" 
							style=" width : 1000px;">

							<x:column>
								<f:facet name="header">
									<h:outputText value="Nro Doc " id="out3" />
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.nroDoc}"  
									style="width: 65px; display:hidden" id="doc" />
							</x:column>
							<x:column width="140">
								<f:facet name="header"  >
									<h:outputText value="Numero  Tarjeta"  id="out4" />
								</f:facet>
							    	<h:outputText value="#{obj.loteComercioItem.nroTarjeta}"
								      style="width: 20px; display:hidden" id="tar"/>
							</x:column>
							<x:column>
								<f:facet name="header">
									<h:outputText value="Cant Cuotas" id="out5"/>
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.cantCuotas}"
									style="width: 70px; display:hidden" id="cuo" />
							</x:column>
							<x:column>
								<f:facet name="header">
									<h:outputText value="Importe" id="out5"/>
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.importe}" id="imp"/>
							</x:column>
							<x:column>
								<f:facet name="header">
									<h:outputText value="Cod. Autorizacion"  id="out6"/>
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.codigoAutorizacion}"
									id="aut" style="width: 80px; display:hidden" />
							</x:column>
							<x:column>
								<f:facet name="header">
									<h:outputText value="Fecha Consumo" id="out7"/>
								</f:facet>
							     <h:outputText value="#{obj.loteComercioItem.fechaReal}"  id="fec"
									 style="width: 80px; display:hidden"/>
							</x:column>
							<x:column>
								<f:facet name="header">
									<h:outputText value="Plan Cuotas" id="tly"
										style="display:hidden" />
								</f:facet>
								<h:outputText
								 value="#{obj.loteComercioItem.planCuotas}" id="pln"
									style="width:100px;display:hidden"/>
							</x:column>
					</h:dataTable>
                    </c:if>
				</s:fieldset>
 
          <s:fieldset legend="Transaccion seleccionada" id="fldtransSel" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.mostrarPanelTransSeleccionada}">
             <c:if test="${!empty ConciliacionCuponesBean.listaTransSeleccionada}">
    	       	<h:dataTable id="listado2" styleClass="standardTable"
							headerClass="dataTable_Header"
							rows="1" var="obj"  
							value="#{ConciliacionCuponesBean.listaTransSeleccionada}" 
							style=" width : 1000px;">
					       
					      
							
							<x:column>
								<f:facet name="header">
									<h:outputText value="Nro Doc " id="out3Trans" />
								</f:facet>
								
								<h:outputText value="#{obj.loteComercioItem.transaccion.nroDoc}"  
									style="width: 65px; display:hidden" id="docTrans" />
							</x:column>
							<x:column width="140">
								<f:facet name="header"  >
									<h:outputText value="Numero  Tarjeta"  />
								</f:facet>
								
							    	<h:outputText value="#{obj.loteComercioItem.transaccion.nroTarjeta}"
								      style="width: 20px; display:hidden" id="tarTrans"/>
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Cant Cuotas" />
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.transaccion.cantCuotas}"
									style="width: 70px; display:hidden"/>
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Importe" />
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.transaccion.importe}"/>
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Cod. Autorizacion" />
								</f:facet>
								<h:outputText value="#{obj.loteComercioItem.transaccion.codigoAutorizacion}"
									id="autTrans" style="width: 80px; display:hidden" />
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Fecha Consumo" />
								</f:facet>
							     <h:outputText value="#{obj.loteComercioItem.transaccion.fechaReal}"  id="fecTrans"
									 style="width: 80px; display:hidden"/>
							</x:column>


							<x:column>
								<f:facet name="header">
									<h:outputText value="Plan Cuotas" id="tlyTrans"
										style="display:hidden" />
								</f:facet>
								<h:outputText
								 value="#{obj.loteComercioItem.transaccion.planCuotas}" id="plnTrans"
									style="width:100px;display:hidden"/>
							</x:column>
                             
					</h:dataTable>
                   </c:if>
				</s:fieldset>
 

					
					
	
		     <s:fieldset legend="Buscar Sugerencias" id="fldBusSug" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.mostrarPanelSugerencias}" >
		          <h:panelGrid columns="4" id="filtrosBusquedaAvanzada" width="700" 
						align="left" >
						
						<h:outputText value="Fecha Desde:" style="width: 30px;" id="outFecDesde"/>
						<x:inputCalendar id="fecLoteFiltroRecDesde"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.fechaDesde}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"   
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
						<h:outputText value="Fecha Hasta:" style="width: 250px;" id="outFecHasta"/>
						<x:inputCalendar id="fecLoteFiltroRecHasta"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.fechaHasta}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"  
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
							</h:panelGrid>
							<h:panelGrid columns="11" id="pnlFiltroDif">
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				                 			     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					                			     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							     	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				
							    <h:outputText value="Cantidad de Diferencias:" styleClass="texto" id="outCantDif"/>
								<h:selectOneMenu id="lstcantDif"  
									value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.idCantDifSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 100px"
									binding="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.cantDif}">
									<f:selectItems id="cantDifItems"
										value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.cantDifItems}" />
								</h:selectOneMenu>
							    <x:commandLink value="" title="Buscar Cupones"
							actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.buscarSugerencias}"
							id="busquedaSugerencias">
							<x:graphicImage id="imgnBusquedaSugerencias" value="/img/icon/srch_32.gif"
								style=" width : 32px;"></x:graphicImage>
						</x:commandLink>
						
						
			</h:panelGrid>
					<h:panelGrid columns="1"  id="pnlTranSug">
						<c:if test="${!empty ConciliacionCuponesBean.popupAsociarCuponesATransaccion.listaSugerencias}">
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 1050px; HEIGHT: 150px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:dataTable id="listadoTrans" styleClass="standardTable"
									headerClass="dataTable_Header"
									footerClass="standardTable_Header"
									rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
									var="obj"  
									value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.listaSugerencias}" 
									style=" width : 1000px;">
									
									<x:column>
										<f:facet name="header">
											<h:outputText value="Nro Cupon" id="out2Trans" />
										</f:facet>
										<h:outputText id="cupTrans" value="#{obj.transaccion.nroCupon}" style="#{obj.nroCuponFont}" />
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Nro Doc " id="out3Trans" />
										</f:facet>
										<h:outputText value="#{obj.transaccion.nroDoc}"	style="#{obj.nroDocFont}" id="docTrans" />
									</x:column>
									<x:column width="140">
										<f:facet name="header"  >
											<h:outputText value="Numero  Tarjeta"   id="out4Trans"/>
										</f:facet>
									    	<h:outputText value="#{obj.transaccion.nroTarjeta}" style="#{obj.nroTarjetaFont}" id="tarTrans"/>
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Cant Cuotas" id="out4Trans"/>
										</f:facet>
										<h:outputText value="#{obj.transaccion.cantCuotas}" id="cuoTrans" style="#{obj.cantCuotasFont}"/>
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Importe" id="out5Trans"/>
										</f:facet>
										<h:outputText value="#{obj.transaccion.importe}" id="impTrans" style="#{obj.importeFont}" />
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Cod. Autorizacion" id="out6Trans"/>
										</f:facet>
										<h:outputText value="#{obj.transaccion.codigoAutorizacion}"	id="autTrans"  style="#{obj.codAutFont}" />
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Fecha Consumo" id="out7Trans"/>
										</f:facet>
									     <h:outputText value="#{obj.transaccion.wraperFechaReal}"  id="fecTrans" style="#{obj.fechaConsumoFont}"/>
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Plan Cuotas" id="out7Trans" style="display:hidden" />
										</f:facet>
										<h:outputText
										 value="#{obj.transaccion.planCuotas}" id="plnTrans" style="#{obj.planCuotasFont}"/>
									</x:column>
									<x:column>
										<f:facet name="header">
											<h:outputText value="Accion" id="out7Trans"	style="display:hidden" />
										</f:facet>
										<h:commandButton id="btonAsoriar" value="Asociar" styleClass="botones" rendered="false"
												action="#{obj.asociar}" >
										</h:commandButton>  
									</x:column>
								</h:dataTable>
							</x:div>
                       
                          <h:panelGrid id="botoneraPaginador2" columns="7" align="center" >
	                        	<h:commandLink id="botonPrimeraPagina2" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.primerRegistroTransaccion}" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage id="imgbotonPraPag2" value="/img/icon/skipb_16.gif" border="0"/>
	                        	</h:commandLink>
	                        	<h:commandLink id="botonPaginaAnterior2" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.anteriorRegistroTransaccion}" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.hayAnterior}" styleClass="botones">
	                        		<x:graphicImage id="imgbotonPagAnt2" value="/img/icon/rewnd_16.gif" border="0"/>
	                        	</h:commandLink>
	                        	<h:outputText id="outPag" value="Página " />
	                        	<h:selectOneMenu  id="lstTransaSug" value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.idPaginaSeleccionada}" binding="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.pagSeleccionada}"
     					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.cambiarPagina}"
     					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
     					 						 <f:selectItems value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.comboDePaginas}" id="selectTransSug" />
     							</h:selectOneMenu>
     									<h:outputText id="outEstadoPag2" value="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.estado}" />
	                        	<h:commandLink id="botonPaginaSiguiente2" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.siguienteRegistroAsiento}" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage id="imgBotonPagSte2" value="/img/icon/fastf_16.gif" border="0" />
	                        	</h:commandLink>
	                            <h:commandLink id="botonUltimaPagina2" action="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.ultimoRegistroAsiento}" rendered="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.paginador.haySiguiente}" styleClass="botones">
	                        		<x:graphicImage id="imgBotonPagUlt2" value="/img/icon/skipf_16.gif" border="0" />
	                        	</h:commandLink>
	                     </h:panelGrid>
                     </c:if>
	            </h:panelGrid>
           </s:fieldset>
		   
		         		            
		
		
		<h:panelGrid id="botonera" columns="16" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			   	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			   	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<x:commandLink value="" title="Guardar" actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.grabar}" id="botonGuardar" > 
				    <x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
				</x:commandLink>
                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				
								
				<x:commandLink value="" title="Cancelar" actionListener="#{ConciliacionCuponesBean.popupAsociarCuponesATransaccion.recargarYCerrarPopup}" id="liDos"> 
				    <x:graphicImage id="imDos" value="/img/button_cancel.png"></x:graphicImage>
				</x:commandLink>

              
			</h:panelGrid>
			
		
		</h:panelGrid>
	</h:panelGroup>
		</f:facet>
	</x:panelLayout>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>
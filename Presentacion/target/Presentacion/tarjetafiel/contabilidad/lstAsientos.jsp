<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{AsientosBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('asientosForm').submit();
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

<jsp:include page="/inc/includes.jsp" />





<body class="hold-transition skin-blue sidebar-mini" onload="ocultarFiltros(document.getElementById('asientosForm:tabAsientos:booleanoFiltro'));ocultarFiltrosLote(document.getElementById('asientosForm:tabLote:booleanoFiltroLote'));" >
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${AsientosBean.tituloCorto}
    <small>${AsientosBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

	<script languaje="javascript">
         
         //Permite que se ingrese un número decimal de decimales definidos en un Input Text
				function soloDecimalesPrecisos(InputText, evt, cantDeci) {
					var charCode = (evt.which) ? evt.which : event.keyCode;
					var strValue = '';
					var canti = parseInt(cantDeci);
				 	var selectionStart = getSelectionStart(InputText);
				  	var selectionEnd = getSelectionEnd(InputText);
					strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
					if (!isDecimalConNDecimales(strValue, canti) && charCode > 31) {
						return false;
					}		
					return true;
				}
				
				// Retorna true si inputVal es decimal.
				function isDecimalConNDecimales(inputVal, numeroDecimales) {
					oneDecimal = false;
					cantidades = 0;
					inputStr = inputVal.toString();
					for (var i = 0; i < inputStr.length; i++) {
						var oneChar = inputStr.charAt(i);
						if (i == 0 && oneChar == "-") {
							continue;
						}
						if (oneDecimal) {
						    cantidades = cantidades + 1;
						    if (cantidades > parseInt(numeroDecimales)) {return false;}
						}
						if (oneChar == "." && !oneDecimal) {
							oneDecimal = true;
							continue;
						}
						if (oneChar < "0" || oneChar > "9") {
							return false;
						}
					}
					return true;
				}	
         
         function ocultarFiltros(SelectBooleanCheckbox) {
            if (SelectBooleanCheckbox.checked) {
               document.getElementById('asientosForm:tabAsientos:panelDeBusquedaAsiento').style.display = '';
               document.getElementById('asientosForm:tabAsientos:paneldeFechas').style.display = '';
               document.getElementById('asientosForm:tabAsientos:conceptoAsiento').style.display = '';
               document.getElementById('asientosForm:tabAsientos:intDeAsiento').style.display = '';
               if (document.getElementById('asientosForm:tabAsientos:importeAsiento').value!="") {
               		verRestoImporteAsiento();
               }
               
            } else {
               document.getElementById('asientosForm:tabAsientos:panelDeBusquedaAsiento').style.display = 'none';
               document.getElementById('asientosForm:tabAsientos:paneldeFechas').style.display = 'none';
               document.getElementById('asientosForm:tabAsientos:conceptoAsiento').style.display = 'none';
               document.getElementById('asientosForm:tabAsientos:intDeAsiento').style.display = 'none';
               ocultarRestoImporteAsiento();
            }
            return true;
         }
         
         function ocultarFiltrosLote(SelectBooleanCheckbox) {
            if (SelectBooleanCheckbox.checked) {
               document.getElementById('asientosForm:tabLote:panelDeBusquedaLote').style.display = '';
               document.getElementById('asientosForm:tabLote:paneldeFechasLote').style.display = '';
               document.getElementById('asientosForm:tabLote:conceptoLote').style.display = '';
               document.getElementById('asientosForm:tabLote:internoDeDebe').style.display = '';
               if (document.getElementById('asientosForm:tabLote:importeLote').value!="") {
               		verRestoImporteLote();
               }
            } else {
               document.getElementById('asientosForm:tabLote:panelDeBusquedaLote').style.display = 'none';
               document.getElementById('asientosForm:tabLote:paneldeFechasLote').style.display = 'none';
               document.getElementById('asientosForm:tabLote:conceptoLote').style.display = 'none';
               document.getElementById('asientosForm:tabLote:internoDeDebe').style.display = 'none';
               ocultarRestoImporteLote();
               
            }
            return true;
         }
         
         function verRestoImporteAsiento() {
         	 document.getElementById('asientosForm:tabAsientos:labelEnDebeAsi').style.display = '';
         	 document.getElementById('asientosForm:tabAsientos:selectBoleanDebeAsi').style.display = '';
         	 document.getElementById('asientosForm:tabAsientos:labelEnHaberAsi').style.display = '';
         	 document.getElementById('asientosForm:tabAsientos:selectBoleanHaberAsi').style.display = '';
         	 document.getElementById('asientosForm:tabAsientos:outRanAproxAsi').style.display = '';
         	 document.getElementById('asientosForm:tabAsientos:aproximadoHastaAsi').style.display = '';
         }
         
         function ocultarRestoImporteAsiento() {
         	 document.getElementById('asientosForm:tabAsientos:labelEnDebeAsi').style.display = 'none';
         	 document.getElementById('asientosForm:tabAsientos:selectBoleanDebeAsi').style.display = 'none';
         	 document.getElementById('asientosForm:tabAsientos:labelEnHaberAsi').style.display = 'none';
         	 document.getElementById('asientosForm:tabAsientos:selectBoleanHaberAsi').style.display = 'none';
         	 document.getElementById('asientosForm:tabAsientos:outRanAproxAsi').style.display = 'none';
         	 document.getElementById('asientosForm:tabAsientos:aproximadoHastaAsi').style.display = 'none';
         }
         
         function verRestoImporteLote() {
         	 document.getElementById('asientosForm:tabLote:labelEnDebeLot').style.display = '';
         	 document.getElementById('asientosForm:tabLote:selectBoleanDebeLot').style.display = '';
         	 document.getElementById('asientosForm:tabLote:labelEnHaberLot').style.display = '';
         	 document.getElementById('asientosForm:tabLote:selectBoleanHaberLot').style.display = '';
         	 document.getElementById('asientosForm:tabLote:aproxLote').style.display = '';
         	 document.getElementById('asientosForm:tabLote:aproximadoHastaLot').style.display = '';
         }
         
         function ocultarRestoImporteLote() {
         	 document.getElementById('asientosForm:tabLote:labelEnDebeLot').style.display = 'none';
         	 document.getElementById('asientosForm:tabLote:selectBoleanDebeLot').style.display = 'none';
         	 document.getElementById('asientosForm:tabLote:labelEnHaberLot').style.display = 'none';
         	 document.getElementById('asientosForm:tabLote:selectBoleanHaberLot').style.display = 'none';
         	 document.getElementById('asientosForm:tabLote:aproxLote').style.display = 'none';
         	 document.getElementById('asientosForm:tabLote:aproximadoHastaLot').style.display = 'none';
         }
         
         function cambiarEstadoFiltrosLote() {
         	if (document.getElementById('asientosForm:tabLote:importeLote').value!="") {
                verRestoImporteLote();
            } else {
                ocultarRestoImporteLote();
            }
         }
         
         
         function cambiarEstadoFiltrosAsiento() {
         	if (document.getElementById('asientosForm:tabAsientos:importeAsiento').value!="") {
               	verRestoImporteAsiento();
            } else {
            	ocultarRestoImporteAsiento();
            }
         }
         
         function marcar() {
         	if (document.getElementById('asientosForm:tabLote:listadoLote:boolTodos').checked) {
                for (i=0; document.getElementById('asientosForm:tabLote:listadoLote:' + i + ':estado')!=null;i++) {
                     document.getElementById('asientosForm:tabLote:listadoLote:' + i + ':estado').checked = true;
                }
            } else {
            	for (i=0; document.getElementById('asientosForm:tabLote:listadoLote:' + i + ':estado')!=null;i++) {
                     document.getElementById('asientosForm:tabLote:listadoLote:' + i + ':estado').checked = false;
                }
            }
         }
         
         
    </script>




<center>
	<secure:check/>

	<h:form id="asientosForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!AsientosBean.popup.mostrar}">
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
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal" >
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{AsientosBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{AsientosBean.popup.nombreIcono}" />
							<h:outputText value="#{AsientosBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="1" width="500">
							<x:commandButton 
								onclick="clickLink('aceptarOK');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="btn btn-primary btn-flat" title="Aceptar."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="aceptarOK" forceId="true" style="display: block;"/>
					
			            <h:panelGrid id="panelBus" columns="2" align="center" style="margin-bottom:20px">
			                    <h:outputText value="Seleccionar la sucursal:" style="margin-right:10px"/>
			                	<h:selectOneMenu id="lstSucursales" value="#{AsientosBean.idSucSeleccionadaParaFiel}" binding="#{AsientosBean.idSucursalDeFielSeleccionada}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{AsientosBean.listarEjercicios}"
			       					  onblur="apagar(this);" style=" width : 200px;margin-bottom:8px" onchange="submit();" disabled="true">
			       					  <f:selectItems value="#{AsientosBean.sucursalesFielSelectItem}" id="selectSucum" />
			       				</h:selectOneMenu>	
			                
			                	<h:outputText value="Seleccione el ejercicio:" style="margin-right:10px"/>
			                	  <h:selectOneMenu id="lstDeEjerciciosPorSucursal" value="#{AsientosBean.idEjercicioSeleccionado}" binding="#{AsientosBean.idEjercicioSeleccionadoItem}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{AsientosBean.listarAsientos}"
			       					  onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       				     <f:selectItems value="#{AsientosBean.ejerciciosSelectItem}" id="selectEjerDeSucum" />
			       				  </h:selectOneMenu>
			       		</h:panelGrid>
			                	
					        
<%--@I4933--%>					        <h:panelGrid id="panelDat" onclick="ocultarFiltrosLote(document.getElementById('asientosForm:tabLote:booleanoFiltroLote'));" >
			                	<x:panelTabbedPane id="tabbedTablas" serverSideTabSwitch="false" width="800">
<%--@F4933--%>			                		<x:panelTab id="tabAsientos" label="Asientos" >
			                			
			                			
							                     <s:fieldset id="fieldDeBusquedaAsiento" legend="Parámetros de búsqueda">  
								                 <h:panelGrid id="panelDeBusquedaAsiento" columns="6" align="center" style="display:hidden;">
								                        <h:outputText value="Cuenta a Buscar: " styleClass="texto" />
								                        <h:inputText value="#{AsientosBean.cuentaABuscarEnAsiento}" style="margin-left:10px;margin-right:10px" disabled="false" id="inputCuentaBusquedaAsiento" title="Nros. de cuenta separados por comas."/>
								                  		<x:commandLink id="buscarCuentaLink" action="#{AsientosBean.buscarCuentaPopup}">
																<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
														</x:commandLink>
								                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
											            <h:outputText value="Centro de Costo: " style="margin-right:10px" styleClass="texto" id="ouputCentroCostobus"/>
											           	<h:selectOneMenu id="centroCostoParaBusqueda" value="#{AsientosBean.idCentroCostoSeleccionado}" binding="#{AsientosBean.centroCostoSeleccionado}"
												        	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
												        	<f:selectItems value="#{AsientosBean.listaCentroDeCostos}" id="selectItemIdcen"/>
										        		</h:selectOneMenu>

								                        
								                 </h:panelGrid>
								                 <h:panelGrid id="paneldeFechas" columns="5" width="600" align="center" style="display:hidden;margin-top:8px">
														<h:outputText value="Desde:" styleClass="texto" style="margin-right:10px"/>
														<f:verbatim>
											                <div class="input-group date">
											                    <div class="input-group-addon">
											                 	   <i class="fa fa-calendar"></i>
											                    </div>
											                    <input type="text" class="form-control pull-right" id="fDesde">
											                </div>
														</f:verbatim>
											            
											            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											            
											            <h:outputText value="Hasta:" styleClass="texto" style="margin-right:10px"/>
														<f:verbatim>
											                <div class="input-group date">
											                    <div class="input-group-addon">
											                 	   <i class="fa fa-calendar"></i>
											                    </div>
											                    <input type="text" class="form-control pull-right" id="fHasta">
											                </div>
														</f:verbatim>
												 </h:panelGrid>           
								                 
								                 <h:panelGrid id="conceptoAsiento" columns="8" style="display:hidden;margin-top:8px">
								                        <h:outputText value="Concepto:" style="margin-right:10px" styleClass="text"/>
								                        <h:inputText value="#{AsientosBean.conceptoABuscarEnAsiento}" id="concepABuscarEnAsiento"/>
								                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
											            <h:outputText value="Origen: " style="margin-right:10px" styleClass="texto" id="ouputorigendefectoAsiento"/>
											           	<h:selectOneMenu id="origenParaBusquedaAsiento" value="#{AsientosBean.idOrigenSeleccionado}" binding="#{AsientosBean.origenSeleccionado}"
												        	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
												        	<f:selectItems value="#{AsientosBean.origenesItem}" id="selectItemIdorigDos"/>
										        		</h:selectOneMenu>
										        		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
										        		<h:outputText value="Nro. Asiento:" style="margin-right:10px" styleClass="texto" />
								                        <h:inputText value="#{AsientosBean.idAsientoABuscarEnAsiento}" id="idAsientoABuscarEnAsiento" style=" width : 87px;" onkeypress="return soloEnteros(this, event);"/>
										          </h:panelGrid>
										        		<h:panelGrid id="intDeAsiento" columns="9" style="display:hidden;margin-top:8px">
											        		<h:outputText value="Importe:" style="margin-right:10px" id="outAsiImp" styleClass="texto"/>
									                        <h:inputText value="#{AsientosBean.importeAsiento}" id="importeAsiento" onkeypress="return soloDecimalesPrecisos(this,event,2);" onkeyup="cambiarEstadoFiltrosAsiento()"/>
									                        <h:outputText value="Debe" id="labelEnDebeAsi" style="display:hidden;margin-right:5px;margin-left:20px"/>
									                        <h:selectBooleanCheckbox id="selectBoleanDebeAsi" value="#{AsientosBean.importeSoloEnDebeAsi}"  style="display:hidden;"/>
															<h:outputText value="Haber" id="labelEnHaberAsi" style="display:hidden;margin-right:5px;margin-left:10px" />
									                        <h:selectBooleanCheckbox id="selectBoleanHaberAsi" value="#{AsientosBean.importeSoloEnHaberAsi}" style="display:hidden;"/>
									                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									                        <h:outputText value="Rango Aproximación" id="outRanAproxAsi"  styleClass="texto" style="display:hidden;margin-right:10px"/>
									                        <h:inputText value="#{AsientosBean.aproximadoHastaAsi}" onkeypress="return soloDecimalesPrecisos(this,event,2);" id="aproximadoHastaAsi" style="display:hidden; width : 60px;"/>
							                            </h:panelGrid>

								                 
								                 
								                 <h:panelGrid id="botonesDeBusquedaAsiento" columns="4" align="right" style="margin-top:8px">
								                         <h:outputText id="mostrarFiltros" value="Mostrar filtros" style="margin-right:5px"/>
								                         <h:selectBooleanCheckbox id="booleanoFiltro" onchange="ocultarFiltros(this)" />
								                         <h:commandButton id="botoBuxcar" style="margin-left:10px;margin-right:10px" action="#{AsientosBean.filtrarAsientos}" value="Buscar Asientos" styleClass="btn btn-primary btn-flat"/>
								                         <h:commandButton id="botonLimpiarCampos" action="#{AsientosBean.limpiarCamposAsientos}" value="LimpiarCampos" styleClass="btn btn-primary btn-flat"/>
								                         </h:panelGrid>
								                 </s:fieldset>
								                 
								                 <f:verbatim>
								                     &nbsp;
                                                 </f:verbatim>
								                 
					                            <h:panelGrid id="panelDeTabla" columns="1">
					                            
					                            <c:if test="${!empty AsientosBean.asientos}">
					                            <h:dataTable id="listado" styleClass="standardTable"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="standardTable_Column,standardTable_Column,standardTable_Column,standardTable_Column,standardTable_Column,standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered"
						                            var="obj" 
						                            value="#{AsientosBean.asientos}">
						                   
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="ID Asiento"/>
						                            </f:facet>
						                            <h:outputText value="#{obj.asiento.idAsiento}"/>
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Concepto" />
						                            </f:facet>
						                            <h:outputText value="#{obj.asiento.concepto}" />
						                        </x:column>
						                        
						                        <x:column >
						                            <f:facet name="header">
						                                <h:outputText value="Tipo Asiento" />
						                            </f:facet>
						                            <h:outputText value="#{obj.asiento.idTipoAsiento}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Fecha Contab" />
						                            </f:facet>
						                            <h:outputText value="#{obj.asiento.fechaContab}" />
						                        </x:column>
						                        
						                        
						                        <h:column>
													<x:commandButton action="#{obj.eliminarAsiento}" image="/img/cat_act.gif" onclick="return confirm('Se borrará el asiento y todas sus cuentas. ¿Desea continuar?');" id="eliminarAsientoLink">
													
													
												</x:commandButton>
												</h:column>
						
												<h:column>
													<x:commandLink action="#{AsientosBean.verDetallesAsiento}" id="centroDeCostoLink">
													<f:param id="idAsientoAVer" name="idAsientoAVer" value="#{obj.idAsien}"/>
													<x:graphicImage value="/img/icon/OrderView.gif" title="Listar los detalles del asiento." border="0" id="botonImagenTres" />
											    	</x:commandLink>
												
												</h:column>     
												<h:column>
											    	<f:facet name="header">
						                                <h:outputText value="Doc. Adj." />
						                            </f:facet>
													<x:commandButton action="#{obj.mostrarDocAdjuntos}" value="Doc. Adj."  styleClass="btn btn-primary btn-flat" id="verDocumentoAdjuntoAsiento">
											    	</x:commandButton>
												
												</h:column>
												 
												                         
						                        </h:dataTable>
					                            
					                        
						                           
						                       
						                        
						                        <h:panelGrid id="botoneraPaginador" columns="7" align="center" rendered="#{!AsientosBean.hayAsientos}">
						                        	<h:commandLink id="botonPrimeraPagina" action="#{AsientosBean.primerRegistroAsiento}" rendered="#{AsientosBean.paginador.hayAnterior}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0"/>
						                        	</h:commandLink>
						                        	<h:commandLink id="botonPaginaAnterior" action="#{AsientosBean.anteriorRegistroAsiento}" rendered="#{AsientosBean.paginador.hayAnterior}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0"/>
						                        	</h:commandLink>
						                        	<h:outputText value="Página " />
						                        	<h:selectOneMenu  id="lstDeEjerciciosPorsucu" value="#{AsientosBean.paginador.idPaginaSeleccionada}" binding="#{AsientosBean.paginador.pagSeleccionada}"
			       					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{AsientosBean.cambiarPagina}"
			       					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       					 						 <f:selectItems value="#{AsientosBean.paginador.comboDePaginas}" id="selectEjerDeSucumA" />
			       									</h:selectOneMenu>
			       									<h:outputText value="#{AsientosBean.paginador.estado}" />
						                        	<h:commandLink id="botonPaginaSiguiente" action="#{AsientosBean.siguienteRegistroAsiento}" rendered="#{AsientosBean.paginador.haySiguiente}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" />
						                        	</h:commandLink>
						                            <h:commandLink id="botonUltimaPagina" action="#{AsientosBean.ultimoRegistroAsiento}" rendered="#{AsientosBean.paginador.haySiguiente}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" />
						                        	</h:commandLink>
						                        </h:panelGrid>
						                        </c:if>
					                       </h:panelGrid>
										         <f:verbatim>
								                     &nbsp;
                                                 </f:verbatim>
										<h:panelGrid align="right" id="jaad" columns="1">
										
										<h:commandButton id="botonCancelar" value="Cancelar" styleClass="btn btn-primary btn-flat" action="#{AsientosBean.cancelar}" />
										</h:panelGrid>
			                			
			                			
			                		</x:panelTab>
			                		<x:panelTab id="tabLote" label="Lote">
			                		
			                                <s:fieldset id="fieldDeBusquedaLote" legend="Parámetros de búsqueda">  
								                 <h:panelGrid id="panelDeBusquedaLote" columns="6" align="center" style="display:hidden;">
								                        <h:outputText value="Cuenta a Buscar: " />
								                        <h:inputText value="#{AsientosBean.cuentaABuscarEnLote}" style="margin-left:10px;margin-right:10px" disabled="false" id="inputCuentaBusquedaLote" title="Nros. de cuenta separados por comas."/>
								                  		<x:commandLink id="buscarCuentaLink" action="#{AsientosBean.buscarCuentaPopupLote}">
																<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
														</x:commandLink>
														<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
											            <h:outputText value="Centro de Costo: " style="margin-right:10px" styleClass="texto" id="ouputCentroCostobusLot"/>
											           	<h:selectOneMenu id="centroCostoParaBusquedaLote" value="#{AsientosBean.idCentroCostoSeleccionadoLote}" binding="#{AsientosBean.centroCostoSeleccionadoLote}"
												        	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
												        	<f:selectItems value="#{AsientosBean.listaCentroDeCostos}" id="selectItemIdcen"/>
										        		</h:selectOneMenu>
								                 
								                 </h:panelGrid>
								                 <h:panelGrid id="paneldeFechasLote" columns="5" width="600" align="center" style="display:hidden;margin-top:8px">
														<h:outputText value="Desde:" styleClass="texto" style="margin-right:10px"/>
														<f:verbatim>
											                <div class="input-group date">
											                    <div class="input-group-addon">
											                 	   <i class="fa fa-calendar"></i>
											                    </div>
											                    <input type="text" class="form-control pull-right" id="fDesdeLote">
											                </div>
														</f:verbatim>
						                                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
														<h:outputText value="Hasta:" styleClass="texto" style="margin-right:10px"/>
														<f:verbatim>
											                <div class="input-group date">
											                    <div class="input-group-addon">
											                 	   <i class="fa fa-calendar"></i>
											                    </div>
											                    <input type="text" class="form-control pull-right" id="fHastaLote">
											                </div>
														</f:verbatim>
									                	
									  
												    
												    
												 </h:panelGrid>           
								                 <h:panelGrid id="conceptoLote" columns="8" style="display:hidden;margin-top:8px">
								                        <h:outputText value="Concepto:" style="margin-right:10px"/>
								                        <h:inputText value="#{AsientosBean.conceptoABuscarEnLote}" id="concepABuscarEnLote"/>
								                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
											            <h:outputText value="Origen: " style="margin-right:10px" styleClass="texto" id="ouputorigendefectoLote"/>
											           	<h:selectOneMenu id="origenParaBusquedaLote" value="#{AsientosBean.idOrigenSeleccionadoLote}" binding="#{AsientosBean.origenSeleccionadoLote}"
												        	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
												        	<f:selectItems value="#{AsientosBean.origenesItem}" id="selectItemIdorigSeg"/>
										         		</h:selectOneMenu>
										         		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
										         		<h:outputText value="Nro. Lote:" style="margin-right:10px" styleClass="texto"/>
								                        <h:inputText value="#{AsientosBean.idAsientoABuscarEnLote}" id="idAsientoABuscarEnLote" style=" width : 87px;" onkeypress="return soloEnteros(this, event);"/>
												 </h:panelGrid>        	
										        		<h:panelGrid columns="9" id="internoDeDebe" style="display:hidden;margin-top:8px">
											        		<h:outputText value="Importe:" id="outImpDebe"  styleClass="texto" style="display:hidden;margin-right:10px"/>
									                        <h:inputText value="#{AsientosBean.importeLote}" id="importeLote" onkeypress="return soloDecimalesPrecisos(this,event,2);" style="display:hidden;" onkeyup="cambiarEstadoFiltrosLote()"/>
									                        <h:outputText value="Debe" id="labelEnDebeLot" style="display:hidden;margin-right:5px;margin-left:20px"/>
									                        <h:selectBooleanCheckbox id="selectBoleanDebeLot" value="#{AsientosBean.importeSoloEnDebeLot}"  style="display:hidden;"/>
															<h:outputText value="Haber" id="labelEnHaberLot" style="display:hidden;margin-right:5px;margin-left:10px" />
									                        <h:selectBooleanCheckbox id="selectBoleanHaberLot" value="#{AsientosBean.importeSoloEnHaberLot}" style="display:hidden;"/>
									                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									                        <h:outputText value="Rango Aproximación" id="aproxLote" styleClass="texto" style="display:hidden;margin-right:10px"/>
									                        <h:inputText value="#{AsientosBean.aproximadoHastaLot}" onkeypress="return soloDecimalesPrecisos(this,event,2);" id="aproximadoHastaLot" style="display:hidden; width : 60px;"/>
							                            </h:panelGrid>

								                 <h:panelGrid id="botonesDeBusquedaAsiento" columns="4" align="right" style="margin-top:8px">
								                         <h:outputText id="mostrarFiltros" value="Mostrar filtros" style="margin-right:5px"/>
								                         <h:selectBooleanCheckbox id="booleanoFiltroLote" onchange="ocultarFiltrosLote(this)" />
								                         <h:commandButton action="#{AsientosBean.filtrarLotes}" style="margin-left:10px;margin-right:10px" value="Buscar" styleClass="btn btn-primary btn-flat"/>
								                         <h:commandButton action="#{AsientosBean.limpiarCamposLotes}" value="LimpiarCampos" styleClass="btn btn-primary btn-flat"/>
								                         </h:panelGrid>
								                         

								                 </s:fieldset>         		
			                		
			                	     	         <f:verbatim>
								                     &nbsp;
                                                 </f:verbatim>
			                		
			                		
			                				<h:panelGrid id="panelDeTablaLote" columns="1">
			                				   <c:if test="${!empty AsientosBean.lotes}">
					                            <h:dataTable id="listadoLote" styleClass="standardTable"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="dataTable_columns,standardTable_Column,standardTable_Column, standardTable_Column"
						                            var="obj" 
						                            value="#{AsientosBean.lotes}">
						                   
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="ID Asiento" />
						                            </f:facet>
						                            <h:outputText value="#{obj.lote.idAsiento}" />
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Concepto" />
						                            </f:facet>
						                            <h:outputText value="#{obj.lote.concepto}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Tipo Asiento" />
						                            </f:facet>
						                            <h:outputText value="#{obj.lote.idTipoAsiento}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Fecha Contab" />
						                            </f:facet>
						                            <h:outputText value="#{obj.lote.fechaContab}" />
						                        </x:column>
						                        
						                        
						                        <h:column>
													<x:commandButton action="#{obj.eliminarLote}" image="/img/cat_act.gif" onclick="return confirm('Se borrará el asiento y todas sus cuentas. ¿Desea continuar?');" id="eliminarLoteLink">
													
													
												    </x:commandButton>
												</h:column>
						
												<h:column>
													<x:commandLink action="#{AsientosBean.verDetallesLote}" id="centroDeCostoLoteLink">
													<f:param id="idLoteAVer" name="idLoteAVer" value="#{obj.idLote}"/>
													<x:graphicImage value="/img/icon/OrderView.gif" title="Listar los detalles del Lote." border="0" id="botonImagenTres" />
											    	</x:commandLink>
												
												</h:column>    
												
												<h:column>
													<f:facet name="header">
														<h:panelGroup>
															<f:facet name="header">
																<h:outputText value="Pasar a Asientos" id="todos" styleClass="texto" />
															</f:facet>
															<h:selectBooleanCheckbox value="#{AsientosBean.todos}" id="boolTodos"  onclick="marcar();"/>
														</h:panelGroup>
													</f:facet>
													<h:selectBooleanCheckbox value="#{obj.estado}" style="width: 25px" id="estado"/>
												</h:column> 
										
												<h:column>
											    	<f:facet name="header">
						                                <h:outputText value="Doc. Adj." />
						                            </f:facet>
													<x:commandButton action="#{obj.mostrarDocAdjuntos}" value="Doc. Adj."  styleClass="btn btn-primary btn-flat" id="verDocumentoAdjuntoLote">
											    	</x:commandButton>
												
												</h:column>
												                         
						                        </h:dataTable>

						                        
						                        <h:panelGrid id="botoneraPaginadorLote" width="150" columns="7" align="center">
						                        	<h:commandLink id="botonPrimeraPaginaLote" action="#{AsientosBean.primerRegistroLote}" rendered="#{AsientosBean.paginadorDeLotes.hayAnterior}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/skipb_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonPaginaAnteriorLote" action="#{AsientosBean.anteriorRegistroLote}" rendered="#{AsientosBean.paginadorDeLotes.hayAnterior}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/rewnd_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:outputText value="Página " />
						                        	<h:selectOneMenu  id="lstDeEjerciciosPorSucursal" value="#{AsientosBean.paginadorDeLotes.idPaginaSeleccionada}" binding="#{AsientosBean.paginadorDeLotes.pagSeleccionada}"
			       					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{AsientosBean.cambiarPaginaLote}"
			       					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       					 						 <f:selectItems value="#{AsientosBean.paginadorDeLotes.comboDePaginas}" id="selectEjerDeSucum" />
			       									</h:selectOneMenu>	
			       									<h:outputText value="#{AsientosBean.paginadorDeLotes.estado}" />
						                        	<h:commandLink id="botonPaginaSiguienteLote" action="#{AsientosBean.siguienteRegistroLote}" rendered="#{AsientosBean.paginadorDeLotes.haySiguiente}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/fastf_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonUltimaPaginaLote" action="#{AsientosBean.ultimoRegistroLote}" rendered="#{AsientosBean.paginadorDeLotes.haySiguiente}" styleClass="btn btn-primary btn-flat">
						                        		<x:graphicImage value="/img/icon/skipf_16.gif" border="0" />
						                        	</h:commandLink>
						                        </h:panelGrid>
						                        </c:if>
					                       </h:panelGrid>
										
									        	 <f:verbatim>
								                     &nbsp;
                                                 </f:verbatim>

										<s:fieldset id="fielDeImportacion" 
											legend="Importar Lotes desde Modulo">
											<h:panelGrid columns="7" id="panelParaImportar">
												<h:outputText value="Módulo de Origen:" style="width: 150px" />
												<h:selectOneMenu id="origenParaBusqueda"
													value="#{AsientosBean.idOrigenSeleccionadoParaImportar}"
													binding="#{AsientosBean.origenSeleccionadoParaImportar}"
													styleClass="lista" immediate="true"
													onfocus="encender(this);" onblur="apagar(this);"
													style="width: 135px;margin-right:20px">
													<f:selectItems value="#{AsientosBean.origenesItem}"
														id="selectItemIdorigDos" />
												</h:selectOneMenu>
												
												<h:outputText value="Desde:" styleClass="texto" style="margin-right:10px"/>
												<f:verbatim>
									                <div class="input-group date" style="margin-right:20px">
									                    <div class="input-group-addon">
									                 	   <i class="fa fa-calendar"></i>
									                    </div>
									                    <input type="text" class="form-control pull-right" id="fDesdeImportar">
									                </div>
												</f:verbatim>

												<h:outputText value="Hasta:" styleClass="texto" style="margin-right:10px"/>
												<f:verbatim>
									                <div class="input-group date" style="margin-right:20px">
									                    <div class="input-group-addon">
									                 	   <i class="fa fa-calendar"></i>
									                    </div>
									                    <input type="text" class="form-control pull-right" id="fHastaImportar">
									                </div>
												</f:verbatim>
											
												<h:commandButton id="botonImportarLote" value="Importar"
													styleClass="btn btn-primary btn-flat"
													action="#{AsientosBean.realizarImportacion}" />
											</h:panelGrid>
										</s:fieldset>
										
										   

										<h:panelGrid align="right" id="jaadLote" columns="3" style="margin-top:20px">
											<h:commandButton id="botonMigrarLote" value="Pasar a Asientos" styleClass="btn btn-primary btn-flat" action="#{AsientosBean.realizarMigracion}" />
											<h:commandButton id="botonGenerarLote" style="margin-right:10px;margin-left:10px" value="Agregar Asiento" styleClass="btn btn-primary btn-flat" action="#{AsientosBean.agregarLote}" />
<%--@F4933--%>								<h:commandButton id="botonCancelarLote" value="Cancelar" styleClass="btn btn-primary btn-flat" action="#{AsientosBean.cancelar}" />
										</h:panelGrid>
			                		</x:panelTab>
			                	</x:panelTabbedPane>
			                </h:panelGrid>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>
		
		
    
    	<h:inputText id="FechaDesde" value="#{AsientosBean.fechaInicioAsiento}" style="display: none;">
			<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>
		<h:inputText id="FechaHasta" value="#{AsientosBean.fechaCierreAsiento}" style="display: none;">
			<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>
														    
		<h:inputText id="FechaDesdeLote" value="#{AsientosBean.fechaInicioLote}" style="display: none;">
			<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>
		<h:inputText id="FechaHastaLote" value="#{AsientosBean.fechaCierreLote}" style="display: none;">
			<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>					    
														    

		<h:inputText id="FechaDesdeImportar" value="#{AsientosBean.fechaInicioImportar}" style="display: none;">
			<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>
			<h:inputText id="FechaHastaImportar" value="#{AsientosBean.fechaCierreImportar}" style="display: none;">
		<f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
		</h:inputText>
										    
	</h:form>
</center>
<div class="box-header"></div>


</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{AsientosBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>



<script type="text/javascript">
$(function () {

	
    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "bottom"
    });
    
    $("#fDesdeLote").datepicker({
        autoclose: true,
        orientation: "bottom"
    });

    $("#fHastaLote").datepicker({
        autoclose: true,
        orientation: "bottom"
    });
    
    $("#fDesdeImportar").datepicker({
        autoclose: true,
        orientation: "bottom"
    });

    $("#fHastaImportar").datepicker({
        autoclose: true,
        orientation: "bottom"
    });
      
      
    
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("asientosForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("asientosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("asientosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaDesdeLote").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fDesdeLote").datepicker("setDate", fAux);
    document.getElementById("asientosForm:FechaDesdeLote").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaHastaLote").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHastaLote").datepicker("setDate", fAux);
	document.getElementById("asientosForm:FechaHastaLote").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaDesdeImportar").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fDesdeImportar").datepicker("setDate", fAux);
    document.getElementById("asientosForm:FechaDesdeImportar").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaHastaImportar").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHastaImportar").datepicker("setDate", fAux);
	document.getElementById("asientosForm:FechaHastaImportar").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";


    
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("asientosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("asientosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    
    $("#fDesdeLote").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("asientosForm:FechaDesdeLote").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHastaLote").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("asientosForm:FechaHastaLote").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    
    $("#fDesdeImportar").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("asientosForm:FechaDesdeImportar").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHastaImportar").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("asientosForm:FechaHastaImportar").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });



  });
</script>


</body>
</html>
</f:view>

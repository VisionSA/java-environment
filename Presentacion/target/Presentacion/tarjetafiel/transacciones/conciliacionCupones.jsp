<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText value="#{ConciliacionCuponesBean.tituloLargo}" /></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('conciliacionForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=ybuses,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		var muestro = false;
		var bool = true;


		function mostrarBarra() {
		    document.getElementById('conciliacionForm:barrita').style.display = '';
			return null;
		}


		function ocultarBarra() {
		    document.getElementById('conciliacionForm:barrita').style.display = 'none';
		}
		


	</s:script>
	</head>
	<jsp:include page="/inc/includes.jsp" />

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('conciliacionForm');  " onload="ocultarBarra();">

	<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    ${ConciliacionCuponesBean.tituloCorto}
	    <small>${ConciliacionCuponesBean.tituloLargo}</small>
	  </h1>
	</section>

	<section class="content">

	<div class="box box-default">
	<div class="box-header"><h3></h3></div>	


	<script language="javascript">
	        function marcar() {
            	if (document.getElementById('conciliacionForm:tablaNoConciliados:boolTodos').checked) {
                for (i=0; document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado')!=null;i++) {
                     document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado').checked = true;
                }
            } else {
            	for (i=0; document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado')!=null;i++) {
                     document.getElementById('conciliacionForm:tablaNoConciliados:' + i + ':estado').checked = false;
                }
              }
             } 
         </script>
     
	<center>
	 <h:form id="conciliacionForm"	enctype="multipart/form-data">



		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!ConciliacionCuponesBean.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">


			<f:facet name="body">
				<h:panelGroup id="body">

					<h:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
							styleClass="viewDialog" dialogTitle="Conciliaciones">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{ConciliacionCuponesBean.popup.nombreIcono}" />
								<h:outputText value="#{ConciliacionCuponesBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>
							<h:panelGrid columns="1" width="500">
								<x:commandButton
									onclick="clickLink('aceptarOK');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="btn btn-primary btn-flat" title="Aceptar." />
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="aceptarOK" forceId="true"
							style="display: block;" />
						<s:fieldset legend="Conciliacion" id="fieldsetConciliacion">

							<h:panelGrid id="panelConciliacion" columns="4" width="700"
								align="center"
								rendered="#{ConciliacionCuponesBean.panelConciliacion}">

								<h:outputText value="Tipo Conciliacion:" styleClass="texto" />
								<h:selectOneMenu id="lstTipoConc"  
									value="#{ConciliacionCuponesBean.idTipoConcSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px"
									binding="#{ConciliacionCuponesBean.tipoConc}"
									valueChangeListener="#{ConciliacionCuponesBean.cambiarTipoAccion}"
									onchange="submit();">
									<f:selectItems id="itemTipo"
										value="#{ConciliacionCuponesBean.tipoConcItems}" />
								</h:selectOneMenu>

								<h:outputText value="Tipo Acción:" styleClass="texto"
									rendered="#{ConciliacionCuponesBean.mostrarCombo}" />
								<h:selectOneMenu id="lstTipoAccion"
									value="#{ConciliacionCuponesBean.idTipoAccionSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									rendered="#{ConciliacionCuponesBean.mostrarCombo}"
									onblur="apagar(this);" style="width: 200px"
									binding="#{ConciliacionCuponesBean.tipoAccion}"
									valueChangeListener="#{ConciliacionCuponesBean.habilitaOpciones}"
									 onchange="submit();">
									<f:selectItems id="itemTipoAccion"
										value="#{ConciliacionCuponesBean.tipoAccionItems}" />
								</h:selectOneMenu>
                             
                                
							</h:panelGrid>
						</s:fieldset>
						
						<f:verbatim> <br id="br"> </f:verbatim>
                       
						<s:layoutingTitlePane id="filtroOrigenen" rendered="#{ConciliacionCuponesBean.comercio}"
							label="Filtro de Comercio" containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane">
							<h:panelGrid id="filtroCod" columns="5" align="center">
								<h:outputText value="Cod Posnet:" styleClass="texto" />
								<h:inputText id="idComercioFiltro" 
									disabled="#{ConciliacionCuponesBean.panelAdjuntar}"
									value="#{ConciliacionCuponesBean.idComercio}"
									styleClass="bordeceldainferior" maxlength="5" size="5"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<x:commandButton id="btnBuscarComercio" value="Buscar" 
									disabled="#{ConciliacionCuponesBean.panelAdjuntar}"
									action="#{ConciliacionCuponesBean.listarComercio}"
									title="Busca el Comercio seleccionado"
									image="/img/icon/srch_24.gif"/>

								<x:commandButton id="btnCancelarBusqueda" value="Cancelar"   
									action="#{ConciliacionCuponesBean.cancelarBusqueda}" styleClass="btn btn-primary btn-flat" disabled="#{ConciliacionCuponesBean.panelAdjuntar}"/>				


								<c:if test="${! empty ConciliacionCuponesBean.comercioList}">	
										<h:outputText value="" styleClass="texto"/>
									
										<h:outputText value="Razon Social:" styleClass="texto"/>

										<h:outputText id="descripcionFiltro"
											value="#{ConciliacionCuponesBean.element.sucEmpresa.empresa.razonSocial}"
											styleClass="texto" />
								</c:if>						

							</h:panelGrid>
						</s:layoutingTitlePane>

						<s:fieldset id="fieldArchivoPostnet"
							rendered="#{ConciliacionCuponesBean.panelAdjuntar}">
							<h:panelGroup id="importacion">
								<h:outputText value="#{ConciliacionCuponesBean.titulo}"
									style="FONT-SIZE: large;" styleClass="texto" />
								<h:panelGrid columns="4" id="panelInternoOnce" width="650"
									align="center">
									<h:outputText value="Archivo: " id="outArch" styleClass="texto" />
									<x:inputFileUpload id="fileUpLoad" storage="file"
										styleClass="fileUploadInput" 
										maxlength="1000" required="true"
										value="#{ConciliacionCuponesBean.uploadedFile}"
										>
							         </x:inputFileUpload>

									<h:outputText value="Formato del Archivo: " rendered="#{ConciliacionCuponesBean.mostrarComboFormato}"/>
									<h:selectOneMenu id="lstFormatoDebito"  rendered="#{ConciliacionCuponesBean.mostrarComboFormato}"
										value="#{ConciliacionCuponesBean.idFormatoArchivo}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px"
										binding="#{ConciliacionCuponesBean.tipoFormatoArchivo}">
										<f:selectItems id="itemFormato"
											value="#{ConciliacionCuponesBean.tipoFormatoItems}" />
									</h:selectOneMenu>

									
									<h:commandButton value="Cancelar" onclick="window.close();"
										styleClass="btn btn-primary btn-flat" id="btonDesabilitarTDocLink" />
										
									<h:commandButton value="Ejecutar"
										action="#{ConciliacionCuponesBean.ejecutar2}"
										styleClass="btn btn-primary btn-flat" id="btonAdjuntarTDocLink"
										disabled="#{ConciliacionCuponesBean.esDebitoAut}"
										rendered="#{ConciliacionCuponesBean.noEsDebitoAut}"
										onclick="mostrarBarra();" />
										
									<h:commandButton value="Procesar"
										action="#{ConciliacionCuponesBean.procesarDebitos}"
										styleClass="btn btn-primary btn-flat" id="btonProcesar"
										disabled="#{ConciliacionCuponesBean.noEsDebitoAut}"
										rendered="#{ConciliacionCuponesBean.esDebitoAut}"
										onclick="mostrarBarra();"/>
										
									
									<h:panelGrid id="barrita" align="center" style="display:hidden">
										<f:verbatim>
											<script type="text/javascript">
		 				               			var bar=createBar(150,15,"white",1,"black","#333366",85,7,3,"");
					                		</script>
										</f:verbatim>
									</h:panelGrid>
									
								</h:panelGrid>
								
							</h:panelGroup>

						</s:fieldset>
						
						
						<s:fieldset id="fieldDebitosControlar" legend="Seleccione Items a Aceptar"
							rendered="#{ConciliacionCuponesBean.panelDebitosControlar}">
							<h:panelGrid columns="1" align="center">
								<s:filterTable id="filterTbl" var="tar" 
										value="#{ConciliacionCuponesBean.loteComercioControl}"
	                                    styleClass="standardTable" 
	                                    headClass="standardTable_Header">
										<s:sortableColumn field="seleccion" text="Sel." align="center">
											<h:selectBooleanCheckbox value="#{tar.seleccionado}" id="seleccionado" />
										</s:sortableColumn>
										<s:sortableColumn                
											field="detalle" text="Cliente (Titular)" align="left" >
											<h:outputText value="#{tar.cliente}" />
										</s:sortableColumn>
										<s:sortableColumn field="fecha" text="Nombre y Apellido (Tarjeta)" align="left">
											<h:outputText value="#{tar.nombreCliente}" />
										</s:sortableColumn>
										<s:sortableColumn field="cuota" text="Días de Mora" align="right">
											<h:outputText value="#{tar.diasMora} " />
										</s:sortableColumn>             
								</s:filterTable>
							</h:panelGrid>
							
							<h:panelGrid columns="1" align="right">
                                <h:commandButton id="btnProcesarDebitos" 
                                     value="Procesar Débitos" 
                                     action="#{ConciliacionCuponesBean.procesarItemsDebitos}" 
                                     styleClass="btn btn-primary btn-flat"
                                     onclick="mostrarBarra();"/>
                            </h:panelGrid>
						</s:fieldset>
                                            
						
						<s:fieldset id="fieldMensaje"
							rendered="#{ConciliacionCuponesBean.panelMensaje}">
							<h:column>
								<h:outputText value="#{ConciliacionCuponesBean.mensaje}" style="font-size: 15px;color: green; align='center'"/>
    						</h:column>	
						</s:fieldset>
						
					
					 <s:fieldset legend="#{ConciliacionCuponesBean.tituloNoConciliados}" id="fieldResConciliacion"
						rendered="#{ConciliacionCuponesBean.mostrarResultConc}">
                        <h:panelGrid id="filtroUno" columns="7" align="center" rendered="#{ConciliacionCuponesBean.filtroAutomatico}" >
						 <h:outputText value="Cod Comercio:" styleClass="texto" rendered="false"/>
						 <h:inputText id="codComercio"   value="#{ConciliacionCuponesBean.idLoteFiltroAut}" rendered="false"
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
			               			 onkeypress="return soloEnteros(this,event);"/>
 						   <%--  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>--%>
						   <h:outputText value="Buscar lotes que no esten cerrados " styleClass="texto" />
							
							<h:commandButton id="btnBusqueda" action="#{ConciliacionCuponesBean.buscarLotesAbiertosCargaAutomatica}"  
											 value="Buscar" styleClass="btn btn-primary btn-flat" /></h:panelGrid>
					
			
							<h:panelGroup id="grup">
								<h:panelGrid columns="3" width="750" align="center"	id="panelEncabezadoConc" rendered="#{ConciliacionCuponesBean.mostrarEncabezado}">
                                  
                                  <h:outputText id="outputEncontrados" value="Encontrados:" styleClass="texto" />
							      <f:verbatim>&nbsp;</f:verbatim>
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  
                                  <h:panelGrid columns="2" align="left" id="itemConciliados">
	                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
	                                  <h:outputText id="outputConciliados" value="Conciliados:" styleClass="texto" />                               
                                  </h:panelGrid>
                                  <h:outputText id="outputResultadoConciliados" value="#{ConciliacionCuponesBean.resultadoConciliacion.conciliados}" style="color:blue" styleClass="texto" />
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  
                                  <h:panelGrid columns="2" align="left" id="itemAnulados">
	                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
	                                  <h:outputText id="outputAnulados" value="Anulados:" styleClass="texto" />                               
                                  </h:panelGrid>
                                  <h:outputText id="outputResultadoAnulados" value="#{ConciliacionCuponesBean.resultadoConciliacion.anulados}" style="color:blue" styleClass="texto" />
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  
                                  <h:panelGrid columns="2" align="left" id="itemOffline">
	                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
	                                  <h:outputText id="outputAutorizadosTelefonicamente" value="Autorizados telefónicamente:" styleClass="texto" />                               
                                  </h:panelGrid>
                                  <h:outputText id="outputResultadoOffline" value="#{ConciliacionCuponesBean.resultadoConciliacion.offline}" style="color:blue" styleClass="texto" />
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  
                                  <h:panelGrid columns="2" align="left" id="itemRechazados">
	                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
	                                  <h:outputText id="outputRechazados" value="Rechazados:" styleClass="texto" />                               
                                  </h:panelGrid>
                                  <h:outputText value="#{ConciliacionCuponesBean.resultadoConciliacion.rechazados}" style="color:blue" styleClass="texto" />
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  
                                  <h:panelGrid columns="2" align="left" id="itemTotalEncontrados">
	                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim> 
	                                  <h:outputText id="outputTotalEncontrados" value="Total encontrados:" styleClass="texto" />                               
                                  </h:panelGrid>
                                  <f:verbatim>&nbsp;</f:verbatim>
                                  <h:outputText value="#{ConciliacionCuponesBean.resultadoConciliacion.encontrados}" style="color:blue" styleClass="texto" />
                                  
                                  
				                  <f:verbatim><hr id="linea" align="center" width="250"></f:verbatim>
				                  <f:verbatim><hr id="lineados" align="center" width="250"></f:verbatim>
				                  <f:verbatim><hr id="lineatres" align="center" width="250"></f:verbatim>
                                                             
                                  <h:outputText id="outputNoEncontrados" value="No encontrados:" styleClass="texto" />
							      <f:verbatim>&nbsp;</f:verbatim>
                                  <h:outputText value="#{ConciliacionCuponesBean.resultadoConciliacion.noEncontrados}" style="color:blue" styleClass="texto" />
                                  
                                  
				                  <f:verbatim><hr id="lineacuatro" align="center" width="250"></f:verbatim>
				                  <f:verbatim><hr id="lineacinco" align="center" width="250"></f:verbatim>
				                  <f:verbatim><hr id="lineaseis" align="center" width="250"></f:verbatim>
                                                             
                                  <h:outputText id="outputTotalProcesados" value="Total cupones procesados:" styleClass="texto" />
							      <f:verbatim>&nbsp;</f:verbatim>
                                  <h:outputText id="outputTotalConciliados" value="#{ConciliacionCuponesBean.resultadoConciliacion.total}" style="color:blue" styleClass="texto" />                               
                                                                                                                                 

                                </h:panelGrid>
                                 
                        <h:panelGrid columns="5" id="filtrosBusqueda" width="700"	align="left" rendered="#{ConciliacionCuponesBean.filtroManual}">
						<h:outputText value="Cod Comercio:" style="width: 120px;" id="outputCodCom" />
						<h:inputText
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codComercioFiltro}"
							style="width: 120px; display:hidden" 
							id="codComFiltrp"
							onkeypress="return soloEnteros(this,event);" />
						<h:outputText value="Nro Lote:" style="width: 120px;" />
						<h:inputText
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.nroLoteFiltro}"
							maxlength="11"
							style="width: 120px; display:hidden" 
							id="nroLoteFiltro"
							onchange="rellenarNro(this, this.value,11);"
							onkeypress="return soloEnteros(this,event);" />
						<x:commandLink value="" title="Buscar Cupones"
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.buscarLotesNoCerradosConcManual}"
							id="busqueda">
							<x:graphicImage id="imgnBusqueda" value="/img/icon/srch_32.gif"
								style=" width : 32px;"></x:graphicImage>
						</x:commandLink>

					</h:panelGrid>
					<h:panelGrid columns="1">
						
						<c:if test="${ConciliacionCuponesBean.busquedaAvanzada && ConciliacionCuponesBean.filtroManual}">
							<x:commandLink value="" title="Ocultar Busqueda Avanzada" 	id="OcultarAvanz"
									actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.ocultarAvanzada}">
								<x:graphicImage id="ocultAvanzImg" value="/img/arrow_u_32.png"
									style="width : 18px; height : 18px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
						<c:if test="${!ConciliacionCuponesBean.busquedaAvanzada && ConciliacionCuponesBean.filtroManual}">
							<x:commandLink value="" title="Mostrar  Busqueda Avanzada" id="buscAvanz"
								actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarAvanzada}">
								<x:graphicImage id="buscAvanzImg" value="/img/arrow_d_32.png" style="width : 18px; height : 18px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
					</h:panelGrid>
					<h:panelGrid columns="4" id="filtrosBusquedaAvanzada" width="700"
						align="left" rendered="#{ConciliacionCuponesBean.busquedaAvanzada}">
						
						<h:outputText value="Fecha Recepcion Desde:" style="width: 250px;" />
						<x:inputCalendar id="fecLoteFiltroRecDesde"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
						<h:outputText value="Fecha Recepcion Hasta:" style="width: 250px;" />
						<x:inputCalendar id="fecLoteFiltroRecHasta"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
					</h:panelGrid>
                    

					<h:panelGrid columns="1" width="650" align="center"	id="panelListadoPendientes" rendered="#{ConciliacionCuponesBean.filtroManual}">
						<c:if		test="${empty ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}">
							<h:outputText value="La Busqueda no produjo resultados."
								styleClass="texto" style="color:green"  id="outMsg2"/>
						</c:if>


						<c:if 	test="${!empty ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}">
							<h:dataTable align="center"
								value="#{ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}"
								id="tablaLoteNoConciliadosManual" styleClass="standardTable"
								headerClass="dataTable_Header"
								footerClass="standardTable_Header"
								rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="obj" style=" width : 570px;">
								<h:column>
									<f:facet name="header">
										<h:outputText value="Id Lote" styleClass="texto"
											 id="outidLoteManual_h" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.idLoteComercio}"
										style=" width : 150px;" styleClass="textoblue" id="outidLoteManual"/>
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Fecha Lote" styleClass="texto"
											 id="outFechaLoteManual_h"/>
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.fechaReal}"
										style=" width : 150px;" styleClass="textoblue" id="outFechaLoteManual"/>
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Cod Comercio" styleClass="texto"
											 id="outCodComManual_h" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.codComercio}"
										style=" width : 150px;" styleClass="textoblue" id="outCodComManual"/>
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Cant cupones" styleClass="texto"
											 id="outCantCupones_h"/>
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.cantCupones}"
										style=" width : 150px;" styleClass="textoblue" id="outCantCupones"/>
								</h:column>
								<h:column>
								<x:commandLink value="" title="Ver  items no conciliados" 	id="verItems"
									actionListener="#{ConciliacionCuponesBean.buscarNoConciliados}">
								<x:graphicImage id="verItemsImg" value="/img/table_add.png"  
									style="width : 18px; height : 18px;"></x:graphicImage>
								  <f:param name="idLote" value="#{obj.loteComercioResumen.idLoteComercio}"/>
							      
							      </x:commandLink>
								</h:column>
							</h:dataTable>
						</c:if>
					</h:panelGrid>
								
					<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 300px; border: 1px; margin-left: auto; margin-right: auto;">
					<h:panelGrid columns="1" width="650" align="center"	id="panelListLoteNoConc" rendered="#{ConciliacionCuponesBean.mostrarTablaLotesAutomAbiertos}">
						<c:if test="${empty ConciliacionCuponesBean.listaLotesAutomaticosAbiertos}">
							<h:outputText value="No existen cupones sin conciliar." id="outMsg"
								styleClass="texto" style="color:green" />
						</c:if>
                        <c:if test="${!empty ConciliacionCuponesBean.listaLotesAutomaticosAbiertos}">
								<h:dataTable align="center" rendered="#{ConciliacionCuponesBean.mostrarTablaNoConcCabecera}"
									value="#{ConciliacionCuponesBean.listaLotesAutomaticosAbiertos}"
									id="tablaLoteNoConciliadosAutomatica" styleClass="standardTable"
									headerClass="dataTable_Header"
									footerClass="standardTable_Header"
									rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_ColumnRight"
									var="obj" style=" width : 570px;">
									<h:column>
										<f:facet name="header">
											<h:outputText value="Id Lote" styleClass="texto"
												 id="outidLoteManual_h" />
										</f:facet>
										<h:outputText value="#{obj.loteComercio.idLoteComercio}"
											style=" width : 150px;" styleClass="textoblue" id="outidLoteManual"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha Lote" styleClass="texto"
												 id="outFechaLoteManual_h"/>
										</f:facet>
										<h:outputText value="#{obj.loteComercio.fechaReal}"
											style=" width : 150px;" styleClass="textoblue" id="outFechaLoteManual"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Cupones Conciliados" styleClass="texto"
												 id="outCantCupones_h"/>
										</f:facet>
										<h:outputText value="#{obj.loteComercio.cuponesConciliados}"
											style=" width : 150px;" styleClass="textoblue" id="outCantCupones"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Cupones Rechazados" styleClass="texto"
												 id="outCantCupones_h"/>
										</f:facet>
										<h:outputText value="#{obj.loteComercio.cuponesRechazados}"
											style=" width : 150px;" styleClass="textoblue" id="outCantCupones"/>
									</h:column>
									<h:column>
									<x:commandLink value="" title="Ver items del lote" 	id="agregarItems"
										actionListener="#{ConciliacionCuponesBean.buscarNoConciliados}">
									<x:graphicImage id="agregarItemsImg" value="/img/table_add.png"  
										style="width : 18px; height : 18px;"></x:graphicImage>
									  <f:param name="idLote" value="#{obj.loteComercioResumen.idLoteComercio}"/>
								      
								      </x:commandLink>
									</h:column>
								</h:dataTable>
						</c:if> 
                   	</h:panelGrid>


								<h:panelGrid columns="1" width="650" align="center"	id="panelListNoConc" rendered="#{ConciliacionCuponesBean.mostrarTablaNoConc}">
									<c:if test="${!empty ConciliacionCuponesBean.listCuponesNoConc}">
										<h:dataTable align="center"
											value="#{ConciliacionCuponesBean.listCuponesNoConc}"
											id="tablaNoConciliados" styleClass="standardTable"
											headerClass="dataTable_Header"
											footerClass="standardTable_Header"
											rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="obj" style=" width : 570px;">
											
											<c:if test="${ConciliacionCuponesBean.esDebito}">
											<h:column>
												<f:facet name="header">
													<h:outputText value="Plástico" styleClass="texto"  id="outPlastico_h"/>
												</f:facet>
												<h:outputText value="#{obj.loteComercioItem.nroTarjeta}" style=" width : 150px;" styleClass="textoblue" id="outPlastico"/>
											</h:column>
											</c:if>
											<c:if test="${!ConciliacionCuponesBean.esDebito}">
											<h:column>
												<f:facet name="header">
													<h:outputText value="Lote" styleClass="texto"  id="outLoteDebito_h"	 />
												</f:facet>
												<c:if test="${ConciliacionCuponesBean.idTipoConcSeleccionada==1}"> 
												<h:outputText value="#{obj.loteComercioItem.nroLote}" style=" width : 150px;" styleClass="textoblue"   id="outLoteDebito" />
												</c:if>
												<c:if test="${ConciliacionCuponesBean.idTipoConcSeleccionada==2}"> 
												<h:outputText value="#{obj.loteComercioItem.loteComercio.idLoteComercio}" style=" width : 150px;" styleClass="textoblue"   id="outLoteDebito" />
												</c:if>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Cupon" styleClass="texto"   id="outCupon_h" />
												</f:facet>
												<h:outputText value="#{obj.loteComercioItem.nroCupon}" style=" width : 150px;" styleClass="textoblue" id="otCupon"/>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Fecha" styleClass="texto"  id="outFecha_h"/>
												</f:facet>
												<h:outputText value="#{obj.loteComercioItem.fechaReal}" style=" width : 150px;" styleClass="textoblue" id="otFecha"/>
											</h:column>
											</c:if>
										    <h:column>
													<f:facet name="header">
														<h:panelGroup>
															<f:facet name="header">
																<h:outputText value="Selecciona Todos" id="todos" styleClass="texto" />
															</f:facet>
															<h:selectBooleanCheckbox value="#{ConciliacionCuponesBean.todos}" id="boolTodos"  onclick="marcar();"/>
														</h:panelGroup>
													</f:facet>
													<h:selectBooleanCheckbox value="#{obj.seleccionado}" style="width: 25px" id="estado"/>
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
										<c:if
										         test="${!ConciliacionCuponesBean.esDebito}">
											<h:panelGrid columns="11" id="pnlesDebito">
												<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
												<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
												<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											     
												<h:commandButton value="Conciliar" styleClass="botones" action="#{ConciliacionCuponesBean.conciliarCupones}"
													id="botonConciliar" />
												<h:commandButton value="Cancelar"
													action="#{ConciliacionCuponesBean.cancelarConc}"
													styleClass="botones" id="btonCancelarConc" />
	
											</h:panelGrid>
										</c:if>
									</c:if>
                                    <c:if    test="${!ConciliacionCuponesBean.esDebito}">
                                       <x:commandLink  id="asociarTrans2"  immediate="true"  rendered="#{ConciliacionCuponesBean.mostrarLinkBusquedaCupNoAsoc}"
                                                  action="#{ConciliacionCuponesBean.asociarTransaccion}"  value="Buscar Cupones no Asociados a Transaccion"/>
                                    </c:if>
                            	</h:panelGrid>
							</x:div>

						</h:panelGroup>
						</s:fieldset>
						
						<s:fieldset id="fieldDebitosPendientes" legend="Seleccione lote a Conciliar" rendered="#{ConciliacionCuponesBean.panelDebitosPendientes}">
							<h:panelGrid id="panelPendientes" columns="1" align="center">
								<s:filterTable id="filterTb2" var="tar" value="#{ConciliacionCuponesBean.lotesPendientes}" styleClass="standardTable" headClass="standardTable_Header">
										<s:sortableColumn id="columnSeleccion" field="seleccion" text="Sel." align="center">
											<h:selectBooleanCheckbox value="#{tar.seleccionado}" id="selec"/>
										</s:sortableColumn>
										<s:sortableColumn id="columnNroLote" field="lote" text="Nro. de Lote" align="left">
											<h:outputText id="outNroLote" value="#{tar.idLote}"/>
										</s:sortableColumn>          
								</s:filterTable>
							</h:panelGrid>
							
							<h:panelGrid id="panelBtnConciliar" columns="1" align="right">
                                <h:commandButton id="btnConciliar" value="Conciliar" action="#{ConciliacionCuponesBean.conciliarDebitosPendientes}" styleClass="botones" onclick="mostrarBarra();"/>
                            </h:panelGrid>
						</s:fieldset>
						
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>
	</h:form></center>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ConciliacionCuponesBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>

	</body>
	</html>
</f:view>

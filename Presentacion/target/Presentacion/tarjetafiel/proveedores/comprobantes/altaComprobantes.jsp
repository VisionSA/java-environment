<%@include file="/inc/tags.jsp"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText
		value="Tarjeta Fiel - Alta de Comprobantes" /></title>
	<s:script language="javascript">
	    function verArchivo() {
	        document.getElementById('linkArchivoAn').click();
	        return true;
	    };
	    
	    
	    
    	function recargar() {
    		document.getElementById('altaComprobantesForm').submit();
    	};
    	
    	function writeImageValue(lugar) { 
    	document.getElementById('altaComprobantesForm:txtLugarArchivo').value = lugar; 
    	  	 
        document.getElementById('altaComprobantesForm:btonAdjuntarApplet').click();
    };
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
		function verDialog() {
			dojo.widget.byId('verErrorImpuesto').show();
		};
    </s:script>
	</head>

	<jsp:include page="/inc/includes.jsp" />

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaComprobantesForm');"
		onload="if('${ComprobanteBean.popupImpuestos.mostrar}'=='true') {verErrorImpuesto.show();}
								 else {if('${ComprobanteBean.popup.mostrar}'=='true') {viewDialog.show();} 
								 			else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}}">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ComprobanteBean.tituloCorto}
    <small>${ComprobanteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>



<center>

<secure:check/>



<h:form id="altaComprobantesForm" enctype="multipart/form-data">

		<%-- GRABA EL ESTADO DEL SCROLL --%>
		<h:panelGroup rendered="#{!ComprobanteBean.verPopup}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">


			<f:facet name="body">
				<h:panelGroup id="body">

					<h:panelGrid columns="1" align="center" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" id="viewDialog"
							dialogVar="viewDialog" styleClass="viewDialog"
							dialogTitle="#{ComprobanteBean.tituloCorto}">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage
									value="/img/#{ComprobanteBean.popup.nombreIcono}" />
								<h:outputText value=" #{ComprobanteBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>

							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{ComprobanteBean.irANuevoComp}"
									onclick="clickLink('nuevoComp');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="btn btn-primary btn-flat"
									title="Agregar nueva orden de pago." />

								<x:commandButton action="#{ComprobanteBean.irAModificarComp}"
									rendered="false"
									onclick="clickLink('modificarComp');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="btn btn-primary btn-flat"
									title="Seguir modificando la orden de pasgo." />

								<x:commandButton action="#{ComprobanteBean.irAListarComp}"
									onclick="clickLink('listarComp');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="btn btn-primary btn-flat"
									title="Ir al listado de ordenes de pago." />
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="nuevoComp"
							action="#{ComprobanteBean.irANuevoComp}" forceId="true"
							style="display: block;" />
						<x:commandLink id="modificarComp"
							action="#{ComprobanteBean.irAModificarComp}" forceId="true"
							style="display: block;" />
						<x:commandLink id="listarComp"
							action="#{ComprobanteBean.irAListarComp}" forceId="true"
							style="display: block;" />

						<%-- POPUP PARA LOS MENSAJES DE ERROR CON LOS IMPUESTOS --%>
						<s:modalDialog dialogId="verErrorImpuesto" id="verErrorImpuesto"
							dialogVar="verErrorImpuesto" styleClass="viewDialog"
							dialogTitle="Cuidado!!!">
							
							<h:panelGrid columns="2" width="400">
								<x:graphicImage
									value="/img/#{ComprobanteBean.popupImpuestos.nombreIcono}" />
								<h:outputText value=" #{ComprobanteBean.popupImpuestos.mensaje}"
									styleClass="texto" />
							</h:panelGrid>

							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{ComprobanteBean.irAGrabarComp}"
									onclick="clickLink('grabarComp');dojo.widget.byId('verErrorImpuesto').hide();"
									value="Grabar" styleClass="btn btn-primary btn-flat"
									title="Graba el comprobante." />

								<x:commandButton
									action="#{ComprobanteBean.irAModificarCompSinGrabar}"
									onclick="clickLink('modificarCompSinGrabar');dojo.widget.byId('verErrorImpuesto').hide();"
									value="Modificar" styleClass="btn btn-primary btn-flat"
									title="Seguir modificando el comprobante." />

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							</h:panelGrid>
						</s:modalDialog>
						<x:commandLink id="grabarComp"
							action="#{ComprobanteBean.irAGrabarComp}" forceId="true"
							style="display: block;" />
						<x:commandLink id="modificarCompSinGrabar"
							action="#{ComprobanteBean.irAModificarCompSinGrabar}"
							forceId="true" style="display: block;" />
					<c:if test="${empty ComprobanteBean.tipoComprobante}">
						<s:layoutingTitlePane id="altaComprobantes" label=" Alta Comprobantes" 
								      		containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >	
							<h:panelGrid id="panelInicio" columns="3" width="500" align="center">
								<h:outputText id="outCuit" value="CUIT del Proveedor: "
									styleClass="texto" />
								<h:panelGroup id="panelCuit">
									<x:inputText id="inCuit" forceId="true"
										value="#{ComprobanteBean.cuit}" size="11" maxlength="11"
										styleClass="bordeceldainferior" style="width: 100px"
										onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" />
								
									<h:panelGroup>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<x:commandLink id="buscarProveedorLink" action="#{ComprobanteBean.buscarProveedorPopup}">
											<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
										</x:commandLink>
									</h:panelGroup>
									
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:outputText id="outSeleccionTipo"
									value="Seleccione el tipo de comprobante:" styleClass="texto" />
								<h:selectOneMenu id="SeleccionTipo"
									value="#{ComprobanteBean.tipoComprobanteSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);">
									<f:selectItems id="selectTiposComp"
										value="#{ComprobanteBean.tiposCompList}" />
								</h:selectOneMenu>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:panelGroup>
									<c:choose>
										<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
											<h:commandButton id="btnCrearComprobante" value="Continuar"
												action="#{ComprobanteBean.crearComprobante}"
												styleClass="btn btn-primary btn-flat" title="Crea el comprobante" />
										</c:when>
										<c:otherwise>
											<h:commandButton id="btnCrearComprobante" value="Continuar"
												onclick="alert('No posee los permisos necesarios.');"
												styleClass="btn btn-primary btn-flat" title="Crea el comprobante" />								       						 
										</c:otherwise>
									</c:choose>					
								</h:panelGroup>		
							</h:panelGrid>
					</s:layoutingTitlePane>
				</c:if>
						<c:if test="${!empty ComprobanteBean.proveedor}">
							<h:panelGrid id="gridPrincipal" columns="1" width="900" align="center">
							<h:panelGrid columns="4" width="800" align="center" >
								<h:outputText id="outNomProveedor" value="Proveedor: "
									styleClass="texto" />
								<h:panelGroup>
									<h:outputText id="CodProveedor" value="#{ComprobanteBean.provPedidoPor.idProveedor}"
										styleClass="textoblue" />
									<h:outputText id="sepProveedor" value=" - " 
										styleClass="textoblue" />
									<h:outputText id="NomProveedor" value="#{ComprobanteBean.provPedidoPor.razonSocial}"
										styleClass="textoblue" />
								</h:panelGroup>
								<h:outputText id="outTipoComp" value="Tipo Comprobante: "
									styleClass="texto" />
								<h:outputText id="TipoCompLargo"
									value="#{ComprobanteBean.tipoComprobante.descripcionLarga}"
									styleClass="textoblue" />

								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>

								<h:outputText id="outCuitVer" value="CUIT: " styleClass="texto" />
								<h:panelGroup id="panelCuit">
									<h:inputText id="Ident" disabled="#{ComprobanteBean.boolCuit}"
										value="#{ComprobanteBean.cuitIdentificador}" size="2"
										maxlength="2" styleClass="bordeceldainferior"
										style="width: 30px" onfocus="encender(this);"
										onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" />
									<h:outputText id="separador_1" value="-" styleClass="texto" />
									<h:inputText id="Dni" disabled="#{ComprobanteBean.boolCuit}"
										value="#{ComprobanteBean.cuitDni}" size="8" maxlength="8"
										styleClass="bordeceldainferior" style=" width : 80px;"
										onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" />
									<h:outputText id="separador_2" value="-" styleClass="texto" />
									<h:inputText id="Verif" disabled="#{ComprobanteBean.boolCuit}"
										value="#{ComprobanteBean.cuitVerificador}" size="1"
										maxlength="1" styleClass="bordeceldainferior"
										style="width: 30px" onfocus="encender(this);"
										onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);" />
									<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
									<x:commandLink action="#{ComprobanteBean.validarCUIT}"
										id="validarCuitLink">
										<x:graphicImage value="/img/icon/pen_red 16.png"
											title="Edita el CUIT." border="0" />
									</x:commandLink>
								</h:panelGroup>


								<h:outputText id="outNroComp" value="Nro. Comprobante: "
									styleClass="texto" />

								<h:panelGroup id="groupNro">
									<h:inputText id="NroCorto" value="#{ComprobanteBean.nroCorto}"
										styleClass="bordeceldainferior" maxlength="4" size="4"
										style="width: 50px" onfocus="encender(this);"
										onblur="apagar(this);" disabled="#{ComprobanteBean.boolComprobante}"
										onkeypress="return soloEnteros(this,event);"
										onchange="rellenarNroCorto(this, this.value);
										armarNombreAsiento('#{ComprobanteBean.tipoComprobante.descripcionCorta}', '#{ComprobanteBean.proveedor.razonSocial}');">
										
										<%--  --%>
									</h:inputText>
									<h:outputText id="out-Nro1" value=" - " styleClass="texto" />
									<h:inputText id="NroLargo" value="#{ComprobanteBean.nroLargo}"
										styleClass="bordeceldainferior" maxlength="8" size="8"
										style="width: 90px" onfocus="encender(this);"
										onblur="apagar(this);" disabled="#{ComprobanteBean.boolComprobante}"
										onkeypress="return soloEnteros(this,event);"
										onchange="rellenarNroLargo(this, this.value); 
										armarNombreAsiento('#{ComprobanteBean.tipoComprobante.descripcionCorta}',
										'#{ComprobanteBean.provPedidoPor.razonSocial}','#{ComprobanteBean.provPedidoPor.idProveedor}');">
									</h:inputText>
								</h:panelGroup>

								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>

								<h:outputText id="outFechaEmision" value="Fecha Emisión: "
									styleClass="texto" />

							 		<f:verbatim>
								            <div class="input-group date">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesde">
								            </div>
									</f:verbatim>

								<h:outputText id="outFechaContable" value="Fecha Contable: "
									styleClass="texto" />
							 		<f:verbatim>
									        <div class="input-group date">
									            <div class="input-group-addon">
									                <i class="fa fa-calendar"></i>
									            </div>
									            <input type="text" class="form-control pull-right" id="fHasta">
									        </div>
									</f:verbatim>


						    <h:inputText id="FechaDesde" value="#{ComprobanteBean.fechaEmision}" style="display: none;">
						        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
						    </h:inputText>
						    <h:inputText id="FechaHasta" value="#{ComprobanteBean.fechaContable}" style="display: none;">
						        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
						    </h:inputText>
						    <f:verbatim>&nbsp;</f:verbatim>
						    <f:verbatim>&nbsp;</f:verbatim>
																	  													
								
						 <h:outputText id="outArchivo" value="Adjuntar Archivo"
									styleClass="texto" />
							
							<h:panelGroup  >
								
									<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" maxlength="1000"
									value="#{ComprobanteBean.imagen}" onfocus="encender(this);" onblur="apagar(this);" disabled="#{ComprobanteBean.mostraTablaArchivosAdjuntos}"/> 
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:commandButton value="Adjuntar" action="#{ComprobanteBean.saveFile}" 
										styleClass="btn btn-primary btn-flat" id="btonAdjuntarTDocLink" disabled="#{ComprobanteBean.mostraTablaArchivosAdjuntos}"/>

										
<%-- 									<x:commandButton  id="btonAdjuntarApplet"  immediate="true" --%>
<%--                             action="#{ComprobanteBean.saveFileApplet}" styleClass="botones" value=""/>  --%>
                           
                           <h:commandButton id="btonAdjuntarApplet" value="Cambiar lugar"
								title="Cambiar lugar" styleClass="btn btn-primary btn-flat"
								action="#{ComprobanteBean.saveFileApplet}" style="display:none" /> 
                           
								</h:panelGroup>
								
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>

								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								
								<h:outputText id="outObservacion" value="Observaciones: "
									styleClass="texto" />
								<x:inputTextarea id="Obcervacion" style=" width : 250px; height : 70px;"
									value="#{ComprobanteBean.observacion}"
									onfocus="encender(this);" onblur="apagar(this);" />
							
							
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>		
								
								<h:panelGrid columns="1" id="panelAdjuntos" width="150" align="left">
								    
									<s:fieldset legend="Archivo Adjunto">
									<x:dataTable id="atributosList" var="listArchAdj" value="#{ComprobanteBean.listArchivosAdjuntos}" width="150px" >
												<h:column>
												    <h:commandLink value="#{listArchAdj.nombreArchivo}" action="#{ComprobanteBean.abrirArchivoAdjunto}">
													
												    </h:commandLink>
												</h:column>
											    
											    <h:column>
													<x:commandLink id="eliminarArchivoAdjuntoLink" action="#{ComprobanteBean.eliminarArchivoAdjunto}">
														<f:param id="idProcesoAtributo" name="idProcesoAtributo" value="#{listArchAdj.idArchivoAdjunto}"/>
														<x:graphicImage value="/img/borrar.gif" title="Eliminar archivo." border="0" />
													</x:commandLink>
												</h:column>
												
											</x:dataTable>
										</s:fieldset>
																		
									</h:panelGrid>
							
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								
							</h:panelGrid>

							<h:panelGrid columns="4" align="right" columnClasses="standardTable_Column,standardTable_ColumnRight">
								
								<h:outputText id="outMontoNoGrabado" value="Monto No Grabado: "
									styleClass="texto" />
								<h:inputText id="MontoNoGrabado"
									value="#{ComprobanteBean.montoNoGrabado}"
									styleClass="bordeceldainferior" maxlength="12" size="12"
									style="width: 90px"
									onchange="javascript:sumar2('altaComprobantesForm:MontoNoGrabado','altaComprobantesForm:MontoGrabado','altaComprobantesForm:SubTotal');
											   javascript:setValueId('MontoGrabado','idFoco'); 
											  recargar();"
									onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloDecimales(this,event);">
								</h:inputText>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText id="outMontoGrabado" value="Monto Grabado: "
									styleClass="texto" />
								<h:inputText id="MontoGrabado"
									value="#{ComprobanteBean.montoGrabado}"
									styleClass="bordeceldainferior" maxlength="12" size="12"
									style="width: 90px"
									onchange="javascript:sumar2('altaComprobantesForm:MontoGrabado','altaComprobantesForm:MontoNoGrabado','altaComprobantesForm:SubTotal');
											 javascript:setValueId('listadoImpuestos:0:MontoImp','idFoco');
											 recargar();"
									onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloDecimales(this,event);">
								</h:inputText>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>


								<h:outputText id="outSubTotal" value="Sub Total: "
									styleClass="texto" />
								<h:outputText id="SubTotal"
									value="#{ComprobanteBean.importeNeto}" styleClass="textoblue" style="text-align: right;"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>


								<h:outputText id="outTotalImpuestos"
									value="Impuestos y Percepciones: " styleClass="texto" />
								<h:outputText id="TotalImpuestos"
									value="#{ComprobanteBean.totalImpuestos}" styleClass="textoblue" style="text-align: right;"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:outputText id="outTotal" value="Total: " styleClass="texto" />
								<h:outputText id="Total" value="#{ComprobanteBean.importeTotal}" styleClass="textoblue" style="text-align: right;"/>


								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
							</h:panelGrid>

							<s:layoutingTitlePane id="impYper"
								label="Impuestos y Percepciones"
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								<x:dataTable id="listadoImpuestos" 
									styleClass="standardTable"
									headerClass="dataTable_Header"
									footerClass="standardTable_Header"
									rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,
													standardTable_ColumnCentered,standardTable_ColumnRight"
									var="impConMonto" value="#{ComprobanteBean.tablaDeImpuestos}"
									rows="10" preserveDataModel="false" style="width: 900px;">

									<h:column>
										<f:facet name="header">
											<h:outputText value="Impuesto" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{impConMonto.impuesto.descripcion}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Codigo" styleClass="texto" />
										</f:facet>
										<h:outputText
											value="#{impConMonto.impuesto.categoria.codCategoria}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto" />
										</f:facet>
										<h:outputText
											value="#{impConMonto.impuesto.categoria.descripcion}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="% Alicuota" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{impConMonto.impuesto.porcAlicuota}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Monto" styleClass="texto" />
										</f:facet>
										<h:inputText id="MontoImp" value="#{impConMonto.monto}"
											styleClass="bordeceldainferior" maxlength="12" size="12"
											style="width: 90px" 
											onchange="javascript:setValueId('listadoPercepciones:0:MontoPer','idFoco'); recargar();"
											onfocus="encender(this);" onblur="apagar(this);"
											onkeypress="return soloDecimales(this,event);">
										</h:inputText>
									</h:column>

								</x:dataTable>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<x:dataTable id="listadoPercepciones" styleClass="standardTable"
									headerClass="dataTable_Header"
									footerClass="standardTable_Header"
									rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,
													standardTable_ColumnCentered,standardTable_ColumnRight"
									var="perConMonto"
									value="#{ComprobanteBean.tablaDePercepciones}" rows="10"
									preserveDataModel="false" style="width: 900px;">

									<h:column>
										<f:facet name="header">
											<h:outputText value="Percepcion" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{perConMonto.impuesto.descripcion}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Codigo" styleClass="texto" />
										</f:facet>
										<h:outputText
											value="#{perConMonto.impuesto.categoria.codCategoria}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto" />
										</f:facet>
										<h:outputText
											value="#{perConMonto.impuesto.categoria.descripcion}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="% Alicuota" styleClass="texto" />
										</f:facet>
										<h:outputText value="#{perConMonto.impuesto.porcAlicuota}" />
									</h:column>

									<h:column>
										<f:facet name="header">
											<h:outputText value="Monto" styleClass="texto" />
										</f:facet>
										<h:inputText id="MontoPer" value="#{perConMonto.monto}"
											styleClass="bordeceldainferior" maxlength="12" size="12"
											style="width: 90px" 
											onchange="javascript:setValueId('Dias','idFoco'); recargar();"
											onfocus="encender(this);" onblur="apagar(this);"
											onkeypress="return soloDecimales(this,event);">
										</h:inputText>
									</h:column>

								</x:dataTable>
							</s:layoutingTitlePane>



							<s:layoutingTitlePane id="compocPagos"
								label="Composicion de pagos"
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								<h:panelGrid id="gridPagos" columns="2" width="450">
									<x:dataTable id="listadoPagos" styleClass="standardTable"
										headerClass="dataTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnRight"
										sortable="true" var="cuota"
										value="#{ComprobanteBean.tablaDeCuotas}"
										preserveDataModel="false" rendered="true" style=" width : 860px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Dias" styleClass="texto" />
											</f:facet>
											<h:inputText id="Dias" value="#{cuota.dias}"
												styleClass="bordeceldatext" maxlength="4" size="4"
												style="width: 70px" 
												onchange="javascript:setValueId('Porcentaje','idFoco'); recargar();"
												onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this,event);">
											</h:inputText>
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Porcentaje" styleClass="texto" />
											</f:facet>
											<h:inputText id="Porcentaje" value="#{cuota.porcentaje}"
												styleClass="bordeceldatext" maxlength="5" size="5"
												style="width: 50px" 
												onchange="javascript:setValueId('grapAddCuota','idFoco'); recargar();"
												onfocus="encender(this);" onblur="apagar(this);"
												onkeypress="return soloEnteros(this.value,event);">
											</h:inputText>
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Vencimiento" styleClass="texto" />
											</f:facet>
											<h:outputText
												value="#{cuota.cuotaComprobante.fechaVencimiento}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Importe" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{cuota.cuotaComprobante.importe}"/>
										</h:column>

									</x:dataTable>

									<h:panelGroup id="groupAddCuota">
										<x:commandLink action="#{ComprobanteBean.agregarCuota}"
											id="agregarCuotaLink">
											<x:graphicImage id="grapAddCuota" value="/img/cat_pad.gif"
												title="Agregar una cuota." border="0" />
										</x:commandLink>
										<x:commandLink action="#{ComprobanteBean.eliminarCuota}"
											id="eliminarCuotaLink">
											<x:graphicImage id="grapRemCuota" value="/img/cat_act.gif"
												title="Eliminar una cuota." border="0" />
										</x:commandLink>
									</h:panelGroup>
								</h:panelGrid>
							</s:layoutingTitlePane>

							<s:layoutingTitlePane id="asientoCont" label="Asiento Contable"
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
								<h:panelGrid id="gridAsientos" columns="2" width="550">
									<h:panelGroup id="groupAsiento1">
										<h:outputText id="outAsientoContable"
											value="Nombre del Asiento: " styleClass="texto" />
										<h:inputText id="ConceptoAsiento"
											value="#{ComprobanteBean.conceptoAsiento}"
											styleClass="bordeceldatext" maxlength="100" size="100"
											style="width: 600px" onfocus="encender(this);"
											onblur="apagar(this);">
										</h:inputText>
									</h:panelGroup>
									<f:verbatim>&nbsp;</f:verbatim>

									<x:dataTable id="listadoAsientoContable"
										styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="dataTable_columns,standardTable_Column,standardTable_ColumnRight,
														standardTable_ColumnRight,standardTable_ColumnCentered"
										var="asiento" value="#{ComprobanteBean.tablaDeAsientos}"
										preserveDataModel="false" style=" width : 860px;">

										<x:column>
											<f:facet name="header" >
												<h:outputText value="Codigo" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{asiento.asiento.nroImputa}" />

										</x:column>

										<x:column>
											<f:facet name="header">
												<h:outputText value="Descripcion Cuenta" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{asiento.titulo}" />
										</x:column>

										<x:column>
											<f:facet name="header">
												<h:outputText value="Debe" styleClass="texto" />
											</f:facet>
											<h:inputText id="Debe"
												onkeypress="return soloDecimales(this,event);"
												onchange="javascript:resetearText(this, 'Haber');"
												value="#{asiento.asiento.importeDebe}"
												styleClass="bordeceldainferior" maxlength="12" size="12"
												style="width: 90px" onfocus="encender(this);"
												onblur="apagar(this);">
											</h:inputText>
										</x:column>

										<x:column>
											<f:facet name="header">
												<h:outputText value="Haber" styleClass="texto" />
											</f:facet>
											<h:inputText id="Haber"
												onkeypress="return soloDecimales(this,event);"
												onchange="javascript:resetearText(this, 'Debe');"
												value="#{asiento.asiento.importeHaber}"
												styleClass="bordeceldainferior" maxlength="12" size="12"
												style="width: 90px" onfocus="encender(this);"
												onblur="apagar(this);">
											</h:inputText>
										</x:column>

										<x:column>
											<f:facet name="header">
												<h:outputText value="Leyenda" styleClass="texto" />
											</f:facet>
											<h:inputText id="Leyenda" value="#{asiento.leyenda}"
												styleClass="bordeceldatext" maxlength="100" size="100"
												style="width: 200px" onfocus="encender(this);"
												onblur="apagar(this);">
											</h:inputText>
										</x:column>

										<x:column>
											<f:facet name="header"></f:facet>
											<x:commandLink action="#{ComprobanteBean.eliminarAsiento}"
												id="eliminarAsientoLink">
												<f:param id="codigoAsiento" name="codigoAsiento"
													value="#{asiento.asiento.nroImputa}" />
												<x:graphicImage id="grapRemAsiento" value="/img/borrar.gif"
													title="Eliminar el Asiento." border="0" />
											</x:commandLink>
										</x:column>

									</x:dataTable>

									<h:outputLink value="#" 
										onclick="popup('#{facesContext.externalContext.requestContextPath}/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf',900,500), 'titlebar=no';"
										id="buscarProveedorLink">
										<x:graphicImage value="/img/icon/srch_24.gif"
											title="Buscar Cuenta." border="0" />
									</h:outputLink>

								</h:panelGrid>
							</s:layoutingTitlePane>
							
							  							
  							

							<f:verbatim>
								<hr align="center" width="900">
							</f:verbatim>
							<h:panelGrid id="gridBotonera" columns="10" width="900">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<x:commandButton id="button" value="" action=""
									styleClass="btn btn-primary btn-flat" style="display:none;" />
								<x:commandButton id="buttonIrCtaCte" value="Ir a Cuenta Corriente"
									action="#{ComprobanteBean.volverCtaCte}"
									styleClass="btn btn-primary btn-flat" rendered="#{!ComprobanteBean.btnDes}"/>									

								<h:panelGroup>
									<c:choose>
										<c:when test="${lst:contains(requestScope.permisos,'alta')}">
											<h:commandButton id="buttonGrabar" value="Grabar"
												action="#{ComprobanteBean.validarImpuestos}"
												styleClass="btn btn-primary btn-flat" rendered="#{ComprobanteBean.btnDes}"/>
										</c:when>
										<c:otherwise>
											<h:commandButton id="buttonGrabar" value="Grabar"
												onclick="alert('No posee los permisos necesarios.');"
												styleClass="btn btn-primary btn-flat" rendered="#{ComprobanteBean.btnDes}"/>																				       						 
										</c:otherwise>
									</c:choose>					
								</h:panelGroup>	
									
								<x:commandButton id="buttonCancelar" value="Cancelar"
									action="#{ComprobanteBean.cancelar}" styleClass="btn btn-primary btn-flat" />
							</h:panelGrid>

						</c:if>
						<x:inputHidden id="idFoco" value="#{ComprobanteBean.focoHidden}" forceId="true"/>
						<s:focus id="foco" for="#{ComprobanteBean.focoHidden}" />
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>


	    </x:panelLayout>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ComprobanteBean.inicializar}") + `</li>`;
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
    


    //Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("altaComprobantesForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	var fAux = new Date(year+"-"+month+"-"+date);
	fAux.setHours(fAux.getHours()+3);
	$("#fDesde").datepicker("setDate", fAux);
	
	fd = document.getElementById("altaComprobantesForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	fAux = new Date(year+"-"+month+"-"+date);
	fAux.setHours(fAux.getHours()+3);
	$("#fHasta").datepicker("setDate", fAux);



    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("altaComprobantesForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });
    
    $("#fHasta").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("altaComprobantesForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>
	
	</body>
	
	</html>
</f:view>

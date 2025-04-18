<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{CodComercioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amCodComercioForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		function eliminarArchivo(){
		  // alert ("hola");
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amCodComercioForm');" onload="if('${CodComercioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CodComercioBean.tituloCorto}
    <small>${CodComercioBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

	<h:form id="amCodComercioForm"  enctype="multipart/form-data">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CodComercioBean.popup.mostrar}">
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
					<f:verbatim>
						<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
						   	<tr>
						    	<td width="351" height="47" align="center" class="titulo"> ${CodComercioBean.tituloLargo} <br>
					    	    	<span class="subtitulo"> ${CodComercioBean.tituloCorto} </span></td>
					        	<td width="589" align="right" valign="top" class="fecha" style="color: #FFFFFF;">
					        		<fmt:formatDate value="${ahora}" timeZone="GMT-3" type="both" pattern="EEEE dd 'de' MMMM 'de' yyyy"/> &nbsp;&nbsp;
						        </td>
					        </tr>
					        <tr>
					        	<td height="10" colspan="4"></td>
					        </tr>
						</table>
					</f:verbatim>
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{CodComercioBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{CodBean.popup.nombreIcono}" />
							<h:outputText value="#{CodComercioBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{CodComercioBean.irANuevoCodComercio}" 
								onclick="clickLink('nuevoCodComercio');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{CodComercioBean.irAModificarCodComercio}" 
								onclick="clickLink('modificarCodComercio');dojo.widget.byId('viewDialog').hide();"
								value="Modificar" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{CodComercioBean.irAListarCodComercio}" 
								onclick="clickLink('listarCodComercio');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoCodComercio" action="#{CodComercioBean.irANuevoCodComercio}" forceId="true" style="display: block;"/>
					<x:commandLink id="modificarCodComercio" action="#{CodComercioBean.irAModificarCodComercio}" forceId="true" style="display: none;" />
					<x:commandLink id="listarCodComercio" action="#{CodComercioBean.irAListarCodComercio}" forceId="true" style="display: block;"/>
					
					   <h:panelGrid id="documento" columns="5" align="center">
							<h:outputText id="outNroCuit" value="N�mero de Cuit: " styleClass="texto" />
							<x:inputText id="inNroCuit" forceId="true" value="#{CodComercioBean.empresa.cuit}" disabled="#{CodComercioBean.empresaCargada}" size="20"
								maxlength="11" styleClass="bordeceldainferior" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								<h:commandButton id="botonNuevoEmpresa" value="Nueva Carga" styleClass="botones" action="#{CodComercioBean.habilitarNuevaCarga}" rendered="#{CodComercioBean.empresaCargada}"/>
						    <h:commandButton id="botonCargarEmpresa" value="Habilitar Carga" styleClass="botones" action="#{CodComercioBean.habilitarCarga}" rendered="#{!CodComercioBean.empresaCargada}"/>
						    <h:commandButton id="botonModificarEmpresa" value="Modificar Datos de la Empresa" styleClass="botones" action="#{CodComercioBean.modificarDatosEmpresa}" rendered="#{CodComercioBean.empresaCargada}"/>
						</h:panelGrid>
						<h:panelGrid id="empresainexistente" columns="3" align="center" rendered="#{CodComercioBean.empresaExistente}">
						      <h:outputText value="El cuit ingresado no corresponde a ninguna empresa existente. �Desea darla de alta?." styleClass="texto" style="color:green"/>
						      <h:commandButton action="#{CodComercioBean.darAltaEmpresa}" value="Si" id="si" styleClass="botones" />
						      <h:commandButton action="#{CodComercioBean.cancelarAltaEmpresa}" value="No" id="no" styleClass="botones" />
						</h:panelGrid>      
						<f:verbatim>
							<br>
					    </f:verbatim>
					    
					    <s:fieldset id="datosComercio" legend="Comercio Adherido" rendered="#{CodComercioBean.empresaCargada}">
					         <h:panelGrid id="panComer" columns="4" align="center">
					           <h:outputText value="Raz�n Social: " id="outRS" styleClass="texto"/>
							   <h:outputText value="#{CodComercioBean.empresa.razonSocial}" id="outRazonSocial" styleClass="textoblue"/>
							   <h:outputText value="Rubro: " id="outR" styleClass="texto"/>
							   <h:outputText value="#{CodComercioBean.empresa.rubro.descripcion}" id="outRubro" styleClass="textoblue"/>
					        </h:panelGrid>
					        <s:fieldset legend="Parametros de Liquidacion de la Empresa: " id="paramLiquidacion" >
								    <h:panelGrid columns="3">
									    <h:selectOneMenu id="lstTipoLiq" value="#{CodComercioBean.idTipoLiqSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);" 
										onblur="apagar(this);" style="width: 200px"
										binding="#{CodComercioBean.tipoLiqHtml}" valueChangeListener="#{CodComercioBean.cambioTipoLiquidacion}"
										onchange="submit();">
										<f:selectItems value="#{CodComercioBean.listTipoLiq}" id="listTipoLiq"/>
									   </h:selectOneMenu>
				  			         <h:panelGrid id="panelAFH" columns="2" width="180"  align="center" >
										  <h:outputText value="Imprimir Liquicion: " styleClass="texto" id="outputimprimir" />
										     <h:selectBooleanCheckbox value="#{CodComercioBean.imprimirLiquidacion}" id="imprimir"/>
										  <h:outputText value="Generar PDF:" styleClass="texto" id="outputPDF" />
										     <h:selectBooleanCheckbox value="#{CodComercioBean.generarPDF}" id="pdf" />
										  <h:outputText value="Enviar por Mail: " styleClass="texto" id="outputmail"/>
										     <h:selectBooleanCheckbox value="#{CodComercioBean.enviarMail}" id="mail" />
									 </h:panelGrid>
									   
									   <h:panelGrid id="confCambioLiq" columns="3" align="center" rendered="#{CodComercioBean.confirmaCambioLiq}">
						                  <h:outputText value="Al cambiar el tipo de liquidacion, puede afectar la forma de liquidacion de otros cod Comercio. �Confirma realizar el cambio?." styleClass="texto" style="color:green"/>
						                  <h:commandButton action="#{CodComercioBean.cambioLiquidacion}" value="Si" id="siConf" styleClass="botones" />
						                  <h:commandButton action="#{CodComercioBean.cancelarCambioLiquidacion}" value="No" id="noConf" styleClass="botones" />
					                   </h:panelGrid> 
	  							       <h:outputText value=" " id="outBlanco" rendered="#{!CodComercioBean.confirmaCambioLiq}"/>
					      	       </h:panelGrid> 
					      	       
					      	       
					      	       
					      	       
					      	       	<h:panelGrid id="panel2Cuit" columns="1" style="height: 150"  rendered="#{CodComercioBean.mostrarFmaPagoCuit}">
										<h:panelGrid columns="2" id="panelFormaPagoCuit">
						                <h:outputText value="Forma de Pago CUIT" style="FONT-SIZE: large;" styleClass="texto"/>
			                        
										<f:verbatim>&nbsp;</f:verbatim>
											 <c:if test="${!empty CodComercioBean.tablaDeFormaDePago}">			
											 
											<h:dataTable value="#{CodComercioBean.tablaDeFormaDePago}" id="tablaFormaPagoCuit"
														 styleClass="standardTable"
							                             headerClass="standardTable_Header"  rows="1"
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="forma2" style=" width : 700px;">
						                        <h:column id="nroCuenta">
						                            <f:facet name="header">
						                                <h:outputText value="Nro Cuenta Fondos" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.nroCuentaFondos}"  id="outNroCuenta" />
						                        </h:column> 
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Forma Pago" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.formaPago.descripcion}"  id="outFmaPago"/>
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Orden Cheque" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.ordenCheque }" id="outOrdenCheque"/>
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Banco" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.banco.descripcion}" id="outBanco" />
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Cod. Cuenta Dep." styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.codCuentaDeposito}"  id="outCtaDeposito"/>
						                        </h:column>
					
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="CBU" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma2.comercioFormaPago.cbu}"  id="outCBU"/>
						                        </h:column>
						                        
						                        <h:column>
													<x:commandLink action="#{CodComercioBean.eliminarFormaPagoCuit}" id="eliminarFPLink" disabled="#{CodComercioBean.habilitaQuitar}" >
														<x:graphicImage value="/img/cat_act.gif" title="Eliminar la forma de pago." border="0"/>
													</x:commandLink>
												</h:column>
						                        
						                        
						                        
						    				</h:dataTable>
					                        </c:if>
					                         <c:if test="${empty CodComercioBean.tablaDeFormaDePago}">
								               	<h:outputText value="No existen Formas de Pago por CUIT." styleClass="texto" style="color:green"/>
								        	</c:if>
								        </h:panelGrid>
								          <h:panelGrid id="agreFomaPagoCUIT" columns="5" align="right" width="700">
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <h:commandButton id="botonCargarFormaPago" value="Agregar" styleClass="botones" action="#{CodComercioBean.irAgregarFormaDePago}"   disabled="#{CodComercioBean.habilitaAgregar}" />
			                             </h:panelGrid>
								                   
				                </h:panelGrid>
					      	          
					
					      	</s:fieldset>
								
					        <h:panelGrid id="panParaSucursalesComercio" columns="3" align="center">
						        <h:outputText value="Sucursal: " id="outSuc" styleClass="texto"/>
						        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstSuc" value="#{CodComercioBean.idSucursalSeleccionada}" disabled="#{CodComercioBean.edicion}"
									styleClass="lista" immediate="true" onfocus="encender(this);" 
									onblur="apagar(this);" style="width: 200px"
									binding="#{CodComercioBean.sucursalHtml}" valueChangeListener="#{CodComercioBean.cambioSucursal}"
									onchange="submit();">
									<f:selectItems value="#{CodComercioBean.listSucEmp}" id="itemSuc"/>
								</h:selectOneMenu>
							</h:panelGrid>
							<h:panelGrid id="datosSucurSeleccionada" columns="4" align="center" rendered="#{CodComercioBean.sucursalCargada}">
								<h:outputText value="Direcci�n: " id="outD" styleClass="texto"/>
								<h:outputText value="#{CodComercioBean.sucEmpresa.domicilio.calleNombre} #{CodComercioBean.sucEmpresa.domicilio.calleNumero}"  id="outDireccion" styleClass="textoblue"/>
								<h:outputText value="Barrio: " id="outBarri" styleClass="texto"/>
								<h:outputText value="#{CodComercioBean.sucEmpresa.domicilio.barrio.descripcion}" id="outbarri" styleClass="textoblue"/>
								<h:outputText value="Localidad: " id="outLoca" styleClass="texto"/>
								<h:outputText value="#{CodComercioBean.sucEmpresa.domicilio.localidad.nombre}" id="outloca" styleClass="textoblue"/>
								<h:outputText value="Tel�fono: " id="outT" styleClass="texto"/>
								<h:outputText value="#{CodComercioBean.telefonoSucursal}" id="outTelefono" styleClass="textoblue"/>
							</h:panelGrid>
					    </s:fieldset>
					    <f:verbatim>
							<br>
					    </f:verbatim>
						<s:fieldset legend="C�digo Comercio" id="legPrincipalPanCuatro"  rendered="#{CodComercioBean.sucursalCargada}">
											<h:panelGrid id="panelPrincipalUnoCuatro" columns="6" width="828" align="center">
												
												<h:outputText id="outCodigoPosnet" value="C�digo Posnet:" styleClass="texto"/>
												<h:inputText id="codigoposnetFiltroCuatro" value="#{CodComercioBean.codigoPosnet}"
												styleClass="bordeceldatext" maxlength="13" size="13" onkeypress="return soloEnteros(this,event);"
												style="width: 90px" onfocus="encender(this);" onblur="apagar(this);" disabled="#{CodComercioBean.edicion}"/>
												<h:outputText id="outEstado"  value="Estado:" styleClass="texto"/>
												<h:selectOneMenu id="lstDeEstadosCuatro" value="#{CodComercioBean.codComercio.estado}"
													styleClass="lista" immediate="true" onfocus="encender(this);"
													onblur="apagar(this);" >
													<f:selectItems id="selectItemsEstadoItem" value="#{CodComercioBean.estadoItems}"/>
												</h:selectOneMenu>

												<h:outputText id="outSucursalFiel"  value="Sucursal Fiel: " styleClass="texto"/>
		    			                       <h:selectOneMenu id="lstSucursalesFiel" value="#{CodComercioBean.sucursalFielSeleccionda}" style="width: 300px;"
		        					         styleClass="lista" immediate="true" onfocus="encender(this);"  disabled="true"
		        					         onblur="apagar(this);">
		       					                <f:selectItems id="selectItemSucursalesCod" value="#{CodComercioBean.listaDeSucursalesFiel}"/>
		       			                   	</h:selectOneMenu>
		       			                   	<%-- Codigo vuelto atras por peticion del cliente
		       			                   	<h:outputText id="outExcluyeCargo"  value="Excluye Cargos: " styleClass="texto"/>
												<h:selectBooleanCheckbox id="boolChecExcluyeCargo" value="#{CodComercioBean.excluyeCargos}" />
		       			                   	--%>
		       			                   	
		       			    	<h:outputText id="outDebitoAutomatico" value="Debito Automatico:" styleClass="texto"/>
						    	<h:selectBooleanCheckbox id="boolChecSiEsDebito" value="#{CodComercioBean.debitoAutomatico}" />
						    	
			                       <h:outputText id="outArchivo" value="Adjuntar Archivo"
									styleClass="texto" />
								<h:panelGroup id="panGrup1"  >
									<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" maxlength="1000"  
									value="#{CodComercioBean.imagen}" onfocus="encender(this);" onblur="apagar(this);" disabled="#{CodComercioBean.mostraTablaArchivosAdjuntosDebito}"/>
									
									<h:commandButton value="Adjuntar" action="#{CodComercioBean.saveFile}" 
										styleClass="botones" id="btonAdjuntarTDocLink" disabled="#{CodComercioBean.mostraTablaArchivosAdjuntosDebito}"/>
									</h:panelGroup> 
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<s:fieldset legend="Archivo Adjunto" style="width:300px;" id="fielDeArchisAdj">
									         <h:panelGrid columns="2" id="archAdj" width="300">
												    <h:commandLink value="#{CodComercioBean.urlArchivo}" id="urlDeArchiv" action="#{CodComercioBean.abrirArchivoAdjunto}">
												    </h:commandLink>
												   
													<x:commandLink id="eliminarArchivoAdjuntoLink" onclick="eliminarArchivo();" action="#{CodComercioBean.eliminarArchivoAdjunto}" >
														<x:graphicImage id="grapImag1" value="/img/borrar.gif" title="Eliminar archivo." border="0" />
													</x:commandLink>
											</h:panelGrid>		
									</s:fieldset>	
			                      
			                        
			                        
			                       	<h:outputText id="outPresentaCupones" value="Presenta Cupones:" styleClass="texto"/>
						    	<h:selectBooleanCheckbox id="siPresentaCupon" value="#{CodComercioBean.presentaCupones}" />
			                      
			                        <h:outputText id="outArchivoCupones" value="Adjuntar Archivo"
									styleClass="texto" />
								<h:panelGroup id="panGrup2" >
									<x:inputFileUpload id="fileUpLoad2" storage="file" styleClass="fileUploadInput" maxlength="1000"  
									value="#{CodComercioBean.imagenPresenta}" onfocus="encender(this);" onblur="apagar(this);" disabled="#{CodComercioBean.mostraArchivosAdjuntos}"/>
									<h:commandButton value="Adjuntar" action="#{CodComercioBean.saveFilePresenta}" 
										styleClass="botones" id="btonAdjuntarTDocLink2" disabled="#{CodComercioBean.mostraArchivosAdjuntos}"/>
								 </h:panelGroup>
								 <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<s:fieldset legend="Archivo Adjunto" style="width:300px;" id="segunFieldDeArchiv">
									         <h:panelGrid columns="2" id="archAdj2" width="300">
												    <h:commandLink id="cmdLinkurlArchiPresenta" value="#{CodComercioBean.urlArchivoPresenta}" action="#{CodComercioBean.abrirArchivoAdjuntoPresenta}">
												    </h:commandLink>
												   
													<x:commandLink id="eliminarArchivoAdjuntoLink2"  action="#{CodComercioBean.eliminarArchivoAdjuntoPresenta}" >
														<x:graphicImage id="grapImag2" value="/img/borrar.gif" title="Eliminar archivo." border="0" />
													</x:commandLink>
											</h:panelGrid>		
									</s:fieldset>	
			                     
			                      
			                      
								</h:panelGrid>
                               								
						</s:fieldset>
						<f:verbatim>
							<br>
					    </f:verbatim>
					<x:panelTabbedPane id="tabbedTablas"  align="center" serverSideTabSwitch="false" width="800" style="height: 400">
			                <x:panelTab id="tabComercioLote" label="Individuos asociados">
			                    <h:panelGrid id="panel1" columns="1" style="height: 150">
			                    <h:outputText value="Autorizados" style="FONT-SIZE: large;" styleClass="texto"/>
			                        <c:if test="${!empty CodComercioBean.listaAutorizados}">
			                    	<h:dataTable value="#{CodComercioBean.listaAutorizados}"
										id="tablaDetallesAutorizado" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="tar" style=" width :700px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido y Nombre" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tar.nombre}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Documento" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tar.documento}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Cargo" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tar.cargo}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Telefono" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tar.telefono}"/>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{CodComercioBean.eliminarAutorizado}" id="eliminarAutorizadoLink">
												<f:param id="idAutorizadoElim" name="idAutorizadoElim" value="#{tar.indice}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Autorizado." border="0" id="botonImagenTresTar" />
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
									</c:if>
			                        <c:if test="${empty CodComercioBean.listaAutorizados}">
									<h:outputText value="No existen autorizados." styleClass="texto" style="color:green"/>
									</c:if>
			                    <h:outputText value="Responsables" style="FONT-SIZE: large;" styleClass="texto"/>
			                        <c:if test="${!empty CodComercioBean.listaResponsables}">
			                        <h:dataTable value="#{CodComercioBean.listaResponsables}"
										id="tablaDetallesResponsables" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="tarDos" style=" width :700px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido y Nombre" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tarDos.nombre}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Documento" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tarDos.documento}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Cargo" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tarDos.cargo}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tel�fono" styleClass="texto" />
											</f:facet>
											<h:outputText value="#{tarDos.telefono}"/>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{CodComercioBean.eliminarResponsable}" id="eliminarResponsableLink" >
												<f:param id="idResponsableElim" name="idResponsableElim" value="#{tarDos.indice}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Responsable." border="0" id="botonImagenTresTarDos" />
											</x:commandLink>
										</h:column>
                    				</h:dataTable>
                    				</c:if>
                    				<c:if test="${empty CodComercioBean.listaResponsables}">
									<h:outputText value="No existen responsables." styleClass="texto" style="color:green"/>
									</c:if>
									
									
			                        <h:panelGrid id="agre" columns="5" align="right" width="700">
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <h:commandButton id="botonCargarAut" value="Agregar" styleClass="botones" action="#{CodComercioBean.mostrarPopupDeCarga}" rendered="#{CodComercioBean.sucursalCargada}"/>
			                        </h:panelGrid>
			                         
			                    </h:panelGrid>
					        </x:panelTab>
							<x:panelTab id="tabComercio" label="Medios de Pago">
								<h:panelGrid id="panel2" columns="1" style="height: 150" >
										<h:panelGrid columns="2" id="panelFormaPago" rendered="#{!CodComercioBean.mostrarFmaPagoCuit}">
						                <h:outputText value="Forma de Pago" style="FONT-SIZE: large;" styleClass="texto"/>
			                        
										<f:verbatim>&nbsp;</f:verbatim>
											 <c:if test="${!empty CodComercioBean.tablaDeFormaDePago}">			
											 
											
											
											 
											<h:dataTable value="#{CodComercioBean.tablaDeFormaDePago}" id="tablaFormaPago"
														 styleClass="standardTable"
							                             headerClass="standardTable_Header"  rows="1"
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="forma" style=" width : 700px;">
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Nro Cuenta Fondos" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.nroCuentaFondos}" />
						                        </h:column>								

 												<h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Tipo Cuenta" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.tipoCuentaBanco.descripcionCorta}" />
						                        </h:column>
					                            
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Forma Pago" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.formaPago.descripcion}" />
						                        </h:column>

						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Orden Cheque" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.ordenCheque}" />
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Banco" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.banco.descripcion}" />
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Cod. Cuenta Dep." styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.codCuentaDeposito}" />
						                        </h:column>
					
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="CBU" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.comercioFormaPago.cbu}" />
						                        </h:column>
						                        
						                        <h:column>
													<x:commandLink action="#{CodComercioBean.eliminarFormaPago}" id="eliminarFPLink" disabled="#{CodComercioBean.habilitaQuitar}" >
														<f:param id="idComercioFormapago" name="idComercioFormapago" value="#{forma.indice}"/>
														<x:graphicImage value="/img/cat_act.gif" title="Eliminar la forma de pago." border="0"/>
													</x:commandLink>
												</h:column>
											</h:dataTable>
					                        </c:if>
                    			          	<c:if test="${empty CodComercioBean.tablaDeFormaDePago}">
								         	<h:outputText value="No existen Formas de Pago." styleClass="texto" style="color:green"/>
								        	</c:if>
					                       
					
											
										</h:panelGrid>
				                         <h:panelGrid id="agreFomaPago" columns="5" align="right" width="700">
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <h:commandButton id="botonCargarFormaPago" value="Agregar" styleClass="botones" action="#{CodComercioBean.irAgregarFormaDePago}" rendered="#{CodComercioBean.sucursalCargada && !CodComercioBean.mostrarFmaPagoCuit}"  disabled="#{CodComercioBean.habilitaAgregar}" />
			                             </h:panelGrid>
				                         
			                    </h:panelGrid>
					        </x:panelTab>	
							<x:panelTab id="tabTransaccion" label="Lista de Precios">
								<h:panelGrid id="panel3" style="height: 150">
									<h:panelGrid columns="2" id="panelListaPrecio">
						                <h:outputText id="outListaPrecio" value="Lista de Precios: " style="FONT-SIZE: large;" styleClass="texto"/>
										<f:verbatim>&nbsp;</f:verbatim>
											
					                        <c:if test="${!empty CodComercioBean.listaDePrecios}">	
											<h:dataTable value="#{CodComercioBean.listaDePrecios}" id="listDePrecios"
														 styleClass="standardTable"
							                             headerClass="standardTable_Header"
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="elemento" style=" width : 700px;">
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Descripci�n" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{elemento.comercioListaPrecio.listaPrecio.descripcion}" />
						                        </h:column>								
					
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Fecha Vigencia" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{elemento.comercioListaPrecio.fechaCarga}" />
						                        </h:column>
						                        
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="C�digo Posnet" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{elemento.comercioListaPrecio.codigoPosnet}" />
						                        </h:column>

												<h:column>
													<x:commandLink action="#{CodComercioBean.reemplazarListaPrecio}" id="reemplazarListaPrecioLink">
														<f:param id="idComercioListaPrecioReemp" name="idComercioListaPrecioReemp" value="#{elemento.indice}"/>
														<x:graphicImage value="/img/editar.gif" title="Reemplazar la Lista de Precio." border="0"/>
													</x:commandLink>
												</h:column>

						                        <h:column>
													<x:commandLink action="#{CodComercioBean.eliminarListaPrecio}" id="eliminarListaPrecioLink"  
														onclick="if(!confirm('�Desea Eliminar esta lista de Precio?')) return false;" >
														<f:param id="idComercioListaPrecioElim" name="idComercioListaPrecioElim" value="#{elemento.indice}"/>
														<x:graphicImage value="/img/cat_act.gif" title="Eliminar la Lista de Precio." border="0"/>
													</x:commandLink>
												</h:column>
											</h:dataTable>
					                        </c:if>
                    			          	<c:if test="${empty CodComercioBean.listaDePrecios}">
								         	<h:outputText value="No existe ninguna lista de Precios." styleClass="texto" style="color:green"/>
								        	</c:if>
					
					
											
										</h:panelGrid>
										<h:panelGrid id="agreListaPrecios" columns="5" align="right" width="700">
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                               <h:commandButton id="botonCargarLIstasPrecisos" value="Agregar" styleClass="botones" action="#{CodComercioBean.irAgregarListaPrecio}" rendered="#{CodComercioBean.sucursalCargada}"/>
			                            </h:panelGrid>
										
										
			                    </h:panelGrid>
					        </x:panelTab>
					         
                            <x:panelTab id="tabImpuestos" label="Retenciones">
							<%-- GESTIONAR IMPUESTOS --%>							
				              <s:fieldset legend="C�digo Comercio" id="legPrincipalRetenciones"  rendered="#{CodComercioBean.sucursalCargada}">
				               	<h:panelGrid columns="2" id="panelImpuestos" width="800">
						         <h:dataTable value="#{CodComercioBean.tablaDeImpuestos}" id="tablaImpuestosNacionales"
									      styleClass="standardTable"
		                                  headerClass="standardTable_Header"
		                                  footerClass="standardTable_Header"
		                                  rowClasses="standardTable_Row1,standardTable_Row2"
		                                  columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									      var="imp" style=" width : 800px;">
	                               <h:column>
	                                  <f:facet name="header">
	                                    <h:outputText value="Tipo" styleClass="texto" />
	                                  </f:facet>
	                                  <h:outputText value="#{imp.tipoImpuesto.descripcion}" />
	                               </h:column>
	                              <h:column>
                                     <f:facet name="header">
	                                    <h:outputText value="Categoria" styleClass="texto" />
	                                 </f:facet>
								 	<h:selectOneMenu id="lstCategorias" value="#{imp.categoriaSeleccionada}" 
				       					 styleClass="lista" onfocus="encender(this);" valueChangeListener="#{imp.cambio}"
				       					 onblur="apagar(this);" immediate="true" onchange="submit();"
				       					 style=" width : 180px;">
				       					 <f:selectItems value="#{imp.categorias}" id="selectCategorias"/>
				       				</h:selectOneMenu>		
	                               </h:column>
	                               <h:column>
	                                  <f:facet name="header">
	                                    <h:outputText value="Jurisdicci�n" styleClass="texto" />
	                                  </f:facet>
	                                  <h:selectOneMenu id="lstJurisActi" value="#{imp.jurisSeleccionada}" 
			       					      styleClass="lista" onfocus="encender(this);" immediate="true" onchange="submit();"
			       					      onblur="apagar(this);" style=" width : 180px;" valueChangeListener="#{imp.cambio}">
			       					     <f:selectItems value="#{imp.jurisdicciones}" id="selectjuris"/>
			       			         </h:selectOneMenu>	
	                               </h:column>
	                               <h:column>
	                                   <f:facet name="header">
	                                     <h:outputText value="Actividad" styleClass="texto" />
	                                   </f:facet>
	                                   <x:commandButton id="btnBuscarActividades" value="" action="#{imp.buscarActividadesComercio}" 
											title="Buscar Actividades" image="/img/icon/reload_page.png"/>
							           <f:verbatim>&nbsp;</f:verbatim>
	                                   <h:selectOneMenu id="lstActi" value="#{imp.jurisActividadSeleccionada}" 
			       					     styleClass="lista" onfocus="encender(this);" disabled="#{imp.boolActi}"
			       					     onblur="apagar(this);" style=" width : 180px;">
			       				    	 <f:selectItems value="#{imp.jurisActividades}" id="selectActivi"/>
			       			           </h:selectOneMenu>	
	                               </h:column>				                        		                        
					         </h:dataTable>
						<f:verbatim>&nbsp;</f:verbatim>
					</h:panelGrid>
					
					<h:panelGrid columns="3" width="800" id="panComp">
						<h:outputText id="outJurisdiccion" value="Jurisdicci�n: " styleClass="texto"/>
							<h:selectOneMenu id="lstJurisdiccion" value="#{CodComercioBean.jurisdiccionSeleccionada}" binding="#{CodComercioBean.jurisSelecItem}"
		       					 	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
		        				<f:selectItems value="#{CodComercioBean.listaDeJurisdicciones}"/>
		       				</h:selectOneMenu>	
		       			<h:panelGrid columns="3" id="pnlJuris">			                
						<h:outputText value="La Jurisdicci�n debe coincidir con la de Ingresos Brutos" styleClass="texto" style="FONT-STYLE: italic; font: italic;"/>
						<h:outputText value="Nro Inscripci�n DGR" styleClass="texto"/>
						<h:inputText value="#{CodComercioBean.inscripcionDgr}" id="inscripcionDgr"
									 styleClass="bordeceldainferior" style=" width : 110px;" immediate="true"
									 onfocus="encender(this);" onblur="apagar(this);" 
									 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="(Ej: 9011711275)" styleClass="texto" style="FONT-STYLE: italic; font: italic;"/>
						</h:panelGrid>
				        
					</h:panelGrid>
				</s:fieldset>

					       </x:panelTab>
						   
					      <x:panelTab id="tabComercioDoc" label="Documentacion Asociada">
						<h:panelGrid columns="3" id="panelInternoOnce" width="650" align="center">
						
							<h:outputText value="Archivo: " id="outArch" styleClass="texto"/>
							<x:inputFileUpload id="fileUpLoad3" storage="file" styleClass="fileUploadInput" maxlength="1000"
								value="#{CodComercioBean.uploadedFile}" onfocus="encender(this);" onblur="apagar(this);" 
								/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:outputText value="Adjuntar: " id="adjunta1" styleClass="texto"/>							
							<h:commandButton value="Adjuntar" action="#{CodComercioBean.saveFile}" 
								styleClass="botones" id="btonAdjuntarTDocLink"  
								onclick="javascript:setValueId('lstTDocs','idFoco');"/>
						</h:panelGrid>
						<h:panelGrid columns="1" width="650" align="center" id="panelListDocAdj">
						<s:fieldset legend="Lista Documentos Adjuntos" id="listaDocAdj">
							<h:panelGrid columns="1" id="panelAdjuntos" width="650" align="center">
							<h:dataTable value="#{CodComercioBean.listTipoDocumentos}"
								id="tablaDocAdj" styleClass="standardTable" headerClass="dataTable_Header"
								footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="listDocAdj" style=" width : 570px;">
										
																			
									<h:column>
										<f:facet name="header">
											<h:outputText value="Archivo" styleClass="texto" style="font: bold"/>
										</f:facet>
										<h:commandLink value="#{listDocAdj.nombreArchivo}" action="#{CodComercioBean.abrirArchivo}">
										      <f:param id="idArchiAbrir" name="idArchiAbrir" value="#{listDocAdj.idWrappers}"/>
										</h:commandLink>
									</h:column>
										
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha" styleClass="texto" style="font: bold"/>
										</f:facet>
											<h:outputText value="#{listDocAdj.descripcion}" styleClass="texto" style="width: 150px" />
									</h:column>

									<h:column>	
									    <x:commandLink action="#{CodComercioBean.borrarArchivo}" >
										      <f:param id="idArchi" name="idArchi" value="#{listDocAdj.idWrappers}"/>
											  <x:graphicImage value="/img/borrar.gif" title="Eliminar archivo." border="0" id="botonImagenocho"/>
										</x:commandLink>
									</h:column>	
									</h:dataTable>
																
							</h:panelGrid>
						</s:fieldset>
						</h:panelGrid>
					</x:panelTab>

                            								
					</x:panelTabbedPane>
					
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
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{CodComercioBean.grabar}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CodComercioBean.cancelar}" styleClass="botones" />
					</h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{CodComercioBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

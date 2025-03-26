<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html lang="es">
	<head>
	<title><h:outputText
		value="Tarjeta Fiel - Cierre de Cuenta Cliente" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('popupCierreCuentaClienteForm').submit();
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

	<jsp:include page="/inc/includes.jsp"/>

	<body class="skin-blue layout-top-nav"  onbeforeunload="ShowWait('popupCierreCuentaClienteForm');"
		onload="${CierreCuentaClienteBean.popupReport}; 
		if('${CierreCuentaClienteBean.popup.mostrar}'=='true' ||
			'${LiquidarSaldo0Bean.popup.mostrar}'=='true' ) {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}" >


<!-- wrapper -->
<div class="wrapper">

 <!-- Main content -->
<div class="content-wrapper">

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CierreCuentaClienteBean.tituloCorto}
    <small>${CierreCuentaClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
	</div>
	
	<center><secure:check /> 
	<h:form	id="popupCierreCuentaClienteForm" enctype="multipart/form-data">
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
						<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{CierreCuentaClienteBean.tituloCorto}">
					    	<h:panelGrid columns="2" width="500">
						    	<x:graphicImage value="/img/#{CierreCuentaClienteBean.popup.nombreIcono}" />
						        <h:outputText value=" #{CierreCuentaClienteBean.popup.mensaje}" styleClass="texto"/>
					        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					        	<h:panelGrid columns="2" width="300">
					        		<x:commandButton action="#{CierreCuentaClienteBean.irASalir}" 
					        		 	onclick="clickLink('listoCierre');dojo.widget.byId('viewDialog').hide();window.close();"
					        		 	value="Aceptar" styleClass="btn btn-primary btn-flat"
					        		 	rendered="#{CierreCuentaClienteBean.origen}"/>
									<x:commandButton action="#{CierreCuentaClienteBean.generarComprobante}" 
					        		 	onclick="clickLink('listoCierreComprobante');dojo.widget.byId('viewDialog').hide()"
					        		 	value="Generar Comprobante" styleClass="btn btn-primary btn-flat"
					        		 	rendered="#{CierreCuentaClienteBean.origen}"/>
					        		<x:commandButton action="#{CierreCuentaClienteBean.irAContinuar}" 
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="btn btn-primary btn-flat" title="Continuar en la pantalla."
										rendered="#{!CierreCuentaClienteBean.origen}"/>
					        		
					        	</h:panelGrid>					        		 
							</h:panelGrid>
							
						</s:modalDialog>
						<x:commandLink id="listoCierre" action="#{CierreCuentaClienteBean.irASalir}" forceId="true" style="display: block;"/>
						<x:commandLink id="listoCierreComprobante" action="#{CierreCuentaClienteBean.generarComprobante}" forceId="true" style="display: block;"/>
						<x:commandLink id="continuar" action="#{CierreCuentaClienteBean.irAContinuar}" forceId="true" style="display: block;"/>						
						<x:dataTable id="mensajesError" var="error" value="#{CierreCuentaClienteBean.mensajesError}">
							<h:column>
								<h:outputText value="#{error}" style="font-size: 10px;color: red"/>
							</h:column>	
						</x:dataTable>
						<h:panelGroup id="PanelDatos" rendered="#{CierreCuentaClienteBean.mostrarDatos}">
							<s:fieldset legend="Datos del Titular" id="primerField" >
								<h:panelGrid id="panelTitular" columns="2" width="900" align="center">
									<h:outputText value="Numero de Cuenta:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.nroCuenta}" styleClass="texto" />
								
									<h:outputText value="Apellido y Nombre:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.titular.nombreCliente}" styleClass="texto" />
									
									<h:outputText value="Tipo y Nro Documento:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.documento}" styleClass="texto" />
									
									<h:outputText value="Plastico:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.plasticosTitular}" styleClass="texto" />								
								</h:panelGrid>
							</s:fieldset> 
							<s:fieldset legend="Datos de la  Cuenta del Titular" id="segundoField" >
								<h:panelGrid id="panelCuenta" columns="2" width="900" align="center">
									<h:outputText value="Saldo Actual:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.saldo}"  style="#{CierreCuentaClienteBean.colorSaldo}" styleClass="texto"/>
							
									<h:outputText value="Estado Cliente:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.titular.estadoCliente.descripcion}"  style="#{CierreCuentaClienteBean.colorEstadoCliente}" styleClass="texto" />
									
									<h:outputText value="Estado Cobranza:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaClienteBean.titular.estadoCobranza.descripcion}" style="#{CierreCuentaClienteBean.colorEstadoCobranza}" styleClass="texto"  />
									
								</h:panelGrid>
							</s:fieldset>
							 
							<s:fieldset legend="Adicionales" id="tercerField">
								<x:dataTable value="#{CierreCuentaClienteBean.adicionales}" id="tablaAdicionales"
											 styleClass="standardTable"
                    		       			 headerClass="standardTable_Header"
                           					 footerClass="standardTable_Header"
		                           			 rowClasses="standardTable_Row1,standardTable_Row2"
		                           			 sortable="true"
        		                   			 columnClasses="standardTable_Column"							             
								             var="adicional" style=" width : 890px;">
			                        <x:column style="width : 220px;">
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Apellido y Nombre" styleClass="texto" />
	                    		        </f:facet>
		                                <h:outputText value="#{adicional.apellido}" styleClass="texto" />
	    		                    </x:column>
			                        <x:column >
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Tipo Documento" styleClass="texto" />
	                    		        </f:facet>
			                            <h:outputText value="#{adicional.tipoDocumento}" styleClass="texto" />  
	    		                    </x:column>
	    		                    <x:column >
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Nro Documento" styleClass="texto" />
	                    		        </f:facet>
			                            <h:outputText value="#{adicional.nroDocumento}" styleClass="texto" />  
	    		                    </x:column>
	    		                    <x:column >
	    		                        <f:facet name="header">
	            		                    <h:outputText value="Datos Plastico" styleClass="texto" />
	                    		        </f:facet>
			                            <h:outputText value="#{adicional.dataPlastico}" styleClass="texto" />  
	    		                    </x:column>
	    		            	</x:dataTable>
							</s:fieldset>

							<s:fieldset legend="Formulario de Cierre" id="cuartoField" rendered="#{CierreCuentaClienteBean.mostrarFormulario}">
								<h:panelGrid id="panelForm" columns="2" width="100" align="left">
									<h:outputText value="(*)Motivo de Cierre:" styleClass="texto"/>

									<h:panelGroup id="panMot" >
										<h:selectOneMenu id="lstMotivos" value="#{CierreCuentaClienteBean.idMotivoCierre}" immediate="true"
	       					 					styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
	       					 					valueChangeListener="#{CierreCuentaClienteBean.cambiarLugar}" binding="#{CierreCuentaClienteBean.motivoHtml}"
												onchange="submit()">
		       								<f:selectItems value="#{CierreCuentaClienteBean.listaMotivosItem}"/>
					       				</h:selectOneMenu>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="Lugar Plastico:" styleClass="texto"/>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="#{CierreCuentaClienteBean.descripcionLugar}" style="COLOR: blue" styleClass="texto"/>
					       			</h:panelGroup>	
									<h:outputText value="Observacion:" styleClass="texto"/>
									<x:inputTextarea id="obs" style="width : 700px; height : 70px;" 
											value="#{CierreCuentaClienteBean.cierreCuentaCliente.observacion}" styleClass="background:colorSuave"
					                		onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText id="outAdjunto" value="Adjuntos:" styleClass="texto"/>
									<h:panelGroup id="panAdj" >
										<x:inputFileUpload id="fileUpLoad123" storage="file" disabled="false" styleClass="fileUploadInput" maxlength="1000"  
											value="#{CierreCuentaClienteBean.imagen}" onfocus="encender(this);" onblur="apagar(this);"  />
										<h:commandButton value="Adjuntar" action="#{CierreCuentaClienteBean.guardarAdjunto}" 
											styleClass="btn btn-primary btn-flat" id="btonAdjuntarTDocLink2" />
								 	</h:panelGroup>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									
									<x:dataTable value="#{CierreCuentaClienteBean.listAdjuntos}" id="varAdjuntos"
												 styleClass="standardTable"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="adj" style=" width : 700px;">
				                        <x:column style="width : 220px;" sortable="true">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Lista de Archivos Adjuntados" styleClass="texto" />
		                    		        </f:facet>
		                    		        <h:commandLink  value="#{adj.truncName}" id="urlDeArchiv" action="#{adj.abrirAdjunto}">
		                    		        </h:commandLink>
			                                
		    		                    </x:column>
				                        <x:column style="width :5px;" >
		    		                        <f:facet name="header" >
		            		                    <h:outputText value="" styleClass="texto" />
		                    		        </f:facet>
		                    		        <x:commandLink action="#{adj.eliminar}" id="vdsLink">
												<x:graphicImage value="/img/borrar.gif" title="Eliminar Adjunto." border="0" 
													onclick="return confirm('¿Confirma  la eliminacion del adjunto?');" />
											</x:commandLink>
		    		                    </x:column>
		    		                </x:dataTable>				
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:commandButton id="buttonGuardarFormulario" value="Guardar Formulario" 
											action="#{CierreCuentaClienteBean.guardarFormulario}"  styleClass="btn btn-primary btn-flat"
											onclick="return confirm('¿Confirma que desea guardar el formulario?');"/>
									<h:commandButton id="buttonGeneracionLibreDeuda" value="Generar Certificado de Libre Deuda" action="#{CierreCuentaClienteBean.generarCertificado}"  styleClass="botones"/>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="(*)campos obligatorios" styleClass="texto"/>		
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
							</s:fieldset>  
							<h:panelGrid id="panelBotones" columns="2" width="100" align="right">
								<h:commandButton id="buttonConfirmar" value="Confirmar Cierre Cuenta" action="#{CierreCuentaClienteBean.ConfirmarCierre}" 
								rendered="#{CierreCuentaClienteBean.botonConfirmarVisible}"   styleClass="btn btn-primary btn-flat"/>
								<h:commandButton id="buttonComprobanteCierreCuenta" value="Generar Comprobante del Cierre de Cuenta" 
												action="#{CierreCuentaClienteBean.generarComprobante}"  styleClass="btn btn-primary btn-flat"
												rendered="#{CierreCuentaClienteBean.botonComprobanteVisible}"/>
								<h:commandButton id="buttonLiquidar" value="Liquidar" action="#{CierreCuentaClienteBean.irALiquidar}"  styleClass="btn btn-primary btn-flat" rendered="#{CierreCuentaClienteBean.mostrarItems}"/>
								<h:commandButton id="buttonCancelar" value="Cerrar" action="#{CierreCuentaClienteBean.cancelar}"  styleClass="btn btn-primary btn-flat"/>
									
							</h:panelGrid>  
						</h:panelGroup>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>
	</h:form>
	
</center>


<%@include file="/inc/footer_popup.jsp" %>


</body>
</html>
</f:view>





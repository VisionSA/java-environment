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
		onload="${CierreCuentaSaldoBean.popupReport}; 
		if('${CierreCuentaSaldoBean.popup.mostrar}'=='true' ||
			'${LiquidarSaldo0Bean.popup.mostrar}'=='true' ) {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}" >


<!-- wrapper -->
<div class="wrapper">

 <!-- Main content -->
<div class="content-wrapper">

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CierreCuentaSaldoBean.tituloCorto}
    <small>${CierreCuentaSaldoBean.tituloLargo}</small>
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
			                   dialogTitle="#{CierreCuentaSaldoBean.tituloCorto}">
					    	<h:panelGrid columns="2" width="500">
						    	<x:graphicImage value="/img/#{CierreCuentaSaldoBean.popup.nombreIcono}" />
						        <h:outputText value=" #{CierreCuentaSaldoBean.popup.mensaje}" styleClass="texto"/>
					        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					        	<h:panelGrid columns="2" width="300">
					        		<x:commandButton action="#{CierreCuentaSaldoBean.irASalir}" 
					        		 	onclick="clickLink('listoCierre');dojo.widget.byId('viewDialog').hide();window.close();"
					        		 	value="Aceptar" styleClass="btn btn-primary btn-flat"
					        		 	rendered="#{CierreCuentaSaldoBean.origen}"/>
									<x:commandButton action="#{CierreCuentaSaldoBean.generarComprobante}" 
					        		 	onclick="clickLink('listoCierreComprobante');dojo.widget.byId('viewDialog').hide()"
					        		 	value="Generar Comprobante" styleClass="btn btn-primary btn-flat"
					        		 	rendered="#{CierreCuentaSaldoBean.origen}"/>
					        		<x:commandButton action="#{CierreCuentaSaldoBean.irAContinuar}" 
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="btn btn-primary btn-flat" title="Continuar en la pantalla."
										rendered="#{!CierreCuentaSaldoBean.origen}"/>
					        		
					        	</h:panelGrid>					        		 
							</h:panelGrid>
							
						</s:modalDialog>
						<x:commandLink id="listoCierre" action="#{CierreCuentaSaldoBean.irASalir}" forceId="true" style="display: block;"/>
						<x:commandLink id="listoCierreComprobante" action="#{CierreCuentaSaldoBean.generarComprobante}" forceId="true" style="display: block;"/>
						<x:commandLink id="continuar" action="#{CierreCuentaSaldoBean.irAContinuar}" forceId="true" style="display: block;"/>						
						<x:dataTable id="mensajesError" var="error" value="#{CierreCuentaSaldoBean.mensajesError}">
							<h:column>
								<h:outputText value="#{error}" style="font-size: 10px;color: red"/>
							</h:column>	
						</x:dataTable>
						<h:panelGroup id="PanelDatos" rendered="#{CierreCuentaSaldoBean.mostrarDatos}">
							<s:fieldset legend="Datos del Titular" id="primerField" >
								<h:panelGrid id="panelTitular" columns="2" width="900" align="center">
									<h:outputText value="Numero de Cuenta:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.nroCuenta}" styleClass="texto" />
								
									<h:outputText value="Apellido y Nombre:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.titular.nombreCliente}" styleClass="texto" />
									
									<h:outputText value="Tipo y Nro Documento:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.documento}" styleClass="texto" />
									
									<h:outputText value="Plastico:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.plasticosTitular}" styleClass="texto" />								
								</h:panelGrid>
							</s:fieldset> 
							
							<f:verbatim><br></f:verbatim>
							
							<s:fieldset legend="Datos de la  Cuenta del Titular" id="segundoField" >
								<h:panelGrid id="panelCuenta" columns="2" width="900" align="center">
									<h:outputText value="Saldo Actual:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.saldo}"  style="#{CierreCuentaSaldoBean.colorSaldo}" styleClass="texto"/>
							
									<h:outputText value="Estado Cliente:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.titular.estadoCliente.descripcion}"  style="#{CierreCuentaSaldoBean.colorEstadoCliente}" styleClass="texto" />
									
									<h:outputText value="Estado Cobranza:" styleClass="texto" />
									<h:outputText value="#{CierreCuentaSaldoBean.titular.estadoCobranza.descripcion}" style="#{CierreCuentaSaldoBean.colorEstadoCobranza}" styleClass="texto"  />
									
								</h:panelGrid>
							</s:fieldset>
							
							<f:verbatim><br></f:verbatim>
							 
							<s:fieldset legend="Adicionales" id="tercerField">
								<x:dataTable value="#{CierreCuentaSaldoBean.adicionales}" id="tablaAdicionales"
											 styleClass="table-bordered"
                    		       			 headerClass="standardTable_Header"
                           					 footerClass="standardTable_Header"
		                           			 rowClasses="tdA,tdA,tdB,tdA"
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
							
							<f:verbatim><br></f:verbatim>

							<s:fieldset legend="Formulario de Cierre" id="cuartoField" rendered="#{CierreCuentaSaldoBean.mostrarFormulario}">
								<h:panelGrid id="panelForm" columns="2" width="100" align="left">
									
									<h:outputText value="(*)Motivo de Cierre:" styleClass="texto"/>
									<h:panelGroup id="panMot" >
										<h:selectOneMenu id="lstMotivos" value="#{CierreCuentaSaldoBean.idMotivoCierre}" immediate="true"
	       					 					styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
	       					 					valueChangeListener="#{CierreCuentaSaldoBean.cambiarLugar}" binding="#{CierreCuentaSaldoBean.motivoHtml}"
												onchange="submit()">
		       								<f:selectItems value="#{CierreCuentaSaldoBean.listaMotivosItem}"/>
					       				</h:selectOneMenu>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="Lugar Plastico:" styleClass="texto"/>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       				<h:outputText value="#{CierreCuentaSaldoBean.descripcionLugar}" style="COLOR: blue" styleClass="texto"/>
					       			</h:panelGroup>	
					       			
					       			<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					       			
									<h:outputText value="Observacion:" styleClass="texto"/>
									<x:inputTextarea id="obs" style="width : 700px; height : 70px;" 
											value="#{CierreCuentaSaldoBean.cierreCuentaCliente.observacion}" styleClass="background:colorSuave"
					                		onfocus="encender(this);" onblur="apagar(this);"/>
					                		
		    		                			
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									
									
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									
									
									<h:commandButton id="buttonGuardarFormulario" value="Guardar Formulario" 
											action="#{CierreCuentaSaldoBean.guardarFormulario}"  styleClass="btn btn-primary btn-flat"
											onclick="return confirm('¿Confirma que desea guardar el formulario?');"/>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									
									
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									
									
									<h:outputText value="(*)campos obligatorios" styleClass="texto"/>		
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
							</s:fieldset>  
							
							
							<h:panelGrid id="panelBotones" columns="2" width="100" align="right">
								<h:commandButton id="buttonConfirmar" value="Confirmar Cierre Cuenta" action="#{CierreCuentaSaldoBean.ConfirmarCierre}" 
								rendered="#{CierreCuentaSaldoBean.botonConfirmarVisible}"   styleClass="btn btn-primary btn-flat"/>
								<h:commandButton id="buttonComprobanteCierreCuenta" value="Generar Comprobante del Cierre de Cuenta" 
												action="#{CierreCuentaSaldoBean.generarComprobante}"  styleClass="btn btn-primary btn-flat"
												rendered="#{CierreCuentaSaldoBean.botonComprobanteVisible}"/>
								<h:commandButton id="buttonLiquidar" value="Liquidar" action="#{CierreCuentaSaldoBean.irALiquidar}"  styleClass="btn btn-primary btn-flat" rendered="#{CierreCuentaSaldoBean.mostrarItems}"/>
								<h:commandButton id="buttonCancelar" value="Cerrar" action="#{CierreCuentaSaldoBean.cancelar}"  styleClass="btn btn-primary btn-flat"/>
									
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





<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title>Detalle de calendario de pago ${bindingScheduleHandler.fechaClikeada}</title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/screen.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />  	
  	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/jscript/common.js"></script>  	
</head>

<body onbeforeunload="ShowWait('calendarioPopupForm');"
	  onload="if('${ImputacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<center>
<h:form id="calendarioPopupForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ImputacionBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Detalle del calendario de pago - #{bindingScheduleHandler.fechaClikeada}" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<f:verbatim><br></f:verbatim>
		<f:verbatim><br></f:verbatim>		
		
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="Gestionar Imputaciones">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{ImputacionBean.popup.nombreIcono}" />
					        <h:outputText value=" #{ImputacionBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
				        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{bindingScheduleHandler.cerrarPopupImputaciones}" 
				        		 onclick="dojo.widget.byId('viewDialog').hide();"
				        		 value="Cerrar" styleClass="botones" title="Cerrar el popup de mensaje."/>
						</h:panelGrid>
				        
				</s:modalDialog>		
		
					<h:panelGrid columns="1" id="panelProveedorC" width="700" align="center">
						<s:fieldset id="proveedoresC" legend="Detalle de proveedores">					
						<h:dataTable value="#{bindingScheduleHandler.listaProveedoresPorFecha}" id="proveedorC"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
						             var="proveedorC" style=" width : 600px;">
						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cuit" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{proveedorC.cuit}" />
	                        </h:column>		                        
	                        						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Razon Social" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{proveedorC.razonSocial}" />
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="FNI" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:commandLink id="cLinkFNI" value="$ #{proveedorC.fniTotal}" action="#{bindingScheduleHandler.habilitarFNI}">
									<f:param id="fechaProveedorCFNI" name="fechaProveedorCFNI" value="#{proveedorC.fecha}"/>
									<f:param id="cuitId" name="cuitId" value="#{proveedorC.cuit}"/>
								</h:commandLink>
	                        </h:column>		                        	                        

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="FI" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:commandLink id="cLinkFI" value="$ #{proveedorC.fiTotal}" action="#{bindingScheduleHandler.habilitarFI}">
									<f:param id="fechaProveedorCFI" name="fechaProveedorCFI" value="#{proveedorC.fecha}"/>
									<f:param id="cuitId" name="cuitId" value="#{proveedorC.cuit}"/>									
								</h:commandLink>								
	                        </h:column>		                        
		                        
	                        <h:column>
	                            <f:facet name="header">
		                        	<h:outputText value="OP" styleClass="texto" style="font: bold;color: white;"/>
		                        </f:facet>
								<h:commandLink id="cLinkOP" value="$ #{proveedorC.opTotal}" action="#{bindingScheduleHandler.habilitarOP}">
									<f:param id="fechaProveedorCOP" name="fechaProveedorCOP" value="#{proveedorC.fecha}"/>
									<f:param id="cuitId" name="cuitId" value="#{proveedorC.cuit}"/>									
								</h:commandLink>								
	                        </h:column>		                        		                        		                        
						</h:dataTable>
						</s:fieldset>
				
				<f:verbatim><br></f:verbatim>				
				
				<s:fieldset id="fni" legend="Detalle de facturas no imputadas" rendered="#{bindingScheduleHandler.mostrarFNI}">
						<h:dataTable value="#{bindingScheduleHandler.listaCuotasPorProveedor}" id="fniTable"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
						             var="fni" style=" width : 600px;">
						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Número" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fni.cuota.comprobante.nroCorto}">
									<f:converter converterId="rellenoUno" />
								</h:outputText>									
								<h:outputText value="-"/>
								<h:outputText value="#{fni.cuota.comprobante.nroLargo}">
									<f:converter converterId="rellenoDos" />
								</h:outputText>			
	                        </h:column>		                        
	                        						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Fecha de Vencimiento" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fni.cuota.fechaVencimiento}">
									<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
								</h:outputText>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Impuestos" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fni.cuota.comprobante.totalImpuestos}" />       
	                        </h:column>		                        	                        

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Total" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fni.cuota.importe}" />
	                        </h:column>		                        
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Imputado" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fni.montoImputado}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Sin Imputar" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fni.montoResta}" />
	                        </h:column>	                        
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Contabilizado" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fni.cuota.comprobante.contabilizado}"/>
	                        </h:column>		                        	                        	                        
						</h:dataTable>
				</s:fieldset>	    

				<s:fieldset id="fi" legend="Detalle de facturas imputadas" rendered="#{bindingScheduleHandler.mostrarFI}">
						<h:dataTable value="#{bindingScheduleHandler.listaCuotasPorProveedor}" id="fiTable"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
						             var="fi" style=" width : 600px;">
						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Número" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fi.cuota.comprobante.nroCorto}">
									<f:converter converterId="rellenoUno" />
								</h:outputText>									
								<h:outputText value="-"/>
								<h:outputText value="#{fi.cuota.comprobante.nroLargo}">
									<f:converter converterId="rellenoDos" />
								</h:outputText>																	
	                        </h:column>		                        
	                        						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Fecha de Vencimiento" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fi.cuota.fechaVencimiento}">
									<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
								</h:outputText>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Impuestos" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fi.cuota.comprobante.totalImpuestos}"/>       
	                        </h:column>		                        	                        

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Total" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fi.cuota.importe}"/>
	                        </h:column>	
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Imputado" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fi.montoImputado}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Sin Imputar" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{fi.montoResta}" />
	                        </h:column>	                        	                        
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Contabilizado" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{fi.cuota.comprobante.contabilizado}"/>
	                        </h:column>		                        	                        	                        	                        
						</h:dataTable>
				</s:fieldset>	    				
				
				<s:fieldset id="op" legend="Detalle de ordenes de pago a cuenta" rendered="#{bindingScheduleHandler.mostrarOP}">
						<h:dataTable value="#{bindingScheduleHandler.listaCuotasPorProveedor}" id="opTable"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							             
						             var="op" style=" width : 600px;">
						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Número" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{op.cuota.comprobante.nroCorto}">
									<f:converter converterId="rellenoUno" />
								</h:outputText>									
								<h:outputText value="-"/>
								<h:outputText value="#{op.cuota.comprobante.nroLargo}">
									<f:converter converterId="rellenoDos" />
								</h:outputText>																	
	                        </h:column>		                        
	                        						             
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Fecha de Vencimiento" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{op.cuota.fechaVencimiento}">
									<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
								</h:outputText>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Total" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{op.cuota.importe}"/>
	                        </h:column>		                        
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Imputa" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{op.montoImputado}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="A Cuenta" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="$ #{op.montoResta}" />
	                        </h:column>	                        
	                        	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Contabilizado" styleClass="texto" style="font: bold;color: white;"/>
	                            </f:facet>
								<h:outputText value="#{op.cuota.comprobante.contabilizado}"/>
	                        </h:column>
						</h:dataTable>
				</s:fieldset>
				
				<c:if test="${bindingScheduleHandler.mostrarFNI || bindingScheduleHandler.mostrarFI || bindingScheduleHandler.mostrarOP}">
					<h:panelGrid columns="10" width="700" id="panelBotoneraVerOcultarImputaciones">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
						<x:commandButton id="verImputacion" value="Ver Imputaciones" action="#{bindingScheduleHandler.verImputaciones}" styleClass="botones"  rendered="#{!bindingScheduleHandler.mostrarImputaciones}"/>
						<x:commandButton id="ocultarImputacion" value="Ocultar Imputaciones" action="#{bindingScheduleHandler.ocultarImputaciones}" styleClass="botones"  rendered="#{bindingScheduleHandler.mostrarImputaciones}"/>						
					</h:panelGrid>									
				</c:if>
				
				
				<h:panelGroup rendered="#{bindingScheduleHandler.mostrarImputaciones}">
					<f:verbatim><br></f:verbatim>
					<%-- ////////// LLAMADA A LA PAGINA DE IMPUTACIONES ////////// --%>	
			        <%@include file="/tarjetafiel/proveedores/imputaciones/imputacionesInclude.jsp" %>					
					<%-- ///////////////////////////////////////////////////////// --%>						
				</h:panelGroup>
				
				<f:verbatim><hr align="center" width="700"></f:verbatim>
				<h:panelGrid columns="10" width="700" id="panelBotoneraCalendario">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<c:if test="${!empty ImputacionBean.imputaciones && bindingScheduleHandler.mostrarImputaciones}">
	               		<x:commandButton id="buttonGrabarImputaciones" value="Guardar" action="#{bindingScheduleHandler.grabarImputacion}" styleClass="botones"/>
	               	</c:if>
					<x:commandButton id="buttonCerrarPopup" value="Cerrar" action="#" styleClass="botones" onclick="window.close();"/>
				</h:panelGrid>
			</h:panelGrid>
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>

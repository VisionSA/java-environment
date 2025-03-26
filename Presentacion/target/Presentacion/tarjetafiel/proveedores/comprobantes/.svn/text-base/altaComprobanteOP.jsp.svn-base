<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="#{OrdenPagoBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaComprobantesOPForm').submit();
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

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaComprobantesOPForm');"
		 style="body" onload="${OrdenPagoBean.popupReport} ;
		 						if('${OrdenPagoBean.verRetenciones}'=='true') {verRetenciones.show();}
									 else {if('${OrdenPagoBean.popup.mostrar}'=='true') {viewDialog.show();} 
								 			else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${OrdenPagoBean.tituloCorto}
    <small>${OrdenPagoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

<secure:check/>

<h:form id="altaComprobantesOPForm">
	
	<%-- GRABA EL ESTADO DEL SCROLL --%>   
	<h:panelGroup rendered="#{!OrdenPagoBean.verPopup}">
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
            	
            	<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%> 					
				<s:modalDialog dialogId="viewDialog" id="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{OrdenPagoBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{OrdenPagoBean.popup.nombreIcono}" />
					        <h:outputText value=" #{OrdenPagoBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{OrdenPagoBean.irANuevaOP}" 
				        		 onclick="clickLink('nuevoOP');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva orden de pago."/>

				        	<x:commandButton action="#{OrdenPagoBean.imprimirOP}" 
				        		 onclick="clickLink('imprimirOP');dojo.widget.byId('viewDialog').hide();"
				         		 value="Imprimir" styleClass="btn btn-primary btn-flat" title="Imprimir la orden de pasgo."/>
							
							<x:commandButton action="#{OrdenPagoBean.irAListarOP}" 
								 onclick="clickLink('listarOP');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado de ordenes de pago."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoOP" action="#{OrdenPagoBean.irANuevaOP}" forceId="true" style="display: block;"/>
				<x:commandLink id="imprimirOP" action="#{OrdenPagoBean.imprimirOP}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarOP" action="#{OrdenPagoBean.irAListarOP}" forceId="true" style="display: block;"/>
				
		<h:panelGrid id="centrador" columns="1" align="center">
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="verErrores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>
				
            	
         <c:if test="${empty OrdenPagoBean.proveedor}" >				
			<s:layoutingTitlePane id="altaComprobantes" label="Alta de ordenes de pago" 
						      		containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
				<f:verbatim><br></f:verbatim>
				            	
             	<h:panelGrid id="panelPrincipalUno" columns="5" width="320" align="center"> 
             		<h:outputText id="outCuit" value="CUIT del Proveedor: " styleClass="texto"/>
             		<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					<x:inputText id="inCuit" forceId="true"  value="#{OrdenPagoBean.cuit}" 
								 size="11" maxlength="11" styleClass="bordeceldainferior" 
								 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
								 onkeypress="return soloEnteros(this,event);" />
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	        		<x:commandLink id="buscarProveedorLink" action="#{OrdenPagoBean.buscarProveedorPopup}">
								<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
					</x:commandLink>
					
				</h:panelGrid>
					
				<f:verbatim><br></f:verbatim>
					
							<h:panelGroup>
									<c:choose>
										<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
											<h:commandButton value="Continuar" 
												action="#{OrdenPagoBean.crearOrdenPago}" styleClass="btn btn-primary btn-flat pull-right" id="btnUno"/>	
											</c:when>
										<c:otherwise>	
										<h:commandButton value="Continuar" 
											onclick="alert('No posee los permisos necesarios.');" styleClass="btn btn-primary btn-flat pull-right" id="btnDos"/>								       						 
										</c:otherwise>
									</c:choose>					
							</h:panelGroup>																				
				
 			</s:layoutingTitlePane>
         </c:if>
             	
             	
           	<c:if test="${!empty OrdenPagoBean.proveedor}">
             	<h:panelGrid id="gridPrincipal" columns="4" width="900">
             		
             		<h:outputText id="outTipoComp" value="Tipo Comprobante: " styleClass="texto"/>
	                <h:outputText id="TipoCompLargo" value="#{OrdenPagoBean.tipoComprobante.descripcionLarga}" styleClass="textoblue"  />
             		
	                <h:outputText id="outNomProveedor" value="Proveedor: " styleClass="texto"/>
	                <h:outputText id="NomProveedor" value="#{OrdenPagoBean.proveedor.razonSocial}" styleClass="textoblue" />
										
					<h:outputText id="outCuitVer" value="CUIT: " styleClass="texto"/>
					<h:panelGroup id="panelCuit2" >
						<h:outputText id="Ident" value="#{OrdenPagoBean.cuitIdentificador}" styleClass="textoblue" />
	    	           	<h:outputText id="separador_1" value="-" styleClass="texto"/>
						<h:outputText id="Dni" value="#{OrdenPagoBean.cuitDni}" styleClass="textoblue" />
						<h:outputText id="separador_2" value="-" styleClass="texto"/>
						<h:outputText id="Verif" value="#{OrdenPagoBean.cuitVerificador}" styleClass="textoblue"/>
					</h:panelGroup>
	                
					<h:outputText id="outDirProveedor" value="Dirección: " styleClass="texto"/>
	                <h:outputText id="DirProveedor" value="#{OrdenPagoBean.direccionProveedor}" styleClass="textoblue" />

	                <%--h:panelGroup id="groupNro" >	                
					<h:outputText id="outNroOP" value="Nro. Orden de pago: " styleClass="texto"/>
		                <h:outputText id="NroCorto" value="#{OrdenPagoBean.nroCortoString}" styleClass="textoblue" rendered="false"/>
   		                <h:outputText id="out-Nro1" value=" - " styleClass="texto" rendered="false"/>
				 		<h:outputText id="NroLargo" value="#{OrdenPagoBean.nroLargoString}" styleClass="textoblue"/>
					</h:panelGroup--%>
					
	                <h:outputText id="outFechaEmision" value="Fecha de Emisión:" styleClass="texto" style="padding-right:5px"/>

					<f:verbatim>
						                <div class="input-group date">
						                    <div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
						                    <input type="text" class="form-control pull-right" id="fDesde" autocomplete="off">
						                </div>
					</f:verbatim>
							    
		           	<h:outputText id="outFechaPago" value="Fecha de Pago:" styleClass="texto" style="padding-right:5px"/>

					<f:verbatim>
						                <div class="input-group date">
						                    <div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
						                    <input type="text" class="form-control pull-right" id="fHasta" autocomplete="off">
						                </div>
					</f:verbatim>
								
								<h:inputText id="FechaDesde" value="#{OrdenPagoBean.fechaEmision}" style="display: none;" >
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText> 
								<h:inputText id="FechaHasta" value="#{OrdenPagoBean.fechaPago}" style="display: none;" >
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText>
							    
							    <f:verbatim>&nbsp;</f:verbatim>
							    <f:verbatim>&nbsp;</f:verbatim>

				</h:panelGrid>
				
				<c:if test="${empty OrdenPagoBean.tablaImputaciones}"> 
					<h:outputText value="No tiene comprobantes sin imputar." styleClass="textoblue" id="textoUno"/>
				</c:if>
				
				
 				<c:if test="${!empty OrdenPagoBean.tablaImputaciones}">  
				<x:dataTable id="listado" 
                         styleClass="standardTable"
                         headerClass="dataTable_Header"
                         footerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column,
                         			standardTable_ColumnRight,standardTable_ColumnRight,standardTable_ColumnRight,standardTable_ColumnRight"
                         sortable="true"
                         var="imputacion" 
                         value="#{OrdenPagoBean.tablaImputaciones}"
                         preserveDataModel="false"
                         style=" width :900px;">
                         
                                                 
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tipo" id="tipoUno"/>
                            </f:facet>
                            <h:outputText style=" width :100px; " value="#{imputacion.cuota.comprobante.tipoComprobante.descripcionLarga}" id="tipouno"/>
                        </h:column>
                   
                      	<x:column>
                            <f:facet name="header">
                                <h:outputText value="Número" id="numero"/>
                            </f:facet>
                            <h:outputText value="#{imputacion.nroCortoCuota}" id="OutNumeroUno"/>
                            <h:outputText value="-" id="OutNumeroDos"/>
                            <h:outputText value="#{imputacion.nroLargoCuota}" id="OutNumeroTres"/>
                        </x:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha" id="fecha"/>
                            </f:facet>
                            <h:outputText value="#{imputacion.cuota.comprobante.fechaEmision}" id="OutFecha"/>
                        </h:column>
                                                
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Monto" id="monto"/>
                            </f:facet>
                            <h:outputText value="#{imputacion.importeCuota}" id="OutMonto"/>
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Imputado" id="imputado"/>
                            </f:facet>
                            <h:outputText value="#{imputacion.totalImputado}" id="OutImputado"/>
                        </h:column>
                                                
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Restante a Imputar" id="restaImputar"/>
                            </f:facet>
                            <h:outputText value="#{imputacion.montoRestante}" id="OutRestaImputar"/>
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Monto a Imputar" id="montoImputar"/>
                            </f:facet>
                            <h:inputText id="MontoImp" value="#{imputacion.montoAImputar}" 
	        		        			 styleClass="bordeceldainferior" 
	   	            					 maxlength="11" size="11" style="width: 90px" 
	   	            					 onfocus="encender(this);" onblur="apagar(this);" 
				               			 onkeypress="return soloDecimales(this,event);">
	   	            		</h:inputText>
                        </h:column>
                        
                        <h:column>
                           	<f:facet name="header">
                           	</f:facet>
                           	<h:selectBooleanCheckbox value="#{imputacion.seleccionado}"
                           	onclick="recargar();" id="imputacionSeleccionada"/>
                       	</h:column>
                </x:dataTable>        
            </c:if>
                
                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
      	
                <h:panelGrid id="panelTotales" columns="2" align="right" width="415">
                		<h:outputText value="Pago a Cuenta: " id="pagoCuenta"/>
                		<h:inputText id="PagoCuenta" value="#{OrdenPagoBean.pagoACuenta}" 
	        		        		styleClass="bordeceldainferior" 
	   	            				maxlength="10" size="10" style="width: 90px"
	   	            				onchange="recargar();"
	   	            				onfocus="encender(this);" onblur="apagar(this);" 
				               		onkeypress="return soloDecimales(this,event);">
   	            		</h:inputText>
   	            		
   	            		<h:outputText value="Importe Total: " id="neto"/>
                		<h:outputText value="#{OrdenPagoBean.importeNeto}" id="OutNeto"/>

                		<h:outputText value="Retenciones: " id="retencion"/>   	            		
   	            		<h:panelGroup>
                			<h:outputText value="#{OrdenPagoBean.totalImpuestos}" id="OutRetencion"/>
                			<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                			<h:commandButton id="verRetencionesButton" action="#{OrdenPagoBean.verRetencionesPopup}" 
								image="/img/icon/text_rich_colored 24.png" 
								title="Ver detalle de retenciones." style=" width : 18px; height : 18px;">
							</h:commandButton>
                		</h:panelGroup>
                		<h:outputText value="Total a Pagar: " id="totalAPagar"/> 
                		<h:outputText value="#{OrdenPagoBean.importeTotal}" id="OutTotalAPagar"/>
                	</h:panelGrid>
                	
                	<h:panelGrid columns="2" id="panelFormaPago" width="900">
	                <h:outputText id="outFormaPago" value="Forma de Pago: " styleClass="texto"/>
					<f:verbatim>&nbsp;</f:verbatim>
												
						<h:dataTable value="#{OrdenPagoBean.tablaDeFormaDePago}" id="tablaFormaPago"
									 styleClass="standardTable"
		                             headerClass="standardTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 var="obj" style=" width : 900px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Nro Cuenta Fondos" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.nroCuentaFondos}" />
	                        </h:column>								

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Forma Pago" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.formaPago.descripcion}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Orden Cheque" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.ordenCheque}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Banco" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.banco.descripcion}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cod. Cuenta Dep." styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.codCtaDeposito}" />
	                        </h:column>

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="CBU" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{obj.formaPago.cbu}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header"/>
	                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" />
	                        </h:column>  
						</h:dataTable>

					</h:panelGrid>

                         	
	        	<f:verbatim><hr align="center" width="900"></f:verbatim>
				<h:panelGrid id="gridBotonera" columns="10" width="900">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					<x:commandButton id="button" value="" action="" styleClass="btn btn-primary btn-flat" style="display:none;"/>
					
						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'alta')}">
									<h:commandButton id="buttonGrabar" value="Grabar" 	
										action="#{OrdenPagoBean.grabarOP}" styleClass="btn btn-primary btn-flat"/>
								</c:when>
								<c:otherwise>
									<h:commandButton id="buttonGrabar2" value="Grabar" 	
										onclick="alert('No posee los permisos necesarios.');" styleClass="btn btn-primary btn-flat"/>																															       						 
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>
					
					<x:commandButton id="buttonCancelar" value="Cancelar" action="#{OrdenPagoBean.cancelar}" styleClass="btn btn-primary btn-flat"/>
				</h:panelGrid>
				
			</c:if>
		
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{OrdenPagoBean.inicializar}") + `</li>`;
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
	var fd = document.getElementById("altaComprobantesOPForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	var fAux = new Date(year+"-"+month+"-"+date);
	fAux.setHours(fAux.getHours()+3);
	$("#fDesde").datepicker("setDate", fAux);
	
	fd = document.getElementById("altaComprobantesOPForm:FechaHasta").value.split(" ")[0].split("/");
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
        document.getElementById("altaComprobantesOPForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });
    
    $("#fHasta").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("altaComprobantesOPForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>

</body>
</html>
</f:view>
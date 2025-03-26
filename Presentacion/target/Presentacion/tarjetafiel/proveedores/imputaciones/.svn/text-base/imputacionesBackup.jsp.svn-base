<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="Tarjeta Fiel - Gestionar Imputaciones"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');"
	  onload="if('${ImputacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ImputacionBean.tituloCorto}
    <small>${ImputacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
<h:form id="generalForm"> 						

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ImputacionBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>
        	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter">

        <f:facet name="body">
            <h:panelGroup id="body">

			<h:panelGrid columns="1" align="center" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
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
				        	<x:commandButton action="#{ImputacionBean.irANuevaImputacion}" 
				        		 onclick="clickLink('nuevaImp');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="botones" title="Agregar nuevas imutaciones."/>

							<x:commandButton action="#{ImputacionBean.irAListarImputaciones}" 
								 onclick="clickLink('listarImp');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="botones" title="Ir al listado de imputaciones."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevaImp" action="#{ImputacionBean.irANuevaImputacion}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarImp" action="#{ImputacionBean.irAListarImputaciones}" forceId="true" style="display: block;"/>
					
					<c:if test="${ImputacionBean.validado!=true}">		
					<s:layoutingTitlePane id="altaComprobantes" label=" Alta Comprobantes" 
							      		containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >								
					<h:panelGroup id="errorCuitInvalido" style="width: 300px">
						<h:outputText id="cuitInvalido" value="#{ImputacionBean.cuitInvalido}" style="font-size: 10px;color: red"/>
					</h:panelGroup>					
             		<h:panelGrid id="panelPrincipalUno" columns="3" width="350" align="center">
		                <h:outputText id="outCuitIngreso" value="CUIT del Proveedor: " styleClass="texto"/>
							<x:inputText id="inCuit" forceId="true"  value="#{ImputacionBean.cuit}" 
										 size="11" maxlength="11" styleClass="bordeceldainferior" 
										 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
										 onkeypress="return soloEnteros(this,event);" />
								
						<x:commandLink id="buscarProveedorLink" action="#{ImputacionBean.buscarProveedorPopup}">
							<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
						</x:commandLink>

						<h:commandButton value="Continuar" actionListener="#{ImputacionBean.validarCuit}" styleClass="botones"/>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>														
					</h:panelGrid>
					</s:layoutingTitlePane>					
					</c:if>
					
					
				<c:if test="${ImputacionBean.validado==true}">
					<s:layoutingTitlePane id="datos"
								label="Datos del Proveedor"
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
					<h:panelGrid id="imputacionesPanelUno" columns="3" align="center">
						<h:panelGrid columns="3" align="center">
			                <h:outputText id="outCodigo" value="Código: " styleClass="texto"/>
			                <h:outputText id="outCodigoValor" value="#{ImputacionBean.idProveedor}" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                <h:outputText id="outRazonSocial" value="Razon Social: " styleClass="texto"/>
			                <h:outputText id="outRazonSocialValor" value="#{ImputacionBean.razonSocial}" styleClass="texto"/>							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>			                
						</h:panelGrid>					
						
						<f:verbatim>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
						</f:verbatim>
												
						<h:panelGrid columns="3" align="center">
			                <h:outputText id="outCuitDos" value="Cuit: " styleClass="texto"/>
							<h:panelGroup id="panelCuitDos">
								<h:outputText id="outCuit" value="#{ImputacionBean.cuit}" styleClass="texto"/>
							</h:panelGroup>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                <h:outputText id="outDomicilioLegal" value="Domicilio Legal: " styleClass="texto"/>
			                <h:outputText id="outDomicilioLegalValor" value="#{ImputacionBean.domicilioLegal}" styleClass="texto"/>												
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</h:panelGrid>
					</h:panelGrid>	
					</s:layoutingTitlePane>
				
        		<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 910; HEIGHT: 350px; border: 1px; margin-left: auto; margin-right: auto;">
					<h:panelGrid id="imputacionesPanelDos" columns="3" align="center">  
					<s:fieldset legend="Cuotas de Comprobantes" style="height: 325px;">
						<c:if test="${empty ImputacionBean.cuotasComprobates}">
							<h:outputText value="No existen comprobantes." styleClass="texto"/>
						</c:if>
					
						<c:if test="${!empty ImputacionBean.cuotasComprobates}">
		        			<f:verbatim>
					        	<display:table id="comprobantes" 
					        				   name="sessionScope.ImputacionBean.cuotasComprobates" 
					        				   defaultsort="1" pagesize="10" export="false"
					        				   class="table-bordered table-striped" requestURIcontext="true"
					        				   requestURI="/tarjetafiel/proveedores/imputaciones/imputaciones.jsf"
					        				   style="width: 420px;">
					        		<display:column property="tipo" title="Tipo y Número" sortable="true" class="tdA"/>
					        		<display:column property="fechaVencimiento" title="Vto" sortable="true" class="tdA"/>
					        		<display:column property="cuota.importe" title="Monto" sortable="true" class="tdA"/>
					        		<display:column property="imputado" title="Imputado" sortable="true" class="tdA"/>
					        		<display:column property="resto" title="Saldo" sortable="true" class="tdA"/>
									<display:column style="width:8%" media="html">
										<input type="checkbox" id="checkComprobantes[${comprobantes.cuota.idCuotaComprobante}]" name="checkComprobantes[${comprobantes.cuota.idCuotaComprobante}]"/>
										<input type="hidden" id="checkComprobantes" name="checkComprobantes" value="${comprobantes.cuota.idCuotaComprobante}"/>
									</display:column>
		
								    <display:setProperty name="sort.amount" value="list" />
								    <display:setProperty name="paging.banner.group_size" value="6" />
								    <display:setProperty name="paging.banner.placement" value="bottom" />
								    <display:setProperty name="paging.banner.item_name" value="Comprobante" />
								    <display:setProperty name="paging.banner.items_name" value="" />
								    <display:setProperty name="paging.banner.some_items_found">
							        <div class="pagebanner" align="center" style="width: 305px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
								    </display:setProperty>
								    <display:setProperty name="paging.banner.no_items_found">
										<div class="pagebanner">No se encontraron {0}.</div>
								    </display:setProperty>						    
								    <display:setProperty name="paging.banner.one_item_found">
										<div class="pagebanner">Un {0} encontrado.</div>
								    </display:setProperty>						    						    
								    <display:setProperty name="paging.banner.all_items_found">
										<div class="pagebanner">{0} {1} encontrados, mostrando todos.</div>
								    </display:setProperty>						    								    
									    						    
						       		<display:setProperty name="paging.banner.full">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.first">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.last">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
					        	</display:table>
			        		</f:verbatim>
						</c:if>
					</s:fieldset>
						
					<f:verbatim>&nbsp;</f:verbatim>
					
					<s:fieldset legend="Ordenes de pago" style="height: 325px;">
						<c:if test="${empty ImputacionBean.cuotasOrdenes}">
							<h:outputText value="No existen ordenes de pago." styleClass="texto"/>
						</c:if>						
						
						<c:if test="${!empty ImputacionBean.cuotasOrdenes}">						
						
		        			<f:verbatim>
					        	<display:table id="ordenes" 
					        				   name="sessionScope.ImputacionBean.cuotasOrdenes" 
					        				   defaultsort="1" pagesize="10" export="false"
					        				   class="table-bordered table-striped" requestURIcontext="true"
					        				   requestURI="/tarjetafiel/proveedores/imputaciones/imputaciones.jsf"
					        				   style="width: 430px;">
					        		<display:column property="tipo" title="Tipo y Número" sortable="true" class="tdA"/>
					        		<display:column property="fechaEmision" title="Emitida" sortable="true" class="tdA"/>
					        		<display:column property="imputado" title="Imputado" sortable="true" class="tdA"/>
					        		<display:column property="cuenta" title="A cuenta" sortable="true" class="tdA"/>
									<display:column style="width:8%" media="html">
										<input type="checkbox" id="checkOrdenes[${ordenes.cuota.idCuotaComprobante}]" name="checkOrdenes[${ordenes.cuota.idCuotaComprobante}]"/>
										<input type="hidden" id="checkOrdenes" name="checkOrdenes" value="${ordenes.cuota.idCuotaComprobante}"/>
									</display:column>
		
								    <display:setProperty name="sort.amount" value="list" />
								    <display:setProperty name="paging.banner.group_size" value="6" />
								    <display:setProperty name="paging.banner.placement" value="bottom" />
								    <display:setProperty name="paging.banner.item_name" value="Orden" />
								    <display:setProperty name="paging.banner.items_name" value="" />
								    <display:setProperty name="paging.banner.some_items_found">
							        <div class="pagebanner" align="center" style="width: 305px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
								    </display:setProperty>
								    <display:setProperty name="paging.banner.no_items_found">
										<div class="pagebanner">No se encontraron {0}.</div>
								    </display:setProperty>						    
								    <display:setProperty name="paging.banner.one_item_found">
										<div class="pagebanner">Una {0} encontrada.</div>
								    </display:setProperty>						    						    
								    <display:setProperty name="paging.banner.all_items_found">
										<div class="pagebanner">{0} {1} encontrados, mostrando todos.</div>
								    </display:setProperty>						    								    
									    						    
						       		<display:setProperty name="paging.banner.full">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.first">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.last">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
					        	</display:table>
			        		</f:verbatim>						
						
						</c:if>
					</s:fieldset>
					</h:panelGrid>
        		</x:div>
				</c:if>

				<c:if test="${ImputacionBean.validado==true}">
				
					<c:if test="${empty ImputacionBean.cuotasComprobates || empty ImputacionBean.cuotasOrdenes}">
						<h:panelGrid columns="2" align="center">
							<h:outputText value="Imposible imputar." styleClass="texto"/>
						</h:panelGrid>
					</c:if>
	
					<c:if test="${!empty ImputacionBean.cuotasComprobates && !empty ImputacionBean.cuotasOrdenes}">
					<h:panelGrid columns="2" align="center">
						<h:commandButton action="#{ImputacionBean.generar}" value="Cargar el monto a imputaciones seleccionadas" styleClass="botones"/>
					</h:panelGrid>

					<c:if test="${!empty ImputacionBean.imputaciones}">
						<h:panelGrid columns="1" align="center">
						<s:fieldset legend="Tabla de imputaciones">
						<x:dataTable id="tablaImputaciones" 
					                 styleClass="standardTable"
					                 headerClass="dataTable_Header"
					                 footerClass="standardTable_Header"
					                 rowClasses="standardTable_Row1,standardTable_Row2"
					                 columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"						
					                 var="imp" renderedIfEmpty="false"
					                 value="#{ImputacionBean.imputaciones}" style=" width : 680px;">
							<h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Orden" styleClass="texto" />
			                    </f:facet>
								<h:outputText value="#{imp.ordenText}" styleClass="texto" />
				    		</h:column>
				                   	
						 	<h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Comprobante" styleClass="texto" />
								</f:facet>
								<h:outputText value="#{imp.comprobanteText}" styleClass="texto" />
				            </h:column>		                   	
				                   	
						    <h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Imputa Por" styleClass="texto" />
			                  	</f:facet>
								<x:inputText id="monto"	value="#{imp.monto}" style="width: 60px;" 
										 styleClass="bordeceldainferior" onfocus="encender(this);" 
										 onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
				        	</h:column>
				        	
						    <h:column>
								<x:commandLink action="#{ImputacionBean.eliminarImputacion}" id="eliminarImpLink">
									<f:param id="idComprobante" name="idComprobante" value="#{imp.cuotasImpComprobante.idImputable}"/>
									<f:param id="idOrden" name="idOrden" value="#{imp.cuotasImpOrden.idImputable}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar imputación." border="0"/>
								</x:commandLink>				        	
							</h:column>
						</x:dataTable>
						</s:fieldset>
						</h:panelGrid>
					
					</c:if>
					</c:if>
						
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
							<c:if test="${!empty ImputacionBean.imputaciones}">
			               		<x:commandButton id="buttonGrabar" value="Guardar" action="#{ImputacionBean.grabarImputado}" styleClass="botones"/>
			               	</c:if>
							<x:commandButton id="buttnnCancelar" value="Cancelar" action="#{ImputacionBean.cancelarImputado}" styleClass="botones"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>

<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Cuenta Corriente Cliente"/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCtaCteCliente');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CtaCteClienteBean.tituloCorto}
    <small>${CtaCteClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<script type="javascript">
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


</script>
<center>
	<secure:check/>

	<h:form id="listadoCtaCteCliente">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="body">
				<h:panelGroup id="body">
					<h:panelGrid columns="1" align="center" id="panelPrincipal" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroCtaCteCliente" label="Filtro Cuenta Corriente Cliente" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid columns="5" rendered="#{CtaCteClienteBean.individuoSeleccionado}" align="center">
							     <h:outputText value="Cuenta Corriente perteneciente a: " styleClass="texto" id="descripCliente"/>
							     <h:outputText value="#{CtaCteClienteBean.nombreCliente}" styleClass="textoblue" id="descripClienteNombre"/>
							     <h:outputText value="Cuit: " styleClass="texto" id="descripClienteCuit"/>
							     <h:outputText value="#{CtaCteClienteBean.cuitCliente}" styleClass="textoblue" id="descripClienteNroCuit"/>
							     <h:commandButton id="botonIncluirTodos" action="#{CtaCteClienteBean.incluirTodosLosClientes}" value="Incluir todos los clientes..." styleClass="botones" />
							</h:panelGrid>
							
							<h:panelGrid id="filtroUno" columns="4" align="center" width="600">
							    
							    
							
								<h:outputText value="Id Cta.Cte.: " styleClass="texto" id="idCtaCte"/>
								<h:inputText id="idctacteclienteFiltro" value="#{CtaCteClienteBean.ctaCteCliente.idCtacteCliente}"	styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Nro. Cuota: " styleClass="texto" id="nroCuota"/>
								<h:inputText id="nrocuotaFiltro" value="#{CtaCteClienteBean.ctaCteCliente.nroCuota}" styleClass="bordeceldainferior" maxlength="4" 
									size="4" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" immediate="true" onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Importe:" styleClass="texto" id="importe"/>
								<h:inputText id="importeFiltro" value="#{CtaCteClienteBean.ctaCteCliente.importe}" styleClass="bordeceldainferior" maxlength="20" 
									size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
								
								<h:outputText value="Concepto: " styleClass="texto" id="conceptoDetalle"/>
			        		    <h:inputText id="conceptoFiltro" value="#{CtaCteClienteBean.conceptoDetalleSeleccionado}"	styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px"/>
						</h:panelGrid>
							<h:panelGrid id="panDosFechas" columns="6" align="center">	
								<h:outputText value="Liquidación: " styleClass="texto" id="liquidacion"/>
			        		    <h:inputText id="liquidacionFiltro" value="#{CtaCteClienteBean.idLiqClienteSeleccionada}"	styleClass="bordeceldainferior" 
									maxlength="20" size="20" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>
									
								<h:outputText value="Fecha Contable: " styleClass="texto" id="fechaContable"/>
								<x:inputCalendar id="contableFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
			                		currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaContable}" renderAsPopup="true"
					            	styleClass="bordeceldainferior" style="width: 100px" immediate="true"
			        		    	popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
			        		    <h:outputText value="Fecha Facturación: " styleClass="texto" id="fechaFacturacion"/>
								<x:inputCalendar id="facturacionFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
			                		currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaFacturacion}" renderAsPopup="true"
					            	styleClass="bordeceldainferior" style="width: 100px" immediate="true"
			        		    	popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>	
			        		    
			        		    </h:panelGrid>
			        		    <h:panelGrid columns="4" align="center" id="fechasReales">	
			        		   <h:outputText value="Fecha Real comprendida desde " styleClass="texto" id="fechaReal"/>
								<x:inputCalendar id="realFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
			                		currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaReal}" renderAsPopup="true"
					            	styleClass="bordeceldainferior" style="width: 100px" immediate="true"
			        		    	popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
			        		    
			        		    <h:outputText value=" hasta " styleClass="texto" id="fechaFacturacionHasta"/>
								<x:inputCalendar id="facturacionFechaHasta" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
			                		currentDayCellClass="currentDayCell" value="#{CtaCteClienteBean.fechaRealHasta}" renderAsPopup="true"
					            	styleClass="bordeceldainferior" style="width: 100px" immediate="true"
			        		    	popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>

							</h:panelGrid>
							
							<h:panelGrid id="filtroDosDek" columns="6" align="center" width="600">

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" action="#{CtaCteClienteBean.listarCtaCteCliente}" 
									title="Busca la Cuenta Corriente Cliente seleccionado" styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty CtaCteClienteBean.ctaCteClienteList}">
							<f:verbatim>
								<display:table id="listaCtaCteCliente" 
									name="sessionScope.CtaCteClienteBean.ctaCteClienteList"
									defaultsort="1" 
									pagesize="25" defaultorder="descending"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarCtaCteCliente.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" 
									requestURIcontext="true" 
									style="width: 900px;">

										<display:column property="ctaCteCliente.idCtacteCliente" title="Id" sortable="true" class="tdB"/>
										<display:column property="ctaCteCliente.conceptoDetalle.label" title="Concepto" sortable="true" class="tdA"/>
										<display:column property="ctaCteCliente.label" title="Cliente" sortable="true" class="tdA"/>
										<display:column property="fechaReal" title="Fecha Real" sortable="true" class="tdB"/>
										<display:column property="ctaCteCliente.nroCuota" title="Nro. Cuotas" sortable="true" class="tdB"/>
										<display:column property="ctaCteCliente.importe" title="Importe" sortable="true" class="tdB"/>
										<display:column property="fechaContable" title="Fecha Contable" sortable="true" class="tdB"/>
										<display:column property="fechaFacturacion" title="Fecha Facturación" sortable="true" class="tdB"/>
										<display:column property="ctaCteCliente.comprobante" title="Comprobante" sortable="true" class="tdB"/>
										<display:column property="ctaCteCliente.liqCliente.label" title="Liquidación" sortable="true" class="tdA"/>
										
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaCtaCteCliente.indice}','idCtaCteClienteHidden');javascript:clickLink('listadoCtaCteCliente:editarCtaCteClienteLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Cuenta Corriente Cliente' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Cuenta Corriente Cliente' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaCtaCteCliente.indice}','idCtaCteClienteHidden');javascript:clickLink('listadoCtaCteCliente:eliminarCtaCteClienteLink')"
														onclick="return confirm('Confirma la eliminación del Cuenta Corriente Cliente.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Cuenta Corriente Cliente' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Cuenta Corriente Cliente' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>

										<display:setProperty name="export.amount" value="list" />
										<display:setProperty name="export.xml" value="true" />
										<display:setProperty name="export.pdf" value="true" />
										<display:setProperty name="export.excel.include_header" value="true" />
										<display:setProperty name="export.banner">
											<div style="width: 900px;" class="exportlinks">Exportar a: {0}</div>
										</display:setProperty>
										<display:setProperty name="basic.show.header" value="true" />
										<display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
										<display:setProperty name="sort.amount" value="list" />
										<display:setProperty name="paging.banner.group_size" value="6" />
										<display:setProperty name="paging.banner.placement" value="bottom" />
										<display:setProperty name="paging.banner.item_name" value="Cuenta Corriente Cliente" />
										<display:setProperty name="paging.banner.items_name" value="Cuenta Corriente Cliente" />
										<display:setProperty name="paging.banner.some_items_found">
											<div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
										</display:setProperty>
										<display:setProperty name="paging.banner.no_items_found">
											<div class="pagebanner">No se encontraron {0}.</div>
										</display:setProperty>						    
										<display:setProperty name="paging.banner.one_item_found">
											<div class="pagebanner">Un {0} encontrado.</div>
										</display:setProperty>						    				
										<display:setProperty name="paging.banner.all_items_found">
											<div class="pagebanner">{0} {1} encontrados, mostrando todos los {2}.</div>
										</display:setProperty>
										<display:setProperty name="paging.banner.full">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
										<display:setProperty name="paging.banner.first">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
										<display:setProperty name="paging.banner.last">
											<div class="pagelinks" align="center" style="width: 900px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
										</display:setProperty>
								</display:table>
							</f:verbatim>

							<%-- Link oculto para eliminar o editar --%>
							<x:commandLink action="#{CtaCteClienteBean.editarCtaCteCliente}" id="editarCtaCteClienteLink" style="display: none;"/>
							<x:commandLink action="#{CtaCteClienteBean.eliminarCtaCteCliente}" id="eliminarCtaCteClienteLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idCtaCteClienteHidden" forceId="true" value="#{CtaCteClienteBean.idCtaCteClienteHidden}"/>

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

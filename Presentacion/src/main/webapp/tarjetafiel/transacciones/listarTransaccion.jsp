<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel | Listado de Transaccion"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoTransaccion');">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Transacción
    <small>Listas</small>
  </h1>
</section>

<section class="content">

		<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Listar Transacciones</h3>
		</div>
		<br/>
<center>
<secure:check/>

	<h:form id="listadoTransaccion">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
				
						<s:layoutingTitlePane id="filtroTransaccion" label="Filtro Transaccion" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="4" align="center" width="600">
								
								<h:outputText value="Id Transacción: " styleClass="texto" id="transaccion"/>
								<h:inputText id="idtranasccionesFiltro" value="#{TransaccionBean.transaccion.idTranascciones}"
									styleClass="bordeceldainferior" maxlength="20" size="20" onkeypress="return soloEnteros(this,event);"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Codigo Comercio: " styleClass="texto" id="codComercio"/>
								<h:inputText id="codcomercioFiltro" value="#{TransaccionBean.transaccion.codComercio}"
									styleClass="bordeceldainferior" maxlength="13" size="13" onkeypress="return soloEnteros(this,event);"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Id Origen: " styleClass="texto" id="origen"/>
								<h:inputText id="idorigenFiltro" value="#{TransaccionBean.transaccion.origen.idOrigenes}" styleClass="bordeceldainferior" 
									maxlength="200" size="200" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Id Operador: " styleClass="texto" id="operador"/>
								<h:inputText id="idoperadorFiltro" value="#{TransaccionBean.transaccion.operador.codigo}" styleClass="bordeceldainferior" 
									maxlength="200" size="200" style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Finalizo Online: " styleClass="texto" id="finalizoOnline"/>
								<x:selectBooleanCheckbox value="#{TransaccionBean.finalizoOnLine}" id="finalizoOnLineBoolean" forceId="true"/>
								
								<h:outputText value="Finalizo Off Line: " styleClass="texto" id="finalizoOffline"/>
								<x:selectBooleanCheckbox value="#{TransaccionBean.finalizoOffLine}" id="finalizoOffLineBoolean" forceId="true"/>
								
								<h:outputText value="Estado Impacto: " styleClass="texto" id="estaImpac"/>
								<x:selectBooleanCheckbox value="#{TransaccionBean.estadoImpacto}" id="estadoimpactoBoolean" forceId="true"/>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText value="Cliente: " styleClass="texto" id="cliente"/>
								<h:selectOneMenu id="lstCliente" value="#{TransaccionBean.idClienteSeleccionada}" styleClass="lista" immediate="true" 
									onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{TransaccionBean.clienteItems}"/>
								</h:selectOneMenu>
																	
								<h:outputText value="Concepto: " styleClass="texto" id="concepto"/>
								<h:selectOneMenu id="lstConcepto" value="#{TransaccionBean.idConceptoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{TransaccionBean.conceptoItems}"/>
								</h:selectOneMenu>

								<h:outputText value="Fecha Conciliado: " styleClass="texto" id="fechaConci"/>
								<x:inputCalendar id="fechaConcilliacion" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
			                		currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold"
			                		value="#{TransaccionBean.transaccion.fechaConciliado}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 150px"
					                popupTodayString="#{example_messages['popup_today_string']}"
			        		        popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					                helpText="DD/MM/YYYY" forceId="true"/>
					              
					            <h:outputText value="Fecha Real: " styleClass="texto" id="fechaReal"/>
								<x:inputCalendar id="realFecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" 
			                		currentDayCellClass="currentDayCell" popupButtonStyleClass="standard_bold"
			                		value="#{TransaccionBean.transaccion.fechaReal}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 150px"
					                popupTodayString="#{example_messages['popup_today_string']}"
			        		        popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					                helpText="DD/MM/YYYY" forceId="true"/>
					           </h:panelGrid>
					           
					           <h:panelGrid id="filtroUnoBis" columns="3" align="center" width="600">
 								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarTransaccion.show();" action="#{TransaccionBean.listarTransaccion}" 
									title="Busca el transaccion seleccionado"	styleClass="botones"/>
								
							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty TransaccionBean.transaccionList}">
							<f:verbatim>
								<display:table id="listaTransaccion" name="sessionScope.TransaccionBean.transaccionList"
									defaultsort="1" pagesize="25"
									class="tableA"
									requestURI="/tarjetafiel/transacciones/listarTransaccion.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="transaccion.idTranascciones" title="Id" sortable="true" class="tdA"/>
										<display:column property="transaccion.clienteTransaccion.individuo.apellido" title="Apellido Cliente" sortable="true" class="tdA"/>
										<display:column property="transaccion.clienteTransaccion.individuo.nombres" title="Nombre Cliente" sortable="true" class="tdA"/>
										<display:column property="transaccion.comercioCod.idCodComercio" title="Cod. Comercio" sortable="true" class="tdA"/>
										<display:column property="transaccion.concepto.label" title="Concepto" sortable="true" class="tdA"/>
										<display:column property="transaccion.origen.idOrigenes" title="Origen" sortable="true" class="tdB"/>
										<display:column property="transaccion.operador.codigo" title="Operador" sortable="true" class="tdB"/>
										<display:column property="transaccion.finalizoOffline" title="Finalizo Offline" sortable="true" class="tdB"/>
										<display:column property="transaccion.finalizoOnline" title="Finalizo Online" sortable="true" class="tdB"/>
										<display:column property="fechaConciliado" title="Fecha Conciliado" sortable="true" class="tdB"/>
										<display:column property="fechaReal" title="Fecha Real" sortable="true" class="tdB"/>
										<display:column property="transaccion.estadoImpacto" title="Estado Impacto" sortable="true" class="tdA"/>
																			

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
										<display:setProperty name="paging.banner.item_name" value="Transaccion" />
										<display:setProperty name="paging.banner.items_name" value="Transaccion" />
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
							<x:commandLink action="#{TransaccionBean.editarTransaccion}" id="editarTransaccionLink" style="display: none;"/>
							<x:commandLink action="#{TransaccionBean.eliminarTransaccion}" id="eliminarTransaccionLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idTransaccionHidden" forceId="true" value="#{TransaccionBean.idTransaccionHidden}"/>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{TransaccionadorBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

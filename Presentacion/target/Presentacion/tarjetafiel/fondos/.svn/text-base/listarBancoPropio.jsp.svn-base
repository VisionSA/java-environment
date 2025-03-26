<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Cuenta Bancaria"/></title>
	<s:script language="javascript">
		function recargar() {
				document.getElementById('conciliacionForm').submit();
			}
			function popup(pagina,popW,popH) {
				var w = 0, h = 0;
			   	w = screen.width;
			   	h = screen.height;
	
				var leftPos = (w-popW)/2, topPos = (h-popH)/2;
			    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
			    
			    if (popupWindow.opener == null){popupWindow.opener = self;}
			    
			};
		</s:script>	
</head>
<jsp:include page="/inc/includes.jsp" />
<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoBancoPropio');">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${BancoPropioBean.tituloCorto}
    <small>${BancoPropioBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
	<secure:check/>

	<h:form id="listadoBancoPropio">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
						
						<h:panelGrid columns="1" id="uno" width="850" align="center">
						<s:layoutingTitlePane id="filtroBancoPropio" label=" Filtro Cuenta Bancaria" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="6" width="848" align="center">
								
								<h:outputText value="Id Cuenta Bancaria:" styleClass="texto"/>
								<h:inputText id="idBancoPropioFiltro" value="#{BancoPropioBean.idBancoPropio}"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>
								<h:outputText value="Tipo de Cuenta:" styleClass="texto"/>
								<h:inputText id="tipoCtaFiltro" value="#{BancoPropioBean.bancoPropio.tipoCuenta}"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>
								<h:outputText value="CBU:" styleClass="texto"/>
								<h:inputText id="cbu" value="#{BancoPropioBean.bancoPropio.cbu}"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>	
								<h:outputText value="Moneda:" styleClass="texto"/>
								<h:selectOneMenu id="lstMoneda" value="#{BancoPropioBean.idMonedaSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{BancoPropioBean.monedaItems}"/>
								</h:selectOneMenu>	
								<h:outputText value="Sucursal:" styleClass="texto"/>
								<h:selectOneMenu id="lstSucursal" value="#{BancoPropioBean.idSucursalSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{BancoPropioBean.sucursalItems}"/>
								</h:selectOneMenu>
								<h:outputText value="Banco" styleClass="texto"/>
								<h:selectOneMenu id="lstBanco" value="#{BancoPropioBean.idBancoSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{BancoPropioBean.bancoItems}"/>
								</h:selectOneMenu>
									<h:outputText value="Numero Cuenta:" styleClass="texto"/>
								<h:inputText id="nombreFiltro" value="#{BancoPropioBean.bancoPropio.numeroCuenta}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="Numero Sucursal:" styleClass="texto" />
								<h:inputText id="nrosuc" value="#{BancoPropioBean.bancoPropio.numeroSucursal}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />
								<h:outputText value="Numero Sucursal:" styleClass="texto" />
								<h:inputText id="plaza" value="#{BancoPropioBean.bancoPropio.plaza}"
									styleClass="bordeceldatext" maxlength="100" size="100"
									style="width: 150px" onfocus="encender(this);" onblur="apagar(this);" />	
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarBancoPropio.show();"
									action="#{BancoPropioBean.listarBancoPropio}" title="Busca la cuenta bancaria seleccionada" styleClass="botones"/>
							
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty BancoPropioBean.bancoPropioList}">
							<f:verbatim>
								<display:table id="listaBancoPropio" name="sessionScope.BancoPropioBean.bancoPropioList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/fondos/listarBancoPropio.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idBancoPropio" title="Id Cuenta Bancaria" sortable="true" class="tdB"/>
										<display:column property="sucursal.label" title="Sucursal Fiel" sortable="true" class="tdA"/>
										<display:column property="banco.label" title="Banco" sortable="true" class="tdA"/>
										<display:column property="tipoCuenta" title="Tipo Cuenta" sortable="true" class="tdA"/>
										<display:column property="numeroCuenta" title="Nro Cuenta" sortable="true" class="tdA"/>
										<display:column property="numeroSucursal" title="Sucursal" sortable="true" class="tdA"/>
										<display:column property="plaza" title="Plaza" sortable="true" class="tdA"/>
										<display:column property="moneda.label" title="Moneda" sortable="true" class="tdA"/>
										<display:column property="cbu" title="CBU" sortable="true" class="tdA"/>
										<display:column property="habilitado" title="Habilitada" sortable="true" class="tdA"/>
										
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaBancoPropio.idBancoPropio}','idBancoPropioHidden');javascript:clickLink('listadoBancoPropio:editarBancoPropioLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Cuenta Bancaria.' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Cuenta Bancaria.' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaBancoPropio.idBancoPropio}','idBancoPropioHidden');javascript:clickLink('listadoBancoPropio:eliminarBancoPropioLink')">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Cuenta Bancaria.' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Cuenta Bancaria.' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										
										<display:column style="width: 25px;" media="html">
													<a href="javascript:viewUser('${listaBancoPropio.idBancoPropio}','idBancoPropioHidden');javascript:clickLink('listadoBancoPropio:mostrarChequerasLink')">					
													  <img src='<%=request.getContextPath()%>/img/icon/srch_16.gif' title='Borrar Cuenta Bancaria.' border='0'/>
													</a>
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
										<display:setProperty name="paging.banner.item_name" value="Cuenta" />
										<display:setProperty name="paging.banner.items_name" value="Cuenta" />
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
							<x:commandLink action="#{BancoPropioBean.editarBancoPropio}" id="editarBancoPropioLink" style="display: none;"/>
							<x:commandLink action="#{BancoPropioBean.eliminarBancoPropio}" id="eliminarBancoPropioLink" style="display: none;"/>
							<x:commandLink action="#{BancoPropioBean.mostrarChequeras}" id="mostrarChequerasLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idBancoPropioHidden" forceId="true" value="#{BancoPropioBean.idBancoPropioHidden}"/>

						</c:if>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{BancoPropioBean.irAListarBancoPropio}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>

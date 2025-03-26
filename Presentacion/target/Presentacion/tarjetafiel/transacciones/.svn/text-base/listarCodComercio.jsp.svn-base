<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de CodComercio"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCodComercio');">

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

<secure:check/>

	<h:form id="listadoCodComercio">
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

						<s:layoutingTitlePane id="filtroCodComercio" label=" Filtro" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid columns="2" id="panelDivisorio" align="center">
							<h:panelGrid id="filtroUno" columns="4" align="center">
								
								<h:outputText value="Código Posnet:" styleClass="texto"/>
								<h:inputText id="codigoposnetFiltro" value="#{CodComercioBean.codcomercioFiltro.codigoPosnet}"
								styleClass="bordeceldainferior" maxlength="13" size="13"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="Estado:" styleClass="texto"/>
								<h:selectOneMenu id="lstDeEstadosFiltor" value="#{CodComercioBean.codcomercioFiltro.estado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{CodComercioBean.estadoItems}"/>
								</h:selectOneMenu>
								
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<%--  <h:outputText value="Comercio:" styleClass="texto"/>
								<h:selectOneMenu id="lstComercio" value="#{CodComercioBean.codcomercioFiltro.comercio.idComercio}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{CodComercioBean.comercioItems}"/>
								</h:selectOneMenu>--%>
                                </h:panelGrid>
                                <h:panelGrid columns="3" id="btnBuscarFiltro">
                                <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" 
								value="Buscar"
								action="#{CodComercioBean.listarCodComercio}" 
								title="Busca el codcomercio seleccionado"
								image="/img/icon/srch_24.gif"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
                                </h:panelGrid>
							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty CodComercioBean.codcomercioList}">
							<f:verbatim>
								<display:table id="listaCodComercio" name="sessionScope.CodComercioBean.codcomercioList"
									defaultsort="1" pagesize="10"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarCodComercio.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}"
									requestURIcontext="true" style="width: 900px;">

										<display:column property="codigo" title="Código" sortable="true" class="tdA"/>
										<display:column property="codigoPosnet" title="Código Posnet" sortable="true" class="tdA"/>
										<display:column property="estado" title="Estado" sortable="true" class="tdA"/>
									    <display:column property="label" title="Comercio" sortable="true" class="tdA"/>
										<display:column property="liqCantcierres" title="Liq. Cant. Cierres" sortable="true" class="tdA"/>
										<display:column property="liqDias" title="Liq. Dias" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaCodComercio.idCodComercio}','idCodComercioHidden');javascript:clickLink('listadoCodComercio:editarCodComercioLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Código Comercio' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Código Comercio' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaCodComercio.idCodComercio}','idCodComercioHidden');javascript:clickLink('listadoCodComercio:eliminarCodComercioLink')"
														onclick="return confirm('Confirma la eliminación del Código Comercio.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Código Comercio' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Código Comercio' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										
										<display:column style="width: 25px;" media="html">
											<a href="javascript:viewUser('${listaCodComercio.idCodComercio}','idCodComercioHidden');javascript:clickLink('listadoCodComercio:ctacteCodComercioLink')">
												<img src='<%=request.getContextPath()%>/img/icon/book_open 16.png' title='Ver Cuenta Corriente' border='0' />
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
										<display:setProperty name="paging.banner.item_name" value="CodComercio" />
										<display:setProperty name="paging.banner.items_name" value="CodComercio" />
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
							<x:commandLink action="#{CodComercioBean.editarCodComercio}" id="editarCodComercioLink" style="display: none;"/>
							<x:commandLink action="#{CodComercioBean.eliminarCodComercio}" id="eliminarCodComercioLink" style="display: none;"/>
							<x:commandLink action="#{CodComercioBean.verCtaCteCodComercio}" id="ctacteCodComercioLink" style="display: none;"/>
							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idCodComercioHidden" forceId="true" value="#{CodComercioBean.idCodComercioHidden}"/>

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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{CodComercioBean.irAListarCodComercio}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

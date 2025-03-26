<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Archivos de Configuración de Débitos."/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoArchivDebConf');">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ArchivoDebitoConfBean.tituloCorto}
    <small>${ArchivoDebitoConfBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>


<center>
	<secure:check/>

	<h:form id="listadoArchivDebConf">
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

						<s:layoutingTitlePane id="filtroOrigenen" label="Filtro Archivos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="5" align="center">
								<h:outputText value="Id:" styleClass="texto"/>
								<h:inputText id="idArchivosFiltro" value="#{ArchivoDebitoConfBean.idArchivoFiltro}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="Descripcion:" styleClass="texto"/>
								<h:inputText id="descripcionFiltro" value="#{ArchivoDebitoConfBean.descripcionFiltro}"
								styleClass="bordeceldainferior" maxlength="50" size="50"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>

								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarArchivo.show();"
								action="#{ArchivoDebitoConfBean.listarArchivos}" 
								title="Busca el Archivo seleccionado"
								image="/img/icon/srch_24.gif"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty ArchivoDebitoConfBean.archivosList}">
							<f:verbatim>
								<display:table id="listaArchivos" name="sessionScope.ArchivoDebitoConfBean.archivosList"
									defaultsort="1" pagesize="10"
									class="table-bordered table-striped"
									
									requestURI="/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idArchivoDebitoConf" title="ID" sortable="true" class="tdA"/>
										<display:column property="descripcion" title="Descripción" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaArchivos.idArchivoDebitoConf}','idArchivoHidden');javascript:clickLink('listadoArchivDebConf:editarArchivoLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Archivo' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Archivo' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaArchivos.idArchivoDebitoConf}','idArchivoHidden');javascript:clickLink('listadoArchivDebConf:eliminarArchivoLink')"
														onclick="return confirm('¿Confirma la eliminación del archivo?.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Archivo' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Archivo' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Origenen" />
										<display:setProperty name="paging.banner.items_name" value="Origenen" />
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
							<x:commandLink action="#{ArchivoDebitoConfBean.editarArchivo}" id="editarArchivoLink" style="display: none;"/>
							<x:commandLink action="#{ArchivoDebitoConfBean.eliminarArchivo}" id="eliminarArchivoLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idArchivoHidden" forceId="true" value="#{ArchivoDebitoConfBean.idArchivoHidden}"/>

						</c:if>
						
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ArchivoDebitoConfBean.irAListarArchivoDebitoConf}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

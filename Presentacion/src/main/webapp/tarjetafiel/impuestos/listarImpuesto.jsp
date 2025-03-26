<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Impuesto"/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoImpuesto');">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ImpuestoBean.tituloCorto}
    <small>${ImpuestoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="listadoImpuesto">
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
						
						<h:panelGrid columns="1" align="center" width="600" id="uno">
						<s:layoutingTitlePane id="filtroImpuesto" label=" Filtro Impuesto" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="6" align="center">
								
								<h:outputText value="Descripción:" styleClass="texto"/>
								<h:inputText id="descripcionFiltro" value="#{ImpuestoBean.impuesto.descripcion}"
								styleClass="bordeceldatext" maxlength="100" size="100"
								style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Importe Minimo:" styleClass="texto"/>
								<h:inputText id="importeminimoFiltro" value="#{ImpuestoBean.importeMinimo}"
								styleClass="bordeceldainferior" maxlength="10" size="10"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Tipo impuesto:" styleClass="texto" id="outputTipoImp" />
								<h:selectOneMenu id="lstTipoImp" value="#{ImpuestoBean.idTipoImpSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									binding="#{ImpuestoBean.tipoImpHtml}" valueChangeListener="#{ImpuestoBean.cambiarCategorias}"
									onblur="apagar(this);" style="width: 200px" onchange="submit();">
									<f:selectItems value="#{ImpuestoBean.tipoImpItems}" id="selectedTipoImp" />
								</h:selectOneMenu>
								
								<h:outputText value="Categoría:" styleClass="texto" id="outputCategoria" />
								<h:selectOneMenu id="lstCategoria" value="#{ImpuestoBean.idCategoriaSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ImpuestoBean.categoriaItems}" id="selectedCategoria" />
								</h:selectOneMenu>
								
								<h:outputText value="Provincia:" styleClass="texto" id="outputProvincia" />
								<h:selectOneMenu id="lstProvincia" value="#{ImpuestoBean.idProvinciaSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px" binding="#{ImpuestoBean.provinciaHtml}"
									valueChangeListener="#{ImpuestoBean.cargarPartido}" onchange="submit();">
									<f:selectItems value="#{ImpuestoBean.provinciaItems}" id="selectedProvincia"/>
								</h:selectOneMenu>
												
								<h:outputText value="Partido:" styleClass="texto" id="outputLocalidad" />
								<h:selectOneMenu id="lstLocalidad" value="#{ImpuestoBean.idPartidoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ImpuestoBean.partidoItems}" id="selectedLocalidad"/>
								</h:selectOneMenu>
															
								<h:outputText value="Alicuota:" styleClass="texto"/>
								<h:panelGroup>
									<h:inputText id="porcalicuotaFiltro" value="#{ImpuestoBean.porcAlicuota}"
										styleClass="bordeceldainferior" maxlength="5" size="5"
										style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloDecimales(this,event);"/>
									<h:outputText value="%" id="porcentaje"/>
								</h:panelGroup>
								
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarImpuesto.show();"
								action="#{ImpuestoBean.listarImpuesto}" title="Busca el impuesto seleccionado" styleClass="botones"/>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>

						<c:if test="${!empty ImpuestoBean.impuestoList}">
							<f:verbatim>
								<display:table id="listaImpuesto" name="sessionScope.ImpuestoBean.impuestoList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/impuestos/listarImpuesto.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idImpuesto" title="Id Impuesto" sortable="true" class="tdA"/>
										<display:column property="descripcion" title="Descripción" sortable="true" class="tdA"/>
										<display:column property="categoria.descripcion" title="Categoría" sortable="true" class="tdA"/>
										<display:column property="provincia.nombre" title="Provincia" sortable="true" class="tdA"/>
										<display:column property="partido.descripcion" title="Partido" sortable="true" class="tdA"/>
										<display:column property="imponibleGncias" title="Ganancia Imponible" sortable="true" class="tdA"/>
										<display:column property="percepcion" title="Percepción" sortable="true" class="tdA"/>
										<display:column property="importeMinimo" title="Importe Minimo" sortable="true" class="tdB"/>
										<display:column property="porcAlicuota" title="% Alicuota" sortable="true" class="tdB"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaImpuesto.idImpuesto}','idImpuestoHidden');javascript:clickLink('listadoImpuesto:editarImpuestoLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Impuesto' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Impuesto' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaImpuesto.idImpuesto}','idImpuestoHidden');javascript:clickLink('listadoImpuesto:eliminarImpuestoLink')"
														onclick="return confirm('Confirma la eliminación del Impuesto.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Impuesto' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Impuesto' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Impuesto" />
										<display:setProperty name="paging.banner.items_name" value="Impuesto" />
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
							<x:commandLink action="#{ImpuestoBean.editarImpuesto}" id="editarImpuestoLink" style="display: none;"/>
							<x:commandLink action="#{ImpuestoBean.eliminarImpuesto}" id="eliminarImpuestoLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idImpuestoHidden" forceId="true" value="#{ImpuestoBean.idImpuestoHidden}"/>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ImpuestoBean.irAListarImpuesto}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>

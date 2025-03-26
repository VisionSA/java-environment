<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel | Listado de Localidad"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoLocalidad');">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LocalidadBean.tituloCorto}
    <small>${LocalidadBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<secure:check/>
	<h:form id="listadoLocalidad">
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
						
						<h:panelGrid columns="1" id="panelPricipal" width="850">
						
							<s:layoutingTitlePane id="filtroLocalidad" label=" Filtro Localidad" containerNodeClass="contentTitlePane" 
								labelNodeClass="labelTitlePane" >
								
								<h:panelGrid id="filtroUno" columns="6" align="center" width="600">
								
									<h:outputText value="Id Localidad:" styleClass="texto"/>
									<h:inputText id="idlocalidadFiltro" value="#{LocalidadBean.idLocalidad}"
									styleClass="bordeceldainferior" maxlength="10" size="10"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>
									
									<h:outputText value="Código Postal:" styleClass="texto"/>
									<h:inputText id="codigopostalFiltro" value="#{LocalidadBean.codigoPostal}"
									styleClass="bordeceldainferior" maxlength="4" size="4"
									style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
									onkeypress="return soloEnteros(this,event);"/>
									
									<h:outputText value="Nombre Localidad:" styleClass="texto"/>
									<h:inputText id="nombreFiltro" value="#{LocalidadBean.localidad.nombre}"
										styleClass="texto" maxlength="100" size="100"
										style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
									
									<h:outputText value="País" styleClass="texto"/>
									<h:selectOneMenu id="lstPais" value="#{LocalidadBean.idPaisSeleccionado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px" binding="#{LocalidadBean.pais}"
										valueChangeListener="#{LocalidadBean.cambiarProvincias}" onchange="submit();">
										<f:selectItems id="itemPais" value="#{LocalidadBean.paisItems}"/>
									</h:selectOneMenu>
									
									<h:outputText value="Provincia:" styleClass="texto"/>
									<h:selectOneMenu id="lstProvincia" value="#{LocalidadBean.idProvinciaSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px" binding="#{LocalidadBean.provincia}"
										valueChangeListener="#{LocalidadBean.cambiarPartidos}" onchange="submit();">
										<f:selectItems id="itemListadoProvincia" value="#{LocalidadBean.provinciaItems}"/>
									</h:selectOneMenu>
									
									<h:outputText value="Partido:" styleClass="texto"/>
									<h:selectOneMenu id="lstPartido" value="#{LocalidadBean.idPartidoSeleccionada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px">
										<f:selectItems id="itemListadoPartido" value="#{LocalidadBean.partidoItems}"/>
									</h:selectOneMenu>
									
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>	
									<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarLocalidad.show();"
										action="#{LocalidadBean.listarLocalidad}" title="Busca el localidad seleccionado" styleClass="botones"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	
								</h:panelGrid>
							</s:layoutingTitlePane>
						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">			
						<c:if test="${!empty LocalidadBean.localidadList}">
							<f:verbatim>
								<display:table id="listaLocalidad" name="sessionScope.LocalidadBean.localidadList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/general/listarLocalidad.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idLocalidad" title="Id Localidad" sortable="true" class="tdB"/>
										<display:column property="codigoPostal" title="Código Postal" sortable="true" class="tdB"/>
										<display:column property="partido.label" title="Partido" sortable="true" class="tdA"/>
										<display:column property="provincia.label" title="Provincia" sortable="true" class="tdA"/>
										<display:column property="nombre" title="Nombre Localidad" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaLocalidad.idLocalidad}','idLocalidadHidden');javascript:clickLink('listadoLocalidad:editarLocalidadLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Localidad.' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Localidad.' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaLocalidad.idLocalidad}','idLocalidadHidden');javascript:clickLink('listadoLocalidad:eliminarLocalidadLink')"
														onclick="return confirm('Confirma la eliminación del Localidad.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Localidad.' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Localidad.' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Localidad" />
										<display:setProperty name="paging.banner.items_name" value="Localidad" />
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
							<x:commandLink action="#{LocalidadBean.editarLocalidad}" id="editarLocalidadLink" style="display: none;"/>
							<x:commandLink action="#{LocalidadBean.eliminarLocalidad}" id="eliminarLocalidadLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idLocalidadHidden" forceId="true" value="#{LocalidadBean.idLocalidadHidden}"/>

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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{LocalidadBean.irAListarLocalidad}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

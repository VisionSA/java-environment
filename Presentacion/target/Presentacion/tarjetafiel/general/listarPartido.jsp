<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel | Listado de Partido"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoPartido');">



<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PartidoBean.tituloCorto}
    <small>${PartidoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
	<secure:check/>

	<h:form id="listadoPartido">
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
						<s:layoutingTitlePane id="filtroPartido" label=" Filtro Partido" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="4" align="center" width="600">
								
								<h:outputText value="Id Partido:" styleClass="texto"/>
								<h:inputText id="idpartidoFiltro" value="#{PartidoBean.idPartido}"
								styleClass="bordeceldainferior" maxlength="10" size="10"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								
								<h:outputText value="Descripci�n Partido:" styleClass="texto"/>
								<h:inputText id="descripcionFiltro" value="#{PartidoBean.partido.descripcion}"
								styleClass="texto" maxlength="50" size="50"
								style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>
								
								<h:outputText value="Pa�s" styleClass="texto"/>
								<h:selectOneMenu id="lstPais" value="#{PartidoBean.idPaisSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px" binding="#{PartidoBean.pais}"
									valueChangeListener="#{PartidoBean.cambiarProvincias}" onchange="submit();">
									<f:selectItems id="itemPais" value="#{PartidoBean.paisItems}"/>
								</h:selectOneMenu>
							
								<h:outputText value="Provincia:" styleClass="texto"/>
								<h:selectOneMenu id="lstProvincia" value="#{PartidoBean.idProvinciaSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{PartidoBean.provinciaItems}"/>
								</h:selectOneMenu>
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarPartido.show();"
									action="#{PartidoBean.listarPartido}" title="Busca el partido seleccionado"	styleClass="botones" />
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty PartidoBean.partidoList}">
							<f:verbatim>
								<display:table id="listaPartido" name="sessionScope.PartidoBean.partidoList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/general/listarPartido.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idPartido" title="Id Partido" sortable="true" class="tdB"/>
										<display:column property="descripcion" title="Descripci�n" sortable="true" class="tdA"/>
										<display:column property="provincia.label" title="Provincia" sortable="true" class="tdA"/>
										<display:column property="informeAmbiental" title="Informe Ambiental" sortable="true" class="tdB"/>
										<display:column property="esLejano" title="Lejano" sortable="true" class="tdB"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaPartido.idPartido}','idPartidoHidden');javascript:clickLink('listadoPartido:editarPartidoLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Partido' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Partido' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaPartido.idPartido}','idPartidoHidden');javascript:clickLink('listadoPartido:eliminarPartidoLink')"
														onclick="return confirm('Confirma la eliminaci�n del Partido.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Partido' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Partido' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Partido" />
										<display:setProperty name="paging.banner.items_name" value="Partido" />
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
							<x:commandLink action="#{PartidoBean.editarPartido}" id="editarPartidoLink" style="display: none;"/>
							<x:commandLink action="#{PartidoBean.eliminarPartido}" id="eliminarPartidoLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idPartidoHidden" forceId="true" value="#{PartidoBean.idPartidoHidden}"/>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{PartidoBean.irAListarPartido}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

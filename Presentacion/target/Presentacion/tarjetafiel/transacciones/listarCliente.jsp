<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Cliente" /></title>
	</head>
	<jsp:include page="/inc/includes.jsp" />
	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCliente');">
	<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    ${ClienteBean.tituloCorto}
	    <small>${ClienteBean.tituloLargo}</small>
	  </h1>
	</section>

	<section class="content">

	<div class="box box-default">
	<div class="box-header"><h3></h3></div>

	<center><secure:check /> <h:form id="listadoCliente">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">


			<f:facet name="body">
				<h:panelGroup id="body">
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroCliente" label=" Filtro Cliente"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane">
							<h:panelGrid id="filtroUno" columns="4">
								<h:panelGroup id="pnaGrupal">
									<h:panelGrid columns="4" id="patronesDeBusqueda" align="center">
										<h:outputText value="Nro. Cuenta:" styleClass="texto" />
										<h:inputText id="cuentaFiltro"
											value="#{ClienteBean.clienteFiltro.idCliente}"
											styleClass="bordeceldainferior" maxlength="11" size="13"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>

										<h:outputText value="CUIL:" styleClass="texto" />
										<h:inputText id="cuitFiltro"
											value="#{ClienteBean.clienteFiltro.individuo.cuil}"
											styleClass="bordeceldainferior" maxlength="11" size="13"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"/>

										<h:outputText value="Plástico:" />
										<h:inputText id="plasticoClienteFiltro"
											value="#{ClienteBean.numeroPlastico}"
											align="right"
											styleClass="bordeceldainferior" maxlength="16" size="18"
											style="width: 150px" onfocus="encender(this);"
											onblur="apagar(this);"
											onkeypress="return soloEnteros(this,event);" />

										<h:outputText value="D.N.I.:" styleClass="texto" />
										<h:inputText id="dniIndividuoFiltro"
											value="#{ClienteBean.clienteFiltro.individuo.nroDocumento}"
											styleClass="bordeceldainferior" style="width: 150px"
											onfocus="encender(this);" onblur="apagar(this);" 
											onkeypress="return soloEnteros(this,event);"/>

										<h:outputText value="Apellido:" styleClass="texto" />
										<h:inputText id="apellIndividuoFiltro"
											value="#{ClienteBean.clienteFiltro.individuo.apellido}"
											align="left"
											styleClass="bordeceldainferior" style="width: 150px"
											onfocus="encender(this);" onblur="apagar(this);" />

										<h:outputText value="Nombres:" styleClass="texto" />
										<h:inputText id="nomIndividuoFiltro"
											value="#{ClienteBean.clienteFiltro.individuo.nombres}"
											align="left"
											styleClass="bordeceldainferior" style="width: 150px"
											onfocus="encender(this);" onblur="apagar(this);" />

									</h:panelGrid>
									
								</h:panelGroup>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar"
									onclick="agregarCliente.show();"
									action="#{ClienteBean.listarCliente}"
									title="Busca el cliente seleccionado"
									image="/img/icon/srch_24.gif" />

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty ClienteBean.clienteList}">
							<f:verbatim>
								<display:table id="listaCliente"
									name="sessionScope.ClienteBean.clienteList" defaultsort="1"
									pagesize="10" class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarCliente.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}"
									requestURIcontext="true" style="width: 900px;">

									<display:column property="idCliente" title="Nro. Cuenta" style="width: 35px;"
										sortable="true" class="tdB" />
									<display:column property="individuo.cuil" title="Cuit"
										sortable="true" class="tdB "/>
									<display:column property="individuo.apellido" title="Apellido"
										sortable="true" class="tdA" />
									<display:column property="individuo.nombres" title="Nombres"
										sortable="true" class="tdA" />
									<display:column property="tipoCliente" title="Condición"
										sortable="true" class="tdA" />
									<display:column property="estadoCobranza.descripcion" style="width: 30px;"
										title="Est.Cobranza" sortable="true" class="tdA" />
									<display:column property="habilitadoConsumo" style="width: 30px;"
										title="Est.Consumo" sortable="true" class="tdA" />
									
									<display:column style="width: 55px;" media="html">
										<a
											href="javascript:viewUser('${listaCliente.idCliente}','idClienteHidden');javascript:clickLink('listadoCliente:verLiqPartClienteLink')" >
										    Gestionar Cliente
                                        </a>
									</display:column>

									
									<display:setProperty name="export.amount" value="list" />
									<display:setProperty name="export.xml" value="true" />
									<display:setProperty name="export.pdf" value="true" />
									<display:setProperty name="export.excel.include_header"
										value="true" />
									<display:setProperty name="export.banner">
										<div style="width: 900px;" class="exportlinks">Exportar
										a: {0}</div>
									</display:setProperty>
									<display:setProperty name="basic.show.header" value="true" />
									<display:setProperty name="basic.msg.empty_list"
										value="No se encontraron elementos." />
									<display:setProperty name="sort.amount" value="list" />
									<display:setProperty name="paging.banner.group_size" value="6" />
									<display:setProperty name="paging.banner.placement"
										value="bottom" />
									<display:setProperty name="paging.banner.item_name"
										value="Cliente" />
									<display:setProperty name="paging.banner.items_name"
										value="Cliente" />
									<display:setProperty name="paging.banner.some_items_found">
										<div class="pagebanner" align="center" style="width: 900px;">{0}
										{1} encontrados, mostrando desde el {2} hasta el {3}</div>
									</display:setProperty>
									<display:setProperty name="paging.banner.no_items_found">
										<div class="pagebanner">No se encontraron {0}.</div>
									</display:setProperty>
									<display:setProperty name="paging.banner.one_item_found">
										<div class="pagebanner">Un {0} encontrado.</div>
									</display:setProperty>
									<display:setProperty name="paging.banner.all_items_found">
										<div class="pagebanner">{0} {1} encontrados, mostrando
										todos los {2}.</div>
									</display:setProperty>
									<display:setProperty name="paging.banner.full">
										<div class="pagelinks" align="center" style="width: 900px;"><a
											href={1}><img
											src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a
											href={2}><img
											src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a
											href={3}><img
											src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a
											href={4}><img
											src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
									</display:setProperty>
									<display:setProperty name="paging.banner.first">
										<div class="pagelinks" align="center" style="width: 900px;"><a
											href={1}><img
											src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a
											href={2}><img
											src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a
											href={3}><img
											src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a
											href={4}><img
											src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
									</display:setProperty>
									<display:setProperty name="paging.banner.last">
										<div class="pagelinks" align="center" style="width: 900px;"><a
											href={1}><img
											src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a
											href={2}><img
											src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a
											href={3}><img
											src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a
											href={4}><img
											src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
									</display:setProperty>
								</display:table>
								
							</f:verbatim>
							<!-- Emiliano -->
							<h:panelGrid id="botoneraPaginadorLote" width="200" columns="7"
								align="center">
								<h:commandLink id="botonPrimeraPaginaLote"
										action="#{ClienteBean.primeraPagina}"
									rendered="#{ClienteBean.pagDeMov.hayAnterior}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/skipb_16.gif" border="0"
										onclick="submit();" />
								</h:commandLink>
								<h:commandLink id="botonPaginaAnteriorLote"
									action="#{ClienteBean.paginaAnterior}"
									rendered="#{ClienteBean.pagDeMov.hayAnterior}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/rewnd_16.gif" border="0"
										onclick="submit();" />
								</h:commandLink>
								<h:outputText value="Página " />
								<h:selectOneMenu id="lstDePaginas"
									value="#{ClienteBean.pagDeMov.idPaginaSeleccionada}"
									binding="#{ClienteBean.pagDeMov.pagSeleccionada}"
									styleClass="lista" onfocus="encender(this);" immediate="true"
									valueChangeListener="#{ClienteBean.cargarPagina}"
									onblur="apagar(this);" style=" width : 50px;"
									onchange="submit();">
									<f:selectItems value="#{ClienteBean.pagDeMov.comboDePaginas}"
										id="selectEjerDeSucum" />
								</h:selectOneMenu>
								<h:outputText value="#{ClienteBean.pagDeMov.estado}" />
								<h:commandLink id="botonPaginaSiguienteLote"
									action="#{ClienteBean.paginaSiguiente}"
									rendered="#{ClienteBean.pagDeMov.haySiguiente}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/fastf_16.gif" border="0"
										onclick="submit();" />
								</h:commandLink>
								<h:commandLink id="botonUltimaPaginaLote"
									action="#{ClienteBean.ultimaPagina}"
									rendered="#{ClienteBean.pagDeMov.haySiguiente}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/skipf_16.gif" border="0"
										onclick="submit();" />
								</h:commandLink>
							</h:panelGrid>
							<%-- Link oculto para eliminar o editar --%>
							<x:commandLink action="#{ClienteBean.editarCliente}"
								id="editarClienteLink" style="display: none;" />
							<x:commandLink action="#{ClienteBean.mostrarCtaCteCliente}"
								id="verCtaCteClienteLink" style="display: none;" />
							<x:commandLink action="#{ClienteBean.mostrarGestionClientes}"
								id="verLiqPartClienteLink" style="display: none;" />
							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idClienteHidden" forceId="true"
								value="#{ClienteBean.idClienteHidden}" />

						</c:if>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>
	</h:form></center>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ClienteBean.irAListarCliente}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
	</body>
	</html>
</f:view>

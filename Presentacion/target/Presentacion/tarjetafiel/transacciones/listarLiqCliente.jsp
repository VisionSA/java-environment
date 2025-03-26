<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de LiqCliente"/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoLiqCliente');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LiqClienteBean.tituloCorto}
    <small>${LiqClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="listadoLiqCliente">
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

						<s:layoutingTitlePane id="filtroLiqCliente" label=" Filtro LiqCliente" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="7">
								<h:outputText value="idLiqCliente:" styleClass="texto"/>
								<h:inputText id="idliqclienteFiltro" value="#{LiqClienteBean.liqCliente.idLiqCliente}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaCierre:" styleClass="texto"/>
								<h:inputText id="fechacierreFiltro" value="#{LiqClienteBean.liqCliente.fechaCierre}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaCierreAnterior:" styleClass="texto"/>
								<h:inputText id="fechacierreanteriorFiltro" value="#{LiqClienteBean.liqCliente.fechaCierreAnterior}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaLiq:" styleClass="texto"/>
								<h:inputText id="fechaliqFiltro" value="#{LiqClienteBean.liqCliente.fechaLiq}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaProxCierre:" styleClass="texto"/>
								<h:inputText id="fechaproxcierreFiltro" value="#{LiqClienteBean.liqCliente.fechaProxCierre}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaProximoVto:" styleClass="texto"/>
								<h:inputText id="fechaproximovtoFiltro" value="#{LiqClienteBean.liqCliente.fechaProximoVto}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaVtoAnterior:" styleClass="texto"/>
								<h:inputText id="fechavtoanteriorFiltro" value="#{LiqClienteBean.liqCliente.fechaVtoAnterior}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="intComp:" styleClass="texto"/>
								<h:inputText id="intcompFiltro" value="#{LiqClienteBean.liqCliente.intComp}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="intPunitorios:" styleClass="texto"/>
								<h:inputText id="intpunitoriosFiltro" value="#{LiqClienteBean.liqCliente.intPunitorios}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="pago3cuotas:" styleClass="texto"/>
								<h:inputText id="pago3cuotasFiltro" value="#{LiqClienteBean.liqCliente.pago3cuotas}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="pago6cuotas:" styleClass="texto"/>
								<h:inputText id="pago6cuotasFiltro" value="#{LiqClienteBean.liqCliente.pago6cuotas}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="pagoMinimo:" styleClass="texto"/>
								<h:inputText id="pagominimoFiltro" value="#{LiqClienteBean.liqCliente.pagoMinimo}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="primerVtoFecha:" styleClass="texto"/>
								<h:inputText id="primervtofechaFiltro" value="#{LiqClienteBean.liqCliente.primerVtoFecha}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="primerVtoMonto:" styleClass="texto"/>
								<h:inputText id="primervtomontoFiltro" value="#{LiqClienteBean.liqCliente.primerVtoMonto}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="segundoVtoFecha:" styleClass="texto"/>
								<h:inputText id="segundovtofechaFiltro" value="#{LiqClienteBean.liqCliente.segundoVtoFecha}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="segundoVtoMonto:" styleClass="texto"/>
								<h:inputText id="segundovtomontoFiltro" value="#{LiqClienteBean.liqCliente.segundoVtoMonto}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="seguroDeudor:" styleClass="texto"/>
								<h:inputText id="segurodeudorFiltro" value="#{LiqClienteBean.liqCliente.seguroDeudor}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="tasaAdelanto:" styleClass="texto"/>
								<h:inputText id="tasaadelantoFiltro" value="#{LiqClienteBean.liqCliente.tasaAdelanto}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="tasaRefinanc:" styleClass="texto"/>
								<h:inputText id="tasarefinancFiltro" value="#{LiqClienteBean.liqCliente.tasaRefinanc}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="tercerVtoFecha:" styleClass="texto"/>
								<h:inputText id="tercervtofechaFiltro" value="#{LiqClienteBean.liqCliente.tercerVtoFecha}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="tercerVtoMonto:" styleClass="texto"/>
								<h:inputText id="tercervtomontoFiltro" value="#{LiqClienteBean.liqCliente.tercerVtoMonto}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>

								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarLiqCliente.show();"
								action="#{LiqClienteBean.listarLiqCliente}" 
								title="Busca el liqcliente seleccionado"
								image="/img/icon/srch_24.gif"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty LiqClienteBean.liqclienteList}">
							<f:verbatim>
								<display:table id="listaLiqCliente" name="sessionScope.LiqClienteBean.liqclienteList"
									defaultsort="1" pagesize="10"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarLiqCliente.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idLiqCliente" title="idLiqCliente" sortable="true" class="tdA"/>
										<display:column property="fechaCierre" title="fechaCierre" sortable="true" class="tdA"/>
										<display:column property="fechaCierreAnterior" title="fechaCierreAnterior" sortable="true" class="tdA"/>
										<display:column property="fechaLiq" title="fechaLiq" sortable="true" class="tdA"/>
										<display:column property="fechaProxCierre" title="fechaProxCierre" sortable="true" class="tdA"/>
										<display:column property="fechaProximoVto" title="fechaProximoVto" sortable="true" class="tdA"/>
										<display:column property="fechaVtoAnterior" title="fechaVtoAnterior" sortable="true" class="tdA"/>
										<display:column property="intComp" title="intComp" sortable="true" class="tdA"/>
										<display:column property="intPunitorios" title="intPunitorios" sortable="true" class="tdA"/>
										<display:column property="pago3cuotas" title="pago3cuotas" sortable="true" class="tdA"/>
										<display:column property="pago6cuotas" title="pago6cuotas" sortable="true" class="tdA"/>
										<display:column property="pagoMinimo" title="pagoMinimo" sortable="true" class="tdA"/>
										<display:column property="primerVtoFecha" title="primerVtoFecha" sortable="true" class="tdA"/>
										<display:column property="primerVtoMonto" title="primerVtoMonto" sortable="true" class="tdA"/>
										<display:column property="segundoVtoFecha" title="segundoVtoFecha" sortable="true" class="tdA"/>
										<display:column property="segundoVtoMonto" title="segundoVtoMonto" sortable="true" class="tdA"/>
										<display:column property="seguroDeudor" title="seguroDeudor" sortable="true" class="tdA"/>
										<display:column property="tasaAdelanto" title="tasaAdelanto" sortable="true" class="tdA"/>
										<display:column property="tasaRefinanc" title="tasaRefinanc" sortable="true" class="tdA"/>
										<display:column property="tercerVtoFecha" title="tercerVtoFecha" sortable="true" class="tdA"/>
										<display:column property="tercerVtoMonto" title="tercerVtoMonto" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaLiqCliente.idLiqCliente}','idLiqClienteHidden');javascript:clickLink('listadoLiqCliente:editarLiqClienteLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar liqcliente' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar liqcliente' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaLiqCliente.idLiqCliente}','idLiqClienteHidden');javascript:clickLink('listadoLiqCliente:eliminarLiqClienteLink')"
														onclick="return confirm('Confirma la eliminación del liqcliente.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar liqcliente' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar liqcliente' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="LiqCliente" />
										<display:setProperty name="paging.banner.items_name" value="LiqCliente" />
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
							<x:commandLink action="#{LiqClienteBean.editarLiqCliente}" id="editarLiqClienteLink" style="display: none;"/>
							<x:commandLink action="#{LiqClienteBean.eliminarLiqCliente}" id="eliminarLiqClienteLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idLiqClienteHidden" forceId="true" value="#{LiqClienteBean.idLiqClienteHidden}"/>

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

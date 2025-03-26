<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de LoteComercio"/></title>
</head>
<%@include file="/inc/head.inc" %>
<body onbeforeunload="ShowWait('listadoLoteComercio');">
<center>
	<secure:check/>

	<h:form id="listadoLoteComercio">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
					<jsp:include page="/inc/navigation_test.jsp" />
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp" >
						<jsp:param name="tituloLargo" value="${LoteComercioBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${LoteComercioBean.tituloCorto}"/>
					</jsp:include>
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroLoteComercio" label=" Filtro LoteComercio" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="7">
								<h:outputText value="idLoteComercio:" styleClass="texto"/>
								<h:inputText id="idlotecomercioFiltro" value="#{LoteComercioBean.loteComercio.idLoteComercio}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="cantCupones:" styleClass="texto"/>
								<h:inputText id="cantcuponesFiltro" value="#{LoteComercioBean.loteComercio.cantCupones}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="fechaRecepcion:" styleClass="texto"/>
								<h:inputText id="fecharecepcionFiltro" value="#{LoteComercioBean.loteComercio.fechaRecepcion}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="montoTotal:" styleClass="texto"/>
								<h:inputText id="montototalFiltro" value="#{LoteComercioBean.loteComercio.montoTotal}"
								styleClass="bordeceldainferior" maxlength="2562" size="2562"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="codComercio:" styleClass="texto"/>
								<h:selectOneMenu id="lstCodComercio" value="#{LoteComercioBean.idCodComercioSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{LoteComercioBean.codcomerciosItems}"/>
								</h:selectOneMenu>

								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarLoteComercio.show();"
								action="#{LoteComercioBean.listarLoteComercio}" 
								title="Busca el lotecomercio seleccionado"
								image="/img/icon/srch_24.gif"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty LoteComercioBean.lotecomercioList}">
							<f:verbatim>
								<display:table id="listaLoteComercio" name="sessionScope.LoteComercioBean.lotecomercioList"
									defaultsort="1" pagesize="10"
									class="tableA"
									requestURI="/tarjetafiel/transacciones/listarLoteComercio.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idLoteComercio" title="idLoteComercio" sortable="true" class="tdA"/>
										<display:column property="cantCupones" title="cantCupones" sortable="true" class="tdA"/>
										<display:column property="fechaRecepcion" title="fechaRecepcion" sortable="true" class="tdA"/>
										<display:column property="montoTotal" title="montoTotal" sortable="true" class="tdA"/>
										<display:column property="codcomercio.label" title="CodComercio" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaLoteComercio.idLoteComercio}','idLoteComercioHidden');javascript:clickLink('listadoLoteComercio:editarLoteComercioLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar lotecomercio' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar lotecomercio' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaLoteComercio.idLoteComercio}','idLoteComercioHidden');javascript:clickLink('listadoLoteComercio:eliminarLoteComercioLink')"
														onclick="return confirm('Confirma la eliminación del lotecomercio.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar lotecomercio' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar lotecomercio' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="LoteComercio" />
										<display:setProperty name="paging.banner.items_name" value="LoteComercio" />
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
							<x:commandLink action="#{LoteComercioBean.editarLoteComercio}" id="editarLoteComercioLink" style="display: none;"/>
							<x:commandLink action="#{LoteComercioBean.eliminarLoteComercio}" id="eliminarLoteComercioLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idLoteComercioHidden" forceId="true" value="#{LoteComercioBean.idLoteComercioHidden}"/>

						</c:if>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
		</x:panelLayout>
	</h:form>
</center>
</body>
</html>
</f:view>

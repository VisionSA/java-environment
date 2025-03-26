<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de OrigenConcepto"/></title>
</head>
<%@include file="/inc/head.inc" %>
<body onbeforeunload="ShowWait('listadoOrigenConcepto');">
<center>
	<secure:check/>

	<h:form id="listadoOrigenConcepto">
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
						<jsp:param name="tituloLargo" value="${OrigenConceptoBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${OrigenConceptoBean.tituloCorto}"/>
					</jsp:include>
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroOrigenConcepto" label=" Filtro OrigenConcepto" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="7">
								<h:outputText value="idOrigenconcepto:" styleClass="texto"/>
								<h:inputText id="idorigenconceptoFiltro" value="#{OrigenConceptoBean.origenConcepto.idOrigenconcepto}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="generaCodAutorizacion:" styleClass="texto"/>
								<h:inputText id="generacodautorizacionFiltro" value="#{OrigenConceptoBean.origenConcepto.generaCodAutorizacion}"
								styleClass="bordeceldainferior" maxlength="1" size="1"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="idConcepto:" styleClass="texto"/>
								<h:selectOneMenu id="lstConcepto" value="#{OrigenConceptoBean.idConceptoSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{OrigenConceptoBean.conceptosItems}"/>
								</h:selectOneMenu>
								<h:outputText value="idOrigenes:" styleClass="texto"/>
								<h:selectOneMenu id="lstOrigenen" value="#{OrigenConceptoBean.idOrigenenSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{OrigenConceptoBean.origenenesItems}"/>
								</h:selectOneMenu>

								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarOrigenConcepto.show();"
								action="#{OrigenConceptoBean.listarOrigenConcepto}" 
								title="Busca el origenconcepto seleccionado"
								image="/img/icon/srch_24.gif"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty OrigenConceptoBean.origenconceptoList}">
							<f:verbatim>
								<display:table id="listaOrigenConcepto" name="sessionScope.OrigenConceptoBean.origenconceptoList"
									defaultsort="1" pagesize="10"
									class="tableA"
									requestURI="/tarjetafiel/transacciones/listarOrigenConcepto.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idOrigenconcepto" title="idOrigenconcepto" sortable="true" class="tdA"/>
										<display:column property="generaCodAutorizacion" title="generaCodAutorizacion" sortable="true" class="tdA"/>
										<display:column property="concepto.label" title="Concepto" sortable="true" class="tdA"/>
										<display:column property="origenen.label" title="Origenen" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaOrigenConcepto.idOrigenconcepto}','idOrigenConceptoHidden');javascript:clickLink('listadoOrigenConcepto:editarOrigenConceptoLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar origenconcepto' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar origenconcepto' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaOrigenConcepto.idOrigenconcepto}','idOrigenConceptoHidden');javascript:clickLink('listadoOrigenConcepto:eliminarOrigenConceptoLink')"
														onclick="return confirm('Confirma la eliminación del origenconcepto.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar origenconcepto' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar origenconcepto' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="OrigenConcepto" />
										<display:setProperty name="paging.banner.items_name" value="OrigenConcepto" />
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
							<x:commandLink action="#{OrigenConceptoBean.editarOrigenConcepto}" id="editarOrigenConceptoLink" style="display: none;"/>
							<x:commandLink action="#{OrigenConceptoBean.eliminarOrigenConcepto}" id="eliminarOrigenConceptoLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idOrigenConceptoHidden" forceId="true" value="#{OrigenConceptoBean.idOrigenConceptoHidden}"/>

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

<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>



<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Concepto"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('listadoConcepto').submit();
		};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		  
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
	</s:script>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoConcepto');">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ConceptoBean.tituloCorto}
    <small>${ConceptoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="listadoConcepto">
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

						<s:layoutingTitlePane id="filtroConcepto" label=" Filtro Concepto" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="7" align="center">
								<h:outputText value="ID Concepto:" styleClass="texto"/>
								<h:inputText id="idconceptoFiltro" value="#{ConceptoBean.idConceptoFiltro}"
								styleClass="bordeceldainferior" maxlength="4" size="4"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="Descripción:" styleClass="texto"/>
								<h:inputText id="descripcionFiltro" value="#{ConceptoBean.descripcionFiltro}"
								styleClass="bordeceldainferior" maxlength="70" size="70"
								style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
								<h:outputText value="Sucursal:" styleClass="texto" rendered="false"/>
								<h:selectOneMenu id="lstSucursal" value="#{ConceptoBean.idSucursalSeleccionada}" rendered="false" binding="#{ConceptoBean.sucSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);"  disabled="true">
									<f:selectItems value="#{ConceptoBean.sucursalItems}"/>
								</h:selectOneMenu>
                                <h:selectOneRadio value="#{ConceptoBean.tipoLista}" id="tipoConceptoParaFiltro">
								    <f:selectItem itemLabel="Concepto Comercios" itemValue="N" />
								    <f:selectItem itemLabel="Concepto Fiel" itemValue="S" />
							    </h:selectOneRadio>
							    <f:verbatim>&nbsp;</f:verbatim> 
								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarConcepto.show();"
								action="#{ConceptoBean.listarConcepto}" 
								title="Busca el concepto seleccionado"  styleClass="botones"
								/>


							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty ConceptoBean.conceptoList}">
							<f:verbatim>
                                <center>
								<display:table id="listaConcepto" name="sessionScope.ConceptoBean.conceptoList" 
									defaultsort="1" pagesize="10"
									class="tableA-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarConcepto.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 700px; align:center;">

										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaConcepto.ccc.idConcepto}','idConceptoHidden');javascript:clickLink('listadoConcepto:eliminarConceptoLink')"
														onclick="return confirm('Confirma la eliminación del concepto.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar concepto' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar concepto' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column property="ccc.codigoConcepto" title="Cod. Concepto" sortable="true" class="tdB"/>
										<display:column property="ccc.descripcion" title="Descripcion" sortable="true" class="tdA"/>
										<display:column property="ccc.target" title="Target" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaConcepto.ccc.idConcepto}','idConceptoHidden');javascript:clickLink('listadoConcepto:editarConceptoLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar concepto' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar concepto' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										
										<display:column style="width: 25px;" media="html" title="Clientes" class="tdC">
										    <c:if test="${listaConcepto.tieneClientes}" >
										    <a href="javascript:viewUser('${listaConcepto.ccc.idConcepto}','idConceptoHidden');javascript:clickLink('listadoConcepto:irAClientesLink')">
												<img src='<%=request.getContextPath()%>/img/btn_client-templates_bg.gif' title='Clientes' border='0' style=" width : 16px; height : 16px;"/>
											</a>
											</c:if>
										</display:column>
										<c:if test="${!ConceptoBean.esFiel}">
											<display:column style="width: 25px;"  media="html" title="Comercios" class="tdC">
											    <c:if test="${listaConcepto.tieneComercios}" >
											    <a href="javascript:viewUser('${listaConcepto.ccc.idConcepto}','idConceptoHidden');javascript:clickLink('listadoConcepto:irAComerciosLink')">
													<img src='<%=request.getContextPath()%>/img/icon/houses 32.png' title='Comercios' border='0' style=" width : 16px; height : 16px;" />
												</a>
												</c:if>
											</display:column>
										</c:if>
											<display:column style="width: 25px;" media="html" title="Orígenes" class="tdC">
											    <a href="javascript:viewUser('${listaConcepto.ccc.idConcepto}','idConceptoHidden');javascript:clickLink('listadoConcepto:irAOrigenesLink')">
													<img src='<%=request.getContextPath()%>/img/icon/book_open 16.png' title='Origenes' border='0' style=" width : 16px; height : 16px;" />
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
										<display:setProperty name="paging.banner.item_name" value="Concepto" />
										<display:setProperty name="paging.banner.items_name" value="Concepto" />
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
                                </center>
							</f:verbatim>

							<%-- Link oculto para eliminar o editar --%>
							<x:commandLink action="#{ConceptoBean.editarConcepto}" id="editarConceptoLink" style="display: none;"/>
							<x:commandLink action="#{ConceptoBean.eliminarConcepto}" id="eliminarConceptoLink" style="display: none;"/>
							<x:commandLink action="#{ConceptoBean.irAClientes}" id="irAClientesLink" style="display: none;"/>
							<x:commandLink action="#{ConceptoBean.irAComercios}" id="irAComerciosLink" style="display: none;"/>
							<x:commandLink action="#{ConceptoBean.irAOrigenes}" id="irAOrigenesLink" style="display: none;"/>
							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idConceptoHidden" forceId="true" value="#{ConceptoBean.idConceptoHidden}"/>
						</c:if>
						<h:panelGrid columns="9" align="center" id="btnAgregar">
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						     <h:commandButton action="#{ConceptoBean.inicializar}"  value="Agregar Concepto" id="cdbtnAgregar" styleClass="botones"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

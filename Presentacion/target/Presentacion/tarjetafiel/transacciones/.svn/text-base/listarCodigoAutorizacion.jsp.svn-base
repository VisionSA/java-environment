<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Código Autorización"/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCodigoAutorizacion');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${CodigoAutorizacionBean.tituloCorto}
    <small>${CodigoAutorizacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>


<center>
<secure:check/>

	<h:form id="listadoCodigoAutorizacion">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

	

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" width="900" id="principal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroCodigoAutorizacion" label=" Filtro Codigo Autorizacion" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="4" align="center" width="600"> 
							
								<h:outputText value="Id. Cód. Aut: " styleClass="texto" id="codAutor"/>
								<h:inputText id="idcodigoautorizacionFiltro" value="#{CodigoAutorizacionBean.codigoAutorizacion.idCodigoautorizacion}" onkeypress="return soloEnteros(this,event);"
									styleClass="bordeceldainferior" maxlength="10" size="100" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Código: " styleClass="texto" />
								<h:inputText id="codigoFiltro" value="#{CodigoAutorizacionBean.codigoAutorizacion.codigo}"
									styleClass="bordeceldainferior" maxlength="8" size="8" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
									
								<h:outputText value="Fecha: " styleClass="texto" id="fecha"/>
								<x:inputCalendar id="Fecha" monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
			                		currentDayCellClass="currentDayCell" value="#{CodigoAutorizacionBean.fecha}" renderAsPopup="true"
					                styleClass="bordeceldainferior" style="width: 100px" immediate="true"
			        		        popupDateFormat="dd/MM/yyyy" helpText="DD/MM/YYYY" forceId="true"/>
			        		       
			        		     <h:outputText value="Origen:" styleClass="texto" id="origen"/>
								<h:selectOneMenu id="lstOrigenen" value="#{CodigoAutorizacionBean.idOrigenenSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 150">
									<f:selectItems value="#{CodigoAutorizacionBean.origenItems}"/>
								</h:selectOneMenu>
								
							</h:panelGrid>
							<f:verbatim><br></f:verbatim>
							<h:panelGrid id="filtroDos" columns="3" align="center" width="600"> 

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarCodigoAutorizacion.show();"
									action="#{CodigoAutorizacionBean.listarCodigoAutorizacion}" title="Busca el codigoautorizacion seleccionado"
									styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty CodigoAutorizacionBean.codigoAutorizacionList}">
							<f:verbatim>
								<display:table id="listaCodigoAutorizacion" 
									name="sessionScope.CodigoAutorizacionBean.codigoAutorizacionList"
									defaultsort="1" 
									pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarCodigoAutorizacion.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" 
									requestURIcontext="true" 
									style="width: 900px;">

										<display:column property="codigoAutorizacion.idCodigoautorizacion" title="Id" sortable="true" class="tdB"/>
										<display:column property="codigoAutorizacion.codigo" title="Código" sortable="true" class="tdB"/>
										<display:column property="fecha" title="Fecha" sortable="true" class="tdB"/>
										<display:column property="codigoAutorizacion.origenen.descripcion" title="Origen" sortable="true" class="tdB"/>
										<display:column style="width: 25px;" media="html" >
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaCodigoAutorizacion.indice}','idCodigoAutorizacionHidden');javascript:clickLink('listadoCodigoAutorizacion:editarCodigoAutorizacionLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Código Autorización' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Código Autorización' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaCodigoAutorizacion.indice}','idCodigoAutorizacionHidden');javascript:clickLink('listadoCodigoAutorizacion:eliminarCodigoAutorizacionLink')"
														onclick="return confirm('Confirma la eliminación del Código Autorización.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Código Autorización' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Código Autorización' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Código Autorización" />
										<display:setProperty name="paging.banner.items_name" value="Código Autorización" />
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
							<x:commandLink action="#{CodigoAutorizacionBean.editarCodigoAutorizacion}" id="editarCodigoAutorizacionLink" style="display: none;"/>
							<x:commandLink action="#{CodigoAutorizacionBean.eliminarCodigoAutorizacion}" id="eliminarCodigoAutorizacionLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idCodigoAutorizacionHidden" forceId="true" value="#{CodigoAutorizacionBean.idCodigoAutorizacionHidden}"/>

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

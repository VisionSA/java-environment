<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Chequera"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoChequera');">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ChequeraBean.tituloCorto}
    <small>${ChequeraBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="listadoChequera">
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
						
						<s:layoutingTitlePane id="filtroChequera" 
							label=" Filtro Chequera" containerNodeClass="contentTitlePane" 
							labelNodeClass="labelTitlePane" >
							
							<h:panelGrid id="filtroUno" columns="8" align="center" width="600">
								<h:outputText value="Id Chequera:" styleClass="texto"/>
								<h:inputText id="idChequeraFiltro" value="#{ChequeraBean.idChequera}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>
								<h:outputText value="Cuenta Bancaria" styleClass="texto"/>
								<h:selectOneMenu id="lstBancoPropio" value="#{ChequeraBean.idBancoPropioSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ChequeraBean.bancoPropioItems}"/>
								</h:selectOneMenu>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarChequera.show();"
								action="#{ChequeraBean.listarChequera}" title="Busca la chequera seleccionada" styleClass="botones"/>
								
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>

						<f:verbatim>&nbsp;</f:verbatim>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty ChequeraBean.chequeraList}">
							<f:verbatim>
								<display:table id="listaChequera" name="sessionScope.ChequeraBean.chequeraList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/fondos/listarChequera.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idChequera" title="Id Chequera" sortable="true" class="tdB"/>
										<display:column property="bancoPropio.label" title="Cuenta" sortable="true" class="tdA"/>
										<display:column property="nroInicio" title="Nro Inicio" sortable="true" class="tdB"/>
										<display:column property="nroFin" title="Nro Fin" sortable="true" class="tdB"/>
										<display:column property="proximoAUsar" title="Proximo a usar" sortable="true" class="tdB"/>
										<display:column property="habilitado" title="Habilitada" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
											   <c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaChequera.idChequera}','idChequeraHidden');javascript:clickLink('listadoChequera:editarChequeraLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Chequera' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Chequera' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaChequera.idChequera}','idChequeraHidden');javascript:clickLink('listadoChequera:eliminarChequeraLink')"
														onclick="return confirm('Confirma la eliminación de la Chequera');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Chequera' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Chequera' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Chequera" />
										<display:setProperty name="paging.banner.items_name" value="Chequeras" />
										<display:setProperty name="paging.banner.some_items_found">
											<div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontradas, mostrando desde la {2} hasta la {3}</div>
										</display:setProperty>
										<display:setProperty name="paging.banner.no_items_found">
											<div class="pagebanner">No se encontraron {0}.</div>
										</display:setProperty>						    
										<display:setProperty name="paging.banner.one_item_found">
											<div class="pagebanner">Una {0} encontrada.</div>
										</display:setProperty>						    				
										<display:setProperty name="paging.banner.all_items_found">
											<div class="pagebanner">{0} {1} encontradas, mostrando todas las {2}.</div>
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
							<x:commandLink action="#{ChequeraBean.editarChequera}" id="editarChequeraLink" style="display: none;"/>
							<x:commandLink action="#{ChequeraBean.eliminarChequera}" id="eliminarChequeraLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idChequeraHidden" forceId="true" value="#{ChequeraBean.idChequeraHidden}"/>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ChequeraBean.irAListarChequera}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>

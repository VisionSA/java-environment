<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel | Listado de Plasticos"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoPlasticoCliente');">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Transacción
    <small>Listas</small>
  </h1>
</section>

<section class="content">

	<div class="box box-default">
	<div class="box-header with-border">
		<h3 class="box-title">Listar Plásticos Clientes</h3>
	</div>
	<br/>

<center>
<secure:check/>

	<h:form id="listadoPlasticoCliente">
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

						<s:layoutingTitlePane id="filtroPlasticoCliente" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid columns="1" id="groups">
							<h:panelGrid id="filtroUno" columns="3">								
								<h:outputText value="Estado:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstestado" value="#{PlasticoClienteBean.estadoFiltro}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" >
									<f:selectItems value="#{PlasticoClienteBean.estadoItems}"/>
								</h:selectOneMenu>
                            </h:panelGrid> 
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:panelGrid id="filtroCuatro" columns="7">	
								<h:outputText value="Número:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:inputText id="numeroFiltro" value="#{PlasticoClienteBean.plasticoCliente.numero}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 250px" onfocus="encender(this);" onblur="apagar(this);"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText value="Pin:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:inputText id="pinFiltro" value="#{PlasticoClienteBean.plasticoCliente.pin}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 250px" onfocus="encender(this);" onblur="apagar(this);"/>
							</h:panelGrid> 	
							<f:verbatim>&nbsp;</f:verbatim>	
								
									 <h:panelGrid columns="3" id="fechas" align="center">
											<h:outputText value="Última modificación:" styleClass="texto"/>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>
										            <div class="input-group date">
										                <div class="input-group-addon">
										                    <i class="fa fa-calendar"></i>
										                </div>
										                <input type="text" class="form-control pull-right" id="fMod" autocomplete="off">
										            </div>
											</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText value="Vigencia Desde:" styleClass="texto"/>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									 		<f:verbatim>
										            <div class="input-group date">
										                <div class="input-group-addon">
										                    <i class="fa fa-calendar"></i>
										                </div>
										                <input type="text" class="form-control pull-right" id="fDesde" autocomplete="off">
										            </div>
											</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText value="Vigencia Hasta:" styleClass="texto"/>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									 		<f:verbatim>
											        <div class="input-group date">
											            <div class="input-group-addon">
											                <i class="fa fa-calendar"></i>
											            </div>
											            <input type="text" class="form-control pull-right" id="fHasta" autocomplete="off">
											        </div>
											</f:verbatim>
	                                </h:panelGrid>
	                                <f:verbatim>&nbsp;</f:verbatim>
                                    <h:panelGrid columns="3">
                                         <h:outputText value="Incluir Fechas en la búsqueda:" />
                                         <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                         <h:selectBooleanCheckbox value="#{PlasticoClienteBean.incluirFechasEnBusqueda}" id="incluirFec" binding="#{PlasticoClienteBean.bindinFecha}"/>
                                    </h:panelGrid> 
                                    <x:commandButton id="btnBuscar" 
											value="Buscar" onclick="agregarPlasticoCliente.show();"
											action="#{PlasticoClienteBean.listarPlasticoCliente}" 
											title="Busca el plasticocliente seleccionado"
											styleClass="btn btn-primary btn-flat pull-right"/>								

							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty PlasticoClienteBean.plasticoclienteList}">
							<f:verbatim>
								<display:table id="listaPlasticoCliente" name="sessionScope.PlasticoClienteBean.plasticoclienteList"
									defaultsort="1" pagesize="10"
									class="tableA"
									requestURI="/tarjetafiel/transacciones/listarPlasticoCliente.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idPlasticocliente" title="idPlasticocliente" sortable="true" class="tdA"/>
										<display:column property="estadoPlastico.descripcion" title="estadoPlastico" sortable="true" class="tdA"/>
										<display:column property="clienteTransaccion.nombreCliente" title="Cliente" sortable="true" class="tdA"/>
										<display:column property="numero" title="numero" sortable="true" class="tdA"/>
										<display:column property="pin" title="pin" sortable="true" class="tdA"/>
										<display:column property="ultimamodif" title="ultimamodif" sortable="true" class="tdA"/>
										<display:column property="vigenciaDesde" title="vigenciaDesde" sortable="true" class="tdA"/>
										<display:column property="vigenciaHasta" title="vigenciaHasta" sortable="true" class="tdA"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaPlasticoCliente.idPlasticocliente}','idPlasticoClienteHidden');javascript:clickLink('listadoPlasticoCliente:editarPlasticoClienteLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar plasticocliente' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar plasticocliente' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaPlasticoCliente.idPlasticocliente}','idPlasticoClienteHidden');javascript:clickLink('listadoPlasticoCliente:eliminarPlasticoClienteLink')"
														onclick="return confirm('Confirma la eliminación del plasticocliente.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar plasticocliente' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar plasticocliente' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="PlasticoCliente" />
										<display:setProperty name="paging.banner.items_name" value="PlasticoCliente" />
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
							<x:commandLink action="#{PlasticoClienteBean.editarPlasticoCliente}" id="editarPlasticoClienteLink" style="display: none;"/>
							<x:commandLink action="#{PlasticoClienteBean.eliminarPlasticoCliente}" id="eliminarPlasticoClienteLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idPlasticoClienteHidden" forceId="true" value="#{PlasticoClienteBean.idPlasticoClienteHidden}"/>

						</c:if>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>

    <h:inputText id="FechaDesde" value="#{PlasticoClienteBean.vigenciaDesdeFiltro}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{PlasticoClienteBean.vigenciaHastaFiltro}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>
    <h:inputText id="FechaMod" value="#{PlasticoClienteBean.ultimaModificacionFiltro}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>		

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{PlasticoClienteBean.irAListarPlasticosCliente}") + `</li>`;
}
</script>
<%@include file="/inc/scripts.jsp" %>
<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "bottom"
    });

    $("#fMod").datepicker({
      autoclose: true,
      orientation: "bottom"
    });


	//Seteo fechas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("listadoPlasticoCliente:FechaDesde").value.split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	$("#fDesde").datepicker("setDate", new Date(year+"-"+month+"-"+date));

	fd = document.getElementById("listadoPlasticoCliente:FechaHasta").value.split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	$("#fHasta").datepicker("setDate", new Date(year+"-"+month+"-"+date));

	fd = document.getElementById("listadoPlasticoCliente:FechaMod").value.split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	$("#fMod").datepicker("setDate", new Date(year+"-"+month+"-"+date));	

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        fd.setDate(fd.getDate() + 1);
        document.getElementById("listadoPlasticoCliente:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd);
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
        fh.setDate(fh.getDate() + 1);
		document.getElementById("listadoPlasticoCliente:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh);
    });

    $("#fMod").change(function() {
        var fh = $(this).datepicker("getDate");
        fh.setDate(fh.getDate() + 1);
		document.getElementById("listadoPlasticoCliente:FechaMod").value = $.datepicker.formatDate('dd/mm/yy', fh);
    });    


  });
</script>
</body>
</html>
</f:view>

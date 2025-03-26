<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Colaboradores"/></title>
</head>
<jsp:include page="/inc/includes.jsp"/>
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoColaboradores');">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ColaboradorBean.tituloCorto}
    <small>${ColaboradorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

<secure:check/>
	<h:form id="listadoColaborador">
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

						<s:layoutingTitlePane id="filtroColaborador" label=" Filtro Colaborador" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="2" align="center">
								<h:panelGroup id="grupo">
								<h:panelGrid id="suFiltro" columns="14" align="center"> 
 						            <h:outputText value="Sucursal:" styleClass="texto"/>
									<h:selectOneMenu id="lstSucursal" value="#{ColaboradorBean.idSucursalSeleccionada}" disabled="true"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);">
										<f:selectItems value="#{ColaboradorBean.sucursalItems}"/>
									</h:selectOneMenu>
									<h:outputText value="Estado Colaborador:" styleClass="texto"/>
									<h:selectOneMenu id="lstestadoColaborador" value="#{ColaboradorBean.estadoFiltro}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" >
										<f:selectItems value="#{ColaboradorBean.listEstadoColaborador}"/>
									</h:selectOneMenu>
					                <h:outputText value="Promotor" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.promotorFiltro}" id="booPromFiltro"/>
					                <h:outputText value="Verificador" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.verificadorFiltro}" id="booVeriFiltro"/>
                                    <h:outputText value="Cobrador" styleClass="texto"/>
					                <h:selectBooleanCheckbox value="#{ColaboradorBean.cobradorFiltro}" id="booCobrFiltro"/>
					                <h:outputText value="Abogado" styleClass="texto"/>
			                		<h:selectBooleanCheckbox value="#{ColaboradorBean.abogadoFiltro}" id="booAbogFiltro" />
									<h:outputText value="Cajero" styleClass="texto"/>
			                		<h:selectBooleanCheckbox value="#{ColaboradorBean.cajeroFiltro}" id="booCajeFiltro" />
 						                
 						       </h:panelGrid>

 						       <f:verbatim><br/></f:verbatim>

 						       <h:panelGrid id="panSoproFiltro" columns="3" align="center">
 						             <h:panelGrid id="fech" columns="6" >
 						       			<h:outputText value="Fecha de Alta " id="outAltaFiltro" styleClass="texto"/>
									 	<f:verbatim>
										    <div class="input-group date">
										        <div class="input-group-addon">
										            <i class="fa fa-calendar"></i>
										        </div>
										        <input type="text" class="form-control pull-right" id="fDesde" autocomplete="off">
										    </div>
										</f:verbatim>
 						       			<h:outputText value="Fecha de Baja" id="outgbajaFiltro" styleClass="texto"/>
								 		<f:verbatim>
										    <div class="input-group date">
										        <div class="input-group-addon">
										            <i class="fa fa-calendar"></i>
										        </div>
										        <input type="text" class="form-control pull-right" id="fHasta" autocomplete="off">
										    </div>
										</f:verbatim>
							            <h:outputText id="incluirfe" value="Incluir Rango Fechas: "/>
							            <h:selectBooleanCheckbox value="#{ColaboradorBean.incluyeFechasFiltro}" />
							        </h:panelGrid>
 						       		<h:outputText id="outNroLegajo" value="Número de Legajo: " styleClass="texto" />
									<x:inputText id="inLegadjoFiltro" value="#{ColaboradorBean.legajoFiltro}" forceId="true" size="50"
											maxlength="100" styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
 						       			
 						       			
 						       </h:panelGrid>
                               </h:panelGroup>
                               <h:panelGroup>
                                <h:panelGrid columns="3" id="alineador">
                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" 
								value="Buscar" onclick="agregarColaborador.show();"
								action="#{ColaboradorBean.listarColaborador}" 
								title="Busca el Colaborador seleccionado"
								image="/img/icon/srch_24.gif"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
                                </h:panelGroup>
							</h:panelGrid>
						</s:layoutingTitlePane>

						<c:if test="${!empty ColaboradorBean.colaboradorList}">
							<f:verbatim>
								<display:table id="listaColaborador" name="sessionScope.ColaboradorBean.colaboradorList"
									defaultsort="1" pagesize="10"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/transacciones/listarColaborador.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="nroLegajo" title="Legajo" sortable="true" class="tdA"/>
										<display:column property="individuo.apellido" title="Apellido" sortable="true" class="tdA"/>
										<display:column property="individuo.cuil" title="Cuit" sortable="true" class="tdA"/>
										<display:column property="estado" title="Estado" sortable="true" class="tdA"/>
										
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaColaborador.idColaborador}','idColaboradorHidden');javascript:clickLink('listadoColaborador:editarColaboradorLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Colaborador' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Colaborador' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaColaborador.idColaborador}','idColaboradorHidden');javascript:clickLink('listadoColaborador:eliminarColaboradorLink')"
														onclick="return confirm('Confirma la eliminación del colaborador.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar colaborador' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Colaborador' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="Colaborador" />
										<display:setProperty name="paging.banner.items_name" value="Colaborador" />
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
							<x:commandLink action="#{ColaboradorBean.editarColaborador}" id="editarColaboradorLink" style="display: none;"/>
							<x:commandLink action="#{ColaboradorBean.eliminarColaborador}" id="eliminarColaboradorLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idColaboradorHidden" forceId="true" value="#{ColaboradorBean.idColaboradorHidden}"/>

						</c:if>

						<f:verbatim><br/></f:verbatim>

						<h:commandButton action="#{ColaboradorBean.inicializar}"  value="Agregar Colaborador" id="cdbtnAgregar" styleClass="btn btn-primary btn-flat pull-right"/>

					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>


	<h:inputText id="FechaDesde" value="#{ColaboradorBean.fechaAltaFiltro}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{ColaboradorBean.fechaBajaFiltro}" style="display: none;">
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

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ColaboradorBean.irAListarColaborador}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>    


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "buttom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "buttom"
    });


	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("listadoColaborador:FechaDesde").value.split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	$("#fDesde").datepicker("setDate", new Date(year+"-"+month+"-"+date));

	fd = document.getElementById("listadoColaborador:FechaHasta").value.split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	$("#fHasta").datepicker("setDate", new Date(year+"-"+month+"-"+date));

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        fd.setDate(fd.getDate() + 1);
        document.getElementById("listadoColaborador:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd);
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
        fh.setDate(fh.getDate() + 1);
		document.getElementById("listadoColaborador:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh);
    });


  });
</script>

</body>
</html>
</f:view>

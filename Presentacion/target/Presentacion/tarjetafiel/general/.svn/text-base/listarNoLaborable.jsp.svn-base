<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel | Listado de NoLaborable"/></title>
</head>
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoNoLaborable');">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${NoLaborableBean.tituloCorto}
    <small>${NoLaborableBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
<secure:check/>

	<h:form id="listadoNoLaborable">
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
						<s:layoutingTitlePane id="filtroNoLaborable" label=" Filtro" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<f:verbatim><br></f:verbatim>
							<h:panelGrid id="filtroUno" columns="9" align="center">
							
								<h:outputText value="Id No Laborable:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:inputText id="idnolaborableFiltro" value="#{NoLaborableBean.idNoLaboral}"
								styleClass="bordeceldainferior" maxlength="2560" size="2560"
								style="width: 50px" onfocus="encender(this);" onblur="apagar(this);"
								onkeypress="return soloEnteros(this,event);"/>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								
								<h:outputText value="Fecha:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>
					                <div class="input-group date">
					                    <div class="input-group-addon">
					                        <i class="fa fa-calendar"></i>
					                    </div>
					                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA">
					                </div>
								</f:verbatim>
					            
					            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					            
					            <x:commandButton id="btnBuscar" value="Buscar" onclick="agregarNoLaborable.show();"	action="#{NoLaborableBean.listarNoLaborable}" 
										title="Busca el nolaborable seleccionado" styleClass="btn btn-primary btn-flat"/>
										

								
							</h:panelGrid>
							<f:verbatim><br></f:verbatim>
						</s:layoutingTitlePane>
						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						<c:if test="${!empty NoLaborableBean.noLaborableList}">
							<f:verbatim>
								<display:table id="listaNoLaborable" name="sessionScope.NoLaborableBean.noLaborableList"
									defaultsort="1" pagesize="25"
									class="table-bordered table-striped"
									requestURI="/tarjetafiel/general/listarNoLaborable.jsf"
									export="${lst:contains(requestScope.permisos,'exportacion')}" requestURIcontext="true" style="width: 900px;">

										<display:column property="idNoLaborable" title="Id No Laborable" sortable="true" class="tdB"/>
										<display:column property="esFeriado" title="Es Feriado" sortable="true" class="tdA"/>
										<display:column property="esBancario" title="Bancario" sortable="true" class="tdA"/>
										<display:column property="esNacional" title="Nacional" sortable="true" class="tdA"/>
										<display:column property="esProvincial" title="Provincial" sortable="true" class="tdA"/>
										<display:column property="fecha" title="Fecha" sortable="true" class="tdB" format="{0,date,dd-MM-yyyy}"/>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
													<a href="javascript:viewUser('${listaNoLaborable.idNoLaborable}','idNoLaborableHidden');javascript:clickLink('listadoNoLaborable:editarNoLaborableLink')">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Día no Laborable' border='0' />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Día no Laborable' border='0'/>
													</a>
												</c:otherwise>
											</c:choose>
										</display:column>
										<display:column style="width: 25px;" media="html">
											<c:choose>
												<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
													<a href="javascript:viewUser('${listaNoLaborable.idNoLaborable}','idNoLaborableHidden');javascript:clickLink('listadoNoLaborable:eliminarNoLaborableLink')"
														onclick="return confirm('Confirma la eliminación del Día no Laborable');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Día no Laborable' border='0'/>
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="return alert('No posee los permisos necesarios.');">
														<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Día no Laborable' border='0'/>
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
										<display:setProperty name="paging.banner.item_name" value="NoLaborable" />
										<display:setProperty name="paging.banner.items_name" value="NoLaborable" />
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
							<x:commandLink action="#{NoLaborableBean.editarNoLaborable}" id="editarNoLaborableLink" style="display: none;"/>
							<x:commandLink action="#{NoLaborableBean.eliminarNoLaborable}" id="eliminarNoLaborableLink" style="display: none;"/>

							<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
							<x:inputHidden id="idNoLaborableHidden" forceId="true" value="#{NoLaborableBean.idNoLaborableHidden}"/>

						</c:if>
						</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>

		
	<h:inputText id="FechaDesde" value="#{NoLaborableBean.fecha}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{NoLaborableBean.irAListarNoLaborable}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        fd.setDate(fd.getDate() + 1);
        document.getElementById("listadoNoLaborable:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd);
    });

  });
</script>


</body>
</html>
</f:view>

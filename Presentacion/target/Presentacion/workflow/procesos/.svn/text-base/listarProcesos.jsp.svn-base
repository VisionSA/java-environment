<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de Procesos"/></title>
</head>

<%@include file="/inc/includes.jsp" %>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoProcesos');">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${AperturaCajaBean.tituloCorto}
    <small>${AperturaCajaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<h:form id="listadoPorcesos">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter">


        <f:facet name="body">
            <h:panelGroup id="body">

			<h:panelGrid columns="1" align="center">
			<h:panelGroup id="errores">
				<jsp:include page="/inc/error.jsp" />
			</h:panelGroup>
				<s:layoutingTitlePane id="filtroProcesos" label=" Filtro Procesos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >	
					<f:verbatim><br></f:verbatim>		
					
					<h:panelGrid id="filtroUno" columns="7" width="500" align="center">
						
						<h:outputText value="Proceso:" styleClass="texto"/>
						
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						
						<h:inputText id="procesoFiltro" value="#{ProcesoBean.nombreFiltro}" 
			               			 styleClass="bordeceldatext" maxlength="45" size="30" 
			               			 style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>       		            
					   
					   <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

			           <x:commandButton id="btnBuscarProceso" value="Buscar Procesos" onclick="agregarProceso.show();"
			       						 action="#{ProcesoBean.listarProcesos}" title="Busca la tarea seleccionada"
			       						 styleClass="btn btn-primary btn-flat" />
			       		
			       				       		
			       		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

			       		<x:commandButton id="buttonAgregarProceso" value="Agregar Proceso"
							action="#{ProcesoBean.inicializar}" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty ProcesoBean.procesos}">	
        	<f:verbatim>
		       	<display:table id="listaProceso" name="sessionScope.ProcesoBean.procesos" 
		       				   defaultsort="1" pagesize="25"
		       				   class="table-bordered table-striped" 
		       				   requestURI="/workflow/procesos/listarProcesos.jsf"
		       				   export="true" requestURIcontext="true" style="width: 900px;">
		       		<display:column property="idProceso" title="Código" sortable="true" class="tdB"/>
		       		<display:column property="titulo" title="Titulo" sortable="true" class="tdA"/>
		       		<display:column property="comentario" title="Comentario" sortable="true" class="tdA"/>
		       		<display:column property="version" title="Version" sortable="true" class="tdB"/>
		       		<display:column property="revision" title="Revision" sortable="true" class="tdB"/>
		       		<display:column property="rol.descripcion" title="Descripción" sortable="true" class="tdA"/>
		       		<display:column property="supervisorDef.apellido" title="Supervisor" sortable="true" class="tdA"/>
		       		<display:column title="Activo" class="tdA" media="html">
		       			<c:if test="${listaProceso.activo}">
		       				Si<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:cambiarEstadoLink')">
								<img src='<%=request.getContextPath()%>/img/icon/replace2.png' title='Desactiva el proceso' border='0'/>
							  </a>
		       			</c:if>
						<c:if test="${!listaProceso.activo}">
							No<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:cambiarEstadoLink')">
								<img src='<%=request.getContextPath()%>/img/icon/replace2.png' title='Activa el proceso' border='0'/>
						  	</a>
						</c:if>
		       		</display:column>
		       		<display:column title="En Test" class="tdA" media="html">
		       			<c:if test="${!listaProceso.discontinuado}">
			       			<c:if test="${!listaProceso.enTest}">
			       				<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:cambiarTestLink')">
									<img src='<%=request.getContextPath()%>/img/disable.gif' title='Activa para testeo' border='0'/>
								  </a>
			       			</c:if>
							<c:if test="${listaProceso.enTest}">
								<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:cambiarTestLink')">
									<img src='<%=request.getContextPath()%>/img/enable.gif' title='Desactiva para testeo' border='0'/>
							  	</a>
							</c:if>
						</c:if>
		       		</display:column>
		       		<display:column property="timestamp" title="Creado" sortable="true" class="tdA"/>
					<display:column style="width: 25px;" media="html">
						<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:editarProcesoLink')">
							<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Proceso' border='0' onclick="return confirm('Mientras se este editando el proceso permanecera inactivo. ¿Desea continuar?');"/>
						</a>
					</display:column>
					<display:column style="width: 25px;" media="html">
						<a href="javascript:viewUser('${listaProceso.idProceso}','procesoHidden');javascript:clickLink('listadoPorcesos:eliminarProcesoLink')" 
						onclick="return confirm('¿Confirma la eliminación del proceso?');">
							<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Proceso' border='0'/>
						</a>
					</display:column>					
							
				    <display:setProperty name="export.amount" value="list" />
				    <display:setProperty name="export.xml" value="true" />
				    <display:setProperty name="export.pdf" value="true" />
				    <display:setProperty name="export.excel.include_header" value="true" />
				    <display:setProperty name="export.banner">
						<div class="exportlinks" style="width: 892px;">Exportar a: {0}</div>
				    </display:setProperty>
				    
				    <display:setProperty name="basic.show.header" value="true" />
				    						    
				    <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
				    <display:setProperty name="sort.amount" value="list" />
				    						    
				    <display:setProperty name="paging.banner.group_size" value="6" />
				    <display:setProperty name="paging.banner.placement" value="bottom" />
				    <display:setProperty name="paging.banner.item_name" value="Proceso" />
				    <display:setProperty name="paging.banner.items_name" value="Procesos" />
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
					
			<%-- Link oculto para eliminar o editar un proveedor --%>
			<x:commandLink action="#{ProcesoBean.editarProceso}" id="editarProcesoLink" style="display: none;"/>
			<x:commandLink action="#{ProcesoBean.eliminarProceso}" id="eliminarProcesoLink" style="display: none;"/>
			<x:commandLink action="#{ProcesoBean.cambiarEstado}" id="cambiarEstadoLink" style="display: none;"/>
			<x:commandLink action="#{ProcesoBean.cambiarTest}" id="cambiarTestLink" style="display: none;"/>

			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="procesoHidden" forceId="true" value="#{ProcesoBean.idProcesoHidden}"/>
        		
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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ProcesoBean.inicializarListarProcesos}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>

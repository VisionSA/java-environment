<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de Tareas"/></title>
</head>

<%@include file="/inc/includes.jsp" %>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoTareas');">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${TareaBean.tituloCorto}
    <small>${TareaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<h:form id="listadoTareas">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body">
            <h:panelGroup id="body">


			<h:panelGrid columns="1" align="center">
			
				<s:layoutingTitlePane id="filtroTareas" label=" Filtro Tareas" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >			
					<h:panelGrid id="filtroUno" columns="4" align="center">
						
						<h:outputText value="Tarea:" styleClass="texto"/>
						<h:inputText id="tareaFiltro" value="#{TareaBean.tituloFiltro}" 
			               			 styleClass="bordeceldatext" maxlength="45" size="30" 
			               			 style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>       		            
					               			 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					               			 
			       		<x:commandButton id="btnBuscarTarea" value="Buscar" onclick="agregarTarea.show();" action="#{TareaBean.filtarTareas}" 
			       			title="Busca la tarea seleccionada" styleClass="botones"/>
					</h:panelGrid>
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty TareaBean.tareas}">	
        	<f:verbatim>
		       	<display:table id="listaTareas" name="sessionScope.TareaBean.tareas" 
		       				   defaultsort="1" pagesize="25"
		       				   class="tableA" 
		       				   requestURI="/workflow/tareas/listarTareas.jsf"
		       				   export="true" requestURIcontext="true" style="width: 900px;">
		       		<display:column property="idTarea" title="Código" sortable="true" class="tdB"/>
		       		<display:column property="titulo" title="Titulo" sortable="true" class="tdA"/>
		       		<display:column property="comentario" title="Comentario" sortable="true" class="tdA"/>
		       		<display:column property="tipoTarea.descripcion" title="Descripción Tipo Tarea" sortable="true" class="tdA"/>
		       		<display:column property="activo" title="Estado Activo" class="tdA" />
		       		<display:column property="duracion" title="Duración" sortable="true" class="tdB"/>
		       		<display:column style="width: 25px;" media="html">
						<a href="javascript:viewUser('${listaTareas.idTarea}','tareaHidden');javascript:clickLink('listadoTareas:editarTareaLink')">
							<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Tarea' border='0'/>
						</a>
					</display:column>
					<display:column style="width: 25px;" media="html">
						<a href="javascript:viewUser('${listaTareas.idTarea}','tareaHidden');javascript:clickLink('listadoTareas:eliminarTareaLink')" 
							onclick="return confirm('Confirma la eliminación de la tarea.');">
							<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar Tarea' border='0'/>
						</a>
					</display:column>					
							
				    <display:setProperty name="export.amount" value="list" />
				    <display:setProperty name="export.xml" value="true" />
				    <display:setProperty name="export.pdf" value="true" />
				    <display:setProperty name="export.excel.include_header" value="true" />
				    <display:setProperty name="export.banner">
						<div class="exportlinks">Exportar a: {0}</div>
				    </display:setProperty>
				    
				    <display:setProperty name="basic.show.header" value="true" />
				    						    
				    <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
				    <display:setProperty name="sort.amount" value="list" />
				    						    
				    <display:setProperty name="paging.banner.group_size" value="6" />
				    <display:setProperty name="paging.banner.placement" value="bottom" />
				    <display:setProperty name="paging.banner.item_name" value="Tarea" />
				    <display:setProperty name="paging.banner.items_name" value="Tareas" />
				    <display:setProperty name="paging.banner.some_items_found">
			        <div class="pagebanner" align="center" style="width: 650px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
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
		       			<div class="pagelinks" align="center" style="width: 650px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
		       		</display:setProperty>
		       		<display:setProperty name="paging.banner.first">
		       			<div class="pagelinks" align="center" style="width: 650px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
		       		</display:setProperty>
		       		<display:setProperty name="paging.banner.last">
		       			<div class="pagelinks" align="center" style="width: 650px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
		       		</display:setProperty>
		       	</display:table>
	    	</f:verbatim>
					
			<%-- Link oculto para eliminar o editar un proveedor --%>
			<x:commandLink action="#{TareaBean.editarTarea}" id="editarTareaLink" style="display: none;"/>
			<x:commandLink action="#{TareaBean.eliminarTarea}" id="eliminarTareaLink" style="display: none;"/>			

			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="tareaHidden" forceId="true" value="#{TareaBean.idTareaHidden}"/>
        		
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{TareaBean.inicializarListarTarea}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>



</body>
</html>
</f:view>



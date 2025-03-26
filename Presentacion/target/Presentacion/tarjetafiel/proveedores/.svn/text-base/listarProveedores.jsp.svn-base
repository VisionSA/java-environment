<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de proveedores"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoProveedores');">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ProveedorBean.tituloCorto}
    <small>${ProveedorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>



<center>

<secure:check/>

<h:form id="listadoProveedores">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body">
            <h:panelGroup id="body">


			<h:panelGrid columns="1" align="center" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Proveedores" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane">	
					<f:verbatim><br></f:verbatim>			      		
					<h:panelGrid id="filtroUno" columns="7" align="center">
						<h:outputText value="Codigo:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="idProvFiltro" value="#{ProveedorBean.idProveedor}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"
			               			 onkeypress="return soloEnteros(this,event);"/>
				               			 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					
						<h:outputText value="Cuit:" styleClass="texto" style="padding-right:5px"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="cuitFiltro" value="#{ProveedorBean.cuit}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>		 
			               			 			            
			            			            
						<h:outputText value="Razon Social:" styleClass="texto" style="padding-right:5px"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="razonSocialFiltro" value="#{ProveedorBean.razonSocial}" 
			               			 styleClass="bordeceldatext" maxlength="45" size="30" 
			               			 style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>       		            
					               			 
			            <f:verbatim>&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;</f:verbatim>
			            
					               			 
						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
						       		<h:commandButton id="btnBuscarProveedor" styleClass="btn btn-primary btn-flat pull-right"
						       						 value="Buscar" onclick="agregarRelacionProveedor.show();"
						       						 action="#{ProveedorBean.filtrarProveedores}" 
						       						 title="Busca el proveedor seleccionado"/>
								</c:when>
								<c:otherwise>
						       		<h:commandButton id="btnBuscarProveedor" 
						       						 value="Buscar" styleClass="btn btn-primary btn-flat pull-right"
						       						 onclick="alert('No posee los permisos necesarios.');"
						       						 title="Busca el proveedor seleccionado"/>
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>	

					</h:panelGrid>
					
				<f:verbatim><br></f:verbatim>	
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty ProveedorBean.proveedores}">	
        	<f:verbatim>
		       	<display:table id="proveedores" name="sessionScope.ProveedorBean.proveedores" 
		       				   defaultsort="1" pagesize="25" 
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/proveedores/listarProveedores.jsf"
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}" 
		       				   requestURIcontext="true" style="width: 902px;">

					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
							<a href="javascript:viewUser('${proveedores.idProveedor}','idProveedorHidden');javascript:clickLink('listadoProveedores:desactivarProveedorLink')" 
							onclick="return confirm('Confirma desactivar del proveedor.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar proveedor' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a href="#" onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar proveedor' border='0'/>
							</a>						
						</c:otherwise>
					</c:choose>		
					</display:column>

		       		<display:column property="idProveedor" title="Código" sortable="true" class="tdB"/>
		       		<display:column property="cuit" title="Cuit" sortable="true" class="tdB"/>
		       		<display:column property="razonSocial" title="Razón Social" sortable="true" class="tdA"/>
		       		<display:column property="nombreFantasia" title="Nombre Fantasia" sortable="true" class="tdA"/>
		       		<display:column property="alias" title="Alias" sortable="true" class="tdA"/>
		       		<display:column property="activo" title="Activo" sortable="true" class="tdB"/>
					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
							<a href="javascript:viewUser('${proveedores.idProveedor}','idProveedorHidden');javascript:clickLink('listadoProveedores:editarProveedorLink')">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar proveedor' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar proveedor' border='0'/>
							</a>
						</c:otherwise>
					</c:choose>						
					</display:column>
					
				    <display:setProperty name="export.amount" value="list" />
				    <display:setProperty name="export.xml" value="true" />
				    <display:setProperty name="export.pdf" value="true" />
				    <display:setProperty name="export.excel.include_header" value="true" />
				    <display:setProperty name="export.banner">
						<div class="exportlinks" style="width: 892px;">Exportar a: {0} </div>
				    </display:setProperty>				
							
				    <display:setProperty name="basic.show.header" value="true" />
				    						    
				    <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos." />
				    <display:setProperty name="sort.amount" value="list" />
				    						    
				    <display:setProperty name="paging.banner.group_size" value="6" />
				    <display:setProperty name="paging.banner.placement" value="bottom" />
				    <display:setProperty name="paging.banner.item_name" value="Proveedor" />
				    <display:setProperty name="paging.banner.items_name" value="Proveedores" />
				    <display:setProperty name="paging.banner.some_items_found">
			        <div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
				    </display:setProperty>
				    <display:setProperty name="paging.banner.no_items_found">
						<div class="pagebanner">No se encontraron {0}.</div>
				    </display:setProperty>						    
				    <display:setProperty name="paging.banner.one_item_found">
						<div class="pagebanner" style="">Un {0} encontrado.</div>
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
			<x:commandLink action="#{ProveedorBean.editarProveedor}" id="editarProveedorLink" style="display: none;"/>
			<x:commandLink action="#{ProveedorBean.desactivarProveedor}" id="desactivarProveedorLink" style="display: none;"/>			

			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="idProveedorHidden" forceId="true" value="#{ProveedorBean.idProveedorHidden}"/>
        		
		</c:if>
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>

        <%@include file="/inc/page_footer.jsp" %>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ProveedorBean.listarProveedores}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>

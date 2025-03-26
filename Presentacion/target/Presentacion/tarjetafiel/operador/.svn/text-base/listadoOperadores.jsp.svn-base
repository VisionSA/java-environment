<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de operadores"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoOperadores');">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${OperadorBean.tituloCorto}
    <small>${OperadorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>



<h:form id="listadoOperadores">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

       
        
        <f:facet name="body">
			<h:panelGroup id="body">
            
     		
<%--@I4273--%>				<h:panelGrid columns="1" align="center" id="panel1"> 
        			<s:layoutingTitlePane id="filtroOperadores"  label=" Filtro Operardores"  containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="9" width="900" align="center" >
        			
						<h:outputText id="lblId" value="Id:" styleClass="texto"/>
<%--@I4287--%>						<h:inputText id="txtId" value="#{OperadorBean.op.codigo}" align="right" 
														styleClass="bordeceldainferior" maxlength="10" size="10"
														style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
														onkeypress="return soloEnteros(this,event);"/>
						
						<h:outputText id="lblUsername" value="Username:" styleClass="texto" />
						<h:inputText id="txtUsername" value="#{OperadorBean.op.username}" align="left" 
														styleClass="bordeceldainferior" maxlength="10" size="10"
														style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
						
						
						<h:outputText id="lblNombre" value="Nombre:" styleClass="texto"/>
						<h:inputText id="nombre" value="#{OperadorBean.op.nombre}" align="left" 
														styleClass="bordeceldainferior" maxlength="10" size="10"
														style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
														/>
						
						
						<h:outputText id="lblApellido" value="Apellido:" styleClass="texto"/>
						<h:inputText id="apellido" value="#{OperadorBean.op.apellido}" align="left" 
														styleClass="bordeceldainferior" maxlength="10" size="10"
														style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"
														/>
						
						
<%--@I4287 --%>						<h:commandButton id="buttonBuscar" value="Buscar" action="#{OperadorBean.buscar}" styleClass="btn btn-primary btn-flat pull-right"/>
        						
					</h:panelGrid>
					</s:layoutingTitlePane>
<%--@F4273--%>				</h:panelGrid>
				
        
        		
        		<h:panelGrid columns="1" align="center">
        		
 <%--@I4273--%>
 <%--@F4273--%>       		
		        	<f:verbatim>
				       	<display:table id="operadores" name="sessionScope.OperadorBean.operadores" 
				       				   defaultsort="1" pagesize="25"
				       				   class="table-bordered table-striped" 
				       				   requestURI="/tarjetafiel/operador/listadoOperadores.jsf"
				       				   export="true" requestURIcontext="true" style="width: 700px;">
				       		<display:column property="codigo" title="Código" sortable="true" class="tdB"/>				       				   
				       		<display:column property="username" title="Username" sortable="true" class="tdA"/>
				       		<display:column property="nombre" title="Nombre" sortable="true" class="tdA"/>
				       		<display:column property="apellido" title="Apellido" sortable="true" class="tdA"/>
				       		<display:column property="operadorAlta.nombre" title="OperadorAlta" sortable="true" class="tdA"/>
				       		<display:column property="fechaAlta" title="FechaAlta" sortable="true" class="tdB"/>
				       		<display:column property="rol.descripcion" title="Rol" sortable="true" class="tdA"/>
				       		<display:column property="estado" title="Estado" sortable="true" class="tdA"/>
							<display:column style="width: 25px;" media="html">
								<a href="javascript:viewUser('${operadores.codigo}','idOperadorHidden');javascript:clickLink('listadoOperadores:editarOperadorLink')">
									<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar Operador' border='0'/>
								</a>
							</display:column>
							<display:column style="width: 25px;" media="html">
								<a href="javascript:viewUser('${operadores.codigo}','idOperadorHidden');javascript:clickLink('listadoOperadores:eliminarOperadorLink')" 
									onclick="return confirm('Confirma la eliminación del operador.');">
										<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar operador' border='0'/>
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
<%--@I4273--%>					    <display:setProperty name="paging.banner.item_name" value="Operador" />
<%--@F4273--%>						<display:setProperty name="paging.banner.items_name" value="Operadores" />
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
							
					<%-- Link oculto para eliminar o editar un Operador --%>
					<x:commandLink action="#{OperadorBean.editarOperador}" id="editarOperadorLink" style="display: none;"/>
					<x:commandLink action="#{OperadorBean.eliminarOperador}" id="eliminarOperadorLink" style="display: none;"/>			
		
					<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
					<x:inputHidden id="idOperadorHidden" forceId="true" value="#{OperadorBean.idOperadorHidden}"/>
		        		
<%--@4273--%>
<%--@F4273--%>				
        		
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{OperadorBean.irAListarOperador}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

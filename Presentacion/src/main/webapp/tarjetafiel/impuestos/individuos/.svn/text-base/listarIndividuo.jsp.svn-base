<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de individuos"/></title>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoIndividuos');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${IndividuoBean.tituloCorto}
    <small>${IndividuoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

<secure:check/>

<h:form id="listadoIndividuos">
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
			
				<s:layoutingTitlePane id="filtroIndividuos" label=" Filtro Individuos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >			
					<h:panelGrid id="filtroUno" columns="6" align="center" width="600">
						<h:outputText value="Cuit:" styleClass="texto"/>
						<h:inputText id="cuitFiltro" value="#{IndividuoBean.cuitFiltro}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
				               			 
			            
			            
						<h:outputText value="Denominación:" styleClass="texto"/>
						<h:inputText id="razonSocialFiltro" value="#{IndividuoBean.denominacionFiltro}" 
			               			 styleClass="bordeceldatext" maxlength="45" size="30" 
			               			 style="width: 200px" onfocus="encender(this);" onblur="apagar(this);"/>       		            
					               			 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					               			 
			       		<x:commandButton id="btnBuscarIndividuo" value="Buscar" action="#{IndividuoBean.filtrarIndividuos}" 
			       						 title="Busca el individuo seleccionado" styleClass="botones"/>
					</h:panelGrid>
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty IndividuoBean.individuos}">	
        	<f:verbatim>
		       	<display:table id="individuos" name="sessionScope.IndividuoBean.individuos" 
		       				   defaultsort="1" pagesize="10" 
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/impuestos/individuos/listarIndividuo.jsf" 
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}" 
		       				   requestURIcontext="true" style="width: 902px;">
		       		<display:column property="idIndividuo" title="Id" sortable="true" class="tdB"/>
		       		<display:column property="cuit" title="Cuit" sortable="true" class="tdB"/>
		       		<display:column property="denominacion" title="Denominación" sortable="true" class="tdA"/>
		       		<c:if test="${lst:equalsString(individuos.integranteSoc,'S')}">
			       		<display:column value="Si" title="Intgr. Sociedad" sortable="true" class="tdA"/>	
		       		</c:if>
		       		<c:if test="${lst:equalsString(individuos.integranteSoc,'N')}">
			       		<display:column value="No" title="Intgr. Sociedad" sortable="true" class="tdA"/>	
		       		</c:if>		       		
		       		<c:if test="${lst:equalsString(individuos.empleador,'S')}">
			       		<display:column value="Si" title="Empleador" sortable="true" class="tdA"/>	
		       		</c:if>
		       		<c:if test="${lst:equalsString(individuos.empleador,'N')}">
			       		<display:column value="No" title="Empleador" sortable="true" class="tdA"/>	
		       		</c:if>		       		
		       		
					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
							<a href="javascript:viewUser('${individuos.idIndividuo}','idIndividuoHidden');javascript:clickLink('listadoIndividuos:editarIndividuoLink')">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar individuo' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a href="#" onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar individuo' border='0'/>
							</a>
						</c:otherwise>
					</c:choose>						
					</display:column>
					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
							<a href="javascript:viewUser('${individuos.idIndividuo}','idIndividuoHidden');javascript:clickLink('listadoIndividuos:eliminarIndividuoLink')" 
							onclick="return confirm('Confirma la eliminación del individuo.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar individuo' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a href="#" onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar individuo' border='0'/>
							</a>						
						</c:otherwise>
					</c:choose>		
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
				    <display:setProperty name="paging.banner.item_name" value="Individuo" />
				    <display:setProperty name="paging.banner.items_name" value="Individuos" />
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
					
			<%-- Link oculto para eliminar o editar un individuo --%>
			<x:commandLink action="#{IndividuoBean.editarIndividuo}" id="editarIndividuoLink" style="display: none;"/>
			<x:commandLink action="#{IndividuoBean.eliminarIndividuo}" id="eliminarIndividuoLink" style="display: none;"/>			
			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="idIndividuoHidden" forceId="true" value="#{IndividuoBean.idIndividuoHidden}"/>
        		
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{IndividuoBean.irAListarIndividuos}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>

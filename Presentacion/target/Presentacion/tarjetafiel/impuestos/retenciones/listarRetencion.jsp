<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de retenciones"/></title>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoRetenciones');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${RetencionBean.tituloCorto}
    <small>${RetencionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

<secure:check/>

<h:form id="listadoRetenciones">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
			<h:panelGrid columns="1" align="center">
			
				<s:layoutingTitlePane id="filtroRetenciones" label=" Filtro Retenciones" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >			
					<h:panelGrid id="filtroUno" columns="6" align="center" width="600">
						<h:outputText value="Descripción: " styleClass="texto"/>
						<h:inputText id="razonSocialFiltro" value="#{RetencionBean.descripcionFiltro}" 
			               			 styleClass="bordeceldatext" maxlength="45" size="30" 
			               			 style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>       		            
					               			 
						<h:outputText value="Tipo impuesto: " styleClass="texto" id="outputTipoImp" />
						<h:selectOneMenu id="lstTipoImp" value="#{RetencionBean.idTipoImpSeleccionada}"
							styleClass="lista" immediate="true" onfocus="encender(this);"
							binding="#{RetencionBean.tipoImpHtml}" valueChangeListener="#{RetencionBean.cambiarCategorias}"
							onblur="apagar(this);" style="width: 150px" onchange="submit();">
							<f:selectItems value="#{RetencionBean.tipoImpItems}" id="selectedTipoImp2" />
						</h:selectOneMenu>
						
						<h:outputText id="outCategoria" value="Categoria: " styleClass="texto"/>
						<h:selectOneMenu id="lstCategoria" value="#{RetencionBean.categoriaSeleccionada}" 
	        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
	        					         onblur="apagar(this);" style="width: 150px" onchange="submit();"
	        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
	        					         binding="#{RetencionBean.categHtml}">
	       					<f:selectItems id="lisCat" value="#{RetencionBean.categoriaItems}"/>
	       				</h:selectOneMenu>
	                	
						<h:outputText id="outJurisdiccion" value="Jurisdicción: " styleClass="texto"/>
						<h:selectOneMenu id="lstJuris" value="#{RetencionBean.jurisdiccionSeleccionada}" 
	        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
	        					         onblur="apagar(this);" onchange="submit();" style="width: 150px"
	        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
	        					         binding="#{RetencionBean.jurisHtml}">
	       					<f:selectItems id="lisJur" value="#{RetencionBean.listaJurisdicciones}"/>
	       				</h:selectOneMenu>
						
						<h:outputText value="Aplicable: " styleClass="texto"/>
						<h:selectOneMenu value="#{RetencionBean.aplicableSeleccionada}" 
	        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
	        					         onblur="apagar(this);" onchange="submit();" style="width: 150px"
	        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
	        					         binding="#{RetencionBean.aplicHtml}">
	       					<f:selectItems id="lisApl" value="#{RetencionBean.listaAplicables}"/>
	       				</h:selectOneMenu>
						
						<h:outputText id="outActividad" value="Actividad: " styleClass="texto"/>
						<h:selectOneMenu id="lstActividad" value="#{RetencionBean.activodadSeleccionada}" 
	        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
	        					         onblur="apagar(this);" style="width: 150px">
	       					<f:selectItems id="lisAct" value="#{RetencionBean.listaActividades}"/>
	       				</h:selectOneMenu>
				        
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			       		<x:commandButton id="btnBuscarRetencion" 
			       						 value="Buscar" action="#{RetencionBean.filtrarRetenciones}" 
			       						 title="Busca la retencion seleccionada"
			       						 image="/img/icon/srch_24.gif"/>
					</h:panelGrid>
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty RetencionBean.retenciones}">	
        	<f:verbatim>
		       	<display:table id="retenciones" name="sessionScope.RetencionBean.retenciones" 
		       				   defaultsort="1" pagesize="25" 
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/impuestos/retenciones/listarRetencion.jsf" 
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}"
		       				   requestURIcontext="true" style="width: 902px;">
		       		<display:column property="idRetencion" title="Código" sortable="true" class="tdB"/>
		       		<display:column property="descripcion" title="Descripción" sortable="true" class="tdA"/>
		       		<display:column property="codigoRegimen" title="Código Regimen" sortable="true" class="tdB"/>
		       		<c:if test="${lst:equalsString(retenciones.acumulaPagos,'S')}">
			       		<display:column value="Si" title="Acumula Pagos" sortable="true" class="tdA"/>	
		       		</c:if>
		       		<c:if test="${lst:equalsString(retenciones.acumulaPagos,'N')}">
			       		<display:column value="No" title="Acumula Pagos" sortable="true" class="tdA"/>	
		       		</c:if>		       		
		       		<display:column property="vigenciaHasta" title="Vigencia Hasta" sortable="true" class="tdB"/>
					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
							<a href="javascript:viewUser('${retenciones.idRetencion}','idRetencionHidden');javascript:clickLink('listadoRetenciones:editarRetencionLink')">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar retención' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a href="#" onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar retención' border='0'/>
							</a>
						</c:otherwise>
					</c:choose>						
					</display:column>
					<display:column style="width: 25px;" media="html">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
							<a href="javascript:viewUser('${retenciones.idRetencion}','idRetencionHidden');javascript:clickLink('listadoRetenciones:eliminarRetencionLink')" 
							onclick="return confirm('Confirma la eliminación de la retención.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar retención' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a href="#" onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar retención' border='0'/>
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
				    <display:setProperty name="paging.banner.item_name" value="Retención" />
				    <display:setProperty name="paging.banner.items_name" value="Retenciones" />
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
					
			<%-- Link oculto para eliminar o editar una retencion --%>
			<x:commandLink action="#{RetencionBean.editarRetencion}" id="editarRetencionLink" style="display: none;"/>
			<x:commandLink action="#{RetencionBean.eliminarRetencion}" id="eliminarRetencionLink" style="display: none;"/>
			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="idRetencionHidden" forceId="true" value="#{RetencionBean.idRetencionHidden}"/>
        		
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{RetencionBean.listarRetenciones}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>

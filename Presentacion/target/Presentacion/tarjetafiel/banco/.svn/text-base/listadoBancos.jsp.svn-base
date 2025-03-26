<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de bancos"/></title>
    <script type="text/javascript" src="modaldbox.js">
    	function popup(pagina,popW,popH) {
			var x = 'something to check';
			$('txt').innerHTML = x;
			sm('box',200,50);
		};	
		function OKSelected() {
		var y = x;
		}		;
	</script>
	<link rel="stylesheet" href="modaldbox.css" type="text/css" />
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoBancos');">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${BancoBean.tituloCorto}
    <small>${BancoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<center>

<secure:check/>

<h:form id="listadoBancos">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter">

      
        
        <f:facet name="body">
            <h:panelGroup id="body">            	        		
        	<h:panelGrid columns="1" align="center" width="900">
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				        	
				<c:if test="${!empty BancoBean.bancos}">
				
		        	<f:verbatim>
				       	<display:table id="bancos" name="sessionScope.BancoBean.bancos" 
				       				   defaultsort="1" pagesize="25" 
				       				   class="table-bordered table-striped" 
				       				   requestURI="/tarjetafiel/proveedores/listadoBancos.jsf"
				       				   export="${lst:contains(requestScope.permisos,'exportacion')}" 
				       				   requestURIcontext="true" style="width: 902px;">
				       		<display:column property="codigo" title="Código" sortable="true" class="tdB"/>
				       		<display:column property="descripcion" title="Descripcion" sortable="true" class="tdA"/>
							<display:column style="width: 25px;" media="html">
								<c:choose>
									<c:when test="${lst:contains(requestScope.permisos,'edicion')}" >
										<a href="javascript:viewUser('${bancos.idBanco}','idBancoHidden');
												 javascript:clickLink('listadoBancos:editarBancoLink')">
											<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar banco' border='0'/>
										</a>																										
									</c:when>
									<c:otherwise> 
										<a onclick="return alert('No posee los permisos necesarios.');">
											<img src='<%=request.getContextPath()%>/img/editar.gif' title='Editar banco' border='0'/>
										</a>
									</c:otherwise>
								</c:choose>						
							</display:column>

							<display:column style="width: 25px;" media="html">
								<c:choose>
									<c:when test="${lst:contains(requestScope.permisos,'baja')}" >							
										<a href="javascript:viewUser('${bancos.idBanco}','idBancoHidden');
												 javascript:clickLink('listadoBancos:eliminarBancoLink')" 
												 onclick="return confirm('Confirma eliminar el banco.');">
											<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Eliminar banco' border='0'/>
										</a>										
									</c:when>									
									<c:otherwise> 
										<a href="#" onclick="return alert('No posee los permisos necesarios.');">
											<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar banco' border='0'/>
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
<h:commandButton id="buttonPopup" value="Popup" onclick="sm('box',200,50)" styleClass="btn btn-primary btn-flat pull-right"/>				       	 
			    	</f:verbatim>				 	
			    	<f:verbatim><div id="box" class="dialog">
<div style="text-align:center"><span id="txt">Press OK to continue.</span><br>
<button onclick="hm('box');okSelected()">OK</button>
</div> 
</f:verbatim>
   					<x:commandLink action="#{BancoBean.eliminar}" id="eliminarBancoLink" style="display: none;"/>
   					<x:commandLink action="#{BancoBean.editar}" id="editarBancoLink" style="display: none;"/>
   					
   					<x:inputHidden id="idBancoHidden" forceId="true" value="#{BancoBean.idBancoHidden}"/>					   
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{BancoBean.listar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>

<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de comprobantes"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('listadoComprobantes').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoComprobantes');"
		onload="if('${ComprobanteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ComprobanteBean.tituloCorto}
    <small>${ComprobanteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>

<secure:check/>

<h:form id="listadoComprobantes">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ComprobanteBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body">
            <h:panelGroup id="body">

				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog" id="viewDialog"
							   dialogVar="viewDialog" 
							   styleClass="viewDialog"
			                   dialogTitle="#{ComprobanteBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{ComprobanteBean.popup.nombreIcono}" />
					        <h:outputText value=" #{ComprobanteBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="2" width="300">
				        	<x:commandButton action="#{ComprobanteBean.borrarComprobante}" 
				        		 onclick="clickLink('eliminar');dojo.widget.byId('viewDialog').hide();"
				        		 value="Eliminar" styleClass="btn btn-primary btn-flat" title="Eliminar el comprobante y sus imputaciones."/>

				        	<x:commandButton action="#{ComprobanteBean.cancelarEliminar}" 
				        		 onclick="clickLink('cancelarElim');dojo.widget.byId('viewDialog').hide();"
				         		 value="Cancelar" styleClass="btn btn-primary btn-flat" title="Cancelar la acción."/>
							
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="eliminar" action="#{ComprobanteBean.borrarComprobante}" forceId="true" style="display: block;"/>
				<x:commandLink id="cancelarElim" action="#{ComprobanteBean.cancelarEliminar}" forceId="true" style="display: block;"/>
				

           <h:panelGrid columns="1" align="center" width="900">
           
				<s:layoutingTitlePane id="filtroComprobantes" label=" Filtro Comprobantes (Es Requerido seleccionar un rango de fechas)" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
				<h:panelGrid id="filtroUno" columns="12" align="center">
				
						<h:outputText id="outCuit" value="Cuit:" styleClass="texto" style="width: 100px"/>
						<h:inputText id="cuitFiltro" value="#{ComprobanteBean.cuitComprobanteFiltro}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
				               			 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            		            
			            <h:outputText id="outRazonSocial"  value="Razón Social: " styleClass="texto" style="width: 100px"/>
					 	<h:inputText id="razonSocialFiltro" value="#{ComprobanteBean.razonSocialFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="200"
		               			 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
			            
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
			            <h:outputText id="outNombreFantasial"  value="Nombre Fantasía: " styleClass="texto" style="width: 100px"/>
					 	<h:inputText id="nombreFantasialFiltro" value="#{ComprobanteBean.nombreFantasiaFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="11" size="11" 
		               			 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
		               	
		               	<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
		               	
		               	<h:outputText id="outAlias"  value="Alias: " styleClass="texto" style="width: 100px"/>
					 	<h:inputText id="aliasFiltro" value="#{ComprobanteBean.aliasFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="11" size="11" 
		               			 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
					    
					   
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			           			            
			            <h:outputText id="outNroLargo"  value="Nro. Cpte. Largo: " styleClass="texto" style="width: 100px"/>
					 	<h:inputText id="aliasNroLargo" value="#{ComprobanteBean.nroCompLargo}" 
		               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
		               			 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>


					    <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>

					    <h:outputText value="Fecha Desde: " styleClass="texto" style="width: 100px"/>

						<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                        <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fDesde">
			                </div>
						</f:verbatim>	

						<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>

						<h:outputText value="Fecha Hasta: " styleClass="texto" style="width: 100px"/>

						<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                 	   <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fHasta">
			                </div>
						</f:verbatim>	

						<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>	
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>		
						

						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
						            <h:commandButton id="btnBuscarComprobantes" value="Buscar" action="#{ComprobanteBean.filtrarComp}" 
						       			title="Busca los comprobantes" styleClass="btn btn-primary btn-flat pull-right"/>
								</c:when>
								<c:otherwise>
							            <h:commandButton id="btnBuscarComprobantes" value="Buscar" onclick="alert('No posee los permisos necesarios.');" 
							       			title="Busca los comprobantes" styleClass="btn btn-primary btn-flat pull-right"/>						       						 
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>		
									       						 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>

					</h:panelGrid>
		</s:layoutingTitlePane>		
			        
		<c:if test="${!empty ComprobanteBean.comprobantes}">	
			<f:verbatim>
		       	<display:table id="listaComp" name="sessionScope.ComprobanteBean.comprobantes" 
		       				   defaultsort="1" pagesize="25"
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/proveedores/comprobantes/listarComprobantes.jsf"
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}" 
		       				   requestURIcontext="true" style="width: 902px;">
		       		<display:column property="tipoComprobante.descripcionCorta" title="Tipo" sortable="true" class="tdB"/>
		       		<display:column property="nroEntero" title="Número" sortable="true" class="tdA"/>
		       		<display:column property="proveedor.cuit" title="Cuit" sortable="true" class="tdB"/>
		       		<display:column property="proveedor.razonSocial" title="Razón Social" sortable="true" class="tdA"/>
		       		<display:column property="fechaEmision" title="Fecha de Emision" sortable="true" class="tdB"/>
		       		<display:column property="totalImpuestos" title="Impuestos" sortable="true" class="tdB dinero"/>
		       		<display:column property="importeTotal" title="Total" sortable="true" class="tdB dinero"/>
		       		<display:column property="contabilizado" title="Contabilizado" sortable="true" class="tdB"/>
					<display:column style="width: 25px;" media="html">
						<a href="javascript:viewUser('${listaComp.idComprobante}','idComprobanteHidden');javascript:clickLink('listadoComprobantes:mostraComprobanteLink')">
							<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Mostrar Comprobante.' border='0'/>
						</a>				
					</display:column>
					<display:column style="width: 25px;" media="html">

					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
							<a href="javascript:viewUser('${listaComp.idComprobante}','idComprobanteHidden');javascript:clickLink('listadoComprobantes:eliminarComprobanteLink')" onclick="return confirm('¿Confirma la eliminación del comprobante?');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar comprobante' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar comprobante' border='0'/>
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
				    <display:setProperty name="paging.banner.item_name" value="Comprobante" />
				    <display:setProperty name="paging.banner.items_name" value="Comprobantes" />
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
     			<%-- Link oculto para mostrar comprobantes --%>
					<x:commandLink action="#{ComprobanteBean.mostrarComprobante}" id="mostraComprobanteLink" style="display: none;"/>			
					
				<%-- Link oculto para eliminar o editar un comprobante --%>
					<x:commandLink action="#{ComprobanteBean.eliminarComprobante}" id="eliminarComprobanteLink" style="display: none;"/>

				<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
					<x:inputHidden id="idComprobanteHidden" forceId="true" value="#{ComprobanteBean.idHidden}"/>
			
				</c:if>
		</h:panelGrid>
      		</h:panelGroup>
        </f:facet>


    </x:panelLayout>


    <h:inputText id="FechaDesde" value="#{ComprobanteBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{ComprobanteBean.fechaHasta}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ComprobanteBean.listarComprobantes}") + `</li>`;
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


  //Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("listadoComprobantes:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("listadoComprobantes:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoComprobantes:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("listadoComprobantes:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

	
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoComprobantes:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("listadoComprobantes:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });



  });
</script>



</body>
</html>
</f:view>

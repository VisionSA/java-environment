<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de imputaciones"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ImputacionBean.tituloCorto}
    <small>${ImputacionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

<secure:check/>

<h:form id="generalForm">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body" >
            <h:panelGroup id="body">
            	
			<h:panelGrid columns="1" align="center" id="panelUno">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Imputaciones" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					
					<f:verbatim><br></f:verbatim>
								      		
					<h:panelGrid id="filtroUno" columns="3" align="center" width="600">
						<h:panelGroup id="panaleGroupUno">
							<h:outputText value="Cuit:" styleClass="texto" id="c1"/>
							<h:inputText id="cuitFiltro" value="#{ImputacionBean.cuitFiltro}" 
				               			 styleClass="bordeceldainferior" maxlength="11" size="11" onchange="submit();"
				               			 style="width: 90px; margin-left:10px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>
				             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				             <x:commandLink id="buscarCuitProveedor" action="#{ImputacionBean.buscarProveedorPopup}">
								<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
			 				 </x:commandLink>
						</h:panelGroup>
					
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
						<h:panelGroup id="c2">
							<h:outputText value="Nro Orden:" styleClass="texto" id="c3"/>
							<h:inputText id="nroOrdenComp" value="#{ImputacionBean.nroOrdenComprobante}" 
				               			 styleClass="bordeceldainferior" maxlength="12" size="12" 
				               			 style="width: 90px; margin-left:10px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>				               			 
						</h:panelGroup>

					
						<h:panelGroup id="c4">
							<h:outputText value="Desde:" styleClass="texto" id="c5"/>
					 		<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                        <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fDesde">
			                </div>
						</f:verbatim>	
						</h:panelGroup>

			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
			            
						<h:panelGroup id="c6">
							<h:outputText value="Hasta:" styleClass="texto" id="c7"/>
					 		<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                 	   <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fHasta">
			                </div>
						</f:verbatim>
						</h:panelGroup>
						
						
						</h:panelGrid>
						
						<f:verbatim><br></f:verbatim>
						<f:verbatim><hr align="center" width="700"></f:verbatim>
						
						<h:panelGrid columns="1" align="center" width="600" id="c8">	
							<h:panelGroup id="gtupobtn">
								<x:commandButton id="btnBuscarProveedor" value="Buscar Imputaciones" action="#{ImputacionBean.filtrarImputaciones}" 
									styleClass="btn btn-primary btn-flat pull-right" style="margin-left:10px"/>
								<x:commandButton id="buttonIrCtaCte" value="Ir a Cuenta Corriente"	action="#{ImputacionBean.volverCtaCte}"
									styleClass="btn btn-primary btn-flat pull-right" style="margin-left:10px"/>
							</h:panelGroup>	
						</h:panelGrid>	
						
										
				</s:layoutingTitlePane>
			        
		<c:if test="${!empty ImputacionBean.imputaciones}">	
        	<f:verbatim>
		       	<display:table id="imputaciones" name="sessionScope.ImputacionBean.imputaciones" 
		       				   defaultsort="1" pagesize="25"
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/proveedores/imputaciones/listarImputaciones.jsf"
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}"
		       				   requestURIcontext="true" style="width: 902px;">
		       		<display:column property="razonSocial" title="Razon Social" sortable="true" class="tdA"/>
		       		<display:column property="nroCuit" title="C.U.I.T." sortable="true" class="tdA"/>
		       		<display:column property="fechaEmision" title="Fecha Emisión" sortable="true" class="tdB"/>
		       		<display:column property="ordenTipo" title="Orden" sortable="true" class="tdB"/>
		       		<display:column property="comprobanteTipo" title="Comprobante" sortable="true" class="tdA"/>
		       		<display:column property="monto" title="Monto" sortable="true" class="tdB"/>
					<display:column style="width: 25px;" media="html">

					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'baja')}" >
							<a href="javascript:viewUser('${imputaciones.idImputacion}','idImputacionHidden');javascript:clickLink('generalForm:eliminarImputacionLink')" onclick="return confirm('¿Confirma la eliminación de la imputacion?');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar imputación' border='0'/>
							</a>
						</c:when>
						<c:otherwise> 
							<a onclick="return alert('No posee los permisos necesarios.');">
								<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Borrar imputación' border='0'/>
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
				    						    
				    <display:setProperty name="basic.msg.empty_list" value="No se encontraron imputaciones." />
				    <display:setProperty name="sort.amount" value="list" />
				    						    
				    <display:setProperty name="paging.banner.group_size" value="6" />
				    <display:setProperty name="paging.banner.placement" value="bottom" />
				    <display:setProperty name="paging.banner.item_name" value="Imputación" />
				    <display:setProperty name="paging.banner.items_name" value="Imputaciones" />
				    <display:setProperty name="paging.banner.some_items_found">
			        <div class="pagebanner" align="center" style="width: 900px;">{0} {1} encontradas, mostrando desde la {2} hasta la {3}</div>
				    </display:setProperty>
				    <display:setProperty name="paging.banner.no_items_found">
						<div class="pagebanner">No se encontraron {0}.</div>
				    </display:setProperty>						    
				    <display:setProperty name="paging.banner.one_item_found">
						<div class="pagebanner">Un {0} encontrada.</div>
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
					
			<%-- Link oculto para eliminar o editar una imputacion --%>
			<x:commandLink action="#{ImputacionBean.eliminarImputacionListado}" id="eliminarImputacionLink" style="display: none;"/>

			<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
			<x:inputHidden id="idImputacionHidden" forceId="true" value="#{ImputacionBean.idImputacionHidden}"/>
        		
		</c:if>
			
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>
    </x:panelLayout>

    <h:inputText id="FechaDesde" value="#{ImputacionBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{ImputacionBean.fechaHasta}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ImputacionBean.irAListarImputaciones}") + `</li>`;
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
	var fd = document.getElementById("generalForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
	document.getElementById("generalForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

	fd = document.getElementById("generalForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("generalForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
    	var fd = $(this).datepicker("getDate");
        document.getElementById("generalForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("generalForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });



  });
</script>


</body>
</html>
</f:view>

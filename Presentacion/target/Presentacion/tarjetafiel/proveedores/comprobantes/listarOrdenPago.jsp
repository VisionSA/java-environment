<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listado de comprobantes"/></title>
     <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaComprobantesOPForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoOPForm');" onload="${OrdenPagoBean.popupReport}">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${OrdenPagoBean.tituloCorto}
    <small>${OrdenPagoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>

<secure:check/>

<h:form id="listadoOPForm">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body">
            <h:panelGroup id="body">

            	
           <h:panelGrid columns="1" align="center">
           		<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
           
				<s:layoutingTitlePane id="filtroComprobantes" label=" Filtro Ordenes de pago (Es Requerido seleccionar un rango de fechas)" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
				
				<f:verbatim><br></f:verbatim>

				<h:panelGrid id="filtroUno" columns="15">
						<h:outputText value="Cuit:" styleClass="texto"/>
						<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<h:inputText id="cuitFiltro" value="#{OrdenPagoBean.cuitComprobanteFiltro}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>
				               			 
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            		            
			            <h:outputText id="outRazonSocial"  value="Razón Social: " styleClass="texto" style="width: 100px"/>
			            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 	<h:inputText id="razonSocialFiltro" value="#{OrdenPagoBean.razonSocialFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="200"
		               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>
			            
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
			            <h:outputText id="outNombreFantasial"  value="Nombre Fantasía: " styleClass="texto" style="width: 100px"/>
			            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 	<h:inputText id="nombreFantasialFiltro" value="#{OrdenPagoBean.nombreFantasiaFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="11" size="11" 
		               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/>
		               	
		               	<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
		               	
		               	<h:outputText id="outAlias"  value="Alias: " styleClass="texto" style="width: 100px"/>
		               	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 	<h:inputText id="aliasFiltro" value="#{OrdenPagoBean.aliasFiltro}" 
		               			 styleClass="bordeceldatext" maxlength="11" size="11" 
		               			 style="width: 90px; margin-bottom:8px" onfocus="encender(this);" onblur="apagar(this);"/> 				   	
			            			            	          
			            <h:outputText id="outNroLargo"  value="Nro. Cpte. Largo: " styleClass="texto" style="width: 100px"/>
			            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 	<h:inputText id="nroLargoFiltro" value="#{OrdenPagoBean.nroCompLargo}" 
		               			 styleClass="bordeceldatext" maxlength="11" size="11" 
		               			 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
		               	
		               	<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>	

		               	 		 
			            <h:outputText value="Desde:" styleClass="texto"/>
			            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                        <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fDesde" style="width: 120px">
			                </div>
						</f:verbatim>
			            
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			            
			            <h:outputText value="Hasta:" styleClass="texto"/>
			            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>
			                <div class="input-group date">
			                    <div class="input-group-addon">
			                 	   <i class="fa fa-calendar"></i>
			                    </div>
			                    <input type="text" class="form-control pull-right" id="fHasta" style="width: 120px">
			                </div>
						</f:verbatim>
			            
			            <f:verbatim>&nbsp;</f:verbatim>	
						<f:verbatim>&nbsp;</f:verbatim>		
						<f:verbatim>&nbsp;</f:verbatim>
			            
						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
						            <h:commandButton id="btnBuscarOP" value="Buscar" action="#{OrdenPagoBean.filtrarOP}" onclick="pasarFechas()"
						       						 title="Busca las ordenes de pago" styleClass="btn btn-primary btn-flat pull-right"/>	
								</c:when>
								<c:otherwise>	
						            <h:commandButton id="btnBuscarOP" value="Buscar" onclick="alert('No posee los permisos necesarios.');"
						       						 title="Busca las ordenes de pago" styleClass="btn btn-primary btn-flat pull-right"/>								       							       						 
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>
			       		
			            <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
			              			            		            
					</h:panelGrid>
		</s:layoutingTitlePane>		
			        
		<c:if test="${!empty OrdenPagoBean.comprobantes}">	
			<f:verbatim>
		       	<display:table id="listaComp" name="sessionScope.OrdenPagoBean.comprobantes" 
		       				   defaultsort="1" pagesize="25"
		       				   class="table-bordered table-striped" 
		       				   requestURI="/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf"
		       				   export="${lst:contains(requestScope.permisos,'exportacion')}"
		       				   requestURIcontext="true" style="width: 902px;">

					<display:column style="width: 25px;" media="html">
						<c:if test="${empty listaComp.compRevertido}">	
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
									<c:if test="${listaComp.pasoFondos==true}">
										<a href="javascript:viewUser('${listaComp.idComprobante}','idROPHidden');javascript:clickLink('listadoOPForm:ROPLink')"
											onclick="return confirm('La orden de pago ya fue tomada por fondos. \n¿Confirma realizar ROP a la Orden de Pago?');">
											<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Generar ROP' border='0'/>
										</a>
									</c:if>
									<c:if test="${listaComp.pasoFondos!=true}">
										<a href="javascript:viewUser('${listaComp.idComprobante}','idROPHidden');javascript:clickLink('listadoOPForm:ROPLink')" 
											onclick="return confirm('¿Confirma realizar ROP a la Orden de Pago?');">
											<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Generar ROP' border='0'/>
										</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<a  onclick="alert('No posee los permisos necesarios.');">
										<img src='<%=request.getContextPath()%>/img/borrar.gif' title='Generar ROP' border='0'/>
									</a>
								</c:otherwise>
							</c:choose>					
						</c:if>
					</display:column>	
		       		<display:column property="nroLargo" title="Número" sortable="true" class="tdB"/>
		       		<display:column property="proveedor.cuit" title="Cuit" sortable="true" class="tdB"/>
		       		<display:column property="proveedor.idProveedor" title="Codigo del Proveedor" sortable="true" class="tdB"/>
		       		<display:column property="proveedor.razonSocial" title="Razón Social" sortable="true" class="tdA"/>
		       		<display:column property="tipoComprobante.descripcionLarga" title="Tipo" sortable="true" class="tdA"/>
		       		<display:column property="fechaEmision" title="Fecha de Emision" sortable="true" class="tdB"/>
		       		<display:column property="importeNeto" title="Total" sortable="true" class="tdB dinero"/>
		       		<display:column property="importeTotal" title="Total sin retenciones" sortable="true" class="tdB dinero"/>
		       		<display:column property="enFondos" title="En Fondos" sortable="true" class="tdA"/>
		       		<display:column style="width: 25px;" media="html">
		       			<c:if test="${empty listaComp.compRevertido}">	
							<a href="javascript:viewUser('${listaComp.idComprobante}','idROPHidden');javascript:clickLink('listadoOPForm:imprimirLink')">
								<img src='<%=request.getContextPath()%>/img/icon/print_16.gif' title='Imprimir Orden de Pago.' border='0'/>
							</a>				
						</c:if>
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
  			
  					<%-- Link oculto para eliminar o editar una OP --%>
					<x:commandLink action="#{OrdenPagoBean.crearROP}" id="ROPLink" style="display: none;"/>

  					<%-- Link oculto para imprimir una OP --%>
					<x:commandLink action="#{OrdenPagoBean.imprimirOPListar}" id="imprimirLink" style="display: none;"/>

					<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
					<x:inputHidden id="idROPHidden" forceId="true" value="#{OrdenPagoBean.idHidden}"/>
       		
				</c:if>
		</h:panelGrid>
      		</h:panelGroup>
        </f:facet>


    </x:panelLayout>

    <h:inputText id="FechaDesde" value="#{OrdenPagoBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{OrdenPagoBean.fechaHasta}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{OrdenPagoBean.listarOP}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp"%>




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
	var fd = document.getElementById("listadoOPForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("listadoOPForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("listadoOPForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("listadoOPForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoOPForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("listadoOPForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    

  });


</script>


</body>
</html>
</f:view>
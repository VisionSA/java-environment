<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Listar (Proveedores Cta Cte)"/></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('listadoCtaCte').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
		function verDialog() {
			dojo.widget.byId('verErrorImpuesto').show();
		};
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCtaCte');" onload="${ProveedorCtaCteBean.popupReport}">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ProveedorCtaCteBean.tituloCorto}
    <small>${ProveedorCtaCteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>

<secure:check/>

<%--@I5562--%><h:form id="listadoCtaCte" enctype="multipart/form-data">
<%--@F5562--%>   <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
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

				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Proveedor (Cta Cte)" 
						containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					
					<f:verbatim><br></f:verbatim>
					
					<h:panelGrid id="filtroUno" columns="3" align="center"  width="500">
						

						<h:panelGroup>
							<h:outputText value="Cuit:" styleClass="texto" style="padding-right: 10px"/>
							<x:inputText id="cuitFiltro" value="#{ProveedorCtaCteBean.cuit}" 
										 size="11" maxlength="11" styleClass="bordeceldainferior" 
										 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
										 onkeypress="return soloEnteros(this,event);"/>
						</h:panelGroup>
		        		<x:commandLink id="buscarProveedorLink" action="#{ProveedorCtaCteBean.buscarProveedorPopup}">
							<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
						</x:commandLink>

			            <f:verbatim>&nbsp;</f:verbatim>
			            
			            
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>

						
						<h:panelGroup>
							<h:outputText value="Desde:" styleClass="texto"/>
							<f:verbatim>
				                <div class="input-group date">
				                    <div class="input-group-addon">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                    <input type="text" class="form-control pull-right" id="fDesde">
				                </div>
							</f:verbatim>	
						</h:panelGroup>


			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						
						<h:panelGroup>
							<h:outputText value="Hasta:" styleClass="texto"/>
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
						
						<f:verbatim><hr align="center" width="600" align="center" ></f:verbatim>	
						
						<h:panelGrid id="panelBotones" width="500" align="center" >
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'acceso')}">
									<h:commandButton id="btnVerCtaCte" value="Ver Cuenta Corriente"
											action="#{ProveedorCtaCteBean.generar}"
											styleClass="btn btn-primary btn-flat pull-right" title="Ver Cuenta Corriente" />	
								</c:when>
								<c:otherwise>
									<h:commandButton id="btnVerCtaCte" value="Ver Cuenta Corriente"
											onclick="alert('No posee los permisos necesarios.');"
											styleClass="btn btn-primary btn-flat pull-right" title="Ver Cuenta Corriente" />							       						 
								</c:otherwise>
							</c:choose>					
						</h:panelGrid>								
								
				</s:layoutingTitlePane>



				<c:if test="${!empty ProveedorCtaCteBean.tablaCtaCte}">						
		        	<f:verbatim>
				       	<display:table id="proveedores" name="sessionScope.ProveedorCtaCteBean.tablaCtaCte" 
				       				   class="report" 
				       				   requestURI="/tarjetafiel/proveedores/listarCtaCte.jsf"
				       				   export="${lst:contains(requestScope.permisos,'exportacion')}"  requestURIcontext="true" 
				       				   style="width: 902px;"
				       				   pagesize="25">
				       		<display:column property="fecha" title="Fecha de Emision" class="tdB"/>
				       		<display:column property="numeroCte" title="Numero de Comprobante" class="tdB"/>
				       		<display:column property="tipoCte" title="Tipo de Comprobante" class="tdA"/>
				       		<display:column property="debe" title="Debe" class="tdB"/>
				       		<display:column property="haber" title="Haber" class="tdB"/>
				       		<display:column property="saldo" title="Saldo" class="tdB"/>
							<display:column title="&nbsp&nbsp" media="html">
								<a href="javascript:viewUser('${proveedores.numeroCte}','nroCteHidden');
										 javascript:viewUser('${proveedores.tipoCte}','tipoCteHidden');
								         javascript:clickLink('listadoCtaCte:mostrarComprobanteLink')">
									<img src='<%=request.getContextPath()%>/img/icon/OrderView.gif' title='Mostrar Comprobante.' border='0'/>
								</a>				
							</display:column>
				       		<display:column  title="&nbsp&nbsp" media="html" >
<%--@I5562--%>								<a href="javascript:viewUser('${proveedores.idComprobante}','idComprobanteHidden');
										 javascript:clickLink('listadoCtaCte:mostrarAdjuntoLink')">
									<img src='<%=request.getContextPath()%>/img/file_16x16.gif' title='Ver Adjunto.' border='0'/>
								</a>
				       		</display:column>	
<%--@F5562--%>						    <display:setProperty name="export.amount" value="list" />
						    <display:setProperty name="export.xml" value="true" />
						    <display:setProperty name="export.pdf" value="true" />
						    <display:setProperty name="export.excel.include_header" value="true" />
						    <display:setProperty name="export.banner">
								<div class="exportlinks" style="width: 892px;">Exportar a: {0}</div>
						    </display:setProperty>
				    				       		
				       	</display:table>
			    	</f:verbatim>
     			<%-- Link oculto para mostrar comprobantes --%>
					<x:commandLink action="#{ProveedorCtaCteBean.mostrarComprobante}" id="mostrarComprobanteLink" style="display: none;"/>
<%--@I5562--%>					<x:commandLink action="#{ProveedorCtaCteBean.mostrarAdjunto}" id="mostrarAdjuntoLink" style="display: none;"/>			
<%--@F5562--%>
				<%-- Paso 3. Crear un input hidden para contener el id del elemento seleccionado --%>
					<x:inputHidden id="nroCteHidden" forceId="true" value="#{ProveedorCtaCteBean.nroCteHidden}"/>
					<x:inputHidden id="tipoCteHidden" forceId="true" value="#{ProveedorCtaCteBean.tipoCteHidden}"/>
<%--@I5562--%>					<x:inputHidden id="idComprobanteHidden" forceId="true" value="#{ProveedorCtaCteBean.idComprobanteHidden}"/>								
<%--@F5562--%>				</c:if>

			        
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>


    </x:panelLayout>

    <h:inputText id="FechaDesde" value="#{ProveedorCtaCteBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{ProveedorCtaCteBean.fechaHasta}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ProveedorCtaCteBean.inicializar}") + `</li>`;
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
	var fd = document.getElementById("listadoCtaCte:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("listadoCtaCte:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

	fd = document.getElementById("listadoCtaCte:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("listadoCtaCte:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

	
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("listadoCtaCte:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("listadoCtaCte:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>



</body>
</html>
</f:view>

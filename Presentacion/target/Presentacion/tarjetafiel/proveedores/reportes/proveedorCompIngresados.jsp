<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Proveedores Composición de Saldo)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onload="${ReporteProveedorCtaCteBean.popupReport}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ReporteProveedorCtaCteBean.tituloCorto}
    <small>${ReporteProveedorCtaCteBean.tituloLargo}</small>
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

        <f:facet name="body">
            <h:panelGroup id="body">

			<h:panelGrid columns="1" align="center">
			<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Reporte" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<f:verbatim><br></f:verbatim>
					<h:panelGrid id="filtroUno" columns="3" align="center" width="500">
						<h:panelGroup>
							<h:outputText value="Id Proveedor Desde:" styleClass="texto"/>
							<h:inputText id="idDesde" value="#{ReporteProveedorCtaCteBean.idDesde}" 
				               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
				               			 style="width: 90px;margin-left:10px;" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGroup>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
		        		<h:panelGroup>
							<h:outputText value="Id Proveedor Hasta:" styleClass="texto"/>
							<h:inputText id="idHasta" value="#{ReporteProveedorCtaCteBean.idHasta}" 
				               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
				               			 style="width: 90px;margin-left:10px;" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGroup>
						
						
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
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
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
					
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					
					<h:panelGrid id="panelbtn" columns="1" width="500" align="center">
					<h:panelGroup>
							<h:commandButton id="verInformeEnExcel" actionListener="#{ReporteProveedorCtaCteBean.generarComprobantesIngresadosAExcel}"
											 styleClass="btn btn-primary btn-flat pull-right" value="Exportar a Excel" style="margin-left:10px;"/>
							
							<h:commandButton id="verInforme" actionListener="#{ReporteProveedorCtaCteBean.generarComprobantesIngresados}" 
											 value="Ver Reporte PDF" styleClass="btn btn-primary btn-flat pull-right" style="margin-left:10px;"/>
					</h:panelGroup>			
					</h:panelGrid>
					
					
				</s:layoutingTitlePane>
			        
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>
    </x:panelLayout>


    <h:inputText id="FechaDesde" value="#{ReporteProveedorCtaCteBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{ReporteProveedorCtaCteBean.fechaHasta}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ReporteProveedorCtaCteBean.comprobantesIngresados}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>



<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    $("#fHasta").datepicker({
      autoclose: true,
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

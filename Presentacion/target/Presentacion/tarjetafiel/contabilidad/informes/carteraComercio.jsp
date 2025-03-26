<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Cartera de Comercio)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onload="${CarteraComercioBean.popupReport}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Contabilidad
    <small>Informes</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Cartera de Comercio</h3>
		</div><br/>

<center>

<secure:check/>

<h:form id="saldoProveedoresForm">
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

			<s:layoutingTitlePane id="filtroProveedores"
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="1" align="center" width="546">
					   				        
						<h:panelGrid id="filtroDos" align="center" columns="3">
							<h:outputText value="Fecha:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					 		<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fHasta">
							        </div>
							</f:verbatim>	
						</h:panelGrid>
												
							<f:verbatim>
								<hr align="center" width="700">
							</f:verbatim>
						
						<h:panelGrid align="center">
							<h:commandButton actionListener="#{CarteraComercioBean.generarReporteCarteraComercio}" 
											 value="Generar Reporte" styleClass="btn btn-primary btn-flat" />
						</h:panelGrid>
					
					</h:panelGrid>
					
				</s:layoutingTitlePane>			        
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>
    </x:panelLayout>

    <h:inputText id="FechaHasta" value="#{CarteraComercioBean.fechaHasta}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{CarteraComercioBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>  


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fHasta").datepicker({
      autoclose: true,
    });

	
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("saldoProveedoresForm:FechaHasta").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
    document.getElementById("saldoProveedoresForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

    
    //Muevo fechas de datepicker a input q pasa datos al bean    
    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("saldoProveedoresForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>


</body>
</html>
</f:view>

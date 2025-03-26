<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Listar Productividad)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onload="${PromotorProductividadBean.popupReport}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PromotorProductividadBean.tituloCorto}
    <small>${PromotorProductividadBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header with-border">
	<h3 class="box-title">Productividad de Promotores</h3>
</div>
<center>


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

			<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Reporte" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="1" align="center" width="546">
						<h:panelGrid columns="3" align="center" width="546">

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

						<f:verbatim>&nbsp;</f:verbatim>

						<h:panelGrid id="filtrodos" columns="7" align="center" width="546">
										<h:outputText value="Promotor:" styleClass="texto"/>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:selectOneMenu id="lstBanco" value="#{PromotorProductividadBean.promotorSeleccionado}"
										disabled="#{PromotorProductividadBean.listaCargada}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);" style="width: 200px">
										<f:selectItems value="#{PromotorProductividadBean.lstPromotores}"/>
										</h:selectOneMenu>
										
<%--@I5703--%> 							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:outputText value="Meses Activaciones:" styleClass="texto"/>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
<%--@F5703--%> 				 			<h:inputText id="idMes" style="width: 60px" value="#{PromotorProductividadBean.mes}" />
						</h:panelGrid> 
                               

                        <f:verbatim>&nbsp;</f:verbatim>

                        <h:panelGrid columns="2" align="center" width="546">
                                <h:selectOneRadio value="#{PromotorProductividadBean.tipoLista}" id="tipoPromotorParaFiltro">
								    <f:selectItem itemLabel="Productividad Promotores Detalles" itemValue="D" />
								    <f:selectItem itemLabel="Productividad Promotores Totales" itemValue="T"/>
							    </h:selectOneRadio>												    
						</h:panelGrid>    
								
                        
						 

						<f:verbatim>&nbsp;</f:verbatim>

					
					   
						<h:panelGroup>
							<h:commandButton actionListener="#{PromotorProductividadBean.listarPromotoresProductividad}" 
											 value="Generar Reporte" styleClass="botones pull-right"/>
						</h:panelGroup>

						</h:panelGrid> 
					
				</s:layoutingTitlePane>			        
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>

    </x:panelLayout>

    <h:inputText id="FechaDesde" value="#{PromotorProductividadBean.fechaDesde}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{PromotorProductividadBean.fechaHasta}" style="display: none;">
        <f:convertDateTime type="date" pattern = "dd/MM/yyyy"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{PromotorProductividadBean.inicializar}") + `</li>`;
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
	var fd = document.getElementById("saldoProveedoresForm:FechaDesde").value.split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	$("#fDesde").datepicker("setDate", new Date(year+"-"+month+"-"+date));

	fd = document.getElementById("saldoProveedoresForm:FechaHasta").value.split("/");
	year = fd[2];
	month = fd[1];
	date = fd[0];
	$("#fHasta").datepicker("setDate", new Date(year+"-"+month+"-"+date));

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        fd.setDate(fd.getDate() + 1);
        document.getElementById("saldoProveedoresForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd);
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
        fh.setDate(fh.getDate() + 1);
		document.getElementById("saldoProveedoresForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh);
    });


  });
</script>

</body>
</html>
</f:view>

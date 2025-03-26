<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
	<html lang="es">
	<head>
	<title><h:outputText
		value="Tarjeta Fiel | Reporte (Libros / Impuestos de Sellos)" /></title>
	<s:script language="javascript">
    	function recargar() {
    		document.getElementById('popupInformesYLibrosImpositivosForm').submit();
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

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('popupLibrosEImpuestosDeSellosForm');"
		onload="${LibrosEImpuestosSellosBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Transacción
    <small>Reportes</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">Libros e Impuestos de Sellos</h3>
		</div><br/>
		

	<center><secure:check /> 
	<h:form
		id="popupLibrosEImpuestosDeSellosForm">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			<f:facet name="body">
				<h:panelGroup id="body">
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>

						<x:commandLink id="aceptarOK" forceId="true"
							style="display: block;" />
						<s:fieldset id="primerField">
							<h:panelGrid id="paneldeFechas" columns="5" width="600"
								align="center">
								<h:outputText value="Desde:" styleClass="texto" />
							 		<f:verbatim>
								            <div class="input-group date">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA" autocomplete="off">
								            </div>
									</f:verbatim>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<h:outputText value="Hasta:" styleClass="texto" />
						 		<f:verbatim>
								        <div class="input-group date">
								            <div class="input-group-addon">
								                <i class="fa fa-calendar"></i>
								            </div>
								            <input type="text" class="form-control pull-right" id="fHasta" autocomplete="off">
								        </div>
								</f:verbatim>
							</h:panelGrid>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:panelGrid id="filtroFecha" columns="1" align="center"
								width="400">
								<s:fieldset legend="Tipo del Informe">
									<h:selectOneRadio
										value="#{LibrosEImpuestosSellosBean.resumido}"
										id="selectOneRadio2" styleClass="radioB">
										<f:selectItem itemValue="1" itemLabel="Resumido" id="Resumido" />
										<f:selectItem itemValue="2" itemLabel="Detallado"
											id="Detallado" />
									</h:selectOneRadio>
								</s:fieldset>
							</h:panelGrid>
							<f:verbatim>
								<hr align="center" width="700">
							</f:verbatim>
							<h:panelGrid id="botonFiltros" columns="10">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="exportarPdflLink"
									action="#{LibrosEImpuestosSellosBean.generarPDF}">
									<x:graphicImage value="/img/icon/pdf_45x45.png"
										title="Generar reporte en formato PDF." border="0" />
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandLink id="exportarExcelLink"
									action="#{LibrosEImpuestosSellosBean.generarAExcel}">
									<x:graphicImage value="/img/icon/excel_45x45.png"
										title="Generar reporte en formato Excel." border="0" />
								</x:commandLink>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:commandButton action="#{LibrosEImpuestosSellosBean.cancelar}"
								value="Volver" id="salir" styleClass="btn btn-primary btn-flat pull-right" />
							</h:panelGrid>
						</s:fieldset>

					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>

		<h:inputText id="FechaDesde" value="#{LibrosEImpuestosSellosBean.fechaDesde}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>
	    <h:inputText id="FechaHasta" value="#{LibrosEImpuestosSellosBean.fechaHasta}" style="display: none;">
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
	    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LibrosEImpuestosSellosBean.inicializar}") + `</li>`;
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
	var fd = document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("popupLibrosEImpuestosDeSellosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>

	</body>
	</html>
</f:view>
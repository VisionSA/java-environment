<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html lang="es">
	<head>
	<title><h:outputText
		value="Tarjeta Fiel - Listado de CodComercio" /></title>
	</head>
<jsp:include page="/inc/includes.jsp"/>
	<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generaArchLiqCom');">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${GeneracionArchLiqComBean.tituloCorto}
    <small>${GeneracionArchLiqComBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>



	<center> <h:form id="generaArchLiqCom">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			
			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<s:layoutingTitlePane id="filtroCodComercio"
							label=" Filtros requeridos"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane">
							<h:panelGrid columns="2" id="panelDivisorio" align="center">
								<h:panelGrid id="filtroUno" columns="4" align="center">

									<h:outputText value="Código Posnet:" styleClass="texto" style="padding-right: 5px;"/>
									<h:inputText id="codigoposnetFiltro"
										value="#{GeneracionArchLiqComBean.codcomercioFiltro.codigoPosnet}"
										styleClass="bordeceldainferior" maxlength="13" size="13"
										style="width: 90px;margin-right: 15px" onfocus="encender(this);"
										onblur="apagar(this);" />
									<h:outputText value="Estado:" styleClass="texto" style="padding-right: 5px;"/>
									<h:selectOneMenu id="lstDeEstadosFiltor"
										value="#{GeneracionArchLiqComBean.codcomercioFiltro.estado}"
										styleClass="lista" immediate="true" onfocus="encender(this);"
										onblur="apagar(this);">
										<f:selectItems value="#{GeneracionArchLiqComBean.estadoItems}" />
									</h:selectOneMenu>

									<f:verbatim>&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;</f:verbatim>

								</h:panelGrid>
								<h:panelGrid columns="3" id="btnBuscarFiltro">
									<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
									<x:commandButton id="btnBuscar" value="Buscar"
										action="#{GeneracionArchLiqComBean.listarDetalleLiqquidacion}"
										title="Busqueda de Liquidaciones de Comercios"
										image="/img/icon/srch_24.gif" />
									<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
								</h:panelGrid>
								<h:panelGrid id="filtroDos" columns="4" align="center">
									<h:outputText value="Fecha Desde:" styleClass="texto" style="padding-right: 5px;" />
								 	<f:verbatim>
									    <div class="input-group date" style="padding-right: 15px">
									        <div class="input-group-addon">
									            <i class="fa fa-calendar"></i>
									        </div>
									        <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA" autocomplete="off">
									    </div>
									</f:verbatim>
									<h:outputText value="Fecha Hasta:" styleClass="texto" style="padding-right: 5px;" />
							 		<f:verbatim>
									    <div class="input-group date">
									        <div class="input-group-addon">
									            <i class="fa fa-calendar"></i>
									        </div>
									        <input type="text" class="form-control pull-right" id="fHasta" autocomplete="off">
									    </div>
									</f:verbatim>
								</h:panelGrid>
							</h:panelGrid>
						</s:layoutingTitlePane>
						
						<f:verbatim><br/></f:verbatim>

						<c:if
							test="${!empty GeneracionArchLiqComBean.tablaListDetLiquidacion}">

							<h:dataTable
								value="#{GeneracionArchLiqComBean.tablaListDetLiquidacion}"
								id="tablaDetLiq" styleClass="standardTable"
								headerClass="standardTable_Header" 
								footerClass="standardTable_Header"
								rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="forma" style=" width : 900px;">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Suc. Comercio" styleClass="texto"
											 />
									</f:facet>
									<h:outputText
										value="#{forma.liqComercio.codComercio.sucEmpresa.descripcion}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Nro Liquidación" styleClass="texto"
											 />
									</f:facet>
									<h:outputText value="#{forma.liqComercio.idLiqComercio}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Fecha Liquidación" styleClass="texto"
											 />
									</f:facet>
									<h:outputText value="#{forma.liqComercio.liquidacion.fechaLiquidacion}" />
								</h:column>
								<h:column>
									<x:commandLink id="generarLink" action="#{forma.generar}">
										<x:graphicImage value="/img/icon/tomar.gif" title="Generar Archivo." border="0"/>
									</x:commandLink>
								</h:column>
							</h:dataTable>
						</c:if>

					</h:panelGrid>
					<h:panelGrid id="botonera" columns="5" width="650">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<c:if
							test="${!empty GeneracionArchLiqComBean.tablaListDetLiquidacion}">
							<x:commandLink value="" title="Guardar"
								actionListener="#{GeneracionArchLiqComBean.generarArchLiquidaciones}"
								id="li">
								<x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
							</x:commandLink>
						</c:if>
                          
						<x:commandLink value="" title="Volver"
							actionListener="#{GeneracionArchLiqComBean.cancelar}" id="liDos">
							<x:graphicImage id="imDos" value="/img/icon/back.gif"></x:graphicImage>
						</x:commandLink>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		</x:panelLayout>

	<h:inputText id="FechaDesde" value="#{GeneracionArchLiqComBean.fecDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{GeneracionArchLiqComBean.fecHasta}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>


	</h:form></center>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{GeneracionArchLiqComBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{GeneracionArchLiqComBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>    


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "buttom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "buttom"
    });


    
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("generaArchLiqCom:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("generaArchLiqCom:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("generaArchLiqCom:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("generaArchLiqCom:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

	

    //Muevo fechas de datepicker a input q pasa datos al bean
        $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("generaArchLiqCom:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("generaArchLiqCom:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    


  });
</script>
	</body>
	</html>
</f:view>

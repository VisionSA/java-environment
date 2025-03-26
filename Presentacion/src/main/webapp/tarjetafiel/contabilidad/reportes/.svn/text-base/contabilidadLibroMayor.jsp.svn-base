<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Reporte (Contabilidad Libro Mayor)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('popupLibroMayorForm').submit();
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


<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('popupLibroMayorForm');" onload="${LibroMayorBean.popupReport}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LibroMayorBean.tituloCorto}
    <small>${LibroMayorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="popupLibroMayorForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LibroMayorBean.popup.mostrar}">
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
				
					<h:panelGrid columns="1" align="center" id="PanelPricipal" >
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{LibroMayorBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{LibroMayorBean.popup.nombreIcono}" />
							<h:outputText value="#{LibroMayorBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="1" width="500">
							<x:commandButton 
								onclick="clickLink('aceptarOK');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Aceptar."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="aceptarOK" forceId="true" style="display: block;"/>
					    <s:fieldset legend="Libro Mayor" id="primerField">
					    <h:panelGrid id="imp" columns="2">
			                <h:panelGrid id="panelBus" columns="2" align="center">
			                	<h:outputText value="Seleccionar la sucursal:" rendered="false"/>
			                	<h:selectOneMenu id="lstSucursales" value="#{LibroMayorBean.idSucSeleccionadaParaFiel}" rendered="false" binding="#{LibroMayorBean.idSucursalDeFielSeleccionada}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{LibroMayorBean.listarEjercicios}"
			       					  onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       					  <f:selectItems value="#{LibroMayorBean.sucursalesFielSelectItem}" id="selectSucum" />
			       				</h:selectOneMenu>	
			                    <h:outputText value="Seleccione el ejercicio:"/>
			                	<h:selectOneMenu id="lstDeEjerciciosPorSucursal" value="#{LibroMayorBean.idEjercicioSeleccionado}" binding="#{LibroMayorBean.idEjercicioSeleccionadoItem}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{LibroMayorBean.acomodarFechas}" onchange="submit();"
			       					  onblur="apagar(this);" style=" width : 200px;">
			       					  <f:selectItems value="#{LibroMayorBean.ejerciciosSelectItem}" id="selectEjerDeSucum" />
			       				</h:selectOneMenu>	
			                </h:panelGrid>
			                <h:panelGrid id="secun" columns="3">
			                     <h:outputText value="Ingrese los Nros. de cuentas buscadas: " styleClass="texto"/>
								 <h:inputText value="#{LibroMayorBean.listaCuentas}"  id="inputCuentaBusquedaLibro" />
								 <x:commandLink id="buscarCuentaLink" action="#{LibroMayorBean.buscarCuentaPopupReporte}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
			                </h:panelGrid>
			                
			             </h:panelGrid>

						<f:verbatim>&nbsp;</f:verbatim>

			             <h:panelGrid id="paneldeFechas" columns="5" width="600" align="center">
								<h:outputText value="Desde:" styleClass="texto"/>
									<f:verbatim>
						                <div class="input-group date">
						                    <div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
						                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA">
						                </div>
									</f:verbatim>
											            
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											            
							    <h:outputText value="Hasta:" styleClass="texto"/>
									<f:verbatim>
							                <div class="input-group date">
							                    <div class="input-group-addon">
							                 	   <i class="fa fa-calendar"></i>
							                    </div>
							                    <input type="text" class="form-control pull-right" id="fHasta" placeholder="DD/MM/AAAA">
							                </div>
									</f:verbatim>
								</h:panelGrid>   

								<f:verbatim>&nbsp;</f:verbatim>

								<h:panelGrid id="botonFiltros" columns="10"> 
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <h:commandButton actionListener="#{LibroMayorBean.generarAExcel}" styleClass="btn btn-primary btn-flat" value="Exportar a Excel" />
								      <h:commandButton id="verReporte" action="#{LibroMayorBean.prepararInforme}" actionListener="#{LibroMayorBean.generar}" value="Ver Reporte" styleClass="btn btn-primary btn-flat" style=" width : 116px;" />
								</h:panelGrid>
					     </s:fieldset>
					     
					    
					      <f:verbatim>&nbsp;</f:verbatim>

					      <h:panelGrid id="boto" columns="10">
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <h:commandButton action="#{LibroMayorBean.cancelar}" value="Volver" id="salir" styleClass="btn btn-primary btn-flat" />
					      </h:panelGrid>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		
		</x:panelLayout>

	<h:inputText id="FechaDesde" value="#{LibroMayorBean.fechaInicioBusqueda}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{LibroMayorBean.fechaCierreBusqueda}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LibroMayorBean.inicializarReporte}") + `</li>`;
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
		var fd = document.getElementById("popupLibroMayorForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("popupLibroMayorForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("popupLibroMayorForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("popupLibroMayorForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("popupLibroMayorForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("popupLibroMayorForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    


  });
</script>


</body>
</html>
</f:view>

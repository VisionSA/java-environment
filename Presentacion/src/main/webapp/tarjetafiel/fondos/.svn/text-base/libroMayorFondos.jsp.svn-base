<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{LibroMayorFondosBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('asientosForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />





<body class="hold-transition skin-blue sidebar-mini">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LibroMayorFondosBean.tituloCorto}
    <small>${LibroMayorFondosBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="asientosForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LibroMayorFondosBean.popup.mostrar}">
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
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{LibroMayorFondosBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{LibroMayorFondosBean.popup.nombreIcono}" />
							<h:outputText value="#{LibroMayorFondosBean.popup.mensaje}" styleClass="texto"/>
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
			               <h:panelGrid id="secun" columns="5">
			                     <h:outputText value="Nro. de Cuenta a Buscar: " styleClass="texto"/>
			                     <f:verbatim>&nbsp;&nbsp;</f:verbatim>
								 <h:inputText value="#{LibroMayorFondosBean.cuentaABuscarEnLibro}" onkeypress="return soloEnteros(this, event);" disabled="false" id="inputCuentaBusquedaLibro" title="Introduzca el nro. de  cuenta buscada."/>
								 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
								 <x:commandLink id="buscarCuentaLink" action="#{LibroMayorFondosBean.buscarCuentaPopup}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
				            </h:panelGrid>
			             </h:panelGrid>

			            <f:verbatim>&nbsp;</f:verbatim> 

			             <h:panelGrid id="paneldeFechas" columns="11" width="700" align="center">
								<h:outputText value="Desde:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
							 		<f:verbatim>
								            <div class="input-group date">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesde">
								            </div>
									</f:verbatim>
											            
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											            
							    <h:outputText value="Hasta:" styleClass="texto"/>
							    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
							 		<f:verbatim>
								            <div class="input-group date">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fHasta">
								            </div>
									</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								    <h:commandButton id="buscarC" value="Buscar" styleClass="btn btn-primary btn-flat" action="#{LibroMayorFondosBean.filtrarCuenta}"/>
								    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								      <h:commandButton id="cancelarC" value="Cancelar" styleClass="btn btn-primary btn-flat" action="#{LibroMayorFondosBean.cancelar}"/>
								</h:panelGrid>   
					 </s:fieldset>
					     
					     <c:if test="${empty LibroMayorFondosBean.renglones}">
					        <h:outputText value="No existe ningún asiento de la cuenta especificada en el rango de fechas introducido." styleClass="bordeceldainferior" style="border:none; background:none;color:green;"/>
					     </c:if>
					     
					     <c:if test="${!empty LibroMayorFondosBean.renglones}">
					     
					     <h:panelGrid id="saldoAnterior" columns="2" align="right" >
			                  	<h:outputText id="salAntCaption" value="Saldo Anterior:" styleClass="texto" style="color:green;"/>
			                  	<h:outputText id="salAnt" value="#{LibroMayorFondosBean.saldoAnterior}" style="border:none; background:none;" styleClass="bordeceldainferior">
			                  		<f:converter converterId="moneyConverter"/>
			                  	</h:outputText>
					     </h:panelGrid>    
					    <x:div id="divPanel" style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 250px; border: 1px; margin-left: auto; margin-right: auto;">
					     
					     	<h:panelGrid id="panelDeTabla" columns="1" align="center" >
					                  <h:dataTable id="listado" styleClass="table-bordered"
					                            	width="950"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="tdB,tdA,tdA,tdB,tdB,tdB"
						                            var="obj" 
						                            value="#{LibroMayorFondosBean.renglones}">
						                   
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Número de Asiento" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.asiento}" />
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Fecha" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.fechaCarga}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Leyenda" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.leyenda}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Debe" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.debe}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Haber" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.haber}" >
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Saldo" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.saldoAcumulado}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>              
						             </h:dataTable>						
					     	</h:panelGrid>
						</x:div>
					     
					     <h:panelGrid id="totalesYSaldo" columns="11" align="center">
					            <h:outputText value="Total Debe: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorFondosBean.debe}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <f:verbatim>&nbsp;&nbsp;|&nbsp;&nbsp;</f:verbatim>
					            <h:outputText value="Total Haber: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorFondosBean.haber}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <f:verbatim>&nbsp;&nbsp;|&nbsp;&nbsp;</f:verbatim>
					            <h:outputText value="Saldo: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorFondosBean.saldo}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					     </h:panelGrid>
                         <h:panelGrid id="periodo" columns="3" align="center">
					            <h:outputText value="Saldo Periodo: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorFondosBean.saldoPeriodo}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					     </h:panelGrid>
                         

					     </c:if>
					     
					      <h:panelGrid id="boto" columns="1" width="700">
					             <f:verbatim><hr align="center" width="700"></f:verbatim>
					             <h:commandButton action="#{LibroMayorFondosBean.cancelar}" value="Volver" id="salir" styleClass="btn btn-primary btn-flat pull-right" />
					      </h:panelGrid>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		>
		</x:panelLayout>

		<h:inputText id="FechaDesde" value="#{LibroMayorFondosBean.fechaInicioBusqueda}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>
	    <h:inputText id="FechaHasta" value="#{LibroMayorFondosBean.fechaCierreBusqueda}" style="display: none;">
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LibroMayorFondosBean.inicializar}") + `</li>`;
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
	var fd = document.getElementById("asientosForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("asientosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("asientosForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("asientosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";

    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("asientosForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("asientosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    


  });
</script>

</body>
</html>
</f:view>

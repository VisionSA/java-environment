<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{LibroMayorBean.tituloLargo}"/></title>
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
    ${AperturaCajaBean.tituloCorto}
    <small>${AperturaCajaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="asientosForm">

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
								value="Listar" styleClass="btn btn-primary btn-flat" title="Aceptar."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="aceptarOK" forceId="true" style="display: block;"/>
					    <s:fieldset legend="Libro Mayor" id="primerField">
					    <h:panelGrid id="imp" columns="2">
			                <h:panelGrid id="panelBus" columns="2" align="center" width="390" >
			                	<h:outputText value="Seleccionar la sucursal:" rendered="false"/>
			                	<h:selectOneMenu id="lstSucursales" value="#{LibroMayorBean.idSucSeleccionadaParaFiel}" rendered="false" binding="#{LibroMayorBean.idSucursalDeFielSeleccionada}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{LibroMayorBean.listarEjercicios}"
			       					  onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       					  <f:selectItems value="#{LibroMayorBean.sucursalesFielSelectItem}" id="selectSucum" />
			       				</h:selectOneMenu>	
			                    <h:outputText value="Seleccione el ejercicio:"/>
			                	<h:selectOneMenu id="lstDeEjerciciosPorSucursal" value="#{LibroMayorBean.idEjercicioSeleccionado}" binding="#{LibroMayorBean.idEjercicioSeleccionadoItem}"
			       					  styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{LibroMayorBean.acomodarFechas}"  onchange="submit();"
			       					  onblur="apagar(this);" style=" width : 200px; margin-left:10px; margin-right:20px;">
			       					  <f:selectItems value="#{LibroMayorBean.ejerciciosSelectItem}" id="selectEjerDeSucum" />
			       				</h:selectOneMenu>	
			                </h:panelGrid>
			                <h:panelGrid id="secun" columns="3" width="410">
			                     <h:outputText value="Nro. de Cuenta a Buscar: " styleClass="texto"/>
								 <h:inputText style="margin-left:10px;margin-right:10px" value="#{LibroMayorBean.cuentaABuscarEnLibro}" onkeypress="return soloEnteros(this, event);" disabled="false" id="inputCuentaBusquedaLibro" title="Introduzca el nro. de  cuenta buscada."/>
								 <x:commandLink id="buscarCuentaLink" action="#{LibroMayorBean.buscarCuentaPopup}">
									<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Cuenta." border="0"/>								
								 </x:commandLink>
								 
			                </h:panelGrid>
			                
			             </h:panelGrid>
			             
			             <f:verbatim><br></f:verbatim>
			             
			             <h:panelGrid id="paneldeFechas" columns="5" width="500" align="center">

								<h:outputText value="Desde:" style="padding-right:10px" styleClass="texto"/>
								<f:verbatim>
						            <div class="input-group date">
						                <div class="input-group-addon">
						                    <i class="fa fa-calendar"></i>
						                </div>
						                <input type="text" class="form-control pull-right" id="fDesde">
						            </div>
								</f:verbatim>
											            
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											            
							    <h:outputText value="Hasta:" style="padding-right:10px" styleClass="texto"/>
								<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fHasta">
							        </div>
								</f:verbatim>	

							</h:panelGrid> 
							
							 <f:verbatim><br></f:verbatim>  


								<h:panelGrid id="botonFiltros" columns="2" width="500" align="center"> 
								
								      <h:commandButton id="buscarC" style="margin-right:5px" value="Buscar" styleClass="btn btn-primary btn-flat pull-right" action="#{LibroMayorBean.filtrarCuenta}"/>
								      <h:commandButton id="cancelarC" style="margin-left:5px" value="Cancelar" styleClass="btn btn-primary btn-flat pull-left" action="#{LibroMayorBean.cancelar}"/>

								</h:panelGrid>
								
							<f:verbatim><br></f:verbatim> 

					     </s:fieldset>
					     
					     <c:if test="${empty LibroMayorBean.renglones}">
					        <h:outputText value="No existe ningún asiento de la cuenta especificada en el rango de fechas introducido." styleClass="bordeceldainferior" style="border:none; background:none;color:green;"/>
					     </c:if>
					     
					     <c:if test="${!empty LibroMayorBean.renglones}">
					     
					     <h:panelGrid id="saldoAnterior" columns="2" align="right" >
			                  	<h:outputText id="salAntCaption" value="Saldo Anterior:" styleClass="texto" style="color:green;"/>
			                  	<h:outputText id="salAnt" value="#{LibroMayorBean.saldoAnterior}" style="border:none; background:none;" styleClass="bordeceldainferior">
			                  		<f:converter converterId="moneyConverter"/>
			                  	</h:outputText>
					     </h:panelGrid>    
					     
					     <h:panelGrid id="panelDeTabla" columns="1">
					                            <h:dataTable id="listado" styleClass="table-bordered table-striped"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="tdB,tdA,tdA,tdB,tdB,tdB"
						                            var="obj" 
						                            value="#{LibroMayorBean.renglones}">
						                   
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Número de Asiento " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.asiento}" />
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Fecha Contab " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.fechaContab}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Leyenda " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.leyenda}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Debe " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.debe}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Haber " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.haber}" >
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value=" Saldo " />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.saldoAcumulado}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
												 
												                         
						                        </h:dataTable>
					     </h:panelGrid>


					     <h:panelGrid id="totalesYSaldo" columns="11" align="center">

					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>
					     		<f:verbatim>&nbsp;</f:verbatim>

					            <h:outputText value="Total Debe: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorBean.debe}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <f:verbatim>&nbsp;&nbsp;|&nbsp;&nbsp;</f:verbatim>
					            <h:outputText value="Total Haber: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorBean.haber}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <f:verbatim>&nbsp;&nbsp;|&nbsp;&nbsp;</f:verbatim>
					            <h:outputText value="Saldo: " styleClass="texto"/>
					            <f:verbatim>&nbsp;</f:verbatim>
					            <h:outputText value="#{LibroMayorBean.saldo}" styleClass="text-blue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>


					     </h:panelGrid>
					     </c:if>
					     
					     
					     <f:verbatim><hr align="center" width="800"></f:verbatim>
					     
					      <h:panelGrid id="boto" columns="1" width="750" >
					             <h:commandButton action="#{LibroMayorBean.cancelar}" value="Volver" id="salir" styleClass="btn btn-primary btn-flat pull-right" />
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LibroMayorBean.inicializar}") + `</li>`;
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

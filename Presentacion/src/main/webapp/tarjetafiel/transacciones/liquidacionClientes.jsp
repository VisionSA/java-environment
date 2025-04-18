<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{LiquidacionClientesBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('liquidacionClienteForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('liquidacionClienteForm'); ${LiquidacionClientesBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LiquidacionClientesBean.tituloCorto}
    <small>${LiquidacionClientesBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="liquidacionClienteForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LiquidacionClientesBean.popup.mostrar}">
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
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>


                    <h:panelGrid id="panelPrincipal" columns="1" align="center" >
                         <h:selectOneMenu id="fechaLiquidacionCliente" 
							 value="#{LiquidacionClientesBean.idFechaLiquidacionSeleccionada}"
							 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style=" width : 245px;"
							 binding="#{LiquidacionClientesBean.fechaLiquidacionSeleccionada}"
							 valueChangeListener="#{LiquidacionClientesBean.presentarListasDisponibles}" onchange="submit();">
							 <f:selectItems id="itemTipo"
							 value="#{LiquidacionClientesBean.listaFechasPosiblesLiq}" />
						 </h:selectOneMenu>			        
			        </h:panelGrid>			        

			        <h:panelGrid id="panelMensajes" rendered="#{LiquidacionClientesBean.hayLiquidacionSeleccionada}" columns="1" align="center" >
                         <h:outputText value="#{LiquidacionClientesBean.liquidacionClientesEditada.leyendaEstado}" styleClass="texto" style="color:green"/>
			        </h:panelGrid>
			        
			        <h:panelGrid id="panelResumen" rendered="#{LiquidacionClientesBean.sePuedeConfirmar}" columns="1" align="center" >
                         <f:verbatim>&nbsp;</f:verbatim>
                         <f:verbatim>&nbsp;</f:verbatim>
                         <h:outputText value="El n�mero de liquidacion es #{LiquidacionClientesBean.liquidacionClientesEditada.idLiquidacionClientes}." styleClass="texto"  style="FONT-SIZE: large; color:green"/>
                         <h:outputText value="El proceso ha finalizado con �xito." styleClass="texto"  style="FONT-SIZE: large; color:green"/>
			             <f:verbatim>&nbsp;</f:verbatim>
                         <f:verbatim>&nbsp;</f:verbatim>
			        </h:panelGrid>

			        <f:verbatim>&nbsp;</f:verbatim>
			        <f:verbatim>&nbsp;</f:verbatim>
					
					<h:panelGrid columns="6" width="727" id="panelBotonera">

						<h:outputText value="Comunicaci�n" styleClass="texto" />
						<x:selectBooleanCheckbox id="chkActivo"
						value="#{LiquidacionClientesBean.comunicado}" />
						
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

						<h:inputText id="inputPreLiquidar1"  value="#{LiquidacionClientesBean.inicio}" /> 
						<h:inputText id="inputPreLiquidar"  value="#{LiquidacionClientesBean.cantidad}" />  
						
									
						<x:commandButton id="buttonPreLiquidar" value="Pre-Liquidaci�n" rendered="#{LiquidacionClientesBean.preliquidacion}"  action="#{LiquidacionClientesBean.preliquidar}" styleClass="btn btn-primary btn-flat"/>
						
					</h:panelGrid>
					
					

					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>

                    <h:panelGrid columns="1" width="700" id="panelPreliquis" align="center">
                              <h:dataTable value="#{LiquidacionClientesBean.listaPreliquidacionesWrapper}"
										id="tablaPreLiq" styleClass="table-bordered table-striped" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="var" style=" width :700px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Cierre Per�odo" id="cierreP" styleClass="texto"  />
											</f:facet>
                                           <h:outputText id="fechaCierreliq" value="#{var.liquidacionCliente.fechaCierrePeriodo}"/>
										</h:column>
									  	<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Preliquidaci�n" id="cab2" styleClass="texto"  />
											</f:facet>
											<h:outputText id="fechaPreliq" value="#{var.liquidacionCliente.fechaPreliq}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Finalizo" id="finalizo" styleClass="texto"  />
											</f:facet>
                                           <h:outputText id="finalizo2" value="#{var.liquidacionCliente.finalizo}"/>
										</h:column>
										<h:column>
											<x:commandLink action="#{var.eliminarPreliquidacion}" id="eliminarPreliquidacionLink">
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar una Liquidacion Particular." border="0" id="botonImagenTresTar" />
											</x:commandLink>
										</h:column>
                                        <h:column>
											<x:commandLink value="Confirmar Liquidaci�n" action="#{var.confirmarLiquidacion}" id="confirmarPreliquidacionLink">
											</x:commandLink>
										</h:column>
										
									</h:dataTable>
                    </h:panelGrid>

					</h:panelGrid>
					
					
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>
	</h:form>
</center>
<div class="box-header">
	<a href="http://192.168.0.7:8080/archivos/comunicacion/comunicacion.pdf" target="_blank" 
	style="color:blue;text-decoration: underline;">
	Ver comunicacion
	</a>
</div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LiquidacionClientesBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

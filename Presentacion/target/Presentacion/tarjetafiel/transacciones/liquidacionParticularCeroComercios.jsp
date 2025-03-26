<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{LiquidacionComerciosCeroParticularBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('liquidacionParticularComercioForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		function marcar() {
         	if (document.getElementById('liquidacionParticularComercioForm:listado:boolTodos').checked) {
                for (i=0; document.getElementById('liquidacionComercioForm:listado:' + i + ':estado')!=null;i++) {
                     document.getElementById('liquidacionComercioForm:listado:' + i + ':estado').checked = true;
                }
                return false;
            } else {
            	for (i=0; document.getElementById('liquidacionParticularComercioForm:listado:' + i + ':estado')!=null;i++) {   
                     document.getElementById('liquidacionParticularComercioForm:listado:' + i + ':estado').checked = false;
                }
                return false;
            }
         }
		
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('liquidacionParticularComercioForm');" onload="${LiquidacionComerciosCeroParticularBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LiquidacionComerciosCeroParticularBean.tituloCorto}
    <small>${LiquidacionComerciosCeroParticularBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>
<center>
	<secure:check/>

	<h:form id="liquidacionComercioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!LiquidacionComerciosCeroParticularBean.popup.mostrar}">
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

					 <h:panelGrid id="busquedaPanel" columns="4" align="center" >
                            <h:outputText value="Cod. Posnet: "/>
							<h:inputText value="#{LiquidacionComerciosCeroParticularBean.codigoPosnetBuscado}" styleClass="texto"  />
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <x:commandButton id="btnBuscar" value="Buscar" action="#{LiquidacionComerciosCeroParticularBean.buscarCodigoComercio}" styleClass="btn btn-primary btn-flat"/>
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

                            <h:outputText value="Cod. Posnet: #{LiquidacionComerciosCeroParticularBean.comercioALiquidar.codigoPosnet}" />
                            <h:outputText value="Empresa:  #{LiquidacionComerciosCeroParticularBean.comercioALiquidar.sucEmpresa.empresa.razonSocial}" /> 
                     </h:panelGrid>
                    
                   
			        <h:panelGrid id="primerPanel" columns="1" align="center" >
						<c:if
										test="${!empty LiquidacionComerciosCeroParticularBean.movimientosPendientes}">
										<h:dataTable align="center"
											value="#{LiquidacionComerciosCeroParticularBean.movimientosPendientes}"
											id="listado" styleClass="standardTable"
											headerClass="dataTable_Header"
											footerClass="standardTable_Header"
											rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="obj" style=" width : 600px;">
											
											<h:column>
												<f:facet name="header">
													<h:outputText value="Id Transaccion" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.ctaCteComercios.idTranascciones}"
													style=" width : 100px;" styleClass="textoblue" />

											</h:column>

                                            
											<h:column>
												<f:facet name="header">
													<h:outputText value="Importe" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.ctaCteComercios.importe}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

											<h:column>
												<f:facet name="header">
													<h:outputText value="Cuotas" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.ctaCteComercios.cantCuotasCliente}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

                                            <h:column>
												<f:facet name="header">
													<h:outputText value="F. Consumo" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.ctaCteComercios.timestamp}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

                                           

                                            <h:column>
												<f:facet name="header">
													<h:outputText value="F. Facturacion" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.ctaCteComercios.fechaFacturacion}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

										    <h:column>
													<f:facet name="header">
														<h:panelGroup>
															<f:facet name="header">
																<h:outputText value="Liquidar" id="todos" styleClass="texto" />
															</f:facet>
															<h:selectBooleanCheckbox value="#{LiquidacionComerciosCeroParticularBean.todos}" id="boolTodos"  onclick="marcar();"/>
														</h:panelGroup>
													</f:facet>
													<h:selectBooleanCheckbox value="#{obj.seleccionado}" style="width: 25px" id="estado"/>
											</h:column> 
											
											
											
										</h:dataTable>
										
										
									</c:if>  
                    
                    </h:panelGrid>

					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabar" value="Liquidar"  action="#{LiquidacionComerciosCeroParticularBean.liquidarListas}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{LiquidacionComerciosCeroParticularBean.cancelar}" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
                    
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
		</x:panelLayout>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LiquidacionComerciosCeroParticularBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

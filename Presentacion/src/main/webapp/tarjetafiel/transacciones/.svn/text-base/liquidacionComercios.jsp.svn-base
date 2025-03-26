<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{LiquidacionComerciosBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('liquidacionComercioForm').submit();
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
         	if (document.getElementById('liquidacionComercioForm:listado:boolTodos').checked) {
                for (i=0; document.getElementById('liquidacionComercioForm:listado:' + i + ':estado')!=null;i++) {
                     document.getElementById('liquidacionComercioForm:listado:' + i + ':estado').checked = true;
                }
                return false;
            } else {
            	for (i=0; document.getElementById('liquidacionComercioForm:listado:' + i + ':estado')!=null;i++) {   
                     document.getElementById('liquidacionComercioForm:listado:' + i + ':estado').checked = false;
                }
                return false;
            }
         }
		
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('liquidacionComercioForm');" onload="${LiquidacionComerciosBean.popupReport}">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LiquidacionComerciosBean.tituloCorto}
    <small>${LiquidacionComerciosBean.tituloLargo}</small>
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
	<h:panelGroup rendered="#{!LiquidacionComerciosBean.popup.mostrar}">
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

					
                    <h:panelGrid id="principalPanel" columns="1" align="center" >
                    
                         <h:selectOneMenu id="fechaLiquidacion" 
							 value="#{LiquidacionComerciosBean.idFechaLiquidacionSeleccionada}"
							 styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);" style=" width : 245px;"
							 binding="#{LiquidacionComerciosBean.fechaLiquidacionSeleccionada}"
							 valueChangeListener="#{LiquidacionComerciosBean.presentarListasDisponibles}" onchange="submit();">
							 <f:selectItems id="itemTipo"
							 value="#{LiquidacionComerciosBean.listaFechasPosiblesLiq}" />
						 </h:selectOneMenu>
			        </h:panelGrid>
			        <h:panelGrid id="secundarioPanel" columns="1" align="center" >
						<c:if
										test="${!empty LiquidacionComerciosBean.listasPrecios}">
										<h:dataTable align="center"
											value="#{LiquidacionComerciosBean.listasPrecios}"
											id="listado" styleClass="standardTable"
											headerClass="dataTable_Header"
											footerClass="standardTable_Header"
											rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="obj" style=" width : 600px;">
											
											<h:column>
												<f:facet name="header">
													<h:outputText value="Lista de Precios" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.listaPrecioParaLiquidar.listaPrecio.descripcion}"
													style=" width : 100px;" styleClass="textoblue" />

											</h:column>

                                            
											<h:column>
												<f:facet name="header">
													<h:outputText value="Período de liquidación" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.listaPrecioParaLiquidar.fechaInicioString} -- #{obj.listaPrecioParaLiquidar.fechaCierreString}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

											<h:column>
												<f:facet name="header">
													<h:outputText value="Cuotas" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{obj.listaPrecioParaLiquidar.cuotasString}"
													style=" width : 150px;" styleClass="textoblue" />

											</h:column>

										    <h:column>
													<f:facet name="header">
														<h:panelGroup>
															<f:facet name="header">
																<h:outputText value="Liquidar" id="todos" styleClass="texto" />
															</f:facet>
															<h:selectBooleanCheckbox value="#{LiquidacionComerciosBean.todos}" id="boolTodos"  onclick="marcar();"/>
														</h:panelGroup>
													</f:facet>
													<h:selectBooleanCheckbox value="#{obj.seleccionado}" style="width: 25px" id="estado"/>
											</h:column> 
											
											<h:column>
											    	<f:facet name="header">
														<h:outputText value="Comercios" styleClass="texto"
															 />
													</f:facet>
													<x:commandLink value="Comercios adheridos" action="#{LiquidacionComerciosBean.verComerciosAdheridos}" id="verComerciosAdheridosLink" title="Comercios Adheridos">
													<f:param id="idComerciosAVer" name="idComerciosAVer" value="#{obj.idListaPrecioALiq}"/>
											    	</x:commandLink>
											</h:column> 
											
											<h:column>
											        <f:facet name="header">
														 <h:outputText value="Evaluación" id="evaluacion" styleClass="texto" />
													</f:facet>
													
													<h:graphicImage value="/img/icon/ok.png" rendered="#{obj.estadoCorrecto}" id="imagenOk" title="Evaluación Reglas Aceptadas."/>
											        <h:graphicImage value="/img/icon/stop.png" rendered="#{obj.estadoIncorrecto}" id="imagenStop" title="Evaluación Reglas Rechazada."/>
											        <h:graphicImage value="/img/icon/Alert32.gif" rendered="#{obj.estadoAlerta}" id="imagenRestric" title="Restrictivo"/>
											        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											        <h:commandLink value="Cupones Rechazados" actionListener="#{obj.verCupones}" rendered="#{!obj.estadoCorrecto}"></h:commandLink>
											</h:column>  
											
										</h:dataTable>
										
										
									</c:if>  
                    
                    </h:panelGrid>
                    <c:if
										test="${!empty LiquidacionComerciosBean.listasPrecios}">
	                    <h:panelGrid columns="2" id="explicaciones">
							<h:graphicImage value="/img/icon/ok.png" id="imagenexpOk"/>
							<h:outputText value="La lista de precio se encuentra en condiciones de Liquidarse." styleClass="texto" style="color:green" />
							<h:graphicImage value="/img/icon/Alert32.gif" id="imagenexpRestric" />
							<h:outputText value="La lista de precio puede liquidarse, pero presenta cupones rechazados." styleClass="texto" style="color:green" />
							<h:graphicImage value="/img/icon/stop.png" id="imagenexpStop"/>
							<h:outputText value="La lista de precio no puede liquidarse, ya hay transacciones que fueron rechazadas automaticamente." styleClass="texto" style="color:green" />
						</h:panelGrid>
					</c:if>
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
						<x:commandButton id="buttonGrabar" value="Liquidar Listas" rendered="#{LiquidacionComerciosBean.hayParaLiquidar}" action="#{LiquidacionComerciosBean.liquidarListas}" styleClass="btn btn-primary btn-flat"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{LiquidacionComerciosBean.cancelar}" styleClass="btn btn-primary btn-flat" />
					</h:panelGrid>
                    <f:verbatim>&nbsp;</f:verbatim>
                    <s:fieldset id="fieldReimpresion" legend="Regenerar Pdfs liquidaciones:">
	                    <h:panelGrid id="panelReimpresion" columns="2" align="center" >
	                         <h:outputText id="outDesdeid" value="Número de liquidación desde:" styleClass="texto"  />
	                         <h:inputText id="pdfDesdeid" value="#{LiquidacionComerciosBean.pdfDesde}" />
	                         <h:outputText id="outHastaid" value="Número de liquidación hasta:" styleClass="texto"  />
                             <h:inputText id="pdfHastaid" value="#{LiquidacionComerciosBean.pdfHasta}" />
				        </h:panelGrid>
                        <h:panelGrid id="panelReimpresionBoton" columns="1" align="right" >
	                         <x:commandButton id="buttonReimp" value="Regenerar Pdf" action="#{LiquidacionComerciosBean.reimprimirPdf}" styleClass="btn btn-primary btn-flat" />
                        </h:panelGrid>
                    </s:fieldset>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{LiquidacionComerciosBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>

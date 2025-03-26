<%@include file="/inc/tags.jsp"%>
<%-- <jsp:useBean id="ahora" class="java.util.Date" scope="request"/> --%>

<f:view>
	<html lang="es">
<head>
<title><x:outputText
		value="Tarjeta Fiel - Embozado de Lotes de Plasticos" /></title>
		

</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('EmbozadoLotePlastico');"
	onload="if('${EmbozadoLotesPlasticosBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

	<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    ${EmbozadoLotesPlasticosBean.tituloCorto}
	    <small>${EmbozadoLotesPlasticosBean.tituloLargo}</small>
	  </h1>
	</section>

	<section class="content">

	<div class="box box-default">
	<div class="box-header"><h3></h3></div>

	<center>


		<h:form id="EmbozadoLotePlastico">
			<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
			<h:panelGroup rendered="#{!EmbozadoLotesPlasticosBean.popup.mostrar}">
				<%@include file="/inc/scroll.inc"%>
			</h:panelGroup>

			<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
				styleClass="pageLayout" headerClass="pageHeader"
				navigationClass="pageNavigation" bodyClass="pageBody"
				footerClass="pageFooter">


				<f:facet name="body">
					<h:panelGroup id="body">

						<x:panelGrid columns="1" align="center" id="PanelPricipal">
							<%-- INCLUDE PARA LOS ERRORES --%>
							<h:panelGroup id="errores">
								<jsp:include page="/inc/error.jsp" />
							</h:panelGroup>

							<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
							<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{EmbozadoLotesPlasticosBean.tituloCorto}">
								<x:panelGrid columns="2" width="500">
									<x:graphicImage
										value="/img/#{EmbozadoLotesPlasticosBean.popup.nombreIcono}" />
									<x:outputText
										value="#{EmbozadoLotesPlasticosBean.popup.mensaje}"
										styleClass="texto" />
								</x:panelGrid>
								<x:panelGrid columns="3" width="500">
									<x:commandButton
										action="#{EmbozadoLotesPlasticosBean.irAContinuar}"
										onclick="clickLink('continuar');dojo.widget.byId('viewDialog').hide();"
										value="Continuar" styleClass="btn btn-primary btn-flat"
										title="Continuar en la pantalla." />
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<%--<x:commandButton action="#{LiquidarSaldo0Bean.irASalir}" 
										onclick="clickLink('salir');dojo.widget.byId('viewDialog').hide();"
										value="Salir" styleClass="botones" title="Salir."/>--%>
								</x:panelGrid>
							</s:modalDialog>
							<x:commandLink id="continuar"
								action="#{EmbozadoLotesPlasticosBean.irAContinuar}"
								forceId="true" style="display: block;" />

							<%-- Area Lotes pendientes erróneos o en generacion --%>
							<c:if test="${EmbozadoLotesPlasticosBean.mostrarLotesErroneos}">
								<x:panelGrid id="resumenLotesErroneos" columns="1" width="900"
									align="center">
									<s:layoutingTitlePane id="cLotesE"
										label="Lotes Erroneos o en generacion"
										containerNodeClass="contentTitlePane"
										labelNodeClass="labelTitlePane">
										<x:panelGrid id="lErroneos" columns="1">
											<x:outputText id="cLE"
												value="Lotes Erroneos o en generacion:  #{EmbozadoLotesPlasticosBean.cantidadLotesErroneos}"
												styleClass="textoACenter" />
										</x:panelGrid>

										<x:panelGrid id="LEPG" columns="1" align="center" width="850">
											<x:dataTable
												value="#{EmbozadoLotesPlasticosBean.lotesErroneosList}"
												id="listadoErroneos" styleClass="table-bordered table-striped"
												headerClass="standardTable_Header"
												footerClass="standardTable_Header"
												rowClasses="standardTable_Row1,standardTable_Row2"
												sortable="true" columnClasses="standardTable_Column"
												var="lotesE" style=" width : 890px;" rows="10"> 
												<x:column>
													<f:facet name="header">
														<x:outputText id="LEPGNroLote" value="Numero Lote"
															styleClass="texto"  />
													</f:facet>
													<x:outputText id="LEPGNroLoteValue"
														value="#{lotesE.plasticoLote.numeroLoteFormateado}"
														styleClass="texto">
													</x:outputText>
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LEPGFechaGeneracion"
															value="Fecha Generacion" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LEPGFechaGeneracionValue"
														value="#{lotesE.plasticoLote.fechaGeneracion}"
														styleClass="texto" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LEPGCantPlasticos"
															value="Cantidad Plasticos" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LEPGCantPlasticosValue"
														value="#{lotesE.plasticoLote.cantidadPlasticos}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LEPGCantTitulares"
															value="Cantidad Titulares" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LEPGCantTitularesValue"
														value="#{lotesE.plasticoLote.cantidadTitulares}"
														styleClass="numero" />
												</x:column>
											</x:dataTable>
										</x:panelGrid>
									</s:layoutingTitlePane>
								</x:panelGrid>
							</c:if>
							<%-- Fin Area Lotes pendientes erróneos o en generacion --%>

							<%-- Area Lotes pendientes de embozar --%>
							<c:if
								test="${EmbozadoLotesPlasticosBean.mostrarLotesPendEmbozar}">
								<x:panelGrid id="resumenLotesPendEmbozar" columns="1"
									width="900" align="center">
									<s:layoutingTitlePane id="cLotesPE"
										label="Lotes pendientes de Procesar"
										containerNodeClass="contentTitlePane"
										labelNodeClass="labelTitlePane">
										<x:panelGrid id="lPendEmbozar" columns="1">
											<x:outputText id="cLPE"
												value="Lotes pendientes de embozar:  #{EmbozadoLotesPlasticosBean.cantidadLotesPendientes}"
												styleClass="textoACenter" />
										</x:panelGrid>
										<x:panelGrid id="LPEPG" columns="1" align="center" width="850">
											<x:dataTable
												value="#{EmbozadoLotesPlasticosBean.lotesPendEmbozarList}"
												id="listadoPendEmbozar" styleClass="table-bordered table-striped"
												headerClass="standardTable_Header"
												footerClass="standardTable_Header"
												rowClasses="standardTable_Row1,standardTable_Row2"
												sortable="true" columnClasses="standardTable_Column"
												var="lotePE" style=" width : 890px;"  rows="10" >
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGNroLote" value="Numero Lote"
															styleClass="texto"  />
													</f:facet>
													<x:outputText
														value="#{lotePE.plasticoLote.numeroLoteFormateado}"
														styleClass="texto">
													</x:outputText>
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGFechaGeneracion"
															value="Fecha Generacion" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LPEPGFechaGeneracionValue"
														value="#{lotePE.plasticoLote.fechaGeneracion}"
														styleClass="texto" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGCantPlasticos"
															value="Cantidad Plasticos" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LPEPGCantPlasticosValue"
														value="#{lotePE.plasticoLote.cantidadPlasticos}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGCantTitulares"
															value="Cantidad Titulares" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LPEPGCantTitularesValue"
														value="#{lotePE.plasticoLote.cantidadTitulares}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGTipoLote"
															value="Generacion Lote" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="LPEPGTipoLoteValue"
														value="#{lotePE.plasticoLote.descripcionTipoLote}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPEPGProcesar" value="Procesar"
															styleClass="texto"  />
													</f:facet>
													<f:verbatim>
														<div id="divButtonsAction" align="center">
													</f:verbatim>
													<x:commandButton id="cmdProcesar"
														image="/img/procesar_16x16.png"
														action="#{lotePE.procesarLoteWrapper}"></x:commandButton>
													<f:verbatim>
														</div>
													</f:verbatim>
												</x:column>
											</x:dataTable>
											<%--<f:verbatim>
												<div style="position: absolute; width: 100%; align: center">
													<%@include file="/inc/paginator.jsp"%>
												</div>
											</f:verbatim> --%>
										</x:panelGrid>
									</s:layoutingTitlePane>
								</x:panelGrid>
							</c:if>
							<%-- Area Lotes pendientes de embozar --%>



							<%-- Area Lotes procesados --%>
							<c:if test="${EmbozadoLotesPlasticosBean.mostrarLotesProcesados}">

								

								<x:panelGrid id="resumenLotesProcesados" columns="1" width="900"
									align="center">
									<s:layoutingTitlePane id="cLotesP" label="Lotes Procesados"
										containerNodeClass="contentTitlePane"
										labelNodeClass="labelTitlePane">
										<x:panelGrid id="lProcesados" columns="1">
											<x:outputText id="cLP"
												value="Lotes Procesados:  #{EmbozadoLotesPlasticosBean.cantidadLotesProcesados}"
												styleClass="textoACenter" />
										</x:panelGrid>

										<x:panelGrid id="LP" columns="1" align="center" width="910">
											<x:dataTable
												value="#{EmbozadoLotesPlasticosBean.lotesProcesadosList}"
												id="listado" styleClass="table-bordered table-striped"
												headerClass="standardTable_Header"
												footerClass="standardTable_Header"
												rowClasses="standardTable_Row1,standardTable_Row2"
												sortable="true" columnClasses="standardTable_Column"
												var="lotesP" style=" width : 890px;" rows="21">
												<x:column>
													<f:facet name="header">
														<x:outputText id="lotesPNroLote" value="Numero Lote"
															styleClass="texto"  />
													</f:facet>
													<x:commandLink id="cmdLote"
														value="#{lotesP.plasticoLote.numeroLoteFormateado}"
														action="#{lotesP.verPlasticosWrapper}"></x:commandLink>
													<%--<x:outputText id="lotesPNroLoteValue"
														value="#{lotesP.plasticoLote.numeroLoteFormateado}"
														styleClass="texto">
													</x:outputText>--%>
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="lotesPFechaGeneracion"
															value="Fecha Generacion" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="lotesPFechaGeneracionValue"
														value="#{lotesP.plasticoLote.fechaGeneracion}"
														styleClass="texto" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="lotesPCantPlasticos"
															value="Cantidad Plasticos" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="lotesPCantPlasticosValue"
														value="#{lotesP.plasticoLote.cantidadPlasticos}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="lotesPCantTitulares"
															value="Cantidad Titulares" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="lotesPCantTitularesValue"
														value="#{lotesP.plasticoLote.cantidadTitulares}"
														styleClass="numero" />
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="lotesPTipoLote"
															value="Generacion Lote" styleClass="texto"
															 />
													</f:facet>
													<x:outputText id="lotesPTipoLoteValue"
														value="#{lotesP.plasticoLote.descripcionTipoLote}"
														styleClass="numero" />
												</x:column>

												<x:column>
													<f:facet name="header">
														<x:outputText id="LPVerEmbozo" value="Ver embozos"
															styleClass="texto"  />
													</f:facet>
													<f:verbatim>
														<div id="divButtonsActionEmb" align="center">
													</f:verbatim>

													<x:commandButton id="cmdGetEmbozo"
														image="/img/file_16x16.gif"
														action="#{lotesP.verArchivoEmbozoLote}"></x:commandButton>
														
													<f:verbatim>
														</div>
													</f:verbatim>
												</x:column>
												
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPVerEmbozoNew" value="Embozadora Nueva"
															styleClass="texto"  />
													</f:facet>
													
													<f:verbatim>
														<div id="divButtonsActionEmbnew" align="center">
													</f:verbatim>
														
														<x:commandButton id="cmdGetEmbozoNew"
														image="/img/file_16x16.gif"
														action="#{lotesP.verArchivoEmbozoLoteNew}"></x:commandButton>
																
													<f:verbatim>
														</div>
													</f:verbatim>
												</x:column>
								
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPVerAcuse" value="Ver acuses"
															styleClass="texto"  />
													</f:facet>
													<f:verbatim>
														<div id="divButtonsActionAcuses" align="center">
													</f:verbatim>
													<x:commandButton id="cmdGetAcuse"
														image="/img/pdf_16x16.png"
														action="#{lotesP.verArchivoAcuseLote}"></x:commandButton>
													<f:verbatim>
														</div>
													</f:verbatim>
												</x:column>
												<x:column>
													<f:facet name="header">
														<x:outputText id="LPVerEtiqueta" value="Ver etiquetas"
															styleClass="texto"  />
													</f:facet>
													<f:verbatim>
														<div id="divButtonsActionEtiqueta" align="center">
													</f:verbatim>
													<x:commandButton id="cmdGetEtiqueta"
														image="/img/pdf_16x16.png"
														action="#{lotesP.verArchivoEtiqueta}"></x:commandButton>
													<f:verbatim>
														</div>
													</f:verbatim>
												</x:column>
											</x:dataTable>
											<f:verbatim>
												<div style="position: absolute; width: 100%; align: center">
													<%@include file="/inc/paginator.jsp"%>
												</div>
											</f:verbatim>
										</x:panelGrid>
									</s:layoutingTitlePane>
								</x:panelGrid>
							</c:if>

							<%-- Fin Area Lotes procesados --%>

						</x:panelGrid>
					</h:panelGroup>
				</f:facet>

				

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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{EmbozadoLotesPlasticosBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
	<s:script language="javascript">
		//var popupPlasticos = window.open('','',800,600);
	</s:script>
</body>
	</html>
</f:view>
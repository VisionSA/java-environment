<%@include file="/inc/tags.jsp"%>
<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>
<html lang="es">
	<head>
	<title><h:outputText
		value="#{LineaCreditoClienteBean.tituloLargo}" /></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amLineaCreditoClienteForm').submit();
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

	<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amLineaCreditoClienteForm');"
		onload="if('${LineaCreditoClienteBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});};">
<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${LineaCreditoClienteBean.tituloCorto}
    <small>${LineaCreditoClienteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>


	<center> <h:form
		id="amLineaCreditoClienteForm">

		<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
		<h:panelGroup rendered="#{!amLineaCreditoClienteForm.popup.mostrar}">
			<%@include file="/inc/scroll.inc"%>
		</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout" headerClass="pageHeader"
			navigationClass="pageNavigation" bodyClass="pageBody"
			footerClass="pageFooter">

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal"
						width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog"
							styleClass="viewDialog" dialogTitle="Confirmacion">
							<h:panelGrid columns="2" width="500">
								<x:graphicImage
									value="/img/#{LineaCreditoClienteBean.popup.nombreIcono}" />
								<h:outputText value="#{LineaCreditoClienteBean.popup.mensaje}"
									styleClass="texto" />
							</h:panelGrid>
<%-- I5224 --%>					<h:panelGrid columns="3" width="500">
								<x:commandButton
									onclick="clickLink('lnContinuar');dojo.widget.byId('viewDialog').hide();"
									value="Continuar" styleClass="botones" title="Continuar en la pantalla."
									rendered="#{LineaCreditoClienteBean.continuar}"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton
									onclick="clickLink('lnCancelar');dojo.widget.byId('viewDialog').hide();"
									value="Salir" styleClass="botones" title="Cerrar." />
							</h:panelGrid>
						</s:modalDialog>						
					
						<x:commandLink id="lnContinuar"
							action="#{LineaCreditoClienteBean.irAContinuar}" forceId="true"
							style="display: block;" />
						<x:commandLink id="lnCancelar"
							action="#{LineaCreditoClienteBean.volverBuscar}" forceId="true"
 						style="display: block;" />
<%--F5224--%>

						<s:layoutingTitlePane id="filtroOrigenen"
							label="Filtro de Clientes" containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane"
							rendered="#{LineaCreditoClienteBean.buscar}">
							<h:panelGrid id="filtroCod" columns="4" align="center">
								<h:outputText value="Nro Documento:" styleClass="texto" />
								<h:inputText id="nroDocumentoFiltro"
									value="#{LineaCreditoClienteBean.cliente.individuo.nroDocumento}"
									styleClass="bordeceldainferior" maxlength="8" size="8"
									onkeypress="return soloEnteros(this,event);"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<h:outputText value="Cuil:" styleClass="texto" />
								<h:inputText id="cuilFiltro"
									value="#{LineaCreditoClienteBean.cliente.individuo.cuil}"
									styleClass="bordeceldainferior" maxlength="11" size="11"
									onkeypress="return soloEnteros(this,event);"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<h:outputText value="Apellido:" styleClass="texto" />
								<h:inputText id="apellidoFiltro"
									value="#{LineaCreditoClienteBean.cliente.individuo.apellido}"
									styleClass="bordeceldainferior" maxlength="20" size="8"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<h:outputText value="Nombre:" styleClass="texto" />
								<h:inputText id="nombresFiltro"
									value="#{LineaCreditoClienteBean.cliente.individuo.nombres}"
									styleClass="bordeceldainferior" maxlength="20" size="8"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<h:outputText value="Número Cuenta:" styleClass="texto" />
								<h:inputText id="codigoFiltro"
									value="#{LineaCreditoClienteBean.cliente.idCliente}"
									styleClass="bordeceldainferior" maxlength="6" size="8"
									onkeypress="return soloEnteros(this,event);"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />
								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<x:commandButton id="btnBuscarCliente" value="Buscar"
									action="#{LineaCreditoClienteBean.buscarCliente}"
									title="Busca el Cliente"
									styleClass="botones"/>

							</h:panelGrid>
						</s:layoutingTitlePane>

						<h:panelGrid id="panelPrincipalUno" columns="1" width="900"
							align="center"
							rendered="#{LineaCreditoClienteBean.clienteCargado}">
							<c:if test="${!empty LineaCreditoClienteBean.clienteList}">

								<s:fieldset legend=" Clientes " id="fieldDomicilios">

									<h:panelGrid columns="2" id="tablaDeDetallesFiel" width="850"
										align="center">
										<h:dataTable value="#{LineaCreditoClienteBean.clienteList}"
											id="tableDeDetallesFiel" styleClass="standardTable"
											headerClass="dataTable_Header"
											footerClass="standardTable_Header"
											rowClasses="standardTable_Row1,standardTable_Row2"
											columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
											var="item" style=" width : 840px;">

											<h:column>
												<f:facet name="header">
													<h:outputText value="Nombre Completo" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{item.individuo.apellido} , #{item.individuo.nombres}" />
											</h:column>								

											<h:column>
												<f:facet name="header">
													<h:outputText value="Nro Documento" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{item.individuo.nroDocumento}" />
											</h:column>

											<h:column>
												<f:facet name="header">
													<h:outputText value="Domicilio" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{item.individuo.domicilio.calleNombre}" />
											</h:column>											

											<h:column>
												<f:facet name="header">
													<h:outputText value="Cod Postal" styleClass="texto"
														 />
												</f:facet>
												<h:outputText value="#{item.individuo.domicilio.codigoPostal}" />
											</h:column>

											<h:column>
												<h:commandLink id="editarLimiteCreditoLink"
													action="#{LineaCreditoClienteBean.editarLimiteCredito}">
													<f:param id="idCliente" name="idCliente"
														value="#{item.idCliente}" />
													<h:graphicImage value="/img/icon/tomar.gif"
														title="Editar linea Credito Cliente" />
												</h:commandLink>
											</h:column>
										</h:dataTable>
									</h:panelGrid>

								</s:fieldset>
							</c:if>

							<c:if test="${empty LineaCreditoClienteBean.clienteList}">
								<h:outputText value="Lista vacia." styleClass="texto"
									id="detallesInexistentes" style="color:green" />
							</c:if>

							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							<h:panelGrid columns="6" width="600" id="panelBotonera">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>								
								<x:commandButton id="btnVolver2" value="Volver a Buscar"
									action="#{LineaCreditoClienteBean.volverBuscar}"
									styleClass="botones" />

							</h:panelGrid>

						</h:panelGrid>


						<h:panelGrid id="panelTitular" columns="1" width="900"
							rendered="#{LineaCreditoClienteBean.titular}">


							<s:fieldset legend=" Titular " id="fieldTitular">

								<h:panelGrid columns="2" id="tablaTitularFiel" width="850"
									align="center">
									<h:dataTable value="#{LineaCreditoClienteBean.listaCliente}"
										id="tableTitularFiel" styleClass="standardTable"
										headerClass="dataTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="item" style=" width : 840px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre Completo" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.nombreTitular}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Condición" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.condicion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Número Cuenta" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.idCliente}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Límite Crédito" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.limiteCredito}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Saldo en Linea" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.saldoLinea}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Disponible" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.disponible}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado Comercial" styleClass="texto"
													 />
											</f:facet>
											<h:outputText
												value="#{item.titular.estadoCliente.descripcion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado Cobranza" styleClass="texto"
													 />
											</f:facet>
											<h:outputText
												value="#{item.titular.estadoCobranza.descripcion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Habilitado Consumo" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.habilitadoConsumo}" />
										</h:column>

									</h:dataTable>
								</h:panelGrid>

							</s:fieldset>


						</h:panelGrid>

						<h:panelGrid id="panelAdicional" columns="1" width="900"
							rendered="#{LineaCreditoClienteBean.adicional}">

							<s:fieldset legend=" Adicional " id="fieldAdicional">

								<h:panelGrid columns="2" id="tablaAdicionalFiel" width="850"
									align="center">
									<h:dataTable value="#{LineaCreditoClienteBean.listaCliente}"
										id="tableAdicionalFiel" styleClass="standardTable"
										headerClass="dataTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="item" style=" width : 840px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre Completo" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.nombreCliente}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Condición" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.condicion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Nombre Titular" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.nombreTitular}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Número Cuenta" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.individuo.idIndividuo}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Límite Crédito" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.limiteCredito}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Saldo en Linea" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.titular.saldoLinea}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Disponible" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.disponible}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado Comercial" styleClass="texto"
													 />
											</f:facet>
											<h:outputText
												value="#{item.cliente.estadoCliente.descripcion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado Cobranza" styleClass="texto"
													 />
											</f:facet>
											<h:outputText
												value="#{item.cliente.estadoCobranza.descripcion}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Habilitado Consumo" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.cliente.habilitadoConsumo}" />
										</h:column>

									</h:dataTable>
								</h:panelGrid>

							</s:fieldset>

						</h:panelGrid>

						<h:panelGrid id="panelLineaCredito" columns="1" width="900"
							rendered="#{LineaCreditoClienteBean.tieneLineaCredito}">

							<s:fieldset legend=" Linea Credito Vigente"
								id="fieldLineaCredito">

								<h:panelGrid columns="2" id="tablaLineaCreditoFiel" width="850"
									align="center">
									<h:dataTable value="#{LineaCreditoClienteBean.lineaCredCompos}"
										id="tableLineaCreditoFiel" styleClass="standardTable"
										headerClass="dataTable_Header"
										footerClass="standardTable_Header"
										rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="item" style=" width : 840px;">

										<h:column>
											<f:facet name="header">
												<h:outputText value="Importe" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.importe}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Desde" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.fechaDesde}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha Hasta" styleClass="texto"
													 />
											</f:facet>
											<h:outputText value="#{item.fechaHasta}" />
										</h:column>

									</h:dataTable>
								</h:panelGrid>

							</s:fieldset>

						</h:panelGrid>


						<s:layoutingTitlePane id="filtrolimiteCredito"
							label="Cambio Límite Credito"
							containerNodeClass="contentTitlePane"
							labelNodeClass="labelTitlePane"
							rendered="#{LineaCreditoClienteBean.cambiarLimite}">
							<h:panelGrid id="filtroLimiteCredito" columns="4" align="center">
								<h:outputText value="Monto:" styleClass="texto" />
								<h:inputText id="monto" value="#{LineaCreditoClienteBean.monto}"
									styleClass="bordeceldainferior" maxlength="8" size="8"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" />

								<h:outputText value="Cantidad Días:" styleClass="texto" />
								<h:selectOneMenu id="lstTipoConc"
									value="#{LineaCreditoClienteBean.idSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<%--binding="#{ConciliacionCuponesBean.tipoConc}"
									 valueChangeListener="#{ConciliacionCuponesBean.cambiarTipoAccion}"
									onchange="submit();" --%>
									<f:selectItems id="itemTipo"
										value="#{LineaCreditoClienteBean.cantidadDias}" />
								</h:selectOneMenu>

								<%-- h:inputText id="cantDias"
									value="#{LineaCreditoClienteBean.cantDias}"
									styleClass="bordeceldainferior" maxlength="1" size="8"
									style="width: 90px" onfocus="encender(this);"
									onblur="apagar(this);" / --%>

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

								<x:commandButton id="btnAceptar" value="Aceptar"
									action="#{LineaCreditoClienteBean.aceptarLimite}"
									styleClass="botones" />

								<x:commandButton id="btnVolver" value="Volver a Buscar"
									action="#{LineaCreditoClienteBean.volverBuscar}"
									styleClass="botones" />

								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

							</h:panelGrid>
						</s:layoutingTitlePane>

					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
		
		</x:panelLayout>
	</h:form></center>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
	</body>
	</html>
</f:view>

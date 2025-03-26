<%@include file="/inc/tags.jsp"%>

<jsp:useBean id="ahora" class="java.util.Date" scope="request" />

<f:view>

	<html lang="es">
<head>

<title>Tarjeta Fiel | Autorizaciones</title>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
<s:script language="javascript">
        function popup(pagina,popW,popH) {
        var w = 0, h = 0;
        w = screen.width;
        h = screen.height;
      
        var leftPos = (w-popW)/2, topPos = (h-popH)/2;
      
        popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
        if (popupWindow.opener == null){popupWindow.opener = self;}
      };
    </s:script>

<style>
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>

</head>


<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue layout-top-nav">


	<header class="main-header">

		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<div class="navbar-header">
				<div class="navbar-brand">
					<b>Tarjeta</b>FIEL &nbsp|&nbsp Autorización Telefónica
				</div>
			</div>

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav pull-right">

					<!-- User Account -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="hidden-xs"><%=request.getSession().getAttribute("nombreOperador")%></span>
					</a>
						<ul class="dropdown-menu">
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="row">
									<div class="col-xs-4"></div>
									<div class="col-xs-4">
										<a class="btn btn-default btn-flat" onclick="salir()">Cerrar</a>
									</div>
									<div class="col-xs-4"></div>
								</div>
							</li>
						</ul>
					</li>

				</ul>
			</div>
		</nav>

	</header>

	<!-- Main content -->
	<div class="content-wrapper">

		<!-- Main content -->
		<section class="content">



			<!-- START CUSTOM TABS -->
			<div class="row">
				<div class="col-md-12">
					<!-- Custom Tabs -->
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li id="tabCompras"><a href="#tab_1" data-toggle="tab">Compras</a>
							</li>
							<li id="tabAnulaciones"><a href="#tab_2" data-toggle="tab">Anulaciones</a>
							</li>
							<li id="tabDevoluciones"><a href="#tab_3" data-toggle="tab">Devoluciones</a>
							</li>
						</ul>

						<div class="tab-content">




							<!-- TAB COMPRAS -->
							<div class="tab-pane" id="tab_1">
								<!-- PANELES -->
								<div class="row">


									<!-- PANEL CLIENTE -->
									<div class="col-md-5">

										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Cliente</h3>
											</div>

											<h:form id="formCliente">

												<div class="box-body">

													<div class="col-md-10">
														<label>Ingrese Nº de Plástico</label>

														<div class="row">

															<div class="form-group col-md-2 col-lg-2">
																<input type="text" class="form-control" id="txt1"
																	style="min-width: 40px; padding: 5px; text-align: center; background: white"
																	value="5049" readonly="readonly">
															</div>

															<div class="form-group col-md-2 col-lg-2">
																<input type="text" class="form-control" id="txt2"
																	style="min-width: 40px; padding: 5px; text-align: center; background: white"
																	value="06" readonly="readonly">
															</div>

															<div class="form-group col-md-2 col-lg-2">
																<input type="text" class="form-control" id="texto1"
																	style="min-width: 55px; padding: 5px; text-align: center"
																	maxlength="2" pattern="[0-9]{2}"
																	onkeyup="if (this.value.length == this.getAttribute('maxlength')) texto2.focus()"
																	autocomplete="off" required onclick="limpiarTab(1);"
																	oninvalid="setCustomValidity('Complete campo: 2 dígitos numéricos')"
																	onchange="try{setCustomValidity('')}catch(e){}" />
															</div>

															<div class="form-group col-md-2 col-lg-2">
																<input type="text" class="form-control" id="texto2"
																	style="min-width: 55px; padding: 5px; text-align: center"
																	maxlength="4" pattern="[0-9]{4}"
																	onkeyup="if (this.value.length == this.getAttribute('maxlength')) texto3.focus()"
																	autocomplete="off" required
																	oninvalid="setCustomValidity('Complete campo: 4 dígitos numéricos')"
																	onchange="try{setCustomValidity('')}catch(e){}" />
															</div>

															<div class="form-group col-md-2 col-lg-2">
																<input type="text" class="form-control" id="texto3"
																	style="min-width: 55px; padding: 5px; text-align: center"
																	maxlength="4" pattern="[0-9]{4}"
																	onkeyup="if (this.value.length == this.getAttribute('maxlength')) inputCvv.focus()"
																	autocomplete="off" required
																	oninvalid="setCustomValidity('Complete campo: 4 dígitos numéricos')"
																	onchange="try{setCustomValidity('')}catch(e){}" />
															</div>

															<x:inputHidden
																value="#{AutorizacionTelefonicaBean.nroTarjeta}"
																id="inputNroTarjetaH" />

															<div class="form-group col-md-1 col-lg-2"></div>

														</div>

													</div>


													<div class="col-md-2">
														<label>CVV</label> <input type="text" class="form-control"
															id="inputCvv"
															style="min-width: 60px; padding: 5px; text-align: center; margin-bottom: 15px"
															maxlength="3" autocomplete="off" required
															pattern="[0-9]{1,}"
															oninvalid="setCustomValidity('Complete campo: Hasta 3 dígitos numéricos')"
															onchange="try{setCustomValidity('')}catch(e){}" />
														<x:inputHidden value="#{AutorizacionTelefonicaBean.cvv}"
															id="inputCvvH" />
													</div>



													<div id="datosPlastico" style="display: none;">

														<div class="row">
															<div class="col-md-4">
																<div class="text-light-blue" id="labelVtoPlastico"
																	style="font-size: 14px">Vencimiento:</div>
																<p id="txtVtoPlastico">${AutorizacionTelefonicaBean.vtoPlastico}</p>
															</div>

															<div class="col-md-4">
																<div class="text-light-blue" style="font-size: 14px">Estado:
																</div>
																<p id="txtEstadoPlastico">${AutorizacionTelefonicaBean.estadoPlastico}</p>
															</div>

															<div class="col-md-4"></div>

														</div>


														<div class="row">

															<div class="col-md-4">
																<div class="text-light-blue" style="font-size: 14px">Cliente:
																</div>
																<p id="txtNombre">${AutorizacionTelefonicaBean.nombreTarjeta}</p>
															</div>

															<div class="col-md-4">
																<div class="text-light-blue" style="font-size: 14px">DNI:
																</div>
																<p id="txtDni">${AutorizacionTelefonicaBean.dni}</p>
															</div>

															<div class="col-md-4"></div>

														</div>

													</div>



													<div class="col-md-12" id="btnBuscarCliCompra">
														<h:commandButton
															action="#{AutorizacionTelefonicaBean.buscarClienteCompra}"
															onclick="buscarCliente(1)"
															styleClass="btn btn-primary pull-right"
															style="margin-top: 10px" value="Buscar" />
													</div>

													<div class="col-md-12" id="btnRecargarCliCompra"
														style="display: none">
														<h:commandButton
															action="#{AutorizacionTelefonicaBean.recargarCliente}"
															styleClass="btn btn-primary pull-right"
															style="margin-top: 10px" value="Recargar" />
													</div>


												</div>




												<div id="datosCliente" class="box-footer"
													style="display: none;">

													<div class="col-md-4">
														<div class="text-light-blue" style="font-size: 16px">TITULAR:
														</div>
														<p id="txtNombre">${AutorizacionTelefonicaBean.nombreTitular}</p>

														<div class="text-light-blue" style="font-size: 16px">Nº
															de Cuenta:</div>
														<p id="txtNroCuenta">${AutorizacionTelefonicaBean.nroCuenta}</p>
													</div>

													<div class="col-md-4">
														<div class="text-light-blue" style="font-size: 16px">Situación
															Comercial:</div>
														<p id="txtSitComercial">${AutorizacionTelefonicaBean.situacionComercial}</p>

														<div class="text-light-blue" style="font-size: 16px">Situación
															Cobranza:</div>
														<p id="txtSitCobranza">${AutorizacionTelefonicaBean.situacionCobranza}</p>

														<div class="text-light-blue" id="labelConsumoHabilitado"
															style="font-size: 16px">Consumo Habilitado:</div>
														<p id="txtConsumoHabilitado">${AutorizacionTelefonicaBean.consumoHabilitado}</p>
													</div>

													<div class="col-md-4">
														<div class="text-light-blue" style="font-size: 16px">Linea
															de Crédito:</div>
														<p id="txtDisponible">${AutorizacionTelefonicaBean.lineaCredito}</p>
														<div class="text-light-blue" style="font-size: 16px">Saldo
															en línea:</div>
														<p id="txtSaldo">${AutorizacionTelefonicaBean.saldo}</p>
														<div class="text-light-blue" style="font-size: 16px">Disponible:
														</div>
														<p id="txtDisponible" class="text-green"
															style="font-weight: bold; font-size: 20px">${AutorizacionTelefonicaBean.disponible}</p>
													</div>

												</div>



											</h:form>

										</div>

									</div>
									<!-- FIN PANEL CLIENTE -->


									<!-- PANEL COMERCIO -->
									<div id="panelComercio" class="col-md-4" style="display: none;">
										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Comercio</h3>
											</div>

											<h:form id="formComercio">
												<div class="box-body">
													<div class="col-md-12">
														<label>Código Comercio</label>
														<div class="form-row">
															<div class="col-md-6">
																<input type="text" id="inputCodComercio" maxlength="4"
																	class="form-control" style="min-width: 60px"
																	autocomplete="off" required onclick="limpiarComercio()"
																	value="${AutorizacionTelefonicaBean.codComercio}"
																	pattern="[0-9]{1,}"
																	oninvalid="setCustomValidity('Solo Números')"
																	onchange="try{setCustomValidity('')}catch(e){}">
																<a href="#"
																	onclick=" abrirModal('#modal-obtener-comercio'); ocultarElemento('tablaBuscarComercio')">Obtener
																	Comercio</a>
																<x:inputHidden
																	value="#{AutorizacionTelefonicaBean.codComercio}"
																	id="inputCodComercioH" />
															</div>
															<div class="col-md-6">
																<h:commandButton id="btnBuscarComercio"
																	action="#{AutorizacionTelefonicaBean.buscarComercio}"
																	onclick="buscarComercio()"
																	styleClass="btn btn-primary pull-right"
																	style="margin-bottom: 20px; margin-left: 20px"
																	value="Buscar" />
															</div>
														</div>
													</div>
												</div>



												<div id="datosComercio" class="box-footer"
													style="display: none;">

													<div class="col-md-6">
														<div class="text-light-blue" style="font-size: 16px">Empresa:
														</div>
														<p id="txtEmpresa">${AutorizacionTelefonicaBean.empresa}</p>
														<div class="text-light-blue" style="font-size: 16px">Sucursal:
														</div>
														<p id="txtSucursal">${AutorizacionTelefonicaBean.sucursal}</p>

														<div class="text-light-blue" style="font-size: 16px">Localidad:
														</div>
														<p id="txtLocalidad">${AutorizacionTelefonicaBean.localidad}</p>
														<div class="text-light-blue" style="font-size: 16px">Dirección:
														</div>
														<p id="txtDireccion">${AutorizacionTelefonicaBean.direccion}</p>
													</div>


													<div class="col-md-6">
														<div class="text-light-blue" style="font-size: 16px">Lista
															de Precios del Comercio:</div>
														<p id="txtListaPrecios">${AutorizacionTelefonicaBean.planCuotas}</p>

														<div class="text-light-blue" style="font-size: 16px">Cuotas
															Disponibles:</div>
														<p id="txtCuotasDisponibles">${AutorizacionTelefonicaBean.cuotasDisponibles}</p>
													</div>

												</div>



											</h:form>

										</div>
									</div>
									<!-- FIN PANEL COMERCIO -->


									<!-- PANELE OPERACION -->
									<div id="panelOperacion" class="col-md-3"
										style="display: none;">
										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Datos Compra</h3>
											</div>



											<h:form id="formOperacion">

												<div class="box-body">

													<div class="col-md-12">

														<div class="form-row">
															<div class="form-group col-md-12">
																<label>Monto de Compra</label> <input
																	value="${AutorizacionTelefonicaBean.monto}" type="text"
																	pattern="\d*|\d*\.\d{1,2}"
																	oninvalid="setCustomValidity('Monto mal ingresado.')"
																	onchange="try{setCustomValidity('')}catch(e){})"
																	class="form-control" id="inputMonto"
																	style="min-width: 60px" autocomplete="off" required>
																<x:inputHidden
																	value="#{AutorizacionTelefonicaBean.monto}"
																	id="inputMontoH" />
															</div>

															<div class="form-group col-md-12">
																<label>Cantidad de Cuotas</label> <input
																	value="${AutorizacionTelefonicaBean.cuotas}"
																	type="text" pattern="[0-9]{1,}"
																	oninvalid="setCustomValidity('Solo Números')"
																	onchange="try{setCustomValidity('')}catch(e){}"
																	class="form-control" id="inputCuotas"
																	style="min-width: 60px" autocomplete="off" required>
																<x:inputHidden
																	value="#{AutorizacionTelefonicaBean.cuotas}"
																	id="inputCuotasH" />
																<a href="#" onclick="abrirSimulador()">Simular
																	Cuotas</a>
															</div>

															<div class="form-group col-md-12">
																<label>Número de Cupón</label> <input
																	value="${AutorizacionTelefonicaBean.nroCupon}"
																	type="text" pattern="[0-9]{1,}"
																	oninvalid="setCustomValidity('Solo Números')"
																	onchange="try{setCustomValidity('')}catch(e){}"
																	class="form-control" id="inputNroCupon"
																	style="min-width: 60px" autocomplete="off" required>
																<x:inputHidden
																	value="#{AutorizacionTelefonicaBean.nroCupon}"
																	id="inputNroCuponH" />
															</div>
														</div>

													</div>

												</div>

												<div class="box-footer">
													<h:commandButton id="btnAceptarConsumo"
														action="#{AutorizacionTelefonicaBean.aceptarConsumo}"
														onclick="aceptarConsumo()"
														styleClass="btn btn-primary pull-right"
														value="Aceptar Consumo" />
												</div>

											</h:form>


										</div>
									</div>
									<!-- FIN PANELE OPERACION -->


									<div class="col-md-4"></div>


								</div>
								<!-- FIN PANELES -->
							</div>
							<!-- FIN TAB COMPRAS -->



							<!-- TAB ANULACIONES -->
							<div class="tab-pane" id="tab_2">
								<!-- PANELES -->
								<div class="row">


									<!-- PANEL COMERCIO -->
									<div id="panelComercioAnulacion" class="col-md-4">
										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Comercio</h3>
											</div>

											<h:form id="formComercioAnulacion">
												<div class="box-body">
													<div class="col-md-12">
														<label>Código Comercio</label>
														<div class="form-row">
															<div class="col-md-6">
																<input type="text" id="inputCodComercioAnulacion" maxlength="4"
																	class="form-control" style="min-width: 60px"
																	autocomplete="off" required onclick="limpiarComercio()"
																	value="${AutorizacionTelefonicaBean.codComercio}"
																	pattern="[0-9]{1,}"
																	oninvalid="anulacionsetCustomValidity('Solo Números')"
																	onchange="try{setCustomValidity('')}catch(e){}">
																<a href="#"
																	onclick=" abrirModal('#modal-obtener-comercio'); ocultarElemento('tablaBuscarComercio')">Obtener
																	Comercio</a>
																<x:inputHidden
																	value="#{AutorizacionTelefonicaBean.codComercio}"
																	id="inputCodComercioHAnulacion" />
															</div>
															<div class="col-md-6">
																<h:commandButton id="btnBuscarComercioAnulacion"
																	action="#{AutorizacionTelefonicaBean.buscarComercioAnulacion}"
																	onclick="buscarComercioAnulacion()"
																	styleClass="btn btn-primary pull-right"
																	style="margin-bottom: 20px; margin-left: 20px"
																	value="Buscar" />
															</div>
														</div>
													</div>
												</div>



												<div id="datosComercioAnulacion" class="box-footer"
													style="display: none;">

													<div class="col-md-6">
														<div class="text-light-blue" style="font-size: 16px">Empresa:
														</div>
														<p id="txtEmpresaAnulacion">${AutorizacionTelefonicaBean.empresa}</p>
														<div class="text-light-blue" style="font-size: 16px">Sucursal:
														</div>
														<p id="txtSucursalAnulacion">${AutorizacionTelefonicaBean.sucursal}</p>

														<div class="text-light-blue" style="font-size: 16px">Localidad:
														</div>
														<p id="txtLocalidadAnulacion">${AutorizacionTelefonicaBean.localidad}</p>
														<div class="text-light-blue" style="font-size: 16px">Dirección:
														</div>
														<p id="txtDireccionAnulacion">${AutorizacionTelefonicaBean.direccion}</p>
													</div>


													<div class="col-md-6">
														<div class="text-light-blue" style="font-size: 16px">Lista
															de Precios del Comercio:</div>
														<p id="txtListaPreciosAnulacion">${AutorizacionTelefonicaBean.planCuotas}</p>

														<div class="text-light-blue" style="font-size: 16px">Cuotas
															Disponibles:</div>
														<p id="txtCuotasDisponiblesAnulacion">${AutorizacionTelefonicaBean.cuotasDisponibles}</p>
													</div>

												</div>



											</h:form>

										</div>
									</div>
									<!-- FIN PANEL COMERCIO -->


									<!-- PANELE OPERACIONES -->
									<div id="panelOperacionAnulacion" class="col-md-8"
										style="display: none;">
										<div class="box box-primary">
											<div class="box-header with-border">
												<h3 class="box-title">Seleccionar Movimiento a Anular</h3>
											</div>


											<h:form id="formOperacionAnulacion">

												<div class="box-body">

													<div class="col-md-12">

														<div class="form-row" id="tablaMovimientos">
														
														<table class="table table-striped table-bordered table-hover">
															<tr>
																<th class="text-right">Fecha y Hora</th>
																<th>Nombre Cliente</th>
																<th class="text-right">DNI</th>
																<th class="text-right">Nro Cliente</th>
																<th class="text-right">Importe</th>
																<th class="text-right">Cuotas</th>
																<th class="text-right">Nro Tarjeta</th>
															</tr>
															<c:forEach var="movimientoPendiente"
																items="${AutorizacionTelefonicaBean.movimientosPendientes}">
																<tr onclick="seleccionarMovimiento({
																	'idTransaccion':'${movimientoPendiente.idTransaccion}',
																	'fecha':'${movimientoPendiente.fecha}',
																	'nombre':'${movimientoPendiente.nombre}',
																	'dni':'${movimientoPendiente.dni}',
																	'idCliente':'${movimientoPendiente.idCliente}',
																	'importe':'${movimientoPendiente.importe}',
																	'cuotas':'${movimientoPendiente.cuotas}',
																	'nroTarjeta':'${movimientoPendiente.nroTarjeta}'
																	})">
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.fecha}</a></td>
																	<td align="left"><a href="#" class="text-black">${movimientoPendiente.nombre}</a></td>
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.dni}</a></td>
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.idCliente}</a></td>
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.importe}</a></td>
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.cuotas}</a></td>
																	<td align="right"><a href="#" class="text-black">${movimientoPendiente.nroTarjeta}</a></td>
																</tr>
															</c:forEach>
														</table>
														
														</div>
														
														<div class="form-row" id="tablaMovSeleccionado" style="display:none">
															<table class="table table-striped table-bordered">
																<tr>
																	<th class="text-right">Fecha y Hora</th>
																	<th>Nombre Cliente</th>
																	<th class="text-right">DNI</th>
																	<th class="text-right">Nro Cliente</th>
																	<th class="text-right">Importe</th>
																	<th class="text-right">Cuotas</th>
																	<th class="text-right">Nro Tarjeta</th>
																</tr>
																<tr>
																	<td align="right"><a class="text-black" id="fechaSelect"></a></td>
																	<td align="left"><a class="text-black" id="nombreSelect"></a></td>
																	<td align="right"><a class="text-black" id="dniSelect"></a></td>
																	<td align="right"><a class="text-black" id="nroClienteSelect"></a></td>
																	<td align="right"><a class="text-black" id="importeSelect"></a></td>
																	<td align="right"><a class="text-black" id="cuotasSelect"></a></td>
																	<td align="right"><a class="text-black" id="nroTarjetaSelect"></a></td>
																</tr>
															</table>	
															<a class="pull-right" href="#"onclick="desSeleccionarMov()">Volver</a>
														</div>

													</div>

												</div>

												<div class="box-footer" id="footerAnularMov" style="display:none">
													<div class="col-md-12">
														<x:inputHidden value="#{AutorizacionTelefonicaBean.idTransaccionSeleccionada}" id="idTransaccionSeleccionadaH" />
														<h:commandButton id="btnAnularConsumo" value="Anular Consumo" action="#{AutorizacionTelefonicaBean.anularConsumo}" styleClass="btn btn-primary btn-flat pull-right"></h:commandButton>
													</div>
												</div>

											</h:form>


										</div>
									</div>
									<!-- FIN PANELE OPERACIONES -->


								</div>
								<!-- FIN PANELES -->
							</div>
							<!-- FIN TAB ANULACIONES -->



							<!-- TAB DEVOLUCIONES -->
							<div class="tab-pane" id="tab_3">En Desarrollo</div>
							<!-- FIN TAB DEVOLUCIONES -->


						</div>
						<!-- /.tab-content -->
					</div>
					<!-- nav-tabs-custom -->
				</div>
				<!-- /.col -->



				<!-- MODAL OBTENER COMERCIO -->
				<div class="modal fade" id="modal-obtener-comercio">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Obtener Comercio</h4>
							</div>

							<div class="modal-body row">
								<h:form id="formBuscarComercio">
									<div class="col-md-12 col-xs-12">
										<label>Parámetro de Búsqueda:</label>
									</div>
									<div class="col-md-6 col-xs-8">
										<input type="text"
											value="${AutorizacionTelefonicaBean.parametroBusqueda}"
											title="Puede buscar por CUIT, Razon social o Nombre de sucursal."
											class="form-control" id="inputBusquedaComercio"
											name="inputBusquedaComercio" style="min-width: 60px"
											autocomplete="off" required />
										<x:inputHidden
											value="#{AutorizacionTelefonicaBean.parametroBusqueda}"
											id="inputBusquedaComercioH" />
										<x:inputHidden value="#{AutorizacionTelefonicaBean.selectedTab}"
											id="selectedTab" />
									</div>
									<div class="col-md-6 col-xs-4">
										<h:commandButton id="btnBusquedaComercio"
											action="#{AutorizacionTelefonicaBean.busquedaComercio}"
											onclick="busquedaComercio()"
											styleClass="btn btn-primary pull-right" value="Buscar" />
									</div>
								</h:form>
							</div>


							<div class="modal-footer">
								<div class="col-md-12 col-xs-12" id="tablaBuscarComercio"
									style="display: none">
									<table class="table table-striped table-bordered table-hover">
										<tr>
											<th class="text-right">Cod. Posnet</th>
											<th class="text-right">Cuit</th>
											<th>Razón Social</th>
											<th>Descripción</th>
										</tr>
										<c:forEach var="comercio"
											items="${AutorizacionTelefonicaBean.listaComercios}">
											<tr onclick="seleccionarComercio(${comercio.codComercio})">
												<td align="right"><a href="#" class="text-black">${comercio.codComercio}</a>
												</td>
												<td align="right"><a href="#" class="text-black">${comercio.cuit}</a>
												</td>
												<td align="left"><a href="#" class="text-black">${comercio.empresa}</a>
												</td>
												<td align="left"><a href="#" class="text-black">${comercio.sucursal}</a>
												</td>
											</tr>
										</c:forEach>
									</table>
								</div>
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal">Cerrar</button>
							</div>

						</div>
					</div>
				</div>
				<!-- MODAL OBTENER COMERCIO -->



				<!-- MODAL SIMULAR CUOTAS -->
				<div class="modal fade" id="modal-simulador-cuotas">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Simulador de Cuotas</h4>
							</div>

							<div class="modal-body row">
								<h:form id="formSimularCuotas">

									<div class="col-md-12">
										<label>Monto:</label>
									</div>

									<div class="col-md-6 col-xs-6">
										<input value="${AutorizacionTelefonicaBean.monto}"
											id="inputMontoSimulador" type="text"
											pattern="\d*|\d*\.\d{1,2}"
											oninvalid="setCustomValidity('Monto mal ingresado.')"
											onchange="try{setCustomValidity('')}catch(e){}"
											class="form-control" style="min-width: 60px"
											autocomplete="off" required />
										<x:inputHidden value="#{AutorizacionTelefonicaBean.monto}"
											id="inputMontoSimuladorH" />
										<x:inputHidden value="#{AutorizacionTelefonicaBean.cuotas}"
											id="inputCuotasSimuladorH" />
										<x:inputHidden value="#{AutorizacionTelefonicaBean.nroCupon}"
											id="inputNroCuponSimuladorH" />
									</div>

									<div class="col-md-6 col-xs-6">
										<h:commandButton id="btnSimularCuotas"
											action="#{AutorizacionTelefonicaBean.simularCuotas}"
											onclick="simularCuotas()"
											styleClass="btn btn-primary pull-right" value="Simular" />
									</div>

								</h:form>
							</div>

							<div class="modal-footer">
								<div class="col-md-12 col-xs-12" id="tablaSimuladorCuotas"
									style="display: none">
									<table class="table table-striped table-bordered table-hover">
										<tr>
											<th class="text-right">Cuota</th>
											<th class="text-right">Importe</th>
										</tr>
										<c:forEach var="cuotaSimulada"
											items="${AutorizacionTelefonicaBean.cuotaSimulada}">
											<tr onclick="document.getElementById('inputCuotas').value = '${cuotaSimulada.cantidadCuotas}'" data-dismiss="modal">
												<td align="right"><a href="#" class="text-black">${cuotaSimulada.cantidadCuotas}</a></td>
												<td align="right"><a href="#" class="text-black">${cuotaSimulada.monto}</a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
				<!-- MODAL SIMULAR CUOTAS -->



				<!-- MODAL TRANSACCION APROBADA -->
				<div class="modal fade" id="modal-aprobada">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="limpiarTab(1)">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Transacción Aprobada</h4>
							</div>
							<div class="modal-body">
								<p>Codigo Autorización:
									${AutorizacionTelefonicaBean.codAutorizacion}</p>
								<p>Mensaje: ${AutorizacionTelefonicaBean.mensaje}</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal" onclick="limpiarTab(1)">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
				<!-- MODAL TRANSACCION APROBADA -->



				<!-- MODAL SESION VENCIDA -->
				<div class="modal fade" id="modal-sesion">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="salir()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Se Venció su sesión</h4>
							</div>
							<div class="modal-body">
								<p>Por favor vuelva a ingresar.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal" onclick="salir()">Salir</button>
							</div>
						</div>
					</div>
				</div>
				<!-- MODAL SESION VENCIDA -->

				<!-- MODAL ERROR TRANSACCIONADOR -->
				<div class="modal fade" id="modal-transaccionador">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="salir()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Error al conectar con el
									Transaccionador</h4>
							</div>
							<div class="modal-body">
								<p>Por favor vuelva a ingresar.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal" onclick="salir()">Salir</button>
							</div>
						</div>
					</div>
				</div>
				<!-- MODAL ERROR TRANSACCIONADOR -->

				<div style="display: none;">
					<input type="text" id=msjError
						value="${AutorizacionTelefonicaBean.msjError}" />
				</div>

			</div>
		</section>
	</div>



	<%@include file="/inc/footer_popup.jsp"%>


	<script type="text/javascript">

	function salir() {
		close();
	}
	
	var selectedTab = ${AutorizacionTelefonicaBean.selectedTab};
	
	var divCliente = document.getElementById("datosCliente");
	var divPlastico = document.getElementById("datosPlastico");
	var divComercio = document.getElementById("panelComercio");
	var divDatosComercio = document.getElementById("datosComercio");
	var divOperacion = document.getElementById("panelOperacion");
	var btnBuscarCliCompra = document.getElementById("btnBuscarCliCompra");
	var btnRecargarCliCompra = document.getElementById("btnRecargarCliCompra");
	
	
	var divComercioAnulacion = document.getElementById("panelComercioAnulacion");
	var divDatosComercioAnulacion = document.getElementById("datosComercioAnulacion");
	var divOperacionAnulacion = document.getElementById("panelOperacionAnulacion");
	
	
	if (selectedTab == 1) {
		var element = document.getElementById("tabCompras");
		element.classList.add("active");
		var elem = document.getElementById("tab_1");
		elem.classList.add("active");
	
		document.getElementById("texto1").focus();
	
		var panelCliente = ${AutorizacionTelefonicaBean.panelCliente};
		var panelComercio = ${AutorizacionTelefonicaBean.panelComercio};
		var datosComercio = ${AutorizacionTelefonicaBean.datosComercio};
		var panelOperacion = ${AutorizacionTelefonicaBean.panelOperacion};
	
		if (panelCliente) {
	
			divPlastico.style.display = "block";
			divCliente.style.display = "block";
	
			btnBuscarCliCompra.style.display = "none";
			btnRecargarCliCompra.style.display = "block";
	
			var nroTarjeta = document
					.getElementById("formCliente:inputNroTarjetaH").value;
			document.getElementById("texto1").value = nroTarjeta.substring(6, 8);
			document.getElementById("texto2").value = nroTarjeta.substring(8, 12);
			document.getElementById("texto3").value = nroTarjeta.substring(12, 16);
			document.getElementById("inputCvv").value = document
					.getElementById("formCliente:inputCvvH").value;
	
			var consumoHabilitado = ${AutorizacionTelefonicaBean.consumoHabilitadoBool};
			var plasticoVencido = ${AutorizacionTelefonicaBean.plasticoVencido};
			if (!consumoHabilitado) {
				var element = document.getElementById("txtConsumoHabilitado");
				element.classList.add("text-red");
				element.style.fontWeight = "bold";
				var elem = document.getElementById("labelConsumoHabilitado");
				elem.classList.remove("text-light-blue");
				elem.classList.add("text-red");
				elem.style.fontWeight = "bold";
			}
			if (plasticoVencido) {
				var element = document.getElementById("txtVtoPlastico");
				element.classList.add("text-red");
				element.style.fontWeight = "bold";
				var elem = document.getElementById("labelVtoPlastico");
				elem.classList.remove("text-light-blue");
				elem.classList.add("text-red");
				elem.style.fontWeight = "bold";
			}
		}
		if (panelComercio) {
			divComercio.style.display = "block";
			document.getElementById("inputCodComercio").focus();
		}
		if (datosComercio) {
			divDatosComercio.style.display = "block";
		}
		if (panelOperacion) {
			divOperacion.style.display = "block";
			document.getElementById("inputMonto").focus();
		}
	}
	
	if (selectedTab == 2) {
		var elementAnulacion = document.getElementById("tabAnulaciones");
		elementAnulacion.classList.add("active");
		var elemAnulacion = document.getElementById("tab_2");
		elemAnulacion.classList.add("active");
		
		var datosComercioAnulacion = ${AutorizacionTelefonicaBean.datosComercio};
		var panelOperacionAnulacion = ${AutorizacionTelefonicaBean.panelOperacion};
	
		document.getElementById("inputCodComercioAnulacion").focus();
		
		if (datosComercioAnulacion) {
			divDatosComercioAnulacion.style.display = "block";
		}
		if (panelOperacionAnulacion) {
			divOperacionAnulacion.style.display = "block";
			if(${AutorizacionTelefonicaBean.idTransaccionSeleccionada}!=0){
				document.getElementById("footerAnularMov").style.display = "block";
			}
		}
		
		
		
	}
	
	if (selectedTab == 3) {
		var element = document.getElementById("tabDevoluciones");
		element.classList.add("active");
		var elem = document.getElementById("tab_3");
		elem.classList.add("active");
	}
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		var target = $(e.target).attr("href");
		if (target == "#tab_1") {
			limpiarTab(1);
		}
		if (target == "#tab_2") {
			limpiarTab(2)
		}
		if (target == "#tab_3") {
			limpiarTab(3)
		}
	});
	
	function limpiarTab(tabActiva) {
		if (tabActiva == 1) {
			divCliente.style.display = "none";
			divPlastico.style.display = "none";
			divComercio.style.display = "none";
			divOperacion.style.display = "none";
			document.getElementById("texto1").value = "";
			document.getElementById("texto2").value = "";
			document.getElementById("texto3").value = "";
			document.getElementById("inputCvv").value = "";
			btnBuscarCliCompra.style.display = "block";
			btnRecargarCliCompra.style.display = "none";
			document.getElementById("texto1").focus();
		}
		if (tabActiva == 2) {
			divDatosComercioAnulacion.style.display = "none";
			divOperacionAnulacion.style.display = "none";
			document.getElementById("inputCodComercioAnulacion").value = "";
			document.getElementById("inputCodComercioAnulacion").focus();
		}
		if (tabActiva == 3) {
		}
	}
	
	function limpiarComercio() {
		if (selectedTab == 1) {
			divDatosComercio.style.display = "none";
			divOperacion.style.display = "none";
			document.getElementById("inputCodComercio").value = "";
			document.getElementById("inputCodComercio").focus();
		}
		if (selectedTab == 2) {
			divDatosComercioAnulacion.style.display = "none";
			divOperacionAnulacion.style.display = "none";
			document.getElementById("inputCodComercioAnulacion").value = "";
			document.getElementById("inputCodComercioAnulacion").focus();
		}
		
	}
	
	function buscarCliente(tabActiva) {
		var nro = "504906";
		if (tabActiva == 1) {
			nro += document.getElementById("texto1").value;
			nro += document.getElementById("texto2").value;
			nro += document.getElementById("texto3").value;
			document.getElementById("formCliente:inputNroTarjetaH").value = nro;
			document.getElementById("formCliente:inputCvvH").value = document.getElementById("inputCvv").value;
		}
	}
	
	
	
	function buscarComercio() {
		document.getElementById("formComercio:inputCodComercioH").value = document.getElementById("inputCodComercio").value;
	}

	function buscarComercioAnulacion() {
		document.getElementById("formComercioAnulacion:inputCodComercioHAnulacion").value = document.getElementById("inputCodComercioAnulacion").value;
	}
	
	
	function aceptarConsumo() {
		document.getElementById("formOperacion:inputMontoH").value = document
				.getElementById("inputMonto").value;
		document.getElementById("formOperacion:inputCuotasH").value = document
				.getElementById("inputCuotas").value;
		document.getElementById("formOperacion:inputNroCuponH").value = document
				.getElementById("inputNroCupon").value;
	}
	
	
	function busquedaComercio() {
		document.getElementById("formBuscarComercio:inputBusquedaComercioH").value = document.getElementById("inputBusquedaComercio").value;
		if ($('.nav-tabs .active').attr("id") == "tabCompras") {
			document.getElementById("formBuscarComercio:selectedTab").value = 1;
		}
		else if ($('.nav-tabs .active').attr("id") == "tabAnulaciones") {
			document.getElementById("formBuscarComercio:selectedTab").value = 2;
		}
	}
	
	function ocultarElemento(elemento) {
		var elem = document.getElementById(elemento);
		elem.style.display = "none";
	}
	
	function mostrarElemento(elemento) {
		var elem = document.getElementById(elemento);
		elem.style.display = "block";
	}
	
	function abrirModal(nombreModal) {
		$(nombreModal).modal();
	}
	
	function pasarValor(from, to) {
		document.getElementById(to).value = document.getElementById(from).value;
	}
	
	var modalBusquedaComercio = ${AutorizacionTelefonicaBean.modalBusquedaComercio};
	if (modalBusquedaComercio) {
		$('#modal-obtener-comercio').modal();
		var tablaCom = document.getElementById("tablaBuscarComercio");
		tablaCom.style.display = "block";
	}
	
	
	function seleccionarComercio(codComercio) {
		if ($('.nav-tabs .active').attr("id") == "tabCompras") {
			document.getElementById("inputCodComercio").value = codComercio;
			document.getElementById("formComercio:btnBuscarComercio").click();
		}
		else if ($('.nav-tabs .active').attr("id") == "tabAnulaciones") {
			document.getElementById("inputCodComercioAnulacion").value = codComercio;
			document.getElementById("formComercioAnulacion:btnBuscarComercioAnulacion").click();
		}
	}
	
	
	var modalSimularCuotas = ${AutorizacionTelefonicaBean.modalSimularCuotas};
	if (modalSimularCuotas) {
		$('#modal-simulador-cuotas').modal();
		var tablaSim = document.getElementById("tablaSimuladorCuotas");
		tablaSim.style.display = "block";
	}
	
	function abrirSimulador() {
		abrirModal('#modal-simulador-cuotas');
		pasarValor('inputMonto', 'inputMontoSimulador');
		ocultarElemento('tablaSimuladorCuotas');
	
		if (document.getElementById("formSimularCuotas:inputMontoSimuladorH").value == document
				.getElementById("inputMontoSimulador").value) {
			var montoAux = document.getElementById("inputMontoSimulador").value;
			if (montoAux) {
				mostrarElemento('tablaSimuladorCuotas');
			}
		}
	}
	
	function simularCuotas() {
		document.getElementById("formSimularCuotas:inputMontoSimuladorH").value = document
				.getElementById("inputMontoSimulador").value;
		document.getElementById("formSimularCuotas:inputCuotasSimuladorH").value = document
				.getElementById("inputCuotas").value;
		document.getElementById("formSimularCuotas:inputNroCuponSimuladorH").value = document
				.getElementById("inputNroCupon").value;
	}
	
	
	
	function seleccionarMovimiento(movimiento){
		document.getElementById("formOperacionAnulacion:idTransaccionSeleccionadaH").value = movimiento.idTransaccion;
		
		document.getElementById("fechaSelect").innerHTML = movimiento.fecha;
		document.getElementById("nombreSelect").innerHTML = movimiento.nombre;
		document.getElementById("dniSelect").innerHTML = movimiento.dni;
		document.getElementById("nroClienteSelect").innerHTML = movimiento.idCliente;
		document.getElementById("importeSelect").innerHTML = movimiento.importe;
		document.getElementById("cuotasSelect").innerHTML = movimiento.cuotas;
		document.getElementById("nroTarjetaSelect").innerHTML = movimiento.nroTarjeta;
		
		document.getElementById("tablaMovimientos").style.display = "none";
		document.getElementById("tablaMovSeleccionado").style.display = "block";
		document.getElementById("footerAnularMov").style.display = "block";
	}
	
	function desSeleccionarMov(){
		document.getElementById("formOperacionAnulacion:idTransaccionSeleccionadaH").value = 0;
		
		document.getElementById("tablaMovimientos").style.display = "block";
		document.getElementById("tablaMovSeleccionado").style.display = "none";
		document.getElementById("footerAnularMov").style.display = "none";
	}
	
	
	var modalTransAprobada = ${AutorizacionTelefonicaBean.modalAprobada};
	if (modalTransAprobada) {
		$('#modal-aprobada').modal();
	}
	
	var modalSesion = ${AutorizacionTelefonicaBean.sesionVencida};
	if (modalSesion) {
		$('#modal-sesion').modal();
	}
	
	var modalTransaccionador = ${AutorizacionTelefonicaBean.transaccionadorOk};
	if (!modalTransaccionador) {
		$('#modal-transaccionador').modal();
	}
	
	$('#modal-obtener-comercio').on('shown.bs.modal', function() {
		$('#inputBusquedaComercio').focus();
	});
	
	$('#modal-simulador-cuotas').on('shown.bs.modal', function() {
		$('#inputMontoSimulador').focus();
	});
	
	var error = ${AutorizacionTelefonicaBean.error};
	if (error) {
		var msjError = document.getElementById("msjError").value;
		alert(msjError);
	}
	
	

</script>



</body>

	</html>

</f:view>
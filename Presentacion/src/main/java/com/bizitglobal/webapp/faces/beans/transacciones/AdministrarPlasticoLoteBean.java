package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.Util;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoEstado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @id 5248
 * @author José Casalis. Bizit Global - Año 2012
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class AdministrarPlasticoLoteBean extends BaseBean {

	private String ADMINISTRAR_PLASTICO_LOTE = "administrarPlasticoLote";
	private static final Logger log = Logger.getLogger(AdministrarPlasticoLoteBean.class);

	private List lugaresList;
	private List plasticosLugares;
	private Long estadoSeleccionado;

	private Long codOperacionTxt;
	private Long codLoteTxt;
	private Long codCuentaTxt;
	private Long codVerificacionTxt;

	private String mensajeConfPlastico;
	private Boolean mostrarMensajeAdvertencia = false;

	private Boolean mostrarPopUpConfirmacion;

	private PlasticoLugar lugarCliente;
	private PlasticoLugar lugarRechazo;
	private PlasticoEstado estadoActivado;
	private PlasticoEstado estadoDesactivado;

	private PlasticosWrapper plasticoWrapper;

	private Popup confirmacion;

	private ClienteTransaccion cliente = null;

	private String operacion;


	public String getMensajeConfPlastico() {
		return mensajeConfPlastico;
	}


	public void setMensajeConfPlastico(String mensajeConfPlastico) {
		this.mensajeConfPlastico = mensajeConfPlastico;
	}


	public Boolean getMostrarHtmlConfLotes() {
		return mostrarHtmlConfLotes;
	}


	public void setMostrarHtmlConfLotes(Boolean mostrarHtmlConfLotes) {
		this.mostrarHtmlConfLotes = mostrarHtmlConfLotes;
	}

	private Boolean mostrarHtmlConfLotes = false;


	public AdministrarPlasticoLoteBean() {
		super();
	}


	public String cancelar() {
		// borrar();
		ejecutarJavaScript("window.close();");
		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String irASalir() {
		limpiarMensajeExito();
		return cancelar();
	}


	public String irAContinuar() {
		limpiarMensajeExito();
		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String irACancelar() {
		limpiarCamposCodPopUp();
		limpiarMensajeExito();
		return ADMINISTRAR_PLASTICO_LOTE;
	}


	@Override
	public void borrar() {
		error.borrar();
		borrarBaseBean();
		tituloCorto = "Administración de Sobres de plasticos";
		tituloLargo = "TARJETA FIEL";
		mostrarPopUpConfirmacion = false;
		limpiarCamposCodPopUp();
		limpiarMensajeExito();
	}


	@Override
	public boolean validar() {
		return false;
	}


	private void limpiarMensajeExito() {
		this.plasticoWrapper = null;
		if (this.confirmacion != null) {
			this.confirmacion.setMostrar(false);
			this.confirmacion.borrar();
		}
		popup.setMostrar(false);
		popup.borrar();
	}


	@Override
	public String inicializar() {

		borrarBaseBean();
		borrar();

		try {
			Filtro filtroLugares = new Filtro();
			// filtroLugares.agregarCampoOperValor("accion", Filtro.IGUAL, PlasticoLugar.CONFIRMA);
			filtroLugares.agregarCampo("accion");
			filtroLugares.agregarOperador(Filtro.IN);
			filtroLugares.agregarValor(PlasticoLugar.CONFIRMA + "," + PlasticoLugar.DESHABILITA + "," + PlasticoLugar.HABILITA);
			filtroLugares.agregarCampoOperValor("idPlasticoLugar", Filtro.IGUAL, 4L);
			filtroLugares.agregarOrderBy("accion");
			plasticosLugares = transaccionesService.getPlasticoLugarService().listar(filtroLugares);
			lugaresList = com.bizitglobal.webapp.faces.util.Util.cargarSelectItem(plasticosLugares);

			// Busca el lugar cliente y rechazado cliente
			Iterator<PlasticoLugar> plIt = plasticosLugares.iterator();
			while (plIt.hasNext()) {
				PlasticoLugar lugar = plIt.next();
				if (lugar.getId().equals(4L)) {// cliente
					lugarCliente = lugar;
				} else if (lugar.getId().equals(11L)) {// Rechazado Cliente
					lugarRechazo = lugar;
				}
			}

			estadoActivado = transaccionesService.getPlasticoEstadoService().leerPlasticoEstado(1L);
			estadoDesactivado = transaccionesService.getPlasticoEstadoService().leerPlasticoEstado(2L);
			confirmacion = new Popup();
			confirmacion.borrar();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("No se pueden leer los lugares de los plasticos");
			error.agregar("Error al intentar recuperar los lugares disponibles para los plásticos");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String verificarLugar(ValueChangeEvent event) {
		PlasticoLugar lugarSeleccionado = null;
		if (event.getNewValue() != null) {
			lugarSeleccionado = (PlasticoLugar) com.bizitglobal.webapp.faces.util.Util.buscarElemento(plasticosLugares,
					new PlasticoLugar(Long.parseLong(event.getNewValue().toString())));
		}

		if (lugarSeleccionado != null && !PlasticoLugar.CONFIRMA.equals(lugarSeleccionado.getAccion())) {
			mostrarMensajeAdvertencia = true;
		} else {
			mostrarMensajeAdvertencia = false;
		}

		limpiarCamposCodPopUp();

		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String cambiarLugar() {

		if (codCuentaTxt == null ||
				codLoteTxt == null ||
				codOperacionTxt == null ||
				codVerificacionTxt == null) {
			// setMensajeErrorHtml(html, "Debe ingresar todos los datos para poder activar un plastico");
			error.agregar("Debe ingresar todos los datos para poder activar un plastico");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		String valorParaVerificar;
		try {
			valorParaVerificar = Convertidores.completarIzquierda(codOperacionTxt.toString(), '0', 2)
					+ Convertidores.completarIzquierda(codLoteTxt.toString(), '0', 6)
					+ Convertidores.completarIzquierda(codCuentaTxt.toString(), '0', 6);
		} catch (Exception e) {
			// setMensajeErrorHtml(html, "Error al intentar verificar el digito");
			error.agregar("Error al intentar verificar el digito");
			return ADMINISTRAR_PLASTICO_LOTE;
		}
		String digito = Util.generarDigitoVerificadorCB(valorParaVerificar);
		if (!digito.equals(codVerificacionTxt.toString())) {
			// setMensajeErrorHtml(html, "No coincide el digito verificador con los demas digitos ingresados, por favor controle los numeros.");
			error.agregar("No coincide el digito verificador con los demas digitos ingresados, por favor controle los numeros.");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		try {
			cliente = transaccionesService.getClienteTransaccionService().leerCliente(codCuentaTxt); // siempre el cliente titular
		} catch (ClienteTransaccionException e) {
			error.agregar("Error al intentar recuperara el cliente");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		if (cliente == null) {
			// setMensajeErrorHtml(html, "No se encuentra la cuenta ingresada. Por favor, verifique los datos");
			error.agregar("No se encuentra la cuenta ingresada. Por favor, verifique los datos");
			return ADMINISTRAR_PLASTICO_LOTE;
		}
		// html.setTitulo(cliente.getIdCliente().toString());
		// html.setTitular(cliente.getNombreCliente());
		// html.setLote(this.codLoteTxt.toString());
		List<PlasticoCliente> plasticosAGuardar;
		/* @I5796 */plasticosAGuardar = transaccionesService.getPlasticoClienteService().leerPlasticoClienteActivar(codCuentaTxt, codLoteTxt,
				codOperacionTxt);
		/* @F5796 */if (plasticosAGuardar.isEmpty()) {
			// setMensajeErrorHtml(html, "No se encuentran plasticos pendientes de confirmar para esta cuenta");
			limpiarCamposCodPopUp();
			error.agregar("No se encuentran plasticos para la cuenta: " + cliente.getIdCliente()
					+ ". Verifique que los mismos hayan sido confirmados");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		PlasticoLugar lugarCambiado = (PlasticoLugar) com.bizitglobal.webapp.faces.util.Util.buscarElemento(plasticosLugares, new PlasticoLugar(
				this.estadoSeleccionado));

		confirmacion.setNombreIcono(Popup.ICONO_CONFIRMACION);

		plasticoWrapper = new PlasticosWrapper();

		Iterator<PlasticoCliente> plasIt = plasticosAGuardar.iterator();
		try {
			String leyendaPlastico = plasticosAGuardar.size() > 1 ? "los plasticos" : "el plastico";
			while (plasIt.hasNext()) {
				PlasticoCliente plasticoCambiar = plasIt.next();
				// Solamente se cambia de lugar si el anterior es distinto
				if (!lugarCambiado.equals(plasticoCambiar.getPlasticoLugar())) {
					plasticoCambiar.setPlasticoLugar(lugarCambiado);
					if (PlasticoLugar.HABILITA.equals(lugarCambiado.getAccion())) {
						plasticoWrapper.setEstado(estadoActivado);
						confirmacion.setMostrar(true);
						/* @I5796 */confirmacion.setMensaje("<b>Desea <font color='red'>confirmar</font> " + leyendaPlastico
								+ " para el cliente titular " + cliente.getNombreCliente() + "&#63;</b>");
						/* @F5796 */operacion = "ACTIVAR";
					} else if (PlasticoLugar.DESHABILITA.equals(lugarCambiado.getAccion())) {
						plasticoWrapper.setEstado(estadoDesactivado);
						confirmacion.setMostrar(true);
						/* @I5796 */confirmacion.setMensaje("<b>Desea <font color='red'>deshabilitar</font> " + leyendaPlastico
								+ " para el cliente titular " + cliente.getNombreCliente() + "&#63;</b>");
						/* @F5796 */operacion = "DESACTIVAR";
					}
				}
			}

			plasticoWrapper.setPlasticos(plasticosAGuardar);

			if (!confirmacion.getMostrar()) {
				// guardarCambios();
				limpiarCamposCodPopUp();
				popup.setPopup(Popup.ICONO_OK, "El plastico se acutalizo correctamente");
				popup.setMostrar(true);
			}

			// } catch (PlasticoClienteException e) {
			// e.printStackTrace();
			// error.agregar("Error al intentar actualizar los plásticos.");
			// return ADMINISTRAR_PLASTICO_LOTE;

		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Error al intentar guardar el historial para un plástico.");
			return ADMINISTRAR_PLASTICO_LOTE;
		}

		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String confirmarAccion() {
		if (plasticoWrapper != null && plasticoWrapper.getPlasticos() != null && !plasticoWrapper.getPlasticos().isEmpty()) {

			try {
				cliente.setDiaCierre(transaccionesService.getClienteTransaccionService().getDiaPagoCliente(cliente.getId()));
				PlasticoOperacion operacionPlastico = ((PlasticoCliente) plasticoWrapper.getPlasticos().get(0)).getOperacion();
				if ("ACTIVAR".equals(this.operacion)) {
					transaccionesService.getPlasticoClienteService().activarPlasticos(plasticoWrapper.getPlasticos(), cliente, estadoActivado,
							estadoDesactivado,
							operacionPlastico, Session.getOperador());

					// Iterator<PlasticoCliente> plasticosIt = plasticoWrapper.getPlasticos().iterator();
					// while(plasticosIt.hasNext()){
					// PlasticoCliente pObject = plasticosIt.next();
					// transaccionesService.getPlasticoClienteService().activarPlasticosApp(pObject, cliente, estadoActivado,
					// estadoDesactivado,operacionPlastico, Session.getOperador(),null,null,null);
					//
					// }

				} else {
					// Deshabilita
					PlasticoLugar lugarCambiado = (PlasticoLugar) com.bizitglobal.webapp.faces.util.Util.buscarElemento(plasticosLugares,
							new PlasticoLugar(this.estadoSeleccionado));
					transaccionesService.getPlasticoClienteService().desactivarPlasticos(plasticoWrapper.getPlasticos(), estadoDesactivado,
							lugarCambiado, Session.getOperador());
				}
				limpiarCamposCodPopUp();
			} catch (ConceptoException e1) {
				e1.printStackTrace();
				error.agregar("Error al intentar leer el concepto de cargo: " + e1.getMessage());
				return ADMINISTRAR_PLASTICO_LOTE;
			} catch (PlasticoClienteException e) {
				e.printStackTrace();
				error.agregar(e.getMessage());
				return ADMINISTRAR_PLASTICO_LOTE;
			} catch (Exception e) {
				e.printStackTrace();
				error.agregar(e.getMessage());
				return ADMINISTRAR_PLASTICO_LOTE;
			}
		}
		limpiarMensajeExito();
		popup.setPopup(Popup.ICONO_OK, "Se actualizaron correctamente los plasticos de la cuenta " + cliente.getIdCliente());
		popup.setMostrar(true);
		return ADMINISTRAR_PLASTICO_LOTE;
	}


	public String rechazarAccion() {
		log.info("seeee, funka");

		return ADMINISTRAR_PLASTICO_LOTE;
	}


	private void guardarCambios() throws PlasticoClienteException {
		transaccionesService.getPlasticoClienteService().actualizarPlasticoCliente(plasticoWrapper.getPlasticos());
	}


	private void limpiarCamposCodPopUp() {
		this.codCuentaTxt = null;
		this.codLoteTxt = null;
		this.codOperacionTxt = null;
		this.codVerificacionTxt = null;
	}


	public List getLugaresList() {
		return lugaresList;
	}


	public void setLugaresList(List lugaresList) {
		this.lugaresList = lugaresList;
	}


	public Long getEstadoSeleccionado() {
		return estadoSeleccionado;
	}


	public void setEstadoSeleccionado(Long estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}


	public Long getCodOperacionTxt() {
		return codOperacionTxt;
	}


	public void setCodOperacionTxt(Long codOperacionTxt) {
		this.codOperacionTxt = codOperacionTxt;
	}


	public Long getCodLoteTxt() {
		return codLoteTxt;
	}


	public void setCodLoteTxt(Long codLoteTxt) {
		this.codLoteTxt = codLoteTxt;
	}


	public Long getCodCuentaTxt() {
		return codCuentaTxt;
	}


	public void setCodCuentaTxt(Long codCuentaTxt) {
		this.codCuentaTxt = codCuentaTxt;
	}


	public Long getCodVerificacionTxt() {
		return codVerificacionTxt;
	}


	public void setCodVerificacionTxt(Long codVerificacionTxt) {
		this.codVerificacionTxt = codVerificacionTxt;
	}


	public Boolean getMostrarMensajeAdvertencia() {
		return mostrarMensajeAdvertencia;
	}


	public void setMostrarMensajeAdvertencia(Boolean mostrarMensajeAdvertencia) {
		this.mostrarMensajeAdvertencia = mostrarMensajeAdvertencia;
	}


	public Boolean getMostrarPopUpConfirmacion() {
		return mostrarPopUpConfirmacion;
	}


	public void setMostrarPopUpConfirmacion(Boolean mostrarPopUpConfirmacion) {
		this.mostrarPopUpConfirmacion = mostrarPopUpConfirmacion;
	}


	public Popup getConfirmacion() {
		return confirmacion;
	}


	public void setConfirmacion(Popup confirmacion) {
		this.confirmacion = confirmacion;
	}


	public String getOperacion() {
		return operacion;
	}


	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @id
	 * @author José Casalis. Bizit Global - Año 2012
	 */
	public class PlasticosWrapper {

		private List<PlasticoCliente> plasticos;
		private PlasticoEstado estado;


		public List<PlasticoCliente> getPlasticos() {
			return plasticos;
		}


		public void setPlasticos(List<PlasticoCliente> plasticos) {
			this.plasticos = plasticos;
		}


		public PlasticoEstado getEstado() {
			return estado;
		}


		public void setEstado(PlasticoEstado estado) {
			this.estado = estado;
		}


		public PlasticosWrapper() {
			super();

		}
	}
}

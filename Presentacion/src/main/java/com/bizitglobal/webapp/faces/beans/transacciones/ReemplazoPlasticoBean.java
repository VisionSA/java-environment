package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
/*@I5275*/
import java.util.Calendar;
/*@F5275*/
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
/*@I5636*/
import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaDetalleException;
/*@F5636*//*@I5275*/
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
/*@F5275*/
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.MotivoCierreCuentaClienteException;
/*@I5636*/
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
/*@F5636*/
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.MotivoCierreCuentaCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoEstado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoHistorial;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
/*@I5275*/
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
/*@F5275*/
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


/**
 * @id 5275
 * @author Facundo Bustos. Bizit Global - Año 2012
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ReemplazoPlasticoBean extends BaseBean {

	private static final Logger log = Logger.getLogger(CambioDiaCierreClienteBean.class);

	private Long nroCuenta;
	private Long idCliente;
	MotivoCierreCuentaCliente motivo;

	private PlasticoCliente plasticoTitular; // se guarda el plastico activo del titular de la cuenta
	private PlasticoCliente plastico; // se guarda el plastico al que se debe de remplazar, sea de un adicional o titular
	private ClienteTransaccion cliente; // se guarda el cliente al que se le debe hacer la reimpresion de su plastico
	private ClienteTransaccion clienteTitular; // guarda el cliente titular de la cuenta
	private Long idMotivoLugar;
	private List listaMotivosLugarList;
	private List<SelectItem> listaMotivosLugarItem = new ArrayList<SelectItem>();
	private List listaMotivosList;
	private List<SelectItem> listaMotivosItem = new ArrayList<SelectItem>();
	private String plasticoParam;
	private String popupReport;
	private String descripcionLugar;
	private HtmlSelectOneMenu motivoHtml = new HtmlSelectOneMenu();
	private int hayReimpresion; // 0->domicilio Titular no valido 1->hay un plastico p/reimpresion y domicilio Titular valido 2->no hay un
											// plasticos p/reimpresion
	private PlasticoLugar lugarBaja;
	private PlasticoEstado inactivo;
	private PlasticoLugar pendienteEmbozar;
	private PlasticoOperacion operacion;


	public String getPopupReport() {
		String res = popupReport;
		popupReport = null;
		return res;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public Long getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	/* @I5275 */public PlasticoCliente getPlastico() {
		return plastico;
	}


	public void setPlastico(PlasticoCliente plastico) {
		this.plastico = plastico;
	}


	/* @F5275 */public ClienteTransaccion getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTransaccion cliente) {
		this.cliente = cliente;
	}


	/* @I5275 */public void setHayReimpresion(int hayReimpresion) {
		this.hayReimpresion = hayReimpresion;
	}


	/* @F5275 */public Long getIdMotivoLugar() {
		return idMotivoLugar;
	}


	public void setIdMotivoLugar(Long idMotivoLugar) {
		this.idMotivoLugar = idMotivoLugar;
	}


	public List<SelectItem> getListaMotivosLugarItem() {
		return listaMotivosLugarItem;
	}


	public void setListaMotivosLugarItem(List<SelectItem> listaMotivosLugarItem) {
		this.listaMotivosLugarItem = listaMotivosLugarItem;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CIERRE CUENTA CLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrar();
		/* @I5636 */try {
			inactivo = transaccionesService.getPlasticoEstadoService().leerPlasticoEstado(2L);
			operacion = transaccionesService.getPlasticoOperacionService().obtenerPlasticoOperacion(2L);
			pendienteEmbozar = transaccionesService.getPlasticoLugarService().get(1L);
		} catch (Exception e) {
			error.agregar("Error al intentar leer los persistentes de los plasticos.");
			e.printStackTrace();
		}
		/* @F5636 */return null;

	}


	public String cancelar() {
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String getDescripcionLugar() {
		if (this.idMotivoLugar.equals(0L)) {
			return "-";
		}
		else {
			motivo = (MotivoCierreCuentaCliente) Util.buscarElemento(this.listaMotivosList, new MotivoCierreCuentaCliente(idMotivoLugar));
			return motivo.getPlasticoLugar().getDescripcion();
		}
	}


	public String irAContinuar() {
		popup.borrar();
		return "";
	}


	public String irASalir() {
		popup.borrar();
		return null;
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");
		return !error.hayErrores();
	}


	public void inicializarParametros(Map param) {
		// PARA USO DEL WORKFLOW
		inicializar();
		try {
			nroCuenta = new Long(param.get("nroCuenta").toString());
			idCliente = new Long(param.get("listaClientes").toString());
			plasticoParam = param.get("plastico").toString();

			if (plasticoParam != null && plasticoParam.trim().equals("TIENE PLASTICOS POR DESTRUIR")) {
				error.agregar("Tiene plasticos para destruir controlar");
			}
		} catch (ClassCastException e) {
			error.agregar("El parametro nroCuenta debe ser numerico");
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			error.agregar("El parametro nroCuenta debe ser numerico");
			e2.printStackTrace();
		} catch (Exception e2) {
			error.agregar("Error al intentar procesar los  parametros del workflow");
			e2.printStackTrace();
		}
		if (!error.hayErrores()) {
			buscarPlasticoCliente();
		}

	}


	public void borrar() {
		borrarBaseBean();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Reimpresion de Plasticos";

		this.nroCuenta = null;
		this.idCliente = null;
		this.cliente = null;
		this.plastico = null;

		this.idMotivoLugar = 0L;
		listaMotivosLugarList = null;
		listaMotivosLugarItem = null;

		this.popupReport = null;
	}


	private String FormatearFecha(Timestamp t) {
		// timestamp -> MM/YYYY
		String[] sp = t.toString().substring(0, 7).split("-");
		return sp[1] + "/" + sp[0];
	}


	public String buscarPlasticoCliente() {
		try {
			cliente = transaccionesService.getClienteTransaccionService().leerCliente(idCliente);
			// lazy
			/* @I5636 */cliente.getIndividuo().getTipoDocumento().getDescripcion();
			// fin lazy

			if (cliente.esTitular()) {
				if (cliente.validarDomicilio()) {
					clienteTitular = cliente;
					plasticoTitular = cliente.getPlaticoClienteUltimo();
					plastico = plasticoTitular;
					hayReimpresion = (plastico != null) ? 1 : 2;
				}

			} else {
				clienteTitular = transaccionesService.getClienteTransaccionService().leerCliente(cliente.getIdTitular());
				if (clienteTitular.validarDomicilio()) {
					/* @I5636 */plasticoTitular = clienteTitular.getPlaticoClienteUltimo();
					plastico = cliente.getPlaticoClienteUltimo();
					/* @F5636 */// plasticoTitular =cliente.getPlaticoClienteUltimo();
					/* @F5636 */hayReimpresion = (plastico != null) ? 1 : 2;
				}
			}

			if (hayReimpresion == 0)
				error.agregar("El cliente Titular no posee actualmente un domicilio valido.");
			if (hayReimpresion == 2)
				error.agregar("El cliente seleccionado no posee actualmente un plastico en condicion de reimprimirse.");
			/* @F5275 */
			try {
				Filtro filtro = new Filtro("plasticoLugar.motivoBaja", Filtro.IGUAL, "'N'");
				listaMotivosList = transaccionesService.getMotivoCierreCuentaClienteService().getMotivoCierreCuentaCliente(filtro);
				listaMotivosItem.clear();
				listaMotivosItem.add(new SelectItem(new Long(0), "Seleccione un Motivo"));
				listaMotivosItem.addAll(Util.cargarSelectItem(listaMotivosList));
			} catch (MotivoCierreCuentaClienteException e) {
				// TODO Auto-generated catch block
				log.info("Error al leer motivos de cierre cuenta cliente ");
				e.printStackTrace();
			}

			Filtro f = new Filtro();
			f.agregarCampoOperValor("motivoBaja", Filtro.IGUAL, "'N'");
			listaMotivosLugarList = transaccionesService.getPlasticoLugarService().listar(f);
			listaMotivosLugarItem = new ArrayList();
			listaMotivosLugarItem.add(new SelectItem(new Long(0), "Seleccione un Motivo"));
			listaMotivosLugarItem.addAll(Util.cargarSelectItem(listaMotivosLugarList));

		} catch (ClienteTransaccionException e) {
			log.info("Error al intentar leer el cliente con id " + idCliente);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Error al intentar leer la lista de MotivosLugar");
			e.printStackTrace();
		}
		return null;
	}


	/* @I5275 */private boolean validarFechaPlastico(PlasticoCliente plastico) throws PlasticoClienteException, ParametroSistemaDetalleException {
		if (plastico != null && plastico.getVigenciaHasta() != null) {
			/* @I5636 */Calendar fechaCorrriente = Calendar.getInstance();
			Calendar fechaHastaPlastico = Calendar.getInstance();
			fechaHastaPlastico.setTime(plastico.getVigenciaHasta());

			fechaHastaPlastico.set(Calendar.DAY_OF_MONTH, fechaHastaPlastico.getActualMaximum(Calendar.DAY_OF_MONTH));

			/* @I5636 */ParametroSistemaDetalle margen = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(
					ParametroSistemaDetalle.MARGEN_DIAS_RENOVACION);
			/* @F5636 */int valor = Integer.parseInt(margen.getValor());
			fechaHastaPlastico.add(Calendar.DAY_OF_MONTH, valor * (-1));

			return fechaHastaPlastico.after(fechaCorrriente);

		} else {
			throw new PlasticoClienteException("Plastico del titular erroneo.");
			/* @F5636 */}
	}


	/* @F5275 */

	public void cambiarLugar(ValueChangeEvent event) {
		if (motivoHtml != null && motivoHtml.getValue() != null && !motivoHtml.getValue().equals("")) {
			Long idMotivo = new Long(motivoHtml.getValue().toString());
			if (!idMotivo.equals(0L)) {
				motivo = (MotivoCierreCuentaCliente) Util.buscarElemento(this.listaMotivosList, new MotivoCierreCuentaCliente(idMotivo));
				this.descripcionLugar = motivo.getPlasticoLugar().getDescripcion();
			}
			else
				this.descripcionLugar = "-";

		}
	}


	public String ejecutarReimpresion() {
		/* @I5275 */// PRE: plastico != null && plasticoTitular != null
		if (this.idMotivoLugar != 0L) {
			try {
				/* @I5636 */// lugarBaja = transaccionesService.getPlasticoLugarService().get(this.idMotivoLugar);

				lugarBaja = motivo.getPlasticoLugar();
				if (validarFechaPlastico(plasticoTitular)) {
					PlasticoCliente plasticoNuevo = ClonarPlasticoAReimprimir(plastico, plasticoTitular);

					plasticoTitular = plastico;
					List<PlasticoCliente> plasticoDesactivar = new ArrayList<PlasticoCliente>();
					plasticoTitular.setMotivoCierre(motivo.getDescripcion());
					plasticoTitular.setIdMotivoCierre(motivo.getIdMotivoCierreCuentaCliente());
					plasticoDesactivar.add(plasticoTitular);

					// transaccionesService.getPlasticoClienteService().desactivarPlasticos(
					// buscarPlasticosADesactivar(plastico.getClienteTransaccion()), inactivo,lugarBaja,Session.getOperador());
					transaccionesService.getPlasticoClienteService().desactivarPlasticos(
							plasticoDesactivar, inactivo, lugarBaja, Session.getOperador());
					transaccionesService.getPlasticoClienteService().grabarPlasticoCliente(plasticoNuevo);
				}
				else {
					RegenerarCuenta(nroCuenta);
				}
				/* @F5275 */if (!popup.getMostrar()) {
					popup.setPopup(popup.ICONO_OK, "El proceso de reimpresion de plastico finalizo con exito.");
				}
				/* @F5636 */
			} catch (Exception e) {
				popup.setPopup(popup.ICONO_FALLA, "Error, el proceso de Reimpresion no finalizo con exito, intente nuevamente.");
				e.printStackTrace();
			}
		} else {
			error.agregar("Debe seleccionar un Motivo de Reimpresion");
		}
		return null;

	}


	/* @I5636 */private String RegenerarCuenta(Long nroCuenta) throws Exception {
		List<PlasticoCliente> plasticosNuevos = new ArrayList<PlasticoCliente>();
		List<PlasticoCliente> plasticosDesactivar = new ArrayList<PlasticoCliente>();

		Calendar calendarDesde = Calendar.getInstance();
		Calendar calendarHasta = Calendar.getInstance();

		/* @I5636 */ParametroSistemaDetalle validezPlastico = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(
				ParametroSistemaDetalle.MESES_VALIDEZ_PLASTICO);
		int mesesVigencia = Integer.valueOf(validezPlastico.getValor());
		/* @F5636 */calendarDesde.set(Calendar.DAY_OF_MONTH, 1);
		calendarHasta.add(Calendar.MONTH, mesesVigencia);
		calendarHasta.set(Calendar.DAY_OF_MONTH, calendarHasta.getActualMaximum(Calendar.DAY_OF_MONTH));

		List<ClienteTransaccion> integrantesDeCuenta = new ArrayList<ClienteTransaccion>();
		integrantesDeCuenta.add(clienteTitular);
		// ahora, buscamos los adicionales de la cuenta
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idTitular", Filtro.IGUAL, nroCuenta);
		filtro.agregarCampoOperValor("estadoCliente.idEstadoCliente", Filtro.IGUAL, 1L); // adicionales actualmente activos
		integrantesDeCuenta.addAll(transaccionesService.getClienteTransaccionService().getCliente(filtro));
		for (ClienteTransaccion cliente : integrantesDeCuenta) {
			PlasticoCliente ultimoPlastico = cliente.getPlaticoClienteUltimo();
			if (ultimoPlastico == null) {
				popup.setPopup(popup.ICONO_FALLA, "Error grave, el cliente " + cliente.getIndividuo().getNombreCompleto()
						+ " NO POSEE PLASTICOS PARA RENOVAR.");
				return null;
			}
			PlasticoCliente plasticoClienteRenovado = transaccionesService.getPlasticoClienteService().crearRenovado(ultimoPlastico,
					calendarDesde.getTime(), calendarHasta.getTime(), null, pendienteEmbozar, Session.getOperador(), operacion);
			transaccionesService.getPlasticoClienteService().crearNroCliPlastico(plasticoClienteRenovado); // Esto es provisorio hasta que este
																											// implementado todo el protocolo de
																											// posnet
			plasticosNuevos.add(plasticoClienteRenovado);

			// plasticosDesactivar.addAll(buscarPlasticosADesactivar(cliente));
			ultimoPlastico.setMotivoCierre(motivo.getDescripcion());
			ultimoPlastico.setIdMotivoCierre(motivo.getIdMotivoCierreCuentaCliente());
			plasticosDesactivar.add(ultimoPlastico);

		}
		// Damosde baja los anteriores
		transaccionesService.getPlasticoClienteService().desactivarPlasticos(plasticosDesactivar, inactivo, lugarBaja, Session.getOperador());
		// Grabamos los nuevos
		transaccionesService.getPlasticoClienteService().grabarPlasticosCliente(plasticosNuevos);

		return null;
	}


	private List<PlasticoCliente> buscarPlasticosADesactivar(ClienteTransaccion cliente) throws PlasticoClienteException {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("clienteTransaccion.idCliente", Filtro.IGUAL, cliente.getIdCliente());
		filtro.agregarCampoOperValor("plasticoLugar.accion", Filtro.IN, "0, 1, 2");
		return transaccionesService.getPlasticoClienteService().getPlasticoCliente(filtro);
	}


	/* @F5636 */

	/* @I5636 */private PlasticoCliente ClonarPlasticoAReimprimir(PlasticoCliente plasticoOriginal, PlasticoCliente plasticoTitular) throws Exception {
		/* @F5275 */PlasticoCliente plasticoClon = new PlasticoCliente();
		/* @I5275 */
		/* @F5275 */plasticoClon.setEstadoPlastico(inactivo);

		plasticoClon.setClienteTransaccion(plasticoOriginal.getClienteTransaccion());
		plasticoClon.setOperador(plasticoOriginal.getOperador());
		// plasticoClon.setNumero(p.getNumero());
		/* ************************************************
		 * Cambio temporal hasta que posnet actualice todas las terminales Se cambia la referencia del plàstico viejo a una nueva para que tenga un
		 * numero distinto y no afecte el numero de plastico original ******************
		 */
		// Le crea un numero nuevo al plàstico ya que en el reemplazo se deben cambiar los numeros
		transaccionesService.getPlasticoClienteService().crearNroCliPlastico(plasticoClon);
		/* @I4655 */plasticoClon.setPin(transaccionesService.getPlasticoClienteService().generarPin(plasticoClon.getNumero()));
		/* @F4655 */
		Timestamp fecha = new Timestamp(new Date().getTime());
		plasticoClon.setUltimamodif(fecha);
		plasticoClon.setUltimamodifFlex(fecha);

		ParametroSistema mesesValidez = generalService.getParametroSistemaService().leerParametroSistema(ParametroSistema.MESES_VALIDEZ_PLASTICO);
		Integer cantidadMesesValidez = Integer.parseInt(((ParametroSistemaDetalle) mesesValidez
				.getParametroSistemaDetalle(ParametroSistemaDetalle.MESES_VALIDEZ_PLASTICO)).getValor());

		/* @I5275 */plasticoClon.setVigenciaDesde(plasticoTitular.getVigenciaDesde());
		plasticoClon.setVigenciaDesdeFlex(plasticoTitular.getVigenciaDesdeFlex());
		/* @I850 */
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(plasticoTitular.getVigenciaDesdeFlex());
		Date validezHasta = Fecha.addMeses(plasticoTitular.getVigenciaDesdeFlex(), cantidadMesesValidez);
		calendar.setTime(validezHasta);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		plasticoClon.setVigenciaHasta(new Timestamp(calendar.getTime().getTime()));
		plasticoClon.setVigenciaHastaFlex(calendar.getTime());
		/* @F850 */
		// plasticoClon.setVigenciaHasta(plasticoTitular.getVigenciaHasta());
		// plasticoClon.setVigenciaHastaFlex(plasticoTitular.getVigenciaHastaFlex());
		/* @F5275 */
		plasticoClon.setFechaEmbozo(null);
		plasticoClon.setPlasticoLugar(pendienteEmbozar);
		plasticoClon.setFechaEstado(fecha);
		plasticoClon.setPlasticoLote(null);
		plasticoClon.setOperacion(transaccionesService.getPlasticoOperacionService().obtenerPlasticoOperacion(3L));

		plasticoClon.setPlasticoHistorialSet(new HashSet());
		plasticoClon.getPlasticoHistorialSet().add(
				new PlasticoHistorial(null, plasticoClon, inactivo, fecha, Session.getOperador(), null, pendienteEmbozar));

		return plasticoClon;
		/* @F5636 */}


	/* @I5636 */
	/* @F5636 */

	public boolean getHayReimpresion() {
		/* @I5275 */return hayReimpresion == 1;
		/* @F5275 */}


	public String getCondicion() {
		return cliente.getIdTitular() == null ? "Titular" : "Adicional";
	}


	public void setListaMotivosItem(List<SelectItem> listaMotivosItem) {
		this.listaMotivosItem = listaMotivosItem;
	}


	public List<SelectItem> getListaMotivosItem() {
		return this.listaMotivosItem;
	}


	public HtmlSelectOneMenu getMotivoHtml() {
		return motivoHtml;
	}


	public void setMotivoHtml(HtmlSelectOneMenu motivoHtml) {
		this.motivoHtml = motivoHtml;
	}


	public String getPlasticoParam() {
		return plasticoParam;
	}


	public void setPlasticoParam(String plasticoParam) {
		this.plasticoParam = plasticoParam;
	}
}

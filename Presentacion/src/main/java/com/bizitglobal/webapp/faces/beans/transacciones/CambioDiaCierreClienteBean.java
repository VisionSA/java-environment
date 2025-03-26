package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CambioDiaPagoHistoricoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.CambioDiaPagoHistorico;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class CambioDiaCierreClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CambioDiaCierreClienteBean.class);
	private int origen;
	private String nroCuenta;

	private Solicitud solicitud;
	private ClienteTransaccion clienteTransaccion;
	private IndividuoEvaluacion individuoEvaluacion;

	private String focoHidden;
	private String diaPagoActual;
	private String diaPagoCambio;
	private Date vencimientoResumenActual;
	private Date vencimientoResumenCambio;
	private Date vencimientoResumenDelCambio;
	private Long idTipoDocumentoSeleccionado = 0L;
	private List listTipoDni;
	private Date fechaNacimiento;
	private List listDiaPago;
	private Long idDiaPagoSeleccionado;

	private Date fechaCierreCliente;
	private boolean verGrabar;
	private List listHistoricoCambioDiaPago;
	private String diaCambio;
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private int mesUltimaLiquidacion;


	public CambioDiaCierreClienteBean() {
		super();
		borrar();
		listTipoDni = new ArrayList();
	}


	public List getListHistoricoCambioDiaPago() {
		return listHistoricoCambioDiaPago;
	}


	public void setListHistoricoCambioDiaPago(List listHistoricoCambioDiaPago) {
		this.listHistoricoCambioDiaPago = listHistoricoCambioDiaPago;
	}


	public int getMesUltimaLiquidacion() {
		return mesUltimaLiquidacion;
	}


	public void setMesUltimaLiquidacion(int mesUltimaLiquidacion) {
		this.mesUltimaLiquidacion = mesUltimaLiquidacion;
	}


	public Date getVencimientoResumenDelCambio() {
		return vencimientoResumenDelCambio;
	}


	public void setVencimientoResumenDelCambio(Date vencimientoResumenDelCambio) {
		this.vencimientoResumenDelCambio = vencimientoResumenDelCambio;
	}


	public boolean isVerGrabar() {
		return verGrabar;
	}


	public void setVerGrabar(boolean verGrabar) {
		this.verGrabar = verGrabar;
	}


	public Long getIdTipoDocumentoSeleccionado() {
		return idTipoDocumentoSeleccionado;
	}


	public void setIdTipoDocumentoSeleccionado(Long idTipoDocumentoSeleccionado) {
		this.idTipoDocumentoSeleccionado = idTipoDocumentoSeleccionado;
	}


	public Date getFechaCierreCliente() {
		return fechaCierreCliente;
	}


	public void setFechaCierreCliente(Date fechaCierreCliente) {
		this.fechaCierreCliente = fechaCierreCliente;
	}

	private HtmlSelectOneMenu diaPagoCambioHtml = new HtmlSelectOneMenu();


	public HtmlSelectOneMenu getDiaPagoCambioHtml() {
		return diaPagoCambioHtml;
	}


	public void setDiaPagoCambioHtml(HtmlSelectOneMenu diaPagoCambioHtml) {
		this.diaPagoCambioHtml = diaPagoCambioHtml;
	}


	public List getListDiaPago() {
		return listDiaPago;
	}


	public void setListDiaPago(List lstDiaPago) {
		this.listDiaPago = lstDiaPago;
	}


	public Long getIdDiaPagoSeleccionado() {
		if (idDiaPagoSeleccionado != null)
			return idDiaPagoSeleccionado;
		else
			return new Long(0);
	}


	public void setIdDiaPagoSeleccionado(Long idDiaPagoSeleccionado) {
		this.idDiaPagoSeleccionado = idDiaPagoSeleccionado;
	}


	public Date getVencimientoResumenActual() {
		return vencimientoResumenActual;
	}


	public void setVencimientoResumenActual(Date vencimientoResumenActual) {
		this.vencimientoResumenActual = vencimientoResumenActual;
	}


	public Date getVencimientoResumenCambio() {
		return vencimientoResumenCambio;
	}


	public void setVencimientoResumenCambio(Date vencimientoResumenCambio) {
		this.vencimientoResumenCambio = vencimientoResumenCambio;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public List getListTipoDni() {
		return listTipoDni;
	}


	public void setListTipoDni(List listTipoDni) {
		this.listTipoDni = listTipoDni;
	}


	public String getDiaPagoCambio() {
		return diaPagoCambio;
	}


	public void setDiaPagoCambio(String diaPagoCambio) {
		this.diaPagoCambio = diaPagoCambio;
	}


	public String getDiaPagoActual() {
		return diaPagoActual;
	}


	public void setDiaPagoActual(String diaPagoActual) {
		this.diaPagoActual = diaPagoActual;
	}


	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public boolean getAlta() {
		return alta;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CLICLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrar();

		cargarItems();
		return "CambioDiaCierreCliente";
	}


	public void inicializarParametros(Map param) {

		inicializar();
		try {
			this.diaPagoActual = (String) param.get("diaPagoActual");
			this.nroCuenta = (String) param.get("nroCuenta");
			this.diaCambio = (String) param.get("diaCambio");
		} catch (ClassCastException e) {
			error.agregar(Error.TRAN_NRO_CUENTA_INCORRECTO);
		}
		log.info("Iniciando el cambio DIA PAGO para la cuenta nro: " + nroCuenta);
		if (!error.hayErrores()) {
			if (validar()) {
				try {
					clienteTransaccion = transaccionesService.getClienteTransaccionService().leerCliente(Long.parseLong(nroCuenta));
					if (clienteTransaccion != null && clienteTransaccion.getIndividuo() != null) {
						individuoEvaluacion = clienteTransaccion.getIndividuo();
						this.diaPagoActual = individuoEvaluacion.getDiaPago().getDiaPago().toString();
						this.fechaNacimiento = individuoEvaluacion.getFechaNacimientoFlex();
						this.idTipoDocumentoSeleccionado = individuoEvaluacion.getTipoDocumento().getIdTipoDocumento();

						// this.mesUltimaLiquidacion = this.getMesUltimaLiquidacion(Long.parseLong(nroCuenta));
						this.vencimientoResumenActual = getFechaPreparada(Integer.parseInt(this.diaPagoActual) + 15, 0);
						this.vencimientoResumenDelCambio = getFechaPreparada(Integer.parseInt(this.diaCambio) + 15, 0);

						if (individuoEvaluacion.getDomicilio() != null && individuoEvaluacion.getDomicilio().validateDomicilio())
						{
							Long idPart = individuoEvaluacion.getDomicilio().getBarrio().getLocalidad().getPartido().getIdPartido();
							List listDiasPagoPartido = evaluacionService.getDiaPagoService().getDiaPago(
									new Filtro("partido.idPartido", Filtro.IGUAL, idPart));
							listDiaPago.addAll(getListDiasSeleccionables(listDiasPagoPartido, this.diaPagoActual));
							this.diaPagoCambio = this.diaCambio;
							this.idDiaPagoSeleccionado = setIdDiaPagoSeleccionado(listDiasPagoPartido, this.diaCambio);
							this.fechaCierreCliente = getFechaPreparada(Integer.parseInt(this.diaCambio), 0);

							this.cargarHistoricoCambios();
						}
						else
						{
							error.agregar("El cliente no tiene un Domicilio correcto.");
						}

						// if(!isDiaPagoMesActualLiquidado(Integer.parseInt(this.diaPagoActual)))
						// {
						// error.agregar(Error.TRAN_DIA_PAGO_NO_LIQUIDADO);
						// }

					}
				} catch (Exception e) {
					error.agregar("Error al cargar los datos.");
					e.printStackTrace();
				}

			}
		}
	}


	public void cargarHistoricoCambios()
	{
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idCliente", Filtro.IGUAL, this.nroCuenta);
		try {
			this.listHistoricoCambioDiaPago = evaluacionService.getCambioDiaPagoHistoricoService().getCambioDiaPagoHistorico(filtro);
		} catch (CambioDiaPagoHistoricoException e) {
			e.printStackTrace();
		}
	}


	public Long setIdDiaPagoSeleccionado(List listDias, String diaPagoSelec)
	{
		Long resul = 0L;
		Iterator iterObj = listDias.iterator();
		while (iterObj.hasNext()) {
			Negocio aux = (Negocio) iterObj.next();
			if (aux.getLabel().equalsIgnoreCase(diaPagoSelec))
			{
				resul = aux.getId();
				break;
			}
		}
		return resul;
	}


	public List getListDiasSeleccionables(List objList, String diaPagoEliminar) {
		List result = new ArrayList();
		if (objList != null && !objList.isEmpty()) {
			Iterator iterObj = objList.iterator();
			while (iterObj.hasNext()) {
				Negocio aux = (Negocio) iterObj.next();
				if (!aux.getLabel().equalsIgnoreCase(diaPagoEliminar))
					result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	/*
	 * Este metodo retorna la proxima fecha de cierre de acuerdo al dia de pago.
	 */
	public Date getFechaPreparada(int diaRequerido, int cantDeMesesAgregar) {
		Calendar fecha = Calendar.getInstance();
		// fecha.set(Calendar.MONTH, mesInicial);
		fecha.add(Calendar.MONTH, cantDeMesesAgregar);
		if (fecha.get(Calendar.MONTH) == Calendar.FEBRUARY && (diaRequerido == 30 || diaRequerido == 29))
			diaRequerido = 28;
		if (diaRequerido <= 30) {
			fecha.set(Calendar.DATE, diaRequerido);
			if (diaRequerido < Calendar.getInstance().get(Calendar.DATE))
				cantDeMesesAgregar += 1;
			fecha.add(Calendar.MONTH, cantDeMesesAgregar);
		} else {
			diaRequerido = diaRequerido % 30;
			fecha.set(Calendar.DATE, diaRequerido);
			if (diaRequerido < Calendar.getInstance().get(Calendar.DATE))
				cantDeMesesAgregar += 1;
			fecha.add(Calendar.MONTH, cantDeMesesAgregar);
		}

		return fecha.getTime();
	}


	private void cargarItems() {

		listTipoDni = IndividuoEvaluacionUtil.cargarTipoDocumeno(generalService.getTipoDocumentoDao());
		listDiaPago = new ArrayList();
		listDiaPago.add(new SelectItem(new Long(0), "Seleccionar Dia Pago"));
		this.verGrabar = false;
		this.listHistoricoCambioDiaPago = new ArrayList();
	}


	public String grabar() {
		try {
			if (validarGrabar()) {
				// Si la diferencia en dias entre la fecha actual y la fecha de cambio es menor a 2, se pasan los items al proximo mes.
				if (!validarLimiteCambio(this.fechaCierreCliente))
				{
					Calendar cal = Calendar.getInstance();
					cal.setTime(this.fechaCierreCliente);
					cal.add(Calendar.MONTH, 1);
					this.fechaCierreCliente = cal.getTime();
				}

				log.info("INICIO LLAMADA SP.....");
				log.info("fechaCierreCliente..... " + this.fechaCierreCliente);
				transaccionesService.getClienteTransaccionService().iniciarProcesoCambioDiaPago(Integer.parseInt(this.diaPagoCambio),
						this.fechaCierreCliente, Long.parseLong(this.nroCuenta), this.idDiaPagoSeleccionado);

				DiaPago diaPagoActual = individuoEvaluacion.getDiaPago();
				DiaPago diaPagoCambio = evaluacionService.getDiaPagoService().leerDiaPago(new Long(this.idDiaPagoSeleccionado));
				Operador operador = Session.getOperador();

				CambioDiaPagoHistorico cambioDiaPagoHistorico = new CambioDiaPagoHistorico(null, diaPagoActual, diaPagoCambio, Calendar.getInstance()
						.getTime(),
						null, new Long(this.nroCuenta), operador);
				evaluacionService.getCambioDiaPagoHistoricoService().grabarCambioDiaPagoHistorico(cambioDiaPagoHistorico);

				this.cargarHistoricoCambios();

				this.verGrabar = true;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				popup.setPopup(popup.ICONO_OK, "El cambio del dia de pago termino correctamente.\n " +
						"La proxima fecha de facturacion sera el " + simpleDateFormat.format(this.fechaCierreCliente));
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla en el cambio de dia pago.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Cambio dia de pago";

		solicitud = null;
		individuoEvaluacion = null;
		this.listDiaPago = null;
		this.clienteTransaccion = null;
		this.diaPagoActual = null;
		this.diaPagoCambio = null;
		this.vencimientoResumenActual = null;
		this.vencimientoResumenCambio = null;
		this.vencimientoResumenDelCambio = null;
		this.nroCuenta = null;
		this.listHistoricoCambioDiaPago = null;

	}


	public String cancelar() {
		// borrar();
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String irAContinuar() {
		popup.borrar();
		return "";
	}


	public String irASalir() {
		return cancelar();
	}


	public String rellenarCerosFaltantes(String num) {
		String aux = "";
		for (int i = 0; i < (8 - num.length()); i++) {
			aux = aux + "0";
		}
		aux = aux + num;
		return aux;
	}


	public boolean validarGrabar() {
		error.borrar();
		// if(!validarLimiteCambio(this.fechaCierreCliente))
		// {
		// error.agregar("Debe tener 2 dias de anticipacion para hacer el cambio.");
		// }

		boolean IsDiaPagoMesActualLiq = isDiaPagoMesActualLiquidado(Integer.parseInt(this.diaPagoActual));
		if (!validarResumenesPagos(Long.parseLong(this.nroCuenta)))
		{
			error.agregar("El cliente debe tener todos sus resumenes vencidos pagos para hacer el cambio.");
		}

		if (!isDiaPagoMesActualLiquidado(Integer.parseInt(this.diaPagoActual)))
		{
			error.agregar(Error.TRAN_DIA_PAGO_NO_LIQUIDADO);
		}

		if (idDiaPagoSeleccionado.equals(new Long(0)))
		{
			error.agregar("Debe seleccionar un dia de cierre para realizar el cambio.");
		}

		Long idDiaPagoUltimoCambioRealizado = null;
		try {
			idDiaPagoUltimoCambioRealizado = evaluacionService.getCambioDiaPagoHistoricoService().buscarIdDiaPagoUltimaCambio(
					Long.parseLong(this.nroCuenta));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CambioDiaPagoHistoricoException e) {
			e.printStackTrace();
		}

		if (this.idDiaPagoSeleccionado.equals(idDiaPagoUltimoCambioRealizado))
		{
			error.agregar("Ya se realizo el cambio de dia cierre para este tramite.");
		}

		return !error.hayErrores();
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");

		if (nroCuenta == null || nroCuenta.equals(new String("")))
			error.agregar(Error.TRAN_NRO_CUENTA_REQUERIDA);

		return !error.hayErrores();
	}


	public boolean validarLimiteCambio(Date fechaCierre)
	{
		Date fecha = Calendar.getInstance().getTime();
		int dias = Fecha.calcularDiferenciaDias(fecha, fechaCierre);
		return (dias > 2);
	}


	public boolean validarResumenesPagos(Long idCliente)
	{
		Double montoTotal = 0D;
		try
		{
			// Trae solamente las liquidaciones impagas.
			List liquidaciones = transaccionesService.getLiqClienteService().getLiqClienteFlex(idCliente);
			Iterator iter = liquidaciones.iterator();
			while (iter.hasNext())
			{
				LiqCliente liq = (LiqCliente) iter.next();
				// No se tienen en cuenta las liquidaciones no vencidas.
				if (liq.getFechaLiq().after(Calendar.getInstance().getTime()))
					continue;

				montoTotal += liq.getMontoTotal().doubleValue() - liq.getImportePagado().doubleValue();
			}
		} catch (Exception e) {
			popup.setPopup(popup.ICONO_FALLA, "Falla al obtener las liquidaciones del cliente.");
			e.printStackTrace();
		}
		return (montoTotal <= 0);
	}


	public int getMesUltimaLiquidacion(Long idCliente)
	{
		LiqCliente utimaLiquidacion = null;
		try {
			utimaLiquidacion = transaccionesService.getLiqClienteService().getUltimaLiquidacion(idCliente);
		} catch (LiqClienteException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(utimaLiquidacion.getFechaCierre());
		return calendar.get(Calendar.MONTH);
	}


	public boolean isLiquidacionDelMesActualRealizada(Long idCliente)
	{
		int mes = 0;
		int anio = 0;
		LiqCliente liq = null;
		try {
			liq = transaccionesService.getLiqClienteService().buscarUltimaLiquidacion(idCliente);
			if (liq != null && liq.getFechaCierre() != null)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(liq.getFechaCierre());
				mes = cal.get(Calendar.MONTH);
				anio = cal.get(Calendar.YEAR);
			}

		} catch (LiqClienteException e) {
			popup.setPopup(popup.ICONO_FALLA, "Falla al obtener la ultima liquidacion del cliente.");
			e.printStackTrace();
		}

		return (liq != null && anio == Calendar.getInstance().get(Calendar.YEAR) && Calendar.getInstance().get(Calendar.MONTH) == mes);
	}


	public boolean isDiaPagoMesActualLiquidado(int diaPago)
	{
		int mes = 0;
		int anio = 0;
		Calendar cal = Calendar.getInstance();
		Boolean result = false;
		try {
			if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY && (diaPago == 30 || diaPago == 29))
				diaPago = 28;

			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), diaPago);
			result = transaccionesService.getClienteTransaccionService().validarLiquidacionRealizada(cal.getTime(), new Long(this.nroCuenta));

		} catch (ClienteTransaccionException e) {
			popup.setPopup(popup.ICONO_FALLA, "Falla al validar liquidacion fecha: " + cal.getTime() + " actual realizada.");
			e.printStackTrace();
		}

		return (result.booleanValue() || diaPago >= 2);
	}


	/*
	 * Metodo que actualiza la fecha de vencimiento segun el dia de pago selecccionado
	 */
	public void cambiarVencimientoResumenCambio(ValueChangeEvent event) throws ParseException {
		error.borrar();
		if (diaPagoCambioHtml != null && diaPagoCambioHtml.getValue() != null && !diaPagoCambioHtml.getValue().equals("")) {

			Iterator iterObj = listDiaPago.iterator();
			while (iterObj.hasNext()) {
				SelectItem aux = (SelectItem) iterObj.next();

				if (Long.parseLong(aux.getValue().toString()) == Long.parseLong(diaPagoCambioHtml.getValue().toString()))
				{
					this.diaPagoCambio = aux.getLabel();
					this.fechaCierreCliente = getFechaPreparada(Integer.parseInt(this.diaPagoCambio), 1);
					break;
				}
			}

			focoHidden = "lstDiaPago";
			// this.vencimientoResumenCambio = getFechaPreparada(Integer.parseInt(this.diaPagoCambio)+15,0);

		}
	}

}

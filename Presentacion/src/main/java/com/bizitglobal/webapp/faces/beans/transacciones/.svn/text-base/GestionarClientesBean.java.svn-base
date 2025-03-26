package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.exception.NoLaborableException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.EstadoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.GestorLiquidacionClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiquidacionClientesException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.GestorLiquidacionCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteFuturoVencimiento;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteRepactacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiquidacionClientes;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ItemLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;


@SuppressWarnings({"rawtypes","unchecked"})
public class GestionarClientesBean extends BaseBean {
	private static final Logger log = Logger.getLogger(GestionarClientesBean.class);
	private String focoHidden;
	private ClienteTransaccion cliente;
	private int contadorRenglones;
	private List listaItemsPendientes;

	// aqui mostramos las liquidaciones particulares realizadas, que no estan confirmadas.
	private List liquidacionesParticularesRealizadas;
	// aqui mostramos las liquidaciones particulares realizadas con anterioridad, que ya han sido confirmadas.
	private List liquidacionesParticularesConfirmadas;

	// El siguiente String, almacena los id de transacciones separados por coma, que seran liquidados por adelantado.
	private String idDeTransaccionesALiquidar;
	Map mapaTransacciones = null; // aqui almaceno como key el id de transaccion, y como ArrayList las cuotas de estas transacciones.

	LiquidacionClientes liquidacionEditada = null; // este objeto es para las liquidaciones particulares.
	GestorLiquidacionCliente gestor = null; // el gestor encargado de la liquidacion por adelantado.
	// dos variables que me ayudan a sacar el saldo en linea de cada cliente
	public BigDecimal saldoTotal = new BigDecimal(0);
	public BigDecimal lineaDeCredito = new BigDecimal(0);
	public BigDecimal disponible = new BigDecimal(0);
	BigDecimal montoAdeudado = new BigDecimal(0);

	private String tipoNotaCredito;
	private String tipoNotaDebito;
	private Double valorDebito;
	private Double valorCredito;

	private Date fechaPrimerVencimiento;
	private Date fechaSegundoVencimiento;
	private Date fechaTercerVencimiento;

	private Date fechaCierreAnterior;
	private Date fechaCierreActual;
	private Date fechaCierreProximo;
	private Date fechaVencimientoAnterior;
	private Date fechaVencimientoProximo;

	private String directorioRelativoLiquidacion;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	String popupReport = "";


	public GestionarClientesBean() {
		error.borrar();
	}


	public String inicializar() {
		try {
			borrar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gestionarClientes";
	}


	private boolean validarCliente(ClienteTransaccion cliente) {
		boolean validacion = false;
		try {
			double saldo = transaccionesService.getCtaCteClienteService().getSaldoAbsolutoCtaCte(cliente.getIdCliente()).doubleValue();
			if (saldo <= 0) {
				error.agregar("El cliente no posee saldo deudor, ver liquidacion Saldo Cero");

			} else {
				validacion = true;
			}

		} catch (CtaCteClienteException e1) {
			error.agregar("Error al intentar obtener el saldo del cliente seleccionado, intentar nuevamente.");
			e1.printStackTrace();
		}
		return validacion;
	}


	/**
	 * Lista todos los movimientos que no han sido imputados, y no han sido liquidado tampoco.
	 * */
	public void listarMovimientos(ClienteTransaccion cliente) {
		borrar();
		this.cliente = cliente;
		if (validarCliente(cliente)) {
			try {
				List listaItemsLiquidaciones = transaccionesService.getItemLiquidacionService().getItemsDeLiquidacion(
						Integer.valueOf(cliente.getIdCliente().intValue()));
				if (listaItemsPendientes == null)
					listaItemsPendientes = new ArrayList();
				listaItemsPendientes.clear();
				Iterator iter = listaItemsLiquidaciones.iterator();
				while (iter.hasNext()) {
					listaItemsPendientes.add(new WrapperItemLiquidacion((ItemLiquidacion) iter.next()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("clienteTransaccion.idCliente", Filtro.IGUAL, cliente.getIdCliente());
			filtro.agregarCampoOperValor("liquidacionClientes.confirmada", Filtro.LIKE, "N");
			filtro.agregarCampoOperValor("liquidacionClientes.finalizo", Filtro.LIKE, "S");
			filtro.agregarCampoOperValor("liquidacionClientes.tipoLiquidacion", Filtro.IGUAL, new Long(2));
			List listaLiq = new ArrayList();
			List listaAuxiliar = new ArrayList();
			liquidacionesParticularesRealizadas = new ArrayList();
			liquidacionesParticularesConfirmadas = new ArrayList();
			try {
				listaLiq = transaccionesService.getLiqClienteService().getLiqCliente(filtro, 5);
				Iterator iter = listaLiq.iterator();
				while (iter.hasNext()) {
					LiqCliente li = (LiqCliente) iter.next();
					li.getLiquidacionClientes().getFechaPreliq();
					liquidacionesParticularesRealizadas.add(new LiquidacionWrapper(li));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			filtro = new Filtro();
			filtro.agregarCampoOperValor("clienteTransaccion.idCliente", Filtro.IGUAL, cliente.getIdCliente());
			filtro.agregarCampoOperValor("liquidacionClientes.confirmada", Filtro.LIKE, "S");
			try {
				listaAuxiliar = transaccionesService.getLiqClienteService().getLiqCliente(filtro, 5);
				Iterator iter = listaAuxiliar.iterator();
				while (iter.hasNext()) {
					LiqCliente li = (LiqCliente) iter.next();
					li.getLiquidacionClientes().getFechaPreliq();
					liquidacionesParticularesConfirmadas.add(new LiquidacionWrapper(li));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Session.redirect("/tarjetafiel/transacciones/gestionarClientes.jsf");
	}


	public String volverAlListado() {
		Session.redirect("/tarjetafiel/transacciones/listarCliente.jsf");
		return null;
	}


	public String getValueBotonBloquearDesbloquearCuenta() {
		if (cliente.getEstadoCliente().getIdEstadoCliente().equals(1L))
			return "Bloquear Cuenta";
		if (cliente.getEstadoCliente().getIdEstadoCliente().equals(3L))
			return "Desbloquear Cuenta";
		else
			return "Bloquear/Desbloquear Cuenta";
	}


	public ClienteTransaccion bloquearDesbloquearCuenta() throws EstadoClienteException, ClienteTransaccionException {

		if (cliente.getEstadoCliente().getIdEstadoCliente().equals(1L)) {
			try {
				EstadoCliente estadoBloqueada = transaccionesService.getEstadoClienteService().leerEstadoCliente(3L);
				Timestamp fecha = new Timestamp(new Date().getTime());
				cliente.setEstadoCliente(estadoBloqueada);
				cliente.setHabilitadoConsumo("D");
				cliente.setFechaEstadoCliente(fecha);
				cliente.setFechaHabilitadoConsumo(fecha);
				cliente.setIdOperadorHabilitadoconsumo(Session.getOperador().getId());
				transaccionesService.getClienteTransaccionService().actualizarCliente(cliente);
				popup.setMensaje("La cuenta ha sido bloqueada con exito");
				popup.setNombreIcono(popup.ICONO_OK);
				popup.setMostrar(true);
			} catch (Exception e) {
				popup.setMensaje("Se produjo una falla al intentar bloquear la cuenta");
				popup.setNombreIcono(popup.ICONO_FALLA);
				popup.setMostrar(true);
			}

		} else if (cliente.getEstadoCliente().getIdEstadoCliente().equals(3L)) {
			try {
				EstadoCliente estadoNormal = transaccionesService.getEstadoClienteService().leerEstadoCliente(1L);
				Timestamp fecha = new Timestamp(new Date().getTime());
				cliente.setEstadoCliente(estadoNormal);
				cliente.setHabilitadoConsumo("H");
				cliente.setFechaEstadoCliente(fecha);
				cliente.setFechaHabilitadoConsumo(fecha);
				cliente.setIdOperadorHabilitadoconsumo(Session.getOperador().getId());
				transaccionesService.getClienteTransaccionService().actualizarCliente(cliente);
				popup.setMensaje("La cuenta ha sido desbloqueada con exito");
				popup.setNombreIcono(popup.ICONO_OK);
				popup.setMostrar(true);
			} catch (Exception e) {
				popup.setMensaje("Se produjo una falla al intentar desbloquear la cuenta");
				popup.setNombreIcono(popup.ICONO_FALLA);
				popup.setMostrar(true);
			}
		} else {
			error.agregar("La cuenta se encuentra en estado " + cliente.getEstadoCliente().getDescripcion());
			error.agregar("La cuenta debe estar en estado NORMAL para ser bloqueada o en estado BLOQUEADA para ser desbloqueada");
		}
		return null;
	}


	public String irASalir() {
		this.borrar();
		return null;
	}



	public void borrar() {
		error.borrar();
		popup.borrar();
		contadorRenglones = 0;
		tipoNotaCredito = "S";
		tipoNotaDebito = "S";
		tituloCorto = "Gestión de Clientes";
		tituloLargo = "TARJETA FIEL";
		listaItemsPendientes = null;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}

	public class WrapperItemLiquidacion {
		private int indice;
		private ItemLiquidacion itemLiquidacion;
		boolean seleccionado;


		public WrapperItemLiquidacion(ItemLiquidacion item) {
			this.indice = contadorRenglones++;
			this.itemLiquidacion = item;
			seleccionado = false;
		}


		public boolean isSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}


		public int getIndice() {
			return indice;
		}


		public ItemLiquidacion getItemLiquidacion() {
			return itemLiquidacion;
		}

	}


	public String liquidarItems() {
		// limpio la lista de id de transacciones a liquidar
		idDeTransaccionesALiquidar = "";
		mapaTransacciones = new HashMap();
		List lisElim = new ArrayList();

		// recorro la lista de Items pendientes de liquidar, y si ha sido seleccionada agrego el id a una lista de
		// id de transacciones pendientes de liquidar.
		Iterator iter = listaItemsPendientes.iterator();
		while (iter.hasNext()) {
			WrapperItemLiquidacion wrap = (WrapperItemLiquidacion) iter.next();
			if (wrap.isSeleccionado()) {
				if (mapaTransacciones.containsKey(Long.valueOf(wrap.getItemLiquidacion().getComprobante()))) {
					List mapa = (ArrayList) mapaTransacciones.get(Long.valueOf(wrap.getItemLiquidacion().getComprobante()));
					mapa.add(Long.valueOf(wrap.getItemLiquidacion().getCuota()));
				} else {
					List mapa = new ArrayList();
					mapa.add(Long.valueOf(wrap.getItemLiquidacion().getCuota()));
					mapaTransacciones.put(Long.valueOf(wrap.getItemLiquidacion().getComprobante()), mapa);
				}
				// getComprobante() esta devolviendo el id de la transaccion
				idDeTransaccionesALiquidar += ", " + wrap.getItemLiquidacion().getComprobante();
				lisElim.add(wrap);

			}
		}
		listaItemsPendientes.removeAll(lisElim);
		idDeTransaccionesALiquidar += ", ";
		Integer diaPago = cliente.getIndividuo().getDiaPago().getDiaPago();
		// acomodo las fechas de facturacio de los items que quedaron pendientes de facturar
		Date d = Fecha.getFechaPago(diaPago, 1);
		transaccionesService.getCtaCteClienteService().corregirFechaFacturacionLiquidacionParticular(cliente.getIdCliente(),
				new Long(diaPago.longValue()), d);
		liquidarPorAdelantado();
		return "";
	}


	/**
	 * Liquida todas las transacciones especificadas por el cliente de manera adelantada.
	 * */
	public void liquidarPorAdelantado() {
		Concepto conceptoPagos = null;
		Concepto conceptoRepactaciones = null;
		Concepto conceptoParamGen = null;
		Concepto conceptoCargoMora = null;
		Concepto conceptoInteresPunitorio = null;
		Concepto conceptoInteresCompensatorio = null;
		Concepto conceptoCargoResumen = null;
		Concepto conceptoSeguroVida = null;
		Concepto conceptoSellos = null;
		Set detallesSellos = null; // los concepto detalles del sello
		ListaPrecioDetalle pagoMinimo = null;
		ListaPrecioItem[] repactaciones = null;
		ListaPrecioDetalle[] pagos = null;
		Operador oper;
		oper = Session.getOperador();
		String rutaRelativa;
		// recupero el dia que de cierre, en el cual estoy queriendo liquidar

		Calendar calendar = Calendar.getInstance();
		int diaCierre = calendar.get(Calendar.DATE);

		gestor = new GestorLiquidacionCliente();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		// recupero los dias no laborables que creo apropiados y se los paso a el gestor de liquidacion.
		Filtro filtro = new Filtro("fecha", Filtro.MAYOR, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
		cal.add(Calendar.MONTH, 6);
		filtro.agregarCampoOperValor("fecha", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
		List listaDiasNoLaborables = null;
		try {
			listaDiasNoLaborables = generalService.getNoLaborableService().getNoLaborable(filtro);
			gestor.setDiasFeriados(listaDiasNoLaborables);
		} catch (NoLaborableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Calculo de fecha a utilizar en la liquidacion
		 */
		Date fechaActual = Calendar.getInstance().getTime();
		fechaPrimerVencimiento = addDiasFechaLaborable(fechaActual, 0);
		fechaSegundoVencimiento = fechaPrimerVencimiento;
		fechaTercerVencimiento = fechaPrimerVencimiento;

		fechaCierreActual = fechaActual;
		fechaCierreAnterior = fechaActual;
		fechaCierreProximo = fechaActual;

		fechaVencimientoAnterior = addDiasFechaLaborable(fechaActual, 15);
		fechaVencimientoProximo = fechaVencimientoAnterior;

		// recupero la fecha de cierre.
		IndividuoEvaluacion indi;
		Integer diaDePago = null;
		try {
			indi = (IndividuoEvaluacion) evaluacionService.getIndividuoEvaluacionService().leerIndividuo(cliente.getIndividuo().getIdIndividuo());
			diaDePago = indi.getDiaPago().getDiaPago();
		} catch (IndividuoEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cie = Calendar.getInstance();
		int dia = cie.get(Calendar.DATE);
		if (diaDePago.intValue() <= dia) {
			cie.set(Calendar.DATE, diaDePago.intValue());
		} else {
			cie.set(Calendar.DATE, diaDePago.intValue());
			cie.add(Calendar.MONTH, -1);
		}

		// Creo un objeto liquidacion Clientes.
		liquidacionEditada = new LiquidacionClientes(null, Calendar.getInstance().getTime(), "N", "N", new HashSet(), listaDiasNoLaborables,
				new Timestamp(Calendar.getInstance().getTime().getTime()), 2, cie.getTime());

		diaCierre = cie.get(Calendar.DATE);
		Long idCliente = cliente.getIdCliente();
		try {
			// una variable para el monto suma de todas las liquidaciones clientes.
			BigDecimal montoTotalLiquidacionClientes = new BigDecimal(0);
			// // Se graba directamente, ya que al ser una preliquidacion no existia el objeto aun.
			Calendar cale = Calendar.getInstance();
			Timestamp times = new Timestamp(cale.getTimeInMillis());
			liquidacionEditada.setFechaPreliq(times);
			transaccionesService.getLiquidacionClientesService().grabarLiquidacionClientes(liquidacionEditada);
			// recupero los parametros de la liquidacion como ser intereses punitorios etc, que tienen el id 2
			BigDecimal intPuni = new BigDecimal(0);
			BigDecimal intComp = new BigDecimal(0);
			BigDecimal segDeud = new BigDecimal(0);
			BigDecimal tasaA = new BigDecimal(0);
			BigDecimal tasaRefin = new BigDecimal(0);

			try {
				// recupero el concepto de INTERES PUNITORIO (codigo 222), para obtener la lista de precios que utiliza
				conceptoInteresPunitorio = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(222))).get(0);
				conceptoInteresPunitorio.armarConcepto();
				conceptoInteresPunitorio.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto Interes Punitorio (Codigo 222). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				// recupero el concepto de INTERES COMPENSATORIO (codigo 221), para obtener la lista de precios que utiliza
				conceptoInteresCompensatorio = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(220))).get(0);
				conceptoInteresCompensatorio.armarConcepto();
				conceptoInteresCompensatorio.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto INTERES COMPENSATORIO (codigo 220). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de CARGO RESUMEN (codigo 204), para obtener la lista de precios que utiliza
				conceptoCargoResumen = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(204))).get(0);
				conceptoCargoResumen.armarConcepto();
				conceptoCargoResumen.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto CARGO RESUMEN (codigo 221). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de CARGO SEGURO DE VIDA (codigo 208), para obtener la lista de precios que utiliza
				conceptoSeguroVida = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(268))).get(0);
				conceptoSeguroVida.armarConcepto();
				conceptoSeguroVida.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto SEGURO DE VIDA (codigo 268). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de SELLOS (codigo 209), para obtener la lista de precios que utiliza
				conceptoSellos = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(209))).get(0);
				conceptoSellos.armarConcepto();
				conceptoSellos.armarReglaConcepto();
				Iterator iter = conceptoSellos.getConceptoDetalleSet().iterator();
				while (iter.hasNext()) {
					ConceptoDetalle conDet = (ConceptoDetalle) iter.next();
					conDet.getConceptoDetalleReglaSet();
				}
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto SELLOS (codigo 209). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de LIQUIDACION PAGOS (id nro. 10), para obtener la lista de precios que utiliza
				conceptoPagos = transaccionesService.getConceptoService().leerConcepto(new Long(10));
				conceptoPagos.armarConcepto();
				conceptoPagos.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto 10 (Pagos). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de LIQUIDACION REPACTACIONES (id nro. 11), para obtener la lista de precios que utiliza
				conceptoRepactaciones = transaccionesService.getConceptoService().leerConcepto(new Long(11));
				conceptoRepactaciones.armarConcepto();
				conceptoRepactaciones.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto 11 (Repactaciones). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de LIQUIDACION PARAMETROS GENERALES (id nro. 12), para obtener la lista de precios que utiliza
				conceptoParamGen = transaccionesService.getConceptoService().leerConcepto(new Long(12));
				conceptoParamGen.armarConcepto();
				conceptoParamGen.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto 12 (Parametros generales de la liquidacion). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de LIQUIDACION CARGO MORA (codigo. 220), para obtener la lista de precios que utiliza

				conceptoCargoMora = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(220))).get(0);
				conceptoCargoMora.armarConcepto();
				conceptoCargoMora.armarReglaConcepto();
			} catch (Exception e) {
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto de Codigo 220 (Cargo por Mora). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			//

			String nombreLiq = "";
			// Creo el directorio de la presente liquidacion particular, este directorio se obtien de contexto properties.
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			String key;
			key = "catalina.home";
			key = System.getProperty(key);
			log.info(key + "/webapps/contexto.properties");
			PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
			try {
				String key1;
				key1 = "catalina.home";
				key1 = System.getProperty(key);

				nombreLiq = System.getProperty("catalina.home") + "/" + prop.getProperties("directorioArchivos") +
						prop.getProperties("directorioLiquidacionesClientes") + "/Particulares";

				File directorio = new File(nombreLiq);
				if (!directorio.exists()) {
					try {
						if (!directorio.mkdir()) {

							throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new GestorLiquidacionClienteException("No se ha podido crear el directorio para almacenar las liquidaciones.");
			}

			// Tenemos un solo cliente para liquidar por adelantado, entonces no hace falta iterar el proceso de liquidacion.
			idCliente = cliente.getIdCliente();
			log.info("Liquidare cliente:" + idCliente);

			// corregimos el importe de imputacion de toda la cuenta corriente del cliente
			transaccionesService.getCtaCteClienteService().corregirImportesImputacion(idCliente);
			
			try {
				// Pagos: aqui tengo que enviarle el id del cliente....
				Set detallesPagos = conceptoPagos.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				pagos = new ListaPrecioDetalle[detallesPagos.size()];
				int j = 0;
				Iterator listaPagos = detallesPagos.iterator();
				while (listaPagos.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
					pagos[j] = var;
					j++;
				}
				// ordeno los pagos de menor a mayor en nro de dias.
				for (int i = 0; i < pagos.length - 1; i++) {
					for (int h = i + 1; h < pagos.length; h++) {
						if (pagos[i].getMonto().intValue() > pagos[h].getMonto().intValue()) {
							ListaPrecioDetalle lispredet = pagos[i];
							pagos[i] = pagos[h];
							pagos[h] = lispredet;
						}
					}
				}
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Se produjo un error al intentar formar el set de pagos del cliente. Posibles causas: el Concepto 10 (Pagos) se encuentra fuera de vigencia o no tiene lista precio asociada");
			}

			try {
				// DetallesParamGen: aqui tengo que enviarle el id del cliente....
				Set detallesParamGen = conceptoParamGen.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				pagoMinimo = new ListaPrecioDetalle(); // hay un solo monto mínimo.
				Iterator listaParamGen = detallesParamGen.iterator();
				while (listaParamGen.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaParamGen.next();
					pagoMinimo = var;
					break;
				}
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Se produjo un error al intentar formar el set de parametros generales de la liquidacion. Posibles causas: el Concepto 12 (Parametros Generales) se encuentra fuera de vigencia o no tiene lista precio asociada");

			}

			// calculo el total del cliente...
			BigDecimal[] totalesDeCliente = transaccionesService.getGestorLiquidacionClienteService().totalConsumoParticularCliente(
					idCliente.intValue(), mapaTransacciones);
			BigDecimal totalCliente = totalesDeCliente[0].setScale(2, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal totalInteresesCliente = totalesDeCliente[1];
			BigDecimal totalRepactacionesCliente = totalesDeCliente[2];
			totalCliente = totalCliente.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			totalInteresesCliente = totalInteresesCliente.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			totalRepactacionesCliente = totalRepactacionesCliente.setScale(2, BigDecimal.ROUND_HALF_DOWN);

			// Se calcula la mora anterior antes de impactar la liquidacion, si no hay que utilizar una consulta muy compleja.
			// le pasamos la proxima fecha de liquidacion
			montoAdeudado = transaccionesService.getGestorLiquidacionClienteService().calcularMoraAnterior(idCliente.intValue(),
					Fecha.getFechaPago(diaDePago, 1));
			
			ClienteLiquidacion cliente = transaccionesService.getClienteLiquidacionService().leerCliente(idCliente);
			cliente.getIndividuo();
			cliente.getNombreCliente();
			lineaDeCredito = cliente.getLimiteCredito();
			saldoTotal = cliente.getSaldoLinea();
			disponible = lineaDeCredito.add(saldoTotal.negate());

			double importe = 0;
			try {
				// Cargo Resumen: aqui tengo que enviarle el id del cliente....
				Set detallesPagos = conceptoCargoResumen.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				Iterator listaPagos = detallesPagos.iterator();
				while (listaPagos.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
					importe = var.getMonto().doubleValue();
					break;
				}

			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Se produjo un error al intentar registrar el cargo resumen. Posibles causas: el Concepto de codigo 204 (Cargo Resumen) se encuentra fuera de vigencia o no tiene lista precio asociada");
			}
			double porcentajeSeguroVida = 0;
			try {
				// Cargo por Seguro de vida: aqui tengo que enviarle el id del cliente....
				Set detallesPagos = conceptoSeguroVida.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				Iterator listaPagos = detallesPagos.iterator();
				while (listaPagos.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
					porcentajeSeguroVida = var.getPorcentaje().doubleValue();
					break;
				}

			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Se produjo un error al intentar registrar el Seguro de Vida. Posibles causas: el Concepto de codigo 208 (Seguro de Vida) se encuentra fuera de vigencia o no tiene lista precio asociada");
			}
			// recupero el porcentaje a cobrar por mora
			Double interesPorMora = new Double(0);
			Set detallesInteresCompensatorio = conceptoInteresCompensatorio.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
			Iterator listaInteres = detallesInteresCompensatorio.iterator();

			while (listaInteres.hasNext()) {
				ListaPrecioDetalle var = (ListaPrecioDetalle) listaInteres.next();
				interesPorMora = var.getPorcentaje();
				break;
			}

			// construyo el objeto LiqCliente y se lo agrego al set de nuestro objeto
			LiqCliente liqCliente = new LiqCliente(intPuni, intComp, segDeud, tasaA, tasaRefin, new BigDecimal(0), liquidacionEditada);
			liqCliente.setMontoTotal(totalCliente);
			liqCliente.setTotalIntereses(totalInteresesCliente);
			liqCliente.setTotalRepactado(totalRepactacionesCliente);
			liqCliente.setImportePagado(new BigDecimal(0));
			// recupero el intCompensatorio y el punitorio...
			liqCliente.setIntComp(new BigDecimal(conceptoInteresCompensatorio.getListaPrecio(idCliente).getVersionVigente().getTnaFiel()
					.doubleValue()));
			liqCliente.setIntPunitorios(new BigDecimal(conceptoInteresPunitorio.getListaPrecio(idCliente).getVersionVigente().getTnaFiel()
					.doubleValue()));
			liqCliente.setLineaDeCredito(lineaDeCredito.doubleValue());
			liqCliente.setSaldoTotal(saldoTotal.doubleValue());
			liqCliente.setDisponible(disponible.doubleValue());
			liqCliente.setFechaCierreAnterior(fechaCierreAnterior);
			liqCliente.setFechaLiq(fechaPrimerVencimiento);
			liqCliente.setFechaProxCierre(fechaCierreProximo);
			liqCliente.setFechaVtoAnterior(fechaVencimientoAnterior);
			liqCliente.setFechaProximoVto(fechaVencimientoProximo);
			liqCliente.setFechaCierre(fechaCierreActual);
			liqCliente.setFechaVto3(fechaTercerVencimiento);

			liqCliente.setClienteTransaccion(new ClienteTransaccion(idCliente));
			liquidacionEditada.getLiqClientes().add(liqCliente);

			// VER BIEN ESTO!!... NO VA A FUNCIONAR PARA MUCHOS CLIENTES...
			transaccionesService.getLiqClienteService().grabarLiqCliente(liqCliente);
			transaccionesService.getLiquidacionClientesService().actualizarLiquidacionClientes(liquidacionEditada);


			// generamos el cargo de resumen.
			generarCargoResumen(cliente, conceptoCargoResumen, oper, liqCliente);

			// Aqui debemos descontar los intereses y el iva, de cuotas que esten a futuro.
			transaccionesService.getGestorLiquidacionClienteService().descontarInteresesEIvaFuturo(idCliente.intValue(), liqCliente,
					mapaTransacciones);

			// generamos el Seguro de vida.
			// generarSeguroVida(cliente, conceptoSeguroVida, oper, liqCliente);

			// Una vez que ya impacte la ctacte Auxiliar, recupero el monto total que suma en esta tabla la liquidacion, para agregarlo al total del
			// cliente y al monto adeudado.

			// //// Calcule el total agregado hasta ahora como artimaña para considerar bien los sellos.

			// por ultimo calculo el impuesto de sellos sobre el total del cliente acumulado hasta este entonces.


			//AQUI CALCULABA SELLOS ANTES
			
			BigDecimal importeAux = transaccionesService.getGestorLiquidacionClienteService().getImportesAgregadosPorLiquidacion(
					liqCliente.getIdLiqCliente());
			// Aqui modificar el saldo en linea de el cliente

			totalCliente = totalCliente.add(importeAux);
			
			
			//CALCULO DE SELLOS
			try {
				BigDecimal montoSellos = transaccionesService.getCtaCteClienteAuxService().impactarSellos(conceptoSellos, idCliente,
						liqCliente, totalCliente);
				totalCliente = totalCliente.add(montoSellos);

				BigDecimal importeAuxTotal = importeAux;
				importeAuxTotal = importeAuxTotal.add(saldoTotal);

				if (generalService.getParametroSistemaService().leerParametroSistema(17L).getParametroSistemaDetalle(25L).getValor().toUpperCase()
						.equals("SI"))
				{
					if (importeAuxTotal.doubleValue() > 0) {
						generarSeguroVida(cliente, conceptoSeguroVida, oper, liqCliente, importeAuxTotal);
					}
				}

				
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException("Se produjo un error al intentar impactar los sellos.");
			}
			
			
			// Actualizo la ctaCte cliente seteandole el id de la LiqCliente. Recordar que si el proceso completo no se puede terminar, estos id seran
			// borrados. o sea son provisorios.
			transaccionesService.getGestorLiquidacionClienteService().asignarLiqParticularACtaCteCliente(idCliente.intValue(), liqCliente,
					mapaTransacciones);

			// NO Hace falta asignar la liquidacion en la tabla auxiliar, ya que se le ha pasado por parametro el id de LIqCliente.
			// transaccionesService.getGestorLiquidacionClienteService().asignarLiquidacionACtaCteClienteAux(idCliente.intValue(),
			// liquidacionClientesEditada.getFechaCierrePeriodo(), liqCliente);

			// aqui debo actualizar sies que pago de mas , se lo descuento y ya le pongo como paga la liquidacion

			liqCliente.setResumenAterior(montoAdeudado.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() +
					totalCliente.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

			liqCliente.setMontoTotal(totalCliente);

			liqCliente.setSaldoTotal(saldoTotal.add(importeAux).doubleValue());
			liqCliente.setDisponible(disponible.add(importeAux.negate()).doubleValue());

			Double pagosAdelantados = 0D;

			if (liqCliente.getResumenAterior().doubleValue() < 0) {
				pagosAdelantados = liqCliente.getMontoTotal().doubleValue();
			} else {
				pagosAdelantados = liqCliente.getMontoTotal().doubleValue() - liqCliente.getResumenAterior().doubleValue();

				if (pagosAdelantados < 0) {
					pagosAdelantados = 0D;
				}
			}

			// aqui debo impactar la ctacte cliente si estan cancelados o no.
			if (pagosAdelantados.doubleValue() > 0) {
				transaccionesService.getCtaCteClienteService().imputarLasCtaCteClientes(liqCliente.getIdLiqCliente(), pagosAdelantados.doubleValue());
				// BigDecimal importePagadoLiquidacion =
				// transaccionesService.getGestorLiquidacionClienteService().calcularImportePagadoLiquidacion(liqCliente.getIdLiqCliente());
				liqCliente.setImportePagado(new BigDecimal(pagosAdelantados).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			}
			// creo el set de repactaciones y el set de pagos.
			// if (!liqCliente.crearSetDePagos(pagos,gestor,interesPorMora.doubleValue()/100)) throw new
			// Exception("No se pudo organizar los pagos del cliente de id: " + idCliente);
			if (!liqCliente.crearSetDePagos(pagos, gestor, 0))
				throw new Exception("No se pudo organizar los pagos del cliente de id: " + idCliente);

			if (!liqCliente.crearSetDeRepactacion(conceptoRepactaciones, (CalculoCuotaServicesImpl)
					transaccionesService.lookupService("calculoCuotaServicesTarget"), diaCierre, Fecha.getFechaPago(diaCierre, -1)))
				throw new Exception("No se pudo calcular la repactacion del cliente de id: " + idCliente);
			// construyo el array de fechas y pagos para pasar al constructor de liqVencimientos futuros...
			Date[] fechas = new Date[6];
			BigDecimal[] montos = new BigDecimal[7];
			// recupero los 6 meses siguientes.... mejorar la consulta.
			BigDecimal totalMes;
			for (int l = 0; l < 6; l++) {
				Calendar calenMes = Calendar.getInstance();
				calenMes.setTime(liquidacionEditada.getFechaCierrePeriodo());
				calenMes.add(Calendar.MONTH, l + 1);
				Date fechaMesQueViene = calenMes.getTime();
				totalMes = transaccionesService.getGestorLiquidacionClienteService().calcularTotalConsumoPorClienteFut(idCliente.intValue(),
						fechaMesQueViene);
				fechas[l] = fechaMesQueViene;
				montos[l] = totalMes;
			}

			// generamos el pdf para esta liqCliente....
			liqCliente.setRutaPdf(generarPDF(liqCliente, idCliente, nombreLiq));

			// BigDecimal pagosAdelantados = transaccionesService.getGestorLiquidacionClienteService().actualizarTablaPagosClientes(
			// liqCliente.getIdLiqCliente(), liqCliente.getMontoTotal(), idCliente,fechaCierreActual);
			
			transaccionesService.getLiqClienteService().actualizarLiqCliente(liqCliente);
			// por ultimo le acutualizo el saldo en linea al cliente.
			transaccionesService.getGestorLiquidacionClienteService().actualizarSaldoEnLinea(idCliente, importeAux);

			liquidacionesParticularesRealizadas.add(new LiquidacionWrapper(liqCliente));
			// actualizamos la lista de preliquidaciones particulares hechas para este cliente.
			liquidacionEditada.setFinalizo("S");
			transaccionesService.getLiquidacionClientesService().actualizarLiquidacionClientes(liquidacionEditada);

		} catch (LiquidacionClientesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GestorLiquidacionClienteException e) {
			error.agregar(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Crea el directorio de las liquidaciones en la posición especificada por los atributos del contexto.properties, de la siguiente forma:
	 * directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
	 * */
	private String crearDirectoriosLiquidacion(Date fechaCierre) throws Exception {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
				+ "contexto.properties");

		String nombreLiq = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
				+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre);

		directorioRelativoLiquidacion = prop.getProperties("directorioArchivos").replaceAll("/webapps", "")
				+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + "Particulares";

		File directorio = new File(nombreLiq);
		try {
			if (!directorio.exists()) {
				directorio.mkdirs();

			}
			// nombreLiq += File.separator+formatoFecha.format(liquidacionClientesEditada.getFechaLiquidacion());
			File directorioInterno = new File(nombreLiq);
			if (!directorioInterno.exists()) {
				directorioInterno.mkdir();
			}
			return nombreLiq;
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
			throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}

	}


	/**
	 * Metodo que impacta el cargo resumen.
	 * */
	public boolean generarCargoResumen(ClienteLiquidacion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente) {
		try {
			transaccionesService.getCargoResumenService().registrarCargoResumen(cliente, concepto, operador, liqCliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Metodo que impacta el cargo por Seguro de Vida.
	 * */
	public boolean generarSeguroVida(ClienteLiquidacion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente,
			BigDecimal importeAuxTotal) {
		try {
			transaccionesService.getSeguroDeVidaService().registrarSeguroDeVida(cliente, concepto, operador, liqCliente, importeAuxTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Calcula el dia del proximo vencimiento teniendo en cuenta si el mes tiene 30 o 31 dias y si el dia es feriado.
	 * 
	 * @param fecha
	 * @param dias
	 * @return
	 */
	// public Date getProximaFecha(Date fecha, int dias){
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(fecha);
	// calendar.add(Calendar.DATE, dias);
	// Calendar calendarAux = Calendar.getInstance();
	// calendarAux.setTime(fecha);
	//
	// if(calendarAux.get(Calendar.MONTH) != calendar.get(Calendar.MONTH)){
	// int ultimoDiaMes = calendar.getMaximum(Calendar.DAY_OF_MONTH);
	//
	// if(ultimoDiaMes == 31){
	// calendar.add(Calendar.DATE, 1);
	// } else if(ultimoDiaMes == 28){
	// calendar.add(Calendar.DATE, 3);
	// } else if(ultimoDiaMes ==27){
	// calendar.add(Calendar.DATE, 4);
	// }
	//
	// }
	//
	// for(Object object : gestor.getDiasFeriados()){
	// NoLaborable noLaborable = (NoLaborable)object;
	// if(noLaborable.getFecha().equals(calendar.getTime())){
	// calendar.add(Calendar.DATE, 1);
	// }
	// }
	//
	// if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
	// calendar.add(Calendar.DATE, 1);
	// }
	//
	// return calendar.getTime();
	// }

	public Calendar siguienteLaborable(Calendar fechaOriginal) {
		if (generalService.getNoLaborableDao().esNoLaborable(
				new Timestamp(fechaOriginal.getTime().getTime()))) {
			fechaOriginal.add(Calendar.DATE, 1);
		}
		return fechaOriginal;
	}


	/**
	 * Solo se debe utilizar para la liquidacion de clientes Calcula el dia del proximo vencimiento teniendo en cuenta si el mes tiene 30 o 31 dias y
	 * si el dia es feriado.
	 * 
	 * @param fecha
	 * @param dias
	 * @return Un dia de vencimiento habil Esta logica tiene una falla cuando la fechaCierre es un 28 y el anio es viciesto, y en algunos casos no
	 *         utilizados para liquidar
	 */
	public Date addDiasFechaLaborable(Date fechaCierre, int dias) {
		Calendar fecha = Calendar.getInstance();
		Calendar fechaBase = Calendar.getInstance();
		fechaBase.setTime(fechaCierre);
		fecha.setTime(fechaCierre);
		fecha.add(Calendar.DATE, dias);
		int ultimoDiaMes = fechaBase.getActualMaximum(Calendar.DAY_OF_MONTH);
		// Controlo si cambio el mes y lo ajusto segun su ultimo dia
		if (fechaBase.get(Calendar.MONTH) != fecha.get(Calendar.MONTH)
				&& fechaBase.get(Calendar.DAY_OF_MONTH) != ultimoDiaMes) {
			switch (ultimoDiaMes) {
			case 31:
				fecha.add(Calendar.DATE, 1);
				break;
			case 28:
				fecha.add(Calendar.DATE, -2);
				break;
			case 29:
				fecha.add(Calendar.DATE, -1);
				break;
			default:
				break;
			}
		}
		fecha = siguienteLaborable(fecha);
		while (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			fecha.add(Calendar.DATE, 1);
			fecha = siguienteLaborable(fecha);
		}

		return fecha.getTime();
	}



	/**
	 * Metodo que impacta el cargo de mora.
	 * */
	public boolean generarInteresMora(ClienteTransaccion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente, double importe) {
		try {
			transaccionesService.getInteresMoraService().registrarInteresMora(cliente, concepto, operador, importe, liqCliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@SuppressWarnings("unchecked")
	public String generarPDF(LiqCliente liquidacionCliente, Long idCliente, String rutaRelativa) throws GestorLiquidacionClienteException {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		ClienteTransaccion clienteT;
		String nombreLiq = "";
		String nombreLiqReturn = "";
		try {
			clienteT = transaccionesService.getClienteTransaccionService().leerCliente(liquidacionCliente.getClienteTransaccion().getIdCliente());

			String codPost = null;

			// Composicion de la informacion del cliente
			String nombre = "NOMBRE INCORRECTO";
			String domicilio = "DOMICILIO INCORRECTO";
			String localidad = "LOCALIDAD INCORRECTA";
			String cp = "CP INCORRECTO";
			String provincia = "PROVINCIA INCORRECTA";
			try {
				IndividuoEvaluacion individuo = clienteT.getIndividuo();
				try {
					nombre = (individuo.getApellido().trim() + ", " + individuo.getNombres().trim()).toUpperCase();
				} catch (Exception e) {
					e.printStackTrace();
				}

				Domicilio dom = individuo.getDomicilioDoc();
				if (dom != null && dom.validateDomicilio())
				{
					domicilio = dom.getCalleNombre().trim() + " " + dom.getCalleNumero().trim() + " "
							+ ((dom.getOrientacion() != null) ? dom.getOrientacion() : "-");

					if (dom.getManzana() != null)
						domicilio = domicilio + " Mz. " + dom.getManzana();
					if (dom.getMonoblock() != null)
						domicilio = domicilio + " Mb. " + dom.getMonoblock();
					if (dom.getPiso() != null)
						domicilio = domicilio + " Piso " + dom.getPiso();
					if (dom.getDepto() != null && !dom.getDepto().trim().equals(""))
						domicilio = domicilio + " Dpto. " + dom.getDepto().trim();
					if (dom.getAreaSector() != null && !dom.getAreaSector().trim().equals(""))
						domicilio = domicilio + " Area. " + dom.getAreaSector().trim();

					domicilio = domicilio + " " + dom.getBarrio().getDescripcion().trim();
					localidad = dom.getLocalidad().getPartido().getDescripcion().trim() + " " + dom.getLocalidad().getNombre().trim();
					cp = dom.getCodigoPostal();
					provincia = dom.getLocalidad().getProvincia().getNombre();
					codPost = dom.getCpa2().toString();
				}
				else
				{
					domicilio = clienteT.getDomicilio();
					localidad = clienteT.getLocalidad();
					cp = clienteT.getCodPost();
					provincia = clienteT.getProvincia();
				}

			} catch (Exception e) {
				log.info("Error Domicilio - \n\n");
				e.printStackTrace();
				log.info("\n");
			}

			// el nombre de la liquidacion es la ruta del pdf
			if (codPost == null) {
				codPost = clienteT.getCodPost().trim();
			}
			nombreLiq = rutaRelativa + "/" + codPost + formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_"
					+ clienteT.getIdCliente();
			nombreLiqReturn = "/" + nombreLiq.substring(nombreLiq.indexOf("archivos"));

			nombreLiqReturn = "/" + nombreLiq.substring(nombreLiq.indexOf("archivos"));

			String p0 = "?guardarEn=" + nombreLiq;
			String p1 = "ƒliquidacion_numero=" + liquidacionCliente.getIdLiqCliente();
			String p2 = "ƒcuenta_nro=" + liquidacionCliente.getClienteTransaccion().getIdCliente();
			// String p3 = "ƒnombre="+clienteT.getNombre();
			// String p4 = "ƒdireccion="+clienteT.getDomicilio();
			String p3 = "ƒnombre=" + nombre;
			String p4 = "ƒdireccion=" + domicilio;
			String p5 = "ƒdescripcion=" + localidad;
			String p6 = "ƒcp=" + cp;
			String p7 = "ƒciudad=" + provincia;

			String adicional1 = "";
			String adicional2 = "";
			String adicional3 = "";
			int i = 0;
			List adicionalesDelTitular;
			String adicionales = "";
			try {
				adicionalesDelTitular = transaccionesService.getClienteTransaccionService().getCliente(
						new Filtro("idTitular", Filtro.IGUAL, liquidacionCliente.getClienteTransaccion().getIdCliente()));
				Iterator iterAdi = adicionalesDelTitular.iterator();
				while (iterAdi.hasNext()) {
					ClienteTransaccion ad = (ClienteTransaccion) iterAdi.next();
					i++;
					switch (i % 3) {
					case 1:
						try {
							adicional1 = "ƒadi1=" + ad.getNombreCliente();
						} catch (Exception e) {
							adicional1 = "ƒadi1= ";
						}
						break;
					case 2:
						try {
							adicional2 = "ƒadi2=" + ad.getNombreCliente();
						} catch (Exception e) {
							adicional2 = "ƒadi2= ";
						}
						break;
					case 3:
						try {
							adicional3 = "ƒadi3=" + ad.getNombreCliente();
						} catch (Exception e) {
							adicional3 = "ƒadi3= ";
						}
						break;
					}
				}
				adicionales = adicional1 + adicional2 + adicional3;
			} catch (ClienteTransaccionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new GestorLiquidacionClienteException("Generacion del PDF: Error en la busqueda del los adicionales de la cuenta.");
			}

			String parametros = "";

			parametros += "ƒdesc1=" + "Int.Compensatorios" + "ƒtna1=" + liquidacionCliente.getIntComp();
			parametros += "ƒdesc2=" + "Int.Punitorio" + "ƒtna2=" + liquidacionCliente.getIntPunitorios();

			// String cierresyVencimientos = "ƒcierre1= ";

			// cierresyVencimientos += "ƒcierre2= " ;

			// cierresyVencimientos += "ƒcierre3= ";

			// cierresyVencimientos += "ƒvencim1= ";

			// cierresyVencimientos += "ƒvencim2= ";

			// cierresyVencimientos += "ƒvencim3= ";

			String lineaCredito = "";
			BigDecimal impoteAxu = transaccionesService.getGestorLiquidacionClienteService()
					.getImportesAgregadosPorLiquidacion(liquidacionCliente.getIdLiqCliente()).setScale(BigDecimal.ROUND_HALF_DOWN, 2);

			lineaCredito += "ƒcredito=" + lineaDeCredito.setScale(BigDecimal.ROUND_HALF_DOWN, 2).add(impoteAxu.negate()) + "ƒsaldo="
					+ saldoTotal.setScale(BigDecimal.ROUND_HALF_DOWN, 2).add(impoteAxu) + "ƒdisponible="
					+ disponible.setScale(BigDecimal.ROUND_HALF_DOWN, 2);

			// cal.setTime(liquidacionCliente.getFechaCierreAnterior());
			String futurosVencimientos = "";
			try {
				for (int h = 1; h < 7; h++) {
					// lo siguiente puede arrojar un null pointer exception.... CONTROLAR!!!!
					Iterator iter = liquidacionCliente.getLiqClienteFuturosVencimientos().iterator();
					while (iter.hasNext()) {
						LiqClienteFuturoVencimiento futVen = (LiqClienteFuturoVencimiento) iter.next();
						if (futVen.getOrden().intValue() == h) {
							// cal.setTime(futVen.getFecha());
							switch (h) {
							case 1:
								// futurosVencimientos += "ƒf_ven_1="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_1="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							case 2:
								// futurosVencimientos += "ƒf_ven_2="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_2="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							case 3:
								// futurosVencimientos += "ƒf_ven_3="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_3="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							case 4:
								// futurosVencimientos += "ƒf_ven_4="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_4="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							case 5:
								// futurosVencimientos += "ƒf_ven_5="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_5="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							case 6:
								// futurosVencimientos += "ƒf_ven_6="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
								// cal.get(Calendar.DATE) + "ƒm_ven_6="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
								break;
							}
							break;
						}
					}
				}
			} catch (NullPointerException e) {
				// nada, es que la liquidacion tenia monto cero, por lo tanto no hay futuros vencimientos.
			} catch (Exception e) {
				e.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar los futuros vencimientos.");
			}

			String repactaciones = "";
			try {
				Object[] liqRepactacion = liquidacionCliente.getLiqClienteRepactacion().toArray();

				Arrays.sort(liqRepactacion, new Comparator() {

					public int compare(Object o1, Object o2) {
						if (((LiqClienteRepactacion) o1).getNroCuota() < ((LiqClienteRepactacion) o2).getNroCuota())
							return -1;
						else if (((LiqClienteRepactacion) o1).getNroCuota() > ((LiqClienteRepactacion) o2).getNroCuota())
							return 1;
						else
							return 0;
					}
				});

				repactaciones = "ƒ1cuota=" + ((LiqClienteRepactacion) liqRepactacion[0]).getMontoMinimo();
				repactaciones += "ƒ2cuota=" + ((LiqClienteRepactacion) liqRepactacion[0]).getMontoCuota();
				repactaciones += "ƒ3cuota=" + ((LiqClienteRepactacion) liqRepactacion[1]).getMontoCuota();
				// repactaciones = "ƒ1cuota=" + pagMi;
				// repactaciones += "ƒ2cuota=" + pagInTres;
				// repactaciones += "ƒ3cuota=" + pagInSeis;
			} catch (Exception e) {
				e.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar las repactaciones del Cliente.");
			}

			String pagos = "";
			// instancio informacion para luego armar el codigo de barras.
			BigDecimal importePagoFacil = new BigDecimal(0);
			int anio = 0;
			int dias = 0;
			int diasEntreVencimiento = 0;

			try {

				pagos += "ƒf_1_ven=" + sdf.format(fechaPrimerVencimiento);

				lineaCredito += "ƒtotal_resumen=" + liquidacionCliente.getMontoTotal();

				Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				lineaCredito += "ƒfechaEspecial=" + dateFormat.format(liquidacionCliente.getFechaCierre());

				lineaCredito += "fechaResumenAnterior=" + dateFormat.format(fechaCierreAnterior);

				importePagoFacil = liquidacionCliente.getMontoTotal().add(new BigDecimal(liquidacionCliente.getMontoAdeudado()))
						.setScale(BigDecimal.ROUND_HALF_DOWN, 2);

				pagos += "ƒf_2_ven=" + sdf.format(fechaSegundoVencimiento);

				pagos += "ƒf_3_ven=" + sdf.format(fechaTercerVencimiento);

				/*
				 * SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy"); fecha1 = format.format(liqPago[0].getFecha()); fecha2 =
				 * format.format(liqPago[1].getFecha()); fecha3 = format.format(liqPago[2].getFecha());
				 */

			} catch (Exception e) {
				e.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar los pagos del Cliente.");
			}

			// recupero el concepto de MENSAJES (id nro. 12), para obtener la lista de precios que utiliza
			Concepto conceptoParamGen;
			String mensaj = "";
			try {
				conceptoParamGen = transaccionesService.getConceptoService().leerConcepto(new Long(18));
				conceptoParamGen.armarConcepto();
				conceptoParamGen.armarReglaConcepto();
				Set detall = conceptoParamGen.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				int cons = 1;
				ListaPrecioDetalle mensaje = new ListaPrecioDetalle();
				Iterator listaParamGen = detall.iterator();
				while (listaParamGen.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaParamGen.next();
					switch (cons) {
					case 1:
						mensaj += "ƒmensaje1= ";
						break;
					case 2:
						mensaj += "ƒmensaje2= ";
						break;
					case 3:
						mensaj += "ƒmensaje3= ";
						break;
					}
					cons++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar los mensajes del Cliente. Verifique la existencia del concepto 18 (Mensajes) y que este posea asociado su lista de precios. Se deben encontrar en vigencia.");
			}
			String codigoBarra = "0000";
			String codigoBarraDos = null;
			String codigoBarraTres = null;
			importePagoFacil = importePagoFacil.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			String importePF = importePagoFacil.toString();
			log.info("importePF " + importePF);
			importePF = importePF.substring(0, importePF.indexOf(".")) + importePF.substring(importePF.indexOf(".") + 1, importePF.indexOf(".") + 3);
			log.info("importePF " + importePF);
			if (importePF.contains("-"))
				importePF = "0";

			importePF = ("0000000" + importePF).substring(("0000000" + importePF).length() - 7);

			codigoBarra = "*11"
					+ ("00000000" + liquidacionCliente.getIdLiqCliente()).substring(("00000000" + liquidacionCliente.getIdLiqCliente()).length() - 8)
					+ "0*";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
			// String fecha = fecha3.substring(0,2)+ fecha3.substring(3,5)+ fecha3.substring(6,8);
			String fecha = simpleDateFormat.format(fechaTercerVencimiento);
			codigoBarraDos = "0181" + ("000000" + idCliente).substring(("000000" + idCliente).length() - 6) + fecha + importePF;
			codigoBarraDos = "*" + getConDigito(codigoBarraDos) + "*";

			codigoBarraTres = fecha + importePF + fecha + importePF;
			codigoBarraTres = "*" + getConDigito(codigoBarraTres) + "*";

			String codBarr = "ƒcodBarr= ";
			String codBarrDos = "ƒcodBarrDos= ";
			String codBarrTres = "ƒcodBarrTres= ";
			liquidacionCliente.setCodBarraUno(codBarr);
			liquidacionCliente.setCodBarraDos(codBarrDos);
			liquidacionCliente.setCodBarraTres(codBarrTres);

			String page = request.getContextPath() + "/report/LiquidacionParticularClientes.jrxml";

			GeneradorDeInforme gen = new GeneradorDeInforme();
			try {
				// log.info(page+p0+p1+p2+p3+p4+p5+p6+p7+ parametros+lineaCredito+codBarr+codBarrDos+codBarrTres+adicionales+
				// cierresyVencimientos+futurosVencimientos+repactaciones+pagos+ mensaj);
				gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + parametros + lineaCredito + codBarr + codBarrDos + codBarrTres
						+ adicionales + futurosVencimientos + repactaciones + pagos + mensaj);
			} catch (JRException e) {
				throw new GestorLiquidacionClienteException("Generacion del documento PDF: A ocurrido un error al intentar grabar el archivo.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				throw new GestorLiquidacionClienteException("Generacion del documento PDF: A ocurrido un error al intentar grabar el archivo.");
			}
		} catch (ClienteTransaccionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return nombreLiqReturn;
	}


	public int calcularDigitoVerif(int j) {
		if (j % 4 == 0) {
			return 9;
		} else {
			return ((j % 4) * 2) + 1;
		}
	}


	public String getConDigito(String cadena) {
		int suma = 0;
		for (int i = 0; i < cadena.length(); i++) {
			int pos = Integer.valueOf(cadena.substring(i, i + 1)).intValue();
			suma += pos;
		}
		int dig = suma % 11;
		if (dig >= 10) {
			return cadena + 0;
		} else {
			return cadena + dig;
		}
	}

	public class LiquidacionWrapper {
		private LiqCliente liqCliente;


		public LiqCliente getLiqCliente() {
			return liqCliente;
		}


		public void setLiqCliente(LiqCliente liqCliente) {
			this.liqCliente = liqCliente;
		}


		public LiquidacionWrapper(LiqCliente liq) {
			this.liqCliente = liq;
		}


		/**
		 * Crea el directorio de las liquidaciones en la posición especificada por los atributos del contexto.properties, de la siguiente forma:
		 * directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
		 * */
		private String crearDirectoriosLiquidacion(Date fechaCierre) throws Exception {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
					+ "contexto.properties");

			String nombreLiq = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
					+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre);

			directorioRelativoLiquidacion = prop.getProperties("directorioArchivos").replaceAll("/webapps", "")
					+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre);

			File directorio = new File(nombreLiq);
			try {
				if (!directorio.exists()) {
					directorio.mkdirs();

				}
				// nombreLiq += File.separator+formatoFecha.format(liquidacionClientesEditada.getFechaLiquidacion());
				File directorioInterno = new File(nombreLiq);
				if (!directorioInterno.exists()) {
					directorioInterno.mkdir();
				}
				return nombreLiq;
			} catch (Exception e) {
				log.error(e, e);
				throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
			}

		}


		// los metodos necesarios
		public String eliminarPreliquidacion() {
			PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
					+ "contexto.properties");
			// logica de borrado de una preliquidacion.
			if (liqCliente.getLiquidacionClientes().getConfirmada().compareTo("N") == 0) {
				// borro por completo el objeto si no estaba confirmado.
				try {
					// borro el directorio
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
					String key;
					key = "catalina.home";
					key = System.getProperty(key);
					log.info(key + "/webapps/contexto.properties");
					// PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
					String nombreLiq = "";
					// nombreLiq = "E:/apache-tomcat-6.0.20/webapps/" + liqCliente.getRutaPdf();

					nombreLiq = System.getProperty("catalina.home") + "/" + prop.getProperties("directorioArchivos")
							+ this.getLiqCliente().getRutaPdf().replace("archivos", "") + ".pdf";

					File directorio = new File(nombreLiq);
					directorio.delete();

					// asi andaba antes, pero borra todo el directorio con pdf que estan correctos.
					// log.info("El path es : "+ directorio.getAbsolutePath());
					// log.info("El archivo a borrar es:" + directorio.exists());
					// directorio.delete();

					// if (directorio.exists() && directorio.isDirectory()) {
					// File[] archivos = directorio.listFiles();
					// for (int i=0; i<archivos.length; i++) {
					// archivos[i].delete();
					// }
					// }
					// directorio.delete();
					// borro el objeto
					transaccionesService.getGestorLiquidacionClienteService().borrarLiquidacion(
							this.getLiqCliente().getLiquidacionClientes().getIdLiquidacionClientes());
					liquidacionesParticularesRealizadas.clear();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// si estaba confirmado, pero no finalizo correctamente, puede haber complicaciones mayores, por el pasaje de datos de la tabla
				// auxiliar a la tabla t_vis_tra_ctacte_clientes

			}

			return "";
		}


		public String abrirArchivo() {
			if (liqCliente.getRutaPdf() != null && liqCliente.getRutaPdf() != "") {
				try {
					ejecutarJavaScript("popup('/../" + liqCliente.getRutaPdf() + ".pdf',1000,700), 'titlebar=no';");
				} catch (Exception e) {
					log.info("Error al intentar leer el archivo");
					e.printStackTrace();
				}
			}
			return "";
		}


		/**
		 * @deprecated No se confirman mas liquidaciones desde la pantalla de gestion. Teoricamente, esta tarea se delega en la caja.
		 * */
		public String confirmarLiquidacion() {
			if (liqCliente.getLiquidacionClientes().getConfirmada().compareTo("N") == 0
					&& liqCliente.getLiquidacionClientes().getFinalizo().compareTo("S") == 0) {
				// paso la tabla auxiliar de t_vis_tra_ctacte_cliente_aux a t_vis_tra_cta_cte_cliente
				String cadena = "" + liqCliente.getIdLiqCliente();
				// Iterator iter = liqCliente.getLiquidacionClientes().getLiqClientes().iterator();

				// while (iter.hasNext()) {
				// LiqCliente liq = (LiqCliente)iter.next();
				// cadena += liq.getIdLiqCliente() + ", ";
				// }
				// // si no habia clientes sera un indexOutOfBoundsExceptinon controlar!!
				// try {
				// cadena = cadena.substring(0, cadena.length()-2);
				// } catch (StringIndexOutOfBoundsException e) {
				// cadena = null;
				// }
				try {
					// transfiere registros de ctacteaux a ctacte y finaliza la liquidacion...
					transaccionesService.getGestorLiquidacionClienteService().confirmarLiquidacion(
							liqCliente.getLiquidacionClientes().getIdLiquidacionClientes(), cadena);
					liquidacionesParticularesConfirmadas.add(liquidacionesParticularesRealizadas.get(0));
					liquidacionesParticularesRealizadas.clear();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// si estaba confirmado, pero no finalizo correctamente, puede haber complicaciones mayores, por el pasaje de datos de la tabla
				// auxiliar a la tabla t_vis_tra_ctacte_clientes

			}
			return "";
		}

	}


	/**
	 * @deprecated
	 * */
	public String generarNotaDebitoSinIva() {
		try {
			// recupero el concepto de NOTA DEBITO SIN IVA(codigo 260), para impactar sus detalles
			Concepto conceptoNotaDebitoSinIva = (Concepto) transaccionesService.getConceptoService()
					.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(206))).get(0);
			Operador oper = Session.getOperador();
			transaccionesService.getNotaDebitoSinIvaService().registrarNotaDebito(cliente, conceptoNotaDebitoSinIva, oper, valorDebito);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}


	/**
	 * @deprecated
	 * */
	public String generarNotaDebitoConIva() {
		try {
			// recupero el concepto de NOTA DEBITO SIN IVA(codigo 252), para impactar sus detalles
			Concepto conceptoNotaDebitoConIva = (Concepto) transaccionesService.getConceptoService()
					.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(252))).get(0);
			Operador oper = Session.getOperador();
			transaccionesService.getNotaDebitoConIvaService().registrarNotaDebito(cliente, conceptoNotaDebitoConIva, oper, valorDebito);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}


	public String generarNotaCreditoSinIva() {
		try {
			// recupero el concepto de NOTA CREDITO SIN IVA(codigo 261), para impactar sus detalles
			Concepto conceptoNotaCreditoSinIva = (Concepto) transaccionesService.getConceptoService()
					.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(261))).get(0);
			Operador oper = Session.getOperador();
			transaccionesService.getNotaCreditoSinIvaService().registrarNotaCredito(cliente, conceptoNotaCreditoSinIva, oper, valorCredito);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}


	public String generarNotaCreditoConIva() {
		try {
			// recupero el concepto de NOTA DEBITO SIN IVA(codigo 251), para impactar sus detalles
			Concepto conceptoNotaCreditoConIva = (Concepto) transaccionesService.getConceptoService()
					.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(251))).get(0);
			Operador oper = Session.getOperador();
			transaccionesService.getNotaCreditoConIvaService().registrarNotaCredito(cliente, conceptoNotaCreditoConIva, oper, valorCredito);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}


	public String crearNotaCredito() {
		log.info("Creo la nota credito");
		if (tipoNotaCredito.compareTo("S") == 0) {
			// nota de Credito sin iva
			generarNotaCreditoSinIva();
		} else {
			// nota de Credito con iva
			generarNotaCreditoConIva();
		}
		return "";
	}


	public String crearNotaDebito() {
		log.info("Creo la nota debito");
		if (tipoNotaDebito.compareTo("S") == 0) {
			// nota de Credito sin iva
			generarNotaDebitoSinIva();
		} else {
			// nota de Credito con iva
			generarNotaDebitoConIva();
		}
		return "";
	}


	public ClienteTransaccion getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTransaccion cliente) {
		this.cliente = cliente;
	}


	public boolean validar() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public List getListaItemsPendientes() {
		return listaItemsPendientes;
	}


	public void setListaItemsPendientes(List listaItemsPendientes) {
		this.listaItemsPendientes = listaItemsPendientes;
	}


	public List getLiquidacionesParticularesRealizadas() {
		return liquidacionesParticularesRealizadas;
	}


	public void setLiquidacionesParticularesRealizadas(
			List liquidacionesParticularesRealizadas) {
		this.liquidacionesParticularesRealizadas = liquidacionesParticularesRealizadas;
	}


	public List getLiquidacionesParticularesConfirmadas() {
		return liquidacionesParticularesConfirmadas;
	}


	public void setLiquidacionesParticularesConfirmadas(
			List liquidacionesParticularesConfirmadas) {
		this.liquidacionesParticularesConfirmadas = liquidacionesParticularesConfirmadas;
	}


	public String getTipoNotaCredito() {
		return tipoNotaCredito;
	}


	public void setTipoNotaCredito(String tipoNotaCredito) {
		this.tipoNotaCredito = tipoNotaCredito;
	}


	public String getTipoNotaDebito() {
		return tipoNotaDebito;
	}


	public void setTipoNotaDebito(String tipoNotaDebito) {
		this.tipoNotaDebito = tipoNotaDebito;
	}


	public Double getValorDebito() {
		return valorDebito;
	}


	public void setValorDebito(Double valorDebito) {
		this.valorDebito = valorDebito;
	}


	public Double getValorCredito() {
		return valorCredito;
	}


	public void setValorCredito(Double valorCredito) {
		this.valorCredito = valorCredito;
	}

}

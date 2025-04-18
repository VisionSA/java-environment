package com.bizitglobal.webapp.faces.beans.transacciones;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.EncriptacionNumeros;
import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.GestorLiquidacionClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiquidacionClientesException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ArchivoPF;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCuota;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteClienteAux;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaCargo;
import com.bizitglobal.tarjetafiel.transacciones.negocio.FuturosVencimientos;
import com.bizitglobal.tarjetafiel.transacciones.negocio.GestorLiquidacionCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteRepactacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiquidacionClientes;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.TasasLiquidacionClientes;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.transacciones.HttpPostMultipart;
import com.bizitglobal.webapp.faces.beans.util.MergePDF;
import com.lowagie.text.pdf.PdfReader;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings({"rawtypes","unchecked"})
public class LiquidacionClientesBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LiquidacionClientesBean.class);
	//private String focoHidden;
	private HtmlSelectOneMenu fechaLiquidacionSeleccionada = new HtmlSelectOneMenu();
	private String idFechaLiquidacionSeleccionada;
	private List listaFechasPosiblesLiq;

	// para la lista de impresoras disponibles....
	private HtmlSelectOneMenu impresoraSeleccionada = new HtmlSelectOneMenu();
	private Long idImpresoraSeleccionada;
	private List listaImpresorasDisponibles;
	private LiquidacionClientes liquidacionClientesEditada = null;

	private String popupReport = new String("");

	// el gestor es el que guiara el proceso de liquidacion....
	private GestorLiquidacionCliente gestor;
	private Integer cantidad;
	private Integer inicio;
	private boolean comunicado = false;
	

	// dos variables que me ayudan a sacar el saldo en linea de cada cliente
	public BigDecimal saldoTotal = new BigDecimal(0);
	public BigDecimal lineaDeCredito = new BigDecimal(0);

	public BigDecimal disponible = new BigDecimal(0);
	BigDecimal montoAdeudado = new BigDecimal(0);
	BigDecimal montoAdeudadoInteresRepac = new BigDecimal(0);
	String ipTransaccionador = "";

	private Date fechaPrimerVencimiento;
	private Date fechaSegundoVencimiento;
	private Date fechaTercerVencimiento;

	private Date fechaCierreAnterior;
	private Date fechaCierreActual;
	private Date fechaCierreProximo;
	private Date fechaVencimientoAnterior;
	private Date fechaVencimientoProximo;
	private List listaPreliquidacionesWrapper;
	private String directorioRelativoLiquidacion;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//private GestorLiquidacionClienteService gestorLiqClteService;
	//private LiquidacionClientesService liquidacionClientesService;

	//private FileReader fr = null;
	private FileWriter fw = null;
	//private BufferedReader br = null;
	private Date fechaInicioDate;
	private Date fechaAltaHasta;
	private Date fechaFinDate;
	private Double montoCargoAnual;
	private ParametroSistema parametrosCargoAnual;
	private Long cantDiasAnteriores;
	private Long cantidadCuotas;
	
	
	private TasasLiquidacionClientes tasasLiquidacionClientes;
	private String tasas;

	//private Date fechaCierre;


	public LiquidacionClientesBean() {
		error.borrar();

	}


	public String inicializar() {
		try {
			borrar();
			liquidacionClientesEditada = null;
			gestor = new GestorLiquidacionCliente();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -2);

			// recupero los dias no laborables que creo apropiados y se los paso a el gestor de liquidacion.
			Filtro filtro = new Filtro("fecha", Filtro.MAYOR, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
			cal.add(Calendar.MONTH, 6);
			filtro.agregarCampoOperValor("fecha", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
			List listaDiasNoLaborables = generalService.getNoLaborableService().getNoLaborable(filtro);
			gestor.setDiasFeriados(listaDiasNoLaborables);
			List diasDePago = transaccionesService.getGestorLiquidacionClienteService().recuperarDiasDePago();
			gestor.crearFechasParaLiquidar(diasDePago, transaccionesService.getGestorLiquidacionClienteService());
			transaccionesService.getGestorLiquidacionClienteService().reemplazarLiquidacionesRealizadas(gestor);
			rellenarComboFechas(gestor.getFechasPosiblesDeLiquidacion());
			rellenarComboImpresoras();

			presentarPreliquidaciones();
		} catch (Exception e) {
			log.error(e, e);
		}
		return "liquidacionClientes";
	}


	public void rellenarComboFechas(Object[] array) {
		listaFechasPosiblesLiq = new ArrayList();
		listaFechasPosiblesLiq.add(new SelectItem("Seleccione fecha a liquidar"));
		fechaLiquidacionSeleccionada.setValue("Seleccione fecha a liquidar");
		for (int i = array.length - 1; i > -1; i--) {
			listaFechasPosiblesLiq.add(new SelectItem(array[i].toString()));
		}
	}


	public void rellenarComboImpresoras() {
		listaImpresorasDisponibles = new ArrayList();
		listaImpresorasDisponibles.add(new SelectItem(new Long(0), "Implementar Impresoras..."));
		impresoraSeleccionada.setValue(new Long(0));
		// recuperar las impresoras con el service....
	}


	/**
	 * Presenta si es que las hay, preliquidaciones que no hayan sido confirmadas. Validara a su vez que no se vuelva a preliquidar si al menos hay
	 * una preliquidacion pendiente de confirmar o borrar.
	 * */
	public void presentarPreliquidaciones() {
		listaPreliquidacionesWrapper = new ArrayList();
		try {
			Filtro filtrin = new Filtro("confirmada", Filtro.LIKE, "N");
			List listaPreliquidaciones = transaccionesService.getLiquidacionClientesService().getLiquidacionClientes(filtrin);
			Iterator<LiquidacionClientes> iter = listaPreliquidaciones.iterator();
			while (iter.hasNext()) {
				LiquidacionClientes li = iter.next();
				if (li.getTipoLiquidacion() == null || li.getTipoLiquidacion() == 1) {
					listaPreliquidacionesWrapper.add(new PreliquidacionWrapper(li));
				}
			}
		} catch (LiquidacionClientesException e) {
			error.agregar("No se pudo leer la lista de preliquidaciones sin confirmar.");
		}
	}


	public void presentarListasDisponibles(ValueChangeEvent event) {
		log.info("Ejecutando cambiar fecha de liquidacion.");
		String valorAComparar = (String) fechaLiquidacionSeleccionada.getValue();
		liquidacionClientesEditada = null;
		for (int i = 0; i < gestor.getFechasPosiblesDeLiquidacion().length; i++) {
			if (gestor.getFechasPosiblesDeLiquidacion()[i].toString().compareTo(valorAComparar) == 0) {
				liquidacionClientesEditada = gestor.getFechasPosiblesDeLiquidacion()[i];
				break;
			}
		}
	}


	public boolean isHayLiquidacionSeleccionada() {
		return liquidacionClientesEditada != null;
	}


	// un booleano que dirige la visibilidad del panel con los datos de resumen de la liquidacion.
	public boolean isSePuedeConfirmar() {
		return (liquidacionClientesEditada != null && liquidacionClientesEditada.getFinalizo().compareTo("S") == 0 && liquidacionClientesEditada
				.getConfirmada().compareTo("N") == 0);
	}


	public boolean isPreliquidacion() {
		if (liquidacionClientesEditada == null)
			return false;
		return (liquidacionClientesEditada.getIdLiquidacionClientes() == null && listaPreliquidacionesWrapper.isEmpty());
	}


	public boolean isLiquidacion() {
		if (liquidacionClientesEditada == null)
			return false;
		return liquidacionClientesEditada.getIdLiquidacionClientes() != null;
	}


	public boolean isLiquidacionConfirmar() {
		if (liquidacionClientesEditada == null)
			return false;
		if (liquidacionClientesEditada.getFinalizo().compareTo("N") == 0)
			return false;
		return liquidacionClientesEditada.getIdLiquidacionClientes() != null;
	}


	public boolean validar() {
		error.borrar();
		
		return (error.cantidad() == 0) ? true : false;
	}


	public void borrar() {
		tasasLiquidacionClientes = null;
		comunicado = false;
		tasas = "";
		error.borrar();
		popup.borrar();
		tituloCorto = "Liquidación de Clientes";
		tituloLargo = "TARJETA FIEL";
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	/**
	 * Crea el directorio de las liquidaciones en la posicion especificada por los atributos del contexto.properties, de la siguiente forma:
	 * directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
	 * */
	private String crearDirectoriosLiquidacion(Date fechaCierre) throws Exception {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
				+ "contexto.properties");

		String nombreLiq = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
				+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre);

		String nombreLiqMails = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
				+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre)
				+ File.separator + "emails";

		//String nombreLiqRevista = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
			//	+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre)
			//	+ File.separator + "revista";
		
		String nombreLiqWhatsapp = System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
				+ File.separator + prop.getProperties("directorioLiquidacionesClientes") + File.separator + formatoFecha.format(fechaCierre)
				+ File.separator + "whatsapp";

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

			directorioInterno = new File(nombreLiqMails);
			if (!directorioInterno.exists()) {
				directorioInterno.mkdir();
			}

			directorioInterno = new File(nombreLiqWhatsapp);
			if (!directorioInterno.exists()) {
				directorioInterno.mkdir();
			}

			return nombreLiq;

		} catch (Exception e) {
			log.error(e, e);
			throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}

	}


	/**
	 * Preliquida una fecha.
	 * */
	public String preliquidar() {
		SessionFactory sessionFactory = transaccionesService.getSessionFactory();
		Concepto conceptoPagos = null;
		Concepto conceptoRepactaciones = null;
		Concepto conceptoParamGen = null;
		Concepto conceptoInteresPunitorio = null;
		Concepto conceptoInteresCompensatorio = null;
		Concepto conceptoCargoResumen = null;
		Concepto conceptoCargoEco = null;
		ConceptoCuota conceptoCargoAnual = null;
		Concepto conceptoSeguroVida = null;
		Concepto conceptosServicioPostal = null;
		Concepto conceptosServicioPostalRevista = null;
		Concepto conceptoSellos = null;

		ListaPrecioDetalle montoMinimoLiq = null;
		ListaPrecioDetalle[] pagos = null;
		Operador oper;
		oper = Session.getOperador();
		String rutaRelativa;

		//ListaPrecioItem[] repactaciones = null;

		// recupero el dia que de cierre, en el cual estoy queriendo liquidar
		Calendar calendar = Calendar.getInstance();

		// cal.setTime(liquidacionClientesEditada.getFechasCierresVencimientos()[0]);
		// calendar.setTime(liquidacionClientesEditada.getFechaLiquidacion());
		calendar.setTime(liquidacionClientesEditada.getFechaCierrePeriodo());
		int diaCierre = calendar.get(Calendar.DATE);
		if (diaCierre == 29 || diaCierre == 28) {
			diaCierre = 30;
		}

		try {

			/**
			 * Calculo de fecha a utilizar en la liquidacion
			 */
			fechaCierreActual = liquidacionClientesEditada.getFechaCierrePeriodo();

			fechaPrimerVencimiento = addDiasFechaLaborable(fechaCierreActual, 15);
			fechaSegundoVencimiento = addDiasFechaLaborable(fechaCierreActual, 20);
			fechaTercerVencimiento = addDiasFechaLaborable(fechaCierreActual, 20);

			calendar.setTime(fechaCierreActual);
			calendar.add(Calendar.MONTH, -1);
			if (diaCierre > calendar.get(Calendar.DATE) && calendar.get(Calendar.MONTH) != 1) {
				calendar.set(Calendar.DATE, diaCierre);
			}
			fechaCierreAnterior = calendar.getTime();

			calendar.setTime(fechaCierreActual);
			calendar.add(Calendar.MONTH, 1);
			if (diaCierre > calendar.get(Calendar.DATE) && calendar.get(Calendar.MONTH) != 1) {
				calendar.set(Calendar.DATE, diaCierre);
			}
			fechaCierreProximo = calendar.getTime();

			fechaVencimientoAnterior = addDiasFechaLaborable(fechaCierreAnterior, 15);
			fechaVencimientoProximo = addDiasFechaLaborable(fechaCierreProximo, 15);

			/* Se recuperan los parametros de liquidación */
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

			/* Calculo el monto mínimo para liquidar */
			try {
				// DetallesParamGen: aqui tengo que enviarle el id del cliente....
				Set detallesParamGen = conceptoParamGen.getListaPrecio(0L).getVersionVigente().getDetallesListaPrecio();
				montoMinimoLiq = new ListaPrecioDetalle(); // hay un solo monto minimo.
				Iterator listaParamGen = detallesParamGen.iterator();
				while (listaParamGen.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaParamGen.next();
					montoMinimoLiq = var;
					break;
				}
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Se produjo un error al intentar formar el set de parametros generales de la liquidacion. Posibles causas: el Concepto 12 (Parametros Generales) se encuentra fuera de vigencia o no tiene lista precio asociada");

			}

			/**
			 * Calculo de fecha a utilizar en la liquidacion
			 */

			transaccionesService.getLiquidacionClientesService().limpiarLiquidacionesParticulares();

			// Se actualiza la fecha facturacion de los items de la ctacte no liquidados en liquidaciones anteriores.
			transaccionesService.getLiquidacionClientesService().actualizarFechaFacturacionItemsNoLiquidados(fechaCierreActual, diaCierre);

			// Se graba directamente, ya que al ser una preliquidacion no existia el objeto aun.
			liquidacionClientesEditada.setFechaPreliq(new Timestamp(new Date().getTime()));

			transaccionesService.getCalculoInteresComService().calcularInteresCompensatorio(fechaVencimientoAnterior, fechaCierreAnterior,
					fechaCierreActual, oper, diaCierre, fechaPrimerVencimiento);
			
			// OJO SACO ESTO PARA PROBAR LA LIQUIDACION ACTIVAR EN PRODUCCION

			transaccionesService.getCalculoInteresComService().calcularInteresPunitorios(fechaVencimientoAnterior, fechaCierreAnterior,
					fechaCierreActual, oper, diaCierre, fechaPrimerVencimiento);
			
			// HASTA AQUI NOMAS SACO ACTIVAR PARA PRODUCCION

			// transaccionesService.getCalculoInteresComService().calcularInteresCompesnatorioVencimientos(fechaVencimientoAnterior,
			// fechaCierreAnterior, fechaCierreActual, oper, diaCierre);
			//
			// transaccionesService.getCalculoInteresComService().calcularInteresCompesnatorio30Dias(fechaCierreAnterior, fechaCierreActual,
			// fechaVencimientoAnterior, oper, pagoMinimo.getMonto(), diaCierre);

			transaccionesService.getCalculoInteresComService().setCalculoInteresRealizado(fechaCierreActual);

			transaccionesService.getLiquidacionClientesService().grabarLiquidacionClientes(liquidacionClientesEditada);

			List idDeClientesALiquidar = transaccionesService.getGestorLiquidacionClienteService().recuperarClientes(
					liquidacionClientesEditada.getFechaCierrePeriodo(), inicio, cantidad);

			liquidacionClientesEditada.setTipoLiquidacion(new Integer(1));

			log.info("Se liquidará sobre " + idDeClientesALiquidar.size() + " clientes.");

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
				conceptoInteresPunitorio.armarConcepto(fechaCierreActual);
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
				conceptoInteresCompensatorio.armarConcepto(fechaCierreActual);
				conceptoInteresCompensatorio.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto INTERES COMPENSATORIO (codigo 220). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de CARGO ECO (codigo 603), para obtener la lista de precios que utiliza
				conceptoCargoEco = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(603))).get(0);
				conceptoCargoEco.armarConcepto(fechaCierreActual);
				conceptoCargoEco.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto CARGO ECO (codigo 249). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de CARGO RESUMEN (codigo 204), para obtener la lista de precios que utiliza
				conceptoCargoResumen = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(204))).get(0);
				conceptoCargoResumen.armarConcepto(fechaCierreActual);
				conceptoCargoResumen.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto CARGO RESUMEN (codigo 221). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			try {
				// recupero el concepto de CARGO SEGURO DE VIDA (codigo 268), para obtener la lista de precios que utiliza
				conceptoSeguroVida = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(268))).get(0);
				conceptoSeguroVida.armarConcepto(fechaCierreActual);
				conceptoSeguroVida.armarReglaConcepto();
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException(
						"Error al intentar utilizar el Concepto SEGURO DE VIDA (codigo 208). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

//			try {
//				// recupero el concepto de CARGO Servicio Postal (codigo 267), para obtener la lista de precios que utiliza
//				conceptosServicioPostal = (Concepto) transaccionesService.getConceptoService()
//						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(267))).get(0);
//				conceptosServicioPostal.armarConcepto(fechaCierreActual);
//				conceptosServicioPostal.armarReglaConcepto();
//			} catch (Exception e) {
//				log.error(e, e);
//				throw new GestorLiquidacionClienteException(
//						"Error al intentar utilizar el Concepto Servicio Postal  (codigo 267). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
//			}

//			try {
//				// recupero el concepto de CARGO Servicio Postal Revista (codigo 269), para obtener la lista de precios que utiliza
//				conceptosServicioPostalRevista = (Concepto) transaccionesService.getConceptoService()
//						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(269))).get(0);
//				conceptosServicioPostalRevista.armarConcepto(fechaCierreActual);
//				conceptosServicioPostalRevista.armarReglaConcepto();
//			} catch (Exception e) {
//				log.error(e, e);
//				throw new GestorLiquidacionClienteException(
//						"Error al intentar utilizar el Concepto Servicio Postal  (codigo 269). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
//			}

			try {
				// recupero el concepto de SELLOS (codigo 209), para obtener la lista de precios que utiliza
				conceptoSellos = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(209))).get(0);
				conceptoSellos.armarConcepto(fechaCierreActual);
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
				conceptoPagos.armarConcepto(fechaCierreActual);
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

			// try {
			// // recupero el concepto de LIQUIDACION PARAMETROS GENERALES (id nro. 12), para obtener la lista de precios que utiliza
			// conceptoParamGen = transaccionesService.getConceptoService().leerConcepto(new Long(12));
			// conceptoParamGen.armarConcepto();
			// conceptoParamGen.armarReglaConcepto();
			// } catch (Exception e) {
			// log.error(e,e);
			// throw new
			// GestorLiquidacionClienteException("Error al intentar utilizar el Concepto 12 (Parametros generales de la liquidacion). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			// }

			// llamo al metodo que crea el directorio donde se almacenaran las liquidaciones.
			try {
				rutaRelativa = crearDirectoriosLiquidacion(fechaCierreActual);
			} catch (Exception e) {
				log.error(e, e);
				throw new GestorLiquidacionClienteException("No se ha podido crear el directorio para almacenar las liquidaciones.");
			}

			log.info("******************************************************************");
			log.info("Lee los datos necesarios para el cargo anual");
			parametrosCargoAnual = obtenerParametrosCargoAnual();
			conceptoCargoAnual = obtenerConceptoCargoAnual();
			log.info("Finaliza lectura de datos necesarios para cargo anual");

			// Controla si la fecha de cierre está contenida en el rango del cálculo del cargo anual para leer sus datos
			if (fechaCierreActual.after(fechaInicioDate) && fechaCierreActual.before(fechaFinDate))
			{
				log.info("Comienza la generación del cargo anual");
				// Se lee una lista de clientes a liquidar con todas sus condiciones para generarles el cargo anual por servicio
				List clientesCargoAnual = transaccionesService.getCargoAnualService().obtenerClientesAGenerarCargoAnual(fechaCierreActual, inicio,
						cantidad, fechaAltaHasta);
				Iterator clientesCargoAnualIt = clientesCargoAnual.iterator();
				int cantidad = 0;
				while (clientesCargoAnualIt.hasNext())
				{
					Long idClienteCA = (Long) clientesCargoAnualIt.next();
					cantidad++;
					generarCargoAnual(conceptoCargoAnual, oper, idClienteCA);
				}
				log.info("Finaliza la generación del cargo anual. " + cantidad + " Clientes.");
			}
			log.info("******************************************************************");

			// limpio la tabla de ctacte_clientes_aux, solo limpio los que no tienen alguna liquidacion editada.
			transaccionesService.getGestorLiquidacionClienteService().limpiarLiquidacionACtaCteCliente();

			// recorremos los clientes y para cada uno armamos el objeto liqCliente.
			int cantidadDeClientes = 0;
			Iterator iterClientes = idDeClientesALiquidar.iterator();
			
			
			//EL id no se utiliza, por lo tanto mando 0.
			BigDecimal intCompensatorio = new BigDecimal(conceptoInteresCompensatorio.getListaPrecio(0L, fechaCierreActual)
					.getVersionVigente().getTnaFiel().doubleValue()); 
			BigDecimal intPunitorio = new BigDecimal(conceptoInteresPunitorio.getListaPrecio(0L, fechaCierreActual)
					.getVersionVigente().getTnaFiel().doubleValue());
			
			
			
			tasasLiquidacionClientes = transaccionesService.getGestorLiquidacionClienteService().getTasasLiquidacionClientes();
			String tasaCompensatorioProx = "ƒtna1=" + tasasLiquidacionClientes.getTnaCompensatorioProx()
								 + "ƒtea1=" + tasasLiquidacionClientes.getTeaCompensatorioProx()
								 + "ƒcfsi1=" + tasasLiquidacionClientes.getCfsiCompensatorioProx()
								 + "ƒcfci1=" + tasasLiquidacionClientes.getCfciCompensatorioProx();
			
			String tasaPunitorioProx = "ƒtna2=" + tasasLiquidacionClientes.getTnaPunitorioProx() 
							 + "ƒtea2=" + tasasLiquidacionClientes.getTeaPunitorioProx()
							 + "ƒcfsi2=" + tasasLiquidacionClientes.getCfsiPunitorioProx()
							 + "ƒcfci2=" + tasasLiquidacionClientes.getCfciPunitorioProx();
			
			String tasaAdelantoProx = "ƒtna3=" + tasasLiquidacionClientes.getTnaAdelantoProx()
							+ "ƒtea3=" + tasasLiquidacionClientes.getTeaAdelantoProx()
							+ "ƒcfsi3=" + tasasLiquidacionClientes.getCfsiAdelantoProx()
							+ "ƒcfci3=" + tasasLiquidacionClientes.getCfciAdelantoProx();
			
			String tasaCompensatorioVigente = "ƒtna4=" + tasasLiquidacionClientes.getTnaCompensatorioVigente()
									+ "ƒtea4=" + tasasLiquidacionClientes.getTeaCompensatorioVigente()
									+ "ƒcfsi4=" + tasasLiquidacionClientes.getCfsiCompensatorioVigente()
									+ "ƒcfci4=" + tasasLiquidacionClientes.getCfciCompensatorioVigente();
			
			String tasaPunitorioVigente = "ƒtna5=" + tasasLiquidacionClientes.getTnaPunitorioVigente()
								+ "ƒtea5=" + tasasLiquidacionClientes.getTeaPunitorioVigente()
								+ "ƒcfsi5=" + tasasLiquidacionClientes.getCfsiPunitorioVigente()
								+ "ƒcfci5=" + tasasLiquidacionClientes.getCfciPunitorioVigente();
			
			String tasaAdelantoVigente = "ƒtna6=" + tasasLiquidacionClientes.getTnaAdelantoVigente()
							   + "ƒtea6=" + tasasLiquidacionClientes.getTeaAdelantoVigente()
							   + "ƒcfsi6=" + tasasLiquidacionClientes.getCfsiAdelantoVigente()
							   + "ƒcfci6=" + tasasLiquidacionClientes.getCfciAdelantoVigente();

			tasas = tasaCompensatorioVigente + tasaPunitorioVigente + tasaAdelantoVigente 
			+ tasaCompensatorioProx + tasaPunitorioProx + tasaAdelantoProx;
			
			//log.info("Parametros tasas: " + tasas);
			
			
			String urlServerClienteWeb = ContextoProperties.getProperty("urlServerClienteWeb");
			//Arranco desde caracter 7, ya que le saco http://
			urlServerClienteWeb = urlServerClienteWeb.substring(7,urlServerClienteWeb.length());
			//vacío tabla temporal con links sms
			String sql = "DELETE FROM t_aux_link_sms";
			generalService.getGenericDao().ejecutarQuery(sql,null);
			
			while (iterClientes.hasNext()) {
				// itero cliente a cliente.
				Long idCliente = (Long) iterClientes.next();
				log.info("Liquidare cliente:" + idCliente);

				// corregimos el importe de imputacion de toda la cuenta corriente del cliente
				transaccionesService.getCtaCteClienteService().corregirImportesImputacion(idCliente);

				try {
					// Pagos: aqui tengo que enviarle el id del cliente....
					Set detallesPagos = conceptoPagos.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente().getDetallesListaPrecio();
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

				// try {
				// // DetallesParamGen: aqui tengo que enviarle el id del cliente....
				// Set detallesParamGen = conceptoParamGen.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
				// pagoMinimo = new ListaPrecioDetalle(); // hay un solo monto minimo.
				// Iterator listaParamGen = detallesParamGen.iterator();
				// while (listaParamGen.hasNext()) {
				// ListaPrecioDetalle var = (ListaPrecioDetalle)listaParamGen.next();
				// pagoMinimo = var;
				// break;
				// }
				// } catch (Exception e) {
				// log.error(e,e);
				// throw new
				// GestorLiquidacionClienteException("Se produjo un error al intentar formar el set de parametros generales de la liquidacion. Posibles causas: el Concepto 12 (Parametros Generales) se encuentra fuera de vigencia o no tiene lista precio asociada");
				//
				// }

				// calculo el total del cliente...
				BigDecimal[] totalesDeCliente = transaccionesService.getGestorLiquidacionClienteService().calcularTotalConsumoPorCliente(
						idCliente.intValue(), liquidacionClientesEditada.getFechaCierrePeriodo());
				BigDecimal totalCliente = totalesDeCliente[0].setScale(2, BigDecimal.ROUND_HALF_DOWN);
				// Se buscan los items de tipo concepto detalle capital (1)
				BigDecimal conceptosCapital = totalesDeCliente[3].setScale(2, BigDecimal.ROUND_HALF_DOWN);

				// Se calcula la mora anterior antes de impactar la liquidacion, si no hay que utilizar una consulta muy compleja.
				montoAdeudado = transaccionesService.getGestorLiquidacionClienteService().calcularMoraAnterior(idCliente.intValue(),
						liquidacionClientesEditada.getFechaCierrePeriodo());

				ClienteLiquidacion cliente = transaccionesService.getClienteLiquidacionService().leerCliente(idCliente);
				cliente.getIndividuo();
				cliente.getNombreCliente();
				lineaDeCredito = cliente.getLimiteCredito();
				
				
				//******************** SALDO TOTAL = SALDO CONSUMOS + SALDO ADELANTOS  ************************//
				log.info("CALCULANDO SALDO REAL CLIENTE: " + idCliente);
				saldoTotal =  transaccionesService.getLiquidacionClientesService().getSaldoTotal(idCliente);//cliente.getSaldoLineaTotal();	
				
				//** DISPNIBLE = LIMITE CREDITO - SALDO CONSUMOS
				disponible = lineaDeCredito.add(cliente.getSaldoLinea().negate());

				// Se crea el objeto liquidacion, solo si el monto a liquidar es mayor al monto minimo liquidable, eido de la tabla de parametros.
				// O si el cliente tiene algun concepto de tipo [Consumo, Adelanto Efectivo, Repactacion, Refinanciacion] en la fecha de cierre.
				double montoAdeudadoAComparar = (montoAdeudado.doubleValue() > 0 ? montoAdeudado.doubleValue() : 0);

				if ((totalCliente.doubleValue() + montoAdeudadoAComparar) > montoMinimoLiq.getMonto().doubleValue()
						|| conceptosCapital.doubleValue() != 0) {
					// si se encuentra monto para facturar, lo primero que hago es setear la deuda anterior por repactaciones y por intereses.
					// montoAdeudadoInteresRepac =
					// transaccionesService.getGestorLiquidacionClienteService().calcularMoraAntPorInteresesYRepac(idCliente.intValue());
					
					
					/*
					double importe = 0;

					try {
						// Cargo Resumen: aqui tengo que enviarle el id del cliente....
						Set detallesPagos = conceptoCargoResumen.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
								.getDetallesListaPrecio();
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
					*/

					/*
					double importeEco = 0;
					try {
						// Cargo Eco: aqui tengo que enviarle el id del cliente....
						Set detallesPagos = conceptoCargoEco.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
								.getDetallesListaPrecio();
						Iterator listaPagos = detallesPagos.iterator();
						while (listaPagos.hasNext()) {
							ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
							importeEco = var.getMonto().doubleValue();
							break;
						}

					} catch (Exception e) {
						log.error(e, e);
						throw new GestorLiquidacionClienteException(
								"Se produjo un error al intentar registrar el cargo eco. Posibles causas: el Concepto de codigo 603 (Cargo ECO) se encuentra fuera de vigencia o no tiene lista precio asociada");
					}
					*/

					/*
					double importeServicioPostal = 0;
					try {
						// Cargo Eco: aqui tengo que enviarle el id del cliente....
						Set detallesPagos = conceptosServicioPostal.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
								.getDetallesListaPrecio();
						Iterator listaPagos = detallesPagos.iterator();
						while (listaPagos.hasNext()) {
							ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
							importeServicioPostal = var.getMonto().doubleValue();
							break;
						}

					} catch (Exception e) {
						log.error(e, e);
						throw new GestorLiquidacionClienteException(
								"Se produjo un error al intentar registrar el cargo eco. Posibles causas: el Concepto de codigo 603 (Cargo ECO) se encuentra fuera de vigencia o no tiene lista precio asociada");
					}
					*/

					/*
					double importeServicioPostalRevista = 0;
					try {
						// Cargo Eco: aqui tengo que enviarle el id del cliente....
						Set detallesPagos = conceptosServicioPostalRevista.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
								.getDetallesListaPrecio();
						Iterator listaPagos = detallesPagos.iterator();
						while (listaPagos.hasNext()) {
							ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
							importeServicioPostalRevista = var.getMonto().doubleValue();
							break;
						}

					} catch (Exception e) {
						log.error(e, e);
						throw new GestorLiquidacionClienteException(
								"Se produjo un error al intentar registrar el cargo eco. Posibles causas: el Concepto de codigo 603 (Cargo ECO) se encuentra fuera de vigencia o no tiene lista precio asociada");
					}
					*/

					/*
					double porcentajeSeguroVida = 0;
					try {
						// Cargo por Seguro de vida: aqui tengo que enviarle el id del cliente....
						Set detallesPagos = conceptoSeguroVida.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
								.getDetallesListaPrecio();
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
					*/
					
					/*
					// recupero el porcentaje a cobrar por mora
					Double interesPorMora = new Double(0);
					Set detallesInteresCompensatorio = conceptoInteresCompensatorio.getListaPrecio(idCliente, fechaCierreActual).getVersionVigente()
							.getDetallesListaPrecio();
					Iterator listaInteres = detallesInteresCompensatorio.iterator();

					while (listaInteres.hasNext()) {
						ListaPrecioDetalle var = (ListaPrecioDetalle) listaInteres.next();
						interesPorMora = var.getPorcentaje();
						break;
					}
					*/

					// construyo el objeto LiqCliente y se lo agrego al set de nuestro objeto
					LiqCliente liqCliente = new LiqCliente(intPuni, intComp, segDeud, tasaA, tasaRefin, new BigDecimal(0), liquidacionClientesEditada);
					liqCliente.setMontoTotal(totalCliente);
					liqCliente.setMontoAdeudado(montoAdeudado.doubleValue());
					liqCliente.setMontoAdeudadoRepactado(new Double(0));
					// liqCliente.setTotalIntereses(totalInteresesCliente);
					// liqCliente.setTotalRepactado(totalRepactacionesCliente);
					liqCliente.setImportePagado(new BigDecimal(0));
					// recupero el intCompensatorio y el punitorio...
					liqCliente.setIntComp(intCompensatorio);
					liqCliente.setIntPunitorios(intPunitorio);
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
					liquidacionClientesEditada.getLiqClientes().add(liqCliente);

					// VER BIEN ESTO!!... NO VA A FUNCIONAR PARA MUCHOS CLIENTES...
					transaccionesService.getLiqClienteService().grabarLiqCliente(liqCliente);

					// generamos si es que hay retraso en el pago, el cargo por mora... para esto recuperamos primero los pagos anteriores de este
					// cliente
					// lo sacamos, porque se calcula antes. montoAdeudado =
					// transaccionesService.getGestorLiquidacionClienteService().calcularMoraAnterior(idCliente.intValue(),
					// liqCliente.getIdLiqCliente().longValue());
					if (montoAdeudado.intValue() > 0) {
						// Double porcentaje = Double.valueOf(montoAdeudado.doubleValue()*interesPorMora.doubleValue()/100);
						// generarInteresMora(cliente, conceptoInteresCompensatorio, oper, liqCliente,porcentaje);
					}
					BigDecimal importeAux;
					Boolean costoResumen = true;
					Boolean excluirCargos = false;
					Boolean personalFiel = false;

					Integer consumoDebitoInteres[] = transaccionesService.getGestorLiquidacionClienteService().tieneConsumosSoloDebitos(
							idCliente.intValue(), liquidacionClientesEditada.getFechaCierrePeriodo());
					Integer consumo = consumoDebitoInteres[0];
					Integer debito = consumoDebitoInteres[1];
					Integer interes = consumoDebitoInteres[2];
					Integer cargos = consumoDebitoInteres[3];
					Integer nota_debito = consumoDebitoInteres[4];

					
					/* DESHABILITADO 04/02/2019. Se Cobra cargo de resumen a clientes q tienen solo debitos.
					// Solo Debito
					if ((interes == 0) && (consumo > 0) && (debito > 0) && (consumo == debito) && (cargos == 0) && (nota_debito == 0)) {
						costoResumen = false;
					}
					
					// Intereses Compensatorios mas debitos no cobrar cargo de cargo de resumen
					if ((interes == 1) && (debito > 0) && (consumo == debito) && (cargos == 0) && (nota_debito == 0)) {
						if (transaccionesService.getGestorLiquidacionClienteService().tieneSoloInteresCompensatorio(idCliente.intValue(),
								liquidacionClientesEditada.getFechaCierrePeriodo())) {
							costoResumen = false;
						}
					}
					*/
					
					

					// No posee consumo esta al dia controlar si tiene solo cargo de reimpresion de plastico o cargo anual
					if ((interes == 0) && (consumo == 0) && (debito == 0) && (cargos == 0) && (nota_debito == 0)) {
						if (transaccionesService.getGestorLiquidacionClienteService().tieneCargoPlasticoOrAnual(idCliente.intValue(),
								liquidacionClientesEditada.getFechaCierrePeriodo())) {
							costoResumen = false;
						}
					}



					// Cuentas cerradas con saldo no paga gasto de resumen. Personal Fiel Tambien esta en esta tabla.
					if (transaccionesService.getGestorLiquidacionClienteService().excluidoMantenimientoCta(idCliente.intValue())) {
						excluirCargos = true;
					}
					
					// Personal de Fiel no paga cargos.
					personalFiel = transaccionesService.getGestorLiquidacionClienteService().esPersonalFiel(idCliente.intValue());


					if (!excluirCargos) {
						// generamos el cargo de resumen.
						if(costoResumen){
							generarCargoResumen(cliente, conceptoCargoResumen, oper, liqCliente);
						}
					}
					
					
					//if(costoResumen){
						// Genera servicio postal ( tiene revista || resumen impreso || no tiene mail)
//						if(!personalFiel){
//							if (transaccionesService.getGestorLiquidacionClienteService().clienteTieneRevista(idCliente.intValue(),
//									liquidacionClientesEditada.getFechaCierrePeriodo())
//								|| !(transaccionesService.getGestorLiquidacionClienteService().noPoseeMail(idCliente.intValue()) ) ) 
//							 {							
//								String noImpreso = "SI";	
//								} else {
//									generarServicioPostal(cliente, conceptosServicioPostal, oper, liqCliente);	
//								}
//							//}
//						
//							// generarServicioPostal(cliente, conceptosServicioPostal, oper, liqCliente);
//						
//							if (transaccionesService.getGestorLiquidacionClienteService().tieneConsumoEco(idCliente.intValue(),
//									liquidacionClientesEditada.getFechaCierrePeriodo())
//									&& generalService.getParametroSistemaService().leerParametroSistema(15L).getParametroSistemaDetalle(21L).getValor()
//											.toUpperCase().equals("SI")) {
//								// generamos el cargo de eco.
//								generarCargoEco(cliente, conceptoCargoEco, oper, liqCliente);
//	
//							}
//						}
//						
					
				
					
					
					if (!personalFiel) {
                      
                        if (LiquidacionClientesBean.transaccionesService.getGestorLiquidacionClienteService().tieneConsumoEco(idCliente.intValue(), this.liquidacionClientesEditada.getFechaCierrePeriodo()) && LiquidacionClientesBean.generalService.getParametroSistemaService().leerParametroSistema(Long.valueOf(15L)).getParametroSistemaDetalle(Long.valueOf(21L)).getValor().toUpperCase().equals("SI")) {
                            this.generarCargoEco(cliente, conceptoCargoEco, oper, liqCliente);
                        }
                    }


						
						// generamos el Seguro de vida.
						// generarSeguroVida(cliente, conceptoSeguroVida, oper, liqCliente);
					//}
					
					
					if (costoResumen && generalService.getParametroSistemaService().leerParametroSistema(17L).getParametroSistemaDetalle(25L).getValor()
									.toUpperCase().equals("SI")) {
						// generamos el Seguro de vida.
						if(!personalFiel){
							BigDecimal importeAuxTotal = transaccionesService.getGestorLiquidacionClienteService().getImportesAgregadosPorLiquidacion(
									liqCliente.getIdLiqCliente());
							importeAuxTotal = importeAuxTotal.add(saldoTotal);
							if (importeAuxTotal.doubleValue() > 0) {
								generarSeguroVida(cliente, conceptoSeguroVida, oper, liqCliente, importeAuxTotal);
							}
						}
					}

					// Una vez que ya impacte la ctacte Auxiliar, recupero el monto total que suma en esta tabla la liquidacion, para agregarlo al
					// total del cliente y al monto adeudado.
					importeAux = transaccionesService.getGestorLiquidacionClienteService().getImportesAgregadosPorLiquidacion(
							liqCliente.getIdLiqCliente());
					// Aqui modificar el saldo en linea del cliente
					
					totalCliente = totalCliente.add(importeAux);
					
					// por ultimo calculo el impuesto de sellos sobre el total del cliente acumulado hasta este entonces.
					BigDecimal montoSellos = new BigDecimal(0);
					try {
						montoSellos = montoSellos.add(transaccionesService.getCtaCteClienteAuxService().impactarSellos(conceptoSellos, idCliente,
								liqCliente, totalCliente));
						totalCliente = totalCliente.add(montoSellos);
					} catch (Exception e) {
						log.error(e, e);
						throw new GestorLiquidacionClienteException("Se produjo un error al intentar impactar los sellos.");
					}
					

					// Actualizo la ctaCte cliente seteandole el id de la LiqCliente. Recordar que si el proceso completo no se puede terminar, estos
					// id seran borrados. o sea son provisorios.
					transaccionesService.getGestorLiquidacionClienteService().asignarLiquidacionACtaCteCliente(idCliente.intValue(),
							liquidacionClientesEditada.getFechaCierrePeriodo(), liqCliente);

					// NO Hace falta asignar la liquidacion en la tabla auxiliar, ya que se le ha pasado por parametro el id de LIqCliente.
					// transaccionesService.getGestorLiquidacionClienteService().asignarLiquidacionACtaCteClienteAux(idCliente.intValue(),
					// liquidacionClientesEditada.getFechaCierrePeriodo(), liqCliente);

					// aqui debo actualizar sies que pago de mas , se lo descuento y ya le pongo como paga la liquidacion

					liqCliente.setResumenAterior(montoAdeudado.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() +
							totalCliente.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());

					liqCliente.setMontoTotal(totalCliente);

					liqCliente.setSaldoTotal(saldoTotal.add(importeAux).doubleValue());
					liqCliente.setDisponible(disponible.add(importeAux.negate()).doubleValue());

					// BigDecimal pagosAdelantados = transaccionesService.getGestorLiquidacionClienteService().actualizarTablaPagosClientes(
					// liqCliente.getIdLiqCliente(), liqCliente.getMontoTotal(), idCliente,fechaCierreActual);
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
						transaccionesService.getCtaCteClienteService().imputarLasCtaCteClientes(liqCliente.getIdLiqCliente(),
								pagosAdelantados.doubleValue());
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
							transaccionesService.lookupService("calculoCuotaServicesTarget"), diaCierre, liqCliente.getFechaCierre()))
						throw new Exception("No se pudo calcular la repactacion del cliente de id: " + idCliente);
					// construyo el array de fechas y pagos para pasar al constructor de liqVencimientos futuros...
					Date[] fechas = new Date[6];
					BigDecimal[] montos = new BigDecimal[7];
					// recupero los 6 meses siguientes.... mejorar la consulta.
					BigDecimal totalMes;
					for (int l = 0; l < 6; l++) {
						Calendar calenMes = Calendar.getInstance();
						calenMes.setTime(liquidacionClientesEditada.getFechaCierrePeriodo());
						calenMes.add(Calendar.MONTH, l + 1);
						Date fechaMesQueViene = calenMes.getTime();
						totalMes = transaccionesService.getGestorLiquidacionClienteService().calcularTotalConsumoPorClienteFut(idCliente.intValue(),
								fechaMesQueViene);
						fechas[l] = fechaMesQueViene;
						montos[l] = totalMes;
					}

					// generamos el pdf para esta liqCliente....
					liqCliente.setRutaPdf(generarPDF(liqCliente, idCliente, rutaRelativa));
					
					
					//genera ruta sms
					String link = urlServerClienteWeb + "L?p=";
					EncriptacionNumeros encriptador = new EncriptacionNumeros();
					String parametrosLink = idCliente.toString() + "-" + liqCliente.getIdLiqCliente().toString();
					link += encriptador.encriptar(parametrosLink);
					sql = "insert into t_aux_link_sms (C_ID_CLIENTE, C_LINK, C_ID_LIQ_CLIENTE) values (" + idCliente + ", '" + link + "', " + liqCliente.getIdLiqCliente() + ")";
					generalService.getGenericDao().ejecutarQuery(sql,null);
					
					
					
					transaccionesService.getLiqClienteService().actualizarLiqCliente(liqCliente);
					// por ultimo le acutualizo el saldo en linea al cliente.
					transaccionesService.getGestorLiquidacionClienteService().actualizarSaldoEnLinea(idCliente, importeAux.add(montoSellos));

				} else {
					// si no supera el pago minimo, no se liquida este cliente.x
					log.info("CLIENTE NO LIQUIDADO: El cliente" + idCliente + "no se pudo liquidar, no supra el monto minimo");
				}
				if (cantidadDeClientes % 20 == 0) {
					sessionFactory.getCurrentSession().flush();
					sessionFactory.getCurrentSession().clear();
					log.info("sessionFactory.getCurrentSession().clear() " + cantidadDeClientes);
					// sessionFactory.getCurrentSession().close();
				}
				cantidadDeClientes++;

			}
			// genero archivo pagomiscuentas
			generarArchivoPagoMisCuentas(liquidacionClientesEditada);

			// una vez que recorrio todos los clientes, le seteo el finalizado en 'N'
			liquidacionClientesEditada.setFinalizo("S");
			transaccionesService.getLiquidacionClientesService().actualizarLiquidacionClientes(liquidacionClientesEditada);

		} catch (LiquidacionClientesException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} catch (GestorLiquidacionClienteException e) {
			log.error(e, e);
			error.agregar(e.getMessage());
		} catch (Exception e) {
			log.error(e, e);
		}
		presentarPreliquidaciones();
		return "";
	}


	// PROYECTO 4029 PAGOMISCUENTAS

	private void generarArchivoPagoMisCuentas(LiquidacionClientes liquidacionCliente) {

		try {
			log.info("Comienzo la generación del archivo de facturacion");
			// Object[] liqClientes = liquidacionCliente.getLiqClientes().toArray();
			Format dateFile = new SimpleDateFormat("ddMMyy");
			Format dateFormat = new SimpleDateFormat("yyyyMMdd");
			Format dateFormatMes = new SimpleDateFormat("yyMM");
			Format dateFormatLink = new SimpleDateFormat("yyMMdd");
			Format dateFormatMesDia = new SimpleDateFormat("MMdd");
			
			Date fCierre = liquidacionCliente.getFechaCierrePeriodo();

			String fechaVencimiento = "";
			String fecha2 = "";
			String fecha3 = "";
			String fecha3Link = "";

			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#######0.00", simbolos);
			formateador.setMinimumFractionDigits(2);

			// guarda la liquidacion para PagoMisCuentas
			List acredList = new ArrayList();

			// guarda la liquidacion para Red Link
			List acredLinkList = new ArrayList();	

			// guarda el archivo de Control para Red Link
			List acredControlLinkList = new ArrayList();

			// Archivo de cabezera de PagoMisCuentas
			StringBuffer cabezera = new StringBuffer(280);
			cabezera.append("0");
			cabezera.append("400");
			cabezera.append("0321");
			cabezera.append(dateFormat.format(fCierre));
			String fechaArchivo = dateFile.format(fCierre);
			cabezera.append(Convertidores.completarDerecha("0", new Character('0'), 264));
			acredList.add(cabezera);
			// Fin del Archivo de Cabezera de PagoMisCuentas
			log.info("Archivo de Cabezera PagoMisCuentas " + cabezera);

			// PROYECTO Red Link
			// Archivo de cabezera de RedLink
			StringBuffer cabezeraLink = new StringBuffer(119);
			cabezeraLink.append("HRFACTURACION");
			cabezeraLink.append("AJ2");
			cabezeraLink.append(dateFormatLink.format(fCierre));
			cabezeraLink.append("00001");
			cabezeraLink.append(Convertidores.completarDerecha(" ", new Character(' '), 92));
			acredLinkList.add(cabezeraLink);
			// Fin del Archivo de Cabezera de RedLink
			log.info("Archivo de Cabezera Red Link " + cabezeraLink);
			// PROYECTO Red Link

			Long cantidadRegistro = new Long(0);
			Double totalImporte = 0D;

			Iterator iter = liquidacionCliente.getLiqClientes().iterator();
			while (iter.hasNext()) {
				LiqCliente liqCliente = (LiqCliente) iter.next();
				StringBuffer detalle = new StringBuffer(280);
				StringBuffer detalleLink = new StringBuffer(119);

				fechaVencimiento = dateFormat.format(liqCliente.getFechaLiq());//Fecha vto actual
				fecha2 = dateFormat.format(liqCliente.getFechaVto3());//Vencimiento 3
				
				Date fechaAux = liqCliente.getFechaVto3();
				Calendar c = Calendar.getInstance();
				c.setTime(fechaAux);
				c.add(Calendar.DATE, 7);  // number of days to add
				fechaAux = c.getTime();
				fecha3 = dateFormat.format(fechaAux); //Vencimiento 3 + 7 dias
				fecha3Link = dateFormatLink.format(fechaAux);
				

				if (liqCliente.getResumenAterior() > 0) {

					detalle.append("5");

					String cuentaCli = liqCliente.getClienteTransaccion().getIdCliente().toString().trim();

					String cuentaCliente = (cuentaCli.length() > 3) ? cuentaCli : ("0000" + cuentaCli).substring(("0000" + cuentaCli).length() - 4);

					detalle.append(Convertidores.completarDerecha
							(cuentaCliente, new Character(' '), 19));

					detalle.append(Convertidores.completarDerecha
							(liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 20));
					detalle.append("0");
					detalle.append(fechaVencimiento);
					

					detalle.append(Convertidores.completarIzquierda
							(formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));
					detalle.append(fecha2);
					
					detalle.append(Convertidores.completarIzquierda
							(formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));

					detalle.append(fecha3);
					
					detalle.append(Convertidores.completarIzquierda
							(formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));
					detalle.append(Convertidores.completarDerecha("0", new Character('0'), 19));

					detalle.append(Convertidores.completarDerecha
							(cuentaCliente, new Character(' '), 19));

					detalle.append(Convertidores.completarDerecha
							("NRO CLIENTE " + liqCliente.getClienteTransaccion().getIdCliente().toString().trim() +
									" LIQUIDACION NR " + liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 40));
					detalle.append(Convertidores.completarDerecha
							("ID" + liqCliente.getClienteTransaccion().getIdCliente().toString().trim() + "-"
									+ liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 15));
					detalle.append(Convertidores.completarDerecha
							(liqCliente.getCodBarraDos().replaceAll("ƒcodBarrDos=", "").replaceAll("[*]", "").trim(), new Character(' '), 60));
					detalle.append(Convertidores.completarDerecha("0", new Character('0'), 29));
					++cantidadRegistro;
					totalImporte += liqCliente.getResumenAterior();
					acredList.add(detalle);

					// PROYECTO Red Link
					// Detalle del Archivo de RedLink

					detalleLink.append("0" + dateFormatMes.format(fCierre));
					detalleLink.append("001");
					detalleLink.append(Convertidores.completarIzquierda(liqCliente.getClienteTransaccion().
									getIdCliente().toString().trim(), new Character('0'), 7));
					detalleLink.append(fecha3Link);//Vto 3 + 7 dias
					
					detalleLink.append(Convertidores.completarIzquierda
							(formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 12));
					detalleLink.append(Convertidores.completarDerecha("0", new Character('0'), 6));
					detalleLink.append(Convertidores.completarDerecha("0", new Character('0'), 12));
					detalleLink.append(Convertidores.completarDerecha("0", new Character('0'), 6));
					detalleLink.append(Convertidores.completarDerecha("0", new Character('0'), 12));
					detalleLink.append(Convertidores.completarDerecha(" ", new Character(' '), 50));
					acredLinkList.add(detalleLink);
					// PROYECTO Red Link
				}
			}

			StringBuffer trailer = new StringBuffer(280);
			trailer.append("9");
			trailer.append("400");
			trailer.append("0321");
			trailer.append(dateFormat.format(fCierre));
			trailer.append(Convertidores.completarIzquierda
					(cantidadRegistro.toString(), new Character('0'), 7));
			trailer.append(Convertidores.completarDerecha("0", new Character('0'), 7));
			trailer.append(Convertidores.completarIzquierda
					(formateador.format(totalImporte).trim().replaceAll("[.]", ""), new Character('0'), 11));
			trailer.append(Convertidores.completarDerecha("0", new Character('0'), 239));
			acredList.add(trailer);
			log.info("Archivo de trailer " + trailer);

			// PROYECTO Red Link
			// Registro final de RedLink
			++cantidadRegistro;
			++cantidadRegistro;
			StringBuffer trailerLink = new StringBuffer(119);
			trailerLink.append("TRFACTURACION");
			trailerLink.append(Convertidores.completarIzquierda
					(cantidadRegistro.toString(), new Character('0'), 8));
			trailerLink.append(Convertidores.completarIzquierda
					(formateador.format(totalImporte).trim().replaceAll("[.]", ""), new Character('0'), 18));
			trailerLink.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			trailerLink.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			trailerLink.append(Convertidores.completarDerecha(" ", new Character(' '), 44));
			acredLinkList.add(trailerLink);

			// Registro inicial Archivo de Control
			StringBuffer inicialControl = new StringBuffer(75);
			String archivoRedLink = "";
			String archivoControl = "";
			inicialControl.append("HRPASCTRL");
			inicialControl.append(dateFormat.format(fCierre));
			inicialControl.append("AJ2");
			String mesDiaControl = dateFormatMesDia.format(fCierre);
			int valor = Integer.parseInt(mesDiaControl.substring(0, 2));
			switch (valor) {
			case 10:
				inicialControl.append("P" + "AJ2" + "1" + "A" + mesDiaControl.substring(2, 4));
				archivoRedLink = "P" + "AJ2" + "1" + "A" + mesDiaControl.substring(2, 4);
				archivoControl = "C" + "AJ2" + "1" + "A" + mesDiaControl.substring(2, 4);
				break;
			case 11:
				inicialControl.append("P" + "AJ2" + "1" + "B" + mesDiaControl.substring(2, 4));
				archivoRedLink = "P" + "AJ2" + "1" + "B" + mesDiaControl.substring(2, 4);
				archivoControl = "C" + "AJ2" + "1" + "B" + mesDiaControl.substring(2, 4);
				break;
			case 12:
				inicialControl.append("P" + "AJ2" + "1" + "C" + mesDiaControl.substring(2, 4));
				archivoRedLink = "P" + "AJ2" + "1" + "C" + mesDiaControl.substring(2, 4);
				archivoControl = "C" + "AJ2" + "1" + "C" + mesDiaControl.substring(2, 4);
				break;
			default:
				inicialControl.append("P" + "AJ2" + "1" + mesDiaControl.substring(1, 4));
				archivoRedLink = "P" + "AJ2" + "1" + mesDiaControl.substring(1, 4);
				archivoControl = "C" + "AJ2" + "1" + mesDiaControl.substring(1, 4);
			}
			Long tamanoArchivo = cantidadRegistro * 121;
			inicialControl.append(Convertidores.completarIzquierda
					(tamanoArchivo.toString(), new Character('0'), 10));
			inicialControl.append(Convertidores.completarDerecha(" ", new Character(' '), 37));
			acredControlLinkList.add(inicialControl);
			log.info("Archivo de control inicial " + inicialControl);

			// Registro Datos Archivo de Control
			StringBuffer datosControl = new StringBuffer(75);
			datosControl.append("LOTES");
			datosControl.append("00001");
			datosControl.append(Convertidores.completarIzquierda
					(cantidadRegistro.toString(), new Character('0'), 8));
			datosControl.append(Convertidores.completarIzquierda
					(formateador.format(totalImporte).trim().replaceAll("[.]", ""), new Character('0'), 18));
			datosControl.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			datosControl.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			datosControl.append(Convertidores.completarDerecha(" ", new Character(' '), 3));
			acredControlLinkList.add(datosControl);
			log.info("Archivo de control DATOS " + datosControl);

			// Registro Final Archivo de Control
			StringBuffer finalControl = new StringBuffer(75);
			finalControl.append("FINAL");
			finalControl.append(Convertidores.completarIzquierda
					(cantidadRegistro.toString(), new Character('0'), 8));
			finalControl.append(Convertidores.completarIzquierda
					(formateador.format(totalImporte).trim().replaceAll("[.]", ""), new Character('0'), 18));
			finalControl.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			finalControl.append(Convertidores.completarDerecha("0", new Character('0'), 18));
			finalControl.append(fecha3);
			acredControlLinkList.add(finalControl);
			log.info("Archivo de control DATOS " + finalControl);
			// PROYECTO Red Link

			grabarArchivo(acredList, fechaArchivo);

			grabarArchivoRedLink(acredLinkList, archivoRedLink);

			grabarArchivoControlRedLink(acredControlLinkList, archivoControl);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void generarArchivoPagoFacil(Date fechaCierre) {
		Format dateCabecera = new SimpleDateFormat("yyyyMMdd");
		String fechaArchivo = dateCabecera.format(fechaCierre);
		log.info("Generando archivo PF");
		List archivo = new ArrayList();

		try {
			List listaQuery = transaccionesService.getLiquidacionClientesService().getArchivoPF(fechaCierre);

			String cantRegistros = "" + listaQuery.size();
			StringBuffer cabecera = new StringBuffer(200);
			cabecera.append("01");
			cabecera.append(Convertidores.completarIzquierda(cantRegistros, '0', 9));
			cabecera.append("T90060181");
			cabecera.append(Convertidores.completarDerecha(fechaArchivo, ' ', 180));
			archivo.add(cabecera);
			// log.info(cabecera.toString());

			Iterator iter = listaQuery.iterator();
			while (iter.hasNext()) {
				ArchivoPF arch = (ArchivoPF) iter.next();
				StringBuffer linea = new StringBuffer(200);

				linea.append(arch.getTipoRegistro());
				linea.append(arch.getNroDocumento());
				linea.append(Convertidores.completarDerecha(arch.getCliente(), new Character(' '), 30));

				linea.append(Convertidores.completarDerecha(arch.getNroSecuencia(), new Character(' '), 26));

				linea.append(Convertidores.completarDerecha(arch.getNombre(), new Character(' '), 40));

				linea.append(arch.getCb01());
				linea.append(arch.getCb02());
				linea.append(arch.getCb03());
				linea.append(arch.getCb04());
				linea.append(arch.getCb0501());
				linea.append(arch.getCb0502());
				linea.append(arch.getCb0503());
				linea.append(arch.getCb0504());
				linea.append(arch.getCb0505());
				linea.append(arch.getCb0506());
			//	linea.append(Convertidores.completarDerecha(arch.getCb0506verificador(), new Character(' '), 24));
				linea.append(Convertidores.completarDerecha(arch.getCb0506verificador(), new Character(' '), 21));

				linea.append(arch.getFechaVigencia());
				linea.append(arch.getFechaVencimientoReg());
				linea.append(Convertidores.completarDerecha(arch.getTipoPago(), new Character(' '), 10));

				// log.info(linea.toString());
				archivo.add(linea);
			}

			grabarArchivoPagoFacil(archivo, fechaArchivo);

		} catch (LiquidacionClientesException e) {
			log.info("Error al generar el archivo PF");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void grabarArchivoPagoFacil(List lineasList, String fechaArchivo) {
		String key = "catalina.home";
		key = System.getProperty(key);
		PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		StringBuffer linea = null;
		try {
			File dir = null;
			File f = null;
			try {
				dir = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion"));
				if (!dir.exists()) {
					dir.mkdirs();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			f = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion") + "/pagoFacil/" + "90060181_" + fechaArchivo + ".ONL");
			// log.info(f.getName());
			if (f.exists()) {
				f.delete();
			}

			fw = new FileWriter(f);
			if (!lineasList.isEmpty()) {
				Iterator iter = lineasList.iterator();
				while (iter.hasNext()) {
					linea = (StringBuffer) iter.next();
					if (linea != null) {
						for (int i = linea.length(); i < 200; i++) {
							linea.append(" ");
						}
						linea.append("\r\n");
						fw.write(linea.toString());
					}
				}
			}
			fw.close();
		} catch (Exception e) {
			error.agregar("Error al generar el archivo");
			e.getMessage();
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void grabarArchivo(List lineasList, String fechaArchivo) {
		log.info("Graba Archivo de Facturacion ");

		String key = "catalina.home";
		key = System.getProperty(key);
		PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		StringBuffer linea = null;
		try {
			File dir = null;
			File f = null;
			try {
				dir = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion"));
				if (!dir.exists()) {
					dir.mkdirs();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			f = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion") + "/pagoMisCuentas/" + "FAC0321." + fechaArchivo);

			if (f.exists()) {
				f.delete();
			}

			fw = new FileWriter(f);
			if (!lineasList.isEmpty()) {
				Iterator iter = lineasList.iterator();
				while (iter.hasNext()) {
					linea = (StringBuffer) iter.next();
					if (linea != null) {
						for (int i = linea.length(); i < 280; i++) {
							linea.append(" ");
						}
						linea.append("\r\n");
						fw.write(linea.toString());
					}
				}
			}
			fw.close();
		} catch (Exception e) {
			error.agregar("Error al generar el archivo");
			e.getMessage();
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @id: 4510 Method: grabarArchivoRedLink Description: graba archivo de deuda Red Link en la carpeta directorioDeuda
	 * @param lineasList
	 * @param nombreArchivo
	 */

	private void grabarArchivoRedLink(List lineasList, String nombreArchivo) {
		log.info("Graba Archivo de Deuda RedLink ");

		String key = "catalina.home";
		key = System.getProperty(key);
		PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		StringBuffer linea = null;
		try {
			File dir = null;
			File f = null;
			try {
				dir = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion"));
				if (!dir.exists()) {
					dir.mkdirs();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			f = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion") + "/redLink/" + nombreArchivo);

			if (f.exists()) {
				f.delete();
			}

			fw = new FileWriter(f);
			if (!lineasList.isEmpty()) {
				Iterator iter = lineasList.iterator();
				while (iter.hasNext()) {
					linea = (StringBuffer) iter.next();
					if (linea != null) {
						for (int i = linea.length(); i < 119; i++) {
							linea.append(" ");
						}
						linea.append("\r\n");
						fw.write(linea.toString());
					}
				}
			}
			fw.close();
		} catch (Exception e) {
			error.agregar("Error al generar el archivo");
			e.getMessage();
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @id: 4510 Method: grabarArchivoControlRedLink Description: graba archivo de control Red Link en la carpeta directorioDeuda
	 * @param lineasList
	 * @param nombreArchivo
	 */
	private void grabarArchivoControlRedLink(List lineasList, String nombreArchivo) {
		log.info("Graba Archivo de Control RedLink ");

		String key = "catalina.home";
		key = System.getProperty(key);
		PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		StringBuffer linea = null;
		try {
			File dir = null;
			File f = null;
			try {
				dir = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion"));
				if (!dir.exists()) {
					dir.mkdirs();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			f = new File(key + "/" + "webapps" + prop.getProperties("directorioFacturacion") + "/redLink/" + nombreArchivo);

			if (f.exists()) {
				f.delete();
			}

			fw = new FileWriter(f);
			if (!lineasList.isEmpty()) {
				Iterator iter = lineasList.iterator();
				while (iter.hasNext()) {
					linea = (StringBuffer) iter.next();
					if (linea != null) {
						for (int i = linea.length(); i < 75; i++) {
							linea.append(" ");
						}
						linea.append("\r\n");
						fw.write(linea.toString());
					}
				}
			}
			fw.close();

		} catch (Exception e) {
			error.agregar("Error al generar el archivo");
			e.getMessage();
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @id 4268
	 * @descripcion Genera el cargo anual a los clientes que se le realiza la liquidacion
	 * @param oper
	 * @param idDeClientesALiquidar
	 * @throws ParametroSistemaException
	 * @throws ParseException
	 * @throws ConceptoException
	 * @throws Exception
	 */
	private void generarCargoAnual(ConceptoCuota conceptoCargoAnual, Operador oper, Long idDeClienteALiquidar)
			throws ParametroSistemaException, ParseException, ConceptoException, Exception {
		// Se verifica que la fecha de liquidación se encuentre dentro del rango configurado para el cálculo de este concepto
		log.info("**** Genera el cargo anual para el cliente: " + idDeClienteALiquidar + " ****");
		// Se genera el cargoAnual
		generarCargoAnual(conceptoCargoAnual, oper, idDeClienteALiquidar, parametrosCargoAnual, fechaCierreActual, montoCargoAnual);
	}


	/**
	 * @id: 4268 Method: obtenerConceptoCargoAnual Description: Obtiene el concepto de cargo anual y todos sus detalles (incluyendo la lista lista de
	 *      precio)
	 * @return
	 * @throws ConceptoException
	 * @throws Exception
	 */
	private ConceptoCuota obtenerConceptoCargoAnual() throws ConceptoException, Exception {
		ConceptoCuota conceptoCargoAnual;
		conceptoCargoAnual = new ConceptoCuota((Concepto) transaccionesService.getConceptoService()
				.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(Concepto.CODIGO_CARGO_ANUAL))).get(0));
		conceptoCargoAnual.armarConcepto();
		conceptoCargoAnual.armarReglaConcepto();
		conceptoCargoAnual.setCantidadCuotas(cantidadCuotas);
		montoCargoAnual = new Double(0);
		montoCargoAnual = conceptoCargoAnual.getMontoCargo(233L);
		Double porcentajeIva = transaccionesService.getCargoAnualService().getPorcentajeIva();
		conceptoCargoAnual.setPorcentajeIVA(porcentajeIva);
		// Calculamos el valor de las cuotas del cargo anual con su respectivo IVA
		Double montoCuota = Math.rint((montoCargoAnual / cantidadCuotas) * 100) / 100;
		for (int i = 0; i < cantidadCuotas; i++)
		{
			CuotaCargo cuotaCargo = new CuotaCargo(montoCuota, porcentajeIva, fechaCierreActual, i + 1);
			conceptoCargoAnual.addCuota(cuotaCargo);
		}

		return conceptoCargoAnual;
	}


	/**
	 * @id: 4268 Method: obtenerParametrosCargoAnual Description:
	 * @return
	 * @throws ParametroSistemaException
	 * @throws ParseException
	 */
	private ParametroSistema obtenerParametrosCargoAnual() throws ParametroSistemaException, ParseException {
		ParametroSistema fechaCargoFijo = generalService.getParametroSistemaService().leerParametroSistema(10L);
		fechaCargoFijo.getDetallesParametrosSistema();
		Format format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		fechaInicioDate = df.parse(fechaCargoFijo.getParametroSistemaDetalle(12L).getValor());
		log.info("La fecha inicial para el rango de cargo anual es: " + format.format(fechaInicioDate));
		fechaFinDate = df.parse(fechaCargoFijo.getParametroSistemaDetalle(13L).getValor());
		log.info("La fecha final para el rango de cargo anual es: " + format.format(fechaFinDate));
		fechaAltaHasta = df.parse(fechaCargoFijo.getParametroSistemaDetalle(25L).getValor());
		log.info("Fecha Limite de alta de clientes que entran al cargo anual es: " + format.format(fechaAltaHasta));
		cantDiasAnteriores = Long.parseLong(fechaCargoFijo.getParametroSistemaDetalle(14L).getValor());
		log.info("La cantidad de días para no volver a incluir el Cargo Anual son: " + cantDiasAnteriores);
		cantidadCuotas = Long.parseLong(fechaCargoFijo.getParametroSistemaDetalle(15L).getValor());
		log.info("La cantidad de cuotas del Cargo Anual son: " + cantidadCuotas);
		return fechaCargoFijo;
	}


	public BigDecimal impactarSellos(Concepto conceptoSellos, Long idCliente, LiqCliente liqCliente, BigDecimal totalCliente) throws Exception {
		BigDecimal totalDeSellos = new BigDecimal(0);
		BigDecimal sello = new BigDecimal(0);
		Set detallesConcepto = conceptoSellos.getConceptoDetalleSet();
		Iterator iter = detallesConcepto.iterator();
		Long idTransaccionParaSellos = transaccionesService.getCtaCteClienteService().getSequenciaTransaccion();
		while (iter.hasNext()) {
			// Cargo por Seguro de vida: aqui tengo que enviarle el id del cliente....
			ConceptoDetalle det = (ConceptoDetalle) iter.next();
			Set detallesPagos = conceptoSellos.getListaPrecio(idCliente).getVersionVigente().getDetallesListaPrecio();
			Iterator listaPagos = detallesPagos.iterator();

			while (listaPagos.hasNext()) {
				ListaPrecioDetalle var = (ListaPrecioDetalle) listaPagos.next();
				if (det.getOrden().intValue() == var.getOrdenImputacion()) {

					sello = totalCliente.multiply(BigDecimal.valueOf(var.getPorcentaje().doubleValue() / new Double(100).doubleValue()));
					totalDeSellos = totalDeSellos.add(sello);
					CtaCteClienteAux ctaCteClienteAux = new CtaCteClienteAux();
					ctaCteClienteAux.setEstadoImpacto("C");
					ctaCteClienteAux.setCtacontabledebe(String.valueOf(det.getCtacontabledebe()));
					ctaCteClienteAux.setCtacontablehaber(String.valueOf(det.getCtacontablehaber()));
					ctaCteClienteAux.setFechaContable(new Timestamp(liqCliente.getFechaCierre().getTime()));
					ctaCteClienteAux.setFechaFacturacion(new Timestamp(liqCliente.getFechaCierre().getTime()));
					ctaCteClienteAux.setIdConceptoDetalle(new Integer(det.getIdConceptoDetalle().toString()));
					ctaCteClienteAux.setIdCliente(idCliente); // PRUEBA - CORREGIR!!!! Corregido!!!!!!!!!!!
					ctaCteClienteAux.setIdOperador(new Long(1));
					ctaCteClienteAux.setIdOrigen(new Long(1));
					ctaCteClienteAux.setIdParent(new Long(1));

					ctaCteClienteAux.setIdTranascciones(idTransaccionParaSellos);
					// lo de tratar los decimales, debe dividirse por 100 antes....

					ctaCteClienteAux.setImporte(sello);
					ctaCteClienteAux.setNroCuota(new Integer(1));
					ctaCteClienteAux.setContabilizado("N");
					ctaCteClienteAux.setSigno(new Integer(1));
					ctaCteClienteAux.setTimestamp(new Timestamp(liqCliente.getFechaCierre().getTime()));
					ctaCteClienteAux.setIdLiqCliente(liqCliente.getIdLiqCliente());
					transaccionesService.getCtaCteClienteAuxService().grabarCtaCteClienteAux(ctaCteClienteAux);
					break;
				}
			}

		}
		return totalDeSellos;

	}


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
			String manzana = "MANZANA INCOMPLETO";
			String generico = "GENERICO INCOMPLETO";
			String barrio = "BARRIO INCOMPLETO";
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
							+ ((dom.getOrientacion() != null) ? dom.getOrientacion() : "");

					if (dom.getManzana() != null)
						manzana = manzana.replace("MANZANA INCOMPLETO", "") + " Mz. " + dom.getManzana();
					if (dom.getMonoblock() != null)
						manzana = manzana.replace("MANZANA INCOMPLETO", "") + " Mb. " + dom.getMonoblock();
					if (dom.getPiso() != null)
						manzana = manzana.replace("MANZANA INCOMPLETO", "") + " Piso " + dom.getPiso();
					if (dom.getDepto() != null && !dom.getDepto().trim().equals(""))
						manzana = manzana.replace("MANZANA INCOMPLETO", "") + " Dpto. " + dom.getDepto().trim();
					if (dom.getAreaSector() != null && !dom.getAreaSector().trim().equals(""))
						manzana = manzana.replace("MANZANA INCOMPLETO", "") + " Area. " + dom.getAreaSector().trim();

					if (dom.getGenerico() != null && !(dom.getGenerico().trim().equals("-")))
						generico = "(" + dom.getGenerico().trim() + ")";

					barrio = dom.getBarrio().getDescripcion().trim().replace("-", "");

					if (manzana.trim().equals("MANZANA INCOMPLETO") && (barrio.trim().equals(""))) {
						manzana = ((dom.getLocalidad().getNombre().trim().equals("-")) ? "" : dom.getLocalidad().getNombre().trim()) + " "
								+ dom.getCodigoPostal().trim();
						localidad = dom.getLocalidad().getPartido().getDescripcion().trim() + " " + dom.getLocalidad().getProvincia().getNombre();
						provincia = "PROVINCIA INCOMPLETO";
					} else if (manzana.trim().equals("MANZANA INCOMPLETO") && !(barrio.trim().equals(""))) {
						manzana = "B. " + barrio;
						localidad = ((dom.getLocalidad().getNombre().trim().equals("-")) ? "" : dom.getLocalidad().getNombre().trim()) + " "
								+ dom.getCodigoPostal().trim();

						
						provincia = dom.getLocalidad().getPartido().getDescripcion().trim() + " " + dom.getLocalidad().getProvincia().getNombre();
					} else if (!(manzana.trim().equals("MANZANA INCOMPLETO")) && (barrio.trim().equals(""))) {
						localidad = ((dom.getLocalidad().getNombre().trim().equals("-")) ? "" : dom.getLocalidad().getNombre().trim()) + " "
								+ dom.getCodigoPostal().trim();
						provincia = dom.getLocalidad().getPartido().getDescripcion().trim() + " " + dom.getLocalidad().getProvincia().getNombre();

					} else {
						localidad = "B. " + barrio;
						provincia = ((dom.getLocalidad().getNombre().trim().equals("-")) ? "" : dom.getLocalidad().getNombre().trim()) + " "
								+ dom.getCodigoPostal().trim() + " " + dom.getLocalidad().getPartido().getDescripcion().trim() + " "
								+ dom.getLocalidad().getProvincia().getNombre();
					}

					if (provincia.trim().equals("PROVINCIA INCOMPLETO") && !(generico.trim().equals("GENERICO INCOMPLETO"))) {
						provincia = generico;
						generico = "GENERICO INCOMPLETO";

					}

					codPost = dom.getCpa2().toString();
				}
				else
				{
					domicilio = clienteT.getDomicilio();
					manzana = clienteT.getLocalidad();
					localidad = clienteT.getCodPost();
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

//			String revista = "";
//
//			if (transaccionesService.getGestorLiquidacionClienteService().clienteTieneRevista(idCliente.intValue(),
//					liquidacionClientesEditada.getFechaCierrePeriodo()))
//			{
//				revista = "S";
//				//revista = "R";
//				// nombreLiq = rutaRelativa + File.separator + revista +codPost +
//				// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//				// nombreLiqReturn = directorioRelativoLiquidacion + File.separator + revista + codPost +
//				// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//
//				nombreLiq = rutaRelativa + File.separator + "whatsapp" + File.separator  + codPost
//						+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//				nombreLiqReturn = directorioRelativoLiquidacion + File.separator + "whatsapp" + File.separator  + codPost
//						+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//
//			} else {
//				revista = "S";
//				// nombreLiq = rutaRelativa + File.separator +codPost +
//				// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//				// nombreLiqReturn = directorioRelativoLiquidacion + File.separator + codPost +
//				// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//
//				if ( transaccionesService.getGestorLiquidacionClienteService().noPoseeMail(idCliente.intValue()))
//				{
//					nombreLiq = rutaRelativa + File.separator + codPost
//							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//					nombreLiqReturn = directorioRelativoLiquidacion + File.separator + codPost
//							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//
//				} else {
//					nombreLiq = rutaRelativa + File.separator + "emails" + File.separator + codPost
//							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//					nombreLiqReturn = directorioRelativoLiquidacion + File.separator + "emails" + File.separator + codPost
//							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
//				}
//			}
			
			String revista = "";
            if (LiquidacionClientesBean.transaccionesService.getGestorLiquidacionClienteService().clienteTieneRevista(idCliente.intValue(), 
			this.liquidacionClientesEditada.getFechaCierrePeriodo())) {
                revista = "S";
        		nombreLiq = rutaRelativa + File.separator + "whatsapp" + File.separator  + codPost
						+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
				nombreLiqReturn = directorioRelativoLiquidacion + File.separator + "whatsapp" + File.separator  + codPost
						+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();

            }
            else {
                revista = "S";
                if (LiquidacionClientesBean.transaccionesService.getGestorLiquidacionClienteService().enviaResumenImpreso(idCliente.intValue()) 
				|| LiquidacionClientesBean.transaccionesService.getGestorLiquidacionClienteService().noPoseeMail(idCliente.intValue())) {
                	nombreLiq = rutaRelativa + File.separator + codPost
							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
					nombreLiqReturn = directorioRelativoLiquidacion + File.separator + codPost
							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
              }
                else {
                	nombreLiq = rutaRelativa + File.separator + "emails" + File.separator + codPost
							+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
					nombreLiqReturn = directorioRelativoLiquidacion + File.separator + "emails" + File.separator + codPost
						+ formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
				}
            }

			log.info("nombreLiq " + nombreLiq);

			// String revista = "";
			//
			// if (transaccionesService.getGestorLiquidacionClienteService().clienteTieneRevista(idCliente.intValue(),
			// liquidacionClientesEditada.getFechaCierrePeriodo()))
			// {
			// revista = "R";
			// nombreLiq = rutaRelativa + File.separator + revista +codPost +
			// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
			// nombreLiqReturn = directorioRelativoLiquidacion + File.separator + revista + codPost +
			// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
			//
			// } else {
			// revista = "S";
			// nombreLiq = rutaRelativa + File.separator +codPost +
			// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
			// nombreLiqReturn = directorioRelativoLiquidacion + File.separator + codPost +
			// formatoFecha.format(liquidacionCliente.getLiquidacionClientes().getFechaLiquidacion()) + "_" + clienteT.getIdCliente();
			// }
			//
			// log.info("nombreLiq " + nombreLiq);

			String p11 = "ƒrevista=" + revista;
			
			String p0 ="";
			
			
			if (comunicado) {
				p0 = "?guardarEn=" + nombreLiq.trim()+"E";
				
			} else {
				 p0 = "?guardarEn=" + nombreLiq;
				
			}
				
			
			String p1 = "ƒliquidacion_numero=" + liquidacionCliente.getIdLiqCliente();
			String p2 = "ƒcuenta_nro=" + liquidacionCliente.getClienteTransaccion().getIdCliente();
			String p3 = "ƒnombre=" + nombre;
			String p4 = "ƒdireccion=" + domicilio;
			String p5 = "ƒdescripcion=" + localidad;
			String p6 = "ƒcp=" + cp;
			String p7 = "ƒciudad=" + provincia;

			String p20 = "ƒmanzana=" + manzana;
			String p21 = "ƒgenerico=" + generico;


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

			parametros += "ƒdesc1=" + "Int.Compensatorios" ;//+ "ƒtna1=" + liquidacionCliente.getIntComp();
			parametros += "ƒdesc2=" + "Int.Punitorio" ;//+ "ƒtna2=" + liquidacionCliente.getIntPunitorios();

			String cierresyVencimientos = "ƒcierre1=" + sdf.format(fechaCierreAnterior);
			cierresyVencimientos += "ƒcierre2=" + sdf.format(fechaCierreActual);
			cierresyVencimientos += "ƒcierre3=" + sdf.format(fechaCierreProximo);
			cierresyVencimientos += "ƒvencim1=" + sdf.format(fechaVencimientoAnterior);
			cierresyVencimientos += "ƒvencim2=" + sdf.format(fechaPrimerVencimiento);
			cierresyVencimientos += "ƒvencim3=" + sdf.format(fechaVencimientoProximo);

			String lineaCredito = "";

			BigDecimal impoteAxu = transaccionesService.getGestorLiquidacionClienteService()
					.getImportesAgregadosPorLiquidacion(liquidacionCliente.getIdLiqCliente()).setScale(BigDecimal.ROUND_HALF_DOWN, 2);

			lineaCredito += "ƒcredito=" + lineaDeCredito.setScale(BigDecimal.ROUND_HALF_DOWN, 2) + "ƒsaldo="
					+ saldoTotal.setScale(BigDecimal.ROUND_HALF_DOWN, 2).add(impoteAxu) + "ƒdisponible="
					+ disponible.setScale(BigDecimal.ROUND_HALF_DOWN, 2).add(impoteAxu.negate());

			// cal.setTime(liquidacionCliente.getFechaCierreAnterior());
			String futurosVencimientos = "";
			try {
				SimpleDateFormat simpleDateFormatF = new SimpleDateFormat("dd/MM/yyyy");
				int numeroDeVencimientos = 0;
				Iterator iterVencimientos = transaccionesService.getClienteTransaccionService()
						.buscarFuturosVencimientoResumenClentes(liquidacionCliente.getClienteTransaccion(), fechaCierreActual).iterator();
				while (iterVencimientos.hasNext()) {
					FuturosVencimientos futVenc = (FuturosVencimientos) iterVencimientos.next();
					numeroDeVencimientos++;
					futurosVencimientos += "ƒf_ven_" + numeroDeVencimientos + "="
							+ simpleDateFormatF.format(addDiasFechaLaborable(simpleDateFormatF.parse(futVenc.getFecha()), 15)) + "ƒm_ven_"
							+ numeroDeVencimientos + "=" + new BigDecimal(futVenc.getMonto());
				}
				

				if (numeroDeVencimientos == 0) {
					numeroDeVencimientos++;
					futurosVencimientos += "ƒf_ven_" + numeroDeVencimientos + "=" + simpleDateFormatF.format(fechaVencimientoProximo) + "ƒm_ven_"
							+ numeroDeVencimientos + "=" + new BigDecimal(0);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar los pagos del Cliente.");
			}

			// COMENTADO FUTUROS VENCIMIENTOS
			// try {
			// for (int h=1; h<7;h++) {
			// // lo siguiente puede arrojar un null pointer exception.... CONTROLAR!!!!
			// Iterator iter = liquidacionCliente.getLiqClienteFuturosVencimientos().iterator();
			// while (iter.hasNext()) {
			// LiqClienteFuturoVencimiento futVen = (LiqClienteFuturoVencimiento)iter.next();
			// if (futVen.getOrden().intValue()==h) {
			// //cal.setTime(futVen.getFecha());
			// switch (h) {
			// case 1:
			// // futurosVencimientos += "ƒf_ven_1="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_1="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// case 2:
			// // futurosVencimientos += "ƒf_ven_2="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_2="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// case 3:
			// //futurosVencimientos += "ƒf_ven_3="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_3="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// case 4:
			// //futurosVencimientos += "ƒf_ven_4="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_4="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// case 5:
			// //futurosVencimientos += "ƒf_ven_5="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_5="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// case 6:
			// //futurosVencimientos += "ƒf_ven_6="+ cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) +
			// "ƒm_ven_6="+futVen.getMonto().setScale(BigDecimal.ROUND_HALF_DOWN, 2);
			// break;
			// }
			// break;
			// }
			// }
			// }
			// } catch (NullPointerException e) {
			// // nada, es que la liquidacion tenia monto cero, por lo tanto no hay futuros vencimientos.
			// } catch (Exception e) {
			// e.printStackTrace();
			// throw new
			// GestorLiquidacionClienteException("Generacion del documento PDF: A ocurrido un error al intentar armar los futuros vencimientos.");
			// }

			String repactaciones = "";
			try {
				Object[] liqRepactacion = liquidacionCliente.getLiqClienteRepactacion().toArray();

				Arrays.sort(liqRepactacion, new Comparator() {
					@Override
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
				repactaciones += "ƒ2cuota=" + ((LiqClienteRepactacion) liqRepactacion[0]).getMontoCuota(); //12 cutas
				repactaciones += "ƒ3cuota=" + ((LiqClienteRepactacion) liqRepactacion[1]).getMontoCuota(); //6 cuotas
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
			//int anio = 0;
			//int dias = 0;
			//int diasEntreVencimiento = 0;
			String totalAPagar;

			try {

				pagos += "ƒf_1_ven=" + sdf.format(fechaPrimerVencimiento);
				lineaCredito += "ƒtotal_resumen=" + liquidacionCliente.getMontoTotal();
				Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				lineaCredito += "ƒfechaEspecial=" + dateFormat.format(liquidacionCliente.getFechaCierre());
		//		lineaCredito += "fechaResumenAnterior=" + dateFormat.format(fechaCierreAnterior);

				// importePagoFacil = liquidacionCliente.getMontoTotal().add(new
				// BigDecimal(liquidacionCliente.getMontoAdeudado())).setScale(BigDecimal.ROUND_HALF_DOWN, 2);

				/* ResumenAnterior en el importe total a pagar por el cliente */
				importePagoFacil = new BigDecimal(liquidacionCliente.getResumenAterior()).setScale(BigDecimal.ROUND_HALF_DOWN, 2);

				totalAPagar = "ƒtotal_pagar=" + importePagoFacil;
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
				//ListaPrecioDetalle mensaje = new ListaPrecioDetalle();
				Iterator listaParamGen = detall.iterator();
				while (listaParamGen.hasNext()) {
					ListaPrecioDetalle var = (ListaPrecioDetalle) listaParamGen.next();
					switch (cons) {
					case 1:
						mensaj += "ƒmensaje1=" + var.getDescripcion();
						break;
					case 2:
						mensaj += "ƒmensaje2=" + var.getDescripcion();
						break;
					case 3:
						// mensaj += "ƒmensaje3=" + var.getDescripcion();
						break;
					}
					cons++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new GestorLiquidacionClienteException(
						"Generacion del documento PDF: A ocurrido un error al intentar armar los mensajes del Cliente. Verifique la existencia del concepto 18 (Mensajes) y que este posea asociado su lista de precios. Se deben encontrar en vigencia.");
			}

			if (clienteT.getIdPlanMora() != null) {
				mensaj += "ƒmensaje3=" + "Registramos un saldo impago en su cuenta. Por favor regularice su situación";
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
			// modificado para que muestre el id del cliente en el codigo de barra es para oca
			// codigoBarra = "*11" + ("00000000" + liquidacionCliente.getIdLiqCliente()).substring(("00000000" +
			// liquidacionCliente.getIdLiqCliente()).length()-8) + "0*";
			// codigoBarra = "*" + ("00000000000" + idCliente).substring(("00000000000" + idCliente).length()-11) + "*";
			codigoBarra = "*" + ("000000" + idCliente).substring(("000000" + idCliente).length() - 6) + "*";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
			// String fecha = fecha3.substring(0,2)+ fecha3.substring(3,5)+ fecha3.substring(6,8);

			// fechaPrimerVencimiento
			// String fecha = simpleDateFormat.format(fechaTercerVencimiento);
			// es solo prueba poner la de arriba fechaTercerVencimiento

			Long documentoTitular = new Long(clienteT.getIndividuo().getNroDocumento());
			String documentoT = ("00000000" + documentoTitular).substring(("00000000" + documentoTitular).length() - 8);

			// String fecha = simpleDateFormat.format(fechaTercerVencimiento);
			String fecha = simpleDateFormat.format(addDiasFechaLaborable(fechaPrimerVencimiento, 25));
			// coloar esto
			// codigoBarraDos = "0181" + ("000000" + idCliente).substring(("000000" + idCliente).length()-6) + fecha + importePF;
			// codigoBarraDos = "0181" + documentoT + ("000000" + idCliente).substring(("000000" + idCliente).length()-6) + fecha + importePF;
			codigoBarraDos = "0321" + documentoT + ("000000" + idCliente).substring(("000000" + idCliente).length() - 6) + importePF + fecha;
			// codigoBarraDos = "*" + getConDigito(codigoBarraDos) + "*";
			codigoBarraDos = getConDigito(codigoBarraDos);
			
			// TRABAJAMOS CON EL CODIGOBARRATRES CON EL CVU
			codigoBarraTres = transaccionesService.getGestorLiquidacionClienteService().buscarCVUdelCliente(idCliente.intValue());
			log.info(" codigoBarraTres " + codigoBarraTres);
			if (codigoBarraTres == null ) {
				codigoBarraTres = transaccionesService.getGestorLiquidacionClienteService().buscarNuevoCVUdelCliente(idCliente.intValue());
				log.info(" codigoBarraTres nuevo " + codigoBarraTres);	
				transaccionesService.getGestorLiquidacionClienteService().actualizarClienteEnCVU(idCliente.intValue(),codigoBarraTres,"Resumen");
				
				log.info(" codigoBarraTres grabado " + codigoBarraTres);
				
			} else {
				
				transaccionesService.getGestorLiquidacionClienteService().UpdateClienteEnCVU(idCliente.intValue(),codigoBarraTres);
				
				log.info(" codigoBarraTres actualizado " + codigoBarraTres);
				
			}
				

//			codigoBarraTres = fecha + importePF + fecha + importePF;
//			// codigoBarraTres = "*" + getConDigito(codigoBarraTres) + "*";
//			codigoBarraTres = getConDigito(codigoBarraTres);

			String codBarr = "ƒcodBarr=" + codigoBarra;
			String codBarrDos = "ƒcodBarrDos=" + codigoBarraDos;
			String codBarrTres = "ƒcodBarrTres=" + codigoBarraTres;
			liquidacionCliente.setCodBarraUno(codBarr);
			liquidacionCliente.setCodBarraDos(codBarrDos);
			liquidacionCliente.setCodBarraTres(codBarrTres);

			// String p8 = "ƒURLLogo1="+Session.getHomePath()+"/img/fiel/rapipago.jpg";
			// String p8 = "ƒURLLogo1="+Session.getHomePath()+"/img/fiel/datos.png";
			String p8 = "ƒURLLogo1=" + Session.getHomePath() + "/img/fiel/artesaniaresumen.jpg";

			// String p8 = "ƒURLLogo1="+Session.getHomePath()+"/img/fiel/Direct_TV_Logo.jpg";
			String p9 = "ƒURLLogo2=" + Session.getHomePath() + "/img/fiel/banelco.jpg";
			// String p10 = "ƒURLLogo3="+Session.getHomePath()+"/img/fiel/red_link.jpg";
			// String p10 = "ƒURLLogo3="+Session.getHomePath()+"/img/fiel/publicidadapp.png";
			String p10 = "ƒURLLogo3=" + Session.getHomePath() + "/img/fiel/publicidadapp.jpg";

			String p12 = "ƒURLFeria=" + Session.getHomePath() + "/img/fiel/pagofacil.jpg";

			// String page = request.getContextPath()+"/report/LiquidacionClientes.jrxml";
			String page = request.getContextPath() + "/report/LiquidacionClientesBuena.jrxml";

			// String page = request.getContextPath()+"/report/LiquidacionClientesVencimiento.jrxml";
			
			
			GeneradorDeInforme gen = new GeneradorDeInforme();
			try {
				
				// log.info(page+p0+p1+p2+p3+p4+p5+p6+p7+ parametros+lineaCredito+codBarr+codBarrDos+codBarrTres+adicionales+
				// cierresyVencimientos+futurosVencimientos+repactaciones+pagos+ mensaj);
				gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p20 + p21 + totalAPagar + parametros
						+ lineaCredito + codBarr + codBarrDos + codBarrTres + adicionales + cierresyVencimientos + futurosVencimientos + tasas
						+ repactaciones + pagos + mensaj);
				
				/// ----------------------------------------------
				// UNIR PDF AL PDF DE COMINICACION
				/// ----------------------------------------------				 
				
				if (comunicado) {
					try {
						
						log.info("Entra a Concatenar pdf ");
						
						String key = "catalina.home";
						key = System.getProperty(key);
						PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
						
						List<PdfReader> pdfs = new ArrayList<PdfReader>();
						String nombrePdfLiq = " ";
						//File archivoLiq = new File(nombreLiq.trim()+".pdf");
						log.info("Concatenar pdf ");
						nombrePdfLiq = nombreLiq.trim()+"E.pdf";
						pdfs.add(new PdfReader(nombrePdfLiq));
						log.info("nombrePdfLiq " + nombrePdfLiq );
						String comunicacion =  System.getProperty("catalina.home") + prop.getProperties("directorioArchivos")
								+ File.separator + "comunicacion" +  File.separator +"comunicacion.pdf";
						log.info("comunicacion " + comunicacion );
						pdfs.add(new PdfReader(comunicacion));
						MergePDF.concatPDFs(pdfs, nombreLiq.trim()+".pdf");
						log.info("terminia Concatenar pdf ");
						
						
						
					}  catch (Exception e) {
						throw new GestorLiquidacionClienteException("Generacion del documento PDF: No se pudo agregar la comunicacion");
					}
					
				}
				
				if (comunicado) {
					try {
						
						log.info("terminia1 Concatenar pdf ");
						
						File file = new File(nombreLiq.trim()+"E.pdf");
						file.delete();
						
					}  catch (Exception e) {
						throw new GestorLiquidacionClienteException("Generacion del documento PDF: No se pudo borrar el archivo " + nombreLiq.trim()+"E.pdf");
					}
					
				}			
				

				File fileAws = null;
				try {
					SimpleDateFormat formatoFechaS3 = new SimpleDateFormat("dd-MM-yyyy");
					String bucket = "liquidacionesClientes/"+formatoFechaS3.format(fechaCierreActual);

				String	 fileAmazonCliente = nombreLiq.trim()+".pdf";
				
				fileAws = new File(fileAmazonCliente);
		    	   
					
					
						
					Map<String, String> headers = new HashMap<String, String>();
			    	     			    	    
			    	    HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.76:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
   
			    	    multipart.addFormField("bucket", bucket);
			    	    // Add file
			    	  //  multipart.addFilePart("file", new File(fileAmazonCliente));
			    	    multipart.addFilePart("file", fileAws);
			    	    // Print result
			    	    String response = multipart.finish();
			    	    System.out.println(response); 
			    	    log.info("archivo pdf subido al Amazon: " + fileAmazonCliente);
				
				
			    	    
			    	    
					} catch (Exception e) {
						log.info("Error en subir archivo al Amazon: " + fechaInicioDate);
						
						// TODO Auto-generated catch block
						e.printStackTrace();
			
					}
				try {
				
				if ( fileAws != null) {
					fileAws.delete();
				}
				
				} catch (Exception e) {
					log.info("Error al borrar el archivo en el servidor 7");
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
								
			} catch (JRException e) {
				throw new GestorLiquidacionClienteException("JRException del documento PDF: A ocurrido un error al intentar grabar el archivo.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				throw new GestorLiquidacionClienteException("Exception del documento PDF: A ocurrido un error al intentar grabar el archivo.");
			}
		} catch (ClienteTransaccionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return nombreLiqReturn;
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
		while (generalService.getNoLaborableDao().esNoLaborable(new Timestamp(fechaOriginal.getTime().getTime()))) {
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


	/**
	 * @id:4268 Método que impacta el cargo anual
	 */
	public Boolean generarCargoAnual(ConceptoCuota concepto, Operador operador, Long idCliente, ParametroSistema parametro, Date fechaCierre,
			Double montoCargoAnual)
	{
		try {
			transaccionesService.getCargoAnualService().registrarCargoAnual(fechaCierre, concepto, operador, idCliente, parametro, montoCargoAnual);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
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
	 * Metodo que impacta el Servicio Postal.
	 * */
	public boolean generarServicioPostal(ClienteLiquidacion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente) {
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
	 * I6802 Metodo que impacta el cargo eco.
	 * */
	public boolean generarCargoEco(ClienteLiquidacion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente) {
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
	 * Metodo que impacta el interes por mora.
	 * */
	public boolean generarInteresMora(ClienteLiquidacion cliente, Concepto concepto, Operador operador, LiqCliente liqCliente, double importe) {
		try {
			transaccionesService.getInteresMoraService().registrarInteresMora(cliente, concepto, operador, importe, liqCliente);
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


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public HtmlSelectOneMenu getFechaLiquidacionSeleccionada() {
		return fechaLiquidacionSeleccionada;
	}


	public void setFechaLiquidacionSeleccionada(
			HtmlSelectOneMenu fechaLiquidacionSeleccionada) {
		this.fechaLiquidacionSeleccionada = fechaLiquidacionSeleccionada;
	}


	public List getListaFechasPosiblesLiq() {
		return listaFechasPosiblesLiq;
	}


	public void setListaFechasPosiblesLiq(List listaFechasPosiblesLiq) {
		this.listaFechasPosiblesLiq = listaFechasPosiblesLiq;
	}


	public String getIdFechaLiquidacionSeleccionada() {
		return idFechaLiquidacionSeleccionada;
	}


	public void setIdFechaLiquidacionSeleccionada(
			String idFechaLiquidacionSeleccionada) {
		this.idFechaLiquidacionSeleccionada = idFechaLiquidacionSeleccionada;
	}


	public LiquidacionClientes getLiquidacionClientesEditada() {
		return liquidacionClientesEditada;
	}


	public void setLiquidacionClientesEditada(
			LiquidacionClientes liquidacionClientesEditada) {
		this.liquidacionClientesEditada = liquidacionClientesEditada;
	}


	public HtmlSelectOneMenu getImpresoraSeleccionada() {
		return impresoraSeleccionada;
	}


	public void setImpresoraSeleccionada(HtmlSelectOneMenu impresoraSeleccionada) {
		this.impresoraSeleccionada = impresoraSeleccionada;
	}


	public Long getIdImpresoraSeleccionada() {
		return idImpresoraSeleccionada;
	}


	public void setIdImpresoraSeleccionada(Long idImpresoraSeleccionada) {
		this.idImpresoraSeleccionada = idImpresoraSeleccionada;
	}


	public List getListaImpresorasDisponibles() {
		return listaImpresorasDisponibles;
	}


	public void setListaImpresorasDisponibles(List listaImpresorasDisponibles) {
		this.listaImpresorasDisponibles = listaImpresorasDisponibles;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getInicio() {
		return inicio;
	}


	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}


	public List getListaPreliquidacionesWrapper() {
		return listaPreliquidacionesWrapper;
	}


	public void setListaPreliquidacionesWrapper(List listaPreliquidacionesWrapper) {
		this.listaPreliquidacionesWrapper = listaPreliquidacionesWrapper;
	}

	public class PreliquidacionWrapper {

		LiquidacionClientes liquidacionCliente;


		public PreliquidacionWrapper(LiquidacionClientes liquidacionCliente) {
			this.liquidacionCliente = liquidacionCliente;
		}


		public void confirmarLiquidacion() {
			if (liquidacionCliente.getConfirmada().compareTo("N") == 0 && liquidacionCliente.getFinalizo().compareTo("S") == 0) {
				// paso la tabla auxiliar de t_vis_tra_ctacte_cliente_aux a t_vis_tra_cta_cte_cliente
				try {

					// // PROYECTO 4029 PAGOMISCUENTAS
					//
					// log.info("Comienzo la generación del archivo de facturacion");
					// Object[] liqClientes = liquidacionCliente.getLiqClientes().toArray();
					// Format dateFile = new SimpleDateFormat("ddMMyy");
					// Format dateFormat = new SimpleDateFormat("yyyyMMdd");
					//
					// DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
					// simbolos.setDecimalSeparator('.');
					// DecimalFormat formateador = new DecimalFormat("#######0.00",simbolos);
					// formateador.setMinimumFractionDigits(2);
					//
					// List acredList = new ArrayList();
					// StringBuffer cabezera = new StringBuffer(280);
					// cabezera.append("0");
					// cabezera.append("400");
					// cabezera.append("0321");
					//
					// cabezera.append(dateFormat.format(((LiqCliente)liqClientes[0]).getFechaCierre()));
					// String fechaArchivo = dateFile.format(((LiqCliente)liqClientes[0]).getFechaCierre());
					// cabezera.append(Convertidores.completarDerecha("0", new Character('0'), 264));
					// acredList.add(cabezera);
					// log.info("Archivo de Cabezera " + cabezera);
					// Long cantidadRegistro = new Long(0);
					// Double totalImporte = 0D;
					//
					// Iterator iter = liquidacionCliente.getLiqClientes().iterator();
					// while (iter.hasNext()) {
					// LiqCliente liqCliente = (LiqCliente)iter.next();
					// StringBuffer detalle = new StringBuffer(280);
					// if (liqCliente.getResumenAterior() > 0) {
					//
					// detalle.append("5");
					//
					//
					// detalle.append(Convertidores.completarDerecha
					// (liqCliente.getClienteTransaccion().
					// getIdCliente().toString().trim(), new Character(' '), 19));
					//
					//
					//
					//
					// detalle.append(Convertidores.completarDerecha
					// (liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 20));
					// detalle.append("0");
					// detalle.append(dateFormat.format(liqCliente.getFechaVto3()));
					// detalle.append(Convertidores.completarIzquierda
					// (formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));
					// detalle.append(dateFormat.format(liqCliente.getFechaVto3()));
					// detalle.append(Convertidores.completarIzquierda
					// (formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));
					//
					// detalle.append(dateFormat.format(liqCliente.getFechaVto3()));
					// detalle.append(Convertidores.completarIzquierda
					// (formateador.format(liqCliente.getResumenAterior()).trim().replaceAll("[.]", ""), new Character('0'), 11));
					// detalle.append(Convertidores.completarDerecha("0", new Character('0'), 19));
					//
					// detalle.append(Convertidores.completarDerecha
					// (liqCliente.getClienteTransaccion().
					// getIdCliente().toString().trim(), new Character(' '), 19));
					//
					// detalle.append(Convertidores.completarDerecha
					// ("NRO CLIENTE " + liqCliente.getClienteTransaccion().getIdCliente().toString().trim() +
					// " LIQUIDACION NRO "+liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 40));
					// detalle.append(Convertidores.completarDerecha
					// ("ID"+liqCliente.getClienteTransaccion().getIdCliente().toString().trim() + "-" +
					// liqCliente.getIdLiqCliente().toString().trim(), new Character(' '), 15));
					// detalle.append(Convertidores.completarDerecha
					// (liqCliente.getCodBarraDos().replaceAll("ƒcodBarrDos=", "").replaceAll("[*]", "").trim(), new Character(' '), 60));
					// detalle.append(Convertidores.completarDerecha("0", new Character('0'), 29));
					// ++cantidadRegistro;
					// totalImporte += liqCliente.getResumenAterior();
					// acredList.add(detalle);
					// }
					// }
					//
					// StringBuffer trailer = new StringBuffer(280);
					// trailer.append("9");
					// trailer.append("400");
					// trailer.append("0321");
					// trailer.append(dateFormat.format(((LiqCliente)liqClientes[0]).getFechaCierre()));
					// trailer.append(Convertidores.completarIzquierda
					// (cantidadRegistro.toString(), new Character('0'), 7));
					// trailer.append(Convertidores.completarDerecha("0", new Character('0'), 7));
					// trailer.append(Convertidores.completarIzquierda
					// (formateador.format(totalImporte).trim().replaceAll("[.]", ""), new Character('0'), 11));
					// trailer.append(Convertidores.completarDerecha("0", new Character('0'), 239));
					// acredList.add(trailer);
					// log.info("Archivo de trailer " + trailer);
					//
					// grabarArchivo(acredList,fechaArchivo);

					// transfiere registros de ctacteaux a ctacte y finaliza la liquidacion...
					transaccionesService.getGestorLiquidacionClienteService().confirmarLiquidacion(liquidacionCliente.getIdLiquidacionClientes(), "");

					// log.info("Cierre Periodo: " + liquidacionCliente.getFechaCierrePeriodo().toString());
					generarArchivoPagoFacil(liquidacionCliente.getFechaCierrePeriodo());
					generarSMS(liquidacionCliente.getFechaCierrePeriodo());
					generarNotificacionesWhatsapp();
					
					log.info("Corrigiendo Caja - Liq: " + liquidacionCliente.getIdLiquidacionClientes());
					Map param = new HashMap();
					param.put("id_liq", liquidacionCliente.getIdLiquidacionClientes());
					generalService.getGenericDao().ejecutarStoreProcedure("SP_LIQ_CORRIGE_CAJA", param);
					
					
					
					/*
					List listaLiqClientes = new ArrayList(liquidacionCliente.getLiqClientes());
					Iterator iter = listaLiqClientes.iterator();
					while(iter.hasNext()){
						LiqCliente aux = (LiqCliente)iter.next();
						transaccionesService.getCtaCteClienteService().corregirImportesImputacion(aux.getClienteTransaccion().getIdCliente());
					}
					*/
					
					listaPreliquidacionesWrapper.remove(this);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// si estaba confirmado, pero no finalizo correctamente, puede haber complicaciones mayores, por el pasaje de datos de la tabla
				// auxiliar a la tabla t_vis_tra_ctacte_clientes
				error.agregar("La preliquidacion no termino correctamente. Deberá borrarla.");

			}
		}
		


		public void eliminarPreliquidacion() {
			if (liquidacionCliente.getConfirmada().compareTo("N") == 0) {
				// borro por completo el objeto si no estaba confirmado.
				try {

					// borro el directorio
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
					PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + "/" + "webapps/contexto.properties");

					String nombreLiq = System.getProperty("catalina.home") + "/" + prop.getProperties("directorioArchivos")
							+ "/" + prop.getProperties("directorioLiquidacionesClientes") + "/"
							+ formatoFecha.format(liquidacionCliente.getFechaLiquidacion());
					File directorio = new File(nombreLiq);
					if (directorio.exists() && directorio.isDirectory()) {
						File[] archivos = directorio.listFiles();
						for (int i = 0; i < archivos.length; i++) {
							archivos[i].delete();
						}
					}
					directorio.delete();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				// borro el objeto
				transaccionesService.getGestorLiquidacionClienteService().borrarLiquidacion(liquidacionCliente.getIdLiquidacionClientes());
				listaPreliquidacionesWrapper.remove(this);
			} else {
				// si estaba confirmado, pero no finalizo correctamente, puede haber complicaciones mayores, por el pasaje de datos de la tabla
				// auxiliar a la tabla t_vis_tra_ctacte_clientes

				error.agregar("No se puede borrar una liquidacion confirmada");
			}
		}


		// PROYECTO 4029 PAGOMISCUENTAS
		// private void grabarArchivo(List lineasList,String fechaArchivo){
		// log.info("Graba Archivo de Facturacion " );
		//
		// String key= "catalina.home";
		// key = System.getProperty(key);
		// PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
		// StringBuffer linea = null;
		// try{
		// File dir = null;
		// File f = null;
		// try{
		// // f = new File(key +"/" + prop.getProperties("directorioArchivos") + "/" + "FAC3377."+fechaArchivo);
		// dir = new File(key +"/" + "webapps" + prop.getProperties("directorioFacturacion"));
		// if(!dir.exists()){
		// dir.mkdirs();
		// }
		//
		// }catch (Exception e) {
		// e.printStackTrace();
		// }
		// f = new File(key +"/" + "webapps" + prop.getProperties("directorioFacturacion") + "/" + "FAC0321."+fechaArchivo);
		//
		// if (f.exists()) {
		// f.delete();
		// }
		//
		// fw = new FileWriter(f);
		// if (!lineasList.isEmpty()) {
		// Iterator iter = lineasList.iterator();
		// while (iter.hasNext()) {
		// linea = (StringBuffer)iter.next();
		// if (linea!=null) {
		// for (int i = linea.length(); i < 280; i++) {
		// linea.append(" ");
		// }
		// linea.append("\r\n");
		// fw.write(linea.toString());
		// }
		// }
		// }
		// log.info(key +"/" + "webapps" + prop.getProperties("directorioArchivos") + "/" + "FAC0321."+fechaArchivo);
		// fw.close();
		// ejecutarJavaScript("popup('" + "/.."+ prop.getProperties("directorioArchivos") + "/" + "FAC0321."+fechaArchivo +
		// "',700,400), 'titlebar=no';");
		// if(f!=null){
		// log.info("Graba Archivo de Facturacion mostrar en pantalla " );
		// HttpServletResponse response = Session.getResponse();
		// FileInputStream archivo2 = new FileInputStream(f.getPath());
		// int longitud = archivo2.available();
		// byte[] datos = new byte[longitud];
		// archivo2.read(datos);
		// archivo2.close();
		// response.setContentType("application/octet-stream");
		// response.setHeader("Content-Disposition","attachment;filename=\"" + f.getName() + "\"");
		// ServletOutputStream ouputStream = response.getOutputStream();
		// ouputStream.write(datos,0,longitud);
		// ouputStream.flush();
		// ouputStream.close();
		// Session.responseComplete();
		// }
		// } catch(Exception e){
		// error.agregar("Error al generar el archivo");
		// e.getMessage();
		// e.printStackTrace();
		// }
		// if (!error.hayErrores()) {
		// try {
		// // fondosService.getLoteInterbankService().grabarLoteInterbank(loteInterbank);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }
		//
		// /**
		// * Ejecuta una porción de codigo javascript.
		// * @param event
		// */
		// public void ejecutarJavaScript(String script) {
		// FacesContext facesContext = FacesContext.getCurrentInstance();
		// AddResource addResource = AddResourceFactory.getInstance(facesContext);
		// addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, script);
		// }

		public LiquidacionClientes getLiquidacionCliente() {
			return liquidacionCliente;
		}


		public void setLiquidacionCliente(LiquidacionClientes liquidacionCliente) {
			this.liquidacionCliente = liquidacionCliente;
		}

	}
	
	public void generarSMS(Date fechaLiq){
		try {
			SimpleDateFormat nomArchivo = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat fLiq = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = nomArchivo.format(fechaLiq);
			String key = "catalina.home";
			key = System.getProperty(key);
			PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
			String archivo = key + "/" + "webapps" + prop.getProperties("directorioFacturacion") + "/SMS/SMS-" + fecha;
			String p0 = "?guardarEn=" + archivo;
			String p1 = "ƒfecha_liq=" + fLiq.format(fechaLiq);
			String p2 = "ƒAExcel=excel";
			HttpServletRequest request = Session.getRequest();
			String page = request.getContextPath() + "/report/SMS_liq_clientes.jrxml";
			GeneradorDeInforme gen = new GeneradorDeInforme();
			try {
				gen.guardarReporte(page + p0 + p1 + p2);
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generarNotificacionesWhatsapp(){	
		Map param = new HashMap();
		param.put("c", "1");
		//genera las notificaciones
		//generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_INSERTA_ID_WSP",param);
		//generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_INSERTA_NOTIF_WSP",param);
		//generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_CAMBIO_LINK_LIQUI",param);
		//generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.sp_cambio_link_liqui",param);
		
		
//		generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_INSERTA_ID_WSP", param);
//        generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_INSERTA_NOTIF_WSP", param);
//        generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_CAMBIO_LINK_LIQUI", param);
//        generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_ENVIA_NOTIF_WSP", param);
        
       	
		
		//envia whatsapp resumen//
		//generalService.getGenericDao().ejecutarStoreProcedure("PKG_WSP.SP_ENVIA_NOTIF_WSP", param);
	}


	public boolean getComunicado() {
		return comunicado;
	}


	public void setComunicado(boolean comunicado) {
		this.comunicado = comunicado;
	}
	


}

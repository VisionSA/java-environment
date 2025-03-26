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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosException;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.general.exception.ConceptoGenException;
import com.bizitglobal.tarjetafiel.general.exception.NoLaborableException;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioFormaPagoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConstructorException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioVersionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercioActividad;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.GestorLiquidacionCom;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComExclusion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComPago;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComRetencion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercioLP;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Liquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiquidacionListasPrecios;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioParaLiquidar;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PdfLiqComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacReporte;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.RetencionUtil;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacAliCuota;

import com.bizitglobal.webapp.faces.beans.transacciones.HttpPostMultipart;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings({"rawtypes","unchecked"})
public class LiquidacionComerciosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LiquidacionComerciosBean.class);
	private String focoHidden;

	private HtmlSelectOneMenu fechaLiquidacionSeleccionada = new HtmlSelectOneMenu();
	// private Date idFechaLiquidacionSeleccionada;
	private Long idFechaLiquidacionSeleccionada;
	private List listaFechasPosiblesLiq;
	private List listasPrecios; // esta lista presenta las listas precios que liquidan en la fecha seleccionada por el usuario.
	private boolean todos; // Un booleano que indica si estan todos seleccionados las listas de precios.
	private Object[] fechasDis; // un array con las fechas;
	// este boleano da true si hay listas que se correspondar a la fecha de liquidacion seleccionada.
	private boolean hayParaLiquidar;
	private String ipTransaccionador = "";
	private String rutaLiquidacion;

	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

	// // el objeto liquidacionComercio
	// LiqComercio liquidacionComercio;
	// Un stringBuffer que le sera pasado al transaccionador para su ejecucion.
	private StringBuffer resultados;
	private List listaComerciosAdheridos;
	// solo lo necesito para el calculo de las retenciones.
	private List tablaRetenciones;
	private GestorLiquidacionCom gestor; // El objeto que gestiona toda la liquidacion al comercio.
	// Para ver los cupones problematicos
	private String popupReport = new String("");
	// Una variable que guarda el monto minimo de cheques.
	private double montoMinimoParaCheques;
	// Una variable que guarda el cargo por transferencia Bancaria.
	private double cargoTransferenciaBancaria;
	// Una variable que guarda el cargo por transferencia Cheques.
	private double cargoTransferenciaCheque;

	private static Operador operador;

	private String pdfDesde;
	private String pdfHasta;

	// un array que me va guardando los pagos para cada liquidacion, de manera de poder persistirlos y presentarlos en el pdf.
	LiqComPago[] pagosFuturos = null;

	ConceptoGen conceptoLiquidacionFondos = null;


	public LiquidacionComerciosBean() {
		error.borrar();
	}


	public String inicializar() {
		log.info("Ejecutando ==> inicilizando el bean de Liquidacion de comercios.");
		borrar();
		List listas = new ArrayList();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	
		
		try {
			operador = new Operador();
			operador = Session.getOperador();
			
			
				
				Filtro filtro = new Filtro("activo", Filtro.LIKE, "S");
				filtro.agregarCampoOperValor("esFiel", Filtro.LIKE, "N");
				listas = transaccionesService.getListaPrecioService().getListaPrecio(filtro);
				// List listasYaLiquidadas = transaccionesService.getLiquidacionListasPreciosService().getLiquidacionListasPrecios(new Filtro());
				List listasYaLiquidadas = new ArrayList();
				Iterator iterListas = listas.iterator();
				while (iterListas.hasNext()) {
					ListaPrecio lista = (ListaPrecio) iterListas.next();
					if (lista.getFechaUltimaLiquidacion() != null) {
						// creo un objeto lista ya liquidada y lo agrego a la lista que se pasara como parametro a el GestorLiquidacionCom
						listasYaLiquidadas.add(new LiquidacionListasPrecios(lista, lista.getFechaUltimaLiquidacion()));
					}
				}
	
				// gestor = new GestorLiquidacionCom(listas, listasYaLiquidadas);
				gestor = transaccionesService.getGestorLiquidacionComService().crearGestorLiquidacion(listas,
						transaccionesService.getListaPrecioParaLiquidarService());
				listaFechasPosiblesLiq = new ArrayList();
				
				
				if(!transaccionesService.getGestorLiquidacionComService().verificarLotePosnet()){
					listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
					fechaLiquidacionSeleccionada.setValue(new Long(0));
					error.agregar("Debe cargar el lote del día de hoy para poder liquidar."); 
					
				} else {					
				
				listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
				fechasDis = ordenarFechas(gestor.getFechasPosiblesLiquidacion());
				for (int i = 0; i < fechasDis.length; i++) {
					try {
						listaFechasPosiblesLiq.add(new SelectItem(new Long(i + 1), formateador.format((Date) fechasDis[i])));
					} catch (NullPointerException e) {
						// nothing
					}
				}
				fechaLiquidacionSeleccionada.setValue(new Long(0));
				}
				
			
			
		} catch (ListaPrecioException e) {
			error.agregar("Hubo un error al intentar recuperar las listas de Precio Activas.");
			e.printStackTrace();
			listaFechasPosiblesLiq = new ArrayList();
			listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
			fechaLiquidacionSeleccionada.setValue(new Long(0));
		} catch (ConstructorException e) {
			error.agregar(e.getMessage());
			listaFechasPosiblesLiq = new ArrayList();
			listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
			fechaLiquidacionSeleccionada.setValue(new Long(0));
		} catch (ListaPrecioVersionException e) {
			error.agregar(e.getMessage());
			listaFechasPosiblesLiq = new ArrayList();
			listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
			fechaLiquidacionSeleccionada.setValue(new Long(0));
		} catch (NullPointerException e) {
			error.agregar("Al menos una de las listas de precio cargadas en el sistema presentan datos incorrectos.");
			listaFechasPosiblesLiq = new ArrayList();
			listaFechasPosiblesLiq.add(new SelectItem(new Long(0), "Seleccione Fecha de Liquidación"));
			fechaLiquidacionSeleccionada.setValue(new Long(0));
			e.printStackTrace();
		}
		return "liquidacionComercios";
	}


	/**
	 * Utilize para ordenar las fechas
	 * 
	 * @param listaFechas
	 *            Una lista de las fechas de liquidacion disponibles
	 * @return Un array de objetos Date ordenado
	 * */
	public Object[] ordenarFechas(List listaFechas) {
		Object[] fechas = listaFechas.toArray();
		Arrays.sort(fechas);
		return fechas;
	}


	public boolean validar() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public void presentarListasDisponibles(ValueChangeEvent event) {
		try {
			popupReport = new String("");
			log.info("Ejecutando cambiar listas");
			listasPrecios.clear();
			List listas = gestor.getListasPrecios((Date) fechasDis[((Long) fechaLiquidacionSeleccionada.getValue()).intValue() - 1]);
			Iterator iter = listas.iterator();
			while (iter.hasNext()) {
				ListaPrecioParaLiquidar lis = (ListaPrecioParaLiquidar) iter.next();
				WrapperListaPrecio wrapperListaPrecio = new WrapperListaPrecio(lis, true,
						(Date) fechasDis[((Long) fechaLiquidacionSeleccionada.getValue()).intValue() - 1]);
				if (wrapperListaPrecio.getIdCodComercios().size() > 0) {
					listasPrecios.add(wrapperListaPrecio);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			listasPrecios.clear();
		}
	}


	public String liquidarListas() {
		error.borrar();
		Concepto conceptoCargoTransfBancaria = null;
		Concepto conceptoCargoTransfCheque = null;
		Concepto conceptoMontoMinimoCheque = null;
		Concepto conceptoTotalLiquidacionComercio = null;
		try {
			popup.borrar();
			popupReport = new String("");
			conceptoLiquidacionFondos = (ConceptoGen) (generalService.getConceptoGenService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL,
					new Long(420)))).get(0);
			conceptoLiquidacionFondos.getConceptoDetalleSet();
			// creamos el directorio para la presente liquidacion, si es que no existe aun.
			try {
				rutaLiquidacion = crearDirectoriosLiquidacion();
			} catch (Exception e3) {
				error.agregar("No se ha podido crear el directorio para las liquidaciones.");
				throw new Exception("No se ha podido crear el directorio para las liquidaciones de comercio.");
			}

			List listaDiasNoLaborables = null;

			// bloque de inicializacion de conceptos.
			try {
				// recupero el concepto de Transferencia Bancaria Comercios (codigo 10), para obtener la lista de precios que utiliza
				conceptoCargoTransfBancaria = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(10))).get(0);
				conceptoCargoTransfBancaria.armarConcepto();
				conceptoCargoTransfBancaria.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception(
						"Error al intentar utilizar el Concepto Transferencia Bancaria Comercios (Codigo 10). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				// recupero el concepto de Cargo Emision Cheques (codigo 11), para obtener la lista de precios que utiliza
				conceptoCargoTransfCheque = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(11))).get(0);
				conceptoCargoTransfCheque.armarConcepto();
				conceptoCargoTransfCheque.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception(
						"Error al intentar utilizar el Concepto Cargo Emision Cheques (codigo 11). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				// recupero el concepto de Emisión Cheques Monto Minimo (codigo 12), para obtener la lista de precios que utiliza
				conceptoMontoMinimoCheque = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(12))).get(0);
				conceptoMontoMinimoCheque.armarConcepto();
				conceptoMontoMinimoCheque.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception(
						"Error al intentar utilizar el Concepto Emisión Cheques Monto Mínimo (codigo 12). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				// recupero el concepto de Total Liquidacion de Comercios (codigo 13), responsable de impactar el total de la liquidacion en la ctacte
				// comercio.
				conceptoTotalLiquidacionComercio = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(13))).get(0);
			} catch (Exception e) {
				throw new Exception(
						"Error al intentar utilizar el Concepto Total Pagos Comercio (Codigo 13). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}

			// leo el concepto transferencia bancaria.
			ConceptoDetalle conDet = null;
			try {
				Concepto con = (Concepto) transaccionesService.getConceptoService()
						.getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(250))).get(0);
				Iterator iterCon = con.getConceptoDetalleSet().iterator();
				while (iterCon.hasNext()) {
					conDet = (ConceptoDetalle) iterCon.next();
					break;
				}
			} catch (ConceptoException e2) {
				error.agregar("Ocurrió un error al intentar recuperar el concepto de Transferencia Bancaria (Código 250)");
				throw new Exception("Ocurrió un error al intentar recuperar el concepto de Transferencia Bancaria (Código 250)");
			} catch (Exception e2) {
				error.agregar("Ocurrió un error al intentar recuperar el concepto de Transferencia Bancaria (Código 250)");
				throw new Exception("Ocurrió un error al intentar recuperar el concepto de Transferencia Bancaria (Código 250)");
			}
			try {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -2);
				// cargo la lista de dias feriados....
				Filtro filtro = new Filtro("fecha", Filtro.MAYOR, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
				cal.add(Calendar.MONTH, 6);
				filtro.agregarCampoOperValor("fecha", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
				listaDiasNoLaborables = generalService.getNoLaborableService().getNoLaborable(filtro);

			} catch (NoLaborableException e2) {
				error.agregar("Ocurrió un error al intentar recuperar los dias feriados");
				throw new Exception("Ocurrió un error al intentar recuperar los dias feriados");
			} catch (Exception e2) {
				error.agregar("Ocurrió un error al intentar recuperar los dias feriados");
				throw new Exception("Ocurrió un error al intentar recuperar los dias feriados");
			}
			// recupero el iva.
			ParametroSistema parametroIva = generalService.getParametroSistemaService().leerParametroSistema(new Long(1));
			Double iva = null;
			Iterator iter = parametroIva.getDetallesParametrosSistema().iterator();
			while (iter.hasNext()) {
				ParametroSistemaDetalle det = (ParametroSistemaDetalle) iter.next();
				if (det.getIdParametroSistemaDetalle() == 1) {
					iva = Double.valueOf(det.getValor());
					break;
				}
			}

			WrapperListaPrecio[] arrayListas = new WrapperListaPrecio[listasPrecios.size()];
			int contador = 0;
			Iterator iterListas = listasPrecios.iterator();
			while (iterListas.hasNext()) {
				WrapperListaPrecio wrapListaPrecio = (WrapperListaPrecio) iterListas.next();
				if (wrapListaPrecio.isSeleccionado() && (wrapListaPrecio.estadoCorrecto || wrapListaPrecio.estadoAlerta)
						&& !wrapListaPrecio.estadoIncorrecto) {
					log.info("Esta lista pasa a ser parte del array de listas a liquidar.");
					arrayListas[contador] = wrapListaPrecio;
					contador++;
				}
			}
			for (int i = 0; i < arrayListas.length; i++) {
				// si el elemento es nulo, no quedan mas listas de precio y sale del ciclo.
				if (arrayListas[i] == null)
					break;
				boolean liquidoPorCuit = false;
				Iterator iteradorListas = arrayListas[i].getIdCodComercios().iterator();

				while (iteradorListas.hasNext()) {
					// recupero uno de los comercios de esta lista
					Long comercioActual = (Long) iteradorListas.next();
					try {
						CodComercio comercio = transaccionesService.getCodComercioService().leerCodComercio(comercioActual);
						comercio.getSucEmpresa().getDomicilio().getCalleNombre();
						// se lee el monto minimo para cheques, y el cargo por transferencia bancaria a partir de la tabla de parametros del sistema
						montoMinimoParaCheques = 0;
						cargoTransferenciaBancaria = 0;
						cargoTransferenciaCheque = 0;
						// recupero los montos necesarios, que le corresponden a este comercio en particular, a partir de los conceptos.
						// Solo si no esta excluido
						if (comercio.getExcluyeCargoPago().equals("N")) {
							try {
								// DetallesCargoTransfBancaria: aqui tengo que enviarle el id del comercio....
								Set detallesCargoTransfBancaria = conceptoCargoTransfBancaria.getListaPrecio(comercioActual).getVersionVigente()
										.getDetallesListaPrecio();
								Iterator listaCargoTransfBancaria = detallesCargoTransfBancaria.iterator();
								while (listaCargoTransfBancaria.hasNext()) {
									ListaPrecioDetalle var = (ListaPrecioDetalle) listaCargoTransfBancaria.next();
									cargoTransferenciaBancaria = var.getMonto().doubleValue();
									break;
								}
							} catch (Exception e) {
								throw new Exception(
										"Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
							}
							try {
								// DetallesCargoTransfCheque: aqui tengo que enviarle el id del comercio....
								Set detallesCargoTransfCheque = conceptoCargoTransfCheque.getListaPrecio(comercioActual).getVersionVigente()
										.getDetallesListaPrecio();
								Iterator listaCargoTransfCheque = detallesCargoTransfCheque.iterator();
								while (listaCargoTransfCheque.hasNext()) {
									ListaPrecioDetalle var = (ListaPrecioDetalle) listaCargoTransfCheque.next();
									cargoTransferenciaCheque = var.getMonto().doubleValue();
									break;
								}
							} catch (Exception e) {
								throw new Exception(
										"Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
							}
						}
						try {
							// DetallesCargoTransfBancaria: aqui tengo que enviarle el id del comercio....
							Set detallesMontoMinimoCheque = conceptoMontoMinimoCheque.getListaPrecio(comercioActual).getVersionVigente()
									.getDetallesListaPrecio();
							Iterator listaMontoMinimoCheque = detallesMontoMinimoCheque.iterator();
							while (listaMontoMinimoCheque.hasNext()) {
								ListaPrecioDetalle var = (ListaPrecioDetalle) listaMontoMinimoCheque.next();
								montoMinimoParaCheques = var.getMonto().doubleValue();
								break;
							}
						} catch (Exception e) {
							throw new Exception(
									"Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
						}

						Liquidacion liqui = null;
						// Decidimos si liquida por cuit o por codComercio

						// Agrege la condicion 1 = 0 para que no entre nunca a liquidar por cuit, hasta que el proceso de liquidacion por cuit este
						// andando bien.
						if (comercio.getSucEmpresa().getEmpresa().getTipoLiquidacion().compareTo(new Long(1)) == 0 && 1 == 0) {
							// liquida por cuit en esta rama, luego creo el objeto liquidacion que sera unico.
							CodComercio codComercio;
							try {
								codComercio = (CodComercio) transaccionesService
										.getCodComercioService()
										.getCodComercio(
												new Filtro("sucEmpresa.idSucEmpresa", Filtro.IGUAL, comercio.getSucEmpresa().getEmpresa()
														.getSucEmpresa().getIdSucEmpresa())).get(0);
							} catch (NullPointerException e) {
								throw new CodComercioException("La empresa que intenta liquidar por cuit ("
										+ comercio.getSucEmpresa().getEmpresa().getCuit() + ") no tiene una sucursal principal asociada.");
							}
							liqui = new Liquidacion();
							liqui.setListaFeriados(listaDiasNoLaborables);
							liqui.setCuit(String.valueOf(comercio.getSucEmpresa().getEmpresa().getCuit()));
							liqui.setFechaLiquidacion(arrayListas[i].fechaLiq);
							// primero recuperamos todos los codComercios que son de la misma empresa.
							List todosLosComerciosDeLaEmpresa = transaccionesService.getCodComercioService().getCodComercio(
									new Filtro("sucEmpresa.empresa.idEmpresa", Filtro.IGUAL, comercio.getSucEmpresa().getEmpresa().getIdEmpresa()));
							// Los recorro y para cada uno voy creando la liquidacion...
							Iterator iterParaCuit = todosLosComerciosDeLaEmpresa.iterator();
							while (iterParaCuit.hasNext()) {
								CodComercio comercioCuit = (CodComercio) iterParaCuit.next();
								for (int h = 0; h < arrayListas.length; h++) {
									if (arrayListas[h].getIdCodComercios().contains(comercioCuit.getIdCodComercio())) {
										arrayListas[i].liquidar(comercioCuit, liqui);
										// itero las listas restantes por las dudas tienen el mismo cod comercio
										recorrerListasRestantes(i + 1, comercio.getIdCodComercio(), arrayListas, liqui, comercio);
										break;
									}
								}
							}
							// dejo de recorrer el arrayListas, ya que ya lo hice, indicando un flag que me permite saltar al final.
							liquidoPorCuit = true;
							i = arrayListas.length;
							// liqui = new (....)

							List fpago = null;
							ComercioFormaPago formaPago = null;
							// Operador operador = null;
							// recupero la forma de pago de ela empresa o comercio.
							try {
								fpago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(
										new Filtro("empresa.idEmpresa", Filtro.IGUAL, comercio.getSucEmpresa().getEmpresa().getIdEmpresa()));
								formaPago = (ComercioFormaPago) fpago.get(0);
								formaPago.getFormaPago().getDescripcion();
								// operador = new Operador();
								// operador = Session.getOperador();

							} catch (ComercioFormaPagoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							boolean modifiqueCtaCte = false;

							// Una vez finalizado todo, le agrego el set de retenciones
							Iterator it = liqui.getLiqComercios().iterator();
							LiqComercio liqC = null;
							while (it.hasNext()) {
								LiqComercio li = (LiqComercio) it.next();
								if (liqC == null)
									liqC = li;
								li.setLiquidacion(liqui);
							}

							// vamos a generar el set de pagos. Si es necesario se modifica el monto total de las transacciones
							// por ej. si se paga mediante transferencia bancaria, entonces debemos restar al total transacciones el monto adecuado..
							// Tambien al total neto.
							boolean esAcreditacionBancaria = (formaPago.getFormaPago().getIdFormaPago().intValue() == 3 || formaPago.getFormaPago()
									.getIdFormaPago().intValue() == 4);
							boolean esCheque = formaPago.getFormaPago().getIdFormaPago().intValue() == 2;
							// seteo en el objeto Liquidacion el total de las tres cosas importantes: arancel, aceleramiento e iva.

							log.info("MontoTotal: " + liqui.getMontoTotal());
							log.info("MontoMinimoCheques: " + montoMinimoParaCheques);
							log.info("MontoAplicableRetencion: " + liqui.getMontoAplicableRetencion());
							log.info("FormaPagoDescripcion: " + formaPago.getFormaPago().getDescripcion());
							log.info("codCtaDeposito: " + formaPago.getCodCuentaDeposito());

							// seteo en el objeto Liquidacion el total de las tres cosas importantes: arancel, aceleramiento e iva.
							Iterator iteCom = liqui.getLiqComercios().iterator();
							while (iteCom.hasNext()) {
								LiqComercio lCom = (LiqComercio) iteCom.next();
								liqui.setAceleramientoTotal(liqui.getAceleramientoTotal().add(lCom.getAceleramientoNeto()));
								liqui.setArancelTotal(liqui.getArancelTotal().add(lCom.getArancelNeto()));
								liqui.setIvaNeto(liqui.getIvaNeto().add(lCom.getIvaNeto()));
							}
							double cargos = 0;
							pagosFuturos = liqui.getPagosDeLaLiquidacion(liqui.getMontoTotal(),
									BigDecimal.valueOf(liqui.getArancelTotal().doubleValue()),
									BigDecimal.valueOf(liqui.getAceleramientoTotal().doubleValue()), montoMinimoParaCheques, cargos,
									liqui.getMontoAplicableRetencion(), formaPago.getFormaPago().getDescripcion(), formaPago.getCodCuentaDeposito(),
									arrayListas[i].getListaPrecioParaLiquidar().getListaPrecio().getVersionVigente().isDifiereLiquidacionBoolean());
							if (esAcreditacionBancaria) {
								// si es acreditacion bancaria, impacto la ctacte del comercio con las acreditaciones bancarias tantas como
								// pagosFuturos .length
								for (int k = 0; k < pagosFuturos.length; k++) {
									liqui.getPagos().add(pagosFuturos[k]);
								}

								// recupero la liqComercio de este codComercio
								LiqComercio liqComercioParaImputaciones = null;
								Iterator iterLC = liqui.getLiqComercios().iterator();
								while (iterLC.hasNext()) {
									LiqComercio liqComercioAux = (LiqComercio) iterLC.next();
									if (liqComercioAux.getCodComercio().getIdCodComercio().compareTo(codComercio.getIdCodComercio()) == 0) {
										liqComercioParaImputaciones = liqComercioAux;
										break;
									}
								}
								// generarCargoTransferenciaBancaria(codComercio, conceptoCargoTransfBancaria, operador, liqComercioParaImputaciones);

								BigDecimal montoAgregado = new BigDecimal(cargoTransferenciaBancaria); // se le cobra un solo cargo por transferencia,
																										// independientemente del numero de pagos.
								montoAgregado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
								// vuelvo a calcular las retenciones, despues de modificar el total de transacciones, el total neto y el
								// totalsujetoARetencion.
								liqui.setMontoTotal(liqui.getMontoTotal().add(montoAgregado.negate()));
								liqui.setMontoNeto(liqui.getMontoNeto().add(montoAgregado.negate()));
								liqui.setMontoAplicableRetencion(liqui.getMontoAplicableRetencion().add(montoAgregado.negate()));
								liqui.setRetenciones(new HashSet());
							} else {
								if (esCheque) {
									// ha de ser Cheque
									for (int k = 0; k < pagosFuturos.length; k++) {
										liqui.getPagos().add(pagosFuturos[k]);
									}
									// recupero la liqComercio de este codComercio
									LiqComercio liqComercioParaImputaciones = null;
									Iterator iterLL = liqui.getLiqComercios().iterator();
									while (iterLL.hasNext()) {
										LiqComercio liqComercioAux = (LiqComercio) iterLL.next();
										if (liqComercioAux.getCodComercio().getIdCodComercio().compareTo(codComercio.getIdCodComercio()) == 0) {
											liqComercioParaImputaciones = liqComercioAux;
											break;
										}
									}
									// generarCargoTransferenciaCheques(codComercio, conceptoCargoTransfBancaria, operador,
									// liqComercioParaImputaciones);

								} else {
									// ha de ser efectivo. NUNCA DEBERIA ENTRAR POR AQUI
									for (int k = 0; k < pagosFuturos.length; k++) {
										liqui.getPagos().add(pagosFuturos[k]);
									}
								}
							}
							agregarRetencionesAlCuit(liqui, liqC.getCodComercio());
						} else {
							// liquida por codigo comercio en esta rama
							liqui = new Liquidacion();
							liqui.setIdLiquidacion(new Long(transaccionesService.getLiquidacionService().getMaximoId().longValue() + 1));
							liqui.setListaFeriados(listaDiasNoLaborables);
							liqui.setCuit(String.valueOf(comercio.getSucEmpresa().getEmpresa().getCuit()));
							liqui.setFechaLiquidacion(arrayListas[i].fechaLiq);
							if (arrayListas[i].liquidar(comercio, liqui))
								iteradorListas.remove();
							// itero las listas restantes por las dudas tienen el mismo cod comercio
							recorrerListasRestantes(i + 1, comercioActual, arrayListas, liqui, comercio);
							boolean tieneDatos = false;
							Iterator it = liqui.getLiqComercios().iterator();
							Long ultimoId = transaccionesService.getLiqComercioService().getMaximoId();
							int incremento = 1;
							while (it.hasNext()) {
								LiqComercio lc = (LiqComercio) it.next();
								if (lc.getImporteNeto().doubleValue() > 10) {
									tieneDatos = true;
									lc.setIdLiqComercio(new Long(ultimoId.longValue() + incremento));
									incremento++;
								} else {
									it.remove();
								}
							}

							if (tieneDatos) {
								// para formar los pagos
								LiqComercio liqC = (LiqComercio) liqui.getLiqComercios().toArray()[0];

								List fpago = null;
								ComercioFormaPago formaPago = null;
								// Operador operador = null;
								// recupero la forma de pago de ela empresa o comercio.
								try {
									fpago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(
											new Filtro("codComercio.idCodComercio", Filtro.IGUAL, comercio.getIdCodComercio()));
									formaPago = (ComercioFormaPago) fpago.get(0);
									formaPago.getFormaPago().getDescripcion();
									formaPago.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial();
									// operador = new Operador();
									// operador = Session.getOperador();

								} catch (ComercioFormaPagoException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IndexOutOfBoundsException e2) {
									throw new Exception("El comercio de código " + comercio.getCodigoPosnet()
											+ " que se intento liquidar no posee medio de pago asociado.");
								}

								// Guardo el objeto Liquidacion, y luego retomo la lista actua y sigo recorriendo los comercios.
								try {
									transaccionesService.getLiquidacionService().grabarLiquidacion(liqui);
								} catch (Exception e) {
									e.printStackTrace();
									popup.setPopup(popup.ICONO_FALLA, "No se logró finalizar la liquidación.");
									return "";
								}

								boolean esAcreditacionBancaria = (formaPago.getFormaPago().getIdFormaPago().intValue() == 3 || formaPago
										.getFormaPago().getIdFormaPago().intValue() == 4);
								boolean esCheque = formaPago.getFormaPago().getIdFormaPago().intValue() == 2;

								LiqComercio lComPrincipal = null;

								// seteo en el objeto Liquidacion el total de las tres cosas importantes: arancel, aceleramiento e iva.
								Iterator iteCom = liqui.getLiqComercios().iterator();
								while (iteCom.hasNext()) {
									LiqComercio lCom = (LiqComercio) iteCom.next();
									liqui.setAceleramientoTotal(liqui.getAceleramientoTotal().add(lCom.getAceleramientoNeto()));
									liqui.setArancelTotal(liqui.getArancelTotal().add(lCom.getArancelNeto()));
									liqui.setIvaNeto(liqui.getIvaNeto().add(lCom.getIvaNeto()));
									// liqui.setMontoAplicableRetencion(liqui.getMontoAplicableRetencion().add(lCom.getTotalDeRetenciones()));
									lComPrincipal = lCom;
									break;
								}
								double cargos = 0;
								// Se verifica si se le excluyen los cargos
								if (comercio.getExcluyeCargoPago().equals("N")) {
									if (esAcreditacionBancaria) {
										cargos = cargoTransferenciaBancaria;
										generarCargoTransferenciaBancaria(comercio, conceptoCargoTransfBancaria, operador, liqC, arrayListas[i]
												.getListaPrecioParaLiquidar().getFechaLiquidacion());
									} else {
										if (esCheque) {
											cargos = cargoTransferenciaCheque;
											// ha de ser Cheque. Se cobra un cargo si el cheque es no cruzado.
											// CtaCteComercio ctaCteComer = new CtaCteComercio(null,"N",String.valueOf(conDet.getCtacontabledebe()),
											// String.valueOf(conDet.getCtacontablehaber()),"C", liqui.getFechaLiquidacion(),
											// liqui.getFechaLiquidacion(), null,conDet,liqC,null,operador.getCodigo(),new Long(0),new
											// Long(0),null,new BigDecimal(cargoTransferenciaBancaria), new Long(0),new Integer(1),"0", new
											// Integer(1), new Timestamp(Calendar.getInstance().getTime().getTime()),liqC.getCodComercio(), null,new
											// Integer(1),null);
											generarCargoTransferenciaCheques(comercio, conceptoCargoTransfCheque, operador, liqC, arrayListas[i]
													.getListaPrecioParaLiquidar().getFechaLiquidacion());
										} else {
											// ha de ser efectivo
											// creo el objeto CtaCteComercio, con el monto de transferencia bancaria. Se cobra un solo monto por
											// transferencia bancaria, indistintamente del numero de pagos.
											CtaCteComercio ctaCteComer = new CtaCteComercio(null, "N", String.valueOf(conDet.getCtacontabledebe()),
													String.valueOf(conDet.getCtacontablehaber()), "C", liqui.getFechaLiquidacion(),
													liqui.getFechaLiquidacion(), null, conDet, liqC, null, operador.getCodigo(), new Long(0),
													new Long(0), null, new BigDecimal(cargoTransferenciaBancaria).setScale(2,
															BigDecimal.ROUND_HALF_DOWN), new Long(0), new Integer(1), "0", new Integer(1),
													new Timestamp(Calendar.getInstance().getTime().getTime()), liqC.getCodComercio(), null,
													new Integer(1), null);
										}
									}
								}

								// Una vez finalizado todo, le agrego el set de retenciones
								Iterator itBis = liqui.getLiqComercios().iterator();
								while (itBis.hasNext()) {
									tieneDatos = true;
									LiqComercio li = (LiqComercio) itBis.next();
									li.setLiquidacion(liqui);
									Double dou = Double.valueOf(cargos + cargos * iva);
									agregarRetenciones(lComPrincipal, comercio, dou, cargos);
								}
								// liqui.setMontoAplicableRetencion(liqui.getMontoAplicableRetencion().add(lComPrincipal.getTotalDeRetenciones()));
								liqui.setMontoAplicableRetencion(new BigDecimal(0));

								lComPrincipal.setArancelNeto(liqui.getArancelTotal());
								lComPrincipal.setAceleramientoNeto(liqui.getAceleramientoTotal());
								lComPrincipal.prepararLiqComercio(iva, lComPrincipal.getTotalDeRetenciones(), BigDecimal.valueOf(cargos).negate()
										.setScale(2, BigDecimal.ROUND_HALF_DOWN));

								Long idTran = transaccionesService.getCtaCteComercioService().getSequenciaTransaccion();
								Iterator iterDeRetenciones = lComPrincipal.getRetenciones().iterator();
								while (iterDeRetenciones.hasNext()) {
									LiqComRetencion ret = (LiqComRetencion) iterDeRetenciones.next();
									ConceptoDetalle conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(
											ret.getRetencion().getIdConceptoDetalle());

									transaccionesService.getRetencionAComercioService().registrarRetencionAComercio(lComPrincipal.getCodComercio(),
											conDe, idTran, operador, lComPrincipal,
											arrayListas[i].getListaPrecioParaLiquidar().getFechaLiquidacion(), ret.getMonto());
								}
								
								Iterator iterDeRetencionesSirtac = lComPrincipal.getSirtacs().iterator();
								while (iterDeRetencionesSirtac.hasNext()) {
									SirtacReporte ret = (SirtacReporte) iterDeRetencionesSirtac.next();
									ConceptoDetalle conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(
											ret.getConceptoDetalle().getIdConceptoDetalle());

									transaccionesService.getRetencionAComercioService().registrarRetencionAComercio(lComPrincipal.getCodComercio(),
											conDe, idTran, operador, lComPrincipal,
											arrayListas[i].getListaPrecioParaLiquidar().getFechaLiquidacion(), ret.getMontoRetenido());
								}

								// impacto en la cuentacte comercio el monto total de la liquidacion.
								generarTotalLiquidacionComercio(comercio, conceptoTotalLiquidacionComercio, operador, liqC, arrayListas[i]
										.getListaPrecioParaLiquidar().getFechaLiquidacion());

								pagosFuturos = liqui.getPagosDeLaLiquidacion(liqui.getMontoTotal(), lComPrincipal.getArancelNeto(),
										lComPrincipal.getAceleramientoNeto(), montoMinimoParaCheques,
										(new BigDecimal(cargos + cargos * 0.21D).setScale(2, BigDecimal.ROUND_HALF_DOWN)).doubleValue(),
										lComPrincipal.getTotalDeRetenciones(), formaPago.getFormaPago().getDescripcion(),
										formaPago.getCodCuentaDeposito(), arrayListas[i].getListaPrecioParaLiquidar().getListaPrecio()
												.getVersionVigente().isDifiereLiquidacionBoolean());

								for (int g = 0; g < pagosFuturos.length; g++) {
									liqui.getPagos().add(pagosFuturos[g]);
								}

								Iterator iterLiqC = liqui.getLiqComercios().iterator();
								while (iterLiqC.hasNext()) {
									LiqComercio liquidacionComercio = (LiqComercio) iterLiqC.next();

									Iterator lp = liquidacionComercio.getDetalles().iterator();
									while (lp.hasNext()) {
										LiqComercioLP liqLp = (LiqComercioLP) lp.next();
										log.info("Actualizare la tabla t_vis_tra_ctacte_comercios");
										transaccionesService.getGestorLiquidacionComService()
												.actualizarRegistrosCtaCteComercio(liqLp.getListaPrecio().getIdListaprecios().intValue(),
														liquidacionComercio.getCodComercio().getIdCodComercio(),
														arrayListas[i].getListaPrecioParaLiquidar().getFechaInicio(),
														arrayListas[i].getListaPrecioParaLiquidar().getFechaLiquidacion(),
														liquidacionComercio.getIdLiqComercio(),
														arrayListas[i].getListaPrecioParaLiquidar().getCuotasString());
									}
								}

								if (comercio.getSucEmpresa().getEmpresa().getTipoLiquidacion().compareTo(new Long(1)) == 0) {
									// hay que modificar esta llamada, ya que es al reporte de cuit
									Iterator iterLi = liqui.getLiqComercios().iterator();
									LiqComercio comAGenerar = (LiqComercio) iterLi.next();
									comAGenerar.setCargos(BigDecimal.valueOf(cargos).setScale(2, BigDecimal.ROUND_HALF_DOWN));
									impactarEnFondos(formaPago, comAGenerar);

									generarParaCuit(comAGenerar);
								} else {
									// genero un pdf por cada codComercio.
									Iterator iterLi = liqui.getLiqComercios().iterator();
									LiqComercio comAGenerar = (LiqComercio) iterLi.next();
									comAGenerar.setCargos(BigDecimal.valueOf(cargos).setScale(2, BigDecimal.ROUND_HALF_DOWN));
									if (comAGenerar.getPagos() == null)
										comAGenerar.setPagos(new HashSet());
									comAGenerar.getPagos().addAll(liqui.getPagos());
									impactarEnFondos(formaPago, comAGenerar);
									if (comAGenerar.getImporteNeto().intValue() != 0) {

										// solo lo genero si el monto es distinto de cero, si no seria un reporte en blanco.
										comAGenerar.setLiquidacion(liqui);
										generar(comAGenerar, esCheque);
									}
								}
							}
						}
						if (liquidoPorCuit)
							break;

					} catch (CodComercioException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error.agregar("Error:" + e.getMessage());
						return "";
					} catch (PlanCuentaDosException e) {
						error.agregar("La cuenta de fondos de la forma de pago del comercio puede no estar seteada correctamente.");
					}

				}
				// sale del for
				if (liquidoPorCuit)
					break;

				// momento de guardar la ListaPrecioParaLiquidar
				transaccionesService.getListaPrecioParaLiquidarService().grabarListaPrecioParaLiquidar(arrayListas[i].getListaPrecioParaLiquidar());

			}
			Iterator iterDeLimpieza = listasPrecios.iterator();
			while (iterDeLimpieza.hasNext()) {
				WrapperListaPrecio wrapListaPrecio = (WrapperListaPrecio) iterDeLimpieza.next();
				if (wrapListaPrecio.isSeleccionado() && (wrapListaPrecio.estadoCorrecto || wrapListaPrecio.estadoAlerta)
						&& !wrapListaPrecio.estadoIncorrecto) {
					wrapListaPrecio.getListaPrecioParaLiquidar().getListaPrecio().setFechaUltimaLiquidacion(wrapListaPrecio.fechaLiq);
					wrapListaPrecio.getListaPrecioParaLiquidar().getListaPrecio().setFinalizoLiquidacion("S");
					try {
						transaccionesService.getListaPrecioService().actualizarListaPrecio(
								wrapListaPrecio.getListaPrecioParaLiquidar().getListaPrecio());
					} catch (ListaPrecioException e) {
						log.info("No se pudo actualizar el estado de la lista precio.");
						e.printStackTrace();
						popup.setPopup(popup.ICONO_FALLA, "No se logró finalizar la liquidación.");
						return "";
					}
					iterDeLimpieza.remove();
				}
			}
			try {
			listaFechasPosiblesLiq.remove(((Long) fechaLiquidacionSeleccionada.getValue()).intValue());
			} catch (Exception e) {			
				e.printStackTrace();
			}
			fechaLiquidacionSeleccionada.setValue(new Long(0));
			popup.setPopup(popup.ICONO_OK, "La liquidacion se realizó con exito.");
		} catch (Exception e) {
			error.agregar("Error:" + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * Impacta el asiento, el asiento item, el movimiento, el movimiento MP, el cheque historial y el cheque.
	 * 
	 * @throws ConceptoGenException
	 * @throws MovimientoException
	 * @throws ChequeEstadoException
	 * @throws LiqComercioException
	 * @throws BancoPropioException
	 * */
	public void impactarEnFondos(ComercioFormaPago comercioFormaPago, LiqComercio liqComercio) throws ConceptoGenException, NullPointerException,
			PlanCuentaDosException, AsientoFondosException, MovimientoException, ChequeEstadoException, LiqComercioException, BancoPropioException {
		log.info("IMPACTA LIQ COMERCIO EN FONDOS");

		Set detallesConcepto = conceptoLiquidacionFondos.getConceptoDetalleSet();
		Date fecha = Calendar.getInstance().getTime();
		Operador oper = Session.getOperador();

		ConceptoDetalleGen detalleConCuentaUnica = null;

		// itero los conceptos detalle y asigno los valores a detalle con cuenta unica
		Iterator iter = detallesConcepto.iterator();
		while (iter.hasNext()) {
			ConceptoDetalleGen det = (ConceptoDetalleGen) iter.next();
			if (det.getOrden().intValue() == 0) {
				detalleConCuentaUnica = det;
				break;
			}
		}

		// creo el asiento Fondos.
		AsientoFondos asientoFondos = new AsientoFondos();
		asientoFondos.setConcepto(conceptoLiquidacionFondos.getDescripcion()); // hablar con Gustavo que descripcion poner.
		asientoFondos.setFecha(fecha);
		asientoFondos.setOperador(oper);
		asientoFondos.setCotabilizado('N');

		// creo el asiento item cabecera
		AsientoItem asientoItemCabecera = new AsientoItem();
		asientoItemCabecera.setAsiento(asientoFondos);
		asientoItemCabecera.setNroRenglon(new Integer(1));
		asientoItemCabecera.setSigno(detalleConCuentaUnica.getSigno());
		asientoItemCabecera.setLeyenda(detalleConCuentaUnica.getNombre());
		asientoItemCabecera.setImporte(liqComercio.getTotalDePagos().doubleValue());
		PlanCuentaDos planCuentaDos = (PlanCuentaDos) contabilidadService.getPlanCuentaDosService().leerPlanCuenta(
				detalleConCuentaUnica.getCtacontable());
		asientoItemCabecera.setPlanCuenta(planCuentaDos);
		asientoItemCabecera.setIdPlanCuenta(planCuentaDos.getIdPlanCuenta());

		// creo el asiento item con el medio de pago de la liquidacion.
		AsientoItem asientoItemMedioPago = new AsientoItem();
		asientoItemMedioPago.setAsiento(asientoFondos);
		asientoItemMedioPago.setNroRenglon(new Integer(2));
		asientoItemMedioPago.setSigno(Integer.valueOf(detalleConCuentaUnica.getSigno().intValue() * -1));
		PlanCuentaDos planCuentaDosBis = (PlanCuentaDos) contabilidadService.getPlanCuentaDosService().leerPlanCuenta(
				comercioFormaPago.getNroCuentaFondos());
		asientoItemMedioPago.setLeyenda(planCuentaDosBis.getTitulo());
		asientoItemMedioPago.setImporte(liqComercio.getTotalDePagos().doubleValue());
		asientoItemMedioPago.setPlanCuenta(planCuentaDosBis);
		asientoItemMedioPago.setIdPlanCuenta(planCuentaDosBis.getIdPlanCuenta());

		if (asientoFondos.getAsientosItems() == null)
			asientoFondos.setAsientosItems(new HashSet());
		asientoFondos.getAsientosItems().add(asientoItemCabecera);
		asientoFondos.getAsientosItems().add(asientoItemMedioPago);

		// impactaremos el Movimiento y el movimiento MP
		Movimiento movimiento = new Movimiento();
		MovimientoMP movimientoMP = new MovimientoMP();

		movimiento.setSigno(new Integer(1));
		movimiento.setFecha(fecha);
		movimiento.setOperador(oper);
		movimiento.setImporte(liqComercio.getTotalDePagos().doubleValue());
		movimiento.setConcepto(conceptoLiquidacionFondos);
		movimiento.setEstado('A');

		movimientoMP.setMonto(liqComercio.getTotalDePagos().doubleValue());
		movimientoMP.setMovimiento(movimiento);
		movimientoMP.setAsientoItem(asientoItemMedioPago);
		movimientoMP.setFormaPago(comercioFormaPago.getFormaPago());

		if (movimiento.getMovimientosMP() == null)
			movimiento.setMovimientosMP(new HashSet());
		movimiento.getMovimientosMP().add(movimientoMP);

		// para cada uno de los pagos....
		Iterator ite = liqComercio.getPagos().iterator();
		int contador = -1;
		while (ite.hasNext()) {
			contador++;
			LiqComPago liqCP = (LiqComPago) ite.next();

			// .....creo el Cheque en fondos y su respectivo cheque historial.
			Cheque cheque = new Cheque();
			ChequeHistorial chequeHistorial = new ChequeHistorial();

			Filtro filtro = new Filtro("banco.idBanco", Filtro.IGUAL, planCuentaDosBis.getCodBco());
			filtro.agregarCampoOperValor("numeroCuenta", Filtro.LIKE, planCuentaDosBis.getCodCtaBco().trim());
			BancoPropio bancoPropio = (BancoPropio) fondosService.getBancoPropioService().getBancoPropios(filtro).get(0);
			if (comercioFormaPago.getFormaPago().getIdFormaPago().compareTo(new Long(2)) == 0) {
				cheque.setTipo('P');
				cheque.setBeneficiario(comercioFormaPago.getOrdenCheque());
				cheque.setSucursalBanco(bancoPropio.getNumeroSucursal().toString());
				cheque.setCuenta(bancoPropio.getNumeroCuenta());
				cheque.setBanco(bancoPropio.getBanco());
			} else {
				cheque.setTipo('A');
				cheque.setBeneficiario(comercioFormaPago.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial());
				cheque.setBanco(comercioFormaPago.getBanco());
				cheque.setCuenta(comercioFormaPago.getCodCuentaDeposito());
				cheque.setCbu(comercioFormaPago.getCbu());
			}
			cheque.setNumero(" ");
			// recupero la empresa

			cheque.setFechaEmision(fecha);
			cheque.setTipoCuentaBanco(comercioFormaPago.getTipoCuentaBanco());
			cheque.setFechaPago(liqCP.getFecha());
			cheque.setEsCruzado(comercioFormaPago.getEsChequeCruzado().toCharArray()[0]);
			cheque.setNoOrden(comercioFormaPago.getChequeNoAlaOrden().toCharArray()[0]);
			cheque.setImporte(Double.valueOf(liqCP.getImporte().doubleValue()));
			cheque.setCodRed(null);
			cheque.setConciliado('N');
			cheque.setProcesado('N');

			log.info("Buscare en base a plan cuenta dos:" + planCuentaDosBis.getIdPlanCuenta());

			cheque.setBancoPropio(bancoPropio);
			cheque.setCodigoPostal("" + bancoPropio.getPlaza());

			chequeHistorial.setCheque(cheque);
			chequeHistorial.setTimestamp(Calendar.getInstance().getTime());
			ChequeEstado estado = fondosService.getChequeEstadoService().leerChequeEstado(new Long(1));
			chequeHistorial.setChequeEstado(estado);
			chequeHistorial.setMovimientoMP(movimientoMP);
			chequeHistorial.setAsientoItem(asientoItemMedioPago);

			if (movimientoMP.getChequeHistorial() == null)
				movimientoMP.setChequeHistorial(new HashSet());
			movimientoMP.getChequeHistorial().add(chequeHistorial);
		}

		// Grabo el asiento y los asientos item.
		liqComercio.setMovimiento(movimiento);
		try {
			transaccionesService.getLiqComercioService().actualizarLiqComercio(liqComercio);
		} catch (LiqComercioException e) {
			// Se borra la ultima liqComercio, y ademar, se dispara el informe del error.
			transaccionesService.getGestorLiquidacionComService().borrarLiqComercio(liqComercio);
			throw new LiqComercioException("Hubo un problema al intentar impactar en fondos. Compruebe que el comercio COD POSNET = "
					+ liqComercio.getCodComercio().getCodigoPosnet() + " tenga su medio de pago asociado, y presente datos correctos.");
		}
		log.info("IMPACTO ASIENTO Y ASIENTO ITEM, Movimiento, movimientoMp, cheque y cheque historial.");

	}


	public void recorrerListasRestantes(int posicionInicial, Long idComercioBuscado, WrapperListaPrecio[] listas, Liquidacion liqui, CodComercio com) {
		for (int j = posicionInicial; j < listas.length; j++) {
			// si el elemento es nulo, no quedan mas listas de precio y sale del ciclo.
			if (listas[j] == null)
				return;
			if (listas[j].getIdCodComercios().contains(idComercioBuscado) && listas[j].isSeleccionado()) {
				// si esta lista contiene el comercio que estoy liquidando, se lo liquido en el mismo objeto liquidacion.
				if (listas[j].liquidar(com, liqui))
					listas[j].getIdCodComercios().remove(idComercioBuscado);
			}
		}
	}


	/**
	 * Metodo que impacta el cargo por transferencia bancaria.
	 * */
	public boolean generarCargoTransferenciaBancaria(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio,
			Date fechaLiquidacion) {
		try {
			transaccionesService.getCargoTransferenciaBancariaService().registrarCargoTransferenciaBancaria(codComercio, concepto, operador,
					liqComercio, fechaLiquidacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Metodo que impacta el cargo por emitir cheques.
	 * */
	public boolean generarCargoTransferenciaCheques(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio,
			Date fechaLiquidacion) {
		try {
			transaccionesService.getCargoTransferenciaChequeService().registrarCargoTransferenciaCheque(codComercio, concepto, operador, liqComercio,
					fechaLiquidacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Metodo que impacta el cargo total de la liquidacion del comercio...
	 * */
	public boolean generarTotalLiquidacionComercio(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio,
			Date fechaLiquidacion) {
		try {
			transaccionesService.getLiquidacionService().generarTotalLiquidacionComercio(codComercio, concepto, operador, liqComercio,
					fechaLiquidacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public void borrar() {
		error.borrar();
		popup.borrar();
		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Liquidación de Comercios";
		listasPrecios = new ArrayList();
		listaComerciosAdheridos = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public String verComerciosAdheridos() {
		popupReport = new String("");
		Long idListaElegida = new Long(Session.getRequestParameter("idComerciosAVer"));
		// aqui listar todos los comercios que gozan de esta lista de precios...
		listaComerciosAdheridos = new ArrayList();
		try {
			/* @I4262 */Date fechaLiq = (Date) fechasDis[((Long) fechaLiquidacionSeleccionada.getValue()).intValue() - 1];
			Filtro filtro = new Filtro("listaPrecio.idListaprecios", Filtro.IGUAL, idListaElegida);
			// se le agrega un filtro mas para optimizar la busqueda
			filtro.agregarfuncion("AND exists (" +
					"SELECT 1 FROM CtaCteComercio ccc, com.bizitglobal.tarjetafiel.transacciones.negocio.Transaccion tra" +
					" WHERE tra.idTranascciones = ccc.idTranascciones" +
					" AND ccc.liqComercio is null" +
					" AND ccc.estadoImpacto like 'C'" +
					// " AND trunc(ccc.fechaFacturacion) = trunc(to_date('25/10/2011', 'DD/MM/YYYY'))" +
					" AND trunc(ccc.fechaFacturacion) = trunc(" + Filtro.getTO_DATE(fechaLiq) + ")" +
					" AND tra.comercioListaPrecio.idComercioListaprecio = obj.idComercioListaprecio)");
			System.out.println("FiltroSQL a pasar: " + filtro.getSQL());
			List listaComAd = transaccionesService.getComercioListaPrecioService().getComercioListaPrecio(filtro);
			/* @F4262 */Iterator iter = listaComAd.iterator();
			while (iter.hasNext()) {
				ComercioListaPrecio com = (ComercioListaPrecio) iter.next();
				com.getCodComercio().getCodigoPosnet();
				com.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial();
				com.getCodComercio().getSucEmpresa().getDescripcion();
				listaComerciosAdheridos.add(com.getCodComercio());
			}
		} catch (ComercioListaPrecioException e) {

			e.printStackTrace();
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/comerciosAdheridosPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
		return null;
	}


	public List getListaFechasPosiblesLiq() {
		return listaFechasPosiblesLiq;
	}


	public void setListaFechasPosiblesLiq(List listaFechasPosiblesLiq) {
		this.listaFechasPosiblesLiq = listaFechasPosiblesLiq;
	}


	public List getListasPrecios() {
		return listasPrecios;
	}


	public void setListasPrecios(List listasPrecios) {
		this.listasPrecios = listasPrecios;
	}


	public HtmlSelectOneMenu getFechaLiquidacionSeleccionada() {
		return fechaLiquidacionSeleccionada;
	}


	public void setFechaLiquidacionSeleccionada(
			HtmlSelectOneMenu fechaLiquidacionSeleccionada) {
		this.fechaLiquidacionSeleccionada = fechaLiquidacionSeleccionada;
	}


	public Set componerRetenciones(List tablaRetenciones, LiqComercio liquidacionComercio, BigDecimal totalRetenible, ListaPrecio  listaPrecio)
	{
		if (totalRetenible == null)
			totalRetenible = new BigDecimal(0);
		BigDecimal resto = new BigDecimal(totalRetenible.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		// System.out.println("Entro a componer retencion");
		log.info("el total retenible es: " + resto);
		Set retencionesSet = new HashSet();
		if (!tablaRetenciones.isEmpty()) {
			// System.out.println("Adentro de la retencion");
			boolean tengoResto = true;
			if (totalRetenible.doubleValue() > 0) {
				tengoResto = true;
			} else {
				tengoResto = false;
			}
			BigDecimal montoRet;
			BigDecimal montoRetSirtac = new BigDecimal("0");
			int  cantidadOperaciones = 0;
			Iterator iterRet = tablaRetenciones.iterator();
			while (iterRet.hasNext()) {
				// System.out.println("hay retencion");
				RetencionUtil retencionUtil = (RetencionUtil) iterRet.next();
				// Calcular ingresos brutos SIRTAC 
				Retencion retencion = retencionUtil.getRetencion();
				
				if (retencion.getIdConceptoDetalle() != null ) {
					
				 montoRet = retencionUtil.getCalculoRetencion();
				montoRet.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				log.info("el calculo de la retencion fue de :" + montoRet);
				if (retencionUtil.getRetencion().getIdRetencion() != null
						&& montoRet.compareTo(new BigDecimal(0)) != 0 && tengoResto) {
					// System.out.println("creo el objeto liq com retencion");
					LiqComRetencion liqComRetencion = new LiqComRetencion();
					liqComRetencion.setLiquidacion(liquidacionComercio);
					liqComRetencion.setRetencion(retencionUtil.getRetencion());
					liqComRetencion.setMonto(montoRet);

					// Voy restando las retenciones
//					resto = resto.subtract(montoRet);
					if (resto.doubleValue() <= 0) {
						// liqComRetencion.setMonto(resto.add(montoRet));
						tengoResto = false;
					} else {
						liqComRetencion.setMonto(montoRet);
					}
					liqComRetencion.setPorcAplicado(retencionUtil.getTramo().getPorcRetencion());
					if (retencionUtil.getExclusion().getIdExclusion() != null) {
						LiqComExclusion liqComExclusion = new LiqComExclusion();
						liqComExclusion.setExclusion(retencionUtil.getExclusion());
						liqComExclusion.setMonto(retencionUtil.getTotalExcluido());
						liqComExclusion.setPorcAplicado(retencionUtil.getExclusion().getPorcentaje());
						liqComExclusion.setRetencion(liqComRetencion);
						liqComRetencion.getExclusiones().add(liqComExclusion);
					}
					log.info("Agrego una retencion al set!!!!!!!!");
					retencionesSet.add(liqComRetencion);
				}
			}
			}
	}
		return retencionesSet;
	}


	public Set componerRetenciones(List tablaRetenciones, Liquidacion liquidacion, BigDecimal totalRetenible) {
		if (totalRetenible == null)
			totalRetenible = new BigDecimal(0);
		BigDecimal resto = new BigDecimal(totalRetenible.doubleValue());
		resto.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		Set retencionesSet = new HashSet();
		if (!tablaRetenciones.isEmpty()) {
			boolean tengoResto = true;
			Iterator iterRet = tablaRetenciones.iterator();
			while (iterRet.hasNext()) {
				RetencionUtil retencionUtil = (RetencionUtil) iterRet.next();
				BigDecimal montoRet = retencionUtil.getCalculoRetencion();
				montoRet.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				System.out.println("el calculo de la retencion fue de :" + montoRet);
				if (retencionUtil.getRetencion().getIdRetencion() != null
						&& montoRet.compareTo(new BigDecimal(0)) != 0 && tengoResto) {
					log.info("creo el objeto liq com retencion");
					LiqComRetencion liqComRetencion = new LiqComRetencion();
					liqComRetencion.setLiquidacionCuit(liquidacion);
					liqComRetencion.setRetencion(retencionUtil.getRetencion());
					liqComRetencion.setMonto(montoRet);

					// Voy restando las retenciones
					resto = resto.subtract(montoRet);
					if (resto.compareTo(new BigDecimal(0)) <= 0) {
						// liqComRetencion.setMonto(resto.add(montoRet));
						tengoResto = false;
					} else {
						liqComRetencion.setMonto(montoRet);
					}
					liqComRetencion.setPorcAplicado(retencionUtil.getTramo().getPorcRetencion());
					if (retencionUtil.getExclusion().getIdExclusion() != null) {
						LiqComExclusion liqComExclusion = new LiqComExclusion();
						liqComExclusion.setExclusion(retencionUtil.getExclusion());
						liqComExclusion.setMonto(retencionUtil.getTotalExcluido());
						liqComExclusion.setPorcAplicado(retencionUtil.getExclusion().getPorcentaje());
						liqComExclusion.setRetencion(liqComRetencion);
						liqComRetencion.getExclusiones().add(liqComExclusion);
					}
					log.info("Agrego una retencion al set!!!!!!!!");
					retencionesSet.add(liqComRetencion);
				}
			}
		}
		return retencionesSet;
	}


	/**
	 * Agrega las retenciones a una liquidacion, pero solo para el caso de Liquidacion por Cod comercio.
	 * 
	 * @param codComercio
	 *            A partir del codComercio, reconoce que empresa es y recupera las retenciones para esa empresa....
	 * */
	public void agregarRetencionesAlCuit(Liquidacion liquidacion, CodComercio codComercio) {
		tablaRetenciones = new ArrayList();
		// aqui calculo las retenciones.... una vez que ya tengo todos los montos calculados....
		try {
			// primero recupero el total del importe de la liquidacion:
			// calcularTotales....21
			liquidacion.calcularTotalesDeLiquidacion(new Double(0.21));

			log.info("Se debe recuperar las actividades...");
			// este metodo no esta trayendo bien las cosas, trae todo en null
			List retencionList = transaccionesService.getLiqComercioService().getRetenciones(codComercio);
			if (!retencionList.isEmpty()) {
				Iterator iterRetencion = retencionList.iterator();
				while (iterRetencion.hasNext()) {
					Object[] retComp = (Object[]) iterRetencion.next();
					log.info("A la retencion la calculare sobre el monto de : " + liquidacion.getMontoTotal());
					RetencionUtil ret = new RetencionUtil((Categoria) retComp[0], (Retencion) retComp[1], (Exclusion) retComp[2],
							liquidacion.getMontoAplicableRetencion(), new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_DOWN),
							new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_DOWN)); // en los tres ultimos deben ir; totalNeto, totalPagosMes y
																						// totalRetencionesMes
					tablaRetenciones.add(ret);
					log.info("agregue retencion....");

				}
			}
			log.info("tenemos " + tablaRetenciones.size() + " retenciones");

		} catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		liquidacion.setRetenciones(componerRetenciones(tablaRetenciones, liquidacion, liquidacion.getMontoAplicableRetencion()));
		// los calculos siguientes son para setear la cantidad de Pago.
		Iterator retenciones = liquidacion.getRetenciones().iterator();
		while (retenciones.hasNext()) {
			LiqComRetencion ret = (LiqComRetencion) retenciones.next();
		}
	}


	/**
	 * Agrega las retenciones a una liquidacion, pero solo para el caso de Liquidacion por Cod comercio.
	 * 
	 * @param cargos
	 *            La suma de todos los cargos con sus intereses e iva si los tendria. (ej, acreditacion bancaria e iva acreditacion bancaria)
	 * */
	public void agregarRetenciones(LiqComercio liqComercio, CodComercio codComercio, Double cargos,Double cargosSinIva) {
		tablaRetenciones = new ArrayList();
		// aqui calculo las retenciones.... una vez que ya tengo todos los montos calculados....
		BigDecimal montoAplicableRetencion = new BigDecimal(0);
		BigDecimal montoAplicableSirTac = new BigDecimal(0);
		ListaPrecio listaPrecio = new ListaPrecio();
		Set retencionesSirTacSet = new HashSet();
		ConceptoDetalle conDe  = null;
		String actualizaPagoSirtac = null;
		
		BigDecimal montoRet;
		
		int  cantidadOperaciones = 0;
		
		try {
			// primero recupero el total del importe de la liquidacion:
			double montoTotal = 0;
			double montoNeto = 0;
			double arancelTotal = 0;
			double aceleramientoTotal = 0;
			Iterator lp = liqComercio.getDetalles().iterator();
			
			
			while (lp.hasNext()) {
				LiqComercioLP varLp = (LiqComercioLP) lp.next();
				montoTotal += varLp.getTotalBruto().doubleValue();
				montoNeto += varLp.getTotalNeto().doubleValue();
				arancelTotal += varLp.getTotalArancel().doubleValue();
				 listaPrecio =  varLp.getListaPrecio();
				aceleramientoTotal += varLp.getTotalAceleramiento().doubleValue();
				
			}
			liqComercio.setImporteNeto(new BigDecimal(montoTotal).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			liqComercio.setArancelNeto(new BigDecimal(arancelTotal).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			liqComercio.setAceleramientoNeto(new BigDecimal(aceleramientoTotal).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			// construir los service para genDetParam y recuperar el valor del iva....!

			/* @I10 */// BigDecimal ivaNeto = (liqComercio.getArancelNeto().add(liqComercio.getAceleramientoNeto())).multiply(new BigDecimal(0.21));
			/* @F10 */BigDecimal ivaNeto = liqComercio.getIvaNeto();
			liqComercio.setIvaNeto(ivaNeto.setScale(2, BigDecimal.ROUND_HALF_DOWN));
			log.info("El monto iva neto es: " + ivaNeto);
			log.info("el monto neto es: " + montoNeto);
			montoAplicableRetencion = new BigDecimal(montoNeto).add(ivaNeto).add(BigDecimal.valueOf(cargos.doubleValue()).negate());
			montoAplicableSirTac = new BigDecimal(montoNeto).add(BigDecimal.valueOf(cargosSinIva.doubleValue()).negate());
			log.info("el montoAplicableSirTac es: " + montoAplicableSirTac);
			log.info("el montoAplicableRetencion es: " + montoAplicableRetencion);
			
			//**********************************************************
			// CALCULAR RETENCIONES DE SIRTAC
			//**********************************************************
			
			Long cuit =	liqComercio.getCodComercio().getSucEmpresa().getEmpresa().getCuit();
			
			SirtacAliCuota sirtacAliCuota = transaccionesService.getGestorLiquidacionComService().obtenerAliCuotaCuit(cuit,liqComercio.getLiquidacion().getFechaLiquidacion());
			
			
			try {
			  cantidadOperaciones = transaccionesService.getGestorLiquidacionComService().cantidadOperaciones( listaPrecio,
					  liqComercio.getCodComercio().getIdCodComercio(),  liqComercio.getLiquidacion().getFechaLiquidacion());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("cantidadOperaciones NO SE ENCUENTRA REVISAR !!!!!!!!");
				
				cantidadOperaciones = 0;
				
				e.printStackTrace();
				
			}
			
			if (!(sirtacAliCuota.getLetra().equals("NI")) && (sirtacAliCuota.getJurisdiccionesAlta().equals("2"))) {
				// CALCULAR RETENCIONES DE SIRTAC SOBRETASA
					SirtacAliCuota sirtacAliCuotaSO = transaccionesService.getGestorLiquidacionComService().obtenerAliCuotaSobretasa();
					montoRet = montoAplicableSirTac.multiply(sirtacAliCuotaSO.getAlicuota()).divide(new BigDecimal(100));					
					montoRet = montoRet.setScale(2, BigDecimal.ROUND_HALF_DOWN);
					log.info("el calculo de la retencion de SOBRETASA fue de :" + montoRet);
					SirtacReporte sirtacReporte = new SirtacReporte();
					sirtacReporte.setCuit(cuit);
					sirtacReporte.setCrc(sirtacAliCuota.getCrc());
					sirtacReporte.setFechaLiquidacion(liqComercio.getLiquidacion().getFechaLiquidacion());
					sirtacReporte.setFechaRetencion(liqComercio.getLiquidacion().getFechaLiquidacion());
					sirtacReporte.setNumeroLiquidacion(liqComercio);
					sirtacReporte.setOperaciones(new Long(cantidadOperaciones));
					sirtacReporte.setMontoSujetoRet(montoAplicableSirTac);
					
				try {	
					
					 conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(1070L);
					 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("concepto sirtac sobretasa no encontrado !!!!!!!!");
				e.printStackTrace();
				
			}
					
//					sirtacReporte.setConceptoDetalle(1070L);
					sirtacReporte.setConceptoDetalle(conDe);
					
					sirtacReporte.setAlicuota(sirtacAliCuotaSO.getAlicuota());
					sirtacReporte.setMontoRetenido(montoRet);
					sirtacReporte.setTipoRegistro(5L);
					sirtacReporte.setCodOperExceptuada(0L);
					sirtacReporte.setJurisdiccion(918L);
					
//					Campo #12: Si el campo #10 es igual a 1, 2,3 o 4 colocar el valor “918”, 
//					y si el campo #10 es igual a 5 colocar el valor que surja del campo #3 de la tabla Base #2: 
//						Padrón de Contribuyentes.

					
					retencionesSirTacSet.add(sirtacReporte);
					
					if (sirtacAliCuota.getLetra().equals("A"))  {
						
						BigDecimal montoCobrado = transaccionesService.getGestorLiquidacionComService().obtenerSirtacMalCobrado(cuit,liqComercio.getCodComercio().getIdCodComercio());
						if (montoCobrado == null) {
							montoRet = new BigDecimal("0");
							
						} else {
							montoRet = montoCobrado;
							
							actualizaPagoSirtac = transaccionesService.getGestorLiquidacionComService().actualizaSirtacPagado(cuit,liqComercio.getCodComercio().getIdCodComercio());
							
						}
						
						
						log.info(" SIRTAC letra A cuit !!!!!!!!" + cuit );
					} else {						
						montoRet = montoAplicableSirTac.multiply(sirtacAliCuota.getAlicuota()).divide(new BigDecimal(100));
						
						log.info(" SIRTAC letra no A cuit !!!!!!!!" + cuit );
					}
					montoRet = montoRet.setScale(2, BigDecimal.ROUND_HALF_DOWN);
					
					 sirtacReporte = new SirtacReporte();
					sirtacReporte.setCuit(cuit);
					sirtacReporte.setCrc(sirtacAliCuota.getCrc());
					sirtacReporte.setFechaLiquidacion(liqComercio.getLiquidacion().getFechaLiquidacion());
					sirtacReporte.setFechaRetencion(liqComercio.getLiquidacion().getFechaLiquidacion());
					sirtacReporte.setNumeroLiquidacion(liqComercio);
					sirtacReporte.setOperaciones(new Long(cantidadOperaciones));
					sirtacReporte.setMontoSujetoRet(montoAplicableSirTac);
//					sirtacReporte.setConceptoDetalle(1071L);
					
					try {	
						
						 conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(1071L);
						 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.info("concepto sirtac  no encontrado !!!!!!!!");
					e.printStackTrace();
					
				}
					

					sirtacReporte.setConceptoDetalle(conDe);
					
					
					log.info("Agrega valores al SirtacReporte!!!!!!!!");
					
					 if (sirtacAliCuota.getLetra().equals("A")) {
						
						sirtacReporte.setAlicuota(new BigDecimal("0"));
						
						BigDecimal montoCobrado = transaccionesService.getGestorLiquidacionComService().obtenerSirtacMalCobrado(cuit,liqComercio.getCodComercio().getIdCodComercio());
						if (montoCobrado == null) {
							sirtacReporte.setMontoRetenido(new BigDecimal("0"));							
						} else {
							sirtacReporte.setMontoRetenido(montoCobrado);
							actualizaPagoSirtac = transaccionesService.getGestorLiquidacionComService().actualizaSirtacPagado(cuit,liqComercio.getCodComercio().getIdCodComercio());
							
						}
						
						//sirtacReporte.setMontoRetenido(new BigDecimal("0"));						
						sirtacReporte.setTipoRegistro(2L);
						sirtacReporte.setCodOperExceptuada(0L);
						sirtacReporte.setJurisdiccion(sirtacAliCuota.getJurisdiccion());
						
					} else {
						
						sirtacReporte.setAlicuota(sirtacAliCuota.getAlicuota());
						sirtacReporte.setMontoRetenido(montoRet);
						sirtacReporte.setTipoRegistro(1L);
						sirtacReporte.setCodOperExceptuada(0L);
						sirtacReporte.setJurisdiccion(sirtacAliCuota.getJurisdiccion());
						
					}					
					log.info("Termino de agregar valores al SirtacReporte!!!!!!!!");
					
					retencionesSirTacSet.add(sirtacReporte);
					
			} else {
				
				if (sirtacAliCuota.getLetra().equals("A"))  {
					
//					BigDecimal montoCobrado = transaccionesService.getGestorLiquidacionComService().obtenerSirtacMalCobrado(cuit,liqComercio.getCodComercio().getIdCodComercio());
//					if (montoCobrado == null) {
//						montoRet = new BigDecimal("0");
//						
//					} else {
//						montoRet = montoCobrado;
//						actualizaPagoSirtac = transaccionesService.getGestorLiquidacionComService().actualizaSirtacPagado(cuit,liqComercio.getCodComercio().getIdCodComercio());
//						
//					}
					
					montoRet = new BigDecimal("0");
					log.info(" SIRTAC letra A cuit !!!!!!!!" + cuit );
				} else {						
					montoRet = montoAplicableSirTac.multiply(sirtacAliCuota.getAlicuota()).divide(new BigDecimal(100));
					
					log.info(" SIRTAC letra no A cuit !!!!!!!!" + cuit );
				}
				montoRet = montoRet.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				
				SirtacReporte sirtacReporte = new SirtacReporte();
				sirtacReporte.setCuit(cuit);
				sirtacReporte.setCrc(sirtacAliCuota.getCrc());
				sirtacReporte.setFechaLiquidacion(liqComercio.getLiquidacion().getFechaLiquidacion());
				sirtacReporte.setFechaRetencion(liqComercio.getLiquidacion().getFechaLiquidacion());
				sirtacReporte.setNumeroLiquidacion(liqComercio);
				sirtacReporte.setOperaciones(new Long(cantidadOperaciones));
				sirtacReporte.setMontoSujetoRet(montoAplicableSirTac);
//				sirtacReporte.setConceptoDetalle(1071L);
				
				try {	
					
					 conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(1071L);
					 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("concepto sirtac  no encontrado !!!!!!!!");
				e.printStackTrace();
				
			}
				

				sirtacReporte.setConceptoDetalle(conDe);
				
				log.info("Agrega valores al SirtacReporte!!!!!!!!");
				
				if (sirtacAliCuota.getLetra().equals("NI"))  {	
					sirtacReporte.setCrc(0L);					
					sirtacReporte.setAlicuota(sirtacAliCuota.getAlicuota());
					sirtacReporte.setMontoRetenido(montoRet);
					sirtacReporte.setTipoRegistro(4L);
					sirtacReporte.setCodOperExceptuada(0L);
					sirtacReporte.setJurisdiccion(918L);
					
				} else if (sirtacAliCuota.getLetra().equals("A")) {
					
					sirtacReporte.setAlicuota(new BigDecimal("0"));
					
					BigDecimal montoCobrado = transaccionesService.getGestorLiquidacionComService().obtenerSirtacMalCobrado(cuit,liqComercio.getCodComercio().getIdCodComercio());
					if (montoCobrado == null) {
						sirtacReporte.setMontoRetenido(new BigDecimal("0"));							
					} else {
						sirtacReporte.setMontoRetenido(montoCobrado.negate());
						
						log.info("cuit!!!!!!!!" + cuit);
						log.info("liqComercio.getCodComercio().getIdCodComercio()!!!!!!!!" + liqComercio.getCodComercio().getIdCodComercio());
						log.info("montoCobrado!!!!!!!!" + montoCobrado);
						
						log.info("sirtacReporte.setMontoRetenido!!!!!!!!" + sirtacReporte.getMontoRetenido().doubleValue());
						actualizaPagoSirtac = transaccionesService.getGestorLiquidacionComService().actualizaSirtacPagado(cuit,liqComercio.getCodComercio().getIdCodComercio());
						
					}
					
					//sirtacReporte.setMontoRetenido(new BigDecimal("0"));						
					sirtacReporte.setTipoRegistro(2L);
					sirtacReporte.setCodOperExceptuada(0L);
					sirtacReporte.setJurisdiccion(918L);
					
				} else {
					
					sirtacReporte.setAlicuota(sirtacAliCuota.getAlicuota());
					sirtacReporte.setMontoRetenido(montoRet);
					sirtacReporte.setTipoRegistro(1L);
					sirtacReporte.setCodOperExceptuada(0L);
					sirtacReporte.setJurisdiccion(918L);
					
				}					
				log.info("Termino de agregar valores al SirtacReporte!!!!!!!!");
				
				retencionesSirTacSet.add(sirtacReporte);
			}
			
			liqComercio.setSirtacs(retencionesSirTacSet);
			
			//**********************************************************
			// FIN CALCULAR RETENCIONES DE SIRTAC
			//**********************************************************
			
			

			// este metodo no esta trayendo bien las cosas, trae todo en null
			List retencionList = transaccionesService.getLiqComercioService().getRetenciones(codComercio);
			if (!retencionList.isEmpty()) {
				Iterator iterRetencion = retencionList.iterator();
				while (iterRetencion.hasNext()) {
					Object[] retComp = (Object[]) iterRetencion.next();
					// System.out.println("A la retencion la calculare sobre el monto de : " + montoTotal);
					RetencionUtil ret = new RetencionUtil((Categoria) retComp[0], (Retencion) retComp[1], (Exclusion) retComp[2],
							montoAplicableRetencion.setScale(2, BigDecimal.ROUND_HALF_DOWN),
							new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_DOWN), new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_DOWN)); // en
																																					// los
																																					// tres
																																					// ultimos
																																					// deben
																																					// ir;
																																					// totalNeto,
																																					// totalPagosMes
																																					// y
																																					// totalRetencionesMes
					tablaRetenciones.add(ret);
					log.info("agregue retencion....");
				}
			}
			log.info("tenemos " + tablaRetenciones.size() + " retenciones");

		} catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		liqComercio.setRetenciones(componerRetenciones(tablaRetenciones, liqComercio, montoAplicableRetencion,listaPrecio));
		// los calculos siguientes son para setear la cantidad de Pago.
		BigDecimal totalDeRetenciones = BigDecimal.valueOf(0);

		Iterator retenciones = liqComercio.getRetenciones().iterator();
		while (retenciones.hasNext()) {
			LiqComRetencion ret = (LiqComRetencion) retenciones.next();
			totalDeRetenciones = totalDeRetenciones.add(ret.getMonto());
		}
		
		//**********************************************************
		// ACUMULA LAS RETENCIONES DE SIRTAC
		//**********************************************************
		
		
		Iterator retencionesSirtac = liqComercio.getSirtacs().iterator();
		while (retencionesSirtac.hasNext()) {
			SirtacReporte ret = (SirtacReporte) retencionesSirtac.next();
			totalDeRetenciones = totalDeRetenciones.add(ret.getMontoRetenido());
		}
		
		//**********************************************************
				// FIN ACUMULA LAS RETENCIONES DE SIRTAC
		//**********************************************************
		
		liqComercio.setTotalDeRetenciones(totalDeRetenciones.negate().setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}


	public String generarParaCuit(LiqComercio liquidacionComercio) {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();

		// recupero el objeto empresa y el objeto Codigo comercio
		CodComercio codComercio = liquidacionComercio.getCodComercio();
		SucEmpresa sucEmpresa = liquidacionComercio.getCodComercio().getSucEmpresa();
		Empresa empresa = liquidacionComercio.getCodComercio().getSucEmpresa().getEmpresa();
		// arreglar el nombre de este archivo.

		String nombreLiq = rutaLiquidacion + File.separator + formatoFecha.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion()) + "_"
				+ codComercio.getCodigoPosnet();

		String p0 = "?guardarEn=" + nombreLiq + "_" + empresa.getCuit();
		String p1 = "ƒliquidacion_numero=" + liquidacionComercio.getLiquidacion().getIdLiquidacion();
		String p2 = "ƒresponsable_comercio=" + empresa.getRazonSocial();
		String p3 = "ƒURLImagen= " + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
		String p4 = "ƒtotal_transacciones=" + liquidacionComercio.getLiquidacion().getMontoTotal();
		String p5 = "ƒarancel=" + liquidacionComercio.getLiquidacion().getArancelTotal();
		String p6 = "ƒaceleramiento=" + liquidacionComercio.getLiquidacion().getAceleramientoTotal();
		// controlar el siguiente renglon...
		Calendar cal = Calendar.getInstance();
		cal.setTime(liquidacionComercio.getLiquidacion().getFechaLiquidacion());
		String p7 = "ƒfecha_liquidacion=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
		String p8 = "ƒnombre_comercial=" + empresa.getRazonSocial();
		String p9 = "ƒdomicilio_legal=" + sucEmpresa.getDomicilio().getCalleNombre() + " " + sucEmpresa.getDomicilio().getCalleNumero();
		String p10 = "ƒcodigo_comercio=" + codComercio.getCodigoPosnet();
		// controlar el siguiente Renglon
		String p11 = "ƒcuit=" + liquidacionComercio.getLiquidacion().getCuit();
		String p12 = "ƒcategoria_iva=" + " ";
		String p13 = "ƒinsc_ing_brutos=" + " ";
		String p14 = "ƒiva=" + liquidacionComercio.getLiquidacion().getIvaNeto();

		// debo hacer un bucle, y las retenciones que no existen, no se las paso, y recordar que se mostraran solamente 6 retenciones.
		int numeroDeRetencion = 0;
		String retenciones = "";
		BigDecimal totalRetenido = new BigDecimal(0);
		Iterator iterRetenciones = liquidacionComercio.getLiquidacion().getRetenciones().iterator();
		while (iterRetenciones.hasNext()) {
			LiqComRetencion liqRet = (LiqComRetencion) iterRetenciones.next();
			numeroDeRetencion++;
			retenciones += "ƒret" + numeroDeRetencion + "Descripcion=" + liqRet.getRetencion().getDescripcion();
			retenciones += "ƒret" + numeroDeRetencion + "Importe=" + liqRet.getMonto();
			totalRetenido = totalRetenido.add(liqRet.getMonto());
		}

		String pagos = "";
		// algo para generar los pagos.
		Calendar c = Calendar.getInstance();
		c.setTime(liquidacionComercio.getLiquidacion().getFechaLiquidacion());

		BigDecimal importeADescontar = liquidacionComercio.getLiquidacion().getAceleramientoTotal()
				.add(liquidacionComercio.getLiquidacion().getArancelTotal());
		importeADescontar = importeADescontar.add(liquidacionComercio.getLiquidacion().getIvaNeto().add(totalRetenido.negate()));

		int p = 0;
		for (int m = 0; m < pagosFuturos.length; m++) {
			c.setTime(pagosFuturos[m].getFecha());
			switch (p) {
			case 1:
				pagos += "ƒpago1Importe=" + pagosFuturos[m].getImporte() + "ƒpago1Fecha=" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
						+ "-" + c.get(Calendar.DATE) + "ƒpago1Descripcion=" + pagosFuturos[m].getDescripcion();
				break;
			case 2:
				pagos += "ƒpago2Importe=" + pagosFuturos[m].getImporte() + "ƒpago2Fecha=" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
						+ "-" + c.get(Calendar.DATE) + "ƒpago2Descripcion=" + pagosFuturos[m].getDescripcion();
				break;
			case 3:
				pagos += "ƒpago3Importe=" + pagosFuturos[m].getImporte() + "ƒpago3Fecha=" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
						+ "-" + c.get(Calendar.DATE) + "ƒpago3Descripcion=" + pagosFuturos[m].getDescripcion();
				break;
			case 4:
				pagos += "ƒpago4Importe=" + pagosFuturos[m].getImporte() + "ƒpago4Fecha=" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
						+ "-" + c.get(Calendar.DATE) + "ƒpago4Descripcion=" + pagosFuturos[m].getDescripcion();
				break;
			}
		}
		// debo hacer un bucle, y le paso los periodos de liquidacion que existen!
		int numeroDePeriodos = 0;
		String periodos = "";
		Iterator iterPeriodos = liquidacionComercio.getDetalles().iterator();
		while (iterPeriodos.hasNext()) {
			LiqComercioLP liqLp = (LiqComercioLP) iterPeriodos.next();
			numeroDePeriodos++;
			periodos += "ƒliq" + numeroDePeriodos + "Descripcion=" + "Periodo Liquidacion Lista " + liqLp.getListaPrecio().getIdListaprecios() + " "
					+ liqLp.getPeriodoDesde() + " al " + liqLp.getPeriodoHasta();
		}
		String p15 = "ƒtotal_liquidacion=" + liquidacionComercio.getLiquidacion().getTotalPagos();

		String p16 = "ƒSUBREPORT_DIR=LiquidacionCuitComercioSR";

		String page = request.getContextPath() + "/report/LiquidacionCuitComercio.jrxml";
		// popupReport = "popup('"+page+p0+p1+p2+p3+p4+p5+p6+p7+p9+p10+p11+p12+p13+p14+retenciones+pagos+periodos+"',1000,600)";
		GeneradorDeInforme gen = new GeneradorDeInforme();
		try {
			System.out.println("Reporte: " + page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + retenciones
					+ pagos + periodos + p15 + p16);
			gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + retenciones + pagos + periodos
					+ p15 + p16);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		log.info(popupReport);
		return null;
	}


	public String generar(LiqComercio liquidacionComercio, boolean esCheque) {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();

		// recupero el objeto empresa y el objeto Codigo comercio
		CodComercio codComercio = liquidacionComercio.getCodComercio();
		SucEmpresa sucEmpresa = liquidacionComercio.getCodComercio().getSucEmpresa();
		Empresa empresa = liquidacionComercio.getCodComercio().getSucEmpresa().getEmpresa();

		String nombreLiq = rutaLiquidacion
				+ File.separator
				+ (esCheque ? "C" : "A")
				+ formatoFecha.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion())
				+ "_"
				+ liquidacionComercio.getLiquidacion().getIdLiquidacion()
				+ "_"
				+ ("00000" + codComercio.getCodigoPosnet()).substring(("00000" + codComercio.getCodigoPosnet()).length() - 5,
						("00000" + codComercio.getCodigoPosnet()).length());
		
		String nombreArchivo = (esCheque ? "C" : "A")
				+ formatoFecha.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion())
				+ "_"
				+ liquidacionComercio.getLiquidacion().getIdLiquidacion()
				+ "_"
				+ ("00000" + codComercio.getCodigoPosnet()).substring(("00000" + codComercio.getCodigoPosnet()).length() - 5,
						("00000" + codComercio.getCodigoPosnet()).length());

		String p0 = "?guardarEn=" + nombreLiq;
		String p1 = "ƒliquidacion_numero=" + liquidacionComercio.getIdLiqComercio();
		String p2 = "ƒresponsable_comercio=" + empresa.getRazonSocial();
		// ///////////
		String p3 = "ƒURLImagen= " + Session.getHomePath() + "/img/fiel/cabecera.jpg";
		// ////////////
		String p17 = "ƒURLImagen2= " + Session.getHomePath() + "/img/fiel/bannercomercio.jpg";
		log.info("le paso el importe neto" + liquidacionComercio.getImporteNeto());
		String p4 = "ƒtotal_transacciones=" + liquidacionComercio.getImporteNeto();
		log.info("le paso el arancel neto" + liquidacionComercio.getArancelNeto());
		String p5 = "ƒarancel=" + liquidacionComercio.getArancelNeto();
		log.info("le paso el importe neto" + liquidacionComercio.getAceleramientoNeto());
		String p6 = "ƒaceleramiento=" + liquidacionComercio.getAceleramientoNeto();
		String p16 = "ƒcargos_varios=" + liquidacionComercio.getCargos().negate();
		// controlar el siguiente renglon...
		Calendar cal = Calendar.getInstance();
		cal.setTime(liquidacionComercio.getLiquidacion().getFechaLiquidacion());
		String p7 = "ƒfecha_liquidacion=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);

		// Comento lo que estaba antes del Nombre Comercial y
		// Lo replazo por el nombre de la Sucursal
		// String p8 = "ƒnombre_comercial="+empresa.getRazonSocial();
		String p8 = "ƒnombre_comercial=" + sucEmpresa.getLabel();

		String p9 = "ƒdomicilio_legal=" + sucEmpresa.getDomicilio().getCalleNombre() + " " + sucEmpresa.getDomicilio().getCalleNumero();
		String p10 = "ƒcodigo_comercio=" + codComercio.getCodigoPosnet();
		// controlar el siguiente Renglon
		String p11 = "ƒcuit=" + liquidacionComercio.getLiquidacion().getCuit();

		String catIva = "";
		Iterator iterComercioActividad = liquidacionComercio.getCodComercio().getCodComercioActividad().iterator();
		while (iterComercioActividad.hasNext()) {
			CodComercioActividad comercioAct = (CodComercioActividad) iterComercioActividad.next();
			if (comercioAct.getJurisdiccionActividad().getCategoria().getTipoImpuesto().getId() == 1
					|| comercioAct.getJurisdiccionActividad().getCategoria().getTipoImpuesto().getId() == 4) {
				catIva = comercioAct.getJurisdiccionActividad().getCategoria().getDescripcion();
				break;
			}
		}
		String p12 = "ƒcategoria_iva=" + catIva;

		// Comento lo anterior de la insc_ing_brutos
		// String p13 = "ƒinsc_ing_brutos="+" ";
		// Asigno el numero de ingreso bruto revisar
		String p13 = "ƒinsc_ing_brutos=" + codComercio.getInscripcionDgr();

		String p14 = "ƒiva=" + liquidacionComercio.getIvaNeto();

		// debo hacer un bucle, y las retenciones que no existen, no se las paso, y recordar que se mostraran solamente 6 retenciones.
		int numeroDeRetencion = 0;
		String retenciones = "";
		Iterator iterRetenciones = liquidacionComercio.getRetenciones().iterator();
		while (iterRetenciones.hasNext()) {
			LiqComRetencion liqRet = (LiqComRetencion) iterRetenciones.next();
			numeroDeRetencion++;
			retenciones += "ƒret" + numeroDeRetencion + "Descripcion=" + liqRet.getRetencion().getDescripcion();
			retenciones += "ƒret" + numeroDeRetencion + "Importe=-" + liqRet.getMonto();
			
		}
		
		
		Iterator iterRetencionesSirtac = liquidacionComercio.getSirtacs().iterator();
		while (iterRetencionesSirtac.hasNext()) {
			SirtacReporte liqRet = (SirtacReporte) iterRetencionesSirtac.next();
			numeroDeRetencion++;			
			
				retenciones += "ƒret" + numeroDeRetencion + "Descripcion=" + liqRet.getConceptoDetalle().getNombre();					
				
			//retenciones += "ƒret" + numeroDeRetencion + "Importe=-" + liqRet.getMontoRetenido();
			retenciones += "ƒret" + numeroDeRetencion + "Importe=" + liqRet.getMontoRetenido().negate();
		}
		
		

		// debo hacer un bucle, y los pagos que no existen, no se los paso.!
		// int numeroDePagos= 0;
		String pagos = "";
		int contadorPagos = 0;
		Iterator iterPag = liquidacionComercio.getLiquidacion().getPagos().iterator();
		while (iterPag.hasNext()) {
			LiqComPago lcomp = (LiqComPago) iterPag.next();
			contadorPagos++;
			Calendar cale = Calendar.getInstance();
			cale.setTime(lcomp.getFecha());
			pagos = pagos + "ƒpago" + contadorPagos + "Fecha=" + cale.get(Calendar.YEAR) + "-" + (cale.get(Calendar.MONTH) + 1) + "-"
					+ cale.get(Calendar.DATE) + "ƒpago" + contadorPagos + "Importe=" + lcomp.getImporte() + "ƒpago" + contadorPagos + "Descripcion="
					+ lcomp.getDescripcion();
		}

		// debo hacer un bucle, y le paso los periodos de liquidacion que existen!
		int numeroDePeriodos = 0;
		String periodos = "";
		Iterator iterPeriodos = liquidacionComercio.getDetalles().iterator();
		while (iterPeriodos.hasNext()) {
			LiqComercioLP liqLp = (LiqComercioLP) iterPeriodos.next();
			numeroDePeriodos++;
			// comentario del periodo, se coloca en el reporte "Período Liquidación "
			// periodos += "ƒliq" + numeroDePeriodos + "Descripcion=" + "Período Liquidación " + formatoFecha.format(liqLp.getPeriodoDesde()) + " al "
			// + formatoFecha.format(liqLp.getPeriodoHasta());
			periodos += "ƒliq" + numeroDePeriodos + "Descripcion=" + formatoFecha.format(liqLp.getPeriodoDesde()) + " al "
					+ formatoFecha.format(liqLp.getPeriodoHasta());
		}
		String p15 = "ƒtotal_liquidacion=" + liquidacionComercio.getTotalDePagos();

		String page = request.getContextPath() + "/report/LiquidacionCodComercio.jrxml";
		// popupReport = "popup('"+page+p0+p1+p2+p3+p4+p5+p6+p7+p9+p10+p11+p12+p13+p14+retenciones+pagos+periodos+"',1000,600)";
		GeneradorDeInforme gen = new GeneradorDeInforme();
		try {
			log.info("la cadena que se envia es: " + page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14
					+ retenciones + pagos + periodos + p15 + p17);
			// log.info("la cadena que se envia es: " + page+p0+p1+p2+p3+p4+p5+p6+p7+p8+p9+p10+p11+p12+p13+p14+retenciones+pagos+periodos+p15);
			String nombreLiqS3 = nombreLiq.trim()+".pdf";			
			log.info("archivo pdf a subir: " + nombreLiqS3);
			// Double totalLiquidacion, Double totalTransacciones, String urlImagen
			nombreLiq = nombreLiq.replace(System.getProperty("catalina.home"), "").replace("/webapps", "").substring(1);
			
		//String	nombreLiqAws = nombreLiq.replace("/archivos/", "").trim();
			SimpleDateFormat formatoFechaAws = new SimpleDateFormat("yyyy-MM-dd");
			String	nombreLiqAws = "https://tarjetafiel.s3-sa-east-1.amazonaws.com/"+ "liquidacionesComercios/"+formatoFechaAws.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion())+ "/"+ nombreArchivo;
			
		
			PdfLiqComercio pdfLiqComercio = null;

		//	transaccionesService.getPdfLiqComercioService().grabarPdfLiqComercio(pdfLiqComercio);
			gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p16 + retenciones + pagos
					+ periodos + p15 + p17);
			// gen.guardarReporte(page+p0+p1+p2+p3+p4+p5+p6+p7+p8+p9+p10+p11+p12+p13+p14+p16+retenciones+pagos+periodos+p15);
			try {
				
				File fileAws = null;
				fileAws = new File(nombreLiqS3);
				
				SimpleDateFormat formatoFechaS3 = new SimpleDateFormat("yyyy-MM-dd");
				String bucket = "liquidacionesComercios/"+formatoFechaS3.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion());
				//String bucket = "pruebaComercios/"+formatoFechaS3.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion());
				
					
				Map<String, String> headers = new HashMap<String, String>();
		    	  
		    	   HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.13:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
		  //  	   HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.66:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
//				HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.13:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
		    	    // Add form field
 	    
		    	    multipart.addFormField("bucket", bucket);
		    	    // Add file
		    	    multipart.addFilePart("file", fileAws);
		    	    // Print result
		    	    String response = multipart.finish();
		    	    System.out.println(response); 
		    	    log.info("archivo pdf a subido al Amazon: " + nombreLiqS3);
		    	    
		    	  pdfLiqComercio = new PdfLiqComercio(liquidacionComercio.getAceleramientoNeto(), liquidacionComercio.getArancelNeto(),
							liquidacionComercio.getCargos().negate(), " ", BigDecimal.valueOf(Long.valueOf(codComercio.getCodigoPosnet()).longValue()),
							liquidacionComercio.getLiquidacion().getCuit(), sucEmpresa.getDomicilio().getCalleNombre() + " "
									+ sucEmpresa.getDomicilio().getCalleNumero(),
							cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE), nombreLiqAws,
							" ", liquidacionComercio.getIvaNeto(), empresa.getRazonSocial(), pagos,
							periodos, empresa.getRazonSocial(), retenciones, liquidacionComercio.getTotalDePagos(), liquidacionComercio.getImporteNeto(),
							Session.getHomePath() + "/img/fiel/fondos_01.jpg", liquidacionComercio.getLiquidacion());

					transaccionesService.getPdfLiqComercioService().grabarPdfLiqComercio(pdfLiqComercio);
					
					String esChequeStr = (esCheque ? "C" : "A");

		    	    
		    	    
		    	    try {
						
						if ( fileAws != null && esChequeStr.equals("A") ) {
//							fileAws.delete();
						}
						
						} catch (Exception e) {
							log.info("Error al borrar el archivo en el servidor 7");
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
		    	    
		    	    
		    	    
				} catch (Exception e) {
					
					 pdfLiqComercio = new PdfLiqComercio(liquidacionComercio.getAceleramientoNeto(), liquidacionComercio.getArancelNeto(),
							liquidacionComercio.getCargos().negate(), " ", BigDecimal.valueOf(Long.valueOf(codComercio.getCodigoPosnet()).longValue()),
							liquidacionComercio.getLiquidacion().getCuit(), sucEmpresa.getDomicilio().getCalleNombre() + " "
									+ sucEmpresa.getDomicilio().getCalleNumero(),
							cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE), nombreLiq,
							" ", liquidacionComercio.getIvaNeto(), empresa.getRazonSocial(), pagos,
							periodos, empresa.getRazonSocial(), retenciones, liquidacionComercio.getTotalDePagos(), liquidacionComercio.getImporteNeto(),
							Session.getHomePath() + "/img/fiel/fondos_01.jpg", liquidacionComercio.getLiquidacion());

					transaccionesService.getPdfLiqComercioService().grabarPdfLiqComercio(pdfLiqComercio);
					
					log.info("Error en subir archivo al Amazon: " + nombreLiqS3);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(popupReport);
		return null;
	}


	public String reimprimirPdf() {
		borrar();
		Long desde = new Long(0);
		Long hasta = new Long(0);
		try {
			desde = Long.valueOf(pdfDesde);
			hasta = Long.valueOf(pdfHasta);
		} catch (Exception e) {
			error.agregar("Ingrese al menos un numero de liquidacion a reimprimir. En caso de que sea un rango, verifique que este correcto");
			return null;
		}
		try {
			List listaPdf = transaccionesService.getPdfLiqComercioService().getPdfLiqComercio(desde, hasta);
			if (!listaPdf.isEmpty()) {
				reGenerarPdf(listaPdf);
			} else {
				error.agregar("El rango a reimprimir no presenta liquidaciones");
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	public String reGenerarPdf(List listaPdfLiqComercio) throws Exception {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();

		SimpleDateFormat formatoFecha = new SimpleDateFormat("MM-yyyy");
		PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + "/webapps/contexto.properties");

		String nombreLiq = System.getProperty("catalina.home") + File.separator + prop.getProperties("directorioArchivos")
				+ prop.getProperties("directorioLiquidacionesComercios");
		File directorio = new File(nombreLiq);
		if (!directorio.exists()) {
			if (!directorio.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}
		nombreLiq += File.separator + "Reimpresiones";
		File directorioInterno = new File(nombreLiq);
		if (!directorioInterno.exists()) {
			if (!directorioInterno.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}
		rutaLiquidacion = nombreLiq;

		Iterator ite = listaPdfLiqComercio.iterator();
		while (ite.hasNext()) {
			PdfLiqComercio pdfLiqComercio = (PdfLiqComercio) ite.next();
			String codigPosne = String.valueOf(pdfLiqComercio.getCodigoComercio());
			String nombre = rutaLiquidacion
					+ File.separator
					+ pdfLiqComercio.getFechaLiquidacion()
					+ "_"
					+ pdfLiqComercio.getLiquidacion().getIdLiquidacion()
					+ "_"
					+ ("00000" + pdfLiqComercio.getCodigoComercio()).substring(("00000" + pdfLiqComercio.getCodigoComercio()).length() - 5,
							("00000" + pdfLiqComercio.getCodigoComercio()).length());

			String p0 = "?guardarEn=" + nombre;
			String p1 = "ƒliquidacion_numero=" + pdfLiqComercio.getLiquidacion().getIdLiquidacion();
			String p2 = "ƒresponsable_comercio=" + pdfLiqComercio.getResponsableComercio();
			String p3 = "ƒURLImagen=" + pdfLiqComercio.getUrlImagen();
			String p4 = "ƒtotal_transacciones=" + pdfLiqComercio.getTotalTransacciones();
			String p5 = "ƒarancel=" + pdfLiqComercio.getArancel();
			String p6 = "ƒaceleramiento=" + pdfLiqComercio.getAceleramiento();
			String p16 = "ƒcargos_varios=" + pdfLiqComercio.getCargosVarios();
			String p7 = "ƒfecha_liquidacion=" + pdfLiqComercio.getFechaLiquidacion();
			String p8 = "ƒnombre_comercial=" + pdfLiqComercio.getNombreComercial();
			String p9 = "ƒdomicilio_legal=" + pdfLiqComercio.getDomicilioLegal();
			String p10 = "ƒcodigo_comercio=" + pdfLiqComercio.getCodigoComercio();
			String p11 = "ƒcuit=" + pdfLiqComercio.getCuit();
			String p12 = "ƒcategoria_iva=" + pdfLiqComercio.getCategoriaIva();
			String p13 = "ƒinsc_ing_brutos=" + pdfLiqComercio.getInscIngBrutos();
			String p14 = "ƒiva=" + pdfLiqComercio.getIva();
			String retenciones = "";
			String pagos = "";
			String periodos = "";
			if (pdfLiqComercio.getRetenciones() != null) {
				retenciones = pdfLiqComercio.getRetenciones().replace("¿", "ƒ");
			}
			if (pdfLiqComercio.getPagos() != null) {
				pagos = pdfLiqComercio.getPagos().replace("¿", "ƒ");
			}
			if (pdfLiqComercio.getPeriodos() != null) {
				periodos = pdfLiqComercio.getPeriodos().replace("¿", "ƒ");
			}
			String p15 = "ƒtotal_liquidacion=" + pdfLiqComercio.getTotalLiquidacion();

			String page = request.getContextPath() + File.separator + "report" + File.separator + "LiquidacionCodComercio.jrxml";
			GeneradorDeInforme gen = new GeneradorDeInforme();
			try {
				log.info("la cadena que se envia es: " + page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14
						+ retenciones + pagos + periodos + p15);
				gen.guardarReporte(page + p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p16 + retenciones + pagos
						+ periodos + p15);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * Crea el directorio de las liquidaciones en la posición especificada por los atributos del contexto.properties, de la siguiente forma:
	 * directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
	 * */
	private String crearDirectoriosLiquidacion() throws Exception {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") + "/webapps/contexto.properties");

		String nombreLiq = System.getProperty("catalina.home") + File.separator + prop.getProperties("directorioArchivos") + File.separator
				+ prop.getProperties("directorioLiquidacionesComercios");
		File directorio = new File(nombreLiq);
		if (!directorio.exists()) {
			if (!directorio.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}
		nombreLiq += File.separator + formatoFecha.format(fechasDis[((Long) fechaLiquidacionSeleccionada.getValue()).intValue() - 1]);
		File directorioInterno = new File(nombreLiq);
		if (!directorioInterno.exists()) {
			if (!directorioInterno.mkdir())
				throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		}
		return nombreLiq;
	}

	public class WrapperListaPrecio {

		private Long idListaPrecioALiq;
		private ListaPrecioParaLiquidar listaPrecioParaLiquidar;
		private Date fechaLiq;
		private boolean seleccionado;
		private boolean estadoCorrecto;
		private boolean estadoIncorrecto;
		private boolean estadoAlerta;
		
		// la coleccion de id de codigos comercios como Long.
		private Collection idCodComercios;


		public WrapperListaPrecio(ListaPrecioParaLiquidar listaParaLiquidar, boolean seleccion, Date fechaLiq) {
			this.listaPrecioParaLiquidar = listaParaLiquidar;
			this.idListaPrecioALiq = listaPrecioParaLiquidar.getListaPrecio().getIdListaprecios();
			this.seleccionado = seleccion;
			this.fechaLiq = fechaLiq;
			System.out.println(this.listaPrecioParaLiquidar.toString());
			// al momento de crear el wraper le asigno todos los codigos de comercio que posee.
			this.idCodComercios = new ArrayList();
			try {
				Filtro filtro = new Filtro("listaPrecio.idListaprecios", Filtro.IGUAL, listaParaLiquidar.getListaPrecio().getIdListaprecios());
				// se le agrega un filtro mas para optimizar la busqueda
				filtro.agregarfuncion("AND exists (" +
						"SELECT 1 FROM CtaCteComercio ccc, com.bizitglobal.tarjetafiel.transacciones.negocio.Transaccion tra" +
						" WHERE tra.idTranascciones = ccc.idTranascciones" +
						" AND ccc.liqComercio is null" +
						" AND ccc.estadoImpacto like 'C'" +
						//" AND trunc(ccc.fechaFacturacion) = trunc(to_date('25/10/2011', 'DD/MM/YYYY'))" +
						" AND trunc(ccc.fechaFacturacion) = trunc(" + Filtro.getTO_DATE(fechaLiq) + ")" +
						" AND tra.comercioListaPrecio.idComercioListaprecio = obj.idComercioListaprecio)");
				System.out.println("FiltroSQL a pasar: " + filtro.getSQL());
				List lista = transaccionesService.getComercioListaPrecioService().getComercioListaPrecio(filtro);
				Iterator ite = lista.iterator();
				while (ite.hasNext()) {
					ComercioListaPrecio comLisPre = (ComercioListaPrecio) ite.next();
					// estos registros con codComercio nulo, no deberian existir, hay que ver que mapeo definir para hacer un cascades
					// delete-all-orphan
					if (comLisPre.getCodComercio() != null) {
						idCodComercios.add(comLisPre.getCodComercio().getIdCodComercio());
					}
				}
			} catch (ComercioListaPrecioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			validarLiquidacionCorrecta();
		}


		public void validarLiquidacionCorrecta() {

			estadoCorrecto = false;
			estadoAlerta = false;
			estadoIncorrecto = false;
			int numerosRechazados = transaccionesService.getGestorLiquidacionComService().contarRegistros(
					this.listaPrecioParaLiquidar.getListaPrecio().getIdListaprecios(), "R", this.listaPrecioParaLiquidar.getFechaInicio(),
					this.listaPrecioParaLiquidar.getFechaLiquidacion(), listaPrecioParaLiquidar.getCuotasString());
			int numerosRechazadosAutomaticos = transaccionesService.getGestorLiquidacionComService().contarRegistros(
					this.listaPrecioParaLiquidar.getListaPrecio().getIdListaprecios(), "X", this.listaPrecioParaLiquidar.getFechaInicio(),
					this.listaPrecioParaLiquidar.getFechaLiquidacion(), listaPrecioParaLiquidar.getCuotasString());
			log.info("Los rechazados son " + numerosRechazados);
			log.info("Los rechazados automaticos " + numerosRechazadosAutomaticos);

			if (numerosRechazadosAutomaticos != 0)
				estadoIncorrecto = true;
			if (numerosRechazados != 0 && !estadoIncorrecto)
				estadoAlerta = true;
			if (!estadoIncorrecto && !estadoAlerta)
				estadoCorrecto = true;
		}


		/**
		 * este metodo modifica el objeto liquidacion de tal manera que lo devuelve contemplando el comercio que fue pasado.
		 * */
		public boolean liquidar(CodComercio codComer, Liquidacion liquidacion) {
			// liquidar la lista que contiene este objeto.
			Set listaItems = listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getItemsListaPrecio();
			int[] cuotas = new int[listaItems.size()];
			int i = 0;
			Iterator ite = listaItems.iterator();
			while (ite.hasNext()) {
				ListaPrecioItem lpi = (ListaPrecioItem) ite.next();
				cuotas[i] = lpi.getComCuotas().intValue();
				i++;
			}
			// inicialmente traigo todas las cuotas de la base con sus respectivos montos. Elimino los que no coincidan con la fecha en el caso
			// que difiera de liquidacion....
			if (listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().isDifiereLiquidacionBoolean()) {
				// el proceso si difiere liquidacion
				log.info("La lista precio DIFIERE liquidacion");
				// el siguiente es el set de elementos LiqComercioDet, deben ser seteados a los elementos LiqComercioLP
				Set detallesLiq = transaccionesService.getGestorLiquidacionComService().liquidarCuota(this.listaPrecioParaLiquidar.getListaPrecio(),
						codComer.getIdCodComercio(), this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getIdListaPrecioVersion(),
						this.listaPrecioParaLiquidar.getFechaInicio(), this.listaPrecioParaLiquidar.getFechaCierre(), this.fechaLiq,
						listaPrecioParaLiquidar.getCuotasString());
				LiqComercio liquidacionCom = null;
				Iterator iter = liquidacion.getLiqComercios().iterator();
				while (iter.hasNext()) {
					LiqComercio liqCom = (LiqComercio) iter.next();
					if (liqCom.getCodComercio().getCodigoPosnet().compareTo(codComer.getCodigoPosnet()) == 0) {
						liquidacionCom = liqCom;
						break;
					}
				}
				LiqComercio liquidacionComercio = null;
				// si la liquidacionComercio para esta liquidacion aun no existe la crea, si no le agrega elementos al set LiquidacionLP
				if (liquidacionCom == null) {
					// no existia aun
					if (detallesLiq != null) {
						liquidacionComercio = gestor.liquidarComercios(this.listaPrecioParaLiquidar, codComer, codComer.getSucEmpresa().getEmpresa()
								.getCuit().toString(), this.fechaLiq, detallesLiq);
						// le agregamos al objeto Liquidacion el objeto LiquidacionComercio
						liquidacion.getLiqComercios().add(liquidacionComercio);
					}
				} else {
					if (detallesLiq != null) {
						// ya existia la liquidacionComercio, para otra lista, por lo tanto le agregamos la lista actual
						LiqComercioLP liqComLp = gestor.armarLP(this.listaPrecioParaLiquidar, liquidacionCom.getCodComercio(),
								String.valueOf(liquidacionCom.getCodComercio().getSucEmpresa().getEmpresa().getCuit()), this.fechaLiq, detallesLiq);
						liqComLp.setLiqComercio(liquidacionCom);
						liquidacionCom.getDetalles().add(liqComLp);
					}
				}

			} else {
				log.info("La lista precio no difiere liquidacion");
				// el siguiente es el set de elementos LiqComercioDet, deben ser seteados a los elementos LiqComercioLP
				Set detallesLiq = transaccionesService.getGestorLiquidacionComService().liquidarCuota(this.listaPrecioParaLiquidar.getListaPrecio(),
						codComer.getIdCodComercio(), this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getIdListaPrecioVersion(),
						this.listaPrecioParaLiquidar.getFechaInicio(), this.listaPrecioParaLiquidar.getFechaCierre(), this.fechaLiq,
						listaPrecioParaLiquidar.getCuotasString());
				LiqComercio liquidacionCom = null;
				Iterator iter = liquidacion.getLiqComercios().iterator();
				while (iter.hasNext()) {
					LiqComercio liqCom = (LiqComercio) iter.next();
					if (liqCom.getCodComercio().getCodigoPosnet().compareTo(codComer.getCodigoPosnet()) == 0) {
						liquidacionCom = liqCom;
						break;
					}
				}
				LiqComercio liquidacionComercio = null;
				// si la liquidacionComercio para esta liquidacion aun no existe la crea, si no le agrega elementos al set LiquidacionLP
				if (liquidacionCom == null) {
					// no existia aun
					if (detallesLiq != null) {
						liquidacionComercio = gestor.liquidarComercios(this.listaPrecioParaLiquidar, codComer, codComer.getSucEmpresa().getEmpresa()
								.getCuit().toString(), this.fechaLiq, detallesLiq);
						// le agregamos al objeto Liquidacion el objeto LiquidacionComercio
						liquidacion.getLiqComercios().add(liquidacionComercio);
					}
				} else {
					if (detallesLiq != null) {
						// ya existia la liquidacionComercio, para otra lista, por lo tanto le agregamos la lista actual
						LiqComercioLP liqComLp = gestor.armarLP(this.listaPrecioParaLiquidar, liquidacionCom.getCodComercio(),
								String.valueOf(liquidacionCom.getCodComercio().getSucEmpresa().getEmpresa().getCuit()), this.fechaLiq, detallesLiq);
						liqComLp.setLiqComercio(liquidacionCom);
						liquidacionCom.getDetalles().add(liqComLp);
					}
				}
			}
			return true;
		}


		public BigDecimal getTotalImpuestos(LiqComercio liq) {
			calculoRetencion(liq);
			liq.setTotalImpuestos(liq.getTotalImpuestos().setScale(2, BigDecimal.ROUND_HALF_DOWN));
			return liq.getTotalImpuestos().setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}


		private void calculoRetencion(LiqComercio liq) {
			liq.setTotalImpuestos(new BigDecimal(0));
			try {
				BigDecimal sumaRetenciones = new BigDecimal(0);
				if (!tablaRetenciones.isEmpty()) {
					log.info("Tengo retenciones");
					Iterator iterTReten = tablaRetenciones.iterator();
					while (iterTReten.hasNext()) {
						RetencionUtil retencionUtil = (RetencionUtil) iterTReten.next();
						retencionUtil.setTotalOP(liq.getImporteNeto());
						System.out.println("La actual retencion es de: " + retencionUtil.getCalculoRetencion());
						sumaRetenciones = sumaRetenciones.add(retencionUtil.getCalculoRetencion());
					}
				}
				// si la retencion es superior al total de la OP devuelvo el moto de la OP
				if (sumaRetenciones.compareTo(liq.getImporteNeto()) > 0) {
					liq.setTotalImpuestos(liq.getImporteNeto());
				} else {
					liq.setTotalImpuestos(sumaRetenciones);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("Estado de totalImpuestos (Retenciones): " + liq.getTotalImpuestos());
		}


		public String verCupones(ActionEvent event) {
			HttpServletRequest request = Session.getRequest();
			error.borrar();
			popupReport = new String("");
			Integer inte = new Integer(1);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?id_lista_precio_comercio=" + this.listaPrecioParaLiquidar.getListaPrecio().getIdListaprecios();
			String p2 = "&fecha_inicio="
					+ dateFormat.format(this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getFechaInicioPeriodoLiq());
			String p3 = "&fecha_fin=" + dateFormat.format(this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getFechaFinPeriodoLiq());
			String page = request.getContextPath() + "/report/CuponesRechazados.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
			log.info(popupReport);
			return null;
		}


		public ListaPrecioParaLiquidar getListaPrecioParaLiquidar() {
			return listaPrecioParaLiquidar;
		}


		public void setListaPrecioParaLiquidar(
				ListaPrecioParaLiquidar listaPrecioParaLiquidar) {
			this.listaPrecioParaLiquidar = listaPrecioParaLiquidar;
		}


		public boolean isSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}


		public Long getIdListaPrecioALiq() {
			return idListaPrecioALiq;
		}


		public void setIdListaPrecioALiq(Long idListaPrecioALiq) {
			this.idListaPrecioALiq = idListaPrecioALiq;
		}


		public boolean isEstadoAlerta() {
			return estadoAlerta;
		}


		public void setEstadoAlerta(boolean estadoAlerta) {
			this.estadoAlerta = estadoAlerta;
		}


		public boolean isEstadoCorrecto() {
			return estadoCorrecto;
		}


		public void setEstadoCorrecto(boolean estadoCorrecto) {
			this.estadoCorrecto = estadoCorrecto;
		}


		public boolean isEstadoIncorrecto() {
			return estadoIncorrecto;
		}


		public void setEstadoIncorrecto(boolean estadoIncorrecto) {
			this.estadoIncorrecto = estadoIncorrecto;
		}


		public Collection getIdCodComercios() {
			return idCodComercios;
		}


		public void setIdCodComercios(Collection idCodComercios) {
			this.idCodComercios = idCodComercios;
		}

	}


	public boolean isTodos() {
		return todos;
	}


	public void setTodos(boolean todos) {
		this.todos = todos;
	}


	public Long getIdFechaLiquidacionSeleccionada() {
		return idFechaLiquidacionSeleccionada;
	}


	public void setIdFechaLiquidacionSeleccionada(
			Long idFechaLiquidacionSeleccionada) {
		this.idFechaLiquidacionSeleccionada = idFechaLiquidacionSeleccionada;
	}


	public List getListaComerciosAdheridos() {
		return listaComerciosAdheridos;
	}


	public void setListaComerciosAdheridos(List listaComerciosAdheridos) {
		this.listaComerciosAdheridos = listaComerciosAdheridos;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public boolean isHayParaLiquidar() {
		return !listasPrecios.isEmpty();
	}


	public void setHayParaLiquidar(boolean hayParaLiquidar) {
		// this.hayParaLiquidar = hayParaLiquidar;
	}


	public String getPdfDesde() {
		return pdfDesde;
	}


	public void setPdfDesde(String pdfDesde) {
		this.pdfDesde = pdfDesde;
	}


	public String getPdfHasta() {
		return pdfHasta;
	}


	public void setPdfHasta(String pdfHasta) {
		this.pdfHasta = pdfHasta;
	}

}

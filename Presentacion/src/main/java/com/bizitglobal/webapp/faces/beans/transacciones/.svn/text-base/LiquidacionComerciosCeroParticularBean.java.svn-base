package com.bizitglobal.webapp.faces.beans.transacciones;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqComercioException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
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
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioParaLiquidar;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PdfLiqComercio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.RetencionUtil;
import com.bizitglobal.webapp.faces.util.GeneradorDeInforme;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;


@SuppressWarnings({"rawtypes","unchecked"})
public class LiquidacionComerciosCeroParticularBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LiquidacionComerciosCeroParticularBean.class); 
	private String focoHidden;


    private	String rutaLiquidacion;
    private CodComercio comercioALiquidar;
    private ListaPrecio listaPrecioALiquidar;
    private ListaPrecioParaLiquidar listaPrecioParaLiquidar;
    
    
    
    private List listaDeIdTransaccionesALiquidar;
    private List movimientosPendientes;
    private boolean todos; // Un booleano que indica si estan todos seleccionados las listas de precios.

	private List tablaRetenciones;
	private GestorLiquidacionCom gestor; // El objeto que gestiona toda la liquidacion al comercio.
	//Para ver los cupones problematicos
 	private String popupReport = new String("");
 	//Una variable que guarda el monto minimo de cheques.
 	private double montoMinimoParaCheques;
    //Una variable que guarda el cargo por transferencia Bancaria.
 	private double cargoTransferenciaBancaria;
    //Una variable que guarda el cargo por transferencia Cheques.	
 	private double cargoTransferenciaCheque;
	
 	// un array que me va guardando los pagos para cada liquidacion, de manera de poder persistirlos y presentarlos en el pdf.
 	LiqComPago[] pagosFuturos = null;
 	ConceptoGen conceptoLiquidacionFondos = null;
 	
 	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
 	
 	private String codigoPosnetBuscado = "";
 	
 	
	public LiquidacionComerciosCeroParticularBean() {
		error.borrar();
	}
 
	public String inicializar() {
		log.info("Ejecutando ==> inicilizando el bean de Liquidacion de comercios.");
		borrar();
		
		return "liquidacionParticularCeroComercios";
	}
	
	

	
	public boolean validar() {
		error.borrar();
		
		return (error.cantidad() == 0) ? true: false;
	}
	
	public String buscarCodigoComercio() {
		Long codigoPosnetComercioBuscado;
		CodComercio comercio = null;
		try {
			movimientosPendientes = new ArrayList();
			codigoPosnetComercioBuscado = Long.valueOf(codigoPosnetBuscado);
			List codComer = transaccionesService.getCodComercioService().getCodComercio(new Filtro("codigoPosnet", Filtro.LIKE, "" + codigoPosnetComercioBuscado));
			Iterator codIter = codComer.iterator();
			while (codIter.hasNext()) {
				CodComercio cod = (CodComercio)codIter.next();
				if (Long.valueOf(cod.getCodigoPosnet()).longValue() == codigoPosnetComercioBuscado.longValue()) {
					log.info("El cuit es: " + cod.getSucEmpresa().getEmpresa().getCuit());
					cod.getSucEmpresa().getDomicilio().getCalleNombre();

					comercio = cod;
					break;
				}
			}
			Iterator iter = comercio.getComercioListaPrecioSet().iterator();
			while (iter.hasNext()) {
				ComercioListaPrecio comLisPre = (ComercioListaPrecio)iter.next();
				listaPrecioALiquidar = comLisPre.getListaPrecio();
				listaPrecioALiquidar.armarListaPrecio();
/*@I19*/		if (comLisPre.getCodigoPosnet() >= 0 ) {
					break;
/*@F19*/		}	
				
			}
			comercioALiquidar = comercio;
			
			List ctacte = transaccionesService.getCtaCteComercioService().getCeroMovimientoCtaCte(comercioALiquidar.getIdCodComercio());
						
			Collections.sort(ctacte, new Comparator() {

				public int compare(Object o1, Object o2) {
					return ((CtaCteComercio)o1).getFechaFacturacion().compareTo(((CtaCteComercio)o2).getFechaFacturacion());
				}
				
			});
			Iterator iterCta = ctacte.iterator();
			while (iterCta.hasNext()) {
				movimientosPendientes.add(new WrapperMovimientoPendiente(false, (CtaCteComercio)iterCta.next()));
			}
			
		} catch(NumberFormatException e) {
			error.agregar("El codigo de comercio ingresado no es válido");
		} catch (CodComercioException e) {
			error.agregar("El comercio no presenta datos correctos. No se ha podido recuperar el comercio.");
		} catch (CtaCteComercioException e) {
			error.agregar("La cuenta Corriente del comercio no pudo leerse.");
		} catch (NullPointerException e) {
			error.agregar("El comercio no existe.");
		}
		return null;
	}
	

	public String liquidarListas() {
		error.borrar();
		listaDeIdTransaccionesALiquidar = new ArrayList();
		Iterator iteMov = movimientosPendientes.iterator();
		while (iteMov.hasNext()) {
			WrapperMovimientoPendiente wra = (WrapperMovimientoPendiente)iteMov.next();
			if (wra.isSeleccionado()) {
				listaDeIdTransaccionesALiquidar.add((wra).getCtaCteComercios().getIdTranascciones());
			}
		}
		if (listaDeIdTransaccionesALiquidar.isEmpty()) {
			error.agregar("No se ha seleccionado ningun movimiento a liquidar.");
			return null;
		}
		Concepto conceptoCargoTransfBancaria = null;
		Concepto conceptoCargoTransfCheque= null;
		Concepto conceptoMontoMinimoCheque = null;
		Concepto conceptoTotalLiquidacionComercio = null;
		try {
			popup.borrar();
			popupReport = new String("");
			conceptoLiquidacionFondos = (ConceptoGen)(generalService.getConceptoGenService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(420)))).get(0);
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
				//recupero el concepto de Transferencia Bancaria Comercios (codigo 10), para obtener la lista de precios que utiliza
				conceptoCargoTransfBancaria = (Concepto)transaccionesService.getConceptoService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(10))).get(0);
				conceptoCargoTransfBancaria.armarConcepto();
				conceptoCargoTransfBancaria.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception("Error al intentar utilizar el Concepto Transferencia Bancaria Comercios (Codigo 10). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				//recupero el concepto de Cargo Emision Cheques (codigo 11), para obtener la lista de precios que utiliza
				conceptoCargoTransfCheque = (Concepto)transaccionesService.getConceptoService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(11))).get(0);
				conceptoCargoTransfCheque.armarConcepto();
				conceptoCargoTransfCheque.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception("Error al intentar utilizar el Concepto Cargo Emision Cheques (codigo 11). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}	
			try {
				//recupero el concepto de Emisión Cheques Monto Minimo (codigo 12), para obtener la lista de precios que utiliza
				conceptoMontoMinimoCheque = (Concepto)transaccionesService.getConceptoService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(12))).get(0);
				conceptoMontoMinimoCheque.armarConcepto();
				conceptoMontoMinimoCheque.armarReglaConcepto();
			} catch (Exception e) {
				throw new Exception("Error al intentar utilizar el Concepto Emisión Cheques Monto Mínimo (codigo 12). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			try {
				//recupero el concepto de Total Liquidacion de Comercios (codigo 13), responsable de impactar el total de la liquidacion en la ctacte comercio.
				conceptoTotalLiquidacionComercio = (Concepto)transaccionesService.getConceptoService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(13))).get(0);
			} catch (Exception e) {
				throw new Exception("Error al intentar utilizar el Concepto Total Pagos Comercio (Codigo 13). Este debe tener asociado la lista de precio y debe encuentre en vigencia.");
			}
			
	        // leo el concepto transferencia bancaria.
			ConceptoDetalle conDet = null;
			try {
				Concepto con = (Concepto)transaccionesService.getConceptoService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(250))).get(0);
				Iterator iterCon = con.getConceptoDetalleSet().iterator();
				while (iterCon.hasNext()) {
					conDet = (ConceptoDetalle)iterCon.next();
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
				Filtro filtro = new Filtro("fecha",Filtro.MAYOR,Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
				cal.add(Calendar.MONTH, 6);
				filtro.agregarCampoOperValor("fecha",Filtro.MENOR_IGUAL,Filtro.getTO_DATE(new Timestamp(cal.getTimeInMillis())));
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
		    	ParametroSistemaDetalle det = (ParametroSistemaDetalle)iter.next();
		    	if (det.getIdParametroSistemaDetalle()==1) {
		    		iva = Double.valueOf(det.getValor());
		    		break;
		    	}
		    }
		    
		    
		    	
		    	    
					//recupero uno de los comercios de esta lista
					Long comercioActual = comercioALiquidar.getIdCodComercio();
					Date fecha = Calendar.getInstance().getTime();
					listaPrecioParaLiquidar = new ListaPrecioParaLiquidar(listaPrecioALiquidar, fecha);
					Calendar fechaAL = Calendar.getInstance();
					fechaAL.add(Calendar.DATE,1);
					WrapperListaPrecio wrapListaPrecio = new WrapperListaPrecio(listaPrecioParaLiquidar, true, fechaAL.getTime());
				

					try {
						CodComercio comercio = transaccionesService.getCodComercioService().leerCodComercio(comercioALiquidar.getIdCodComercio());
						comercio.getSucEmpresa().getDomicilio().getCalleNombre();
//					    CodComercio comercio = comercioALiquidar;
						
						// se  lee el monto minimo para cheques, y el cargo por transferencia bancaria a partir de la tabla de parametros del sistema
/*@I4016*/						montoMinimoParaCheques = 0;
						cargoTransferenciaBancaria = 0;
						cargoTransferenciaCheque = 0;
						// recupero los montos necesarios, que le corresponden a este comercio en particular, a partir de los conceptos.
						// Solo si no esta excluido
						if (comercio.getExcluyeCargoPago().equals("N")) {
							try {
								//DetallesCargoTransfBancaria: aqui tengo que enviarle el id del comercio....
								Set detallesCargoTransfBancaria = conceptoCargoTransfBancaria.getListaPrecio(comercioActual).getVersionVigente().getDetallesListaPrecio();
								Iterator listaCargoTransfBancaria = detallesCargoTransfBancaria.iterator();
								while (listaCargoTransfBancaria.hasNext()) {
									ListaPrecioDetalle var = (ListaPrecioDetalle)listaCargoTransfBancaria.next();
									cargoTransferenciaBancaria = var.getMonto().doubleValue();
									break;
								}
							} catch (Exception e) {
								throw new Exception("Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
							}
							try {
								//DetallesCargoTransfCheque: aqui tengo que enviarle el id del comercio....
								Set detallesCargoTransfCheque = conceptoCargoTransfCheque.getListaPrecio(comercioActual).getVersionVigente().getDetallesListaPrecio();
								Iterator listaCargoTransfCheque = detallesCargoTransfCheque.iterator();
								while (listaCargoTransfCheque.hasNext()) {
									ListaPrecioDetalle var = (ListaPrecioDetalle)listaCargoTransfCheque.next();
									cargoTransferenciaCheque = var.getMonto().doubleValue();
									break;
								}
							} catch (Exception e) {
								throw new Exception("Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
							}
/*@F4016*/						}
						try {
							//DetallesCargoTransfBancaria: aqui tengo que enviarle el id del comercio....
							Set detallesMontoMinimoCheque = conceptoMontoMinimoCheque.getListaPrecio(comercioActual).getVersionVigente().getDetallesListaPrecio();
							Iterator listaMontoMinimoCheque = detallesMontoMinimoCheque.iterator();
							while (listaMontoMinimoCheque.hasNext()) {
								ListaPrecioDetalle var = (ListaPrecioDetalle)listaMontoMinimoCheque.next();
								montoMinimoParaCheques = var.getMonto().doubleValue();
								break;
							}
						} catch (Exception e) {
							throw new Exception("Se produjo un error al intentar leer el monto de Cargo Transferencia Bancaria de la lista de precio correspondiente.");
						}
						
						Liquidacion liqui = null; 
						// Decidimos si liquida por cuit o por codComercio
						
								
							// liquida por codigo comercio en los casos de liquidacion Particular.
							liqui = new Liquidacion();
							liqui.setIdLiquidacion(new Long(transaccionesService.getLiquidacionService().getMaximoId().longValue()+1));
							liqui.setListaFeriados(listaDiasNoLaborables);
							liqui.setCuit(String.valueOf(comercio.getSucEmpresa().getEmpresa().getCuit()));
							liqui.setFechaLiquidacion(wrapListaPrecio.fechaLiq);
							wrapListaPrecio.liquidar(comercio, liqui); // este metodo liquida.
							
							boolean tieneDatos = false;
								Iterator it = liqui.getLiqComercios().iterator();
								Long ultimoId = transaccionesService.getLiqComercioService().getMaximoId();
								int incremento = 1;
								while (it.hasNext()) {
									LiqComercio lc = (LiqComercio)it.next();
									
									tieneDatos = true;
									lc.setIdLiqComercio(new Long(ultimoId.longValue()+incremento));
									incremento++;
							}
							
							if (tieneDatos) {
								// para formar los pagos
								LiqComercio liqC = (LiqComercio)liqui.getLiqComercios().toArray()[0];
								
								List fpago = null;
								ComercioFormaPago formaPago = null;
								Operador operador = null;
								// recupero la forma de pago de ela empresa o comercio.
								try {
									fpago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(new Filtro("codComercio.idCodComercio",Filtro.IGUAL,comercio.getIdCodComercio()));
								    formaPago = (ComercioFormaPago)fpago.get(0);	
								    formaPago.getFormaPago().getDescripcion();
								    formaPago.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial();
								    operador = new Operador();
									operador = Session.getOperador();
									
								} catch (ComercioFormaPagoException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IndexOutOfBoundsException e2) {
									throw new Exception("El comercio de código " + comercio.getCodigoPosnet() + " que se intento liquidar no posee medio de pago asociado.");
								}
								
								
								
//								 Guardo el objeto Liquidacion, y luego retomo la lista actua y sigo recorriendo los comercios.
								try {
									transaccionesService.getLiquidacionService().grabarLiquidacion(liqui);
								} catch (Exception e) {
									e.printStackTrace();
									popup.setPopup(popup.ICONO_FALLA, "No se logró finalizar la liquidación.");
									return "";
								}
								
								
								boolean esAcreditacionBancaria = (formaPago.getFormaPago().getIdFormaPago().intValue()==3 || formaPago.getFormaPago().getIdFormaPago().intValue()==4);
								boolean esCheque = formaPago.getFormaPago().getIdFormaPago().intValue()==2;
								
								LiqComercio lComPrincipal = null;
								
								// seteo en el objeto Liquidacion el total de las tres cosas importantes: arancel, aceleramiento e iva.
								Iterator iteCom = liqui.getLiqComercios().iterator();
								while (iteCom.hasNext()) {
									LiqComercio lCom = (LiqComercio)iteCom.next();
									liqui.setAceleramientoTotal(liqui.getAceleramientoTotal().add(lCom.getAceleramientoNeto()));
									liqui.setArancelTotal(liqui.getArancelTotal().add(lCom.getArancelNeto()));
									liqui.setIvaNeto(liqui.getIvaNeto().add(lCom.getIvaNeto()));
//									liqui.setMontoAplicableRetencion(liqui.getMontoAplicableRetencion().add(lCom.getTotalDeRetenciones()));
									lComPrincipal = lCom;
									break;
								}
								double cargos = 0;
								// Se verifica si se le excluyen los cargos
/*@I4016*/								Boolean escluye = false;
/*@I4016*/								if (comercio.getExcluyeCargoPago().equals("N") && (escluye) ) {
/*@F4016*/		                            if (esAcreditacionBancaria) {
		                            	cargos = cargoTransferenciaBancaria;
		       							generarCargoTransferenciaBancaria(comercio, conceptoCargoTransfBancaria, operador, liqC,wrapListaPrecio.getListaPrecioParaLiquidar().getFechaLiquidacion());
		                            } else {
		                            	if (esCheque) {
		                            		cargos = cargoTransferenciaCheque;
		                            		// ha de ser Cheque. Se cobra un cargo si el cheque es no cruzado.
		                                	//	CtaCteComercio ctaCteComer = new CtaCteComercio(null,"N",String.valueOf(conDet.getCtacontabledebe()), String.valueOf(conDet.getCtacontablehaber()),"C", liqui.getFechaLiquidacion(), liqui.getFechaLiquidacion(), null,conDet,liqC,null,operador.getCodigo(),new Long(0),new Long(0),null,new BigDecimal(cargoTransferenciaBancaria), new Long(0),new Integer(1),"0", new Integer(1), new Timestamp(Calendar.getInstance().getTime().getTime()),liqC.getCodComercio(), null,new Integer(1),null);
		                            		generarCargoTransferenciaCheques(comercio, conceptoCargoTransfCheque, operador, liqC,wrapListaPrecio.getListaPrecioParaLiquidar().getFechaLiquidacion());
		                            	} else {
		                            		//ha de ser efectivo
		                                	// creo el objeto CtaCteComercio, con el monto de transferencia bancaria. Se cobra un solo monto por transferencia bancaria, indistintamente del numero de pagos.
		                                	CtaCteComercio ctaCteComer = new CtaCteComercio(null,"N",String.valueOf(conDet.getCtacontabledebe()), String.valueOf(conDet.getCtacontablehaber()),"C", liqui.getFechaLiquidacion(), liqui.getFechaLiquidacion(), null,conDet,liqC,null,operador.getCodigo(),new Long(0),new Long(0),null,new BigDecimal(cargoTransferenciaBancaria).setScale(2,BigDecimal.ROUND_HALF_DOWN), new Long(0),new Integer(1),"0", new Integer(1), new Timestamp(Calendar.getInstance().getTime().getTime()),liqC.getCodComercio(), null,new Integer(1),null);
		                            	}
		                            }
								}

//	   					    	Una vez finalizado todo, le agrego el set de retenciones
								Iterator itBis = liqui.getLiqComercios().iterator();
								while (itBis.hasNext()) {
									tieneDatos = true;
									LiqComercio li = (LiqComercio)itBis.next();
									li.setLiquidacion(liqui);
									Double dou = Double.valueOf(cargos + cargos*iva);
									agregarRetenciones(lComPrincipal, comercio, dou);
								}
								//liqui.setMontoAplicableRetencion(liqui.getMontoAplicableRetencion().add(lComPrincipal.getTotalDeRetenciones()));
								liqui.setMontoAplicableRetencion(new BigDecimal(0));
								
								lComPrincipal.setArancelNeto(liqui.getArancelTotal());
								lComPrincipal.setAceleramientoNeto(liqui.getAceleramientoTotal());
								lComPrincipal.prepararLiqComercio(iva, lComPrincipal.getTotalDeRetenciones(),BigDecimal.valueOf(cargos).negate().setScale(2,BigDecimal.ROUND_HALF_DOWN));

								Long idTran = transaccionesService.getCtaCteComercioService().getSequenciaTransaccion();
								Iterator iterDeRetenciones = lComPrincipal.getRetenciones().iterator();
								while (iterDeRetenciones.hasNext()) {
									LiqComRetencion ret = (LiqComRetencion)iterDeRetenciones.next();
									ConceptoDetalle conDe = transaccionesService.getConceptoDetalleService().leerConceptoDetalle(ret.getRetencion().getIdConceptoDetalle());
									
							        transaccionesService.getRetencionAComercioService().registrarRetencionAComercio(lComPrincipal.getCodComercio(), conDe, idTran, Session.getOperador(), lComPrincipal, wrapListaPrecio.getListaPrecioParaLiquidar().getFechaLiquidacion(), ret.getMonto());
								}
								
								// impacto en la cuentacte comercio el monto total de la liquidacion.
								generarTotalLiquidacionComercio(comercio, conceptoTotalLiquidacionComercio, operador, liqC, wrapListaPrecio.getListaPrecioParaLiquidar().getFechaLiquidacion());
								
								
	                            pagosFuturos = liqui.getPagosDeLaLiquidacion(liqui.getMontoTotal(),lComPrincipal.getArancelNeto(), lComPrincipal.getAceleramientoNeto(),  montoMinimoParaCheques, (new BigDecimal(cargos + cargos*0.21D).setScale(2,BigDecimal.ROUND_HALF_DOWN)).doubleValue(),lComPrincipal.getTotalDeRetenciones(), formaPago.getFormaPago().getDescripcion(), formaPago.getCodCuentaDeposito(), wrapListaPrecio.getListaPrecioParaLiquidar().getListaPrecio().getVersionVigente().isDifiereLiquidacionBoolean());
	                            
	                            for (int g=0; g<pagosFuturos.length; g++) {
	                                 liqui.getPagos().add(pagosFuturos[g]);
	                            }
	                            
								Iterator iterLiqC = liqui.getLiqComercios().iterator();
								while (iterLiqC.hasNext()) {
									LiqComercio liquidacionComercio = (LiqComercio)iterLiqC.next();
									
									Iterator lp = liquidacionComercio.getDetalles().iterator();
									while (lp.hasNext()) {
										LiqComercioLP liqLp = (LiqComercioLP)lp.next();
										log.info("Actualizare la tabla t_vis_tra_ctacte_comercios");
						    			transaccionesService.getGestorLiquidacionComService().actualizarRegistrosCtaCteComercioParticular(liqLp.getListaPrecio().getIdListaprecios().intValue(), liquidacionComercio.getCodComercio().getIdCodComercio(),liquidacionComercio.getIdLiqComercio(), listaDeIdTransaccionesALiquidar);
									}
								}
			
						    	
						    	
						    		// genero un pdf por cada codComercio.  (no se tiene en cuenta si es cuit o codComercio porque 
						    		Iterator iterLi = liqui.getLiqComercios().iterator();
						    		LiqComercio comAGenerar = (LiqComercio)iterLi.next();
						    		comAGenerar.setCargos(BigDecimal.valueOf(cargos).setScale(2,BigDecimal.ROUND_HALF_DOWN));
						    		if (comAGenerar.getPagos()==null) comAGenerar.setPagos(new HashSet());
						    		comAGenerar.getPagos().addAll(liqui.getPagos());
/*@F19*/		//					impactarEnFondos(formaPago, comAGenerar);
									comAGenerar.setLiquidacion(liqui);
					    			generar(comAGenerar,esCheque);		   
//						    		if (comAGenerar.getImporteNeto().intValue()!=0) {
//						    			
//						    			// solo lo genero si el monto es distinto de cero, si no seria un reporte en blanco.
//						    			comAGenerar.setLiquidacion(liqui);
//						    			generar(comAGenerar,esCheque);
//						    		}

							}


						
					} catch (CodComercioException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error.agregar("Error:"+e.getMessage());
						return "";
					} catch (PlanCuentaDosException e) {
						error.agregar("La cuenta de fondos de la forma de pago del comercio puede no estar seteada correctamente.");
					}

				
				//momento de guardar la ListaPrecioParaLiquidar
				transaccionesService.getListaPrecioParaLiquidarService().grabarListaPrecioParaLiquidar(wrapListaPrecio.getListaPrecioParaLiquidar());
				List listaRemover = new ArrayList();
				Iterator iteMovFinal = movimientosPendientes.iterator();
				while (iteMovFinal.hasNext()) {
					WrapperMovimientoPendiente wra = (WrapperMovimientoPendiente)iteMovFinal.next();
					if (wra.isSeleccionado()) {
						listaRemover.add(wra);
					}
				}
				movimientosPendientes.removeAll(listaRemover);
		    popup.setPopup(popup.ICONO_OK, "La liquidacion se realizó con exito.");
		} catch (Exception e) {
			error.agregar("Error:"+e.getMessage());
			e.printStackTrace();
		}
	    
        return "";
	}
	

	/**
	 *  Metodo que  impacta el cargo total de la liquidacion del comercio...
	 * */
	public boolean generarTotalLiquidacionComercio(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio, Date fechaLiquidacion) {
		try {
			transaccionesService.getLiquidacionService().generarTotalLiquidacionComercio(codComercio, concepto, operador, liqComercio,fechaLiquidacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}
	
	/**
	 * Impacta el asiento, el asiento item, el movimiento, el movimiento MP, el cheque historial y el cheque.
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
		
		//itero los conceptos detalle y asigno los valores a detalle con cuenta unica
		Iterator iter = detallesConcepto.iterator();
		while(iter.hasNext()) {
			ConceptoDetalleGen det = (ConceptoDetalleGen)iter.next();
			if (det.getOrden().intValue()==0) {
				detalleConCuentaUnica = det;
				break;
			}
		}
		
		// creo el asiento Fondos.
		AsientoFondos asientoFondos = new AsientoFondos();
		asientoFondos.setConcepto(conceptoLiquidacionFondos.getDescripcion());   // hablar con Gustavo que descripcion poner.
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
		PlanCuentaDos planCuentaDos = (PlanCuentaDos)contabilidadService.getPlanCuentaDosService().leerPlanCuenta(detalleConCuentaUnica.getCtacontable());
		asientoItemCabecera.setPlanCuenta(planCuentaDos);
		asientoItemCabecera.setIdPlanCuenta(planCuentaDos.getIdPlanCuenta());
		
		// creo el asiento item con el medio de pago de la liquidacion.
		AsientoItem asientoItemMedioPago = new AsientoItem();
		asientoItemMedioPago.setAsiento(asientoFondos);
		asientoItemMedioPago.setNroRenglon(new Integer(2));
		asientoItemMedioPago.setSigno(Integer.valueOf(detalleConCuentaUnica.getSigno().intValue()*-1));
		PlanCuentaDos planCuentaDosBis = (PlanCuentaDos)contabilidadService.getPlanCuentaDosService().leerPlanCuenta(comercioFormaPago.getNroCuentaFondos());
		asientoItemMedioPago.setLeyenda(planCuentaDosBis.getTitulo());
		asientoItemMedioPago.setImporte(liqComercio.getTotalDePagos().doubleValue());
		asientoItemMedioPago.setPlanCuenta(planCuentaDosBis);
		asientoItemMedioPago.setIdPlanCuenta(planCuentaDosBis.getIdPlanCuenta());
		
		if (asientoFondos.getAsientosItems()==null) asientoFondos.setAsientosItems(new HashSet());
		asientoFondos.getAsientosItems().add(asientoItemCabecera);
		asientoFondos.getAsientosItems().add(asientoItemMedioPago);
		

		
		//impactaremos el Movimiento y el movimiento MP
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
			
		if (movimiento.getMovimientosMP()==null) movimiento.setMovimientosMP(new HashSet());
		movimiento.getMovimientosMP().add(movimientoMP);
			
		// para cada uno de los pagos....
		Iterator ite = liqComercio.getPagos().iterator();
		int contador = -1;
		while (ite.hasNext()) {
			contador++;
			LiqComPago liqCP = (LiqComPago)ite.next();
			
			//.....creo el Cheque en fondos y su respectivo cheque historial.
			Cheque cheque = new Cheque();
			ChequeHistorial chequeHistorial = new ChequeHistorial();
			
			Filtro filtro = new Filtro("banco.idBanco", Filtro.IGUAL, planCuentaDosBis.getCodBco());
			filtro.agregarCampoOperValor("numeroCuenta", Filtro.LIKE, planCuentaDosBis.getCodCtaBco().trim());
			BancoPropio bancoPropio = (BancoPropio)fondosService.getBancoPropioService().getBancoPropios(filtro).get(0);
			if (comercioFormaPago.getFormaPago().getIdFormaPago().compareTo(new Long(2))==0) {
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
			//recupero la empresa
			
			cheque.setFechaEmision(fecha);
			cheque.setTipoCuentaBanco(comercioFormaPago.getTipoCuentaBanco());
			cheque.setFechaPago(liqCP.getFecha());
			cheque.setEsCruzado(comercioFormaPago.getEsChequeCruzado().toCharArray()[0]);
			cheque.setNoOrden(comercioFormaPago.getChequeNoAlaOrden().toCharArray()[0]);
			cheque.setImporte(Double.valueOf(liqCP.getImporte().doubleValue()));
			cheque.setCodRed(null);
			cheque.setConciliado('N');
			cheque.setProcesado('N');
			
			cheque.setBancoPropio(bancoPropio);
			cheque.setCodigoPostal("" + bancoPropio.getPlaza());
			
			chequeHistorial.setCheque(cheque);
			chequeHistorial.setTimestamp(Calendar.getInstance().getTime());
			ChequeEstado estado = fondosService.getChequeEstadoService().leerChequeEstado(new Long(1));
			chequeHistorial.setChequeEstado(estado);
			chequeHistorial.setMovimientoMP(movimientoMP);
			chequeHistorial.setAsientoItem(asientoItemMedioPago);
			
			if (movimientoMP.getChequeHistorial()==null) movimientoMP.setChequeHistorial(new HashSet());
			movimientoMP.getChequeHistorial().add(chequeHistorial);
		}
		
		// Grabo el asiento y los asientos item.
		liqComercio.setMovimiento(movimiento);
		try {
		     transaccionesService.getLiqComercioService().actualizarLiqComercio(liqComercio);
		} catch (LiqComercioException e) {
			 // Se borra la ultima liqComercio, y ademar, se dispara el informe del error.
			 transaccionesService.getGestorLiquidacionComService().borrarLiqComercio(liqComercio);
		     throw new LiqComercioException("Hubo un problema al intentar impactar en fondos. Compruebe que el comercio COD POSNET = " + liqComercio.getCodComercio().getCodigoPosnet() + " tenga su medio de pago asociado, y presente datos correctos.");	
		}
		log.info("IMPACTO ASIENTO Y ASIENTO ITEM, Movimiento, movimientoMp, cheque y cheque historial.");
		
	}
	
	/**
	 *  Metodo que  impacta el cargo por transferencia bancaria.
	 * */
	public boolean generarCargoTransferenciaBancaria(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio, Date fechaLiquidacion) {
		try {
			transaccionesService.getCargoTransferenciaBancariaService().registrarCargoTransferenciaBancaria(codComercio, concepto, operador, liqComercio, fechaLiquidacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}
	
	/**
	 *  Metodo que  impacta el cargo por emitir cheques.
	 * */
	public boolean generarCargoTransferenciaCheques(CodComercio codComercio, Concepto concepto, Operador operador, LiqComercio liqComercio, Date fechaLiquidacion) {
		try {
			transaccionesService.getCargoTransferenciaChequeService().registrarCargoTransferenciaCheque(codComercio, concepto, operador, liqComercio,fechaLiquidacion);
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
		tituloCorto= "Liquidación Particular Cero de Comercios";
		codigoPosnetBuscado = "";
		comercioALiquidar = new CodComercio();
		comercioALiquidar.setSucEmpresa(new SucEmpresa());
		comercioALiquidar.getSucEmpresa().setEmpresa(new Empresa());
		comercioALiquidar.getSucEmpresa().getEmpresa().setRazonSocial("No hay comercio Seleccionado");
		comercioALiquidar.setCodigoPosnet(" ");
		listaDeIdTransaccionesALiquidar = new ArrayList();
		movimientosPendientes = new ArrayList();
	}

	public String cancelar() {
		borrar();
		return "inicio";
	}
	
	public Set componerRetenciones(List tablaRetenciones, LiqComercio liquidacionComercio, BigDecimal totalRetenible){
		if (totalRetenible==null) totalRetenible= new BigDecimal(0);
		BigDecimal resto = new BigDecimal(totalRetenible.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
//		System.out.println("Entro a componer retencion");
		log.info("el total retenible es: " + resto);
		Set retencionesSet = new HashSet();
		if (!tablaRetenciones.isEmpty()) {
			//System.out.println("Adentro de la retencion");
			boolean tengoResto = true;
			if (totalRetenible.doubleValue()>0) {
				tengoResto = true;
			} else {
				tengoResto = false;
			}
			Iterator iterRet = tablaRetenciones.iterator();
			while (iterRet.hasNext()) {
				//System.out.println("hay retencion");
				RetencionUtil retencionUtil = (RetencionUtil) iterRet.next();
				BigDecimal montoRet = retencionUtil.getCalculoRetencion();
				montoRet.setScale(2,BigDecimal.ROUND_HALF_DOWN);
				log.info("el calculo de la retencion fue de :" + montoRet);
				if (retencionUtil.getRetencion().getIdRetencion() != null
						&& montoRet.compareTo(new BigDecimal(0)) != 0 && tengoResto ) {
//					System.out.println("creo el objeto liq com retencion");
					LiqComRetencion liqComRetencion = new LiqComRetencion();
					liqComRetencion.setLiquidacion(liquidacionComercio);
					liqComRetencion.setRetencion(retencionUtil.getRetencion());
					liqComRetencion.setMonto(montoRet);
					
					// Voy restando las retenciones
					resto = resto.subtract(montoRet);
					if (resto.doubleValue() <= 0 ) {
							//liqComRetencion.setMonto(resto.add(montoRet));
							tengoResto = false;
					} else {
						liqComRetencion.setMonto(montoRet);
					}
					liqComRetencion.setPorcAplicado(retencionUtil.getTramo().getPorcRetencion());
					if(retencionUtil.getExclusion().getIdExclusion() != null){
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
	
	public Set componerRetenciones(List tablaRetenciones, Liquidacion liquidacion, BigDecimal totalRetenible) {
		if (totalRetenible==null) totalRetenible= new BigDecimal(0);
		BigDecimal resto = new BigDecimal(totalRetenible.doubleValue());
		resto.setScale(2,BigDecimal.ROUND_HALF_DOWN);

		Set retencionesSet = new HashSet();
		if (!tablaRetenciones.isEmpty()) {
			boolean tengoResto = true;
			Iterator iterRet = tablaRetenciones.iterator();
			while (iterRet.hasNext()) {
				RetencionUtil retencionUtil = (RetencionUtil) iterRet.next();
				BigDecimal montoRet = retencionUtil.getCalculoRetencion();
				montoRet.setScale(2,BigDecimal.ROUND_HALF_DOWN);
				System.out.println("el calculo de la retencion fue de :" + montoRet);
				if (retencionUtil.getRetencion().getIdRetencion() != null
						&& montoRet.compareTo(new BigDecimal(0)) != 0 && tengoResto ) {
					log.info("creo el objeto liq com retencion");
					LiqComRetencion liqComRetencion = new LiqComRetencion();
					liqComRetencion.setLiquidacionCuit(liquidacion);
					liqComRetencion.setRetencion(retencionUtil.getRetencion());
					liqComRetencion.setMonto(montoRet);
					
					// Voy restando las retenciones
					resto = resto.subtract(montoRet);
					if (resto.compareTo(new BigDecimal(0)) <= 0 ) {
							//liqComRetencion.setMonto(resto.add(montoRet));
							tengoResto = false;
					} else {
						liqComRetencion.setMonto(montoRet);
					}
					liqComRetencion.setPorcAplicado(retencionUtil.getTramo().getPorcRetencion());
					if(retencionUtil.getExclusion().getIdExclusion() != null){
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
	 * @param cargos La suma de todos los cargos con sus intereses e iva si los tendria. (ej, acreditacion bancaria e iva acreditacion bancaria)
	 * */
	public void agregarRetenciones(LiqComercio liqComercio, CodComercio codComercio, Double cargos) {
    	tablaRetenciones = new ArrayList();
    	// aqui calculo las retenciones.... una vez que ya tengo todos los montos calculados....
    	BigDecimal montoAplicableRetencion = new BigDecimal(0);
        try {
        	//primero recupero el total del importe de la liquidacion:
        	double montoTotal = 0;
        	double montoNeto = 0;
        	double arancelTotal = 0;
        	double aceleramientoTotal = 0;
        	Iterator lp = liqComercio.getDetalles().iterator();
        	while (lp.hasNext()) {
        		LiqComercioLP varLp = (LiqComercioLP)lp.next();
        		montoTotal += varLp.getTotalBruto().doubleValue();
        		montoNeto += varLp.getTotalNeto().doubleValue();
        		arancelTotal += varLp.getTotalArancel().doubleValue();
        		aceleramientoTotal += varLp.getTotalAceleramiento().doubleValue();
        	}
        	liqComercio.setImporteNeto(new BigDecimal(montoTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        	liqComercio.setArancelNeto(new BigDecimal(arancelTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        	liqComercio.setAceleramientoNeto(new BigDecimal(aceleramientoTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        	// construir los service  para genDetParam y recuperar el valor del iva....!

        	
        	/*@I10*/    //   	BigDecimal ivaNeto = (liqComercio.getArancelNeto().add(liqComercio.getAceleramientoNeto())).multiply(new BigDecimal(0.21));
        	 /*@F10*/       	BigDecimal ivaNeto = liqComercio.getIvaNeto();
        	liqComercio.setIvaNeto(ivaNeto.setScale(2,BigDecimal.ROUND_HALF_DOWN));
        	log.info("El monto iva neto es: "  + ivaNeto);
        	log.info("el monto neto es: " + montoNeto);
            montoAplicableRetencion = new BigDecimal(montoNeto).add(ivaNeto).add(BigDecimal.valueOf(cargos.doubleValue()).negate());
            log.info("el montoAplicableRetencion es: " + montoAplicableRetencion);
            
            // este metodo no esta trayendo bien las cosas, trae todo en null
        	List retencionList = transaccionesService.getLiqComercioService().getRetenciones(codComercio);
        	if (!retencionList.isEmpty()) {
				Iterator iterRetencion = retencionList.iterator();
				while (iterRetencion.hasNext()) {
					Object[] retComp = (Object[]) iterRetencion.next();
					//System.out.println("A la retencion la calculare sobre el monto de : " + montoTotal);
					RetencionUtil ret = new RetencionUtil((Categoria)retComp[0], (Retencion)retComp[1], (Exclusion)retComp[2], montoAplicableRetencion.setScale(2,BigDecimal.ROUND_HALF_DOWN), new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_DOWN), new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_DOWN)); // en los tres ultimos deben ir; totalNeto, totalPagosMes y totalRetencionesMes
					tablaRetenciones.add(ret);
					log.info("agregue retencion....");
				}
			}
        	log.info("tenemos " + tablaRetenciones.size() + " retenciones");
        	
        }  catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        liqComercio.setRetenciones(componerRetenciones(tablaRetenciones, liqComercio, montoAplicableRetencion));
        // los calculos siguientes son para setear la cantidad de Pago.
        BigDecimal totalDeRetenciones = BigDecimal.valueOf(0);
        
        Iterator retenciones = liqComercio.getRetenciones().iterator();
        while (retenciones.hasNext()) {
        	LiqComRetencion ret = (LiqComRetencion)retenciones.next();
        	totalDeRetenciones = totalDeRetenciones.add(ret.getMonto());
        }
        liqComercio.setTotalDeRetenciones(totalDeRetenciones.negate().setScale(2,BigDecimal.ROUND_HALF_DOWN));
    }
	
	
	public String generar(LiqComercio liquidacionComercio, boolean esCheque) {
		error.borrar();
		popupReport = new String("");
	    HttpServletRequest request = Session.getRequest();
		
			// recupero el objeto empresa y el objeto Codigo comercio
			CodComercio codComercio = liquidacionComercio.getCodComercio();
			SucEmpresa sucEmpresa = liquidacionComercio.getCodComercio().getSucEmpresa();
			Empresa empresa = liquidacionComercio.getCodComercio().getSucEmpresa().getEmpresa();
			
			String nombreLiq = rutaLiquidacion+ File.separator + (esCheque ? "C":"A") + formatoFecha.format(liquidacionComercio.getLiquidacion().getFechaLiquidacion()) + "_" + liquidacionComercio.getLiquidacion().getIdLiquidacion() + "_" + ("00000" + codComercio.getCodigoPosnet()).substring(("00000" + codComercio.getCodigoPosnet()).length() - 5, ("00000" + codComercio.getCodigoPosnet()).length());

			String p0 = "?guardarEn="+nombreLiq;
			String p1 = "ƒliquidacion_numero="+liquidacionComercio.getIdLiqComercio();
			String p2 = "ƒresponsable_comercio="+empresa.getRazonSocial();
			String p3 = "ƒURLImagen= "+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			
			String p4 = "ƒtotal_transacciones="+liquidacionComercio.getImporteNeto();
			
			String p5 = "ƒarancel="+liquidacionComercio.getArancelNeto();
			
			String p6 = "ƒaceleramiento="+liquidacionComercio.getAceleramientoNeto();
			String p16 = "ƒcargos_varios="+liquidacionComercio.getCargos().negate();
			// controlar el siguiente renglon...
			Calendar cal = Calendar.getInstance();
			cal.setTime(liquidacionComercio.getLiquidacion().getFechaLiquidacion());
			String p7 = "ƒfecha_liquidacion="+cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE);
			
			
			// Comento lo que estaba antes del Nombre Comercial y
			// Lo replazo por el nombre de la Sucursal
			//String p8 = "ƒnombre_comercial="+empresa.getRazonSocial();
			String p8 = "ƒnombre_comercial="+sucEmpresa.getLabel();
						
			String p9 = "ƒdomicilio_legal="+sucEmpresa.getDomicilio().getCalleNombre() + " " + sucEmpresa.getDomicilio().getCalleNumero();
			String p10 = "ƒcodigo_comercio="+codComercio.getCodigoPosnet();
			// controlar el siguiente Renglon
			String p11 = "ƒcuit="+liquidacionComercio.getLiquidacion().getCuit();
			String p12 = "ƒcategoria_iva="+" ";
			
			// Comento lo anterior de la insc_ing_brutos
			//String p13 = "ƒinsc_ing_brutos="+" ";
			// Asigno el numero de ingreso bruto revisar
			String p13 = "ƒinsc_ing_brutos="+codComercio.getInscripcionDgr();
			
			String p14 = "ƒiva=" + liquidacionComercio.getIvaNeto();
			
			// debo hacer un bucle, y las retenciones que no existen, no se las paso, y recordar que se mostraran solamente 6 retenciones.
			int numeroDeRetencion = 0;
			String retenciones = "";
			Iterator iterRetenciones = liquidacionComercio.getRetenciones().iterator();
			while (iterRetenciones.hasNext()) {
				LiqComRetencion liqRet = (LiqComRetencion)iterRetenciones.next();
				numeroDeRetencion++;
				retenciones += "ƒret" + numeroDeRetencion + "Descripcion=" + liqRet.getRetencion().getDescripcion();
				retenciones += "ƒret" + numeroDeRetencion + "Importe=-" + liqRet.getMonto();
			}
			
			
//			 debo hacer un bucle, y los pagos que no existen, no se los paso.!
//			int numeroDePagos= 0;
			String pagos = "";
			int contadorPagos = 0;
			Iterator iterPag = liquidacionComercio.getLiquidacion().getPagos().iterator();
			while (iterPag.hasNext()) {
				LiqComPago lcomp =(LiqComPago)iterPag.next();
				contadorPagos++;
				Calendar cale = Calendar.getInstance();
				cale.setTime(lcomp.getFecha());
				pagos = pagos + "ƒpago" + contadorPagos + "Fecha=" + cale.get(Calendar.YEAR) + "-" + (cale.get(Calendar.MONTH)+1) + "-" + cale.get(Calendar.DATE) + "ƒpago" + contadorPagos + "Importe=" + lcomp.getImporte()+ "ƒpago" + contadorPagos + "Descripcion=" + lcomp.getDescripcion();
			}
			
//			 debo hacer un bucle, y le paso los periodos de liquidacion que existen!
			int numeroDePeriodos = 0;
			String periodos = "";
			Iterator iterPeriodos= liquidacionComercio.getDetalles().iterator();
			while (iterPeriodos.hasNext()) {
				LiqComercioLP liqLp = (LiqComercioLP)iterPeriodos.next();
				numeroDePeriodos++;
				
				periodos += "ƒliq" + numeroDePeriodos + "Descripcion= ";
			}
			periodos += "ƒliq" + numeroDePeriodos + "Descripcion= "+" ";
			
			String p15 = "ƒtotal_liquidacion="+liquidacionComercio.getTotalDePagos();
			
//			String p17 = "ƒURLLogo1="+Session.getHomePath()+"/img/fiel/fiel_grises_web.jpg";
//			
//			log.info("URLLogo1 " + p17);
			
			String page = request.getContextPath() + "/report/LiquidacionCodComercio.jrxml";
//			popupReport = "popup('"+page+p0+p1+p2+p3+p4+p5+p6+p7+p9+p10+p11+p12+p13+p14+retenciones+pagos+periodos+"',1000,600)";
			GeneradorDeInforme gen = new GeneradorDeInforme();
			try {
				log.info("la cadena que se envia es: " + page+p0+p1+p2+p3+p4+p5+p6+p7+p8+p9+p10+p11+p12+p13+p14+retenciones+pagos+periodos+p15);




			//	Double totalLiquidacion, Double totalTransacciones, String urlImagen
				nombreLiq = nombreLiq.replace(System.getProperty("catalina.home"), "").replace("/webapps", "").substring(1);
				PdfLiqComercio pdfLiqComercio  = new PdfLiqComercio(liquidacionComercio.getAceleramientoNeto(),liquidacionComercio.getArancelNeto(),
						liquidacionComercio.getCargos().negate(), " ",BigDecimal.valueOf(Long.valueOf(codComercio.getCodigoPosnet()).longValue()),
						liquidacionComercio.getLiquidacion().getCuit(),sucEmpresa.getDomicilio().getCalleNombre() + " " + sucEmpresa.getDomicilio().getCalleNumero(),
						cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE),nombreLiq,
						" ", liquidacionComercio.getIvaNeto(),empresa.getRazonSocial(), pagos,
						periodos,empresa.getRazonSocial(),retenciones,liquidacionComercio.getTotalDePagos(),liquidacionComercio.getImporteNeto(),Session.getHomePath()+"/img/fiel/fondos_01.jpg",liquidacionComercio.getLiquidacion());
				
				pdfLiqComercio.setLiquidacionCero("SI");
				
				transaccionesService.getPdfLiqComercioService().grabarPdfLiqComercio(pdfLiqComercio);
				gen.guardarReporte(page+p0+p1+p2+p3+p4+p5+p6+p7+p8+p9+p10+p11+p12+p13+p14+p16+retenciones+pagos+periodos+p15);
				popupReport = "popup('"+page+"?"+p1.substring(1)+p2.replace("ƒ", "&")+p3.replace("ƒ", "&")+p4.replace("ƒ", "&")+p5.replace("ƒ", "&")+p6.replace("ƒ", "&")+p7.replace("ƒ", "&")+p8.replace("ƒ", "&")+p9.replace("ƒ", "&")+p10.replace("ƒ", "&")+p11.replace("ƒ", "&")+p12.replace("ƒ", "&")+p13.replace("ƒ", "&")+p14.replace("ƒ", "&")+p16.replace("ƒ", "&")+retenciones.replace("ƒ", "&")+pagos.replace("ƒ", "&")+periodos.replace("ƒ", "&")+p15.replace("ƒ", "&")+"',1000,600)";
				
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
	
	/**
	 * Crea el directorio de las liquidaciones en la posición especificada por los atributos del contexto.properties,
	 * de la siguiente forma: directorioArchivos/directorioLiquidacionesClientes/carpeta con dd-mm-yyyy
	 * */
	private String crearDirectoriosLiquidacion() throws Exception {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		PropertieFile prop = new PropertieFile(System.getProperty("catalina.home") +"/webapps/contexto.properties");
		
	        String nombreLiq = System.getProperty("catalina.home") + File.separator + prop.getProperties("directorioArchivos") +  File.separator
	        +prop.getProperties("directorioLiquidacionesComercios");
	        File directorio = new File(nombreLiq);
	        if (!directorio.exists()) {
		    	if (!directorio.mkdir()) throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		    }	
	        nombreLiq +=   File.separator + "ParticularesCero"; 
		    File directorioInterno = new File(nombreLiq);
		    if (!directorioInterno.exists()) {
		    	if (!directorioInterno.mkdir()) throw new Exception("No se ha podido crear el directorio para almacenar las liquidaciones.");
		    }
		    return nombreLiq;
	}
			

	public String getPopupReport() {
		return popupReport;
	}

	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}

	public void setHayParaLiquidar(boolean hayParaLiquidar) {
//		this.hayParaLiquidar = hayParaLiquidar;
	}

	public class WrapperListaPrecio {

		private Long idListaPrecioALiq;
		private ListaPrecioParaLiquidar listaPrecioParaLiquidar;
		private Date fechaLiq;
		private boolean seleccionado;

		
		
		
		public WrapperListaPrecio(ListaPrecioParaLiquidar listaParaLiquidar, boolean seleccion, Date fechaLiq) {
			this.listaPrecioParaLiquidar = listaParaLiquidar;
			this.idListaPrecioALiq = listaPrecioParaLiquidar.getListaPrecio().getIdListaprecios();
			this.seleccionado = seleccion;
			this.fechaLiq = fechaLiq;
		}
		
		
		/**
		 * este metodo modifica el objeto liquidacion de tal manera que lo devuelve contemplando el comercio que fue pasado.
		 * */
        public boolean liquidar(CodComercio codComer, Liquidacion liquidacion) {		
			// liquidar la lista que contiene este objeto.
        	gestor = new GestorLiquidacionCom();
			Set listaItems = listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getItemsListaPrecio();
			int[] cuotas = new int[listaItems.size()];
			int i = 0;
		    Iterator ite = listaItems.iterator();
		    while (ite.hasNext()) {
		    	ListaPrecioItem lpi = (ListaPrecioItem)ite.next();
		    	cuotas[i] = lpi.getComCuotas().intValue();
		    	i++;
		    }
		    // inicialmente traigo todas las cuotas de la base con sus respectivos montos. Elimino los que no coincidan con la fecha en el caso 
		    // que difiera de liquidacion....
            if (listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().isDifiereLiquidacionBoolean()) {
            	//el proceso si difiere liquidacion
    			//el siguiente es el set de elementos LiqComercioDet, deben ser seteados a los elementos LiqComercioLP
    			Set detallesLiq = transaccionesService.getGestorLiquidacionComService().liquidarCuotaCeroParticular(this.listaPrecioParaLiquidar.getListaPrecio(), codComer.getIdCodComercio(), this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getIdListaPrecioVersion(),listaDeIdTransaccionesALiquidar, this.fechaLiq);
    			LiqComercio liquidacionCom = null;
    			Iterator iter = liquidacion.getLiqComercios().iterator();
    			while (iter.hasNext()) {
    				LiqComercio liqCom = (LiqComercio)iter.next();
    				if (liqCom.getCodComercio().getCodigoPosnet().compareTo(codComer.getCodigoPosnet())==0) {
    					liquidacionCom = liqCom;
    					break;
    				}
    			}
    			LiqComercio liquidacionComercio = null;
    			// si la liquidacionComercio para esta liquidacion aun no existe la crea, si no le agrega elementos al set LiquidacionLP
    			if (liquidacionCom==null) {
    				//no existia aun
    				if (detallesLiq!=null) {
	    				liquidacionComercio = gestor.liquidarComerciosParticular(this.listaPrecioParaLiquidar, codComer, codComer.getSucEmpresa().getEmpresa().getCuit().toString() , this.fechaLiq, detallesLiq);
	    				//le agregamos al objeto Liquidacion el objeto LiquidacionComercio
	    				liquidacion.getLiqComercios().add(liquidacionComercio);
    				}
    			} else {
    				if (detallesLiq!=null) {
	    				// ya existia la liquidacionComercio, para otra lista, por lo tanto le agregamos la lista actual
	    				LiqComercioLP liqComLp = gestor.armarLPParticular(this.listaPrecioParaLiquidar, liquidacionCom.getCodComercio(), String.valueOf(liquidacionCom.getCodComercio().getSucEmpresa().getEmpresa().getCuit()) , this.fechaLiq, detallesLiq);
	    				liqComLp.setLiqComercio(liquidacionCom);
	    				liquidacionCom.getDetalles().add(liqComLp);
    				}
    			}
            	
            	
            } else {
            	log.info("La lista precio no difiere liquidacion");
    			//el siguiente es el set de elementos LiqComercioDet, deben ser seteados a los elementos LiqComercioLP
    			Set detallesLiq = transaccionesService.getGestorLiquidacionComService().liquidarCuotaCeroParticular(this.listaPrecioParaLiquidar.getListaPrecio(), codComer.getIdCodComercio(), this.listaPrecioParaLiquidar.getListaPrecio().getVersionVigente().getIdListaPrecioVersion(),listaDeIdTransaccionesALiquidar, this.fechaLiq);
    			LiqComercio liquidacionCom = null;
    			Iterator iter = liquidacion.getLiqComercios().iterator();
    			while (iter.hasNext()) {
    				LiqComercio liqCom = (LiqComercio)iter.next();
    				if (liqCom.getCodComercio().getCodigoPosnet().compareTo(codComer.getCodigoPosnet())==0) {
    					liquidacionCom = liqCom;
    					break;
    				}
    			}
    			LiqComercio liquidacionComercio = null;
    			// si la liquidacionComercio para esta liquidacion aun no existe la crea, si no le agrega elementos al set LiquidacionLP
    			if (liquidacionCom==null) {
    				//no existia aun
    				if (detallesLiq!=null) {
    					log.info("1" + listaPrecioParaLiquidar);
    					log.info("2" + codComer);
    					log.info("3" + codComer.getSucEmpresa().getEmpresa().getCuit().toString());
    					log.info("4" + detallesLiq);
	    				liquidacionComercio = gestor.liquidarComerciosParticular(this.listaPrecioParaLiquidar, codComer, codComer.getSucEmpresa().getEmpresa().getCuit().toString() , this.fechaLiq, detallesLiq);
	    				//le agregamos al objeto Liquidacion el objeto LiquidacionComercio
	    				liquidacion.getLiqComercios().add(liquidacionComercio);
    				}
    			} else {
    				if (detallesLiq!=null) {
	    				// ya existia la liquidacionComercio, para otra lista, por lo tanto le agregamos la lista actual
	    				LiqComercioLP liqComLp = gestor.armarLPParticular(this.listaPrecioParaLiquidar, liquidacionCom.getCodComercio(), String.valueOf(liquidacionCom.getCodComercio().getSucEmpresa().getEmpresa().getCuit()) , this.fechaLiq, detallesLiq);
	    				liqComLp.setLiqComercio(liquidacionCom);
	    				liquidacionCom.getDetalles().add(liqComLp);
    				}
    			}
            }
			return true;
		}
        
		public BigDecimal getTotalImpuestos(LiqComercio liq) {
			calculoRetencion(liq);
			liq.setTotalImpuestos(liq.getTotalImpuestos().setScale(2,BigDecimal.ROUND_HALF_DOWN));
			return liq.getTotalImpuestos().setScale(2,BigDecimal.ROUND_HALF_DOWN);
		}

		private void calculoRetencion(LiqComercio liq) {
			liq.setTotalImpuestos(new BigDecimal(0));
			try {
				BigDecimal sumaRetenciones = new BigDecimal(0);
				if (!tablaRetenciones.isEmpty()) {
					Iterator iterTReten = tablaRetenciones.iterator();
					while (iterTReten.hasNext()) {
						RetencionUtil retencionUtil = (RetencionUtil) iterTReten.next();
						retencionUtil.setTotalOP(liq.getImporteNeto());
						sumaRetenciones = sumaRetenciones.add(retencionUtil.getCalculoRetencion());
					}
				}
//				si la retencion es superior al total de la OP devuelvo el moto de la OP
				if (sumaRetenciones.compareTo(liq.getImporteNeto()) > 0) {
					liq.setTotalImpuestos(liq.getImporteNeto());
				}else {
					liq.setTotalImpuestos(sumaRetenciones); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		
	}

	public class WrapperMovimientoPendiente {
		private boolean seleccionado;
	    private CtaCteComercio ctaCteComercios;
	    
	    public WrapperMovimientoPendiente(boolean esta, CtaCteComercio cta) {
	    	seleccionado = esta;
	    	ctaCteComercios = cta;
	    	
	    }

		public boolean isSeleccionado() {
			return seleccionado;
		}

		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado =seleccionado;
		}

		public CtaCteComercio getCtaCteComercios() {
			return ctaCteComercios;
		}

		public void setCtaCteComercios(CtaCteComercio ctaCteComercios) {
			this.ctaCteComercios = ctaCteComercios;
		}
	    
	    
	    
	}

	public CodComercio getComercioALiquidar() {
		return comercioALiquidar;
	}

	public void setComercioALiquidar(CodComercio comercioALiquidar) {
		this.comercioALiquidar = comercioALiquidar;
	}

	public List getMovimientosPendientes() {
		return movimientosPendientes;
	}

	public void setMovimientosPendientes(List movimientosPendientes) {
		this.movimientosPendientes = movimientosPendientes;
	}

	public String getCodigoPosnetBuscado() {
		return codigoPosnetBuscado;
	}

	public void setCodigoPosnetBuscado(String codigoPosnetBuscado) {
		this.codigoPosnetBuscado = codigoPosnetBuscado;
	}
	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	
}

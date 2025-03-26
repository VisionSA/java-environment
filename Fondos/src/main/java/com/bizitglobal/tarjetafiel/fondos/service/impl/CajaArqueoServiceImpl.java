package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoItemDao;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaAperturaDao;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaArqueoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaCierreDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoMPDao;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaArqueoDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaArqueoException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaArqueoNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaArqueo;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaCierre;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovXCuentaXConcepto;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.CajaArqueoService;
import com.bizitglobal.tarjetafiel.general.dao.ConceptoGenDao;
import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaException;
import com.bizitglobal.tarjetafiel.general.impresion.ImpresionTickets;
import com.bizitglobal.tarjetafiel.general.impresion.RespuestaImpresion;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.general.service.ParametroSistemaService;
import com.xml.ArmarXmlTicket;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class CajaArqueoServiceImpl implements CajaArqueoService {
	
	private Logger logger = Logger.getLogger(CajaArqueoServiceImpl.class);
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CajaArqueoDao cajaArqueoDao;
	private CajaCierreDao cajaCierreDao;
	private AsientoItemDao asientoItemDao;
	private MovimientoMPDao movimientoMPDao;
	private MovimientoDao movimientoDao;
	private CajaAperturaDao cajaAperturaDao;
	private ConceptoGenDao conceptoGenDao;
	private ParametroSistemaService parametroSistemaService1;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	 
	public void grabarCajaArqueo(final CajaArqueo unaCajaArqueo) throws CajaArqueoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaArqueoDao.grabarCajaArqueo(unaCajaArqueo);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La CajaArqueo ya existe en la base de datos.";
			throw new CajaArqueoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaArqueo no pudo ser grabada.";
			throw new CajaArqueoException(msg,e);
		}
		
	}
	/**
	 * 
	 * @param caja idCaja
	 * @param idApertura apertura vigente 
	 * @param cajaArqueoList lista de arqueos
	 * @return lista de cierres de caja una por medio de pago
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public RespuestaImpresion procesarArqueo(final CajaApertura cajaApertura, final List<CajaCierre> cajaCierreList, final Impresora impresora,List<Cheque> chequesEnCaja) throws Exception{
		// Se cargan los parametros necesarios
		final Double limiteMontoAbsorve; //Si falla al recuperar el parametro toma 5.
		try {
			limiteMontoAbsorve = new Double(parametroSistemaService1.buscarValot(ParametroSistema.MONTO_LIMITE_ERROR_CAJERO, ParametroSistemaDetalle.MONTOLIMITEERRORCAJERO_MONTOLIMITE_DOUBLE));
		} catch (NumberFormatException e1) {
			throw e1;
		} catch (ParametroSistemaException e1) {
			throw e1;
		}		

		try{
			final ArmarXmlTicket armarXmlTicket = new ArmarXmlTicket();
			armarXmlTicket.addCajaHeader(cajaApertura.getCaja().getIdCaja().toString());
			armarXmlTicket.addFechaHeader(new Date());
			armarXmlTicket.addHoraHeader(new Date());
	/*@I7936*/		armarXmlTicket.addOperadorHeader(cajaApertura.getOperador().getCodigo() + " " +
	/*@F7936*/				cajaApertura.getOperador().getApellido() + ", " + cajaApertura.getOperador().getNombre());
			armarXmlTicket.addFechaAperturaHeader(cajaApertura.getFechaApertura());
			armarXmlTicket.addHoraAperturaHeader(cajaApertura.getFechaApertura());
			final Movimiento mov = new Movimiento();
			for(Cheque cheque : chequesEnCaja){
				armarXmlTicket.addChequeEnCajaCierre(cheque.getNumero(), cheque.getTipo().toString(), new BigDecimal(cheque.getImporte().doubleValue()));
			}
			
			return (RespuestaImpresion)transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					// TODO Auto-generated method stub
					List list = cajaArqueoDao.buscarTotalesMovimientos(cajaApertura.getCaja().getIdCaja(), cajaApertura.getIdCajaApertura());
					HashMap<Long, Double> totales = new HashMap<Long, Double>(); 
					double total = 0;
					double diferencia = 0;

					HashMap<Long, List<MovXCuentaXConcepto>> hashMapMov = new HashMap<Long, List<MovXCuentaXConcepto>>();
					
					for(Object o : list){
						MovXCuentaXConcepto movXCuentaXConcepto = (MovXCuentaXConcepto)o;
						if(totales.containsKey(movXCuentaXConcepto.getId().getIdPlanCuenta())){
							double totalParcial = totales.get(movXCuentaXConcepto.getId().getIdPlanCuenta());
							totales.put(movXCuentaXConcepto.getId().getIdPlanCuenta(), totalParcial + movXCuentaXConcepto.getTotal());
						} else {
							totales.put(movXCuentaXConcepto.getId().getIdPlanCuenta(), movXCuentaXConcepto.getTotal());
							hashMapMov.put(movXCuentaXConcepto.getId().getIdPlanCuenta(), new ArrayList<MovXCuentaXConcepto>());
						}			
						
						hashMapMov.get(movXCuentaXConcepto.getId().getIdPlanCuenta()).add(movXCuentaXConcepto);
						
						//total += movXCuentaXConcepto.getTotal();
					}
					
					Set<Long> idPlanCuentaSet = totales.keySet();
					String tipo = "";
					
					for(CajaCierre cajaCierre : cajaCierreList){
						//por el momento no hay que hacer nada con los cheques en el arqueo
						tipo = cajaCierre.getTipo().toString();									
						
								if(totales.get(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta()) != null && 
										cajaCierre.getCaja().getFormaPago().getIdFormaPago() != 2 && 
										cajaCierre.getCaja().getFormaPago().getIdFormaPago() != 6 &&
										cajaCierre.getCaja().getFormaPago().getIdFormaPago() != 11){				
								
										cajaCierre.setTotalContable(totales.get(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta()));
										cajaCierre.setDiferencia(0D);
										if(cajaCierre.getTotalArqueo().doubleValue() != totales.get(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta()).doubleValue()){									
											cajaCierre.setDiferencia( cajaCierre.getTotalArqueo() - cajaCierre.getTotalContable());
										} 								
									
									Element cuenta = armarXmlTicket.addCuentaCierre(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta() + " - " + 
											cajaCierre.getCaja().getPlanCuentaDos().getTitulo(),cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta().toString());								
									//armo el detalle de los movimientos por concepto por cuenta
									//en el ticket								
									for(MovXCuentaXConcepto movXCuentaXConcepto : hashMapMov.get(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta())){									
										armarXmlTicket.addMedioCuentaCierre(cuenta, movXCuentaXConcepto.getId().getIdConcepto().toString(),
												movXCuentaXConcepto.getConcepto(), new BigDecimal(movXCuentaXConcepto.getTotal()).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString(), movXCuentaXConcepto.getTotal() > 0 ? "DB" : "CR");																								
									}

									Object[] cajaArqueos = cajaCierre.getCajaArqueosList().toArray();
									Arrays.sort(cajaArqueos, new Comparator<Object>(){
										public int compare(Object o1,
												Object o2) {

											if(((CajaArqueo)o1).getFormaPagoValor().getMultiplo().doubleValue() > ((CajaArqueo)o2).getFormaPagoValor().getMultiplo().doubleValue()){
												return 1;
											} else if (((CajaArqueo)o1).getFormaPagoValor().getMultiplo().doubleValue() < ((CajaArqueo)o2).getFormaPagoValor().getMultiplo().doubleValue()){
												return -1;
											}
											
											return 0;
										}
									});
									
									for(Object cajaArqueo : cajaArqueos){
										armarXmlTicket.addArqueoItemCuentaCierre(cuenta, ((CajaArqueo)cajaArqueo).getFormaPagoValor().getDescripcion(), ((CajaArqueo)cajaArqueo).getCantidad().toString());
									}
									
									armarXmlTicket.addSaldoFinalCuenta(cuenta, new BigDecimal(cajaCierre.getTotalContable()).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString());
									armarXmlTicket.addTotalArqueo(cuenta, new BigDecimal(cajaCierre.getTotalArqueo()).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString());
									
									String porcentajeDif = "";
									if(cajaCierre.getDiferencia() != 0){										
										double diferenciaPorcentaje = cajaCierre.getDiferencia() < 0 ? cajaCierre.getDiferencia() * -1 : cajaCierre.getDiferencia(); 
										if (cajaCierre.getTotalContable() != 0)
										{
											diferenciaPorcentaje = (diferenciaPorcentaje * 100) / cajaCierre.getTotalContable();
										}
										porcentajeDif = new BigDecimal(diferenciaPorcentaje).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
									}
									
									if(cajaCierre.getDiferencia() < 0){
										armarXmlTicket.addFaltanteSobranteArqueo(cuenta,"FALTANTE % " + porcentajeDif + "  " + new BigDecimal(cajaCierre.getDiferencia()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
									} else if (cajaCierre.getDiferencia() > 0){
										armarXmlTicket.addFaltanteSobranteArqueo(cuenta,"SOBRANTE % " + porcentajeDif + "  " + new BigDecimal(cajaCierre.getDiferencia()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
									} else {
										armarXmlTicket.addFaltanteSobranteArqueo(cuenta,"CAJA SIN DIFERENCIA " + "% 0.00   -   0.00");
									}
								
									cajaCierreDao.grabarCajaCierre(cajaCierre);
								}
						
					}
					
					armarXmlTicket.addTipoCierre(tipo.toUpperCase());
					
					//Si el cierre es de tipo X lo inserto

					if(tipo.toUpperCase().equals(CajaCierre.CIERRE_Z.toString().toUpperCase())){
						//antes de registrar el cierre tengo registrar los movimientos de ajuste
						//y cerrar la caja
						
						Caja caja = null;
						HashSet movimientosMP = new HashSet();
						for(CajaCierre cajaCierre : cajaCierreList){
							//por el momento no hay que hacer nada con los cheques en el arqueo
							if(cajaCierre.getCaja().getFormaPago().getIdFormaPago() != 2){
								caja = cajaCierre.getCaja().getCaja();
								
								MovimientoMP movimientoMP = new MovimientoMP();
								movimientoMP.setFormaPago(cajaCierre.getCaja().getFormaPago());							
								
								if(cajaCierre.getDiferencia().doubleValue() != 0){
									if(cajaCierre.getDiferencia().doubleValue() > 0){
										guardarAjustes(cajaApertura, cajaCierre, Long.valueOf(ConceptoGen.CODIGO_FONDO_SOBRANTE_CAJA),armarXmlTicket);
										
									} else {
										if((cajaCierre.getDiferencia().doubleValue() * -1) < limiteMontoAbsorve)
											guardarAjustes(cajaApertura, cajaCierre, Long.valueOf(ConceptoGen.CODIGO_FONDO_FALTANTE_CAJA), armarXmlTicket);
										else
											guardarAjustes(cajaApertura, cajaCierre, Long.valueOf(ConceptoGen.CODIGO_FONDO_ANTICIPO_SUELDO), armarXmlTicket);																	
									}																
								}
								
								
								total += (cajaCierre.getTotalArqueo());
								movimientoMP.setMonto(cajaCierre.getTotalArqueo());
								movimientosMP.add(movimientoMP);
							} 
																					
						}
						
						if(caja!=null){
							//Inserto moviento de cierre							
							mov.setCaja(caja);
							mov.setCajaApertura(cajaApertura);
							mov.setConcepto(conceptoGenDao.buscarConceptoGenXCodigo(Long.valueOf(ConceptoGen.CODIGO_CIERRE)));
							mov.setEstado('C');
							mov.setFecha(new Date());
							mov.setImporte(total);
							mov.setOperador(cajaApertura.getOperador());
							mov.setSigno(-1);
							mov.setMovimientosMP(movimientosMP);
							movimientoDao.grabarMovimiento(mov);
							for(Object movimientoMP : mov.getMovimientosMP()){
								((MovimientoMP)movimientoMP).setMovimiento(mov);
								movimientoMPDao.grabarMovimientoMP((MovimientoMP)movimientoMP);
							}							
						}
						
						cajaApertura.setEstado('C');
						cajaApertura.setFechaCierre(new Date());
						
						cajaAperturaDao.actualizarCajaApertura(cajaApertura);
										
					} 					

					//tengo que imprimir el ticket					
					String mensaje = "";
					RespuestaImpresion respuestaImpresion = new RespuestaImpresion("El comprobante de cierre esta siendo impreso.",cajaCierreList,false);
					ImpresionTickets impresionTickets = new ImpresionTickets();
					try {
						logger.info(armarXmlTicket.getDatosTicket().asXML());						
						mensaje = impresionTickets.imprimirTicket(armarXmlTicket, "cierre.xsl", impresora);
						respuestaImpresion.setTicket(impresionTickets.getTicket());
						if(mov.getIdMovimiento() != null){
							mov.setTicket(impresionTickets.getTicket());
							movimientoDao.actualizarMovimiento(mov);	
						}
						
						
					} catch (Exception e) {
						logger.error(e,e);
						respuestaImpresion.setMensaje(mensaje);
					}
					
					return respuestaImpresion;
				}
			});
			
		}catch (Exception e) {
			logger.error(e,e);
			throw e;
		}
	}
	
	public void guardarAjustes(CajaApertura cajaApertura, CajaCierre cajaCierre, Long codigo,ArmarXmlTicket armarXmlTicket){
		//registrar movimientos y asientos de sobrante
		Movimiento movimiento = new Movimiento();
		movimiento.setCaja(cajaApertura.getCaja());
		movimiento.setCajaApertura(cajaApertura);
		ConceptoGen conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(Long.valueOf(codigo));
		movimiento.setConcepto(conceptoGen);
		movimiento.setEstado('C');
		movimiento.setFecha(new Date());
		movimiento.setImporte(cajaCierre.getDiferencia() < 0 ? (cajaCierre.getDiferencia() *-1) : cajaCierre.getDiferencia());
		movimiento.setOperador(cajaApertura.getOperador());												
		ConceptoDetalleGen conceptoDetalleGen = (ConceptoDetalleGen)conceptoGen.getConceptoDetalleSet().toArray()[0];
		movimiento.setSigno(conceptoDetalleGen.getSigno() * -1);
		movimiento.setMovimientosMP(new HashSet());
		MovimientoMP movimientoMP = new MovimientoMP();
		
		movimientoMP.setAsientoItem(new AsientoItem());
		movimientoMP.setFormaPago(cajaCierre.getCaja().getFormaPago());
		movimientoMP.setMonto(cajaCierre.getDiferencia() < 0 ? (cajaCierre.getDiferencia() *-1) : cajaCierre.getDiferencia());
		movimientoMP.setMovimiento(movimiento);
		
		movimientoMP.getAsientoItem().setAsiento(new AsientoFondos());
		movimientoMP.getAsientoItem().getAsiento().setConcepto(conceptoGen.getDescripcion());
		movimientoMP.getAsientoItem().getAsiento().setCotabilizado('N');
		movimientoMP.getAsientoItem().getAsiento().setFecha(new Date());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		movimientoMP.getAsientoItem().getAsiento().setHora(simpleDateFormat.format(new Date()));
		movimientoMP.getAsientoItem().getAsiento().setOperador(cajaApertura.getOperador());
		
		movimientoMP.getAsientoItem().setIdPlanCuenta(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta());
		movimientoMP.getAsientoItem().setImporte(cajaCierre.getDiferencia() < 0 ? (cajaCierre.getDiferencia() * -1) : cajaCierre.getDiferencia());
		//esto se debe cambiar por leyenda que pase gustavo
		movimientoMP.getAsientoItem().setLeyenda(cajaCierre.getCaja().getCaja().getDescripcion());
		movimientoMP.getAsientoItem().setSigno(conceptoDetalleGen.getSigno() * -1);
		movimientoMP.getAsientoItem().setNroRenglon(2);
		
		AsientoItem asientoItem = new AsientoItem();
		asientoItem.setAsiento(movimientoMP.getAsientoItem().getAsiento());
		asientoItem.setIdPlanCuenta(conceptoDetalleGen.getCtacontable());
		asientoItem.setImporte(cajaCierre.getDiferencia() < 0 ? (cajaCierre.getDiferencia() * -1) : cajaCierre.getDiferencia());
		//esto se debe cambiar por leyenda que pase gustavo
		asientoItem.setLeyenda(conceptoDetalleGen.getNombre());
		asientoItem.setNroRenglon(1);
		asientoItem.setSigno(conceptoDetalleGen.getSigno());
		
		movimientoDao.grabarMovimiento(movimiento);
		movimientoMPDao.grabarMovimientoMP(movimientoMP);
		asientoItemDao.grabarAsientoItem(asientoItem);
/*@I7936*/	DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator(',');
	    simbolos.setGroupingSeparator('.');
		DecimalFormat formateador = new DecimalFormat("##,##0.00",simbolos);
		
		Iterator<Element> iterator = armarXmlTicket.getDatosTicket().getRootElement().element("cierre").elementIterator("cuenta");
		while(iterator.hasNext()){
			Element element = iterator.next();
			if(element.attribute("id").getText().equals(cajaCierre.getCaja().getPlanCuentaDos().getIdPlanCuenta().toString())){
				armarXmlTicket.addAjusteCuentaCierre(element, 
						conceptoGen.getIdConcepto().toString(), 
							conceptoGen.getDescripcion().toString(), 
							formateador.format(new BigDecimal(movimientoMP.getMonto()).setScale(2,BigDecimal.ROUND_HALF_DOWN)), 
/*@I7936*/		     		conceptoDetalleGen.getSigno() > 0 ? "DB" : "CR");
				break;
			}
		}
	}
	
	public List getCajaArqueoes() throws CajaArqueoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaArqueoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaArqueo no pudo ser leida.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	public CajaArqueo leerCajaArqueo(Long id) throws CajaArqueoException {
		try {
			return cajaArqueoDao.buscarCajaArqueo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La cajaArqueo no existe en la base de datos.";
			throw new CajaArqueoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaArqueo no pudo leerse desde la base de datos.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	public void borrarCajaArqueo(final Long idCajaArqueo) throws CajaArqueoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaArqueoDao.borrarCajaArqueo(idCajaArqueo);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaArqueo no pudo borrase.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	public void borrarCajaArqueo(final CajaArqueo unaCajaArqueo) throws CajaArqueoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaArqueoDao.borrarCajaArqueo(unaCajaArqueo);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaArqueo no pudo borrase.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	public void actualizarCajaArqueo(final CajaArqueo unaCajaArqueo) throws CajaArqueoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaArqueoDao.actualizarCajaArqueo(unaCajaArqueo);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaArqueo no pudo actualizarse.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	public List getCajaArqueos(final Filtro filtro) throws CajaArqueoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaArqueoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaArqueo no pudo ser leida.";
			throw new CajaArqueoException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CajaArqueoDao getCajaArqueoDao() {
		return cajaArqueoDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setCajaArqueoDao(CajaArqueoDao cajaArqueoDao) {
		this.cajaArqueoDao = cajaArqueoDao;
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto transactionManager.
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	
	/**
	 * Necesario para spring
	 * @param transactionManager, Objeto a setear.
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	public CajaCierreDao getCajaCierreDao() {
		return cajaCierreDao;
	}
	
	public void setCajaCierreDao(CajaCierreDao cajaCierreDao) {
		this.cajaCierreDao = cajaCierreDao;
	}
	
	
	public MovimientoDao getMovimientoDao() {
		return movimientoDao;
	}
	
	public MovimientoMPDao getMovimientoMPDao() {
		return movimientoMPDao;
	}
	
	public AsientoItemDao getAsientoItemDao() {
		return asientoItemDao;
	}
	
	public void setAsientoItemDao(AsientoItemDao asientoItemDao) {
		this.asientoItemDao = asientoItemDao;
	}
			
	public void setMovimientoDao(MovimientoDao movimientoDao) {
		this.movimientoDao = movimientoDao;
	}
	
	public void setMovimientoMPDao(MovimientoMPDao movimientoMPDao) {
		this.movimientoMPDao = movimientoMPDao;
	}
	
	public CajaAperturaDao getCajaAperturaDao() {
		return cajaAperturaDao;
	}
	
	public void setCajaAperturaDao(CajaAperturaDao cajaAperturaDao) {
		this.cajaAperturaDao = cajaAperturaDao;
	}
	
	public ConceptoGenDao getConceptoGenDao() {
		return conceptoGenDao;
	}
	
	public void setConceptoGenDao(ConceptoGenDao conceptoGenDao) {
		this.conceptoGenDao = conceptoGenDao;
	}
	public void setParametroSistemaService1(ParametroSistemaService parametroSistemaService) {
		this.parametroSistemaService1 = parametroSistemaService;
	}
	public ParametroSistemaService getParametroSistemaService1() {
		return parametroSistemaService1;
	}
}

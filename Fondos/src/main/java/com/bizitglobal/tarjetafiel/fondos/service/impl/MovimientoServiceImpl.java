package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoItemDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeHistorialDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeLugarDao;
import com.bizitglobal.tarjetafiel.fondos.dao.LugarDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoMPDao;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.MovimientoService;
import com.bizitglobal.tarjetafiel.general.dao.ConceptoGenDao;
import com.bizitglobal.tarjetafiel.general.impresion.ImpresionTickets;
import com.bizitglobal.tarjetafiel.general.impresion.RespuestaImpresion;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.xml.ArmarXmlTicket;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */

public class MovimientoServiceImpl implements MovimientoService {
	
	private static Logger logger = Logger.getLogger(MovimientoMPServiceImpl.class);
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private MovimientoDao movimientoDao;
	private AsientoItemDao asientoItemDao;
	private MovimientoMPDao movimientoMPDao;	
	private ConceptoGenDao conceptoGenDao;
	private LugarDao lugarDao;
	private ChequeLugarDao chequeLugarDao;
	private ChequeHistorialDao chequeHistorialDao;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarMovimiento(final Movimiento unMovimiento) throws MovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoDao.grabarMovimiento(unMovimiento);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Movimiento ya existe en la base de datos.";
			throw new MovimientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El Movimiento no pudo ser grabado.";
			throw new MovimientoException(msg,e);
		}
		
	}
	
	public List getMovimientoes() throws MovimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return movimientoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Movimiento no pudo ser leida.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public Movimiento leerMovimiento(Long id) throws MovimientoException {
		try {
			return movimientoDao.buscarMovimiento(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La movimiento no existe en la base de datos.";
			throw new MovimientoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La Movimiento no pudo leerse desde la base de datos.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public void borrarMovimiento(final Long idMovimiento) throws MovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoDao.borrarMovimiento(idMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Movimiento no pudo borrase.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public void borrarMovimiento(final Movimiento unaMovimiento) throws MovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoDao.borrarMovimiento(unaMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Movimiento no pudo borrase.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public void actualizarMovimiento(final Movimiento unaMovimiento) throws MovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoDao.actualizarMovimiento(unaMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Movimiento no pudo actualizarse.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public RespuestaImpresion registrarDescargaValores(final List<CajaMP> cajaMpList,final CajaApertura cajaApertura, final Operador operador, final Impresora impresora) throws Exception{
		try{

			
			return (RespuestaImpresion)transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					
					ConceptoGen conceptoGen = null; 
					
					ArmarXmlTicket armarXmlTicket = new ArmarXmlTicket();
					armarXmlTicket.addFechaHeader(new Date());
					armarXmlTicket.addHoraHeader(new Date());
		/*@F7936*/	armarXmlTicket.addOperadorHeader(operador.getCodigo().toString() + " " + operador.getApellido());	
				    
	    /*@I7936*/	DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
					simbolos.setDecimalSeparator(',');
				    simbolos.setGroupingSeparator('.');
	    /*@F7936*/	DecimalFormat formateador = new DecimalFormat("##,##0.00",simbolos);
				    
				    List<Movimiento> movimientos = new ArrayList<Movimiento>();
					for(CajaMP cajaMP : cajaMpList){
						if(cajaMP.getImporteRetiro() > 0){
							
							armarXmlTicket.addCajaHeader(cajaMP.getCaja().getIdCaja().toString());
							
							Element element = armarXmlTicket.addNuevoDato(armarXmlTicket.getDatosTicket().getRootElement().element("retiros"), "retiro");													
							//ATENCIÓN: Cuando la forma de pago es solamente Depósito Bancario este código no funciona
							//dedibo a que no está contemplado esta forma de pago
							if(cajaMP.getFormaPago().getIdFormaPago() == 1){								
								conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(Long.valueOf(ConceptoGen.CODIGO_FONDO_DESCARGA_VALORES_EFECTIVO));
							} else if(cajaMP.getFormaPago().getIdFormaPago() == 5){								
								conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(Long.valueOf(ConceptoGen.CODIGO_FONDO_DESCARGA_VALORES_TICKETS));
							} else if (cajaMP.getFormaPago().getIdFormaPago() == 2){								
								conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(Long.valueOf(ConceptoGen.CODIGO_FONDO_DESCARGA_VALORES_CHEQUES));
							}
							
							Movimiento movimiento = new Movimiento();
							movimiento.setCaja(cajaMP.getCaja());
							movimiento.setCajaApertura(cajaApertura);
							movimiento.setConcepto(conceptoGen);
							movimiento.setSigno(-1);
							movimiento.setEstado('P');
							movimiento.setFecha(new Date());
							movimiento.setImporte(cajaMP.getImporteRetiro());
							movimiento.setOperador(cajaMP.getCaja().getOperador());
							movimientoDao.grabarMovimiento(movimiento);
							MovimientoMP movimientoMP;
							if(cajaMP.getFormaPago().getIdFormaPago() != 2){							
								
								element.addAttribute("cheque", "false");
								element.addElement("medio").setText(cajaMP.getFormaPago().getDescripcion());
			/*@F7936*/			element.addElement("importe").setText("$"+formateador.format(new BigDecimal(cajaMP.getImporteRetiro()).setScale(2,BigDecimal.ROUND_HALF_DOWN)));			
								movimientoMP = new MovimientoMP();
								movimientoMP.setAsientoItem(new AsientoItem());
								movimientoMP.getAsientoItem().setAsiento(new AsientoFondos());
								movimientoMP.getAsientoItem().getAsiento().setConcepto(conceptoGen.getDescripcion());
								movimientoMP.getAsientoItem().getAsiento().setConcepto("N");
								movimientoMP.getAsientoItem().getAsiento().setFecha(new Date());
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
								movimientoMP.getAsientoItem().getAsiento().setHora(simpleDateFormat.format(new Date()));
								movimientoMP.getAsientoItem().getAsiento().setOperador(cajaMP.getCaja().getOperador());
								movimientoMP.getAsientoItem().setImporte(cajaMP.getImporteRetiro());
								//esto se debe cambiar segun documento de gustavo
								movimientoMP.getAsientoItem().setLeyenda("DESCARGA VALORES");
								movimientoMP.getAsientoItem().setSigno(-1);
								movimientoMP.getAsientoItem().setIdPlanCuenta(cajaMP.getPlanCuentaDos().getIdPlanCuenta());
								movimientoMP.getAsientoItem().setNroRenglon(2);
								movimientoMP.setFormaPago(cajaMP.getFormaPago());
								movimientoMP.setMonto(cajaMP.getImporteRetiro());
								movimientoMP.setMovimiento(movimiento);
								movimientoMPDao.grabarMovimientoMP(movimientoMP);
								AsientoItem asientoItem = new AsientoItem();
								asientoItem.setAsiento(movimientoMP.getAsientoItem().getAsiento());
								Long idPlanCuenta = ((ConceptoDetalleGen)conceptoGen.getConceptoDetalleSet().toArray()[0]).getCtacontable();
								asientoItem.setIdPlanCuenta(idPlanCuenta);
								asientoItem.setImporte(cajaMP.getImporteRetiro());
								//esto se debe cambiar segun documento de gustavo
								asientoItem.setLeyenda("DESCARGA VALORES");
								asientoItem.setSigno(1);
								asientoItem.setNroRenglon(1);
								asientoItemDao.grabarAsientoItem(asientoItem);
							} else if(cajaMP.getFormaPago().getIdFormaPago() == 2){
								
								//cambiar el lugar fisico del cheque
								//generar un movimiento que reste el importe del cheque de la caja
								element.addAttribute("cheque", "true");
								
								double total = 0;
								Lugar lugar = lugarDao.buscarLugarPorCodigo(Lugar.TESORERIA);
								for(Cheque cheque : cajaMP.getDescargaChequesList()){
									
									Element elementCheque = armarXmlTicket.addNuevoDato(element, "cheque");
									elementCheque.addElement("numero").setText(cheque.getNumero());
									elementCheque.addElement("tipo").setText(cheque.getTipo().toString());
	/*@F7936*/						elementCheque.addElement("importe").setText("$"+formateador.format(new BigDecimal(cheque.getImporte()).setScale(2,BigDecimal.ROUND_HALF_DOWN)));																		
									movimientoMP = new MovimientoMP();
									movimientoMP.setFormaPago(cajaMP.getFormaPago());
									movimientoMP.setMonto(cheque.getImporte());
									movimientoMP.setMovimiento(movimiento);
									total += cheque.getImporte();
									movimientoMPDao.grabarMovimientoMP(movimientoMP);
									
									ChequeLugar chequeLugar = new ChequeLugar();
									chequeLugar.setCheque(cheque);
									chequeLugar.setLugar(lugar);
									chequeLugar.setTimestamp(new Date());
									chequeLugarDao.grabarChequeLugar(chequeLugar);
								}
								
								movimiento.setImporte(total);
								//movimientoDao.actualizarMovimiento(movimiento);
								movimientos.add(movimiento);
							}
						}
					}
					
					//imprimir ticket
					ImpresionTickets impresionTickets = new ImpresionTickets();
					RespuestaImpresion respuestaImpresion = new RespuestaImpresion();
					try {
						respuestaImpresion.setFalloImpresion(false);
						impresionTickets.imprimirTicket(armarXmlTicket, "retiros.xsl", impresora);
						respuestaImpresion.setTicket(impresionTickets.getTicket());
						respuestaImpresion.setMensaje("La descarga de valores se realizó con éxito");
						
					} catch (Exception e) {					
						respuestaImpresion.setMensaje("La descarga de valores se realizó con éxito\nError en la impresión del ticket");
						respuestaImpresion.setFalloImpresion(true);
					}
					
					for(Movimiento mov : movimientos){
						mov.setTicket(impresionTickets.getTicket());
						movimientoDao.actualizarMovimiento(mov);
					}
					
					return respuestaImpresion;
				}
			});
			
		}catch (Exception e) {
			logger.error(e,e);
			throw e;
		}
	}

	/**
	 * Genera un movimiento que es la reversiom del recivido 
	 * @param movOriginal
	 * @param fechaAsiento, puede ser la fecha del dia de hoy o de la operacion original.
	 * @param concepto, Por lo especificado siempre deberia ser el mismo. Pero lo deja parametrizado por futuros cambios.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String generarReversion(final Long idMovOriginal, final Date fechaAsiento, final ConceptoGen concepto, final Operador operador)throws MovimientoException{
		String errores = null;
//		hay que tener varias cuestiones a tener en cuenta
//		cuando el movimiento no fue generado manualmente ???? solo este
//		si fue manual e interviene una caja ???? Si se puede 
//		Agregar una descripcion al elegir la fecha
//		estados de los cheques, acreditaciones, etc  ???? Hablar con gustavo
//		pienso que se debe actualizar el asiento original con el asiento que lo revirtio(para poder tener en cuenta que esta revertido)
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					Movimiento movOriginal = movimientoDao.buscarMovimiento(idMovOriginal);
					if (movOriginal.getMovimientosMP() != null) {
						Movimiento movRev = movOriginal;
						Iterator<MovimientoMP> iterator = movOriginal.getMovimientosMP().iterator();
						while (iterator.hasNext()) {
							MovimientoMP movimientoMP = iterator.next();
							if(movimientoMP.getAsientoItem() != null){
								AsientoFondos asiento = movimientoMP.getAsientoItem().getAsiento();
								AsientoFondos asiNuevo = asiento.getClon();
								asiNuevo.setIdAsiento(null);
								asiNuevo.setAsientoRev(asiento);
								asiNuevo.setConcepto(concepto.getDescripcion());
								asiNuevo.setFecha(fechaAsiento);
								asiNuevo.setOperador(operador);
								asiNuevo.setAsientosItems(new HashSet());
								Iterator iterItem = asiento.getAsientosItems().iterator();
								while (iterItem.hasNext()) {
									AsientoItem aItem = (AsientoItem) iterItem.next();
									AsientoItem nuevoItem = aItem.getClon();
									nuevoItem.setIdAsientoItem(null);
									nuevoItem.setChequeHistorial(new HashSet());
									ChequeHistorial nuevoHistorial = new ChequeHistorial();
									if (aItem.getChequeHistorial() != null && !aItem.getChequeHistorial().isEmpty()) {
										ChequeHistorial chequeHistorial = (ChequeHistorial)aItem.getChequeHistorial().iterator().next();
										ChequeHistorial ultimoHistorial = chequeHistorialDao.buscarUltimoHistorial(chequeHistorial.getCheque());
										nuevoHistorial = ultimoHistorial.clone();
										nuevoHistorial.setIdChequeHistorial(null);
										nuevoHistorial.setAsientoItem(nuevoItem);
//										nuevoHistorial.setChequeEstado(chequeEstado); hay que cambiarlo
										nuevoHistorial.setTimestamp(new Date());
										nuevoItem.getChequeHistorial().add(nuevoHistorial);
									}
									nuevoItem.setMovimientoMPs(new HashSet());
									if (aItem.getMovimientoMPs() != null && !aItem.getMovimientoMPs().isEmpty()) {
										MovimientoMP mp = (MovimientoMP)aItem.getMovimientoMPs().iterator().next();
										if(movRev.getIdMovimiento() != null){
											movRev = mp.getMovimiento().getClon();
											movRev.setIdMovimiento(null);
											movRev.setFecha(new Date());
											movRev.setSigno(movRev.getSigno() * -1);
											movRev.setFechaAsiento(fechaAsiento);
											movRev.setConcepto(concepto);
											movRev.setEstado('R');
											movRev.setOperador(operador);
											movRev.setMovimientosMP(new HashSet());
										}
										MovimientoMP nuevoMP = mp.getClon();
										nuevoMP.setIdMovimientoMP(null);
										nuevoMP.setMovimiento(movRev);
										movRev.getMovimientosMP().add(nuevoMP);
										nuevoItem.getMovimientoMPs().add(nuevoMP);
										nuevoMP.setAsientoItem(nuevoItem);
										nuevoMP.setChequeHistorial(new HashSet());
										if (mp.getChequeHistorial() != null && !mp.getChequeHistorial().isEmpty()) {
											nuevoHistorial.setMovimientoMP(nuevoMP);
											nuevoMP.getChequeHistorial().add(nuevoHistorial);
										}
									}
									nuevoItem.setSigno(aItem.getSigno() * -1);
									nuevoItem.setAsiento(asiNuevo);
									asiNuevo.getAsientosItems().add(nuevoItem);
								}
							}
						}
						if(movRev.getIdMovimiento() == null){
							movimientoDao.grabarMovimiento(movRev);
						}
					}
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Movimiento ya existe en la base de datos.";
			throw new MovimientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El Movimiento no pudo ser grabado.";
			throw new MovimientoException(msg,e);
		}
		
		return errores;
	}

	public List getMovimientos(final Filtro filtro) throws MovimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return movimientoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Movimiento no pudo ser leida.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public Paginador getMovimientosPagina(final Filtro filtro, final Paginador paginador) throws MovimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Paginador) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					paginador.setResult(movimientoDao.listarTodosPagina(filtro, paginador));
					return paginador;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Movimiento no pudo ser leida.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public Page getMovimientosPage(final Filtro filtro, final int pageNumber, final int pageSize) throws MovimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Page) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return movimientoDao.listarTodosPage(filtro, pageNumber, pageSize);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Movimiento no pudo ser leida.";
			throw new MovimientoException(msg,e);
		}
	}
	
	public Page getPage(Filtro filtro, int pageNumber, int pageSize)throws Exception {
		Page page = getMovimientosPage(filtro, pageNumber, pageSize);
		Iterator iter = page.getThisPageElements().iterator();
		while (iter.hasNext())
		{
			Movimiento element = (Movimiento)iter.next();
			if(element.getCaja()!=null)
				element.getCaja().getDescripcion();
			if(element.getConcepto()!=null)
				element.getConcepto().getDescripcion();
			if(element.getOperador()!=null)
				element.getOperador().getLabel();
		}
		return page;
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public MovimientoDao getMovimientoDao() {
		return movimientoDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setMovimientoDao(MovimientoDao movimientoDao) {
		this.movimientoDao = movimientoDao;
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
	
	public AsientoItemDao getAsientoItemDao() {
		return asientoItemDao;
	}
	
	public ConceptoGenDao getConceptoGenDao() {
		return conceptoGenDao;
	}
	
	public MovimientoMPDao getMovimientoMPDao() {
		return movimientoMPDao;
	}
	
	public void setAsientoItemDao(AsientoItemDao asientoItemDao) {
		this.asientoItemDao = asientoItemDao;
	}
	
	public void setConceptoGenDao(ConceptoGenDao conceptoGenDao) {
		this.conceptoGenDao = conceptoGenDao;
	}
	
	public void setMovimientoMPDao(MovimientoMPDao movimientoMPDao) {
		this.movimientoMPDao = movimientoMPDao;
	}
	
	public LugarDao getLugarDao() {
		return lugarDao;
	}
	
	public void setLugarDao(LugarDao lugarDao) {
		this.lugarDao = lugarDao;
	}
	
	public ChequeLugarDao getChequeLugarDao() {
		return chequeLugarDao;
	} 
	
	public void setChequeLugarDao(ChequeLugarDao chequeLugarDao) {
		this.chequeLugarDao = chequeLugarDao;
	}

	public ChequeHistorialDao getChequeHistorialDao() {
		return chequeHistorialDao;
	}

	public void setChequeHistorialDao(ChequeHistorialDao chequeHistorialDao) {
		this.chequeHistorialDao = chequeHistorialDao;
	}
	
}

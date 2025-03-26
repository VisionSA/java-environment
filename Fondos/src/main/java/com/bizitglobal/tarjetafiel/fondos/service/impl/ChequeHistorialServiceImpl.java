package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoFondosDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeEstadoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeHistorialDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeHistorialService;
import com.bizitglobal.tarjetafiel.general.dao.ConceptoGenDao;
import com.bizitglobal.tarjetafiel.general.dao.FormaPagoDao;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ChequeHistorialServiceImpl implements ChequeHistorialService {
	private static Logger logger = Logger.getLogger(ChequeHistorialServiceImpl.class);
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ChequeHistorialDao chequeHistorialDao;
	private ChequeEstadoDao chequeEstadoDao;
	private OperadorDao operadorDao;
	private AsientoFondosDao asientoFondosDao;
	private ConceptoGenDao conceptoGenDao;
	private FormaPagoDao formaPagoDao;

	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    private Operador operador;

	public void grabarChequeHistorial(final ChequeHistorial unaChequeHistorial) throws ChequeHistorialException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeHistorialDao.grabarChequeHistorial(unaChequeHistorial);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ChequeHistorial ya existe en la base de datos.";
			throw new ChequeHistorialDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo ser grabada.";
			throw new ChequeHistorialException(msg,e);
		}
		
	}
	
	public List getChequeHistoriales() throws ChequeHistorialException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeHistorialDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeHistorial no pudo ser leida.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	public ChequeHistorial leerChequeHistorial(Long id) throws ChequeHistorialException {
		try {
			return chequeHistorialDao.buscarChequeHistorial(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeHistorial no existe en la base de datos.";
			throw new ChequeHistorialNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo leerse desde la base de datos.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	public void borrarChequeHistorial(final Long idChequeHistorial) throws ChequeHistorialException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeHistorialDao.borrarChequeHistorial(idChequeHistorial);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo borrase.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	public void borrarChequeHistorial(final ChequeHistorial unaChequeHistorial) throws ChequeHistorialException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeHistorialDao.borrarChequeHistorial(unaChequeHistorial);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo borrase.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	public void actualizarChequeHistorial(final ChequeHistorial unaChequeHistorial) throws ChequeHistorialException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeHistorialDao.actualizarChequeHistorial(unaChequeHistorial);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo actualizarse.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	public List getChequeHistorials(final Filtro filtro) throws ChequeHistorialException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeHistorialDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeHistorial no pudo ser leida.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ChequeHistorialDao getChequeHistorialDao() {
		return chequeHistorialDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setChequeHistorialDao(ChequeHistorialDao chequeHistorialDao) {
		this.chequeHistorialDao = chequeHistorialDao;
	}
	
	public ChequeEstadoDao getChequeEstadoDao() {
		return chequeEstadoDao;
	}
	
	public void setChequeEstadoDao(ChequeEstadoDao chequeEstadoDao) {
		this.chequeEstadoDao = chequeEstadoDao;
	}
	
	public OperadorDao getOperadorDao() {
		return operadorDao;
	}
	
	public void setOperadorDao(OperadorDao operadorDao) {
		this.operadorDao = operadorDao;
	}
	
	public AsientoFondosDao getAsientoFondosDao() {
		return asientoFondosDao;
	}
	
	public void setAsientoFondosDao(AsientoFondosDao asientoFondosDao) {
		this.asientoFondosDao = asientoFondosDao;
	}
	
	public ConceptoGenDao getConceptoGenDao() {
		return conceptoGenDao;
	}

	public void setConceptoGenDao(ConceptoGenDao conceptoGenDao) {
		this.conceptoGenDao = conceptoGenDao;
	}

	public FormaPagoDao getFormaPagoDao() {
		return formaPagoDao;
	}

	public void setFormaPagoDao(FormaPagoDao formaPagoDao) {
		this.formaPagoDao = formaPagoDao;
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
	
	public ChequeHistorial buscarUltimo(Cheque cheque) throws ChequeHistorialException {
		try {
			return chequeHistorialDao.buscarUltimoHistorial(cheque);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeHistorial no existe en la base de datos.";
			throw new ChequeHistorialNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo leerse desde la base de datos.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	

	public String buscarEmailProveedor(final Long  codComercio) throws ChequeHistorialException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeHistorialDao.buscarEmailProveedor(codComercio);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de buscarEmailProveedor no pudo ser leida.";
			throw new ChequeHistorialException(msg,e);
		}
	}
	
	
	
		

	
	
	public String buscarEmailComercio(final Long  codComercio) throws ChequeHistorialException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeHistorialDao.buscarEmailComercio(codComercio);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de buscarEmailComercio no pudo ser leida.";
			throw new ChequeHistorialException(msg,e);
		}
	}
				
			
	
	public void validarEstadoChequesPropios(final Date fecha) {
		operador = null;
		transactionTemplate.execute(new TransactionCallback(){
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				// Pasaje a Corriente
//				List list = chequeHistorialDao.buscarParaPasarCorriente();
				List list = chequeHistorialDao.buscarParaPasarCorriente(fecha);
				if (!list.isEmpty()) 
//					iterarCambios(list, 2L);
					iterarCambios(list, 2L, fecha);
				return null;
			}
		});
//		transactionTemplate.execute(new TransactionCallback(){
//			@Override
//			public Object doInTransaction(TransactionStatus arg0) {
//				// Pasaje a Vencido
//				List list = chequeHistorialDao.buscarParaPasarVencido();
//				if (!list.isEmpty())
	//				iterarCambios(list, 6L);
	//				return null;
//			}
//		});
//		transactionTemplate.execute(new TransactionCallback(){
//			@Override
//			public Object doInTransaction(TransactionStatus arg0) {
//				// Pasaje a Baja
//				List list = chequeHistorialDao.buscarParaPasarBaja();
//				if (!list.isEmpty())
	//				iterarCambios(list, 5L);
	//				return null;
//			}
//		});
		logger.info("Proceso Cambio de estados Cheques Propios Finalizado");
	}
//	private void iterarCambios(List list, ChequeEstado chequeEstado) {
	private void iterarCambios(List list, Long idChequeEstado, Date fecha) {
		ChequeEstado chequeEstado = chequeEstadoDao.buscarChequeEstado(idChequeEstado);
//		Date fecha = new Date();
		for(Object object : list){
			cambiarEstado((ChequeHistorial)object, chequeEstado, fecha);
		}
	}
	
//	private void cambiarEstado(ChequeHistorial chequeHistorial, ChequeEstado nuevoEstado){
	/**
	 * 427 - 'ACREDITACION - CAMBIO ESTADO DIFERIDO A ACREDITADO'
	 * 426 - 'CHEQUE - CAMBIO ESTADO DIFERIDO A CORRIENTE'
	 */
	private void cambiarEstado(ChequeHistorial chequeHistorial, ChequeEstado nuevoEstado, Date fecha){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			String leyenda;
			ConceptoGen conceptoGen;
			if(chequeHistorial.getCheque().getTipo().equals('P')){
				leyenda = "Cheque ";
				conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(426L);				
			}else{
				leyenda = "Acred. ";
				conceptoGen = conceptoGenDao.buscarConceptoGenXCodigo(427L);				
			}
			leyenda += chequeHistorial.getCheque().getIdCheque() + " a " + nuevoEstado.getDescripcion();
			
			
			AsientoFondos asientoFondos = new AsientoFondos();
			asientoFondos.setAsientosItems(new HashSet());
			asientoFondos.setConcepto(leyenda);
			asientoFondos.setCotabilizado('N');
			asientoFondos.setFecha(chequeHistorial.getCheque().getFechaPago());
			asientoFondos.setHora(simpleDateFormat.format(chequeHistorial.getCheque().getFechaPago()));
			asientoFondos.setOperador(operadorSistema());
			
			AsientoItem asientoItem = new AsientoItem();
			asientoItem.setAsiento(asientoFondos);
			PlanCuentaDos planCuentaDos = chequeHistorialDao.buscarPlanCuenta(chequeHistorial);
			asientoItem.setIdPlanCuenta(planCuentaDos.getIdPlanCuenta());
			asientoItem.setSigno(1);
			asientoItem.setImporte(chequeHistorial.getCheque().getImporte());
			asientoItem.setLeyenda(leyenda);
			asientoItem.setNroRenglon(1);
			
			asientoFondos.getAsientosItems().add(asientoItem);
			
			// Creo el nuevo historial
			ChequeHistorial nuevoChequeHistorial = new ChequeHistorial();
			nuevoChequeHistorial.setCheque(chequeHistorial.getCheque());
			nuevoChequeHistorial.setChequeEstado(nuevoEstado);
			nuevoChequeHistorial.setTimestamp(chequeHistorial.getCheque().getFechaPago());
			// Y su item de asiento
			asientoItem = new AsientoItem();
			asientoItem.setAsiento(asientoFondos);
			planCuentaDos = chequeHistorialDao.buscarPlanCuenta(nuevoChequeHistorial);
			asientoItem.setIdPlanCuenta(planCuentaDos.getIdPlanCuenta());
			asientoItem.setSigno(-1);
			asientoItem.setImporte(chequeHistorial.getCheque().getImporte());
			asientoItem.setLeyenda(leyenda);
			asientoItem.setNroRenglon(2);
			
			// Agregado del movimiento
			asientoItem.setMovimientoMPs(new HashSet());
			Movimiento movi = new Movimiento();
			movi.setConcepto(conceptoGen);
			movi.setFecha(fecha);
			movi.setFechaAsiento(asientoFondos.getFecha());
			movi.setEstado('C');
			movi.setImporte(asientoItem.getImporte());
			movi.setOperador(operadorSistema());
			movi.setSigno(1);
			// El MP lo asocio con el nuevo historial y el nuevo asiento
			MovimientoMP movMP = new MovimientoMP();
			movMP.setAsientoItem(asientoItem);
			movMP.setCheque(chequeHistorial.getCheque());
			movMP.setMonto(asientoItem.getImporte());
			movMP.setMovimiento(movi);
			movMP.setFormaPago(formaPagoDao.buscarFormaPago(2L));// siempre la forma es cheque
			// Una vez cargado el MP lo asigno al nuevo historial
			nuevoChequeHistorial.setMovimientoMP(movMP);
			asientoItem.getMovimientoMPs().add(movMP);
			
			nuevoChequeHistorial.setAsientoItem(asientoItem);
			asientoItem.setChequeHistorial(new HashSet());
			asientoItem.getChequeHistorial().add(nuevoChequeHistorial);
			asientoFondos.getAsientosItems().add(asientoItem);
			
			
			asientoFondosDao.grabarAsiento(asientoFondos);
			
//			chequeHistorialDao.grabarChequeHistorial(nuevoChequeHistorial);
			
		} catch (Exception e) {
			logger.error("Error cambiando estado cheque ID " + chequeHistorial.getCheque().getIdCheque() + "\n" + 
					"DE ESTADO " + chequeHistorial.getChequeEstado().getDescripcion() + " A ESTADO " +
					nuevoEstado.getDescripcion(),e);
		}
		
	}

	@Override
	public ChequeEstado getChequeEstadoByIdCheque(Long idCheque) throws ChequeHistorialException {
		try {
			return chequeHistorialDao.getChequeEstadoByIdCheque(idCheque);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeHistorial no existe en la base de datos.";
			throw new ChequeHistorialNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeHistorial no pudo leerse desde la base de datos.";
			throw new ChequeHistorialException(msg,e);
		}
	}

	private Operador operadorSistema(){
		if (operador == null) 
			operador = operadorDao.buscarOperador(0L);			
		return operador;
	}
	
}

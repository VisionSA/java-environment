package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoFondosDao;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosException;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.AsientoFondosService;
import com.bizitglobal.tarjetafiel.general.dao.ConceptoGenDao;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class AsientoFondosServiceImpl implements AsientoFondosService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private AsientoFondosDao asientoFondosDao;
    private ConceptoGenDao conceptoGenDao;	
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarAsiento(final AsientoFondos unaAsiento) throws AsientoFondosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoFondosDao.grabarAsiento(unaAsiento);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La Asiento ya existe en la base de datos.";
			throw new AsientoFondosDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La Asiento no pudo ser grabada.";
			throw new AsientoFondosException(msg,e);
		}
		
	}
	
	public List getAsientoes() throws AsientoFondosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return asientoFondosDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Asiento no pudo ser leida.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	public AsientoFondos leerAsiento(Long id) throws AsientoFondosException {
		try {
			return asientoFondosDao.buscarAsiento(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La asiento no existe en la base de datos.";
			throw new AsientoFondosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La Asiento no pudo leerse desde la base de datos.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	public void borrarAsiento(final Long idAsiento) throws AsientoFondosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoFondosDao.borrarAsiento(idAsiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Asiento no pudo borrase.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	public void borrarAsiento(final AsientoFondos unaAsiento) throws AsientoFondosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoFondosDao.borrarAsiento(unaAsiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Asiento no pudo borrase.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	public void actualizarAsiento(final AsientoFondos unaAsiento) throws AsientoFondosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoFondosDao.actualizarAsiento(unaAsiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Asiento no pudo actualizarse.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	public List getAsientos(final Filtro filtro) throws AsientoFondosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return asientoFondosDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Asiento no pudo ser leida.";
			throw new AsientoFondosException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public AsientoFondosDao getAsientoFondosDao() {
		return asientoFondosDao;
	}
	
	
	public AsientoFondos construirAsientoPagoLiquidacionComercio(Date fechaAsiento, Operador operador, int codigoComercio, int nroLiquidacion, Double importe, FormaPago formaPago) {
		ConceptoGen conceptoLiquidacionComercio = (ConceptoGen)(conceptoGenDao.listarTodos(new Filtro("codigoConcepto", Filtro.IGUAL , new Long(51)))).get(0);
	    AsientoFondos asientoFondos = new AsientoFondos();
	    asientoFondos.setConcepto(conceptoLiquidacionComercio.getDescripcion());
	    asientoFondos.setCotabilizado(Character.valueOf('N'));
	    asientoFondos.setFecha(fechaAsiento);
	    asientoFondos.setHora(null);
	    asientoFondos.setOperador(operador);
    
	    // creo el Asiento Item.
	    AsientoItem asientoItem = new AsientoItem();
	    asientoItem.setAsiento(asientoFondos);
	    asientoItem.setSigno(new Integer(-1));
	    asientoItem.setLeyenda(conceptoLiquidacionComercio.getDescripcion() + "-" +codigoComercio + "-" + nroLiquidacion);
	    asientoItem.setImporte(importe);
	    
	    // agrego al set de items del asiento este ultimo objeto.
	    if (asientoFondos.getAsientosItems()==null) asientoFondos.setAsientosItems(new HashSet());
	    asientoFondos.getAsientosItems().add(asientoItem);
	    
	    Movimiento movimiento = new Movimiento();
	    movimiento.setSigno(new Integer(-1));
	    movimiento.setFecha(Calendar.getInstance().getTime());
	    movimiento.setOperador(operador);
	    movimiento.setImporte(importe);
	    movimiento.setEstado(Character.valueOf('P'));
	    movimiento.setConcepto(conceptoLiquidacionComercio);
	    
	    
	    MovimientoMP movimientoMP = new MovimientoMP();
	    movimientoMP.setMonto(importe);
	    movimientoMP.setMovimiento(movimiento);
	    movimientoMP.setFormaPago(formaPago);
	    movimientoMP.setAsientoItem(asientoItem);
	    // aqui deberia setear el cheque si es que lo hay.
	    
	    
	    // agrego el movimientoMP al set de movimiento
	    if (movimiento.getMovimientosMP()==null) movimiento.setMovimientosMP(new HashSet());
	    movimiento.getMovimientosMP().add(movimientoMP);
		return null;
	}

	public double saldoContableRealAFecha(Long idPlanCuenta, Date fechaHasta) throws AsientoException {
		try {
			return asientoFondosDao.saldoContableRealAFecha(idPlanCuenta, fechaHasta);
		} catch (Exception e) {
			String msg = "No se pudo leer el saldo del libro mayor.";
			throw new AsientoException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setAsientoFondosDao(AsientoFondosDao asientoFondosDao) {
		this.asientoFondosDao = asientoFondosDao;
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ConceptoGenDao getConceptoGenDao() {
		return conceptoGenDao;
	}

	/**
	 * Necesario para spring.
	 * @param ConceptogenDao, Objeto dao a setear.
	 */
	public void setConceptoGenDao(ConceptoGenDao conceptoGenDao) {
		this.conceptoGenDao = conceptoGenDao;
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


}

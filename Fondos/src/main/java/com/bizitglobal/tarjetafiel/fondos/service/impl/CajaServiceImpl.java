package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.CajaDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.service.CajaService;
import com.bizitglobal.tarjetafiel.general.dao.MonedaDao;
import com.bizitglobal.tarjetafiel.general.exception.MonedaDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.exception.MonedaNotFoundException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.service.MonedaService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class CajaServiceImpl implements CajaService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CajaDao cajaDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarCaja(final Caja unaCaja) throws CajaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaDao.grabarCaja(unaCaja);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La Caja ya existe en la base de datos.";
			throw new CajaDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La Caja no pudo ser grabada.";
			throw new CajaException(msg,e);
		}
	}
	
	public List getCajas() throws CajaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de cajas no pudo ser leida.";
			throw new CajaException(msg,e);
		}
	}
	
	public Caja leerCaja(Long id) throws CajaException {
		try {
			return cajaDao.buscarCaja(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La caja no existe en la base de datos.";
			throw new CajaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La Caja no pudo leerse desde la base de datos.";
			throw new CajaException(msg,e);
		}
	}
	
	public Caja buscarCajaPorOperadorFlex(Operador operador) throws CajaException {
		try {
			Caja caja = ((CajaDaoHibernateImpl)cajaDao).buscarCajaPorOperadorFlex(operador.getCodigo());
			if(caja == null){
				throw new Exception("La caja no tiene una apertura para el operador "+ operador.getApellido() + ", " + operador.getNombre());
			} else if(caja.getHabilitada().toString().equals("N")){
				throw new Exception("La Caja no esta habilitada");
			}else {
				return caja;
			}
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La caja no existe en la base de datos.";
			throw new CajaNotFoundException(msg,ex);
		} catch (Exception e) {
			//String msg = "La Caja no pudo leerse desde la base de datos.";
			throw new CajaException(e.getMessage(),e);
		}
	}
	
	public void borrarCaja(final Long idCaja) throws CajaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaDao.borrarCaja(idCaja);
				}
			});
		} catch (Exception e) {
			String msg = "La Caja no pudo borrase.";
			throw new CajaException(msg,e);
		}
	}
	
	public void borrarCaja(final Caja unaCaja) throws CajaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaDao.borrarCaja(unaCaja);
				}
			});
		} catch (Exception e) {
			String msg = "La Caja no pudo borrase.";
			throw new CajaException(msg,e);
		}
	}
	
	public void actualizarCaja(final Caja unaCaja) throws CajaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaDao.actualizarCaja(unaCaja);
				}
			});
		} catch (Exception e) {
			String msg = "La Caja no pudo actualizarse.";
			throw new CajaException(msg,e);
		}
	}
	
	public List getCajas(final Filtro filtro) throws CajaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Caja no pudo ser leida.";
			throw new CajaException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CajaDao getCajaDao() {
		return cajaDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setCajaDao(CajaDao cajaDao) {
		this.cajaDao = cajaDao;
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

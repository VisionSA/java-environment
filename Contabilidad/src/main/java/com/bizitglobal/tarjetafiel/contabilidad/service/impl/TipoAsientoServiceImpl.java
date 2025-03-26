package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.TipoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.TipoAsientoDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.TipoAsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.TipoAsientoNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.TipoAsiento;
import com.bizitglobal.tarjetafiel.contabilidad.service.TipoAsientoService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class TipoAsientoServiceImpl implements TipoAsientoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TipoAsientoDao tipoAsientoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTipoAsiento (final TipoAsiento pObject) throws TipoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoAsientoDao.grabarTipoAsiento(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el TipoAsiento ya existe en la base de datos.";
			throw new TipoAsientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new TipoAsientoException(msg,e);
		}
	}
	public List getTipoAsiento(final Filtro filtro) throws TipoAsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = tipoAsientoDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de TipoAsiento no pudo ser leida.";
			throw new TipoAsientoException(msg,e);
		}
	}
	public TipoAsiento leerTipoAsiento (final Long id) throws TipoAsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (TipoAsiento) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					TipoAsiento tipo = tipoAsientoDao.buscarTipoAsiento(id);
				return tipo;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El TipoAsiento no existe en la base de datos.";
			throw new TipoAsientoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El TipoAsiento no pudo leerse desde la base de datos.";
			throw new TipoAsientoException(msg,e);
		}
	}
	
	public void borrarTipoAsiento (final Long id) throws TipoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoAsientoDao.borrarTipoAsiento(id);
				}
			});
		} catch (Exception e) {
			String msg = "El TipoAsiento no pudo borrase.";
			throw new TipoAsientoException(msg,e);
		}
	}
	
	public void borrarTipoAsiento(final TipoAsiento pObject) throws TipoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoAsientoDao.borrarTipoAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El TipoAsiento no pudo borrase.";
			throw new TipoAsientoException(msg,e);
		}
	}
	public void actualizarTipoAsiento (final TipoAsiento pObject) throws TipoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoAsientoDao.actualizarTipoAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El TipoAsiento no pudo actualizarse.";
			throw new TipoAsientoException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public TipoAsientoDao getTipoAsientoDao() {
		return tipoAsientoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setTipoAsientoDao(TipoAsientoDao tipoAsientoDao) {
		this.tipoAsientoDao = tipoAsientoDao;
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


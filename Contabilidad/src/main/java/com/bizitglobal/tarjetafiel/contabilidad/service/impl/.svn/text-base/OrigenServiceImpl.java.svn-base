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
import com.bizitglobal.tarjetafiel.contabilidad.dao.OrigenDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Origen;
import com.bizitglobal.tarjetafiel.contabilidad.service.OrigenService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class OrigenServiceImpl implements OrigenService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private OrigenDao origenDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarOrigen (final Origen pObject) throws OrigenException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					origenDao.grabarOrigen(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el Origen ya existe en la base de datos.";
			throw new OrigenDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new OrigenException(msg,e);
		}
	}
	public List getOrigen(final Filtro filtro) throws OrigenException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = origenDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Origen no pudo ser leida.";
			throw new OrigenException(msg,e);
		}
	}
	public Origen leerOrigen (final Long id) throws OrigenException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Origen) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Origen origen = origenDao.buscarOrigen(id);
				return origen;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Origen no existe en la base de datos.";
			throw new OrigenNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El Origen no pudo leerse desde la base de datos.";
			throw new OrigenException(msg,e);
		}
	}
	public void borrarOrigen (final Long id) throws OrigenException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					origenDao.borrarOrigen(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Origen no pudo borrase.";
			throw new OrigenException(msg,e);
		}
	}
	public void borrarOrigen(final Origen pObject) throws OrigenException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					origenDao.borrarOrigen(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Origen no pudo borrase.";
			throw new OrigenException(msg,e);
		}
	}
	public void actualizarOrigen (final Origen pObject) throws OrigenException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					origenDao.actualizarOrigen(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Origen no pudo actualizarse.";
			throw new OrigenException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public OrigenDao getOrigenDao() {
		return origenDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setOrigenDao(OrigenDao origenDao) {
		this.origenDao = origenDao;
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


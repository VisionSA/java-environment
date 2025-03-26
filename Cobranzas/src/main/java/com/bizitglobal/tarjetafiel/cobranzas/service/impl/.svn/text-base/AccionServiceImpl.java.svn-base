package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.AccionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;
import com.bizitglobal.tarjetafiel.cobranzas.service.AccionService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class AccionServiceImpl implements AccionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AccionDao accionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarAccion (final Accion pObject) throws AccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionDao.grabarAccion(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Accion ya existe en la base de datos.";
			throw new AccionException(msg,ex);
		} catch (Exception e) {
			String msg = "El Accion no pudo ser grabada.";
			throw new AccionException(msg,e);
		}
	}
	
	public List getAccion(final Filtro filtro) throws AccionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List lista = accionDao.listarTodos(filtro);
					return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Acciones no pudo ser leida.";
			throw new AccionException(msg,e);
		}
	}
	public Accion leerAccion (final Long id) throws AccionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Accion) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Accion archi = accionDao.buscarAccion(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Accion no existe en la base de datos.";
			throw new AccionException(msg,ex);
		} catch (Exception e) {
			String msg = "El Accion no pudo leerse desde la base de datos.";
			throw new AccionException(msg,e);
		}
	}
	public void borrarAccion (final Long id) throws AccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionDao.borrarAccion(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Accion no pudo borrase.";
			throw new AccionException(msg,e);
		}
	}
	public void borrarAccion(final Accion pObject) throws AccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionDao.borrarAccion(pObject);
				}
			});
			accionDao.borrarAccion(pObject);
		} catch (Exception e) {
			String msg = "El Accion no pudo borrase.";
			throw new AccionException(msg,e);
		}
	}
	public void actualizarAccion (final Accion pObject) throws AccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionDao.actualizarAccion(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Accion no pudo actualizarse.";
			throw new AccionException(msg,e);
		}
	}
	public List listarAcciones() throws AccionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List lista = accionDao.listarTodos(new Filtro());
					return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Acciones no pudo ser leida.";
			throw new AccionException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AccionDao getAccionDao() {
		return accionDao;
	}
	/**
	* Necesario para spring.
	* @param accionDao, Objeto dao a setear.
	*/
	public void setAccionDao(AccionDao accionDao) {
		this.accionDao = accionDao;
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

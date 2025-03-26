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

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.AccionVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.cobranzas.service.AccionVersionService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class AccionVersionServiceImpl implements AccionVersionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AccionVersionDao accionVersionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transPlanes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarAccionVersion (final AccionVersion pObject) throws AccionVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionVersionDao.grabarAccionVersion(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El AccionVersion ya existe en la base de datos.";
			throw new AccionVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El AccionVersion no pudo ser grabada.";
			throw new AccionVersionException(msg,e);
		}
	}
	public List getAccionVersion(final Filtro filtro) throws AccionVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = accionVersionDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AccionVersiones no pudo ser leida.";
			throw new AccionVersionException(msg,e);
		}
	}
	public AccionVersion leerAccionVersion (final Long id) throws AccionVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (AccionVersion) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					AccionVersion archi = accionVersionDao.buscarAccionVersion(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El AccionVersion no existe en la base de datos.";
			throw new AccionVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El AccionVersion no pudo leerse desde la base de datos.";
			throw new AccionVersionException(msg,e);
		}
	}
	public void borrarAccionVersion (final Long id) throws AccionVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionVersionDao.borrarAccionVersion(id);
				}
			});
		} catch (Exception e) {
			String msg = "El AccionVersion no pudo borrase.";
			throw new AccionVersionException(msg,e);
		}
	}
	public void borrarAccionVersion(final AccionVersion pObject) throws AccionVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionVersionDao.borrarAccionVersion(pObject);
				}
			});
			accionVersionDao.borrarAccionVersion(pObject);
		} catch (Exception e) {
			String msg = "El AccionVersion no pudo borrase.";
			throw new AccionVersionException(msg,e);
		}
	}
	public void actualizarAccionVersion (final AccionVersion pObject) throws AccionVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					accionVersionDao.actualizarAccionVersion(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El AccionVersion no pudo actualizarse.";
			throw new AccionVersionException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AccionVersionDao getAccionVersionDao() {
		return accionVersionDao;
	}
	/**
	* Necesario para spring.
	* @param AccionVersionDao, Objeto dao a setear.
	*/
	public void setAccionVersionDao(AccionVersionDao accionVersionDao) {
		this.accionVersionDao = accionVersionDao;
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

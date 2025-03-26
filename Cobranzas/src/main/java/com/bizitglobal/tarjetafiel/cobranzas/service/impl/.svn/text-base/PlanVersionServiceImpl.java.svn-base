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

import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.PlanVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.cobranzas.service.PlanVersionService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class PlanVersionServiceImpl implements PlanVersionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private PlanVersionDao planVersionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transPlanes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarPlanVersion (final PlanVersion pObject) throws PlanVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planVersionDao.grabarPlanVersion(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El PlanVersion ya existe en la base de datos.";
			throw new PlanVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El PlanVersion no pudo ser grabada.";
			throw new PlanVersionException(msg,e);
		}
	}
	public List getPlanVersion(final Filtro filtro) throws PlanVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planVersionDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de PlanVersiones no pudo ser leida.";
			throw new PlanVersionException(msg,e);
		}
	}
	public PlanVersion leerPlanVersion (final Long id) throws PlanVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (PlanVersion) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					PlanVersion archi = planVersionDao.buscarPlanVersion(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El PlanVersion no existe en la base de datos.";
			throw new PlanVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El PlanVersion no pudo leerse desde la base de datos.";
			throw new PlanVersionException(msg,e);
		}
	}
	public void borrarPlanVersion (final Long id) throws PlanVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planVersionDao.borrarPlanVersion(id);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanVersion no pudo borrase.";
			throw new PlanVersionException(msg,e);
		}
	}
	public void borrarPlanVersion(final PlanVersion pObject) throws PlanVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planVersionDao.borrarPlanVersion(pObject);
				}
			});
			planVersionDao.borrarPlanVersion(pObject);
		} catch (Exception e) {
			String msg = "El PlanVersion no pudo borrase.";
			throw new PlanVersionException(msg,e);
		}
	}
	public void actualizarPlanVersion (final PlanVersion pObject) throws PlanVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planVersionDao.actualizarPlanVersion(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanVersion no pudo actualizarse.";
			throw new PlanVersionException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public PlanVersionDao getPlanVersionDao() {
		return planVersionDao;
	}
	/**
	* Necesario para spring.
	* @param PlanVersionDao, Objeto dao a setear.
	*/
	public void setPlanVersionDao(PlanVersionDao planVersionDao) {
		this.planVersionDao = planVersionDao;
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
	@Override
	public List getPlanesVersionByFiltro(final Filtro filtro)
			throws PlanVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planVersionDao.getPlanesVersionByFiltro(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de PlanVersiones no pudo ser leida.";
			throw new PlanVersionException(msg,e);
		}
	}
}

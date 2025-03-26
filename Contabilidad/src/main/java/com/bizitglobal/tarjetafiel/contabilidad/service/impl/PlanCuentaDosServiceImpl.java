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
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.contabilidad.service.PlanCuentaDosService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class PlanCuentaDosServiceImpl implements PlanCuentaDosService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private PlanCuentaDosDao planCuentaDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarPlanCuenta (final PlanCuentaDos pObject) throws PlanCuentaDosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planCuentaDao.grabarPlanCuenta(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el plan cuenta  ya existe en la base de datos.";
			throw new PlanCuentaDosDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  plan cuenta no pudo ser grabado.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	
	
	public List getPlanCuenta(final Filtro filtro) throws PlanCuentaDosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planCuentaDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de PlanCuenta no pudo ser leida.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	
	
	public List getPlanCuentaSimple(final Filtro filtro) throws PlanCuentaDosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planCuentaDao.listarTodosPlanCuentaSimple(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de PlanCuenta simple no pudo ser leida.";
			throw new PlanCuentaDosException(msg,e);
		}
	}

	
	
	
	public PlanCuentaDos leerPlanCuenta (final Long id) throws PlanCuentaDosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (PlanCuentaDos) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					PlanCuentaDos plan = planCuentaDao.buscarPlanCuenta(id);
				return plan;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El plan cuenta no existe en la base de datos.";
			throw new PlanCuentaDosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo leerse desde la base de datos.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	
	public void borrarPlanCuenta (final Long id) throws PlanCuentaDosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planCuentaDao.borrarPlanCuenta(id);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	
	public void borrarPlanCuenta(final PlanCuentaDos pObject) throws PlanCuentaDosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planCuentaDao.borrarPlanCuenta(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	
	public void actualizarPlanCuenta (final PlanCuentaDos pObject) throws PlanCuentaDosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planCuentaDao.actualizarPlanCuenta(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo actualizarse.";
			throw new PlanCuentaDosException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public PlanCuentaDosDao getPlamCuentaDao() {
		return planCuentaDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setPlanCuentaDao(PlanCuentaDosDao planCuentaDao) {
		this.planCuentaDao = planCuentaDao;
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


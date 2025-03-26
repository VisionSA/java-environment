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
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostosDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostoAsiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;
import com.bizitglobal.tarjetafiel.contabilidad.service.CentroCostosService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class CentroCostosServiceImpl implements CentroCostosService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private CentroCostosDao centroCostosDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarCentroCostos (final CentroCostos pObject) throws CentroCostosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostosDao.grabarCentroCostos(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el CentroCostos ya existe en la base de datos.";
			throw new CentroCostosDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new CentroCostosException(msg,e);
		}
	}
	public List getCentroCostos(final Filtro filtro) throws CentroCostosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = centroCostosDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CentroCostos no pudo ser leida.";
			throw new CentroCostosException(msg,e);
		}
	}
	public CentroCostos leerCentroCostos (final Long id) throws CentroCostosException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (CentroCostos) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				CentroCostos centroCostos = centroCostosDao.buscarCentroCostos(id);
				return centroCostos;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El CentroCostos no existe en la base de datos.";
			throw new CentroCostosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El CentroCostos no pudo leerse desde la base de datos.";
			throw new CentroCostosException(msg,e);
		}
	}
	public void borrarCentroCostos (final Long id) throws CentroCostosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostosDao.borrarCentroCostos(id);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostos no pudo borrase.";
			throw new CentroCostosException(msg,e);
		}
	}
	public void borrarCentroCostos(final CentroCostos pObject) throws CentroCostosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostosDao.borrarCentroCostos(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostos no pudo borrase.";
			throw new CentroCostosException(msg,e);
		}
	}
	public void actualizarCentroCostos (final CentroCostos pObject) throws CentroCostosException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostosDao.actualizarCentroCostos(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostos no pudo actualizarse.";
			throw new CentroCostosException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public CentroCostosDao getCentroCostosDao() {
		return centroCostosDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setCentroCostosDao(CentroCostosDao centroCostosDao) {
		this.centroCostosDao = centroCostosDao;
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


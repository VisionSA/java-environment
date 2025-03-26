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
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostoAsientoDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostoAsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostoAsientoNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostoAsiento;
import com.bizitglobal.tarjetafiel.contabilidad.service.CentroCostoAsientoService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class CentroCostoAsientoServiceImpl implements CentroCostoAsientoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private CentroCostoAsientoDao centroCostoAsientoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarCentroCostoAsiento (final CentroCostoAsiento pObject) throws CentroCostoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostoAsientoDao.grabarCentroCostoAsiento(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el CentroCostoAsiento ya existe en la base de datos.";
			throw new CentroCostoAsientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	
	public List getCentroCostoAsiento(final Filtro filtro) throws CentroCostoAsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = centroCostoAsientoDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CentroCostoAsiento no pudo ser leida.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	public CentroCostoAsiento leerCentroCostoAsiento (final Long id) throws CentroCostoAsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (CentroCostoAsiento) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				CentroCostoAsiento centroCostoAsiento = centroCostoAsientoDao.buscarCentroCostoAsiento(id);
				return centroCostoAsiento;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El CentroCostoAsiento no existe en la base de datos.";
			throw new CentroCostoAsientoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El CentroCostoAsiento no pudo leerse desde la base de datos.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	
	public void borrarCentroCostoAsiento (final Long id) throws CentroCostoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostoAsientoDao.borrarCentroCostoAsiento(id);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostoAsiento no pudo borrase.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	
	public void borrarCentroCostoAsiento(final CentroCostoAsiento pObject) throws CentroCostoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostoAsientoDao.borrarCentroCostoAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostoAsiento no pudo borrase.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	
	public void actualizarCentroCostoAsiento (final CentroCostoAsiento pObject) throws CentroCostoAsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					centroCostoAsientoDao.actualizarCentroCostoAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El CentroCostoAsiento no pudo actualizarse.";
			throw new CentroCostoAsientoException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public CentroCostoAsientoDao getCentroCostoAsientoDao() {
		return centroCostoAsientoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setCentroCostoAsientoDao(CentroCostoAsientoDao centroCostoAsientoDao) {
		this.centroCostoAsientoDao = centroCostoAsientoDao;
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


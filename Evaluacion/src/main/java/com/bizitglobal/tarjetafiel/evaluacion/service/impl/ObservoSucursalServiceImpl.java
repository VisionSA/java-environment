package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoSucursalDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoSucursalDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoSucursalException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoSucursalNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoSucursal;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoSucursalService;

/**
*	Implementacion de la interfaz ObservoSucursalService.
*/
public class ObservoSucursalServiceImpl implements ObservoSucursalService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ObservoSucursalDao observoSucursalDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarObservoSucursal(final ObservoSucursal pObject) throws ObservoSucursalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoSucursalDao.grabarEvaObsSucursales(pObject);

			}
		});
	}
	
	public List getObservoSucursal(Filtro filtro) throws ObservoSucursalException {
		try {
			return observoSucursalDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaobssucursales no pudo ser leida.";
			throw new ObservoSucursalException(msg,e);
		}
	}
	
	public ObservoSucursal leerObservoSucursal(Long id) throws ObservoSucursalException {
		ObservoSucursal result = null;
		try {
			result = observoSucursalDao.buscarEvaObsSucursales(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ObservoSucursal no existe en la base de datos.";
			throw new ObservoSucursalNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaobssucursales no pudo leerse desde la base de datos.";
			throw new ObservoSucursalException(msg,e);
		}
		return result;
	}
	
	public void borrarObservoSucursal(final Long id) throws ObservoSucursalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoSucursalDao.borrarEvaObsSucursales(id);
			}
		});
	}
	
	public void borrarObservoSucursal(final ObservoSucursal pObject) throws ObservoSucursalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoSucursalDao.borrarEvaObsSucursales(pObject);

			}
		});
	}
	
	public void actualizarObservoSucursal(final ObservoSucursal pObject) throws ObservoSucursalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoSucursalDao.actualizarEvaObsSucursales(pObject);

			}
		});
	}

	public ObservoSucursalDao getObservoSucursalDao() {
		return observoSucursalDao;
	}

	public void setObservoSucursalDao(ObservoSucursalDao observoSucursalDao) {
		this.observoSucursalDao = observoSucursalDao;
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


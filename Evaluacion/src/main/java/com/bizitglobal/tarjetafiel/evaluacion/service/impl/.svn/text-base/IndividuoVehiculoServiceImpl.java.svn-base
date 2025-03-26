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
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoVehiculoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoVehiculoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoVehiculoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoVehiculoService;

/**
*	Implementacion de la interfaz IndividuoVehiculoService.
*/
public class IndividuoVehiculoServiceImpl implements IndividuoVehiculoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private IndividuoVehiculoDao individuoVehiculoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarIndividuoVehiculo (final IndividuoVehiculo pObject) throws IndividuoVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoVehiculoDao.grabarEvaIndiVehiculos(pObject);

			}
		});
	}
	
	public List getIndividuoVehiculo(Filtro filtro) throws IndividuoVehiculoException {
		try {
			return individuoVehiculoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaindivehiculos no pudo ser leida.";
			throw new IndividuoVehiculoException(msg,e);
		}
	}
	
	public IndividuoVehiculo leerIndividuoVehiculo (Long id) throws IndividuoVehiculoException {
		IndividuoVehiculo result = null;
		try {
			result = individuoVehiculoDao.buscarEvaIndiVehiculos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La IndividuoVehiculo no existe en la base de datos.";
			throw new IndividuoVehiculoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaindivehiculos no pudo leerse desde la base de datos.";
			throw new IndividuoVehiculoException(msg,e);
		}
		return result;
	}
	
	public void borrarIndividuoVehiculo (final Long id) throws IndividuoVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoVehiculoDao.borrarEvaIndiVehiculos(id);

			}
		});
	}
	
	public void borrarIndividuoVehiculo(final IndividuoVehiculo pObject) throws IndividuoVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoVehiculoDao.borrarEvaIndiVehiculos(pObject);

			}
		});
	}
	
	public void actualizarIndividuoVehiculo (final IndividuoVehiculo pObject) throws IndividuoVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoVehiculoDao.actualizarEvaIndiVehiculos(pObject);

			}
		});
	}
	
	public IndividuoVehiculoDao getIndividuoVehiculoDao() {
		return individuoVehiculoDao;
	}

	public void setIndividuoVehiculoDao(IndividuoVehiculoDao individuoVehiculoDao) {
		this.individuoVehiculoDao = individuoVehiculoDao;
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


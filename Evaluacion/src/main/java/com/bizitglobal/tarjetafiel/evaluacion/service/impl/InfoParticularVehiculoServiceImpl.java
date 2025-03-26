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
import com.bizitglobal.tarjetafiel.evaluacion.dao.InfoParticularVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InfoParticularVehiculoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InfoParticularVehiculoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InfoParticularVehiculoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InfoParticularVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.service.InfoParticularVehiculoService;

/**
*	Implementacion de la interfaz InfoParticularVehiculoService.
*/
public class InfoParticularVehiculoServiceImpl implements InfoParticularVehiculoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private InfoParticularVehiculoDao infoParticularVehiculoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarInfoParticularVehiculo (final InfoParticularVehiculo pObject) throws InfoParticularVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				infoParticularVehiculoDao.grabarInfoParticularVehiculo(pObject);

			}
		});
	}
	public List getInfoParticularVehiculo(Filtro filtro) throws InfoParticularVehiculoException {
		try {
			return infoParticularVehiculoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de infoparticularvehiculo no pudo ser leida.";
			throw new InfoParticularVehiculoException(msg,e);
		}
	}
	public InfoParticularVehiculo leerInfoParticularVehiculo (Long id) throws InfoParticularVehiculoException {
		InfoParticularVehiculo result = null;
		try {
			result = infoParticularVehiculoDao.buscarInfoParticularVehiculo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La InfoParticularVehiculo no existe en la base de datos.";
			throw new InfoParticularVehiculoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La infoparticularvehiculo no pudo leerse desde la base de datos.";
			throw new InfoParticularVehiculoException(msg,e);
		}
		return result;
	}
	public void borrarInfoParticularVehiculo (final Long id) throws InfoParticularVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				infoParticularVehiculoDao.borrarInfoParticularVehiculo(id);

			}
		});
	}
	public void borrarInfoParticularVehiculo(final InfoParticularVehiculo pObject) throws InfoParticularVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				infoParticularVehiculoDao.borrarInfoParticularVehiculo(pObject);

			}
		});
	}
	public void actualizarInfoParticularVehiculo (final InfoParticularVehiculo pObject) throws InfoParticularVehiculoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				infoParticularVehiculoDao.actualizarInfoParticularVehiculo(pObject);

			}
		});
	}
	
	public InfoParticularVehiculoDao getInfoParticularVehiculoDao() {
		return infoParticularVehiculoDao;
	}
	public void setInfoParticularVehiculoDao(
			InfoParticularVehiculoDao infoParticularVehiculoDao) {
		this.infoParticularVehiculoDao = infoParticularVehiculoDao;
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


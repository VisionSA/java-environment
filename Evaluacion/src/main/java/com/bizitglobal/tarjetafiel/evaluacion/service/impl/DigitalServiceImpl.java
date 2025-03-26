package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ch.qos.logback.classic.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DigitalDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DigitalDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DigitalException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DigitalNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.evaluacion.service.DigitalService;

/**
*	Implementacion de la interfaz DigitalService.
*/
public class DigitalServiceImpl implements DigitalService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private DigitalDao digitalDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarDigital (final Digital pObject) throws DigitalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				digitalDao.grabarEvaDigitales(pObject);

			}
		});
	}
	
	public List getDigital(Filtro filtro) throws DigitalException {
		try {
			return digitalDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evadigitales no pudo ser leida.";
			throw new DigitalException(msg,e);
		}
	}
	
	public Digital leerDigital (Long id) throws DigitalException {
		Digital result = null;
		try {
			result = digitalDao.buscarEvaDigitales(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Digital no existe en la base de datos.";
			throw new DigitalNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evadigitales no pudo leerse desde la base de datos.";
			throw new DigitalException(msg,e);
		}
		return result;
	}
	
	public void borrarDigital (final Long id) throws DigitalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				digitalDao.borrarEvaDigitales(id);

			}
		});
	}
	
	public void borrarDigital(final Digital pObject) throws DigitalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				digitalDao.borrarEvaDigitales(pObject);

			}
		});
	}
	
	public void actualizarDigital (final Digital pObject) throws DigitalException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				digitalDao.actualizarEvaDigitales(pObject);

			}
		});
	}
	
	
	public DigitalDao getDigitalDao() {
		return digitalDao;
	}

	public void setDigitalDao(DigitalDao digitalDao) {
		this.digitalDao = digitalDao;
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
	
	public List buscarPorFecha(Timestamp desde, Timestamp hasta){
		return digitalDao.buscarPorFecha(desde,hasta);
	}
	
	
}


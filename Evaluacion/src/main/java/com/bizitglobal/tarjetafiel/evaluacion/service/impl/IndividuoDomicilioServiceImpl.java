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
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoDomicilioDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoDomicilioDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoDomicilioException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoDomicilioNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoDomicilioService;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;

/**
*	Implementacion de la interfaz IndividuoDomicilioService.
*/
public class IndividuoDomicilioServiceImpl implements IndividuoDomicilioService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private IndividuoDomicilioDao individuoDomiciliosDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarIndividuoDomicilio (final IndividuoDomicilio pObject) throws IndividuoDomicilioException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoDomiciliosDao.grabarEvaIndivDomicilios(pObject);

			}
		});
	}
	
	public List getIndividuoDomicilio(Filtro filtro) throws IndividuoDomicilioException {
		try {
			return individuoDomiciliosDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaindivdomicilios no pudo ser leida.";
			throw new IndividuoDomicilioException(msg,e);
		}
	}
	
	public IndividuoDomicilio leerIndividuoDomicilio (Long id) throws IndividuoDomicilioException {
		IndividuoDomicilio result = null;
		try {
			result = individuoDomiciliosDao.buscarEvaIndivDomicilios(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La IndividuoDomicilio no existe en la base de datos.";
			throw new IndividuoDomicilioNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaindivdomicilios no pudo leerse desde la base de datos.";
			throw new IndividuoDomicilioException(msg,e);
		}
		return result;
	}
	
	public void borrarIndividuoDomicilio (final Long id) throws IndividuoDomicilioException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoDomiciliosDao.borrarEvaIndivDomicilios(id);

			}
		});
	}
	
	public void borrarIndividuoDomicilio(final IndividuoDomicilio pObject) throws IndividuoDomicilioException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoDomiciliosDao.borrarEvaIndivDomicilios(pObject);

			}
		});
	}
	
	public void actualizarIndividuoDomicilio (final IndividuoDomicilio pObject) throws IndividuoDomicilioException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoDomiciliosDao.actualizarEvaIndivDomicilios(pObject);

			}
		});
	}
	
	
	public IndividuoDomicilioDao getIndividuoDomiciliosDao() {
		return individuoDomiciliosDao;
	}

	public void setIndividuoDomiciliosDao(
			IndividuoDomicilioDao individuoDomiciliosDao) {
		this.individuoDomiciliosDao = individuoDomiciliosDao;
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
	public Domicilio getDomicilioByIdIndividuo(Long idIndividuo)
			throws IndividuoDomicilioException {
		
		Domicilio resultado = null;
		
		try {
			resultado = individuoDomiciliosDao.getDomicilioByIdIndividuo(idIndividuo);			
		}catch (Exception e) {
			String msg = "Ha ocurrido un problema para obtener el domicilio.";
			throw new IndividuoDomicilioException(msg,e);
		}

		
		return resultado;
	}
}


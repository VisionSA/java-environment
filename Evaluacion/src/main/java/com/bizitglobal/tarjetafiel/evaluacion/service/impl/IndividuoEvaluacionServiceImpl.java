package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoEvaluacionService;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;

/**
*	Implementacion de la interfaz IndividuoEvaluacionService.
*/
public class IndividuoEvaluacionServiceImpl implements IndividuoEvaluacionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private IndividuoEvaluacionDao individuoEvaluacionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    
    private static Logger logger = Logger.getLogger(IndividuoEvaluacionServiceImpl.class);
    
	public void grabarIndividuo (final IndividuoEvaluacion pObject) throws IndividuoEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoEvaluacionDao.grabarEvaIndividuos(pObject);

			}
		});
	}
	
	public List getIndividuo(Filtro filtro) throws IndividuoEvaluacionException {
		try {			
			return individuoEvaluacionDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaindividuos no pudo ser leida.";
			throw new IndividuoEvaluacionException(msg,e);
		}
	}
	
	public IndividuoEvaluacion leerIndividuo (Long id) throws IndividuoEvaluacionException {
		IndividuoEvaluacion result = null;
		try {
			result = individuoEvaluacionDao.buscarEvaIndividuos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La IndividuoEvaluacion no existe en la base de datos.";
			throw new IndividuoEvaluacionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaindividuos no pudo leerse desde la base de datos.";
			throw new IndividuoEvaluacionException(msg,e);
		}
		return result;
	}
	
	public void borrarIndividuo (final Long id) throws IndividuoEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoEvaluacionDao.borrarEvaIndividuos(id);

			}
		});
	}
	
	public void borrarIndividuo(final IndividuoEvaluacion pObject) throws IndividuoEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoEvaluacionDao.borrarEvaIndividuos(pObject);

			}
		});
	}
	
	public void actualizarIndividuo (final IndividuoEvaluacion pObject) throws IndividuoEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				individuoEvaluacionDao.actualizarEvaIndividuos(pObject);

			}
		});
	}

	public IndividuoEvaluacion buscarIndividuo(String cuil)	throws IndividuoEvaluacionException {
		try {
			List indivList = individuoEvaluacionDao.listarTodos(new Filtro("cuil",Filtro.LIKE,cuil));
			if (!indivList.isEmpty()) {
				return (IndividuoEvaluacion)indivList.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			String msg = "El individuo no pudo ser leido.";
			throw new IndividuoEvaluacionException(msg,e);
		}
	}

	public IndividuoEvaluacionDao getIndividuoEvaluacionDao() {
		return individuoEvaluacionDao;
	}

	public void setIndividuoEvaluacionDao(
			IndividuoEvaluacionDao individuoEvaluacionDao) {
		this.individuoEvaluacionDao = individuoEvaluacionDao;
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

	public List<IndividuoEvaluacion> buscarIndividuosHabilitadosCuit(
			final Empresa empresa) throws IndividuoEvaluacionException {
		try{
			List<IndividuoEvaluacion> execute = (List<IndividuoEvaluacion>) transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					// TODO Auto-generated method stub
					return individuoEvaluacionDao.buscarIndividuosHabilitadosCuit(empresa);	
				}
			});
			return execute;
			
		}catch (Exception e) {
			logger.error(e,e);
			throw new IndividuoEvaluacionException(e.getMessage());
		}
		
		
	}
}


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.ActividadEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.service.ActividadEvaluacionService;

/**
*	Implementacion de la interfaz ActividadEvaluacionService.
*/
public class ActividadEvaluacionServiceImpl implements ActividadEvaluacionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ActividadEvaluacionDao actividadEvaluacionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarActividad (final ActividadEvaluacion pObject) throws ActividadEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				actividadEvaluacionDao.grabarEvaActividades(pObject);

			}
		});
	}
	public List getActividad(Filtro filtro) throws ActividadEvaluacionException {
		try {
			return actividadEvaluacionDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaactividades no pudo ser leida.";
			throw new ActividadEvaluacionException(msg,e);
		}
	}
	public ActividadEvaluacion leerActividad (Long id) throws ActividadEvaluacionException {
		ActividadEvaluacion result = null;
		try {
			result = actividadEvaluacionDao.buscarEvaActividades(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ActividadEvaluacion no existe en la base de datos.";
			throw new ActividadEvaluacionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaactividades no pudo leerse desde la base de datos.";
			throw new ActividadEvaluacionException(msg,e);
		}
		return result;
	}
	public void borrarActividad (final Long id) throws ActividadEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				actividadEvaluacionDao.borrarEvaActividades(id);

			}
		});
	}
	public void borrarActividad(final ActividadEvaluacion pObject) throws ActividadEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				actividadEvaluacionDao.borrarEvaActividades(pObject);

			}
		});
	}
	public void actualizarActividad (final ActividadEvaluacion pObject) throws ActividadEvaluacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				actividadEvaluacionDao.actualizarEvaActividades(pObject);

			}
		});
	}

	public ActividadEvaluacionDao getActividadEvaluacionDao() {
		return actividadEvaluacionDao;
	}
	public void setActividadEvaluacionDao(
			ActividadEvaluacionDao actividadEvaluacionDao) {
		this.actividadEvaluacionDao = actividadEvaluacionDao;
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


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.EducacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EducacionDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EducacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EducacionNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.evaluacion.service.EducacionService;

/**
*	Implementacion de la interfaz EducacionService.
*/
public class EducacionServiceImpl implements EducacionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EducacionDao educacionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarEducacion (final Educacion pObject) throws EducacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				educacionDao.grabarEvaEducaciones(pObject);

			}
		});
	}
	
	public List getEducacion(Filtro filtro) throws EducacionException {
		try {
			return educacionDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaeducaciones no pudo ser leida.";
			throw new EducacionException(msg,e);
		}
	}
	
	public Educacion leerEducacion (Long id) throws EducacionException {
		Educacion result = null;
		try {
			result = educacionDao.buscarEvaEducaciones(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Educacion no existe en la base de datos.";
			throw new EducacionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaeducaciones no pudo leerse desde la base de datos.";
			throw new EducacionException(msg,e);
		}
		return result;
	}
	
	public void borrarEducacion (final Long id) throws EducacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				educacionDao.borrarEvaEducaciones(id);

			}
		});
	}
	
	public void borrarEducacion(final Educacion pObject) throws EducacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				educacionDao.borrarEvaEducaciones(pObject);

			}
		});
	}
	
	public void actualizarEducacion (final Educacion pObject) throws EducacionException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				educacionDao.actualizarEvaEducaciones(pObject);

			}
		});
	}
	
	public EducacionDao getEducacionDao() {
		return educacionDao;
	}

	public void setEducacionDao(EducacionDao educacionDao) {
		this.educacionDao = educacionDao;
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


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.ConfirmacionTelefonicaDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ConfirmacionTelefonicaDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ConfirmacionTelefonicaException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ConfirmacionTelefonicaNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ConfirmacionTelefonica;
import com.bizitglobal.tarjetafiel.evaluacion.service.ConfirmacionTelefonicaService;

/**
*	Implementacion de la interfaz ConfirmacionTelefonicaService.
*/
public class ConfirmacionTelefonicaServiceImpl implements ConfirmacionTelefonicaService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ConfirmacionTelefonicaDao confirmacionTelefonicaDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarConfirmacionTelefonica (final ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				confirmacionTelefonicaDao.grabarEvaConfTelefonicas(pObject);

			}
		});
	}
	
	public List getConfirmacionTelefonica(Filtro filtro) throws ConfirmacionTelefonicaException {
		try {
			return confirmacionTelefonicaDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaconftelefonicas no pudo ser leida.";
			throw new ConfirmacionTelefonicaException(msg,e);
		}
	}
	
	public ConfirmacionTelefonica leerConfirmacionTelefonica (Long id) throws ConfirmacionTelefonicaException {
		ConfirmacionTelefonica result = null;
		try {
			result = confirmacionTelefonicaDao.buscarEvaConfTelefonicas(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ConfirmacionTelefonica no existe en la base de datos.";
			throw new ConfirmacionTelefonicaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaconftelefonicas no pudo leerse desde la base de datos.";
			throw new ConfirmacionTelefonicaException(msg,e);
		}
		return result;
	}
	
	public void borrarConfirmacionTelefonica (final Long id) throws ConfirmacionTelefonicaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				confirmacionTelefonicaDao.borrarEvaConfTelefonicas(id);

			}
		});
	}
	
	public void borrarConfirmacionTelefonica(final ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				confirmacionTelefonicaDao.borrarEvaConfTelefonicas(pObject);

			}
		});
	}
	
	public void actualizarConfirmacionTelefonica (final ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				confirmacionTelefonicaDao.actualizarEvaConfTelefonicas(pObject);

			}
		});
	}
	
	public ConfirmacionTelefonicaDao getConfirmacionTelefonicaDao() {
		return confirmacionTelefonicaDao;
	}

	public void setConfirmacionTelefonicaDao(
			ConfirmacionTelefonicaDao confirmacionTelefonicaDao) {
		this.confirmacionTelefonicaDao = confirmacionTelefonicaDao;
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


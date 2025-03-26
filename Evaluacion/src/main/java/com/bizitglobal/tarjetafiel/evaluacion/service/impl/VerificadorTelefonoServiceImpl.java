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
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorTelefonoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorTelefonoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorTelefonoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;
import com.bizitglobal.tarjetafiel.evaluacion.service.VerificadorTelefonoService;

/**
*	Implementacion de la interfaz VerificadorTelefonoService.
*/
public class VerificadorTelefonoServiceImpl implements VerificadorTelefonoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private VerificadorTelefonoDao verificadorTelefonoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarVerificadorTelefono (final VerificadorTelefono pObject) throws VerificadorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorTelefonoDao.grabarEvaVerifTelefonos(pObject);

			}
		});
	}
	
	public List getVerificadorTelefono(Filtro filtro) throws VerificadorTelefonoException {
		try {
			return verificadorTelefonoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaveriftelefonos no pudo ser leida.";
			throw new VerificadorTelefonoException(msg,e);
		}
	}
	
	public VerificadorTelefono leerVerificadorTelefono (Long id) throws VerificadorTelefonoException {
		VerificadorTelefono result = null;
		try {
			result = verificadorTelefonoDao.buscarEvaVerifTelefonos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La VerificadorTelefono no existe en la base de datos.";
			throw new VerificadorTelefonoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaveriftelefonos no pudo leerse desde la base de datos.";
			throw new VerificadorTelefonoException(msg,e);
		}
		return result;
	}
	
	public void borrarVerificadorTelefono (final Long id) throws VerificadorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorTelefonoDao.borrarEvaVerifTelefonos(id);

			}
		});
	}
	
	public void borrarVerificadorTelefono (final VerificadorTelefono pObject) throws VerificadorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorTelefonoDao.borrarEvaVerifTelefonos(pObject);

			}
		});
	}
	
	public void actualizarVerificadorTelefono (final VerificadorTelefono pObject) throws VerificadorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorTelefonoDao.actualizarEvaVerifTelefonos(pObject);

			}
		});
	}
	
	public VerificadorTelefonoDao getVerificadorTelefonoDao() {
		return verificadorTelefonoDao;
	}

	public void setVerificadorTelefonoDao(
			VerificadorTelefonoDao verificadorTelefonoDao) {
		this.verificadorTelefonoDao = verificadorTelefonoDao;
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


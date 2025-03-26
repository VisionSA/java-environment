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
import com.bizitglobal.tarjetafiel.evaluacion.dao.EmailsDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EmailsDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EmailsException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EmailsNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.service.EmailsService;

/**
*	Implementacion de la interfaz EmailsService.
*/
public class EmailsServiceImpl implements EmailsService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EmailsDao emailsDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarEmails (final Emails pObject) throws EmailsException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				emailsDao.grabarEvaEmails(pObject);

			}
		});
	}
	
	public List getEmails(Filtro filtro) throws EmailsException {
		try {
			return emailsDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaemails no pudo ser leida.";
			throw new EmailsException(msg,e);
		}
	}
	
	public Emails leerEmails (Long id) throws EmailsException {
		Emails result = null;
		try {
			result = emailsDao.buscarEvaEmails(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Emails no existe en la base de datos.";
			throw new EmailsNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaemails no pudo leerse desde la base de datos.";
			throw new EmailsException(msg,e);
		}
		return result;
	}
	
	public void borrarEmails (final Long id) throws EmailsException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				emailsDao.borrarEvaEmails(id);

			}
		});
	}
	
	public void borrarEmails(final Emails pObject) throws EmailsException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				emailsDao.borrarEvaEmails(pObject);

			}
		});
	}
	
	public void actualizarEmails (final Emails pObject) throws EmailsException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				emailsDao.actualizarEvaEmails(pObject);

			}
		});
	}
	
	
	
	public EmailsDao getEmailsDao() {
		return emailsDao;
	}

	public void setEmailsDao(EmailsDao emailsDao) {
		this.emailsDao = emailsDao;
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


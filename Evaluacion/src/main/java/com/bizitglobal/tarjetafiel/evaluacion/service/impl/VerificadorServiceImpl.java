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
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.evaluacion.service.VerificadorService;

/**
*	Implementacion de la interfaz VerificadorService.
*/
public class VerificadorServiceImpl implements VerificadorService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private VerificadorDao verificadorDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarVerificador (final Verificador pObject) throws VerificadorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorDao.grabarEvaVerificadores(pObject);

			}
		});
	}
	
	public List getVerificador (Filtro filtro) throws VerificadorException {
		try {
			return verificadorDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaverificadores no pudo ser leida.";
			throw new VerificadorException(msg,e);
		}
	}
	
	public Verificador leerVerificador (Long id) throws VerificadorException {
		Verificador result = null;
		try {
			result = verificadorDao.buscarEvaVerificadores(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La TipoIndividuo no existe en la base de datos.";
			throw new VerificadorNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evatiposindividuos no pudo leerse desde la base de datos.";
			throw new VerificadorException(msg,e);
		}
		return result;
	}
	
	public void borrarVerificador (final Long id) throws VerificadorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorDao.borrarEvaVerificadores(id);

			}
		});
	}
	
	public void borrarVerificador (final Verificador pObject) throws VerificadorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorDao.borrarEvaVerificadores(pObject);

			}
		});
	}
	
	public void actualizarVerificador (final Verificador pObject) throws VerificadorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				verificadorDao.actualizarEvaVerificadores(pObject);

			}
		});
	}

	public VerificadorDao getVerificadorDao() {
		return verificadorDao;
	}

	public void setVerificadorDao(VerificadorDao verificadorDao) {
		this.verificadorDao = verificadorDao;
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


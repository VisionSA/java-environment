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
import com.bizitglobal.tarjetafiel.evaluacion.dao.EsquemaIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EsquemaIndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EsquemaIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EsquemaIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.EsquemaIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.service.EsquemaIndividuoService;

/**
*	Implementacion de la interfaz EsquemaIndividuoService.
*/
public class EsquemaIndividuoServiceImpl implements EsquemaIndividuoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EsquemaIndividuoDao esquemaIndividuoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarEsquemaIndividuo (final EsquemaIndividuo pObject) throws EsquemaIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				esquemaIndividuoDao.grabarEsquemaIndividuo(pObject);

			}
		});
	}
	
	public List getEsquemaIndividuo(Filtro filtro) throws EsquemaIndividuoException {
		try {
			return esquemaIndividuoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de EsquemaIndividuo no pudo ser leida.";
			throw new EsquemaIndividuoException(msg,e);
		}
	}
	
	public EsquemaIndividuo leerEsquemaIndividuo (Long id) throws EsquemaIndividuoException {
		EsquemaIndividuo result = null;
		try {
			result = esquemaIndividuoDao.buscarEsquemaIndividuo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La EsquemaIndividuo no existe en la base de datos.";
			throw new EsquemaIndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La EsquemaIndividuo no pudo leerse desde la base de datos.";
			throw new EsquemaIndividuoException(msg,e);
		}
		return result;
	}
	
	public void borrarEsquemaIndividuo (final Long id) throws EsquemaIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				esquemaIndividuoDao.borrarEsquemaIndividuo(id);

			}
		});
	}
	
	public void borrarEsquemaIndividuo(final EsquemaIndividuo pObject) throws EsquemaIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				esquemaIndividuoDao.borrarEsquemaIndividuo(pObject);

			}
		});
	}
	
	public void actualizarEsquemaIndividuo (final EsquemaIndividuo pObject) throws EsquemaIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				esquemaIndividuoDao.actualizarEsquemaIndividuo(pObject);

			}
		});
	}
	
	public EsquemaIndividuoDao getEsquemaIndividuoDao() {
		return esquemaIndividuoDao;
	}

	public void setEsquemaIndividuoDao(EsquemaIndividuoDao esquemaIndividuoDao) {
		this.esquemaIndividuoDao = esquemaIndividuoDao;
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


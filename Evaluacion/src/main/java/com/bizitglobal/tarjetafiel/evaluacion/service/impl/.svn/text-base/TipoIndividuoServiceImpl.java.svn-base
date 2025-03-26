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
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.service.TipoIndividuoService;

/**
*	Implementacion de la interfaz TipoIndividuoService.
*/
public class TipoIndividuoServiceImpl implements TipoIndividuoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TipoIndividuoDao tipoIndividuoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTipoIndividuo (final TipoIndividuo pObject) throws TipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoIndividuoDao.grabarEvaTiposIndividuos(pObject);

			}
		});
	}
	
	public List getTipoIndividuo(Filtro filtro) throws TipoIndividuoException {
		try {
			return tipoIndividuoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evatiposindividuos no pudo ser leida.";
			throw new TipoIndividuoException(msg,e);
		}
	}
	
	public TipoIndividuo leerTipoIndividuo (Long id) throws TipoIndividuoException {
		TipoIndividuo result = null;
		try {
			result = tipoIndividuoDao.buscarEvaTiposIndividuos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La TipoIndividuo no existe en la base de datos.";
			throw new TipoIndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evatiposindividuos no pudo leerse desde la base de datos.";
			throw new TipoIndividuoException(msg,e);
		}
		return result;
	}
	
	public void borrarTipoIndividuo (final Long id) throws TipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoIndividuoDao.borrarEvaTiposIndividuos(id);

			}
		});
	}
	
	public void borrarTipoIndividuo (final TipoIndividuo pObject) throws TipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoIndividuoDao.borrarEvaTiposIndividuos(pObject);

			}
		});
	}
	
	public void actualizarTipoIndividuo (final TipoIndividuo pObject) throws TipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoIndividuoDao.actualizarEvaTiposIndividuos(pObject);

			}
		});
	}
	
	public TipoIndividuoDao getTipoIndividuoDao() {
		return tipoIndividuoDao;
	}

	public void setTipoIndividuoDao(TipoIndividuoDao tipoIndividuoDao) {
		this.tipoIndividuoDao = tipoIndividuoDao;
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


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoClearingDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoClearingException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoClearingNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoClearing;
import com.bizitglobal.tarjetafiel.evaluacion.service.TipoClearingService;

/**
*	Implementacion de la interfaz TipoClearingService.
*/
public class TipoClearingServiceImpl implements TipoClearingService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TipoClearingDao tipoClearingDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTipoClearing (final TipoClearing pObject) throws TipoClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoClearingDao.grabarEvaTiposClearings(pObject);

			}
		});
	}
	
	public List getTipoClearing (Filtro filtro) throws TipoClearingException {
		try {
			return tipoClearingDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evatiposclearings no pudo ser leida.";
			throw new TipoClearingException(msg,e);
		}
	}
	
	public TipoClearing leerTipoClearing (Long id) throws TipoClearingException {
		TipoClearing result = null;
		try {
			result = tipoClearingDao.buscarEvaTiposClearings(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La TipoClearing no existe en la base de datos.";
			throw new TipoClearingNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evatiposclearings no pudo leerse desde la base de datos.";
			throw new TipoClearingException(msg,e);
		}
		return result;
	}
	
	public void borrarTipoClearing (final Long id) throws TipoClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoClearingDao.borrarEvaTiposClearings(id);

			}
		});
	}
	
	public void borrarTipoClearing (final TipoClearing pObject) throws TipoClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoClearingDao.borrarEvaTiposClearings(pObject);

			}
		});
	}
	
	public void actualizarTipoClearing (final TipoClearing pObject) throws TipoClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tipoClearingDao.actualizarEvaTiposClearings(pObject);

			}
		});
	}

	public TipoClearingDao getTipoClearingDao() {
		return tipoClearingDao;
	}

	public void setTipoClearingDao(TipoClearingDao tipoClearingDao) {
		this.tipoClearingDao = tipoClearingDao;
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


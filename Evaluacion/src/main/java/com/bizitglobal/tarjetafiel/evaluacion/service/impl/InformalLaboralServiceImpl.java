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
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;
import com.bizitglobal.tarjetafiel.evaluacion.service.InformeLaboralService;

/**
*	Implementacion de la interfaz InformeLaboralService.
*/
public class InformalLaboralServiceImpl implements InformeLaboralService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private InformeLaboralDao informeLaboralDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarInformeLaboral (final InformeLaboral pObject) throws InformeLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeLaboralDao.grabarEvaInfoLaborales(pObject);

			}
		});
	}
	
	public List getInformeLaboral(Filtro filtro) throws InformeLaboralException {
		try {
			return informeLaboralDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evainfolaborales no pudo ser leida.";
			throw new InformeLaboralException(msg,e);
		}
	}
	
	public InformeLaboral leerInformeLaboral (Long id) throws InformeLaboralException {
		InformeLaboral result = null;
		try {
			result = informeLaboralDao.buscarEvaInfoLaborales(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La InformeLaboral no existe en la base de datos.";
			throw new InformeLaboralNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evainfolaborales no pudo leerse desde la base de datos.";
			throw new InformeLaboralException(msg,e);
		}
		return result;
	}
	
	public void borrarInformeLaboral (final Long id) throws InformeLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeLaboralDao.borrarEvaInfoLaborales(id);

			}
		});
	}
	
	public void borrarInformeLaboral(final InformeLaboral pObject) throws InformeLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeLaboralDao.borrarEvaInfoLaborales(pObject);

			}
		});
	}
	
	public void actualizarInformeLaboral (final InformeLaboral pObject) throws InformeLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeLaboralDao.actualizarEvaInfoLaborales(pObject);

			}
		});
	}
	
	
	
	public InformeLaboralDao getInformeLaboralDao() {
		return informeLaboralDao;
	}

	public void setInformeLaboralDao(InformeLaboralDao informeLaboralDao) {
		this.informeLaboralDao = informeLaboralDao;
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


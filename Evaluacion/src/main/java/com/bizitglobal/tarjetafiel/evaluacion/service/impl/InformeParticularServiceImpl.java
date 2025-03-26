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
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeParticularDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeParticular;
import com.bizitglobal.tarjetafiel.evaluacion.service.InformeParticularService;

/**
*	Implementacion de la interfaz InformeParticularService.
*/
public class InformeParticularServiceImpl implements InformeParticularService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private InformeParticularDao informeParticularDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarInformeParticular(final InformeParticular pObject) throws InformeParticularException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeParticularDao.grabarEvaInfoParticulares(pObject);

			}
		});
	}
	
	public List getInformeParticular(Filtro filtro) throws InformeParticularException {
		try {
			return informeParticularDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evainfoparticulares no pudo ser leida.";
			throw new InformeParticularException(msg,e);
		}
	}
	public InformeParticular leerInformeParticular(Long id) throws InformeParticularException {
		InformeParticular result = null;
		try {
			result = informeParticularDao.buscarEvaInfoParticulares(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La InformeParticular no existe en la base de datos.";
			throw new InformeParticularNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evainfoparticulares no pudo leerse desde la base de datos.";
			throw new InformeParticularException(msg,e);
		}
		return result;
	}
	public void borrarInformeParticular(final Long id) throws InformeParticularException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeParticularDao.borrarEvaInfoParticulares(id);

			}
		});
	}
	public void borrarEvaInfoParticulares(final InformeParticular pObject) throws InformeParticularException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeParticularDao.borrarEvaInfoParticulares(pObject);

			}
		});
	}
	public void actualizarInformeParticular(final InformeParticular pObject) throws InformeParticularException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				informeParticularDao.actualizarEvaInfoParticulares(pObject);

			}
		});
	}
	
	public InformeParticularDao getInformeParticularDao() {
		return informeParticularDao;
	}

	public void setInformeParticularDao(InformeParticularDao informeParticularDao) {
		this.informeParticularDao = informeParticularDao;
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
	public void borrarInformeParticular(InformeParticular pObject) throws InformeParticularException {
		// TODO Auto-generated method stub
		
	}
}


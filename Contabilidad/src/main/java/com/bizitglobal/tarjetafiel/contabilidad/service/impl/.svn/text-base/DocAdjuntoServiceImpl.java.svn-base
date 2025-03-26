package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.DocAdjuntoDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.DocAdjuntoDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.DocAdjuntoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.DocAdjuntoNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.DocAdjunto;
import com.bizitglobal.tarjetafiel.contabilidad.service.DocAdjuntoService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class DocAdjuntoServiceImpl implements DocAdjuntoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private DocAdjuntoDao docAdjuntoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private PlatformTransactionManager transactionManagerSpring;
	private TransactionTemplate transactionTemplate;
    private TransactionTemplate transactionTemplateSpring;
    
	public void grabarDocAdjunto (final DocAdjunto pObject) throws DocAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					docAdjuntoDao.grabarDocAdjunto(pObject);

				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el DocAdjunto ya existe en la base de datos.";
			throw new DocAdjuntoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	public List getDocAdjunto(final Filtro filtro) throws DocAdjuntoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = docAdjuntoDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DocAdjunto no pudo ser leida.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	public DocAdjunto leerDocAdjunto (final Long id) throws DocAdjuntoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (DocAdjunto) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				DocAdjunto doc = docAdjuntoDao.buscarDocAdjunto(id);
				return doc;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El DocAdjunto no existe en la base de datos.";
			throw new DocAdjuntoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El DocAdjunto no pudo leerse desde la base de datos.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	public void borrarDocAdjunto (final Long id) throws DocAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					docAdjuntoDao.borrarDocAdjunto(id);
				}
			});
		} catch (Exception e) {
			String msg = "El DocAdjunto no pudo borrase.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	public void borrarDocAdjunto(final DocAdjunto pObject) throws DocAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					docAdjuntoDao.borrarDocAdjunto(pObject);
				}
			});
			docAdjuntoDao.borrarDocAdjunto(pObject);
		} catch (Exception e) {
			String msg = "El DocAdjunto no pudo borrase.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	public void actualizarDocAdjunto (final DocAdjunto pObject) throws DocAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					docAdjuntoDao.actualizarDocAdjunto(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El DocAdjunto no pudo actualizarse.";
			throw new DocAdjuntoException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public DocAdjuntoDao getDocAdjuntoDao() {
		return docAdjuntoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setDocAdjuntoDao(DocAdjuntoDao DocAdjuntoDao) {
		this.docAdjuntoDao = docAdjuntoDao;
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
	
	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}

	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}
	
}


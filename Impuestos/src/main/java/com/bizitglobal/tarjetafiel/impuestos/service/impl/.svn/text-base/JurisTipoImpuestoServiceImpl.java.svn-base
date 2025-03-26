package com.bizitglobal.tarjetafiel.impuestos.service.impl;
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
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisTipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisTipoImpuestoService;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionService;

/**
*	Implementacion de la interfaz JurisdiccionService.
*/
public class JurisTipoImpuestoServiceImpl implements JurisTipoImpuestoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private JurisTipoImpuestoDao jurisTipoImpuestoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarJurisTipoImpuesto(final JurisTipoImpuesto pObject) throws JurisTipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisTipoImpuestoDao.grabarJurisTipoImpuesto(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La jurisTipoImpuesto ya existe en la base de datos.";
			throw new JurisTipoImpuestoException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisTipoImpuesto no pudo ser grabada.";
			throw new JurisTipoImpuestoException (msg);
		}
	}
	
	public List getJurisTipoImpuesto(final Filtro filtro) throws JurisTipoImpuestoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = jurisTipoImpuestoDao.listarTodos(filtro);
				return lista;
				}
			});
			
		} catch (Exception e) {
			String msg = "La lista de jurisTipoImpuesto no pudo ser leida.";
			throw new JurisTipoImpuestoException(msg,e);
		}
	}
	public JurisTipoImpuesto leerJurisTipoImpuesto(final Long id) throws JurisTipoImpuestoException {
		JurisTipoImpuesto result = null;
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (JurisTipoImpuesto) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  JurisTipoImpuesto jurisTipoImpuesto  = jurisTipoImpuestoDao.buscarJurisTipoImpuesto(id);
					return jurisTipoImpuesto;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La JurisTipoImpuesto no existe en la base de datos.";
			throw new JurisTipoImpuestoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisTipoImpuesto no pudo leerse desde la base de datos.";
			throw new JurisTipoImpuestoException(msg,e);
		}
	}
	
	public void borrarJurisTipoImpuesto(final Long id) throws JurisTipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisTipoImpuestoDao.borrarJurisTipoImpuesto(id);
				}
			});
		} catch (Exception e) {
			String msg = "La jurisTipoImpuesto no pudo borrase.";
			throw new JurisTipoImpuestoException(msg,e);
		}
	}
	
	public void actualizarJurisTipoImpuesto(final JurisTipoImpuesto pObject) throws JurisTipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisTipoImpuestoDao.actualizarJurisTipoImpuesto(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "La jurisTipoImpuesto no pudo actualizarse.";
			throw new JurisTipoImpuestoException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public JurisTipoImpuestoDao getJurisTipoImpuestoDao() {
		return jurisTipoImpuestoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setJurisTipoImpuestoDao(JurisTipoImpuestoDao jurisTipoImpuestoDao) {
		this.jurisTipoImpuestoDao = jurisTipoImpuestoDao;
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


	public void borrarJurisTipoImpuesto(JurisTipoImpuesto pObject) throws JurisTipoImpuestoException {
		// TODO Auto-generated method stub
		
	}
}


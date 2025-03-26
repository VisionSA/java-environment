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
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionService;

/**
*	Implementacion de la interfaz JurisdiccionService.
*/
public class JurisdiccionServiceImpl implements JurisdiccionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private JurisdiccionDao jurisdiccionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarJurisdiccion (final Jurisdiccion pObject) throws JurisdiccionException {
		 try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult(){
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
						jurisdiccionDao.grabarJurisdiccion(pObject);
					}
				});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La jurisdiccion ya existe en la base de datos.";
			throw new JurisdiccionDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisdiccion no pudo ser grabada.";
			throw new JurisdiccionException(msg,e);
		}
	}
	public List getJurisdiccion(final Filtro filtro) throws JurisdiccionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = jurisdiccionDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de jurisdiccion no pudo ser leida.";
			throw new JurisdiccionException(msg,e);
		}
	}
	public Jurisdiccion leerJurisdiccion (final Long id) throws JurisdiccionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Jurisdiccion) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Jurisdiccion jurisdiccion  = jurisdiccionDao.buscarJurisdiccion(id);
					return jurisdiccion;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Jurisdiccion no existe en la base de datos.";
			throw new JurisdiccionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisdiccion no pudo leerse desde la base de datos.";
			throw new JurisdiccionException(msg,e);
		}
	}
	
	public void borrarJurisdiccion (final Long id) throws JurisdiccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisdiccionDao.borrarJurisdiccion(id);
				}
			});
		} catch (Exception e) {
			String msg = "La jurisdiccion no pudo borrase.";
			throw new JurisdiccionException(msg,e);
		}
	}
	
	public void borrarJurisdiccion(final Jurisdiccion pObject) throws JurisdiccionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisdiccionDao.borrarJurisdiccion(pObject);
				}
			});
	
		} catch (Exception e) {
			String msg = "La jurisdiccion no pudo borrase.";
			throw new JurisdiccionException(msg,e);
		}
	}
	public void actualizarJurisdiccion (final Jurisdiccion pObject) throws JurisdiccionException {
		try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult(){
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
						jurisdiccionDao.actualizarJurisdiccion(pObject);
					}
				});
		} catch (Exception e) {
			String msg = "La jurisdiccion no pudo actualizarse.";
			throw new JurisdiccionException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public JurisdiccionDao getJurisdiccionDao() {
		return jurisdiccionDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setJurisdiccionDao(JurisdiccionDao jurisdiccionDao) {
		this.jurisdiccionDao = jurisdiccionDao;
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


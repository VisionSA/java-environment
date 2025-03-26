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
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionActividadService;

/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class JurisdiccionActividadServiceImpl implements JurisdiccionActividadService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private JurisdiccionActividadDao jurisdiccionActividadDao;
	private TransactionTemplate transactionTemplate;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	
	public void grabarJurisdiccionActividad(final JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException {
		try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult(){
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
						jurisdiccionActividadDao.grabarJurisdiccionActividad(unaJurisdiccionActividad);
					}
				});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La jurisdiccionActividad ya existe en la base de datos.";
			throw new JurisdiccionActividadDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisdiccionActividad no pudo ser grabado.";
			throw new JurisdiccionActividadException(msg,e);
		}
	}

	public List getJurisdiccionActividads(final Filtro unFiltro) throws JurisdiccionActividadException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = jurisdiccionActividadDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de jurisdiccionActividads no pudo ser leida.";
			throw new JurisdiccionActividadException(msg,e);
		}
	}
	
	public JurisdiccionActividad leerJurisdiccionActividad(final Long id) throws JurisdiccionActividadException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (JurisdiccionActividad) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  JurisdiccionActividad jurisdiccionActividad  = jurisdiccionActividadDao.buscarJurisdiccionActividad(id);
					return jurisdiccionActividad;
					}
			});	
			
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La jurisdiccionActividad no existe en la base de datos.";
			throw new JurisdiccionActividadNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La jurisdiccionActividad no pudo leerse desde la base de datos.";
			throw new JurisdiccionActividadException(msg,e);
		}
		
	}
	
	public void borrarJurisdiccionActividad(final Long idJurisdiccionActividad) throws JurisdiccionActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisdiccionActividadDao.borrarJurisdiccionActividad(idJurisdiccionActividad);
				}
			});
		} catch (Exception e) {
			String msg = "La jurisdiccionActividad no pudo borrase.";
			throw new JurisdiccionActividadException(msg,e);
		}
	}
	
	public void borrarJurisdiccionActividad(final JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisdiccionActividadDao.borrarJurisdiccionActividad(unaJurisdiccionActividad);
				}
			});
		} catch (Exception e) {
			String msg = "La jurisdiccionActividad no pudo borrase.";
			throw new JurisdiccionActividadException(msg,e);
		}
	}	
	
	public void actualizarJurisdiccionActividad(final JurisdiccionActividad unaJurisdiccionActividad) throws JurisdiccionActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					jurisdiccionActividadDao.actualizarJurisdiccionActividad(unaJurisdiccionActividad);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la jurisdiccionActividad.";
			throw new JurisdiccionActividadException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public JurisdiccionActividadDao getJurisdiccionActividadDao() {
		return jurisdiccionActividadDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setJurisdiccionActividadDao(JurisdiccionActividadDao jurisdiccionActividadDao) {
		this.jurisdiccionActividadDao = jurisdiccionActividadDao;
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

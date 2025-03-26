package com.bizitglobal.tarjetafiel.proveedores.service.impl;

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
import com.bizitglobal.tarjetafiel.proveedores.dao.GrupoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;
import com.bizitglobal.tarjetafiel.proveedores.service.GrupoService;

/**
 *	Implementacion de la interfaz GrupoService.
 */
public class GrupoServiceImpl implements GrupoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private GrupoDao grupoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarGrupo(final Grupo unGrupo) throws GrupoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					grupoDao.grabarGrupo(unGrupo);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El grupo ya existe en la base de datos.";
			throw new GrupoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El grupo no pudo ser grabado.";
			throw new GrupoException(msg,e);
		}
	}

	public List getGrupos(final Filtro filtro) throws GrupoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return grupoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de grupos no pudo ser leida.";
			throw new GrupoException(msg,e);
		}
	}
	
	public Grupo leerGrupo(Long id) throws GrupoException {
		Grupo result = null;
		
		try {
			result = grupoDao.buscarGrupo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El grupo no existe en la base de datos.";
			throw new GrupoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El grupo no pudo leerse desde la base de datos.";
			throw new GrupoException(msg,e);
		}
		
		return result;
	}
	
	public void borrarGrupo(final Long idGrupo) throws GrupoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					grupoDao.borrarGrupo(idGrupo);
				}
			});
		} catch (Exception e) {
			String msg = "El grupo no pudo borrase.";
			throw new GrupoException(msg,e);
		}
	}
	
	public void borrarGrupo(final Grupo unGrupo) throws GrupoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					grupoDao.borrarGrupo(unGrupo);
				}
			});
		} catch (Exception e) {
			String msg = "El grupo no pudo borrase.";
			throw new GrupoException(msg,e);
		}
	}	
	
	public void actualizarGrupo(final Grupo unGrupo) throws GrupoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					grupoDao.actualizarGrupo(unGrupo);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el grupo.";
			throw new GrupoException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public GrupoDao getGrupoDao() {
		return grupoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setGrupoDao(GrupoDao grupoDao) {
		this.grupoDao = grupoDao;
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

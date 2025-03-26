package com.bizitglobal.tarjetafiel.operador.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.dao.PaginaDao;
import com.bizitglobal.tarjetafiel.operador.exeption.PaginaDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.PaginaException;
import com.bizitglobal.tarjetafiel.operador.exeption.PaginaNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;
import com.bizitglobal.tarjetafiel.operador.service.PaginaService;

/**
 *	Implementacion de la interfaz PaginaService.
 */
public class PaginaServiceImpl implements PaginaService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private PaginaDao paginaDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PaginaService#grabarPagina(com.bizitglobal.tarjetafiel.acceso.negocio.Pagina, java.util.Iterator)
	 */
	public void grabarPagina(final Pagina unPagina) throws PaginaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					paginaDao.savePagina(unPagina);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El pagina ya existe en la base de datos.";
			throw new PaginaDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El pagina no pudo ser grabado.";
			throw new PaginaException(msg,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PaginaService#getPaginas(java.util.Iterator)
	 */
	public List getPaginas() throws PaginaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  paginaDao.listAll();
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de paginas no pudo ser leida.";
			throw new PaginaException(msg,e);
		}
	}
	
	public List getPaginas(final Filtro filtro) throws PaginaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  paginaDao.listAll(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de paginas no pudo ser leida.";
			throw new PaginaException(msg,e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PaginaService#leerPagina(java.lang.Integer, java.util.Iterator)
	 */
	public Pagina leerPagina(final Long id) throws PaginaException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Pagina) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Pagina pagina = paginaDao.findPagina(id);
					return pagina;
					}
				});	 
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El pagina no existe en la base de datos.";
			throw new PaginaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El pagina no pudo leerse desde la base de datos.";
			throw new PaginaException(msg,e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PaginaService#borrarPagina(java.lang.Integer)
	 */
	public void borrarPagina(final Long idPagina) throws PaginaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					paginaDao.deletePagina(idPagina);
				}
			});
		} catch (Exception e) {
			String msg = "El pagina no pudo borrase.";
			throw new PaginaException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public PaginaDao getPaginaDao() {
		return paginaDao;
	}

	/**
	 * Necesario para spring.
	 * @param paginaDao, Objeto dao a setear.
	 */
	public void setPaginaDao(PaginaDao paginaDao) {
		this.paginaDao = paginaDao;
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
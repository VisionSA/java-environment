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
import com.bizitglobal.tarjetafiel.impuestos.dao.CategoriaDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.service.CategoriaService;

/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class CategoriaServiceImpl implements CategoriaService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CategoriaDao categoriaDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarCategoria(final Categoria unaCategoria) throws CategoriaException {
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult(){
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
						categoriaDao.grabarCategoria(unaCategoria);
					}
				});
			
		} catch (DataIntegrityViolationException ex) {
			String msg = "La categoria ya existe en la base de datos.";
			throw new CategoriaDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La categoria no pudo ser grabado.";
			throw new CategoriaException(msg,e);
		}
	}

	public List getCategorias(final Filtro unFiltro) throws CategoriaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = categoriaDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de categorias no pudo ser leida.";
			throw new CategoriaException(msg,e);
		}
	}
	
	public Categoria leerCategoria(final Long id) throws CategoriaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Categoria) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Categoria categoria  = categoriaDao.buscarCategoria(id);
					return categoria;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La categoria no existe en la base de datos.";
			throw new CategoriaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La categoria no pudo leerse desde la base de datos.";
			throw new CategoriaException(msg,e);
		}
	}
	
	public void borrarCategoria(final Long idCategoria) throws CategoriaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					categoriaDao.borrarCategoria(idCategoria);
				}
			});

		} catch (Exception e) {
			String msg = "La categoria no pudo borrase.";
			throw new CategoriaException(msg,e);
		}
	}
	
	public void borrarCategoria(final Categoria unaCategoria) throws CategoriaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					categoriaDao.borrarCategoria(unaCategoria);
				}
			});
		} catch (Exception e) {
			String msg = "La categoria no pudo borrase.";
			throw new CategoriaException(msg,e);
		}
	}	
	
	public void actualizarCategoria(final  Categoria unaCategoria) throws CategoriaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					categoriaDao.actualizarCategoria(unaCategoria);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la categoria.";
			throw new CategoriaException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
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

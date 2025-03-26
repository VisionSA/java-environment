package com.bizitglobal.tarjetafiel.fondos.service.impl;

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
import com.bizitglobal.tarjetafiel.fondos.dao.BancoPropioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.BancoPropioDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.service.BancoPropioService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class BancoPropioServiceImpl implements BancoPropioService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private BancoPropioDao bancoPropioDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarBancoPropio(final BancoPropio unaBancoPropio) throws BancoPropioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					bancoPropioDao.grabarBancoPropio(unaBancoPropio);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La BancoPropio ya existe en la base de datos.";
			throw new BancoPropioDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La BancoPropio no pudo ser grabada.";
			throw new BancoPropioException(msg,e);
		}
		
	}
	
	public List getBancoPropios() throws BancoPropioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return bancoPropioDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de BancoPropio no pudo ser leida.";
			throw new BancoPropioException(msg,e);
		}
	}
	
	public BancoPropio leerBancoPropio(Long id) throws BancoPropioException {
		try {
			return bancoPropioDao.buscarBancoPropio(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La bancoPropio no existe en la base de datos.";
			throw new BancoPropioNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La BancoPropio no pudo leerse desde la base de datos.";
			throw new BancoPropioException(msg,e);
		}
	}
	
	public void borrarBancoPropio(final Long idBancoPropio) throws BancoPropioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					bancoPropioDao.borrarBancoPropio(idBancoPropio);
				}
			});
		} catch (Exception e) {
			String msg = "La BancoPropio no pudo borrase.";
			throw new BancoPropioException(msg,e);
		}
	}
	
	public void borrarBancoPropio(final BancoPropio unaBancoPropio) throws BancoPropioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					bancoPropioDao.borrarBancoPropio(unaBancoPropio);
				}
			});
		} catch (Exception e) {
			String msg = "La BancoPropio no pudo borrase.";
			throw new BancoPropioException(msg,e);
		}
	}
	
	public void actualizarBancoPropio(final BancoPropio unaBancoPropio) throws BancoPropioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					bancoPropioDao.actualizarBancoPropio(unaBancoPropio);
				}
			});
		} catch (Exception e) {
			String msg = "La BancoPropio no pudo actualizarse.";
			throw new BancoPropioException(msg,e);
		}
	}
	
	public List getBancoPropios(final Filtro filtro) throws BancoPropioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return bancoPropioDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de BancoPropio no pudo ser leida.";
			throw new BancoPropioException(msg,e);
		}
	}
	public List getBancoPropiosFlex(final Filtro filtro) throws BancoPropioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return ((BancoPropioDaoHibernateImpl)bancoPropioDao).listarTodosFlex(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de BancoPropio no pudo ser leida.";
			throw new BancoPropioException(msg,e);
		}
	}
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public BancoPropioDao getBancoPropioDao() {
		return bancoPropioDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setBancoPropioDao(BancoPropioDao bancoPropioDao) {
		this.bancoPropioDao = bancoPropioDao;
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

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
import com.bizitglobal.tarjetafiel.fondos.dao.CajaMPDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.CajaMPDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.fondos.service.CajaMPService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class CajaMPServiceImpl implements CajaMPService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CajaMPDao cajaMPDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarCajaMP(final CajaMP unaCajaMP) throws CajaMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaMPDao.grabarCajaMP(unaCajaMP);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La CajaMP ya existe en la base de datos.";
			throw new CajaMPDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaMP no pudo ser grabada.";
			throw new CajaMPException(msg,e);
		}
		
	}
	
	public List getCajaMPes() throws CajaMPException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaMPDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaMP no pudo ser leida.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public List listarFormaPagoCajaFlex(final Filtro filtro) throws CajaMPException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return ((CajaMPDaoHibernateImpl)cajaMPDao).listarTodosFlex(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaMP no pudo ser leida.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public CajaMP leerCajaMP(Long id) throws CajaMPException {
		try {
			return cajaMPDao.buscarCajaMP(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La cajaMP no existe en la base de datos.";
			throw new CajaMPNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaMP no pudo leerse desde la base de datos.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public void borrarCajaMP(final Long idCajaMP) throws CajaMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaMPDao.borrarCajaMP(idCajaMP);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaMP no pudo borrase.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public void borrarCajaMP(final CajaMP unaCajaMP) throws CajaMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaMPDao.borrarCajaMP(unaCajaMP);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaMP no pudo borrase.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public void actualizarCajaMP(final CajaMP unaCajaMP) throws CajaMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaMPDao.actualizarCajaMP(unaCajaMP);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaMP no pudo actualizarse.";
			throw new CajaMPException(msg,e);
		}
	}
	
	public List getCajaMPs(final Filtro filtro) throws CajaMPException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaMPDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaMP no pudo ser leida.";
			throw new CajaMPException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CajaMPDao getCajaMPDao() {
		return cajaMPDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setCajaMPDao(CajaMPDao cajaMPDao) {
		this.cajaMPDao = cajaMPDao;
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

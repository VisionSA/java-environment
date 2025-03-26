package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.DetalleExtractoDao;
import com.bizitglobal.tarjetafiel.fondos.exception.DetalleExtractoDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.DetalleExtractoException;
import com.bizitglobal.tarjetafiel.fondos.exception.DetalleExtractoNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.service.DetalleExtractoService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class DetalleExtractoServiceImpl implements DetalleExtractoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private DetalleExtractoDao detalleExtractoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarDetalleExtracto(final DetalleExtracto unaDetalleExtracto) throws DetalleExtractoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					detalleExtractoDao.grabarDetalleExtracto(unaDetalleExtracto);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La DetalleExtracto ya existe en la base de datos.";
			throw new DetalleExtractoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo ser grabada.";
			throw new DetalleExtractoException(msg,e);
		}
		
	}
	
	public List getDetalleExtractoes() throws DetalleExtractoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return detalleExtractoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DetalleExtracto no pudo ser leida.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public DetalleExtracto leerDetalleExtracto(Long id) throws DetalleExtractoException {
		try {
			return detalleExtractoDao.buscarDetalleExtracto(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La detalleExtracto no existe en la base de datos.";
			throw new DetalleExtractoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo leerse desde la base de datos.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public void borrarDetalleExtracto(final Long idDetalleExtracto) throws DetalleExtractoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					detalleExtractoDao.borrarDetalleExtracto(idDetalleExtracto);
				}
			});
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo borrase.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public void borrarDetalleExtracto(final DetalleExtracto unaDetalleExtracto) throws DetalleExtractoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					detalleExtractoDao.borrarDetalleExtracto(unaDetalleExtracto);
				}
			});
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo borrase.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public void actualizarDetalleExtracto(final DetalleExtracto unaDetalleExtracto) throws DetalleExtractoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					detalleExtractoDao.actualizarDetalleExtracto(unaDetalleExtracto);
				}
			});
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo actualizarse.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public List getDetalleExtractos(final Filtro filtro) throws DetalleExtractoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return detalleExtractoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DetalleExtracto no pudo ser leida.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public List getDetalleExtractosFlex(final Filtro filtro) throws DetalleExtractoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return detalleExtractoDao.listarTodosFlex(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DetalleExtracto no pudo ser leida.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public List getDetalleExtractosNoConciliadosFlex(final Filtro filtro) throws DetalleExtractoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return detalleExtractoDao.listarTodosNoConciliadosFlex(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DetalleExtracto no pudo ser leida.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public double saldoBancoFechaCorte(final Long idBancoPropio, final Date fechaCorte) throws DetalleExtractoException {
		try {
			return detalleExtractoDao.traerSaldoExtractoHastaFecha(idBancoPropio, fechaCorte, 'S');
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo leerse.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	
	public double saldoMovimientosNoConciliadosHastaFecha(final Long idBancoPropio, final Date fechaCorte) throws DetalleExtractoException {
		try {
			return detalleExtractoDao.traerSaldoExtractoHastaFecha(idBancoPropio, fechaCorte, 'N');
		} catch (Exception e) {
			String msg = "La DetalleExtracto no pudo leerse.";
			throw new DetalleExtractoException(msg,e);
		}
	}
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public DetalleExtractoDao getDetalleExtractoDao() {
		return detalleExtractoDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setDetalleExtractoDao(DetalleExtractoDao detalleExtractoDao) {
		this.detalleExtractoDao = detalleExtractoDao;
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

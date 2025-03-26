package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.contabilidad.service.LoteService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class LoteServiceImpl implements LoteService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private LoteDao loteDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private PlatformTransactionManager transactionManagerSpring;
	private TransactionTemplate transactionTemplate;
    private TransactionTemplate transactionTemplateSpring;

	public void grabarLote (final Lote pObject) throws LoteException {
		try {
			transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteDao.grabarLote(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el Lote ya existe en la base de datos.";
			throw new LoteDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Lote no pudo ser grabado.";
			throw new LoteException(msg,e);
		}
	}
	
	public List getLote(final Filtro filtro) throws LoteException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				return loteDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Lote no pudo ser leida.";
			throw new LoteException(msg,e);
		}
	}
	
	public Long contarLotes(final Filtro filtro) throws LoteException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				return loteDao.contarLotes(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "Error al intentar contar los Lotes.";
			throw new LoteException(msg,e);
		}
	}
	
	public Lote leerLote (final Long id) throws LoteException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Lote) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Lote lote = loteDao.buscarLote(id);
				return lote;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Lote no existe en la base de datos.";
			throw new LoteNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El Lote no pudo leerse desde la base de datos.";
			throw new LoteException(msg,e);
		}
	}
	
	public void borrarLote (final Long id) throws LoteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteDao.borrarLote(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Lote no pudo borrase.";
			throw new LoteException(msg,e);
		}
	}
	public void borrarLote(final Lote pObject) throws LoteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteDao.borrarLote(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new LoteException(msg,e);
		}
	}
	
	public void actualizarLote (final Lote pObject) throws LoteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteDao.actualizarLote(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Lote no pudo actualizarse.";
			throw new LoteException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public LoteDao getLoteDao() {
		return loteDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setLoteDao(LoteDao loteDao) {
		this.loteDao = loteDao;
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
	
	public void asentarLote(final Long idEjercicio, final Long idEmpresa, final Long idAsiento) throws LoteException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDao.moverLote(idEjercicio, idEmpresa, idAsiento);
			}
		});
	}
	
//	public void asentarDetallesDelLote(Long idEjercicio, Long idEmpresa, Long idAsiento) throws LoteException {
//		loteDao.moverDetallesDelLote(idEjercicio, idEmpresa, idAsiento);
//	}
	public Long getLastIdDeAsientos() {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long num = loteDao.getLastIdDeAsiento();
			return num;
			}
		});
	}
	
	public Long getLastIdDeLotes() {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long num = loteDao.getLastIdDeLote();
			return num;
			}
		});
	}
	
	public List getLoteConsultaManual(final Filtro filtro) throws LoteException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List lista = loteDao.listarTodosConsultaEspecial(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteDetalle no pudo ser leida.";
			throw new LoteException(msg,e);
		}
	}
	public void impactarOrigenContab(final Long nroAsiento, final Long idProveedor, final Long idComprobante) {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDao.grabarOrigenContab(nroAsiento, idProveedor, idComprobante);
			}
		});
	}
	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}
	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}
	
}


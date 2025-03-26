package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.service.LoteDetalleService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class LoteDetalleServiceImpl implements LoteDetalleService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private LoteDetalleDao loteDetalleDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;

	public void grabar(final LoteDetalle pObject) throws LoteDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDetalleDao.grabar(pObject);
                System.out.println("He grabado el lote detalle de manera transaccional...");
			}
		});
	}
	
	public List getLoteDetalle(final Filtro filtro) throws LoteDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = loteDetalleDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteDetalle no pudo ser leida.";
			throw new LoteDetalleException(msg,e);
		}
	}
	
	public List getLoteDetalleConsultaManual(final Filtro filtro) throws LoteDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = loteDetalleDao.listarTodosConsultaEspecial(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteDetalle no pudo ser leida.";
			throw new LoteDetalleException(msg,e);
		}
	}
	
	
	
	public void borrar (final Long idEjercicio, final Long idEmpresa, final Long idAsiento, final Long renglon) throws LoteDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDetalleDao.borrar(idEjercicio, idEmpresa, idAsiento, renglon);
			}
		});
	}

	public void actualizar(final LoteDetalle pObject) throws LoteDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDetalleDao.actualizar(pObject);
			}
		});
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public LoteDetalleDao getLoteDetalleDao() {
		return loteDetalleDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setLoteDetalleDao(LoteDetalleDao loteDetalleDao) {
		this.loteDetalleDao = loteDetalleDao;
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
	public Long getLastIdDeRenglon(final Long idEjercicio, final Long idEmpresa, final Long idAsiento) {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long num = loteDetalleDao.getLastIdDeRenglon(idEjercicio, idEmpresa, idAsiento);
			return num;
			}
		});
	}
	
	
	public long getSumaDelTotal(final Long idEjercicio, final Long idEmpresa, final Long idAsiento, final String comparacion) {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return ((Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long suma = Long.valueOf(loteDetalleDao.getBalance(idEjercicio, idEmpresa, idAsiento, comparacion));
			return suma;
			}
		})).longValue();
	}
	

	public long getSumaDelTotal(final Long idEjercicio, final Long idEmpresa, final Long idAsiento) {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return ((Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long suma = Long.valueOf(loteDetalleDao.getBalance(idEjercicio, idEmpresa, idAsiento));
			return suma;
			}
		})).longValue();
	}
	
	public void borrarTodosLosDetallesDelLote(final Long idEjercicio, final Long idEmpresa, final Long idAsiento) {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				loteDetalleDao.borrarLosDetalles(idEjercicio, idEmpresa, idAsiento);
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


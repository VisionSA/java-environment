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
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoDetalleService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class AsientoDetalleServiceImpl implements AsientoDetalleService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AsientoDetalleDao asientoDetalleDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private PlatformTransactionManager transactionManagerSpring;
	private TransactionTemplate transactionTemplate;
    private TransactionTemplate transactionTemplateSpring;

	public void grabarAsientoDetalle (final AsientoDetalle pObject) throws AsientoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDetalleDao.grabarAsientoDetalle(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "el AsientoDetalle ya existe en la base de datos.";
			throw new AsientoDetalleDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  AsientoDetalle no pudo ser grabado.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
    public void grabar(final AsientoDetalle pObject) throws AsientoDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				asientoDetalleDao.grabar(pObject);
			}
		});
	}

	public void actualizar(final AsientoDetalle pObject) throws AsientoDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				asientoDetalleDao.actualizar(pObject);
			}
		});
	}
	
	public List getAsientoDetalle(final Filtro filtro) throws AsientoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoDetalleDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientoDetalle no pudo ser leida.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	public List getAsientoDetalleConsultaManual(final Filtro filtro) throws AsientoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoDetalleDao.listarTodosConsultaEspecial(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientoDetalle no pudo ser leida.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	public AsientoDetalle leerAsientoDetalle (final Long id) throws AsientoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (AsientoDetalle) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				AsientoDetalle asientoDetalle = asientoDetalleDao.buscarAsientoDetalle(id);
				return asientoDetalle;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El AsientoDetalle no existe en la base de datos.";
			throw new AsientoDetalleNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El AsientoDetalle no pudo leerse desde la base de datos.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	public void borrarAsientoDetalle (final Long id) throws AsientoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDetalleDao.borrarAsientoDetalle(id);

				}
			});
		} catch (Exception e) {
			String msg = "El AsientoDetalle no pudo borrase.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	public void borrarAsientoDetalle(final AsientoDetalle pObject) throws AsientoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDetalleDao.borrarAsientoDetalle(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	public void actualizarAsientoDetalle (final AsientoDetalle pObject) throws AsientoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDetalleDao.actualizarAsientoDetalle(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El AsientoDetalle no pudo actualizarse.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AsientoDetalleDao getAsientoDetalleDao() {
		return asientoDetalleDao;
	}
	
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setAsientoDetalleDao(AsientoDetalleDao asientoDetalleDao) {
		this.asientoDetalleDao = asientoDetalleDao;
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
			Long id = asientoDetalleDao.getLastIdDeRenglon(idEjercicio, idEmpresa, idAsiento);
			return id;
			}
		});
	}
	
	public void borrarTodosLosDetallesDelAsiento(final Long idEjercicio, final Long idEmpresa, final Long idAsiento) {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				asientoDetalleDao.borrarLosDetalles(idEjercicio, idEmpresa, idAsiento);
			}
		});
	}
	
	public void borrar(final Long idEjercicio, final Long idEmpresa, final Long idAsiento, final Long renglon) throws AsientoDetalleException {
		transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				asientoDetalleDao.borrar(idEjercicio, idEmpresa, idAsiento, renglon);
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


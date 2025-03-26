package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
import java.util.Date;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoProveedorDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoProveedorException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoProveedorService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class AsientoProveedorServiceImpl implements AsientoProveedorService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AsientoProveedorDao asientoProveedorDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;

	
	public void actualizarAsientoProveedor (final AsientoProveedor pObject) throws AsientoProveedorException {
		try {
			transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoProveedorDao.actualizarAsientoProveedor(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Asiento no pudo actualizarse.";
			throw new AsientoProveedorException(msg,e);
		}
	}
	
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AsientoProveedorDao getAsientoProveedorDao() {
		return asientoProveedorDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setAsientoProveedorDao(AsientoProveedorDao asientoProveedorDao) {
		this.asientoProveedorDao = asientoProveedorDao;
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto transactionManager.
	*/
	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}
	/**
	* Necesario para spring
	* @param transactionManager, Objeto a setear.
	*/
	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}

	
	public List getAsientoProveedorImportables(final Date inicioEjercicio, final Date finEjercicio) throws AsientoProveedorException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoProveedorDao.listarTodosImportables(inicioEjercicio, finEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosProveedores para importar no pudo ser leida.";
			throw new AsientoProveedorException(msg,e);
		}
	}


	public List getDetallesAsientoProveedorImportado(final Long id) throws AsientoProveedorException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoProveedorDao.listarDetallesDelImportable(id);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosProveedoresDetallesS para importar no pudo ser leida.";
			throw new AsientoProveedorException(msg,e);
		}
	}
	
	
	
}


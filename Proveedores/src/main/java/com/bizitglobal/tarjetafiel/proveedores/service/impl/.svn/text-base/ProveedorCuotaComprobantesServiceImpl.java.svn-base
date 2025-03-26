package com.bizitglobal.tarjetafiel.proveedores.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCuotaComprobantesDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorCuotaComprobantesException;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCuotaComprobantesService;


/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class ProveedorCuotaComprobantesServiceImpl implements ProveedorCuotaComprobantesService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ProveedorCuotaComprobantesDao proveedorCuotaComprobantesDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public List getProveedorCuotaComprobantesManual(final String fecha) throws ProveedorCuotaComprobantesException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return proveedorCuotaComprobantesDao.listarCuotaComprobante(fecha);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de balances  no pudo ser leida.";
			throw new ProveedorCuotaComprobantesException(msg,e);
		}
	}
	
	
	
	

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}





	public ProveedorCuotaComprobantesDao getProveedorCuotaComprobantesDao() {
		return proveedorCuotaComprobantesDao;
	}


    public void setProveedorCuotaComprobantesDao(
			ProveedorCuotaComprobantesDao proveedorCuotaComprobantesDao) {
		this.proveedorCuotaComprobantesDao = proveedorCuotaComprobantesDao;
	}



	
}


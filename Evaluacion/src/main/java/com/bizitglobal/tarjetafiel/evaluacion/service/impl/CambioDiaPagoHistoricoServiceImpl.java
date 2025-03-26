package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.CambioDiaPagoHistoricoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CambioDiaPagoHistoricoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.CambioDiaPagoHistorico;
import com.bizitglobal.tarjetafiel.evaluacion.service.CambioDiaPagoHistoricoService;

/**
*	Implementacion de la interfaz CambioDiaPagoHistoricoService.
*/
public class CambioDiaPagoHistoricoServiceImpl implements CambioDiaPagoHistoricoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private CambioDiaPagoHistoricoDao cambioDiaPagoHistoricoDao;

	public CambioDiaPagoHistoricoDao getCambioDiaPagoHistoricoDao() {
		return cambioDiaPagoHistoricoDao;
	}

	public void setCambioDiaPagoHistoricoDao(
			CambioDiaPagoHistoricoDao cambioDiaPagoHistoricoDao) {
		this.cambioDiaPagoHistoricoDao = cambioDiaPagoHistoricoDao;
	}


	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	
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

	@Override
	public void borrarCambioDiaPagoHistorico(final Long id)throws CambioDiaPagoHistoricoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cambioDiaPagoHistoricoDao.borrarCambioDiaPagoHistorico(id);
				
			}
		});
	}

	@Override
	public void borrarCambioDiaPagoHistorico(final CambioDiaPagoHistorico object)throws CambioDiaPagoHistoricoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cambioDiaPagoHistoricoDao.borrarCambioDiaPagoHistorico(object);
				
			}
		});
		
	}

	@Override
	public void grabarCambioDiaPagoHistorico(final CambioDiaPagoHistorico object)throws CambioDiaPagoHistoricoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cambioDiaPagoHistoricoDao.grabarCambioDiaPagoHistorico(object);

			}
		});
		
	}

	public List getCambioDiaPagoHistorico(Filtro filtro) throws CambioDiaPagoHistoricoException {
		try {
			return cambioDiaPagoHistoricoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evadiaspago no pudo ser leida.";
			throw new CambioDiaPagoHistoricoException(msg,e);
		}
	}
	
	@Override
	public CambioDiaPagoHistorico leerCambioDiaPagoHistorico(Long id)throws CambioDiaPagoHistoricoException {
		CambioDiaPagoHistorico result = null;
		try {
			result = cambioDiaPagoHistoricoDao.buscarCambioDiaPagoHistorico(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La CambioDiaPagoHistorico no existe en la base de datos.";
			throw new CambioDiaPagoHistoricoException(msg,ex);
		} catch (Exception e) {
			String msg = "La CambioDiaPagoHistorico no pudo leerse desde la base de datos.";
			throw new CambioDiaPagoHistoricoException(msg,e);
		}
		return result;
	}
	
	public Long buscarIdDiaPagoUltimaCambio(final Long idCliente) throws CambioDiaPagoHistoricoException {
 		try {
 			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
 			return (Long) transactionTemplate.execute(new TransactionCallback() {
 				public Object doInTransaction(TransactionStatus status) {
 					Long idDiaPago = null;
 					try {
						idDiaPago = cambioDiaPagoHistoricoDao.buscarIdDiaPagoUltimaCambio(idCliente);
					} catch (Exception e) {
						String msg = "El dia de pago no existe en la base de datos.";
					}
 				return idDiaPago;
 				}
 			});
 		} catch (HibernateObjectRetrievalFailureException ex) {
 			String msg = "El dia de pago  no existe en la base de datos.";
 			throw new CambioDiaPagoHistoricoException(msg,ex);
 		} catch (Exception e) {
 			String msg = "El dia de pago no pudo leerse desde la base de datos.";
 			throw new CambioDiaPagoHistoricoException(msg,e);
 		}
 	}
}


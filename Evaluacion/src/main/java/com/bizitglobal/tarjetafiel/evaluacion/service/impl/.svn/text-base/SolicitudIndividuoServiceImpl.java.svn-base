package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudIndividuoService;

/**
*	Implementacion de la interfaz SolicitudIndividuoService.
*/
public class SolicitudIndividuoServiceImpl implements SolicitudIndividuoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private SolicitudIndividuoDao solicitudIndividuoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarSolicitudIndividuo(final SolicitudIndividuo pObject) throws SolicitudIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudIndividuoDao.grabarEvaSolicIndividuos(pObject);

			}
		});
	}
	
	public List getSolicitudIndividuo(Filtro filtro) throws SolicitudIndividuoException {
		try {
			return solicitudIndividuoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evasolicindividuos no pudo ser leida.";
			throw new SolicitudIndividuoException(msg,e);
		}
	}
	
	public SolicitudIndividuo leerSolicitudIndividuo(Long id) throws SolicitudIndividuoException {
		SolicitudIndividuo result = null;
		try {
			result = solicitudIndividuoDao.buscarEvaSolicIndividuos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La SolicitudIndividuo no existe en la base de datos.";
			throw new SolicitudIndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evasolicindividuos no pudo leerse desde la base de datos.";
			throw new SolicitudIndividuoException(msg,e);
		}
		return result;
	} 
	
	public void borrarSolicitudIndividuo(final Long id) throws SolicitudIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudIndividuoDao.borrarEvaSolicIndividuos(id);

			}
		});
	}
	
	public void borrarSolicitudIndividuo(final SolicitudIndividuo pObject) throws SolicitudIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudIndividuoDao.borrarEvaSolicIndividuos(pObject);

			}
		});
	}
	
	public void actualizarSolicitudIndividuo(final SolicitudIndividuo pObject) throws SolicitudIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudIndividuoDao.actualizarEvaSolicIndividuos(pObject);

			}
		});
	}
	
	public SolicitudIndividuoDao getSolicitudIndividuoDao() {
		return solicitudIndividuoDao;
	}

	public void setSolicitudIndividuoDao(SolicitudIndividuoDao solicitudIndividuoDao) {
		this.solicitudIndividuoDao = solicitudIndividuoDao;
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

	
	public boolean isCargarAdicionales(Solicitud solicitud)throws SolicitudIndividuoException {
		try {
			return solicitudIndividuoDao.isCargarAdicionales(solicitud);
		} catch (Exception e) {
			String msg = "No se pudo comprobar si existen adicionales.";
			throw new SolicitudIndividuoException(msg,e);
		}
		
	}

	
	public boolean isCargarGarantes(Solicitud solicitud)throws SolicitudIndividuoException {
		try {
			return solicitudIndividuoDao.isCargarGarantes(solicitud);
		} catch (Exception e) {
			String msg = "No se pudo comprobar si existen garantes.";
			throw new SolicitudIndividuoException(msg,e);
		}
	}
}


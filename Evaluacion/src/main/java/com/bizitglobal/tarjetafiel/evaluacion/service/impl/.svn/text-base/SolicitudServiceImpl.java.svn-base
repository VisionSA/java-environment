package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.NroVerificador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
*	Implementacion de la interfaz SolicitudService.
*/
public class SolicitudServiceImpl implements SolicitudService {
	private static Logger log = Logger.getLogger(SolicitudServiceImpl.class);
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private SolicitudDao solicitudDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarSolicitud(final Solicitud pObject) throws SolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudDao.grabarSolicitudes(pObject);

			}
		});
	}
	
	public List getSolicitud(Filtro filtro) throws SolicitudException {
		try {
			return solicitudDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de solicitudes no pudo ser leida.";
			throw new SolicitudException(msg,e);
		}
	}
	
	public Solicitud leerSolicitud(Long id) throws SolicitudException {
		Solicitud result = null;
		try {
			result = solicitudDao.buscarSolicitudes(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Solicitud no existe en la base de datos.";
			throw new SolicitudNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evasolicitudes no pudo leerse desde la base de datos.";
			throw new SolicitudException(msg,e);
		}
		return result;
	}
	
	public List generarSolicitudes(Operador operador, Long cantidad) throws SolicitudException{
		log.info("Ejecutando ==> generarSolicitudes()");
		List lista = new ArrayList();
		Long maxNro = null;
		
		try {
			maxNro = solicitudDao.maxNroSolicitud();
			log.info("maxNro: " + maxNro);
			maxNro = new Long(maxNro.intValue()+1);
//			if(maxNro != null)
//				maxNro = new Long(maxNro.intValue()+1);
//			else
//				maxNro = new Long(0);
			
		}catch(Exception e){
			String msg = "No pudo obtenerse el numero maximo de solicitud cargado.";
			throw new SolicitudException(msg, e);
		}
		
		String ceros = "0000000";
		for (int i=0; i<cantidad.intValue(); i++){
			String num = maxNro.toString();
			num = ceros.substring(0, 7-num.length()).concat(num);
			
			Solicitud sol = new Solicitud(null,null,"N",null,null,null, operador.getCodigo(),
										  null,null, new HashSet(), num, 
										  NroVerificador.generarDV(maxNro.toString()),1L);
			lista.add(sol);
			maxNro = new Long(maxNro.intValue()+1);
		}
		
		return lista;
	}
	
	public Solicitud busNroComprobante(Filtro filtro) throws SolicitudException {
		Solicitud result = null;
		
		try{
			result = solicitudDao.busNroSolicitud(filtro);
		}
		catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Solicitud no existe en la base de datos.";
			throw new SolicitudNotFoundException(msg,ex);
		}
		catch (Exception e) {
			String msg = "La Solicitudes no pudo leerse desde la base de datos.";
			throw new SolicitudException(msg,e);
		}
		return result;
	}
	
	public void borrarSolicitud(final Long id) throws SolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudDao.borrarSolicitudes(id);

			}
		});
	}
	
	public void borrarSolicitud(final Solicitud pObject) throws SolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudDao.borrarSolicitudes(pObject);

			}
		});
	}
	
	public void actualizarSolicitud(final Solicitud pObject) throws SolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				solicitudDao.actualizarSolicitudes(pObject);

			}
		});
	}

	public SolicitudDao getSolicitudDao() {
		return solicitudDao;
	}

	public void setSolicitudDao(SolicitudDao solicitudDao) {
		this.solicitudDao = solicitudDao;
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


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.TarjetaDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TarjetaDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TarjetaException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TarjetaNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.evaluacion.service.TarjetaService;

/**
*	Implementacion de la interfaz TarjetaService.
*/
public class TarjetaServiceImpl implements TarjetaService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TarjetaDao tarjetaDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTarjeta(final Tarjeta pObject) throws TarjetaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tarjetaDao.grabarEvaTarjetas(pObject);

			}
		});
	}
	
	public List getTarjeta(Filtro filtro) throws TarjetaException {
		try {
			return tarjetaDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evatarjetas no pudo ser leida.";
			throw new TarjetaException(msg,e);
		}
	}
	
	public Tarjeta leerTarjeta(Long id) throws TarjetaException {
		Tarjeta result = null;
		try {
			result = tarjetaDao.buscarEvaTarjetas(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Tarjeta no existe en la base de datos.";
			throw new TarjetaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evatarjetas no pudo leerse desde la base de datos.";
			throw new TarjetaException(msg,e);
		}
		return result;
	}
	
	public void borrarTarjeta(final Long id) throws TarjetaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tarjetaDao.borrarEvaTarjetas(id);

			}
		});
	}
	
	public void borrarTarjeta(final Tarjeta pObject) throws TarjetaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tarjetaDao.borrarEvaTarjetas(pObject);

			}
		});
	}
	
	public void actualizarTarjeta(final Tarjeta pObject) throws TarjetaException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tarjetaDao.actualizarEvaTarjetas(pObject);

			}
		});
	}
	
	public TarjetaDao getTarjetaDao() {
		return tarjetaDao;
	}

	public void setTarjetaDao(TarjetaDao tarjetaDao) {
		this.tarjetaDao = tarjetaDao;
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


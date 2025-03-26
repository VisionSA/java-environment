package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.LibroMayorExeption;
import com.bizitglobal.tarjetafiel.fondos.dao.LibroMayorFondosDao;
import com.bizitglobal.tarjetafiel.fondos.service.LibroMayorFondosService;

public class LibroMayorFondosServiceImpl implements LibroMayorFondosService{
	private LibroMayorFondosDao libroMayorFondosDao;
    private TransactionTemplate transactionTemplate;
    private PlatformTransactionManager transactionManager;
   
    
    public List getCuenta(final Filtro filtro) throws LibroMayorExeption {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = libroMayorFondosDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Ejercicio no pudo ser leida.";
			throw new LibroMayorExeption(msg,e);
		}
	}


	public List getRenglonesLibroMayorFondos(final Date inicio, final Date cierre,final Long idCuenta,final Date inicioEjercicio)  throws LibroMayorExeption{
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = libroMayorFondosDao.listarTodosFondos(inicio, cierre, idCuenta, inicioEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosFondosItem no pudo ser leida.";
			throw new LibroMayorExeption(msg,e);
		}
	}
	
	
   public	BigDecimal getSaldoAC(final Date fin, final Long idCuenta){
	   transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (BigDecimal) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
			 try {
				return libroMayorFondosDao.getSaldoAC(fin, idCuenta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			}
		});
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


	public LibroMayorFondosDao getLibroMayorFondosDao() {
		return libroMayorFondosDao;
	}


	public void setLibroMayorFondosDao(LibroMayorFondosDao libroMayorFondosDao) {
		this.libroMayorFondosDao = libroMayorFondosDao;
	}
	
	
}

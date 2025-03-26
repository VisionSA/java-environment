package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoConciliableDao;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoConciliableException;
import com.bizitglobal.tarjetafiel.fondos.service.MovimientoConciliableService;


public class MovimientoConciliableServiceImpl implements MovimientoConciliableService {
	
	private MovimientoConciliableDao movimientoConciliableDao;
	
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	 
    @Override
	public List listarNoConciliados(Long idPlanCuenta, Date fechaDesde,Date fechaHasta) throws MovimientoConciliableException {
		List result = new ArrayList();
    	try{
			result =  movimientoConciliableDao.listarNoConciliados(idPlanCuenta, fechaDesde, fechaHasta);
		} catch (Exception e) {
			String msg = "La lista de MovimientoConciliable no pudo ser leida.";
			throw new MovimientoConciliableException(msg,e);
		}
		return result;
	}
    @Override
    public double saldoMovContabilidadNoConciliadosHastaFecha(Long idPlanCuenta, Date fechaHasta) throws MovimientoConciliableException {
    	try{
			return  movimientoConciliableDao.saldoMovContabilidadNoConciliadosHastaFecha(idPlanCuenta, fechaHasta, 'N');
		} catch (Exception e) {
			String msg = "No se pudo leer el saldo de los movimientos.";
			throw new MovimientoConciliableException(msg,e);
		}
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

	public MovimientoConciliableDao getMovimientoConciliableDao() {
		return movimientoConciliableDao;
	}

	public void setMovimientoConciliableDao(
			MovimientoConciliableDao movimientoConciliableDao) {
		this.movimientoConciliableDao = movimientoConciliableDao;
	}

	

	
}

package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.dao.AcreditacionFondoDetalleDao;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoDetalleException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondoDetalle;
import com.bizitglobal.tarjetafiel.fondos.service.AcreditacionFondoDetalleService;

public class AcreditacionFondoDetalleServiceImpl implements AcreditacionFondoDetalleService{

	private AcreditacionFondoDetalleDao acreditacionFondoDetalleDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    
	@Override
	public void actualizarAcreditacionFondoDetalle(final AcreditacionFondoDetalle acreditacionFondoDetalle)throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					acreditacionFondoDetalleDao.actualizarAcreditacionFondoDetalle(acreditacionFondoDetalle);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo actualizarse.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
		
	}

	@Override
	public void borrarAcreditacionFondoDetalle(final Long id)throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					acreditacionFondoDetalleDao.borrarAcreditacionFondoDetalle(id);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo borrase.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
		
	}

	@Override
	public void borrarAcreditacionFondoDetalle(final AcreditacionFondoDetalle acreditacionFondoDetalle)throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					acreditacionFondoDetalleDao.borrarAcreditacionFondoDetalle(acreditacionFondoDetalle);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo borrase.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
		
	}

	@Override
	public List getAcreditacionesDetalle() throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return acreditacionFondoDetalleDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AcreditacionFondo no pudo ser leida.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}

	@Override
	public List getAcreditacionesDetalle(final Filtro filtro)throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return acreditacionFondoDetalleDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de acreditacion no pudo ser leida.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}

	@Override
	public void grabarAcreditacionFondoDetalle(final AcreditacionFondoDetalle acreditacionFondoDetalle)throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					acreditacionFondoDetalleDao.grabarAcreditacionFondoDetalle(acreditacionFondoDetalle);
				}
			});
		
		} catch (Exception e) {
			String msg = "La AcreditacionFondo no pudo ser grabada.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
		
	}

	@Override
	public AcreditacionFondoDetalle leerAcreditacionFondoDetalle(Long id)throws AcreditacionFondoDetalleException {
		try {
			return acreditacionFondoDetalleDao.buscarAcreditacionFondoDetalle(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La acreditacion no existe en la base de datos.";
			throw new AcreditacionFondoDetalleException(msg,ex);
		} catch (Exception e) {
			String msg = "La acreditacion no pudo leerse desde la base de datos.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}

	public AcreditacionFondoDetalleDao getAcreditacionFondoDetalleDao() {
		return acreditacionFondoDetalleDao;
	}

	public void setAcreditacionFondoDetalleDao(AcreditacionFondoDetalleDao acreditacionFondoDetalleDao) {
		this.acreditacionFondoDetalleDao = acreditacionFondoDetalleDao;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	@Override
	public Page getPage(Filtro filtro, int pageNumber, int pageSize)throws Exception {
		Page page = getMovimientosPage(filtro, pageNumber, pageSize);
//		Iterator iter = page.getThisPageElements().iterator();
//		while (iter.hasNext())
//		{
//			Movimiento element = (Movimiento)iter.next();
//			if(element.getCaja()!=null)
//				element.getCaja().getDescripcion();
//			if(element.getConcepto()!=null)
//				element.getConcepto().getDescripcion();
//			if(element.getOperador()!=null)
//				element.getOperador().getLabel();
//		}
		return page;
	}
	
	public Page getMovimientosPage(final Filtro filtro, final int pageNumber, final int pageSize) throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Page) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return acreditacionFondoDetalleDao.listarTodosPage(filtro, pageNumber, pageSize);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Acreditaciones no pudo ser leida.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}
	
	public Paginador getMovimientosPagina(final Filtro filtro, final Paginador paginador) throws AcreditacionFondoDetalleException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Paginador) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					paginador.setResult(acreditacionFondoDetalleDao.listarTodosPagina(filtro, paginador));
					return paginador;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de acreditaciones no pudo ser leida.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}
	
	public List buscarRangoConDatos(final Long minFecha, final Long maxFecha, final Long idBanco)throws AcreditacionFondoDetalleException 
	{
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return acreditacionFondoDetalleDao.buscarRangoConDatos(minFecha, maxFecha, idBanco);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de acreditaciones para controla solapamiento no pudo ser leida.";
			throw new AcreditacionFondoDetalleException(msg,e);
		}
	}
}

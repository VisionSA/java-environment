package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeHistorialDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoCabeceraDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.DetalleExtractoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoConciliableDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ConciliacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.service.ConciliacionFondoService;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz ConciliacionFondoService.
 */
public class ConciliacionFondoServiceImpl implements ConciliacionFondoService {
	
	
	private ConciliacionFondoDao conciliacionFondoDao;
	private DetalleExtractoDao detalleExtractoDao;
	private MovimientoConciliableDao movimientoConciliableDao;
	private ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao;
	private ChequeHistorialDao chequeHistorialDao;
	private ChequeDao chequeDao;
/*@I3918*/	private OperadorDao operadorDao;	
/*@F3918*/	
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	 
	public void grabarConciliacionFondo(final ConciliacionFondo unaConciliacionFondo) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoDao.grabarConciliacionFondo(unaConciliacionFondo);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ConciliacionFondo ya existe en la base de datos.";
			throw new ConciliacionFondoException(msg,ex);
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo ser grabada.";
			throw new ConciliacionFondoException(msg,e);
		}
		
	}
	
	public List getConciliacionFondos() throws ConciliacionFondoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return conciliacionFondoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondo no pudo ser leida.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public ConciliacionFondo leerConciliacionFondo(Long id) throws ConciliacionFondoException {
		try {
			return conciliacionFondoDao.buscarConciliacionFondo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ConciliacionFondo no existe en la base de datos.";
			throw new ConciliacionFondoException(msg,ex);
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo leerse desde la base de datos.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public void borrarConciliacionFondo(final Long idConciliacionFondo) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoDao.borrarConciliacionFondo(idConciliacionFondo);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo borrase.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public void borrarConciliacionFondo(final ConciliacionFondo unaConciliacionFondo) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoDao.borrarConciliacionFondo(unaConciliacionFondo);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo borrase.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public void actualizarConciliacionFondo(final ConciliacionFondo unaConciliacionFondo) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoDao.actualizarConciliacionFondo(unaConciliacionFondo);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo actualizarse.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public List getConciliacionFondos(final Filtro filtro) throws ConciliacionFondoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return conciliacionFondoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondo no pudo ser leida.";
			throw new ConciliacionFondoException(msg,e);
		}
	}

	public List getConciliacionFondosFlex(final Filtro filtro) throws ConciliacionFondoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List concList = conciliacionFondoDao.listarTodos(filtro);
					Iterator iter = concList.iterator();
					while (iter.hasNext()) {
						ConciliacionFondo concFondo = (ConciliacionFondo) iter.next();
						if(concFondo.getTipo().equals('E')){
							concFondo.setContenedor(detalleExtractoDao.buscarDetalleExtractoFlex(concFondo.getIdRegistro()));
						}else{
							concFondo.setContenedor(movimientoConciliableDao.buscarMovimientoConciliable(concFondo.getIdRegistro()));
						}
					}
					return concList;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondo no pudo ser leida.";
			throw new ConciliacionFondoException(msg,e);
		}
	}
	
	public void revertirConciliacionFondo(final List<ConciliacionFondoCabecera>  listCabecera, final Date fechaReversion, final Operador operadorReversion) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
/*@I4304*/					//si fechaConfirmacion llega null seteo la fecha actual del server
					Date fechaReversionTemp = (fechaReversion==null)? new Date() : fechaReversion;
/*@F4304*/
					Iterator<ConciliacionFondoCabecera> iterCabecera = listCabecera.iterator();
					while(iterCabecera.hasNext()){
						ConciliacionFondoCabecera cabecera = iterCabecera.next();
						if(cabecera!=null){
/*@I3918*/							ConciliacionFondoCabecera cabeceraAux = conciliacionFondoCabeceraDao.buscarConciliacionFondoCabecera(cabecera.getIdCabeceraConciliacion());
							cabeceraAux.setConciliacionFondosFlex(cabecera.getConciliacionFondosFlex());
/*@I4304*/							cabeceraAux.setFechaReversion(fechaReversionTemp);
/*@F4304*/							Operador operador = operadorDao.buscarOperador(operadorReversion.getCodigo());
							cabeceraAux.setOperadorReversion(operador);
							cabeceraAux.setConciliado(ConciliacionFondoCabecera.CONCILIADO_REVERTIDO);
							Iterator iter = cabeceraAux.getConciliacionFondosFlex().iterator();
/*@F3918*/							while(iter.hasNext())
							{
								ConciliacionFondo conciliacion = (ConciliacionFondo) iter.next();
								
								if(conciliacion.getTipo().equals('E')){
									DetalleExtracto detalle =  detalleExtractoDao.buscarDetalleExtracto(conciliacion.getIdRegistro());
									detalle.setConciliado('N');
									detalleExtractoDao.actualizarDetalleExtracto(detalle);
								}else{
									ChequeHistorial chequeHistorial = chequeHistorialDao.buscarChequeHistorial(conciliacion.getIdRegistro());
									chequeHistorial.setConciliado('N');
									chequeHistorialDao.actualizarChequeHistorial(chequeHistorial);
									
									Cheque cheque = chequeHistorial.getCheque();
									cheque.setConciliado('N');
									chequeDao.actualizarCheque(cheque);
								}
							}
/*@I3918*/							conciliacionFondoCabeceraDao.actualizarConciliacionFondoCabecera(cabeceraAux);
/*@F3918*/						}
					}
					
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo actualizarse.";
			throw new ConciliacionFondoException(msg,e);
		}
	}

	public void revertirConciliacionFondoTodos(final Filtro filtro, final Date fechaReversion, final Operador operadorReversion) throws ConciliacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
/*@I4304*/					//si fechaConfirmacion llega null seteo la fecha actual del server
					Date fechaReversionTemp = (fechaReversion==null)? new Date() : fechaReversion;
/*@F4304*/
					List listCabecera = conciliacionFondoCabeceraDao.listarTodos(filtro);
					Iterator<ConciliacionFondoCabecera> iterCabecera = listCabecera.iterator();
					while(iterCabecera.hasNext()){
						ConciliacionFondoCabecera cabecera = iterCabecera.next();
						if(cabecera!=null){
/*@I3918*/							ConciliacionFondoCabecera cabeceraAux = conciliacionFondoCabeceraDao.buscarConciliacionFondoCabecera(cabecera.getIdCabeceraConciliacion());
/*@I4304*/							cabeceraAux.setFechaReversion(fechaReversionTemp);
							cabeceraAux.setFechaReversion(fechaReversionTemp);
/*@F4304*/							cabeceraAux.setOperadorReversion(operadorReversion);
							cabeceraAux.setConciliado(ConciliacionFondoCabecera.CONCILIADO_REVERTIDO);
/*@F3918*/							Iterator iter = cabecera.getConciliacionFondos().iterator();
							while(iter.hasNext())
							{
								ConciliacionFondo conciliacion = (ConciliacionFondo) iter.next();
								
								if(conciliacion.getTipo().equals('E')){
									DetalleExtracto detalle =  detalleExtractoDao.buscarDetalleExtracto(conciliacion.getIdRegistro());
									detalle.setConciliado('N');
									detalleExtractoDao.actualizarDetalleExtracto(detalle);
								}else{
									ChequeHistorial chequeHistorial = chequeHistorialDao.buscarChequeHistorial(conciliacion.getIdRegistro());
									chequeHistorial.setConciliado('N');
									chequeHistorialDao.actualizarChequeHistorial(chequeHistorial);
									
									Cheque cheque = chequeHistorial.getCheque();
									cheque.setConciliado('N');
									chequeDao.actualizarCheque(cheque);
								}
							}
/*@I3918*/							conciliacionFondoCabeceraDao.actualizarConciliacionFondoCabecera(cabeceraAux);
/*@F3918*/						}
					}
					
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondo no pudo actualizarse.";
			throw new ConciliacionFondoException(msg,e);
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

	public ConciliacionFondoDao getConciliacionFondoDao() {
		return conciliacionFondoDao;
	}

	public void setConciliacionFondoDao(ConciliacionFondoDao conciliacionFondoDao) {
		this.conciliacionFondoDao = conciliacionFondoDao;
	}

	public DetalleExtractoDao getDetalleExtractoDao() {
		return detalleExtractoDao;
	}

	public void setDetalleExtractoDao(DetalleExtractoDao detalleExtractoDao) {
		this.detalleExtractoDao = detalleExtractoDao;
	}

	public MovimientoConciliableDao getMovimientoConciliableDao() {
		return movimientoConciliableDao;
	}

	public void setMovimientoConciliableDao(MovimientoConciliableDao movimientoConciliableDao) {
		this.movimientoConciliableDao = movimientoConciliableDao;
	}

	public ConciliacionFondoCabeceraDao getConciliacionFondoCabeceraDao() {
		return conciliacionFondoCabeceraDao;
	}

	public void setConciliacionFondoCabeceraDao(
			ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao) {
		this.conciliacionFondoCabeceraDao = conciliacionFondoCabeceraDao;
	}

	public ChequeHistorialDao getChequeHistorialDao() {
		return chequeHistorialDao;
	}

	public void setChequeHistorialDao(ChequeHistorialDao chequeHistorialDao) {
		this.chequeHistorialDao = chequeHistorialDao;
	}

	public ChequeDao getChequeDao() {
		return chequeDao;
	}

	public void setChequeDao(ChequeDao chequeDao) {
		this.chequeDao = chequeDao;
	}
/*@I3918*/
	public OperadorDao getOperadorDao() {
		return operadorDao;
	}

	public void setOperadorDao(OperadorDao operadorDao) {
		this.operadorDao = operadorDao;
	}
/*@F3918*/	
}

package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.bizitglobal.tarjetafiel.fondos.exception.ConciliacionFondoCabeceraException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoConciliable;
import com.bizitglobal.tarjetafiel.fondos.service.ConciliacionFondoCabeceraService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz ConciliacionFondoService.
 */
public class ConciliacionFondoCabeceraServiceImpl implements ConciliacionFondoCabeceraService {
	
	
	private ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao;
	private DetalleExtractoDao detalleExtractoDao;
	private MovimientoConciliableDao movimientoConciliableDao;
	private ChequeHistorialDao chequeHistorialDao;
	private ConciliacionFondoDao conciliacionFondoDao;
	private ChequeDao chequeDao;
	
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
    public void grabarConciliacionFondoCabecera(final ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					conciliacionFondoCabeceraDao.grabarConciliacionFondoCabecera(unaConciliacionFondoCabecera);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ConciliacionFondoCabecera ya existe en la base de datos.";
			throw new ConciliacionFondoCabeceraException(msg,ex);
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo ser grabada.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
		
	}
    
	public void grabarConciliacionFondoCabeceraFlex(final ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
/*@I4304*/					unaConciliacionFondoCabecera.setFechaGeneracion(new Date());//tomo la fecha de generacion desde el server
/*@F4304*/					Iterator iter = unaConciliacionFondoCabecera.getConciliacionFondosFlex().iterator();
					Set conciliacionFondosFlex = new HashSet();
					while(iter.hasNext())
					{
						Object obj =  iter.next();
						if(obj instanceof DetalleExtracto)
						{
							DetalleExtracto detalle = (DetalleExtracto)obj;
							int signo = detalle.getSigno()=='D' ? -1 : 1;
							detalle = detalleExtractoDao.buscarDetalleExtracto(detalle.getIdDetalleExtracto());
							detalle.setConciliado('S');
							detalleExtractoDao.actualizarDetalleExtracto(detalle);
							
							ConciliacionFondo conciliacionExtracto = new ConciliacionFondo(ConciliacionFondo.TIPO_ID_EXTRACTO,
									detalle.getIdDetalleExtracto(),detalle.getImporte(),signo);
							conciliacionExtracto.setConciliacionFondoCabecera(unaConciliacionFondoCabecera);
							
							conciliacionFondosFlex.add(conciliacionExtracto);
						}else if(obj instanceof MovimientoConciliable)
						{
							MovimientoConciliable movimiento = (MovimientoConciliable)obj;
							ChequeHistorial cheque = chequeHistorialDao.buscarChequeHistorial(movimiento.getIdChequeHistorial());
							cheque.setConciliado('S');
							chequeHistorialDao.actualizarChequeHistorial(cheque);
							
							int signo = movimiento.getSigno()=='D' ? -1 : 1;
							ConciliacionFondo conciliacionFondo = new ConciliacionFondo(ConciliacionFondo.TIPO_ID_FONDO,
									movimiento.getIdChequeHistorial(),movimiento.getImporte(),signo);
							conciliacionFondo.setConciliacionFondoCabecera(unaConciliacionFondoCabecera);
							
							conciliacionFondosFlex.add(conciliacionFondo);
						}
						
						
					}
					unaConciliacionFondoCabecera.setConciliacionFondos(conciliacionFondosFlex);
					
					conciliacionFondoCabeceraDao.grabarConciliacionFondoCabecera(unaConciliacionFondoCabecera);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ConciliacionFondoCabecera ya existe en la base de datos.";
			throw new ConciliacionFondoCabeceraException(msg,ex);
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo ser grabada.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
		
	}
	
	public List getConciliacionFondoCabeceras() throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return conciliacionFondoCabeceraDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public ConciliacionFondoCabecera leerConciliacionFondoCabecera(Long id) throws ConciliacionFondoCabeceraException {
		try {
			return conciliacionFondoCabeceraDao.buscarConciliacionFondoCabecera(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ConciliacionFondoCabecera no existe en la base de datos.";
			throw new ConciliacionFondoCabeceraException(msg,ex);
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo leerse desde la base de datos.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public void borrarConciliacionFondoCabecera(final Long idConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoCabeceraDao.borrarConciliacionFondoCabecera(idConciliacionFondoCabecera);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo borrase.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public void borrarConciliacionFondoCabecera(final ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoCabeceraDao.borrarConciliacionFondoCabecera(unaConciliacionFondoCabecera);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo borrase.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public void actualizarConciliacionFondoCabecera(final ConciliacionFondoCabecera unaConciliacionFondoCabecera) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					conciliacionFondoCabeceraDao.actualizarConciliacionFondoCabecera(unaConciliacionFondoCabecera);
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo actualizarse.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	 
	public void confirmarConciliacionFondoCabecera(final List<ConciliacionFondoCabecera> list, final Date fechaConfirmacion, final Operador operadorConfirmo) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
/*@I4304*/					//si fechaConfirmacion llega null seteo la fecha actual del server
					Date fechaConfirmacionTemp = (fechaConfirmacion==null)? new Date() : fechaConfirmacion;
/*@F4304*/					
					Iterator<ConciliacionFondoCabecera> iter = list.iterator();
					while(iter.hasNext())
					{
						ConciliacionFondoCabecera cabecera = iter.next();
						if(cabecera!=null){
/*@I4304*/							cabecera.setFechaConfirmacion(fechaConfirmacionTemp);
/*@F4304*/							cabecera.setOperadorConfirmo(operadorConfirmo);
							cabecera.setConciliado(ConciliacionFondoCabecera.CONCILIADO_SI);
							Iterator iterHijos = cabecera.getConciliacionFondosFlex().iterator();
							while(iterHijos.hasNext())
							{
								ConciliacionFondo conciliacion = (ConciliacionFondo) iterHijos.next();
								
								if(conciliacion.getTipo().equals('E')){
									DetalleExtracto detalle =  detalleExtractoDao.buscarDetalleExtracto(conciliacion.getIdRegistro());
									detalle.setConciliado('S');
									detalleExtractoDao.actualizarDetalleExtracto(detalle);
								}else{
									ChequeHistorial chequeHistorial = chequeHistorialDao.buscarChequeHistorial(conciliacion.getIdRegistro());
									chequeHistorial.setConciliado('S');
									chequeHistorialDao.actualizarChequeHistorial(chequeHistorial);
									
									Cheque cheque = chequeHistorial.getCheque();
									cheque.setConciliado('S');
									chequeDao.actualizarCheque(cheque);
								}
							}
							conciliacionFondoCabeceraDao.actualizarConciliacionFondoCabecera(cabecera);
						}
					}
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo actualizarse.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public void confirmarTodosConciliacionFondoCabecera(final Date fechaConfirmacion, final Operador operadorConfirmo, final Filtro filtro) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {	
/*@I4304*/					//si fechaConfirmacion llega null seteo la fecha actual del server
					Date fechaConfirmacionTemp = (fechaConfirmacion==null)? new Date() : fechaConfirmacion;
/*@F4304*/
					List listConciliacionCabeceraConfirmar = conciliacionFondoCabeceraDao.listarTodos(filtro);
					Iterator iter = listConciliacionCabeceraConfirmar.iterator();
					while(iter.hasNext())
					{
						ConciliacionFondoCabecera cabecera = (ConciliacionFondoCabecera)iter.next();
						if(cabecera!=null){
/*@I4304*/							cabecera.setFechaConfirmacion(fechaConfirmacionTemp);
/*@F4304*/							cabecera.setOperadorConfirmo(operadorConfirmo);
							cabecera.setConciliado(ConciliacionFondoCabecera.CONCILIADO_SI);
							Iterator iterHijos = cabecera.getConciliacionFondos().iterator();
							while(iterHijos.hasNext())
							{
								ConciliacionFondo conciliacion = (ConciliacionFondo) iterHijos.next();
								
								if(conciliacion.getTipo().equals('E')){
									DetalleExtracto detalle =  detalleExtractoDao.buscarDetalleExtracto(conciliacion.getIdRegistro());
									detalle.setConciliado('S');
									detalleExtractoDao.actualizarDetalleExtracto(detalle);
								}else{
									ChequeHistorial chequeHistorial = chequeHistorialDao.buscarChequeHistorial(conciliacion.getIdRegistro());
									chequeHistorial.setConciliado('S');
									chequeHistorialDao.actualizarChequeHistorial(chequeHistorial);
									
									Cheque cheque = chequeHistorial.getCheque();
									cheque.setConciliado('S');
									chequeDao.actualizarCheque(cheque);
								}
							}
							conciliacionFondoCabeceraDao.actualizarConciliacionFondoCabecera(cabecera);
						}
					}
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera (todos), no pudo actualizarse.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	
	
	public void deshacerConciliacionFondoCabecera(final List<ConciliacionFondoCabecera> list) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {	
					Iterator<ConciliacionFondoCabecera> iter = list.iterator();
					while(iter.hasNext())
					{
						ConciliacionFondoCabecera unaConciliacionFondoCabecera = iter.next();
						Iterator iterConciliacionFondo = unaConciliacionFondoCabecera.getConciliacionFondosFlex().iterator();
						while(iterConciliacionFondo.hasNext())
						{
							ConciliacionFondo obj =  (ConciliacionFondo)iterConciliacionFondo.next();
							if(obj.getTipo().equals(ConciliacionFondo.TIPO_ID_EXTRACTO))
							{
								DetalleExtracto detalle = detalleExtractoDao.buscarDetalleExtracto(obj.getIdRegistro());
								detalle.setConciliado(new Character('N'));
								detalleExtractoDao.actualizarDetalleExtracto(detalle);
							}else if(obj.getTipo().equals(ConciliacionFondo.TIPO_ID_FONDO))
							{
								ChequeHistorial cheque = chequeHistorialDao.buscarChequeHistorial(obj.getIdRegistro()); 
								cheque.setConciliado(new Character('N'));
								chequeHistorialDao.actualizarChequeHistorial(cheque);
							}
							
							//delete conciliacion fondo
							conciliacionFondoDao.borrarConciliacionFondo(obj);
							
						}
						//delete cabecera.
						conciliacionFondoCabeceraDao.borrarConciliacionFondoCabecera(unaConciliacionFondoCabecera);
					}
				}
			});
		} catch (Exception e) {
			String msg = "La ConciliacionFondoCabecera no pudo actualizarse.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	public List getConciliacionFondoCabeceras(final Filtro filtro) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return conciliacionFondoCabeceraDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}

	public List getConciliacionFondoCabecerasFlex(final Filtro filtro) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List concList = conciliacionFondoCabeceraDao.listarTodos(filtro);
					Iterator iter = concList.iterator();
					while (iter.hasNext()) {
						ConciliacionFondoCabecera concFondoCabecera = (ConciliacionFondoCabecera) iter.next();
						BancoPropio bancoPropio = new BancoPropio();
						bancoPropio.setIdBancoPropio(concFondoCabecera.getBancoPropio().getIdBancoPropio());
						concFondoCabecera.setBancoPropio(bancoPropio);
						concFondoCabecera.setOperadorConfirmo(null);
						concFondoCabecera.setOperadorReversion(null);
						concFondoCabecera.setConciliacionFondos(new HashSet());  
//						Iterator iterConciliacion =  concFondoCabecera.getConciliacionFondos().iterator();
//						while(iterConciliacion.hasNext()){
//							ConciliacionFondo concFondo = (ConciliacionFondo)iterConciliacion.next();
//							if(concFondo.getTipo().equals(ConciliacionFondo.TIPO_ID_EXTRACTO)){
//								concFondo.setContenedor(detalleExtractoDao.buscarDetalleExtractoFlex(concFondo.getId()));
//							}else{
//								concFondo.setContenedor(movimientoConciliableDao.buscarMovimientoConciliable(concFondo.getId()));
//							}
//						}
					}
					return concList;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}

/*@I3918*/	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPorFecha(final int tipoFecha, final Date fechaDesde, final Date fechaHasta, final Long idBancoPropio, 
			final String conciliado,final int firstResult, final int maxResults, final String numero, final Double importe) 
/*@F3918*/			throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List<ConciliacionFondoCabecera>) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List<ConciliacionFondoCabecera> concList = conciliacionFondoCabeceraDao.buscarConciliacionCabeceraPorFecha(tipoFecha,fechaDesde,fechaHasta,idBancoPropio,conciliado,firstResult, maxResults, numero, importe);
					Iterator<ConciliacionFondoCabecera> iter = concList.iterator();
					
					while (iter.hasNext()) {
						ConciliacionFondoCabecera concFondoCabecera = iter.next();
						
						Filtro filtro = new Filtro();
						filtro.agregarCampoOperValor("conciliacionFondoCabecera.idCabeceraConciliacion", Filtro.IGUAL, concFondoCabecera.getIdCabeceraConciliacion());
						List detalles =  conciliacionFondoDao.listarTodos(filtro);
						Iterator iterDetalle = detalles.iterator(); 
						while (iterDetalle.hasNext()) {
							ConciliacionFondo concFondo = (ConciliacionFondo) iterDetalle.next();
							if(concFondo.getTipo().equals('E')){
								concFondo.setContenedor(detalleExtractoDao.buscarDetalleExtractoFlex(concFondo.getIdRegistro()));
							}else{
								concFondo.setContenedor(movimientoConciliableDao.buscarMovimientoConciliable(concFondo.getIdRegistro()));
							}
						}
						
						concFondoCabecera.setConciliacionFondos(new HashSet(detalles)); 
						concFondoCabecera.setConciliacionFondosFlex(detalles);
						
					}
					return concList;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	@Override
/*@I3918*/	public Long cantidadRegistrosPorFecha(int tipoFecha, Date fechaDesde,Date fechaHasta, Long idBancoPropio, String conciliado, String numero, Double importe)throws ConciliacionFondoCabeceraException {
		try
		{
			return conciliacionFondoCabeceraDao.cantidadRegistrosPorFecha(tipoFecha, fechaDesde, fechaHasta, idBancoPropio, conciliado, numero, importe);
/*@F3918*/		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPaginado(final Filtro filtro,final int firstResult, final int maxResults) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List<ConciliacionFondoCabecera>) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					List<ConciliacionFondoCabecera> concList = conciliacionFondoCabeceraDao.listarPaginado(filtro, firstResult, maxResults);
					Iterator<ConciliacionFondoCabecera> iter = concList.iterator();
					
					while (iter.hasNext()) {
						ConciliacionFondoCabecera concFondoCabecera = iter.next();
						
						
						Filtro filtro = new Filtro();
						filtro.agregarCampoOperValor("conciliacionFondoCabecera.idCabeceraConciliacion", Filtro.IGUAL, concFondoCabecera.getIdCabeceraConciliacion());
						List detalles =  conciliacionFondoDao.listarTodos(filtro);
						Iterator iterDetalle = detalles.iterator(); 
						while (iterDetalle.hasNext()) {
							ConciliacionFondo concFondo = (ConciliacionFondo) iterDetalle.next();
							if(concFondo.getTipo().equals('E')){
								concFondo.setContenedor(detalleExtractoDao.buscarDetalleExtractoFlex(concFondo.getIdRegistro()));
							}else{
								concFondo.setContenedor(movimientoConciliableDao.buscarMovimientoConciliable(concFondo.getIdRegistro()));
							}
						}
						
						concFondoCabecera.setConciliacionFondos(new HashSet(detalles)); 
						concFondoCabecera.setConciliacionFondosFlex(detalles);
						
					}
					return concList;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
		}
	}
	
	public Long cantidadRegistros(final Filtro filtro) throws ConciliacionFondoCabeceraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return conciliacionFondoCabeceraDao.cantidadRegistros(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ConciliacionFondoCabecera no pudo ser leida.";
			throw new ConciliacionFondoCabeceraException(msg,e);
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

	public ConciliacionFondoCabeceraDao getConciliacionFondoCabeceraDao() {
		return conciliacionFondoCabeceraDao;
	}

	public void setConciliacionFondoCabeceraDao(
			ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao) {
		this.conciliacionFondoCabeceraDao = conciliacionFondoCabeceraDao;
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

	public ChequeHistorialDao getChequeHistorialDao() {
		return chequeHistorialDao;
	}

	public void setChequeHistorialDao(ChequeHistorialDao chequeHistorialDao) {
		this.chequeHistorialDao = chequeHistorialDao;
	}

	public ConciliacionFondoDao getConciliacionFondoDao() {
		return conciliacionFondoDao;
	}

	public void setConciliacionFondoDao(ConciliacionFondoDao conciliacionFondoDao) {
		this.conciliacionFondoDao = conciliacionFondoDao;
	}

	public ChequeDao getChequeDao() {
		return chequeDao;
	}

	public void setChequeDao(ChequeDao chequeDao) {
		this.chequeDao = chequeDao;
	}

	

	
}

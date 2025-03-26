package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.BancoPropioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeHistorialDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoCabeceraDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.DetalleExtractoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioTipoMovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.CajaAperturaDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoAcreditacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.fondos.service.ExtractoBancarioService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ExtractoBancarioServiceImpl implements ExtractoBancarioService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ExtractoBancarioDao extractoBancarioDao;
	private BancoPropioDao bancoPropioDao;
	private DetalleExtractoDao detalleExtractoDao;
	private ChequeHistorialDao chequeHistorialDao;
	private ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao;
	private ExtractoBancarioTipoMovimientoDao extractoBancarioTipoMovimientoDao;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
    private static final Logger log = Logger.getLogger(ExtractoBancarioServiceImpl.class);
    
	public void grabarExtractoBancario(final ExtractoBancario unaExtractoBancario) throws ExtractoBancarioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioDao.grabarExtractoBancario(unaExtractoBancario);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ExtractoBancario ya existe en la base de datos.";
			throw new ExtractoBancarioDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo ser grabada.";
			throw new ExtractoBancarioException(msg,e);
		}
		
	}
	
	public List getExtractoBancarioes() throws ExtractoBancarioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return extractoBancarioDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ExtractoBancario no pudo ser leida.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	public ExtractoBancario leerExtractoBancario(Long id) throws ExtractoBancarioException {
		try {
			return extractoBancarioDao.buscarExtractoBancario(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La extractoBancario no existe en la base de datos.";
			throw new ExtractoBancarioNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo leerse desde la base de datos.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	public void borrarExtractoBancario(final Long idExtractoBancario) throws ExtractoBancarioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioDao.borrarExtractoBancario(idExtractoBancario);
				}
			});
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo borrase.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	public void borrarExtractoBancario(final ExtractoBancario unaExtractoBancario) throws ExtractoBancarioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioDao.borrarExtractoBancario(unaExtractoBancario);
				}
			});
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo borrase.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	public void actualizarExtractoBancario(final ExtractoBancario unaExtractoBancario) throws ExtractoBancarioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioDao.actualizarExtractoBancario(unaExtractoBancario);
				}
			});
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo actualizarse.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	public List getExtractoBancarios(final Filtro filtro) throws ExtractoBancarioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return extractoBancarioDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ExtractoBancario no pudo ser leida.";
			throw new ExtractoBancarioException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ExtractoBancarioDao getExtractoBancarioDao() {
		return extractoBancarioDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setExtractoBancarioDao(ExtractoBancarioDao extractoBancarioDao) {
		this.extractoBancarioDao = extractoBancarioDao;
	}

	
	public BancoPropioDao getBancoPropioDao() {
		return bancoPropioDao;
	}

	public void setBancoPropioDao(BancoPropioDao bancoPropioDao) {
		this.bancoPropioDao = bancoPropioDao;
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

	
	public DetalleExtractoDao getDetalleExtractoDao() {
		return detalleExtractoDao;
	}

	public void setDetalleExtractoDao(DetalleExtractoDao detalleExtractoDao) {
		this.detalleExtractoDao = detalleExtractoDao;
	}

	
	public ChequeHistorialDao getChequeHistorialDao() {
		return chequeHistorialDao;
	}

	public void setChequeHistorialDao(ChequeHistorialDao chequeHistorialDao) {
		this.chequeHistorialDao = chequeHistorialDao;
	}

	
	public ConciliacionFondoCabeceraDao getConciliacionFondoCabeceraDao() {
		return conciliacionFondoCabeceraDao;
	}

	public void setConciliacionFondoCabeceraDao(
			ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao) {
		this.conciliacionFondoCabeceraDao = conciliacionFondoCabeceraDao;
	}

	public ExtractoBancarioTipoMovimientoDao getExtractoBancarioTipoMovimientoDao() {
		return extractoBancarioTipoMovimientoDao;
	}

	public void setExtractoBancarioTipoMovimientoDao(
			ExtractoBancarioTipoMovimientoDao extractoBancarioTipoMovimientoDao) {
		this.extractoBancarioTipoMovimientoDao = extractoBancarioTipoMovimientoDao;
	}

	@Override
	public String grabarExtractoBancarioDesdeArchivo(final byte[] bytes)throws ExtractoBancarioException {
		ArchivoExtracto archivo;
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			archivo = (ArchivoExtracto) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
					ArchivoExtracto archivoExtracto=null;
					byte[] arrayByte = bytes;
					InputStream in = new ByteArrayInputStream(arrayByte);
					InputStream inProceso = new ByteArrayInputStream(arrayByte);
					String result = validarCarga(in, extractoBancarioDao);
					if(result.isEmpty())
					{
						archivoExtracto = new ArchivoExtracto(inProceso,"DNCTAS.DAT",new Operador());
						//Seteamos el banco propio al detalle del extracto.
						BancoPropio bancoPropio = setearBancoPropio(archivoExtracto,bancoPropioDao);
						if(bancoPropio != null)
						{
							//Filtramos solamente los registros que no tenemos en la base.
							List listResult = detalleExtractoDao.buscarRangoConDatos(archivoExtracto.getMinFecha(), archivoExtracto.getMaxFecha(), bancoPropio.getIdBancoPropio());
							archivoExtracto.eliminarRegistrosYaGuardados(listResult);
							if(archivoExtracto.getExtractoBancario().getDetallesExtractoOrdenado().size()>0)
							{
								
								if(archivoExtracto.getExtractoBancario()!=null){
									extractoBancarioDao.grabarExtractoBancario(archivoExtracto.getExtractoBancario());
									archivoExtracto.setBancoPropio(bancoPropio);
									
								}
							}else
							{
								archivoExtracto.setMensageResultado("El archivo extracto, no contiene registros que no esten en la base.");
							}
							
						}else{
							archivoExtracto.setMensageResultado("El archivo seleccionado, no contiene un numero de cuenta correcto.");
						}
						
						
					}
					
					return archivoExtracto;
				}
			});
			
//			if(result!=null && !result.isEmpty()){
//				throw new ExtractoBancarioException(result);
//			}
			
			
		} catch (DataIntegrityViolationException ex) {
			String msg = "El ExtractoBancario ya existe en la base de datos.";
			throw new ExtractoBancarioDuplicateException(msg,ex);
		} catch (Exception e) {
			
			if(e instanceof ExtractoBancarioException)
			{
				throw new ExtractoBancarioException(e.getMessage(),e);
			}
			else
			{
				String msg = "El ExtractoBancario no pudo ser grabado.";
				throw new ExtractoBancarioException(msg,e);
			}
		}
		
		
		try{
			//En este punto hacemos la conciliacion automatica del extracto bancario.
			if(archivo!=null && archivo.getBancoPropio()!=null){
				conciliacionAutomaticaExtracto(archivo, chequeHistorialDao, conciliacionFondoCabeceraDao, 
						extractoBancarioTipoMovimientoDao,archivo.getBancoPropio(), detalleExtractoDao, extractoBancarioDao);
				
			}
		} catch (ExtractoBancarioException e) {
			throw new ExtractoBancarioException(e.getMessage(),e);
		}
		
		return archivo.getMensageResultado()==null ? " Se grabo el archivo de extracto bancario con exito." : archivo.getMensageResultado();
	}
	/**
	 * Conciliacion automatica del extracto bancario. 
	 * @param archivoExtracto
	 * @param chequeHistorialDao
	 * @param conciliacionFondoDao
	 * @param extractoBancarioTipoMovimientoDao
	 * @param idBancoPropio
	 * @param detalleExtractoDao
	 */
	private String conciliacionAutomaticaExtracto(ArchivoExtracto archivoExtracto,final ChequeHistorialDao chequeHistorialDao, 
			final ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao, final ExtractoBancarioTipoMovimientoDao extractoBancarioTipoMovimientoDao,
			BancoPropio bancoPropio, final DetalleExtractoDao detalleExtractoDao,final ExtractoBancarioDao extractoBancarioDao)throws ExtractoBancarioException
	{
		String result = "";
		List listCabeceraConciliacionFondos = new ArrayList();
		List listChequeHistorialConciliados = new ArrayList();
		List listDetalleExtractoConciliados = new ArrayList();
		
		
		Filtro filtro = new Filtro();
		//filtro.agregarCampoOperValor("asientoItem.asiento.fecha", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(archivoExtracto.getMinFechaDate()));
		//filtro.agregarCampoOperValor("asientoItem.asiento.fecha", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(archivoExtracto.getMaxFechaDate()));
		filtro.agregarCampoOperValor("asientoItem.idPlanCuenta", Filtro.IGUAL, bancoPropio.getPlanCuenta().getIdPlanCuenta());
		filtro.agregarCampoOperValor("conciliado", Filtro.LIKEXS, "N");
		//Movimientos fondos a conciliar.
/*@I3918*/		List listChequeHistorial = chequeHistorialDao.listarTodosConciliar(493L);
//				Iterator tmpHistIt = listChequeHistorial.iterator();
//				while (tmpHistIt.hasNext())
//				{
//					ChequeHistorial tmpHistorial = (ChequeHistorial) tmpHistIt.next();
//					tmpHistorial.getAsientoItem().getIdAsientoItem();
//					tmpHistorial.getAsientoItem().getSigno();
//				}
/*@F3918*/		
		if(listChequeHistorial!=null && listChequeHistorial.size()>0)
		{
			filtro.reset();
			filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, archivoExtracto.getExtractoBancario().getIdBanco());
			//Tipos de movimientos que se tienen en cuenta del extracto para conciliar.
			List listTipoMovExtracto = extractoBancarioTipoMovimientoDao.listarTodos(filtro);
			
			//Filtramos solo los tipos de movimientos conciliables.
			Iterator iter = archivoExtracto.getExtractoBancario().getDetallesExtractoOrdenado().iterator();
			while(iter.hasNext()){
				DetalleExtracto detalle = (DetalleExtracto)iter.next();
				
				if(listTipoMovExtracto!=null && listTipoMovExtracto.size()>0)
				{
					//Solo se concilian los codigo de operacion que estan cargadas en tabla t_vis_fon_tipo_mov_extracto.
					if(listTipoMovExtracto.contains(detalle))
					{
						//Conciliacion Automatica
						if(listChequeHistorial.contains(detalle)){
							ChequeHistorial chequeHistorial = DetalleExtracto.buscarDetalleExtracto(listChequeHistorial, detalle);
							int signo = detalle.getSigno().equals('D') ?  -1 : 1;
							
							//Conciliamos del lado de fondos -> ChequeHistorial
/*@I3918*/						chequeHistorial.getCheque().setConciliado('S');/*@F3918*/
							chequeHistorial.setConciliado('S');
							listChequeHistorialConciliados.add(chequeHistorial);
							ConciliacionFondo conciliacionFondo = new ConciliacionFondo(ConciliacionFondo.TIPO_ID_FONDO,chequeHistorial.getIdChequeHistorial(),
									chequeHistorial.getCheque().getImporte(),signo);
							
							
							//Conciliamos del lado extracto -> DetalleExtracto
							detalle.setConciliado('S');
							listDetalleExtractoConciliados.add(detalle);
							ConciliacionFondo conciliacionExtracto = new ConciliacionFondo(ConciliacionFondo.TIPO_ID_EXTRACTO,detalle.getIdDetalleExtracto(),
									detalle.getImporte(),signo);
							
							//Armamos el objeto cabecera de conciliacion. Por defecto esta NO conciliado hasta que se confirme.
							ConciliacionFondoCabecera cabecera = new ConciliacionFondoCabecera(null,null,new Date(),null,null,'S',detalle.getBancoPropio());
							conciliacionFondo.setConciliacionFondoCabecera(cabecera);
							conciliacionExtracto.setConciliacionFondoCabecera(cabecera);
							
							Set setConciliacionFondo = new HashSet();
							setConciliacionFondo.add(conciliacionFondo);
							setConciliacionFondo.add(conciliacionExtracto);
							cabecera.setConciliacionFondos(setConciliacionFondo);
							
							listCabeceraConciliacionFondos.add(cabecera);
						}
						
					}
				}else{
					result="No existen Codigos de Operacion configurados para el archivo del Banco que desea cargar.";
				}
			}
			
			
			if(listCabeceraConciliacionFondos.size()==listDetalleExtractoConciliados.size() 
					&& listDetalleExtractoConciliados.size()==listChequeHistorialConciliados.size())
			{
				this.impactarConciliacionAutomatica(chequeHistorialDao, conciliacionFondoCabeceraDao, detalleExtractoDao, 
						listCabeceraConciliacionFondos, listDetalleExtractoConciliados, listChequeHistorialConciliados);
				
				//Si se conciliarion todos los detalles del extracto, ponemos el extracto como conciliado. 
				if(listDetalleExtractoConciliados.size()==archivoExtracto.getExtractoBancario().getCantRegistroMF()){
					archivoExtracto.getExtractoBancario().setConciliado('S');
					extractoBancarioDao.actualizarExtractoBancario(archivoExtracto.getExtractoBancario());
				}
				
			}
			else
			{
				throw new ExtractoBancarioException("Se produjo un error grave los items a conciliar no son consistentes.");
			}
			
		}else{
			result="No existen asientos para conciliar para el rango de fechas y banco del archivo extracto.";
		}
		
		
		return result;
	}
	
	
	private void impactarConciliacionAutomatica(final ChequeHistorialDao chequeHistorialDao, 
			final ConciliacionFondoCabeceraDao conciliacionFondoCabeceraDao, final DetalleExtractoDao detalleExtractoDao, 
			final List listCabeceraConciliacionFondos,final List listDetalleExtractoConciliados,
			final List listChequeHistorialConciliados)
	{
		if(listCabeceraConciliacionFondos!=null && listCabeceraConciliacionFondos.size()>0)
		{
			final Iterator iterCabecera = listCabeceraConciliacionFondos.iterator();
			final Iterator iterChuequeHis = listChequeHistorialConciliados.iterator();
			final Iterator iterDetalle = listDetalleExtractoConciliados.iterator();
				
			while(iterCabecera.hasNext()){
				
				transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						try
						{
							ConciliacionFondoCabecera cabecera = (ConciliacionFondoCabecera)iterCabecera.next();
							conciliacionFondoCabeceraDao.grabarConciliacionFondoCabecera(cabecera);
							
							ChequeHistorial chequeHistorial = (ChequeHistorial)iterChuequeHis.next();
							chequeHistorialDao.actualizarChequeHistorial(chequeHistorial);	
							
							DetalleExtracto detalle = (DetalleExtracto)iterDetalle.next();
							detalleExtractoDao.actualizarDetalleExtracto(detalle);
							
						}catch(Exception e)
						{
							log.error(e.getMessage() + " Tracing: "  + e.getStackTrace());
						}
						return null;
					}
				});
			}
				
			
		}
		
	}
	/**
	 *	Este metodo setea el banco propio a los detalles extracto. En cada archivo vienen los detalles del mismo banco. 
	 * @param archivoExtracto
	 * @param pBancoPropioDao
	 * @return
	 */
	private static BancoPropio setearBancoPropio(ArchivoExtracto archivoExtracto, BancoPropioDao pBancoPropioDao){
		Long idBancoPropio = 0L;
		boolean result=true;
		Iterator iter = archivoExtracto.getExtractoBancario().getDetallesExtractoOrdenado().iterator();
		BancoPropio bancoPropio = null;
		if(iter.hasNext()){

			DetalleExtracto det = (DetalleExtracto)iter.next();	
			
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, archivoExtracto.getExtractoBancario().getIdBanco());
			filtro.agregarCampoOperValor("numeroCuenta", Filtro.LIKEXS, det.getNroCuenta().trim());
			List list = pBancoPropioDao.listarTodos(filtro);
			if(list!=null && list.size()>0)
			{
				bancoPropio = (BancoPropio)list.get(0);
				det.setBancoPropio(bancoPropio);
				String numeroCuentaExtracto = det.getNroCuenta().trim();
//				idBancoPropio = bancoPropio.getIdBancoPropio();
				while(iter.hasNext()){
					det = (DetalleExtracto)iter.next();
					if(numeroCuentaExtracto.equals(det.getNroCuenta().trim()))
					{
						det.setBancoPropio(bancoPropio);
					}
					else{
						//Si al menos un detalleExtracto no tiene un numero de cuenta correcto, resultado fallido.
						result=false;
						break;
					}
				}
			}
		}
		if (result){
			bancoPropio.getPlanCuenta().getIdPlanCuenta();
			return bancoPropio;
		}else{
			return null;
		}
	}
	
	private static String validarCarga(InputStream in, ExtractoBancarioDao extractoBancarioDao) {
		String result="";
		if (in==null) {
			result="El archivo leido esta vacio.";
		} else {
			//Valida si la carga del archivo DNCTAS ya fue realizada previamente
			try {
				BufferedReader d = new BufferedReader(new InputStreamReader(in));
				String cadenaTexto = d.readLine();
				ArchivoExtracto archivo = new ArchivoExtracto();
				ExtractoBancario extracto = archivo.armarCabecera(cadenaTexto);
				if(archivo.isFormatoCorrecto())
				{
//					Filtro filtro = new Filtro();
//					filtro.agregarCampoOperValor("nroCliente", Filtro.LIKEXS, extracto.getNroCliente());
//					filtro.agregarCampoOperValor("fechaProcesoCadena", Filtro.LIKEXS, extracto.getFechaProcesoCadena());
//					List extractoList = extractoBancarioDao.listarTodos(filtro); 
//						
//					if(extractoList!=null && extractoList.size()>0){
//						result = "El archivo de extracto ya fue procesado.";
//					}
				}else{
					result = "El archivo no tiene el formato correcto.";
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}

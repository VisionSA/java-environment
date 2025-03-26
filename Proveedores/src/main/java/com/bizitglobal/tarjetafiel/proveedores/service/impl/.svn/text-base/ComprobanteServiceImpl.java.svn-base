package com.bizitglobal.tarjetafiel.proveedores.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.LugarException;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteImputadoDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.CuotaComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobantesNoCancelados;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteService;

/**
 *	Implementacion de la interfaz ComprobanteService.
 */
public class ComprobanteServiceImpl implements ComprobanteService {
	private static final Logger log = Logger.getLogger(ComprobanteServiceImpl.class);
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ComprobanteDao comprobanteDao;
	private CuotaComprobanteDao cuotaComprobanteDao;
	private ComprobanteImputadoDao comprobanteImputadoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarComprobante(final Comprobante unComprobante) throws ComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteDao.grabarComprobante(unComprobante);
				}
			});
		} 
		catch (DataIntegrityViolationException ex) {
			String msg = "El comprobante ya existe en la base de datos.";
			throw new ComprobanteDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante no pudo ser grabado.";
			throw new ComprobanteException(msg,e);
		}
	}

	public List getComprobantes(final Filtro filtro) throws ComprobanteException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return comprobanteDao.listarTodos(filtro);
				}
			});
		}
		catch (Exception e) {
			String msg = "La lista de comprobantes no pudo ser leida.";
			throw new ComprobanteException(msg,e);
		}
	}
	
	public Comprobante leerComprobante(Long id) throws ComprobanteException {
		Comprobante result = null;
		
		try {
			result = comprobanteDao.buscarComprobante(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El comprobante no existe en la base de datos.";
			throw new ComprobanteNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante no pudo leerse desde la base de datos.";
			throw new ComprobanteException(msg,e);
		}
		
		return result;
	}
	
	public void borrarComprobante(final Long idComprobante) throws ComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteDao.borrarComprobante(idComprobante);
				}
			});
		}
		catch (Exception e) {
			String msg = "El comprobante no pudo borrase.";
			throw new ComprobanteException(msg,e);
		}
	}
	
	public void borrarComprobante(final Comprobante unComprobante) throws ComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteDao.borrarComprobante(unComprobante);
				}
			});
		}
		catch (Exception e) {
			String msg = "El comprobante no pudo borrase.";
			throw new ComprobanteException(msg,e);
		}
	}	
	
	public void actualizarComprobante(final Comprobante unComprobante) throws ComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteDao.actualizarComprobante(unComprobante);
				}
			});
		} 
		catch (Exception e) {
			String msg = "No se pudo actualizar el comprobante.";
			throw new ComprobanteException(msg,e);
		}
	}
	
	public Long buscarNro(Integer nroCorto, Integer nroLargo, Proveedor proveedor) throws ComprobanteException {
		List idList;
		try {
			idList = comprobanteDao.buscarNro(nroCorto, nroLargo, proveedor);
		} catch (Exception e) {
			String msg = "No se pudo aceder a la base de datos.";
			throw new ComprobanteException(msg,e);
		}
		
		if (idList.isEmpty()) {
			return null;
		} else {
			return (Long)idList.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteService#getComprobantesNoCancelados(com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor, java.util.Iterator)
	 */
	public ComprobantesNoCancelados getComprobantesNoCancelados(Long idProveedor) throws ComprobanteException {
		boolean cancelado = true;
		ComprobantesNoCancelados result = new ComprobantesNoCancelados();
		log.info("Entrando a getComprobantesNoCancelados");
		List comprobantesList;

		comprobantesList = comprobanteDao.listarTodos(new Filtro("proveedor.idProveedor",Filtro.IGUAL,idProveedor));
		
		if(!comprobantesList.isEmpty()) {
//			log.info("lista de comprobantes obtenido");
			Iterator comprobantes = comprobantesList.iterator(); // Obtenemos la lista de comprobantes.
			while(comprobantes.hasNext()) {
				cancelado = true;
				Comprobante comp = (Comprobante)comprobantes.next();
				if (!comp.getTipoComprobante().getDescripcionCorta().equals("OP")) {
					Set cuotasList = comp.getCuotaComprobantes();
					Set cuotasNoImputadas = new HashSet(); // AGREGADO!!!! ----- para la lista de comprobantes.
					if(!cuotasList.isEmpty()) {
//						log.info("lista de cuotas obtenido");
						Iterator cuotas = cuotasList.iterator(); // Obtenemos una lista de cuotas para el comprobante.
						while(cuotas.hasNext()) {
							CuotaComprobante cuotaComprobante = (CuotaComprobante)cuotas.next();
//							log.info("Cuota: " + cuotaComprobante);
							float importeCuota = cuotaComprobante.getImporte().floatValue();
							float sumaImporteCuota = new Float(0).floatValue();
							Set imputadosSet = cuotaComprobante.getCuotaComprobanteD();
							if(!imputadosSet.isEmpty()) {
//								log.info("lista de comprobantes imputados obtenido");
								Iterator imputados = imputadosSet.iterator(); // Obtenemos una lista de comprobantes que ya han sido imputados.
								while(imputados.hasNext()) {
									ComprobanteImputado comprobanteImputado = (ComprobanteImputado)imputados.next();
//									float importeComprobanteImputado = new Float(comprobanteImputado.
//											getImporteCancela().toString()).floatValue();
									sumaImporteCuota += comprobanteImputado.getImporteCancela().floatValue();
								}
							} //else {
//								cancelado = false;
//								result.addCuotaComprobante(cuotaComprobante);
//								log.info("Cuota agragada");
//							}
							
							if(importeCuota!=sumaImporteCuota) {
								cancelado = false;
								result.addCuotaComprobante(cuotaComprobante);
								cuotasNoImputadas.add(cuotaComprobante);
//								log.info("Cuota agregada");
							}
						}
					}
//					comp.setCuotaComprobantes(cuotasNoImputadas);
					if(!cancelado) {
						result.addComprobante(comp);
//						log.info("Comprobante agregado");
					}
				}
			}
		}
		return result;
	}
	
	public ComprobantesNoCancelados getComprobantesNoCanceladosSec(Long idProveedor) 
			throws ComprobanteException {
		return getComprobantesNoCanceladosGenerico(idProveedor, true);
	}
	
	public ComprobantesNoCancelados getOrdenesNoCanceladasSec(Long idProveedor) 
			throws ComprobanteException {
		return getComprobantesNoCanceladosGenerico(idProveedor, false);
	}
	
	private ComprobantesNoCancelados getComprobantesNoCanceladosGenerico(Long idProveedor, 
		boolean comprobante) throws ComprobanteException {
		
		boolean cancelado = true;
		ComprobantesNoCancelados result = new ComprobantesNoCancelados();
		
		List comprobantesList = comprobanteDao.listarTodos(new Filtro("proveedor.idProveedor",Filtro.IGUAL,idProveedor));
		if(!comprobantesList.isEmpty()) {
			//			log.info("lista de comprobantes obtenido");
			//			log.info("Cantidad de comprobantes  ->  "+ comprobantesList.size());
			Iterator comprobantes = comprobantesList.iterator(); 
			while(comprobantes.hasNext()) {
				cancelado = true;
				Comprobante comp = (Comprobante)comprobantes.next();
				Comprobante copia = (Comprobante)comp.copia();
				Set cuotasList = comp.getCuotaComprobantes();
				Set cuotasNoImputadas = new HashSet(); 
				if(!cuotasList.isEmpty() && (esComprobante(comp) == comprobante)) {
					//					log.info("lista de cuotas obtenido");
					Iterator cuotas = cuotasList.iterator(); 
					while(cuotas.hasNext()) {
						CuotaComprobante cuotaComprobante = (CuotaComprobante)cuotas.next();
						//						log.info("Cuota: " + cuotaComprobante);
						//						log.info("Cuota - Id Comprobante: " + cuotaComprobante.getComprobante().getIdComprobante() + " Importe Cuota:"+ cuotaComprobante.getImporte());
						float importeCuota = new BigDecimal(cuotaComprobante.getImporte().floatValue()).
						setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
						//						log.info("$$ Importe Cuota = " + importeCuota);
						float sumaImporteCuota = 0;
						Set imputadosSet = null;
						if(comprobante) {
							imputadosSet = cuotaComprobante.getCuotaComprobanteD();
						} else {
							imputadosSet = cuotaComprobante.getCuotaComprobanteH();
						}
						if(!imputadosSet.isEmpty()) {
							//							log.info("lista de comprobantes imputados obtenido");
							Iterator imputados = imputadosSet.iterator(); 
							while(imputados.hasNext()) {
								ComprobanteImputado comprobanteImputado = (ComprobanteImputado)imputados.next();
								sumaImporteCuota += comprobanteImputado.getImporteCancela().floatValue();
								//								log.info("Importe imputado" + comprobanteImputado.getImporteCancela());
								//								log.info("$$ Suma total imputado = " + sumaImporteCuota);
							}
						} 
						sumaImporteCuota = new BigDecimal(sumaImporteCuota).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
						if(importeCuota!=sumaImporteCuota) {
							cancelado = false;
							result.addCuotaComprobante(cuotaComprobante);
							cuotasNoImputadas.add(cuotaComprobante);
							//							log.info("Cuota agregada");
						}
					}
				}
				comp = null;
				copia.setCuotaComprobantes(cuotasNoImputadas);
				
				if(!cancelado) {
					if (copia.getCompRevertido() == null) {
						result.addComprobante(copia);
						//						log.info("Comprobante agregado");
					}					
				}
				
			}
		}
		return result;
	}
	
	public boolean esComprobante(Comprobante unComprobante) {
		List tiposOrdenes = new ArrayList();
		tiposOrdenes.add("OP");
		tiposOrdenes.add("NCA");
		tiposOrdenes.add("NCB");
		tiposOrdenes.add("NCC");
		tiposOrdenes.add("NCM");
		tiposOrdenes.add("NCT");
		tiposOrdenes.add("NCIA");
		tiposOrdenes.add("NCIB");
		tiposOrdenes.add("NCIC");
		tiposOrdenes.add("NCIM");
		tiposOrdenes.add("NCIT");
		tiposOrdenes.add("ROP");

		if(!tiposOrdenes.contains(unComprobante.getTipoComprobante().getDescripcionCorta())) {
			return true;
		}
		
		return false;
	}
	

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ComprobanteDao getComprobanteDao() {
		return comprobanteDao;
	}

	/**
	 * Necesario para spring.
	 * @param comprobanteDao, Objeto dao a setear.
	 */
	public void setComprobanteDao(ComprobanteDao comprobanteDao) {
		this.comprobanteDao = comprobanteDao;
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

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */	
	public ComprobanteImputadoDao getComprobanteImputadoDao() {
		return comprobanteImputadoDao;
	}

	/**
	 * Necesario para spring.
	 * @param comprobanteImputadoDao, Objeto dao a setear.
	 */	
	public void setComprobanteImputadoDao(ComprobanteImputadoDao comprobanteImputadoDao) {
		this.comprobanteImputadoDao = comprobanteImputadoDao;
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */	
	public CuotaComprobanteDao getCuotaComprobanteDao() {
		return cuotaComprobanteDao;
	}

	/**
	 * Necesario para spring.
	 * @param cuotaComprobanteDao, Objeto dao a setear.
	 */	
	public void setCuotaComprobanteDao(CuotaComprobanteDao cuotaComprobanteDao) {
		this.cuotaComprobanteDao = cuotaComprobanteDao;
	}

}

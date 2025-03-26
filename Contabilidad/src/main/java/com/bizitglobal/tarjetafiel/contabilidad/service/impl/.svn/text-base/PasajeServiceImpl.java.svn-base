package com.bizitglobal.tarjetafiel.contabilidad.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.contabilidad.service.PasajeService;
import com.bizitglobal.tarjetafiel.general.dao.IGenericDao;


public class PasajeServiceImpl implements PasajeService {
	private static final Logger log = Logger.getLogger(PasajeServiceImpl.class);
	
	private IGenericDao genericDao;

	                     
	
	
	
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
		
	public IGenericDao getGenericDao() {
		return genericDao;
	}
	 
	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}



	
	
	
	
	
	/**
	 * @id: 4933
	 * Method: pasajeDeFondosAContabilidad
	 * Description: Realiza el pasaje de fondos a contabildad entre fechas determinadas
	 * @param idOperador
	 * @param fecha_desde
	 * @param fecha_hasta
	 */
	@Override
/*@I5869*/	public Long pasajeDeFondosAContabilidad(Long idOperador,String fecha_desde, String fecha_hasta,Long numeroEjercicio) throws Exception {
/*@F5499*/		log.info("pasaje de fondos a contabilidad entre fechas");
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ID_OPERADOR", new Long(idOperador));
		param.put("FECHA_DESDE", fecha_desde);
		param.put("FECHA_HASTA", fecha_hasta);
/*@F5869*/		param.put("EJERCICIO", numeroEjercicio);
		HashMap<String, Object> outParam = new HashMap<String, Object>();
		outParam.put("CANTIDAD_TRASPASO", new Long(0));		
		Map results = genericDao.ejecutarStoreFunction("SP_PASAJE_CONT03",param,outParam);
		
		return ((BigDecimal)(results.get("CANTIDAD_TRASPASO"))).longValue();
//		for (Iterator it = results.entrySet().iterator(); it.hasNext(); ) {
//            System.out.println(it.next());  
//        }
	}
	

	
	/**
	 * @id: 5499
	 * Method: pasajeDeFondosAContabilidad
	 * Description: Realiza el pasaje de fondos a contabildad entre fechas determinadas
	 * @param idOperador
	 * @param fecha_desde
	 * @param fecha_hasta
	 */
	@Override
/*@F5869*/	public Long pasajeDeComercioAContabilidad(Long idOperador,String fecha_desde, String fecha_hasta,Long numeroEjercicio) throws Exception {
		log.info("pasaje de comercio a contabilidad entre fechas");
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ID_OPERADOR", new Long(idOperador));
		param.put("FECHA_DESDE", fecha_desde);
		param.put("FECHA_HASTA", fecha_hasta);
/*@F5869*/		param.put("EJERCICIO", numeroEjercicio);
		HashMap<String, Object> outParam = new HashMap<String, Object>();
		outParam.put("CANTIDAD_TRASPASO", new Long(0));		
		Map results = genericDao.ejecutarStoreFunction("SP_PASAJE_CONT02",param,outParam);
		return ((BigDecimal)(results.get("CANTIDAD_TRASPASO"))).longValue();
	}
}

package com.bizitglobal.tarjetafiel.fondos;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.PlanCuentaDosService;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.fondos.service.CajaService;
import com.bizitglobal.tarjetafiel.fondos.service.impl.ChequeLugarServiceImpl;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;

 
/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
@ContextConfiguration
public class TestFondosService extends AbstractTransactionalJUnit4SpringContextTests {

	
	private static Logger log = Logger.getLogger(TestFondosService.class);
	
	@Test
	public void testABM() throws Exception {
		applicationContext = new FileSystemXmlApplicationContext("src/main/java/com/bizitglobal/tarjetafiel/fondos/fondosTest.xml");
		ChequeLugarServiceImpl chequeLugarServiceImpl = (ChequeLugarServiceImpl)applicationContext.getBean("chequeLugarServiceTarget");
		try{
			
			Lugar lugar = new Lugar();
			lugar.setIdLugar(1L);
			List<Cheque> list = chequeLugarServiceImpl.buscarChequesEnLugar(lugar);
			for(Cheque cheque : list){
				System.out.println(cheque.getBanco().getDescripcion());
				//System.out.println(cheque.getChequeEstado().getDescripcion());
			}
			
		}catch (Exception e) {
			log.error(e,e);
		}

	}
	
	
}

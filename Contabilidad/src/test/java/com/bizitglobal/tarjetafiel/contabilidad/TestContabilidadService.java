package com.bizitglobal.tarjetafiel.contabilidad;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.PlanCuentaDosService;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;

 
/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
public class TestContabilidadService extends TestCase {
	private ApplicationContext ctx;
	private PlanCuentaDosService planCuentaService;
	private OperadorDao operadorDao;
	private PlanCuentaDosDao planCuentaDao;
	private AsientoService asientoService;
	
	private static Logger log = Logger.getLogger(TestContabilidadService.class);
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		//PropertyConfigurator.configure("log4j.properties");
		ctx = new FileSystemXmlApplicationContext("src/main/java/com/bizitglobal/tarjetafiel/contabilidad/contabilidadTest.xml");
		planCuentaService = (PlanCuentaDosService)ctx.getBean("planCuentaDosService");
		planCuentaDao = (PlanCuentaDosDao)ctx.getBean("planCuentaDosDao");
		asientoService = (AsientoService)ctx.getBean("asientoService");
		
	//	operadorDao = (OperadorDao)ctx.getBean("operadorTestDao");
//		sucTelefonoDao = (SucTelefonoDao)ctx.getBean("sucTelefonoDao");
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		planCuentaService = null;
	}
	
	/**
	 * Test propiamente dicho, en este metodo se encuentra el codigo a testear.
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
	public void testABM() throws Exception {
		
		try {
		log.info("");
		log.info("");
		log.info("");
		log.info("");
		log.info("Iniciando el test...");
		tearDown();
		setUp();


		log.info("**********************************************");
		log.info("LISTANDO PLAN CUENTA DE LA BASE DE DATOS");
		log.info("**********************************************");
		
	/*	List planCuentas = planCuentaService.getPlanCuenta(generaPermiso());
		for (Object domicilio : domicilios) {
			log.info(((Domicilio)domicilio));
		}*/
		
     	PlanCuentaDos planCuenta =new PlanCuentaDos();
		log.info("Tamos por generar el filtro");
		Filtro filtro = new Filtro("idPlanCuenta", Filtro.IGUAL, new Long(1));
		log.info("Ya creamos el filtro.");
		List aux = new ArrayList();
		aux = planCuentaService.getPlanCuenta(filtro);
		if(!aux.isEmpty()){
			log.info("La lista no esta vacia.");
			
			Iterator iterator = aux.iterator();
			while (iterator.hasNext()) {
				planCuenta = (PlanCuentaDos) iterator.next();
				planCuenta.getIdPlanCuenta();
				planCuenta.getIdPadre();
				planCuenta.getSeccion();
				
				log.info("aca traemos el plan cuenta.");
				log.info(planCuenta.toString());
				
			}
		}
		
		
		
		
		
		
	/*	log.info("*********************************************");
		log.info("GRABANDO NUEVO PLAN CUENTA EN LA BASE DE DATOS");
		log.info("*********************************************");		
		
		PlanCuenta planCuenta = new PlanCuenta();*/
		
	/*	planCuenta.setIdPlanCuenta(new Long(1));	
		planCuenta.setIdPadre(new Long(0));
		planCuenta.setSeccion(new Long(2));
		planCuenta.setNumeroContable("10000545400");
		planCuentaService.grabarPlanCuenta(planCuenta);
		log.info("plan Cuenta  grabado ->"+planCuenta);
*/		
		
		
		
		
		
	    log.info("Tamos por llamar al dao para q nos traiga el plan");
		planCuenta = planCuentaService.leerPlanCuenta(new Long(0));
		log.info("Ya trajimos plan cuenta");
		if(planCuenta != null){
			log.info("no esta vacía");
			log.info("plan cuenta: " +  planCuenta.toString());
		}else{
			log.info("plan cuenta nulo");
	}
		
		
		
		
		
		log.info("*******************************************");
		log.info("ELIMINANDO PLAN CUENTA DE  LA BASE DE DATOS");
		log.info("*******************************************");
		
		
	//	planCuentaService.borrarPlanCuenta(planCuenta);
		log.info("plan cuenta borrado");
		
		
		
			
		log.info("Fin del test.");
		
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
//		try {
//			List list = asientoService.getAsiento(new Filtro());
//			if (!list.isEmpty()) {
//				Asiento asiento = (Asiento)list.get(0);
//				log.info("Asiento: " + asiento);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Ejecuta el test.
	 * @param args, nada.
	 */
	public static void main (String[] args)	{
		//junit.textui.TestRunner.run(TestOperadorService.class);
	/*	TestContabilidadService test= new TestContabilidadService();
		try {
			test.testABM();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
}

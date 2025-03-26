package com.bizitglobal.tarjetafiel.impuestos;

import java.util.Date;
import java.util.Vector;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestoService;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;

/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
public class TestImpuestosService extends TestCase {
	private ApplicationContext ctx;
	private ImpuestoService impuestoService;
	private static Logger log = Logger.getLogger(TestImpuestosService.class);
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		ctx = new FileSystemXmlApplicationContext("src/main/java/com/bizitglobal/tarjetafiel/impuestos/impuestoTest.xml");
		impuestoService = (ImpuestoService)ctx.getBean("impuestoService");
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		impuestoService = null;
	}
	
	/**
	 * Test propiamente dicho, en este metodo se encuentra el codigo a testear.
	 * @throws Exception
	 */
	public void testABM() throws Exception {
		log.info("");
		log.info("");
		log.info("");
		log.info("");
		log.info("Iniciando el test...");
		Impuesto impuesto = new Impuesto();
		log.info("Leyendo de la base...");
		impuesto = impuestoService.leerImpuesto(new Long(1));
		log.info("IMPUESTO ...>" + impuesto);
		
		log.info("Fin del test.");
	}
	
	/**
	 * Ejecuta el test.
	 * @param args, nada.
	 */
	public static void main (String[] args)	{
		//junit.textui.TestRunner.run(TestOperadorService.class);
	}
}

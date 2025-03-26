package com.bizitglobal.tarjetafiel.proveedores;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.ModalidadPago;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorSICOREDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorCtaCteException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CbuValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCategoria;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;
import com.bizitglobal.tarjetafiel.proveedores.reportes.ReporteProvSP;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCtaCteService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorSICOREService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorService;

/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
@ContextConfiguration
public class TestProveedorService extends AbstractTransactionalJUnit4SpringContextTests {
	
	
	private ApplicationContext ctx;
	
	private ProveedorService proveedorService;
	
	private ReporteProvSP proveedorReporteDao;
	
	private ProveedorCtaCteService proveedorCtaCteService;
	
	private ProveedorSICOREService proveedorSICOREService;
	private static Logger log = Logger.getLogger(TestProveedorService.class);
	
	
	
	/**
	 * Test propiamente dicho, en este metodo se encuentra el codigo a testear.
	 * @throws Exception
	 */
	@Test
	public void testABM() throws Exception {
//		ctx = new FileSystemXmlApplicationContext("classpath:/com/bizitglobal/tarjetafiel/proveedores/proveedorTest.xml");
//		proveedorService = (ProveedorService)ctx.getBean("proveedorService");
//		proveedorReporteDao = (ReporteProvSP)ctx.getBean("proveedorReporteDao");
//		proveedorCtaCteService = (ProveedorCtaCteService)ctx.getBean("proveedorCtaCteService");
//		proveedorSICOREService = (ProveedorSICOREService)ctx.getBean("proveedorSICOREService");
//		log.info("");
//		log.info("");
//		log.info("");
//		log.info("");
//		log.info("Iniciando el test...");
//		
//		log.info("**********************************");
//		log.info("*   CARGANDO PROVEEDOR SICORE    *");
//		log.info("**********************************");		
//		
////		try {
////			Calendar fecha = Calendar.getInstance();
////			Timestamp fechaHasta = new Timestamp(fecha.getTime().getTime());
////			fecha.add(Calendar.YEAR, -1);
////			Timestamp fechaDesde = new Timestamp(fecha.getTime().getTime());
////			
////			Iterator iterSICORE = proveedorSICOREService.obtenerSICORE(fechaDesde, fechaHasta).iterator();
////			while (iterSICORE.hasNext()) {
////				log.info(iterSICORE.next() + "|");
////			}
////		} catch (Exception e1) {
////			e1.printStackTrace();
////		}
//		
//		log.info("*********************************************");
//		log.info("CARGANDO OBJECTOS DEPENDIENTES");
//		log.info("*********************************************");
//				
//		//Proveedor proveedor = proveedorService.leerProveedor(new Long(1), generaPermiso());
//		try {
//			CbuValido cbuValido = new CbuValido("2650450202145056396676");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		Email email1 = new Email();
//		email1.setDescripcion("ZZZZZZ");
//		email1.setEmail("ejemplo1@ejemplo1.com");
//		
//		Email email2 = new Email();
//		email2.setDescripcion("ejemplo2");
//		email2.setEmail("ejemplo2@ejemplo2.com");
//		
//		//proveedor.setRazonSocial("NUEVO1");
//		
//		Telefono tel1 = new Telefono();
//		tel1.setNroTlefono("555-5555");
//		tel1.setEsCelular("N");
//		tel1.setEsFax("N");
//		tel1.setHorarios("14:00hs");
//		tel1.setDescripcion("Telefono casa");
//		
//		Grupo unGrupo = new Grupo();
//		unGrupo.setIdGrupo(new Long(13));
//		unGrupo.setDescripcion("Deudores");
//		
//		Actividad actividad = new Actividad();
//		actividad.setIdActividad(new Long(12));
//		actividad.setDescripcion("Alquiler Inmuebles");
//		
//		ModalidadPago modalidadPago = new ModalidadPago();
//		modalidadPago.setIdModalidadPago(new Long(10));
//		modalidadPago.setEsDiaCalendario("S");
//		modalidadPago.setEsDiaSemana("S");
//		modalidadPago.setNombreDia("Lunes");
//		modalidadPago.setNroDia(new Integer(5));
//		
//		Moneda moneda = new Moneda();
//		moneda.setIdMoneda(new Long(1));
//		moneda.setDescripcion("Pesos");
//		
//		Operador operador = new Operador();
//		operador.setCodigo(new Long(1212));
//		
//		Domicilio domicilio = new Domicilio();
//		domicilio.setIdDomicilio(new Long(1));
//		
//		SucursalFiel sucursal = new SucursalFiel();
//		sucursal.setIdSucursal(new Long(1));
//		sucursal.setNombre("Principal");
//		sucursal.setDomicilio(domicilio);
//		
//		TipoVencimiento tipo = new TipoVencimiento();
//		tipo.setIdTipoVencimiento(new Long(1));
//		
//		log.info("*********************************************");
//		log.info("*   CARGANDO PROVEEDORES CUENTA CORRIENTE    *");
//		log.info("*********************************************");	
////		try {
////			Calendar calendar = Calendar.getInstance();
////			Timestamp fechaHasta = new Timestamp(calendar.getTime().getTime());
////			calendar.add(Calendar.YEAR, -1);
////			Timestamp fechaDesde = new Timestamp(calendar.getTime().getTime());
////			proveedorCtaCteService.obtenerCtaCte(new Long(10332), fechaDesde,
////					fechaHasta);
////		} catch (ProveedorCtaCteException e) {
////			e.printStackTrace();
//		}
		
		log.info("Fin del test.");
	}
	
	
	
}

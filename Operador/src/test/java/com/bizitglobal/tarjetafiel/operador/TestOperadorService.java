package com.bizitglobal.tarjetafiel.operador;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bizitglobal.tarjetafiel.operador.dao.MenuItemDao;
import com.bizitglobal.tarjetafiel.operador.dao.RolPaginaPermisoDao;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.tarjetafiel.operador.service.RolMenuItemService;
import com.bizitglobal.tarjetafiel.operador.service.RolPaginaPermisoService;

/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
public class TestOperadorService extends TestCase {
	private ApplicationContext ctx;
	private OperadorService operadorService;
	private MenuItemDao menuItemDao;
	private RolPaginaPermisoService rolPaginaService;
	private RolMenuItemService rolMenuItemService;
	private static Logger log = Logger.getLogger(TestOperadorService.class);
	
	protected void setUp() throws Exception {	
		ctx = new FileSystemXmlApplicationContext("src/main/java/com/bizitglobal/tarjetafiel/operador/operadorTest.xml");
		operadorService = (OperadorService)ctx.getBean("operadorService");
		menuItemDao = (MenuItemDao)ctx.getBean("menuItemDao");
		rolPaginaService = (RolPaginaPermisoService)ctx.getBean("rolPaginaPermisoService");
		rolMenuItemService = (RolMenuItemService)ctx.getBean("rolMenuItemService");
	}
	
	protected void tearDown() throws Exception {
		operadorService = null;
	}
	
	public void testABM() throws Exception {
		/*log.info("");
		log.info("");
		log.info("");
		log.info("");
		log.info("Iniciando el test...");
		
		log.info("iniciando prueba!!!");
		
		log.info("*********************************************");
		log.info("OBTENIENDO OPERADORES DESDE LA BASE DE DATOS");
		log.info("*********************************************");
		Operador operador_1 = operadorService.leerOperador(new Long(1212));
		log.info("Obteniendo un operador desde la base de datos ---> "+operador_1);
		log.info("Rol del operador -> "+operador_1.getRol().getDescripcion());
//		Set menus_1 = operador_1.getRol().getMenuItems();
//		if(!menus_1.isEmpty()) {
//			Iterator menus_1i = menus_1.iterator();
//			while(menus_1i.hasNext()) {
//				log.info("	Menu Para el Rol: "+((RolMenuItem)menus_1i.next()).getMenuItem().getLabel());
//			}
//		}*/
//		
//		Operador operador_2 = operadorService.leerOperador(new Long(1213));
//		log.info("Obteniendo un operador desde la base de datos ---> "+operador_2);
//		log.info("Rol del operador -> "+operador_2.getRol().getDescripcion());
//		Set menus_2 = operador_2.getRol().getMenuItems();
//		if(!menus_2.isEmpty()) {
//			Iterator menus_2i = menus_2.iterator();
//			while(menus_2i.hasNext()) {
//				log.info("	Menu Para el Rol: "+((RolMenuItem)menus_2i.next()).getMenuItem().getLabel());
//			}
//		}
//		
//		
//		Operador operador_3 = operadorService.leerOperador(new Long(1214));
//		log.info("Obteniendo un operador desde la base de datos ---> "+operador_3);
//		log.info("Rol del operador -> "+operador_3.getRol().getDescripcion());
//		Set menus_3 = operador_3.getRol().getMenuItems();
//		if(!menus_3.isEmpty()) {
//			Iterator menus_3i = menus_3.iterator();
//			while(menus_3i.hasNext()) {
//				log.info("	Menu Para el Rol: "+((RolMenuItem)menus_3i.next()).getMenuItem().getLabel());
//			}
//		}		
//		
//
//		log.info("*********************************************");
//		log.info("OBTENIENDO PERMISOS SEGUN ROL Y PAGINA");
//		log.info("*********************************************");		
//		List permisos = rolPaginaService.getPermisosPorPaginaYRol("listarProveedores.jsf", operador_1.getRol().getIdRol());
//		
//		if(!permisos.isEmpty()) {
//			Iterator permisosi = permisos.iterator();
//			while(permisosi.hasNext()) {
//				log.info("Permiso -> "+((Permiso)permisosi.next()).getNombre());
//			}
//		}
//		
//		log.info("*********************************************");
//		log.info("OBTENIENDO LISTA DE MENU ITEMS");
//		log.info("*********************************************");
//		List listaMenuItems = menuItemDao.listAll();
//		if(!listaMenuItems.isEmpty()) {
//			Iterator iter2 = listaMenuItems.iterator();
//			
//			while(iter2.hasNext()) {
//				MenuItem menuItem = (MenuItem)iter2.next();
//				log.info("Label : "+menuItem.getLabel());
//				log.info("Icon : "+menuItem.getIcon());
//				log.info("Action :"+menuItem.getAction());
//			}			
//			
//		}
		
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

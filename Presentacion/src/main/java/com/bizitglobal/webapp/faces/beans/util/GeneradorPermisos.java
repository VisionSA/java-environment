package com.bizitglobal.webapp.faces.beans.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.service.operador.ABMOperadorServiceFaces;
import com.bizitglobal.webapp.xml.Item;
import com.bizitglobal.webapp.xml.ItemPermiso;
import com.bizitglobal.webapp.xml.Menu;
import com.bizitglobal.webapp.xml.MenuAdmin;
import com.bizitglobal.webapp.xml.SubMenu;


@SuppressWarnings({"rawtypes","unchecked"})
public class GeneradorPermisos {
	private static Logger log = Logger.getLogger(GeneradorPermisos.class);
	private ABMOperadorServiceFaces service = new ABMOperadorServiceFaces();
	private String mensaje = "";


	public List buscarPermisos(Object nodo) {
		List result = new ArrayList();

		if (nodo instanceof Item) {
			List itemsList = ((Item) nodo).getItemPermisos();
			if (!itemsList.isEmpty()) {
				Iterator items = itemsList.iterator();
				while (items.hasNext()) {
					ItemPermiso permiso = (ItemPermiso) items.next();
					result.add(permiso);
				}
			}
		}

		if (nodo instanceof SubMenu) {
			List submenusList = ((SubMenu) nodo).getSubmenues();
			if (!submenusList.isEmpty()) {
				Iterator submenus = submenusList.iterator();
				while (submenus.hasNext()) {
					SubMenu submenu = (SubMenu) submenus.next();
					result.addAll(buscarPermisos(submenu));
				}
			}

			List itemsList = ((SubMenu) nodo).getItems();
			if (!itemsList.isEmpty()) {
				Iterator items = itemsList.iterator();
				while (items.hasNext()) {
					Item item = (Item) items.next();
					result.addAll(buscarPermisos(item));
				}
			}
		}

		if (nodo instanceof Menu) {
			List menusList = ((Menu) nodo).getSubmenues();
			if (!menusList.isEmpty()) {
				Iterator menus = menusList.iterator();
				while (menus.hasNext()) {
					SubMenu menu = (SubMenu) menus.next();
					result.addAll(buscarPermisos(menu));
				}
			}
		}

		return result;
	}


	public void generarPermisos() {
		log.info("Obteniendo lista de permisos desde el archivo xml...");
		List permisos = buscarPermisos(MenuAdmin.getInstance());

		log.info("Creando permiso para el root...");
		ItemPermiso root = new ItemPermiso(new Integer(1), "*", "*", "Todos los permisos");
		permisos.add(root);

		/*
		 * Iterator iterator = permisos.iterator(); while(iterator.hasNext()) { ItemPermiso aux = (ItemPermiso)iterator.next(); Permiso permiso = new
		 * Permiso(aux.getId(),aux.getDescripcion(),aux.getClase(),aux.getMetodo()); try { service.getPermisoService().borrarPermiso(permiso.getId(),
		 * generaPermiso()); } catch (PermisoException e1) { e1.printStackTrace(); }
		 * 
		 * try { service.getPermisoService().grabarPermiso(permiso, generaPermiso()); mensaje = "[Ok]"; log.info("Grabando permiso ---> "+permiso); }
		 * catch(PermisoException e) { mensaje = "[Falla]"; e.printStackTrace(); } }
		 */
	}


	/*
	 * public Iterator generaPermiso() { Permiso aux = new Permiso(new Integer(1),"","*","*"); Vector permisos = new Vector();
	 * log.info("Generando Permiso ... ["+aux+"]"); permisos.add(aux);
	 * 
	 * return permisos.iterator(); }
	 */
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}

package com.bizitglobal.webapp.faces.beans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class MenuBean {
	private List menu;
	private List menuPermitido;
	private List menuTerminado;


	public MenuBean() {
		menu = null;
		menuTerminado = null;
	}


	public void crear() {
		this.menu = null;
		this.menuTerminado = null;
		this.menuPermitido = new ArrayList();

		List menus = new ArrayList();
		Set rolMenus = Session.getOperador().getRol().getMenuItems();
		if (!rolMenus.isEmpty()) {
			Iterator rolMenusI = rolMenus.iterator();
			while (rolMenusI.hasNext()) {
				MenuItem item = ((RolMenuItem) rolMenusI.next()).getMenuItem();
				if (item.padre()) {
					menus.add(item);
				}
				menuPermitido.add(item);
			}
		}

		this.menu = menus;
		Collections.sort(this.menu);
	}


	public List getMenuSegunRol() {
		if (menuTerminado == null) {
			List result = new ArrayList();
			if (menu != null) {
				if (!menu.isEmpty()) {
					Object[] menus = menu.toArray();
					for (int i = 0; i < menus.length; i++) {
						MenuItem item = (MenuItem) menus[i];
						NavigationMenuItem padre = new NavigationMenuItem(item.getLabel(), item.getAction(), item.getIcon(), false);
						padre.setActionListener(item.getActionListener());
						result.add(cargarHijos(padre, Convertidores.setToList(item.getHijos())));
					}
				}
			}
			menuTerminado = result;
			return result;
		} else {
			return menuTerminado;
		}
	}
	

	private NavigationMenuItem cargarHijos(NavigationMenuItem padre, List hijos) {
		Collections.sort(hijos);

		Iterator hijosI = hijos.iterator();
		while (hijosI.hasNext()) {
			MenuItem item = ((MenuItemRelacion) hijosI.next()).getHijo();
			if (menuPermitido.contains(item)) {
				NavigationMenuItem nItem = new NavigationMenuItem(item.getLabel(), item.getAction(), item.getIcon(), false);
				nItem.setActionListener(item.getActionListener());
				padre.add(cargarHijos(nItem, Convertidores.setToList(item.getHijos())));
			}
		}

		return padre;
	}


	public List getMenu() {
		return menu;
	}


	public void setMenu(List menu) {
		this.menu = menu;
	}

}

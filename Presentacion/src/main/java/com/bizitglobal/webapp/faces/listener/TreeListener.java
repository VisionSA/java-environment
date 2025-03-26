package com.bizitglobal.webapp.faces.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.tree.DefaultMutableTreeNode;
import org.apache.myfaces.custom.tree.HtmlTree;
import org.apache.myfaces.custom.tree.event.TreeSelectionEvent;
import org.apache.myfaces.custom.tree.event.TreeSelectionListener;

import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.xml.Item;
import com.bizitglobal.webapp.xml.ItemPermiso;
import com.bizitglobal.webapp.xml.Menu;
import com.bizitglobal.webapp.xml.MenuAdmin;
import com.bizitglobal.webapp.xml.SubMenu;


@SuppressWarnings({"rawtypes","unchecked"})
public class TreeListener implements TreeSelectionListener {
	private Logger log = Logger.getLogger(TreeListener.class);
	private List permisosSeleccionados = null;


	public void valueChanged(TreeSelectionEvent arg0) {
		permisosSeleccionados = new ArrayList();
		HtmlTree tree = (HtmlTree) arg0.getComponent();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

		Object nodo = null;
		if (!node.toString().equals("Menu")) {
			nodo = getObjectMenu(MenuAdmin.getInstance(), node);
		} else {
			Menu menu = MenuAdmin.getInstance();
			List menuList = menu.getSubmenues();
			if (!menuList.isEmpty()) {
				Iterator menus = menuList.iterator();
				while (menus.hasNext()) {
					SubMenu subAux = (SubMenu) menus.next();
					permisosSeleccionados = contatenaListas(generarPermisos(subAux), permisosSeleccionados);
				}
			}
		}

		if (nodo instanceof SubMenu) {
			SubMenu submenu = (SubMenu) nodo;
			List lista = generarPermisos(submenu);
			permisosSeleccionados = contatenaListas(lista, permisosSeleccionados);
		}

		if (nodo instanceof Item) {
			Item item = (Item) nodo;
			log.info("Nombre Item:" + item.getNombre());
			List permisosList = item.getItemPermisos();
			if (!permisosList.isEmpty()) {
				Iterator permisos = permisosList.iterator();
				while (permisos.hasNext()) {
					ItemPermiso permiso = (ItemPermiso) permisos.next();
					permisosSeleccionados.add(permiso);
				}
			}
		}

		Session.setPermisosSeleccionadosCompletos(permisosSeleccionados);
	}


	public Object getObjectMenu(Menu menu, DefaultMutableTreeNode node) {
		Object result = null;
		SubMenu auxSubMenu = null;

		List submenuesList = menu.getSubmenues();
		Iterator submenues = submenuesList.iterator();
		while (submenues.hasNext()) {
			auxSubMenu = (SubMenu) submenues.next();
			result = encontrarSubMenu(auxSubMenu, node);
			if (result != null) {
				return result;
			}
		}

		return result;
	}


	public Object encontrarSubMenu(SubMenu submenu, DefaultMutableTreeNode node) {
		SubMenu auxSubMenu2 = null;
		Item auxItem = null;

		List itemsList = submenu.getItems();
		if (!itemsList.isEmpty()) {
			Iterator items = itemsList.iterator();
			while (items.hasNext()) {
				auxItem = (Item) items.next();
				if ((auxItem.getNombre().equals(node.toString())) &&
						(submenu.getNombre().equals(node.getParent().toString()))) {
					return auxItem;
				}
			}
		}

		List submenusList = submenu.getSubmenues();
		if (!submenusList.isEmpty()) {
			Iterator submenus2 = submenusList.iterator();
			while (submenus2.hasNext()) {
				auxSubMenu2 = (SubMenu) submenus2.next();

				if (node.getParent().toString().equals(auxSubMenu2.getNombre())) {
					Object auxResult = encontrarSubMenu(auxSubMenu2, node);
					if (auxResult != null) {
						return auxResult;
					}
				}

				if ((auxSubMenu2.getNombre().equals(node.toString())) &&
						(submenu.getNombre().equals(node.getParent().toString()))) {
					return auxSubMenu2;
				}

			}
		}

		if (node.getParent().toString().equals("Menu") && submenu.getNombre().equals(node.toString())) {
			return submenu;
		}

		return null;
	}


	public List generarPermisos(SubMenu submenu) {
		List result = new ArrayList();

		List itemsList = submenu.getItems();
		if (!itemsList.isEmpty()) {
			Iterator items = itemsList.iterator();
			while (items.hasNext()) {
				Item item = (Item) items.next();
				List permisosList = item.getItemPermisos();
				if (!permisosList.isEmpty()) {
					Iterator permisos = permisosList.iterator();
					while (permisos.hasNext()) {
						ItemPermiso permiso = (ItemPermiso) permisos.next();
						result.add(permiso);
					}
				}
			}
		}

		List submenusList = submenu.getSubmenues();
		if (!submenusList.isEmpty()) {
			Iterator submenus = submenusList.iterator();
			while (submenus.hasNext()) {
				SubMenu aux = (SubMenu) submenus.next();
				result = contatenaListas(generarPermisos(aux), result);
			}
		}

		return result;
	}


	public List contatenaListas(List list1, List list2) {
		Iterator iterator1 = list1.iterator();
		while (iterator1.hasNext()) {
			Object obj = (Object) iterator1.next();
			list2.add(obj);
		}

		return list2;
	}


	public List eliminarRepetidos(List lista) {
		List result = new ArrayList();

		if (!lista.isEmpty()) {
			Iterator listaIter = lista.iterator();
			while (listaIter.hasNext()) {
				ItemPermiso item = (ItemPermiso) listaIter.next();
				boolean encontrado = false;
				if (!result.isEmpty()) {
					Iterator iter = result.iterator();
					while (iter.hasNext() && !encontrado) {
						ItemPermiso aux = (ItemPermiso) iter.next();
						if (item.getId().equals(aux.getId())) {
							encontrado = true;
						}
					}
				}

				if (!encontrado) {
					result.add(item);
				}

			}
		}

		return result;
	}


	public List getPermisosSeleccionados() {
		return permisosSeleccionados;
	}


	public void setPermisosSeleccionados(List permisosSeleccionados) {
		this.permisosSeleccionados = permisosSeleccionados;
	}

}

package com.bizitglobal.webapp.faces;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.tree.DefaultMutableTreeNode;
import org.apache.myfaces.custom.tree.HtmlTree;
import org.apache.myfaces.custom.tree.event.TreeSelectionEvent;
import org.apache.myfaces.custom.tree.event.TreeSelectionListener;

import com.bizitglobal.webapp.xml.Item;
import com.bizitglobal.webapp.xml.ItemPermiso;
import com.bizitglobal.webapp.xml.Menu;
import com.bizitglobal.webapp.xml.MenuAdmin;
import com.bizitglobal.webapp.xml.SubMenu;


public class TreeListener implements TreeSelectionListener {
	private Logger log = Logger.getLogger(TreeListener.class);


	public void valueChanged(TreeSelectionEvent arg0) {
		HtmlTree tree = (HtmlTree) arg0.getComponent();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

		log.info("Buscando nodo...");

		log.info("Padre==" + node.getParent().toString());

		Object nodo = null;
		if (!node.toString().equals("Menu")) {
			nodo = getObjectMenu(MenuAdmin.getInstance(), node);
		}

		if (nodo instanceof SubMenu) {
			SubMenu submenu = (SubMenu) nodo;
			log.info("Nombre SubMenu:" + submenu.getNombre());

		}

		if (nodo instanceof Item) {
			Item item = (Item) nodo;
			log.info("Nombre Item:" + item.getNombre());
			List permisosList = item.getItemPermisos();
			if (!permisosList.isEmpty()) {
				Iterator permisos = permisosList.iterator();
				while (permisos.hasNext()) {
					log.info(((ItemPermiso) permisos.next()).toString());
				}
			}
		}

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
}

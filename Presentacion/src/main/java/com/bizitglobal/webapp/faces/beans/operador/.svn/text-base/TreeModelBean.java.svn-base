package com.bizitglobal.webapp.faces.beans.operador;

import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree.DefaultMutableTreeNode;
import org.apache.myfaces.custom.tree.model.DefaultTreeModel;

import com.bizitglobal.webapp.xml.Item;
import com.bizitglobal.webapp.xml.Menu;
import com.bizitglobal.webapp.xml.MenuAdmin;
import com.bizitglobal.webapp.xml.SubMenu;


@SuppressWarnings({"rawtypes"})
public class TreeModelBean {

	public DefaultTreeModel getTreeData() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu");

		Menu menu = MenuAdmin.getInstance();

		Iterator submenues = menu.getSubmenues().iterator();
		while (submenues.hasNext()) {
			SubMenu auxSubMenu = (SubMenu) submenues.next();
			root.insert(this.generateDefaultMutableTreeNode(auxSubMenu));
		}

		return new DefaultTreeModel(root);
	}


	private DefaultMutableTreeNode generateDefaultMutableTreeNode(SubMenu submenu) {
		DefaultMutableTreeNode result = new DefaultMutableTreeNode(submenu);

		List itemsList = submenu.getItems();
		if (!itemsList.isEmpty()) {
			Iterator items = itemsList.iterator();
			while (items.hasNext()) {
				result.insert(new DefaultMutableTreeNode((Item) items.next()));
			}
		}

		List submenuesList = submenu.getSubmenues();
		if (!submenuesList.isEmpty()) {
			Iterator submenues = submenuesList.iterator();
			while (submenues.hasNext()) {
				result.insert(this.generateDefaultMutableTreeNode((SubMenu) submenues.next()));
			}
		}

		return result;
	}

}

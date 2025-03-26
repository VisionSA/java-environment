package com.bizitglobal.webapp.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


@SuppressWarnings({"rawtypes","unchecked"})
public class Menu {
	private List submenues = null;


	public Menu() {
		submenues = new ArrayList();

		SAXBuilder builder = new SAXBuilder(false);
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/menu-permisos.xml");

			Document doc = builder.build(path);

			Element raiz = doc.getRootElement();
			List submenuesList = raiz.getChildren("sub-menu");
			Iterator submenues = submenuesList.iterator();
			while (submenues.hasNext()) {
				Element submenu = (Element) submenues.next();
				this.submenues.add(this.generateSubMenu(submenu));
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JDOMException e2) {
			e2.printStackTrace();
		}
	}


	public Menu(List submenues) {
		super();
		this.submenues = submenues;
	}


	public List getSubmenues() {
		return submenues;
	}


	public void setSubmenues(List submenues) {
		this.submenues = submenues;
	}


	private SubMenu generateSubMenu(Element elemento) {
		SubMenu result = new SubMenu();
		result.setNombre(elemento.getAttributeValue("nombre"));

		List itemsList = elemento.getChildren("item");
		if (!itemsList.isEmpty()) {
			Iterator items = itemsList.iterator();
			while (items.hasNext()) {
				Element item = (Element) items.next();
				Item auxItem = new Item();
				auxItem.setNombre(item.getAttributeValue("nombre"));
				List permisosList = item.getChildren("item-permiso");
				if (!permisosList.isEmpty()) {
					Iterator permisos = permisosList.iterator();
					while (permisos.hasNext()) {
						Element permiso = (Element) permisos.next();
						ItemPermiso auxPermiso = new ItemPermiso();
						auxPermiso.setId(new Integer(permiso.getAttributeValue("id")));
						auxPermiso.setClase(permiso.getAttributeValue("clase"));
						auxPermiso.setMetodo(permiso.getAttributeValue("metodo"));
						auxPermiso.setDescripcion(permiso.getAttributeValue("descripcion"));
						auxItem.getItemPermisos().add(auxPermiso);
					}

				}

				result.getItems().add(auxItem);
			}
		}

		List submenuesList = elemento.getChildren("sub-menu");
		if (!submenuesList.isEmpty()) {
			Iterator submenues = submenuesList.iterator();
			while (submenues.hasNext()) {
				Element submenu = (Element) submenues.next();
				result.getSubmenues().add(this.generateSubMenu(submenu));
			}
		}

		return result;
	}

}

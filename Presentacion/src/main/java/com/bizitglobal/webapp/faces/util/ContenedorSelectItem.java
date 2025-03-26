package com.bizitglobal.webapp.faces.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.faces.model.SelectItem;


@SuppressWarnings({"rawtypes","unchecked"})
public class ContenedorSelectItem {
	private Vector contenedor;
	private Vector ids;


	public ContenedorSelectItem() {
		contenedor = new Vector();
		ids = new Vector();
	}


	public void cargarContedor(List listaSession) {
		for (int i = 0; i < contenedor.size(); i++) {
			if (!contenedor.elementAt(i).equals(listaSession)) {
				contenedor.add(listaSession);
				ids.add(getVector(listaSession));
			}
		}
	}


	private int getPosicionVector(List listaSession) {
		int result = 0;
		boolean encontrado = false;

		for (int i = 0; i < contenedor.size(); i++) {
			List contenedorList = (List) contenedor.elementAt(i);
			if (!contenedorList.isEmpty()) {
				Iterator contenedorIter = contenedorList.iterator();
				while (contenedorIter.hasNext() && !encontrado) {
					SelectItem item1 = (SelectItem) contenedorIter.next();
					if (!listaSession.isEmpty()) {
						Iterator lista = listaSession.iterator();
						while (lista.hasNext() && !encontrado) {
							SelectItem item2 = (SelectItem) lista.next();
							if (item1.getLabel().equals(item2.getLabel())) {
								result = i;
								encontrado = true;
							}
						}
					}
				}
			}
		}

		return result;
	}


	private Integer[] getVector(List listaSession) {
		List lista = (List) contenedor.elementAt(getPosicionVector(listaSession));
		Integer[] result = new Integer[lista.size()];
		if (!lista.isEmpty()) {
			Iterator listaIter = lista.iterator();
			int i = 0;
			while (listaIter.hasNext()) {
				SelectItem item = (SelectItem) listaIter.next();
				result[i] = new Integer(item.getValue().toString());
				i++;
			}
		}

		return result;
	}


	public Vector getIds(List listaSession) {
		Integer[] result = (Integer[]) ids.elementAt(getPosicionVector(listaSession));

		return ids;
	}


	public void setIds(Vector ids) {
		this.ids = ids;
	}

}

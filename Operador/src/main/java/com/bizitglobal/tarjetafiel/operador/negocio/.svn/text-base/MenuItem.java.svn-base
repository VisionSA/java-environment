package com.bizitglobal.tarjetafiel.operador.negocio;

import java.util.HashSet;
import java.util.Set;

public class MenuItem implements Comparable {
	private Long idMenuItem;
	private String idItem;
	private String label;
	private String icon;
	private String action;
	private String actionListener;
	private String split;
	private String esPadre;
	private Set hijos;
	private Pagina pagina;
	
	public MenuItem() {
		this(null,null,null,null,null,null,"N","N",new HashSet(),null);
	}
	
	public MenuItem(Long idMenuItem, String idItem, String label, String icon, 
			String action, String actionListener, String split, String esPadre, Set hijos, Pagina pag) {
		super();
		this.idMenuItem = idMenuItem;
		this.idItem = idItem;
		this.label = label;
		this.icon = icon;
		this.action = action;
		this.actionListener = actionListener;
		this.split = split;
		this.esPadre = esPadre;
		this.hijos = hijos;
		this.pagina = pag;
	}





	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getActionListener() {
		return actionListener;
	}

	public void setActionListener(String actionListener) {
		this.actionListener = actionListener;
	}

	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIdItem() {
		return idItem;
	}
	
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	
	public Long getIdMenuItem() {
		return idMenuItem;
	}
	
	public void setIdMenuItem(Long idMenuItem) {
		this.idMenuItem = idMenuItem;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getSplit() {
		return split;
	}
	
	public void setSplit(String split) {
		this.split = split;
	}

	public String getEsPadre() {
		return esPadre;
	}

	public void setEsPadre(String esPadre) {
		this.esPadre = esPadre;
	}
	
	public boolean padre() {
		return (esPadre.equals("S")) ? true : false;
	}
	
	public int compareTo(Object unMenuItem) {
		MenuItem item = (MenuItem)unMenuItem;
		return this.idMenuItem.compareTo(item.getIdMenuItem());
	}

	public Set getHijos() {
		return hijos;
	}

	public void setHijos(Set hijos) {
		this.hijos = hijos;
	}
	
	public boolean equals(Object unObject) {
		boolean result = false;
		if(unObject instanceof MenuItem) {
			MenuItem menuItem = (MenuItem)unObject;
			if(menuItem.idMenuItem.equals(this.idMenuItem)) {
				result = true;
			}
		}
		
		return result;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
	
}

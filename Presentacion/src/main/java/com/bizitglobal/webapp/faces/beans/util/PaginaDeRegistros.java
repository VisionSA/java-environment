package com.bizitglobal.webapp.faces.beans.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;


/**
 * Autor Krumrick, Waldemar
 * */
@SuppressWarnings({"rawtypes","unchecked"})
public class PaginaDeRegistros {

	private List listaDeContenidos;
	private int cantRegistrosADevolver;
	private boolean haySiguiente;
	private boolean hayAnterior;
	private int cantidadPaginas;
	private int cantidadDeRegistrosTotales;
	private int resto;
	private int paginaActual;
	private String estado;
	private List comboDePaginas; // una lista de select items para guardar las páginas
	// objetos para trabajar con el select item de paginador
	private Long idPaginaSeleccionada;
	private HtmlSelectOneMenu pagSeleccionada;


	public PaginaDeRegistros() {
		estado = "";
	}


	/**
	 * Esta clase recibe una lista con objetos, la subdivide en los grupos que sea necesarios para devolver en cada pagina la cantidad de
	 * cantRegistrosADevolver especificados en el constructor
	 * */
	public PaginaDeRegistros(int cantRegistrosADevolver, List listaDeContenidos) {
		this.cantRegistrosADevolver = cantRegistrosADevolver;
		this.listaDeContenidos = listaDeContenidos;
		cantidadDeRegistrosTotales = listaDeContenidos.size();
		resto = cantidadDeRegistrosTotales % cantRegistrosADevolver;
		cantidadPaginas = ((cantidadDeRegistrosTotales - resto) / cantRegistrosADevolver) + 1;
		comboDePaginas = new ArrayList();
		for (int i = 1; i < (cantidadPaginas + 1); i++) {
			comboDePaginas.add(new SelectItem(new Long(i), String.valueOf(i)));
		}
		estado = "";
		pagSeleccionada = new HtmlSelectOneMenu();
		pagSeleccionada.setValue(new Long(1));
		idPaginaSeleccionada = new Long(1);
	}


	/**
	 * Llamar a este método luego de borrar algun elemento del paginador.... y luego inmediatemente llamar a getPagina(paginador.getPaginaActual());
	 * */
	public void reconstruirPaginador() {
		cantidadDeRegistrosTotales = listaDeContenidos.size();
		resto = cantidadDeRegistrosTotales % cantRegistrosADevolver;
		cantidadPaginas = ((cantidadDeRegistrosTotales - resto) / cantRegistrosADevolver) + 1;
		comboDePaginas = new ArrayList();
		for (int i = 1; i < (cantidadPaginas + 1); i++) {
			comboDePaginas.add(new SelectItem(new Long(i), String.valueOf(i)));
		}
		estado = "";
		Long paginaEnQueEstaba = idPaginaSeleccionada;
		pagSeleccionada = new HtmlSelectOneMenu();
		if (paginaEnQueEstaba.intValue() < (cantidadPaginas + 1)) {
			pagSeleccionada.setValue(paginaEnQueEstaba);
			idPaginaSeleccionada = paginaEnQueEstaba;
			paginaActual = paginaEnQueEstaba.intValue();
		} else {
			pagSeleccionada.setValue(new Long(paginaEnQueEstaba.intValue() - 1));
			idPaginaSeleccionada = new Long(paginaEnQueEstaba.intValue() - 1);
			paginaActual = paginaEnQueEstaba.intValue() - 1;
		}
	}


	public List getPagina(int i) {
		if (i == cantidadPaginas) {
			haySiguiente = false;
		} else {
			haySiguiente = true;
		}
		if (i > 1) {
			hayAnterior = true;
		} else {
			hayAnterior = false;
		}
		if (i < 1 || i > cantidadPaginas) {
			return null;
		} else {
			idPaginaSeleccionada = new Long(i);
			pagSeleccionada.setValue(new Long(i));
			paginaActual = i;
		}
		List listaResultante = new ArrayList();
		int tope = (i) * cantRegistrosADevolver;
		if (tope > cantidadDeRegistrosTotales)
			tope = cantidadDeRegistrosTotales;
		for (int j = ((i - 1) * cantRegistrosADevolver); j < tope; j++) {
			listaResultante.add(listaDeContenidos.get(j));
		}
		estado = " de " + cantidadPaginas;
		return listaResultante;
	}


	public List getPrimeraPagina() {
		paginaActual = 1;
		return getPagina(paginaActual);
	}


	public List getUltimaPagina() {
		paginaActual = cantidadPaginas;
		return getPagina(paginaActual);
	}


	public List getPaginaSiguiente() {
		if (haySiguiente) {
			paginaActual++;
			return getPagina(paginaActual);
		}
		return null;
	}


	public List getPaginaAnterior() {
		if (hayAnterior) {
			paginaActual--;
			return getPagina(paginaActual);
		}
		return null;
	}


	public int getCantidadDeRegistrosTotales() {
		return cantidadDeRegistrosTotales;
	}


	public int getCantidadPaginas() {
		return cantidadPaginas;
	}


	public boolean isHayAnterior() {
		return hayAnterior;
	}


	public boolean isHaySiguiente() {
		return haySiguiente;
	}


	public List getListaDeContenidos() {
		return listaDeContenidos;
	}


	public int getPaginaActual() {
		return paginaActual;
	}


	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List getComboDePaginas() {
		return comboDePaginas;
	}


	public void setComboDePaginas(List comboDePaginas) {
		this.comboDePaginas = comboDePaginas;
	}


	public Long getIdPaginaSeleccionada() {
		return idPaginaSeleccionada;
	}


	public void setIdPaginaSeleccionada(Long idPaginaSeleccionada) {
		this.idPaginaSeleccionada = idPaginaSeleccionada;
	}


	public HtmlSelectOneMenu getPagSeleccionada() {
		return pagSeleccionada;
	}


	public void setPagSeleccionada(HtmlSelectOneMenu pagSeleccionada) {
		this.pagSeleccionada = pagSeleccionada;
	}

}

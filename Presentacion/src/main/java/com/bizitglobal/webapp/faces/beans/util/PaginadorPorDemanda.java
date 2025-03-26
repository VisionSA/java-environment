package com.bizitglobal.webapp.faces.beans.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.webapp.faces.beans.error.ErrorBean;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * Autor Krumrick, Waldemar
 * */
@SuppressWarnings({"rawtypes","unchecked"})
public class PaginadorPorDemanda {

	private Page page;
	private Paginacion paginacion;
	private Filtro filtro;
	private List list;
	private int cantRegistros;
	private ErrorBean error;
	private String redireccion;
	private HtmlSelectOneMenu pagSeleccionada = new HtmlSelectOneMenu();;
	private List comboDePaginas = new ArrayList(); // una lista de select items para guardar las páginas
	// objetos para trabajar con el select item de paginador
	private String estado;


	public PaginadorPorDemanda(Filtro filtro, Paginacion paginacion, List list, int cantRegistros, ErrorBean error, String redireccion) {
		estado = "";
		this.redireccion = redireccion;
		this.error = error;
		this.cantRegistros = cantRegistros;
		this.filtro = filtro;
		this.list = list;
		this.paginacion = paginacion;
		leerPagina(0, cantRegistros);
	}


	private void leerPagina(int nroPagina, int cantRegistros) {
		try {
			error.borrar();

			page = paginacion.getPage(filtro, nroPagina, cantRegistros);
			list.clear();
			list.addAll(page.getThisPageElements());
			pagSeleccionada.setValue(new Long(nroPagina));
			int cantidadPaginas = page.getLastPageNumber() + 1;
			estado = " de " + cantidadPaginas;
			comboDePaginas.clear();
			for (int i = 0; i < (cantidadPaginas); i++) {
				comboDePaginas.add(new SelectItem(new Long(i), String.valueOf(i + 1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un problema al leer la lista da objetos.");
		}
		if (redireccion != null) {
			Session.redirect(redireccion);
		}
	}


	public void cargarPagina(ValueChangeEvent e) {
		int pag = new Integer(pagSeleccionada.getValue().toString()).intValue();
		leerPagina(pag, cantRegistros);
	}


	public String primeraPagina() {
		leerPagina(0, cantRegistros);
		return "";
	}


	public String ultimaPagina() {
		leerPagina(page.getLastPageNumber(), cantRegistros);
		return "";
	}


	public String paginaSiguiente() {
		leerPagina(page.getNextPageNumber(), cantRegistros);
		return "";
	}


	public String paginaAnterior() {
		leerPagina(page.getPreviousPageNumber(), cantRegistros);
		return "";
	}


	public boolean isHayAnterior() {
		return page.hasPreviousPage();
	}


	public boolean isHaySiguiente() {
		return page.hasNextPage();
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
		return new Long(page.getPageNumber());
	}


	public void setIdPaginaSeleccionada(Long idPaginaSeleccionada) {
		// this.idPaginaSeleccionada = idPaginaSeleccionada;
	}


	public HtmlSelectOneMenu getPagSeleccionada() {
		return pagSeleccionada;
	}


	public void setPagSeleccionada(HtmlSelectOneMenu pagSeleccionada) {
		this.pagSeleccionada = pagSeleccionada;
	}

}

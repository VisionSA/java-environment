package com.bizitglobal.webapp.faces.beans.impuestos.wrappers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.webapp.faces.util.Convertidores;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImpuestoTabla {
	private Long idImpuestoTabla;
	private TipoImpuesto tipoImpuesto;
	private List impuestoItems;
	private List listaExclusiones;
	private Long impuestoSeleccionado;


	public ImpuestoTabla() {
		this(null, null, new ArrayList(), new ArrayList(), null);
	}


	public ImpuestoTabla(Long idImpuestoTabla, TipoImpuesto tipoImpuesto, List impuestoItems, List listaExclusiones, Long impuestoSeleccionado) {
		super();
		this.idImpuestoTabla = idImpuestoTabla;
		this.tipoImpuesto = tipoImpuesto;
		this.impuestoItems = impuestoItems;
		this.listaExclusiones = listaExclusiones;
		this.impuestoSeleccionado = impuestoSeleccionado;
	}


	public ImpuestoTabla(TipoImpuesto tipoImpuesto, List listaExclusiones) {
		super();
		this.idImpuestoTabla = tipoImpuesto.getIdTipoImpuesto();
		this.tipoImpuesto = tipoImpuesto;
		this.impuestoItems = getCategSelectItems(Convertidores.setToList(tipoImpuesto.getCategorias()));
		this.listaExclusiones = new ArrayList(listaExclusiones);
		this.impuestoSeleccionado = new Long(0);
	}


	public List getImpuestoItems() {
		return impuestoItems;
	}


	public void setImpuestoItems(List impuestoItems) {
		this.impuestoItems = impuestoItems;
	}


	public Long getIdImpuestoTabla() {
		return idImpuestoTabla;
	}


	public void setIdImpuestoTabla(Long idImpuestoTabla) {
		this.idImpuestoTabla = idImpuestoTabla;
	}


	public Long getImpuestoSeleccionado() {
		return impuestoSeleccionado;
	}


	public void setImpuestoSeleccionado(Long impuestoSeleccionado) {
		this.impuestoSeleccionado = impuestoSeleccionado;
	}


	public List getListaExclusiones() {
		return listaExclusiones;
	}


	public void setListaExclusiones(List listaExclusiones) {
		this.listaExclusiones = listaExclusiones;
	}


	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}


	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof ImpuestoTabla) {
			ImpuestoTabla temp = (ImpuestoTabla) obj;
			if (this.idImpuestoTabla.equals(temp.getIdImpuestoTabla())) {
				result = true;
			}
		}

		return result;
	}


	public List getCategSelectItems(List categorias) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione"));
		if (!categorias.isEmpty()) {
			Iterator iter = categorias.iterator();
			while (iter.hasNext()) {
				SelectItem item = new SelectItem();
				Categoria categoria = (Categoria) iter.next();
				item.setValue(categoria.getIdCategoria());
				item.setLabel(categoria.getDescripcion() + "-" + categoria.getCodCategoria());
				result.add(item);
			}
		}

		return result;
	}
}

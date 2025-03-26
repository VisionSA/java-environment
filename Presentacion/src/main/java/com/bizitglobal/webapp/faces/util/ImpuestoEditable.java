package com.bizitglobal.webapp.faces.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCategoria;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercioActividad;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImpuestoEditable {
	private TipoImpuesto tipoImpuesto;
	private List categorias;
	private Long categoriaSeleccionada;
	private List jurisdicciones;
	private Long jurisSeleccionada;
	private List jurisActividades;
	private Long jurisActividadSeleccionada;
	// Id del objeto relacional entre proveedor y categorias de impuestos.
	private ProveedorCategoria proveedorCategoria;
	private CodComercioActividad codComercioActividad;
	private boolean boolActi = true;
	private JurisdiccionActividadDao jurisdiccionActividadDao;


	public ImpuestoEditable() {
		this(null, new ArrayList(), new Long(0), new ArrayList(), new Long(0));
	}


	public ImpuestoEditable(TipoImpuesto tipoImpuesto, List categorias, Long categoriaSeleccionada, List jurisdicciones, Long jurisSeleccionada) {
		super();
		this.tipoImpuesto = tipoImpuesto;
		this.categorias = categorias;
		this.categoriaSeleccionada = categoriaSeleccionada;
		this.jurisSeleccionada = jurisSeleccionada;
		this.jurisdicciones = jurisdicciones;
		this.proveedorCategoria = new ProveedorCategoria();
		jurisActividades = new ArrayList();
		jurisActividades.add(new SelectItem(new Long(0), "Seleccione"));
	}


	public ImpuestoEditable(TipoImpuesto tipoImpuesto, JurisdiccionActividadDao jurisdiccionActividadDao, String tipo) {
		super();
		this.tipoImpuesto = tipoImpuesto;
		this.categorias = getCategSelectItems(Convertidores.setToList(tipoImpuesto.getCategorias()));
		this.categoriaSeleccionada = new Long(0);
		this.jurisSeleccionada = new Long(0);
		this.jurisActividadSeleccionada = new Long(0);
		cargarJurisItems(tipoImpuesto);
		if (tipo.equals("prov"))
			this.proveedorCategoria = new ProveedorCategoria();
		else
			this.codComercioActividad = new CodComercioActividad();
		this.jurisdiccionActividadDao = jurisdiccionActividadDao;
		jurisActividades = new ArrayList();
		jurisActividades.add(new SelectItem(new Long(0), "Seleccione"));
	}


	public boolean getBoolActi() {
		return boolActi;
	}


	public void setBoolActi(boolean boolActi) {
		this.boolActi = boolActi;
	}


	public List getJurisActividades() {
		return jurisActividades;
	}


	public void setJurisActividades(List jurisActividades) {
		this.jurisActividades = jurisActividades;
	}


	public Long getJurisActividadSeleccionada() {
		return jurisActividadSeleccionada;
	}


	public void setJurisActividadSeleccionada(Long jurisActividadSeleccionada) {
		if (boolActi) {
			this.jurisActividadSeleccionada = new Long(0);
		} else {
			this.jurisActividadSeleccionada = jurisActividadSeleccionada;
		}
	}


	public List getJurisdicciones() {
		return jurisdicciones;
	}


	public void setJurisdicciones(List jurisdicciones) {
		this.jurisdicciones = jurisdicciones;
	}


	public Long getJurisSeleccionada() {
		return jurisSeleccionada;
	}


	public void setJurisSeleccionada(Long jurisSeleccionada) {
		this.jurisSeleccionada = jurisSeleccionada;
	}


	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}


	public List getCategorias() {
		return categorias;
	}


	public void setCategorias(List categorias) {
		this.categorias = categorias;
	}


	public Long getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}


	public void setCategoriaSeleccionada(Long categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}


	// public String getTipo() {
	// return tipoImpuesto.getDescripcion();
	// }
	//
	// public void setTipo(String tipo) {
	// this.tipoImpuesto.setDescripcion(tipo);
	// }

	public ProveedorCategoria getProveedorCategoria() {
		return proveedorCategoria;
	}


	public void setProveedorCategoria(ProveedorCategoria proveedorCategoria) {
		this.proveedorCategoria = proveedorCategoria;
	}


	public String buscarActividadesComercio() {
		jurisActividades = new ArrayList();
		jurisActividades.add(new SelectItem(new Long(0), "Seleccione"));
		if (!categoriaSeleccionada.equals(new Long(0)) && !jurisSeleccionada.equals(new Long(0))) {
			Filtro filtro = new Filtro("jurisdiccion.idJurisdiccion", Filtro.IGUAL, jurisSeleccionada);
			filtro.agregarCampoOperValor("categoria.idCategoria", Filtro.IGUAL, categoriaSeleccionada);
			filtro.agregarCampoOperValor("aplicable.idAplicable", Filtro.IGUAL, "2");
			List jActividad = jurisdiccionActividadDao.listarTodos(filtro);
			if (!jActividad.isEmpty()) {
				jurisActividades.addAll(Util.cargarSelectItem(jActividad));
				boolActi = false;
			}
		} else {
			setJurisActividadSeleccionada(new Long(0));
			boolActi = true;
		}
		return "";
	}


	public String buscarActividades() {
		jurisActividades = new ArrayList();
		jurisActividades.add(new SelectItem(new Long(0), "Seleccione"));
		if (!categoriaSeleccionada.equals(new Long(0)) && !jurisSeleccionada.equals(new Long(0))) {
			Filtro filtro = new Filtro("jurisdiccion.idJurisdiccion", Filtro.IGUAL, jurisSeleccionada);
			filtro.agregarCampoOperValor("categoria.idCategoria", Filtro.IGUAL, categoriaSeleccionada);
			filtro.agregarCampoOperValor("aplicable.idAplicable", Filtro.IGUAL, "1");
			List jActividad = jurisdiccionActividadDao.listarTodos(filtro);
			if (!jActividad.isEmpty()) {
				jurisActividades.addAll(Util.cargarSelectItem(jActividad));
				boolActi = false;
			}
		} else {
			setJurisActividadSeleccionada(new Long(0));
			boolActi = true;
		}
		return "";
	}


	public void cambio(ValueChangeEvent event) {
		boolActi = true;
	}


	public List getCategSelectItems(List categorias) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "-"));
		if (!categorias.isEmpty()) {
			Iterator iter = categorias.iterator();
			while (iter.hasNext()) {
				SelectItem item = new SelectItem();
				Categoria categoria = (Categoria) iter.next();
				item.setValue(categoria.getIdCategoria());
				item.setLabel(categoria.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public void cargarJurisItems(TipoImpuesto tipoImpuesto) {
		jurisdicciones = new ArrayList();
		jurisdicciones.add(new SelectItem(new Long(0), "Seleccione"));
		if (!tipoImpuesto.getJurisTipoImp().isEmpty()) {
			Iterator iter = tipoImpuesto.getJurisTipoImp().iterator();
			while (iter.hasNext()) {
				// Cargo la lista de jurisdicciones
				JurisTipoImpuesto jurisTipoImpuesto = (JurisTipoImpuesto) iter.next();
				Jurisdiccion jurisdiccion = jurisTipoImpuesto.getJurisdiccion();
				jurisdicciones.add(new SelectItem(jurisdiccion.getIdJurisdiccion(), jurisdiccion.getDescripcion()));
			}
		}
	}


	public boolean tieneCategoria(Categoria categoria) {
		if (categoria != null) {
			if (!categorias.isEmpty()) {
				Iterator iter = categorias.iterator();
				while (iter.hasNext()) {
					SelectItem selectItem = (SelectItem) iter.next();
					if (categoria.getIdCategoria().equals(selectItem.getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}


	public CodComercioActividad getCodComercioActividad() {
		return codComercioActividad;
	}


	public void setCodComercioActividad(CodComercioActividad codComercioActividad) {
		this.codComercioActividad = codComercioActividad;
	}

	// public Categoria getCategoria() {
	// Categoria result = null;
	//
	// if(!categoriaSeleccionada.equals(new Long(0))) {
	// if(!categorias.isEmpty()) {
	// Iterator iter = categorias.iterator();
	// while(iter.hasNext()) {
	// Categoria aux = (Categoria)iter.next();
	// if(aux.getIdCategoria().equals(categoriaSeleccionada)) {
	// result = aux;
	// }
	// }
	// }
	// }
	//
	// return result;
	// }

}

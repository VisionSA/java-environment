package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.service.CategoriaService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CategoriaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CategoriaBean.class);
	private Categoria categoria;
	private String nombreFiltro;
	private Long idCategoriaHidden;
	private String idCategoria;
	private String codCategoria;
	private List unaCategoria;
	// definicion de un list del objeto base
	private List categoriaList;

	// Listas para la presentacion(HtmlSelectItems).
	private List tipoImpuestoList = new ArrayList();
	private List tipoImpuestoItems = new ArrayList();

	// Objetos Relacionados.
	private Long idTipoImpuestoSeleccionada;

	private String focoHidden;


	public CategoriaBean() {
		super();
		borrar();
		try {
			try {
				log.info("Trayendo Tipos de Domicilio");
				tipoImpuestoList = impuestoService.getTipoImpuestoService().getTipoImpuesto(new Filtro());
				log.info("Size: " + tipoImpuestoList.size());
			} catch (TipoImpuestoException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Long getIdCategoriaHidden() {
		return idCategoriaHidden;
	}


	public void setIdCategoriaHidden(Long idCategoriaHidden) {
		this.idCategoriaHidden = idCategoriaHidden;
	}


	public List getTipoImpuestoItems() {
		return tipoImpuestoItems;
	}


	public void setTipoImpuestoItems(List tipoImpuestoItems) {
		this.tipoImpuestoItems = tipoImpuestoItems;
	}


	public Long getIdTipoImpuestoSeleccionada() {
		return idTipoImpuestoSeleccionada;
	}


	public void setIdTipoImpuestoSeleccionada(Long idTipoImpuestoSeleccionada) {
		this.idTipoImpuestoSeleccionada = idTipoImpuestoSeleccionada;
	}


	public List getCategoriaList() {
		return categoriaList;
	}


	public void setCategoriaList(List object) {
		this.categoriaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CATEGORIA
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		cargarItems();
		return "amCategoria";
	}


	private void cargarItems() {

		if ((tipoImpuestoItems.size() - 1) != tipoImpuestoList.size()) {
			log.info("Ingresando dentro de if cargarItems");
			tipoImpuestoItems = new ArrayList();
			tipoImpuestoItems.add(new SelectItem(new Long(0), "Seleccionar Tipo Impuesto"));
			tipoImpuestoItems.addAll(Util.cargarSelectItem(tipoImpuestoList));
		}
	}


	public String editarCategoria() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Categoría";
		try {
			categoria = impuestoService.getCategoriaService().leerCategoria(idCategoriaHidden);
			idTipoImpuestoSeleccionada = categoria.getTipoImpuesto().getIdTipoImpuesto();

			result = "amCategoria";
		} catch (CategoriaException e1) {
			error.agregar("Ocurrio un error al intentar editar el categoria");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarCategoria.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el categoria");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarCategoria.jsf");
		}
		return result;
	}


	public String eliminarCategoria() {
		try {
			impuestoService.getCategoriaService().borrarCategoria(idCategoriaHidden);
			categoriaList.remove(new Categoria(idCategoriaHidden));
		} catch (CategoriaException e1) {
			error.agregar("Imposible borrar el categoria. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el categoria");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarCategoria.jsf");
		return null;
	}


	public String grabar() {
		try {
			categoria.setCodCategoria(categoria.getCodCategoria().trim());
			categoria.setDescripcion(categoria.getDescripcion().trim());
			CategoriaService categoriaService = impuestoService.getCategoriaService();
			categoria.setTipoImpuesto((TipoImpuesto) Util.buscarElemento(tipoImpuestoList, new TipoImpuesto(idTipoImpuestoSeleccionada)));
			if (validar()) {
				if (alta) {
					categoriaService.grabarCategoria(categoria);
				}
				else {
					categoriaService.actualizarCategoria(categoria);
				}
				categoria.setDescripcion("");
				idTipoImpuestoSeleccionada = new Long(0);
				categoria.setCodCategoria("");
				popup.setPopup(popup.ICONO_OK, "La Categoría ha sido almacenado exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CategoriaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Categoría.");
			e1.printStackTrace();
		} catch (CategoriaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Categoría.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Categoría.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Categoría";
		popup.borrar();
		try {
			unaCategoria = impuestoService.getCategoriaService().getCategorias(new Filtro());
		} catch (CategoriaException e) {
			e.printStackTrace();
		}
		categoria = new Categoria();
		categoriaList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (categoria.getCodCategoria() == null || categoria.getCodCategoria().equals(""))
			error.agregar(Error.AMCATEGORIA_CODIGOCATEGORIA_REQUERIDA);
		if (idTipoImpuestoSeleccionada == null || idTipoImpuestoSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMCATEGORIA_TIPOIMPUESTO_REQUERIDO);
		if (alta) {
			if (!unaCategoria.isEmpty()) {
				Iterator iterator = unaCategoria.iterator();
				while (iterator.hasNext()) {
					Categoria element = (Categoria) iterator.next();
					if (element.getDescripcion().compareTo(categoria.getDescripcion()) == 0
							&& element.getTipoImpuesto().getIdTipoImpuesto().equals(idTipoImpuestoSeleccionada)) {
						error.agregar(Error.AMCATEGORIA_CATEGORIA_REPETIDA);
						return false;
					}
				}
			}
		} else {
			if (!unaCategoria.isEmpty()) {
				Iterator iterator = unaCategoria.iterator();
				while (iterator.hasNext()) {
					Categoria element = (Categoria) iterator.next();
					if (element.getDescripcion().compareTo(categoria.getDescripcion()) == 0
							&& element.getTipoImpuesto().getIdTipoImpuesto().equals(idTipoImpuestoSeleccionada)
							&& !element.getIdCategoria().equals(idCategoriaHidden)) {
						error.agregar(Error.AMCATEGORIA_CATEGORIA_REPETIDA);
						return false;
					}
				}
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCategoria() {
		return inicializar();
	}


	public String irAModificarCategoria() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Categoría";
		return null;
	}


	public String irAListarCategoria() {
		borrar();
		tituloCorto = "Listado de Categoría";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarCategoria.jsf");
		return "";
	}


	public String listarCategoria() {
		categoriaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idCategoria != null && !idCategoria.equals(""))
				filtro.agregarCampoOperValor("idCategoria", Filtro.IGUAL, new Long(idCategoria));
			if (categoria.getCodCategoria() != null && !categoria.getCodCategoria().equals(""))
				filtro.agregarCampoOperValor("codCategoria", Filtro.LIKE, categoria.getCodCategoria());
			if (categoria.getDescripcion() != null && !categoria.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, categoria.getDescripcion());
			if (idTipoImpuestoSeleccionada != null && !idTipoImpuestoSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("tipoImpuesto.idTipoImpuesto", Filtro.IGUAL, idTipoImpuestoSeleccionada);

			categoriaList = impuestoService.getCategoriaService().getCategorias(filtro);
			Iterator iter = categoriaList.iterator();
			while (iter.hasNext())
			{
				Categoria element = (Categoria) iter.next();
				element.getTipoImpuesto().toString();
			}
			idCategoria = "";
			categoria.setCodCategoria("");
			categoria.setDescripcion("");
			idTipoImpuestoSeleccionada = new Long(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarCategoria.jsf");
		return null;
	}


	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getCodCategoria() {
		return codCategoria;
	}


	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}
}

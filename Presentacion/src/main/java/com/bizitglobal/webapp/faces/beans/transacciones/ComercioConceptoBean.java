package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioConceptoDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioConcepto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioConceptoService;


@SuppressWarnings({"rawtypes"})
public class ComercioConceptoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ComercioConceptoBean.class);
	private ComercioConcepto comercioconcepto;
	private String nombreFiltro;
	private Long idComercioConceptoHidden;

	// definicion de un list del objeto base
	private List comercioconceptoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List comercioItems = new ArrayList();

	private List conceptoList = new ArrayList();
	private List conceptoItems = new ArrayList();

	// Objetos Relacionados.
	private Long idComercioSeleccionada;
	private Long idConceptoSeleccionada;

	private String focoHidden;


	public ComercioConceptoBean() {
		super();
		borrar();
		try {
			conceptoList = transaccionesService.getConceptoService().getConcepto(new Filtro());
		} catch (ConceptoException e1) {
			e1.printStackTrace();
		}
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public ComercioConcepto getComercioConcepto() {
		return comercioconcepto;
	}


	public void setComercioConcepto(ComercioConcepto comercioconcepto) {
		this.comercioconcepto = comercioconcepto;
	}


	public Long getIdComercioConceptoHidden() {
		return idComercioConceptoHidden;
	}


	public void setIdComercioConceptoHidden(Long idComercioConceptoHidden) {
		this.idComercioConceptoHidden = idComercioConceptoHidden;
	}


	public List getComercioItems() {
		return comercioItems;
	}


	public void setComercioItems(List comercioItems) {
		this.comercioItems = comercioItems;
	}


	public Long getIdComercioSeleccionada() {
		return idComercioSeleccionada;
	}


	public void setIdComercioSeleccionada(Long idComercioSeleccionada) {
		this.idComercioSeleccionada = idComercioSeleccionada;
	}


	public List getConceptoItems() {
		return conceptoItems;
	}


	public void setConceptoItems(List conceptoItems) {
		this.conceptoItems = conceptoItems;
	}


	public Long getIdConceptoSeleccionada() {
		return idConceptoSeleccionada;
	}


	public void setIdConceptoSeleccionada(Long idConceptoSeleccionada) {
		this.idConceptoSeleccionada = idConceptoSeleccionada;
	}


	public List getComercioConceptoList() {
		return comercioconceptoList;
	}


	public void setComercioConceptoList(List object) {
		this.comercioconceptoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE COMERCIOCONCEPTO
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
		return "amComercioConcepto";
	}


	private void cargarItems() {
		if (conceptoItems.size() != conceptoList.size()) {
			conceptoItems = Util.cargarSelectItem(conceptoList);
		}
	}


	public String editarComercioConcepto() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar comercioconcepto";
		try {
			comercioconcepto = transaccionesService.getComercioConceptoService().leerComercioConcepto(idComercioConceptoHidden);
			result = "amComercioConcepto";
		} catch (ComercioConceptoException e1) {
			error.agregar("Ocurrio un error al intentar editar el comercioconcepto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarComercioConcepto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el comercioconcepto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarComercioConcepto.jsf");
		}
		return result;
	}


	public String eliminarComercioConcepto() {
		try {
			transaccionesService.getComercioConceptoService().borrarComercioConcepto(idComercioConceptoHidden);
			comercioconceptoList.remove(new ComercioConcepto(idComercioConceptoHidden));
		} catch (ComercioConceptoException e1) {
			error.agregar("Imposible borrar el comercioconcepto. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el comercioconcepto");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarComercioConcepto.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ComercioConceptoService comercioconceptoService = transaccionesService.getComercioConceptoService();
				comercioconcepto.setConcepto((Concepto) Util.buscarElemento(conceptoList, new Concepto(idConceptoSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					comercioconceptoService.grabarComercioConcepto(comercioconcepto);
				} else {
					comercioconceptoService.actualizarComercioConcepto(comercioconcepto);
				}
				popup.setPopup(popup.ICONO_OK, "El comercioconcepto ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ComercioConceptoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioconcepto.");
			e1.printStackTrace();
		} catch (ComercioConceptoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioconcepto.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioconcepto.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de comercioconcepto";
		popup.borrar();

		comercioconcepto = new ComercioConcepto();
		comercioconceptoList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		// TODO: recordar definir las validaciones
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoComercioConcepto() {
		return inicializar();
	}


	public String irAModificarComercioConcepto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar comercioconcepto";
		return null;
	}


	public String irAListarComercioConcepto() {
		borrar();
		tituloCorto = "Listado de comercioconcepto";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarComercioConcepto.jsf");
		return "";
	}


	public String listarComercioConcepto() {
		comercioconceptoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, comercioconcepto.
			comercioconceptoList = transaccionesService.getComercioConceptoService().getComercioConcepto(filtro);
			Iterator iter = comercioconceptoList.iterator();
			while (iter.hasNext())
			{
				ComercioConcepto element = (ComercioConcepto) iter.next();
				element.getConcepto().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarComercioConcepto.jsf");
		return null;
	}
}

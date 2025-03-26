package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenConceptoDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;

import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenenException;

import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Origenen;
import com.bizitglobal.tarjetafiel.transacciones.negocio.OrigenConcepto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.OrigenConceptoService;


@SuppressWarnings({"rawtypes"})
public class OrigenConceptoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(OrigenConceptoBean.class);
	private OrigenConcepto origenconcepto;
	private String nombreFiltro;
	private Long idOrigenConceptoHidden;

	// definicion de un list del objeto base
	private List origenconceptoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List conceptoList = new ArrayList();
	private List conceptoItems = new ArrayList();

	private List origenenList = new ArrayList();
	private List origenenItems = new ArrayList();

	// Objetos Relacionados.
	private Long idConceptoSeleccionada;
	private Long idOrigenenSeleccionada;

	private String focoHidden;


	public OrigenConceptoBean() {
		super();
		borrar();
		try {
			try {
				conceptoList = transaccionesService.getConceptoService().getConcepto(new Filtro());
			} catch (ConceptoException e1) {
				e1.printStackTrace();
			}
			try {
				origenenList = transaccionesService.getOrigenenService().getOrigenen(new Filtro());
			} catch (OrigenenException e1) {
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


	public OrigenConcepto getOrigenConcepto() {
		return origenconcepto;
	}


	public void setOrigenConcepto(OrigenConcepto origenconcepto) {
		this.origenconcepto = origenconcepto;
	}


	public Long getIdOrigenConceptoHidden() {
		return idOrigenConceptoHidden;
	}


	public void setIdOrigenConceptoHidden(Long idOrigenConceptoHidden) {
		this.idOrigenConceptoHidden = idOrigenConceptoHidden;
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


	public List getOrigenenItems() {
		return origenenItems;
	}


	public void setOrigenenItems(List origenenItems) {
		this.origenenItems = origenenItems;
	}


	public Long getIdOrigenenSeleccionada() {
		return idOrigenenSeleccionada;
	}


	public void setIdOrigenenSeleccionada(Long idOrigenenSeleccionada) {
		this.idOrigenenSeleccionada = idOrigenenSeleccionada;
	}


	public List getOrigenConceptoList() {
		return origenconceptoList;
	}


	public void setOrigenConceptoList(List object) {
		this.origenconceptoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE ORIGENCONCEPTO
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
		return "amOrigenConcepto";
	}


	private void cargarItems() {
		if (conceptoItems.size() != conceptoList.size()) {
			conceptoItems = Util.cargarSelectItem(conceptoList);
		}
		if (origenenItems.size() != origenenList.size()) {
			origenenItems = Util.cargarSelectItem(origenenList);
		}
	}


	public String editarOrigenConcepto() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar origenconcepto";
		try {
			origenconcepto = transaccionesService.getOrigenConceptoService().leerOrigenConcepto(idOrigenConceptoHidden);
			result = "amOrigenConcepto";
		} catch (OrigenConceptoException e1) {
			error.agregar("Ocurrio un error al intentar editar el origenconcepto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarOrigenConcepto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el origenconcepto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarOrigenConcepto.jsf");
		}
		return result;
	}


	public String eliminarOrigenConcepto() {
		try {
			transaccionesService.getOrigenConceptoService().borrarOrigenConcepto(idOrigenConceptoHidden);
			origenconceptoList.remove(new OrigenConcepto(idOrigenConceptoHidden));
		} catch (OrigenConceptoException e1) {
			error.agregar("Imposible borrar el origenconcepto. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el origenconcepto");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarOrigenConcepto.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				OrigenConceptoService origenconceptoService = transaccionesService.getOrigenConceptoService();
				origenconcepto.setConcepto((Concepto) Util.buscarElemento(conceptoList, new Concepto(idConceptoSeleccionada)));
				origenconcepto.setOrigenen((Origenen) Util.buscarElemento(origenenList, new Origenen(idOrigenenSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					origenconceptoService.grabarOrigenConcepto(origenconcepto);
				} else {
					origenconceptoService.actualizarOrigenConcepto(origenconcepto);
				}
				popup.setPopup(popup.ICONO_OK, "El origenconcepto ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (OrigenConceptoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origenconcepto.");
			e1.printStackTrace();
		} catch (OrigenConceptoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origenconcepto.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origenconcepto.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de origenconcepto";
		popup.borrar();

		origenconcepto = new OrigenConcepto();
		origenconceptoList = new ArrayList();
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


	public String irANuevoOrigenConcepto() {
		return inicializar();
	}


	public String irAModificarOrigenConcepto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar origenconcepto";
		return null;
	}


	public String irAListarOrigenConcepto() {
		borrar();
		tituloCorto = "Listado de origenconcepto";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarOrigenConcepto.jsf");
		return "";
	}


	public String listarOrigenConcepto() {
		origenconceptoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, origenconcepto.
			origenconceptoList = transaccionesService.getOrigenConceptoService().getOrigenConcepto(filtro);
			Iterator iter = origenconceptoList.iterator();
			while (iter.hasNext())
			{
				OrigenConcepto element = (OrigenConcepto) iter.next();
				element.getConcepto().getLabel();
				element.getOrigenen().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarOrigenConcepto.jsf");
		return null;
	}
}

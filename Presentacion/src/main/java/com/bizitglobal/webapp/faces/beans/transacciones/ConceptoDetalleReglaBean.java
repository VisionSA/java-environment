package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleReglaDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleReglaException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleRegla;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoDetalleReglaService;


@SuppressWarnings({"rawtypes"})
public class ConceptoDetalleReglaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptoDetalleReglaBean.class);
	private ConceptoDetalleRegla conceptodetalleregla;
	private String nombreFiltro;
	private Long idConceptoDetalleReglaHidden;
	private HtmlSelectOneMenu conceptoDetalleSeleccionado;
	private HtmlSelectOneMenu conceptoDetalleSeleccionadoFiltro;
	private Long idConceptoDetalleSeleccionadaFiltro;

	// definicion de un list del objeto base
	private List conceptodetallereglaList;

	// Listas para la presentacion(HtmlSelectItems).
	private List conceptodetalleList = new ArrayList();
	private List conceptodetalleItems = new ArrayList();

	// Objetos Relacionados.
	private Long idConceptoDetalleSeleccionada;

	private String idConceptoDetalleReglaFiltro;

	private String focoHidden;


	public ConceptoDetalleReglaBean() {
		super();
		borrar();
		try {
			try {
				conceptodetalleList = transaccionesService.getConceptoDetalleService().getConceptoDetalle(new Filtro());
			} catch (ConceptoDetalleException e1) {
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


	public ConceptoDetalleRegla getConceptoDetalleRegla() {
		return conceptodetalleregla;
	}


	public void setConceptoDetalleRegla(ConceptoDetalleRegla conceptodetalleregla) {
		this.conceptodetalleregla = conceptodetalleregla;
	}


	public Long getIdConceptoDetalleReglaHidden() {
		return idConceptoDetalleReglaHidden;
	}


	public void setIdConceptoDetalleReglaHidden(Long idConceptoDetalleReglaHidden) {
		this.idConceptoDetalleReglaHidden = idConceptoDetalleReglaHidden;
	}


	public List getConceptoDetalleItems() {
		return conceptodetalleItems;
	}


	public void setConceptoDetalleItems(List conceptodetalleItems) {
		this.conceptodetalleItems = conceptodetalleItems;
	}


	public Long getIdConceptoDetalleSeleccionada() {
		return idConceptoDetalleSeleccionada;
	}


	public void setIdConceptoDetalleSeleccionada(Long idConceptoDetalleSeleccionada) {
		this.idConceptoDetalleSeleccionada = idConceptoDetalleSeleccionada;
	}


	public List getConceptoDetalleReglaList() {
		return conceptodetallereglaList;
	}


	public void setConceptoDetalleReglaList(List object) {
		this.conceptodetallereglaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CONCEPTODETALLEREGLA
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amConceptoDetalleRegla";
	}


	private void cargarItems() {
		if (conceptodetalleItems.size() != conceptodetalleList.size()) {
			conceptodetalleItems = Util.cargarSelectItem(conceptodetalleList);
		}
	}


	public String editarConceptoDetalleRegla() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Concepto Detalle Regla";
		try {
			conceptodetalleregla = transaccionesService.getConceptoDetalleReglaService().leerConceptoDetalleRegla(idConceptoDetalleReglaHidden);
			result = "amConceptoDetalleRegla";
		} catch (ConceptoDetalleReglaException e1) {
			error.agregar("Ocurrio un error al intentar editar el concepto detalle regla");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleRegla.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el conceptodetalleregla");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleRegla.jsf");
		}
		return result;
	}


	public String eliminarConceptoDetalleRegla() {
		try {
			transaccionesService.getConceptoDetalleReglaService().borrarConceptoDetalleRegla(idConceptoDetalleReglaHidden);
			conceptodetallereglaList.remove(new ConceptoDetalleRegla(idConceptoDetalleReglaHidden));
		} catch (ConceptoDetalleReglaException e1) {
			error.agregar("Imposible borrar el conceptodetalleregla. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el conceptodetalleregla");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleRegla.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ConceptoDetalleReglaService conceptodetallereglaService = transaccionesService.getConceptoDetalleReglaService();
				conceptodetalleregla.setConceptoDetalle((ConceptoDetalle) Util.buscarElemento(conceptodetalleList, new ConceptoDetalle(
						idConceptoDetalleSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					conceptodetallereglaService.grabarConceptoDetalleRegla(conceptodetalleregla);
				} else {
					conceptodetallereglaService.actualizarConceptoDetalleRegla(conceptodetalleregla);
				}
				popup.setPopup(popup.ICONO_OK, "El conceptodetalleregla ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ConceptoDetalleReglaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto detalle regla.");
			e1.printStackTrace();
		} catch (ConceptoDetalleReglaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto detalle regla.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto detalle regla.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de conceptodetalleregla";
		popup.borrar();
		conceptodetalleregla = new ConceptoDetalleRegla();
		conceptodetallereglaList = new ArrayList();
		idConceptoDetalleSeleccionada = new Long(0);
		conceptoDetalleSeleccionado = new HtmlSelectOneMenu();
		conceptoDetalleSeleccionado.setValue(new Long(0));
		conceptoDetalleSeleccionadoFiltro = new HtmlSelectOneMenu();
		conceptoDetalleSeleccionadoFiltro.setValue(new Long(0));
		idConceptoDetalleSeleccionadaFiltro = new Long(0);
		cargarItems();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (conceptodetalleregla.getDetalle() == null || conceptodetalleregla.getDetalle().compareTo("") == 0) {
			error.agregar(Error.TRAN_CLASE_PARA_DETALLE_REGLA_REQUERIDO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoConceptoDetalleRegla() {
		return inicializar();
	}


	public String irAModificarConceptoDetalleRegla() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar conceptodetalleregla";
		return null;
	}


	public String irAListarConceptoDetalleRegla() {
		borrar();
		tituloCorto = "Listado de conceptodetalleregla";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleRegla.jsf");
		return "";
	}


	public String listarConceptoDetalleRegla() {
		cargarItems();
		conceptodetallereglaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idConceptoDetalleReglaFiltro != null && idConceptoDetalleReglaFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idConceptosdetallereglas", Filtro.IGUAL, new Long(idConceptoDetalleReglaFiltro));
			}
			idConceptoDetalleSeleccionadaFiltro = (Long) conceptoDetalleSeleccionadoFiltro.getValue();
			if (idConceptoDetalleSeleccionadaFiltro != null) {
				filtro.agregarCampoOperValor("conceptoDetalle.idConceptoDetalle", Filtro.IGUAL, idConceptoDetalleSeleccionadaFiltro);
			}
			log.info("filtro dice: " + filtro.toString());
			conceptodetallereglaList = transaccionesService.getConceptoDetalleReglaService().getConceptoDetalleRegla(filtro);
			Iterator iter = conceptodetallereglaList.iterator();
			while (iter.hasNext())
			{
				ConceptoDetalleRegla element = (ConceptoDetalleRegla) iter.next();
				element.getConceptoDetalle().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleRegla.jsf");
		return null;
	}


	public HtmlSelectOneMenu getConceptoDetalleSeleccionado() {
		return conceptoDetalleSeleccionado;
	}


	public void setConceptoDetalleSeleccionado(
			HtmlSelectOneMenu conceptoDetalleSeleccionado) {
		this.conceptoDetalleSeleccionado = conceptoDetalleSeleccionado;
	}


	public HtmlSelectOneMenu getConceptoDetalleSeleccionadoFiltro() {
		return conceptoDetalleSeleccionadoFiltro;
	}


	public void setConceptoDetalleSeleccionadoFiltro(
			HtmlSelectOneMenu conceptoDetalleSeleccionadoFiltro) {
		this.conceptoDetalleSeleccionadoFiltro = conceptoDetalleSeleccionadoFiltro;
	}


	public String getIdConceptoDetalleReglaFiltro() {
		return idConceptoDetalleReglaFiltro;
	}


	public void setIdConceptoDetalleReglaFiltro(String idConceptoDetalleReglaFiltro) {
		this.idConceptoDetalleReglaFiltro = idConceptoDetalleReglaFiltro;
	}


	public Long getIdConceptoDetalleSeleccionadaFiltro() {
		return idConceptoDetalleSeleccionadaFiltro;
	}


	public void setIdConceptoDetalleSeleccionadaFiltro(Long idConceptoDetalleSeleccionadaFiltro) {
		this.idConceptoDetalleSeleccionadaFiltro = idConceptoDetalleSeleccionadaFiltro;
	}


	public List getConceptodetalleItems() {
		return conceptodetalleItems;
	}


	public void setConceptodetalleItems(List conceptodetalleItems) {
		this.conceptodetalleItems = conceptodetalleItems;
	}


	public List getConceptodetallereglaList() {
		return conceptodetallereglaList;
	}


	public void setConceptodetallereglaList(List conceptodetallereglaList) {
		this.conceptodetallereglaList = conceptodetallereglaList;
	}
}

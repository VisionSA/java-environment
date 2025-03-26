package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleTargetDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleTargetException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleTarget;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoDetalleTargetService;


@SuppressWarnings({"rawtypes"})
public class ConceptoDetalleTargetBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptoDetalleTargetBean.class);
	private ConceptoDetalleTarget conceptodetalletarget;
	private String nombreFiltro;
	private Long idConceptoDetalleTargetHidden;
	private HtmlSelectOneMenu conceptoDetalleSeleccionado;
	private Long idConceptoDetalleSeleccionadoFiltro;
	private HtmlSelectOneMenu conceptoDetalleSeleccionadoFiltro;
	// definicion de un list del objeto base
	private List conceptodetalletargetList;

	private String idConceptoDetalleTargetFiltro;

	// Listas para la presentacion(HtmlSelectItems).
	private List conceptodetalleList = new ArrayList();
	private List conceptodetalleItems = new ArrayList();

	// Objetos Relacionados.
	private Long idConceptoDetalleSeleccionada;

	private String focoHidden;


	public ConceptoDetalleTargetBean() {
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


	public ConceptoDetalleTarget getConceptoDetalleTarget() {
		return conceptodetalletarget;
	}


	public void setConceptoDetalleTarget(ConceptoDetalleTarget conceptodetalletarget) {
		this.conceptodetalletarget = conceptodetalletarget;
	}


	public Long getIdConceptoDetalleTargetHidden() {
		return idConceptoDetalleTargetHidden;
	}


	public void setIdConceptoDetalleTargetHidden(Long idConceptoDetalleTargetHidden) {
		this.idConceptoDetalleTargetHidden = idConceptoDetalleTargetHidden;
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


	public List getConceptoDetalleTargetList() {
		return conceptodetalletargetList;
	}


	public void setConceptoDetalleTargetList(List object) {
		this.conceptodetalletargetList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CONCEPTODETALLETARGET
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amConceptoDetalleTarget";
	}


	private void cargarItems() {
		if (conceptodetalleItems.size() != conceptodetalleList.size()) {
			conceptodetalleItems = Util.cargarSelectItem(conceptodetalleList);
		}
	}


	public String editarConceptoDetalleTarget() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Concepto Detalle Target";
		try {
			conceptodetalletarget = transaccionesService.getConceptoDetalleTargetService().leerConceptoDetalleTarget(idConceptoDetalleTargetHidden);
			result = "amConceptoDetalleTarget";
		} catch (ConceptoDetalleTargetException e1) {
			error.agregar("Ocurrio un error al intentar editar el conceptodetalletarget");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleTarget.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el conceptodetalletarget");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleTarget.jsf");
		}
		return result;
	}


	public String eliminarConceptoDetalleTarget() {
		try {
			transaccionesService.getConceptoDetalleTargetService().borrarConceptoDetalleTarget(idConceptoDetalleTargetHidden);
			conceptodetalletargetList.remove(new ConceptoDetalleTarget(idConceptoDetalleTargetHidden));
		} catch (ConceptoDetalleTargetException e1) {
			error.agregar("Imposible borrar el Concepto Detalle Target. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Concepto Detalle Target");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleTarget.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ConceptoDetalleTargetService conceptodetalletargetService = transaccionesService.getConceptoDetalleTargetService();
				conceptodetalletarget.setConceptoDetalle((ConceptoDetalle) Util.buscarElemento(conceptodetalleList, new ConceptoDetalle(
						idConceptoDetalleSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					conceptodetalletargetService.grabarConceptoDetalleTarget(conceptodetalletarget);
				} else {
					conceptodetalletargetService.actualizarConceptoDetalleTarget(conceptodetalletarget);
				}
				popup.setPopup(popup.ICONO_OK, "El conceptodetalletarget ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ConceptoDetalleTargetDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del conceptodetalletarget.");
			e1.printStackTrace();
		} catch (ConceptoDetalleTargetException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del conceptodetalletarget.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del conceptodetalletarget.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de Concepto Detalle Target";
		popup.borrar();
		idConceptoDetalleTargetFiltro = "";
		conceptodetalletarget = new ConceptoDetalleTarget();
		conceptodetalletargetList = new ArrayList();
		idConceptoDetalleSeleccionada = new Long(0);
		conceptoDetalleSeleccionado = new HtmlSelectOneMenu();
		conceptoDetalleSeleccionado.setValue(new Long(0));
		idConceptoDetalleSeleccionadoFiltro = new Long(0);
		conceptoDetalleSeleccionadoFiltro = new HtmlSelectOneMenu();
		conceptoDetalleSeleccionadoFiltro.setValue(new Long(0));

		cargarItems();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (conceptodetalletarget.getDetalle() == null || conceptodetalletarget.getDetalle().compareTo("") == 0) {
			error.agregar(Error.TRAN_DETALLE_PARA_DETALLE_TARGET_REQUERIDO);
		}
		if (conceptodetalletarget.getSql() == null || conceptodetalletarget.getSql().compareTo("") == 0) {
			error.agregar(Error.TRAN_SQL_PARA_DETALLE_TARGET_REQUERIDO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoConceptoDetalleTarget() {
		return inicializar();
	}


	public String irAModificarConceptoDetalleTarget() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Concepto Detalle Target";
		return null;
	}


	public String irAListarConceptoDetalleTarget() {
		borrar();
		tituloCorto = "Listado de Concepto Detalle Target";
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleTarget.jsf");
		return "";
	}


	public String listarConceptoDetalleTarget() {
		cargarItems();
		conceptodetalletargetList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idConceptoDetalleTargetFiltro != null && idConceptoDetalleTargetFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idConceptostarget", Filtro.IGUAL, new Long(idConceptoDetalleTargetFiltro));
			}
			idConceptoDetalleSeleccionadoFiltro = (Long) conceptoDetalleSeleccionadoFiltro.getValue();
			if (idConceptoDetalleSeleccionadoFiltro != null) {
				filtro.agregarCampoOperValor("conceptoDetalle.idConceptoDetalle", Filtro.IGUAL, idConceptoDetalleSeleccionadoFiltro);
			}
			conceptodetalletargetList = transaccionesService.getConceptoDetalleTargetService().getConceptoDetalleTarget(filtro);
			Iterator iter = conceptodetalletargetList.iterator();
			while (iter.hasNext())
			{
				ConceptoDetalleTarget element = (ConceptoDetalleTarget) iter.next();
				element.getConceptoDetalle().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalleTarget.jsf");
		return null;
	}


	public HtmlSelectOneMenu getConceptoDetalleSeleccionado() {
		return conceptoDetalleSeleccionado;
	}


	public void setConceptoDetalleSeleccionado(
			HtmlSelectOneMenu conceptoDetalleSeleccionado) {
		this.conceptoDetalleSeleccionado = conceptoDetalleSeleccionado;
	}


	public List getConceptodetalletargetList() {
		return conceptodetalletargetList;
	}


	public void setConceptodetalletargetList(List conceptodetalletargetList) {
		this.conceptodetalletargetList = conceptodetalletargetList;
	}


	public List getConceptodetalleList() {
		return conceptodetalleList;
	}


	public void setConceptodetalleList(List conceptodetalleList) {
		this.conceptodetalleList = conceptodetalleList;
	}


	public List getConceptodetalleItems() {
		return conceptodetalleItems;
	}


	public void setConceptodetalleItems(List conceptodetalleItems) {
		this.conceptodetalleItems = conceptodetalleItems;
	}


	public HtmlSelectOneMenu getConceptoDetalleSeleccionadoFiltro() {
		return conceptoDetalleSeleccionadoFiltro;
	}


	public void setConceptoDetalleSeleccionadoFiltro(
			HtmlSelectOneMenu conceptoDetalleSeleccionadoFiltro) {
		this.conceptoDetalleSeleccionadoFiltro = conceptoDetalleSeleccionadoFiltro;
	}


	public Long getIdConceptoDetalleSeleccionadoFiltro() {
		return idConceptoDetalleSeleccionadoFiltro;
	}


	public void setIdConceptoDetalleSeleccionadoFiltro(
			Long idConceptoDetalleSeleccionadoFiltro) {
		this.idConceptoDetalleSeleccionadoFiltro = idConceptoDetalleSeleccionadoFiltro;
	}


	public String getIdConceptoDetalleTargetFiltro() {
		return idConceptoDetalleTargetFiltro;
	}


	public void setIdConceptoDetalleTargetFiltro(
			String idConceptoDetalleTargetFiltro) {
		this.idConceptoDetalleTargetFiltro = idConceptoDetalleTargetFiltro;
	}
}

package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;
import com.bizitglobal.tarjetafiel.impuestos.service.TramosRetencionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class TramoRetencionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TramoRetencionBean.class);
	private TramosRetencion tramo;
	private String nombreFiltro;
	private Long idTramoHidden;
	private String idTramo;

	// definicion de un list del objeto base
	private List tramoList;
	// Listas para la presentacion(HtmlSelectItems).
	private List retencionList = new ArrayList();
	private List retencionItems = new ArrayList();

	// Objetos Relacionados.
	private Long idImpRetencionesSeleccionada;

	private String focoHidden;


	public TramoRetencionBean() {
		// tramo = new TramosRetencion();
		// tramo.setIdTramoRetencion(new Long(""+tramo.hashCode()));
		super();
		borrar();
		try {
			try {
				retencionList = impuestoService.getRetencionService().getRetenciones(new Filtro());
			} catch (RetencionException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Long getIdImpRetencionesSeleccionada() {
		return idImpRetencionesSeleccionada;
	}


	public void setIdImpRetencionesSeleccionada(Long idImpRetencionesSeleccionada) {
		this.idImpRetencionesSeleccionada = idImpRetencionesSeleccionada;
	}


	public Long getIdTramoHidden() {
		return idTramoHidden;
	}


	public void setIdTramoHidden(Long idTramoHidden) {
		this.idTramoHidden = idTramoHidden;
	}


	public List getRetencionItems() {
		return retencionItems;
	}


	public void setRetencionItems(List retencionItems) {
		this.retencionItems = retencionItems;
	}


	public List getTramoList() {
		return tramoList;
	}


	public void setTramoList(List tramoList) {
		this.tramoList = tramoList;
	}


	public TramosRetencion getTramo() {
		return tramo;
	}


	public void setTramo(TramosRetencion tramo) {
		this.tramo = tramo;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public boolean getAlta() {
		return alta;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE TRAMOS RETENCIONES
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
		return "amImpTramoRetencion";
	}


	private void cargarItems() {
		if (retencionItems.size() != retencionList.size()) {
			retencionItems = new ArrayList();
			retencionItems.add(new SelectItem(new Long(0), "Seleccionar Retencion"));
			retencionItems.addAll(Util.cargarSelectItem(retencionList));
		}
	}


	public String editarTramoRetencion() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Tramo Retencion";
		try {
			tramo = impuestoService.getTramosRetencionService().leerTramosRetencion(idTramoHidden);
			idImpRetencionesSeleccionada = tramo.getIdTramoRetencion();
			result = "amImpTramoRetencion";
		} catch (TramosRetencionException e1) {
			error.agregar("Ocurrio un error al intentar editar un tramo retencion");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarImpTramoRetencion.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar un tramo retencion");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarImpTramoRetencion.jsf");
		}
		return result;
	}


	public String eliminarTramoRetencion() {
		try {
			impuestoService.getTramosRetencionService().borrarTramosRetencion(idTramoHidden);
			tramoList.remove(new TramosRetencion(idTramoHidden));
		} catch (TramosRetencionException e1) {
			error.agregar("Imposible borrar el Tramo Retencion. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Imposible borrar el Tramo Retencion.");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarImpTramoRetencion.jsf");
		return null;
	}


	public String grabar() {
		try {
			// tramo.setNombre(provincia.getNombre().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				TramosRetencionService tramoService = impuestoService.getTramosRetencionService();
				tramo.setRetencion((Retencion) Util.buscarElemento(retencionList, new Retencion(idImpRetencionesSeleccionada)));
				if (alta) {

					tramoService.grabarTramosRetencion(tramo);
					idImpRetencionesSeleccionada = new Long(0);
				}
				else {
					tramoService.actualizarTramosRetencion(tramo);
					idImpRetencionesSeleccionada = new Long(0);
				}
				popup.setPopup(popup.ICONO_OK, "El Tramo Retenciona ha sido almacenado exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (TramosRetencionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de el Tramo Retencion.");
			e1.printStackTrace();
		} catch (TramosRetencionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de el Tramo Retencion.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de el Tramo Retencion.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Tramo Retencion";
		popup.borrar();

		idTramo = "";

		tramo = new TramosRetencion();
		tramoList = new ArrayList();

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (tramo.getMontoDesde() == null) {
			error.agregar(Error.TRAMO_RETENCION_MONTO_DESDE_REQUERIDA);
		}

		if (tramo.getMontoHasta() == null) {
			error.agregar(Error.TRAMO_RETENCION_MONTO_HASTA_REQUERIDA);
		}

		if (tramo.getMontoMinimo() == null) {
			error.agregar(Error.TRAMO_RETENCION_MONTO_MINIMO_REQUERIDA);
		}

		if (tramo.getMontoDesde() != null && tramo.getMontoHasta() != null) {
			if (tramo.getMontoHasta().longValue() < tramo.getMontoDesde().longValue()) {
				error.agregar(Error.TRAMO_RETENCION_MONTODESDE_MAYOR_MONTOHASTA);
			}
		}

		if (tramo.getPorcRetencion() == null) {
			error.agregar(Error.TRAMO_RETENCION_PORCENTAJE_REQUERIDA);
		}

		if (tramo.getSobreExedente() == null) {
			error.agregar(Error.TRAMO_RETENCION_SOBRE_EXEDENTE_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoTramoRetencion() {
		return inicializar();
	}


	public String irAModificarTramoRetencion() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Tramo Retencion";
		return null;
	}


	public String irAListarTramoRetencion() {
		borrar();
		tramo.setPorcRetencion(null);
		tituloCorto = "Listado de Tramo Retencion";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarImpTramoRetencion.jsf");
		return "";
	}


	public String listarTramoRetencion() {
		tramoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idImpRetencionesSeleccionada != null && !idImpRetencionesSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("retencion.idRetencion", Filtro.IGUAL, idImpRetencionesSeleccionada);
			if (tramo.getMontoDesde() != null && !tramo.getMontoDesde().equals(""))
				filtro.agregarCampoOperValor("montoDesde", Filtro.IGUAL, tramo.getMontoDesde());
			if (tramo.getMontoHasta() != null && !tramo.getMontoHasta().equals(""))
				filtro.agregarCampoOperValor("montoHasta", Filtro.IGUAL, tramo.getMontoHasta());
			if (tramo.getPorcRetencion() != null && !tramo.getPorcRetencion().equals(""))
				filtro.agregarCampoOperValor("porcRetencion", Filtro.IGUAL, tramo.getPorcRetencion());
			/*
			 * if(idTramo != null && !idTramo.equals("")) filtro.agregarCampoOperValor("idTramoRetencion", Filtro.IGUAL, new Long(idTramo));
			 * if(tramo.getMontoMinimo()!= null && !tramo.getMontoMinimo().equals("")) filtro.agregarCampoOperValor("montoMinimo", Filtro.IGUAL,
			 * tramo.getMontoMinimo()); if(tramo.getSobreExedente()!= null && !tramo.getSobreExedente().equals(""))
			 * filtro.agregarCampoOperValor("montoMinimo", Filtro.IGUAL, tramo.getSobreExedente());
			 */

			tramoList = impuestoService.getTramosRetencionService().getTramosRetenciones(filtro);
			Iterator iter = tramoList.iterator();
			while (iter.hasNext())
			{
				TramosRetencion element = (TramosRetencion) iter.next();
				element.getRetencion().getLabel();
			}
			idTramo = "";
			idImpRetencionesSeleccionada = new Long(0);
			tramo.setMontoDesde(null);
			tramo.setMontoHasta(null);
			tramo.setPorcRetencion(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarImpTramoRetencion.jsf");
		return null;
	}


	public String agregarTramo() {
		log.info("Agregando tramo al bean de retenciones!!!");
		if (validar()) {
			RetencionBean bean = (RetencionBean) Session.getBean("RetencionBean");
			bean.getTramos().add(tramo);
			borrar();
		}
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (error.cantidad() == 0) {
			ejecutarJavaScript("window.opener.recargar();window.close();");
		}
	}
}
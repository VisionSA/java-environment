package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.impuestos.service.ActividadService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;



@SuppressWarnings({"rawtypes"})
public class ActividadBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ActividadBean.class);
	private Actividad actividad;
	private String nombreFiltro;
	private Long idActividadHidden;
	private String idActividad;

	// definicion de un list del objeto base
	private List actividadList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;
	private String descActividadInicial;


	public ActividadBean() {
		super();
		borrar();
		try {
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


	public Actividad getActividad() {
		return actividad;
	}


	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}


	public Long getIdActividadHidden() {
		return idActividadHidden;
	}


	public void setIdActividadHidden(Long idActividadHidden) {
		this.idActividadHidden = idActividadHidden;
	}


	public List getActividadList() {
		return actividadList;
	}


	public void setActividadList(List object) {
		this.actividadList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE ACTIVIDAD
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
		return "amActividad";
	}


	private void cargarItems() {
	}


	public String editarActividad() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Actividad";
		try {
			actividad = impuestoService.getActividadService().leerActividad(idActividadHidden);
			descActividadInicial = actividad.getDescripcion();

			result = "amActividad";
		} catch (ActividadException e1) {
			error.agregar("Ocurrio un error al intentar editar el actividad");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarActividad.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el actividad");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarActividad.jsf");
		}
		return result;
	}


	public String eliminarActividad() {
		try {
			impuestoService.getActividadService().borrarActividad(idActividadHidden);
			actividadList.remove(new Actividad(idActividadHidden, null));
		} catch (ActividadException e1) {
			error.agregar("Imposible borrar el actividad. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el actividad");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarActividad.jsf");
		return null;
	}


	public String grabar() {
		try {
			actividad.setDescripcion(actividad.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				ActividadService actividadService = impuestoService.getActividadService();
				if (alta) {
					// Grabo el nuevo objeto
					actividadService.grabarActividad(actividad);
				}
				else {
					if (!(descActividadInicial.equals(actividad.getDescripcion())))
						actividadService.actualizarActividad(actividad);
				}
				popup.setPopup(popup.ICONO_OK, "La Actividad ha sido almacenada exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ActividadDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Actividad.");
			e1.printStackTrace();
		} catch (ActividadException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Actividad.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Actividad.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Actividad";
		popup.borrar();

		actividad = new Actividad();
		actividadList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (actividad.getDescripcion() == null || actividad.getDescripcion().equals(""))
			error.agregar(Error.AMACTIVIDAD_DESCRIPCION_REQUERIDA);

		try {
			List unaActividad = impuestoService.getActividadService().getActividades(
					new Filtro("descripcion", Filtro.LIKEXS, actividad.getDescripcion()));
			if (alta) {
				if (!unaActividad.isEmpty())
					error.agregar(Error.AMACTIVIDAD_ACTIVIDAD_REPETIDA);
			}
			else {
				if (!(descActividadInicial.compareTo(actividad.getDescripcion()) == 0))
				{
					if (!unaActividad.isEmpty())
						error.agregar(Error.AMACTIVIDAD_ACTIVIDAD_REPETIDA);
				}
			}

		} catch (ActividadException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoActividad() {
		return inicializar();
	}


	public String irAModificarActividad() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Actividad";
		return null;
	}


	public String irAListarActividad() {
		borrar();
		tituloCorto = "Listado de Actividad";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarActividad.jsf");
		return "";
	}


	public String listarActividad() {
		actividadList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idActividad != null && !idActividad.equals(""))
				filtro.agregarCampoOperValor("idActividad", Filtro.IGUAL, new Long(idActividad));
			if (actividad.getDescripcion() != null && !actividad.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, actividad.getDescripcion());

			actividadList = impuestoService.getActividadService().getActividades(filtro);
			Iterator iter = actividadList.iterator();
			while (iter.hasNext())
			{
				Actividad element = (Actividad) iter.next();
			}
			idActividad = "";
			actividad.setDescripcion("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarActividad.jsf");
		return null;
	}


	public String getIdActividad() {
		return idActividad;
	}


	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}
}

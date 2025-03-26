package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionActividadService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class JurisActividadBean extends BaseBean {
	private static final Logger log = Logger.getLogger(JurisActividadBean.class);
	private JurisdiccionActividad jurisActividad;
	private String nombreFiltro;
	private Long idJurisActividadHidden;
	private String idJurisActividad;

	// definicion de un list del objeto base
	private List jurisActividadList;

	// Listas para la presentacion(HtmlSelectItems).
	private List actividadList = new ArrayList();
	private List actividadItems = new ArrayList();

	private List jurisdiccionList = new ArrayList();
	private List jurisdiccionItems = new ArrayList();

	// Objetos Relacionados.
	private Long idActividadSeleccionada;
	private Long idJurisdiccionSeleccionada;

	private String focoHidden;


	public JurisActividadBean() {
		super();
		borrar();
		try {
			try {
				actividadList = impuestoService.getActividadService().getActividades(new Filtro());
			} catch (ActividadException e1) {
				e1.printStackTrace();
			}
			try {
				jurisdiccionList = impuestoService.getJurisdiccionService().getJurisdiccion(new Filtro());
			} catch (JurisdiccionException e1) {
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


	public Long getIdJurisActividadHidden() {
		return idJurisActividadHidden;
	}


	public void setIdJurisActividadHidden(Long idJurisActividadHidden) {
		this.idJurisActividadHidden = idJurisActividadHidden;
	}


	public List getActividadItems() {
		return actividadItems;
	}


	public void setActividadItems(List actividadItems) {
		this.actividadItems = actividadItems;
	}


	public Long getIdActividadSeleccionada() {
		return idActividadSeleccionada;
	}


	public void setIdActividadSeleccionada(Long idActividadSeleccionada) {
		this.idActividadSeleccionada = idActividadSeleccionada;
	}


	public List getJurisdiccionItems() {
		return jurisdiccionItems;
	}


	public void setJurisdiccionItems(List jurisdiccionItems) {
		this.jurisdiccionItems = jurisdiccionItems;
	}


	public Long getIdJurisdiccionSeleccionada() {
		return idJurisdiccionSeleccionada;
	}


	public void setIdJurisdiccionSeleccionada(Long idJurisdiccionSeleccionada) {
		this.idJurisdiccionSeleccionada = idJurisdiccionSeleccionada;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE JURISACTIVIDAD
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
		return "amJurisActividad";
	}


	private void cargarItems() {

		if (actividadItems.size() != actividadList.size()) {
			actividadItems = new ArrayList();
			actividadItems.add(new SelectItem(new Long(0), "Seleccionar Actividad"));
			actividadItems.addAll(Util.cargarSelectItem(actividadList));
		}
		if (jurisdiccionItems.size() != jurisdiccionList.size()) {
			jurisdiccionItems = new ArrayList();
			jurisdiccionItems.add(new SelectItem(new Long(0), "Seleccionar Jurisdicción"));
			jurisdiccionItems.addAll(Util.cargarSelectItem(jurisdiccionList));
		}
	}


	public String editarJurisActividad() {
		String result = null;
		// borrarBaseBean();
		// borrar();
		alta = false;
		tituloCorto = "Modificar Jurisdicción Actividad";
		try {
			log.info("Id Jurisdicción Actividad a modificar: " + idJurisActividadHidden);
			jurisActividad = impuestoService.getJurisdiccionActividadService().leerJurisdiccionActividad(idJurisActividadHidden);
			log.info(jurisActividad);

			// idActividadSeleccionada = jurisActividad.getActividad().getIdActividad();
			idJurisdiccionSeleccionada = jurisActividad.getJurisdiccion().getIdJurisdiccion();

			result = "amJurisActividad";
		} catch (JurisdiccionActividadException e1) {
			error.agregar("Ocurrio un error al intentar editar el jurisactividad");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarJurisActividad.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el jurisactividad");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarJurisActividad.jsf");
		}
		return result;
	}


	public String eliminarJurisActividad() {
		try {
			impuestoService.getJurisdiccionActividadService().borrarJurisdiccionActividad(idJurisActividadHidden);
			// jurisActividadList.remove(new JurisdiccionActividad(idJurisActividadHidden, null,null));
			listarJurisActividad();
		} catch (JurisdiccionActividadException e1) {
			error.agregar("Imposible borrar el jurisactividad. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Imposible borrar el jurisactividad. Posee elementos asociados");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarJurisActividad.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				JurisdiccionActividadService jurisactividadService = impuestoService.getJurisdiccionActividadService();
				// jurisActividad.setActividad((Actividad)Util.buscarElemento(actividadList, new Actividad(idActividadSeleccionada,null)));
				jurisActividad.setJurisdiccion((Jurisdiccion) Util.buscarElemento(jurisdiccionList,
						new Jurisdiccion(idJurisdiccionSeleccionada, null)));
				if (alta) {
					// Grabo el nuevo objeto
					jurisactividadService.grabarJurisdiccionActividad((jurisActividad));
					idActividadSeleccionada = new Long(0);
					idJurisdiccionSeleccionada = new Long(0);
				} else {
					jurisactividadService.actualizarJurisdiccionActividad(jurisActividad);
					idActividadSeleccionada = new Long(0);
					idJurisdiccionSeleccionada = new Long(0);
				}
				popup.setPopup(popup.ICONO_OK, "La Jurisdicción Actividad ha sido almacenada exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (JurisdiccionActividadDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción Actividad.");
			e1.printStackTrace();
		} catch (JurisdiccionActividadException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción Actividad.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción Actividad.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Jurisdicción Actividad";
		popup.borrar();

		jurisActividad = new JurisdiccionActividad();
		jurisActividadList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (idActividadSeleccionada == null || idActividadSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMACTIVIDAD_ACTIVIDAD_REPETIDA);
		if (idJurisdiccionSeleccionada == null || idJurisdiccionSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMJURIDICCION_REQUERIDA);
		if (alta) {

			List unaActividad;
			try {
				unaActividad = impuestoService.getJurisdiccionActividadService().getJurisdiccionActividads(
						new Filtro("actividad", Filtro.IGUAL, idActividadSeleccionada));
				if (!unaActividad.isEmpty()) {
					Iterator iter = unaActividad.iterator();
					while (iter.hasNext()) {
						JurisdiccionActividad element = (JurisdiccionActividad) iter.next();
						if (element.getJurisdiccion().getIdJurisdiccion().equals(idJurisdiccionSeleccionada))
							error.agregar(Error.AMJURISDICCION_JURISDICCION_REPETIDA);
					}
				}
				// List unaJuridis = impuestoService.getJurisdiccionActividadService().getJurisdiccionActividads(new Filtro("descripcion",
				// Filtro.LIKEXS, jurisActividad.getJurisdiccion().getDescripcion()));
				// if(!unaJuridis.isEmpty()){
				// Iterator iterator = unaJuridisActividad.iterator();
				// while (iterator.hasNext()) {
				// JurisdiccionActividad element = (JurisdiccionActividad) iterator.next();
				//
				// if (element.getTipoImpuesto().getIdTipoImpuesto().equals(idTipoImpuestoSeleccionada)){
				// error.agregar(Error.AMCATEGORIA_CATEGORIA_REPETIDA);
				// return false;
				// }
			} catch (JurisdiccionActividadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoJurisActividad() {
		return inicializar();
	}


	public String irAModificarJurisActividad() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Jurisdicción Actividad";
		return null;
	}


	public String irAListarJurisActividad() {
		borrar();
		tituloCorto = "Listado de Jurisdicción Actividad";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarJurisActividad.jsf");
		return "";
	}


	public String listarJurisActividad() {
		jurisActividadList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idJurisActividad != null && !idJurisActividad.equals(""))
				filtro.agregarCampoOperValor("idJurisdiccionActividad", Filtro.IGUAL, new Long(idJurisActividad));

			if (idActividadSeleccionada != null && !idActividadSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("idJurisdiccionActividad", Filtro.IGUAL, idActividadSeleccionada);

			if (idJurisdiccionSeleccionada != null && !idJurisdiccionSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("jurisdiccion.idJurisdiccion", Filtro.IGUAL, idJurisdiccionSeleccionada);

			jurisActividadList = impuestoService.getJurisdiccionActividadService().getJurisdiccionActividads(filtro);
			Iterator iter = jurisActividadList.iterator();
			while (iter.hasNext())
			{
				JurisdiccionActividad element = (JurisdiccionActividad) iter.next();
				// element.getActividad().toString();
				element.getJurisdiccion().toString();
			}
			idJurisActividad = "";
			idActividadSeleccionada = new Long(0);
			idJurisdiccionSeleccionada = new Long(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarJurisActividad.jsf");
		return null;
	}


	public String getIdJurisActividad() {
		return idJurisActividad;
	}


	public void setIdJurisActividad(String idJurisActividad) {
		this.idJurisActividad = idJurisActividad;
	}


	public JurisdiccionActividad getJurisActividad() {
		return jurisActividad;
	}


	public void setJurisActividad(JurisdiccionActividad jurisActividad) {
		this.jurisActividad = jurisActividad;
	}


	public List getJurisActividadList() {
		return jurisActividadList;
	}


	public void setJurisActividadList(List jurisActividadList) {
		this.jurisActividadList = jurisActividadList;
	}

}

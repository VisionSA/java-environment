package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.LocalidadDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.LocalidadException;
import com.bizitglobal.tarjetafiel.general.exception.PaisException;
import com.bizitglobal.tarjetafiel.general.exception.PartidoException;
import com.bizitglobal.tarjetafiel.general.exception.ProvinciaException;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.general.service.LocalidadService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class LocalidadBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LocalidadBean.class);
	private Localidad localidad;
	private String nombreFiltro;

	private Long idLocalidadHidden;

	private String idLocalidad;
	private String codigoPostal;
	private String localidadAEditar;
	// definicion de un list del objeto base
	private List localidadList;

	// Listas para la presentacion(HtmlSelectItems).
	private List partidoList = new ArrayList();
	private List partidoItems = new ArrayList();

	private List provinciaList = new ArrayList();
	private List provinciaItems = new ArrayList();

	private List paisItems = new ArrayList();

	private List listaDeLocalidadesExistentes;
	// Objetos Relacionados.
	private Long idPartidoSeleccionada;
	private Long idProvinciaSeleccionada;
	private Long idPaisSeleccionado;

	private String focoHidden;

	private HtmlSelectOneMenu pais = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu provincia = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu localidadHtml = new HtmlSelectOneMenu();


	public LocalidadBean() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Localidad getLocalidad() {
		return localidad;
	}


	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


	public Long getIdLocalidadHidden() {
		return idLocalidadHidden;
	}


	public void setIdLocalidadHidden(Long idLocalidadHidden) {
		this.idLocalidadHidden = idLocalidadHidden;
	}


	public List getPartidoItems() {
		return partidoItems;
	}


	public void setPartidoItems(List partidoItems) {
		this.partidoItems = partidoItems;
	}


	public Long getIdPartidoSeleccionada() {
		return idPartidoSeleccionada;
	}


	public void setIdPartidoSeleccionada(Long idPartidoSeleccionada) {
		this.idPartidoSeleccionada = idPartidoSeleccionada;
	}


	public List getProvinciaItems() {
		return provinciaItems;
	}


	public void setProvinciaItems(List provinciaItems) {
		this.provinciaItems = provinciaItems;
	}


	public Long getIdProvinciaSeleccionada() {
		return idProvinciaSeleccionada;
	}


	public void setIdProvinciaSeleccionada(Long idProvinciaSeleccionada) {
		this.idProvinciaSeleccionada = idProvinciaSeleccionada;
	}


	public List getLocalidadList() {
		return localidadList;
	}


	public void setLocalidadList(List object) {
		this.localidadList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public List getPaisItems() {
		return paisItems;
	}


	public void setPaisItems(List paisItems) {
		this.paisItems = paisItems;
	}


	public HtmlSelectOneMenu getLocalidadHtml() {
		return localidadHtml;
	}


	public void setLocalidadHtml(HtmlSelectOneMenu localidadHtml) {
		this.localidadHtml = localidadHtml;
	}


	public HtmlSelectOneMenu getPais() {
		return pais;
	}


	public void setPais(HtmlSelectOneMenu pais) {
		this.pais = pais;
	}


	public HtmlSelectOneMenu getProvincia() {
		return provincia;
	}


	public void setProvincia(HtmlSelectOneMenu provincia) {
		this.provincia = provincia;
	}


	public Long getIdPaisSeleccionado() {
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionada) {
		this.idPaisSeleccionado = idPaisSeleccionada;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE LOCALIDAD
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amLocalidad";
	}


	public String editarLocalidad() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Localidad";
		try {
			localidad = generalService.getLocalidadService().leerLocalidad(idLocalidadHidden);
			localidadAEditar = localidad.getNombre();
			try {
				idPaisSeleccionado = localidad.getPartido().getProvincia().getPais().getIdPais();
				pais.setValue(idPaisSeleccionado);
				cambiarProvincias();
				idProvinciaSeleccionada = localidad.getPartido().getProvincia().getIdProvincia();
				provincia.setValue(idProvinciaSeleccionada);
				cambiarPartidos();
				idPartidoSeleccionada = localidad.getPartido().getIdPartido();
			} catch (NullPointerException e) {
				error.agregar("La localidad no ha sido guardada bien. Los datos se encuentran corrompidos. No es posible editarla.");
			}

			result = "amLocalidad";
		} catch (LocalidadException e1) {
			error.agregar("Ocurrio un error al intentar editar el localidad");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarLocalidad.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el localidad");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarLocalidad.jsf");
		}
		return result;
	}


	public String eliminarLocalidad() {
		try {
			generalService.getLocalidadService().borrarLocalidad(idLocalidadHidden);
			localidadList.remove(new Localidad(idLocalidadHidden));
		} catch (LocalidadException e1) {
			error.agregar("Imposible borrar el localidad. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el localidad");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarLocalidad.jsf");
		return null;
	}


	public String grabar() {
		try {
			LocalidadService localidadService = generalService.getLocalidadService();
			localidad.setNombre(localidad.getNombre().trim());
			localidad.setPartido((Partido) Util.buscarElemento(partidoList, new Partido(idPartidoSeleccionada)));
			localidad.setProvincia((Provincia) Util.buscarElemento(provinciaList, new Provincia(idProvinciaSeleccionada)));
			if (validar()) {
				log.info("Actualizando la localidad");
				if (alta) {
					localidadService.grabarLocalidad(localidad);
				} else {
					localidadService.actualizarLocalidad(localidad);
				}
				// idPartidoSeleccionada = new Long(0);
				// idProvinciaSeleccionada = new Long(0);
				popup.setPopup(popup.ICONO_OK, "La Localidad ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (LocalidadDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Localidad.");
			e1.printStackTrace();
		} catch (LocalidadException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Localidad.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Localidad.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Localidad";
		popup.borrar();

		idLocalidad = "";
		codigoPostal = "";
		localidadAEditar = "";
		idProvinciaSeleccionada = new Long(0);
		idPartidoSeleccionada = new Long(0);

		localidad = new Localidad();
		localidadList = new ArrayList();

		try {
			paisItems.clear();
			paisItems.add(new SelectItem(new Long(0), "Seleccionar País"));
			paisItems.addAll(Util.cargarSelectItem(generalService.getPaisService().getPais(new Filtro())));

			provinciaList = generalService.getProvinciaService().getProvincia(new Filtro());
			partidoList = generalService.getPartidoService().getPartido(new Filtro());

			provinciaItems.clear();
			provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));

			partidoItems.clear();
			partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));

		} catch (PaisException e) {
			e.printStackTrace();
		} catch (ProvinciaException e) {
			e.printStackTrace();
		} catch (PartidoException e) {
			e.printStackTrace();
		}
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (localidad.getNombre() == null || localidad.getNombre().equals(""))
			error.agregar(Error.AMLOCALIDAD_NOMBRE_LOCALIDAD_REQUERIDA);

		if (localidad.getCodigoPostal() == null || localidad.getCodigoPostal().equals(""))
			error.agregar(Error.AMLOCALIDAD_CODIGO_POSTAL_REQUERIDA);

		if (idPartidoSeleccionada == null || idPartidoSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMLOCALIDAD_PARTIDO_REQUERIDA);

		if (idProvinciaSeleccionada == null || idProvinciaSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMLOCALIDAD_PROVINCIA_REQUERIDA);

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada);
		try {
			listaDeLocalidadesExistentes = generalService.getLocalidadService().getLocalidad(filtro);
		} catch (LocalidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (alta) {
			if (!listaDeLocalidadesExistentes.isEmpty()) {
				Iterator iterator = listaDeLocalidadesExistentes.iterator();
				while (iterator.hasNext()) {
					Localidad element = (Localidad) iterator.next();
					if (element.getNombre().compareTo(localidad.getNombre()) == 0) {
						if (element.getPartido().getIdPartido().equals(idPartidoSeleccionada) &&
								element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
							error.agregar(Error.AMLOCALDAD_LOCALIDAD_REPETIDA);
					}
				}
			}
		} else {
			if (!listaDeLocalidadesExistentes.isEmpty()) {
				Iterator iterator = listaDeLocalidadesExistentes.iterator();
				while (iterator.hasNext()) {
					Localidad element = (Localidad) iterator.next();
					if (element.getNombre().compareTo(localidad.getNombre()) == 0 && !element.getIdLocalidad().equals(idLocalidadHidden)) {
						if (element.getPartido().getIdPartido().equals(idPartidoSeleccionada) &&
								element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada)
								&& !element.getIdLocalidad().equals(idLocalidad))
							error.agregar(Error.AMLOCALDAD_LOCALIDAD_REPETIDA);
					}
				}
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoLocalidad() {
		pais.setValue(new Long(0));

		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provicia"));

		partidoItems.clear();
		partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));

		return inicializar();
	}


	public String irAModificarLocalidad() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Localidad";

		// pais.setValue(new Long(0));
		//
		// provinciaItems.clear();
		// provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provicia"));
		//
		// partidoItems.clear();
		// partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		return null;
	}


	public String irAListarLocalidad() {
		borrar();
		tituloCorto = "Listado de Localidades";
		Session.redirect("/tarjetafiel/general/listarLocalidad.jsf");
		return "";
	}


	public String listarLocalidad() {
		localidadList = new ArrayList();
		try {
			Filtro filtro = new Filtro();

			if (idLocalidad != null && !idLocalidad.equals(""))
				filtro.agregarCampoOperValor("idLocalidad", Filtro.IGUAL, new Long(idLocalidad));
			if (codigoPostal != null && !codigoPostal.equals(""))
				filtro.agregarCampoOperValor("codigoPostal", Filtro.LIKE, codigoPostal);
			if (idPartidoSeleccionada != null && !idPartidoSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("partido.idPartido", Filtro.IGUAL, idPartidoSeleccionada);
			if (idProvinciaSeleccionada != null && !idProvinciaSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada);
			if (localidad.getNombre() != null && !localidad.getNombre().equals(""))
				filtro.agregarCampoOperValor("nombre", Filtro.LIKE, localidad.getNombre());

			localidadList = generalService.getLocalidadService().getLocalidad(filtro);
			Iterator iter = localidadList.iterator();
			while (iter.hasNext())
			{
				Localidad element = (Localidad) iter.next();
				element.getPartido().getLabel();
				element.getProvincia().getLabel();
			}
			// idLocalidad = "";
			// codigoPostal = "";
			// idPaisSeleccionado = new Long(0);
			// pais.setValue(new Long(0));
			// idPartidoSeleccionada = new Long(0);
			// idProvinciaSeleccionada = new Long(0);
			// localidad.setNombre("");
			//
			// provinciaItems.clear();
			// provinciaItems.add(new SelectItem(new Long(0),"Seleccionar Provincia"));
			//
			// partidoItems.clear();
			// partidoItems.add(new SelectItem(new Long(0),"Seleccionar Partido"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarLocalidad.jsf");
		return null;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getIdLocalidad() {
		return idLocalidad;
	}


	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		Long paisSeleccionado = new Long(pais.getValue().toString());
		partidoItems.clear();
		partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		if (!paisSeleccionado.equals(new Long(0))) {
			provinciaItems.addAll(LocalidadUtil.filtrarProvincias(provinciaList, paisSeleccionado));
			provincia.setValue(new Long(0));
		}
	}


	public void cambiarPartidos(ValueChangeEvent event) {
		Long provinciaSeleccionado = new Long(provincia.getValue().toString());
		partidoItems.clear();
		partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		if (!provinciaSeleccionado.equals(new Long(0))) {
			partidoItems.addAll(LocalidadUtil.filtrarPartido(partidoList, provinciaSeleccionado));
		}
	}


	public void cambiarProvincias() {
		Long paisSeleccionado = new Long(pais.getValue().toString());

		if (!paisSeleccionado.equals(new Long(0))) {
			provinciaItems.clear();
			provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
			provinciaItems.addAll(LocalidadUtil.filtrarProvincias(provinciaList, paisSeleccionado));
			provincia.setValue(new Long(0));

		}
	}


	public void cambiarPartidos() {
		Long provinciaSeleccionado = new Long(provincia.getValue().toString());

		if (!provinciaSeleccionado.equals(new Long(0))) {
			partidoItems.clear();
			partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
			partidoItems.addAll(LocalidadUtil.filtrarPartido(partidoList, provinciaSeleccionado));
		}
	}

}
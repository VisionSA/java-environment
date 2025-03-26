package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.BarrioDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.BarrioException;
import com.bizitglobal.tarjetafiel.general.negocio.Barrio;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class BarrioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(BarrioBean.class);
	private Barrio barrio;
	private String nombreFiltro;
	private Long idBarrioHidden;
	private boolean esRiesgoso;
	private boolean esVilla;
	private String idBarrio;
	private Localidad localidadEditada;

	// utilizado para poder grabar el barrio con el mismo nombre en caso de editarlo
	private String nombreBarrioAEditar;

	// definicion de un list del objeto base
	private List barrioList;
	private List unBarrio;

	// Listas para la presentacion(HtmlSelectItems).
	private List localidadList;
	private List localidadItems = new ArrayList();

	private List paisItem = new ArrayList();

	private List provinciaList;
	private List provinciaItem = new ArrayList();

	private List partidoList;
	private List partidoItem = new ArrayList();

	// Objetos Relacionados.
	private Long idLocalidadSeleccionada;
	private Long idPaisSeleccionado;
	private Long idProvinciaSeleccionada;
	private Long idPartidoSeleccionada;

	private HtmlSelectOneMenu pais = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu provincia = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu partido = new HtmlSelectOneMenu();

	private String focoHidden;


	public BarrioBean() {
		super();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Barrio getBarrio() {
		return barrio;
	}


	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}


	public Long getIdBarrioHidden() {
		return idBarrioHidden;
	}


	public void setIdBarrioHidden(Long idBarrioHidden) {
		this.idBarrioHidden = idBarrioHidden;
	}


	public List getLocalidadItems() {
		return localidadItems;
	}


	public void setLocalidadItems(List localidadItems) {
		this.localidadItems = localidadItems;
	}


	public Long getIdLocalidadSeleccionada() {
		return idLocalidadSeleccionada;
	}


	public void setIdLocalidadSeleccionada(Long idLocalidadSeleccionada) {
		this.idLocalidadSeleccionada = idLocalidadSeleccionada;
	}


	public List getBarrioList() {
		return barrioList;
	}


	public void setBarrioList(List object) {
		this.barrioList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public boolean isEsRiesgoso() {
		return esRiesgoso;
	}


	public void setEsRiesgoso(boolean esRiesgoso) {
		this.esRiesgoso = esRiesgoso;
	}


	public boolean isEsVilla() {
		return esVilla;
	}


	public void setEsVilla(boolean esVilla) {
		this.esVilla = esVilla;
	}


	public String getIdBarrio() {
		return idBarrio;
	}


	public void setIdBarrio(String idBarrio) {
		this.idBarrio = idBarrio;
	}


	public Long getIdPaisSeleccionado() {
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionado) {
		this.idPaisSeleccionado = idPaisSeleccionado;
	}


	public Long getIdProvinciaSeleccionada() {
		return idProvinciaSeleccionada;
	}


	public void setIdProvinciaSeleccionada(Long idProvinciaSeleccionada) {
		this.idProvinciaSeleccionada = idProvinciaSeleccionada;
	}


	public HtmlSelectOneMenu getPais() {
		return pais;
	}


	public void setPais(HtmlSelectOneMenu pais) {
		this.pais = pais;
	}


	public List getPaisItem() {
		return paisItem;
	}


	public void setPaisItem(List paisItem) {
		this.paisItem = paisItem;
	}


	public List getProvinciaItem() {
		return provinciaItem;
	}


	public void setProvinciaItem(List provinciaItem) {
		this.provinciaItem = provinciaItem;
	}


	public HtmlSelectOneMenu getProvincia() {
		return provincia;
	}


	public void setProvincia(HtmlSelectOneMenu provincia) {
		this.provincia = provincia;
	}


	public Long getIdPartidoSeleccionada() {
		return idPartidoSeleccionada;
	}


	public void setIdPartidoSeleccionada(Long idPartidoSeleccionada) {
		this.idPartidoSeleccionada = idPartidoSeleccionada;
	}


	public HtmlSelectOneMenu getPartido() {
		return partido;
	}


	public void setPartido(HtmlSelectOneMenu partido) {
		this.partido = partido;
	}


	public List getPartidoItem() {
		return partidoItem;
	}


	public void setPartidoItem(List partidoItem) {
		this.partidoItem = partidoItem;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE BARRIO
	 ************************************************************************/

	public String inicializar() {
		borrar();
		cargarListas();
		barrio = new Barrio();
		return "amBarrio";
	}


	private void cargarListas() {
		try {
			paisItem.clear();
			// paisItem.add(new SelectItem(new Long(0), "Seleccionar País"));
			paisItem.addAll(Util.cargarSelectItem(generalService.getPaisService().getPais(new Filtro())));
			idPaisSeleccionado = new Long(1);
			pais.setValue(idPaisSeleccionado);
			// provinciaList = generalService.getProvinciaService().getProvincia(new Filtro("pais.idPais",Filtro.IGUAL,idPaisSeleccionado));
			provinciaItem.clear();
			provinciaItem.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
			// provinciaItem.addAll(Util.cargarSelectItem(provinciaList));
			provinciaItem.addAll(Util.cargarSelectItem(generalService.getProvinciaService().getProvincia(
					new Filtro("pais.idPais", Filtro.IGUAL, idPaisSeleccionado))));
			idProvinciaSeleccionada = new Long(1);
			provincia.setValue(idProvinciaSeleccionada);
			partidoItem.clear();
			partidoItem.add(new SelectItem(new Long(0), "Seleccionar Partido"));
			partidoItem.addAll(Util.cargarSelectItem(generalService.getPartidoService().getPartido(
					new Filtro("provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada))));
			idPartidoSeleccionada = new Long(0);
			partido.setValue(idPartidoSeleccionada);
			localidadItems.clear();
			localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String editarBarrio() {
		String result = null;
		cargarListas();
		popup.borrar();
		error.borrar();
		alta = false;
		tituloCorto = "Modificar Barrio";
		try {
			Iterator iter = barrioList.iterator();
			while (iter.hasNext())
			{
				Barrio element = (Barrio) iter.next();
				if (element.getIdBarrio().compareTo(idBarrioHidden) == 0) {
					barrio = element;
					break;
				}
			}
			nombreBarrioAEditar = barrio.getDescripcion();
			localidadEditada = barrio.getLocalidad();
			if (barrio.getEsRiesgozo().equals(new Character('S')))
				esRiesgoso = true;
			if (barrio.getEsVilla().equals(new Character('S')))
				esVilla = true;
			try {
				idPaisSeleccionado = barrio.getLocalidad().getProvincia().getPais().getIdPais();
				pais.setValue(idPaisSeleccionado);
				cambiarProvincias(null);
				idProvinciaSeleccionada = barrio.getLocalidad().getProvincia().getIdProvincia();
				provincia.setValue(idProvinciaSeleccionada);
				cambiarPartidos(null);
				idPartidoSeleccionada = barrio.getLocalidad().getPartido().getIdPartido();
				partido.setValue(idPartidoSeleccionada);
				cambiarLocalidades(null);
				idLocalidadSeleccionada = barrio.getLocalidad().getIdLocalidad();
			} catch (NullPointerException e) {
				error.agregar("El barrio no ha sido guardado bien. Los datos se encuentran corrompidos. No es posible editarlo.");
			}

			result = "amBarrio";
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el barrio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarBarrio.jsf");
		}
		return result;
	}


	public String eliminarBarrio() {
		try {
			generalService.getBarrioService().borrarBarrio(idBarrioHidden);
			barrioList.remove(new Barrio(idBarrioHidden));
		} catch (BarrioException e1) {
			error.agregar("Imposible borrar el barrio. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el barrio");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarBarrio.jsf");
		return null;
	}


	public String grabar() {
		try {
			barrio.setDescripcion(barrio.getDescripcion().trim());
			if (esRiesgoso)
				barrio.setEsRiesgozo(new Character('S'));
			else
				barrio.setEsRiesgozo(new Character('N'));
			if (esVilla)
				barrio.setEsVilla(new Character('S'));
			else
				barrio.setEsVilla(new Character('N'));
			if (alta) {
				barrio.setLocalidad((Localidad) Util.buscarElemento(localidadList, new Localidad(idLocalidadSeleccionada)));
			}
			else {
				if (localidadEditada.getIdLocalidad().compareTo(idLocalidadSeleccionada) != 0) {
					barrio.setLocalidad((Localidad) Util.buscarElemento(localidadList, new Localidad(idLocalidadSeleccionada)));
				}
			}
			if (validar()) {
				if (alta) {
					generalService.getBarrioService().grabarBarrio(barrio);
				} else {
					generalService.getBarrioService().actualizarBarrio(barrio);
				}
				popup.setPopup(popup.ICONO_OK, "El Barrio ha sido almacenado exitosamente.");
			}
		} catch (BarrioDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Barrio.");
			e1.printStackTrace();
		} catch (BarrioException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Barrio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Barrio.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		esRiesgoso = true;
		esVilla = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Barrio";
		idBarrio = "";
		barrioList = new ArrayList();
		localidadEditada = null;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (barrio.getDescripcion() == null || barrio.getDescripcion().equals(new String(""))) {
			error.agregar(Error.AMBARRIO_DESCRIPCION_REQUERIDO);
		}
		if (idLocalidadSeleccionada == null || idLocalidadSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMBARRIO_LOCALIDAD_REQUERIDA);
		if (!error.hayErrores()) {
			// verificar si el barrio no existe...

			try {
				if (localidadEditada != null && localidadEditada.getIdLocalidad().compareTo(idLocalidadSeleccionada) != 0) {
					unBarrio = generalService.getBarrioService()
							.getBarrio(new Filtro("localidad.idLocalidad", Filtro.IGUAL, idLocalidadSeleccionada));
				} else {
					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor("localidad.idLocalidad", Filtro.IGUAL, idLocalidadSeleccionada);
					if (barrio.getIdBarrio() != null)
						filtro.agregarCampoOperValor("idBarrio", Filtro.NOTIN, "(" + barrio.getIdBarrio() + ")");
					unBarrio = generalService.getBarrioService().getBarrio(filtro);
				}

			} catch (BarrioException e) {
				e.printStackTrace();
			}
			if (alta) {
				if (!unBarrio.isEmpty()) {
					Iterator iterator = unBarrio.iterator();
					while (iterator.hasNext()) {
						Barrio element = (Barrio) iterator.next();
						if (element.getDescripcion().toUpperCase().compareTo(barrio.getDescripcion().toUpperCase()) == 0) {
							error.agregar(Error.AMBARRIO_BARRIO_REPETIDO);
						}
					}
				}
			} else {
				if (!unBarrio.isEmpty()) {
					Iterator iterator = unBarrio.iterator();
					while (iterator.hasNext()) {
						Barrio element = (Barrio) iterator.next();
						if (element.getDescripcion().toUpperCase().compareTo(barrio.getDescripcion().toUpperCase()) == 0) {
							if (!element.getIdBarrio().equals(barrio.getIdBarrio()))
								error.agregar(Error.AMBARRIO_BARRIO_REPETIDO);
						}
					}
				}
			}
		}
		unBarrio = null;
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoBarrio() {

		pais.setValue(new Long(0));
		provinciaItem.clear();
		provinciaItem.add(new SelectItem(new Long(0), "Seleccionar Provincia"));

		localidadItems.clear();
		localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));

		return inicializar();
	}


	public String irAModificarBarrio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Barrio";
		nombreBarrioAEditar = barrio.getDescripcion();
		localidadEditada = barrio.getLocalidad();

		return null;
	}


	public String irAListarBarrio() {
		barrio = new Barrio();
		barrioList = new ArrayList();
		cargarListas();
		tituloCorto = "Listado de Barrio";
		Session.redirect("/tarjetafiel/general/listarBarrio.jsf");
		return "";
	}


	public String listarBarrio() {
		barrioList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idBarrio != null && !idBarrio.equals(""))
				filtro.agregarCampoOperValor("idBarrio", Filtro.IGUAL, new Long(idBarrio));

			if (barrio.getDescripcion() != null && !barrio.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, barrio.getDescripcion());

			// if(esRiesgoso)
			// filtro.agregarCampoOperValor("esRiesgozo", Filtro.LIKEXS, new Character('S'));
			//
			// if(esVilla)
			// filtro.agregarCampoOperValor("esVilla", Filtro.LIKEXS, new Character('S'));

			if (idPartidoSeleccionada != null && !idPartidoSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("localidad.partido.idPartido", Filtro.IGUAL, idPartidoSeleccionada);

			if (idProvinciaSeleccionada != null && !idProvinciaSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("localidad.provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada);

			if (idLocalidadSeleccionada != null && !idLocalidadSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("localidad.idLocalidad", Filtro.IGUAL, idLocalidadSeleccionada);

			barrioList = generalService.getBarrioService().getBarrio(filtro);
			Iterator iter = barrioList.iterator();
			while (iter.hasNext())
			{
				Barrio element = (Barrio) iter.next();
				element.getLocalidad().getLabel();
				element.getLocalidad().getProvincia().getPais();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarBarrio.jsf");
		return null;
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		Long paisSeleccionado = new Long(pais.getValue().toString());
		try {
			provinciaItem.clear();
			provinciaItem.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
			provinciaItem.addAll(Util.cargarSelectItem(generalService.getProvinciaService().getProvincia(
					new Filtro("pais.idPais", Filtro.IGUAL, paisSeleccionado))));
			idProvinciaSeleccionada = new Long(0);
			provincia.setValue(idProvinciaSeleccionada);
			partidoItem.clear();
			partidoItem.add(new SelectItem(new Long(0), "Seleccionar Partido"));
			idPartidoSeleccionada = new Long(0);
			partido.setValue(idPartidoSeleccionada);
			localidadItems.clear();
			localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
			idLocalidadSeleccionada = new Long(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void cambiarPartidos(ValueChangeEvent event) {
		Long provinciaSeleccionado = new Long(provincia.getValue().toString());
		try {
			partidoItem.clear();
			partidoItem.add(new SelectItem(new Long(0), "Seleccionar Partido"));
			partidoItem.addAll(Util.cargarSelectItem(generalService.getPartidoService().getPartido(
					new Filtro("provincia.idProvincia", Filtro.IGUAL, provinciaSeleccionado))));
			idPartidoSeleccionada = new Long(0);
			partido.setValue(idPartidoSeleccionada);
			localidadItems.clear();
			localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
			idLocalidadSeleccionada = new Long(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void cambiarLocalidades(ValueChangeEvent event) {
		Long partidoSeleccionado = new Long(partido.getValue().toString());
		try {
			localidadList = generalService.getLocalidadService().getLocalidad(new Filtro("partido.idPartido", Filtro.IGUAL, partidoSeleccionado));
			localidadItems.clear();
			localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
			localidadItems.addAll(Util.cargarSelectItem(localidadList));
			idLocalidadSeleccionada = new Long(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void cambiarProvincias(){
	// Long paisSeleccionado = new Long(pais.getValue().toString());
	//
	// if(!paisSeleccionado.equals(new Long(0))){
	// provinciaItem.clear();
	// provinciaItem.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
	// provinciaItem.addAll(BarrioUtil.filtrarProvincias(provinciaList,paisSeleccionado));
	// provincia.setValue(new Long(0));
	// }
	// localidadItems.clear();
	// localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
	// }
	//
	// public void cambiarLocalidades(){
	// Long provinciaSeleccionado = new Long(provincia.getValue().toString());
	//
	// if(!provinciaSeleccionado.equals(new Long(0))){
	// localidadItems.clear();
	// localidadItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
	// localidadItems.addAll(BarrioUtil.filtrarLocalidadesProvincia(localidadList, provinciaSeleccionado));
	// }
	// }

}
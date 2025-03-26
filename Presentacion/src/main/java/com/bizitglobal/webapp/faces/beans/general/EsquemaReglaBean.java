package com.bizitglobal.webapp.faces.beans.general;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.general.exception.EsquemaException;
import com.bizitglobal.tarjetafiel.general.exception.EsquemaReglaException;
import com.bizitglobal.tarjetafiel.general.exception.ReglaException;
import com.bizitglobal.tarjetafiel.general.negocio.Esquema;
import com.bizitglobal.tarjetafiel.general.negocio.EsquemaRegla;
import com.bizitglobal.tarjetafiel.general.negocio.Regla;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class EsquemaReglaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(EsquemaReglaBean.class);
	private Regla regla;
	private Esquema esquemaActual;

	// definicion de un list del objeto base
	private List tablaEsqRegra = new ArrayList();

	// Listas para la presentacion(HtmlSelectItems).
	private List esquemaList = new ArrayList();
	private List esquemaItems = new ArrayList();
	private List reglasList = new ArrayList();
	private List reglasItems = new ArrayList();

	private List ponderacionItems = new ArrayList();

	// Objetos Relacionados.
	private Long idEsquemaSeleccionado;
	private Long idReglaSeleccionada;

	private HtmlSelectOneMenu esquema;

	private String focoHidden;


	public EsquemaReglaBean() {
		super();
		borrar();
		ponderacionItems.add(new SelectItem(new Integer(0), "Permisiva"));
		ponderacionItems.add(new SelectItem(new Integer(1), "Restrictiva"));
	}


	public Regla getRegla() {
		return regla;
	}


	public void setRegla(Regla regla) {
		this.regla = regla;
	}


	public List getReglasItems() {
		return reglasItems;
	}


	public void setReglasItems(List reglasItems) {
		this.reglasItems = reglasItems;
	}


	public Long getIdReglaSeleccionada() {
		return idReglaSeleccionada;
	}


	public void setIdReglaSeleccionada(Long idReglaSeleccionada) {
		this.idReglaSeleccionada = idReglaSeleccionada;
	}


	public List getTablaEsqRegra() {
		return tablaEsqRegra;
	}


	public void setTablaEsqRegra(List tablaEsqRegra) {
		this.tablaEsqRegra = tablaEsqRegra;
	}


	public List getEsquemaItems() {
		return esquemaItems;
	}


	public void setEsquemaItems(List esquemaItems) {
		this.esquemaItems = esquemaItems;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Long getIdEsquemaSeleccionado() {
		return idEsquemaSeleccionado;
	}


	public void setIdEsquemaSeleccionado(Long idEsquemaSeleccionado) {
		this.idEsquemaSeleccionado = idEsquemaSeleccionado;
	}


	public HtmlSelectOneMenu getEsquema() {
		return esquema;
	}


	public void setEsquema(HtmlSelectOneMenu esquema) {
		this.esquema = esquema;
	}


	public List getPonderacionItems() {
		return ponderacionItems;
	}


	public void setPonderacionItems(List ponderacionItems) {
		this.ponderacionItems = ponderacionItems;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE REGAS
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		try {
			reglasList = generalService.getReglaService().getRegla(new Filtro());
		} catch (ReglaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarItems();
		return "amEsqReglas";
	}


	private void cargarItems() {
		esquemaItems.clear();
		esquemaItems.add(new SelectItem(new Long(0), "Seleccionar Esquema"));
		esquemaItems.addAll(Util.cargarSelectItem(esquemaList));
		reglasItems.clear();
		reglasItems.add(new SelectItem(new Long(0), "Seleccionar Regla"));
		reglasItems.addAll(Util.cargarSelectItem(reglasList));
	}


	public String editarRegla() {
		error.borrar();
		if (!idReglaSeleccionada.equals(new Long(0))) {
			alta = false;
			regla = (Regla) Util.buscarElemento(reglasList, new Regla(idReglaSeleccionada));
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/amReglaPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',850,250), 'titlebar=no';");
		} else {
			error.agregar("Debe seleccionar una regla para poderla editar");
		}
		return "";
	}


	public String nuevaRegla() {
		alta = true;
		regla = new Regla();
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/amReglaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',850,250), 'titlebar=no';");
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		log.info("Ejecutando ==> recargarYCerrarPopup Reglas");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar();window.close();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
	}


	public String grabarRegla() {
		try {
			log.info("desc al grabar " + regla.getLabel());
			if (alta) {
				generalService.getReglaService().grabarRegla(regla);
				reglasList.add(regla);
			} else {
				generalService.getReglaService().actualizarRegla(regla);
			}
		} catch (ReglaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarItems();
		// while (iterRegla.hasNext()) {
		// ReglaWrap reglaWrap = (ReglaWrap) iterRegla.next();
		// try {
		// generalService.getEsquemaReglaService().actualizarEsquemaRegla(reglaWrap.getEsqRegla());
		// popup.setPopup(popup.ICONO_OK, "La confuguracion de reeglas ha sido almacenada exitosamente.");
		// } catch (EsquemaReglaException e) {
		// popup.setPopup(popup.ICONO_FALLA, "Fallo al grabar.");
		// e.printStackTrace();
		// return "";
		// }
		// }
		return "";
	}


	public String cancelarRegla() {
		recargarYCerrarPopup(null);
		return null;
	}


	public String grabar() {
		Iterator iterRegla = tablaEsqRegra.iterator();
		while (iterRegla.hasNext()) {
			ReglaWrap reglaWrap = (ReglaWrap) iterRegla.next();
			try {
				generalService.getEsquemaReglaService().actualizarEsquemaRegla(reglaWrap.getEsqRegla());
				popup.setPopup(popup.ICONO_OK, "La confuguracion de reeglas ha sido almacenada exitosamente.");
			} catch (EsquemaReglaException e) {
				popup.setPopup(popup.ICONO_FALLA, "Fallo al grabar.");
				e.printStackTrace();
				return "";
			}
		}
		return "";
	}


	public void borrar() {
		super.borrarBaseBean();
		regla = new Regla();
		alta = true;
		esquemaActual = null;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Administración de Reglas";
		tablaEsqRegra.clear();
		idEsquemaSeleccionado = new Long(0);
		idReglaSeleccionada = new Long(0);
		esquemaList.clear();
		reglasList.clear();
		esquema = new HtmlSelectOneMenu();
		try {
			esquemaList = generalService.getEsquemaService().getEsquema(new Filtro());
		} catch (EsquemaException e) {
			e.printStackTrace();
		}

		// try {
		// paisItems.clear();
		// paisItems.add(new SelectItem(new Long(0),"Seleccionar País"));
		// paisItems.addAll(Util.cargarSelectItem(generalService.getPaisService().getPais(new Filtro())));
		// provinciaList = generalService.getProvinciaService().getProvincia(new Filtro());
		// }
		// catch (PaisException e) {
		// e.printStackTrace();
		// }
		// catch (ProvinciaException e) {
		// e.printStackTrace();
		// }
		//
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		// if(partido.getDescripcion() == null || partido.getDescripcion().equals(""))
		// error.agregar(Error.AMPARTIDO_DESCRIPCION_REQUERIDA);
		//
		// if(idProvinciaSeleccionada == null || idProvinciaSeleccionada.equals(new Long(0)))
		// error.agregar(Error.AMPARTIDO_PROVINCIA_REQUERIDA);
		//
		// if(alta){
		// if(!unPartido.isEmpty()){
		// Iterator iterator = unPartido.iterator();
		// while (iterator.hasNext()) {
		// Partido element = (Partido) iterator.next();
		// if (element.getDescripcion().compareTo(partido.getDescripcion())==0) {
		// if(element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
		// error.agregar(Error.AMPARTIDO_PARTIDO_REPETIDO);
		// }
		// }
		// }
		// } else {
		// if(!unPartido.isEmpty()){
		// Iterator iterator = unPartido.iterator();
		// while (iterator.hasNext()) {
		// Partido element = (Partido) iterator.next();
		// if (element.getDescripcion().compareTo(partido.getDescripcion())==0&&!element.getIdPartido().equals(idPartidoHidden)) {
		// if(element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
		// error.agregar(Error.AMPARTIDO_PARTIDO_REPETIDO);
		// }
		// }
		// }
		// }
		return !error.hayErrores();
	}


	public String irANuevoPartido() {
		// pais.setValue(new Long(0));
		//
		// provinciaItems.clear();
		// provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));

		return inicializar();
	}


	public String irASeguirModificando() {
		popup.borrar();
		// pais.setValue(new Long(0));
		//
		// provinciaItems.clear();
		// provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		return null;
	}


	public String irASalir() {
		return cancelar();
	}


	public String addRegla() {
		error.borrar();
		if (esquemaActual == null) {
			error.agregar("Debe seleccionar un esquema para poder agregar reglas");
			return null;
		}
		regla = (Regla) Util.buscarElemento(reglasList, new Regla(idReglaSeleccionada));
		EsquemaRegla esquemaRegla = new EsquemaRegla(null, "", "S", "S", new Integer(0), esquemaActual, regla);
		try {
			log.info("Intentando grabar el esqRegla");
			generalService.getEsquemaReglaService().grabarEsquemaRegla(esquemaRegla);
			generalService.getEsquemaReglaService().getEsquemaRegla(
					new Filtro("esquema.idEsquema", Filtro.IGUAL, esquemaActual.getIdEsquema()));
			tablaEsqRegra.add(new ReglaWrap(esquemaRegla));
		} catch (EsquemaReglaException e) {
			error.agregar("Error al agregar regla. No se permite dos veces la misma regla en el esquema.");
			e.printStackTrace();
		} catch (Exception e) {
			error.agregar("Error al agregar regla. No se permite dos veces la misma regla en el esquema.");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/amEsqReglas.jsf");
		return null;
	}


	public void cambiarEsquema(ValueChangeEvent event) {
		Long esquemaSeleccionado = new Long(esquema.getValue().toString());
		esquemaActual = (Esquema) Util.buscarElemento(esquemaList, new Esquema(esquemaSeleccionado));
		tablaEsqRegra.clear();
		try {
			Filtro filtro = new Filtro("esquema.idEsquema", Filtro.IGUAL, esquemaSeleccionado);
			filtro.agregarOrderBy("regla.idRegla");
			Iterator iterRegla = generalService.getEsquemaReglaService().getEsquemaRegla(filtro).iterator();
			while (iterRegla.hasNext()) {
				EsquemaRegla esq = (EsquemaRegla) iterRegla.next();
				esq.getRegla().getDescripcion();
				tablaEsqRegra.add(new ReglaWrap(esq));
			}
		} catch (EsquemaReglaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/amEsqReglas.jsf");
	}

	public class ReglaWrap {
		private EsquemaRegla esqRegla;


		public ReglaWrap(EsquemaRegla esqRegla) {
			super();
			this.esqRegla = esqRegla;
		}


		public boolean getManual() {
			return Convertidores.getBoolean(esqRegla.getEsManual());
		}


		public void setManual(boolean manual) {
			esqRegla.setEsManual(Convertidores.getSorN(manual));
		}


		public boolean getActiva() {
			return Convertidores.getBoolean(esqRegla.getActiva());
		}


		public void setActiva(boolean activa) {
			esqRegla.setActiva(Convertidores.getSorN(activa));
		}


		public EsquemaRegla getEsqRegla() {
			return esqRegla;
		}


		public void setEsqRegla(EsquemaRegla esqRegla) {
			this.esqRegla = esqRegla;
		}

	}
}

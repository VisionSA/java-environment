package com.bizitglobal.webapp.faces.beans.impuestos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class RetencionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(RetencionBean.class);

	// Propiedad que define los datos que se quieren grabar, leer, modificar.
	private Retencion retencion;
	private List jurisdiccionesList;

	// Propiedades de ayuda para el bean.
	private boolean acumulaPagos;
	private Date vigenciaDesde;
	private Date vigenciaHasta;
	private List listaProvincias = new ArrayList();
	private List listaCategorias;
	private List categoriaItems = new ArrayList();
	private List listaJurisdicciones = new ArrayList();
	private List listaAplicables = new ArrayList();
	private List listaActividades = new ArrayList();
	private Long provinciaSeleccionada;
	private Long categoriaSeleccionada;
	private Long jurisdiccionSeleccionada;
	private Long aplicableSeleccionada;
	private Long activodadSeleccionada;
	private List tramos;
	private List tramosLeidos;

	// Propiedades utilizadas para cambiar entre el alta y la modificacion.
	private String tituloLargo;
	private String tituloCorto;

	// Indica si estamos en un alta o baja de una retencion.
	private boolean alta;

	// Lista que contiene los retenciones a mostrar(en el listado)
	private List retenciones;

	// Filtros para el listado de individuos.
	private String codigoFiltro;
	private String descripcionFiltro;

	// Individuo hidden, que sirve para borrar o editar el individuo.
	private Long idRetencionHidden;

	private Long idTipoImpSeleccionada;

	// Listas para la presentacion(HtmlSelectItems).
	private List tipoImpList = new ArrayList();
	private List tipoImpItems = new ArrayList();
	private HtmlSelectOneMenu tipoImpHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu categHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu jurisHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu aplicHtml = new HtmlSelectOneMenu();


	public RetencionBean() {
		log.info("Construyendo RetencionBean!!!");
		limpiarBean();
	}


	public HtmlSelectOneMenu getCategHtml() {
		return categHtml;
	}


	public void setCategHtml(HtmlSelectOneMenu categHtml) {
		this.categHtml = categHtml;
	}


	public HtmlSelectOneMenu getJurisHtml() {
		return jurisHtml;
	}


	public void setJurisHtml(HtmlSelectOneMenu jurisHtml) {
		this.jurisHtml = jurisHtml;
	}


	public HtmlSelectOneMenu getAplicHtml() {
		return aplicHtml;
	}


	public void setAplicHtml(HtmlSelectOneMenu aplicHtml) {
		this.aplicHtml = aplicHtml;
	}


	public List getCategoriaItems() {
		return categoriaItems;
	}


	public void setCategoriaItems(List categoriaItems) {
		this.categoriaItems = categoriaItems;
	}


	public Long getIdTipoImpSeleccionada() {
		return idTipoImpSeleccionada;
	}


	public void setIdTipoImpSeleccionada(Long idTipoImpSeleccionada) {
		this.idTipoImpSeleccionada = idTipoImpSeleccionada;
	}


	public HtmlSelectOneMenu getTipoImpHtml() {
		return tipoImpHtml;
	}


	public void setTipoImpHtml(HtmlSelectOneMenu tipoImpHtml) {
		this.tipoImpHtml = tipoImpHtml;
	}


	public List getTipoImpItems() {
		return tipoImpItems;
	}


	public void setTipoImpItems(List tipoImpItems) {
		this.tipoImpItems = tipoImpItems;
	}


	public Long getActivodadSeleccionada() {
		return activodadSeleccionada;
	}


	public void setActivodadSeleccionada(Long activodadSeleccionada) {
		this.activodadSeleccionada = activodadSeleccionada;
	}


	public List getListaActividades() {
		return listaActividades;
	}


	public void setListaActividades(List listaActividades) {
		this.listaActividades = listaActividades;
	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Retencion getRetencion() {
		return retencion;
	}


	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}


	public boolean getAcumulaPagos() {
		return acumulaPagos;
	}


	public void setAcumulaPagos(boolean acumulaPagos) {
		this.acumulaPagos = acumulaPagos;
	}


	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}


	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}


	public Long getAplicableSeleccionada() {
		return aplicableSeleccionada;
	}


	public void setAplicableSeleccionada(Long aplicableSeleccionada) {
		this.aplicableSeleccionada = aplicableSeleccionada;
	}


	public Long getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}


	public void setCategoriaSeleccionada(Long categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}


	public String getCodigoFiltro() {
		return codigoFiltro;
	}


	public void setCodigoFiltro(String codigoFiltro) {
		this.codigoFiltro = codigoFiltro;
	}


	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}


	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}


	public Long getIdRetencionHidden() {
		return idRetencionHidden;
	}


	public void setIdRetencionHidden(Long idRetencionHidden) {
		this.idRetencionHidden = idRetencionHidden;
	}


	public Long getJurisdiccionSeleccionada() {
		return jurisdiccionSeleccionada;
	}


	public void setJurisdiccionSeleccionada(Long jurisdiccionSeleccionada) {
		this.jurisdiccionSeleccionada = jurisdiccionSeleccionada;
	}


	public List getListaAplicables() {
		return listaAplicables;
	}


	public void setListaAplicables(List listaAplicables) {
		this.listaAplicables = listaAplicables;
	}


	public List getListaCategorias() {
		return listaCategorias;
	}


	public void setListaCategorias(List listaCategorias) {
		this.listaCategorias = listaCategorias;
	}


	public List getListaJurisdicciones() {
		return listaJurisdicciones;
	}


	public void setListaJurisdicciones(List listaJurisdicciones) {
		this.listaJurisdicciones = listaJurisdicciones;
	}


	public List getListaProvincias() {
		return listaProvincias;
	}


	public void setListaProvincias(List listaProvincias) {
		this.listaProvincias = listaProvincias;
	}


	public Long getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}


	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}


	public List getRetenciones() {
		return retenciones;
	}


	public void setRetenciones(List retenciones) {
		this.retenciones = retenciones;
	}


	public List getTramos() {
		return tramos;
	}


	public void setTramos(List tramos) {
		this.tramos = tramos;
	}


	public List getTramosLeidos() {
		return tramosLeidos;
	}


	public void setTramosLeidos(List tramosLeidos) {
		this.tramosLeidos = tramosLeidos;
	}


	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}


	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}


	/*
	 * ACCIONES DEL BEAN DE INDIVIDUOS
	 */

	public String grabar() {
		if (validar()) {
			try {
				retencion.setAplicable(impuestoService.getAplicableService().leerAplicable(aplicableSeleccionada));
				// retencion.setCategoria(impuestoService.getCategoriaService().leerCategoria(categoriaSeleccionada));
				// Filtro filtro = new Filtro("jurisdiccion.idJurisdiccion", Filtro.IGUAL, jurisdiccionSeleccionada);
				// filtro.agregarCampoOperValor("actividad.idActividad", Filtro.IGUAL, activodadSeleccionada);
				// List juriActiList = impuestoService.getJurisdiccionActividadService().getJurisdiccionActividads(filtro);
				retencion.setJuridiccionActividad(impuestoService.getJurisdiccionActividadService().leerJurisdiccionActividad(activodadSeleccionada));
				retencion.setProvincia(generalService.getProvinciaDao().buscarProvincia(provinciaSeleccionada));
				retencion.setAcumulaPagos(acumulaPagos ? "S" : "N");
				retencion.setVigenciaHasta(new Timestamp(vigenciaHasta.getTime()));
				retencion.setVigenciaDesde(new Timestamp(vigenciaDesde.getTime()));

				if (alta) {
					impuestoService.getRetencionService().grabarRetencion(retencion);
				} else {
					impuestoService.getRetencionService().actualizarRetencion(retencion);
				}

				RetencionUtil.abTramos(tramos, tramosLeidos, retencion, impuestoService.getTramosRetencionService());

				// Sincronizamos las tramos en memoria
				if (!tramos.isEmpty()) {
					Iterator iter = tramos.iterator();
					while (iter.hasNext()) {
						TramosRetencion temp = (TramosRetencion) iter.next();
						tramosLeidos.add(temp.getIdTramoRetencion());
					}
				}

				popup.setPopup(popup.ICONO_OK, "La retención ha sido almacenada exitosamente.");
			} catch (RetencionException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la retención.");
				e.printStackTrace();
			} catch (Exception e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la retención.");
				e.printStackTrace();
			}
		}

		return null;
	}


	public String cancelar() {
		listarRetenciones();
		return "";
	}


	public String eliminarTramo() {
		log.info("Eliminar tramo!!!");
		Long idTramo = new Long(Session.getRequestParameter("idTramo"));
		tramos = RetencionUtil.borrarTramo(tramos, idTramo);

		return null;
	}


	public String irANuevaRetencion() {
		return inicializar();
	}


	public String irAModificarRetencion() {
		alta = false;
		tituloCorto = "Modificación de retención";
		tituloLargo = "Tarjeta Fiel - Modificación de Retención";
		popup.borrar();
		return "altaRetencion";
	}


	public String irAListarRetencion() {
		return listarRetenciones();
	}


	public String listarRetenciones() {
		inicializar();
		tituloLargo = "Tarjeta Fiel - Listado de Retenciones";
		tituloCorto = "Listado de Retenciones";
		Session.redirect("/tarjetafiel/impuestos/retenciones/listarRetencion.jsf");
		return null;
	}


	public void limpiarBean() {
		retencion = new Retencion();
		acumulaPagos = false;
		retenciones = new ArrayList();
		tituloLargo = "Tarjeta Fiel - Alta de Retenciones";
		tituloCorto = "Alta de Retenciones";
		alta = true;
		codigoFiltro = null;
		descripcionFiltro = null;
		tramos = new ArrayList();
		tramosLeidos = new ArrayList();
		vigenciaDesde = null;
		vigenciaHasta = null;
		popup.borrar();
		categoriaItems.clear();
		listaJurisdicciones.clear();
		jurisHtml.setValue(new Long(0));
		categHtml.setValue(new Long(0));
		aplicHtml.setValue(new Long(0));

		idTipoImpSeleccionada = new Long(0);
		tipoImpHtml.setValue(new Long(0));
		categoriaSeleccionada = new Long(0);
		jurisdiccionSeleccionada = new Long(0);
		activodadSeleccionada = new Long(0);
		try {
			try {
				tipoImpList = impuestoService.getTipoImpuestoService().getTipoImpuesto(new Filtro());
			} catch (TipoImpuestoException e1) {
				e1.printStackTrace();
			}
			jurisdiccionesList = impuestoService.getJurisdiccionDao().listarTodos(new Filtro());
			listaCategorias = impuestoService.getCategoriaService().getCategorias(new Filtro());
			listaProvincias.clear();
			listaProvincias.add(new SelectItem(new Long(0), "Seleccione Provincia"));
			listaProvincias.addAll(Util.cargarSelectItem(generalService.getProvinciaDao().listarTodos()));
			listaAplicables.clear();
			listaAplicables.add(new SelectItem(new Long(0), "Seleccione Aplicable"));
			listaAplicables.addAll(Util.cargarSelectItem(impuestoService.getAplicableService().getAplicables(new Filtro())));
			listaActividades.clear();
			listaActividades.add(new SelectItem(new Long(0), "Seleccione"));
			if ((tipoImpItems.size() - 1) != tipoImpList.size()) {
				tipoImpItems = new ArrayList();
				tipoImpItems.add(new SelectItem(new Long(0), "Seleccionar Tipo Impuesto"));
				tipoImpItems.addAll(Util.cargarSelectItem(tipoImpList));
			}
		} catch (CategoriaException e) {
			e.printStackTrace();
		} catch (AplicableException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}


	public String filtrarRetenciones() {
		try {
			Filtro filtro = new Filtro();
			Long long0 = new Long(0);
			if (descripcionFiltro != null && !descripcionFiltro.equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, descripcionFiltro);

			if (activodadSeleccionada != null && !activodadSeleccionada.equals(long0))
				filtro.agregarCampoOperValor("juridiccionActividad.idJurisdiccionActividad", Filtro.IGUAL, activodadSeleccionada);

			if (categoriaSeleccionada != null && !categoriaSeleccionada.equals(long0))
				filtro.agregarCampoOperValor("juridiccionActividad.categoria.idCategoria", Filtro.IGUAL, categoriaSeleccionada);

			if (jurisdiccionSeleccionada != null && !jurisdiccionSeleccionada.equals(long0))
				filtro.agregarCampoOperValor("juridiccionActividad.jurisdiccion.idJurisdiccion", Filtro.IGUAL, jurisdiccionSeleccionada);

			if (aplicableSeleccionada != null && !aplicableSeleccionada.equals(long0))
				filtro.agregarCampoOperValor("juridiccionActividad.aplicable.idAplicable", Filtro.IGUAL, aplicableSeleccionada);

			if (idTipoImpSeleccionada != null && !idTipoImpSeleccionada.equals(long0))
				filtro.agregarCampoOperValor("juridiccionActividad.categoria.tipoImpuesto.idTipoImpuesto", Filtro.IGUAL, idTipoImpSeleccionada);

			retenciones = impuestoService.getRetencionService().getRetenciones(filtro);
		} catch (RetencionException e) {
			e.printStackTrace();
		}

		Session.redirect("/tarjetafiel/impuestos/retenciones/listarRetencion.jsf");
		return null;
	}


	public void cambiarCategorias(ValueChangeEvent event) {
		Long idTipoImpSeleccionado = (Long) tipoImpHtml.getValue();
		listarCategorias(idTipoImpSeleccionado);
		listarJurisdicciones(idTipoImpSeleccionado);
	}


	private void listarJurisdicciones(Long idTipoImpSeleccionado) {
		if (!idTipoImpSeleccionado.equals(new Long(0))) {
			listaJurisdicciones.clear();
			listaJurisdicciones.add(new SelectItem(new Long(0), "Seleccione jurisdicción"));
			listaJurisdicciones.addAll(RetencionUtil.cargarJurisdicciones(idTipoImpSeleccionado, impuestoService.getJurisTipoImpuestoService()));
		} else {
			listaJurisdicciones.clear();
			listaJurisdicciones.add(new SelectItem(new Long(0), "Seleccione jurisdicción"));
		}
	}


	private void listarCategorias(Long idTipoImpSeleccionado) {
		if (!idTipoImpSeleccionado.equals(new Long(0))) {
			categoriaItems.clear();
			categoriaItems.add(new SelectItem(new Long(0), "Seleccione Categoría"));
			categoriaItems.addAll(ImpuestoUtil.filtrarCategorias(listaCategorias, idTipoImpSeleccionado));
		} else {
			categoriaItems.clear();
			categoriaItems.add(new SelectItem(new Long(0), "Seleccione Categoría"));
		}
	}


	public String editarRetencion() {
		log.info("Entrando a editar retencion!!! -> " + idRetencionHidden);
		String result = null;
		limpiarBean();
		try {
			retencion = impuestoService.getRetencionService().leerRetencion(idRetencionHidden);
			acumulaPagos = (retencion.getAcumulaPagos().equals("S") ? true : false);
			// categoriaSeleccionada = retencion.getCategoria().getIdCategoria();
			jurisdiccionSeleccionada = retencion.getJuridiccionActividad().getJurisdiccion().getIdJurisdiccion();
			aplicableSeleccionada = retencion.getAplicable().getIdAplicable();
			activodadSeleccionada = retencion.getJuridiccionActividad().getIdJurisdiccionActividad();
			categoriaSeleccionada = retencion.getJuridiccionActividad().getCategoria().getIdCategoria();
			idTipoImpSeleccionada = retencion.getJuridiccionActividad().getCategoria().getTipoImpuesto().getIdTipoImpuesto();
			provinciaSeleccionada = retencion.getProvincia().getIdProvincia();
			tipoImpHtml.setValue(idTipoImpSeleccionada);
			jurisHtml.setValue(jurisdiccionSeleccionada);
			categHtml.setValue(categoriaSeleccionada);
			aplicHtml.setValue(aplicableSeleccionada);
			vigenciaHasta = new Date(retencion.getVigenciaHasta().getTime());
			vigenciaDesde = new Date(retencion.getVigenciaDesde().getTime());
			cambiarCategorias(null);
			cambiarActividad(null);
			tramos = Convertidores.setToList(retencion.getTramosRetenciones());
			tramosLeidos = RetencionUtil.idsTramosLeidas(retencion.getTramosRetenciones());

			alta = false;
			tituloCorto = "Modificación de Retención";
			tituloLargo = "Tarjeta Fiel - Modificación de Retención";
			result = "altaRetencion";
		} catch (RetencionException e) {
			e.printStackTrace();
		}

		return result;
	}


	public String eliminarRetencion() {
		try {
			retencion = impuestoService.getRetencionService().leerRetencion(idRetencionHidden);
			RetencionUtil.borrarTramos(retencion.getTramosRetenciones(), impuestoService.getTramosRetencionService());
			impuestoService.getRetencionService().borrarRetencion(idRetencionHidden);
		} catch (RetencionException e) {
			e.printStackTrace();
		}

		return filtrarRetenciones();
	}


	public void borrar() {
		limpiarBean();
	}


	public String inicializar() {
		limpiarBean();
		categoriaItems.add(new SelectItem(new Long(0), "Seleccionar Categoría"));
		listaJurisdicciones.add(new SelectItem(new Long(0), "Seleccionar jurisdicción"));
		return "altaRetencion";
	}


	public void cambiarActividad(ValueChangeEvent event) {
		log.info("Cambiar la actividad!!!");
		Long long0 = new Long(0);
		categoriaSeleccionada = new Long(categHtml.getValue().toString());
		jurisdiccionSeleccionada = new Long(jurisHtml.getValue().toString());
		aplicableSeleccionada = new Long(aplicHtml.getValue().toString());
		listaActividades.clear();
		listaActividades.add(new SelectItem(long0, "Seleccione Actividad"));
		if (!categoriaSeleccionada.equals(long0) && !jurisdiccionSeleccionada.equals(long0) && !aplicableSeleccionada.equals(long0)) {
			try {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("jurisdiccion.idJurisdiccion", Filtro.IGUAL, jurisdiccionSeleccionada);
				filtro.agregarCampoOperValor("categoria.idCategoria", Filtro.IGUAL, categoriaSeleccionada);
				filtro.agregarCampoOperValor("aplicable.idAplicable", Filtro.IGUAL, aplicableSeleccionada);
				listaActividades.addAll(Util.cargarSelectItem(
						impuestoService.getJurisdiccionActividadService().getJurisdiccionActividads(filtro)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public boolean validar() {
		error.borrar();

		if (retencion.getDescripcion() == null || retencion.getDescripcion().equals("")) {
			error.agregar(Error.RETENCION_DESCRIPCION_REQUERIDA);
		}

		// if(retencion.getCodigoRegimen()==null || retencion.getCodigoRegimen().equals("")) {
		// error.agregar(Error.RETENCION_CODIGO_REG__REQUERIDA);
		// }

		if (retencion.getMinimoImponible() == null) {
			error.agregar(Error.RETENCION_MINIMO_IMP_REQUERIDA);
		}

		if (retencion.getMinimoRetencion() == null) {
			error.agregar(Error.RETENCION_MINIMO_REQUERIDA);
		}

		if (vigenciaDesde == null) {
			error.agregar(Error.RETENCION_VIGENCIA_DESDE_REQUERIDA);
		}

		if (vigenciaHasta == null) {
			error.agregar(Error.RETENCION_VIGENCIA_HASTA_REQUERIDA);
		}

		if ((vigenciaDesde != null && vigenciaHasta != null) && (vigenciaDesde.after(vigenciaHasta))) {
			error.agregar(Error.RETENCION_VIGENCIA_RANGO);
		}

		if (aplicableSeleccionada == null || aplicableSeleccionada.equals(new Long(0))) {
			error.agregar(Error.RETENCION_APLICABLE_REQUERIDA);
		}

		if (provinciaSeleccionada == null || provinciaSeleccionada.equals(new Long(0))) {
			error.agregar(Error.RETENCION_PROVINCIA_REQUERIDA);
		}

		if (categoriaSeleccionada == null || categoriaSeleccionada.equals(new Long(0))) {
			error.agregar(Error.RETENCION_CATEGORIA_REQUERIDA);
		}

		if (jurisdiccionSeleccionada == null || jurisdiccionSeleccionada.equals(new Long(0))) {
			error.agregar(Error.RETENCION_JURISDICCION_REQUERIDA);
		}

		if (activodadSeleccionada == null || activodadSeleccionada.equals(new Long(0))) {
			error.agregar(Error.RETENCION_ACTIVIDAD_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}

}

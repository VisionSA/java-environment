package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.general.exception.PartidoException;
import com.bizitglobal.tarjetafiel.general.exception.ProvinciaException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
//import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.general.DomicilioUtil;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestoService;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImpuestoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ImpuestoBean.class);
	private Impuesto impuesto;
	private String nombreFiltro;
	private Long idImpuestoHidden;
	private boolean ganaciaImpuesta;
	private boolean percerpcion;
	private String idImpuesto;
	private String importeMinimo;
	private String porcAlicuota;

	// definicion de un list del objeto base
	private List impuestoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List tipoImpList = new ArrayList();
	private List tipoImpItems = new ArrayList();
	private HtmlSelectOneMenu tipoImpHtml = new HtmlSelectOneMenu();

	private List categoriaList = new ArrayList();
	private List categoriaItems = new ArrayList();

	private List partidoList = new ArrayList();
	private List partidoItems = new ArrayList();

	private List provinciaList = new ArrayList();
	private List provinciaItems = new ArrayList();
	private HtmlSelectOneMenu provinciaHtml = new HtmlSelectOneMenu();

	// Objetos Relacionados.
	private Long idTipoImpSeleccionada;
	private Long idCategoriaSeleccionada;
	private Long idPartidoSeleccionada;
	private Long idProvinciaSeleccionada;

	private String focoHidden;


	public ImpuestoBean() {
		super();
		borrar();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public HtmlSelectOneMenu getTipoImpHtml() {
		return tipoImpHtml;
	}


	public void setTipoImpHtml(HtmlSelectOneMenu tipoImpHtml) {
		this.tipoImpHtml = tipoImpHtml;
	}


	public Impuesto getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}


	public Long getIdImpuestoHidden() {
		return idImpuestoHidden;
	}


	public void setIdImpuestoHidden(Long idImpuestoHidden) {
		this.idImpuestoHidden = idImpuestoHidden;
	}


	public List getCategoriaItems() {
		return categoriaItems;
	}


	public void setCategoriaItems(List categoriaItems) {
		this.categoriaItems = categoriaItems;
	}


	public Long getIdCategoriaSeleccionada() {
		return idCategoriaSeleccionada;
	}


	public void setIdCategoriaSeleccionada(Long idCategoriaSeleccionada) {
		this.idCategoriaSeleccionada = idCategoriaSeleccionada;
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


	public List getImpuestoList() {
		return impuestoList;
	}


	public void setImpuestoList(List object) {
		this.impuestoList = object;
	}


	public Long getIdTipoImpSeleccionada() {
		return idTipoImpSeleccionada;
	}


	public void setIdTipoImpSeleccionada(Long idTipoImpSeleccionada) {
		this.idTipoImpSeleccionada = idTipoImpSeleccionada;
	}


	public List getTipoImpItems() {
		return tipoImpItems;
	}


	public void setTipoImpItems(List tipoImpItems) {
		this.tipoImpItems = tipoImpItems;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public boolean isGanaciaImpuesta() {
		return ganaciaImpuesta;
	}


	public void setGanaciaImpuesta(boolean ganaciaImpuesta) {
		this.ganaciaImpuesta = ganaciaImpuesta;
	}


	public boolean isPercerpcion() {
		return percerpcion;
	}


	public void setPercerpcion(boolean percerpcion) {
		this.percerpcion = percerpcion;
	}


	public String getIdImpuesto() {
		return idImpuesto;
	}


	public void setIdImpuesto(String idImpuesto) {
		this.idImpuesto = idImpuesto;
	}


	public HtmlSelectOneMenu getProvinciaHtml() {
		return provinciaHtml;
	}


	public void setProvinciaHtml(HtmlSelectOneMenu provinciaHtml) {
		this.provinciaHtml = provinciaHtml;
	}


	public String getImporteMinimo() {
		return importeMinimo;
	}


	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}


	public String getPorcAlicuota() {
		return porcAlicuota;
	}


	public void setPorcAlicuota(String porcAlicuota) {
		this.porcAlicuota = porcAlicuota;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE IMPUESTO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		categoriaItems = new ArrayList();
		categoriaItems.add(new SelectItem(new Long(0), "Seleccionar Categoría"));
		partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		partidoItems.addAll(Util.cargarSelectItem(partidoList));
		cargarItems();
		return "amImpuesto";
	}


	private void cargarItems() {
		try {
			try {
				tipoImpList = impuestoService.getTipoImpuestoService().getTipoImpuesto(new Filtro());
			} catch (TipoImpuestoException e1) {
				e1.printStackTrace();
			}
			try {
				categoriaList = impuestoService.getCategoriaService().getCategorias(new Filtro());
			} catch (CategoriaException e1) {
				e1.printStackTrace();
			}
			try {
				partidoList = generalService.getPartidoService().getPartido(new Filtro());
			} catch (PartidoException e1) {
				e1.printStackTrace();
			}
			try {
				provinciaList = generalService.getProvinciaService().getProvincia(new Filtro());
			} catch (ProvinciaException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((tipoImpItems.size() - 1) != tipoImpList.size()) {
			tipoImpItems = new ArrayList();
			tipoImpItems.add(new SelectItem(new Long(0), "Seleccionar Tipo Impuesto"));
			tipoImpItems.addAll(Util.cargarSelectItem(tipoImpList));
		}
		// if ((categoriaItems.size() - 1) != categoriaList.size()) {
		// categoriaItems = new ArrayList();
		// categoriaItems.add(new SelectItem(new Long(0), "Seleccionar Categoría"));
		// categoriaItems.addAll(Util.cargarSelectItem(categoriaList));
		// }
		// if ((partidoItems.size() - 1) != partidoList.size()) {
		// partidoItems = new ArrayList();
		// partidoItems.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		// partidoItems.addAll(Util.cargarSelectItem(partidoList));
		// }
		if ((provinciaItems.size() - 1) != provinciaList.size()) {
			provinciaItems = new ArrayList();
			provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
			provinciaItems.addAll(Util.cargarSelectItem(provinciaList));
		}
	}


	public void cambiarCategorias(ValueChangeEvent event) {
		Long idTipoImpSeleccionado = (Long) tipoImpHtml.getValue();
		listarCategorias(idTipoImpSeleccionado);
	}


	private void listarCategorias(Long idTipoImpSeleccionado) {
		if (!idTipoImpSeleccionado.equals(new Long(0))) {
			categoriaItems.clear();
			categoriaItems.add(new SelectItem(new Long(0), "Seleccione Categoría"));
			categoriaItems.addAll(ImpuestoUtil.filtrarCategorias(categoriaList, idTipoImpSeleccionado));
		} else {
			categoriaItems.clear();
			categoriaItems.add(new SelectItem(new Long(0), "Seleccione Categoría"));
		}
	}


	public void cargarPartido(ValueChangeEvent event) {
		Long idSeleccionado = new Long(provinciaHtml.getValue().toString());
		listarPartidos(idSeleccionado);
	}


	private void listarPartidos(Long idSeleccionado) {
		if (!idSeleccionado.equals(new Long(0))) {
			partidoItems.clear();
			partidoItems.add(new SelectItem(new Long(0), "Seleccione Partido"));
			partidoItems.addAll(DomicilioUtil.filtrarPartidos(partidoList, idSeleccionado));
		} else {
			partidoItems.clear();
			partidoItems.add(new SelectItem(new Long(0), "Seleccione Partido"));
		}
	}


	public String editarImpuesto() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Impuesto";
		try {
			impuesto = impuestoService.getImpuestoService().leerImpuesto(idImpuestoHidden);

			idCategoriaSeleccionada = impuesto.getCategoria().getIdCategoria();
			idTipoImpSeleccionada = impuesto.getCategoria().getTipoImpuesto().getIdTipoImpuesto();
			listarCategorias(idTipoImpSeleccionada);
			if (impuesto.getProvincia() != null) {
				idProvinciaSeleccionada = impuesto.getProvincia().getIdProvincia();
				listarPartidos(idProvinciaSeleccionada);
				if (impuesto.getPartido() != null) {
					idPartidoSeleccionada = impuesto.getPartido().getIdPartido();
				}
				// if(!partidoList.isEmpty()){
				// Iterator iterator = partidoList.iterator();
				// partidoItems = new ArrayList();
				// partidoItems.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
				// while (iterator.hasNext()) {
				// Partido element = (Partido) iterator.next();
				//
				// if(element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
				// partidoItems.add(new SelectItem(element.getId(), element.getLabel()));
				// }
				// }
				// if (impuesto.getPartido()!=null) {
				// idPartidoSeleccionada = impuesto.getPartido().getId();
				// } else {
				// idPartidoSeleccionada = new Long(0);
				// }
			} else {
				idProvinciaSeleccionada = new Long(0);
				idPartidoSeleccionada = new Long(0);
			}
			if (impuesto.getImponibleGncias().equals(new Character('S')))
				ganaciaImpuesta = true;
			if (impuesto.getPercepcion().equals(new Character('S')))
				percerpcion = true;

			result = "amImpuesto";
		} catch (ImpuestoException e1) {
			error.agregar("Ocurrio un error al intentar editar el impuesto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarImpuesto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el impuesto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarImpuesto.jsf");
		}
		return result;
	}


	public String eliminarImpuesto() {

		try {
			impuestoService.getImpuestoService().borrarImpuesto(idImpuestoHidden);
			impuestoList.remove(new Impuesto(idImpuestoHidden));
		} catch (ImpuestoException e1) {
			error.agregar("Imposible borrar el impuesto. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el impuesto");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarImpuesto.jsf");
		return null;
	}


	public String grabar() {
		try {
			impuesto.setDescripcion(impuesto.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				ImpuestoService impuestoService = this.impuestoService.getImpuestoService();
				impuesto.setCategoria((Categoria) Util.buscarElemento(categoriaList, new Categoria(idCategoriaSeleccionada)));
				impuesto.setPartido((Partido) Util.buscarElemento(partidoList, new Partido(idPartidoSeleccionada)));
				impuesto.setProvincia((Provincia) Util.buscarElemento(provinciaList, new Provincia(idProvinciaSeleccionada)));
				if (ganaciaImpuesta)
					impuesto.setImponibleGncias(new Character('S'));
				else
					impuesto.setImponibleGncias(new Character('N'));
				if (percerpcion)
					impuesto.setPercepcion(new Character('S'));
				else
					impuesto.setPercepcion(new Character('N'));
				if (alta) {
					impuestoService.grabarImpuesto(impuesto);
				} else {
					impuestoService.actualizarImpuesto(impuesto);
				}
				popup.setPopup(popup.ICONO_OK, "El Impuesto ha sido almacenado exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ImpuestoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impuesto.");
			e1.printStackTrace();
		} catch (ImpuestoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impuesto.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impuesto.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Impuesto";
		popup.borrar();

		tipoImpHtml = new HtmlSelectOneMenu();
		provinciaHtml = new HtmlSelectOneMenu();
		idTipoImpSeleccionada = new Long(0);
		idCategoriaSeleccionada = new Long(0);
		idPartidoSeleccionada = new Long(0);
		idProvinciaSeleccionada = new Long(0);
		ganaciaImpuesta = false;
		percerpcion = false;
		impuesto = new Impuesto();
		importeMinimo = "";
		porcAlicuota = "";
		idImpuesto = "";
		impuestoList = new ArrayList();

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (impuesto.getDescripcion() == null || impuesto.getDescripcion().equals(""))
			error.agregar(Error.AMIMPUESTO_DESCRIPCION_REQUERIDA);

		if (impuesto.getImporteMinimo() == null)
			error.agregar(Error.AMIMPUESTO_IMPONIBLEMINIMO_REQUERIDO);

		if (idCategoriaSeleccionada == null || idCategoriaSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMIMPUESTO_CATEGORIA_REQUERIDA);

		if (impuesto.getPorcAlicuota() == null || impuesto.getPorcAlicuota().equals(new Long(0)))
			error.agregar(Error.AMIMPUESTO_PORCENTAJEALICUOTA_REQUERIDO);

		if (alta) {
			try {
				List unImpuestoa = impuestoService.getImpuestoService().getImpuestos(
						new Filtro("descripcion", Filtro.LIKEXS, impuesto.getDescripcion().trim()));
				if (!unImpuestoa.isEmpty()) {

					Iterator iterator = unImpuestoa.iterator();
					while (iterator.hasNext()) {
						Impuesto element = (Impuesto) iterator.next();

						if (element.getCategoria().getIdCategoria().equals(idCategoriaSeleccionada) &&
								// (idPartidoSeleccionada != null && !idPartidoSeleccionada.equals(new Long(0)) &&
								// element.getPartido().getId().equals(idPartidoSeleccionada)) &&
								(idProvinciaSeleccionada != null && idProvinciaSeleccionada.equals(new Long(0)) && element.getProvincia()
										.getIdProvincia().equals(idProvinciaSeleccionada))) {

							error.agregar(Error.AMIMPUESTO_IMPUESTO_REPETIDO);
							return false;
						}
						else {

							if (element.getCategoria().getIdCategoria().equals(idCategoriaSeleccionada)) {
								error.agregar(Error.AMIMPUESTO_IMPUESTO_REPETIDO);
								return false;
							}
						}
					}
				}
			} catch (ImpuestoException e) {
				e.printStackTrace();
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoImpuesto() {
		return inicializar();
	}


	public String irAModificarImpuesto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Impuesto";
		return null;
	}


	public String irAListarImpuesto() {
		inicializar();
		tituloCorto = "Listado de Impuesto";
		Session.redirect("/tarjetafiel/impuestos/listarImpuesto.jsf");
		return "";
	}


	public String listarImpuesto() {
		impuestoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			log.info("idImpuesto: " + idImpuesto);
			// if(idImpuesto != null && !idImpuesto.equals(new String("")))
			// filtro.agregarCampoOperValor("idImpuesto", Filtro.IGUAL, new Long(idImpuesto));

			if (impuesto.getDescripcion() != null && !impuesto.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, impuesto.getDescripcion());

			if (idTipoImpSeleccionada != null && !idTipoImpSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("categoria.tipoImpuesto.idTipoImpuesto", Filtro.IGUAL, idTipoImpSeleccionada);

			if (idCategoriaSeleccionada != null && !idCategoriaSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("categoria.idCategoria", Filtro.IGUAL, idCategoriaSeleccionada);

			if (idPartidoSeleccionada != null && !idPartidoSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("partido.idPartido", Filtro.IGUAL, idPartidoSeleccionada);

			if (idProvinciaSeleccionada != null && !idProvinciaSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada);

			if (importeMinimo != null && !importeMinimo.equals(""))
				filtro.agregarCampoOperValor("importeMinimo", Filtro.IGUAL, new BigDecimal(importeMinimo));

			if (porcAlicuota != null && !porcAlicuota.equals(""))
				filtro.agregarCampoOperValor("porcAlicuota", Filtro.IGUAL, new Long(porcAlicuota));

			impuestoList = impuestoService.getImpuestoService().getImpuestos(filtro);
			Iterator iter = impuestoList.iterator();
			while (iter.hasNext())
			{
				Impuesto element = (Impuesto) iter.next();

				element.getCategoria().getDescripcion();
				element.getImporteMinimo();
				if (element.getPartido() != null)
					element.getPartido().getDescripcion();
				if (element.getProvincia() != null)
					element.getProvincia().getNombre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarImpuesto.jsf");
		return null;
	}

}

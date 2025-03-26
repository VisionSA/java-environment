package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableException;
import com.bizitglobal.tarjetafiel.impuestos.exception.CategoriaException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionActividadException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class TipoImpuestoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TipoImpuestoBean.class);
	private static long numeroDeInstancias = 0;
	private static long numeroDeInstanciasActividades = 0;
	private static long numeroDeInstanciasJuris = 0;
	private TipoImpuesto tipoimpuesto;
	private String nombreFiltro;
	private Long idTipoImpuestoHidden;
	private String idTipoImpuesto;
	private String tituloDelPanelActividades = "Listado de Actividades";

	List jurisdiccionesPosibles;
	List jurisdicciones;
	List aplicables;
	List jurisdiccionesImpuestos;
	// para guardar la categoria que se esta editando
	private Categoria cateEdi;
	private boolean verTablaJurisdiccion = false;
	private boolean todos;
	private String descripcionTipoImpuestoAEditar;
	// definicion de un list del objeto base
	private List tipoImpuestoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List jurisdiccionList = new ArrayList();
	private List jurisdiccionItems = new ArrayList();
	private List tablaJurisdiccion = new ArrayList();
	private List listCategorias;
	private List listActividades = new ArrayList();;

	// Objetos Relacionados.
	private Long idJurisdiccionSeleccionada;

	private String focoHidden;
	private String descTipoImpInicial;


	public TipoImpuestoBean() {
		super();
		borrar();
		try {
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


	public TipoImpuesto getTipoImpuesto() {
		return tipoimpuesto;
	}


	public void setTipoImpuesto(TipoImpuesto tipoimpuesto) {
		this.tipoimpuesto = tipoimpuesto;
	}


	public Long getIdTipoImpuestoHidden() {
		return idTipoImpuestoHidden;
	}


	public void setIdTipoImpuestoHidden(Long idTipoImpuestoHidden) {
		this.idTipoImpuestoHidden = idTipoImpuestoHidden;
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


	public List getTipoImpuestoList() {
		return tipoImpuestoList;
	}


	public void setTipoImpuestoList(List object) {
		this.tipoImpuestoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE TIPOIMPUESTO
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
			jurisdicciones = impuestoService.getJurisdiccionService().getJurisdiccion(new Filtro());
		} catch (JurisdiccionException e2) {
			e2.printStackTrace();
		}
		try {
			aplicables = impuestoService.getAplicableService().getAplicables(new Filtro());
		} catch (AplicableException e2) {
			e2.printStackTrace();
		}
		// cargarItems();
		// armarTablaJurisdiccion();

		return "amTipoImpuesto";
	}


	// private void cargarItems() {
	//
	// if (jurisdiccionItems.size() != jurisdiccionList.size()) {
	// jurisdiccionItems.add(new SelectItem(new Long(0), "Seleccionar Jurisdicción"));
	// jurisdiccionItems.addAll(Util.cargarSelectItem(jurisdiccionList));
	// }
	// }

	public String eliminarCategoria() {
		error.borrar();
		String result = null;
		listActividades.clear();
		// bloqueo todos los elementos que se estaban editando
		Iterator it = listCategorias.iterator();
		while (it.hasNext()) {
			CategoriaWrapper cat = (CategoriaWrapper) it.next();
			cat.setEditable(false);
		}

		Long idCateAEditar = new Long(Session.getRequestParameter("idCatEli"));
		log.info(idCateAEditar);
		Iterator iter = listCategorias.iterator();
		while (iter.hasNext()) {
			CategoriaWrapper c = (CategoriaWrapper) iter.next();
			if (c.getIndiceTabla().equals(idCateAEditar)) {
				if (c.getCategoria().getIdCategoria() != null) {
					try {
						List lista = new ArrayList();
						lista = impuestoService.getImpuestosIndividuoService().getImpuestos(
								new Filtro("categoria", Filtro.IGUAL, c.getCategoria().getIdCategoria()));
						if (!lista.isEmpty()) {
							error.agregar(Error.IMP_CATEGORIA_REFERENCIADA);
						}
						List listaDos = new ArrayList();
						listaDos = impuestoService.getImpuestoService().getImpuestos(
								new Filtro("categoria", Filtro.IGUAL, c.getCategoria().getIdCategoria()));
						if (!listaDos.isEmpty()) {
							error.agregar(Error.IMP_CATEGORIA_REFERENCIADA_POR_IMPUESTO);
						}
						if (error.cantidad() == 0) {
							log.info("Se borra la Categoria");
							tipoimpuesto.getCategorias().remove(c.getCategoria());
							listCategorias.remove(c);
							impuestoService.getCategoriaService().borrarCategoria(c.getCategoria().getIdCategoria());
							break;
						}
					} catch (ImpuestosIndividuoException e) {
						error.agregar(Error.IMP_CATEGORIA_REFERENCIADA);
					} catch (ImpuestoException e2) {
						error.agregar(Error.IMP_CATEGORIA_REFERENCIADA);
					} catch (CategoriaException e3) {
						error.agregar(Error.IMP_CATEGORIA_REFERENCIADA);
					} catch (Exception e4) {
						error.agregar(Error.IMP_IMPOSIBLE_BORRAR_CATEGORIA);
					}
				} else {
					tipoimpuesto.getCategorias().remove(c.getCategoria());
					listCategorias.remove(c);
					break;
				}
			}
		}
		return result;
	}


	public void editarLasJurisdiccionesDelTipoImpuesto() {
		if (alta) {
			try {
				impuestoService.getTipoImpuestoService().grabarTipoImpuesto(tipoimpuesto);
				idTipoImpuestoHidden = tipoimpuesto.getIdTipoImpuesto();
				alta = false;
			} catch (TipoImpuestoException e) {
				e.printStackTrace();
			}
		}
		try {
			tipoimpuesto = impuestoService.getTipoImpuestoService().leerTipoImpuesto(tipoimpuesto.getId());
			tipoimpuesto.getIdTipoImpuesto();
			descTipoImpInicial = tipoimpuesto.getDescripcion();
			descripcionTipoImpuestoAEditar = tipoimpuesto.getDescripcion();
			Iterator it = tipoimpuesto.getJurisTipoImp().iterator();
			while (it.hasNext()) {
				JurisTipoImpuesto juTiIm = (JurisTipoImpuesto) it.next();
				juTiIm.getJurisdiccion().getDescripcion();
			}
			armarTablaJurisdiccion();
			listarCategoriasAsociadas();
		} catch (TipoImpuestoException e) {
			e.printStackTrace();
		}
		List tipoImp = Convertidores.setToList(tipoimpuesto.getJurisTipoImp());
		if (!tipoImp.isEmpty() && !tablaJurisdiccion.isEmpty()) {
			Iterator iterator = tipoImp.iterator();
			while (iterator.hasNext()) {
				JurisTipoImpuesto element = (JurisTipoImpuesto) iterator.next();
				Iterator iter = tablaJurisdiccion.iterator();
				while (iter.hasNext()) {
					TipoImpuestoWrappers elem = (TipoImpuestoWrappers) iter.next();
					if (element.getJurisdiccion().getIdJurisdiccion().equals(elem.getJurisdiccion().getIdJurisdiccion())) {
						elem.setEstado(true);
						break;
					}
				}
			}
		}
		verTablaJurisdiccion = true;
	}


	public String grabarLasJurisdicciones() {
		if (validar()) {
			if (!alta) {
				boolean errorYaAgregado = false;
				try {
					List tipoImp = Convertidores.setToList(tipoimpuesto.getJurisTipoImp());
					log.info("Se debe borrar la lista de impuesto Jurisdicciones");
					if (!tipoImp.isEmpty()) {
						Iterator iter = tipoImp.iterator();
						while (iter.hasNext()) {
							log.info("Borrando un elemento");
							JurisTipoImpuesto element = (JurisTipoImpuesto) iter.next();
							boolean estaReferenciada = false;
							Iterator iterDeCategorias = tipoimpuesto.getCategorias().iterator();
							while (iterDeCategorias.hasNext()) {
								Categoria cater = (Categoria) iterDeCategorias.next();
								Iterator iterDeActiv = cater.getJurisActividades().iterator();
								while (iterDeActiv.hasNext()) {
									JurisdiccionActividad jurisAct = (JurisdiccionActividad) iterDeActiv.next();
									if (jurisAct.getJurisdiccion().getIdJurisdiccion().equals(element.getJurisdiccion().getIdJurisdiccion())) {
										estaReferenciada = true;

									}
								}
							}
							if (!estaReferenciada) {
								log.info("borramos la juris");
								impuestoService.getJurisTipoImpuestoService().borrarJurisTipoImpuesto(element.getIdJurisTipoImpuesto());
								if (!errorYaAgregado) {
									error.agregar(Error.IMP_IMPOSIBLE_BORRAR_JURISDICCIONES);
									errorYaAgregado = true;
								}
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				if (alta) {
					if (!tablaJurisdiccion.isEmpty()) {
						Iterator iterator = tablaJurisdiccion.iterator();
						while (iterator.hasNext()) {
							TipoImpuestoWrappers element = (TipoImpuestoWrappers) iterator.next();
							if (element.getEstado()) {
								JurisTipoImpuesto jurisTipoImpuesto = new JurisTipoImpuesto();
								jurisTipoImpuesto.setJurisdiccion(element.getJurisdiccion());
								jurisTipoImpuesto.setTipoImpuesto(tipoimpuesto);
								tipoimpuesto.getJurisTipoImp().add(jurisTipoImpuesto);
							}
						}
					}
					impuestoService.getTipoImpuestoService().grabarTipoImpuesto(tipoimpuesto);
					idTipoImpuestoHidden = tipoimpuesto.getIdTipoImpuesto();
					alta = false;
				} else {
					if (!tablaJurisdiccion.isEmpty()) {
						Iterator iterator = tablaJurisdiccion.iterator();
						while (iterator.hasNext()) {
							TipoImpuestoWrappers element = (TipoImpuestoWrappers) iterator.next();
							if (element.getEstado()) {
								JurisTipoImpuesto jurisTipoImpuesto = new JurisTipoImpuesto();
								jurisTipoImpuesto.setJurisdiccion(element.getJurisdiccion());
								jurisTipoImpuesto.setTipoImpuesto(tipoimpuesto);
								// tipoimpuesto.getJurisTipoImp().add(jurisTipoImpuesto);
								impuestoService.getJurisTipoImpuestoService().grabarJurisTipoImpuesto(jurisTipoImpuesto);
							}
						}
					}
					// tipoimpuestoService.grabarTipoImpuesto(tipoimpuesto);
				}
			} catch (TipoImpuestoDuplicateException e1) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e1.printStackTrace();
			} catch (TipoImpuestoException e2) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e2.printStackTrace();
			} catch (Exception e3) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e3.printStackTrace();
			}
		} else {
			ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
			scrollBean.borrar();
		}
		editarTipoImpuesto();
		verTablaJurisdiccion = false;
		return "";
	}


	public String editarCategoria() {
		listActividades.clear();
		// Aca armamos la lista de jurisdicciones
		try {
			jurisdiccionesImpuestos = impuestoService.getJurisTipoImpuestoService().getJurisTipoImpuesto(
					new Filtro("tipoImpuesto", Filtro.IGUAL, tipoimpuesto.getIdTipoImpuesto()));
			jurisdiccionesPosibles.clear();
			Iterator iterJurisdiccionesImpuestos = jurisdiccionesImpuestos.iterator();
			while (iterJurisdiccionesImpuestos.hasNext()) {
				JurisTipoImpuesto jurisTipo = (JurisTipoImpuesto) iterJurisdiccionesImpuestos.next();
				jurisTipo.getJurisdiccion().getDescripcion();
				jurisdiccionesPosibles.add(jurisTipo.getJurisdiccion());
			}
		} catch (JurisTipoImpuestoException e2) {
			e2.printStackTrace();
		}
		Long idCateAEditar = new Long(Session.getRequestParameter("idCatEdi"));
		log.info("El parámetro es: " + idCateAEditar);
		Iterator iter = listCategorias.iterator();
		while (iter.hasNext()) {
			CategoriaWrapper c = (CategoriaWrapper) iter.next();
			if (c.getIndiceTabla().equals(idCateAEditar)) {
				cateEdi = c.getCategoria();
				c.setEditable(true);
			} else {
				c.setEditable(false);
			}
		}
		listarActividadesAsociadas(idCateAEditar);
		return "";
	}


	public void listarCategoriasAsociadas() {
		Iterator iter = tipoimpuesto.getCategorias().iterator();
		while (iter.hasNext()) {
			Categoria catAux = (Categoria) iter.next();
			catAux.getCodCategoria();
			catAux.getDescripcion();
			catAux.getId();
			Set setin = catAux.getJurisActividades();
			Iterator iterSetin = setin.iterator();
			while (iterSetin.hasNext()) {
				JurisdiccionActividad ju = (JurisdiccionActividad) iterSetin.next();
				ju.getDescActividad();
				ju.getJurisdiccion().getIdJurisdiccion();
			}
			CategoriaWrapper catW = new CategoriaWrapper();
			catW.setEditable(false);
			catW.setCategoria(catAux);
			listCategorias.add(catW);
		}
	}


	public String listarActividadesAsociadas(Long idCateAEditar) {
		List listActividadesAuxi = new ArrayList();
		listActividades.clear();
		log.info("El numero de categoria seleccionado es: " + cateEdi.getIdCategoria());
		Iterator iterActi = cateEdi.getJurisActividades().iterator();
		while (iterActi.hasNext()) {
			JurisdiccionActividad acti = (JurisdiccionActividad) iterActi.next();
			ActividadWrapper ac = new ActividadWrapper();
			ac.setJurisActividad(acti);
			ac.agregarJurisdiccionesPosibles(jurisdiccionesPosibles);
			ac.setJurisSeleccionada(acti.getJurisdiccion().getIdJurisdiccion());
			ac.setAplicableSeleccionada(acti.getAplicable().getIdAplicable());
			ac.setEditable(false);
			listActividades.add(ac);
		}
		return "";
	}


	public String editarTipoImpuesto() {
		descripcionTipoImpuestoAEditar = new String();
		String result = null;
		borrarBaseBean();
		borrar();
		verTablaJurisdiccion = false;
		alta = false;
		tituloCorto = "Modificar Tipo Impuesto";
		try {
			jurisdicciones = impuestoService.getJurisdiccionService().getJurisdiccion(new Filtro());
		} catch (JurisdiccionException e2) {
			e2.printStackTrace();
		}
		try {
			aplicables = impuestoService.getAplicableService().getAplicables(new Filtro());
		} catch (AplicableException e2) {
			e2.printStackTrace();
		}
		try {
			tipoimpuesto = (impuestoService.getTipoImpuestoService()).leerTipoImpuesto(idTipoImpuestoHidden);
			descTipoImpInicial = tipoimpuesto.getDescripcion();
			descripcionTipoImpuestoAEditar = tipoimpuesto.getDescripcion();
			Iterator it = tipoimpuesto.getJurisTipoImp().iterator();
			while (it.hasNext()) {
				JurisTipoImpuesto juTiIm = (JurisTipoImpuesto) it.next();
				juTiIm.getJurisdiccion().getDescripcion();
			}

			armarTablaJurisdiccion();
			listarCategoriasAsociadas();
			log.info("Presentamos el tipo Impuesto para edición");
			result = "amTipoImpuesto";
		} catch (TipoImpuestoException e1) {
			error.agregar("Ocurrio un error al intentar editar el tipoimpuesto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el tipoimpuesto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		}
		return result;
	}


	public String eliminarTipoImpuesto() {
		try {
			impuestoService.getTipoImpuestoService().borrarTipoImpuesto(idTipoImpuestoHidden);
			tipoImpuestoList.remove(new TipoImpuesto(idTipoImpuestoHidden));
		} catch (TipoImpuestoException e1) {
			error.agregar("Imposible borrar el tipoimpuesto. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el tipoimpuesto");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		return null;
	}


	public String grabar() {

		if (validar()) {
			// Inicio los servis que voy a usar
			log.info(tipoimpuesto);
			try {
				if (alta) {
					impuestoService.getTipoImpuestoService().grabarTipoImpuesto(tipoimpuesto);
				} else {
					log.info("vamos a actualizar");
					if (tipoimpuesto.getDescripcion().compareTo(descripcionTipoImpuestoAEditar) != 0) {
						impuestoService.getTipoImpuestoService().actualizarTipoImpuesto(tipoimpuesto);
					} else {
						log.info("vamos a actualizar solo las actividades");
						// Filtro filtro = new Filtro();
						// filtro.agregarCampoOperValor("tipoImpuesto", Filtro.IGUAL, tipoimpuesto.getIdTipoImpuesto());
						try {
							Iterator iterDos = tipoimpuesto.getCategorias().iterator();
							while (iterDos.hasNext()) {
								log.info("Vamos a editar una categoria");
								Categoria catDos = (Categoria) iterDos.next();
								if (catDos.getIdCategoria() != null) {
									log.info("Se actualiza una categoria");
									Categoria editada = impuestoService.getCategoriaService().leerCategoria(catDos.getIdCategoria());
									editada.setDescripcion(catDos.getDescripcion());
									editada.getJurisActividades();
									Iterator iterAux = catDos.getJurisActividades().iterator();
									while (iterAux.hasNext()) {
										JurisdiccionActividad jur = (JurisdiccionActividad) iterAux.next();
										if (jur.getIdJurisdiccionActividad() != null) {
											Iterator it = editada.getJurisActividades().iterator();
											while (it.hasNext()) {
												JurisdiccionActividad j = (JurisdiccionActividad) it.next();
												if (j.getIdJurisdiccionActividad() != null
														&& j.getIdJurisdiccionActividad().equals(jur.getIdJurisdiccionActividad())) {
													j.setDescActividad(jur.getDescActividad());
													j.setJurisdiccion(jur.getJurisdiccion());
													j.setAplicable(jur.getAplicable());
												}
											}
										} else {
											log.info("Se agrego la nueva actividad");
											editada.getJurisActividades().add(jur);
										}

									}
									impuestoService.getCategoriaService().actualizarCategoria(editada);
								} else {
									log.info("Se graba una categoria nueva");
									impuestoService.getCategoriaService().grabarCategoria(catDos);
								}
							}
							popup.setPopup(popup.ICONO_OK, "El Tipo de Impuesto se ha almacenado correctamente.");
						} catch (CategoriaException e) {
							e.printStackTrace();
						}
					}
				}
				if (error.cantidad() == 0) {
					popup.setPopup(popup.ICONO_OK, "El Tipo de Impuesto se ha almacenado correctamente.");
				}
			} catch (TipoImpuestoDuplicateException e1) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e1.printStackTrace();
			} catch (TipoImpuestoException e2) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e2.printStackTrace();
			} catch (Exception e4) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Tipo de Impuesto.");
				e4.printStackTrace();
			}
		} else {
			ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
			scrollBean.borrar();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Tipo Impuesto";
		tituloDelPanelActividades = "Listado de Actividades";
		popup.borrar();
		tablaJurisdiccion = new ArrayList();
		todos = false;
		cateEdi = null;
		verTablaJurisdiccion = false;
		listCategorias = new ArrayList();
		listActividades.clear();
		tipoimpuesto = new TipoImpuesto();
		jurisdiccionesPosibles = new ArrayList();
		tipoImpuestoList = new ArrayList();
	}


	public String cancelar() {
		if (verTablaJurisdiccion) {
			verTablaJurisdiccion = false;
		} else {
			tituloCorto = "Listado de Tipo Impuesto";
			// cargarItems();
			Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		}
		return "";
	}


	public String eliminarActividad() {
		String result = null;
		log.info("la actividad a eliminar tiene id: " + Session.getRequestParameter("idActEli"));
		Long idActiAEli = new Long(Session.getRequestParameter("idActEli"));
		Iterator iter = listActividades.iterator();
		while (iter.hasNext()) {
			ActividadWrapper c = (ActividadWrapper) iter.next();
			if (c.getIndiceTablaActividad().equals(idActiAEli)) {
				try {
					if (c.getJurisActividad().getIdJurisdiccionActividad() != null) {
						impuestoService.getJurisdiccionActividadService().borrarJurisdiccionActividad(
								c.getJurisActividad().getIdJurisdiccionActividad());
						log.info("Se borro la juris Actividad de la base de datos");
					}
					listActividades.remove(c);
					log.info("Se borro la juris Actividad de la lista de actividades");
					cateEdi.getJurisActividades().remove(c.getJurisActividad());
					log.info("Se borro la juris Actividad de la lista de objetos de la categoria");
				} catch (org.hibernate.exception.ConstraintViolationException he) {
					error.agregar(Error.IMP_CATEGORIA_REFERENCIADA);
				} catch (JurisdiccionActividadException e) {
					error.agregar(Error.IMP_ACTIVIDAD_REFERENCIDA);
				} catch (Exception e) {
					error.agregar(Error.IMP_ACTIVIDAD_REFERENCIDA);
				}

				break;
			}
		}
		return result;
	}


	public String editarActividad() {
		String result = null;
		Long idCateAEditar = new Long(Session.getRequestParameter("idActEdi"));
		Iterator iter = listActividades.iterator();
		while (iter.hasNext()) {
			ActividadWrapper c = (ActividadWrapper) iter.next();
			if (c.getIndiceTablaActividad().equals(idCateAEditar)) {
				c.setEditable(true);
			} else {
				c.setEditable(false);
			}
		}
		return result;
	}


	public String agregarJurisActividad() {
		String result = null;
		if (cateEdi != null) {
			if (jurisdiccionesPosibles != null && !jurisdiccionesPosibles.isEmpty()) {
				Iterator iter = listActividades.iterator();
				while (iter.hasNext()) {
					ActividadWrapper c = (ActividadWrapper) iter.next();
					c.setEditable(false);
				}
				JurisdiccionActividad acti = new JurisdiccionActividad();
				acti.setRetenciones(null);
				acti.setCategoria(cateEdi);
				// acti.setIdJurisdiccionActividad(null);
				// Jurisdiccion jur = new Jurisdiccion();
				// jur.setIdJurisdiccion(new Long(0));
				// acti.setJurisdiccion(jur);
				// acti.setDescActividad("");
				cateEdi.getJurisActividades().add(acti);
				ActividadWrapper ac = new ActividadWrapper();
				ac.setJurisActividad(acti);
				ac.agregarJurisdiccionesPosibles(jurisdiccionesPosibles);
				// ac.setJurisSeleccionada(acti.getJurisdiccion().getIdJurisdiccion());
				listActividades.add(ac);
			} else {
				error.agregar(Error.IMP_NO_POSEE_JURISDICCION_ASIGNADA);
			}
		} else {
			error.agregar(Error.IMP_NO_HAY_CATEGORIA_ASOCIADA);
		}
		return result;
	}


	public String agregarCategoria() {

		String result = null;

		// bloqueo todos los elementos que se estaban editando
		Iterator it = listCategorias.iterator();
		while (it.hasNext()) {
			CategoriaWrapper cat = (CategoriaWrapper) it.next();
			cat.setEditable(false);
		}

		Categoria cate = new Categoria();
		cate.setIdCategoria(null);
		cate.setTipoImpuesto(tipoimpuesto);
		cate.setJurisActividades(new HashSet());
		cateEdi = cate;
		jurisdiccionesPosibles.clear();
		try {
			jurisdiccionesImpuestos = impuestoService.getJurisTipoImpuestoService().getJurisTipoImpuesto(
					new Filtro("tipoImpuesto", Filtro.IGUAL, tipoimpuesto.getIdTipoImpuesto()));
			// recorro la lista de jurisdicciones, si la jurisdiccion se encuentra en la lista
			// jurisdiccionesImpuestos la agrego
			Iterator iterJurisdicciones = jurisdicciones.iterator();
			while (iterJurisdicciones.hasNext()) {
				Jurisdiccion juri = (Jurisdiccion) iterJurisdicciones.next();
				Iterator iterJurisdiccionesImpuestos = jurisdiccionesImpuestos.iterator();
				while (iterJurisdiccionesImpuestos.hasNext()) {
					JurisTipoImpuesto jurisTipo = (JurisTipoImpuesto) iterJurisdiccionesImpuestos.next();
					if (jurisTipo.getJurisdiccion().getIdJurisdiccion().equals(juri.getIdJurisdiccion())) {
						jurisdiccionesPosibles.add(juri);
						break;
					}
				}
			}
		} catch (JurisTipoImpuestoException e2) {
			e2.printStackTrace();
		}

		// creo el wrapper para meterlo en la lista
		CategoriaWrapper catWrap = new CategoriaWrapper();
		catWrap.setEditable(true);
		catWrap.setCategoria(cate);
		listCategorias.add(catWrap);
		tipoimpuesto.getCategorias().add(catWrap.getCategoria());
		listarActividadesAsociadas(catWrap.getIndiceTabla());
		return result;
	}


	public boolean validar() {
		error.borrar();

		if (tipoimpuesto.getDescripcion() == null || tipoimpuesto.getDescripcion().equals(""))
			error.agregar(Error.AMTIPOIMPUESTO_DESCRIPCION_REQUERIDA);
		else {
			try {
				tipoimpuesto.setDescripcion(tipoimpuesto.getDescripcion().trim());
				List unTipoImpuesto = impuestoService.getTipoImpuestoService().getTipoImpuesto(
						new Filtro("descripcion", Filtro.LIKEXS, tipoimpuesto.getDescripcion()));
				if (alta) {
					if (!unTipoImpuesto.isEmpty()) {
						error.agregar(Error.AMTIPOIMPUESTO_TIPOIMPUESTO_REPETIDO);
					}
				}
				else {
					if (!(descTipoImpInicial.compareTo(tipoimpuesto.getDescripcion()) == 0)) {
						if (!unTipoImpuesto.isEmpty())
							error.agregar(Error.AMTIPOIMPUESTO_TIPOIMPUESTO_REPETIDO);
					}
				}
			} catch (TipoImpuestoException e) {
				e.printStackTrace();
			}
		}
		Iterator iterControlador = listCategorias.iterator();
		while (iterControlador.hasNext()) {
			CategoriaWrapper catAControlar = (CategoriaWrapper) iterControlador.next();
			if (catAControlar.getCategoria().getDescripcion() == null || catAControlar.getCategoria().getDescripcion().compareTo("") == 0) {
				error.agregar(Error.IMP_CATEGORIA_SIN_DESCRIPCION);
			}
			if (catAControlar.getCategoria().getCodCategoria() == null || catAControlar.getCategoria().getCodCategoria().compareTo("") == 0) {
				error.agregar(Error.IMP_CATEGORIA_SIN_CODIGO);
			}
			if (catAControlar.getCategoria().getJurisActividades() != null) {
				Iterator actividadesAControlar = catAControlar.getCategoria().getJurisActividades().iterator();
				while (actividadesAControlar.hasNext()) {
					JurisdiccionActividad activida = (JurisdiccionActividad) actividadesAControlar.next();
					if (activida.getDescActividad() == null || activida.getDescActividad().compareTo("") == 0)
						error.agregar(Error.IMP_ACTIVIDAD_SIN_DESCRIPCION);
					if (activida.getJurisdiccion() == null || activida.getJurisdiccion().getIdJurisdiccion().equals(new Long(0)))
						error.agregar(Error.IMP_ACTIVIDAD_SIN_JURISDICCION);
					if (activida.getAplicable() == null || activida.getAplicable().getIdAplicable().equals(new Long(0)))
						error.agregar(Error.IMP_ACTIVIDAD_SIN_APLICABLE);
					if (error.hayErrores())
						break;
				}
			}
			if (error.hayErrores())
				break;
		}
		return !error.hayErrores();
	}


	public String irANuevoTipoImpuesto() {
		return inicializar();
	}


	public String irAModificarTipoImpuesto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Tipo Impuesto";

		tablaJurisdiccion = new ArrayList();
		List tipoList = Convertidores.setToList(tipoimpuesto.getJurisTipoImp());

		if (!tipoList.isEmpty()) {
			Iterator iterator = tipoList.iterator();
			while (iterator.hasNext()) {
				JurisTipoImpuesto element = (JurisTipoImpuesto) iterator.next();

				TipoImpuestoWrappers wrappers = new TipoImpuestoWrappers();

				wrappers.setJurisdiccion(element.getJurisdiccion());

				tablaJurisdiccion.add(wrappers);
			}
		}

		return null;
	}


	public String irAListarTipoImpuesto() {
		borrar();
		tituloCorto = "Listado de Tipo Impuesto";
		// cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		return "";
	}


	public String listarTipoImpuesto() {
		tipoImpuestoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idTipoImpuesto != null && !idTipoImpuesto.equals(""))
				filtro.agregarCampoOperValor("idTipoImpuesto", Filtro.IGUAL, new Long(idTipoImpuesto));
			if (tipoimpuesto.getDescripcion() != null && !tipoimpuesto.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, tipoimpuesto.getDescripcion());

			tipoImpuestoList = impuestoService.getTipoImpuestoService().getTipoImpuesto(filtro);
			Iterator iter = tipoImpuestoList.iterator();
			while (iter.hasNext())
			{
				TipoImpuesto element = (TipoImpuesto) iter.next();
				// List auxJuris = Convertidores.setToList(element.getJurisTipoImp());
				//
				// if(!auxJuris.isEmpty()){
				// Iterator iterator = auxJuris.iterator();
				// while (iterator.hasNext()) {
				// JurisTipoImpuesto elem = (JurisTipoImpuesto) iterator.next();
				//
				// elem.getJurisdiccion().getDescripcion();
				//
				// }
				// }
			}
			idTipoImpuesto = "";
			tipoimpuesto.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarTipoImpuesto.jsf");
		return null;
	}


	public void armarTablaJurisdiccion() {

		if (!jurisdiccionList.isEmpty()) {
			tablaJurisdiccion = new ArrayList();
			Iterator iterator = jurisdiccionList.iterator();
			while (iterator.hasNext()) {
				Jurisdiccion element = (Jurisdiccion) iterator.next();
				TipoImpuestoWrappers wrappers = new TipoImpuestoWrappers(element);
				tablaJurisdiccion.add(wrappers);
			}
		}
	}


	public List getTablaJurisdiccion() {
		return tablaJurisdiccion;
	}


	public void setTablaJurisdiccion(List tablaJurisdiccion) {
		this.tablaJurisdiccion = tablaJurisdiccion;
	}


	public boolean isTodos() {
		return todos;
	}


	public void setTodos(boolean todos) {
		this.todos = todos;
	}


	public String getIdTipoImpuesto() {
		return idTipoImpuesto;
	}


	public void setIdTipoImpuesto(String idTipoImpuesto) {
		this.idTipoImpuesto = idTipoImpuesto;
	}


	public List getListCategorias() {
		return listCategorias;
	}


	public void setListCategorias(List listCategorias) {
		this.listCategorias = listCategorias;
	}

	public class CategoriaWrapper {
		public Long indiceTabla;
		public Categoria categoria;
		public boolean editable;


		public CategoriaWrapper() {
			this.indiceTabla = new Long(++numeroDeInstancias);
		}


		public Categoria getCategoria() {
			return categoria;
		}


		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}


		public boolean isEditable() {
			return editable;
		}


		public void setEditable(boolean editable) {
			this.editable = editable;
		}


		public Long getIndiceTabla() {
			return indiceTabla;
		}


		public void setIndiceTabla(Long indiceTabla) {
			this.indiceTabla = indiceTabla;
		}

	}

	public class ActividadWrapper {

		public Long indiceTablaActividad;
		public JurisdiccionActividad jurisActividad;
		public boolean editable;
		public Long jurisSeleccionada;
		public Long aplicableSeleccionada;
		// aqui la lista de select items jurisdicciones
		public List listaDeJurisdicciones;
		public List listaAplicables = new ArrayList();


		public ActividadWrapper() {
			this.indiceTablaActividad = new Long(++numeroDeInstanciasActividades);
			editable = true;
			jurisSeleccionada = new Long(0);
			aplicableSeleccionada = new Long(0);
			listaDeJurisdicciones = new ArrayList();
			listaDeJurisdicciones.add(new SelectItem(new Long(0), "Seleccione Jurisdicción"));
			listaAplicables.clear();
			listaAplicables.add(new SelectItem(new Long(0), "Seleccione Aplicable"));
			listaAplicables.addAll(Util.cargarSelectItem(aplicables));
		}


		// public void cambiarJurisdiccion() {
		// this.jurisActividad.setJurisdiccion(
		// (Jurisdiccion)Util.buscarElemento(jurisdiccionesPosibles, jurisSeleccionada));
		// }
		//
		// public String getCambiarJurisdiccion() {
		// cambiarJurisdiccion();
		// return null;
		// }
		//
		// public void setCambiarJurisdiccion() {
		// cambiarJurisdiccion();
		// }
		//
		// public String cambiarAplicable() {
		// this.jurisActividad.setAplicable(
		// (Aplicable)Util.buscarElemento(aplicables, aplicableSeleccionada));
		// return null;
		// }
		//
		// public String getCambiarAplicable() {
		// cambiarAplicable();
		// return null;
		// }
		//
		// public void setCambiarAplicable() {
		// cambiarAplicable();
		// }

		/*
		 * lista debe estar compuesta por objetos jurisdiccion.
		 */
		public void agregarJurisdiccionesPosibles(List lista) {
			Iterator it = listActividades.iterator();
			while (it.hasNext()) {
				ActividadWrapper cat = (ActividadWrapper) it.next();
				cat.setEditable(false);
			}
			listaDeJurisdicciones.clear();
			listaDeJurisdicciones.add(new SelectItem(new Long(0), "Seleccione Jurisdicción"));
			listaDeJurisdicciones.addAll(Util.cargarSelectItem(lista));
		}


		public boolean isEditable() {
			return editable;
		}


		public void setEditable(boolean editable) {
			this.editable = editable;
		}


		public Long getIndiceTablaActividad() {
			return indiceTablaActividad;
		}


		public void setIndiceTablaActividad(Long indiceTablaActividad) {
			this.indiceTablaActividad = indiceTablaActividad;
		}


		public JurisdiccionActividad getJurisActividad() {
			return jurisActividad;
		}


		public void setJurisActividad(JurisdiccionActividad jurisActividad) {
			this.jurisActividad = jurisActividad;
		}


		public List getListaAplicables() {
			return listaAplicables;
		}


		public void setListaAplicables(List listaAplicables) {
			this.listaAplicables = listaAplicables;
		}


		public Long getJurisSeleccionada() {
			return jurisSeleccionada;
		}


		public void setJurisSeleccionada(Long jurisSeleccionada) {
			this.jurisSeleccionada = jurisSeleccionada;
			jurisActividad.setJurisdiccion(
					(Jurisdiccion) Util.buscarElemento(jurisdiccionesPosibles, new Jurisdiccion(jurisSeleccionada)));
		}


		public Long getAplicableSeleccionada() {
			return aplicableSeleccionada;
		}


		public void setAplicableSeleccionada(Long aplicableSeleccionada) {
			this.aplicableSeleccionada = aplicableSeleccionada;
			jurisActividad.setAplicable(
					(Aplicable) Util.buscarElemento(aplicables, new Aplicable(aplicableSeleccionada)));
		}


		public List getListaDeJurisdicciones() {
			return listaDeJurisdicciones;
		}


		public void setListaDeJurisdicciones(List listaDeJurisdicciones) {
			this.listaDeJurisdicciones = listaDeJurisdicciones;
		}
	}


	public List getListActividades() {
		return listActividades;
	}


	public void setListActividades(List listActividades) {
		this.listActividades = listActividades;
	}


	public boolean isVerTablaJurisdiccion() {
		return verTablaJurisdiccion;
	}


	public void setVerTablaJurisdiccion(boolean verTablaJurisdiccion) {
		this.verTablaJurisdiccion = verTablaJurisdiccion;
	}

	public class TipoImpuestoWrappers {

		private long idTipoImpuestoWrappers;
		private boolean estado;
		private Jurisdiccion jurisdiccion;


		public TipoImpuestoWrappers(boolean estado, Jurisdiccion jurisdiccion) {
			this.idTipoImpuestoWrappers = ++numeroDeInstanciasJuris;
			this.estado = estado;
			this.jurisdiccion = jurisdiccion;
		}


		public TipoImpuestoWrappers() {
			this(false, new Jurisdiccion());
			this.idTipoImpuestoWrappers = ++numeroDeInstanciasJuris;
		}


		public TipoImpuestoWrappers(Jurisdiccion jurisdiccion) {
			this(false, jurisdiccion);
		}


		public boolean getEstado() {
			return estado;
		}


		public void setEstado(boolean estado) {
			this.estado = estado;
		}


		public long getIdTipoImpuestoWrappers() {
			return idTipoImpuestoWrappers;
		}


		public void setIdTipoImpuestoWrappers(long idTipoImpuestoWrappers) {
			this.idTipoImpuestoWrappers = idTipoImpuestoWrappers;
		}


		public Jurisdiccion getJurisdiccion() {
			return jurisdiccion;
		}


		public void setJurisdiccion(Jurisdiccion jurisdiccion) {
			this.jurisdiccion = jurisdiccion;
		}
	}


	public String getTituloDelPanelActividades() {
		return tituloDelPanelActividades;
	}


	public void setTituloDelPanelActividades(String tituloDelPanelActividades) {
		this.tituloDelPanelActividades = tituloDelPanelActividades;
	}

}

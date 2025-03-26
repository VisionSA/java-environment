package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConstructorException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioVersionDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioVersionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.AlmanaqueDePagos;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioVersion;
import com.bizitglobal.tarjetafiel.transacciones.service.ListaPrecioVersionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ListaPrecioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ListaPrecioBean.class);
	private ListaPrecio listaprecio;
	private String nombreFiltro;
	private Long idListaPrecioHidden;

	private boolean perteneceAFiel;

	private boolean pertenecaASeleccionable;

	// definicion de un list del objeto base
	private List listaPrecioList;
	private SucursalFiel sucursal;
	private Date fechaVigencia;
	private boolean verAltaVersiones; // controla la visibilidad del panel de versiones anteriores, actuales y futuras.
	private boolean verEdicion; // controla la visibilidad del boton guardar
	private Date fechaComienzoNuevaVersion;
	private Date fechaOriginal; // esta fecha es para cuando se edita se pueda deducir si es editable la listaPrecio.
								// si la fecha original es menor a la actual, entonces nose puede.!!

	private ListaPrecioVersion versionEditada; // un objeto que representa la version que estamos editando o mostrando.
	private List items; // una lista con los objetos wrapper de ListaPrecioItem;

	private List itemsFiel; // una lista con los objetos wrapper de ListaPrecioItem pero cuando la lista precio es de fiel.
	private List detallesFiel; // una lista para los objeto ListaPrecioDetalle que se presentan como wrappen cdo. la lista precio es de fiel.
	private int indiceItem = 0; // un entero identificador para cada renglon de la tabla de wrappers
	private int indiceItemFiel = 0; // un entero identificador para cada renglon de la tabla de wrappers si la lista de precio es fiel
	private int indiceDetallesFiel = 0; // un entero identificador para cada renglon de la tabla de wrappers Detalles si la lista de precio es fiel
	private static int indiceListadoListaPrecio = 0; // un entero identificador para cada renglon de la tabla de wrappers
	private boolean calendarioVisible;
	private List diasDePago;
	private String estadoCalendario;
	private boolean mostrarOpciones; // si es true, debera permitir decidir si dar de alta una lista precio comercio o una lista precio fiel.
	private String tipoLista;

	private boolean verBtnGuardar;

	private UploadedFile imagen;

	// un booleano que indica la visibilidad del boton volver para la navegacion entre el listado y el alta.
	private boolean verBtnVolver;


	public ListaPrecioBean() {
		super();
		listaPrecioList = new ArrayList();
		borrar();
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE LISTAPRECIO
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
			sucursal = (SucursalFiel) generalService.getSucursalFielService().leerSucursalFiel(new Long(1));
		} catch (SucursalFielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "amListaPrecio";
	}


	public String editarListaPrecio() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		verBtnVolver = true;
		tituloCorto = "Modificar Lista Precio";
		try {
			log.info("Tamaño listaPrecioList " + listaPrecioList.size());
			if (!listaPrecioList.isEmpty()) {
				Iterator iterator = listaPrecioList.iterator();
				while (iterator.hasNext()) {
					ListaPrecioWrappers element = (ListaPrecioWrappers) iterator.next();
					if (element.getIndice() == idListaPrecioHidden.intValue()) {
						listaprecio = transaccionesService.getListaPrecioService().leerListaPrecio(element.getListaPrecio().getIdListaprecios());
						listaprecio.armarListaPrecio();
						mostrarOpciones = false;
						break;
					}
				}
				if (listaprecio.getVersionVigente() != null) {
					editarUnaVersion(listaprecio.getVersionVigente());
				} else {
					editarUnaVersion((ListaPrecioVersion) (listaprecio.getVersionesFuturas().get(0)));
				}
				result = "amListaPrecio";
			}
		} catch (ListaPrecioException e1) {
			error.agregar("Ocurrio un error al intentar editar el Lista Precio");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Lista Precio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
		}
		return result;
	}


	public String cambiarDisponibilidad() {
		if (listaprecio.getDisponibleSeleccion().compareTo("S") == 0) {
			listaprecio.setDisponibleSeleccion("N");
			transaccionesService.getListaPrecioService().cambiarDisponibilidad("N", listaprecio.getIdListaprecios());
		} else {
			listaprecio.setDisponibleSeleccion("S");
			transaccionesService.getListaPrecioService().cambiarDisponibilidad("S", listaprecio.getIdListaprecios());
		}
		return "";
	}


	public String editarVersionFutura() {
		log.info("Ejecutando ==> editarVersion()");
		Long idVersion = new Long(Session.getRequestParameter("idVerFutura"));
		ListaPrecioVersion aEditar = null;
		Iterator iter = listaprecio.getVersionesFuturas().iterator();
		while (iter.hasNext()) {
			ListaPrecioVersion lpv = (ListaPrecioVersion) iter.next();
			if (lpv.getVersion().compareTo(idVersion) == 0) {
				aEditar = lpv;
				break;
			}
		}
		editarUnaVersion(aEditar);
		return null;
	}


	public String editarVersionAnterior() {
		log.info("Ejecutando ==> editarVersion()");
		Long idVersion = new Long(Session.getRequestParameter("idVerAnterior"));
		ListaPrecioVersion aEditar = null;
		Iterator iter = listaprecio.getVersionesAnteriores().iterator();
		while (iter.hasNext()) {
			ListaPrecioVersion lpv = (ListaPrecioVersion) iter.next();
			if (lpv.getVersion().compareTo(idVersion) == 0) {
				aEditar = lpv;
				break;
			}
		}
		editarUnaVersion(aEditar);
		return null;
	}


	public String editarVersionVigente() {
		editarUnaVersion(listaprecio.getVersionVigente());
		return null;
	}


	public void editarUnaVersion(ListaPrecioVersion lp) {
		verAltaVersiones = false;
		ocultarCalendario();
		if (lp == null) {
			lp = new ListaPrecioVersion();
			verBtnGuardar = true;
		} else {
			fechaOriginal = lp.getFechaVigencia();
			if (fechaOriginal.after(new Timestamp(Calendar.getInstance().getTimeInMillis()))) {
				verBtnGuardar = true;
			} else {
				verBtnGuardar = false;
			}
		}
		lp.setListaPrecio(listaprecio);
		versionEditada = lp;
		items.clear();
		itemsFiel.clear();
		detallesFiel.clear();
		if (lp.getItemsListaPrecio() == null)
			lp.setItemsListaPrecio(new HashSet());
		boolean fi = lp.getListaPrecio().getEsFiel().compareTo("N") == 0;
		// List aux = Convertidores.setToList(lp.getItemsListaPrecio());
		Iterator iter = lp.getItemsListaPrecio().iterator();
		while (iter.hasNext()) {
			ListaPrecioItem lisItem = (ListaPrecioItem) iter.next();
			if (fi) {
				items.add(new ListaPrecioItemWrappers(lisItem));

				indiceItem++;
			} else {
				// se le agrega el item al set que se corresponde con una lista precio fiel.
				itemsFiel.add(new ListaPrecioItemWrappers(lisItem));
				// ordeno los items. Esto es provisorio
				indiceItemFiel++;
			}
			// ordeno los items y los itemsFiel
			List itemsAuxiliares = new ArrayList();
			for (int i = 0; !items.isEmpty(); i++) {
				Iterator iter2 = items.iterator();
				while (iter2.hasNext()) {
					ListaPrecioItemWrappers lpiw = (ListaPrecioItemWrappers) iter2.next();
					if (lpiw.getListaPrecioItem().getComCuotas().intValue() == i) {
						itemsAuxiliares.add(lpiw);
						items.remove(lpiw);
						break;
					}
				}
			}
			items = itemsAuxiliares;
		}
		if (!fi) {
			List detalles = Convertidores.setToList(lp.getDetallesListaPrecio());
			Iterator iterDetalles = detalles.iterator();
			while (iterDetalles.hasNext()) {
				ListaPrecioDetalle lisDetal = (ListaPrecioDetalle) iterDetalles.next();
				detallesFiel.add(new ListaPrecioDetalleWrappers(lisDetal));
				indiceDetallesFiel++;
			}
		}
	}


	public String eliminarDetalle() {
		verAltaVersiones = false;
		if (listaprecio.getEsFiel().compareTo("S") == 0) {
			// Aqui elimina del set de la lista precio fiel.
			Integer idItem = new Integer(Session.getRequestParameter("idDetalleFielElim"));
			Iterator iter = detallesFiel.iterator();
			while (iter.hasNext()) {
				ListaPrecioDetalleWrappers w = (ListaPrecioDetalleWrappers) iter.next();
				if (w.getIndice() == idItem.intValue()) {
					iter.remove();
					break;
				}
			}
		}
		return "";
	}


	public String eliminarItem() {
		verAltaVersiones = false;
		if (listaprecio.getEsFiel().compareTo("N") == 0) {
			// elimina un item de la lista precio comun.
			Integer idItem = new Integer(Session.getRequestParameter("idItemElim"));
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				ListaPrecioItemWrappers w = (ListaPrecioItemWrappers) iter.next();
				if (w.getIndice() == idItem.intValue()) {
					iter.remove();
					break;
				}
			}
		} else {
			// Aqui elimina del set de la lista precio fiel.
			Integer idItem = new Integer(Session.getRequestParameter("idItemFielElim"));
			Iterator iter = itemsFiel.iterator();
			while (iter.hasNext()) {
				ListaPrecioItemWrappers w = (ListaPrecioItemWrappers) iter.next();
				if (w.getIndice() == idItem.intValue()) {
					iter.remove();
					break;
				}
			}
		}
		return "";
	}


	public String eliminarListaPrecio() {
		try {
			log.info("idListaPrecioHidden " + idListaPrecioHidden);
			if (!listaPrecioList.isEmpty()) {
				Iterator iterator = listaPrecioList.iterator();
				while (iterator.hasNext()) {
					ListaPrecioWrappers element = (ListaPrecioWrappers) iterator.next();
					if (element.getIndice() == idListaPrecioHidden.intValue()) {
						element.getListaPrecio().armarListaPrecio();
						if (element.getListaPrecio().getVersionVigente() != null) {
							error.agregar("No se puede borrar la lista de precios pues ya ha entrado en vigencia.");
						} else {
							transaccionesService.getListaPrecioService().borrarListaPrecio(element.getListaPrecio());
							listaPrecioList.remove(element.getListaPrecio());
						}
					}
				}
			}
		} catch (ListaPrecioException e1) {
			error.agregar("Imposible borrar el Lista Precio. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Lista Precio");
			e.printStackTrace();
		}
		listarListaPrecio();
		return null;
	}


	public boolean validarFechaNuevaVersion(Date fechaInicio) throws ListaPrecioException {
		if (fechaInicio.before(Calendar.getInstance().getTime())) {
			error.agregar("La nueva versión debe poseer una fecha de vigencia posterior al dia actual.");
		}
		Iterator iter = listaprecio.getVersionesFuturas().iterator();
		while (iter.hasNext()) {
			ListaPrecioVersion lpv = (ListaPrecioVersion) iter.next();
			if (lpv.getFechaVigencia().compareTo(fechaInicio) == 0) {
				error.agregar("Ya existe una versión de la lista de precios programada para la fecha de vigencia escogida.");
				break;
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String grabarNuevaVersion() {
		error.borrar();
		try {
			List listaDeItems;
			if (!perteneceAFiel) {
				listaDeItems = items;
			} else {
				listaDeItems = itemsFiel;
			}

			if (validarSinFecha() && validarFechaNuevaVersion(getFechaComienzoNuevaVersion())) {
				// ListaPrecioService listaprecioService = transaccionesService.getListaPrecioService();
				ListaPrecioVersion lpv = new ListaPrecioVersion(versionEditada);
				List listaItems = new ArrayList();
				List listaNuevos = new ArrayList();
				Iterator iter = listaDeItems.iterator();
				while (iter.hasNext()) {
					ListaPrecioItemWrappers w = (ListaPrecioItemWrappers) iter.next();
					if (w.getListaPrecioItem().getIdListaPrecioItem() == null) {
						listaNuevos.add(w.getListaPrecioItem());
					} else {
						listaItems.add(new ListaPrecioItem(w.getListaPrecioItem(), lpv));
					}
				}
				List listaDetalles = new ArrayList();
				List listaDetallesNuevos = new ArrayList();
				Iterator iterDetalles = detallesFiel.iterator();
				while (iterDetalles.hasNext()) {
					ListaPrecioDetalleWrappers w = (ListaPrecioDetalleWrappers) iterDetalles.next();
					if (w.getListaPrecioDetalle().getIdListaPrecioDetalle() == null) {
						listaDetallesNuevos.add(w.getListaPrecioDetalle());
					} else {
						listaDetalles.add(new ListaPrecioDetalle(w.getListaPrecioDetalle(), lpv));
					}
				}
				lpv.getItemsListaPrecio().addAll(listaItems);
				lpv.getItemsListaPrecio().addAll(listaNuevos);
				// ahora guardo el set de Detalles
				lpv.getDetallesListaPrecio().addAll(listaDetalles);
				lpv.getDetallesListaPrecio().addAll(listaDetallesNuevos);
				// A partir de aqui debo clonar el objeto para darlo de alta, pero sabiendo que numero de version es
				Long version = transaccionesService.getListaPrecioVersionService().getMaximaVersionListaPrecio(listaprecio.getIdListaprecios());
				lpv.setListaPrecio(listaprecio);
				lpv.setFechaVigencia(new Timestamp(fechaComienzoNuevaVersion.getTime()));
				lpv.setVersion(new Long(version.longValue() + 1));
				transaccionesService.getListaPrecioVersionService().grabarListaPrecioVersion(lpv);
				listaprecio.getVersionesFuturas().add(lpv);
				Collections.sort(listaprecio.getVersionesFuturas());
				if (listaprecio.getVersionVigente() != null) {
					editarUnaVersion(listaprecio.getVersionVigente());
				} else {
					editarUnaVersion((ListaPrecioVersion) (listaprecio.getVersionesFuturas().get(0)));
				}
				ocultarCalendario();
				popup.setPopup(popup.ICONO_OK, "La nueva Versión de la Lista Precio ha sido almacenada.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ListaPrecioVersionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la nueva Versión Lista Precio.");
			e1.printStackTrace();
		} catch (ListaPrecioVersionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la nueva Versión Lista Precio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la nueva Versión Lista Precio.");
			e3.printStackTrace();
		}
		return "";
	}


	public String borrarVersion() {
		error.borrar();
		try {
			if (listaprecio.getVersionVigente() != null || listaprecio.getVersionesFuturas().size() > 1) {
				listaprecio.getVersionesFuturas().remove(versionEditada);
				transaccionesService.getListaPrecioVersionService().borrarListaPrecioVersion(versionEditada);
				ocultarCalendario();
				editarUnaVersion(listaprecio.getVersionVigente());
			} else {
				error.agregar("No se puede borrar la versión, ya que una lista de precios debe tener al menos una versión.");
			}
		} catch (ListaPrecioVersionException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String verCalendario() {
		diasDePago = new ArrayList();
		try {
			AlmanaqueDePagos almanaque = new AlmanaqueDePagos(versionEditada.getComCiclo().intValue(), versionEditada.getComDias().intValue(),
					Integer.valueOf(versionEditada.getFechaLiquidacion()).intValue());
			diasDePago = almanaque.getFechasDePagoComoLista();
		} catch (NumberFormatException e) {
			estadoCalendario = "No son válidos los datos introducidos en ciclos, dias y fecha.";
			e.printStackTrace();
		} catch (ConstructorException e2) {
			estadoCalendario = e2.getMessage();
			e2.printStackTrace();
		} catch (NullPointerException e3) {
			estadoCalendario = "Debe especificar la cantidad de ciclos, los dias y una fecha.";
		}
		calendarioVisible = true;
		return null;
	}


	public String cambiarEstadoListaPrecio() {
		error.borrar();
		try {
			if (!listaPrecioList.isEmpty()) {
				Iterator iterator = listaPrecioList.iterator();
				while (iterator.hasNext()) {
					ListaPrecioWrappers element = (ListaPrecioWrappers) iterator.next();
					if (element.getIndice() == idListaPrecioHidden.intValue()) {
						element.getListaPrecio().setEstaActivo(!element.getListaPrecio().isEstaActivo());
						transaccionesService.getListaPrecioService().actualizarListaPrecio(element.getListaPrecio());
						break;
					}
				}
			}
		} catch (ListaPrecioException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String copiarVersion() {
		ListaPrecio listaCopiada = new ListaPrecio(listaprecio, versionEditada);
		this.listaprecio = listaCopiada;
		Iterator ite = listaprecio.getVersionesListaPrecio().iterator();
		while (ite.hasNext()) {
			versionEditada = (ListaPrecioVersion) ite.next();
			break;
		}
		mostrarOpciones = false;
		alta = true;
		verBtnGuardar = true;

		return "amListaPrecio";
	}


	public String grabar() {
		error.borrar();
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				if (alta) {
					String esSelect = "N";
					if (pertenecaASeleccionable) {
						esSelect = "S";
					}

					listaprecio.setSucursal(sucursal);
					listaprecio.setVersionesListaPrecio(new HashSet());
					versionEditada.setVersion(new Long(1));
					List listaItemsOriginal;
					if (perteneceAFiel) {
						listaItemsOriginal = itemsFiel;
						Iterator iter = detallesFiel.iterator();
						while (iter.hasNext()) {
							ListaPrecioDetalleWrappers w = (ListaPrecioDetalleWrappers) iter.next();
							versionEditada.getDetallesListaPrecio().add(w.getListaPrecioDetalle());
						}
					} else {
						listaItemsOriginal = items;
					}
					Iterator iter = listaItemsOriginal.iterator();
					while (iter.hasNext()) {
						ListaPrecioItemWrappers w = (ListaPrecioItemWrappers) iter.next();
						versionEditada.getItemsListaPrecio().add(w.getListaPrecioItem());
					}
					listaprecio.getVersionesListaPrecio().add(versionEditada);
					transaccionesService.getListaPrecioService().grabarListaPrecio(listaprecio);
					listaprecio.armarListaPrecio();
					ocultarCalendario();
					popup.setPopup(popup.ICONO_OK, "La Lista De Precio ha sido almacenada exitosamente.");
				} else {
					// Inicio los servis que voy a usar
					ListaPrecioVersionService listaprecioVersionService = transaccionesService.getListaPrecioVersionService();
					List listaItems = new ArrayList();
					List listaNuevos = new ArrayList();
					List listaItemsOriginal;
					if (perteneceAFiel) {
						listaItemsOriginal = itemsFiel;
						Iterator iter = detallesFiel.iterator();
						while (iter.hasNext()) {
							ListaPrecioDetalleWrappers w = (ListaPrecioDetalleWrappers) iter.next();
							if (w.getListaPrecioDetalle().getIdListaPrecioDetalle() == null) {
								listaNuevos.add(w.getListaPrecioDetalle());
							} else {
								listaItems.add(w.getListaPrecioDetalle());
							}
						}
						versionEditada.getDetallesListaPrecio().retainAll(listaItems);
						versionEditada.getDetallesListaPrecio().addAll(listaNuevos);
					} else {
						listaItemsOriginal = items;
					}
					Iterator iter = listaItemsOriginal.iterator();
					listaNuevos.clear();
					listaItems.clear();
					while (iter.hasNext()) {
						ListaPrecioItemWrappers w = (ListaPrecioItemWrappers) iter.next();
						if (w.getListaPrecioItem().getIdListaPrecioItem() == null) {
							listaNuevos.add(w.getListaPrecioItem());
						} else {
							listaItems.add(w.getListaPrecioItem());
						}
					}
					versionEditada.getItemsListaPrecio().retainAll(listaItems);
					versionEditada.getItemsListaPrecio().addAll(listaNuevos);
					listaprecioVersionService.actualizarListaPrecioVersion(versionEditada);
					ocultarCalendario();
					popup.setPopup(popup.ICONO_OK, "La versión ha sido almacenada exitosamente.");
				}
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ListaPrecioVersionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Lista Precio.");
			e1.printStackTrace();
		} catch (ListaPrecioVersionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Lista Precio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Lista Precio.");
			e3.printStackTrace();
		}
		return "";
	}


	public String aceptarNuevaVersion() {
		verAltaVersiones = true;
		return null;
	}


	public String cancelarNuevaVersion() {
		verAltaVersiones = false;
		return null;
	}


	public String ocultarCalendario() {
		diasDePago = new ArrayList();
		estadoCalendario = "";
		calendarioVisible = false;
		return "";
	}


	public void borrar() {
		error.borrar();
		mostrarOpciones = true;
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Lista Precio";
		popup.borrar();
		items = new ArrayList();
		itemsFiel = new ArrayList();
		detallesFiel = new ArrayList();
		tipoLista = "N";
		verBtnVolver = false;
	}


	public String aceptarTipoLista() {
		mostrarOpciones = false;
		if (tipoLista.compareTo("N") == 0) {
			listaprecio = new ListaPrecio();
			listaprecio.setActivo("S");
			versionEditada = new ListaPrecioVersion();
			versionEditada.setListaPrecio(listaprecio);
			indiceItem = 0;
			indiceItemFiel = 0;
			verBtnGuardar = true;
			Calendar fecha = Calendar.getInstance();
			fechaComienzoNuevaVersion = new Date(fecha.getTime().getTime());
			fecha.add(Calendar.DAY_OF_MONTH, +1);
			verAltaVersiones = false;
			calendarioVisible = false;
			diasDePago = new ArrayList();
			estadoCalendario = "";
		} else {
			listaprecio = new ListaPrecio();
			listaprecio.setEsFiel("S");
			listaprecio.setActivo("S");
			versionEditada = new ListaPrecioVersion();
			versionEditada.setListaPrecio(listaprecio);
			indiceItem = 0;
			indiceItemFiel = 0;
			verBtnGuardar = true;
			Calendar fecha = Calendar.getInstance();
			fechaComienzoNuevaVersion = new Date(fecha.getTime().getTime());
			fecha.add(Calendar.DAY_OF_MONTH, +1);
			verAltaVersiones = false;
			calendarioVisible = false;
			diasDePago = new ArrayList();
			estadoCalendario = "";
		}
		return "";
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (listaprecio.getEsFiel().compareTo("N") == 0) {
			if (!listaprecio.validar()) {
				String cadenaDeErrores[] = listaprecio.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!versionEditada.validar()) {
				String cadenaDeErrores[] = versionEditada.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!items.isEmpty()) {
				boolean cuotaRepetidaAgregada = false; // un flag que nos dice si ya hemos agregado el error de nro. de cuota repetida.
				List nrosDeCuota = new ArrayList();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					ListaPrecioItemWrappers wrap = (ListaPrecioItemWrappers) iter.next();
					if (wrap.getListaPrecioItem().getComCuotas() != null && !nrosDeCuota.contains(wrap.getListaPrecioItem().getComCuotas())) {
						nrosDeCuota.add(wrap.getListaPrecioItem().getComCuotas());
					} else {
						if (wrap.getListaPrecioItem().getComCuotas() != null && !cuotaRepetidaAgregada) {
							cuotaRepetidaAgregada = true;
							error.agregar("Hay numeros de cuota repetidos.");
						}
					}
					if (!wrap.getListaPrecioItem().validar()) {
						String cadenaDeErrores[] = wrap.getListaPrecioItem().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
		} else {
			// aca validara si es lista de precio fiel.
			if (!listaprecio.validarFormatoFiel()) {
				String cadenaDeErrores[] = listaprecio.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!versionEditada.validarFormatoFiel()) {
				String cadenaDeErrores[] = versionEditada.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!itemsFiel.isEmpty()) {
				boolean cuotaRepetidaAgregada = false; // un flag que nos dice si ya hemos agregado el error de nro. de cuota repetida.
				List nrosDeCuota = new ArrayList();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					ListaPrecioItemWrappers wrap = (ListaPrecioItemWrappers) iter.next();
					if (wrap.getListaPrecioItem().getNroCuotaFiel() != null && !nrosDeCuota.contains(wrap.getListaPrecioItem().getNroCuotaFiel())) {
						nrosDeCuota.add(wrap.getListaPrecioItem().getNroCuotaFiel());
					} else {
						if (wrap.getListaPrecioItem().getNroCuotaFiel() != null && !cuotaRepetidaAgregada) {
							cuotaRepetidaAgregada = true;
							error.agregar("Hay numeros de cuota repetidos.");
						}
					}
					if (!wrap.getListaPrecioItem().validarFormatoFiel()) {
						String cadenaDeErrores[] = wrap.getListaPrecioItem().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
			if (!detallesFiel.isEmpty()) {
				Iterator iter = detallesFiel.iterator();
				while (iter.hasNext()) {
					ListaPrecioDetalleWrappers wrap = (ListaPrecioDetalleWrappers) iter.next();
					if (!wrap.getListaPrecioDetalle().validar()) {
						String cadenaDeErrores[] = wrap.getListaPrecioDetalle().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarSinFecha() {
		error.borrar();
		if (listaprecio.getEsFiel().compareTo("N") == 0) {
			if (!listaprecio.validar()) {
				String cadenaDeErrores[] = listaprecio.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!versionEditada.validarSinFecha()) {
				String cadenaDeErrores[] = versionEditada.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!items.isEmpty()) {
				boolean cuotaRepetidaAgregada = false; // un flag que nos dice si ya hemos agregado el error de nro. de cuota repetida.
				List nrosDeCuota = new ArrayList();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					ListaPrecioItemWrappers wrap = (ListaPrecioItemWrappers) iter.next();
					if (wrap.getListaPrecioItem().getComCuotas() != null && !nrosDeCuota.contains(wrap.getListaPrecioItem().getComCuotas())) {
						nrosDeCuota.add(wrap.getListaPrecioItem().getComCuotas());
					} else {
						if (wrap.getListaPrecioItem().getComCuotas() != null && !cuotaRepetidaAgregada) {
							cuotaRepetidaAgregada = true;
							error.agregar("Hay numeros de cuota repetidos.");
						}
					}
					if (!wrap.getListaPrecioItem().validar()) {
						String cadenaDeErrores[] = wrap.getListaPrecioItem().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
		} else {
			if (!listaprecio.validarFormatoFiel()) {
				String cadenaDeErrores[] = listaprecio.getErrores().split(":");
				for (int i = 0; i < cadenaDeErrores.length; i++) {
					error.agregar(cadenaDeErrores[i]);
				}
			}
			if (!itemsFiel.isEmpty()) {
				boolean cuotaRepetidaAgregada = false; // un flag que nos dice si ya hemos agregado el error de nro. de cuota repetida.
				List nrosDeCuota = new ArrayList();
				Iterator iter = itemsFiel.iterator();
				while (iter.hasNext()) {
					ListaPrecioItemWrappers wrap = (ListaPrecioItemWrappers) iter.next();
					if (wrap.getListaPrecioItem().getNroCuotaFiel() != null && !nrosDeCuota.contains(wrap.getListaPrecioItem().getNroCuotaFiel())) {
						nrosDeCuota.add(wrap.getListaPrecioItem().getNroCuotaFiel());
					} else {
						if (wrap.getListaPrecioItem().getNroCuotaFiel() != null && !cuotaRepetidaAgregada) {
							cuotaRepetidaAgregada = true;
							error.agregar("Hay numeros de cuota repetidos.");
						}
					}
					if (!wrap.getListaPrecioItem().validarFormatoFiel()) {
						String cadenaDeErrores[] = wrap.getListaPrecioItem().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
			if (!detallesFiel.isEmpty()) {
				Iterator iter = detallesFiel.iterator();
				while (iter.hasNext()) {
					ListaPrecioDetalleWrappers wrap = (ListaPrecioDetalleWrappers) iter.next();
					if (!wrap.getListaPrecioDetalle().validar()) {
						String cadenaDeErrores[] = wrap.getListaPrecioDetalle().getErrores().split(":");
						for (int i = 0; i < cadenaDeErrores.length; i++) {
							error.agregar(cadenaDeErrores[i]);
						}
						break;
					}
				}
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoListaPrecio() {
		return inicializar();
	}


	public String irAModificarListaPrecio() {
		alta = false;
		popup.borrar();
		indiceItem = 0;
		indiceItemFiel = 0;
		tituloCorto = "Modificar Lista Precio";
		return null;
	}


	public String irAListarListaPrecio() {
		borrar();
		listaprecio = new ListaPrecio();
		listaPrecioList = new ArrayList();
		tituloCorto = "Listado de Lista Precio";
		Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
		return "";
	}


	public String listarListaPrecio() {
		listaPrecioList = new ArrayList();
		verBtnVolver = false;
		try {
			if (tipoLista == null)
				tipoLista = "N";
			Filtro filtro = new Filtro();
			if (listaprecio.getIdListaprecios() != null && !listaprecio.getIdListaprecios().equals(new Long(0))) {
				filtro.agregarCampoOperValor("idListaprecios", Filtro.IGUAL, listaprecio.getIdListaprecios());
			}
			if (listaprecio.getDescripcion() != null && listaprecio.getDescripcion().compareTo("") != 0) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, listaprecio.getDescripcion());
			}
			filtro.agregarCampoOperValor("esFiel", Filtro.LIKE, tipoLista);
			List aux = transaccionesService.getListaPrecioService().getListaPrecio(filtro);
			Iterator iter = aux.iterator();
			while (iter.hasNext())
			{
				ListaPrecio element = (ListaPrecio) iter.next();
				ListaPrecioWrappers wrappers = new ListaPrecioWrappers(element);
				listaPrecioList.add(wrappers);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
		return null;
	}


	public String volverAlListado() {
		return listarListaPrecio();
	}


	public String agregarItem() {
		verAltaVersiones = false;
		if (listaprecio.getEsFiel().compareTo("N") == 0) {
			items.add(new ListaPrecioItemWrappers());
		} else {
			itemsFiel.add(new ListaPrecioItemWrappers());
		}
		return "";
	}


	public String agregarDetalle() {
		verAltaVersiones = false;
		detallesFiel.add(new ListaPrecioDetalleWrappers());
		return "";
	}

	public class ListaPrecioWrappers {

		private int indice;
		private ListaPrecio listaPrecio;


		public ListaPrecioWrappers(ListaPrecio listaPrecio) {
			super();
			this.listaPrecio = listaPrecio;
			this.indice = indiceListadoListaPrecio++;

		}


		public ListaPrecio getListaPrecio() {
			return listaPrecio;
		}


		public void setListaPrecio(ListaPrecio listaPrecio) {
			this.listaPrecio = listaPrecio;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}
	}

	public class ListaPrecioItemWrappers {

		private int indice;
		private ListaPrecioItem listaPrecioItem;


		/**
		 * Para armar la lista en las ediciones
		 * */
		public ListaPrecioItemWrappers(ListaPrecioItem listaPrecioItem) {
			super();
			this.listaPrecioItem = listaPrecioItem;
			if (!perteneceAFiel) {
				this.indice = indiceItem++;
			} else {
				this.indice = indiceItemFiel++;
			}
		}


		public ListaPrecioItemWrappers() {
			this.listaPrecioItem = new ListaPrecioItem();
			this.listaPrecioItem.setListaPrecioVersion(versionEditada);
			if (!perteneceAFiel) {
				this.indice = indiceItem++;
			} else {
				this.indice = indiceItemFiel++;
			}
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public ListaPrecioItem getListaPrecioItem() {
			return listaPrecioItem;
		}


		public void setListaPrecioItem(ListaPrecioItem listaPrecioItem) {
			this.listaPrecioItem = listaPrecioItem;
		}
	}

	public class ListaPrecioDetalleWrappers {

		private int indice;
		private ListaPrecioDetalle listaPrecioDetalle;


		/**
		 * Para armar la lista en las ediciones
		 * */
		public ListaPrecioDetalleWrappers(ListaPrecioDetalle listaPrecioDetalle) {
			super();
			this.listaPrecioDetalle = listaPrecioDetalle;
			this.indice = indiceDetallesFiel++;
		}


		public ListaPrecioDetalleWrappers() {
			this.listaPrecioDetalle = new ListaPrecioDetalle();
			this.listaPrecioDetalle.setListaPrecioVersion(versionEditada);
			this.indice = indiceDetallesFiel++;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public ListaPrecioDetalle getListaPrecioDetalle() {
			return listaPrecioDetalle;
		}


		public void setListaPrecioDetalle(ListaPrecioDetalle listaPrecioDetalle) {
			this.listaPrecioDetalle = listaPrecioDetalle;
		}


		public String eliminarDetalleFiel() {
			detallesFiel.remove(this);
			return "";
		}

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public ListaPrecio getListaPrecio() {
		return listaprecio;
	}


	public void setListaPrecio(ListaPrecio listaprecio) {
		this.listaprecio = listaprecio;
	}


	public Long getIdListaPrecioHidden() {
		return idListaPrecioHidden;
	}


	public void setIdListaPrecioHidden(Long idListaPrecioHidden) {
		this.idListaPrecioHidden = idListaPrecioHidden;
	}


	public List getListaPrecioList() {
		return listaPrecioList;
	}


	public void setListaPrecioList(List listaPrecioList) {
		this.listaPrecioList = listaPrecioList;
	}


	public Date getFechaVigencia() {
		return (Date) versionEditada.getFechaVigencia();
	}


	public void setFechaVigencia(Date fechaVigencia) {
		if (fechaVigencia == null) {
			versionEditada.setFechaVigencia(null);
		} else {
			versionEditada.setFechaVigencia(new Timestamp(fechaVigencia.getTime()));
		}
	}


	public List getItems() {
		return items;
	}


	public void setItems(List items) {
		this.items = items;
	}


	public boolean isVerBtnGuardar() {
		return verBtnGuardar;
	}


	public void setVerBtnGuardar(boolean verBtnGuardar) {
		this.verBtnGuardar = verBtnGuardar;
	}


	public ListaPrecioVersion getVersionEditada() {
		return versionEditada;
	}


	public void setVersionEditada(ListaPrecioVersion versionEditada) {
		this.versionEditada = versionEditada;
	}


	public Date getFechaComienzoNuevaVersion() {
		return fechaComienzoNuevaVersion;
	}


	public void setFechaComienzoNuevaVersion(Date fechaComienzoNuevaVersion) {
		this.fechaComienzoNuevaVersion = fechaComienzoNuevaVersion;
	}


	public boolean isVerAltaVersiones() {
		return verAltaVersiones;
	}


	public void setVerAltaVersiones(boolean verAltaVersiones) {
		this.verAltaVersiones = verAltaVersiones;
	}


	public boolean isVerEdicion() {
		return (verBtnGuardar && !alta);
	}


	public boolean isCalendarioVisible() {
		return calendarioVisible;
	}


	public void setCalendarioVisible(boolean calendarioVisible) {
		this.calendarioVisible = calendarioVisible;
	}


	public List getDiasDePago() {
		return diasDePago;
	}


	public void setDiasDePago(List diasDePago) {
		this.diasDePago = diasDePago;
	}


	public String getEstadoCalendario() {
		return estadoCalendario;
	}


	public void setEstadoCalendario(String estadoCalendario) {
		this.estadoCalendario = estadoCalendario;
	}


	public boolean isPerteneceAFiel() {
		if (listaprecio != null && listaprecio.getEsFiel() != null) {
			perteneceAFiel = listaprecio.getEsFiel().compareTo("S") == 0;
			return perteneceAFiel;
		} else {
			return false;
		}
	}


	public void setPerteneceAFiel(boolean perteneceAFiel) {
		this.perteneceAFiel = perteneceAFiel;
	}


	public List getItemsFiel() {
		return itemsFiel;
	}


	public void setItemsFiel(List itemsFiel) {
		this.itemsFiel = itemsFiel;
	}


	public boolean isMostrarOpciones() {
		return mostrarOpciones;
	}


	public void setMostrarOpciones(boolean mostrarOpciones) {
		this.mostrarOpciones = mostrarOpciones;
	}


	public String getTipoLista() {
		return tipoLista;
	}


	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}


	public List getDetallesFiel() {
		return detallesFiel;
	}


	public void setDetallesFiel(List detallesFiel) {
		this.detallesFiel = detallesFiel;
	}


	public boolean isVerBtnVolver() {
		return verBtnVolver;
	}


	public void setVerBtnVolver(boolean verBtnVolver) {
		this.verBtnVolver = verBtnVolver;
	}


	// *********funcionalidad agregada para archivo Adjunto.

	public String abrirArchivoAdjunto() {
		if (versionEditada.getUrlArchivo() != null && versionEditada.getUrlArchivo() != "") {
			try {
				ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosListasDePrecios + "/" + versionEditada.getUrlArchivo()
						+ "',1000,700), 'titlebar=no';");
			} catch (Exception e) {
				log.info("Error al intentar leer el archivo");
				e.printStackTrace();
			}
		}
		return null;
	}


	public String saveFile() {
		try {

			int size = new Long(imagen.getSize()).intValue();
			String path = Archivo.grabarArchivo(imagen.getInputStream(), imagen.getName(), size, Archivo.archivosListasDePrecios);
			log.info(path);

			if (path != null && !path.equals("No grabo")) {
				versionEditada.setUrlArchivo(path);
			}
		} catch (Exception x) {
			x.printStackTrace();
			return null;
		}
		return null;
	}


	public UploadedFile getImagen() {
		return imagen;
	}


	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}


	public String eliminarArchivoAdjunto() {
		versionEditada.setUrlArchivo("");
		return null;
	}

}

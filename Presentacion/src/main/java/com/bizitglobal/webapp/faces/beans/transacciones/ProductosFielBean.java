package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoFielVersionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ReglaPFException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoFielVersion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DetalleReglaPF;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DetalleReglaXConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ReglaPF;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProductosFielBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ProductosFielBean.class);
	private Concepto conceptoEditado;
	private ConceptoFielVersion conceptoFielVersionEditado;
	private List detallesXConcepto; // una lista con objetos wrapper de detalleXConcepto;
	private String listaPrecioDefecto;
	private String listaInvestigada;
	private boolean hayInvestigada;

	private Long idListaPrecioHidden;

	// definicion de listas del objeto Concepto fiel, para mostrarlo de forma aplanada.
	private List conceptoFielList1;
	private List conceptoFielList2;
	private List conceptoFielList3;

	// definicion de listas del objeto ListaPrecio fiel, para mostrarlo de forma aplanada.
	private List listasFielList1;
	private List listasFielList2;
	private List listasFielList3;

	private List conceptosViejos; // una lista que muestra los conceptos en donde se utiliza la lista de precios especificada
	private List conceptosActuales;
	private List conceptosFuturos;

	private boolean verBtnGuardar;
	private Date fechaOriginal;// esta fecha es para cuando se edita se pueda deducir si es editable la listaPrecio.
								// si la fecha original es menor a la actual, entonces nose puede.!!
	private Date fechaVigencia;
	private HtmlSelectOneMenu reglaHtml;
	private Long idReglaSeleccionada;
	private ReglaPF reglaPF; // guarda la regla actualmente seleccionada.
	List listaDeReglasPF; // guarda la lista de ReglasPF
	private List reglasItems; // la lista de SelectItems de REglasPF
	private boolean verAltaNuevaVersion; // controla la visibilidad del panel de alta de una version modificada..
	// private boolean verEdicion; // controla la visibilidad del boton guardar
	private Date fechaComienzoNuevaVersion;

	private ConceptoFielVersion versionConceptoEditada; // un objeto que representa la version que estamos editando o mostrando.


	public ProductosFielBean() {
		super();
		conceptoFielList1 = new ArrayList();
		conceptoFielList2 = new ArrayList();
		conceptoFielList3 = new ArrayList();
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
		leerConceptos();
		leerListasPrecios();
		leerReglas();
		return "productosFiel";
	}


	public void leerConceptos() {
		try {
			List listaDeConceptos = transaccionesService.getConceptoService().getConcepto(new Filtro("esFiel", Filtro.LIKE, "S"));
			int contador = 1; // utilizo el contador con el resto en la division por tres para desplegar los conceptos en tres listas.
			Iterator iterConceptos = listaDeConceptos.iterator();
			while (iterConceptos.hasNext()) {
				switch (contador % 3) {
				case 0:
					conceptoFielList3.add(new ConceptoWrappers((Concepto) iterConceptos.next()));
					break;
				case 1:
					conceptoFielList1.add(new ConceptoWrappers((Concepto) iterConceptos.next()));
					break;
				case 2:
					conceptoFielList2.add(new ConceptoWrappers((Concepto) iterConceptos.next()));
					break;
				}
				contador++;
			}
		} catch (ConceptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void leerListasPrecios() {
		try {
			List listaDeListasPrecios = transaccionesService.getListaPrecioService().getListaPrecio(new Filtro("esFiel", Filtro.LIKE, "S"));
			int contador = 1; // utilizo el contador con el resto en la division por tres para desplegar los conceptos en tres listas.
			Iterator iterListas = listaDeListasPrecios.iterator();
			while (iterListas.hasNext()) {
				switch (contador % 3) {
				case 0:
					listasFielList3.add(new ListaPrecioWrapper((ListaPrecio) iterListas.next()));
					break;
				case 1:
					listasFielList1.add(new ListaPrecioWrapper((ListaPrecio) iterListas.next()));
					break;
				case 2:
					listasFielList2.add(new ListaPrecioWrapper((ListaPrecio) iterListas.next()));
					break;
				}
				contador++;
			}
		} catch (ListaPrecioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void leerReglas() {
		reglasItems = new ArrayList();
		try {
			listaDeReglasPF = transaccionesService.getReglaPFService().getReglaPF(new Filtro());
			Iterator iterReglas = listaDeReglasPF.iterator();
			while (iterReglas.hasNext()) {
				ReglaPF regla = (ReglaPF) iterReglas.next();
				if (regla.getIdReglaPF().intValue() == 0)
					reglaPF = regla;
				reglasItems.add(new SelectItem(regla.getIdReglaPF(), regla.getDescripcion()));
			}
		} catch (ReglaPFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void buscarDetallesXConcepto(ValueChangeEvent event) {
		detallesXConcepto.clear();
		Long valorSeleccionado = (Long) reglaHtml.getValue();
		Iterator iter = listaDeReglasPF.iterator();
		while (iter.hasNext()) {
			ReglaPF reg = (ReglaPF) iter.next();
			if (reg.getIdReglaPF().intValue() == valorSeleccionado.intValue()) {
				reglaPF = reg;
				break;
			}
		}

		// decidir aqui si se crean detalles nuevos o se recuperan los que tenia el concepto.
		try {
			Long seleccion = ((DetalleReglaXConcepto) (Convertidores.setToList(conceptoFielVersionEditado.getDetallesReglasXConcepto())).get(0))
					.getDetalleReglaPF().getReglaPF().getIdReglaPF();
			if (seleccion.intValue() == valorSeleccionado.intValue()) {
				// se recuperan los de los items....
				Iterator it = conceptoFielVersionEditado.getDetallesReglasXConcepto().iterator();
				while (it.hasNext()) {
					DetalleReglaXConcepto de = (DetalleReglaXConcepto) it.next();
					detallesXConcepto.add(new WrapperDetalleReglaXConcepto(de));
				}
			} else {
				// se crean nuevos
				Iterator iterDeta = reglaPF.getDetallesReglaPF().iterator();
				while (iterDeta.hasNext()) {
					DetalleReglaPF det = (DetalleReglaPF) iterDeta.next();
					detallesXConcepto.add(new WrapperDetalleReglaXConcepto(det));
				}
			}
		} catch (Exception e) {
			// se crean nuevos
			try {
				Iterator iterDeta = reglaPF.getDetallesReglaPF().iterator();
				while (iterDeta.hasNext()) {
					DetalleReglaPF det = (DetalleReglaPF) iterDeta.next();
					detallesXConcepto.add(new WrapperDetalleReglaXConcepto(det));
				}
			} catch (NullPointerException ex) {
				error.agregar("No hay ninguna regla dada de alta en el sistema. Comuniquese con el departamento de Sistemas");
			}

		}
	}


	public boolean isVerEdicion() {
		if (conceptoFielVersionEditado == null)
			return false;
		return (conceptoEditado.getVersionesFuturas().contains(conceptoFielVersionEditado));
	}


	public boolean isVerGuardar() {
		return (isVerEdicion() || isAltaFiltros());
	}


	// public String editarListaPrecio() {
	// String result = null;
	// borrarBaseBean();
	// borrar();
	// alta = false;
	// tituloCorto = "Modificar Lista Precio";
	// try {
	// log.info("Tamaño listaPrecioList " + listaPrecioList.size());
	// if(!listaPrecioList.isEmpty()){
	// Iterator iterator = listaPrecioList.iterator();
	// while (iterator.hasNext()) {
	// ListaPrecioWrappers element = (ListaPrecioWrappers) iterator.next();
	// if(element.getIndice() == idListaPrecioHidden.intValue()){
	// listaprecio = transaccionesService.getListaPrecioService().leerListaPrecio(element.getListaPrecio().getIdListaprecios());
	// listaprecio.armarListaPrecio();
	// mostrarOpciones = false;
	// break;
	// }
	// }
	// if (listaprecio.getVersionVigente()!=null) {
	// editarUnaVersion(listaprecio.getVersionVigente());
	// } else {
	// editarUnaVersion((ListaPrecioVersion)(listaprecio.getVersionesFuturas().get(0)));
	// }
	// result = "amListaPrecio";
	// }
	// } catch (ListaPrecioException e1) {
	// error.agregar("Ocurrio un error al intentar editar el Lista Precio");
	// e1.printStackTrace();
	// Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
	// } catch (Exception e) {
	// error.agregar("Ocurrio un error al intentar editar el Lista Precio");
	// e.printStackTrace();
	// Session.redirect("/tarjetafiel/transacciones/listarListaPrecio.jsf");
	// }
	// return result;
	// }

	public void agregarListaPrecioDefecto() {
		ListaPrecioFielPopupBean bean = (ListaPrecioFielPopupBean) Session.getBean("ListaPrecioFielPopupBean");
		bean.inicializarDesdePopup(bean.PRODUCTO_FIEL, (conceptoFielVersionEditado.getListaPrecioDefecto() == null) ? new ListaPrecio()
				: conceptoFielVersionEditado.getListaPrecioDefecto(), this.conceptoFielVersionEditado);
	}


	public String editarVersionFutura() {
		log.info("Ejecutando ==> editarVersion()");
		Long idVersion = new Long(Session.getRequestParameter("idVerFutura"));
		ConceptoFielVersion aEditar = null;
		Iterator iter = conceptoEditado.getVersionesFuturas().iterator();
		while (iter.hasNext()) {
			ConceptoFielVersion cfv = (ConceptoFielVersion) iter.next();
			if (cfv.getVersion().intValue() == idVersion.intValue()) {
				aEditar = cfv;
				break;
			}
		}
		editarUnaVersion(aEditar);
		return null;
	}


	public String editarVersionAnterior() {
		log.info("Ejecutando ==> editarVersion()");
		Long idVersion = new Long(Session.getRequestParameter("idVerAnterior"));
		ConceptoFielVersion aEditar = null;
		Iterator iter = conceptoEditado.getVersionesAnteriores().iterator();
		while (iter.hasNext()) {
			ConceptoFielVersion cfv = (ConceptoFielVersion) iter.next();
			if (cfv.getVersion().intValue() == idVersion.intValue()) {
				aEditar = cfv;
				break;
			}
		}
		editarUnaVersion(aEditar);
		return null;
	}


	public String editarVersionVigente() {
		editarUnaVersion(conceptoEditado.getVersionVigente());
		return null;
	}


	public void editarUnaVersion(ConceptoFielVersion cfv) {
		detallesXConcepto.clear();
		if (cfv == null) {
			cfv = new ConceptoFielVersion();
			verBtnGuardar = true;
		} else {
			fechaOriginal = cfv.getFechaVigencia();
			if (fechaOriginal.after(new Timestamp(Calendar.getInstance().getTimeInMillis()))) {
				verBtnGuardar = true;
			} else {
				verBtnGuardar = false;
			}

		}

		Iterator iter = cfv.getDetallesReglasXConcepto().iterator();
		while (iter.hasNext()) {
			DetalleReglaXConcepto det = (DetalleReglaXConcepto) iter.next();
			detallesXConcepto.add(new WrapperDetalleReglaXConcepto(det));
		}
		Long seleccion = new Long(0);
		try {
			seleccion = ((DetalleReglaXConcepto) (Convertidores.setToList(cfv.getDetallesReglasXConcepto())).get(0)).getDetalleReglaPF().getReglaPF()
					.getIdReglaPF();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		reglaHtml.setValue(seleccion);
		conceptoFielVersionEditado = cfv;
	}


	public boolean validarFechaNuevaVersion(Date fechaInicio) {
		if (fechaInicio.before(Calendar.getInstance().getTime())) {
			error.agregar("La nueva versión debe poseer una fecha de vigencia posterior al dia actual.");
		}
		Iterator iter = conceptoEditado.getVersionesFuturas().iterator();
		while (iter.hasNext()) {
			ConceptoFielVersion cfv = (ConceptoFielVersion) iter.next();
			if (cfv.getFechaVigencia().compareTo(fechaInicio) == 0) {
				error.agregar("Ya existe una versión de la lista de precios programada para la fecha de vigencia escogida.");
				break;
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String grabarNuevaVersion() {
		error.borrar();
		try {
			if (validarSinFecha() && validarFechaNuevaVersion(getFechaComienzoNuevaVersion())) {
				// recorro el set de versiones y obtengo el ultimo.
				int maxVersion = 0;
				Iterator iterD = conceptoEditado.getConceptoFielVersion().iterator();
				while (iterD.hasNext()) {
					ConceptoFielVersion conF = (ConceptoFielVersion) iterD.next();
					if (conF.getVersion().intValue() > maxVersion)
						maxVersion = conF.getVersion().intValue();
				}

				ConceptoFielVersion lpv = new ConceptoFielVersion(conceptoFielVersionEditado);
				conceptoFielVersionEditado = lpv;
				conceptoFielVersionEditado.setVersion(new Integer(maxVersion + 1));
				conceptoEditado.getConceptoFielVersion().add(conceptoFielVersionEditado);
				conceptoFielVersionEditado.setFechaVigencia(getFechaComienzoNuevaVersion());
				Iterator iter = detallesXConcepto.iterator();
				while (iter.hasNext()) {
					WrapperDetalleReglaXConcepto wrap = (WrapperDetalleReglaXConcepto) iter.next();
					DetalleReglaXConcepto concep = wrap.getDetalleReglaXConcepto();
					concep.setConceptoFielVersion(conceptoFielVersionEditado);
					conceptoFielVersionEditado.getDetallesReglasXConcepto().add(concep);
				}
				conceptoEditado.armarConcepto();
				transaccionesService.getConceptoFielVersionService().grabarConceptoFielVersion(conceptoFielVersionEditado);
				// transaccionesService.getConceptoService().actualizarConcepto(conceptoEditado);

				verAltaNuevaVersion = false;
				popup.setPopup(popup.ICONO_OK, "La nueva Versión del Producto Fiel ha sido almacenada.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ConceptoFielVersionException e) {
			e.printStackTrace();
		}
		return "";
	}


	public String borrarVersion() {
		error.borrar();
		try {
			conceptoEditado.getConceptoFielVersion().remove(conceptoFielVersionEditado);
			detallesXConcepto.clear();
			conceptoEditado.armarConcepto();
			transaccionesService.getConceptoService().actualizarConcepto(conceptoEditado);
			conceptoFielVersionEditado = null;
			conceptoEditado.armarConcepto();
			if (!conceptoEditado.getConceptoFielVersion().isEmpty()) {
				if (conceptoEditado.getVersionVigente() != null) {
					editarUnaVersion(conceptoEditado.getVersionVigente());
				} else {
					editarUnaVersion((ConceptoFielVersion) (conceptoEditado.getVersionesFuturas().get(0)));
				}
			} else {
				// el concepto clickeado no posee versiones aun.
				conceptoFielVersionEditado = new ConceptoFielVersion();
				reglaHtml.setValue(new Long(0));
				buscarDetallesXConcepto(null);
			}
		} catch (ConceptoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String aceptarNuevaVersion() {
		verAltaNuevaVersion = true;
		return null;
	}


	public String cancelarNuevaVersion() {
		verAltaNuevaVersion = false;
		return null;
	}


	public String grabar() {
		error.borrar();
		try {
			if (validar()) {
				if (conceptoEditado.getConceptoFielVersion().isEmpty()) {
					// es el primer alta del objeto...
					ConceptoFielVersion conFielVer = conceptoFielVersionEditado;
					conFielVer.setConcepto(conceptoEditado);
					conceptoEditado.getConceptoFielVersion().add(conFielVer);
					conFielVer.setVersion(new Integer(1));
					Iterator iter = detallesXConcepto.iterator();
					while (iter.hasNext()) {
						WrapperDetalleReglaXConcepto wrap = (WrapperDetalleReglaXConcepto) iter.next();
						DetalleReglaXConcepto concep = wrap.getDetalleReglaXConcepto();
						concep.setConceptoFielVersion(conFielVer);
						conFielVer.getDetallesReglasXConcepto().add(concep);
					}
					// conFielVer.setFechaVigencia(fechaVigencia);
					conceptoEditado.armarConcepto();
					transaccionesService.getConceptoService().actualizarConcepto(conceptoEditado);
				} else {
					// aqui solo puede entrar si es una concepto fiel que no entro en vigencia, pudiendolo modificar...

				}
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ConceptoException e) {
			e.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Productos de Fiel";
		conceptoFielList1 = new ArrayList();
		conceptoFielList2 = new ArrayList();
		conceptoFielList3 = new ArrayList();
		listasFielList1 = new ArrayList();
		listasFielList2 = new ArrayList();
		listasFielList3 = new ArrayList();
		detallesXConcepto = new ArrayList();
		reglaHtml = new HtmlSelectOneMenu();
		idReglaSeleccionada = new Long(0);
		fechaVigencia = Calendar.getInstance().getTime();
		conceptoEditado = null;
		conceptoFielVersionEditado = null;
		listaInvestigada = "";
		hayInvestigada = false;
		popup.borrar();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}

	public class ListaPrecioWrapper {

		private ListaPrecio listaPrecio;


		public ListaPrecioWrapper(ListaPrecio listaPrecio) {
			this.listaPrecio = listaPrecio;
		}


		public void listarConceptos() {
			Filtro filtro = new Filtro();
			// transacciones
		}


		public String editar() {
			listaInvestigada = listaPrecio.getDescripcion();
			hayInvestigada = true;
			Collection viejos = new TreeSet();
			Collection actuales = new TreeSet();
			Collection futuros = new TreeSet();
			conceptosActuales = new ArrayList();
			conceptosViejos = new ArrayList();
			conceptosFuturos = new ArrayList();
			// recorro todos los conceptos, los construyo y veo si el set de versiones futuras, o actuales o pasadas, tienen esa lista en uso.
			Iterator iter = conceptoFielList1.iterator();
			while (iter.hasNext()) {
				ConceptoWrappers con = (ConceptoWrappers) iter.next();
				con.getConcepto().armarConcepto();
				try {
					Iterator ite = con.getConcepto().getVersionesAnteriores().iterator();
					while (ite.hasNext()) {
						ConceptoFielVersion conVer = (ConceptoFielVersion) ite.next();
						Iterator it = conVer.getDetallesReglasXConcepto().iterator();
						while (it.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}

				try {
					// version actual
					ConceptoFielVersion conVer = con.getConcepto().getVersionVigente();
					Iterator it = conVer.getDetallesReglasXConcepto().iterator();
					while (it.hasNext()) {
						DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
						if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
							actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
						}
					}
					if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
						actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
				} catch (NullPointerException e) {
					// nada
				}

				try {
					Iterator ite2 = con.getConcepto().getVersionesFuturas().iterator();
					while (ite2.hasNext()) {
						ConceptoFielVersion conVer2 = (ConceptoFielVersion) ite2.next();
						Iterator it2 = conVer2.getDetallesReglasXConcepto().iterator();
						while (it2.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it2.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer2.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}
			}
			Iterator iter2 = conceptoFielList2.iterator();
			while (iter2.hasNext()) {
				ConceptoWrappers con = (ConceptoWrappers) iter2.next();
				con.getConcepto().armarConcepto();

				try {
					Iterator ite = con.getConcepto().getVersionesAnteriores().iterator();
					while (ite.hasNext()) {
						ConceptoFielVersion conVer = (ConceptoFielVersion) ite.next();
						Iterator it = conVer.getDetallesReglasXConcepto().iterator();
						while (it.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}

				try {
					ConceptoFielVersion conVer = con.getConcepto().getVersionVigente();
					Iterator it = conVer.getDetallesReglasXConcepto().iterator();
					while (it.hasNext()) {
						DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
						if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
							actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
						}
					}
					if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
						actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
				} catch (NullPointerException e) {
					// nada
				}

				try {
					Iterator ite2 = con.getConcepto().getVersionesFuturas().iterator();
					while (ite2.hasNext()) {
						ConceptoFielVersion conVer2 = (ConceptoFielVersion) ite2.next();
						Iterator it2 = conVer2.getDetallesReglasXConcepto().iterator();
						while (it2.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it2.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer2.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}
			}
			Iterator iter3 = conceptoFielList3.iterator();
			while (iter3.hasNext()) {
				ConceptoWrappers con = (ConceptoWrappers) iter3.next();
				con.getConcepto().armarConcepto();

				try {
					Iterator ite = con.getConcepto().getVersionesAnteriores().iterator();
					while (ite.hasNext()) {
						ConceptoFielVersion conVer = (ConceptoFielVersion) ite.next();
						Iterator it = conVer.getDetallesReglasXConcepto().iterator();
						while (it.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							viejos.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}

				try {

					ConceptoFielVersion conVer = con.getConcepto().getVersionVigente();
					Iterator it = conVer.getDetallesReglasXConcepto().iterator();
					while (it.hasNext()) {
						DetalleReglaXConcepto det = (DetalleReglaXConcepto) it.next();
						if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
							actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
						}
					}
					if (conVer.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
						actuales.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
				} catch (NullPointerException e) {
					// nada
				}

				try {
					Iterator ite2 = con.getConcepto().getVersionesFuturas().iterator();
					while (ite2.hasNext()) {
						ConceptoFielVersion conVer2 = (ConceptoFielVersion) ite2.next();
						Iterator it2 = conVer2.getDetallesReglasXConcepto().iterator();
						while (it2.hasNext()) {
							DetalleReglaXConcepto det = (DetalleReglaXConcepto) it2.next();
							if (det.getListaPrecio().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue()) {
								futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
							}
						}
						if (conVer2.getListaPrecioDefecto().getIdListaprecios().intValue() == listaPrecio.getIdListaprecios().intValue())
							futuros.add(new WrapperConceptoLista(con.getConcepto().getDescripcion()));
					}
				} catch (NullPointerException e) {
					// nada
				}
			}
			Iterator i = viejos.iterator();
			while (i.hasNext()) {
				WrapperConceptoLista l = (WrapperConceptoLista) i.next();
				conceptosViejos.add(l);
			}
			Iterator i2 = actuales.iterator();
			while (i2.hasNext()) {
				WrapperConceptoLista l = (WrapperConceptoLista) i2.next();
				conceptosActuales.add(l);
			}
			Iterator i3 = futuros.iterator();
			while (i3.hasNext()) {
				WrapperConceptoLista l = (WrapperConceptoLista) i3.next();
				conceptosFuturos.add(l);
			}
			return "";
		}


		public ListaPrecio getListaPrecio() {
			return listaPrecio;
		}


		public void setListaPrecio(ListaPrecio listaPrecio) {
			this.listaPrecio = listaPrecio;
		}

	}

	public class ConceptoWrappers {

		private Concepto concepto;


		public ConceptoWrappers(Concepto concepto) {
			this.concepto = concepto;
		}


		public String editar() {
			conceptoEditado = concepto;
			conceptoEditado.armarConcepto();
			if (!conceptoEditado.getConceptoFielVersion().isEmpty()) {
				if (conceptoEditado.getVersionVigente() != null) {
					editarUnaVersion(conceptoEditado.getVersionVigente());
				} else {
					editarUnaVersion((ConceptoFielVersion) (conceptoEditado.getVersionesFuturas().get(0)));
				}
			} else {
				// el concepto clickeado no posee versiones aun.
				conceptoFielVersionEditado = new ConceptoFielVersion();
				reglaHtml.setValue(new Long(0));
				buscarDetallesXConcepto(null);
			}
			return "";
		}


		public Concepto getConcepto() {
			return concepto;
		}


		public void setConcepto(Concepto concepto) {
			this.concepto = concepto;
		}
	}

	public class WrapperDetalleReglaXConcepto {

		private DetalleReglaXConcepto detalleReglaXConcepto;


		// retornamos un objeto detalleReglaXCOncepto recien creado
		public WrapperDetalleReglaXConcepto(DetalleReglaPF detalleReglaPF) {
			this.detalleReglaXConcepto = new DetalleReglaXConcepto();
			this.detalleReglaXConcepto.setDetalleReglaPF(detalleReglaPF);
		}


		public WrapperDetalleReglaXConcepto(DetalleReglaXConcepto detalleReglaXConcepto) {
			this.detalleReglaXConcepto = detalleReglaXConcepto;
		}


		public DetalleReglaXConcepto getDetalleReglaXConcepto() {
			return detalleReglaXConcepto;
		}


		public String getLabel() {
			if (this.detalleReglaXConcepto.getListaPrecio() == null) {
				return "AGREGAR LISTA PRECIO";
			}
			else
				return this.detalleReglaXConcepto.getListaPrecio().getDescripcion();
		}


		public void setDetalleReglaXConcepto(DetalleReglaXConcepto detalleReglaXConcepto) {
			this.detalleReglaXConcepto = detalleReglaXConcepto;
		}


		public String irAgregarListaPrecio() {
			ListaPrecioFielPopupBean bean = (ListaPrecioFielPopupBean) Session.getBean("ListaPrecioFielPopupBean");
			bean.inicializarDesdePopup(bean.PRODUCTO_FIEL, (detalleReglaXConcepto.getListaPrecio() == null) ? new ListaPrecio()
					: detalleReglaXConcepto.getListaPrecio(), this.detalleReglaXConcepto);
			return null;
		}

	}


	public boolean validar() {
		// debo validar si la fecha, es posterior al dia actual, si no hay otra lista que entra en vigencia el mismo dia, etc.
		if (conceptoFielVersionEditado.getFechaVigencia() == null
				|| conceptoFielVersionEditado.getFechaVigencia().before(Calendar.getInstance().getTime())) {
			error.agregar("La fecha de vigencia es un dato requerido y debe ser posterior al dia actual.");
		}
		if (conceptoFielVersionEditado.getListaPrecioDefecto() == null) {
			error.agregar("Debe especificar una lista de precios por defecto.");
		}
		return error.cantidad() == 0 ? true : false;
	}


	public boolean validarSinFecha() {
		// debo validar si la fecha, es posterior al dia actual, si no hay otra lista que entra en vigencia el mismo dia, etc.
		if (conceptoFielVersionEditado.getListaPrecioDefecto() == null) {
			error.agregar("Debe especificar una lista de precios por defecto.");
		}
		return error.cantidad() == 0 ? true : false;
	}

	public class WrapperConceptoLista implements Comparable {

		String concepto;


		public WrapperConceptoLista(String cad) {
			this.concepto = cad;
		}


		public String getConcepto() {
			return concepto;
		}


		public void setConcepto(String concepto) {
			this.concepto = concepto;
		}


		public int compareTo(Object arg0) {
			return concepto.compareTo(((WrapperConceptoLista) arg0).getConcepto());
		}

	}


	public List getConceptoFielList1() {
		return conceptoFielList1;
	}


	public void setConceptoFielList1(List conceptoFielList1) {
		this.conceptoFielList1 = conceptoFielList1;
	}


	public List getConceptoFielList2() {
		return conceptoFielList2;
	}


	public void setConceptoFielList2(List conceptoFielList2) {
		this.conceptoFielList2 = conceptoFielList2;
	}


	public List getConceptoFielList3() {
		return conceptoFielList3;
	}


	public void setConceptoFielList3(List conceptoFielList3) {
		this.conceptoFielList3 = conceptoFielList3;
	}


	public ConceptoFielVersion getVersionConceptoEditada() {
		return versionConceptoEditada;
	}


	public void setVersionConceptoEditada(ConceptoFielVersion versionConceptoEditada) {
		this.versionConceptoEditada = versionConceptoEditada;
	}


	public Concepto getConceptoEditado() {
		return conceptoEditado;
	}


	public void setConceptoEditado(Concepto conceptoEditado) {
		this.conceptoEditado = conceptoEditado;
	}


	public List getListasFielList1() {
		return listasFielList1;
	}


	public void setListasFielList1(List listasFielList1) {
		this.listasFielList1 = listasFielList1;
	}


	public List getListasFielList2() {
		return listasFielList2;
	}


	public void setListasFielList2(List listasFielList2) {
		this.listasFielList2 = listasFielList2;
	}


	public List getListasFielList3() {
		return listasFielList3;
	}


	public void setListasFielList3(List listasFielList3) {
		this.listasFielList3 = listasFielList3;
	}


	public boolean isConceptoSeleccionado() {
		return conceptoEditado != null;
	}


	public boolean isAltaFiltros() {
		return conceptoEditado.getConceptoFielVersion().size() == 0;
	}


	public List getReglasItems() {
		return reglasItems;
	}


	public void setReglasItems(List reglasItems) {
		this.reglasItems = reglasItems;
	}


	public ReglaPF getReglaPF() {
		return reglaPF;
	}


	public void setReglaPF(ReglaPF reglaPF) {
		this.reglaPF = reglaPF;
	}


	public ConceptoFielVersion getConceptoFielVersionEditado() {
		return conceptoFielVersionEditado;
	}


	public void setConceptoFielVersionEditado(
			ConceptoFielVersion conceptoFielVersionEditado) {
		this.conceptoFielVersionEditado = conceptoFielVersionEditado;
	}


	public List getDetallesXConcepto() {
		return detallesXConcepto;
	}


	public void setDetallesXConcepto(List detallesXConcepto) {
		this.detallesXConcepto = detallesXConcepto;
	}


	public HtmlSelectOneMenu getReglaHtml() {
		return reglaHtml;
	}


	public void setReglaHtml(HtmlSelectOneMenu reglaHtml) {
		this.reglaHtml = reglaHtml;
	}


	public Long getIdReglaSeleccionada() {
		return idReglaSeleccionada;
	}


	public void setIdReglaSeleccionada(Long idReglaSeleccionada) {
		this.idReglaSeleccionada = idReglaSeleccionada;
	}


	public Date getFechaVigencia() {
		return fechaVigencia;
	}


	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}


	public boolean isVerBtnGuardar() {
		return verBtnGuardar;
	}


	public void setVerBtnGuardar(boolean verBtnGuardar) {
		this.verBtnGuardar = verBtnGuardar;
	}


	public Date getFechaComienzoNuevaVersion() {
		return fechaComienzoNuevaVersion;
	}


	public void setFechaComienzoNuevaVersion(Date fechaComienzoNuevaVersion) {
		this.fechaComienzoNuevaVersion = fechaComienzoNuevaVersion;
	}


	public boolean isVerAltaNuevaVersion() {
		return verAltaNuevaVersion;
	}


	public void setVerAltaNuevaVersion(boolean verAltaNuevaVersion) {
		this.verAltaNuevaVersion = verAltaNuevaVersion;
	}


	public String getListaPrecioDefecto() {
		if (conceptoFielVersionEditado.getListaPrecioDefecto() == null)
			return "AGREGAR LISTA PRECIO";
		return conceptoFielVersionEditado.getListaPrecioDefecto().getDescripcion();
	}


	public void setListaPrecioDefecto(String listaPrecioDefecto) {
		this.listaPrecioDefecto = listaPrecioDefecto;
	}


	public List getConceptosActuales() {
		return conceptosActuales;
	}


	public void setConceptosActuales(List conceptosActuales) {
		this.conceptosActuales = conceptosActuales;
	}


	public List getConceptosFuturos() {
		return conceptosFuturos;
	}


	public void setConceptosFuturos(List conceptosFuturos) {
		this.conceptosFuturos = conceptosFuturos;
	}


	public List getConceptosViejos() {
		return conceptosViejos;
	}


	public void setConceptosViejos(List conceptosViejos) {
		this.conceptosViejos = conceptosViejos;
	}


	public String getListaInvestigada() {
		return listaInvestigada;
	}


	public void setListaInvestigada(String listaInvestigada) {
		this.listaInvestigada = listaInvestigada;
	}


	public boolean isHayInvestigada() {
		return hayInvestigada;
	}


	public void setHayInvestigada(boolean hayInvestigada) {
		this.hayInvestigada = hayInvestigada;
	}


	// un metodo que devuelve la visibilidad de la botonera de guardado de conceptos fiel
	public boolean getHayReglas() {
		return (reglaPF != null);
	}
}

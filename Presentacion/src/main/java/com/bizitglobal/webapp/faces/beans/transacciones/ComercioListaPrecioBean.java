package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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
import com.bizitglobal.tarjetafiel.transacciones.exception.ConstructorException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.AlmanaqueDePagos;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioVersion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.transacciones.CodComercioBean.WrapperListaPrecio;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ComercioListaPrecioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ComercioListaPrecioBean.class);

	private static int indiceItem = 0; // un entero identificador para cada renglon de la tabla de wrappers
	private ComercioListaPrecio comercioListaPrecio;
	private Long idComercioListaPrecioHidden;

	// definicion de un list del objeto base
	private List comercioListaPrecioList;

	// Listas para la presentacion(HtmlSelectItems).
	private List listaPrecioList;
	private List listaPrecioItems;
	private List listaCodigosPosnetItems;

	// Objetos Relacionados.
	private Long idListaPrecioSeleccionada;
	private Long idCodComercioSeleccionada;
	private Long idCodigoPosnet;

	private Date fechaCarga;

	private String focoHidden;

	private boolean listaPrecioSeleccionada;
	private HtmlSelectOneMenu listaPrecioSeleccionadaMenu;
	private HtmlSelectOneMenu listaCodigoPosnetMenu;
	// tres atributos para visualizar la lista Precio elegida.
	private ListaPrecio listaPrecio;
	private List items;
	private Date fechaVigencia;
	private String estadoCalendario;

	private static int contador;

	// Los atributos para mostrar la lista de precio elegida.
	private ListaPrecioVersion versionEditada;
	private boolean calendarioVisible;
	private List diasDePago;
	private boolean agregar = false;


	public ComercioListaPrecioBean() {
		super();
		comercioListaPrecioList = new ArrayList();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Long getIdComercioListaPrecioHidden() {
		return idComercioListaPrecioHidden;
	}


	public void setIdComercioListaPrecioHidden(Long idComercioListaPrecioHidden) {
		this.idComercioListaPrecioHidden = idComercioListaPrecioHidden;
	}


	public List getListaPrecioItems() {
		return listaPrecioItems;
	}


	public void setListaPrecioItems(List listaPrecioItems) {
		this.listaPrecioItems = listaPrecioItems;
	}


	public Long getIdListaPrecioSeleccionada() {
		return idListaPrecioSeleccionada;
	}


	public void setIdListaPrecioSeleccionada(Long idListaPrecioSeleccionada) {
		this.idListaPrecioSeleccionada = idListaPrecioSeleccionada;
	}


	public Long getIdCodComercioSeleccionada() {
		return idCodComercioSeleccionada;
	}


	public void setIdCodComercioSeleccionada(Long idCodComercioSeleccionada) {
		this.idCodComercioSeleccionada = idCodComercioSeleccionada;
	}


	public List getComercioListaPrecioList() {
		return comercioListaPrecioList;
	}


	public void setComercioListaPrecioList(List comercioListaPrecioList) {
		this.comercioListaPrecioList = comercioListaPrecioList;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Date getFechaCarga() {
		return fechaCarga;
	}


	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}


	public Date getFechaVigencia() {
		// return (Date)listaPrecio.getFechaVigencia();
		return null;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE COMERCIOLISTAPRECIO
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
			listaPrecioList = transaccionesService.getListaPrecioService().getListaPrecio(new Filtro());
		} catch (ListaPrecioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "amComercioListaPrecio";
	}


	public void armarListaDePosnetDisponibles(List listaDeComercios) {
		listaCodigosPosnetItems = new ArrayList();
		List listaNumeros = new ArrayList();
		for (int i = 0; i < 10; i++) {
			listaNumeros.add(new Long(i));
		}
		List listaOcupados = new ArrayList();
		if (!listaDeComercios.isEmpty()) {
			Iterator ite = listaDeComercios.iterator();
			while (ite.hasNext()) {
				listaOcupados.add(((WrapperListaPrecio) ite.next()).getComercioListaPrecio().getCodigoPosnet());
			}
		}
		listaNumeros.removeAll(listaOcupados);
		listaCodigosPosnetItems.add(new SelectItem(new Long(-1), "Seleccione un número"));
		Iterator iter = listaNumeros.iterator();
		while (iter.hasNext()) {
			Long num = (Long) iter.next();
			listaCodigosPosnetItems.add(new SelectItem(num, "" + num));
		}

	}


	public String inicializarDesdePopup(ComercioListaPrecio comercioListaPrecio, List listaDeComercios, boolean agregar) {
		this.agregar = agregar;
		this.comercioListaPrecio = comercioListaPrecio;
		armarListaDePosnetDisponibles(listaDeComercios);

		try {
			CodComercioBean codComer = (CodComercioBean) Session.getBean("CodComercioBean");
			String agregados = "";
			String cad = "";
			List lis = codComer.getListaDePrecios();
			Iterator iter = lis.iterator();
			while (iter.hasNext()) {
				WrapperListaPrecio wrap = (WrapperListaPrecio) iter.next();
				agregados += cad + wrap.getComercioListaPrecio().getListaPrecio().getIdListaprecios();
				cad = ", ";
			}
			Filtro filtro = new Filtro();

			if (cad.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idListaprecios", Filtro.NOTIN, agregados);
			}
			filtro.agregarCampoOperValor("disponibleSeleccion", Filtro.LIKE, "S");
			listaPrecioList = transaccionesService.getListaPrecioService().getListaPrecio(filtro);
			Iterator iterLP = listaPrecioList.iterator();
			while (iterLP.hasNext()) {
				ListaPrecio lista = (ListaPrecio) iterLP.next();
				lista.getVersionesListaPrecio();
			}
			listaPrecioItems = new ArrayList();
			listaPrecioItems.clear();
			listaPrecioItems.add(new SelectItem(new Long(0), "Seleccionar Lista Precio"));
			listaPrecioItems.addAll(ComercioListaPrecioUtil.armarListaPrecio(listaPrecioList));
			listaPrecioSeleccionadaMenu = new HtmlSelectOneMenu();
			listaPrecioSeleccionadaMenu.setValue(new Long(0));
			listaPrecio = new ListaPrecio();
			items = new ArrayList();
		} catch (ListaPrecioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (comercioListaPrecio.getId() != null) {
			fechaCarga = (Date) comercioListaPrecio.getFechaCarga();
			idListaPrecioSeleccionada = comercioListaPrecio.getListaPrecio().getIdListaprecios();
			listaPrecioSeleccionadaMenu.setValue(idListaPrecioSeleccionada);
		} else {
			fechaCarga = new Date(Calendar.getInstance().getTimeInMillis());
			idListaPrecioSeleccionada = new Long(0);
		}

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/comercioListaPrecioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',800,600), 'titlebar=no';");
		return null;
	}


	public String grabarDesdePopup() {
		if (validar()) {
			comercioListaPrecio.setListaPrecio(listaPrecio);
			comercioListaPrecio.setFechaCarga(new Timestamp(fechaCarga.getTime()));
			comercioListaPrecio.setCodigoPosnet(idCodigoPosnet);
			CodComercioBean cod = (CodComercioBean) Session.getBean("CodComercioBean");
			if (agregar)
				cod.agregarUnaListaPrecio(comercioListaPrecio);
			else {
				try {
					cod.reemplazarLista(comercioListaPrecio);
				} catch (Exception e) {
					error.agregar(e.getMessage());
				}
			}
		}
		return null;
	}


	public String cancelarDesdePopup() {

		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	// public String editarComercioListaPrecio() {
	// String result = null;
	// borrarBaseBean();
	// borrar();
	// alta = false;
	// tituloCorto = "Modificar Comercio Lista Precio";
	// try {
	// if(!comercioListaPrecioList.isEmpty()){
	// Iterator iterator = comercioListaPrecioList.iterator();
	// while (iterator.hasNext()) {
	// ComercioListaPrecioWrappers element = (ComercioListaPrecioWrappers) iterator.next();
	//
	// if(element.getIndice() == idComercioListaPrecioHidden.intValue()){
	// comercioListaPrecio =
	// transaccionesService.getComercioListaPrecioService().leerComercioListaPrecio(element.getComercioListaPrecio().getIdComercioListaprecio());
	// if(comercioListaPrecio.getFechaDesde() != null)
	// fechaDesde = new Date(comercioListaPrecio.getFechaDesde().getTime());
	//
	// if(comercioListaPrecio.getFechaHasta() != null)
	// fechaHasta = new Date(comercioListaPrecio.getFechaHasta().getTime());
	//
	// if(comercioListaPrecio.getCodComercio() != null && comercioListaPrecio.getCodComercio().getIdCodComercio() != null
	// && !comercioListaPrecio.getCodComercio().getIdCodComercio().equals(new Long(0)))
	// idCodComercioSeleccionada = comercioListaPrecio.getCodComercio().getIdCodComercio();
	//
	// if(comercioListaPrecio.getListaPrecio() != null && comercioListaPrecio.getListaPrecio().getIdListaprecios() != null
	// && !comercioListaPrecio.getListaPrecio().getIdListaprecios().equals(new Long(0)))
	// idListaPrecioSeleccionada = comercioListaPrecio.getListaPrecio().getIdListaprecios();
	//
	// result = "amComercioListaPrecio";
	// }
	// }
	// }
	//
	// } catch (ComercioListaPrecioException e1) {
	// error.agregar("Ocurrio un error al intentar editar el Comercio Lista Precio");
	// e1.printStackTrace();
	// Session.redirect("/tarjetafiel/Transacciones/listarComercioListaPrecio.jsf");
	// } catch (Exception e) {
	// error.agregar("Ocurrio un error al intentar editar el Comercio Lista Precio");
	// e.printStackTrace();
	// Session.redirect("/tarjetafiel/Transacciones/listarComercioListaPrecio.jsf");
	// }
	// return result;
	// }

	// public String eliminarComercioListaPrecio() {
	// try {
	// if(!comercioListaPrecioList.isEmpty()){
	// Iterator iterator = comercioListaPrecioList.iterator();
	// while (iterator.hasNext()) {
	// ComercioListaPrecioWrappers element = (ComercioListaPrecioWrappers) iterator.next();
	//
	// if(element.getIndice() == idComercioListaPrecioHidden.intValue()){
	// transaccionesService.getComercioListaPrecioService().borrarComercioListaPrecio(element.getComercioListaPrecio());
	// comercioListaPrecioList.remove(element.getComercioListaPrecio());
	// }
	// }
	// }
	// } catch (ComercioListaPrecioException e1) {
	// error.agregar("Imposible borrar el Comercio Lista Precio. Posee elemintos asociados");
	// e1.printStackTrace();
	// } catch (Exception e) {
	// error.agregar("Ocurrio un error al intentar borrar el Comercio Lista Precio");
	// e.printStackTrace();
	// }
	// listarComercioListaPrecio();
	// return null;
	// }

	// public String grabar(){
	// try {
	// if(validar()) {
	// // Inicio los servis que voy a usar
	// ComercioListaPrecioService comerciolistaPrecioService = transaccionesService.getComercioListaPrecioService();
	// comercioListaPrecio.setListaPrecio((ListaPrecio)Util.buscarElemento(listaPrecioList, new ListaPrecio(idListaPrecioSeleccionada)));
	// // comercioListaPrecio.setCodComercio((CodComercio)Util.buscarElemento(comercioList, new CodComercio(idCodComercioSeleccionada)));
	//
	// if(fechaDesde != null && !fechaDesde.equals(new Date()))
	// comercioListaPrecio.setFechaDesde(new Timestamp(fechaDesde.getTime()));
	//
	// if(fechaHasta != null && !fechaHasta.equals(new Date()))
	// comercioListaPrecio.setFechaHasta(new Timestamp(fechaHasta.getTime()));
	//
	// if(alta) {
	// //Grabo el nuevo objeto
	// comerciolistaPrecioService.grabarComercioListaPrecio(comercioListaPrecio);
	// } else {
	// comerciolistaPrecioService.actualizarComercioListaPrecio(comercioListaPrecio);
	// }
	// popup.setPopup(popup.ICONO_OK, "El Comercio Lista Precio ha sido almacenado exitosamente.");
	// }else {
	// ScrollBean scrollBean = (ScrollBean)Session.getBean("ScrollBean");
	// scrollBean.borrar();
	// }
	// } catch (ComercioListaPrecioDuplicateException e1) {
	// popup.setPopup(popup.ICONO_FALLA, "Falla el alta de Comercio Lista Precio.");
	// e1.printStackTrace();
	// } catch (ComercioListaPrecioException e2) {
	// popup.setPopup(popup.ICONO_FALLA, "Falla el alta de Comercio Lista Precio.");
	// e2.printStackTrace();
	// } catch (Exception e3) {
	// popup.setPopup(popup.ICONO_FALLA, "Falla el alta de Comercio Lista Precio.");
	// e3.printStackTrace();
	// }
	// return "";
	// }

	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Comercio Lista Precio";
		popup.borrar();
		listaPrecioList = new ArrayList();
		idCodComercioSeleccionada = new Long(0);
		idListaPrecioSeleccionada = new Long(0);
		comercioListaPrecio = new ComercioListaPrecio();
		listaPrecioItems = new ArrayList();
		listaPrecioItems.clear();
		listaPrecioItems.add(new SelectItem(new Long(0), "Seleccionar Lista Precio"));
		listaPrecioItems.addAll(ComercioListaPrecioUtil.armarListaPrecio(listaPrecioList));
		listaPrecioSeleccionadaMenu = new HtmlSelectOneMenu();
		listaPrecioSeleccionadaMenu.setValue(new Long(0));

		listaPrecio = new ListaPrecio();
		items = new ArrayList();
	}


	public void mostrarListaPrecio(ValueChangeEvent e) {
		alta = false;
		tituloCorto = "Modificar Lista Precio";
		try {
			log.info("Tamaño listaPrecioList " + listaPrecioList.size());
			if (!listaPrecioList.isEmpty()) {
				Iterator iterator = listaPrecioList.iterator();
				while (iterator.hasNext()) {
					ListaPrecio element = (ListaPrecio) iterator.next();
					if (element.getIdListaprecios().compareTo((Long) listaPrecioSeleccionadaMenu.getValue()) == 0) {
						listaPrecio = element;
						break;
					}
				}
				listaPrecio.armarListaPrecio();
				if (listaPrecio.getVersionVigente() != null) {
					editarUnaVersion(listaPrecio.getVersionVigente());
				} else {
					editarUnaVersion((ListaPrecioVersion) (listaPrecio.getVersionesFuturas().get(0)));
				}
			}
		} catch (Exception e1) {
			error.agregar("Ocurrio un error al intentar editar el Lista Precio");
			e1.printStackTrace();
		}
	}


	public String editarVersionFutura() {
		log.info("Ejecutando ==> editarVersion()");
		Long idVersion = new Long(Session.getRequestParameter("idVerFutura"));
		ListaPrecioVersion aEditar = null;
		Iterator iter = listaPrecio.getVersionesFuturas().iterator();
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
		Iterator iter = listaPrecio.getVersionesAnteriores().iterator();
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
		editarUnaVersion(listaPrecio.getVersionVigente());
		return null;
	}


	public void editarUnaVersion(ListaPrecioVersion lp) {
		ocultarCalendario();
		lp.setListaPrecio(listaPrecio);
		versionEditada = lp;
		items.clear();
		if (lp.getItemsListaPrecio() == null)
			lp.setItemsListaPrecio(new HashSet());
		List aux = Convertidores.setToList(lp.getItemsListaPrecio());
		Iterator iter = aux.iterator();
		while (iter.hasNext()) {
			ListaPrecioItem lisItem = (ListaPrecioItem) iter.next();
			items.add(new ListaPrecioItemWrappers(lisItem));
			indiceItem++;
		}
	}


	public String ocultarCalendario() {
		diasDePago = new ArrayList();
		estadoCalendario = "";
		calendarioVisible = false;
		return "";
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


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		idListaPrecioSeleccionada = (Long) listaPrecioSeleccionadaMenu.getValue();
		if (idListaPrecioSeleccionada.equals(new Long(0))) {
			error.agregar(Error.TRAN_LISTPRE_LISTAPRECIO_REQUERIDA);
		}
		if (agregar) {
			if (idCodigoPosnet == null || idCodigoPosnet.equals(new Long(-1))) {
				error.agregar(Error.TRAN_LISTPRE_CODIGOPOSNET_REQUERIDO);
			}
		}
		if (fechaCarga == null) {
			error.agregar("Debe especificar una fecha de carga.");
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoComercioListaPrecio() {
		borrar();
		return inicializar();
	}


	public String irAModificarComercioListaPrecio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Comercio Lista Precio";
		return null;
	}


	public String irAListarComercioListaPrecio() {
		borrar();
		comercioListaPrecioList = new ArrayList();
		tituloCorto = "Listado de Comercio Lista Precio";
		Session.redirect("/tarjetafiel/transacciones/listarComercioListaPrecio.jsf");
		return "";
	}


	public boolean isListaPrecioSeleccionada() {
		return listaPrecioSeleccionada;
	}


	public void setListaPrecioSeleccionada(boolean listaPrecioSeleccionada) {
		this.listaPrecioSeleccionada = listaPrecioSeleccionada;
	}


	public HtmlSelectOneMenu getListaPrecioSeleccionadaMenu() {
		return listaPrecioSeleccionadaMenu;
	}


	public void setListaPrecioSeleccionadaMenu(
			HtmlSelectOneMenu listaPrecioSeleccionadaMenu) {
		this.listaPrecioSeleccionadaMenu = listaPrecioSeleccionadaMenu;
	}


	public List getItems() {
		return items;
	}


	public void setItems(List items) {
		this.items = items;
	}


	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}


	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public class ListaPrecioItemWrappers {

		private int indice;
		private ListaPrecioItem listaPrecioItem;


		public ListaPrecioItemWrappers(ListaPrecioItem listaPrecioItem) {
			super();
			this.listaPrecioItem = listaPrecioItem;
			this.indice = indiceItem++;
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


	public List getDiasDePago() {
		return diasDePago;
	}


	public void setDiasDePago(List diasDePago) {
		this.diasDePago = diasDePago;
	}


	public ListaPrecio getListaprecio() {
		return listaPrecio;
	}


	public void setListaprecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}


	public ListaPrecioVersion getVersionEditada() {
		return versionEditada;
	}


	public void setVersionEditada(ListaPrecioVersion versionEditada) {
		this.versionEditada = versionEditada;
	}


	public boolean isCalendarioVisible() {
		return calendarioVisible;
	}


	public void setCalendarioVisible(boolean calendarioVisible) {
		this.calendarioVisible = calendarioVisible;
	}


	public String getEstadoCalendario() {
		return estadoCalendario;
	}


	public void setEstadoCalendario(String estadoCalendario) {
		this.estadoCalendario = estadoCalendario;
	}


	public List getListaCodigosPosnetItems() {
		return listaCodigosPosnetItems;
	}


	public void setListaCodigosPosnetItems(List listaCodigosPosnetItems) {
		this.listaCodigosPosnetItems = listaCodigosPosnetItems;
	}


	public HtmlSelectOneMenu getListaCodigoPosnetMenu() {
		return listaCodigoPosnetMenu;
	}


	public void setListaCodigoPosnetMenu(HtmlSelectOneMenu listaCodigoPosnetMenu) {
		this.listaCodigoPosnetMenu = listaCodigoPosnetMenu;
	}


	public Long getIdCodigoPosnet() {
		return idCodigoPosnet;
	}


	public void setIdCodigoPosnet(Long idCodigoPosnet) {
		this.idCodigoPosnet = idCodigoPosnet;
	}


	public boolean isAgregar() {
		return agregar;
	}


	public void setAgregar(boolean agregar) {
		this.agregar = agregar;
	}

}

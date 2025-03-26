package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
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
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoFielVersion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DetalleReglaXConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioVersion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ListaPrecioFielPopupBean extends BaseBean {

	private static final Logger log = Logger.getLogger(ListaPrecioFielPopupBean.class);

	private int origen;
	public static final int PRODUCTO_FIEL = 1;
	private Object objeto;

	private boolean hayListaSeleccionada;
	private boolean hayVersionVigente;
	private static int indiceItem = 0; // un entero identificador para cada renglon de la tabla de wrappers
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


	public ListaPrecioFielPopupBean() {
		super();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
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
		return (Date) versionEditada.getFechaVigencia();
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE ListaPrecioFielPopupBean
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "";
	}


	public String inicializarDesdePopup(int origen, ListaPrecio listaPrecio, Object o) {
		this.origen = origen;
		this.listaPrecio = listaPrecio;
		this.objeto = o;
		try {
			Filtro filtro = new Filtro();
			// si el origen es un producto fiel, entonces solo debo listar las listas de precio fiel.
			if (origen == PRODUCTO_FIEL)
				filtro.agregarCampoOperValor("esFiel", Filtro.LIKE, "S");
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
			// listaPrecio = new ListaPrecio();
			items = new ArrayList();
		} catch (ListaPrecioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listaPrecio.getIdListaprecios() != null) {
			listaPrecioSeleccionadaMenu.setValue(listaPrecio.getIdListaprecios());
			mostrarListaPrecio();
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/listaPrecioFielPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',800,600), 'titlebar=no';");
		return null;
	}


	public String grabarDesdePopup() {
		if (validar()) {
			switch (origen) {
			case PRODUCTO_FIEL:
				try {
					((DetalleReglaXConcepto) objeto).setListaPrecio(listaPrecio);
				} catch (ClassCastException c) {
					try {
						((ConceptoFielVersion) objeto).setListaPrecioDefecto(listaPrecio);
					} catch (ClassCastException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			// CodComercioBean cod = (CodComercioBean)Session.getBean("CodComercioBean");
			// cod.agregarUnaListaPrecio(comercioListaPrecio);
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


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "";
		popup.borrar();
		idCodComercioSeleccionada = new Long(0);
		idListaPrecioSeleccionada = new Long(0);
		listaPrecioItems = new ArrayList();
		listaPrecioItems.clear();
		listaPrecioItems.add(new SelectItem(new Long(0), "Seleccionar Lista Precio"));
		listaPrecioItems.addAll(ComercioListaPrecioUtil.armarListaPrecio(listaPrecioList));
		listaPrecioSeleccionadaMenu = new HtmlSelectOneMenu();
		listaPrecioSeleccionadaMenu.setValue(new Long(0));
		listaPrecio = new ListaPrecio();
		items = new ArrayList();
	}


	public void mostrarListaPrecio() {
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


	public void mostrarListaPrecio(ValueChangeEvent e) {
		alta = false;
		tituloCorto = "Modificar Lista Precio";
		listaPrecio = new ListaPrecio();
		versionEditada = null;
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
		fechaVigencia = versionEditada.getFechaVigencia();
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
		return (error.cantidad() == 0) ? true : false;
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


	public boolean isHayListaSeleccionada() {
		if (versionEditada != null)
			return true;
		return false;
	}


	public boolean isHayVersionVigente() {
		if (listaPrecio.getVersionVigente() != null)
			return true;
		return false;
	}


	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

}

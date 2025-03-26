package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleRegla;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleTarget;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.ConceptosPopupBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ConceptoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptoBean.class);
	private static int contador = 0;

	private boolean perteneceAFiel;
	private Concepto concepto;
	private String idConceptoFiltro;
	private String descripcionFiltro;
	private Long idConceptoHidden;
	private HtmlSelectOneMenu menuTar;
	private Long tarSeleccionado;
	private List listaTar;
	// todo sobre la lista de detalles
	private List listDetalles;

	// definicion de un list del objeto base
	private List conceptoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List sucursalList = new ArrayList();
	private List sucursalItems = new ArrayList();

	// Objetos Relacionados.
	private Long idSucursalSeleccionada;
	private HtmlSelectOneMenu sucSeleccionada;
	private boolean calculaDisponibleAux;

	private String focoHidden;
	private boolean esFiel;

	private boolean mostrarOpciones; // si es true, debera permitir decidir si dar de alta una lista precio comercio o una lista precio fiel.
	private String tipoLista;


	public ConceptoBean() {
		super();
		borrar();
		menuTar = new HtmlSelectOneMenu();
		menuTar.setValue(new Long(0));
		tarSeleccionado = new Long(0);
		listaTar = new ArrayList();
		listaTar.add(new SelectItem(new Long(0), "CL"));
		listaTar.add(new SelectItem(new Long(1), "CO"));
		listaTar.add(new SelectItem(new Long(2), "AM"));
		try {
			try {
				sucursalList = generalService.getSucursalFielService().getSucursales(new Filtro());
			} catch (SucursalFielException e1) {
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


	public Concepto getConcepto() {
		return concepto;
	}


	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}


	public Long getIdConceptoHidden() {
		return idConceptoHidden;
	}


	public void setIdConceptoHidden(Long idConceptoHidden) {
		this.idConceptoHidden = idConceptoHidden;
	}


	public List getSucursalItems() {
		return sucursalItems;
	}


	public void setSucursalItems(List sucursalItems) {
		this.sucursalItems = sucursalItems;
	}


	public Long getIdSucursalSeleccionada() {
		return idSucursalSeleccionada;
	}


	public void setIdSucursalSeleccionada(Long idSucursalSeleccionada) {
		this.idSucursalSeleccionada = idSucursalSeleccionada;
	}


	public List getConceptoList() {
		return conceptoList;
	}


	public void setConceptoList(List object) {
		this.conceptoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CONCEPTO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amConcepto";
	}


	public String irAClientes() {
		Concepto concep = null;
		Iterator iter = conceptoList.iterator();
		while (iter.hasNext()) {
			WrapperConcepto wrapConcep = (WrapperConcepto) iter.next();
			if (wrapConcep.getCcc().getIdConcepto().equals(idConceptoHidden)) {
				concep = wrapConcep.getCcc();
				break;
			}
		}
		ConceptosPopupBean conPopupBean = (ConceptosPopupBean) Session.getBean("ConceptosPopupBean");
		conPopupBean.inicializar(conPopupBean.CLIENTE, concep);
		return null;
	}


	public String irAComercios() {
		Concepto concep = null;
		Iterator iter = conceptoList.iterator();
		while (iter.hasNext()) {
			WrapperConcepto wrapConcep = (WrapperConcepto) iter.next();
			if (wrapConcep.getCcc().getIdConcepto().equals(idConceptoHidden)) {
				concep = wrapConcep.getCcc();
				break;
			}
		}
		ConceptosPopupBean conPopupBean = (ConceptosPopupBean) Session.getBean("ConceptosPopupBean");
		conPopupBean.inicializar(conPopupBean.COMERCIO, concep);
		return null;
	}


	public String irAOrigenes() {
		Concepto concep = null;
		Iterator iter = conceptoList.iterator();
		while (iter.hasNext()) {
			WrapperConcepto wrapConcep = (WrapperConcepto) iter.next();
			if (wrapConcep.getCcc().getIdConcepto().equals(idConceptoHidden)) {
				concep = wrapConcep.getCcc();
				break;
			}
		}
		ConceptosPopupBean conPopupBean = (ConceptosPopupBean) Session.getBean("ConceptosPopupBean");
		conPopupBean.inicializar(conPopupBean.ORIGEN, concep);
		return null;
	}


	private void cargarItems() {
		if (sucursalItems.size() != sucursalList.size()) {
			sucursalItems.clear();
			Iterator iter = sucursalList.iterator();
			while (iter.hasNext()) {
				SucursalFiel suc = (SucursalFiel) iter.next();
				sucursalItems.add(new SelectItem(suc.getIdSucursal(), suc.getNombre()));
			}

		}
	}


	public WrapperDetalle obtenerWrapperDetalle(ConceptoDetalle cp) {
		return new WrapperDetalle(cp);
	}


	public String agregarDetalle() {
		log.info("Ejecutando ==> agregarDetalle()");
		ConceptoDetalleBean bean = (ConceptoDetalleBean) Session.getBean("ConceptoDetalleBean");
		bean.inicializarDesdePopup(new ConceptoDetalle(), concepto, alta);
		return null;
	}


	public String editarDetalle() {
		ConceptoDetalleBean bean = (ConceptoDetalleBean) Session.getBean("ConceptoDetalleBean");
		int idEdi = Integer.valueOf(Session.getRequestParameter("idDetalleEdi").toString()).intValue();
		WrapperDetalle wrap = null;
		Iterator iter = listDetalles.iterator();
		while (iter.hasNext()) {
			wrap = (WrapperDetalle) iter.next();
			if (wrap.getIndice() == idEdi) {
				break;
			}
		}
		bean.inicializarDesdePopup(wrap.getDetalle(), concepto, alta);
		return null;
	}


	public String verTarget() {
		// implementar Target
		return null;
	}


	public String verReglas() {
		// implementar Reglas
		return null;
	}


	public String eliminarDetalle() {
		error.borrar();
		int idElim = Integer.valueOf(Session.getRequestParameter("idDetalleElim").toString()).intValue();
		WrapperDetalle wrap = null;
		WrapperDetalle wr = null;
		Collection col = new ArrayList();
		Iterator i = listDetalles.iterator();
		while (i.hasNext()) {
			wr = (WrapperDetalle) i.next();
			if (wr.getDetalle().getParent() != null) {
				col.add(wr.getDetalle().getParent());
			}
		}
		Iterator iter = listDetalles.iterator();
		while (iter.hasNext()) {
			wrap = (WrapperDetalle) iter.next();
			if (wrap.getIndice() == idElim && !col.contains(wrap.getDetalle().getIdConceptoDetalle())) {
				if (wrap.getDetalle().getIdConceptoDetalle() != null) {
					try {
						transaccionesService.getConceptoDetalleService().borrarConceptoDetalle(wrap.getDetalle());
						concepto.getConceptoDetalleSet().remove(wrap.getDetalle());
					} catch (ConceptoDetalleException e) {
						log.info("problemas al eliminar el detalle");
						e.printStackTrace();
					}
				}
				break;
			}
		}
		if (!col.contains(wrap.getDetalle().getIdConceptoDetalle())) {
			listDetalles.remove(wrap);
		} else {
			error.agregar(Error.TRAN_NO_SE_BORRA_EL_DETALLE);
		}
		return null;
	}


	public String editarConcepto() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar concepto";

		try {
			concepto = transaccionesService.getConceptoService().leerConcepto(idConceptoHidden);
			listDetalles.clear();
			if (concepto.getCalculaDisponible().compareTo("V") == 0) {
				calculaDisponibleAux = true;
			} else {
				calculaDisponibleAux = false;
			}
			if (concepto.getTarget().compareTo("CL") == 0) {
				menuTar.setValue(new Long(0));
				tarSeleccionado = new Long(0);
			} else {
				if (concepto.getTarget().compareTo("CO") == 0) {
					menuTar.setValue(new Long(1));
					tarSeleccionado = new Long(1);
				} else {
					menuTar.setValue(new Long(2));
					tarSeleccionado = new Long(2);
				}
			}
			List auxiDetalles = Convertidores.setToList(concepto.getConceptoDetalleSet());
			log.info("tienen detalles " + auxiDetalles.size());
			Iterator iter = auxiDetalles.iterator();
			while (iter.hasNext()) {
				ConceptoDetalle cop = (ConceptoDetalle) iter.next();
				listDetalles.add(new WrapperDetalle(cop));
				Iterator it = cop.getConceptoDetalleReglaSet().iterator();
				while (it.hasNext()) {
					ConceptoDetalleRegla co = (ConceptoDetalleRegla) it.next();
					co.getConceptoDetalle();
				}
				cop.getConceptoDetalleTargetSet();
				Iterator itDos = cop.getConceptoDetalleTargetSet().iterator();
				while (itDos.hasNext()) {
					ConceptoDetalleTarget cod = (ConceptoDetalleTarget) itDos.next();
					cod.getConceptoDetalle();
				}
				cop.getConcepto();
				cop.getIdConceptoDetalle();
			}
			sucSeleccionada.setValue(concepto.getSucursal().getIdSucursal());
			mostrarOpciones = false;
			result = "amConcepto";
		} catch (ConceptoException e1) {
			error.agregar("Ocurrio un error al intentar editar el concepto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarConcepto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el concepto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarConcepto.jsf");
		}
		return result;
	}


	public String eliminarConcepto() {
		try {
			transaccionesService.getConceptoService().borrarConcepto(idConceptoHidden);
			conceptoList.remove(new WrapperConcepto(idConceptoHidden));
		} catch (ConceptoException e1) {
			error.agregar("Imposible borrar el concepto. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar borrar el concepto");
			e.printStackTrace();
		}
		return null;
	}


	public boolean actualizarDetalles() {
		log.info("Actualizare los cambios");
		try {
			if (validar()) {
				ConceptoService conceptoService = transaccionesService.getConceptoService();
				idSucursalSeleccionada = (Long) (sucSeleccionada.getValue());
				concepto.setSucursal((SucursalFiel) Util.buscarElemento(sucursalList, new SucursalFiel(idSucursalSeleccionada)));
				if (concepto.getConceptoDetalleSet() == null) {
					concepto.setConceptoDetalleSet(new HashSet());
				}
				Iterator iter = listDetalles.iterator();
				while (iter.hasNext()) {
					WrapperDetalle wap = (WrapperDetalle) iter.next();
					if (wap.getDetalle().getIdConceptoDetalle() == null) {
						wap.getDetalle().setConcepto(concepto);
						concepto.getConceptoDetalleSet().add(wap.getDetalle());
					}
				}
				if (calculaDisponibleAux) {
					concepto.setCalculaDisponible("V");
				} else {
					concepto.setCalculaDisponible("F");
				}
				tarSeleccionado = (Long) menuTar.getValue();
				switch (tarSeleccionado.intValue()) {
				case 0:
					concepto.setTarget("CL");
					break;
				case 1:
					concepto.setTarget("CO");
					break;
				case 2:
					concepto.setTarget("AM");
					break;
				}
				if (concepto.getIdConcepto() == null) {
					conceptoService.grabarConcepto(concepto);
					alta = false;
				} else {
					conceptoService.actualizarConcepto(concepto);
				}
				concepto = transaccionesService.getConceptoService().leerConcepto(concepto.getId());
				if (concepto.getCalculaDisponible().compareTo("V") == 0) {
					calculaDisponibleAux = true;
				} else {
					calculaDisponibleAux = false;
				}
				listDetalles.clear();
				List auxiDetalles = Convertidores.setToList(concepto.getConceptoDetalleSet());
				Iterator iterAdor = auxiDetalles.iterator();
				while (iterAdor.hasNext()) {
					ConceptoDetalle cop = (ConceptoDetalle) iterAdor.next();
					listDetalles.add(new WrapperDetalle(cop));
					Iterator it = cop.getConceptoDetalleReglaSet().iterator();
					while (it.hasNext()) {
						ConceptoDetalleRegla co = (ConceptoDetalleRegla) it.next();
						co.getConceptoDetalle();
					}
					cop.getConceptoDetalleTargetSet();
					Iterator itDos = cop.getConceptoDetalleTargetSet().iterator();
					while (itDos.hasNext()) {
						ConceptoDetalleTarget cod = (ConceptoDetalleTarget) itDos.next();
						cod.getConceptoDetalle();
					}
					cop.getConcepto();
					cop.getIdConceptoDetalle();
				}
				sucSeleccionada.setValue(concepto.getSucursal().getIdSucursal());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ConceptoService conceptoService = transaccionesService.getConceptoService();
				idSucursalSeleccionada = (Long) (sucSeleccionada.getValue());
				concepto.setSucursal((SucursalFiel) Util.buscarElemento(sucursalList, new SucursalFiel(idSucursalSeleccionada)));
				if (concepto.getConceptoDetalleSet() == null) {
					concepto.setConceptoDetalleSet(new HashSet());
				}
				Iterator iter = listDetalles.iterator();
				while (iter.hasNext()) {
					WrapperDetalle wap = (WrapperDetalle) iter.next();
					if (wap.getDetalle().getIdConceptoDetalle() == null) {
						wap.getDetalle().setConcepto(concepto);
						concepto.getConceptoDetalleSet().add(wap.getDetalle());
					}
				}
				concepto.setFondos("N");
				if (calculaDisponibleAux) {
					concepto.setCalculaDisponible("V");
				} else {
					concepto.setCalculaDisponible("F");
				}
				tarSeleccionado = (Long) menuTar.getValue();
				switch (tarSeleccionado.intValue()) {
				case 0:
					concepto.setTarget("CL");
					break;
				case 1:
					concepto.setTarget("CO");
					break;
				case 2:
					concepto.setTarget("AM");
					break;
				}
				if (concepto.getIdConcepto() == null) {
					conceptoService.grabarConcepto(concepto);
				} else {
					conceptoService.actualizarConcepto(concepto);
				}
				alta = false;
				popup.setPopup(popup.ICONO_OK, "El concepto ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ConceptoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto.");
			e1.printStackTrace();
		} catch (ConceptoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		listDetalles = new ArrayList();
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de concepto";
		popup.borrar();
		calculaDisponibleAux = false;
		idSucursalSeleccionada = new Long(0);
		sucSeleccionada = new HtmlSelectOneMenu();
		sucSeleccionada.setValue(new Long(0));
		concepto = new Concepto();
		idConceptoFiltro = "";
		descripcionFiltro = "";
		conceptoList = new ArrayList();
		mostrarOpciones = true;
		tipoLista = "N";
		cargarItems();
	}


	public String aceptarTipoLista() {
		mostrarOpciones = false;
		if (tipoLista.compareTo("N") == 0) {
			// dar de alta concepto Comercio...
			concepto.setEsFiel("N");
		} else {
			// dar de alta concepto Fiel...
			concepto.setEsFiel("S");
		}
		sucSeleccionada.setValue(new Long(1));
		menuTar.setValue(new Long(0));
		tarSeleccionado = new Long(0);
		return "";
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (concepto.getDescripcion() == null || (concepto.getDescripcion() != null && concepto.getDescripcion().compareTo("") == 0)) {
			error.agregar(Error.TRAN_DESCRIPCION_CONCEPTO_REQUERIDA);
		}
		if (concepto.getCodigoConcepto() == null) {
			error.agregar(Error.TRAN_CODIGO_CONCEPTO_REQUERIDO);
		} else {
			// controlo que sea un digito de 3 cifras.
			if (concepto.getCodigoConcepto().longValue() > 1000 || concepto.getCodigoConcepto().longValue() < 0) {
				error.agregar("El código de concepto debe ser un número de 3 cifras.");
			}
		}
		if (concepto.getIdConcepto() == null) {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("codigoConcepto", Filtro.IGUAL, concepto.getCodigoConcepto());
			List listaConceptos;
			try {
				listaConceptos = transaccionesService.getConceptoService().getConcepto(filtro);
				if (listaConceptos.size() > 0)
					error.agregar("El código de concepto ingresado ya existe.");
			} catch (ConceptoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoConcepto() {
		return inicializar();
	}


	public String irAModificarConcepto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar concepto";
		return null;
	}


	public String irAListarConcepto() {
		borrar();
		tituloCorto = "Listado de concepto";
		if (tipoLista == null)
			tipoLista = "N";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarConcepto.jsf");
		return "";
	}


	public String listarConcepto() {
		conceptoList.clear();
		if (tipoLista == null)
			tipoLista = "N";
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (descripcionFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, descripcionFiltro);
			}
			if (idConceptoFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idConcepto", Filtro.IGUAL, new Long(idConceptoFiltro));
			}
			filtro.agregarCampoOperValor("esFiel", Filtro.LIKE, tipoLista);
			List conceptoListAuxi = transaccionesService.getConceptoService().getConcepto(filtro);
			Iterator iter = conceptoListAuxi.iterator();
			while (iter.hasNext())
			{
				Concepto element = (Concepto) iter.next();
				element.getSucursal().getLabel();
				WrapperConcepto wConc = new WrapperConcepto(element);
				conceptoList.add(wConc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConcepto.jsf");
		return null;
	}

	public class WrapperConcepto {
		private boolean tieneClientes;
		private boolean tieneComercios;
		private Concepto ccc;


		public WrapperConcepto(Long idConcepto) {
			this.ccc = new Concepto(idConcepto);
		}


		public WrapperConcepto(Concepto c) {
			this.ccc = c;
			tieneClientes = true;
			tieneComercios = true;
			if (ccc.getTarget().compareTo("CO") == 0) {
				tieneClientes = false;
			}
			if (ccc.getTarget().compareTo("CL") == 0) {
				tieneComercios = false;
			}
		}


		public Concepto getCcc() {
			return ccc;
		}


		public void setCcc(Concepto ccc) {
			this.ccc = ccc;
		}


		public boolean isTieneClientes() {
			return tieneClientes;
		}


		public void setTieneClientes(boolean tieneClientes) {
			this.tieneClientes = tieneClientes;
		}


		public boolean isTieneComercios() {
			return tieneComercios;
		}


		public void setTieneComercios(boolean tieneComercios) {
			this.tieneComercios = tieneComercios;
		}


		public boolean equals(Object obj) {
			boolean result = false;
			if (obj instanceof WrapperConcepto) {
				WrapperConcepto aux = (WrapperConcepto) obj;
				if (aux.getCcc().getIdConcepto().equals(ccc.getIdConcepto())) {
					result = true;
				}
			}
			return result;
		}

	}


	public boolean isCalculaDisponibleAux() {
		return calculaDisponibleAux;
	}


	public void setCalculaDisponibleAux(boolean calculaDisponibleAux) {
		this.calculaDisponibleAux = calculaDisponibleAux;
	}


	public HtmlSelectOneMenu getSucSeleccionada() {
		return sucSeleccionada;
	}


	public void setSucSeleccionada(HtmlSelectOneMenu sucSeleccionada) {
		this.sucSeleccionada = sucSeleccionada;
	}


	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}


	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}


	public String getIdConceptoFiltro() {
		return idConceptoFiltro;
	}


	public void setIdConceptoFiltro(String idConceptoFiltro) {
		this.idConceptoFiltro = idConceptoFiltro;
	}


	public List getListDetalles() {
		return listDetalles;
	}


	public void setListDetalles(List listDetalles) {
		this.listDetalles = listDetalles;
	}

	public class WrapperDetalle {

		private int indice;
		private ConceptoDetalle detalle;


		public WrapperDetalle(ConceptoDetalle conceptoDet) {
			this.indice = contador++;
			this.detalle = conceptoDet;
		}


		public ConceptoDetalle getDetalle() {
			return detalle;
		}


		public void setDetalle(ConceptoDetalle detalle) {
			this.detalle = detalle;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}

	}


	public HtmlSelectOneMenu getMenuTar() {
		return menuTar;
	}


	public void setMenuTar(HtmlSelectOneMenu menuTar) {
		this.menuTar = menuTar;
	}


	public Long getTarSeleccionado() {
		return tarSeleccionado;
	}


	public void setTarSeleccionado(Long tarSeleccionado) {
		this.tarSeleccionado = tarSeleccionado;
	}


	public List getListaTar() {
		return listaTar;
	}


	public void setListaTar(List listaTar) {
		this.listaTar = listaTar;
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


	public boolean isPerteneceAFiel() {
		if (concepto != null && concepto.getEsFiel() != null) {
			perteneceAFiel = concepto.getEsFiel().compareTo("S") == 0;
			return perteneceAFiel;
		} else {
			return false;
		}
	}


	public boolean isEsFiel() {
		if (tipoLista.compareTo("S") == 0)
			return true;
		return false;
	}

}

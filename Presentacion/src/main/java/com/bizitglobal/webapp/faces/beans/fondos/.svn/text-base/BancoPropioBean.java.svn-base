package com.bizitglobal.webapp.faces.beans.fondos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.exception.NroBancarioNoValidoException;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.NroBancarioValido;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;
import com.bizitglobal.tarjetafiel.fondos.service.BancoPropioService;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class BancoPropioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(BancoPropioBean.class);
	private BancoPropio bancoPropio;
	private String nombreFiltro;
	private Long idBancoPropioHidden;
	private String idBancoPropio;
	private List listaChequeras;
	// definicion de un list del objeto base
	private List bancoPropioList;
	private List unBancoPropio;
	// Listas para la presentacion(HtmlSelectItems).
	private List sucursalList = new ArrayList();
	private List sucursalItems = new ArrayList();
	private List bancoList = new ArrayList();
	private List bancoItems = new ArrayList();
	private List monedaList = new ArrayList();
	private List monedaItems = new ArrayList();
	private List tipoCuentaItems = new ArrayList();
	private HtmlSelectOneMenu htmlMenuTip;

	// bloque 1 cbu
	private String nroEntidadCBU;
	private String digitoVer1;

	// bloque 2 cbu

	private String nroCuentaCBU;
	private String digitoVer2;

	// Objetos Relacionados.
	private Long idSucursalSeleccionada;
	private Long idBancoSeleccionado;
	private Long idMonedaSeleccionada;
	private Long idTipoCuentaSeleccionado;
	private String focoHidden;
	private boolean habilitada;

	private String cbuBloque1;
	private String cbuBloque2;


	/*
	 * Tener en cuenta la composición de la CBU, según el siguiente detalle:
	 * 
	 * CBU= Bloque 1: 3 posiciones para Número de Entidad.
	 * 
	 * 4 posiciones para Número de Sucursal.
	 * 
	 * 1 posición para dígito verificador de este bloque.
	 * 
	 * Bloque 2: 13 posiciones para Identificación de la cuenta (2 dígitos para la característica de la cuenta y 11 dígitos para el número de la
	 * cuenta).
	 * 
	 * 1 posición para dígito verificador de este bloque.
	 * 
	 * De esta manera, quedará conformado un campo de 22 posiciones.
	 */

	public BancoPropioBean() {
		super();
		borrar();
		htmlMenuTip = new HtmlSelectOneMenu();
		htmlMenuTip.setValue(new Long(0));
		try {
			try {
				sucursalList = generalService.getSucursalFielService().getSucursales(new Filtro());
				bancoList = generalService.getBancoService().getBancos(new Filtro());
				monedaList = generalService.getMonedaService().getMonedas(new Filtro());
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


	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}


	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}


	public Long getIdBancoPropioHidden() {
		return idBancoPropioHidden;
	}


	public void setIdBancoPropioHidden(Long idBancoPropioHidden) {
		this.idBancoPropioHidden = idBancoPropioHidden;
	}


	public List getSucursalItems() {
		return sucursalItems;
	}


	public void setSucursalItems(List sucursalItems) {
		this.sucursalItems = sucursalItems;
	}


	public List getBancoItems() {
		return bancoItems;
	}


	public void setBancoItems(List bancoItems) {
		this.bancoItems = bancoItems;
	}


	public Long getIdSucursalSeleccionada() {
		return idSucursalSeleccionada;
	}


	public void setIdSucursalSeleccionada(Long idSucursalSeleccionada) {
		this.idSucursalSeleccionada = idSucursalSeleccionada;
	}


	public Long getIdBancoSeleccionado() {
		return idBancoSeleccionado;
	}


	public void setIdBancoSeleccionado(Long idBancoSeleccionado) {
		this.idBancoSeleccionado = idBancoSeleccionado;
	}


	public List getBancoPropioList() {
		return bancoPropioList;
	}


	public void setBancoPropioList(List object) {
		this.bancoPropioList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE BANCO PROPIO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		cargarItems();
		return "amBancoPropio";
	}


	private void cargarItems() {

		if (sucursalItems.size() != sucursalList.size()) {
			sucursalItems = new ArrayList();
			sucursalItems.add(new SelectItem(new Long(0), "Seleccionar Sucursal"));
			sucursalItems.addAll(Util.cargarSelectItem(sucursalList));
		}
		if (bancoItems.size() != bancoList.size()) {
			bancoItems = new ArrayList();
			bancoItems.add(new SelectItem(new Long(0), "Seleccionar Banco"));
			bancoItems.addAll(Util.cargarSelectItem(bancoList));
		}
		if (monedaItems.size() != monedaList.size()) {
			monedaItems = new ArrayList();
			monedaItems.add(new SelectItem(new Long(0), "Seleccionar Moneda"));
			monedaItems.addAll(Util.cargarSelectItem(monedaList));
		}
		Util.limpiarLista(tipoCuentaItems);
		tipoCuentaItems.add(new SelectItem(new Long(0), "Seleccionar Tipo de Cuenta"));
		tipoCuentaItems.add(new SelectItem(new Long(1), "CC"));
		tipoCuentaItems.add(new SelectItem(new Long(2), "CA"));
		tipoCuentaItems.add(new SelectItem(new Long(3), "CU"));

	}


	public String editarBancoPropio() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Cuenta Bancaria";
		try {
			bancoPropio = fondosService.getBancoPropioService().leerBancoPropio(idBancoPropioHidden);
			idSucursalSeleccionada = bancoPropio.getSucursal().getIdSucursal();
			idBancoSeleccionado = bancoPropio.getBanco().getIdBanco();
			idMonedaSeleccionada = bancoPropio.getMoneda().getIdMoneda();
			if (bancoPropio.getTipoCuenta().compareTo("CC") == 0) {
				htmlMenuTip.setValue(new Long(1));
				idTipoCuentaSeleccionado = new Long(1);
			} else {
				if (bancoPropio.getTipoCuenta().compareTo("CA") == 0) {
					htmlMenuTip.setValue(new Long(2));
					idTipoCuentaSeleccionado = new Long(2);
				} else {
					htmlMenuTip.setValue(new Long(3));
					idTipoCuentaSeleccionado = new Long(3);
				}
			}

			nroEntidadCBU = bancoPropio.getCbu().substring(0, 3);
			nroCuentaCBU = bancoPropio.getCbu().substring(8, bancoPropio.getCbu().length() - 1);
			digitoVer2 = bancoPropio.getCbu().substring(bancoPropio.getCbu().length() - 1, bancoPropio.getCbu().length());
			digitoVer1 = bancoPropio.getCbu().substring(7, 8);

			result = "amBancoPropio";

		} catch (BancoPropioException e1) {
			error.agregar("Ocurrio un error al intentar editar la cuenta bancaria");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarBancoPropio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar la cuenta bancaria");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarBancoPropio.jsf");
		}
		return result;
	}


	public String eliminarBancoPropio() {
		try {
			fondosService.getBancoPropioService().borrarBancoPropio(idBancoPropioHidden);
			bancoPropioList.remove(new BancoPropio(idBancoPropioHidden));
		} catch (BancoPropioException e1) {
			error.agregar("Imposible borrar la cuenta bancaria. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar la cuenta bancaria");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarBancoPropio.jsf");
		return null;
	}


	public String mostrarChequeras() {

		listaChequeras = new ArrayList();
		try {
			List listaCheq = fondosService.getChequeraService().getChequeras(
					new Filtro("bancoPropio.idBancoPropio", Filtro.IGUAL, idBancoPropioHidden));
			Iterator iter = listaCheq.iterator();
			while (iter.hasNext()) {
				Chequera cheq = (Chequera) iter.next();
				cheq.getBancoPropio();
				listaChequeras.add(cheq);
			}
		} catch (ChequeraException e) {

			e.printStackTrace();
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/fondos/popup/chequerasPorBancoPropio.jsf";
		ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
		return null;
	}


	public String setearEntidadCBU() {

		// Banco banco= (Banco)Util.buscarElemento(bancoList, new Banco(idBancoSeleccionado));
		nroEntidadCBU = ((Banco) Util.buscarElemento(bancoList, new Banco(idBancoSeleccionado))).getCodigo();

		// nroEntidadCBU="234";
		return null;

	}


	public List getListaChequeras() {
		return listaChequeras;
	}


	public void setListaChequeras(List listaChequeras) {
		this.listaChequeras = listaChequeras;
	}


	public String grabar() {
		try {

			cbuBloque1 = nroEntidadCBU + bancoPropio.getNumeroSucursal() + digitoVer1;
			cbuBloque2 = nroCuentaCBU + digitoVer2;
			System.out.println("bloque  1:" + cbuBloque1);
			System.out.println("bloque  2:" + cbuBloque2);

			bancoPropio.setNumeroCuenta(bancoPropio.getNumeroCuenta().trim());
			bancoPropio.setCbu(cbuBloque1 + cbuBloque2);
			bancoPropio.setNumeroSucursal(bancoPropio.getNumeroSucursal());
			bancoPropio.setPlaza(bancoPropio.getPlaza());
			bancoPropio.setTipoCuenta(bancoPropio.getTipoCuenta());
			BancoPropioService bancoPropioService = fondosService.getBancoPropioService();
			bancoPropio.setSucursal((SucursalFiel) Util.buscarElemento(sucursalList, new SucursalFiel(idSucursalSeleccionada)));
			bancoPropio.setBanco((Banco) Util.buscarElemento(bancoList, new Banco(idBancoSeleccionado)));
			bancoPropio.setMoneda((Moneda) Util.buscarElemento(monedaList, new Moneda(idMonedaSeleccionada)));
			idTipoCuentaSeleccionado = (Long) htmlMenuTip.getValue();
			switch (idTipoCuentaSeleccionado.intValue()) {
			case 1:
				bancoPropio.setTipoCuenta("CC");
				break;
			case 2:
				bancoPropio.setTipoCuenta("CA");
				break;
			case 3:
				bancoPropio.setTipoCuenta("CU");
				break;
			}
			if (validar()) {
				if (alta) {
					bancoPropioService.grabarBancoPropio(bancoPropio);
				}
				else {
					bancoPropioService.actualizarBancoPropio(bancoPropio);
				}
				// idPaisSeleccionada = new Long(0);
				popup.setPopup(popup.ICONO_OK, "La Cuenta bancaria  ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (BancoPropioDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Cuenta Bancaria.");
			e1.printStackTrace();
		} catch (BancoPropioException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Cuenta Bancaria.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Cuenta Bancaria.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Cuenta Bancaria";
		popup.borrar();
		idBancoPropio = "";
		try {
			unBancoPropio = fondosService.getBancoPropioService().getBancoPropios(new Filtro());
			Iterator bancoPropioIterator = unBancoPropio.iterator();
			while (bancoPropioIterator.hasNext()) {
				BancoPropio ban = (BancoPropio) bancoPropioIterator.next();
				ban.getSucursal();
				ban.getBanco();
				ban.getMoneda();
			}
		} catch (BancoPropioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bancoPropio = new BancoPropio();
		bancoPropioList = new ArrayList();
		idBancoSeleccionado = new Long(0);
		idSucursalSeleccionada = new Long(0);
		idMonedaSeleccionada = new Long(0);
		habilitada = false;
		listaChequeras = new ArrayList();
		nroCuentaCBU = "";
		nroEntidadCBU = "";
		digitoVer1 = "";
		digitoVer2 = "";

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (bancoPropio.getNumeroCuenta() == null || bancoPropio.getNumeroCuenta().equals(""))
			error.agregar(Error.FON_AMBANCOSPROPIOS_NROCTA_REQUERIDA);

		if (idBancoSeleccionado.equals(new Long(0)))
			error.agregar(Error.FON_AMBANCOSPROPIOS_BANCO_REQUERIDO);

		if (idMonedaSeleccionada.equals(new Long(0)))
			error.agregar(Error.FON_AMBANCOSPROPIOS_MONEDA_REQUERIDA);

		if (bancoPropio.getNumeroSucursal() == null || bancoPropio.getNumeroSucursal().equals(new Long(0)))
			error.agregar(Error.FON_AMBANCOSPROPIOS_SUCURSAL_REQUERIDA);

		if (!idBancoSeleccionado.equals(new Long(0)) && !bancoPropio.getNumeroSucursal().equals(new Long(0))) {
			try {
				NroBancarioValido bloque1 = new NroBancarioValido(cbuBloque1, "bloque 1");
			} catch (NroBancarioNoValidoException e) {
				// TODO Auto-generated catch block
				error.agregar(Error.FON_AMBANCOSPROPIOS_BLOQUE1_NOVALIDO);
				e.printStackTrace();
			}

			try {
				NroBancarioValido bloque2 = new NroBancarioValido(cbuBloque2, "bloque 2");
			} catch (NroBancarioNoValidoException e) {
				// TODO Auto-generated catch block
				error.agregar(Error.FON_AMBANCOSPROPIOS_BLOQUE2_NOVALIDO);
				e.printStackTrace();
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoBancoPropio() {
		idSucursalSeleccionada = new Long(0);
		idBancoSeleccionado = new Long(0);
		idMonedaSeleccionada = new Long(0);
		idTipoCuentaSeleccionado = new Long(0);
		return inicializar();
	}


	public String irAModificarBancoPropio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Cuenta Bancaria";

		return null;
	}


	public String irAListarBancoPropio() {
		borrar();
		tituloCorto = "Listado de Cuentas Bancarias";
		cargarItems();
		Session.redirect("/tarjetafiel/fondos/listarBancoPropio.jsf");
		return "";
	}


	public String listarBancoPropio() {
		bancoPropioList = new ArrayList();
		try {

			Filtro filtro = new Filtro();
			if (idBancoPropio != null && !idBancoPropio.equals(""))
				filtro.agregarCampoOperValor("idBancoPropio", Filtro.IGUAL, new Long(idBancoPropio));
			if (idSucursalSeleccionada != null && !idSucursalSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("sucursal.idSucursal", Filtro.IGUAL, idSucursalSeleccionada);
			if (idBancoSeleccionado != null && !idBancoSeleccionado.equals(new Long(0)))
				filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, idBancoSeleccionado);

			/*
			 * if(idBancoSeleccionado != null && !idBancoSeleccionado.equals(new Long(0))) filtro.agregarCampoOperValor("impresora.idImpresora",
			 * Filtro.IGUAL, idBancoSeleccionado);
			 */

			if (bancoPropio.getNumeroCuenta() != null && !bancoPropio.getNumeroCuenta().equals(""))
				filtro.agregarCampoOperValor("numeroCuenta", Filtro.LIKE, bancoPropio.getNumeroCuenta());

			bancoPropioList = fondosService.getBancoPropioService().getBancoPropios(filtro);
			Iterator iter = bancoPropioList.iterator();
			while (iter.hasNext())
			{
				BancoPropio element = (BancoPropio) iter.next();
				if (element.getSucursal() != null)
					element.getSucursal().getLabel();
				if (element.getBanco() != null)
					element.getBanco().getLabel();
				if (element.getMoneda() != null)
					element.getMoneda().getLabel();
			}
			// idProvincia = "";
			// idPaisSeleccionada = new Long(0);
			// provincia.setNombre("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarBancoPropio.jsf");
		return null;
	}


	public String getIdBancoPropio() {
		return idBancoPropio;
	}


	public void setIdBancoPropio(String idBancoPropio) {
		this.idBancoPropio = idBancoPropio;
	}


	public boolean isHabilitada() {
		if (bancoPropio.getHabilitado() != null)
			return Convertidores.getBoolean(bancoPropio.getHabilitado().toString());
		return false;
	}


	public void setHabilitada(boolean habilitada) {
		bancoPropio.setHabilitado(Character.valueOf(Convertidores.getSorN(habilitada).charAt(0)));
	}


	public Long getIdMonedaSeleccionada() {
		return idMonedaSeleccionada;
	}


	public void setIdMonedaSeleccionada(Long idMonedaSeleccionada) {
		this.idMonedaSeleccionada = idMonedaSeleccionada;
	}


	public List getMonedaItems() {
		return monedaItems;
	}


	public void setMonedaItems(List monedaItems) {
		this.monedaItems = monedaItems;
	}


	public List getTipoCuentaItems() {
		return tipoCuentaItems;
	}


	public void setTipoCuentaItems(List tipoCuentaItems) {
		this.tipoCuentaItems = tipoCuentaItems;
	}


	public Long getIdTipoCuentaSeleccionado() {
		return idTipoCuentaSeleccionado;
	}


	public void setIdTipoCuentaSeleccionado(Long idTipoCuentaSeleccionado) {
		this.idTipoCuentaSeleccionado = idTipoCuentaSeleccionado;
	}


	public HtmlSelectOneMenu getHtmlMenuTip() {
		return htmlMenuTip;
	}


	public void setHtmlMenuTip(HtmlSelectOneMenu htmlMenuTip) {
		this.htmlMenuTip = htmlMenuTip;
	}


	public String getDigitoVer1() {
		return digitoVer1;
	}


	public void setDigitoVer1(String digitoVer1) {
		this.digitoVer1 = digitoVer1;
	}


	public String getDigitoVer2() {
		return digitoVer2;
	}


	public void setDigitoVer2(String digitoVer2) {
		this.digitoVer2 = digitoVer2;
	}


	public String getNroCuentaCBU() {
		return nroCuentaCBU;
	}


	public void setNroCuentaCBU(String nroCuentaCBU) {
		this.nroCuentaCBU = nroCuentaCBU;
	}


	public String getNroEntidadCBU() {
		return nroEntidadCBU;
	}


	public void setNroEntidadCBU(String nroEntidadCBU) {
		this.nroEntidadCBU = nroEntidadCBU;
	}

}

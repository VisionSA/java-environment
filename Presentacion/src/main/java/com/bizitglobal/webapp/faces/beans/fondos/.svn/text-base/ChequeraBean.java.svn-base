package com.bizitglobal.webapp.faces.beans.fondos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeraService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;




@SuppressWarnings({ "rawtypes", "unchecked" })
public class ChequeraBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ChequeraBean.class);
	private Chequera chequera;
	private String nombreFiltro;
	private Long idChequeraHidden;
	private String idChequera;
	private boolean habilitada;
	private List unaChequera;
	// definicion de un list del objeto base
	private List chequeraList;
	// Listas para la presentacion(HtmlSelectItems).
	// Listas para la presentacion(HtmlSelectItems).
	private List bancoPropioList = new ArrayList();
	private List bancoPropioItems = new ArrayList();

	// Objetos Relacionados.
	private Long idBancoPropioSeleccionado;

	private String focoHidden;


	public ChequeraBean() {
		super();
		borrar();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Chequera getChequera() {
		return chequera;
	}


	public void setChequera(Chequera chequera) {
		this.chequera = chequera;
	}


	public Long getIdChequeraHidden() {
		return idChequeraHidden;
	}


	public void setIdChequeraHidden(Long idChequeraHidden) {
		this.idChequeraHidden = idChequeraHidden;
	}


	public List getChequeraList() {
		return chequeraList;
	}


	public void setChequeraList(List object) {
		this.chequeraList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CHEQUERA
	 ************************************************************************/

	public String inicializar() {
		System.out.println("BEAN CHEQUERA");
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		try {
			try {

				bancoPropioList = fondosService.getBancoPropioService().getBancoPropios(new Filtro("tipoCuenta", Filtro.NOTIN, "'CA'"));
			} catch (BancoPropioException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cargarItems();
		return "amChequera";
	}


	private void cargarItems() {

		bancoPropioItems = new ArrayList();
		bancoPropioItems.add(new SelectItem(new Long(0), "Seleccionar Cuenta"));
		bancoPropioItems.addAll(Util.cargarSelectItem(bancoPropioList));
	}


	public String editarChequera() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Chequera";
		try {
			Util.limpiarLista(bancoPropioList);
			bancoPropioList = fondosService.getBancoPropioService().getBancoPropios(new Filtro("tipoCuenta", Filtro.NOTIN, "'CA'"));
			cargarItems();
			chequera = fondosService.getChequeraService().leerChequera(idChequeraHidden);
			idBancoPropioSeleccionado = chequera.getBancoPropio().getIdBancoPropio();
			result = "amChequera";
		} catch (ChequeraException e1) {
			error.agregar("Ocurrio un error al intentar editar la chequera");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarChequera.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar la chequera");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarChequera.jsf");
		}
		return result;
	}


	public String eliminarChequera() {
		try {
			fondosService.getChequeraService().borrarChequera(idChequeraHidden);
			chequeraList.remove(new Chequera(idChequeraHidden));
		} catch (ChequeraException e1) {
			error.agregar("Imposible borrar la chequera. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar la chequera");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarChequera.jsf");
		return null;
	}


	public String grabar() {
		try {
			chequera.setNroInicio(chequera.getNroInicio());
			chequera.setNroFin(chequera.getNroFin());
			chequera.setBancoPropio((BancoPropio) Util.buscarElemento(bancoPropioList, new BancoPropio(idBancoPropioSeleccionado)));

			if (validar()) {
				// Inicio los servis que voy a usar
				ChequeraService chequeraService = fondosService.getChequeraService();
				if (alta) {
					// Grabo el nuevo objeto
					chequera.setProximoAUsar(chequera.getNroInicio());
					chequeraService.grabarChequera(chequera);
				} else {
					chequeraService.actualizarChequera(chequera);
				}
				popup.setPopup(popup.ICONO_OK, "La Chequera ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ChequeraDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Chequera .");
			e1.printStackTrace();
		} catch (ChequeraException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Chequera.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Chequera.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Chequera";
		popup.borrar();
		try {
			unaChequera = fondosService.getChequeraService().getChequeras(new Filtro());
			Iterator chequeraIterator = unaChequera.iterator();
			while (chequeraIterator.hasNext()) {
				Chequera cheq = (Chequera) chequeraIterator.next();
				cheq.getBancoPropio();
			}
		} catch (ChequeraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		idChequera = "";

		chequera = new Chequera();
		chequeraList = new ArrayList();
		// idBancoPropioSeleccionado = chequera.getBancoPropio().getIdBancoPropio();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (chequera.getNroInicio() == null)
			error.agregar(Error.FON_AMCHEQUERA_NROINI_REQUERIDA);
		if (chequera.getNroFin() == null)
			error.agregar(Error.FON_AMCHEQUERA_NROFIN_REQUERIDA);
		else {
			if (chequera.getNroInicio() != null)
				if (chequera.getNroFin().longValue() < chequera.getNroInicio().longValue())
					error.agregar(Error.FON_AMCHEQUERA_NROFIN_MENOR_NROINICIO);
			if (chequera.getProximoAUsar() != null) {
				if (chequera.getNroFin().longValue() < chequera.getProximoAUsar().longValue())
					error.agregar(Error.FON_AMCHEQUERA_NROFIN_MENOR_PROXIMOAUSAR);
				if (chequera.getNroInicio().longValue() > chequera.getProximoAUsar().longValue())
					error.agregar(Error.FON_AMCHEQUERA_NROINICIO_MAYOR_PROXIMOAUSAR);
			}
		}

		if (idBancoPropioSeleccionado.equals(new Long(0)))
			error.agregar(Error.FON_AMCHEQUERA_BANCO_PROPIO_REQUERIDA);

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoChequera() {
		idBancoPropioSeleccionado = new Long(0);
		return inicializar();
	}


	public String irAModificarChequera() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Chequera";
		return null;
	}


	public String irAListarChequera() {
		borrar();
		tituloCorto = "Listado de Chequera";
		cargarItems();
		Session.redirect("/tarjetafiel/fondos/listarChequera.jsf");
		return "";
	}


	public String listarChequera() {
		chequeraList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idChequera != null && !idChequera.equals(""))
				filtro.agregarCampoOperValor("idChequera", Filtro.IGUAL, new Long(idChequera));
			if (idBancoPropioSeleccionado != null && !idBancoPropioSeleccionado.equals(new Long(0)))
				filtro.agregarCampoOperValor("bancoPropio.idBancoPropio", Filtro.IGUAL, idBancoPropioSeleccionado);
			/*
			 * if(impresora.getDescripcion() != null && !impresora.getDescripcion().equals("")) filtro.agregarCampoOperValor("descripcion",
			 * Filtro.LIKE, impresora.getDescripcion());
			 */
			/*
			 * if(impresora.getHabilitado() != null && !impresora.getHabilitado().equals("")) filtro.agregarCampoOperValor("signo", Filtro.LIKE,
			 * moneda.getSigno());
			 */

			chequeraList = fondosService.getChequeraService().getChequeras(filtro);
			Iterator iter = chequeraList.iterator();
			while (iter.hasNext())
			{
				Chequera element = (Chequera) iter.next();
				if (element.getBancoPropio() != null)
					element.getBancoPropio().getLabel();
			}
			// idChequera = "";
			// chequera.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarChequera.jsf");
		return null;
	}


	public String getIdChequera() {
		return idChequera;
	}


	public void setIdChequera(String idChequera) {
		this.idChequera = idChequera;
	}


	public boolean isHabilitada() {
		if (chequera.getHabilitado() != null) {
			return Convertidores.getBoolean(chequera.getHabilitado().toString());
		}
		return false;
	}


	public void setHabilitada(boolean habilitada) {
		chequera.setHabilitado(Character.valueOf(Convertidores.getSorN(habilitada).charAt(0)));
	}


	public List getBancoPropioItems() {
		return bancoPropioItems;
	}


	public void setBancoPropioItems(List bancoPropioItems) {
		this.bancoPropioItems = bancoPropioItems;
	}


	public Long getIdBancoPropioSeleccionado() {
		return idBancoPropioSeleccionado;
	}


	public void setIdBancoPropioSeleccionado(Long idBancoPropioSeleccionado) {
		this.idBancoPropioSeleccionado = idBancoPropioSeleccionado;
	}

}

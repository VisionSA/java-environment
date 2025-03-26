package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.general.exception.ImpresoraDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.ImpresoraException;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.service.ImpresoraService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes"})
public class ImpresoraBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ImpresoraBean.class);
	private Impresora impresora;
	private String nombreFiltro;
	private Long idImpresoraHidden;
	private String idImpresora;
	private boolean habilitada;

	// definicion de un list del objeto base
	private List impresoraList;
	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;
	private String descImpresoraInic;


	public ImpresoraBean() {
		super();
		borrar();
		try {
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


	public Impresora getImpresora() {
		return impresora;
	}


	public void setImpresora(Impresora impresora) {
		this.impresora = impresora;
	}


	public Long getIdImpresoraHidden() {
		return idImpresoraHidden;
	}


	public void setIdImpresoraHidden(Long idImpresoraHidden) {
		this.idImpresoraHidden = idImpresoraHidden;
	}


	public List getImpresoraList() {
		return impresoraList;
	}


	public void setImpresoraList(List object) {
		this.impresoraList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE IMPRESORA
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
		return "amImpresora";
	}


	private void cargarItems() {
	}


	public String editarImpresora() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Impresora";
		try {
			impresora = generalService.getImpresoraService().leerImpresora(idImpresoraHidden);
			descImpresoraInic = impresora.getDescripcion();
			result = "amImpresora";
		} catch (ImpresoraException e1) {
			error.agregar("Ocurrio un error al intentar editar la impresora");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarImpresora.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar la impresora");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarImpresora.jsf");
		}
		return result;
	}


	public String eliminarImpresora() {
		try {
			generalService.getImpresoraService().borrarImpresora(idImpresoraHidden);
			impresoraList.remove(new Impresora(idImpresoraHidden));
		} catch (ImpresoraException e1) {
			error.agregar("Imposible borrar la impresora. Posee elemntos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar la impresora");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarImpresora.jsf");
		return null;
	}


	public String grabar() {
		try {
			impresora.setDescripcion(impresora.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				ImpresoraService impresoraService = generalService.getImpresoraService();
				if (alta) {
					// Grabo el nuevo objeto
					impresoraService.grabarImpresora(impresora);
				} else {
					// if(!(descImpresoraInic!=null && descImpresoraInic.equals(impresora.getDescripcion())))
					impresoraService.actualizarImpresora(impresora);
				}
				popup.setPopup(popup.ICONO_OK, "La Impresora ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ImpresoraDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impresora.");
			e1.printStackTrace();
		} catch (ImpresoraException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impresora.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Impresora.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Impresora";
		popup.borrar();

		idImpresora = "";

		impresora = new Impresora();
		impresoraList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (impresora.getDescripcion() == null || (impresora.getDescripcion() != null && impresora.getDescripcion().equals("")))
			error.agregar(Error.AMIMPRESORA_DESCRIPCION_REQUERIDA);
		if (impresora.getPort() == null || (impresora.getPort() != null && impresora.getPort().equals("")))
			error.agregar(Error.AMIMPRESORA_PUERTO_REQUERIDO);
		/*
		 * if(impresora.getSigno() == null || impresora.getSigno().equals("")) error.agregar(Error.AMMONEDA_SIGNO_REQUERIDA); try { List unaMoneda =
		 * generalService.getMonedaService().getMonedas(new Filtro("descripcion", Filtro.LIKEXS, moneda.getDescripcion().trim())); if(alta){
		 * if(!unaMoneda.isEmpty()) error.agregar(Error.AMMONEDA_MONEDA_REPETIDA); }
		 * 
		 * else{ if(!(descMonedaInic.compareTo(moneda.getDescripcion())==0)) { if(!unaMoneda.isEmpty() )
		 * error.agregar(Error.AMFORMAPAGO_FORMAPAGO_REPETIDA); } }
		 * 
		 * } catch (MonedaException e) { e.printStackTrace(); }
		 */

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoImpresora() {
		return inicializar();
	}


	public String irAModificarImpresora() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Impresora";
		return null;
	}


	public String irAListarImpresora() {
		borrar();
		tituloCorto = "Listado de Impresoras";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarImpresora.jsf");
		return "";
	}


	public String listarImpresora() {
		impresoraList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idImpresora != null && !idImpresora.equals(""))
				filtro.agregarCampoOperValor("idImpresora", Filtro.IGUAL, new Long(idImpresora));
			if (impresora.getDescripcion() != null && !impresora.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, impresora.getDescripcion());
			/*
			 * if(impresora.getHabilitado() != null && !impresora.getHabilitado().equals("")) filtro.agregarCampoOperValor("signo", Filtro.LIKE,
			 * moneda.getSigno());
			 */

			impresoraList = generalService.getImpresoraService().getImpresora(filtro);
			Iterator iter = impresoraList.iterator();
			while (iter.hasNext())
			{
				Impresora element = (Impresora) iter.next();
			}
			idImpresora = "";
			impresora.setDescripcion("");
			habilitada = false;
			// impresora.setSigno("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarImpresora.jsf");
		return null;
	}


	public String getIdImpresora() {
		return idImpresora;
	}


	public void setIdImpresora(String idImpresora) {
		this.idImpresora = idImpresora;
	}


	public String getDescImpresoraInic() {
		return descImpresoraInic;
	}


	public void setDescImpresoraInic(String descImpresoraInic) {
		this.descImpresoraInic = descImpresoraInic;
	}


	public boolean isHabilitada() {
		if (impresora.getHabilitado() != null) {
			return Convertidores.getBoolean(impresora.getHabilitado().toString());
		}
		return false;
	}


	public void setHabilitada(boolean habilitada) {
		impresora.setHabilitado(Character.valueOf(Convertidores.getSorN(habilitada).charAt(0)));
	}

}

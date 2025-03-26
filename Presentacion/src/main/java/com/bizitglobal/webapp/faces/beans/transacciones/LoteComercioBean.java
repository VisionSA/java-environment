package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.LoteComercioDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LoteComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LoteComercio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.LoteComercioService;


@SuppressWarnings({"rawtypes"})
public class LoteComercioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LoteComercioBean.class);
	private LoteComercio lotecomercio;
	private Long idLoteComercioHidden;

	// definicion de un list del objeto base
	private List lotecomercioList;

	// Listas para la presentacion(HtmlSelectItems).
	private List codcomercioList = new ArrayList();
	private List codcomercioItems = new ArrayList();

	// Objetos Relacionados.
	private Long idCodComercioSeleccionada;

	private String focoHidden;


	public LoteComercioBean() {
		super();
		borrar();
		try {
			try {
				codcomercioList = transaccionesService.getCodComercioService().getCodComercio(new Filtro());
			} catch (CodComercioException e1) {
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


	public LoteComercio getLoteComercio() {
		return lotecomercio;
	}


	public void setLoteComercio(LoteComercio lotecomercio) {
		this.lotecomercio = lotecomercio;
	}


	public Long getIdLoteComercioHidden() {
		return idLoteComercioHidden;
	}


	public void setIdLoteComercioHidden(Long idLoteComercioHidden) {
		this.idLoteComercioHidden = idLoteComercioHidden;
	}


	public List getCodComercioItems() {
		return codcomercioItems;
	}


	public void setCodComercioItems(List codcomercioItems) {
		this.codcomercioItems = codcomercioItems;
	}


	public Long getIdCodComercioSeleccionada() {
		return idCodComercioSeleccionada;
	}


	public void setIdCodComercioSeleccionada(Long idCodComercioSeleccionada) {
		this.idCodComercioSeleccionada = idCodComercioSeleccionada;
	}


	public List getLoteComercioList() {
		return lotecomercioList;
	}


	public void setLoteComercioList(List object) {
		this.lotecomercioList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE LOTECOMERCIO
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
		return "amLoteComercio";
	}


	private void cargarItems() {
		if (codcomercioItems.size() != codcomercioList.size()) {
			codcomercioItems = Util.cargarSelectItem(codcomercioList);
		}
	}


	public String editarLoteComercio() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar lotecomercio";
		try {
			lotecomercio = transaccionesService.getLoteComercioService().leerLoteComercio(idLoteComercioHidden);
			result = "amLoteComercio";
		} catch (LoteComercioException e1) {
			error.agregar("Ocurrio un error al intentar editar el lotecomercio");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarLoteComercio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el lotecomercio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarLoteComercio.jsf");
		}
		return result;
	}


	public String eliminarLoteComercio() {
		try {
			transaccionesService.getLoteComercioService().borrarLoteComercio(idLoteComercioHidden);
			lotecomercioList.remove(new LoteComercio(idLoteComercioHidden));
		} catch (LoteComercioException e1) {
			error.agregar("Imposible borrar el lotecomercio. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el lotecomercio");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarLoteComercio.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				LoteComercioService lotecomercioService = transaccionesService.getLoteComercioService();
				if (alta) {
					// Grabo el nuevo objeto
					lotecomercioService.grabarLoteComercio(lotecomercio);
				} else {
					lotecomercioService.actualizarLoteComercio(lotecomercio);
				}
				popup.setPopup(popup.ICONO_OK, "El lotecomercio ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (LoteComercioDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del lotecomercio.");
			e1.printStackTrace();
		} catch (LoteComercioException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del lotecomercio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del lotecomercio.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de lotecomercio";
		popup.borrar();

		lotecomercio = new LoteComercio();
		lotecomercioList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		// TODO: recordar definir las validaciones
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoLoteComercio() {
		return inicializar();
	}


	public String irAModificarLoteComercio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar lotecomercio";
		return null;
	}


	public String irAListarLoteComercio() {
		borrar();
		tituloCorto = "Listado de lotecomercio";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarLoteComercio.jsf");
		return "";
	}


	public String listarLoteComercio() {
		lotecomercioList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, lotecomercio.
			lotecomercioList = transaccionesService.getLoteComercioService().getLoteComercio(filtro);
			Iterator iter = lotecomercioList.iterator();
			while (iter.hasNext())
			{
				LoteComercio element = (LoteComercio) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarLoteComercio.jsf");
		return null;
	}
}

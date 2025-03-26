package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqClienteDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqClienteService;


@SuppressWarnings({"rawtypes"})
public class LiqClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LiqClienteBean.class);
	private LiqCliente liqcliente;
	private String nombreFiltro;
	private Long idLiqClienteHidden;

	// definicion de un list del objeto base
	private List liqclienteList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public LiqClienteBean() {
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


	public LiqCliente getLiqCliente() {
		return liqcliente;
	}


	public void setLiqCliente(LiqCliente liqcliente) {
		this.liqcliente = liqcliente;
	}


	public Long getIdLiqClienteHidden() {
		return idLiqClienteHidden;
	}


	public void setIdLiqClienteHidden(Long idLiqClienteHidden) {
		this.idLiqClienteHidden = idLiqClienteHidden;
	}


	public List getLiqClienteList() {
		return liqclienteList;
	}


	public void setLiqClienteList(List object) {
		this.liqclienteList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE LIQCLIENTE
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
		return "amLiqCliente";
	}


	private void cargarItems() {
	}


	public String editarLiqCliente() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar liqcliente";
		try {
			liqcliente = transaccionesService.getLiqClienteService().leerLiqCliente(idLiqClienteHidden);
			result = "amLiqCliente";
		} catch (LiqClienteException e1) {
			error.agregar("Ocurrio un error al intentar editar el liqcliente");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarLiqCliente.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el liqcliente");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarLiqCliente.jsf");
		}
		return result;
	}


	public String eliminarLiqCliente() {
		try {
			transaccionesService.getLiqClienteService().borrarLiqCliente(idLiqClienteHidden);
			liqclienteList.remove(new LiqCliente(idLiqClienteHidden));
		} catch (LiqClienteException e1) {
			error.agregar("Imposible borrar el liqcliente. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el liqcliente");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarLiqCliente.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				LiqClienteService liqclienteService = transaccionesService.getLiqClienteService();
				if (alta) {
					// Grabo el nuevo objeto
					liqclienteService.grabarLiqCliente(liqcliente);
				} else {
					liqclienteService.actualizarLiqCliente(liqcliente);
				}
				popup.setPopup(popup.ICONO_OK, "El liqcliente ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (LiqClienteDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del liqcliente.");
			e1.printStackTrace();
		} catch (LiqClienteException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del liqcliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del liqcliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de liqcliente";
		popup.borrar();

		liqcliente = new LiqCliente();
		liqclienteList = new ArrayList();
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


	public String irANuevoLiqCliente() {
		return inicializar();
	}


	public String irAModificarLiqCliente() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar liqcliente";
		return null;
	}


	public String irAListarLiqCliente() {
		borrar();
		tituloCorto = "Listado de liqcliente";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarLiqCliente.jsf");
		return "";
	}


	public String listarLiqCliente() {
		liqclienteList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, liqcliente.
			liqclienteList = transaccionesService.getLiqClienteService().getLiqCliente(filtro);
			Iterator iter = liqclienteList.iterator();
			while (iter.hasNext())
			{
				LiqCliente element = (LiqCliente) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarLiqCliente.jsf");
		return null;
	}
}

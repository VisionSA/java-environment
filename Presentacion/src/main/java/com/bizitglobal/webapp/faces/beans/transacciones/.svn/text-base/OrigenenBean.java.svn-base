package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenenDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenenException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Origenen;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.transacciones.service.OrigenenService;


@SuppressWarnings({"rawtypes","unchecked"})
public class OrigenenBean extends BaseBean {
	private static final Logger log = Logger.getLogger(OrigenenBean.class);
	private static int nroRenglones = 0;

	private Origenen origenen;
	private String nombreFiltro;
	private Long idOrigenenHidden;
	private boolean estaActivo;

	private String idOrigenFiltro;
	private String descripcionFiltro;

	// definicion de un list del objeto wrapper del base.
	private List origenenList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public OrigenenBean() {
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


	public Origenen getOrigenen() {
		return origenen;
	}


	public void setOrigenen(Origenen origenen) {
		this.origenen = origenen;
	}


	public Long getIdOrigenenHidden() {
		return idOrigenenHidden;
	}


	public void setIdOrigenenHidden(Long idOrigenenHidden) {
		this.idOrigenenHidden = idOrigenenHidden;
	}


	public List getOrigenenList() {
		return origenenList;
	}


	public void setOrigenenList(List object) {
		this.origenenList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE ORIGENEN
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
		return "amOrigenen";
	}


	private void cargarItems() {

	}


	public String editarOrigenen() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Transacciones - Modificar origen";
		try {
			origenen = transaccionesService.getOrigenenService().leerOrigenen(idOrigenenHidden);
			if (origenen.getActivo() != null && origenen.getActivo().compareTo("S") == 0) {
				estaActivo = true;
			} else {
				estaActivo = false;
			}
			result = "amOrigenen";
		} catch (OrigenenException e1) {
			error.agregar("Ocurrio un error al intentar editar el origen");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarOrigenen.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el origen");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarOrigenen.jsf");
		}
		return result;
	}


	public String eliminarOrigenen() {
		try {
			WrapperOrigen aBorrar = null;
			transaccionesService.getOrigenenService().borrarOrigenen(idOrigenenHidden);
			Iterator iter = origenenList.iterator();
			while (iter.hasNext()) {
				WrapperOrigen wrap = (WrapperOrigen) iter.next();
				if (wrap.getOrig().getIdOrigenes().equals(idOrigenenHidden)) {
					aBorrar = wrap;
					break;
				}
			}
			origenenList.remove(aBorrar);
		} catch (OrigenenException e1) {
			error.agregar("Imposible borrar el origen. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el origen");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarOrigenen.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				OrigenenService origenenService = transaccionesService.getOrigenenService();
				if (estaActivo) {
					origenen.setActivo("S");
				} else {
					origenen.setActivo("N");
				}
				if (alta) {
					// Grabo el nuevo objeto
					// origenenService.grabarOrigenen(origenen);

					log.info("a probar una transaccion");
					// OrigenenService origen = (OrigenenService)ctx.getBean("origenenService")
					origenenService.grabarOrigenen(origenen);

				} else {
					origenenService.actualizarOrigenen(origenen);
				}
				popup.setPopup(popup.ICONO_OK, "El origen ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (OrigenenDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origen.");
			e1.printStackTrace();
		} catch (OrigenenException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origen.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del origen.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Transacciones - Alta de origen";
		popup.borrar();
		estaActivo = false;
		origenen = new Origenen();
		origenenList = new ArrayList();
		descripcionFiltro = "";
		idOrigenFiltro = "";
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (origenen.getDescripcion() == null || origenen.getDescripcion().compareTo("") == 0) {
			error.agregar(Error.TRAN_ORIGEN_DESCRIPCION_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoOrigenen() {
		return inicializar();
	}


	public String irAModificarOrigenen() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar origen";
		return null;
	}


	public String irAListarOrigenen() {
		borrar();
		tituloCorto = "Transacciones - Listado de origen";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarOrigenen.jsf");
		return "";
	}


	public String listarOrigenen() {
		origenenList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (descripcionFiltro != null && descripcionFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, descripcionFiltro);
			}
			if (idOrigenFiltro != null && idOrigenFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idOrigenes", Filtro.IGUAL, new Long(idOrigenFiltro));
			}
			List origenenListAux = transaccionesService.getOrigenenService().getOrigenen(filtro);
			Iterator iter = origenenListAux.iterator();
			while (iter.hasNext())
			{
				Origenen element = (Origenen) iter.next();
				WrapperOrigen wrap = new WrapperOrigen(element);
				origenenList.add(wrap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarOrigenen.jsf");
		return null;
	}


	public boolean isEstaActivo() {
		return estaActivo;
	}


	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	public class WrapperOrigen {

		private Origenen orig;
		private boolean activ;
		private Long renglon;


		public WrapperOrigen(Origenen orig) {
			this.orig = orig;
			this.renglon = new Long(++nroRenglones);
			if (orig.getActivo().compareTo("S") == 0) {
				activ = true;
			} else {
				activ = false;
			}
		}


		public boolean isActiv() {
			return activ;
		}


		public void setActiv(boolean activ) {
			this.activ = activ;
		}


		public Origenen getOrig() {
			return orig;
		}


		public void setOrig(Origenen orig) {
			this.orig = orig;
		}


		public Long getRenglon() {
			return renglon;
		}


		public void setRenglon(Long renglon) {
			this.renglon = renglon;
		}

	}


	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}


	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}


	public String getIdOrigenFiltro() {
		return idOrigenFiltro;
	}


	public void setIdOrigenFiltro(String idOrigenFiltro) {
		this.idOrigenFiltro = idOrigenFiltro;
	}

}

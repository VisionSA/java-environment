package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodigoAutorizacionDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodigoAutorizacionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenenException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodigoAutorizacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Origenen;
import com.bizitglobal.tarjetafiel.transacciones.service.CodigoAutorizacionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CodigoAutorizacionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CodigoAutorizacionBean.class);
	private CodigoAutorizacion codigoAutorizacion;
	private String nombreFiltro;
	private Long idCodigoAutorizacionHidden;

	// definicion de un list del objeto base
	private List codigoAutorizacionList;

	// Listas para la presentacion(HtmlSelectItems).
	private List origenList;
	private List origenItems;

	// Objetos Relacionados.
	private Long idOrigenenSeleccionada;

	private String focoHidden;

	private Date fecha;

	private static int contador;


	public CodigoAutorizacionBean() {
		super();
		codigoAutorizacionList = new ArrayList();
		try {
			origenList = new ArrayList();
			origenList = transaccionesService.getOrigenenService().getOrigenen(new Filtro());
			log.info("tamaño origenList " + origenList.size());
		} catch (OrigenenException e1) {
			e1.printStackTrace();
		}

		borrar();
	}


	public CodigoAutorizacion getCodigoAutorizacion() {
		return codigoAutorizacion;
	}


	public void setCodigoAutorizacion(CodigoAutorizacion codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Long getIdCodigoAutorizacionHidden() {
		return idCodigoAutorizacionHidden;
	}


	public void setIdCodigoAutorizacionHidden(Long idCodigoAutorizacionHidden) {
		this.idCodigoAutorizacionHidden = idCodigoAutorizacionHidden;
	}


	public List getOrigenItems() {
		return origenItems;
	}


	public void setOrigenItems(List origenItems) {
		this.origenItems = origenItems;
	}


	public Long getIdOrigenenSeleccionada() {
		return idOrigenenSeleccionada;
	}


	public void setIdOrigenenSeleccionada(Long idOrigenenSeleccionada) {
		this.idOrigenenSeleccionada = idOrigenenSeleccionada;
	}


	public List getCodigoAutorizacionList() {
		return codigoAutorizacionList;
	}


	public void setCodigoAutorizacionList(List codigoAutorizacionList) {
		this.codigoAutorizacionList = codigoAutorizacionList;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CODIGOAUTORIZACION
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amCodigoAutorizacion";
	}


	public String editarCodigoAutorizacion() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Codigo Autorización";
		try {
			if (!codigoAutorizacionList.isEmpty()) {
				Iterator iterator = codigoAutorizacionList.iterator();
				while (iterator.hasNext()) {
					CodigoAutorizacionWrappers element = (CodigoAutorizacionWrappers) iterator.next();

					if (element.getIndice() == idCodigoAutorizacionHidden.intValue()) {
						codigoAutorizacion = transaccionesService.getCodigoAutorizacionService().leerCodigoAutorizacion(
								element.getCodigoAutorizacion().getIdCodigoautorizacion());
						if (codigoAutorizacion.getFecha() != null)
							fecha = new Date(codigoAutorizacion.getFecha().getTime());

						if (codigoAutorizacion.getOrigenen() != null && !codigoAutorizacion.getOrigenen().getIdOrigenes().equals(new Long(0)))
							idOrigenenSeleccionada = codigoAutorizacion.getOrigenen().getIdOrigenes();
						result = "amCodigoAutorizacion";
					}
				}
			}
			// codigoAutorizacion = transaccionesService.getCodigoAutorizacionService().leerCodigoAutorizacion(idCodigoAutorizacionHidden);

		} catch (CodigoAutorizacionException e1) {
			error.agregar("Ocurrio un error al intentar editar el Codigo Autorización");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCodigoAutorizacion.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Codigo Autorización");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCodigoAutorizacion.jsf");
		}
		return result;
	}


	public String eliminarCodigoAutorizacion() {
		try {
			if (!codigoAutorizacionList.isEmpty()) {
				Iterator iterator = codigoAutorizacionList.iterator();
				while (iterator.hasNext()) {
					CodigoAutorizacionWrappers element = (CodigoAutorizacionWrappers) iterator.next();

					if (element.getIndice() == idCodigoAutorizacionHidden.intValue()) {
						transaccionesService.getCodigoAutorizacionService().borrarCodigoAutorizacion(element.getCodigoAutorizacion());
						// codigoAutorizacionList.remove(element);
					}
				}
			}
		} catch (CodigoAutorizacionException e1) {
			error.agregar("Imposible borrar el Codigo Autorización. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Codigo Autorización");
			e.printStackTrace();
		}
		listarCodigoAutorizacion();
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {

				CodigoAutorizacionService codigoautorizacionService = transaccionesService.getCodigoAutorizacionService();
				codigoAutorizacion.setOrigenen((Origenen) Util.buscarElemento(origenList, new Origenen(idOrigenenSeleccionada)));

				if (fecha != null && !fecha.equals(new Date()))
					codigoAutorizacion.setFecha(new Timestamp(fecha.getTime()));

				if (alta) {
					// Grabo el nuevo objeto
					codigoautorizacionService.grabarCodigoAutorizacion(codigoAutorizacion);
				} else {
					codigoautorizacionService.actualizarCodigoAutorizacion(codigoAutorizacion);
				}
				popup.setPopup(popup.ICONO_OK, "El Codigo Autorización ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CodigoAutorizacionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Codigo Autorización.");
			e1.printStackTrace();
		} catch (CodigoAutorizacionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Codigo Autorización.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Codigo Autorización.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Codigo Autorización";
		popup.borrar();

		codigoAutorizacion = new CodigoAutorizacion();

		origenItems = new ArrayList();
		origenItems.clear();
		origenItems.add(new SelectItem(new Long(0), "Seleccionar Origen"));
		origenItems.addAll(CodigoAutorizacionUtil.armarOrigen(origenList));

		idOrigenenSeleccionada = new Long(0);
		fecha = null;
		contador = 0;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCodigoAutorizacion() {
		return inicializar();
	}


	public String irAModificarCodigoAutorizacion() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Código Autorización";
		return null;
	}


	public String irAListarCodigoAutorizacion() {
		borrar();
		codigoAutorizacionList = new ArrayList();
		tituloCorto = "Listado de Codigo Autorización";
		Session.redirect("/tarjetafiel/transacciones/listarCodigoAutorizacion.jsf");
		return "";
	}


	public String listarCodigoAutorizacion() {
		codigoAutorizacionList = new ArrayList();
		try {

			Filtro filtro = new Filtro();
			if (codigoAutorizacion.getIdCodigoautorizacion() != null && !codigoAutorizacion.getIdCodigoautorizacion().equals(new Long(0)))
				filtro.agregarCampoOperValor("idCodigoautorizacion", Filtro.IGUAL, codigoAutorizacion.getIdCodigoautorizacion());

			if (codigoAutorizacion.getCodigo() != null && !codigoAutorizacion.getCodigo().equals(""))
				filtro.agregarCampoOperValor("codigo", Filtro.LIKE, codigoAutorizacion.getCodigo());

			if (fecha != null && !fecha.equals(new Date())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fecha);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("fecha", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fecha.getTime())));
				filtro.agregarCampoOperValor("fecha", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			if (codigoAutorizacion.getOrigenen() != null && codigoAutorizacion.getOrigenen().getIdOrigenes() != null
					&& !codigoAutorizacion.getOrigenen().getIdOrigenes().equals(new Long(0)))
				filtro.agregarCampoOperValor("origenen.idOrigenes", Filtro.IGUAL, codigoAutorizacion.getOrigenen().getIdOrigenes());

			List aux = transaccionesService.getCodigoAutorizacionService().getCodigoAutorizacion(filtro);
			Iterator iter = aux.iterator();
			while (iter.hasNext())
			{
				CodigoAutorizacion element = (CodigoAutorizacion) iter.next();

				if (element.getOrigenen() != null)
					element.getOrigenen().getLabel();

				CodigoAutorizacionWrappers wrappers = new CodigoAutorizacionWrappers(element);
				codigoAutorizacionList.add(wrappers);
			}
			codigoAutorizacion.setIdCodigoautorizacion(null);
			codigoAutorizacion.setCodigo(null);
			fecha = null;
			codigoAutorizacion.getOrigenen().setIdOrigenes(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCodigoAutorizacion.jsf");
		return "";
	}

	public class CodigoAutorizacionWrappers {

		private int indice;
		private CodigoAutorizacion codigoAutorizacion;
		private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


		public CodigoAutorizacionWrappers(CodigoAutorizacion codigoAutorizacion) {
			this.indice = contador++;
			this.codigoAutorizacion = codigoAutorizacion;
		}


		public CodigoAutorizacion getCodigoAutorizacion() {
			return codigoAutorizacion;
		}


		public void setCodigoAutorizacion(CodigoAutorizacion codigoAutorizacion) {
			this.codigoAutorizacion = codigoAutorizacion;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String getFecha() {
			if (codigoAutorizacion.getFecha() != null)
				return format.format(new Date(codigoAutorizacion.getFecha().getTime()));

			return "";
		}
	}
}

package com.bizitglobal.webapp.faces.beans.transacciones;

import java.math.BigDecimal;
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
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteComercioDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqComercioException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercio;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteComercioService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CtaCteComercioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CtaCteComercioBean.class);

	private CtaCteComercio ctaCteComercio;
	private String nombreFiltro;
	private Long idCtaCteComercioHidden;

	// definicion de un list del objeto base
	private List ctaCteComercioList;
	private Long idComercio;
	private String nombreComercio, cuitComercio;
	private boolean comercioSeleccionado;

	// Listas para la presentacion(HtmlSelectItems).
	private List conceptoDetalleList;
	private List conceptoDetalleItems;

	private List liqComercioList;
	private List liqComercioItems;

	private List codComercioList;
	private List codComercioItems;

	// Objetos Relacionados.
	private Long idConceptoDetalleSeleccionada;
	private String conceptoDetalleSeleccionado;
	private Long idLiqComercioSeleccionada;
	private Long idCodComercioSeleccionada;

	private String focoHidden;

	private Date fechaContable;
	private Date fechaFacturacion;
	private Date fechaLote;
	private Date fechaReal;
	private Date fechaRealHasta;

	private static int contador;


	public CtaCteComercioBean() {
		super();

		try {
			conceptoDetalleList = new ArrayList();
			liqComercioList = new ArrayList();
			codComercioList = new ArrayList();

			conceptoDetalleList = transaccionesService.getConceptoDetalleService().getConceptoDetalle(new Filtro());
			liqComercioList = transaccionesService.getLiqComercioService().getLiqComercio(new Filtro());
			codComercioList = transaccionesService.getCodComercioService().getCodComercio(new Filtro());
		} catch (ConceptoDetalleException e1) {
			e1.printStackTrace();
		} catch (LiqComercioException e1) {
			e1.printStackTrace();
		} catch (CodComercioException e1) {
			e1.printStackTrace();
		}
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public CtaCteComercio getCtaCteComercio() {
		return ctaCteComercio;
	}


	public void setCtaCteComercio(CtaCteComercio ctaCteComercio) {
		this.ctaCteComercio = ctaCteComercio;
	}


	public Long getIdCtaCteComercioHidden() {
		return idCtaCteComercioHidden;
	}


	public void setIdCtaCteComercioHidden(Long idCtaCteComercioHidden) {
		this.idCtaCteComercioHidden = idCtaCteComercioHidden;
	}


	public List getConceptoDetalleItems() {
		return conceptoDetalleItems;
	}


	public void setConceptoDetalleItems(List conceptoDetalleItems) {
		this.conceptoDetalleItems = conceptoDetalleItems;
	}


	public Long getIdConceptoDetalleSeleccionada() {
		return idConceptoDetalleSeleccionada;
	}


	public void setIdConceptoDetalleSeleccionada(Long idConceptoDetalleSeleccionada) {
		this.idConceptoDetalleSeleccionada = idConceptoDetalleSeleccionada;
	}


	public List getLiqComercioItems() {
		return liqComercioItems;
	}


	public void setLiqComercioItems(List liqComercioItems) {
		this.liqComercioItems = liqComercioItems;
	}


	public Long getIdLiqComercioSeleccionada() {
		return idLiqComercioSeleccionada;
	}


	public void setIdLiqComercioSeleccionada(Long idLiqComercioSeleccionada) {
		this.idLiqComercioSeleccionada = idLiqComercioSeleccionada;
	}


	public List getCodComercioItems() {
		return codComercioItems;
	}


	public void setCodComercioItems(List codComercioItems) {
		this.codComercioItems = codComercioItems;
	}


	public Long getIdCodComercioSeleccionada() {
		return idCodComercioSeleccionada;
	}


	public void setIdCodComercioSeleccionada(Long idCodComercioSeleccionada) {
		this.idCodComercioSeleccionada = idCodComercioSeleccionada;
	}


	public List getCtaCteComercioList() {
		return ctaCteComercioList;
	}


	public void setCtaCteComercioList(List ctaCteComercioList) {
		this.ctaCteComercioList = ctaCteComercioList;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Date getFechaContable() {
		return fechaContable;
	}


	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}


	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}


	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}


	public Date getFechaLote() {
		return fechaLote;
	}


	public void setFechaLote(Date fechaLote) {
		this.fechaLote = fechaLote;
	}


	public Date getFechaReal() {
		return fechaReal;
	}


	public void setFechaRealHasta(Date fechaRealHasta) {
		this.fechaRealHasta = fechaRealHasta;
	}


	public Date getFechaRealHasta() {
		return fechaRealHasta;
	}


	public void setFechaReal(Date fechaReal) {
		this.fechaReal = fechaReal;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CTACTECOMERCIO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		comercioSeleccionado = false;
		return "amCtaCteComercio";
	}


	public String editarCtaCteComercio() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Cuenta Corriente Comercio";
		try {
			if (!ctaCteComercioList.isEmpty()) {
				Iterator iterator = ctaCteComercioList.iterator();
				while (iterator.hasNext()) {
					CtaCteComercioWrappers element = (CtaCteComercioWrappers) iterator.next();

					if (element.getIndice() == idCtaCteComercioHidden.intValue()) {
						ctaCteComercio = transaccionesService.getCtaCteComercioService().leerCtaCteComercio(
								element.getComercio().getIdCtacteComercio());
						fechaContable = new Date(ctaCteComercio.getFechaContable().getTime());

						if (ctaCteComercio.getFechaFacturacion() != null)
							fechaFacturacion = new Date(ctaCteComercio.getFechaFacturacion().getTime());

						if (ctaCteComercio.getFechaLote() != null)
							fechaLote = new Date(ctaCteComercio.getFechaLote().getTime());

						if (ctaCteComercio.getCodComercio() != null && ctaCteComercio.getCodComercio().getIdCodComercio() != null)
							idCodComercioSeleccionada = ctaCteComercio.getCodComercio().getIdCodComercio();

						if (ctaCteComercio.getConceptoDetalle() != null && ctaCteComercio.getConceptoDetalle().getIdConceptoDetalle() != null)
							idConceptoDetalleSeleccionada = ctaCteComercio.getConceptoDetalle().getIdConceptoDetalle();

						if (ctaCteComercio.getLiqComercio() != null && ctaCteComercio.getLiqComercio().getIdLiqComercio() != null)
							idLiqComercioSeleccionada = ctaCteComercio.getLiqComercio().getIdLiqComercio();

						result = "amCtaCteComercio";
					}
				}
			}
		} catch (CtaCteComercioException e1) {
			error.agregar("Ocurrio un error al intentar editar el Cuenta Corriente Comercio");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarCtaCteComercio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Cuenta Corriente Comercio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarCtaCteComercio.jsf");
		}
		return result;
	}


	public String eliminarCtaCteComercio() {
		try {
			if (!ctaCteComercioList.isEmpty()) {
				Iterator iterator = ctaCteComercioList.iterator();
				while (iterator.hasNext()) {
					CtaCteComercioWrappers element = (CtaCteComercioWrappers) iterator.next();

					if (element.getIndice() == idCtaCteComercioHidden.intValue()) {
						transaccionesService.getCtaCteComercioService().borrarCtaCteComercio(element.getComercio());
						listarCtaCteComercio();
					}
				}
			}
		} catch (CtaCteComercioException e1) {
			error.agregar("Imposible borrar el Cuenta Corriente Comercio. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Cuenta Corriente Comercio");
			e.printStackTrace();
		}
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				CtaCteComercioService ctactecomercioService = transaccionesService.getCtaCteComercioService();
				ctaCteComercio.setConceptoDetalle((ConceptoDetalle) Util.buscarElemento(conceptoDetalleList, new ConceptoDetalle(
						idConceptoDetalleSeleccionada)));
				ctaCteComercio.setLiqComercio((LiqComercio) Util.buscarElemento(liqComercioList, new LiqComercio(idLiqComercioSeleccionada)));
				ctaCteComercio.setCodComercio((CodComercio) Util.buscarElemento(codComercioList, new CodComercio(idCodComercioSeleccionada)));

				ctaCteComercio.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));

				ctaCteComercio.setFechaContable(new Timestamp(fechaContable.getTime()));

				if (fechaFacturacion != null && !fechaFacturacion.equals(new Date()))
					ctaCteComercio.setFechaFacturacion(new Timestamp(fechaFacturacion.getTime()));

				if (fechaLote != null && !fechaLote.equals(new Date()))
					ctaCteComercio.setFechaLote(new Timestamp(fechaLote.getTime()));

				if (alta) {
					ctactecomercioService.grabarCtaCteComercio(ctaCteComercio);
				} else {
					ctactecomercioService.actualizarCtaCteComercio(ctaCteComercio);
				}
				popup.setPopup(popup.ICONO_OK, "La Cuenta Corriente Comercio ha sido almacenado exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CtaCteComercioDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Comercio.");
			e1.printStackTrace();
		} catch (CtaCteComercioException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Comercio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Comercio.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {

		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Cuenta Corriente Comercio";
		popup.borrar();

		conceptoDetalleItems = new ArrayList();
		liqComercioItems = new ArrayList();
		codComercioItems = new ArrayList();

		ctaCteComercio = new CtaCteComercio();

		conceptoDetalleItems.clear();
		conceptoDetalleItems.add(new SelectItem(new Long(0), "Seleccionar Concepto"));
		conceptoDetalleItems.addAll(Util.cargarSelectItem(conceptoDetalleList));

		liqComercioItems.clear();
		liqComercioItems.add(new SelectItem(new Long(0), "Seleccionar Liquidación"));
		liqComercioItems.addAll(Util.cargarSelectItem(liqComercioList));

		codComercioItems.clear();
		codComercioItems.add(new SelectItem(new Long(0), "Seleccionar Código Comercio"));
		codComercioItems.addAll(Util.cargarSelectItem(codComercioList));

		fechaContable = new Date(Calendar.getInstance().getTime().getTime());
		fechaFacturacion = null;
		fechaLote = null;
		fechaReal = null;
		fechaRealHasta = null;
		idCodComercioSeleccionada = new Long(0);
		idConceptoDetalleSeleccionada = new Long(0);
		idLiqComercioSeleccionada = new Long(0);

		contador = 0;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (fechaContable == null || fechaContable.equals(new Date()))
			error.agregar(Error.TRAN_FECHA_CONTABLE_REQUERIDO);

		if (ctaCteComercio.getIdOperador() == null || ctaCteComercio.getIdOperador().equals(new Long(0)))
			error.agregar(Error.TRAN_ID_OPERADOR_REQUERIDO);

		if (ctaCteComercio.getIdTranascciones() == null || ctaCteComercio.getIdTranascciones().equals(new Long(0)))
			error.agregar(Error.TRAN_TRANSACCION_REQUERIDO);

		if (ctaCteComercio.getImporte() == null || ctaCteComercio.getImporte().equals(new Long(0)))
			error.agregar(Error.TRAN_IMPORTE_REQUERIDO);

		if (ctaCteComercio.getNroCuota() == null || ctaCteComercio.getNroCuota().equals(new Integer(0)))
			error.agregar(Error.TRAN_NRO_CUOTA_REQUERIDO);

		if (ctaCteComercio.getSigno() == null || ctaCteComercio.getSigno().equals(new Integer(0)))
			error.agregar(Error.TRAN_SIGNO_REQUERIDO);

		if (idConceptoDetalleSeleccionada == null || idConceptoDetalleSeleccionada.equals(new Long(0)))
			error.agregar(Error.TRAN_CONCEPTO_DETALLE_REQUERIDO);

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCtaCteComercio() {
		return inicializar();
	}


	public String irAModificarCtaCteComercio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Cuenta Corriente Comercio";
		return null;
	}


	public String irAListarCtaCteComercio() {
		borrar();
		ctaCteComercioList = new ArrayList();
		idConceptoDetalleSeleccionada = null;
		idLiqComercioSeleccionada = null;
		fechaContable = null;
		fechaFacturacion = null;
		fechaLote = null;
		fechaReal = null;
		fechaRealHasta = null;
		tituloCorto = "Listado de Cuenta Corriente Comercio";
		comercioSeleccionado = false;
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteComercio.jsf");
		return "";
	}


	public String listarCtaCteComercio() {
		ctaCteComercioList = new ArrayList();
		try {
			Calendar calendar = Calendar.getInstance();

			Filtro filtro = new Filtro();
			if (isComercioSeleccionado()) {
				log.info("el id del comercio a buscar es " + idComercio);
				filtro.agregarCampoOperValor("codComercio.idCodComercio", Filtro.IGUAL, idComercio);
			}
			if (ctaCteComercio.getIdCtacteComercio() != null && !ctaCteComercio.getIdCtacteComercio().equals(new Long(0)))
				filtro.agregarCampoOperValor("idCtacteComercio", Filtro.IGUAL, ctaCteComercio.getIdCtacteComercio());

			if (ctaCteComercio.getNroCuota() != null && !ctaCteComercio.getNroCuota().equals(new Integer(0)))
				filtro.agregarCampoOperValor("nroCuota", Filtro.IGUAL, ctaCteComercio.getNroCuota());

			if (ctaCteComercio.getImporte() != null && !ctaCteComercio.getImporte().equals(new BigDecimal(0)))
				filtro.agregarCampoOperValor("importe", Filtro.IGUAL, ctaCteComercio.getImporte());

			if (conceptoDetalleSeleccionado != null && conceptoDetalleSeleccionado.compareTo("") != 0)
				filtro.agregarCampoOperValor("conceptoDetalle.nombre", Filtro.LIKE, conceptoDetalleSeleccionado);

			if (idLiqComercioSeleccionada != null && !idLiqComercioSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("liqComercio.idLiqComercio", Filtro.IGUAL, idLiqComercioSeleccionada);

			if (fechaContable != null && !fechaContable.equals(new Date())) {
				filtro.agregarCampoOperValor("fechaContable", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaContable.getTime())));
				calendar.setTime(fechaContable);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("fechaContable", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			if (fechaFacturacion != null && !fechaFacturacion.equals(new Date())) {
				filtro.agregarCampoOperValor("fechaFacturacion", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaFacturacion.getTime())));
				calendar.setTime(fechaFacturacion);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("fechaFacturacion", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			if (fechaReal != null && !fechaReal.equals(new Date())) {
				filtro.agregarCampoOperValor("timestamp", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaReal.getTime())));
				// calendar.setTime(fechaReal);
				// calendar.add(Calendar.DAY_OF_YEAR, 1);
				// filtro.agregarCampoOperValor("timestamp", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			if (fechaRealHasta != null && !fechaRealHasta.equals(new Date())) {
				// filtro.agregarCampoOperValor("timestamp", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaReal.getTime())));
				calendar.setTime(fechaRealHasta);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("timestamp", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			if (fechaLote != null && !fechaLote.equals(new Date())) {
				filtro.agregarCampoOperValor("fechaLote", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaLote.getTime())));
				calendar.setTime(fechaLote);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("fechaLote", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			List aux = transaccionesService.getCtaCteComercioService().getCtaCteComercio(filtro);
			Iterator iter = aux.iterator();
			while (iter.hasNext())
			{
				CtaCteComercio element = (CtaCteComercio) iter.next();

				if (element.getConceptoDetalle() != null)
					element.getConceptoDetalle().getLabel();

				if (element.getLiqComercio() != null)
					element.getLiqComercio().getLabel();

				if (element.getCodComercio() != null)
					element.getCodComercio().getLabel();

				CtaCteComercioWrappers wrappers = new CtaCteComercioWrappers(element);

				ctaCteComercioList.add(wrappers);

			}

			// idConceptoDetalleSeleccionada = null;
			// idLiqComercioSeleccionada = null;
			// fechaContable = null;
			// fechaFacturacion = null;
			// fechaLote = null;
			// fechaReal = null;
			// fechaRealHasta = null;
			// ctaCteComercio = new CtaCteComercio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteComercio.jsf");
		return null;
	}


	public String listarCtaCteComercio(Long idComercio) {
		log.info("listare los cuentas corrientes de un solo comercio");
		ctaCteComercioList = new ArrayList();
		comercioSeleccionado = true;
		this.idComercio = idComercio;
		CodComercio comercio;
		try {
			comercio = transaccionesService.getCodComercioService().leerCodComercio(idComercio);
			nombreComercio = comercio.getSucEmpresa().getDescripcion();
			cuitComercio = comercio.getSucEmpresa().getEmpresa().getCuit().toString();
		} catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borrar();
		fechaContable = null;
		fechaFacturacion = null;
		fechaReal = null;
		fechaRealHasta = null;
		idConceptoDetalleSeleccionada = null;
		idLiqComercioSeleccionada = null;
		tituloCorto = "Listado de Cuenta Corriente Cliente";
		ctaCteComercioList = new ArrayList();
		listarCtaCteComercio();
		return null;
	}


	public String incluirTodosLosComercios() {
		comercioSeleccionado = false;
		return null;
	}

	public class CtaCteComercioWrappers {

		private int indice;
		private CtaCteComercio comercio;
		private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


		public CtaCteComercioWrappers(CtaCteComercio comercio) {
			super();
			this.comercio = comercio;
			this.indice = contador++;
		}


		public CtaCteComercio getComercio() {
			return comercio;
		}


		public void setComercio(CtaCteComercio comercio) {
			this.comercio = comercio;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String getFechaContable() {
			if (comercio.getFechaContable() != null)
				return format.format(new Date(comercio.getFechaContable().getTime()));

			return "";
		}


		public String getFechaFacturacion() {
			if (comercio.getFechaFacturacion() != null)
				return format.format(new Date(comercio.getFechaFacturacion().getTime()));

			return "";
		}


		public String getFechaReal() {
			if (comercio.getTimestamp() != null)
				return format.format(new Date(comercio.getTimestamp().getTime()));

			return "";
		}


		public String getFechaLote() {
			if (comercio.getFechaLote() != null)
				return format.format(new Date(comercio.getFechaLote().getTime()));

			return "";
		}
	}


	public String getConceptoDetalleSeleccionado() {
		return conceptoDetalleSeleccionado;
	}


	public void setConceptoDetalleSeleccionado(String conceptoDetalleSeleccionado) {
		this.conceptoDetalleSeleccionado = conceptoDetalleSeleccionado;
	}


	public String getCuitComercio() {
		return cuitComercio;
	}


	public void setCuitComercio(String cuitComercio) {
		this.cuitComercio = cuitComercio;
	}


	public String getNombreComercio() {
		return nombreComercio;
	}


	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}


	public boolean isComercioSeleccionado() {
		return comercioSeleccionado;
	}


	public void setComercioSeleccionado(boolean comercioSeleccionado) {
		this.comercioSeleccionado = comercioSeleccionado;
	}
}

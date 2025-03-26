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
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteClienteService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CtaCteClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CtaCteClienteBean.class);
	private CtaCteCliente ctaCteCliente;
	private String nombreFiltro;
	private Long idCtaCteClienteHidden;
	private boolean individuoSeleccionado;
	private Long idCliente; // para el filtro por clientes, cuando listo desde un individuo en particular.
	private String nombreCliente, cuitCliente;

	// definicion de un list del objeto base
	private List ctaCteClienteList;

	// Listas para la presentacion(HtmlSelectItems).
	private List clienteList = new ArrayList();
	private List clienteItems = new ArrayList();

	private List conceptoDetalleList = new ArrayList();
	private List conceptoDetalleItems = new ArrayList();

	private List liqClienteList = new ArrayList();
	private List liqClienteItems = new ArrayList();

	// Objetos Relacionados.
	private Long idClienteSeleccionada;
	private Long idConceptoDetalleSeleccionada;
	private String conceptoDetalleSeleccionado;
	private Long idLiqClienteSeleccionada;

	private String focoHidden;

	private Date fechaContable;
	private Date fechaFacturacion;
	private Date fechaReal;
	private Date fechaRealHasta;

	private boolean contabilizado;
	private boolean estadoImpacto;

	private static int contador;


	public CtaCteClienteBean() {
		super();
		ctaCteClienteList = new ArrayList();
		try {
			clienteList = new ArrayList();
			conceptoDetalleList = new ArrayList();
			liqClienteList = new ArrayList();

			clienteList = transaccionesService.getClienteTransaccionService().getCliente(new Filtro());
			conceptoDetalleList = transaccionesService.getConceptoDetalleService().getConceptoDetalle(new Filtro());
			liqClienteList = transaccionesService.getLiqClienteService().getLiqCliente(new Filtro());
		} catch (ClienteTransaccionException e1) {
			e1.printStackTrace();
		} catch (ConceptoDetalleException e1) {
			e1.printStackTrace();
		} catch (LiqClienteException e1) {
			e1.printStackTrace();
		}
		individuoSeleccionado = false;
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public CtaCteCliente getCtaCteCliente() {
		return ctaCteCliente;
	}


	public void setCtaCteCliente(CtaCteCliente ctaCteCliente) {
		this.ctaCteCliente = ctaCteCliente;
	}


	public Long getIdCtaCteClienteHidden() {
		return idCtaCteClienteHidden;
	}


	public void setIdCtaCteClienteHidden(Long idCtaCteClienteHidden) {
		this.idCtaCteClienteHidden = idCtaCteClienteHidden;
	}


	public List getClienteItems() {
		return clienteItems;
	}


	public void setClienteItems(List clienteItems) {
		this.clienteItems = clienteItems;
	}


	public Long getIdClienteSeleccionada() {
		return idClienteSeleccionada;
	}


	public void setIdClienteSeleccionada(Long idClienteSeleccionada) {
		this.idClienteSeleccionada = idClienteSeleccionada;
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


	public List getLiqClienteItems() {
		return liqClienteItems;
	}


	public void setLiqClienteItems(List liqClienteItems) {
		this.liqClienteItems = liqClienteItems;
	}


	public Long getIdLiqClienteSeleccionada() {
		return idLiqClienteSeleccionada;
	}


	public void setIdLiqClienteSeleccionada(Long idLiqClienteSeleccionada) {
		this.idLiqClienteSeleccionada = idLiqClienteSeleccionada;
	}


	public List getCtaCteClienteList() {
		return ctaCteClienteList;
	}


	public void setCtaCteClienteList(List ctaCteClienteList) {
		this.ctaCteClienteList = ctaCteClienteList;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public boolean getContabilizado() {
		return contabilizado;
	}


	public void setContabilizado(boolean contabilizado) {
		this.contabilizado = contabilizado;
	}


	public boolean getEstadoImpacto() {
		return estadoImpacto;
	}


	public void setEstadoImpacto(boolean estadoImpacto) {
		this.estadoImpacto = estadoImpacto;
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


	public Date getFechaReal() {
		return fechaReal;
	}


	public void setFechaReal(Date fechaReal) {
		this.fechaReal = fechaReal;
	}


	public Date getFechaRealHasta() {
		return fechaRealHasta;
	}


	public void setFechaRealHasta(Date fechaRealHasta) {
		this.fechaRealHasta = fechaRealHasta;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CTACTECLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		return "amCtaCteCliente";
	}


	public String editarCtaCteCliente(CtaCteCliente ctaCteCliente) {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Cuenta Corriente Cliente";
		this.ctaCteCliente = ctaCteCliente;
		if (ctaCteCliente.getFechaContable() != null)
			fechaContable = new Date(ctaCteCliente.getFechaContable().getTime());
		if (ctaCteCliente.getFechaFacturacion() != null)
			fechaFacturacion = new Date(ctaCteCliente.getFechaFacturacion().getTime());
		contabilizado = Convertidores.getBoolean(ctaCteCliente.getContabilizado());
		estadoImpacto = Convertidores.getBoolean(ctaCteCliente.getEstadoImpacto());
		if (ctaCteCliente.getCliente() != null && ctaCteCliente.getCliente().getIdCliente() != null
				&& !ctaCteCliente.getCliente().getIdCliente().equals(new Long(0)))
			idClienteSeleccionada = ctaCteCliente.getCliente().getIdCliente();
		if (ctaCteCliente.getConceptoDetalle() != null && ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle() != null
				&& !ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle().equals(new Long(0)))
			idConceptoDetalleSeleccionada = ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle();
		if (ctaCteCliente.getLiqCliente() != null && ctaCteCliente.getLiqCliente().getIdLiqCliente() != null
				&& !ctaCteCliente.getLiqCliente().getIdLiqCliente().equals(new Long(0)))
			idLiqClienteSeleccionada = ctaCteCliente.getLiqCliente().getIdLiqCliente();
		result = "amCtaCteCliente";
		return result;
	}


	public String editarCtaCteCliente() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Cuenta Corriente Cliente";
		try {
			if (!ctaCteClienteList.isEmpty()) {
				Iterator iterator = ctaCteClienteList.iterator();
				while (iterator.hasNext()) {
					CtaCteClienteWrappers element = (CtaCteClienteWrappers) iterator.next();
					if (element.getIndice() == idCtaCteClienteHidden.intValue()) {
						ctaCteCliente = transaccionesService.getCtaCteClienteService().leerCtaCteCliente(
								element.getCtaCteCliente().getIdCtacteCliente());

						if (ctaCteCliente.getFechaContable() != null)
							fechaContable = new Date(ctaCteCliente.getFechaContable().getTime());

						if (ctaCteCliente.getFechaFacturacion() != null)
							fechaFacturacion = new Date(ctaCteCliente.getFechaFacturacion().getTime());

						contabilizado = Convertidores.getBoolean(ctaCteCliente.getContabilizado());
						estadoImpacto = Convertidores.getBoolean(ctaCteCliente.getEstadoImpacto());

						if (ctaCteCliente.getCliente() != null && ctaCteCliente.getCliente().getIdCliente() != null
								&& !ctaCteCliente.getCliente().getIdCliente().equals(new Long(0)))
							idClienteSeleccionada = ctaCteCliente.getCliente().getIdCliente();

						if (ctaCteCliente.getConceptoDetalle() != null && ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle() != null
								&& !ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle().equals(new Long(0)))
							idConceptoDetalleSeleccionada = ctaCteCliente.getConceptoDetalle().getIdConceptoDetalle();

						if (ctaCteCliente.getLiqCliente() != null && ctaCteCliente.getLiqCliente().getIdLiqCliente() != null
								&& !ctaCteCliente.getLiqCliente().getIdLiqCliente().equals(new Long(0)))
							idLiqClienteSeleccionada = ctaCteCliente.getLiqCliente().getIdLiqCliente();

						result = "amCtaCteCliente";
					}
				}
			}
		} catch (CtaCteClienteException e1) {
			error.agregar("Ocurrio un error al intentar editar el Cuenta Corriente Cliente");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Cuenta Corriente Cliente");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		}
		return result;
	}


	public String eliminarCtaCteCliente() {
		try {
			if (!ctaCteClienteList.isEmpty()) {
				Iterator iterator = ctaCteClienteList.iterator();
				while (iterator.hasNext()) {
					CtaCteClienteWrappers element = (CtaCteClienteWrappers) iterator.next();
					if (element.getIndice() == idCtaCteClienteHidden.intValue()) {
						transaccionesService.getCtaCteClienteService().borrarCtaCteCliente(element.getCtaCteCliente());
						listarCtaCteCliente();
					}
				}
			}
		} catch (CtaCteClienteException e1) {
			error.agregar("Imposible borrar el Cuenta Corriente Cliente. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Cuenta Corriente Cliente");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				CtaCteClienteService ctacteclienteService = transaccionesService.getCtaCteClienteService();
				ctaCteCliente.setCliente((ClienteTransaccion) Util.buscarElemento(clienteList, new ClienteTransaccion(idClienteSeleccionada)));
				ctaCteCliente.setConceptoDetalle((ConceptoDetalle) Util.buscarElemento(conceptoDetalleList, new ConceptoDetalle(
						idConceptoDetalleSeleccionada)));
				ctaCteCliente.setLiqCliente((LiqCliente) Util.buscarElemento(liqClienteList, new LiqCliente(idLiqClienteSeleccionada)));

				ctaCteCliente.setContabilizado(Convertidores.getSorN(contabilizado));
				ctaCteCliente.setEstadoImpacto(Convertidores.getSorN(estadoImpacto));

				ctaCteCliente.setFechaContable(new Timestamp(fechaContable.getTime()));

				if (fechaFacturacion != null && !fechaFacturacion.equals(new Date()))
					ctaCteCliente.setFechaFacturacion(new Timestamp(fechaFacturacion.getTime()));

				ctaCteCliente.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));

				if (alta) {
					ctacteclienteService.grabarCtaCteCliente(ctaCteCliente);
				} else {
					ctacteclienteService.actualizarCtaCteCliente(ctaCteCliente);
				}
				popup.setPopup(popup.ICONO_OK, "El Cuenta Corriente Cliente ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CtaCteClienteDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Cliente.");
			e1.printStackTrace();
		} catch (CtaCteClienteException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Cliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Cuenta Corriente Cliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Cuenta Corriente Cliente";
		popup.borrar();

		ctaCteCliente = new CtaCteCliente();

		fechaContable = new Date(Calendar.getInstance().getTime().getTime());
		fechaFacturacion = null;
		fechaReal = null;
		fechaRealHasta = null;

		contador = 0;

		contabilizado = false;
		estadoImpacto = false;
		liqClienteItems = new ArrayList();
		clienteItems = new ArrayList();
		conceptoDetalleItems = new ArrayList();

		clienteItems.clear();
		clienteItems.add(new SelectItem(new Long(0), "Seleccionar Cliente"));
		clienteItems.addAll(Util.cargarSelectItem(clienteList));

		conceptoDetalleItems.clear();
		conceptoDetalleItems.add(new SelectItem(new Long(0), "Seleccionar Concepto Detalle"));
		conceptoDetalleItems.addAll(Util.cargarSelectItem(conceptoDetalleList));

		liqClienteItems.clear();
		liqClienteItems.add(new SelectItem(new Long(0), "Seleccionar Liquiedación Cliente"));
		liqClienteItems.addAll(Util.cargarSelectItem(liqClienteList));
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (fechaContable == null || fechaContable.equals(new Date()))
			error.agregar(Error.TRAN_FECHA_CONTABLE_REQUERIDO);

		if (ctaCteCliente.getIdOperador() == null || ctaCteCliente.getIdOperador().equals(new Long(0)))
			error.agregar(Error.TRAN_ID_OPERADOR_REQUERIDO);

		if (ctaCteCliente.getImporte() == null || ctaCteCliente.getImporte().equals(new BigDecimal(0)))
			error.agregar(Error.TRAN_IMPORTE_REQUERIDO);

		if (ctaCteCliente.getNroCuota() == null || ctaCteCliente.getNroCuota().equals(new Integer(0)))
			error.agregar(Error.TRAN_NRO_CUOTA_REQUERIDO);

		if (ctaCteCliente.getSigno() == null || ctaCteCliente.getSigno().equals(new Integer(0)))
			error.agregar(Error.TRAN_SIGNO_REQUERIDO);

		if (ctaCteCliente.getIdTranascciones() == null || ctaCteCliente.getIdTranascciones().equals(new Long(0)))
			error.agregar(Error.TRAN_TRANSACCION_REQUERIDO);

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCtaCteCliente() {
		return inicializar();
	}


	public String irAModificarCtaCteCliente() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Cuenta Corriente Cliente";
		return null;
	}


	public String irAListarCtaCteCliente() {
		borrar();
		fechaContable = null;
		fechaFacturacion = null;
		fechaReal = null;
		fechaRealHasta = null;
		idConceptoDetalleSeleccionada = null;
		conceptoDetalleSeleccionado = "";
		idLiqClienteSeleccionada = null;
		tituloCorto = "Listado de Cuenta Corriente Cliente";
		ctaCteClienteList = new ArrayList();
		individuoSeleccionado = false;
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		return "";
	}


	public String listarCtaCteCliente() {
		ctaCteClienteList = new ArrayList();
		// individuoSeleccionado = false;
		try {
			Calendar calendar = Calendar.getInstance();

			Filtro filtro = new Filtro();
			if (isIndividuoSeleccionado()) {
				filtro.agregarCampoOperValor("clienteTransaccion.idCliente", Filtro.IGUAL, idCliente);
			}
			if (ctaCteCliente.getIdCtacteCliente() != null && !ctaCteCliente.getIdCtacteCliente().equals(new Long(0)))
				filtro.agregarCampoOperValor("idCtacteCliente", Filtro.IGUAL, ctaCteCliente.getIdCtacteCliente());

			if (ctaCteCliente.getNroCuota() != null && !ctaCteCliente.getNroCuota().equals(new Integer(0)))
				filtro.agregarCampoOperValor("nroCuota", Filtro.IGUAL, ctaCteCliente.getNroCuota());

			if (ctaCteCliente.getImporte() != null && !ctaCteCliente.getImporte().equals(new BigDecimal(0)))
				filtro.agregarCampoOperValor("importe", Filtro.IGUAL, ctaCteCliente.getImporte());

			if (conceptoDetalleSeleccionado != null && conceptoDetalleSeleccionado.compareTo("") != 0) {
				filtro.agregarCampoOperValor("conceptoDetalle.nombre", Filtro.LIKE, conceptoDetalleSeleccionado);
			}

			if (idLiqClienteSeleccionada != null && !idLiqClienteSeleccionada.equals(new Long(0))) {
				filtro.agregarCampoOperValor("liqCliente.idLiqCliente", Filtro.IGUAL, idLiqClienteSeleccionada);
			}

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
				// filtro.agregarCampoOperValor("timestamp", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaRealHasta.getTime())));
				calendar.setTime(fechaRealHasta);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				filtro.agregarCampoOperValor("timestamp", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(calendar.getTime().getTime())));
			}

			List aux = transaccionesService.getCtaCteClienteService().getCtaCteCliente(filtro);
			Iterator iter = aux.iterator();
			while (iter.hasNext())
			{
				CtaCteCliente element = (CtaCteCliente) iter.next();

				if (element.getCliente() != null)
					element.getCliente().getLabel();

				if (element.getConceptoDetalle() != null)
					element.getConceptoDetalle().getLabel();

				if (element.getLiqCliente() != null)
					element.getLiqCliente().getLabel();

				CtaCteClienteWrappers wrappers = new CtaCteClienteWrappers(element);
				ctaCteClienteList.add(wrappers);
			}
			// ctaCteCliente.setIdCtacteCliente(null);
			// ctaCteCliente.setNroCuota(null);
			// ctaCteCliente.setImporte(null);
			// idConceptoDetalleSeleccionada = null;
			// idLiqClienteSeleccionada = null;
			// fechaContable = null;
			// fechaFacturacion = null;
			// fechaReal = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		return null;
	}


	public String listarCtaCteCliente(Long idCliente) {
		ctaCteClienteList = new ArrayList();
		individuoSeleccionado = true;
		this.idCliente = idCliente;
		ClienteTransaccion cliente;
		try {
			cliente = transaccionesService.getClienteTransaccionService().leerCliente(idCliente);
			nombreCliente = cliente.getNombreCliente();
			cuitCliente = cliente.getIndividuo().getCuil();
		} catch (ClienteTransaccionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borrar();
		fechaContable = null;
		fechaFacturacion = null;
		fechaReal = null;
		fechaRealHasta = null;
		idConceptoDetalleSeleccionada = null;
		idLiqClienteSeleccionada = null;
		tituloCorto = "Listado de Cuenta Corriente Cliente";
		ctaCteClienteList = new ArrayList();
		listarCtaCteCliente();
		Session.redirect("/tarjetafiel/transacciones/listarCtaCteCliente.jsf");
		return null;
	}


	public String incluirTodosLosClientes() {
		individuoSeleccionado = false;
		return null;
	}

	public class CtaCteClienteWrappers {

		private int indice;
		private CtaCteCliente ctaCteCliente;
		private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


		public CtaCteClienteWrappers(CtaCteCliente ctaCteCliente) {
			super();
			this.ctaCteCliente = ctaCteCliente;
			this.indice = contador++;

		}


		public CtaCteCliente getCtaCteCliente() {
			return ctaCteCliente;
		}


		public void setCtaCteCliente(CtaCteCliente ctaCteCliente) {
			this.ctaCteCliente = ctaCteCliente;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String getFechaContable() {
			if (ctaCteCliente.getFechaContable() != null)
				return format.format(new Date(ctaCteCliente.getFechaContable().getTime()));

			return "";
		}


		public String getFechaFacturacion() {
			if (ctaCteCliente.getFechaFacturacion() != null)
				return format.format(new Date(ctaCteCliente.getFechaFacturacion().getTime()));

			return "";
		}


		public String getFechaReal() {
			if (ctaCteCliente.getTimestamp() != null)
				return format.format(new Date(ctaCteCliente.getTimestamp().getTime()));

			return "";
		}

	}


	public boolean isIndividuoSeleccionado() {
		return individuoSeleccionado;
	}


	public void setIndividuoSeleccionado(boolean individuoSeleccionado) {
		this.individuoSeleccionado = individuoSeleccionado;
	}


	public String getCuitCliente() {
		return cuitCliente;
	}


	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public String getConceptoDetalleSeleccionado() {
		return conceptoDetalleSeleccionado;
	}


	public void setConceptoDetalleSeleccionado(String conceptoDetalleSeleccionado) {
		this.conceptoDetalleSeleccionado = conceptoDetalleSeleccionado;
	}
}

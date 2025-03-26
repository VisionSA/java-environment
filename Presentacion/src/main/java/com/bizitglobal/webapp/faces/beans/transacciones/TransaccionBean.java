package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioListaPrecioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.TransaccionDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.TransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Transaccion;
import com.bizitglobal.tarjetafiel.transacciones.service.TransaccionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class TransaccionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TransaccionBean.class);
	private Transaccion transaccion;
	private String nombreFiltro;
	private Long idTransaccionHidden;

	// definicion de un list del objeto base
	private List transaccionList;

	// Listas para la presentacion(HtmlSelectItems).
	private List clienteList = new ArrayList();
	private List clienteItems = new ArrayList();

	private List comerciolistaprecioList = new ArrayList();
	private List comerciolistaprecioItems = new ArrayList();

	private List conceptoList = new ArrayList();
	private List conceptoItems = new ArrayList();

	private List codcomercioList = new ArrayList();
	private List codcomercioItems = new ArrayList();

	// Objetos Relacionados.
	private Long idClienteSeleccionada;
	private Long idComercioListaPrecioSeleccionada;
	private Long idConceptoSeleccionada;
	private Long idCodComercioSeleccionada;

	private String focoHidden;

	private boolean anulacion;
	private boolean estadoImpacto;
	private boolean finalizoOffLine;
	private boolean finalizoOnLine;
	private boolean formaIngresoTarjeta;
	private boolean indicadorCaptura;
	private boolean signo;
	private boolean tipoCuota;
	private boolean tipoTerminal;
	private boolean utilizaPin;


	public TransaccionBean() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Transaccion getTransaccion() {
		return transaccion;
	}


	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}


	public Long getIdTransaccionHidden() {
		return idTransaccionHidden;
	}


	public void setIdTransaccionHidden(Long idTransaccionHidden) {
		this.idTransaccionHidden = idTransaccionHidden;
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


	public List getComercioListaPrecioItems() {
		return comerciolistaprecioItems;
	}


	public void setComercioListaPrecioItems(List comerciolistaprecioItems) {
		this.comerciolistaprecioItems = comerciolistaprecioItems;
	}


	public Long getIdComercioListaPrecioSeleccionada() {
		return idComercioListaPrecioSeleccionada;
	}


	public void setIdComercioListaPrecioSeleccionada(Long idComercioListaPrecioSeleccionada) {
		this.idComercioListaPrecioSeleccionada = idComercioListaPrecioSeleccionada;
	}


	public List getConceptoItems() {
		return conceptoItems;
	}


	public void setConceptoItems(List conceptoItems) {
		this.conceptoItems = conceptoItems;
	}


	public Long getIdConceptoSeleccionada() {
		return idConceptoSeleccionada;
	}


	public void setIdConceptoSeleccionada(Long idConceptoSeleccionada) {
		this.idConceptoSeleccionada = idConceptoSeleccionada;
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


	public List getTransaccionList() {
		return transaccionList;
	}


	public void setTransaccionList(List object) {
		this.transaccionList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public boolean getAnulacion() {
		return anulacion;
	}


	public void setAnulacion(boolean anulacion) {
		this.anulacion = anulacion;
	}


	public boolean getEstadoImpacto() {
		return estadoImpacto;
	}


	public void setEstadoImpacto(boolean estadoImpacto) {
		this.estadoImpacto = estadoImpacto;
	}


	public boolean getFinalizoOffLine() {
		return finalizoOffLine;
	}


	public void setFinalizoOffLine(boolean finalizoOffLine) {
		this.finalizoOffLine = finalizoOffLine;
	}


	public boolean getFinalizoOnLine() {
		return finalizoOnLine;
	}


	public void setFinalizoOnLine(boolean finalizoOnLine) {
		this.finalizoOnLine = finalizoOnLine;
	}


	public boolean getFormaIngresoTarjeta() {
		return formaIngresoTarjeta;
	}


	public void setFormaIngresoTarjeta(boolean formaIngresoTarjeta) {
		this.formaIngresoTarjeta = formaIngresoTarjeta;
	}


	public boolean getIndicadorCaptura() {
		return indicadorCaptura;
	}


	public void setIndicadorCaptura(boolean indicadorCaptura) {
		this.indicadorCaptura = indicadorCaptura;
	}


	public boolean getSigno() {
		return signo;
	}


	public void setSigno(boolean signo) {
		this.signo = signo;
	}


	public boolean getTipoCuota() {
		return tipoCuota;
	}


	public void setTipoCuota(boolean tipoCuota) {
		this.tipoCuota = tipoCuota;
	}


	public boolean getTipoTerminal() {
		return tipoTerminal;
	}


	public void setTipoTerminal(boolean tipoTerminal) {
		this.tipoTerminal = tipoTerminal;
	}


	public boolean getUtilizaPin() {
		return utilizaPin;
	}


	public void setUtilizaPin(boolean utilizaPin) {
		this.utilizaPin = utilizaPin;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE TRANSACCION
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amTransaccion";
	}


	public String editarTransaccion() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar transaccion";
		try {
			transaccion = transaccionesService.getTransaccionService().leerTransaccion(idTransaccionHidden);

			anulacion = Convertidores.getBoolean(transaccion.getAnulacion());
			estadoImpacto = Convertidores.getBoolean(transaccion.getEstadoImpacto());
			finalizoOffLine = Convertidores.getBoolean(transaccion.getFinalizoOffline());
			finalizoOnLine = Convertidores.getBoolean(transaccion.getFinalizoOnline());
			formaIngresoTarjeta = Convertidores.getBoolean(transaccion.getFormaIngresoTarjeta());
			signo = Convertidores.getBoolean(transaccion.getSigno());
			tipoCuota = Convertidores.getBoolean(transaccion.getTipoCuota());
			tipoTerminal = Convertidores.getBoolean(transaccion.getTipoTerminal());
			utilizaPin = Convertidores.getBoolean(transaccion.getUtilizaPin());

			result = "amTransaccion";
		} catch (TransaccionException e1) {
			error.agregar("Ocurrio un error al intentar editar el transaccion");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarTransaccion.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el transaccion");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarTransaccion.jsf");
		}
		return result;
	}


	public String eliminarTransaccion() {
		try {
			transaccionesService.getTransaccionService().borrarTransaccion(idTransaccionHidden);
			transaccionList.remove(new Transaccion(idTransaccionHidden));
		} catch (TransaccionException e1) {
			error.agregar("Imposible borrar el transaccion. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el transaccion");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarTransaccion.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				TransaccionService transaccionService = transaccionesService.getTransaccionService();

				transaccion.setAnulacion(Convertidores.getSorN(anulacion));
				transaccion.setEstadoImpacto(Convertidores.getSorN(estadoImpacto));
				transaccion.setFinalizoOffline(Convertidores.getSorN(finalizoOffLine));
				transaccion.setFinalizoOnline(Convertidores.getSorN(finalizoOnLine));
				transaccion.setFormaIngresoTarjeta(Convertidores.getSorN(formaIngresoTarjeta));
				transaccion.setSigno(Convertidores.getSorN(signo));
				transaccion.setTipoCuota(Convertidores.getSorN(tipoCuota));
				transaccion.setTipoTerminal(Convertidores.getSorN(tipoTerminal));
				transaccion.setUtilizaPin(Convertidores.getSorN(utilizaPin));

				transaccion.setClienteTransaccion((ClienteTransaccion) Util
						.buscarElemento(clienteList, new ClienteTransaccion(idClienteSeleccionada)));
				transaccion.setComercioListaPrecio((ComercioListaPrecio) Util.buscarElemento(comerciolistaprecioList, new ComercioListaPrecio(
						idComercioListaPrecioSeleccionada)));
				transaccion.setConcepto((Concepto) Util.buscarElemento(conceptoList, new Concepto(idConceptoSeleccionada)));
				transaccion.setComercioCod(((CodComercio) Util.buscarElemento(codcomercioList, new CodComercio(idCodComercioSeleccionada))));
				if (alta) {
					// Grabo el nuevo objeto
					transaccionService.grabarTransaccion(transaccion);
				} else {
					transaccionService.actualizarTransaccion(transaccion);
				}
				popup.setPopup(popup.ICONO_OK, "El transaccion ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (TransaccionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del transaccion.");
			e1.printStackTrace();
		} catch (TransaccionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del transaccion.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del transaccion.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Transacción";
		popup.borrar();

		transaccion = new Transaccion();
		transaccionList = new ArrayList();

		anulacion = false;
		estadoImpacto = false;
		finalizoOffLine = false;
		formaIngresoTarjeta = false;
		indicadorCaptura = false;
		signo = false;
		tipoCuota = false;
		tipoTerminal = false;
		utilizaPin = false;

		clienteItems = new ArrayList();
		conceptoItems = new ArrayList();
		try {
			clienteList = transaccionesService.getClienteTransaccionService().getCliente(new Filtro());
			clienteItems.clear();
			clienteItems.add(new SelectItem(new Long(0), "Seleccionar Cliente"));
			clienteItems.addAll(TransaccionUtil.armarClienteTransaccion(clienteList));

			conceptoList = transaccionesService.getConceptoService().getConcepto(new Filtro());
			conceptoItems.clear();
			conceptoItems.add(new SelectItem(new Long(0), "Seleccionar Concepto"));
			conceptoItems.addAll(TransaccionUtil.armarConcepto(conceptoList));

			comerciolistaprecioList = transaccionesService.getComercioListaPrecioService().getComercioListaPrecio(new Filtro());
			codcomercioList = transaccionesService.getCodComercioService().getCodComercio(new Filtro());
		} catch (ClienteTransaccionException e1) {
			e1.printStackTrace();
		} catch (ComercioListaPrecioException e1) {
			e1.printStackTrace();
		} catch (ConceptoException e1) {
			e1.printStackTrace();
		} catch (CodComercioException e1) {
			e1.printStackTrace();
		}

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


	public String irANuevoTransaccion() {
		return inicializar();
	}


	public String irAModificarTransaccion() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar transaccion";
		return null;
	}


	public String irAListarTransaccion() {
		borrar();
		tituloCorto = "Listado de transaccion";
		Session.redirect("/tarjetafiel/transacciones/listarTransaccion.jsf");
		return "";
	}


	public String listarTransaccion() {
		transaccionList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (transaccion.getIdTranascciones() != null && !transaccion.getIdTranascciones().equals(new Long(0))) {
				filtro.agregarCampoOperValor("idTranascciones", Filtro.IGUAL, transaccion.getIdTranascciones());
				transaccion.setIdTranascciones(new Long(0));
			}

			log.info("idClienteSeleccionada " + idClienteSeleccionada);
			if (idClienteSeleccionada != null && !idClienteSeleccionada.equals(new Long(0))) {
				filtro.agregarCampoOperValor("clienteTransaccion.idCliente", Filtro.IGUAL, idClienteSeleccionada);
				idClienteSeleccionada = new Long(0);
			}

			if (transaccion.getCodComercio() != null && !transaccion.getCodComercio().equals("")) {
				filtro.agregarCampoOperValor("codComercio", Filtro.IGUAL, transaccion.getCodComercio());
				transaccion.setCodComercio("");
			}

			if (idConceptoSeleccionada != null && !idConceptoSeleccionada.equals(new Long(0))) {
				filtro.agregarCampoOperValor("concepto.idConcepto", Filtro.IGUAL, idConceptoSeleccionada);
				idConceptoSeleccionada = new Long(0);
			}

			if (transaccion.getOrigen() != null && transaccion.getOrigen().getIdOrigenes() != null
					&& !transaccion.getOrigen().getIdOrigenes().equals(new Long(0))) {
				filtro.agregarCampoOperValor("idOrigen", Filtro.IGUAL, transaccion.getOrigen().getIdOrigenes());
				transaccion.getOrigen().setIdOrigenes(new Long(0));
			}

			if (transaccion.getOperador() != null && transaccion.getOperador().getCodigo() != null
					&& !transaccion.getOperador().getCodigo().equals(new Long(0))) {
				filtro.agregarCampoOperValor("idOperador", Filtro.IGUAL, transaccion.getOperador().getCodigo());
				transaccion.getOperador().setCodigo(new Long(0));
			}

			if (finalizoOnLine)
				filtro.agregarCampoOperValor("finalizoOnline", Filtro.IGUAL, "S");

			if (finalizoOffLine)
				filtro.agregarCampoOperValor("finalizoOffline", Filtro.IGUAL, "S");

			if (transaccion.getFechaConciliado() != null && !transaccion.getFechaConciliado().equals(new Timestamp(0))) {
				filtro.agregarCampoOperValor("fechaConciliado", Filtro.IGUAL, Filtro.getTO_DATE(transaccion.getFechaConciliado()));
				transaccion.setFechaConciliado(null);
			}

			if (estadoImpacto)
				filtro.agregarCampoOperValor("estadoImpacto", Filtro.IGUAL, "S");

			if (transaccion.getFechaReal() != null && !transaccion.getFechaReal().equals(new Date(0))) {
				filtro.agregarCampoOperValor("fechaReal", Filtro.IGUAL, Filtro.getTO_DATE(transaccion.getFechaReal()));
				transaccion.setFechaReal(null);
			}

			List auxi = transaccionesService.getTransaccionService().getTransaccion(filtro);
			Iterator iter = auxi.iterator();
			while (iter.hasNext())
			{
				Transaccion element = (Transaccion) iter.next();
				if (element.getClienteTransaccion() != null && element.getClienteTransaccion().getIndividuo() != null) {
					element.getClienteTransaccion().getIndividuo().getApellido();
					element.getClienteTransaccion().getIndividuo().getNombres();
				}
				element.getComercioListaPrecio();
				element.getConcepto();
				if (element.getComercioCod() != null)
					element.getComercioCod().getIdCodComercio();

				TransaccionWrappers wrappers = new TransaccionWrappers(element);
				transaccionList.add(wrappers);
			}

			finalizoOnLine = false;
			finalizoOffLine = false;
			estadoImpacto = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarTransaccion.jsf");
		return null;
	}

	public class TransaccionWrappers {

		private Transaccion transaccion;
		private String fechaConciliado;
		private String fechaReal;
		private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


		public TransaccionWrappers(Transaccion transaccion) {
			super();
			this.transaccion = transaccion;
		}


		public String getFechaConciliado() {
			if (transaccion.getFechaConciliado() != null)
				return format.format(new Date(transaccion.getFechaConciliado().getTime()));

			return "";

		}


		public String getFechaReal() {
			if (transaccion.getFechaReal() != null)
				return format.format(new Date(transaccion.getFechaReal().getTime()));

			return "";
		}


		public Transaccion getTransaccion() {
			return transaccion;
		}


		public void setTransaccion(Transaccion transaccion) {
			this.transaccion = transaccion;
		}
	}
}

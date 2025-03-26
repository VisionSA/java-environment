package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectBooleanCheckbox;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoClienteService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes"})
public class PlasticoClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PlasticoClienteBean.class);
	private PlasticoCliente plasticocliente;
	private Long idPlasticoClienteHidden;

	// definicion de un list del objeto base
	private List plasticoclienteList;

	// Listas para la presentacion(HtmlSelectItems).
	private List clienteList = new ArrayList();
	// private List clienteItems = new ArrayList();
	private List estadoItems = new ArrayList();

	// objetos Date para las fechas que de los calendar popup
	private Date ultimaModificacion;
	private Date fechaDesde;
	private Date fechaHasta;

	// definiciones para el filtro;
	private String estadoFiltro;
	private String idClienteFiltro;
	private String numeroFiltro;
	private String pinFiltro;
	private Date ultimaModificacionFiltro;
	private Date vigenciaDesdeFiltro;
	private Date vigenciaHastaFiltro;
	private boolean incluirFechasEnBusqueda;
	private HtmlSelectBooleanCheckbox bindinFecha;

	// Objetos Relacionados.
	private Long idClienteSeleccionada;

	private String focoHidden;


	public PlasticoClienteBean() {
		super();
		borrar();
		// clienteItems = new ArrayList();
		// try {
		// //clienteList = transaccionesService.getClienteTransaccionService().getCliente(new Filtro());
		// } catch(ClienteTransaccionException e1) {
		// e1.printStackTrace();
		// }
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public PlasticoCliente getPlasticoCliente() {
		return plasticocliente;
	}


	public void setPlasticoCliente(PlasticoCliente plasticocliente) {
		this.plasticocliente = plasticocliente;
	}


	public Long getIdPlasticoClienteHidden() {
		return idPlasticoClienteHidden;
	}


	public void setIdPlasticoClienteHidden(Long idPlasticoClienteHidden) {
		this.idPlasticoClienteHidden = idPlasticoClienteHidden;
	}


	// public List getClienteItems() {
	// return clienteItems;
	// }
	// public void setClienteItems(List clienteItems) {
	// this.clienteItems = clienteItems;
	// }
	public Long getIdClienteSeleccionada() {
		return idClienteSeleccionada;
	}


	public void setIdClienteSeleccionada(Long idClienteSeleccionada) {
		this.idClienteSeleccionada = idClienteSeleccionada;
	}


	public List getPlasticoClienteList() {
		return plasticoclienteList;
	}


	public void setPlasticoClienteList(List object) {
		this.plasticoclienteList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PLASTICOCLIENTE
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
		return "amPlasticoCliente";
	}


	private void cargarItems() {
		// try {
		// clienteList = transaccionesService.getClienteTransaccionService().getCliente(new Filtro());
		// Iterator iter = clienteList.iterator();
		// while(iter.hasNext()) {
		// ClienteTransaccion clie = (ClienteTransaccion)iter.next();
		// clie.getIndividuo().getApellido();
		// clie.getIndividuo().getNombres();
		// }
		// } catch(ClienteTransaccionException e1) {
		// e1.printStackTrace();
		// }
		// if (clienteItems.size() != clienteList.size()) {
		// clienteItems = Util.cargarSelectItem(clienteList);
		// }
		estadoItems = Util.cargarSelectItemMascara(PlasticoCliente.estadoStaticList);
	}


	public String editarPlasticoCliente() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar plasticocliente";
		try {
			plasticocliente = transaccionesService.getPlasticoClienteService().leerPlasticoCliente(idPlasticoClienteHidden);
			if (plasticocliente.getUltimamodif() != null) {
				ultimaModificacion = (Date) plasticocliente.getUltimamodif();
			} else {
				ultimaModificacion = null;
			}
			if (plasticocliente.getVigenciaDesde() != null) {
				fechaDesde = (Date) plasticocliente.getVigenciaDesde();
			} else {
				fechaDesde = null;
			}
			if (plasticocliente.getVigenciaHasta() != null) {
				fechaHasta = (Date) plasticocliente.getVigenciaHasta();
			} else {
				fechaHasta = null;
			}
			result = "amPlasticoCliente";
		} catch (PlasticoClienteException e1) {
			error.agregar("Ocurrio un error al intentar editar el plasticocliente");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarPlasticoCliente.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el plasticocliente");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarPlasticoCliente.jsf");
		}
		return result;
	}


	public String eliminarPlasticoCliente() {
		try {
			transaccionesService.getPlasticoClienteService().borrarPlasticoCliente(idPlasticoClienteHidden);
			plasticoclienteList.remove(new PlasticoCliente(idPlasticoClienteHidden));
		} catch (PlasticoClienteException e1) {
			error.agregar("Imposible borrar el plasticocliente. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el plasticocliente");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarPlasticoCliente.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				PlasticoClienteService plasticoclienteService = transaccionesService.getPlasticoClienteService();
				plasticocliente.setClienteTransaccion((ClienteTransaccion) Util.buscarElemento(clienteList, new ClienteTransaccion(
						idClienteSeleccionada)));
				ClienteTransaccion cli = transaccionesService.getClienteTransaccionService().leerCliente(idClienteSeleccionada);
				plasticocliente.setClienteTransaccion(cli);
				if (ultimaModificacion != null) {
					plasticocliente.setUltimamodif(new Timestamp(ultimaModificacion.getTime()));
				} else {
					plasticocliente.setUltimamodif(null);
				}
				if (fechaDesde != null) {
					plasticocliente.setVigenciaDesde(new Timestamp(fechaDesde.getTime()));
				} else {
					plasticocliente.setVigenciaDesde(null);
				}
				if (fechaHasta != null) {
					plasticocliente.setVigenciaHasta(new Timestamp(fechaHasta.getTime()));
				} else {
					plasticocliente.setVigenciaHasta(null);
				}
				plasticocliente.setOperador(Session.getOperador());

				if (alta) {
					log.info("Alta nuevo plástico....");
					plasticoclienteService.grabarPlasticoCliente(plasticocliente);
				} else {
					plasticoclienteService.actualizarPlasticoCliente(plasticocliente);
				}
				popup.setPopup(popup.ICONO_OK, "El Plástico Cliente ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (PlasticoClienteDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Plástico Cliente.");
			e1.printStackTrace();
		} catch (PlasticoClienteException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Plástico Cliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Plástico Cliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de plasticocliente";
		popup.borrar();
		plasticocliente = new PlasticoCliente();
		plasticoclienteList = new ArrayList();
		fechaDesde = new Date(Calendar.getInstance().getTimeInMillis());
		fechaHasta = new Date(Calendar.getInstance().getTimeInMillis());
		;
		ultimaModificacion = new Date(Calendar.getInstance().getTimeInMillis());

		// estadoFiltro = "H";
		idClienteFiltro = "";
		numeroFiltro = "";
		pinFiltro = "";
		ultimaModificacionFiltro = new Date(Calendar.getInstance().getTimeInMillis());
		Calendar fechaAux = Calendar.getInstance();
		fechaAux.add(Calendar.MONTH, -1);
		vigenciaDesdeFiltro = new Date(fechaAux.getTimeInMillis());
		vigenciaHastaFiltro = new Date(Calendar.getInstance().getTimeInMillis());
		incluirFechasEnBusqueda = false;
		bindinFecha = new HtmlSelectBooleanCheckbox();
		bindinFecha.setValue(new Boolean(false));

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (plasticocliente.getNumero() == null || plasticocliente.getNumero().compareTo("") == 0) {
			error.agregar(Error.TRAN_NUMERO_PLASTICO_INVALIDO);
		}
		if (plasticocliente.getPin() == null) {
			error.agregar(Error.TRAN_PIN_PLASTICO_INVALIDO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoPlasticoCliente() {
		return inicializar();
	}


	public String irAModificarPlasticoCliente() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Plástico Cliente";
		return null;
	}


	public String irAListarPlasticosCliente() {
		borrar();
		tituloCorto = "Listado de Plástico Cliente";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarPlasticoCliente.jsf");
		return "";
	}


	public String listarPlasticoCliente() {
		plasticoclienteList = new ArrayList();
		try {
			incluirFechasEnBusqueda = ((Boolean) bindinFecha.getValue()).booleanValue();
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("estado",Filtro.LIKE , estadoFiltro);
			// if (numeroFiltro!=null&& numeroFiltro.compareTo("")!=0) {
			filtro.agregarCampoOperValor("numero", Filtro.LIKE, numeroFiltro);
			// }
			if (pinFiltro != null && pinFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("pin", Filtro.IGUAL, pinFiltro);
			}
			if (incluirFechasEnBusqueda) {
				// filtro.agregarCampoOperValor(arg0, arg1, arg2);
				// filtro.agregarCampoOperValor(arg0, arg1, arg2);
			}
			plasticoclienteList = transaccionesService.getPlasticoClienteService().getPlasticoCliente(filtro);
			log.info("La cantidad de plasticos es: " + plasticoclienteList.size());
			Iterator iter = plasticoclienteList.iterator();
			while (iter.hasNext())
			{
				PlasticoCliente element = (PlasticoCliente) iter.next();
				element.getClienteTransaccion().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarPlasticoCliente.jsf");
		return null;
	}


	public List getEstadoItems() {
		return estadoItems;
	}


	public void setEstadoItems(List estadoItems) {
		this.estadoItems = estadoItems;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}


	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}


	public String getEstadoFiltro() {
		return estadoFiltro;
	}


	public void setEstadoFiltro(String estadoFiltro) {
		this.estadoFiltro = estadoFiltro;
	}


	public String getIdClienteFiltro() {
		return idClienteFiltro;
	}


	public void setIdClienteFiltro(String idClienteFiltro) {
		this.idClienteFiltro = idClienteFiltro;
	}


	public String getNumeroFiltro() {
		return numeroFiltro;
	}


	public void setNumeroFiltro(String numeroFiltro) {
		this.numeroFiltro = numeroFiltro;
	}


	public String getPinFiltro() {
		return pinFiltro;
	}


	public void setPinFiltro(String pinFiltro) {
		this.pinFiltro = pinFiltro;
	}


	public Date getUltimaModificacionFiltro() {
		return ultimaModificacionFiltro;
	}


	public void setUltimaModificacionFiltro(Date ultimaModificacionFiltro) {
		this.ultimaModificacionFiltro = ultimaModificacionFiltro;
	}


	public Date getVigenciaDesdeFiltro() {
		return vigenciaDesdeFiltro;
	}


	public void setVigenciaDesdeFiltro(Date vigenciaDesdeFiltro) {
		this.vigenciaDesdeFiltro = vigenciaDesdeFiltro;
	}


	public Date getVigenciaHastaFiltro() {
		return vigenciaHastaFiltro;
	}


	public void setVigenciaHastaFiltro(Date vigenciaHastaFiltro) {
		this.vigenciaHastaFiltro = vigenciaHastaFiltro;
	}


	public List getClienteList() {
		return clienteList;
	}


	public void setClienteList(List clienteList) {
		this.clienteList = clienteList;
	}


	public PlasticoCliente getPlasticocliente() {
		return plasticocliente;
	}


	public void setPlasticocliente(PlasticoCliente plasticocliente) {
		this.plasticocliente = plasticocliente;
	}


	public List getPlasticoclienteList() {
		return plasticoclienteList;
	}


	public void setPlasticoclienteList(List plasticoclienteList) {
		this.plasticoclienteList = plasticoclienteList;
	}


	public HtmlSelectBooleanCheckbox getBindinFecha() {
		return bindinFecha;
	}


	public void setBindinFecha(HtmlSelectBooleanCheckbox bindinFecha) {
		this.bindinFecha = bindinFecha;
	}


	public boolean isIncluirFechasEnBusqueda() {
		return incluirFechasEnBusqueda;
	}


	public void setIncluirFechasEnBusqueda(boolean incluirFechasEnBusqueda) {
		this.incluirFechasEnBusqueda = incluirFechasEnBusqueda;
	}
}

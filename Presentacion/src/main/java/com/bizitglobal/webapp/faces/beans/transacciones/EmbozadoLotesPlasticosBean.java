package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.Util;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoLoteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.util.PDFUtil;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @id 4801
 * @author José Casalis. Bizit Global - Año 2012
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class EmbozadoLotesPlasticosBean extends BaseBean {

	public static String EMBOZADO_PLASTICOS_LOTES = "embozadoLotesPlasticos";
	private static final Logger log = Logger.getLogger(EmbozadoLotesPlasticosBean.class);

	private Boolean mostrarLotesPendEmbozar = false;
	private List<WrapperPlasticoLote> lotesPendEmbozarList = null;
	private Integer cantidadLotesPendientes = 0;

	private Boolean mostrarLotesErroneos = false;
	private List<WrapperPlasticoLote> lotesErroneosList = null;
	private Integer cantidadLotesErroneos = 0;

	private Boolean mostrarLotesProcesados = false;
	private List<WrapperPlasticoLote> lotesProcesadosList = null;
	private Integer cantidadLotesProcesados = 0;

	private Long idArchivoEmbozoHidden;
	private Long idArchivoAcuseHidden;
	private Long idLoteProcesar;

	private List plasticosLugares;

	/** Página de plasticos incluidos en lotes **/
	private static String TRATAR_PLASTICOS_DE_LOTES = "tratarPlasticosDeLotes";

	private Long loteSeleccionado;
	private String tituloCortoPlasticos;

	private List lugaresList;
	private Long estadoSeleccionado;

	private String mensajeConfPlastico;

	private Long codOperacionTxt;
	private Long codLoteTxt;
	private Long codCuentaTxt;
	private Long codVerificacionTxt;

	private Boolean mostrarHtmlConfLotes = false;
	private String nroPlasticoRegenerar;


	/*********************************************/
	public EmbozadoLotesPlasticosBean() {
		super();
		// borrar();
		inicializar();
	}


	public String irAContinuar() {
		limpiarMensajeExito();
		// borrar();
		return EMBOZADO_PLASTICOS_LOTES;
	}


	private void limpiarMensajeExito() {
		popup.borrar();
	}


	@Override
	public void borrar() {
		limpiarMensajeExito();
		error.borrar();
		tituloCorto = "Embozado de Lotes de Plasticos";
		tituloLargo = "TARJETA FIEL";
		tituloCortoPlasticos = "Confirmacion de Plasticos";
		mostrarLotesPendEmbozar = false;
		mostrarLotesErroneos = false;
		mostrarLotesProcesados = false;
		cantidadLotesErroneos = 0;
		cantidadLotesPendientes = 0;
		cantidadLotesProcesados = 0;
	}


	@Override
	public boolean validar() {
		return false;
	}


	@Override
	public String inicializar() {
		borrarBaseBean();
		borrar();

		buscarLotesEnGeneracion();

		buscarLotesPendienteProcesar();

		buscarLotesProcesados();

		try {
			Filtro filtroLugares = new Filtro();
			filtroLugares.agregarCampoOperValor("accion", Filtro.IGUAL, PlasticoLugar.CONFIRMA);
			filtroLugares.agregarCampoOperValor("idPlasticoLugar", Filtro.IGUAL, PlasticoLugar.CASACENTRAL);
			plasticosLugares = transaccionesService.getPlasticoLugarService().listar(filtroLugares);
			lugaresList = com.bizitglobal.webapp.faces.util.Util.cargarSelectItem(plasticosLugares);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("No se pueden leer los lugares de los plasticos");
			error.agregar("Error al intentar recuperar los lugares disponibles para los plásticos");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		return EMBOZADO_PLASTICOS_LOTES;
	}


	public void volverPaginaLotes() {
		buscarLotesEnGeneracion();
		buscarLotesPendienteProcesar();
		buscarLotesProcesados();
		this.loteSeleccionado = null;
		this.nroPlasticoRegenerar = null;
		limpiarCamposCodPopUp();
		Session.redirect("/tarjetafiel/transacciones/embozadoLotesPlasticos.jsf");
	}


	/**
	 * @id: Method: buscarLotesPendienteProcesar Description:
	 */
	private void buscarLotesPendienteProcesar() {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("estadoLote.idPlastLoteEstado", Filtro.IGUAL, EstadoLote.PENDIENTE_PROCESAR);
		List<PlasticoLote> tmp = transaccionesService.getPlasticoLoteService().listarLotes(filtro);
		lotesPendEmbozarList = new ArrayList<WrapperPlasticoLote>();
		if (tmp.isEmpty()) {
			return;
		}
		Iterator<PlasticoLote> loteIt = tmp.iterator();
		while (loteIt.hasNext()) {
			WrapperPlasticoLote wraPlaLote = new WrapperPlasticoLote(loteIt.next());
			lotesPendEmbozarList.add(wraPlaLote);
		}

		if (!lotesPendEmbozarList.isEmpty()) {
			mostrarLotesPendEmbozar = true;
			cantidadLotesPendientes = lotesPendEmbozarList.size();
		}
	}


	/**
	 * @id: Method: buscarLotesEnGeneracion Description:
	 */
	private void buscarLotesEnGeneracion() {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("estadoLote.idPlastLoteEstado", Filtro.IGUAL, EstadoLote.EN_GENERACION);
		List<PlasticoLote> tmp = transaccionesService.getPlasticoLoteService().listarLotes(filtro);
		lotesErroneosList = new ArrayList<WrapperPlasticoLote>();
		if (tmp.isEmpty()) {
			return;
		}
		Iterator<PlasticoLote> loteIt = tmp.iterator();
		while (loteIt.hasNext()) {
			WrapperPlasticoLote wraPlaLote = new WrapperPlasticoLote(loteIt.next());
			lotesErroneosList.add(wraPlaLote);
		}
		if (!lotesErroneosList.isEmpty()) {
			mostrarLotesErroneos = true;
			cantidadLotesErroneos = lotesErroneosList.size();
		}
	}


	private void buscarLotesProcesados() {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("estadoLote.idPlastLoteEstado", Filtro.IGUAL, EstadoLote.PROCESADO);
		List<PlasticoLote> tmp = transaccionesService.getPlasticoLoteService().listarLotes(filtro);
		lotesProcesadosList = new ArrayList<WrapperPlasticoLote>();
		if (tmp.isEmpty()) {
			return;
		}
		Iterator<PlasticoLote> loteIt = tmp.iterator();
		while (loteIt.hasNext()) {
			WrapperPlasticoLote wraPlaLote = new WrapperPlasticoLote(loteIt.next());
			lotesProcesadosList.add(wraPlaLote);
		}
		if (!lotesProcesadosList.isEmpty()) {
			mostrarLotesProcesados = true;
			cantidadLotesProcesados = lotesProcesadosList.size();
		}
	}


	public String verArchivoEmbozo(String archivo) {
		error.borrar();
		if ("".equals(archivo)) {
			log.error("No se encuentra el lote para obtener el archivo");
			error.agregar("No se encuentra el lote para obtener el archivo");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		String url;
		try {
			url = getServerUrl();
		} catch (Exception e) {
			log.error("No se puede encontrar la url de la aplicacion");
			error.agregar("Error al intentar obtener la ruta de la aplicacion");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		String serverFileFolder = "";
		try {
			serverFileFolder = getServerFileFolder(serverFileFolder);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("No se puede encontrar la url del contenedor de archivos del servidor");
			error.agregar("Error al intentar obtener la ruta del contenedor de archivos del servidor");
			return EMBOZADO_PLASTICOS_LOTES;
		}
		ejecutarJavaScript("window.open('http://" + url + serverFileFolder + archivo + "','popupEmbozo','left=20,top=20, width=1000,height=400')");// popup('http://"
																																					// +
																																					// url
																																					// +
																																					// "/archivos"
																																					// +
																																					// archivo
																																					// +
																																					// "',700,400);");

		return EMBOZADO_PLASTICOS_LOTES;
	}


	public String verArchivoAcuse(String archivo) {
		error.borrar();

		if ("".equals(archivo)) {
			log.error("No se encuentra el lote para obtener el archivo");
			error.agregar("No se encuentra el lote para obtener el archivo");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		String url;
		try {
			url = getServerUrl();
		} catch (Exception e) {
			log.error("No se puede encontrar la url de la aplicacion");
			error.agregar("Error al intentar obtener la ruta de la aplicacion");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		String serverFileFolder = "";
		try {
			serverFileFolder = getServerFileFolder(serverFileFolder);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("No se puede encontrar la url del contenedor de archivos del servidor");
			error.agregar("Error al intentar obtener la ruta del contenedor de archivos del servidor");
			return EMBOZADO_PLASTICOS_LOTES;
		}
		ejecutarJavaScript("window.open('http://" + url + serverFileFolder + archivo + "','popupAcuse','left=80,top=80, width=800,height=400')");// popup('http://"

		return EMBOZADO_PLASTICOS_LOTES;
	}


	/**
	 * @id: Method: getServerFileFolder Description:
	 * @param serverFileFolder
	 * @return
	 * @throws IOException
	 */
	private String getServerFileFolder(String serverFileFolder) throws IOException {
		serverFileFolder = ContextoProperties.getProperty("directorioArchivos");

		serverFileFolder = serverFileFolder.replace("/webapps", "");
		return serverFileFolder;
	}


	/**
	 * @id: Method: getServerUrl Description:
	 * @return
	 * @throws Exception
	 */
	private String getServerUrl() throws Exception {
		String url = "";
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request instanceof HttpServletRequest)
		{
			url = ((HttpServletRequest) request).getServerName() + ":" + ((HttpServletRequest) request).getServerPort();
		} else
		{
			throw new Exception("Error al intentar obtener la ruta de la aplicacion");
		}
		return url;
	}


	@SuppressWarnings("unchecked")
	public String procesarLote(Long idLoteProcesar) {
		PlasticoLote loteProcesar = transaccionesService.getPlasticoLoteService().leerLote(idLoteProcesar);
		EstadoLote estadoLote = new EstadoLote();
		estadoLote.setIdPlastLoteEstado(Long.parseLong(EstadoLote.PROCESADO));

		if (loteProcesar == null) {
			error.agregar("No se encuentra el lote para procesar");
			return EMBOZADO_PLASTICOS_LOTES;
		}

		loteProcesar.setEstadoLote(estadoLote);
		loteProcesar.setFechaEmbozo(new Date());
		
		transaccionesService.getPlasticoLoteService().modificarPlasticoLote(loteProcesar);

		//verArchivoEmbozo(loteProcesar.getArchivoEmbozadoraNew());
		//verArchivoEmbozo(loteProcesar.getArchivoEmbozadora());
		//verArchivoAcuse(loteProcesar.getArchivoAcuses());

		popup.setPopup(Popup.ICONO_OK, "El lote " + loteProcesar.getNumeroLoteFormateado() + " se proceso con exito.");
		popup.setMostrar(true);

		buscarLotesEnGeneracion();
		buscarLotesPendienteProcesar();
		buscarLotesProcesados();

		return EMBOZADO_PLASTICOS_LOTES;
	}


	public String verPlasticosLote() {
		StringBuffer javascript = new StringBuffer();
		javascript.append("var popupPlasticos;");
		Session.redirect("/tarjetafiel/transacciones/tratarPlasticosDeLotes.jsf");
		mostrarHtmlConfLotes = false;
		ejecutarJavaScript(javascript.toString());

		return EMBOZADO_PLASTICOS_LOTES;

		// String codigo = Session.getOperador().getCodigo().toString();
		// String rol = Session.getOperador().getRol().getIdRol().toString().trim();
		// if (!(codigo.equals("23")) && !(rol.equals("20")) ) {
		// ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
		// ejecutarJavaScript("popup('"+ "PlasticosVerFiel.swf?codigoOperador="+codigo+"',window.screen.width,window.screen.height);");
		// }
		//
		// return EMBOZADO_PLASTICOS_LOTES;
	}


	public String regenerarArchivoEmbozo() {

		WrapperHTML html = new WrapperHTML();
		html.borrar();
		html.setPlasticoOk(true);

		mostrarHtmlConfLotes = false;
		if (nroPlasticoRegenerar == null || "".equals(nroPlasticoRegenerar)) {
			setMensajeErrorHtml(html, "Debe ingresar un plasico a regenerar");
			return TRATAR_PLASTICOS_DE_LOTES;
		}

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("numero", Filtro.IGUAL, nroPlasticoRegenerar);
		filtro.agregarCampoOperValor("plasticoLote.idPlastLote", Filtro.IGUAL, loteSeleccionado);
		filtro.agregarCampoOperValor("plasticoLugar.idPlasticoLugar", Filtro.IGUAL, 9L);
		List<PlasticoCliente> lstPlastico = null;
		try {
			lstPlastico = transaccionesService.getPlasticoClienteService().getPlasticoCliente(filtro);
		} catch (PlasticoClienteException e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al intentar recuperar el plastico");
			return TRATAR_PLASTICOS_DE_LOTES;
		}
		if (lstPlastico.isEmpty()) {
			setMensajeErrorHtml(html, "No se puede encontrar el plástico para regenerar");
			return TRATAR_PLASTICOS_DE_LOTES;
		}

		PlasticoLote lote = new PlasticoLote();// transaccionesService.getPlasticoLoteService().leerLote(loteSeleccionado);

		PlasticoCliente plastico = lstPlastico.get(0);
		lote.getPlasticos().add(plastico);
		String arcEmbozo = null;
		try {
			arcEmbozo = transaccionesService.getPlasticoLoteService().regenerarArchivoEmbozo(lote, Session.getOperador());
			arcEmbozo = arcEmbozo.replace(ContextoProperties.catalinaHome, "").replace("\\", "/")
					.replace(ContextoProperties.getProperty("directorioArchivos"), "");
			arcEmbozo = "http://" + getServerUrl() + getServerFileFolder("") + arcEmbozo;
		} catch (PlasticoLoteException e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al regenerar el archivo");
			return TRATAR_PLASTICOS_DE_LOTES;
		} catch (IOException e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al formatear el archivo");
			return TRATAR_PLASTICOS_DE_LOTES;
		} catch (Exception e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al formatear el archivo");
			return TRATAR_PLASTICOS_DE_LOTES;
		}

		ejecutarJavaScript("window.open('" + arcEmbozo + "','popupAcuse','width=800,height=400')");

		nroPlasticoRegenerar = null;

		return TRATAR_PLASTICOS_DE_LOTES;
	}


	public String confirmarPlastico() {
		WrapperHTML html = new WrapperHTML();
		html.borrar();
		html.setPlasticoOk(true);
		ClienteTransaccion cliente = null;
		mostrarHtmlConfLotes = true;

		if (codCuentaTxt == null ||
				codLoteTxt == null ||
				codOperacionTxt == null ||
				codVerificacionTxt == null) {

			setMensajeErrorHtml(html, "Debe ingresar todos los datos para poder activar un plastico");
			return TRATAR_PLASTICOS_DE_LOTES;
		}

		try {

			String valorParaVerificar;
			try {
				valorParaVerificar = Convertidores.completarIzquierda(codOperacionTxt.toString(), '0', 2)
						+ Convertidores.completarIzquierda(codLoteTxt.toString(), '0', 6)
						+ Convertidores.completarIzquierda(codCuentaTxt.toString(), '0', 6);
			} catch (Exception e) {
				setMensajeErrorHtml(html, "Error al intentar verificar el digito");
				return TRATAR_PLASTICOS_DE_LOTES;
			}
			String digito = Util.generarDigitoVerificadorCB(valorParaVerificar);
			if (!digito.equals(codVerificacionTxt.toString())) {
				setMensajeErrorHtml(html, "No coincide el digito verificador con los demas digitos ingresados, por favor controle los numeros.");
				return TRATAR_PLASTICOS_DE_LOTES;
			}

			if (!loteSeleccionado.equals(codLoteTxt)) {
				setMensajeErrorHtml(html, "No coincide el lote ingresado con el seleccionado.");
				return TRATAR_PLASTICOS_DE_LOTES;
			}

			cliente = transaccionesService.getClienteTransaccionService().leerCliente(codCuentaTxt); // siempre el cliente titular de la cuenta nro
																										// codCuentaTxt

			if (cliente == null) {
				setMensajeErrorHtml(html, "No se encuentra la cuenta ingresada. Por favor, verifique los datos");
				return TRATAR_PLASTICOS_DE_LOTES;
			}
			html.setCuenta(codCuentaTxt.toString());
			html.setNroOperacion(codOperacionTxt.toString());
			html.setTitular(cliente.getNombreCliente());
			html.setLote(this.codLoteTxt.toString());

			List<PlasticoCliente> plastico = transaccionesService.getPlasticoClienteService().leerPlasticoClienteConfirmar(codCuentaTxt, codLoteTxt,
					codOperacionTxt);
			if (plastico.isEmpty()) {
				setMensajeErrorHtml(html, "No se encuentran plasticos pendientes de confirmar para esta cuenta");
				return TRATAR_PLASTICOS_DE_LOTES;
			}
			PlasticoLugar lugarCambiado = (PlasticoLugar) com.bizitglobal.webapp.faces.util.Util.buscarElemento(plasticosLugares, new PlasticoLugar(
					this.estadoSeleccionado));
			Iterator<PlasticoCliente> plasIt = plastico.iterator();
			while (plasIt.hasNext()) {
				PlasticoCliente plasticoCambiar = plasIt.next();
				plasticoCambiar.setPlasticoLugar(lugarCambiado);
				plasticoCambiar.setFechaEmbozo(new Date());
				transaccionesService.getPlasticoClienteService().guardarHistorialPlastico(plasticoCambiar, Session.getOperador());
				// transaccionesService.getPlasticoClienteService().actualizarPlasticoCliente(plasticoCambiar);
				log.info("se actualizó el plastico");
			}
			transaccionesService.getPlasticoClienteService().actualizarPlasticoCliente(plastico);
			html.setCantidadPlasticos(String.valueOf(plastico.size()));
			html.setLugar(lugarCambiado.getDescripcion());
			log.info("Se leyeron los plasticos");

			// Controla si el lote tiene plasticos para seguir confirmando
			Long cantPlasticosRestantes = transaccionesService.getPlasticoLoteService().verificarPlasticosPendConfirmar(codLoteTxt);
			log.info("Quedan " + cantPlasticosRestantes + " plasticos pendientes de confirmar en el lote " + codLoteTxt);
			if (new Long(0).equals(cantPlasticosRestantes)) {
				// cierra el lote
				EstadoLote estadoLote = new EstadoLote(Long.parseLong(EstadoLote.CERRADO), "Cerrado");
				PlasticoLote lote = transaccionesService.getPlasticoLoteService().leerLote(codLoteTxt);
				if (null != lote) {
					lote.setEstadoLote(estadoLote);
					lote.setFechaConfirmacion(new Date());
					transaccionesService.getPlasticoLoteService().modificarPlasticoLote(lote);
					log.info("Se cerró el lote " + lote.getIdPlastLote());
					popup.setPopup(Popup.ICONO_OK, "El lote " + Convertidores.completarIzquierda(lote.getIdPlastLote().toString(), '0', 6)
							+ " se cerró correctamente. Todos los plasticos del mismo se encuentran confirmados");
					popup.setMostrar(true);
					volverPaginaLotes();
				}
			}

		} catch (ClienteTransaccionException e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al intentar recuperar los datos de la cuenta");
		} catch (PlasticoClienteException e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al intentar actualizar los plásticos");
		} catch (Exception e) {
			e.printStackTrace();
			setMensajeErrorHtml(html, "Error al guardar el historial de un plástico, vuelva a intentarlo");
		}

		limpiarCamposCodPopUp();

		mensajeConfPlastico = html.getPlasticoOk() ? html.getHTML() : html.getErrorHtml();
		return TRATAR_PLASTICOS_DE_LOTES;
	}


	private void setMensajeErrorHtml(WrapperHTML html, String error) {
		log.error(error);
		html.setErrorMsg(error);
		html.setPlasticoOk(false);
		limpiarCamposCodPopUp();
		mostrarHtmlConfLotes = true;
		mensajeConfPlastico = html.getErrorHtml();
	}


	private void limpiarCamposCodPopUp() {
		this.codCuentaTxt = null;
		this.codLoteTxt = null;
		this.codOperacionTxt = null;
		this.codVerificacionTxt = null;
	}


	public List<WrapperPlasticoLote> getLotesPendEmbozarList() {
		return lotesPendEmbozarList;
	}


	public void setLotesPendEmbozarList(List<WrapperPlasticoLote> lotesPendEmbozarList) {
		this.lotesPendEmbozarList = lotesPendEmbozarList;
	}


	public Boolean getMostrarLotesPendEmbozar() {
		return mostrarLotesPendEmbozar;
	}


	public void setMostrarLotesPendEmbozar(Boolean mostrarLotesPendEmbozar) {
		this.mostrarLotesPendEmbozar = mostrarLotesPendEmbozar;
	}


	public Boolean getMostrarLotesErroneos() {
		return mostrarLotesErroneos;
	}


	public void setMostrarLotesErroneos(Boolean mostrarLotesErroneos) {
		this.mostrarLotesErroneos = mostrarLotesErroneos;
	}


	public List<WrapperPlasticoLote> getLotesErroneosList() {
		return lotesErroneosList;
	}


	public void setLotesErroneosList(List<WrapperPlasticoLote> lotesErroneosList) {
		this.lotesErroneosList = lotesErroneosList;
	}


	public Integer getCantidadLotesPendientes() {
		return cantidadLotesPendientes;
	}


	public void setCantidadLotesPendientes(Integer cantidadLotesPendientes) {
		this.cantidadLotesPendientes = cantidadLotesPendientes;
	}


	public Integer getCantidadLotesErroneos() {
		return cantidadLotesErroneos;
	}


	public void setCantidadLotesErroneos(Integer cantidadLotesErroneos) {
		this.cantidadLotesErroneos = cantidadLotesErroneos;
	}


	public Long getIdArchivoEmbozoHidden() {
		return idArchivoEmbozoHidden;
	}


	public void setIdArchivoEmbozoHidden(Long idArchivoEmbozoHidden) {
		this.idArchivoEmbozoHidden = idArchivoEmbozoHidden;
	}


	public Long getIdArchivoAcuseHidden() {
		return idArchivoAcuseHidden;
	}


	public void setIdArchivoAcuseHidden(Long idArchivoAcuseHidden) {
		this.idArchivoAcuseHidden = idArchivoAcuseHidden;
	}


	public Long getIdLoteProcesar() {
		return idLoteProcesar;
	}


	public void setIdLoteProcesar(Long idLoteProcesar) {
		this.idLoteProcesar = idLoteProcesar;
	}


	public Boolean getMostrarLotesProcesados() {
		return mostrarLotesProcesados;
	}


	public void setMostrarLotesProcesados(Boolean mostrarLotesProcesados) {
		this.mostrarLotesProcesados = mostrarLotesProcesados;
	}


	public List<WrapperPlasticoLote> getLotesProcesadosList() {
		return lotesProcesadosList;
	}


	public void setLotesProcesadosList(List<WrapperPlasticoLote> lotesProcesadosList) {
		this.lotesProcesadosList = lotesProcesadosList;
	}


	public Integer getCantidadLotesProcesados() {
		return cantidadLotesProcesados;
	}


	public void setCantidadLotesProcesados(Integer cantidadLotesProcesados) {
		this.cantidadLotesProcesados = cantidadLotesProcesados;
	}


	public Long getLoteSeleccionado() {
		return loteSeleccionado;
	}


	public void setLoteSeleccionado(Long loteSeleccionado) {
		this.loteSeleccionado = loteSeleccionado;
	}


	public String getTituloCortoPlasticos() {
		return tituloCortoPlasticos;
	}


	public void setTituloCortoPlasticos(String tituloCortoPlasticos) {
		this.tituloCortoPlasticos = tituloCortoPlasticos;
	}


	public List getLugaresList() {
		return lugaresList;
	}


	public void setLugaresList(List lugaresList) {
		this.lugaresList = lugaresList;
	}


	public Long getEstadoSeleccionado() {
		return estadoSeleccionado;
	}


	public void setEstadoSeleccionado(Long estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}


	public String getMensajeConfPlastico() {
		return mensajeConfPlastico;
	}


	public void setMensajeConfPlastico(String mensajeConfPlastico) {
		this.mensajeConfPlastico = mensajeConfPlastico;
	}


	public Long getCodOperacionTxt() {
		return codOperacionTxt;
	}


	public void setCodOperacionTxt(Long codOperacionTxt) {
		this.codOperacionTxt = codOperacionTxt;
	}


	public Long getCodLoteTxt() {
		return codLoteTxt;
	}


	public void setCodLoteTxt(Long codLoteTxt) {
		this.codLoteTxt = codLoteTxt;
	}


	public Long getCodCuentaTxt() {
		return codCuentaTxt;
	}


	public void setCodCuentaTxt(Long codCuentaTxt) {
		this.codCuentaTxt = codCuentaTxt;
	}


	public Long getCodVerificacionTxt() {
		return codVerificacionTxt;
	}


	public void setCodVerificacionTxt(Long codVerificacionTxt) {
		this.codVerificacionTxt = codVerificacionTxt;
	}


	public Boolean getMostrarHtmlConfLotes() {
		return mostrarHtmlConfLotes;
	}


	public void setMostrarHtmlConfLotes(Boolean mostrarHtmlConfLotes) {
		this.mostrarHtmlConfLotes = mostrarHtmlConfLotes;
	}


	public String getNroPlasticoRegenerar() {
		return nroPlasticoRegenerar;
	}


	public void setNroPlasticoRegenerar(String nroPlasticoRegenerar) {
		this.nroPlasticoRegenerar = nroPlasticoRegenerar;
	}


	public String getNroLoteFormateado() {
		try {
			return Convertidores.completarIzquierda(loteSeleccionado.toString(), '0', 6);
		} catch (Exception e) {
			return loteSeleccionado.toString();
		}
	}

	/**************************************************************************************************************************************
	 * Wrapper Para las grillas
	 * 
	 * @id 4801
	 * @author José Casalis. Bizit Global - Año 2012
	 */
	public class WrapperPlasticoLote {
		private PlasticoLote plasticoLote;


		public WrapperPlasticoLote(PlasticoLote plasticoLote) {
			this.plasticoLote = plasticoLote;
		}


		public String verArchivoEmbozoLote() {
			return verArchivoEmbozo(plasticoLote.getArchivoEmbozadora());
		}


		public String verArchivoEmbozoLoteNew() {
			return verArchivoEmbozo(plasticoLote.getArchivoEmbozadoraNew());
		}


		public String verArchivoAcuseLote() {
			return verArchivoAcuse(plasticoLote.getArchivoAcuses());
		}
		
		
		
		public String verArchivoEtiqueta() {
			if(plasticoLote.getArchivoEtiquetas() == null){
				try {
					plasticoLote.setArchivoEtiquetas(PDFUtil.generarPDFEtiquetasPlasticos(plasticoLote.getIdPlastLote()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				transaccionesService.getPlasticoLoteService().modificarPlasticoLote(plasticoLote);
			}
			return verArchivoAcuse(plasticoLote.getArchivoEtiquetas());
		}


		public String procesarLoteWrapper() {
			return procesarLote(plasticoLote.getIdPlastLote());
		}

		

		public String verPlasticosWrapper() {
			// loteSeleccionado = transaccionesService.getPlasticoLoteService().leerLoteCompleto(this.plasticoLote.getIdPlastLote());
			// loteSeleccionado.getPlasticos();
			loteSeleccionado = this.plasticoLote.getIdPlastLote();
			verPlasticosLote();
			return EMBOZADO_PLASTICOS_LOTES;

		}


		public PlasticoLote getPlasticoLote() {
			return plasticoLote;
		}


		public void setPlasticoLote(PlasticoLote plasticoLote) {
			this.plasticoLote = plasticoLote;
		}

	}

	/**************************************************************************************************************************************
	 * Wrapper Para generar objeto HTML
	 * 
	 * @id 4801 Bizit Global - Año 2012
	 */
	public class WrapperHTML {
		private String titular;
		private String cuenta;
		private String nroOperacion;
		private String numeroPlastico;
		private String cantidadPlasticos;
		private String lugar;
		private String lote;

		private String errorMsg;
		private Boolean plasticoOk = false;


		public String getHTML() {
			StringBuffer html = new StringBuffer();
			html.append("<div><table border='1' cellpadding='1' cellspacing='1' style='width: 500px; '>");
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td>");
			html.append("<p style='text-align: center; '>");
			html.append("<strong><span style='font-size:22px;'><span style='color: rgb(0, 100, 0); '><span>Se confirm&oacute; la cuenta N&ordm; "
					+ this.cuenta + " Operacion " + this.nroOperacion + " &nbsp;</span></span></span></strong></p>");
			html.append("<p style='text-align: center; '>");
			html.append("<strong>Cliente Titular</strong>: " + this.titular + " </p>");

			html.append("<p style='text-align: center; '>");
			html.append("<strong>Lote: </strong>&nbsp;" + this.lote + "</p>");

			html.append("<p style='text-align: center; '>");
			html.append("<strong>Cant Plasticos: </strong>&nbsp;" + this.cantidadPlasticos + "</p>");
			html.append("<p style='text-align: center; '>");
			html.append("<b>Lugar: </b>" + this.lugar + "</p>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
			html.append("</table></div>");

			return html.toString();
		}


		public String getErrorHtml() {
			StringBuffer html = new StringBuffer();
			html.append("<table border='1' cellpadding='1' cellspacing='1' style='width: 500px; '>");
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td>");
			html.append("<p dir='ltr' style='text-align: center; '>");
			html.append("<span style='color:#ff0000;'><strong><span style='font-size:22px;'><em>" + this.errorMsg
					+ "</em></span></strong></span></p>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
			html.append("</table>");

			return html.toString();
		}


		private void borrar() {
			titular = "";
			cuenta = "";
			nroOperacion = "";
			numeroPlastico = "";
			cantidadPlasticos = "";
			lugar = "";
			lote = "";
		}


		public String getTitular() {
			return titular;
		}


		public void setTitular(String titular) {
			this.titular = titular;
		}


		public String getCuenta() {
			return cuenta;
		}


		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}


		public String getNroOperacion() {
			return nroOperacion;
		}


		public void setNroOperacion(String nroOperacion) {
			this.nroOperacion = nroOperacion;
		}


		public String getNumeroPlastico() {
			return numeroPlastico;
		}


		public void setNumeroPlastico(String numeroPlastico) {
			this.numeroPlastico = numeroPlastico;
		}


		public String getCantidadPlasticos() {
			return cantidadPlasticos;
		}


		public void setCantidadPlasticos(String cantidadPlasticos) {
			this.cantidadPlasticos = cantidadPlasticos;
		}


		public String getLugar() {
			return lugar;
		}


		public void setLugar(String lugar) {
			this.lugar = lugar;
		}


		public String getErrorMsg() {
			return errorMsg;
		}


		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}


		public Boolean getPlasticoOk() {
			return plasticoOk;
		}


		public void setPlasticoOk(Boolean plasticoOk) {
			this.plasticoOk = plasticoOk;
		}


		public String getLote() {
			return lote;
		}


		public void setLote(String lote) {
			this.lote = lote;
		}

	}

}

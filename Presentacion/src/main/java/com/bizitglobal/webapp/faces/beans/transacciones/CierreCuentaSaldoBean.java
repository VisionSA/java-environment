/*@I4629*/package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
/*@I4629*/
/*@F4629*/
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.transacciones.exception.CierreCuentaClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
/*@I4629*/
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteException;
/*@F4629*/
import com.bizitglobal.tarjetafiel.transacciones.exception.MotivoCierreCuentaClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CierreCuentaCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.MotivoCierreCuentaCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CierreCuentaSaldoBean extends BaseBean {

	private static final Logger log = Logger.getLogger(CambioDiaCierreClienteBean.class);
	private ClienteTransaccion titular;
	private List<Adicional> adicionales; // adicional clase interna
	private boolean mostrarDatos;
	private Integer modo; // para controlar la interfaz a mostrar segun el operador

	private List adic; // lista de objetos ClienteTransacciones que se corresponde con los adicionales del titular

	private Long nroCuenta;
	private Double saldo;
	private String documento;
	private String plastico;
	private String plasticosTitular;

	// atributos para el formulario de cierre//
	private boolean mostrarFormulario;
	private boolean mostrarItems;
	private List listaMotivosList;
	private List<SelectItem> listaMotivosItem = new ArrayList<SelectItem>();
	private CierreCuentaCliente cierreCuentaCliente;
	private Long idMotivoCierre;
	private List<PlasticoLugar> listPlasticoLugar;
	private HtmlSelectOneMenu motivoHtml = new HtmlSelectOneMenu();
	private String descripcionLugar;
	
	private List<Adjunto> listAdjuntos;
	private UploadedFile imagen;
	private String popupReport;
	private List<String> mensajesError;
	private boolean origen;
	LiquidarSaldo0Bean liq;



	public String getPopupReport() {
		String res = popupReport;
		popupReport = null;
		return res;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public UploadedFile getImagen() {
		return imagen;
	}


	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}


	public List<Adjunto> getListAdjuntos() {
		return listAdjuntos;
	}


	public void setListAdjuntos(List<Adjunto> listAdjuntos) {
		this.listAdjuntos = listAdjuntos;
	}


	public ClienteTransaccion getTitular() {
		return titular;
	}


	public void setTitular(ClienteTransaccion titular) {
		this.titular = titular;
	}


	public List getAdicionales() {
		return adicionales;
	}


	public void setAdicionales(List<Adicional> adicionales) {
		this.adicionales = adicionales;
	}


	public boolean getMostrarDatos() {
		return mostrarDatos;
	}


	public void setMostrarDatos(boolean mostrarDatos) {
		this.mostrarDatos = mostrarDatos;
	}


	public Long getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public String getPlasticosTitular() {
		return plasticosTitular;
	}


	public void setPlasticosTitular(String plasticosTitular) {
		this.plasticosTitular = plasticosTitular;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public Integer getModo() {
		return modo;
	}


	public void setModo(Integer modo) {
		this.modo = modo;
	}


	public Long getIdMotivoCierre() {
		return idMotivoCierre;
	}


	public void setIdMotivoCierre(Long idMotivoCierre) {
		this.idMotivoCierre = idMotivoCierre;
	}


	public CierreCuentaCliente getCierreCuentaCliente() {
		return cierreCuentaCliente;
	}


	public void setCierreCuentaCliente(CierreCuentaCliente cierreCuentaCliente) {
		this.cierreCuentaCliente = cierreCuentaCliente;
	}


	public boolean getMostrarFormulario() {
		return mostrarFormulario;
	}


	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}


	public boolean isMostrarItems() {
		return mostrarItems;
	}


	public void setMostrarItems(boolean mostrarItems) {
		this.mostrarItems = mostrarItems;
	}


	public void setListaMotivosItem(List<SelectItem> listaMotivosItem) {
		this.listaMotivosItem = listaMotivosItem;
	}


	public List<SelectItem> getListaMotivosItem() {
		return this.listaMotivosItem;
	}


	public List getAdic() {
		return adic;
	}


	public void setAdic(List adic) {
		this.adic = adic;
	}


	public HtmlSelectOneMenu getMotivoHtml() {
		return motivoHtml;
	}


	public void setMotivoHtml(HtmlSelectOneMenu motivoHtml) {
		this.motivoHtml = motivoHtml;
	}


	public String getDescripcionLugar() {
		if (this.idMotivoCierre.equals(0L)) {
			return "-";
		}
		else {
			MotivoCierreCuentaCliente motivo = (MotivoCierreCuentaCliente) Util.buscarElemento(this.listaMotivosList, new MotivoCierreCuentaCliente(
					idMotivoCierre));
			return motivo.getPlasticoLugar().getDescripcion();
		}
	}




	public List getMensajesError() {
		return mensajesError;
	}


	public void setMensajesError(List mensajesError) {
		this.mensajesError = mensajesError;
	}


	public LiquidarSaldo0Bean getLiq() {
		return liq;
	}


	public void setLiq(LiquidarSaldo0Bean liq) {
		this.liq = liq;
	}



	/************************************************************************
	 * ACCIONES DEL BEAN DE CIERRE CUENTA CLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrar();
		return null;

	}


	public String cancelar() {
		// borrar();
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String irALiquidar() {
		liq = ((LiquidarSaldo0Bean) Session.getBean("LiquidarSaldo0Bean"));
		liq.inicializar();
		liq.setCuentaLiquidar(this.nroCuenta);
		liq.buscarCuenta();
		liq.liquidar();

		this.popup.setNombreIcono(liq.getPopup().getNombreIcono());
		this.popup.setMensaje(liq.getPopup().getMensaje());
		this.mostrarItems = false;
		origen = false;
		return null;

	}


	public String guardarAdjunto() {
		if (imagen != null) {
			int size = new Long(imagen.getSize()).intValue();
			try {
				String path = Archivo.grabarArchivo(imagen.getInputStream(), imagen.getName(), size, Archivo.archivosDeCierreCuentaCliente);
				if (path != null && !path.equals("No grabo")) {
					listAdjuntos.add(new Adjunto(listAdjuntos.size(), path));
				}
			} catch (Exception x) {
				x.printStackTrace();
				return null;
			}
		}
		return null;
	}


	public String guardarFormulario() {
		try {
			if (idMotivoCierre == 0) {
				error.agregar("Error, se debe seleccionar obligatoriamente un motivo de cierre");
			} else {

				cierreCuentaCliente.setAdjuntos(ListAdjuntoToString(':'));
				cierreCuentaCliente.setMotivoCierre((MotivoCierreCuentaCliente) Util.buscarElemento(listaMotivosList, new MotivoCierreCuentaCliente(
						idMotivoCierre)));
				if (alta) {
					transaccionesService.getCierreCuentaClienteService().grabarCierreCuentaCliente(cierreCuentaCliente);
					alta = false;
				}
				else
					transaccionesService.getCierreCuentaClienteService().actualizarCierreCuentaCliente(cierreCuentaCliente);

			}

		} catch (CierreCuentaClienteException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void ConfirmarCierre() {
		if (this.cierreCuentaCliente.getMotivoCierre() != null) {
			try {
				transaccionesService.getCierreCuentaClienteService().confirmarCierreCuentaSaldo(titular, adic, cierreCuentaCliente,
						Session.getOperador());
				popup.setPopup(popup.ICONO_OK, "La cuenta " + titular.getIdCliente() + " se ha cerrado exitosamente.");
			} catch (CierreCuentaClienteException e) {
				// TODO Auto-generated catch block
				error.agregar("Error, no se pudo realizar el cierre de la cuenta del cliente " + titular.getIdCliente());
				e.printStackTrace();
			}
		} else {
			error.agregar("Para poder cerrar la cuenta debe completar el motivo de cierre y guardar el formulario");
			}
	}


	public boolean getOrigen() {
		return origen;
	}



	public String irAContinuar() {
		popup.borrar();
		this.liq.getPopup().borrar();
		// borrar();
		this.buscarCuentaCliente();
		return "cierreCuentaCliente";
	}


	public String irASalir() {
		popup.borrar();
		return null;
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");
		return !error.hayErrores();
	}


	public void inicializarParametros(Map param) {
		// PARA USO DEL WORKFLOW
		inicializar();
		try {
			nroCuenta = new Long(param.get("nroCuenta").toString());
			modo = new Integer(param.get("modo").toString());
			plastico = param.get("plastico").toString();

			if (plastico != null && plastico.trim().equals("TIENE PLASTICOS POR DESTRUIR")) {
				error.agregar("Tiene plasticos para destruir controlar");
			}

		} catch (ClassCastException e) {
			error.agregar("El parametro nroCuenta debe ser numerico");
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			error.agregar("El parametro nroCuenta debe ser numerico");
			e2.printStackTrace();
		} catch (Exception e2) {
			error.agregar("Error al intentar procesar los  parametros del workflow");
			e2.printStackTrace();
		}

		// BUSCO EL TITULAR DE LA CUENTA//
		Filtro f = new Filtro("idCliente", Filtro.IGUAL, nroCuenta);
		f.agregarCampoComparacionNulo("idTitular", Filtro.NULL);
		List titularList;
		try {
			titularList = transaccionesService.getClienteTransaccionService().getCliente(f);
			if (titularList == null || titularList.size() == 0) {
				error.agregar("El nro de cuenta ingresado no pertenece a un titular de cuenta");
			} else {
				titular = (ClienteTransaccion) (titularList.get(0));

				// BUSCO LOS ADICIONALES DEL TITULAR
				Filtro g = new Filtro("idTitular", Filtro.IGUAL, nroCuenta);
				g.agregarCampoOperValor("estadoCliente.idEstadoCliente", Filtro.DISTINTO, 4L);
				adic = transaccionesService.getClienteTransaccionService().getCliente(g);

			}
		} catch (ClienteTransaccionException e) {
			error.agregar("Error al intentar verificar el titular de la cuenta");
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			buscarCuentaCliente();
		}

	}


	public void borrar() {
		borrarBaseBean();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Cierre Cuenta Cliente";

		this.titular = null;
		this.adicionales = null;
		this.mostrarDatos = false;
		this.nroCuenta = null;
		this.popupReport = null;
		this.mostrarFormulario = false;
		this.idMotivoCierre = null;
		this.mostrarItems = false;
		this.mostrarFormulario = false;
		this.mensajesError = new ArrayList<String>();
		// this.plasticosTitular = null;

	}


	private String FormatearFecha(Timestamp t) {
		// timestamp -> MM/YYYY
		String[] sp = t.toString().substring(0, 7).split("-");
		return sp[1] + "/" + sp[0];
	}


	public String getColorEstadoCliente() {
		if (this.titular.getEstadoCliente().getIdEstadoCliente() == 1)
			return "COLOR: green;";
		else
			return "COLOR: red;";
	}


	public String getColorEstadoCobranza() {
		if (this.titular.getEstadoCobranza().getIdEstadoCobranza() == 1)
			return "COLOR: green;";
		else
			return "COLOR: red;";
	}


	public String getColorSaldo() {
		if (saldo == 0)
			return "COLOR: green;";
		else
			return "COLOR: red;";
	}


	public boolean getBotonConfirmarVisible() {
		if (modo == 1 || titular.getEstadoCliente().getIdEstadoCliente() == 4L)
			return false; // modo operador
		else if (modo == 2)
			return true; // modo supervisor
		return false;
		// return true;
	}


	public boolean getBotonComprobanteVisible() {
		if (titular.getEstadoCliente().getIdEstadoCliente() == 4L)
			return true; // la cuenta esta cerrada
		else
			return false;
	}


	private String getDataPlasticoHabilitado(PlasticoCliente plasticoHabilitado) {
		// devuelve un string con los datos del plastico hablitado del cliente, si es que posee uno
		if (plasticoHabilitado != null) {
			String nro = plasticoHabilitado.getNumero();
			String fechaDesde = FormatearFecha(plasticoHabilitado.getVigenciaDesde());
			String fechaHasta = FormatearFecha(plasticoHabilitado.getVigenciaHasta());
			// String estadoPlastico = plasticoHabilitado.getEstadoPlastico().getDescripcion();
			return "Nro: " + nro + " Estado: " + "Activo" + " Desde: " + fechaDesde + " Hasta: " + fechaHasta;
		}
		else
			return "No se registra ningun plastico habilitado.";
	}


	public String buscarCuentaCliente() {
		mensajesError.clear();
		try {

			mostrarDatos = true;
			mostrarFormulario = true;
			saldo = transaccionesService.getCtaCteClienteService().getSaldoAbsolutoCtaCte(this.nroCuenta);

			documento = titular.getIndividuo().getTipoDocumento().getDescripcion() + " " + this.titular.getIndividuo().getNroDocumento();
			plasticosTitular = getDataPlasticoHabilitado(titular.getPlaticoClienteHabilitado());

			adicionales = new ArrayList<Adicional>();
			for (Object ct : adic) {
				String nombreAdicional = ((ClienteTransaccion) ct).getNombreCliente();
				String tipoDocumentoAdicional = ((ClienteTransaccion) ct).getIndividuo().getTipoDocumento().getDescripcion();
				String nroDocumentoAdicional = ((ClienteTransaccion) ct).getIndividuo().getNroDocumento();
				Set platicosAdicional = ((ClienteTransaccion) ct).getPlasticoClienteSet();
				String dataPlastico = getDataPlasticoHabilitado(((ClienteTransaccion) ct).getPlaticoClienteHabilitado());
				adicionales.add(new Adicional(nombreAdicional, tipoDocumentoAdicional, nroDocumentoAdicional, dataPlastico));
			}

			// VALIDACIONES//
			if (this.titular.getEstadoCliente().getIdEstadoCliente() == 4) {
				mensajesError.add("La cuenta ya esta cerrada.");
			}
			if (this.titular.getEstadoCliente().getIdEstadoCliente() == 6) {
				mensajesError.add("La cuenta ya esta cerrada.");
			}
			if (saldo == 0) {
				mensajesError.add("La cuenta se encuentra con saldo 0(cero). Realice Cierre de Cuenta");
			}
			// FIN VALIDACIONES//

			if (!mensajesError.isEmpty()) {
				mostrarFormulario = false;
			} else { // si se valido todo bien mostramos el formulario para la carga de los detalles del cierre de cuenta
			
				try {
					Filtro filtro = new Filtro("cliente.idCliente", Filtro.IGUAL, nroCuenta);
					filtro.agregarCampoOperValor("esDefinitivo", Filtro.LIKEXS, "N");
					List cierres = transaccionesService.getCierreCuentaClienteService().getCierreCuentaCliente(filtro);
					if (cierres.size() == 0) {
						cierreCuentaCliente = new CierreCuentaCliente(null, titular, null, "", null, "N");
						idMotivoCierre = new Long(0);
						StringToListAdjunto(cierreCuentaCliente.getAdjuntos(), ':');
						alta = true;
					} else {
						if (cierres.size() > 1)
							error.agregar("Error existe un cliente con dos cierres distintos en estado de edicion.");

						cierreCuentaCliente = (CierreCuentaCliente) cierres.get(0);
						idMotivoCierre = cierreCuentaCliente.getMotivoCierre().getIdMotivoCierreCuentaCliente();
						StringToListAdjunto(cierreCuentaCliente.getAdjuntos(), ':');
						alta = false;

					}
				} catch (CierreCuentaClienteException e) {
					e.printStackTrace();
				}
				try {
					Filtro filtro = new Filtro("plasticoLugar.motivoBaja", Filtro.IGUAL, "'S'");
					listaMotivosList = transaccionesService.getMotivoCierreCuentaClienteService().getMotivoCierreCuentaCliente(filtro);
					listaMotivosItem.clear();
					listaMotivosItem.add(new SelectItem(new Long(0), "Seleccione un Motivo"));
					listaMotivosItem.addAll(Util.cargarSelectItem(listaMotivosList));
				} catch (MotivoCierreCuentaClienteException e) {
					// TODO Auto-generated catch block
					log.info("Error al leer motivos de cierre cuenta cliente ");
					e.printStackTrace();
				}
				Filtro f = new Filtro();
				f.agregarCampoOperValor("motivoBaja", Filtro.IGUAL, "'S'");
				this.listPlasticoLugar = transaccionesService.getPlasticoLugarService().listar(f);
				origen = true;
			}

		} catch (CtaCteClienteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private String ListAdjuntoToString(char c) {
		// convierte la lista de Adjuntos del bean en un string de sus paths separados por por el caracter c
		String s = "";
		for (Adjunto a : listAdjuntos) {
			s += a.getFullName() + c;

		}
		return s;
	}


	private void StringToListAdjunto(String s, char c) {
		// convierte el string de paths de adjuntos en la lista de adjuntos
		listAdjuntos = new LinkedList<Adjunto>();
		if (s != null && !s.equals("")) {
			String[] sp = s.split(Character.toString(c));
			for (int i = 0; i < sp.length; i++) {
				listAdjuntos.add(new Adjunto(listAdjuntos.size(), sp[i]));
			}
		}
	}


	public String generarCertificado() {
		try {
			HttpServletRequest request = Session.getRequest();
			String p1 = "?v_cliente=" + nroCuenta;
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p3 = "&fechaHoy=" + Fecha.getFechaFormatoEspanol().trim();
			String page = request.getContextPath() + "/report/certificado_libre_deuda.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
			log.info(popup);

		} catch (Exception e) {
			log.info("Error al intentar generar el certificado de libre deuda");
			e.printStackTrace();
		}

		return null;
	}


	public String generarComprobante() {
		try {
			HttpServletRequest request = Session.getRequest();
			String p1 = "?v_cliente=" + nroCuenta;
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			Calendar c = Calendar.getInstance();

			String p3 = "&dia=" + c.get(Calendar.DAY_OF_MONTH);
			String p4 = "&mes=" + Util.nombreMes(c.get(Calendar.MONTH) + 1);
			String p5 = "&year=" + c.get(Calendar.YEAR);

			String page = request.getContextPath() + "/report/Comprobante_Cierre_Cuenta_Saldo.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";
			log.info(popup);

		} catch (Exception e) {
			log.info("Error al intentar generar el comprobante de cierre de cuenta cliente");
			e.printStackTrace();
		}

		return null;
	}


	public void cambiarLugar(ValueChangeEvent event) {
		if (motivoHtml != null && motivoHtml.getValue() != null && !motivoHtml.getValue().equals("")) {
			Long idMotivo = new Long(motivoHtml.getValue().toString());
			if (!idMotivo.equals(0L)) {
				MotivoCierreCuentaCliente motivo = (MotivoCierreCuentaCliente) Util.buscarElemento(this.listaMotivosList,
						new MotivoCierreCuentaCliente(idMotivo));
				this.descripcionLugar = motivo.getPlasticoLugar().getDescripcion();
			}
			else
				this.descripcionLugar = "-";

		}
	}


	// CLASE INTERNA DEL BEAN //
	public class Adicional {
		private String apellido;
		private String tipoDocumento;
		private String nroDocumento;
		private String dataPlastico;


		public Adicional(String apellido, String tipoDocumento, String nroDocumento, String dataPlastico) {
			this.apellido = apellido;
			this.tipoDocumento = tipoDocumento;
			this.nroDocumento = nroDocumento;
			this.dataPlastico = dataPlastico;
		}


		public String getApellido() {
			return apellido;
		}


		public void setApellido(String apellido) {
			this.apellido = apellido;
		}


		public String getTipoDocumento() {
			return tipoDocumento;
		}


		public void setTipoDocumento(String tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
		}


		public String getNroDocumento() {
			return nroDocumento;
		}


		public void setNroDocumento(String nroDocumento) {
			this.nroDocumento = nroDocumento;
		}


		public String getDataPlastico() {
			return dataPlastico;
		}


		public void setDataPlastico(String dataPlastico) {
			this.dataPlastico = dataPlastico;
		}
	}

	// CLASE INTERNA DEL BEAN //
	public class Adjunto {
		private int id;
		private String fullName;
		private String truncName;


		public Adjunto(int id, String fullName) {
			this.id = id;
			this.fullName = fullName;
			this.truncName = FormatearNombreAdjunto(fullName);
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getTruncName() {
			return truncName;
		}


		public void setTruncName(String truncName) {
			this.truncName = truncName;
		}


		public boolean equals(Object obj) {
			if (obj instanceof Adjunto) {
				Adjunto aux = (Adjunto) obj;
				if (aux.getId() == id) {
					return true;
				}
			}
			return false;
		}


		private String FormatearNombreAdjunto(String name) {
			// fecha en milisegundos-nombre --> nombre
			String s = "";
			String[] sp = name.split("-");
			for (int i = 1; i < sp.length; i++) {
				s += sp[i];
			}
			return s;
		}


		public String eliminar() {
			listAdjuntos.remove(this);
			return "";
		}


		public String abrirAdjunto() {
			if (fullName != null && fullName != "") {
				try {
					ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosDeCierreCuentaCliente + "/" + fullName
							+ "',1000,700), 'titlebar=no';");
				} catch (Exception e) {
					log.info("Error al intentar leer el archivo");
					e.printStackTrace();
				}
			}
			return "";
		}

	}


	public String getPlastico() {
		return plastico;
	}


	public void setPlastico(String plastico) {
		this.plastico = plastico;
	}

}


package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.beans.util.PaginadorPorDemanda;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes","unchecked"})
public class ClienteBean extends BaseBean {

	private static final Logger log = Logger.getLogger(ClienteBean.class);

	private boolean individuoCargado = false;
	private ClienteTransaccion cliente, clienteFiltro;
	private Long idClienteHidden;

	// definicion de un list del objeto base
	private List clienteList;

	private IndividuoEvaluacion individuo;
	private String focoHidden;

	private EstadoCliente estadoCliente;
	private EstadoCobranza estadoCobranza;
	private String consumoHabilitado;
	private boolean individuoExistente;
	private String nombreTitular;
	private boolean clienteCargado;
	private boolean mensajeParaAlta;
	private String numeroPlastico;

	private Date fechaCliente;
	private Date fechaConsumo;
	private Date fechaCobranza;

	private List estadoClienteItems;
	private List estadoCobranzaItems;
	private List estadoConsumoItems;
	private List estadoClienteFiltroItems;
	private List estadoCobranzaFiltroItems;
	private List estadoConsumoFiltroItems;
	private List historicoList;

	private boolean esTitular; // en caso de ser titular, muestra un texto
	// "El cliente es titular de la tarjeta" si no
	// dice que es adicional

	private int contador;
	// Emiliano paginador
	private PaginadorPorDemanda pagDeMov;
	// El array contiene los nombres de las columnas por ordenar
	private List ordenItems;
	// Objetos Relacionados.
	private Integer idOrdenSeleccionada;


	public ClienteBean() {
		super();
		clienteList = new ArrayList();
		borrar();
		estadoClienteItems = Util
				.cargarSelectItemMascara(ClienteTransaccion.estadosClienteStaticList);
		estadoCobranzaItems = Util
				.cargarSelectItemMascara(ClienteTransaccion.estadoCobranzaStaticList);
		estadoConsumoItems = Util
				.cargarSelectItemMascara(ClienteTransaccion.habilitadoConsumoStaticList);
		estadoClienteFiltroItems = new ArrayList();
		estadoClienteFiltroItems.add(new SelectItem("NoValido",
				"Seleccione un estado"));
		estadoClienteFiltroItems.addAll(estadoClienteItems);
		estadoCobranzaFiltroItems = new ArrayList();
		estadoCobranzaFiltroItems.add(new SelectItem(new Long(0),
				"Seleccione un estado"));
		estadoCobranzaFiltroItems.addAll(estadoCobranzaItems);
		estadoConsumoFiltroItems = new ArrayList();
		historicoList = new ArrayList();
		estadoConsumoFiltroItems.add(new SelectItem("NoValido",
				"Seleccione un estado"));
		estadoConsumoFiltroItems.addAll(estadoConsumoItems);
		/**************** Emiliano ********************/
		ordenItems = new ArrayList();
		ordenItems.add(new SelectItem(new Integer(0), "Id Cliente"));
		ordenItems.add(new SelectItem(new Integer(1), "Cuit"));
		ordenItems.add(new SelectItem(new Integer(2), "Apellido"));
		ordenItems.add(new SelectItem(new Integer(3), "Estado Cobranza"));
		ordenItems.add(new SelectItem(new Integer(4), "Estado Consumo"));
		/**************** Emiliano ********************/

	}

	/************************************************************************
	 * ACCIONES DEL BEAN DE CLIENTE
	 ************************************************************************/

	private String fechaDesde;
	private String fechaHasta;


	/*
	 * public void cambiarDirectorio(){ if(fechaDesde.length()==8 && fechaHasta.length()==8){ String rutaOrigen = "docAdjuntos/"; String rutaDestino =
	 * ""; char aux[]=fechaDesde.toCharArray(); String año = ""; año+=aux[4]; año+=aux[5]; año+=aux[6]; año+=aux[7]; String mes = ""; mes+=aux[2];
	 * mes+=aux[3]; String dia = ""; dia+=aux[0]; dia+=aux[1]; rutaDestino+=año+"-"+mes+"/";
	 * 
	 * int diaAux = Integer.parseInt(dia); int mesAux = Integer.parseInt(mes)-1; int añoAux = Integer.parseInt(año)-1900; Timestamp desde = new
	 * Timestamp(añoAux, mesAux, diaAux, 0, 0, 0, 0);
	 * 
	 * aux=fechaHasta.toCharArray(); dia=""; dia+=aux[0]; dia+=aux[1]; mes=""; mes+=aux[2]; mes+=aux[3]; año=""; año+=aux[4]; año+=aux[5];
	 * año+=aux[6]; año+=aux[7];
	 * 
	 * diaAux = Integer.parseInt(dia); mesAux = Integer.parseInt(mes)-1; añoAux = Integer.parseInt(año)-1900; Timestamp hasta = new Timestamp(añoAux,
	 * mesAux, diaAux, 23, 59, 59, 0);
	 * 
	 * List<Digital> list = evaluacionService.getDigitalService().buscarPorFecha(desde,hasta);
	 * 
	 * log.info("Ruta Destino: " + rutaDestino); log.info("Elementos a Trasladar: " + list.size()); String urlAux="";
	 * 
	 * 
	 * String SFTPHOST = "192.168.0.7"; int SFTPPORT = 22; String SFTPUSER = "root"; String SFTPPASS = "root66"; String SFTPWORKINGDIR =
	 * "/usr/local/apache-tomcat-6.0.20/webapps/archivos/individuos/";
	 * 
	 * com.jcraft.jsch.Session session = null; Channel channel = null; ChannelSftp channelSftp = null;
	 * 
	 * try { JSch jsch = new JSch(); session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT); session.setPassword(SFTPPASS); java.util.Properties
	 * config = new java.util.Properties(); config.put("StrictHostKeyChecking", "no"); session.setConfig(config); session.connect(); channel =
	 * session.openChannel("sftp"); channel.connect(); channelSftp = (ChannelSftp) channel; channelSftp.cd(SFTPWORKINGDIR);
	 * 
	 * for(Digital digital:list){ String url = digital.getUrl(); if(url!=urlAux) { String rutaOrigenAux = rutaOrigen+url; String rutaDestinoAux =
	 * rutaDestino+url;
	 * 
	 * SftpATTRS attrs=null; try { attrs = channelSftp.stat(rutaOrigenAux); } catch (Exception e) { }
	 * 
	 * if (attrs != null) { channelSftp.rename(rutaOrigenAux, rutaDestinoAux); //moverArchivo(rutaOrigenAux, rutaDestinoAux); urlAux = url;
	 * log.info("File transfered successfully."); } } } }catch (Exception ex) { log.info("Exception found while tranfer the response."); } finally{
	 * channelSftp.exit(); channel.disconnect(); session.disconnect(); }
	 * 
	 * } }
	 */

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amCliente";
	}


	public String editarCliente() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar cliente";
		try {
			cliente = transaccionesService.getClienteTransaccionService()
					.leerCliente(idClienteHidden);
			estadoCliente = cliente.getEstadoCliente();
			cliente.getIndividuo().getCuil();
			cliente.getIndividuo().getApellido();
			individuoCargado = true;
			clienteCargado = true;
			tituloCorto = "Modificar cliente";
			result = "amCliente";

		} catch (ClienteTransaccionException e1) {
			error.agregar("Ocurrio un error al intentar editar el cliente");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarCliente.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el cliente");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarCliente.jsf");
		}
		return result;
	}


	public String eliminarCliente() {
		try {
			transaccionesService.getClienteTransaccionService().borrarCliente(
					idClienteHidden);
			clienteList.remove(new ClienteTransaccion(idClienteHidden));
		} catch (ClienteTransaccionException e1) {
			error
					.agregar("Imposible borrar el cliente. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el cliente");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarCliente.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				if (cliente.getIdCliente() != null) {
					alta = false;
				} else {
					alta = true;
				}
				// Inicio los servis que voy a usar
				ClienteTransaccionService clienteService = transaccionesService
						.getClienteTransaccionService();

				cliente.setEstadoCliente(estadoCliente);
				cliente.setEstadoCobranza(estadoCobranza);
				// cliente.setHabiliadoConsumo(consumoHabilitado);

				if (fechaCliente != null && !fechaCliente.equals(new Date()))
					cliente.setFechaEstadoCliente(new Timestamp(fechaCliente
							.getTime()));

				if (fechaCobranza != null && !fechaCobranza.equals(new Date()))
					cliente.setFechaEstadoCobranza(new Timestamp(fechaCobranza
							.getTime()));

				if (fechaConsumo != null && !fechaConsumo.equals(new Date()))
					cliente.setFechaHabilitadoConsumo(new Timestamp(
							fechaConsumo.getTime()));

				if (alta) {
					clienteService.grabarCliente(cliente);
				} else {
					clienteService.actualizarCliente(cliente);
				}
				popup.setPopup(popup.ICONO_OK,
						"El cliente ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session
						.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ClienteTransaccionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e1.printStackTrace();
		} catch (ClienteTransaccionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Cliente";
		popup.borrar();
		estadoCliente = null;
		estadoCobranza = null;
		consumoHabilitado = "";
		fechaCliente = null;
		fechaCobranza = null;
		fechaConsumo = null;
		clienteList.clear();
		cliente = new ClienteTransaccion();
		clienteCargado = false;
		individuoCargado = false;
		mensajeParaAlta = false;
		clienteFiltro = new ClienteTransaccion();
		clienteFiltro.setIndividuo(new IndividuoEvaluacion());
		clienteFiltro.setPlaticoClienteHabilitado(new PlasticoCliente());
		contador = 0;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (!individuoCargado) {
			error
					.agregar("Debe cargar un individuo para poder guardar el cliente");
		}
		if (cliente.getCiclo() == null
				|| cliente.getCiclo().equals(new Long(0)))
			error.agregar(Error.TRAN_CICLO_REQUERIDO);

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCliente() {
		return inicializar();
	}


	public String irAModificarCliente() {
		popup.borrar();
		clienteCargado = true;
		individuoCargado = true;
		tituloCorto = "Modificar cliente";
		return null;
	}


	public String irAListarCliente() {
		borrar();
		tituloCorto = "Listado de Cliente";
		Session.redirect("/tarjetafiel/transacciones/listarCliente.jsf");
		return "";
	}


	public String listarCliente() {

		if (clienteFiltro.getIndividuo().getApellido().length() > 3 || clienteFiltro.getIndividuo().getApellido().length() == 0) {
			clienteList = new ArrayList();
			historicoList.clear();
			Filtro filtro = new Filtro();
			try {
				filtro.reset();

				boolean seleccion = false;
				boolean seleccionPlastico = false;
				if (clienteFiltro.getIdCliente() != null && !clienteFiltro.getIdCliente().equals(new Long(0))) {
					filtro.agregarCampoOperValor("idCliente", Filtro.IGUAL, clienteFiltro.getIdCliente());
					seleccion = true;
				}
				if (clienteFiltro.getIndividuo().getCuil() != null && clienteFiltro.getIndividuo().getCuil().compareTo("") != 0) {
					filtro.agregarCampoOperValor("individuo.cuil", Filtro.LIKE, clienteFiltro.getIndividuo().getCuil());
					seleccion = true;
				}
				if (numeroPlastico != null && numeroPlastico.compareTo("") != 0) {
					seleccionPlastico = true;
				}

				if (clienteFiltro.getIndividuo().getApellido() != null && clienteFiltro.getIndividuo().getApellido().compareTo("") != 0) {
					filtro.agregarCampoOperValor("individuo.apellido", Filtro.LIKE, clienteFiltro.getIndividuo().getApellido());
					seleccion = true;
				}

				if (clienteFiltro.getIndividuo().getNombres() != null && clienteFiltro.getIndividuo().getNombres().compareTo("") != 0) {
					filtro.agregarCampoOperValor("individuo.nombres", Filtro.LIKE, clienteFiltro.getIndividuo().getNombres());
					seleccion = true;
				}

				if (clienteFiltro.getIndividuo().getNroDocumento() != null && clienteFiltro.getIndividuo().getNroDocumento().compareTo("") != 0) {
					filtro.agregarCampoOperValor("individuo.nroDocumento", Filtro.LIKE, clienteFiltro.getIndividuo().getNroDocumento());
					seleccion = true;
				}
				if (seleccion && seleccionPlastico) {
					error.agregar("Si desea filtrar por Plástico las demás casillas deben permanecer vacías");
				} else {

					if (seleccion) {
						// clienteList = transaccionesService.getClienteTransaccionService().getCliente(filtro);
						Iterator iter = clienteList.iterator();
						while (iter.hasNext()) {
							ClienteTransaccion element = (ClienteTransaccion) iter.next();
							element.getIndividuo().getLabel();
							element.getIndividuo().getCuil();
							element.getIndividuo().getApellido();
							element.getSucursal().getLabel();
						}
						pagDeMov = new PaginadorPorDemanda(filtro, (Paginacion) transaccionesService.getClienteTransaccionService(), clienteList, 10,
								error, "/tarjetafiel/transacciones/listarCliente.jsf");

					}
					if (seleccionPlastico) {
						filtro = new Filtro("numero", Filtro.LIKE, numeroPlastico);
						List plasticoList = transaccionesService.getPlasticoClienteService().getPlasticoCliente(filtro);
						Iterator iterPlastico = plasticoList.iterator();
						PlasticoCliente plastico = new PlasticoCliente();
						while (iterPlastico.hasNext()) {
							plastico = (PlasticoCliente) iterPlastico.next();
							filtro = new Filtro("idCliente", Filtro.IGUAL, plastico.getClienteTransaccion().getIdCliente());
						}

						Iterator iter = clienteList.iterator();
						while (iter.hasNext()) {
							ClienteTransaccion element = (ClienteTransaccion) iter.next();
							element.getIndividuo().getLabel();
							element.getIndividuo().getCuil();
							element.getIndividuo().getApellido();
							element.getSucursal().getLabel();
						}
						pagDeMov = new PaginadorPorDemanda(filtro, (Paginacion) transaccionesService.getClienteTransaccionService(), clienteList, 10,
								error, "/tarjetafiel/transacciones/listarCliente.jsf");

					}
					if (!seleccion && !seleccionPlastico) {
						error.agregar("Para proceder, debe llenar por lo menos un campo de búsqueda");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		Session.redirect("/tarjetafiel/transacciones/listarCliente.jsf");

		return null;
	}


	/************************************************************************
	 * METODOS PARA EL PAGINADO REESCRITOS
	 ************************************************************************/

	public void cargarPagina(ValueChangeEvent e) {
		historicoList.clear();
		pagDeMov.cargarPagina(e);
	}


	public String primeraPagina() {
		historicoList.clear();
		return pagDeMov.primeraPagina();
	}


	public String ultimaPagina() {
		historicoList.clear();
		return pagDeMov.ultimaPagina();
	}


	public String paginaSiguiente() {
		historicoList.clear();
		return pagDeMov.paginaSiguiente();
	}


	public String paginaAnterior() {
		historicoList.clear();
		return pagDeMov.paginaAnterior();
	}


	public String mostrarCtaCteCliente() {
		String result = null;
		CtaCteClienteBean ctaCteClienteBean = (CtaCteClienteBean) Session
				.getBean("CtaCteClienteBean");
		ctaCteClienteBean.listarCtaCteCliente(idClienteHidden);
		return result;
	}


	public String cargarDatosIndividuo() {
		individuo = new IndividuoEvaluacion();
		IndividuoPopupBean indPopupBean = (IndividuoPopupBean) Session
				.getBean("IndividuoPopupBean");
		indPopupBean.inicializar(individuo, indPopupBean.CLIENTE, null);
		return null;
	}


	/*
	 * public String modificarDatosIndividuo() { Empresa emp = null; EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean"); try {
	 * beanEmpresa.setCuitEditado(empresa.getCuit().toString()); List empresas = generalService.getEmpresaService().getEmpresa(new Filtro()); if
	 * (!empresas.isEmpty()) { log.info("La lista de empresas tiene un tamaño de: " + empresas.size()); Iterator iterEmp = empresas.iterator(); while
	 * (iterEmp.hasNext()) { Empresa empresita = (Empresa)iterEmp.next(); if (empresita.getCuit().equals(empresa.getCuit())) { emp = empresita;
	 * emp.getCuit(); emp.getRazonSocial(); emp.getLabel(); emp.getRubro(); emp.getTamEmpresa(); emp.getEsRiesgoza(); Iterator iter =
	 * emp.getSucEmpresas().iterator(); while (iter.hasNext()) { SucEmpresa sucEmpresa = (SucEmpresa)iter.next(); sucEmpresa.getIdSucEmpresa();
	 * log.info("La sucEmpresa seleccionada tiene id: " + sucEmpresa.getIdSucEmpresa()); sucEmpresa.getDescripcion();
	 * sucEmpresa.getDomicilio().getLocalidad().getNombre(); sucEmpresa.getDomicilio ().getBarrio().getLocalidad().getProvincia().getPais();
	 * sucEmpresa.getDomicilio().getTipoDomicilio(); sucEmpresa.getDomicilio().getTipoVivienda(); sucEmpresa.getDomicilio().getPropVivienda();
	 * sucEmpresa.getAutonomo(); Iterator it = sucEmpresa.getSucTelefonos().iterator(); while (it.hasNext()) { SucTelefono tel =
	 * (SucTelefono)it.next(); tel.getTelefono().getTipo().getDescripcion(); } Iterator itDos = sucEmpresa.getSucEmails().iterator(); while
	 * (itDos.hasNext()) { SucEmail email = (SucEmail)itDos.next(); email.getEmail().getEmail(); email.getIdSucEmail(); } } break; } } } } catch
	 * (EmpresaException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch (ClassCastException e2) { e2.printStackTrace(); } if
	 * (emp !=null) beanEmpresa.inicializarDesdePopup(emp); String path = FacesContext.getCurrentInstance
	 * ().getExternalContext().getRequestContextPath(); path += "/tarjetafiel/general/empresa/empresaPopup.jsf"; ejecutarJavaScript("popup('" + path +
	 * "',900,700), 'titlebar=no';"); return null; }
	 */

	public String mostrarGestionClientes() {
		String result = null;
		GestionarClientesBean gestionarClientes = (GestionarClientesBean) Session.getBean("GestionarClientesBean");
		ClienteTransaccion clienteTransaccion = null;
		Iterator iter = clienteList.iterator();
		while (iter.hasNext()) {
			ClienteTransaccion clie = (ClienteTransaccion) iter.next();
			if (clie.getIdCliente().compareTo(idClienteHidden) == 0) {
				clienteTransaccion = clie;
				break;
			}
		}
		try {
			clienteTransaccion = transaccionesService.getClienteTransaccionService().leerCliente(clienteTransaccion.getIdCliente());
			clienteTransaccion.getIndividuo().getDiaPago().getDiaPago();
		} catch (ClienteTransaccionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gestionarClientes.listarMovimientos(clienteTransaccion);
		return result;
	}

	public class ClienteTransaccionWrappers {

		private int indice;
		private ClienteTransaccion clienteTransaccion;


		public ClienteTransaccionWrappers(ClienteTransaccion clienteTransaccion) {
			super();
			this.clienteTransaccion = clienteTransaccion;
			this.indice = contador++;
		}


		public ClienteTransaccion getClienteTransaccion() {
			return clienteTransaccion;
		}


		public void setClienteTransaccion(ClienteTransaccion clienteTransaccion) {
			this.clienteTransaccion = clienteTransaccion;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}

	}


	public void buscarClienteRelacionadoAIndividuo() {
		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("individuo.idIndividuo", Filtro.IGUAL,
					individuo.getIdIndividuo());
			List clientesExistentes = transaccionesService
					.getClienteTransaccionService().getCliente(filtro);
			log.info("LOS CLIENTES QUE TIENEN ESE INDIVIDUO SON "
					+ clientesExistentes.size());
			if (!clientesExistentes.isEmpty()) {
				cliente = (ClienteTransaccion) clientesExistentes.get(0);
				cliente.getIndividuo().getApellido();
				cliente.getIndividuo().getNroDocumento();
				fechaCliente = (Date) cliente.getFechaEstadoCliente();
				fechaCobranza = (Date) cliente.getFechaEstadoCobranza();
				fechaConsumo = (Date) cliente.getFechaHabilitadoConsumo();
				estadoCobranza = cliente.getEstadoCobranza();
				consumoHabilitado = cliente.getHabilitadoConsumo();
				clienteCargado = true;
				individuoCargado = true;
				tituloCorto = "Modificar Cliente";
			} else {
				log.info("el cliente no existe, buscare darlo de alta");
				clienteCargado = false;
				cliente.setIndividuo(individuo);
				mensajeParaAlta = true;
			}
		} catch (ClienteTransaccionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public String darAltaCliente() {
		mensajeParaAlta = false;
		tituloCorto = "Alta Cliente";
		cliente.setIndividuo(individuo);
		log.info("setee el cliente: " + cliente.getIndividuo().getApellido());
		try {
			cliente.setSucursal(generalService.getSucursalFielService()
					.leerSucursalFiel(new Long(1)));
		} catch (SucursalFielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clienteCargado = true;
		individuoCargado = true;
		return null;
	}


	public String cancelarAltaCliente() {
		mensajeParaAlta = false;
		individuo = new IndividuoEvaluacion();
		clienteCargado = false;
		return null;
	}


	public boolean isEsTitular() {
		return esTitular;
	}


	public void setEsTitular(boolean esTitular) {
		this.esTitular = esTitular;
	}


	public String getNombreTitular() {
		return nombreTitular;
	}


	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}


	public boolean isClienteCargado() {
		return clienteCargado;
	}


	public void setClienteCargado(boolean clienteCargado) {
		this.clienteCargado = clienteCargado;
	}


	public boolean isMensajeParaAlta() {
		return mensajeParaAlta;
	}


	public void setMensajeParaAlta(boolean mensajeParaAlta) {
		this.mensajeParaAlta = mensajeParaAlta;
	}


	public List getEstadoClienteItems() {
		return estadoClienteItems;
	}


	public void setEstadoClienteItems(List estadoClienteItems) {
		this.estadoClienteItems = estadoClienteItems;
	}


	public List getEstadoCobranzaItems() {
		return estadoCobranzaItems;
	}


	public void setEstadoCobranzaItems(List estadoCobranzaItems) {
		this.estadoCobranzaItems = estadoCobranzaItems;
	}


	public List getEstadoConsumoItems() {
		return estadoConsumoItems;
	}


	public void setEstadoConsumoItems(List estadoConsumoItems) {
		this.estadoConsumoItems = estadoConsumoItems;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public ClienteTransaccion getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTransaccion cliente) {
		this.cliente = cliente;
	}


	public Long getIdClienteHidden() {
		return idClienteHidden;
	}


	public void setIdClienteHidden(Long idClienteHidden) {
		this.idClienteHidden = idClienteHidden;
	}


	public List getClienteList() {
		return clienteList;
	}


	public void setClienteList(List object) {
		this.clienteList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public EstadoCobranza getEstadoCobranza() {
		return estadoCobranza;
	}


	public void setEstadoCobranza(EstadoCobranza estadoCobranza) {
		this.estadoCobranza = estadoCobranza;
	}


	public String getConsumoHabilitado() {
		return consumoHabilitado;
	}


	public void setConsumoHabilitado(String consumoHabilitado) {
		this.consumoHabilitado = consumoHabilitado;
	}


	public Date getFechaCliente() {
		return fechaCliente;
	}


	public void setFechaCliente(Date fechaCliente) {
		this.fechaCliente = fechaCliente;
	}


	public Date getFechaCobranza() {
		return fechaCobranza;
	}


	public void setFechaCobranza(Date fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}


	public Date getFechaConsumo() {
		return fechaConsumo;
	}


	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}


	public IndividuoEvaluacion getIndividuo() {
		return individuo;
	}


	public void setIndividuo(IndividuoEvaluacion individuo) {
		this.individuo = individuo;
	}


	public boolean isIndividuoCargado() {
		return individuoCargado;
	}


	public void setIndividuoCargado(boolean individuoCargado) {
		this.individuoCargado = individuoCargado;
	}


	public boolean isIndividuoExistente() {
		return individuoExistente;
	}


	public void setIndividuoExistente(boolean individuoExistente) {
		this.individuoExistente = individuoExistente;
	}


	public PaginadorPorDemanda getPagDeMov() {
		return pagDeMov;
	}


	public void setPagDeMov(PaginadorPorDemanda pagDeMov) {
		this.pagDeMov = pagDeMov;
	}


	public List getEstadoClienteFiltroItems() {
		return estadoClienteFiltroItems;
	}


	public void setEstadoClienteFiltroItems(List estadoClienteFiltroItems) {
		this.estadoClienteFiltroItems = estadoClienteFiltroItems;
	}


	public List getEstadoCobranzaFiltroItems() {
		return estadoCobranzaFiltroItems;
	}


	public void setEstadoCobranzaFiltroItems(List estadoCobranzaFiltroItems) {
		this.estadoCobranzaFiltroItems = estadoCobranzaFiltroItems;
	}


	public List getEstadoConsumoFiltroItems() {
		return estadoConsumoFiltroItems;
	}


	public void setEstadoConsumoFiltroItems(List estadoConsumoFiltroItems) {
		this.estadoConsumoFiltroItems = estadoConsumoFiltroItems;
	}


	public ClienteTransaccion getClienteFiltro() {
		return clienteFiltro;
	}


	public void setClienteFiltro(ClienteTransaccion clienteFiltro) {
		this.clienteFiltro = clienteFiltro;
	}


	public List getHistoricoList() {
		return historicoList;
	}


	public void setHistoricoList(List historicoList) {
		this.historicoList = historicoList;
	}


	public List getOrdenItems() {
		return ordenItems;
	}


	public void setOrdenItems(List ordenItems) {
		this.ordenItems = ordenItems;
	}


	public Integer getIdOrdenSeleccionada() {
		return idOrdenSeleccionada;
	}


	public void setIdOrdenSeleccionada(Integer idOrdenSeleccionada) {
		this.idOrdenSeleccionada = idOrdenSeleccionada;
	}


	public String getNumeroPlastico() {
		return numeroPlastico;
	}


	public void setNumeroPlastico(String numeroPlastico) {
		this.numeroPlastico = numeroPlastico;
	}


	public String getFechaDesde() {
		return this.fechaDesde;
	}


	public void setFechaDesde(String f) {
		this.fechaDesde = f;
	}


	public String getFechaHasta() {
		return this.fechaHasta;
	}


	public void setFechaHasta(String f) {
		this.fechaHasta = f;
	}

}

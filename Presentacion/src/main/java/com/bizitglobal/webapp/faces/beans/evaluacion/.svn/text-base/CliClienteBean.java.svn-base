package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.NroVerificador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliClienteDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliCliente2;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliGarante;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliIndividuo;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliMarca;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class CliClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CliClienteBean.class);
	private int origen;
	public static final int CAMBIO_LIM = 1;
	public static final int ABM_ADIC = 2;
	private String obsCred;
	private BigDecimal limiteCred;
	private Long idCliClienteHidden;
	private String nroSolicitud;
	private Solicitud solicitud;

	private IndividuoEvaluacion individuoEvaluacion;
	private SolicitudIndividuo solicIndividuoTitular;
	private IndividuoEvaluacion individuoGarante;
	private SolicitudIndividuo solicIndividuoGarante;
	private StringBuffer adicionales = new StringBuffer();

	// definicion de un list del objeto base

	private CliMarca marca;
	private CliCliente titular;

	// private List cliclienteList;
	private List listAdicional = new ArrayList();
	private List listSolicAdicional = new ArrayList();

	// Lista para objetos del sistema viejo.
	private List cliIndividuosViejos;

	private String focoHidden;


	public CliClienteBean() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public String getObsCred() {
		return obsCred;
	}


	public void setObsCred(String obsCred) {
		this.obsCred = obsCred;
	}


	public BigDecimal getLimiteCred() {
		return limiteCred;
	}


	public void setLimiteCred(BigDecimal limiteCred) {
		this.limiteCred = limiteCred;
	}


	public Long getIdCliClienteHidden() {
		return idCliClienteHidden;
	}


	public void setIdCliClienteHidden(Long idCliClienteHidden) {
		this.idCliClienteHidden = idCliClienteHidden;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public List getCliIndividuosViejos() {
		return cliIndividuosViejos;
	}


	public void setCliIndividuosViejos(List object) {
		this.cliIndividuosViejos = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public String getGarante() {
		if (individuoGarante != null)
			return individuoGarante.getApellido() + ", " + individuoGarante.getNombres();
		return null;
	}


	public String getAdicionales() {
		adicionales.delete(0, adicionales.length());
		boolean flag = false;
		Iterator iter = listAdicional.iterator();
		IndividuoEvaluacion ind;
		while (iter.hasNext()) {
			if (flag)
				adicionales.append(" | ");
			ind = (IndividuoEvaluacion) iter.next();
			adicionales.append(ind.getApellido() + ", " + ind.getNombres());
			flag = true;
		}
		return adicionales.toString();
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CLICLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrar();

		cargarItems();
		return "amCliCliente";
	}


	public void inicializarParametros(Map param) {
		borrar();
		try {
			this.nroSolicitud = (String) param.get("nroSolicitud");
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		}
		try {
			origen = new Integer((String) param.get("origen")).intValue();
		} catch (Exception e) {
			error.agregar("El parametro \"origen\" es incorrecto.");
		}
		log.info("Iniciando La aceptacion para la solicitud nro: " + nroSolicitud);
		if (!error.hayErrores()) {
			if (validar()) {
				try {
					Filtro filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
					solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
					if (solicitud != null) {
						if (solicitud.getPromotor() != null) {
							alta = false;
							List solicIndList = new ArrayList(solicitud.getSolicIndividuos());
							if (solicIndList.isEmpty()) {
								error.agregar("Error grabe. La solicitud esta vacia");
							} else {
								solicitud.getEstados().getDescripcion();
								Iterator iterCargando = solicitud.getSolicIndividuos().iterator();
								// boolean flag = false;
								// if (origen == 2) {
								// flag = true;
								// }
								while (iterCargando.hasNext()) {
									SolicitudIndividuo sol = (SolicitudIndividuo) iterCargando.next();
									if (sol.getActivo().equals("S") && sol.getAceptado().equals("S")) {
										if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1))) {
											individuoEvaluacion = sol.getIndividuoEvaluacion();
											individuoEvaluacion.getApellido();
										} else {
											if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
												individuoGarante = sol.getIndividuoEvaluacion();
												individuoGarante.getApellido();
											} else {
												if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))) {
													sol.getIndividuoEvaluacion().getApellido();
													listAdicional.add(sol.getIndividuoEvaluacion());
												}
											}
										}
									}
								}
							}
						} else {
							error.agregar(Error.EVA_SOLICITUD_SIN_PROMOTOR);
						}

					} else {
						error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);
					}
				} catch (SolicitudException e) {
					error.agregar("Error al cargar los datos.");
					e.printStackTrace();
				} catch (NullPointerException e2) {
					error.agregar("Error al cargar los datos.");
					e2.printStackTrace();
				} catch (Exception e) {
					error.agregar("Error al cargar los datos.");
					e.printStackTrace();
				}
				switch (origen) {
				case CAMBIO_LIM:
					break;
				case ABM_ADIC:
					try {
						if (individuoEvaluacion != null) {
							alta = false;
							List cliIndList = transaccionesService.getCliIndividuoService().getCliIndividuo(
									new Filtro(CliIndividuo.ID_INDIVIDUO, Filtro.IGUAL, individuoEvaluacion.getIdIndividuo()));
							if (cliIndList.isEmpty()) {
								error.agregar("El individuo no fue encontrado");
							} else {
								Iterator iter = cliIndList.iterator();
								while (iter.hasNext()) {
									CliIndividuo cliInd = (CliIndividuo) iter.next();
									if (cliInd.getIdTipoIndividuo().equals(new Long(1))) {
										marca = transaccionesService.getCliMarcaService().leerCliMarca(cliInd.getCliente());
										titular = transaccionesService.getCliClienteService().leerCliCliente(cliInd.getCliente());
										break;
									}
								}
								if (marca == null)
									error.agregar("El individuo no es un cliente");
							}
						} else {
							error.agregar("El individuo no fue encontrado");
						}
					} catch (Exception e) {
						error.agregar("Error al cargar los datos.");
						e.printStackTrace();
					}

					try {
						cliIndividuosViejos = transaccionesService.getCliIndividuoService().getCliIndividuo(
								new Filtro(CliIndividuo.NRO_SOLICITUD, Filtro.IGUAL, nroSolicitud.substring(0, 7)));

						/**
						 * Pienso poner todos los individuos del sistema viejo.
						 */
					} catch (Exception e) {

					}
					break;
				default:
					error.agregar("El parametro \"origen\" es invalido.");
					break;
				}
			}
		}
	}


	private void leerIndividuos() throws SolicitudException {

		Filtro filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
		solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
		Iterator iterCargando = solicitud.getSolicIndividuos().iterator();
		listAdicional.clear();
		while (iterCargando.hasNext()) {
			SolicitudIndividuo sol = (SolicitudIndividuo) iterCargando.next();
			if (sol.getActivo().equals("S") && sol.getAceptado().equals("S")) {
				IndividuoEvaluacion in = sol.getIndividuoEvaluacion();
				if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1))) {
					individuoEvaluacion = sol.getIndividuoEvaluacion();
					solicIndividuoTitular = sol;
				} else {
					if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
						individuoGarante = sol.getIndividuoEvaluacion();
						solicIndividuoGarante = sol;
					} else {
						if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))) {
							listAdicional.add(sol.getIndividuoEvaluacion());
							listSolicAdicional.add(sol);
						}
					}
				}
				in.getIdIndividuo();
				if (in.getActividad() != null) {
					if (in.getActividad().getSucEmpresa() != null) {
						if (in.getActividad().getSucEmpresa().getEmpresa() != null)
							in.getActividad().getSucEmpresa().getEmpresa().getCuit();
						if (in.getActividad().getSucEmpresa().getDomicilio() != null) {
							in.getActividad().getSucEmpresa().getDomicilio().getBarrio().getDescripcion();
							in.getActividad().getSucEmpresa().getDomicilio().setLocalidad(
									generalService.getLocalidadDao().buscarLocalidad(
											in.getActividad().getSucEmpresa().getDomicilio().getLocalidad().getIdLocalidad()));
							in.getActividad().getSucEmpresa().getDomicilio().getLocalidad().getProvincia().getPais().getNombre();
						}
						if (in.getActividad().getSucEmpresa().getSucTelefonos() != null) {
							Iterator iter = in.getActividad().getSucEmpresa().getSucTelefonos().iterator();
							while (iter.hasNext()) {
								SucTelefono tel = (SucTelefono) iter.next();
								tel.getTelefono().getNroTlefono();
							}
						}
					}
					in.getActividad().getOcupacion();
				}
				in.getEducacion();
				in.getVinculo();
				if (in.getDomicilio() != null) {
					in.getDomicilio().getTipoDomicilio();
					if (in.getDomicilio().getTipoVivienda() != null) {
						in.getDomicilio().getTipoVivienda().getDescripcion();
					}
					if (in.getDomicilio().getPropVivienda() != null)
						in.getDomicilio().getPropVivienda().getDescripcion();
					if (in.getDomicilio().getBarrio() != null)
						in.getDomicilio().getBarrio().getDescripcion();
					if (in.getDomicilio().getLocalidad() != null)
						in.getDomicilio().getLocalidad().getCodigoPostal();
					if (in.getDomicilio().getTipoDomicilio() != null)
						in.getDomicilio().getTipoDomicilio().getDescripcion();
					in.getDomicilio().getAntiguedad();
					in.getDomicilio().getTimestamp();
					in.getDomicilio().getCodigoPostal();
					in.getDomicilio().getOperador();
					in.getDomicilio().getOrientacion();
					in.getDomicilio().getCuotaAlquiler();
				}
				if (in.getMails() != null) {
					Iterator iterMail = in.getMails().iterator();
					while (iterMail.hasNext()) {
						Emails maiAuxi = (Emails) iterMail.next();
						maiAuxi.getEmail().getEmail();
						maiAuxi.getIndividuoEvaluacion();
					}
				}
				if (in.getTarjetas() != null) {
					Iterator iterTarj = in.getTarjetas().iterator();
					while (iterTarj.hasNext()) {
						Tarjeta tarAuxi = (Tarjeta) iterTarj.next();
						tarAuxi.getBanco();
						tarAuxi.getIndividuoEvaluacion();
					}
				}
				if (in.getDiaPago() != null) {
					in.getDiaPago().getPartido();
				}
				if (in.getTelefonos() != null) {
					Iterator iterTelefonos = in.getTelefonos().iterator();
					while (iterTelefonos.hasNext()) {
						Telefonos telAuxi = (Telefonos) iterTelefonos.next();
						telAuxi.getTelefono().getTipo().getDescripcion();
						telAuxi.getIndividuoEvaluacion();
					}
				}
				in.getVehiculos();
				if (in.getVehiculos() != null) {
					Iterator iterVehiculos = in.getVehiculos().iterator();
					while (iterVehiculos.hasNext()) {
						IndividuoVehiculo vehAuxi = (IndividuoVehiculo) iterVehiculos.next();
						vehAuxi.getVehiculo().getPatente();
						vehAuxi.getIndividuoEvaluacion();
					}
				}
				if (in.getBancos() != null) {
					Iterator iterBancos = in.getBancos().iterator();
					while (iterBancos.hasNext()) {
						Bancos banAuxi = (Bancos) iterBancos.next();
						banAuxi.getBanco().getDescripcion();
						banAuxi.getIndividuo();
					}
				}
				in.getDomicilioDoc();
				if (in.getDomicilios() != null) {
					Iterator iterDeDomicilios = in.getDomicilios().iterator();
					while (iterDeDomicilios.hasNext()) {
						IndividuoDomicilio domAuxi = (IndividuoDomicilio) iterDeDomicilios.next();
						if (domAuxi.getDomicilio().getTipoDomicilio() != null)
							domAuxi.getDomicilio().getTipoDomicilio().getDescripcion();
						if (domAuxi.getDomicilio().getTipoVivienda() != null)
							domAuxi.getDomicilio().getTipoVivienda().getDescripcion();
						if (domAuxi.getDomicilio().getPropVivienda() != null)
							domAuxi.getDomicilio().getPropVivienda().getDescripcion();
						domAuxi.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais().getNombre();
						domAuxi.getIndividuoEvaluacion();
					}
				}
				in.getProfesion();
			}
		}
	}


	private void cargarItems() {

	}


	public String grabar() {
		try {
			leerIndividuos();
			if (validarGrabar()) {
				switch (origen) {
				case CAMBIO_LIM:
					if (transaccionesService.getClienteTransaccionService().noExisteComoTitular(individuoEvaluacion)) {
						List garantes = new ArrayList();
						if (individuoGarante != null) {
							garantes.add(individuoGarante);
						}
						transaccionesService.getClienteTransaccionService().crearNuevaCuenta(
								individuoEvaluacion, garantes, listAdicional, Session.getOperador(), limiteCred, solicitud.getNroSolicitud());
						popup.setPopup(popup.ICONO_OK, "El cliente ha sido almacenado exitosamente.");
					} else {
						error.agregar("Error. El individuo ya es tutular de una cuenta.");
					}
					break;
				case ABM_ADIC:
					if (!transaccionesService.getCliClienteService().noExisteCliCliente(individuoEvaluacion.getCuil())) {
						CliCliente titularAGrabar = new CliCliente(individuoEvaluacion, new Long(solicitud.getNroSolicitud()));
						// Tengo que duplicar el objeto para conservar los valores viejos
						titularAGrabar.setCliente(titular.getCliente());

						CliCliente2 titular2 = new CliCliente2(individuoEvaluacion);
						CliMarca marca = new CliMarca(individuoEvaluacion, this.marca.getLimiteCredito());
						// CliCredPend credPend = new CliCredPend(marca,limiteCred,Session.getOperador());

						CliGarante garante = null;
						// Lo que intento hacer aca es buscar los garante viejo para modificarlo
						if (individuoGarante != null) {
							Filtro filtro = new Filtro(CliGarante.CLIENTE, Filtro.LIKEXS, titular.getCliente());
							filtro.agregarCampoOperValor(CliGarante.NRO_DOC, Filtro.IGUAL, individuoGarante.getNroDocumento());
							List cliGaranteList = transaccionesService.getCliGaranteService().getCliGarante(filtro);
							if (!cliGaranteList.isEmpty()) {
								garante = new CliGarante(individuoGarante, titular.getCliente());
							}

						}

						List adicionales = new ArrayList();
						Iterator iter = listAdicional.iterator();
						log.info("Todo se construllo bien voy a grabar los objetos");
						// SI TODO DE ARMO BIEN LO GRABO
						transaccionesService.getCliClienteService().actualizarCliCliente(titularAGrabar);
						titular2.setCliente(titular.getCliente());
						transaccionesService.getCliCliente2Service().actualizarCliCliente2(titular2);
						// transaccionesService.getCliCredPendService().grabarCliCredPend(credPend);
						marca.setLimiteCredito(limiteCred);
						marca.setLimiteFecha(Calendar.getInstance().getTime());
						// En el obj marca solo modifico el limite y la fecha de este, los otros datos a mi forma de ver se deben modificar en otro
						// proceso
						transaccionesService.getCliMarcaService().actualizarCliMarca(marca);
						popup.setPopup(popup.ICONO_OK, "El cliente ha sido modificado exitosamente.");

					} else {
						error.agregar("Error. No se encontro una cuenta asociada.");
					}
					break;
				}
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CliClienteDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e1.printStackTrace();
		} catch (CliClienteException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del cliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de nuevo cliente";

		obsCred = "";
		limiteCred = new BigDecimal(0);
		idCliClienteHidden = new Long(0);
		nroSolicitud = null;
		solicitud = null;
		individuoEvaluacion = null;
		solicIndividuoTitular = null;
		individuoGarante = null;
		solicIndividuoGarante = null;
		listAdicional.clear();
		listSolicAdicional.clear();
		marca = null;
		cliIndividuosViejos = new ArrayList();
	}


	public String cancelar() {
		borrar();
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String irAIndividuo() {
		IndividuoEvaluacionBean bean = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
		bean.inicializar(1, rellenarCerosFaltantes(solicitud.getNroSolicitud() + solicitud.getDV()), null);
		// individuoEvaluacion = bean.getIndividuoEvaluacion();
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/evaluacion/amIndividuo.jsf";
		ejecutarJavaScript("popup('" + path + "',1000,600), 'titlebar=no';");
		return null;
	}


	public String irAContinuar() {
		popup.borrar();
		return "";
	}


	public String irASalir() {
		return cancelar();
	}


	public String rellenarCerosFaltantes(String num) {
		String aux = "";
		for (int i = 0; i < (8 - num.length()); i++) {
			aux = aux + "0";
		}
		aux = aux + num;
		return aux;
	}


	public boolean validarGrabar() {
		if (limiteCred == null || limiteCred.equals(new BigDecimal(0)))
			error.agregar("El limite de credito es un dato requerido.");

		IndividuoEvaluacion in = individuoEvaluacion;
		if (in.getFechaNacimiento() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_FECHA_NAC_REQ);

		if (in.getDomicilio() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DOMICILIO_REQ);

		if (in.getEstadoCivil() == null
				|| in.getEstadoCivil().getIdEstadoCivil() == null
				|| in.getEstadoCivil().getIdEstadoCivil().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_ESTADO_CIVIL_REQ);

		if (in.getHijosCargo() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);

		if (in.getEducacion() == null
				|| in.getEducacion().getIdEducacion() == null
				|| in.getEducacion().getIdEducacion().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_EDUCACION_REQ);

		if (in.getDomicilioDoc() == null
				|| in.getDomicilioDoc().getIdDomicilio() == null
				|| in.getDomicilioDoc().getIdDomicilio().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DOMICILIO_DOCUM_REQ);

		if (in.getDiaPago() == null
				|| in.getDiaPago().getIdDiaPago() == null
				|| in.getDiaPago().getIdDiaPago().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DIA_DE_PAGO_REQ);

		if (in.getActividad() == null
				|| in.getActividad().getSucEmpresa() == null
				|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);

		if (individuoGarante != null) {
			in = individuoGarante;
			if (in.getVinculo() == null
					|| in.getVinculo().getIdVinculo() == null
					|| in.getVinculo().getIdVinculo().equals(new Long(0)))
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_VINCULO_REQ);

			if (in.getHijosCargo() == null)
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);

			if (in.getActividad() == null
					|| in.getActividad().getSucEmpresa() == null
					|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0)))
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);
		}
		return !error.hayErrores();
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");

		if (nroSolicitud == null || nroSolicitud.equals(new String("")))
			error.agregar(Error.EVA_NRO_SOLICITUD_REQUERIDA);

		if (nroSolicitud == null || nroSolicitud.length() != 8)
			error.agregar(Error.EVA_NRO_SOLICITUD_OCHODIGITOS);

		if (!validarDigitoVerificador())
			error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);

		if (origen == 0)
			error.agregar("El parametro \"origen\" es requerido.");

		return !error.hayErrores();
	}


	private boolean validarDigitoVerificador() {

		if (nroSolicitud != null && !nroSolicitud.equals(new String("")) && nroSolicitud.length() == 8) {
			String numero = nroSolicitud.substring(0, 7);
			String dv = nroSolicitud.substring(7);
			boolean digitoV = NroVerificador.esValido(numero, dv);
			log.info("Nro Verificador: " + digitoV);
			return digitoV;
		}
		return false;
	}

	// private CliIndividuo buscarIndViejo(IndividuoEvaluacion ie, Long tipoInd){
	// Iterator iter = cliIndividuosViejos.iterator();
	// while (iter.hasNext()) {
	// CliIndividuo ci = (CliIndividuo) iter.next();
	// if (tipoInd.compareTo(ci.getIdTipoIndividuo()) == 0) {
	// switch (ci.getIdTipoIndividuo().intValue()) {
	// case 1:
	// return ci; // si es el titular lo devuelvo de una
	// case 2:
	// return ci; // si es el garante lo devuelvo de una
	// case 3:
	// if (ci.getIdIndividuo().compareTo(ie.getIdIndividuo()) == 0) {
	// return ci;
	// }else
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// return null;
	// }
}
/*
 * COPIA DE LA LOGICA UTILIZADA PARA CREAR EL CLIENTE EN EL SISTEMA VIEJO
 * if(transaccionesService.getCliClienteService().noExisteCliCliente(individuoEvaluacion.getCuil())) { leerIndividuos(); // ARMO EL CLIENTE Y CLIENTE2
 * // OJO QUE SI SE LLEGA A MODIFICAR LA ESTRUCTURA DE GARANTES HAY QUE CAMBIAR ESTE CODIGO CliCliente titular = new
 * CliCliente(individuoEvaluacion,new Long(solicitud.getNroSolicitud()));; CliCliente2 titular2 = new CliCliente2(individuoEvaluacion); CliMarca marca
 * = new CliMarca(individuoEvaluacion,limiteCred); CliPlastico plasticoTitular = new CliPlastico(individuoEvaluacion,new Long(0));
 * transaccionesService.getCliPlasticoService().crearNroCliPlastico(plasticoTitular); CliSaldoActual saldoActual = new
 * CliSaldoActual(individuoEvaluacion); CliSituacionLog situacionLog = new CliSituacionLog(individuoEvaluacion,"AN"); CliCobSitLog cobSitLog = new
 * CliCobSitLog(individuoEvaluacion);
 * 
 * CliGarante garante = null; if(individuoGarante != null) garante = new CliGarante(individuoGarante,titular.getCliente());
 * 
 * List adicionales = new ArrayList(); Iterator iter = listAdicional.iterator(); int i = 1; while (iter.hasNext()) { Object[] grupo = new Object[3];
 * // [0]CliAdicional, [1]CliPlastico, [2]CliIndividuo IndividuoEvaluacion in = (IndividuoEvaluacion) iter.next(); grupo[0] = new CliAdicional(in,
 * titular.getCliente(), new Long(i)); CliPlastico plasticoAdi = new CliPlastico(in,new Long(i));
 * transaccionesService.getCliPlasticoService().crearNroCliPlastico(plasticoAdi); grupo[1] = plasticoAdi; grupo[2] = new
 * CliIndividuo(titular.getCliente(),in.getIdIndividuo(),new Long(3),new Long(i),new Long(solicitud.getNroSolicitud())); // tipo ind. 3 corresponde al
 * adicional adicionales.add(grupo); i++; } log.info("Todo se construllo bien voy a grabar los objetos"); // SI TODO DE ARMO BIEN LO GRABO
 * transaccionesService.getCliClienteService().grabarCliCliente(titular); transaccionesService.getCliIndividuoService().grabarCliIndividuo( new
 * CliIndividuo(titular.getCliente(),individuoEvaluacion.getIdIndividuo(),new Long(1),new Long(0),new Long(solicitud.getNroSolicitud()))); if(garante
 * != null){ garante.setCliente(titular.getCliente()); transaccionesService.getCliGaranteService().grabarCliGarante(garante);
 * transaccionesService.getCliIndividuoService().grabarCliIndividuo( new CliIndividuo(titular.getCliente(),individuoGarante.getIdIndividuo(),new
 * Long(2),new Long(1),new Long(solicitud.getNroSolicitud()))); } titular2.setCliente(titular.getCliente());
 * transaccionesService.getCliCliente2Service().grabarCliCliente2(titular2); marca.setCliente(titular.getCliente());
 * transaccionesService.getCliMarcaService().grabarCliMarca(marca); plasticoTitular.setCliente(titular.getCliente());
 * transaccionesService.getCliPlasticoService().grabarCliPlastico(plasticoTitular); saldoActual.setCliente(titular.getCliente());
 * transaccionesService.getCliSaldoActualService().grabarCliSaldoActual(saldoActual); situacionLog.setCliente(titular.getCliente());
 * transaccionesService.getCliSituacionLogService().grabarCliSituacionLog(situacionLog); cobSitLog.setCliente(titular.getCliente());
 * transaccionesService.getCliCobSitLogService().grabarCliCobSitLog(cobSitLog); Iterator iterAdi = adicionales.iterator(); while (iterAdi.hasNext()) {
 * Object[] grupo = (Object[]) iterAdi.next(); CliAdicional cliAdicional = (CliAdicional)grupo[0]; cliAdicional.setCliente(titular.getCliente());
 * transaccionesService.getCliAdicionalService().grabarCliAdicional(cliAdicional); CliPlastico plasticoAdi = (CliPlastico)grupo[1];
 * plasticoAdi.setCliente(titular.getCliente()); transaccionesService.getCliPlasticoService().grabarCliPlastico(plasticoAdi); CliIndividuo
 * cliIndividuo = (CliIndividuo)grupo[2]; cliIndividuo.setCliente(titular.getCliente());
 * transaccionesService.getCliIndividuoService().grabarCliIndividuo(cliIndividuo); } // voy grabando los logs de los individuos // solicitud Estados
 * estados = evaluacionService.getEstadosService().leerEstados(new Long(5)); solicitud.setEstados(estados); solicitud.setTimestamp(new
 * Timestamp(Calendar.getInstance().getTime().getTime())) ; evaluacionService.getSolicitudService().actualizarSolicitud(solicitud); // titular
 * evaluacionService.getSolicLogService().grabarSolicLog( new SolicLog(estados,Session.getOperador().getCodigo(),solicIndividuoTitular)); // garante
 * if (solicIndividuoGarante != null){ evaluacionService.getSolicLogService().grabarSolicLog( new
 * SolicLog(estados,Session.getOperador().getCodigo(),solicIndividuoGarante)); } // adicionales Iterator iterSolic = listSolicAdicional.iterator();
 * while (iterSolic.hasNext()) { SolicitudIndividuo solicIndAdic = (SolicitudIndividuo)iterSolic.next();
 * evaluacionService.getSolicLogService().grabarSolicLog( new SolicLog(estados,Session.getOperador().getCodigo(),solicIndAdic)); }
 * popup.setPopup(popup.ICONO_OK, "El cliente ha sido almacenado exitosamente."); } else {
 * error.agregar("Error. El individuo ya es tutular de una cuenta."); }
 */

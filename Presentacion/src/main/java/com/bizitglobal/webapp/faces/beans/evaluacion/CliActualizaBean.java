package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliClienteDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliCliente2;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliIndividuo;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliMarca;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class CliActualizaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CliActualizaBean.class);
	// private String obsCred;
	// private BigDecimal limiteCred;
	private Long idCliClienteHidden;
	private String cuil;
	private CliMarca marca = new CliMarca();
	private CliCliente titular;
	// private Solicitud solicitud;

	private IndividuoEvaluacion individuo;
	// private SolicitudIndividuo solicIndividuoTitular;
	// private IndividuoEvaluacion individuoGarante;
	// private SolicitudIndividuo solicIndividuoGarante;
	// private StringBuffer adicionales = new StringBuffer();

	// definicion de un list del objeto base
	private List cliclienteList;
	// private List listAdicional = new ArrayList();
	// private List listSolicAdicional = new ArrayList();

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public CliActualizaBean() {
		super();
		borrar();
	}


	public CliMarca getMarca() {
		return marca;
	}


	public void setMarca(CliMarca marca) {
		this.marca = marca;
	}


	/*
	 * public String getObsCred() { return obsCred; } public void setObsCred(String obsCred) { this.obsCred = obsCred; } public BigDecimal
	 * getLimiteCred() { return limiteCred; } public void setLimiteCred(BigDecimal limiteCred) { this.limiteCred = limiteCred; }
	 */public Long getIdCliClienteHidden() {
		return idCliClienteHidden;
	}


	public void setIdCliClienteHidden(Long idCliClienteHidden) {
		this.idCliClienteHidden = idCliClienteHidden;
	}


	public IndividuoEvaluacion getIndividuo() {
		return individuo;
	}


	public void setIndividuo(IndividuoEvaluacion individuo) {
		this.individuo = individuo;
	}


	public List getCliClienteList() {
		return cliclienteList;
	}


	public void setCliClienteList(List object) {
		this.cliclienteList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public String getCuil() {
		return cuil;
	}


	public void setCuil(String cuil) {
		this.cuil = cuil;
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
		String paraCuil = null;
		try {
			paraCuil = (String) param.get("cuil");
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		} catch (NumberFormatException e2) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		}
		this.cuil = paraCuil;
		if (validar()) {
			try {
				leerIndividuos();
				if (individuo != null) {
					alta = false;
					List cliIndList = transaccionesService.getCliIndividuoService().getCliIndividuo(
							new Filtro(CliIndividuo.ID_INDIVIDUO, Filtro.IGUAL, individuo.getIdIndividuo()));
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
			} catch (IndividuoEvaluacionException e) {
				error.agregar("Error al cargar los datos.");
				e.printStackTrace();
			} catch (Exception e) {
				error.agregar("Error al cargar los datos.");
				e.printStackTrace();
			}
		}
	}


	private void leerIndividuos() throws IndividuoEvaluacionException {
		individuo = evaluacionService.getIndividuoEvaluacionService().buscarIndividuo(cuil);
		IndividuoEvaluacion in = individuo;
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


	private void cargarItems() {

	}


	public String grabar() {
		try {
			if (validarGrabar()) {
				leerIndividuos();
				// ARMO EL CLIENTE Y CLIENTE2 // OJO QUE SI SE LLEGA A MODIFICAR LA ESTRUCTURA DE GARANTES HAY QUE CAMBIAR ESTE CODIGO
				CliCliente titularAGrabar = new CliCliente(individuo, titular.getNroSolic());// Tengo que duplicar el objeto para conservar los
																								// valores viejos
				titularAGrabar.setCliente(titular.getCliente());

				CliCliente2 titular2 = new CliCliente2(individuo);
				// CliCredPend credPend = new CliCredPend(marca,limiteCred,Session.getOperador());

				log.info("Todo se construllo bien voy a grabar los objetos");
				// SI TODO DE ARMO BIEN LO GRABO
				transaccionesService.getCliClienteService().actualizarCliCliente(titularAGrabar);
				titular2.setCliente(titular.getCliente());
				transaccionesService.getCliCliente2Service().actualizarCliCliente2(titular2);
				// transaccionesService.getCliCredPendService().grabarCliCredPend(credPend);
				// marca.setLimiteCredito(limiteCred);
				marca.setLimiteFecha(Calendar.getInstance().getTime());
				// En el obj marca solo modifico el limite y la fecha de este, los otros datos a mi forma de ver se deben modificar en otro proceso
				transaccionesService.getCliMarcaService().actualizarCliMarca(marca);
				popup.setPopup(popup.ICONO_OK, "El cliente ha sido modificado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (IndividuoEvaluacionException e) {
			error.agregar("Error al cargar los datos.");
			e.printStackTrace();
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
		tituloCorto = "Actualiza Cliente";

		// obsCred = "";
		// limiteCred = new BigDecimal(0);
		idCliClienteHidden = new Long(0);
		// nroSolicitud = "";
		// solicitud = null;
		// individuoEvaluacion = null;
		// solicIndividuoTitular = null;
		// individuoGarante = null;
		// solicIndividuoGarante = null;
		// listAdicional.clear();
		// listSolicAdicional.clear();
		marca = null;
		cliclienteList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String irAIndividuo() {
		IndividuoPopupBean bean = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");
		Map param = new HashMap();
		param.put("idIndividuo", individuo.getIdIndividuo());
		param.put("origen", "1");
		param.put("editDatos", "true");
		param.put("editDomicilio", "true");
		param.put("editTelefono", "true");
		param.put("editEmail", "true");
		param.put("editFamilia", "true");
		param.put("editActividad", "true");
		param.put("editFacturacion", "true");
		param.put("editFinanciero", "true");
		param.put("editPropiedades", "true");
		param.put("editDigitales", "true");
		bean.inicializarParametros(param);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/IndividuoPopup.jsf";
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
		// if (limiteCred == null || limiteCred.equals(new BigDecimal(0)))
		// error.agregar("El limite de credito es un dato requerido.");

		try {
			if (transaccionesService.getCliClienteService().noExisteCliCliente(
					individuo.getCuil())) {
				error.agregar("El ciente que se desea modificar no existe en el sistema.");
			}
		} catch (CliClienteException e) {
			error.agregar("No se pudo verificar la existancia del cliente, no se puede continuar.");
			e.printStackTrace();
		}

		return !error.hayErrores();
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");

		// if (nroSolicitud == null || nroSolicitud.equals(new String("")))
		// error.agregar(Error.EVA_NRO_SOLICITUD_REQUERIDA);
		//
		// if (nroSolicitud == null || nroSolicitud.length() != 8)
		// error.agregar(Error.EVA_NRO_SOLICITUD_OCHODIGITOS);

		// if (!validarDigitoVerificador()) {
		// error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);
		// }
		return !error.hayErrores();
	}


	private boolean validarDigitoVerificador() {

		// if (nroSolicitud != null && !nroSolicitud.equals(new String(""))&& nroSolicitud.length() == 8) {
		// String numero = nroSolicitud.substring(0, 7);
		// String dv = nroSolicitud.substring(7);
		// boolean digitoV = NroVerificador.esValido(numero, dv);
		// log.info("Nro Verificador: " + digitoV);
		// return digitoV;
		// }
		return false;
	}

}

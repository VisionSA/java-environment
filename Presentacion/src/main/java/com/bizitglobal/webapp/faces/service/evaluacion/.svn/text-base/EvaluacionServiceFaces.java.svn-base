package com.bizitglobal.webapp.faces.service.evaluacion;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.evaluacion.dao.EducacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.service.ActividadEvaluacionService;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaSolicitudService;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaTipoIndividuoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.BancosService;
import com.bizitglobal.tarjetafiel.evaluacion.service.CambioDiaPagoHistoricoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ClearingService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ConfirmacionTelefonicaService;
import com.bizitglobal.tarjetafiel.evaluacion.service.DiaPagoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.DigitalService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EducacionService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EmailsService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EsquemaIndividuoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EstadosService;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoDomicilioService;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoEvaluacionService;
import com.bizitglobal.tarjetafiel.evaluacion.service.IndividuoVehiculoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.InfoParticularVehiculoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.InformeLaboralService;
import com.bizitglobal.tarjetafiel.evaluacion.service.InformeParticularService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoLaboralService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoSucursalService;
import com.bizitglobal.tarjetafiel.evaluacion.service.PromotorService;
import com.bizitglobal.tarjetafiel.evaluacion.service.PromotorTelefonoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicLogService;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudImprimibleService;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudIndividuoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudService;
import com.bizitglobal.tarjetafiel.evaluacion.service.TarjetaService;
import com.bizitglobal.tarjetafiel.evaluacion.service.TclienteService;
import com.bizitglobal.tarjetafiel.evaluacion.service.TelefonosService;
import com.bizitglobal.tarjetafiel.evaluacion.service.TipoClearingService;
import com.bizitglobal.tarjetafiel.evaluacion.service.TipoIndividuoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.VerificadorService;
import com.bizitglobal.tarjetafiel.evaluacion.service.VerificadorTelefonoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ViviendaEstadoService;
import com.bizitglobal.webapp.faces.service.BaseService;


/**
 * @author agustin
 * 
 */
public class EvaluacionServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(EvaluacionServiceFaces.class);

	private static final String ACTIVIDAD_EVALUACION_SERVICE_NAME = "actividadEvaluacionService";
	private static final String ALERTA_SOLICITUD_SERVICE_NAME = "alertaSolicitudService";
	private static final String ALERTA_TIPO_INDIVIDUO_SERVICE_NAME = "alertaTipoIndividuoService";
	private static final String BANCOS_SERVICE_NAME = "bancosService";
	private static final String CLARING_SERVICE_NAME = "clearingService";
	private static final String CONFIRMACION_TELEFONICA_SERVICE_NAME = "confirmacionTelefonicaService";
	private static final String DIA_PAGO_SERVICE_NAME = "diaPagoService";
	private static final String DIGITAL_SERVICE_NAME = "digitalService";
	private static final String EDUCACION_SERVICE_NAME = "educacionService";
	private static final String EDUCACION_DAO_NAME = "educacionDao";
	private static final String EMAILS_SERVICE_NAME = "emailsService";
	private static final String ESTADOS_SERVICE_NAME = "estadosService";
	private static final String INDIVIDUO_VEICULO_SERVICE_NAME = "individuoVehiculoService";
	private static final String INDIVIDUO_DOMICILIOS_SERVICE_NAME = "individuoDomiciliosService";
	private static final String INDIVIDUO_EVALUACION_SERVICE_NAME = "individuoEvaluacionService";
	private static final String INFORME_LABORAL_SERVICE_NAME = "informeLaboralService";
	private static final String INFORME_PARTICULAR_SERVICE_NAME = "informeParticularService";
	private static final String OBSERVO_LABORAL_SERVICE_NAME = "observoLaboralService";
	private static final String OBSERVO_SUCURSAL_SERVICE_NAME = "observoSucursalService";
	private static final String OBSERVO_SERVICE_NAME = "observoService";
	private static final String PROMOTOR_TELEFONO_SERVICE_NAME = "promotorTelefonoService";
	private static final String PROMOTOR_SERVICE_NAME = "promotorService";
	private static final String SOLICITUD_INDIVIDUO_SERVICE_NAME = "solicitudIndividuoService";
	private static final String SOLICITUD_SERVICE_NAME = "solicitudService";
	private static final String SOLIC_LOG_SERVICE_NAME = "solicLogService";
	private static final String TARJETA_SERVICE_NAME = "tarjetaService";
	private static final String TELEFONOS_SERVICE_NAME = "telefonosService";
	private static final String TIPO_CLEARING_SERVICE_NAME = "tipoClearingService";
	private static final String TIPO_INDIVIDUO_SERVICE_NAME = "tipoIndividuoService";
	private static final String VERIFICADOR_TELEFONO_SERVICE_NAME = "verificadorTelefonoService";
	private static final String VERIFICADOR_SERVICE_NAME = "verificadorService";
	private static final String VIVIENDA_ESTADO_SERVICE_NAME = "viviendaEstadoService";
	private static final String SOLICITUD_DAO_NAME = "solicitudDao";
	private static final String SOLICITUD_IMPRIMIBLE_SERVICE_NAME = "solicitudImprimibleService";
	private static final String INFORME_PARTICULAR_VEHICULO = "infoParticularVehiculoService";
	private static final String ESQUEMA_INDIVIDUO_SERVICE = "esquemaIndividuoService";
	private static final String TCLIENTE_SERVICE_NAME = "tclienteService";
	private static final String CAMBIO_DIA_PAGO_HISTORICO = "cambioDiaPagoHistoricoService";

	/*
	 * Objetos services para el modulo.
	 */

	private ActividadEvaluacionService actividadEvaluacionService;
	private AlertaSolicitudService alertaSolicitudService;
	private AlertaTipoIndividuoService alertaTipoIndividuoService;
	private BancosService bancosService;
	private ClearingService clearingService;
	private ConfirmacionTelefonicaService confirmacionTelefonicaService;
	private DiaPagoService diaPagoService;
	private DigitalService digitalService;
	private EducacionDao educacionDao;
	private EducacionService educacionService;
	private EmailsService emailsService;
	private EstadosService estadosService;
	private IndividuoVehiculoService individuoVehiculoService;
	private IndividuoDomicilioService individuoDomicilioService;
	private IndividuoEvaluacionService individuoEvaluacionService;
	private InformeLaboralService informeLaboralService;
	private InformeParticularService informeParticularService;
	private ObservoLaboralService observoLaboralService;
	private ObservoSucursalService observoSucursalService;
	private ObservoService observoService;
	private PromotorTelefonoService promotorTelefonoService;
	private PromotorService promotorService;
	private SolicitudIndividuoService solicitudIndividuoService;
	private SolicitudService solicitudService;
	private SolicLogService solicLogService;
	private TarjetaService tarjetaService;
	private TelefonosService telefonosService;
	private TipoClearingService tipoClearingService;
	private TipoIndividuoService tipoIndividuoService;
	private VerificadorTelefonoService verificadorTelefonoService;
	private VerificadorService verificadorService;
	private ViviendaEstadoService viviendaEstadoService;
	private SolicitudDao solicitudDao;
	private SolicitudImprimibleService solicitudImprimibleService;
	private InfoParticularVehiculoService infoParticularVehiculoService;
	private EsquemaIndividuoService esquemaIndividuoService;
	private TclienteService tclienteService;
	private CambioDiaPagoHistoricoService cambioDiaPagoHistoricoService;


	public EvaluacionServiceFaces() {
		super();
		log.info("Construyendo el service de evaluacion!!!");

		this.actividadEvaluacionService = (ActividadEvaluacionService) this.lookupService(ACTIVIDAD_EVALUACION_SERVICE_NAME);
		this.alertaSolicitudService = (AlertaSolicitudService) this.lookupService(ALERTA_SOLICITUD_SERVICE_NAME);
		this.alertaTipoIndividuoService = (AlertaTipoIndividuoService) this.lookupService(ALERTA_TIPO_INDIVIDUO_SERVICE_NAME);
		this.bancosService = (BancosService) this.lookupService(BANCOS_SERVICE_NAME);
		this.clearingService = (ClearingService) this.lookupService(CLARING_SERVICE_NAME);
		this.confirmacionTelefonicaService = (ConfirmacionTelefonicaService) this.lookupService(CONFIRMACION_TELEFONICA_SERVICE_NAME);
		this.diaPagoService = (DiaPagoService) this.lookupService(DIA_PAGO_SERVICE_NAME);
		this.digitalService = (DigitalService) this.lookupService(DIGITAL_SERVICE_NAME);
		this.educacionDao = (EducacionDao) this.lookupService(EDUCACION_DAO_NAME);
		this.educacionService = (EducacionService) this.lookupService(EDUCACION_SERVICE_NAME);
		this.emailsService = (EmailsService) this.lookupService(EMAILS_SERVICE_NAME);
		this.estadosService = (EstadosService) this.lookupService(ESTADOS_SERVICE_NAME);
		this.individuoDomicilioService = (IndividuoDomicilioService) this.lookupService(INDIVIDUO_DOMICILIOS_SERVICE_NAME);
		this.individuoEvaluacionService = (IndividuoEvaluacionService) this.lookupService(INDIVIDUO_EVALUACION_SERVICE_NAME);
		this.individuoVehiculoService = (IndividuoVehiculoService) this.lookupService(INDIVIDUO_VEICULO_SERVICE_NAME);
		this.informeLaboralService = (InformeLaboralService) this.lookupService(INFORME_LABORAL_SERVICE_NAME);
		this.informeParticularService = (InformeParticularService) this.lookupService(INFORME_PARTICULAR_SERVICE_NAME);
		this.observoLaboralService = (ObservoLaboralService) this.lookupService(OBSERVO_LABORAL_SERVICE_NAME);
		this.observoService = (ObservoService) this.lookupService(OBSERVO_SERVICE_NAME);
		this.observoSucursalService = (ObservoSucursalService) this.lookupService(OBSERVO_SUCURSAL_SERVICE_NAME);
		this.promotorService = (PromotorService) this.lookupService(PROMOTOR_SERVICE_NAME);
		this.promotorTelefonoService = (PromotorTelefonoService) this.lookupService(PROMOTOR_TELEFONO_SERVICE_NAME);
		this.solicitudIndividuoService = (SolicitudIndividuoService) this.lookupService(SOLICITUD_INDIVIDUO_SERVICE_NAME);
		this.solicitudService = (SolicitudService) this.lookupService(SOLICITUD_SERVICE_NAME);
		this.solicLogService = (SolicLogService) this.lookupService(SOLIC_LOG_SERVICE_NAME);
		this.tarjetaService = (TarjetaService) this.lookupService(TARJETA_SERVICE_NAME);
		this.telefonosService = (TelefonosService) this.lookupService(TELEFONOS_SERVICE_NAME);
		this.tipoClearingService = (TipoClearingService) this.lookupService(TIPO_CLEARING_SERVICE_NAME);
		this.tipoIndividuoService = (TipoIndividuoService) this.lookupService(TIPO_INDIVIDUO_SERVICE_NAME);
		this.verificadorService = (VerificadorService) this.lookupService(VERIFICADOR_SERVICE_NAME);
		this.verificadorTelefonoService = (VerificadorTelefonoService) this.lookupService(VERIFICADOR_TELEFONO_SERVICE_NAME);
		this.viviendaEstadoService = (ViviendaEstadoService) this.lookupService(VIVIENDA_ESTADO_SERVICE_NAME);
		this.solicitudDao = (SolicitudDao) this.lookupService(SOLICITUD_DAO_NAME);
		this.solicitudImprimibleService = (SolicitudImprimibleService) this.lookupService(SOLICITUD_IMPRIMIBLE_SERVICE_NAME);
		this.infoParticularVehiculoService = (InfoParticularVehiculoService) this.lookupService(INFORME_PARTICULAR_VEHICULO);
		this.esquemaIndividuoService = (EsquemaIndividuoService) this.lookupService(ESQUEMA_INDIVIDUO_SERVICE);
		this.tclienteService = (TclienteService) this.lookupService(TCLIENTE_SERVICE_NAME);
		this.cambioDiaPagoHistoricoService = (CambioDiaPagoHistoricoService) this.lookupService(CAMBIO_DIA_PAGO_HISTORICO);
	}


	public SolicitudImprimibleService getSolicitudImprimibleService() {
		return solicitudImprimibleService;
	}


	public SolicitudDao getSolicitudDao() {
		return solicitudDao;
	}


	public ActividadEvaluacionService getActividadEvaluacionService() {
		return actividadEvaluacionService;
	}


	public AlertaSolicitudService getAlertaSolicitudService() {
		return alertaSolicitudService;
	}


	public AlertaTipoIndividuoService getAlertaTipoIndividuoService() {
		return alertaTipoIndividuoService;
	}


	public BancosService getBancosService() {
		return bancosService;
	}


	public ClearingService getClearingService() {
		return clearingService;
	}


	public ConfirmacionTelefonicaService getConfirmacionTelefonicaService() {
		return confirmacionTelefonicaService;
	}


	public DiaPagoService getDiaPagoService() {
		return diaPagoService;
	}


	public DigitalService getDigitalService() {
		return digitalService;
	}


	public EducacionDao getEducacionDao() {
		return educacionDao;
	}


	public EducacionService getEducacionService() {
		return educacionService;
	}


	public EmailsService getEmailsService() {
		return emailsService;
	}


	public EstadosService getEstadosService() {
		return estadosService;
	}


	public IndividuoDomicilioService getIndividuoDomicilioService() {
		return individuoDomicilioService;
	}


	public IndividuoEvaluacionService getIndividuoEvaluacionService() {
		return individuoEvaluacionService;
	}


	public IndividuoVehiculoService getIndividuoVehiculoService() {
		return individuoVehiculoService;
	}


	public InformeLaboralService getInformeLaboralService() {
		return informeLaboralService;
	}


	public InformeParticularService getInformeParticularService() {
		return informeParticularService;
	}


	public ObservoLaboralService getObservoLaboralService() {
		return observoLaboralService;
	}


	public ObservoService getObservoService() {
		return observoService;
	}


	public ObservoSucursalService getObservoSucursalService() {
		return observoSucursalService;
	}


	public PromotorService getPromotorService() {
		return promotorService;
	}


	public PromotorTelefonoService getPromotorTelefonoService() {
		return promotorTelefonoService;
	}


	public SolicitudIndividuoService getSolicitudIndividuoService() {
		return solicitudIndividuoService;
	}


	public SolicitudService getSolicitudService() {
		return solicitudService;
	}


	public TarjetaService getTarjetaService() {
		return tarjetaService;
	}


	public TelefonosService getTelefonosService() {
		return telefonosService;
	}


	public TipoClearingService getTipoClearingService() {
		return tipoClearingService;
	}


	public TipoIndividuoService getTipoIndividuoService() {
		return tipoIndividuoService;
	}


	public VerificadorService getVerificadorService() {
		return verificadorService;
	}


	public VerificadorTelefonoService getVerificadorTelefonoService() {
		return verificadorTelefonoService;
	}


	public ViviendaEstadoService getViviendaEstadoService() {
		return viviendaEstadoService;
	}


	public InfoParticularVehiculoService getInfoParticularVehiculoService() {
		return infoParticularVehiculoService;
	}


	public EsquemaIndividuoService getEsquemaIndividuoService() {
		return esquemaIndividuoService;
	}


	public TclienteService getTclienteService() {
		return this.tclienteService;
	}


	public SolicLogService getSolicLogService() {
		return solicLogService;
	}


	public CambioDiaPagoHistoricoService getCambioDiaPagoHistoricoService() {
		return cambioDiaPagoHistoricoService;
	}


	public void setCambioDiaPagoHistoricoService(CambioDiaPagoHistoricoService cambioDiaPagoHistoricoService) {
		this.cambioDiaPagoHistoricoService = cambioDiaPagoHistoricoService;
	}
}

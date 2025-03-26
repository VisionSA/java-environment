package com.bizitglobal.tarjetafiel.evaluacion;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ActividadEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaSolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaTipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.BancosDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ConfirmacionTelefonicaDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DiaPagoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DigitalDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EducacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EmailsDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EstadosDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoDomicilioDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InfoParticularVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeParticularDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoSucursalDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TarjetaDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TclienteDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TelefonosDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ViviendaEstadoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;
import com.bizitglobal.tarjetafiel.evaluacion.service.ActividadEvaluacionService;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaSolicitudService;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaTipoIndividuoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.BancosService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ClearingService;
import com.bizitglobal.tarjetafiel.evaluacion.service.ConfirmacionTelefonicaService;
import com.bizitglobal.tarjetafiel.evaluacion.service.DiaPagoService;
import com.bizitglobal.tarjetafiel.evaluacion.service.DigitalService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EducacionService;
import com.bizitglobal.tarjetafiel.evaluacion.service.EmailsService;
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

/**
 * Ejecuta un serie de operaciones(las definidas por la interface de servicio) que tienen el objeto de comprobar
 * que esta funcionalidad es correcta.
 */
public class TestEvaluacionService extends TestCase {
	private ApplicationContext ctx;
	/* CREACION DE OBJETOS */
	private ViviendaEstadoDao viviendaEstadoDao;
	private ViviendaEstadoService viviendaEstadoService;
	private VerificadorDao verificadorDao;
	private VerificadorService verificadorService;
	private VerificadorTelefonoDao verificadorTelefonoDao;
	private VerificadorTelefonoService verificadorTelefonoService;
	private TipoIndividuoDao tipoIndividuoDao;
	private TipoIndividuoService tipoIndividuoService;
	private TipoClearingDao tipoClearingDao;
	private TipoClearingService tipoClearingService;
	private TelefonosDao telefonosDao;
	private TelefonosService telefonosService;
	private TarjetaDao tarjetaDao;
	private TarjetaService tarjetaService;
	private SolicitudDao solicitudDao;
	private SolicitudService solicitudService;
	private SolicitudIndividuoDao solicitudIndividuoDao;
	private SolicitudIndividuoService solicitudIndividuoService;
	private PromotorDao promotorDao;
	private PromotorService promotorService;
	private PromotorTelefonoDao promotorTelefonoDao;
	private PromotorTelefonoService promotorTelefonoService;
	private ObservoDao observoDao;
	private ObservoService observoService;
	private ObservoSucursalDao observoSucursalDao;
	private ObservoSucursalService observoSucursalService;
	private ObservoLaboralDao observoLaboralDao;
	private ObservoLaboralService observoLaboralService;
	private InformeParticularDao informeParticularDao;
	private InformeParticularService informeParticularService;
	private InformeLaboralDao informeLaboralDao;
	private InformeLaboralService informeLaboralService;
	private IndividuoEvaluacionDao individuoEvaluacionDao;
	private IndividuoEvaluacionService individuoEvaluacionService;
	private IndividuoDomicilioDao individuoDomicilioDao;
	private IndividuoDomicilioService individuoDomicilioService;
	private IndividuoVehiculoDao individuoVehiculoDao;
	private IndividuoVehiculoService individuoVehiculoService;
	private EstadosDao estadosDao;
	private EstadosService estadosService;
	private EmailsDao emailsDao;
	private EmailsService emailsService;
	private EducacionDao educacionDao;
	private EducacionService educacionService;
	private DigitalDao digitalDao;
	private DigitalService digitalService;
	private DiaPagoDao diaPagoDao;
	private DiaPagoService diaPagoService;
	private ConfirmacionTelefonicaDao confirmacionTelefonicaDao;
	private ConfirmacionTelefonicaService confirmacionTelefonicaService;
	private ClearingDao clearingDao;
	private ClearingService clearingService;
	private BancosDao bancosDao;
	private BancosService bancosService;
	private AlertaTipoIndividuoDao alertaTipoIndividuoDao;
	private AlertaTipoIndividuoService alertaTipoIndividuoService;
	private AlertaSolicitudDao alertaSolicitudDao;
	private AlertaSolicitudService alertaSolicitudService;
	private ActividadEvaluacionDao actividadEvaluacionDao;
	private ActividadEvaluacionService actividadEvaluacionService;
	private InfoParticularVehiculoDao infoParticularVehiculoDao;
	private InfoParticularVehiculoService infoParticularVehiculoService;
	private TclienteDao tclienteDao;
	private TclienteService tclienteService;
	private static Logger log = Logger.getLogger(TestEvaluacionService.class);
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {		
		ctx = new FileSystemXmlApplicationContext("src/main/java/com/bizitglobal/tarjetafiel/evaluacion/evaluacionTest.xml");
		/* INSTANCIACION DE OBJETOS */
		viviendaEstadoDao = (ViviendaEstadoDao)ctx.getBean("viviendaEstadoDao");
		viviendaEstadoService = (ViviendaEstadoService)ctx.getBean("viviendaEstadoService");
		verificadorDao = (VerificadorDao)ctx.getBean("verificadorDao");
		verificadorService = (VerificadorService)ctx.getBean("verificadorService");
		verificadorTelefonoDao = (VerificadorTelefonoDao)ctx.getBean("verificadorTelefonoDao");
		verificadorTelefonoService = (VerificadorTelefonoService)ctx.getBean("verificadorTelefonoService");
		tipoIndividuoDao = (TipoIndividuoDao)ctx.getBean("tipoIndividuoDao");
		tipoIndividuoService = (TipoIndividuoService)ctx.getBean("tipoIndividuoService");
		tipoClearingDao = (TipoClearingDao)ctx.getBean("tipoClearingDao");
		tipoClearingService = (TipoClearingService)ctx.getBean("tipoClearingService");
		telefonosDao = (TelefonosDao)ctx.getBean("telefonosDao");
		telefonosService = (TelefonosService)ctx.getBean("telefonosService");
		tarjetaDao = (TarjetaDao)ctx.getBean("tarjetaDao");
		tarjetaService = (TarjetaService)ctx.getBean("tarjetaService");
		solicitudDao = (SolicitudDao)ctx.getBean("solicitudDao");
		solicitudService = (SolicitudService)ctx.getBean("solicitudService");
		solicitudIndividuoDao = (SolicitudIndividuoDao)ctx.getBean("solicitudIndividuoDao");
		solicitudIndividuoService = (SolicitudIndividuoService)ctx.getBean("solicitudIndividuoService");
		promotorDao = (PromotorDao)ctx.getBean("promotorDao");
		promotorService = (PromotorService)ctx.getBean("promotorService");
		promotorTelefonoDao = (PromotorTelefonoDao)ctx.getBean("promotorTelefonoDao");
		promotorTelefonoService = (PromotorTelefonoService)ctx.getBean("promotorTelefonoService");
		observoDao = (ObservoDao)ctx.getBean("observoDao");
		observoService = (ObservoService)ctx.getBean("observoService");
		observoSucursalDao = (ObservoSucursalDao)ctx.getBean("observoSucursalDao");
		observoSucursalService = (ObservoSucursalService)ctx.getBean("observoSucursalService");
		observoLaboralDao = (ObservoLaboralDao)ctx.getBean("observoLaboralDao");
		observoLaboralService = (ObservoLaboralService)ctx.getBean("observoLaboralService");
		informeParticularDao = (InformeParticularDao)ctx.getBean("informeParticularDao");
		informeParticularService = (InformeParticularService)ctx.getBean("informeParticularService");
		informeLaboralDao = (InformeLaboralDao)ctx.getBean("informeLaboralDao");
		informeLaboralService = (InformeLaboralService)ctx.getBean("informeLaboralService");
		individuoEvaluacionDao = (IndividuoEvaluacionDao)ctx.getBean("individuoEvaluacionDao");
		individuoEvaluacionService = (IndividuoEvaluacionService)ctx.getBean("individuoEvaluacionService");
		individuoDomicilioDao = (IndividuoDomicilioDao)ctx.getBean("individuoDomiciliosDao");
		individuoDomicilioService = (IndividuoDomicilioService)ctx.getBean("individuoDomiciliosService");
		individuoVehiculoDao = (IndividuoVehiculoDao)ctx.getBean("individuoVehiculoDao");
		individuoVehiculoService = (IndividuoVehiculoService)ctx.getBean("individuoVehiculoService");
		estadosDao = (EstadosDao)ctx.getBean("estadosDao");
		estadosService = (EstadosService)ctx.getBean("estadosService");
		emailsDao = (EmailsDao)ctx.getBean("emailsDao");
		emailsService = (EmailsService)ctx.getBean("emailsService");
		educacionDao = (EducacionDao)ctx.getBean("educacionDao");
		educacionService = (EducacionService)ctx.getBean("educacionService");
		digitalDao = (DigitalDao)ctx.getBean("digitalDao");
		digitalService = (DigitalService)ctx.getBean("digitalService");
		diaPagoDao = (DiaPagoDao)ctx.getBean("diaPagoDao");
		diaPagoService = (DiaPagoService)ctx.getBean("diaPagoService");
		confirmacionTelefonicaDao = (ConfirmacionTelefonicaDao)ctx.getBean("confirmacionTelefonicaDao");
		confirmacionTelefonicaService = (ConfirmacionTelefonicaService)ctx.getBean("confirmacionTelefonicaService");
		clearingDao = (ClearingDao)ctx.getBean("clearingDao");
		clearingService = (ClearingService)ctx.getBean("clearingService");
		bancosDao = (BancosDao)ctx.getBean("bancosDao");
		bancosService = (BancosService)ctx.getBean("bancosService");
		alertaTipoIndividuoDao = (AlertaTipoIndividuoDao)ctx.getBean("alertaTipoIndividuoDao");
		alertaTipoIndividuoService = (AlertaTipoIndividuoService)ctx.getBean("alertaTipoIndividuoService");
		alertaSolicitudDao = (AlertaSolicitudDao)ctx.getBean("alertaSolicitudDao");
		alertaSolicitudService = (AlertaSolicitudService)ctx.getBean("alertaSolicitudService");
		actividadEvaluacionDao = (ActividadEvaluacionDao)ctx.getBean("actividadEvaluacionDao");
		actividadEvaluacionService = (ActividadEvaluacionService)ctx.getBean("actividadEvaluacionService");
		infoParticularVehiculoDao = (InfoParticularVehiculoDao) ctx.getBean("infoParticularVehiculoDao");
		infoParticularVehiculoService = (InfoParticularVehiculoService) ctx.getBean("infoParticularVehiculoService");
		tclienteDao = (TclienteDao)ctx.getBean("tclienteDao");
		tclienteService = (TclienteService)ctx.getBean("tclienteService");
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		/* INICIALIZACION DE OBJETOS */
		viviendaEstadoDao = null;
		viviendaEstadoService = null;
		verificadorDao = null;
		verificadorService = null;
		verificadorTelefonoDao = null;
		verificadorTelefonoService = null;
		tipoIndividuoDao = null;
		tipoIndividuoService = null;
		tipoClearingDao = null;
		tipoClearingService = null;
		telefonosDao = null;
		telefonosService = null;
		tarjetaDao = null;
		tarjetaService = null;
//		solicitudDao = null;
		solicitudService = null;
		solicitudIndividuoDao = null;
		solicitudIndividuoService = null;
		promotorDao = null;
		promotorService = null;
		promotorTelefonoDao = null;
		promotorTelefonoService = null;
		observoDao = null;
		observoService = null;
		observoSucursalDao = null;
		observoSucursalService = null;
		observoLaboralDao = null;
		observoLaboralService = null;
		informeParticularDao = null;
		informeParticularService = null;
		informeLaboralDao = null;
		informeLaboralService = null;
		individuoEvaluacionDao = null;
		individuoEvaluacionService = null;
		individuoDomicilioDao = null;
		individuoDomicilioService = null;
		individuoVehiculoDao = null;
		individuoVehiculoService = null;
		estadosDao = null;
		estadosService = null;
		emailsDao = null;
		emailsService = null;
		educacionDao = null;
		educacionService = null;
		digitalDao = null;
		digitalService = null;
		diaPagoDao = null;
		diaPagoService = null;
		confirmacionTelefonicaDao = null;
		confirmacionTelefonicaService = null;
		clearingDao = null;
		clearingService = null;
		bancosDao = null;
		bancosService = null;
		alertaTipoIndividuoDao = null;
		alertaTipoIndividuoService = null;
		alertaSolicitudDao = null;
		alertaSolicitudService = null;
		actividadEvaluacionDao = null;
		actividadEvaluacionService = null;
		infoParticularVehiculoDao = null;
		infoParticularVehiculoService = null;
		tclienteDao = null;
		tclienteService = null;
	}
	
	/**
	 * Test propiamente dicho, en este metodo se encuentra el codigo a testear.
	 * @throws Exception
	 */
		public void testABM() throws Exception {
		log.info("");
		log.info("");
		log.info("");
		log.info("");
		log.info("Iniciando el test...");
		
		log.info("iniciando prueba!!!");
		try {
			tearDown();
//			log.info("Tamos dentro del Try - Catch!!!");
//			
//			log.info("Tamos por generar el filtro");
//			Filtro filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, new String("1000001"));
//			log.info("Ya creamos el filtro.");
//			
//			log.info("Tamos por llamar al dao para q nos traiga la solicitud");
//			Solicitud solicitud = solicitudService.busNroComprobante(filtro);
//			log.info("Ya trajimos la solicitud");
//			
//			log.info("Tamos por verificar si la solicitud viene vacía o no");
//			if(solicitud != null){
//				log.info("La solicitud no esta vacía");
//				
//				Estados estados = solicitud.getEstados();
//				Promotor promotor = solicitud.getPromotor();
//				
//				log.info("Solicitud: " +  solicitud.toString());
//			}else{
//				log.info("Solicitud nula");
//			}
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("*********************************************");
		log.info("OBTENIENDO NRO DE SOLICITUD");
		log.info("*********************************************");
		
		Long nroSolicitud = solicitudDao.maxNroSolicitud();
		log.info("El nro maximo es: " + nroSolicitud);
		
		log.info("*********************************************");
		log.info("OBTENIENDO OPERADORES DESDE LA BASE DE DATOS");
		log.info("*********************************************");
		int cont = 0;
		try {
			List aux = tclienteService.getTcliente(new Filtro());
			if(!aux.isEmpty()){
				log.info("Tamaño Lista == >" + aux.size());
//				Iterator iterator = aux.iterator();
//				while (iterator.hasNext()) {
//					Tcliente element = (Tcliente) iterator.next();
//					cont++;
//					log.info("Codigo: " + element.getCliente());
//					log.info("Nombre: " + element.getEmpresa());
//					log.info("Cargo: " + element.getCargo());
//					if (cont == 5)
//						break;
//				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			
		log.info("Fin del test.");
	}
	
	/**
	 * Ejecuta el test.
	 * @param args, nada.
	 */
	public static void main (String[] args)	{
		//junit.textui.TestRunner.run(TestOperadorService.class);
	}
}

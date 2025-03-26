package com.bizitglobal.tarjetafiel.planificadoremail.jobs;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.quartz.StatefulJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.ui.web.QuartzAppContext;
import org.springframework.context.ApplicationContext;

import javax.mail.Session;

import com.bizitglobal.tarjetafiel.planificadoremail.negocio.EmailInCola;
import com.bizitglobal.tarjetafiel.planificadoremail.service.EnvioEmailService;
import com.bizitglobal.tarjetafiel.planificadoremail.service.RegistrationService;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteComercio;
import com.bizitglobal.tarjetafiel.general.dao.IGenericDao;


public class PlanificadorEmailJob implements StatefulJob {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String ENVIO_EMAIL_SERVICE_NAME = "envioEmailService";
	private IGenericDao genericDao;
	private EnvioEmailService envioEmailService = null;

	@SuppressWarnings("unused")
	private final Properties properties = new Properties();

	@SuppressWarnings("unused")
	private String password;

	@SuppressWarnings("unused")
	private Session session;


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext app = QuartzAppContext.getInstance().getApplicationContext();
		RegistrationService registrationService = (RegistrationService) app.getBean("registrationService");

		envioEmailService = (EnvioEmailService) app.getBean(ENVIO_EMAIL_SERVICE_NAME);
		EmailInCola emailInCola = new EmailInCola();

		try {
		//	List<EmailInCola> emailInColas = envioEmailService.buscarTodosEmail();
		//	Iterator<EmailInCola> iterator = emailInColas.iterator();
			
//			while (iterator.hasNext()) {
//				CtaCteComercio ctaCteComercio = iterator.next();
//				importe += ctaCteComercio.getImporte().multiply(cien)
//						.longValue();
//			}
			
			emailInCola = envioEmailService.buscarNextEmail();
				while (emailInCola != null) {
		//			while (iterator.hasNext()) {
//						emailInCola = iterator.next();
					// emailInCola.setEstado(EmailInCola.ESTADO_EMAIL_COLA);
					// envioEmailService.actualizarEmailInCola(emailInCola);

//					emailInCola.setEstado(EmailInCola.ESTADO_EMAIL_INICIADO);
//					envioEmailService.actualizarEmailInCola(emailInCola);

					String emailValor = registrationService.sendConfirmationEmail(emailInCola);
					
					if (emailValor == EmailInCola.ESTADO_EMAIL_ENVIADO ) {
						emailInCola.setEstado(EmailInCola.ESTADO_EMAIL_ENVIADO);					
					} else {
						emailInCola.setEstado(EmailInCola.ESTADO_EMAIL_REBOTADO);
					}

					emailInCola.setFecEnvio(new Date());
				//	emailInCola.setEstado(EmailInCola.ESTADO_EMAIL_ENVIADO);
					emailInCola.setPendiente(false);
					envioEmailService.actualizarEmailInCola(emailInCola);
					
					emailInCola = envioEmailService.buscarNextEmail();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public IGenericDao getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}

}

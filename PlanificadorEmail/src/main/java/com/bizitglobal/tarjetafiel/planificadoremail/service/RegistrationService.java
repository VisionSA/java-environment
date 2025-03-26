package com.bizitglobal.tarjetafiel.planificadoremail.service;

import com.bizitglobal.tarjetafiel.planificadoremail.negocio.EmailInCola;


/***** @Id:6958 ******/
public interface RegistrationService {

	public String sendConfirmationEmail(EmailInCola emailInCola);
	
}

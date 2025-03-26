package com.bizitglobal.tarjetafiel.planificadoremail.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.planificadoremail.exception.EnvioEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.ImagenEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.EmailInCola;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.ImagenEmail;


/***** @Id:6958 ******/
public interface EnvioEmailService {

	/**
	 * Busca todos los email
	 */
	public List findAll() throws EnvioEmailException;
	public EmailInCola leerEnvioEmail(Long id) throws EnvioEmailException;
	public Boolean correrPlanEnviarLiqByEmail(Long idPlan) throws EnvioEmailException;
	public EmailInCola buscarNextEmail() throws EnvioEmailException;
	public Boolean actualizarEmailInCola(EmailInCola emailInCola) throws EnvioEmailException;
	public ImagenEmail BuscarImagenEmail(Long idImagen) throws ImagenEmailException;
	public EmailInCola buscarNextNotificacion() throws EnvioEmailException;
	public List<EmailInCola> buscarTodosEmail();
	
}

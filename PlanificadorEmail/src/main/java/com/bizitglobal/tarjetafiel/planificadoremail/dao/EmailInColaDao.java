package com.bizitglobal.tarjetafiel.planificadoremail.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.planificadoremail.negocio.EmailInCola;


/***** @Id:6958 ******/
public interface EmailInColaDao {

	public EmailInCola buscarNextEmail();
	
	public List <EmailInCola> buscarTodosEmail();


	public EmailInCola buscarNextNotificacion();


	public List buscarNotificacionApp(final String registo, final int tipoNotificacion);

}

package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;

public class Emails implements Negocio {
	private Long idEmails = new Long(0);
	private Email email;
	private IndividuoEvaluacion individuoEvaluacion;

	public Emails() {
		this(null,new Email(),null);
	}
	
	public Emails(Long idEmail) {
		this(idEmail,new Email(),null);
	}

	public Emails(Long idEmails, Email email, IndividuoEvaluacion individuoEvaluacion) {
		super();
		this.idEmails = idEmails;
		this.email = email;
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public Long getId() {
		return idEmails;
	} 

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Email getEmail() {
		return email;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}
	
	public Long getIdEmails() {
		return idEmails;
	}

	public void setIdEmails(Long idEmails) {
		this.idEmails = idEmails;
	}
	
	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}

	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public String toString() {
		
		return "Emails: " +
				"id: " + idEmails +
				", email: " + email.toString() +
				", individuoEvaluacion: " + individuoEvaluacion.toString();
	}

}


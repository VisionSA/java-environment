package com.bizitglobal.tarjetafiel.planificadoremail.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.TemplateEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.Template;


public interface TemplateEmailService {

	public List<Template> find(final Filtro filtro)
			throws TemplateEmailException;

	public void grabarTemplate(Template template) throws TemplateEmailException;

	public void actualizarTemplate(Template template)
			throws TemplateEmailException;

}

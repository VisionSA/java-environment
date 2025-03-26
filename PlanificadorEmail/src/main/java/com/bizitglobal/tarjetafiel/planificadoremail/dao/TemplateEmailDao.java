package com.bizitglobal.tarjetafiel.planificadoremail.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.Template;


public interface TemplateEmailDao {

	public List<Template> find(Filtro filtro);


	public void grabarTemplate(Template template);


	public void actualizarTemplate(Template template);
}

package com.bizitglobal.tarjetafiel.planificadoremail.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.TipoParamTemp;


public interface TipoParamTempEmailDao {

	public List<TipoParamTemp> find(Filtro filtro);
}

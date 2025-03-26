package com.bizitglobal.tarjetafiel.impuestos.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;

public interface ImpuestosIndividuoDao {
	
    public void grabarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo);
	
	public ImpuestosIndividuo buscarImpuestosIndividuo(Long id);
	
	public void borrarImpuestosIndividuo(Long id);
	
	public void borrarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo);
	
	public List listarTodos(Filtro unFiltro);
	
	
}

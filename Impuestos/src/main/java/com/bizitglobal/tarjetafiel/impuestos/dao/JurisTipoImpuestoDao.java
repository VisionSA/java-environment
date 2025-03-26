package com.bizitglobal.tarjetafiel.impuestos.dao;

import java.util.List;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface JurisTipoImpuestoDao {
	
	
	public void grabarJurisTipoImpuesto(JurisTipoImpuesto jurisTipoImpuesto);
	
	public JurisTipoImpuesto buscarJurisTipoImpuesto(Long idJurisTipoImpuesto);
    
	public void borrarJurisTipoImpuesto(Long idJurisTipoImpuesto);
	
	public List listarTodos(Filtro unFiltro);
	
	public void actualizarJurisTipoImpuesto(JurisTipoImpuesto jurisTipoImpuesto);
	
}

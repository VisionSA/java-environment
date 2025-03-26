package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Clearing;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ClearingDao {
	public void grabarEvaClearings (Clearing pObject);
	public Clearing buscarEvaClearings (Long id);
	public void borrarEvaClearings (Long id);
	public void borrarEvaClearings (Clearing pObject);
	public void actualizarEvaClearings (Clearing pObject);
	public List listarTodos(Filtro filtro);
}


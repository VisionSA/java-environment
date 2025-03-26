package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface BancosDao {
	public void grabarEvaBancos (Bancos pObject);
	public Bancos buscarEvaBancos (Long id);
	public void borrarEvaBancos (Long id);
	public void borrarEvaBancos (Bancos pObject);
	public void actualizarEvaBancos (Bancos pObject);
	public List listarTodos(Filtro filtro);
}


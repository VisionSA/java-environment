package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface TelefonosDao {
	public void grabarEvaTelefonos (Telefonos pObject);
	public Telefonos buscarEvaTelefonos (Long id);
	public void borrarEvaTelefonos (Long id);
	public void borrarEvaTelefonos (Telefonos pObject);
	public void actualizarEvaTelefonos (Telefonos pObject);
	public List listarTodos(Filtro filtro);
	public boolean tieneTelefono(Long idCliente);
}


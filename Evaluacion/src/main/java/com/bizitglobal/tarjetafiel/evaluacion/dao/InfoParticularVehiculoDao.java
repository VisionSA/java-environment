package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InfoParticularVehiculo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface InfoParticularVehiculoDao {
	public void grabarInfoParticularVehiculo (InfoParticularVehiculo pObject);
	public InfoParticularVehiculo buscarInfoParticularVehiculo (Long id);
	public void borrarInfoParticularVehiculo (Long id);
	public void borrarInfoParticularVehiculo (InfoParticularVehiculo pObject);
	public void actualizarInfoParticularVehiculo (InfoParticularVehiculo pObject);
	public List listarTodos(Filtro filtro);
}


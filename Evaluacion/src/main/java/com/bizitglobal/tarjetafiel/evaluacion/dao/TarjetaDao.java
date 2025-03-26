package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface TarjetaDao {
	public void grabarEvaTarjetas (Tarjeta pObject);
	public Tarjeta buscarEvaTarjetas (Long id);
	public void borrarEvaTarjetas (Long id);
	public void borrarEvaTarjetas (Tarjeta pObject);
	public void actualizarEvaTarjetas (Tarjeta pObject);
	public List listarTodos(Filtro filtro);
}


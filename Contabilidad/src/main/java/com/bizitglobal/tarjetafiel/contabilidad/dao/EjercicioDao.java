package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;

public interface EjercicioDao {
	public void grabarEjercicio (Ejercicio pObject);
	public Ejercicio buscarEjercicio (Integer id);
	public void borrarEjercicio (Integer id);
	public void borrarEjercicio(Ejercicio pObject);
	public void actualizarEjercicio (Ejercicio pObject);
	public List listarTodos(Filtro filtro);
	public Ejercicio leerEjercicio(Integer id);

}

package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.sql.Timestamp;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface DigitalDao {
	public void grabarEvaDigitales (Digital pObject);
	public Digital buscarEvaDigitales (Long id);
	public void borrarEvaDigitales (Long id);
	public void borrarEvaDigitales (Digital pObject);
	public void actualizarEvaDigitales (Digital pObject);
	public List listarTodos(Filtro filtro);
	public List buscarPorFecha(Timestamp desde,Timestamp hasta);
}


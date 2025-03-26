package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ConfirmacionTelefonica;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ConfirmacionTelefonicaDao {
	public void grabarEvaConfTelefonicas (ConfirmacionTelefonica pObject);
	public ConfirmacionTelefonica buscarEvaConfTelefonicas (Long id);
	public void borrarEvaConfTelefonicas (Long id);
	public void borrarEvaConfTelefonicas (ConfirmacionTelefonica pObject);
	public void actualizarEvaConfTelefonicas (ConfirmacionTelefonica pObject);
	public List listarTodos(Filtro filtro);
}


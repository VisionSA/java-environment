package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface EmailsDao {
	public void grabarEvaEmails (Emails pObject);
	public Emails buscarEvaEmails (Long id);
	public void borrarEvaEmails (Long id);
	public void borrarEvaEmails (Emails pObject);
	public void actualizarEvaEmails (Emails pObject);
	public List listarTodos(Filtro filtro);
}


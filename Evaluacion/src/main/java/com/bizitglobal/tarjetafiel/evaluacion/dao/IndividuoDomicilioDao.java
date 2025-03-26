package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface IndividuoDomicilioDao {
	public void grabarEvaIndivDomicilios (IndividuoDomicilio pObject);
	public IndividuoDomicilio buscarEvaIndivDomicilios (Long id);
	public void borrarEvaIndivDomicilios (Long id);
	public void borrarEvaIndivDomicilios (IndividuoDomicilio pObject);
	public void actualizarEvaIndivDomicilios (IndividuoDomicilio pObject);
	public List listarTodos(Filtro filtro);
	public Domicilio getDomicilioByIdIndividuo(Long idIndividuo);
}


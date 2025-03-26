package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface DiaPagoDao {
	public void grabarEvaDiasPago (DiaPago pObject);
	public DiaPago buscarEvaDiasPago (Long id);
	public void borrarEvaDiasPago (Long id);
	public void borrarEvaDiasPago (DiaPago pObject);
	public void actualizarEvaDiasPago (DiaPago pObject);
	public List listarTodos(Filtro filtro);
	public DiaPago getDiaPagoByIdCliente(Long idCliente);
}


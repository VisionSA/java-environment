package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface PromotorTelefonoDao {
	public void grabarEvaPromoTelefonos (PromotorTelefono pObject);
	public PromotorTelefono buscarEvaPromoTelefonos (Long id);
	public void borrarEvaPromoTelefonos (Long id);
	public void borrarEvaPromoTelefonos (PromotorTelefono pObject);
	public void actualizarEvaPromoTelefonos (PromotorTelefono pObject);
	public List listarTodos(Filtro filtro);
}


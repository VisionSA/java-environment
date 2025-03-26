package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.sql.SQLException;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public interface ComprobanteDao {
	
	public void grabarComprobante(Comprobante unComprobante);
	
	public Comprobante buscarComprobante(Long id);
	
	public void borrarComprobante(Long id);
	
	public void borrarComprobante(Comprobante unComprobante);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarComprobante(Comprobante unComprobante);
	
	public List buscarNro(Integer nroCorto, Integer nroLargo, Proveedor proveedor);
	
	public Integer leerNroTipo(String tipo);
	
	public void generarPrintFormaPago(Long idOperador, Long nroOP)throws SQLException;
}

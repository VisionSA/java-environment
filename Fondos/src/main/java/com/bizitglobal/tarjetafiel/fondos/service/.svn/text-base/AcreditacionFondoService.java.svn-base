package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoAcreditacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionBancaria;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;


public interface AcreditacionFondoService {
	
	
	public void grabarAcreditacionFondo(AcreditacionFondo acreditacionFondo)throws AcreditacionFondoException ;
	
	
	public AcreditacionFondo leerAcreditacionFondo(Long id) throws AcreditacionFondoException;
	
	
	public void borrarAcreditacionFondo(Long id) throws AcreditacionFondoException;
	
	
	public void borrarAcreditacionFondo(AcreditacionFondo acreditacionFondo) throws AcreditacionFondoException;
	
	
	public void actualizarAcreditacionFondo(AcreditacionFondo acreditacionFondo) throws AcreditacionFondoException;
	
	
	public List getAcreditaciones() throws AcreditacionFondoException;
	
	
	public List getAcreditaciones(Filtro filtro) throws AcreditacionFondoException;
	
	public ConciliacionBancaria conciliarAcreditaciones(ArchivoAcreditacion archivoAcreditacion, Operador operador)throws AcreditacionFondoException;
	
	public List getUltimasAcreditacionesCargadas() throws AcreditacionFondoException;
	
}

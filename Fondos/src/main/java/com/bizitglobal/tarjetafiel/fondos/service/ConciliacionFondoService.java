package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ConciliacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public interface ConciliacionFondoService {
	
	/**
	 * Graba una ConciliacionFondo en la base de datos.
	 * @param ConciliacionFondo a grabar.
	 */
	public void grabarConciliacionFondo(ConciliacionFondo unaConciliacionFondo)throws ConciliacionFondoException ;
	
	/**
	 * Obtiene una ConciliacionFondo de la base de datos dado su id.
	 * @param id, Identificador de la ConciliacionFondo buscada.
	 * @return La ConciliacionFondo buscada.
	 */
	public ConciliacionFondo leerConciliacionFondo(Long id) throws ConciliacionFondoException;
	
	/**
	 * Borra un ConciliacionFondo de la base de datos dado su id.
	 * @param id, Identificador de la ConciliacionFondo.
	 */
	public void borrarConciliacionFondo(Long id) throws ConciliacionFondoException;
	
	/**
	 * Borra una ConciliacionFondo de la base de datos dado el mismo.
	 * @param ConciliacionFondo a borrar.
	 */
	public void borrarConciliacionFondo(ConciliacionFondo unaConciliacionFondo) throws ConciliacionFondoException;
	
	/**
	 * Actualiza una ConciliacionFondo en la base de datos.
	 * @param ConciliacionFondo a actualizar.
	 */
	public void actualizarConciliacionFondo(ConciliacionFondo unaConciliacionFondo) throws ConciliacionFondoException;
	
	/**
	 * Obtiene una lista de todas las ConciliacionFondo.
	 * @return Una lista de ConciliacionFondos.
	 */
	public List getConciliacionFondos() throws ConciliacionFondoException;
	
	/**
	 * Obtiene una lista de todas los tipos de ConciliacionFondo según el filtro.
	 * @return Una lista de ConciliacionFondos.
	 */
	public List getConciliacionFondos(Filtro filtro) throws ConciliacionFondoException;
	
	public List getConciliacionFondosFlex(Filtro filtro) throws ConciliacionFondoException;
	
	/**
	 * Permite revertir una conciliacion.
	 * @param idCabeceraConciliacion
	 * @param fechaReversion
	 * @param operadorReversion
	 * @throws ConciliacionFondoException
	 */
	public void revertirConciliacionFondo(List<ConciliacionFondoCabecera> listCabeceras, Date fechaReversion, Operador operadorReversion) throws ConciliacionFondoException;
	
	public void revertirConciliacionFondoTodos(final Filtro filtro, final Date fechaReversion, final Operador operadorReversion) throws ConciliacionFondoException;
}

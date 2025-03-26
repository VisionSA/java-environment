package com.bizitglobal.tarjetafiel.operador.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 * @author Administrador
 * Interface de servicios para los operadores del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface OperadorService {
	
	/**
	 * Graba un operador en la base de datos.
	 * @param unOperador, Operador a grabar.
	 * @param permisos, Iterator de permisos.
	 */
	public void grabarOperador(Operador unOperador) throws OperadorException;
	
	/**
	 * Obtiene un operador de la base de datos dado su id.
	 * @param id, Identificador del operador buscado.
	 * @return El operador buscado.
	 */
	public Operador leerOperador(Long id) throws OperadorException;
	
	/**
	 * Borra un operador de la base de datos dado su id.
	 * @param id, Identificador del operador.
	 */
	public void borrarOperador(Long id) throws OperadorException;
	
	/**
	 * Borra un operador de la base de datos dado el mismo.
	 * @param unOperador, Operador a borrar.
	 */
	public void borrarOperador(Operador unOperador) throws OperadorException;
	
	/**
	 * Actualiza un operador en la base de datos.
	 * @param unOperador, Operador a actualizar.
	 */
	public void actualizarOperador(Operador unOperador) throws OperadorException;
	
	/**
	 * Obtiene una lista de todos los operadores.
	 * @return Una lista de operadores.
	 */
	public List getOperadores() throws OperadorException;
	
	/**
	 * Obtiene una lista de todos los peradores según el filtro. 
	 * @param filtro, Un filtro que se utilizara para filtras al operador deseado.
	 * @return Una lista con todos los operadores filtrados.
	 * @throws OperadorException
	 */
	public List getOperadores(Filtro filtro) throws OperadorException;
		
	/**
	 * Verifica el ingreso del operador al sistema.
	 * @param codigo, Codigo de acceso.
	 * @param unaClave, Clave de acceso.
	 * @return El operador que ingresa al sistema.
	 */
	public Operador login(String username) throws OperadorException;
	
}

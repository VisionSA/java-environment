package com.bizitglobal.tarjetafiel.operador.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 * @author Hernan
 * Interface que se encarga de definir las operaciones que 
 * se podran efectuar con la base de datos de los operadores.
 */

public interface OperadorDao {
	
	/**
	 * @param unOperador
	 * Graba todos los atributos del operador en la DB 
	 */
	public void grabarOperador(Operador unOperador);
	
	/**
	 * @param id
	 * Busca un operador en la DB 
	 * @return
	 */
	public Operador buscarOperador(Long id);
	
	/**
	 * @param id, Elimina un operador a partir de su Id
	 */
	public void borrarOperador(Long id);
	
	/**
	 * @param unOperador, Elimina un operador a partir de su objeto operador
	 */
	public void borrarOperador(Operador unOperador);
	
	/**
	 * Retorna un lista con todos los operadores disponibles.
	 * @return Una lista con los operadores.
	 */
	public List listarTodos();
	
	/**
	 * @param filtro, filtra un operdor atraves del objeto que se le pasa como parametro. 
	 * @return Una lista con todos los operadores. 
	 */
	public List listarTodos(Filtro filtro);
	
	/**
	 * Actualiza el operador en la base de datos.
	 */
	public void actualizarOperador(Operador unOperador);
	
	
	public List validarPermisoDesdeFlex(Operador operador, String pagina);
}

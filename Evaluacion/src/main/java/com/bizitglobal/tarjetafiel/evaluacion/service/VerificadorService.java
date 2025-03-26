package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface VerificadorService {
	/**
	* Graba una Verificador en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarVerificador(Verificador pObject) throws VerificadorException;
	/**
	* Obtiene una Verificador de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Verificador leerVerificador(Long id) throws VerificadorException;
	/**
	* Borra una Verificador de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarVerificador(Long id) throws VerificadorException;
	/**
	* Borra una Verificador de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarVerificador(Verificador pObject) throws VerificadorException;
	/**
	* Actualiza una Verificador de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarVerificador(Verificador pObject) throws VerificadorException;
	/**
	* Obtiene una lista de todas las Verificador de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getVerificador(Filtro filtro) throws VerificadorException;
}


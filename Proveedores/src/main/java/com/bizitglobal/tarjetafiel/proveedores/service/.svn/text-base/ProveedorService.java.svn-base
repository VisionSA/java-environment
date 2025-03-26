package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

/**
 * @author Daniel
 * Interface de servicios para los proveedores del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ProveedorService {
	
	/**
	 * Graba un proveedor en la base de datos.
	 * @param unProveedor, Proveedor a grabar.
	 */
	public void grabarProveedor(Proveedor unProveedor) throws ProveedorException;
	
	/**
	 * Obtiene un proveedor de la base de datos dado su id.
	 * @param id, Identificador del proveedor buscado.
	 * @return El proveedor buscado.
	 */
	public Proveedor leerProveedor(Long id) throws ProveedorException;
	
	/**
	 * Borra un proveedor de la base de datos dado su id.
	 * @param id, Identificador del proveedor.
	 */
	public void borrarProveedor(Long id) throws ProveedorException;
	
	/**
	 * Borra un proveedor de la base de datos dado el mismo.
	 * @param unProveedor, Proveedor a borrar.
	 */
	public void borrarProveedor(Proveedor unProveedor) throws ProveedorException;
	
	/**
	 * Busca un proveedor de la base de datos dado su cuit.
	 * @param unProveedor, Proveedor a borrar.
	 */
	public Proveedor buscarProveedor(String cuit) throws ProveedorException;
	
	/**
	 * Actualiza un proveedor en la base de datos.
	 * @param unProveedor, Proveedor a actualizar.
	 */
	public void actualizarProveedor(Proveedor unProveedor) throws ProveedorException;
	
	
	/**
	 * Graba o actualiza un proveedor según el caso.
	 * @param unProveedor, proveedor a grabar o actualizar.
	 * @throws ProveedorException, se lanza si no se puede realizar la operacion.
	 */
	public void grabarYActualizarProveedor(Proveedor unProveedor) throws ProveedorException;
	
	/**
	 * Obtiene una lista de todos los proveedores.
	 * @param filtro, Filtro a aplicar a la busqueda.
	 * @return Una lista de proveedores.
	 */
	public List getProveedores(Filtro filtro) throws ProveedorException;
	
	/**
	 * Obtiene el maximo de los proveedores en el sistema.
	 * @return El id maximo.
	 * @throws ProveedorException, Lanzada si existen errores en el acceso a datos.
	 */
	public Long maxIdProveedor() throws ProveedorException;
	
	/**
	 * Obtiene una lista de todas las retenciones y exclusiones asociadas, del proveedor dado.
	 * @param unProveedor, Pvoveedor para hacer la busqueda.
	 * @return Una lista de retenciones con su correspondiente de exclusiones.
	 */
	public List getRetenciones(Proveedor proveedor) throws ProveedorException;
	
	/**
	 * Obtiene la suma de todas las exclusiones del proveedor dado.
	 * @param unProveedor, Pvoveedor para hacer la busqueda.
	 * @return % de exclusiones.
	 */
//	public List getExclusiones(Proveedor proveedor) throws ProveedorException;
	
}

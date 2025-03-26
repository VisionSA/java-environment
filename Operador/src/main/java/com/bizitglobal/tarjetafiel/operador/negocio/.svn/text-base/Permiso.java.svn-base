package com.bizitglobal.tarjetafiel.operador.negocio;

import java.io.Serializable;

/**
 * Representa los permisos en el sistema, cuando un operador accede a un servicio, este necesitara pasar
 * por los controles de acceso. Si lo logra, entonces obtendra los datos de la interfaz de servicio, caso contrario
 * se generara una excepcion.
 */
public class Permiso implements Serializable {
	
	private static final long serialVersionUID = 7972971920833385924L;
	
	/**
	 * Id del permiso.
	 */
	private Integer id;
	/**
	 * Nombre para el permiso.
	 */
	private String nombre;
	/**
	 * Clase a la que se autoriza el acceso.
	 */
	private String descripcion;
	
	/**
	 * Constructor por defecto el permiso.
	 */
	public Permiso() {
		this(null,"","");
	}
	
	/**
	 * Constructor con id, para comparacion.
	 * @param id, 
	 */
	public Permiso(Integer id) {
		this(id,"","");
	}
	
	/**
	 * Constructor con argumentos para los permisos.
	 * @param nombre, nombre del permiso.
	 * @param clase, clase a la que se aplica el permiso(clase="*" todas las clases).
	 * @param metodo, metodo al cual se aplica el permiso(metodo="*" todos los metodos).
	 */
	public Permiso(Integer id,String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion  = descripcion;
	}
	
	/**
	 * @return Retorna el id del permiso.
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Setea el id del permiso.
	 * @param newId, Id del permiso.
	 */
	public void setId(Integer newId) {
		this.id = newId;
	}

	/**
	 * Setea el nombre del permiso.
	 * @param nombre, Nombre del permiso.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Retorna el nombre del permiso.
	 */
	public String getNombre() {
		return nombre.trim();
	}

	/**
	 * Setea la descripcion para el permiso.
	 * @param unaClase, Clase del permiso.
	 */
	public void setDescripcion(String unaDescripcion) {
		this.descripcion = unaDescripcion;
	}

	/**
	 * @return Retorna la clase del permiso.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Compara dos permisos.
	 * @param unPermiso, Permiso a comparar con el objeto receptor.
	 * @return Retorna true si los permisos son iguales y false en caso contrario.
	 */
	public boolean equals(Object unPermiso) {
		boolean result = false;
		
		if(unPermiso instanceof Permiso) {
			Permiso permiso = (Permiso)unPermiso;
			if(permiso.getId().equals(this.getId())) {
				result = true;
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Nombre:"+this.getNombre()+"|Descripcion:"+this.getDescripcion();
	}

}

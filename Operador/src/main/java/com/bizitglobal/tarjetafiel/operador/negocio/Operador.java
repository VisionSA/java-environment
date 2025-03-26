package com.bizitglobal.tarjetafiel.operador.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

/**
 * Clase que representa los operadores del sistema.
 */

public class Operador implements Serializable, Negocio {

	private static final long serialVersionUID = -4022520466033200579L;

	/**
	 * Identificador de negocio para la entidad operador.
	 */
	private Long codigo;
	
	/**
	 * Nombre de usuario para el ingreso al sistema.
	 */	
	private String username;

	/**
	 * Nombre del operador
	 */
	private String nombre;
	
	/**
	 * Apellido del operador. 
	 */
	private String apellido;

	/**
	 * Clave del operador.
	 */
	private String clave;
	
	/**
	 * Email del operador
	 */
	private String email;

	/**
	 * Estado del operador - a difinir...
	 */
	private String estado;
	
	/**
	 * Fecha en la que se dio de alta el operador.
	 */
	private Date fechaAlta;
	
	/**
	 * Operador responsable del alta.
	 */
	private Operador operadorAlta;
	
	/**
	 * Operador resposable de la baja.
	 */
	private Operador operadorBaja;
			
	/**
	 * Rol relacionado en este operador.
	 */
	private Rol rol;
	
	private String permiteLinea;
	
	private String nombreCompleto;
	
	private String permiteGrabar;

	/**
	 * Default constructor.
	 */
	public Operador() {
		codigo = new Long(0);
		username = "";
		nombre   = "";
		apellido = "";
		clave    = "";
		email    = "";
		estado   = "";
		rol = null;
	}
		
	public Operador(Long id) {
		this(id,null,null,null,null,null,null,null,null,null,null);
	}

	public Operador(Long codigo, String username, String nombre, String apellido,
			String clave, String email, String estado, Date fechaAlta, Operador operadorAlta, 
			Operador operadorBaja, Rol unRol) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.email = email;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.operadorAlta = operadorAlta;
		this.operadorBaja = operadorBaja;
		this.rol = unRol;
	}
	public Long getId() {
		return codigo;
	}
	
	public String getLabel() {
		return apellido + ", " + nombre ;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre.trim();
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave != null ? clave.trim() : null;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado.trim();
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
		
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol unRol) {
		this.rol = unRol;
	}

	/**
	 * @return devuelve el operador que lo dio de ALTA
	 */
	public Operador getOperadorAlta() {
		return operadorAlta;
	}

	/**
	 * @param operadorAlta, setea el operador que le da de ALTA
	 */
	public void setOperadorAlta(Operador operadorAlta) {
		this.operadorAlta = operadorAlta;
	}

	/**
	 * @return devuelve el operador que lo dio de BAJA
	 */
	public Operador getOperadorBaja() {
		return operadorBaja;
	}

	/**
	 * @param operadorBaja, setea el operador que le da de BAJA
	 */
	public void setOperadorBaja(Operador operadorBaja) {
		this.operadorBaja = operadorBaja;
	}

	/**
	 * @param unOperador
	 * Compara si los atributos de dos operadores son iguales.
	 * Compara: id, clave, nombre, estado
	 * @return 
	 */
	public boolean equals(Operador unOperador) {
		boolean result = false;
		if (unOperador.getClave().equals(this.getClave())
				&& unOperador.getCodigo().equals(this.getCodigo())
				&& unOperador.getNombre().equals(this.getNombre())
				&& unOperador.getEstado().equals(this.getEstado())) {
			result = true;
		}

		return result;
	}

	/**
	 * Devuelve un detalle del operador
	 */
	public String toString() {
		try{
			return "Codigo:" + this.getCodigo() + "|Nombre:" + this.getNombre() + "|Clave:" + this.getClave()
			+ "|Estado:" + this.getEstado() + "|Fecha de Alta:" + this.getFechaAlta();
			
		} catch (Exception e) {
			return super.toString();
		}
	}
		

	/**
	 * Obtiene el codigo de este operador.
	 * @return Codigo del operador.
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * Setea un codigo para el operador.
	 * @param codigo, Codigo a setear.
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getUsername() {
		return username.trim();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email.trim();
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean equals(Object unOperador) {
		boolean result = false;
		if(unOperador instanceof Operador) {
			Operador aux = (Operador)unOperador;
			if(aux.getCodigo().equals(codigo)) {
				result = true;
			}
		}
		return result;
	}

	public String getPermiteLinea() {
		return permiteLinea;
	}

	public void setPermiteLinea(String permiteLinea) {
		this.permiteLinea = permiteLinea;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPermiteGrabar() {
		return permiteGrabar;
	}

	public void setPermiteGrabar(String permiteGrabar) {
		this.permiteGrabar = permiteGrabar;
	}
	
}

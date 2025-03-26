package org.java.bean;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable{

	private static final long serialVersionUID = -3885996850827455569L;
	
	private Integer id;          
	private String  nombres;         
	private String  apellidos;        
	private Date    cumpleanos;      
	private String  direccion;  
	private String  foto;
	private String  email;        
	private String  usuario;         
	private String  password;   
	private String  tipo;        
	private String  estado;
	
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public Date getCumpleanos() {
		return cumpleanos;
	}
	
	public void setCumpleanos(Date cumpleanos) {
		this.cumpleanos = cumpleanos;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}	
	
 }

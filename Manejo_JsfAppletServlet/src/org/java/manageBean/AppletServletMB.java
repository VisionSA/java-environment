package org.java.manageBean;

import java.io.Serializable;


public class AppletServletMB implements Serializable{

	private static final long serialVersionUID = 929879873791124689L;
	
	private Integer codigo;
	private String  nombre;
	private String  apellido;
	private String  foto;
	private String  mensajeErrorApplet;	
	
	
	public AppletServletMB(){	
		this.codigo   = 123456;
		this.nombre   = "Ricardo";
		this.apellido = "Guerra";
		this.foto     = "JavaMan.jpg";
		this.mensajeErrorApplet = "Error al Cargar el APPLET...!!!";
	}
	
	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensajeErrorApplet() {
		return mensajeErrorApplet;
	}

	public void setMensajeErrorApplet(String mensajeErrorApplet) {
		this.mensajeErrorApplet = mensajeErrorApplet;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

 }

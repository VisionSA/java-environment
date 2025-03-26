package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

import java.sql.Blob;


/***** @Id:6958 ******/
public class ImagenEmail {

	public final static String IMAGEN_EMAIL = "T_VIS_PLA_IMAGES";
	public final static String ID_IMAGEN_EMAIL = "C_ID_IMG";
	public final static String DESC_IMAGEN_EMAIL = "C_DESCRIPION";
	public final static String IMG_IMAGEN_EMAIL = "C_IMAGE";
	private Long idImag;
	private String descripcion;
	private Blob imagen;


	public ImagenEmail() {
	}


	public Long getIdImag() {
		return idImag;
	}


	public void setIdImag(Long idImag) {
		this.idImag = idImag;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Blob getImagen() {
		return imagen;
	}


	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

}

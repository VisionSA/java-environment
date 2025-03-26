package com.bizitglobal.webapp.faces.beans.proveedores.wrappers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.util.Session;


public class ArchivoAdjunto {
	private static final Logger log = Logger.getLogger(ArchivoAdjunto.class);

	private Long idArchivoAdjunto;
	private String nombreArchivo;
	private String path;
	private HttpServletRequest request;
	private String url;


	public ArchivoAdjunto(Long idArchivoAdjunto, String nombreArchivo) {
		super();
		this.idArchivoAdjunto = idArchivoAdjunto;
		this.nombreArchivo = nombreArchivo;

	}


	public ArchivoAdjunto() {
		this(null, null);
	}


	public Long getIdArchivoAdjunto() {
		return idArchivoAdjunto;
	}


	public void setIdArchivoAdjunto(Long idArchivoAdjunto) {
		this.idArchivoAdjunto = idArchivoAdjunto;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public String getURL() {

		request = Session.getRequest();

		String urlUno = request.getRequestURI();
		String urlDos = request.getRequestURL().toString();

		log.info("request.getRequestURI(): " + urlUno);
		log.info("request.getRequestURL().toString(): " + urlDos);

		url = urlDos.replaceAll(urlUno, "/imagenes/" + nombreArchivo);
		log.info(url);

		return url;
	}


	public void setPath(String path) {
		this.path = path;
	}

}

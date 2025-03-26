package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;


public class WrapperFile {

	private Long idWrappers;
	private Long idTipoDoc;
	private Long idDocumentoAdjunto;
	private String nombreArchivo;
	private String descripcion;
	private String tipoDocumento;
	private List listaDocumentos;
	private String path;
	private Timestamp timestamp;


	/**
	 * @param idTipoDoc
	 * @param nombreArchivo
	 * @param descripcion
	 */
	public WrapperFile(Long idWrappers, Long idTipoDoc, String nombreArchivo, String descripcion, String path, Timestamp timestamp) {
		super();
		this.idWrappers = idWrappers;
		this.idTipoDoc = idTipoDoc;
		this.nombreArchivo = nombreArchivo;
		this.descripcion = descripcion;
		this.path = path;
		this.timestamp = timestamp;
	}


	/**
	 * @param idTipoDoc
	 * @param nombreArchivo
	 */
	public WrapperFile(Long idWrappers, Long idTipoDoc, String nombreArchivo, Timestamp timestamp) {
		this(idWrappers, idTipoDoc, nombreArchivo, null, null, timestamp);
	}


	/**
	 * @param idTipoDoc
	 */
	public WrapperFile(Long idWrappers, Long idTipoDoc, Timestamp timestamp) {
		this(idWrappers, idTipoDoc, null, null, null, timestamp);
	}


	/**
	 * 
	 */
	public WrapperFile(Long idWrappers, Timestamp timestamp) {
		this(idWrappers, null, null, null, null, timestamp);
	}


	/**
	 * 
	 */
	public WrapperFile() {
		this(null, null, null, null, null, null);
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the idTipoDoc
	 */
	public Long getIdTipoDoc() {
		return idTipoDoc;
	}


	/**
	 * @param idTipoDoc
	 *            the idTipoDoc to set
	 */
	public void setIdTipoDoc(Long idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}


	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}


	/**
	 * @param nombreArchivo
	 *            the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	/**
	 * @return the idWrappers
	 */
	public Long getIdWrappers() {
		return idWrappers;
	}


	/**
	 * @param idWrappers
	 *            the idWrappers to set
	 */
	public void setIdWrappers(Long idWrappers) {
		this.idWrappers = idWrappers;
	}


	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		if (idTipoDoc != null && !idTipoDoc.equals(new Long(0))) {

			if (!listaDocumentos.isEmpty()) {
				Iterator iterator = listaDocumentos.iterator();
				while (iterator.hasNext()) {
					TipoDigital element = (TipoDigital) iterator.next();

					if (element.getIdTipoDigital().equals(idTipoDoc)) {

						return element.getDescripcion();
					}

				}
			}
		}
		return tipoDocumento;
	}


	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	/**
	 * @return the listaDocumentos
	 */
	public List getListaDocumentos() {
		return listaDocumentos;
	}


	/**
	 * @param listaDocumentos
	 *            the listaDocumentos to set
	 */
	public void setListaDocumentos(List listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}


	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}


	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}


	public Long getIdDocumentoAdjunto() {
		return idDocumentoAdjunto;
	}


	public void setIdDocumentoAdjunto(Long idDocumentoAdjunto) {
		this.idDocumentoAdjunto = idDocumentoAdjunto;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}

package com.bizitglobal.webapp.faces.beans.general;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.DocAdjunto;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.contabilidad.AsientosBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class DocumentoAdjuntoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(DocumentoAdjuntoBean.class);
	private String focoHidden;

	private List listTipoDigital;
	private List listAuxTipoDigital;
	private List listTipoDocumentos;
	private Long idTipoDocSeleccionado;
	private DocAdjunto docAdjunto;
	private UploadedFile uploadedFile;
	private String titulo;
	private boolean panelAdjuntar;
	private boolean verTiposDoc; // flag que indica si se muestran los tipos de documentos digitales.

	private Asiento asiento; // objeto por si el origen es ContabilidadAsientos.
	private Lote lote; // objeto por si el origen es ContabilidadLotes.

	// Objetos para inicializar desde distintos origenes
	private int origen = 0;
	public static final int CONTABILIDAD_ASIENTOS = 1;
	public static final int CONTABILIDAD_LOTES = 2;
	public static final int CONTABILIDAD_POPUP = 3;


	public DocumentoAdjuntoBean() {
		error.borrar();
		titulo = "Documentos Adjuntos";
	}


	/*
	 * Acciones para el bean deDocAdjuntos.
	 */

	public String cancelar() {
		borrar();
		return null;
	}


	public void borrar() {
		focoHidden = "";
		docAdjunto = new DocAdjunto();
		listTipoDocumentos = new ArrayList();
		panelAdjuntar = false;
	}


	public boolean validar() {
		error.borrar();
		// validar segun el origen
		return (error.cantidad() == 0) ? true : false;
	}


	/*
	 * Este metodo se utiliza para poder almacenar un archivo digital en un directorio predefinido. Primero creamos la ruta donde se va a almacenar el
	 * archivo.
	 */
	public String saveFile() {
		log.info("Ejecutando ==> saveFile()");
		String path;
		path = "No grabo";
		try {
			path = Archivo.grabarArchivo(uploadedFile.getInputStream(), uploadedFile.getName(), new Long(uploadedFile.getSize()).intValue(),
					Archivo.archivosDeContabilidad);
			log.info(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		if (path != null && !path.equals("No grabo")) {
			WrapperFile wrapperFile = new WrapperFile(null, idTipoDocSeleccionado, path, docAdjunto.getDescripcion(), path);
			wrapperFile.setIdWrappers(new Long(wrapperFile.hashCode()));
			wrapperFile.setListaDocumentos(listAuxTipoDigital);
			listTipoDocumentos.add(wrapperFile);

			// y grabamos en la base de datos tambien...
			switch (origen) {
			case CONTABILIDAD_ASIENTOS:
				// //recupero el tipo digital.
				// TipoDigital tip = null;
				// Iterator iter = listAuxTipoDigital.iterator();
				// while (iter.hasNext()) {
				// TipoDigital tipo = (TipoDigital)iter.next();
				// if (tipo.getIdTipoDigital().compareTo(idTipoDocSeleccionado)==0) {
				// tip = tipo;
				// break;
				// }
				// }

				docAdjunto = new DocAdjunto();
				docAdjunto.setDescripcion(wrapperFile.getDescripcion());
				docAdjunto.setIdAsiento(asiento.getIdAsiento());
				docAdjunto.setIdEjercicio(asiento.getIdEjercicio());
				docAdjunto.setIdEmpresa(asiento.getIdEmpresa());
				docAdjunto.setIdOperador(Session.getOperador().getId());
				docAdjunto.setIsAsiento("S");
				docAdjunto.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				docAdjunto.setTipoDigital(null);
				docAdjunto.setUrl(wrapperFile.getPath());
				contabilidadService.getDocAdjuntoDao().grabar(docAdjunto);
				wrapperFile.setIdDocumentoAdjunto(docAdjunto.getIdDocAdjunto());
				panelAdjuntar = false;
				break;
			case CONTABILIDAD_LOTES:
				// // recupero el tipo digital.
				// TipoDigital tipLo = null;
				// Iterator iterLo = listAuxTipoDigital.iterator();
				// while (iterLo.hasNext()) {
				// TipoDigital tipo = (TipoDigital)iterLo.next();
				// if (tipo.getIdTipoDigital().compareTo(idTipoDocSeleccionado)==0) {
				// tipLo = tipo;
				// break;
				// }
				// }
				docAdjunto = new DocAdjunto();
				docAdjunto.setDescripcion(wrapperFile.getDescripcion());
				docAdjunto.setIdAsiento(lote.getIdAsiento());
				docAdjunto.setIdEjercicio(lote.getIdEjercicio());
				docAdjunto.setIdEmpresa(lote.getIdEmpresa());
				docAdjunto.setIdOperador(Session.getOperador().getId());
				docAdjunto.setIsAsiento("N");
				docAdjunto.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				docAdjunto.setTipoDigital(null);
				docAdjunto.setUrl(wrapperFile.getPath());
				contabilidadService.getDocAdjuntoDao().grabar(docAdjunto);
				wrapperFile.setIdDocumentoAdjunto(docAdjunto.getIdDocAdjunto());
				panelAdjuntar = false;
				break;
			case CONTABILIDAD_POPUP:
				// //recupero el tipo digital.
				// TipoDigital tip = null;
				// Iterator iter = listAuxTipoDigital.iterator();
				// while (iter.hasNext()) {
				// TipoDigital tipo = (TipoDigital)iter.next();
				// if (tipo.getIdTipoDigital().compareTo(idTipoDocSeleccionado)==0) {
				// tip = tipo;
				// break;
				// }
				// }

				// distinguir en tre lote y asiento
				AsientosBean beanAsientos = (AsientosBean) Session.getBean("AsientosBean");
				if (lote == null) {
					docAdjunto = new DocAdjunto();
					docAdjunto.setDescripcion(wrapperFile.getDescripcion());
					docAdjunto.setIdAsiento(asiento.getIdAsiento()); // en este caso los pondre despues de guardar el asiento.
					docAdjunto.setIdEjercicio(asiento.getIdEjercicio());
					docAdjunto.setIdEmpresa(asiento.getIdEmpresa());
					docAdjunto.setIsAsiento("S");
				} else {
					docAdjunto = new DocAdjunto();
					docAdjunto.setDescripcion(wrapperFile.getDescripcion());
					if (lote.getIdAsiento() != null) {
						docAdjunto.setIdAsiento(lote.getIdAsiento());
					}
					docAdjunto.setIdEjercicio(lote.getIdEjercicio());
					docAdjunto.setIdEmpresa(lote.getIdEmpresa());
					docAdjunto.setIsAsiento("N");
				}
				docAdjunto.setIdOperador(Session.getOperador().getId());
				docAdjunto.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				docAdjunto.setTipoDigital(null);
				docAdjunto.setUrl(wrapperFile.getPath());
				// contabilidadService.getDocAdjuntoDao().grabar(docAdjunto);
				// wrapperFile.setIdDocumentoAdjunto(docAdjunto.getIdDocAdjunto());
				panelAdjuntar = false;
				beanAsientos.getPopupAltaAsiento().getDocAdjuntos().add(docAdjunto);
				break;
			}

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}

		return null;
	}


	public String habilitarCarga() {
		panelAdjuntar = true;
		return null;
	}


	public String inicializar(int origen, Object object) {
		log.info("Ejecutando ==> inicilizando el bean de DocAdjuntos.");
		borrar();
		this.origen = origen;
		error.borrar();
		listAuxTipoDigital = generalService.getTipoDigitalDao().listarTodos(new Filtro());
		listTipoDigital = IndividuoEvaluacionUtil.cargarTipoDigitales(listAuxTipoDigital);
		List listaAux = new ArrayList();
		switch (origen) {
		case (CONTABILIDAD_ASIENTOS):
			verTiposDoc = false;
			asiento = (Asiento) object;
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, asiento.getIdEjercicio());
			filtro.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, asiento.getIdEmpresa());
			filtro.agregarCampoOperValor("idAsiento", Filtro.IGUAL, asiento.getIdAsiento());
			// filtro.agregarCampoOperValor("isAsiento", Filtro.LIKE, "S");
			listaAux = contabilidadService.getDocAdjuntoDao().listarTodos(filtro);
			if (listaAux.size() > 0) {
				Iterator iDocTit = listaAux.iterator();
				while (iDocTit.hasNext()) {
					DocAdjunto di = (DocAdjunto) iDocTit.next();
					WrapperFile wrapin = new WrapperFile();
					wrapin.setDescripcion(di.getDescripcion());
					wrapin.setIdDocumentoAdjunto(di.getIdDocAdjunto());
					wrapin.setIdTipoDoc(null);
					wrapin.setIdWrappers(new Long(di.hashCode()));
					wrapin.setNombreArchivo(di.getUrl());
					wrapin.setPath(di.getUrl());
					wrapin.setListaDocumentos(listAuxTipoDigital);
					TipoDigital ti = null;
					// Iterator iterTipos = listAuxTipoDigital.iterator();
					// while (iterTipos.hasNext()) {
					// TipoDigital tip = (TipoDigital)iterTipos.next();
					// if (tip.getIdTipoDigital().equals(di.getTipoDigital().getIdTipoDigital())) {
					// ti = tip;
					// break;
					// }
					// }
					wrapin.setTipoDocumento(null);
					listTipoDocumentos.add(wrapin);
					panelAdjuntar = false;
				}

			} else {
				listTipoDocumentos = new ArrayList();
			}

			break;
		case (CONTABILIDAD_LOTES):
			verTiposDoc = false;
			lote = (Lote) object;
			Filtro filtroLo = new Filtro();
			filtroLo.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, lote.getIdEjercicio());
			filtroLo.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, lote.getIdEmpresa());
			filtroLo.agregarCampoOperValor("idAsiento", Filtro.IGUAL, lote.getIdAsiento());
			// filtroLo.agregarCampoOperValor("isAsiento", Filtro.LIKE, "N");
			listaAux = contabilidadService.getDocAdjuntoDao().listarTodos(filtroLo);
			if (listaAux.size() > 0) {
				Iterator iDocTit = listaAux.iterator();
				while (iDocTit.hasNext()) {
					DocAdjunto di = (DocAdjunto) iDocTit.next();
					WrapperFile wrapin = new WrapperFile();
					wrapin.setDescripcion(di.getDescripcion());
					wrapin.setIdDocumentoAdjunto(di.getIdDocAdjunto());
					wrapin.setIdTipoDoc(null);
					wrapin.setIdWrappers(new Long(di.hashCode()));
					wrapin.setNombreArchivo(di.getUrl());
					wrapin.setPath(di.getUrl());
					wrapin.setListaDocumentos(listAuxTipoDigital);
					// TipoDigital ti = null;
					// Iterator iterTipos = listAuxTipoDigital.iterator();
					// while (iterTipos.hasNext()) {
					// TipoDigital tip = (TipoDigital)iterTipos.next();
					// if (tip.getIdTipoDigital().equals(di.getTipoDigital().getIdTipoDigital())) {
					// ti = tip;
					// break;
					// }
					// }
					wrapin.setTipoDocumento(null);
					listTipoDocumentos.add(wrapin);
					panelAdjuntar = false;
				}

			} else {
				listTipoDocumentos = new ArrayList();
			}

			break;
		case (CONTABILIDAD_POPUP):
			verTiposDoc = false;
			Filtro filtroPopup = null;
			if (object instanceof Lote) {
				lote = (Lote) object;
				asiento = null;
				if (lote.getIdAsiento() != null) {
					filtroPopup = new Filtro();
					filtroPopup.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, lote.getIdEjercicio());
					filtroPopup.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, lote.getIdEmpresa());
					filtroPopup.agregarCampoOperValor("idAsiento", Filtro.IGUAL, lote.getIdAsiento());
					// filtroPopup.agregarCampoOperValor("isAsiento", Filtro.LIKE, "N");
					listaAux = contabilidadService.getDocAdjuntoDao().listarTodos(filtroPopup);
				}
			}
			if (object instanceof Asiento) {
				asiento = (Asiento) object;
				lote = null;
				if (asiento.getIdAsiento() != null) {
					filtroPopup = new Filtro();
					filtroPopup.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, asiento.getIdEjercicio());
					filtroPopup.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, asiento.getIdEmpresa());
					filtroPopup.agregarCampoOperValor("idAsiento", Filtro.IGUAL, asiento.getIdAsiento());
					// filtroPopup.agregarCampoOperValor("isAsiento", Filtro.LIKE, "S");
					listaAux = contabilidadService.getDocAdjuntoDao().listarTodos(filtroPopup);
				}
			}
			AsientosBean beanAsientos = (AsientosBean) Session.getBean("AsientosBean");
			listaAux.addAll(beanAsientos.getPopupAltaAsiento().getDocAdjuntos());
			if (listaAux.size() > 0) {
				Iterator iDocTit = listaAux.iterator();
				while (iDocTit.hasNext()) {
					DocAdjunto di = (DocAdjunto) iDocTit.next();
					WrapperFile wrapin = new WrapperFile();
					wrapin.setDescripcion(di.getDescripcion());
					wrapin.setIdDocumentoAdjunto(di.getIdDocAdjunto());
					wrapin.setIdTipoDoc(null);
					wrapin.setIdWrappers(new Long(di.hashCode()));
					wrapin.setNombreArchivo(di.getUrl());
					wrapin.setPath(di.getUrl());
					wrapin.setListaDocumentos(listAuxTipoDigital);
					// TipoDigital ti = null;
					// Iterator iterTipos = listAuxTipoDigital.iterator();
					// while (iterTipos.hasNext()) {
					// TipoDigital tip = (TipoDigital)iterTipos.next();
					// if (tip.getIdTipoDigital().equals(di.getTipoDigital().getIdTipoDigital())) {
					// ti = tip;
					// break;
					// }
					// }
					wrapin.setTipoDocumento(null);
					listTipoDocumentos.add(wrapin);
					panelAdjuntar = false;
				}

			} else {
				listTipoDocumentos = new ArrayList();
			}

			break;
		}

		return null;
	}


	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public DocAdjunto getDocAdjunto() {
		return docAdjunto;
	}


	public void setDocAdjunto(DocAdjunto docAdjunto) {
		this.docAdjunto = docAdjunto;
	}


	public Long getIdTipoDocSeleccionado() {
		return idTipoDocSeleccionado;
	}


	public void setIdTipoDocSeleccionado(Long idTipoDocSeleccionado) {
		this.idTipoDocSeleccionado = idTipoDocSeleccionado;
	}


	public List getListTipoDigital() {
		return listTipoDigital;
	}


	public void setListTipoDigital(List listTipoDigital) {
		this.listTipoDigital = listTipoDigital;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public class WrapperFile {

		private Long idWrappers;
		private Long idTipoDoc;
		private Long idDocumentoAdjunto;
		private String nombreArchivo;
		private String descripcion;
		private String tipoDocumento;
		private List listaDocumentos;
		private String path;


		/**
		 * @param idTipoDoc
		 * @param nombreArchivo
		 * @param descripcion
		 */
		public WrapperFile(Long idWrappers, Long idTipoDoc, String nombreArchivo, String descripcion, String path) {
			super();
			this.idWrappers = idWrappers;
			this.idTipoDoc = idTipoDoc;
			this.nombreArchivo = nombreArchivo;
			this.descripcion = descripcion;
			this.path = path;
		}


		public String borrarArchivo() {
			log.info("Ejecutando eliminar archivo...");
			listTipoDocumentos.remove(this);
			switch (origen) {
			case CONTABILIDAD_ASIENTOS:
			case CONTABILIDAD_LOTES:
				contabilidadService.getDocAdjuntoDao().borrar(this.getIdDocumentoAdjunto());
				break;
			case CONTABILIDAD_POPUP:
				if (lote == null) {
					if (asiento.getIdAsiento() != null) {
						if (this.getIdDocumentoAdjunto() != null) {
							contabilidadService.getDocAdjuntoDao().borrar(this.getIdDocumentoAdjunto());
						}
					}
				}
				if (asiento == null) {
					if (lote.getIdAsiento() != null) {
						if (this.getIdDocumentoAdjunto() != null) {
							contabilidadService.getDocAdjuntoDao().borrar(this.getIdDocumentoAdjunto());
						}
					}
				}
				break;
			}

			return "";
		}


		public String abrirArchivo() {
			switch (origen) {
			case CONTABILIDAD_ASIENTOS:
			case CONTABILIDAD_LOTES:
			case CONTABILIDAD_POPUP:
				try {
					ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosDeContabilidad + "/" + nombreArchivo
							+ "',700,400), 'titlebar=no';");
				} catch (Exception e) {
					log.info("Error al intentar leer el archivo");
					e.printStackTrace();
				}
				break;
			}
			return "";
		}


		/**
		 * @param idTipoDoc
		 * @param nombreArchivo
		 */
		public WrapperFile(Long idWrappers, Long idTipoDoc, String nombreArchivo) {
			this(idWrappers, idTipoDoc, nombreArchivo, null, null);
		}


		/**
		 * @param idTipoDoc
		 */
		public WrapperFile(Long idWrappers, Long idTipoDoc) {
			this(idWrappers, idTipoDoc, null, null, null);
		}


		/**
		 * 
		 */
		public WrapperFile(Long idWrappers) {
			this(idWrappers, null, null, null, null);
		}


		/**
		 * 
		 */
		public WrapperFile() {
			this(null, null, null, null, null);
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
	}


	public List getListTipoDocumentos() {
		return listTipoDocumentos;
	}


	public void setListTipoDocumentos(List listTipoDocumentos) {
		this.listTipoDocumentos = listTipoDocumentos;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public boolean getPanelAdjuntar() {
		return panelAdjuntar;
	}


	public void setPanelAdjuntar(boolean panelAdjuntar) {
		this.panelAdjuntar = panelAdjuntar;
	}


	public boolean getVerTiposDoc() {
		return verTiposDoc;
	}


	public void setVerTiposDoc(boolean verTiposDoc) {
		this.verTiposDoc = verTiposDoc;
	}

}

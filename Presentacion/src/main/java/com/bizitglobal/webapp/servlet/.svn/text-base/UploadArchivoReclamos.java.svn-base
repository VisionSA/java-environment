package com.bizitglobal.webapp.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ArchivoPagoFacilExcpetion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ReclamoCabecera;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ReclamoDoc;
import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoCobroExternoService;
import com.visionis.transaccionador.util.Convertidores;


/**
 * Servlet implementation class UploadFile
 */
public class UploadArchivoReclamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UploadArchivoReclamos.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadArchivoReclamos() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		List listFile = null;
		File file = null;
		try {
			listFile = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		}

		String webAppHome = System.getProperty("catalina.home") + File.separator + "webapps";
		PropertieFile propertieFile = new PropertieFile(webAppHome + File.separator + "contexto.properties");

		String idReclamo = request.getParameter("idReclamo");
		String idOperador = request.getParameter("idOperador");
		String NombreOperador = request.getParameter("NombreOperador");
		String apellidoOperador = request.getParameter("apellidoOperador");

		// String pathArchivos = webAppHome + (tipoArchivo.equals("PF") == true ? propertieFile.getProperties("directorio.pagofacil") :
		// propertieFile.getProperties("directorio.farmacia"));

		String pathArchivos = webAppHome + propertieFile.getProperties("directorio.reclamo");

		File dir = new File(pathArchivos);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyhhmmss");

		// String extension = tipoArchivo.equals("PF") == false ? ".csv" : "";
		String fileName = "Reclamo" + "-" + idReclamo + "-" + simpleDateFormat.format(new Date()) + ".pdf";

		file = new File(dir.getPath() + File.separator + fileName);

		FileItem fileItem = null;
		for (Object item : listFile) {
			if (!((FileItem) item).isFormField()) {
				fileItem = ((FileItem) item);
				break;
			}
		}

		try {

			fileItem.write(file);

		} catch (Exception e) {
			response.setStatus(406);
			return;
		}

		try {

			WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession()
					.getServletContext());

			ArchivoCobroExternoService archivoCobroExternoService = (ArchivoCobroExternoService) applicationContext
					.getBean("archivoCobroExternoService");

			ReclamoCabecera reclamoCabecera = archivoCobroExternoService.buscarReclamos(new Long(idReclamo));

			ReclamoDoc reclamoDoc = new ReclamoDoc();
			Operador operador = new Operador();

			reclamoDoc.setReclamoCabecera(reclamoCabecera);
			reclamoDoc.setFecha(new Date());
			reclamoDoc.setRuta(File.separator + propertieFile.getProperties("directorio.reclamo") + File.separator + fileName);

			operador.setCodigo(new Long(idOperador));
			operador.setApellido(apellidoOperador);
			operador.setNombre(NombreOperador);
			reclamoDoc.setIdOperador(operador);

			reclamoCabecera.setReclamoDocSet(Convertidores.listToSet(archivoCobroExternoService.listAllReclamosAdjuntos(new Long(idReclamo))));

			reclamoCabecera.getReclamoDocSet().add(reclamoDoc);

			archivoCobroExternoService.actualizarReclamo(reclamoCabecera);

			// Operador operador = reclamoCabecera.getReclamoDetalleSet()

			// procesarArchivoPagoFacil.procesarArchivoCobroExterno(file, tipoArchivo);

		} catch (Exception e) {

			logger.error(e, e);

			try {
				if (file != null) {
					file.delete();
				}
			} catch (Exception e2) {
				// No pudo borrar el archivo no hay drama

			}

			if (e instanceof ArchivoPagoFacilExcpetion) {
				response.setStatus(((ArchivoPagoFacilExcpetion) e).status);
			} else {
				response.setStatus(406);
			}

		}

	}

}

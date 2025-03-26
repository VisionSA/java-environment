package com.bizitglobal.webapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteClienteService;
import com.visionis.transaccionador.service.TransaccionService;



public class NotaDebitoJudicialServlet extends HttpServlet implements
javax.servlet.Servlet{
private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(NotaDebitoJudicialServlet.class);
	private FileWriter fw = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotaDebitoJudicialServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			ApplicationContext appContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(getServletContext());
			
			
			
			
			
			CtaCteClienteService ctaCteClienteService = (CtaCteClienteService) appContext
					.getBean("ctaCteClienteService");
			
//			int cantDias = Integer.parseInt(request.getParameter("dias"));
			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.DATE, - cantDias);
			Date fecha = calendar.getTime();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String fechaPagos = format.format(fecha);
			
			String archivo = ctaCteClienteService.notaDebitoJudicial();
			
			log.info("Finalizó el proceso de debito judicial!");
			
			try {
				
				if (archivo != null) {
				
				fw = new FileWriter(System.getProperty("catalina.home") + File.separator
						+ "/webapps/archivos/" + "/NotaDebitoJudicial/nota_debito_" + fechaPagos + ".txt");
				fw.write(archivo);
				fw.close();
				}
				
				
			} catch (Exception e) {
				e.getMessage();
			}
			
		}
		catch (Exception e) {
			log.info("Ocurrió un error en el proceso de devolución de intereses.");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}


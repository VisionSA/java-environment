package com.bizitglobal.webapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;

/**
 * Servlet implementation class GestionClienteServlet
 */
public class GestionClienteServlet extends HttpServlet implements
javax.servlet.Servlet{
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(GestionClienteServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionClienteServlet() {
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
			GestionarClienteService gestionarClienteService = (GestionarClienteService) appContext
					.getBean("gestionarClienteService");
			
			gestionarClienteService.actualizarConsumos();
			
			log.info("Gestion Cliente Actualizada!");
		}
		catch (Exception e) {
			log.info("Ocurrió un error al actualizar gestion cliente.");
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

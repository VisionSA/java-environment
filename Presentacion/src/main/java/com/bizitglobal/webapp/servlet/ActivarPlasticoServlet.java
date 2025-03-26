package com.bizitglobal.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoEstado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoEstadosService;


/**
 * @id 5532
 * @author Hernan Guillen. Bizit Global - Año 2012
 */
public class ActivarPlasticoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 6584463924824216356L;

	private static final String PLASTICO_CLIENTE_SERVICE_NAME = "plasticoClienteService";
	private static final String CLIENTE_SERVICE_NAME = "clienteTransaccionService";
	private static final String PLASTICO_ESTADO_SERVICE_NAME = "plasticoEstadosServiceTarget";
	private PlasticoClienteService plasticoClienteService = null;
	private ClienteTransaccionService clienteTransaccionService = null;
	private PlasticoEstadosService plasticoEstadosService = null;
	private List<PlasticoCliente> listaCuenta = new ArrayList<PlasticoCliente>();
	private ClienteTransaccion ultimoTitular;
	private PlasticoOperacion plasticoOperacion;


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ActivarPlasticoServlet() {
		super();
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			out.print("Test!!!<br>");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			Operador operadorAutomatico = ((OperadorService) appContext.getBean("operadorService")).leerOperador(1212l);
			plasticoClienteService = (PlasticoClienteService) appContext.getBean(PLASTICO_CLIENTE_SERVICE_NAME);
			clienteTransaccionService = (ClienteTransaccionService) appContext.getBean(CLIENTE_SERVICE_NAME);
			plasticoEstadosService = (PlasticoEstadosService) appContext.getBean(PLASTICO_ESTADO_SERVICE_NAME);
			PlasticoEstado estadoActivado = plasticoEstadosService.leerPlasticoEstado(1L);
			PlasticoEstado estadoDesactivado = plasticoEstadosService.leerPlasticoEstado(2L);

			List<PlasticoCliente> listaTarjetas = plasticoClienteService.obtenerPlasticosActivar();
			if (!listaTarjetas.isEmpty()) {
				for (PlasticoCliente p : listaTarjetas) {
					if (ultimoTitular == null) {
						nuevoCiclo(p);
					} else {
						if (pertenece(p)) {
							listaCuenta.add(p);
						} else {
							activarCuenta(operadorAutomatico, estadoActivado, estadoDesactivado);
							nuevoCiclo(p);
						}
					}
				}
				activarCuenta(operadorAutomatico, estadoActivado, estadoDesactivado);
			}

			out.print("Result: <b>Todo correcto</b>");
		} catch (Exception e) {
			out.print("Error Activacion automatica de plasticos!!!");
			e.printStackTrace();
		} finally {
			out.close();
		}

	}


	private void activarCuenta(Operador operadorAutomatico, PlasticoEstado estadoActivado, PlasticoEstado estadoDesactivado)
			throws Exception, PlasticoClienteException {
		ultimoTitular.setDiaCierre(clienteTransaccionService.getDiaPagoCliente(ultimoTitular.getIdCliente()));
		plasticoClienteService.activarPlasticos(listaCuenta, ultimoTitular, estadoActivado, estadoDesactivado, plasticoOperacion, operadorAutomatico);
	}


	private void nuevoCiclo(PlasticoCliente p) throws ClienteTransaccionException {
		listaCuenta.clear();
		if (p.esPlasticoTitular())
			ultimoTitular = p.getClienteTransaccion();
		else
			ultimoTitular = clienteTransaccionService.leerCliente(p.getClienteTransaccion().getIdTitular());
		plasticoOperacion = p.getOperacion();
		listaCuenta.add(p);
	}


	private boolean pertenece(PlasticoCliente p) {
		Long idTitular = p.esPlasticoTitular() ? p.getClienteTransaccion().getIdCliente() : p.getClienteTransaccion().getIdTitular();
		return (ultimoTitular.getIdCliente().equals(idTitular))
				&& plasticoOperacion.getIdPlasticoOperacion().equals(p.getOperacion().getIdPlasticoOperacion());
	}
}

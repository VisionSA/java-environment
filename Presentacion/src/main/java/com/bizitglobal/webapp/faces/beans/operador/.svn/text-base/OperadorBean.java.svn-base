package com.bizitglobal.webapp.faces.beans.operador;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


/**
 * @author Administrator
 * 
 */

@SuppressWarnings({"rawtypes"})
public class OperadorBean extends BaseBean {

	private Logger log = Logger.getLogger(OperadorBean.class);

	private SelectItem[] listEstados = {
			new SelectItem(1, "Activo"), new SelectItem(2, "Inactivo"), new SelectItem(3, "Bloqueado") };

	private Operador op;
	private String confirmarClave;
	private boolean verClave;
	private String useraameAnterior;

	// Lista de roles para el operador.
	private List roles;

	// Rol seleccionado para el operador.
	private Long rolSeleccionado;

	// Titulos para las paginas de alta y modificacion del operador.
	private String tituloLargo = "Tarjeta Fiel - Alta de operadores";
	private String tituloCorto = "Alta de operadores";

	// Indica si se trata de un alta o de una baja.
	private boolean alta = true;
	/* @4287 */
	// fromAlta = true => alta
	// fromAlta = false => modificacion
	private boolean fromAlta;
	/* @F4287 */

	// id hidden para la edicion.
	private Long idOperadorHidden;

	// Ordenar por columnas
	private String nombreColumna;
	private boolean ascendente = true;

	private List operadores;

	// Asigna el estado del Operador.
	// 1-activo 2-inactivo 3-bloqueado
	private Integer estado;


	public OperadorBean() {
		this(null, null, null, null);
	}


	public OperadorBean(Operador op) {
		this(op, null, null, null);
	}


	
	public OperadorBean(Operador op, String confirmarClave, Long rolSeleccionado, List operadores) {
		super();
		this.op = op;
		this.confirmarClave = confirmarClave;
		this.rolSeleccionado = rolSeleccionado;
		this.operadores = operadores;
		roles = OperadorUtil.cargarRoles(operadorService.getRolService());
	}


	public Operador getOp() {
		return op;
	}


	public void setOp(Operador o) {
		op = o;
	}


	public SelectItem[] getListEstados() {
		return listEstados;
	}


	public void setListEstados(SelectItem[] listEstados) {
		this.listEstados = listEstados;
	}


	public List getRoles() {
		return roles;
	}


	public void setRoles(List roles) {
		this.roles = roles;
	}


	public Long getRolSeleccionado() {
		return rolSeleccionado;
	}


	public void setRolSeleccionado(Long rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public List getOperadores() {
		return operadores;
	}


	public void setOperadores(List operadores) {
		this.operadores = operadores;
	}


	public String inicializar() {
		borrar();
		fromAlta = true;
		return "altaOperadores";
	}


	public Long getIdOperadorHidden() {
		return idOperadorHidden;
	}


	public void setIdOperadorHidden(Long idOperadorHidden) {
		this.idOperadorHidden = idOperadorHidden;
	}


	public Integer getEstado() {
		log.info("Ingreso a getEstado");
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}



	public String getconfirmarClave() {
		return confirmarClave;
	}


	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE OPERADORES
	 ************************************************************************/

	public String buscar() {
		Filtro f = new Filtro();
		f.agregarCampoOperValor("estado", Filtro.NOTLIKE, "E");
		f.agregarCampoOperValor("estado", Filtro.NOTLIKE, "D");
		if (op.getCodigo() != null && !op.getCodigo().equals(0L)) {
			f.agregarCampoOperValor("codigo", Filtro.IGUAL, op.getCodigo());

		}
		if (op.getUsername() != null && !op.getUsername().equals("")) {
			f.agregarCampoOperValor("username", Filtro.LIKE, op.getUsername());

		}
		if (op.getNombre() != null && !op.getNombre().equals("")) {
			f.agregarCampoOperValor("nombre", Filtro.LIKE, op.getNombre());

		}
		if (op.getApellido() != null && !op.getApellido().equals("")) {
			f.agregarCampoOperValor("apellido", Filtro.LIKE, op.getApellido());

		}
		try {
			operadores = operadorService.getOperadorService().getOperadores(f);
			Session.redirect("/tarjetafiel/operador/listadoOperadores.jsf");
		} catch (OperadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}


	public String grabar() {
		System.out.println("Entrando a grabar!!!");

		Operador operadorActual = Session.getOperador();

		if (validar()) {

			if (estado == 1) {
				op.setEstado(new String("A"));
			}
			else if (estado == 2) {
				op.setEstado(new String("I"));
			}

			else if (estado == 3) {
				op.setEstado(new String("B"));
			}

			try {
				op.setRol(operadorService.getRolService().leerRol(new Long(rolSeleccionado.longValue())));
				if (alta) {
					op.setOperadorAlta(operadorActual);
					op.setFechaAlta(new Date());
					operadorService.getOperadorService().grabarOperador(op);
					this.confirmarClave = op.getClave();
				} else {
					operadorService.getOperadorService().actualizarOperador(op);
				}

				popup.setPopup(popup.ICONO_OK, "El operador ha sido almacenado exitosamente.");
			} catch (OperadorDuplicateException e1) {
				e1.printStackTrace();
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta del operador.");
			} catch (OperadorException e2) {
				e2.printStackTrace();
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta del operador.");
			} catch (Exception e3) {
				e3.printStackTrace();
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta del operador.");
			}
		}

		return null;
	}


	public void borrar() {
		op = new Operador();
		op.setCodigo(null);

		operadores = null;

		rolSeleccionado = null;
		alta = true;
		tituloLargo = "Tarjeta Fiel - Alta de operadores";
		tituloCorto = "Alta de operadores";
		popup.borrar();
		verClave = true;
		estado = 1;
	}




	public String editarOperador() {
		String result = "";
		alta = false;
		verClave = false;
		tituloCorto = "Modificacion de operadores";
		tituloLargo = "Tarjeta Fiel - Modificacion de operadores";

		try {
			op = operadorService.getOperadorService().leerOperador(idOperadorHidden);
			confirmarClave = op.getClave();
			useraameAnterior = op.getUsername();
			log.info("Confirmar clave: " + confirmarClave);

			rolSeleccionado = op.getRol().getIdRol();
			if (op.getEstado().equals(new String("A"))) {
				estado = 1;
			} else {
				if (op.getEstado().equals(new String("I"))) {
					estado = 2;
				} else {
					if (op.getEstado().equals(new String("B"))) {
						estado = 3;
					}
				}
			}
			result = "altaOperadores";
		} catch (Exception e) {
			popup.setPopup(popup.ICONO_FALLA, "Error al intentar leer el operador.");
			e.printStackTrace();
		}

		return result;
	}


	public String eliminarOperador() {

		Operador operador = null;
		Operador operadorActual = Session.getOperador();
		try {
			operador = operadorService.getOperadorService().leerOperador(idOperadorHidden);
			operador.setEstado("E");
			operador.setOperadorBaja(operadorActual);
			operadorService.getOperadorService().actualizarOperador(operador);
			buscar();
		} catch (OperadorException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String irANuevoOperador() {
		borrar();
		return "altaOperadores";
	}


	public String irAModificarOperador() {
		alta = false;
		verClave = false;
		tituloCorto = "Modificacinn de operadores";
		tituloLargo = "Tarjeta Fiel - Modificacion de operadores";

		log.info("clave: " + op.getClave());

		confirmarClave = op.getClave();

		log.info("Confirmar clave: " + confirmarClave);
		popup.borrar();
		return "altaOperadores";
	}


	public String irAListarOperador() {
		borrar();
		tituloLargo = "Tarjeta Fiel - Operadores";
		tituloCorto = "Listado de operadores";
		Session.redirect("/tarjetafiel/operador/listadoOperadores.jsf");
		return null;
	}


	public String salir() {

		return "inicio";
	}


	public String cambiarClave() {
		verClave = true;
		return "";
	}


	public boolean validar() {
		System.out.println("Entrando a validar!!!");
		error.borrar();
		
		// Valido el username
		if (Validador.esNulo(op.getUsername()) || op.getUsername().equals("")) {
			error.agregar(Error.OPERADOR_USERNAME_REQUERIDO);
			return false;
		}
		if (op.getUsername().length() < Error.LONGITUD_MIN) {
			error.agregar(Error.OPERADOR_USERNAME_LONGITUD_MIN);
			return false;
		}
		if (alta || !useraameAnterior.trim().equals(op.getUsername().trim())) {
			Filtro f = new Filtro();
			f.agregarCampoOperValor("username", Filtro.LIKEXS, op.getUsername());
			try {
				operadores = operadorService.getOperadorService().getOperadores(f);
				if (operadores.size() != 0) {
					error.agregar(Error.OPERADOR_USERNAME_NODISPONIBLE);
					return false;
				}
			} catch (OperadorException e) {
				e.printStackTrace();
				error.agregar("Error al intentar validar el nombre de usuario");
				return false;
			}
		}

		if (Validador.esNulo(op.getNombre()) || op.getNombre().equals("")) {
			error.agregar(Error.OPERADOR_NOMBRE_REQUERIDO);
			return false;
		}

		if (Validador.esNulo(op.getApellido()) || op.getApellido().equals("")) {
			error.agregar(Error.OPERADOR_APELLIDO_REQUERIDO);
			return false;
		}

		// Valido el email
		if (Validador.esNulo(op.getEmail()) || op.getEmail().equals("")) {
			error.agregar(Error.OPERADOR_EMAIL_REQUERIDO);
			return false;
		} else if (!Validador.checkEmail(op.getEmail())) {
			error.agregar(Error.OPERADOR_EMAIL_INCORRECTO);
			return false;
		}

		// valido la clave
		if (Validador.esNulo(op.getClave()) || op.getClave().equals("")) {
			error.agregar(Error.OPERADOR_CLAVE_REQUERIDO);
			return false;
		} else if (op.getClave().length() < Error.LONGITUD_MIN) {
			error.agregar(Error.OPERADOR_CLAVE_LONGITUD_MIN);
			return false;
		}

		// valido la confirmacion de clave y chequeo si coincide
		if (Validador.esNulo(confirmarClave) || confirmarClave.equals("") ||
				Validador.esNulo(op.getClave()) || op.getClave().equals("")) {
			error.agregar(Error.OPERADOR_REPETIR_CLAVE_REQUERIDO);
			return false;
		} else if (!op.getClave().equals(new String(confirmarClave))) {
			error.agregar(Error.OPERADOR_CONFIRMACION_INVALIDA);
			return false;
		}
		return true;
	}


	public boolean isVerClave() {
		return verClave;
	}


	public void setVerClave(boolean verClave) {
		this.verClave = verClave;
	}

}
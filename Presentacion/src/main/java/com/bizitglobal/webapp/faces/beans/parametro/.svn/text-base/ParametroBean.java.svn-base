package com.bizitglobal.webapp.faces.beans.parametro;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.tparametros.exception.ParametroException;
import com.bizitglobal.tarjetafiel.tparametros.negocio.Parametro;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.service.parametro.ABMParametroServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes"})
public class ParametroBean extends BaseBean {
	private Logger log = Logger.getLogger(ParametroBean.class);
	// Atributos del objeto de negocio.
	private Parametro.Id id;
	private String concetab;
	private String colum1;
	private Float colum2;
	private Float colum3;
	private Float colum4;
	private Float colum5;
	private Float colum6;
	private Float colum7;
	private Float colum8;
	private Float colum9;
	private Date colum10;

	// Listado de Parametros
	private List parametros;

	// Mensajes para las paginas de error y success.
	private String falla;
	private String ok;

	// Objetos de servicio
	ABMParametroServiceFaces parametroService = new ABMParametroServiceFaces();


	public ParametroBean() {
		borrar();
		parametros = null;
		falla = null;
		ok = null;
	}


	public List getParametros() {
		return parametros;
	}


	public void setParametros(List parametros) {
		this.parametros = parametros;
	}


	public String getFalla() {
		return falla;
	}


	public void setFalla(String falla) {
		this.falla = falla;
	}


	public String getOk() {
		return ok;
	}


	public void setOk(String ok) {
		this.ok = ok;
	}


	public String getColum1() {
		return colum1;
	}


	public void setColum1(String colum1) {
		this.colum1 = colum1;
	}


	public Date getColum10() {
		return colum10;
	}


	public void setColum10(Date colum10) {
		this.colum10 = colum10;
	}


	public Float getColum2() {
		return colum2;
	}


	public void setColum2(Float colum2) {
		this.colum2 = colum2;
	}


	public Float getColum3() {
		return colum3;
	}


	public void setColum3(Float colum3) {
		this.colum3 = colum3;
	}


	public Float getColum4() {
		return colum4;
	}


	public void setColum4(Float colum4) {
		this.colum4 = colum4;
	}


	public Float getColum5() {
		return colum5;
	}


	public void setColum5(Float colum5) {
		this.colum5 = colum5;
	}


	public Float getColum6() {
		return colum6;
	}


	public void setColum6(Float colum6) {
		this.colum6 = colum6;
	}


	public Float getColum7() {
		return colum7;
	}


	public void setColum7(Float colum7) {
		this.colum7 = colum7;
	}


	public Float getColum8() {
		return colum8;
	}


	public void setColum8(Float colum8) {
		this.colum8 = colum8;
	}


	public Float getColum9() {
		return colum9;
	}


	public void setColum9(Float colum9) {
		this.colum9 = colum9;
	}


	public String getConcetab() {
		return concetab;
	}


	public void setConcetab(String concetab) {
		this.concetab = concetab;
	}


	// Sets y Gets del identificador.
	public Parametro.Id getId() {
		return id;
	}


	public void setId(Parametro.Id id) {
		this.id = id;
	}


	// Sets y Gets de cada parte del objeto id.
	public Integer getNivel1() {
		return id.getNivel1();
	}


	public void setNivel1(Integer nivel1) {
		this.id.setNivel1(nivel1);
	}


	public Integer getNivel2() {
		return id.getNivel2();
	}


	public void setNivel2(Integer nivel2) {
		this.id.setNivel2(nivel2);
	}


	public Integer getNivel3() {
		return id.getNivel3();
	}


	public void setNivel3(Integer nivel3) {
		this.id.setNivel3(nivel3);
	}


	public String listar() {
		String result = "listadoParametros";
		Operador operadorActual = Session.getOperador();
		try {
			parametros = parametroService.getParametroService().getParametros();
			log.info("Creando lista de parametros...");
		} catch (ParametroException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}

		return result;
	}


	/**
	 * @return
	 */
	public String grabar() {
		String result = "alta_falla";
		Operador operadorActual = Session.getOperador();

		Parametro paramGra = armarParametro();

		try {
			parametroService.getParametroService().grabarParametro(paramGra);
			log.info("Grabando parametro ->" + paramGra);
			result = "alta_ok";
			ok = "El parametro ha sido dado de alta.";
			borrar();
		} catch (ParametroException e2) {
			result = "fallaGeneral";
		} catch (Exception e3) {
			result = "accesoDenegado";
		}

		return result;
	}


	public String goEditar() {
		String result = "editarParametro";
		Parametro.Id idAux = construirId();

		try {
			Parametro parametroActual = parametroService.getParametroService().buscarParametro(idAux);
			id = parametroActual.getId();
			concetab = parametroActual.getConcetab();
			colum1 = parametroActual.getColum1();
			colum2 = parametroActual.getColum2();
			colum3 = parametroActual.getColum3();
			colum4 = parametroActual.getColum4();
			colum5 = parametroActual.getColum5();
			colum6 = parametroActual.getColum6();
			colum7 = parametroActual.getColum7();
			colum8 = parametroActual.getColum8();
			colum9 = parametroActual.getColum9();
			colum10 = parametroActual.getColum10();

		} catch (ParametroException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}

		return result;
	}


	public String editar() {
		String result = "";

		Parametro paramActualizar = armarParametro();

		try {
			parametroService.getParametroService().actualizarParametro(paramActualizar);
			borrar();
			result = this.listar();
			log.info("Actualizando parametro->" + paramActualizar);
			ok = "El parametro ha sido actualizado.";
		} catch (ParametroException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}

		return result;
	}


	public String eliminar() {
		String result = "";
		Parametro.Id idAux = construirId();
		try {
			parametroService.getParametroService().borrarParametro(idAux);
			log.info("Parametro eliminado-> " + idAux);
			result = this.listar();
		} catch (ParametroException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}

		return result;
	}


	// Metodos Auxiliares

	public void borrar() {
		id = new Parametro.Id();
		concetab = null;
		colum1 = null;
		colum2 = null;
		colum3 = null;
		colum4 = null;
		colum5 = null;
		colum6 = null;
		colum7 = null;
		colum8 = null;
		colum9 = null;
		colum10 = new Date();
	}


	private Parametro.Id construirId() {
		Integer n1 = new Integer(Session.getRequestParameter("n1Parametro"));
		Integer n2 = new Integer(Session.getRequestParameter("n2Parametro"));
		Integer n3 = new Integer(Session.getRequestParameter("n3Parametro"));

		return new Parametro.Id(n1, n2, n3);
	}


	private Parametro armarParametro() {
		Parametro param = new Parametro();
		param.setId(id);
		param.setConcetab(concetab);
		param.setColum1(colum1);
		param.setColum2(colum2);
		param.setColum3(colum3);
		param.setColum4(colum4);
		param.setColum5(colum5);
		param.setColum6(colum6);
		param.setColum7(colum7);
		param.setColum8(colum8);
		param.setColum9(colum9);
		param.setColum10(colum10);

		return param;
	}


	public String inicializar() {
		return null;
	}


	public boolean validar() {
		return false;
	}
}
package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

/***** @Id:6958 ******/
public class ParamTemplate {
	/*
	 * La siguiente lista de constantes son valores obligatorios al momento de crear el Template y tambien seran obligatoros al momento de grear el
	 * proceso
	 */
	public final static String PARM_EMAIL = "email";
	public final static String PARM_CLAVE = "clave_unica";
	public final static String PARM_ASUNTO = "asunto";
	public final static String PARM_IDENTIFICADOR = "identificador"; // si o si debe ser una clave numerica por que esta relacionado con el Tipo de
																		// Origen que se encuentra en el Template

	private Long idParam;
	private Template template;
	private String parametro;
	private String descripcion;
	private TipoParamTemp tipo;
	private Boolean paramSystem;


	public ParamTemplate() {
		paramSystem = false;
	}


	public Long getIdParam() {
		return idParam;
	}


	public void setIdParam(Long idParam) {
		this.idParam = idParam;
	}


	public Template getTemplate() {
		return template;
	}


	public void setTemplate(Template template) {
		this.template = template;
	}


	public String getParametro() {
		return parametro;
	}


	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public TipoParamTemp getTipo() {
		return tipo;
	}


	public void setTipo(TipoParamTemp tipo) {
		this.tipo = tipo;
	}


	public Boolean getParamSystem() {
		return paramSystem;
	}


	public void setParamSystem(Boolean paramSystem) {
		this.paramSystem = paramSystem;
	}

}

package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import org.apache.log4j.Logger;

import com.bizitglobal.workflow.negocio.FlujoCondicion;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.TipoCondicion;


public class ArmarCondicion {
	private static final Logger log = Logger.getLogger(ArmarCondicion.class);

	private FlujoCondicion flujoCondicion;

	private Long id;
	private Long idTipoUnionSelect;
	private Long idTipoCondicionesSelect;
	private Long idCondicionSelect;

	private boolean opcionA;
	private boolean opcionB;
	private boolean opcionC;
	private boolean opcionAtributoAtributo;


	public ArmarCondicion() {
		super();
		limpiarBuleanos();
		borrar();
		flujoCondicion = new FlujoCondicion();

		if (flujoCondicion.getIdFlujoCondicion() != null && !flujoCondicion.getIdFlujoCondicion().equals(new Long(0)))
			id = flujoCondicion.getIdFlujoCondicion();
	}


	public boolean getOpcionA() {
		return opcionA;
	}


	public void setOpcionA(boolean opcionA) {
		this.opcionA = opcionA;
	}


	public boolean getOpcionB() {
		return opcionB;
	}


	public void setOpcionB(boolean opcionB) {
		this.opcionB = opcionB;
	}


	public boolean getOpcionC() {
		return opcionC;
	}


	public void setOpcionC(boolean opcionC) {
		this.opcionC = opcionC;
	}


	public Integer getOrden() {
		return flujoCondicion.getOrden();
	}


	public void setOrden(Integer orden) {
		flujoCondicion.setOrden(orden);
	}


	public Long getIdCondicionTabla() {
		return flujoCondicion.getIdFlujoCondicion();
	}


	public void setIdCondicionTabla(Long idCondicionTabla) {
		flujoCondicion.setIdFlujoCondicion(idCondicionTabla);
	}


	public TipoCondicion getTipoCondicion() {
		return flujoCondicion.getTipoCondicion();
	}


	public void setTipoCondicion(TipoCondicion tipoCondicion) {
		flujoCondicion.setTipoCondicion(tipoCondicion);
	}


	public void setTipoUnion(String tipoUnion) {
		flujoCondicion.setTipoUnion(tipoUnion);
	}


	public String getValor() {
		return flujoCondicion.getValor();
	}


	public void setValor(String valor) {
		flujoCondicion.setValor(valor);
	}


	public String getAtributoUno() {
		return flujoCondicion.getProcesoAtributo().getNombre();
	}


	public Long getValorAtributoDos() {
		return flujoCondicion.getProcesoAtributoDos().getIdProcesoAtributo();
	}


	public void setValorAtributoDos(Long valorAtribDos) {
		flujoCondicion.setProcesoAtributoDos(new ProcesoAtributo(valorAtribDos));
	}


	public Integer getParentesisFinal() {
		return flujoCondicion.getParentesisFinal();
	}


	public void setParentesisFinal(Integer parentesisFinal) {
		flujoCondicion.setParentesisFinal(parentesisFinal);
	}


	public Integer getParentesisInicial() {
		return flujoCondicion.getParentesisInicio();
	}


	public void setParentesisInicial(Integer parentesisInicial) {
		flujoCondicion.setParentesisInicio(parentesisInicial);
	}


	public void setIdAtributoSelect(Long idAtributoSelect) {
		flujoCondicion.getProcesoAtributo().setIdProcesoAtributo(idAtributoSelect);
	}


	public Long getIdAtributoSelect() {
		return flujoCondicion.getProcesoAtributo().getIdProcesoAtributo();
	}


	public Long getIdCondicionSelect() {
		// log.info("getIdCondicionSelect: " + idCondicionSelect);
		return flujoCondicion.getOperadorCondicion().getIdOperadorCondicion();
	}


	public void setIdCondicionSelect(Long idCondicionSelect) {
		flujoCondicion.getOperadorCondicion().setIdOperadorCondicion(idCondicionSelect);
		// log.info("getIdCondicionSelect: " + idCondicionSelect);
	}


	public Long getIdTipoUnionSelect() {
		return idTipoUnionSelect;
	}


	public void setIdTipoUnionSelect(Long idTipoUnionSelect) {
		this.idTipoUnionSelect = idTipoUnionSelect;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdTipoCondicionesSelect() {
		return idTipoCondicionesSelect;
	}


	public Long getIdAtributoDosSelect() {
		return flujoCondicion.getProcesoAtributoDos().getIdProcesoAtributo();
	}


	public void setIdAtributoDosSelect(Long idAtributoDosSelect) {
		flujoCondicion.getProcesoAtributoDos().setIdProcesoAtributo(idAtributoDosSelect);
	}


	public FlujoCondicion getFlujoCondicion() {
		return flujoCondicion;
	}


	public void setFlujoCondicion(FlujoCondicion flujoCondicion) {
		this.flujoCondicion = flujoCondicion;
	}


	public boolean getOpcionAtributoAtributo() {
		return opcionAtributoAtributo;
	}


	public void setOpcionAtributoAtributo(boolean opcionAtributoAtributo) {
		this.opcionAtributoAtributo = opcionAtributoAtributo;
	}


	public String getArmarParentesisiInic() {
		String aux = " ";
		if (!getParentesisInicial().equals(new Long(0))) {
			for (int i = 0; i < getParentesisInicial().intValue(); i++) {
				aux += "(";
			}
		}
		return aux;
	}


	public String getArmarParentesisiFin() {
		String aux = " ";
		if (!getParentesisFinal().equals(new Long(0))) {
			for (int i = 0; i < getParentesisFinal().intValue(); i++) {
				aux += ")";
			}
		}
		return aux;
	}


	public String getSegundoValor() {

		if (getValorAtributoDos() != null && !getValorAtributoDos().equals(new Long(0)))
			return flujoCondicion.getProcesoAtributoDos().getNombre();

		return getValor();
	}


	public boolean equals(Object obj) {
		if (obj instanceof ArmarCondicion == false)
			return false;
		ArmarCondicion armarCondicion = (ArmarCondicion) obj;
		return armarCondicion.getId().equals(getId());
	}


	public String getTipoUnion() {
		if (flujoCondicion.getTipoUnion().equals(new String("A")))
			return "AND";

		if (flujoCondicion.getTipoUnion().equals(new String("O")))
			return "OR";
		return null;
	}


	public void setIdTipoCondicionesSelect(Long idTipocondicionesSelec) {
		this.idTipoCondicionesSelect = idTipocondicionesSelec;
		int op = this.idTipoCondicionesSelect.intValue();
		switch (op) {
		case 1:
			opcionA = true;
			opcionB = false;
			opcionAtributoAtributo = false;
			opcionC = false;
			break;
		case 2:
			opcionA = false;
			opcionB = true;
			opcionAtributoAtributo = true;
			opcionC = false;
			break;
		case 3:
			opcionA = false;
			opcionB = false;
			opcionAtributoAtributo = false;
			opcionC = true;
			break;
		}
	}


	public void limpiarBuleanos() {
		opcionA = false;
		opcionB = false;
		opcionC = false;
		opcionAtributoAtributo = false;
	}


	public void borrar() {
		id = new Long(0);
		idTipoCondicionesSelect = new Long(0);
		idTipoUnionSelect = new Long(0);
	}
}

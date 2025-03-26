package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.commons.util.CuitValido;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.negocio.IniParametro;
import com.bizitglobal.workflow.negocio.Proceso;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Valor;
import com.bizitglobal.workflow.service.ProcesoAtributoService;


public class TramiteWrapper {
	private static final Logger log = Logger.getLogger(TramiteWrapper.class);

	private IniParametro parametro;
	private Proceso proceso;
	private ProcesoAtributo atributo;

	private String nombreParam;

	private boolean boolCadena;
	private boolean boolTexto;
	private boolean boolEntero;
	private boolean boolDecimal;
	private boolean boolLista;
	private boolean boolVerificacion;
	private boolean boolFecha;
	private boolean boolCuit;

	private boolean completaPanel;
	private boolean boolNombre;

	private String valorTexto;
	private BigDecimal valorNro;
	private boolean valorBoleano;
	private Date valorFecha;
	private String valorCuit;
	private List selectItems = new ArrayList();


	public TramiteWrapper() {

	}


	public TramiteWrapper(IniParametro parametro, ProcesoAtributo atributo, List valores) {
		super();
		borrarBoolean();
		boolNombre = true;
		this.parametro = parametro;
		this.proceso = new Proceso();
		this.atributo = atributo;
		setearValorDefecto();
		setearBoolean();
		if (!valores.isEmpty()) {
			selectItems = new ArrayList();
			Iterator iter = valores.iterator();
			while (iter.hasNext()) {
				Valor valor = (Valor) iter.next();
				selectItems.add(new SelectItem(valor.getId(), valor.getValor()));
			}
		}
	}


	public IniParametro getParametro() {
		return parametro;
	}


	public void setParametro(IniParametro parametro) {
		this.parametro = parametro;
	}


	public Proceso getProceso() {
		return proceso;
	}


	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}


	public ProcesoAtributo getAtributo() {
		return atributo;
	}


	public void setAtributo(ProcesoAtributo atributo) {
		this.atributo = atributo;
	}


	public void setTipoAtributo(String tipoAtributo) {
		atributo.getTipoAtributo().setDescripcion(tipoAtributo);
	}


	public String getTipoAtributo() {
		return atributo.getTipoAtributo().getDescripcion();
	}


	public boolean isBoolCadena() {
		return boolCadena;
	}


	public boolean isBoolDecimal() {
		return boolDecimal;
	}


	public boolean isBoolEntero() {
		return boolEntero;
	}


	public boolean isBoolFecha() {
		return boolFecha;
	}


	public boolean isBoolLista() {
		return boolLista;
	}


	public boolean isBoolTexto() {
		return boolTexto;
	}


	public boolean isBoolCuit() {
		return boolCuit;
	}


	public boolean isBoolVerificacion() {
		return boolVerificacion;
	}


	public String getNombreParam() {
		return atributo.getDescripcion() + ": ";
	}


	public boolean isCompletaPanel() {
		return completaPanel;
	}


	public void setCompletaPanel(boolean completaPanel) {
		this.completaPanel = completaPanel;
	}


	public boolean isBoolNombre() {
		return boolNombre;
	}


	public void setBoolNombre(boolean boolNombre) {
		this.boolNombre = boolNombre;
	}


	public boolean getValorBoleano() {
		return valorBoleano;
	}


	public void setValorBoleano(boolean valorBoleano) {
		this.valorBoleano = valorBoleano;
		log.info("Valor: " + this.valorBoleano);
	}


	public Date getValorFecha() {
		return valorFecha;
	}


	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
		log.info("Valor: " + this.valorFecha);
	}


	public BigDecimal getValorNro() {
		return valorNro;
	}


	public void setValorNro(BigDecimal valorNro) {
		this.valorNro = valorNro;
		log.info("Valor: " + this.valorNro);
	}


	public Integer getValorNroEntero() {
		return new Integer(valorNro.intValue());
	}


	public void setValorNroEntero(Integer valorNroEntero) {
		this.valorNro = new BigDecimal(valorNroEntero.doubleValue());
		log.info("Valor nroEntero: " + valorNro.intValue());
	}


	public String getValorTexto() {
		return valorTexto;
	}


	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
		log.info("Valor: " + this.valorTexto);
	}


	public String getValorCuit() {
		return valorCuit;
	}


	public void setValorCuit(String valorCuit) {
		this.valorCuit = valorCuit;
	}


	public List getSelectItems() {
		return selectItems;
	}


	public void setSelectItems(List selectItems) {
		this.selectItems = selectItems;
	}


	public String getAuxiliar() {

		return "<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>";
	}


	public void borrarBoolean() {

		boolCadena = false;
		boolTexto = false;
		boolEntero = false;
		boolDecimal = false;
		boolLista = false;
		boolVerificacion = false;
		boolFecha = false;
		boolCuit = false;
		completaPanel = false;
	}


	public String toString() {

		return "Parametro Tramite: " +
				" " + proceso.toString() +
				" " + atributo.toString();
	}


	public void setearValorDefecto() {

		switch (atributo.getTipoAtributo().getIdTipoAtributo().intValue()) {

		case 1:
		case 2:
			valorTexto = atributo.getValorDefecto();
			break;

		case 3:
		case 4:
			if (atributo.getValorDefecto() != null) {
				valorNro = new BigDecimal(atributo.getValorDefecto());
			} else {
				valorNro = new BigDecimal(0);
			}

			break;

		case 5:
			valorTexto = atributo.getValorDefecto();
			break;

		case 6:
			if (atributo.getValorDefecto() != null && atributo.getValorDefecto().equals("true") || atributo.getValorDefecto().equals("false")) {
				valorBoleano = new Boolean(atributo.getValorDefecto()).booleanValue();
			} else {
				valorBoleano = false;
			}

			break;

		case 7:
			valorFecha = new Date(atributo.getValorDefecto());
			break;

		case 8:
			valorCuit = atributo.getValorDefecto();
			break;
		}
	}


	public void setearBoolean() {

		switch (atributo.getTipoAtributo().getIdTipoAtributo().intValue()) {

		case 1:
			boolCadena = true;
			break;

		case 2:
			boolTexto = true;
			break;

		case 3:
			boolEntero = true;
			break;

		case 4:
			boolDecimal = true;
			break;

		case 5:
			boolLista = true;
			break;

		case 6:
			boolVerificacion = true;
			break;

		case 7:
			boolFecha = true;
			break;

		case 8:
			boolCuit = true;
			break;
		}
	}


	/*
	 * Este metodo se utiliza para capturar el valor del campo que se cargo en el bean tramites.
	 */
	public String devolverValor() {
		String result = "";

		switch (getAtributo().getTipoAtributo().getIdTipoAtributo().intValue()) {

		case 1:
		case 2:
			result = getValorTexto();
			break;

		case 3:
			result = getValorNroEntero() + "";
			break;

		case 4:
			result = getValorNro() + "";
			break;

		case 5:
			result = getValorTexto();
			break;

		case 6:
			result = getValorBoleano() + "";
			break;

		case 7:
			result = getValorFecha() + "";
			break;

		case 8:
			result = getValorCuit() + "";
			break;
		}
		return result;
	}


	public String validar(ProcesoAtributoService atributoService, Map atributosMap) {
		log.info("Ejecutando --> validar() en wrapper");
		String flag = null;

		if (!completaPanel) {

			if (atributo.getRequerido()) {
				log.info("El atributo es requerido. Se esta verificando que se haya cargado algun dato");
				log.info("valor Texto: " + getValorTexto());
				log.info("valor Bolean: " + getValorBoleano());
				log.info("valor Fecha: " + getValorFecha());
				log.info("valor Nro: " + getValorNro());
				log.info("valor Cuit: " + getValorCuit());

				switch (getAtributo().getTipoAtributo().getIdTipoAtributo().intValue()) {

				case 1:
				case 2:
					if (getValorTexto() == null || getValorTexto().equals("")) {
						flag = "El campo " + getNombreParam() + " es un dato requerido.";
					}
					break;

				case 3:
					if (getValorNroEntero() == null) {
						flag = "El campo " + getNombreParam() + " es un dato requerido.";
					}
					break;

				case 4:
					if (getValorNro() == null) {
						flag = "El campo " + getNombreParam() + " es un dato requerido.";
					}
					break;

				case 5:
					flag = "El campo " + getNombreParam() + " es un dato requerido.";
					break;

				case 6:
					break;

				case 7:
					if (getValorFecha() == null || getValorFecha().equals(new String(""))) {
						flag = "El campo " + getNombreParam() + " es un dato requerido.";
					}
					break;

				case 8:
					if (getValorCuit() == null || getValorCuit().equals("")) {
						flag = "El campo " + getNombreParam() + " es un dato requerido.";
					}
					break;

				default:
					flag = "Error general.";
					break;
				}
			}

			if (flag != null)
				return flag;

			// if (atributo.getNombre().equals("Base")) {
			// try {
			// if (!atributoService.sqlValidaNomTramite(getValorTexto(),proceso.getTitulo())) {
			// flag = "El " + getNombreParam() + " esta siendo usado en otro tramite";
			// }
			// } catch (ProcesoAtributoException e) {
			// flag = "Error al validar el nombre del tramite.";
			// e.printStackTrace();
			// }
			// }

			if (atributo.getTipoAtributo().getIdTipoAtributo().equals(new Long(8))) {
				try {
					if (getValorCuit() != null || !getValorCuit().equals("")) {
						if (getValorCuit().length() == 11) {
							CuitValido cuitValido = new CuitValido(getValorCuit());
						} else {
							flag = "El campo " + getNombreParam() + " es un CUIT/CUIL y debe tener 11 digitos.";
						}
					}
				} catch (CuitNoValidoException e) {
					flag = "El campo " + getNombreParam() + " es un CUIT/CUIL invalido.";
				}
			}
			if (atributo.getValida()) {
				try {
					if (!atributoService.sqlValida(atributo, devolverValor(), atributosMap)) {
						flag = "El campo " + getNombreParam() + " es un dato invalido.";
					}
				} catch (ProcesoAtributoException e) {
					flag = "Ocurrio un error con la validacion del campo " + getNombreParam();
					e.printStackTrace();
				} catch (Exception e) {
					flag = "Ocurrio un error con la validacion del campo " + getNombreParam();
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}

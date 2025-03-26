package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.CuitValido;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.negocio.AtributoValor;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Valor;
import com.bizitglobal.workflow.service.ProcesoAtributoService;


@SuppressWarnings({"rawtypes","unchecked"})
public class AtributoDinamico {
	private static final Logger log = Logger.getLogger(AtributoDinamico.class);

	private ProcesoAtributo atributo;
	private AtributoValor atributoValor;
	private Long idAtributo;
	private boolean soloLectura;
	private List selectItems = new ArrayList();

	private boolean boolCadena;
	private boolean boolTexto;
	private boolean boolEntero;
	private boolean boolDecimal;
	private boolean boolLista;
	private boolean boolVerificacion;
	private boolean boolFecha;
	private boolean completaPanel;
	private boolean boolNombre;
	private boolean boolCuit;
	private boolean boolImagen;


	// private String valorTexto;
	// private BigDecimal valorNro;
	// private boolean valorBoleano;
	// private Date valorFecha;

	public AtributoDinamico() {
		this.idAtributo = null;
	}


	public AtributoDinamico(Long id) {
		this.idAtributo = id;
	}


	public AtributoDinamico(ProcesoAtributo atributo, AtributoValor atributoValor, boolean soloLectura, List valores) {
		super();
		borrarBoolean();
		boolNombre = true;
		this.atributo = atributo;
		this.idAtributo = atributo.getIdProcesoAtributo();
		this.atributoValor = atributoValor;
		if (atributoValor.getValor() == null) {
			this.atributoValor.setValor(atributo.getValorDefecto());
		}
		setearBoolean();
		this.soloLectura = soloLectura;
		if (!valores.isEmpty()) {
			selectItems = new ArrayList();
			Iterator iter = valores.iterator();
			while (iter.hasNext()) {
				Valor valor = (Valor) iter.next();
				selectItems.add(new SelectItem(valor.getId(), valor.getValor()));
			}
		}
	}


	public Long getIdAtributo() {
		return idAtributo;
	}


	public void setIdAtributo(Long idAtributo) {
		this.idAtributo = idAtributo;
	}


	public AtributoValor getAtributoValor() {
		return atributoValor;
	}


	public void setAtributoValor(AtributoValor atributoValor) {
		this.atributoValor = atributoValor;
	}


	public ProcesoAtributo getAtributo() {
		return atributo;
	}


	public void setAtributo(ProcesoAtributo atributo) {
		this.atributo = atributo;
		this.idAtributo = atributo.getIdProcesoAtributo();
	}


	public void setTipoAtributo(String tipoAtributo) {
		atributo.getTipoAtributo().setDescripcion(tipoAtributo);
	}


	public String getTipoAtributo() {
		return atributo.getTipoAtributo().getDescripcion();
	}


	public boolean isSoloLectura() {
		return soloLectura;
	}


	public boolean isBoolCadena() {
		return boolCadena;
	}


	public List getSelectItems() {
		return selectItems;
	}


	public void setSelectItems(List selectItems) {
		this.selectItems = selectItems;
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


	public boolean isBoolVerificacion() {
		return boolVerificacion;
	}


	public boolean isBoolCuit() {
		return boolCuit;
	}


	public boolean isBoolImagen() {
		return boolImagen;
	}


	public boolean isCompletaPanel() {
		return completaPanel;
	}


	public void setCompletaPanel(boolean completaPanel) {
		this.completaPanel = completaPanel;
	}


	public String getNombre() {
		return atributo.getDescripcion();
	}


	public void setNombre(String nombre) {

	}


	public boolean isBoolNombre() {
		return boolNombre;
	}


	public void setBoolNombre(boolean boolNombre) {
		this.boolNombre = boolNombre;
	}


	public boolean getValorBoleano() {
		if (atributoValor.getValor() != null && atributoValor.getValor().equals("true") || atributoValor.getValor().equals("false")) {
			return new Boolean(atributoValor.getValor()).booleanValue();
		} else {
			return false;
		}
	}


	public void setValorBoleano(boolean valorBoleano) {
		this.atributoValor.setValor(valorBoleano + "");
		log.info("set Valor bool: " + atributoValor.getValor());
	}


	public Date getValorFecha() {
		return Convertidores.getdate(atributoValor.getValor());
	}


	public void setValorFecha(Date valorFecha) {
		atributoValor.setValor(Convertidores.getdate(valorFecha));
		log.info("set Valor date: " + atributoValor.getValor());
	}


	public BigDecimal getValorNro() {
		return new BigDecimal(atributoValor.getValor());
	}


	public void setValorNro(BigDecimal valorNro) {
		atributoValor.setValor(valorNro.doubleValue() + "");
		log.info("set Valor nro: " + atributoValor.getValor());
	}


	public Integer getValorNroEntero() {
		return new Integer(atributoValor.getValor());
	}


	public void setValorNroEntero(Integer valorNroEntero) {
		atributoValor.setValor(valorNroEntero.intValue() + "");
		log.info("set Valor nroEntero: " + atributoValor.getValor());
	}


	public String getValorImagen() {
		return atributoValor.getValor();
	}


	public void setValorImagen(String valorImagen) {
		atributoValor.setValor(valorImagen);
		log.info("set Valor imagen: " + atributoValor.getValor());
	}


	public String getValorTexto() {
		return atributoValor.getValor();
	}


	public void setValorTexto(String valorTexto) {
		atributoValor.setValor(valorTexto);
		log.info("set Valor texto: " + atributoValor.getValor());
	}


	public String getValorCuit() {
		return atributoValor.getValor();
	}


	public void setValorCuit(String valorCuit) {
		atributoValor.setValor(valorCuit);
		log.info("set Valor texto: " + atributoValor.getValor());
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
		boolImagen = false;
		completaPanel = false;
	}


	public String toString() {

		return "Atributo Dinamico: " +
				" " + atributo.toString() +
				" " + atributoValor.toString();
	}


	public boolean equals(Object obj) {
		if (obj instanceof AtributoDinamico == false)
			return false;
		AtributoDinamico dinamico = (AtributoDinamico) obj;
		return dinamico.getIdAtributo().equals(getIdAtributo());
	}


	public void setearBoolean() {
		if (atributo.getTipoAtributo().getIdTipoAtributo() != null) {
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

			case 9:
				boolImagen = true;
				break;
			}
		}
	}


	/*
	 * Este metodo se utiliza para retornar el valor del campo que se cargo en el formulario.
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

		case 9:
			result = getValorImagen() + "";
			break;

		}
		return result;
	}


	public String validar(ProcesoAtributoService atributoService, Long idTramite) {
		log.info("Ejecutando --> validar() en wrapper");
		String flag = null;

		if (!completaPanel) {

			if (atributo.getRequerido()) {
				log.info("Valor: " + devolverValor());

				log.info("Case a validar: case " + getAtributo().getTipoAtributo().getIdTipoAtributo().intValue());
				switch (getAtributo().getTipoAtributo().getIdTipoAtributo().intValue()) {

				case 1:
				case 2:
					if (getValorTexto() == null || getValorTexto().equals(""))
						flag = "El campo " + getNombre() + " es un dato requerido.";
					break;

				case 3:
				case 4:
					if (getValorNro() == null)
						flag = "El campo " + getNombre() + " es un dato requerido.";
					break;

				case 5:
					if (devolverValor().equals(""))
						flag = "El campo " + getNombre() + " es un dato requerido.";
					break;

				case 6:
					break;

				case 7:
					if (getValorFecha() == null || getValorFecha().equals(new Date())) {
						flag = "El campo " + getNombre() + " es un dato requerido.";
					}
					break;

				case 8:
					if (getValorCuit() == null || getValorCuit().equals("")) {
						flag = "El campo " + getNombre() + " es un dato requerido.";
					}
					break;

				case 9:
					if (getValorImagen() == null || getValorImagen().equals("")) {
						flag = "El campo " + getNombre() + " es un dato requerido.";
					}
					break;

				default:
					flag = "Error general.";
					break;
				}
			}
			if (flag != null)
				return flag;

			if (atributo.getTipoAtributo().getIdTipoAtributo().equals(new Long(8))) {
				try {
					if (getValorCuit() != null || !getValorCuit().equals("")) {
						if (getValorCuit().length() == 11) {
							CuitValido cuitValido = new CuitValido(getValorCuit());
						} else {
							flag = "El campo " + getNombre() + " es un CUIT/CUIL y debe tener 11 digitos.";
						}
					}
				} catch (CuitNoValidoException e) {
					flag = "El campo " + getNombre() + " es un CUIT/CUIL invalido.";
				}
			}
			if (atributo.getValida()) {
				try {
					if (!atributoService.sqlValida(atributo, devolverValor(), idTramite)) {
						flag = "El campo " + getNombre() + " es un dato invalido.";
					}
				} catch (ProcesoAtributoException e) {
					flag = "Ocurrio un error con la validacion del campo " + getNombre();
					e.printStackTrace();
				} catch (Exception e) {
					flag = "Ocurrio un eror con la validacion del campo " + getNombre();
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}

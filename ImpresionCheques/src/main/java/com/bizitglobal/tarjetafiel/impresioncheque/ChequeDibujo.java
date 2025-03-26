/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque;

import com.bizitglobal.tarjetafiel.commons.util.NumeroALetraConvertidor;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import com.bizitglobal.tarjetafiel.view.PanelChequeBBVA;

import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.omg.CORBA.OMGVMCID;

/**
 *
 * @author Mario
 */
public class ChequeDibujo implements Printable{

    private String diaEmitido;
    private String mesEmitido;
    private String anioEmitido;
    private String diaPagar;
    private String mesPagar;
    private String anioPagar;
    private String beneficiario;
    private String pesosLetra;
    private String centavosLetra;
    private String pesosNum;
    private Character esCruzado;
    private Character esNoOrden;
    /**
    * Son los ultimos X cheques de una chequera.
    */
    public static final Long CHEQUES_NO_SE_IMPRIMEN=2L;
    
    private PanelChequeBBVA panelChequeBBVA;

    public ChequeDibujo(){
        diaEmitido="";
        mesEmitido="";
        setAnioEmitido("");
        diaPagar="";
        mesPagar="";
        setAnioPagar("");
        beneficiario="";
        pesosLetra="";
        centavosLetra="";
        pesosNum="";

    }
    public ChequeDibujo(Cheque cheque){
    	this();
        esNoOrden = cheque.getNoOrden();
    	esCruzado = cheque.getEsCruzado();
        diaEmitido = cheque.getFechaEmisionFormat().substring(0, 2);
        mesEmitido = mesLetras(new Integer(cheque.getFechaEmisionFormat().substring(3, 5)));
        setAnioEmitido(cheque.getFechaEmisionFormat().substring(6, 10));
        diaPagar = cheque.getFechaPagoFormat().substring(0, 2);
        mesPagar = mesLetras(new Integer(cheque.getFechaPagoFormat().substring(3, 5)));
        setAnioPagar(cheque.getFechaPagoFormat().substring(6, 10));
        beneficiario =  dibujarALaOrden(cheque.getBeneficiario(), cheque.getNoOrden()) ;
        BigDecimal importe = new BigDecimal(cheque.getImporte()).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal entero = new BigDecimal(importe.longValue()).setScale(2,BigDecimal.ROUND_HALF_UP);
        pesosLetra = NumeroALetraConvertidor.convertNumberToLetter(entero.longValue());
        BigDecimal decimales = importe.subtract(entero).multiply(new BigDecimal(100).setScale(0,BigDecimal.ROUND_HALF_UP));
        if (decimales.compareTo(new BigDecimal(0))!= 0) {
        	centavosLetra = "CON " + decimales.intValue() + "/100";
        	pesosNum = importe.longValue() + "," + decimales.intValue();
        	
        	pesosNum= JhFormat.formatearNumero(importe,2);
        	
		}else {
			pesosNum = importe.longValue() + ",00";
		}
        pesosLetra = pesosLetra + centavosLetra;
        String asteriscos = "";
        for (int i = 0; i < 21 - pesosNum.length(); i++) {
			asteriscos = asteriscos + "*";
		}
        pesosNum = asteriscos + pesosNum;
    }
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }

    public float puntos(double  cm){
        double pto = 28.346456692913385826771653543307;
        return (float)(cm * pto);
    }

    private String mesLetras(int mes){
    	switch (mes) {
    	case 1:
			return "Enero";
    	case 2:
			return "Febrero";
    	case 3:
			return "Marzo";
    	case 4:
			return "Abril";
    	case 5:
			return "Mayo";
    	case 6:
			return "Junio";
    	case 7:
			return "Julio";
    	case 8:
			return "Agosto";
    	case 9:
			return "Septiembre";
    	case 10:
			return "Octubre";
    	case 11:
			return "Noviembre";
    	case 12:
			return "Diciembre";
		default:
			return "ERROR";
		}
    }
    

    public void dibujarCruzado(Graphics2D g2d, Character esCruzado)
    {
        if (esCruzado.equals(new Character('S'))) {
            g2d.drawLine(1,70,70,1);
            g2d.drawLine(1,100,100,1);
            
        }
    }

    public String dibujarALaOrden(String benef, Character esNoOrden){
    	
    	if(esNoOrden.equals(new Character('N'))){
            return benef + " - No a la Orden";
        }
        else{
            return benef;
        }
    }

    public static String[] CortaLineaCheque(String linea,int capacidad){
        char []letras = linea.toCharArray();
        int i=0;
        String rdo[]=new String[2];
        if(letras.length>capacidad)        {
            for(i=capacidad;i>0;i--){
                if(new Character(letras[i]).equals(new Character(' '))){
                        rdo[0]= linea.substring(0,i);
                        break;
                    }
                }
          rdo[1]=linea.substring(i,linea.length());
        }
        else{
            rdo[0]=linea;
            rdo[1]=" ";
        }

        return rdo;
    }
    
    public Character getEsCruzado() {
		return esCruzado;
	}
	public void setEsCruzado(Character esCruzado) {
		this.esCruzado = esCruzado;
	}
	/**
     * @return the diaEmitido
     */
    public String getDiaEmitido() {
        return diaEmitido;
    }

    /**
     * @param diaEmitido the diaEmitido to set
     */
    public void setDiaEmitido(String diaEmitido) {
        this.diaEmitido = diaEmitido;
    }

    /**
     * @return the mesEmitido
     */
    public String getMesEmitido() {
        return mesEmitido;
    }

    /**
     * @param mesEmitido the mesEmitido to set
     */
    public void setMesEmitido(String mesEmitido) {
        this.mesEmitido = mesEmitido;
    }

    
    /**
     * @return the diaPagar
     */
    public String getDiaPagar() {
        return diaPagar;
    }

    /**
     * @param diaPagar the diaPagar to set
     */
    public void setDiaPagar(String diaPagar) {
        this.diaPagar = diaPagar;
    }

    /**
     * @return the mesPagar
     */
    public String getMesPagar() {
        return mesPagar;
    }

    /**
     * @param mesPagar the mesPagar to set
     */
    public void setMesPagar(String mesPagar) {
        this.mesPagar = mesPagar;
    }

    
    public String getBeneficiario() {
		return beneficiario;
	}
 
    public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	/**
     * @return the pesosLetra
     */
    public String getPesosLetra() {
        return pesosLetra;
    }

    /**
     * @param pesosLetra the pesosLetra to set
     */
    public void setPesosLetra(String pesosLetra) {
        this.pesosLetra = pesosLetra;
    }

    /**
     * @return the centavosLetra
     */
    public String getCentavosLetra() {
        return centavosLetra;
    }

    /**
     * @param centavosLetra the centavosLetra to set
     */
    public void setCentavosLetra(String centavosLetra) {
        this.centavosLetra = centavosLetra;
    }

    /**
     * @return the pesosNum
     */
    public String getPesosNum() {
        return pesosNum;
    }

    /**
     * @param pesosNum the pesosNum to set
     */
    public void setPesosNum(String pesosNum) {
        this.pesosNum = pesosNum;
    }

    /**
     * @return the panelChequeBBVA
     */
    public PanelChequeBBVA getPanelChequeBBVA() {
        return panelChequeBBVA;
    }

    /**
     * @param panelChequeBBVA the panelChequeBBVA to set
     */
    public void setPanelChequeBBVA(PanelChequeBBVA panelChequeBBVA) {
        this.panelChequeBBVA = panelChequeBBVA;
    }
    public void setAnioEmitido(String anioEmitido) {
		this.anioEmitido = anioEmitido;
	}
	public String getAnioEmitido() {
		return anioEmitido;
	}
	public void setAnioPagar(String anioPagar) {
		this.anioPagar = anioPagar;
	}
	public String getAnioPagar() {
		return anioPagar;
	}

    /**
     * @return the esALaOrden
     */
    public Character getEsNoOrden() {
        return esNoOrden;
    }

    /**
     * @param esALaOrden the esALaOrden to set
     */
    public void setEsNoOrden(Character esNoOrden) {
        this.esNoOrden = esNoOrden;
    }



   
}

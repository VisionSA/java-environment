/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.print;

import java.awt.print.PageFormat;
import java.io.Serializable;

/**
 *
 * @author sporta
 */
public class TrabajoImpresion implements Serializable{

    private Impresora impresora;

    private byte[] trabajo;

    private Exception exception;

    private String mensaje;

    private ConfiguracionTrabajo configuracion;

    private IPrintableDoc printableDoc;
        
    /**
     * Tipo de impresion: puede ser texto o pdf
     */
    private String tipo;

    public TrabajoImpresion() {
    }

    public Impresora getImpresora() {
        return impresora;
    }

    public byte[] getTrabajo() {
        return trabajo;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }

    public void setTrabajo(byte[] trabajo) {
        this.trabajo = trabajo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ConfiguracionTrabajo getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionTrabajo configuracion) {
        this.configuracion = configuracion;
    }

    public IPrintableDoc getPrintableDoc() {
        return printableDoc;
    }

    public void setPrintableDoc(IPrintableDoc printableDoc) {
        this.printableDoc = printableDoc;
    }

    

}

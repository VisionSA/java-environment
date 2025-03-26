/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque.modelos;

import com.bizitglobal.tarjetafiel.impresioncheque.Impresora;

/**
 *
 * @author Mario
 */
public class ImpresoraModel {
    private Impresora impresoraSeleccionada= new Impresora();

    public ImpresoraModel() {
    }

    /**
     * @return the impresoraSeleccionada
     */
    public Impresora getImpresoraSeleccionada() {
        return impresoraSeleccionada;
    }

    /**
     * @param impresoraSeleccionada the impresoraSeleccionada to set
     */
    public void setImpresoraSeleccionada(Impresora impresoraSeleccionada) {
        this.impresoraSeleccionada = impresoraSeleccionada;
    }



}

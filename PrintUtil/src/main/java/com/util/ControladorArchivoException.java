/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

/**
 *
 * @author sporta
 */
public class ControladorArchivoException extends Exception{

    public ControladorArchivoException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }

}

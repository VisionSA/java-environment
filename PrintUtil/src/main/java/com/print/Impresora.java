/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.print;

import java.io.Serializable;

/**
 *
 * @author sporta
 */
public class Impresora implements Serializable{

    private String nombre;

    private String ipServer;

    private Integer portServer;

    private Boolean aceptaTrabajos;
    

    public Impresora() {
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public int getPortServer() {
        return portServer;
    }

    public void setPortServer(Integer portServer) {
        this.portServer = portServer;
    }

    public Boolean getAceptaTrabajos() {
        return aceptaTrabajos;
    }

    public void setAceptaTrabajos(Boolean aceptaTrabajos) {
        this.aceptaTrabajos = aceptaTrabajos;
    }

    
}

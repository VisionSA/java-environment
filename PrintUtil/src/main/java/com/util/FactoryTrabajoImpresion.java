/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import com.print.ConfiguracionTrabajo;
import com.print.Impresora;
import com.print.TrabajoImpresion;

/**
 *
 * @author sporta
 */
public class FactoryTrabajoImpresion {

    protected Impresora impresora;

    protected  TrabajoImpresion trabajoImpresion;

    protected ConfiguracionTrabajo configuracionTrabajo;

    protected EnviarTrabajoDeImpresion enviarTrabajoDeImpresion;

    protected IResponder iResponder;

    public FactoryTrabajoImpresion(IResponder iResponder) {        
        this.iResponder = iResponder;
        trabajoImpresion = new TrabajoImpresion();
        configuracionTrabajo = new ConfiguracionTrabajo();
        trabajoImpresion.setConfiguracion(configuracionTrabajo);
        impresora = new Impresora();
        trabajoImpresion.setImpresora(impresora);
        enviarTrabajoDeImpresion = new EnviarTrabajoDeImpresion(this.iResponder, trabajoImpresion);
    }

    public void setParametrosImpresora(String ipServer, String nombre, int portServer){
        impresora.setIpServer(ipServer);
        impresora.setNombre(nombre);
        impresora.setPortServer(portServer);
    }

    public ConfiguracionTrabajo getConfiguracionTrabajo() {
        return configuracionTrabajo;
    }

    public EnviarTrabajoDeImpresion getEnviarTrabajoDeImpresion() {
        return enviarTrabajoDeImpresion;
    }

    public Impresora getImpresora() {
        return impresora;
    }

    public TrabajoImpresion getTrabajoImpresion() {
        return trabajoImpresion;
    }
    
    public void setConfiguracionTrabajo(ConfiguracionTrabajo configuracionTrabajo) {
        this.configuracionTrabajo = configuracionTrabajo;
    }

    public void setEnviarTrabajoDeImpresion(EnviarTrabajoDeImpresion enviarTrabajoDeImpresion) {
        this.enviarTrabajoDeImpresion = enviarTrabajoDeImpresion;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }

    public void setTrabajoImpresion(TrabajoImpresion trabajoImpresion) {
        this.trabajoImpresion = trabajoImpresion;
    }

    public IResponder getIResponder() {
        return iResponder;
    }

    public void setIResponder(IResponder iResponder) {
        this.iResponder = iResponder;
    }


    

    

}

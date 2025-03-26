/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author sporta
 */
public class ReemplazaVariables {

    public String string;
    
    public ReemplazaVariables(String string) {
        this.string = string;
    }

    public String reemplazaVariables(HashMap<String,String> parametros){

        string = reemplazarVariablesGenericas();
        
        if(string.contains("%medioPago%") && parametros.containsKey("%medioPago%") && parametros.get("%medioPago%") != null){
            string = string.replace("%medioPago%", parametros.get("%medioPago%"));
        }
        if(string.contains("%total%") && parametros.containsKey("%total%") && parametros.get("%medioPago%") != null){
            string = string.replace("%total%", parametros.get("%total%"));
        }                
        if(string.contains("%resumen%") && parametros.containsKey("%resumen%") && parametros.get("%resumen%") != null){
            string = string.replace("%resumen%", parametros.get("%resumen%"));
        }
        if(string.contains("%cuenta%") && parametros.containsKey("%cuenta%") && parametros.get("%cuenta%") != null){
            string = string.replace("%cuenta%", parametros.get("%cuenta%"));
        }
        if(string.contains("%nombre-cliente%") && parametros.containsKey("%nombre-cliente%") && parametros.get("%nombre-cliente%") != null){
            string = string.replace("%nombre-cliente%", parametros.get("%nombre-cliente%"));
        }
        if(string.contains("%operador%") && parametros.containsKey("%operador%") && parametros.get("%operador%") != null){
            string = string.replace("%operador%", parametros.get("%operador%"));
        }
        if(string.contains("%transaccion%") && parametros.containsKey("%transaccion%") && parametros.get("%transaccion%") != null){
            string = string.replace("%transaccion%", parametros.get("%transaccion%"));
        }
        if(string.contains("%recibo%") && parametros.containsKey("%recibo%") && parametros.get("%recibo%") != null){
            string = string.replace("%recibo%", parametros.get("%recibo%"));
        }
        
        return string;
    }

    private String reemplazarVariablesGenericas(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(string.contains("%fecha%")){
            string = string.replaceAll("%fecha%", simpleDateFormat.format(new Date()));
        }
        if(string.contains("%hora%")){
            simpleDateFormat.applyPattern("HH:mm");
            string = string.replaceAll("%hora%", simpleDateFormat.format(new Date()));
        }
        return string;
    }
    
}

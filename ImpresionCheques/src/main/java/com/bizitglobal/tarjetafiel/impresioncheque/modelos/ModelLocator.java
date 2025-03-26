/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque.modelos;

/**
 *
 * @author sporta
 */
public class ModelLocator {
    private static ModelLocator modelLocator;

    public ChequeModel chequeModel = new ChequeModel();
    public FrameModel frameModel= new FrameModel();
    public ChequeraModel chequeraModel = new ChequeraModel();

    public ChequeModel chequeProcesadosModel = new ChequeModel();
    public ImpresoraModel impresoraSeleccionada= new ImpresoraModel();
    

    private ModelLocator() {
    }

    public static ModelLocator getInstance(){
        if(modelLocator == null){
            modelLocator = new ModelLocator();
        }

        return modelLocator;
    }


}

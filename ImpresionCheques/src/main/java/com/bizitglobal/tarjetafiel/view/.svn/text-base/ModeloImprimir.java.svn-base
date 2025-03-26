/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.view;

import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import java.util.Date;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Mario
 */
public class ModeloImprimir extends Modelo{

    //METODOS QUE SE DEBEN EDITAR PARA AGREGAR O QUITAR COLUMNAS AL TABLE
    

    public ModeloImprimir() {
        super();
        columnName = new String[]{"Numero","Cheque", "Beneficiario", "Importe", "F. Emision","F. Pago"};
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        MainView.ChequeWape aux = (MainView.ChequeWape) (datos.get(rowIndex));
        switch (columnIndex) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                aux.getCheque().setBeneficiario((String) aValue);
                break;
            case 3:
                aux.getCheque().setImporte((Double) aValue);
                break;
            case 4:
                aux.getCheque().setFechaEmision((Date) aValue);
                break;
            case 5:
                aux.getCheque().setFechaPago((Date) aValue);
                break;
            default:
                break;
        }

        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
        TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex, columnIndex);
        // ... y pasándoselo a los suscriptores.
        avisaSuscriptores(evento);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MainView.ChequeWape aux;
        aux = (MainView.ChequeWape) (datos.get(rowIndex));
        switch (columnIndex) {

            case 0:
                return aux.getCheque().getNumero();
            case 1:
                return aux.getCheque().getIdCheque();
            case 2:
                return aux.getCheque().getBeneficiario();
            case 3:
                return new Double(aux.getCheque().getImporte());
            case 4:
                return aux.getCheque().getFechaEmision();
            case 5:
                return aux.getCheque().getFechaPago();
//            case 4:
//                return aux.getNumero();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
    }

    private void datosChequesSetImprimir(Boolean bol, int rowIndex) {
        TableModelEvent evento;
        if (bol) {
            for (int i = 0; i <= rowIndex; i++) {
                Cheque aux = (Cheque) datos.get(i);
                aux.setImprimir(bol);
            }
            evento = new TableModelEvent(this, 0,
                    rowIndex, TableModelEvent.ALL_COLUMNS,
                    TableModelEvent.UPDATE);
        } else {
            for (int i = rowIndex; i < datos.size(); i++) {
                Cheque aux = (Cheque) datos.get(i);
                aux.setImprimir(bol);
            }
            evento = new TableModelEvent(this, rowIndex,
                    this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                    TableModelEvent.UPDATE);
        }

        avisaSuscriptores(evento);
    }
}

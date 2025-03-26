package com.bizitglobal.tarjetafiel.view;

import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import java.util.Date;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.LinkedList;
import java.util.Vector;

public class Modelo extends DefaultTableModel {

    protected LinkedList datos = new LinkedList();
    protected LinkedList listeners = new LinkedList();
    protected String[] columnName;
    
    public Modelo() {
        columnName = new String[]{"Imprimir", "Cheque", "Beneficiario", "Importe", "F. Emision","F. Pago","Numero"};
    }



    public void borraCheque(int fila) {
        // Se borra la fila
        datos.remove(fila);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent(this, fila, fila,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pasándoselo a los suscriptores
        avisaSuscriptores(evento);
    }

    public void borraCheque(Cheque cheque) {
        // Se borra la fila
        datos.remove(cheque);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent(this,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pasándoselo a los suscriptores
        avisaSuscriptores(evento);
    }

    public void borraChequeWraper(MainView.ChequeWape cheque) {
        // Se borra la fila
        datos.remove(cheque);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent(this,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pasándoselo a los suscriptores
        avisaSuscriptores(evento);
    }

    public void anhadeCheque(Cheque cheque) {
        // Añade la persona al modelo
        datos.add(cheque);

        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1,
                this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }

    public void anhadeChequeWraper(MainView.ChequeWape chequeWape) {
        // Añade la persona al modelo
        datos.add(chequeWape);

        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1,
                this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }



    public void updateCheque(Cheque chequeOld, Cheque chequeNew) {
        // Añade la persona al modelo
        datos.remove(chequeOld);
        datos.add(chequeNew);



        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1,
                this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }

    public Cheque getCheque(int index) {
        return (Cheque) datos.get(index);
    }

    public void avisaSuscriptores(TableModelEvent evento) {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i = 0; i < listeners.size(); i++) {
            ((TableModelListener) listeners.get(i)).tableChanged(evento);
        }
    }

    public void setList(LinkedList lnkList) {
        datos = lnkList;

        TableModelEvent evento;
        evento = new TableModelEvent(this);
        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }

    public LinkedList getList() {
        return datos;
    }

    private static Vector nonNullVector(Vector v) {
        return (v != null) ? v : new Vector();
    }

    

    
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Devuelve el nombre de cada columna. Este texto aparecerá en la
        // cabecera de la tabla.

        return columnName[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }
    
    @Override
    public int getRowCount() {

        return (datos != null) ? datos.size() : 0;
    }

    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    @Override
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add(l);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    
    


    

    //METODOS QUE SE DEBEN EDITAR PARA AGREGAR O QUITAR COLUMNAS AL TABLE
    

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        MainView.ChequeWape aux = (MainView.ChequeWape) (datos.get(rowIndex));
        switch (columnIndex) {
            case 0:
                aux.setImprimir((Boolean) aValue);
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
            case 6:
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
                return new Boolean(aux.getImprimir());
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
            case 6:
                return aux.getCheque().getNumero();
            default:
                return null;
        }
    }

    
}

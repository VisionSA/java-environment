/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainView.java
 *
 * Created on Jul 13, 2009, 9:03:30 PM
 */
package com.bizitglobal.tarjetafiel.view;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraException;
import com.bizitglobal.tarjetafiel.impresioncheque.*;
import java.awt.Component;
import java.util.logging.Level;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.bizitglobal.tarjetafiel.impresioncheque.Impresora;
import com.bizitglobal.tarjetafiel.impresioncheque.core.Contexto;
import com.bizitglobal.tarjetafiel.impresioncheque.modelos.ModelLocator;
import javax.swing.JList;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;
import com.bizitglobal.tarjetafiel.fondos.service.impl.*;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class MainView extends javax.swing.JFrame {

	//private static Logger logger = LoggerFactory.getLogger(MainView.class);
    /** Creates new form MainView */
    private ChequeServiceImpl chequeServiceImpl;
    private BancoPropioServiceImpl bancoPropioServiceImpl;
    private ChequeraServiceImpl chequeraServiceImpl;
    private Filtro filtro = new Filtro();
    private Long numProximoUsar = new Long("0");
    private Long idChequeraUsada = new Long("0");
    private LinkedList<ChequeWape> listChequesRdos;
    private LinkedList<ChequeWape> listChequesImprimir;
    private LinkedList<ChequeWape> listChequesSeleccionar;

    public MainView() {

        Contexto.getInstance();
        ModelLocator.getInstance();
        initComponents();
        cargarImpresoras();
        cargarCheques(new Long("0"));
        cargarBancos();
        cargarChequeras(new Long("0"));
        //CboBancos.setSelectedIndex(-1);

        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        ListSelectionModel rowSM = jTable1.getSelectionModel();
//
//        rowSM.addListSelectionListener(new ListSelectionListener() {
//
//            public void valueChanged(ListSelectionEvent e) {
//                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
//                if (lsm.isSelectionEmpty()) {
//                    //./no rows are selected
//                    } else {
//                    int selectedRow = lsm.getMinSelectionIndex();
//                    jTable1.getModel().setValueAt(new Boolean(true), lsm.getMinSelectionIndex(), 3);
//                }
//            }
//        });

        cboChequeras.setRenderer(new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = new JLabel();
                if (value != null) {
                    Chequera chequera = (Chequera) value;
                    label.setText(((Chequera) value).getIdChequera().toString() + "  -" + "Desde:  " + chequera.getNroInicio().toString() + "  Hasta:  " + chequera.getNroFin().toString() + "  Prox:  " + chequera.getProximoAUsar().toString());
                    label.setOpaque(true);
                    label.setHorizontalAlignment(JLabel.LEFT);
                    label.setVerticalAlignment(JLabel.CENTER);

                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }

                    mostrarNumerosChequera();
                    lblChequera.setVisible(true);
                } else {
                    lblChequera.setVisible(false);
                }

                return label;
            }
        });

        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblImprimir = new javax.swing.JTable();
        btnPasarCheque = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboImpresoras = new javax.swing.JComboBox();
        cboChequeras = new javax.swing.JComboBox();
        CboBancos = new javax.swing.JComboBox();
        lblChequera = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Impresion de cheques");

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir_click(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tblImprimir.setModel(new ModeloImprimir());
        jScrollPane2.setViewportView(tblImprimir);

        btnPasarCheque.setText("Seleccionar");
        btnPasarCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarChequeActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblError.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblError.setForeground(new java.awt.Color(255, 0, 51));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jLabel1.setText("Bancos: ");

        jLabel2.setText("Chequeras:");

        jLabel3.setText("Impresoras:");

        cboImpresoras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboChequeras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChequeras.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboChequerasItemStateChanged(evt);
            }
        });

        CboBancos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CboBancos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CboBancosItemStateChanged(evt);
            }
        });

        lblChequera.setText("Chequera");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel3))
                .add(34, 34, 34)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(CboBancos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 375, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, cboImpresoras, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, cboChequeras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblChequera, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 446, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, CboBancos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboChequeras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(lblChequera))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboImpresoras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 843, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblError, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(btnImprimir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btnCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(678, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(btnPasarCheque)
                        .addContainerGap(768, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblError)
                .add(7, 7, 7)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnPasarCheque)
                .add(23, 23, 23)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnCancelar)
                    .add(btnImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimir_click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir_click
        // TODO add your handling code here:

        //ACA SE DEBERIA GUARDAR EN LA TABLA TEMPORAL LOS CHEQUES ANTES DE IMPRIMIRLOS

        LinkedList<ChequeWape> list = ((Modelo) tblImprimir.getModel()).getList();

        if (list.size() > 0) {
            for (ChequeWape cheque : list) {
                imprimirCheque(cheque.getCheque());
            }
            ModelLocator modelLocator = ModelLocator.getInstance();
            modelLocator.chequeModel.setListCheques(((Modelo) tblImprimir.getModel()).getList());
            modelLocator.frameModel.setFrameRef(this);
            modelLocator.chequeraModel.setChequera((Chequera) cboChequeras.getSelectedItem());
            modelLocator.impresoraSeleccionada.setImpresoraSeleccionada((Impresora) cboImpresoras.getSelectedItem());

            ResultadoImpresion resultadoImpresion = new ResultadoImpresion();            
           
        } else {
            JOptionPane.showMessageDialog(this, new String("Primero seleccione algun cheque para imprimir."),
                "Impresion Cheques", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimir_click

    public void imprimirCheque(Cheque cheque) {
        Impresora print = (Impresora) cboImpresoras.getSelectedItem();
        BancoPropio bancoPropio = (BancoPropio) CboBancos.getSelectedItem();

        String eval = bancoPropio.getIdBancoPropio().toString();
        switch (Integer.parseInt(eval)) {
            case 1:
                //DE SAN JUAN S.A.
                ChequeBsj chequeBsj = new ChequeBsj(cheque);
                print.imprimir(chequeBsj);
                break;
            case 2:
                //FRANCES
                ChequeBbva chequeBbva = new ChequeBbva(cheque);
                print.imprimir(chequeBbva);
                break;
            case 3:
                //STANDARD BANK
                ChequeStandartBank chequeStandartBank = new ChequeStandartBank(cheque);
                print.imprimir(chequeStandartBank);
                break;
            default:
                System.out.println("error");
                break;
        }
//        STANDARD BANK                                      3
//        FRANCES                                            2
//        DE SAN JUAN S.A.                                   1

    }

    private void CboBancosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CboBancosItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            cargarCuentaBcoSeleccionado();
        }
    }//GEN-LAST:event_CboBancosItemStateChanged

    private void cargarCuentaBcoSeleccionado() {
        BancoPropio bancoPropio = (BancoPropio) CboBancos.getSelectedItem();
        cargarCheques(bancoPropio.getIdBancoPropio());
        cargarChequeras(bancoPropio.getIdBancoPropio());
    }

    private void cboChequerasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboChequerasItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            mostrarNumerosChequera();
        }
    }//GEN-LAST:event_cboChequerasItemStateChanged

    private void mostrarNumerosChequera() {
        Chequera chequera = (Chequera) cboChequeras.getSelectedItem();
        lblChequera.setText("Desde:  " + chequera.getNroInicio().toString() + "  Hasta:  " + chequera.getNroFin().toString() + "  Prox:  " + chequera.getProximoAUsar().toString());
    }

    private void btnPasarChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarChequeActionPerformed
        // TODO add your handling code here:
        Long numSecChequera = Long.parseLong("0");
        ModeloImprimir modImprimir = (ModeloImprimir) tblImprimir.getModel();
        //Cheques para imprimier  -->> ListCheques
        for (ChequeWape cheque : (LinkedList<ChequeWape>) ((Modelo) jTable1.getModel()).getList()) {
            if (cheque.getImprimir()) {
                numSecChequera = getNextNumeroImpresion(((Chequera) cboChequeras.getSelectedItem()).getIdChequera(), ((Chequera) cboChequeras.getSelectedItem()).getProximoAUsar());

                if (numSecChequera + ChequeDibujo.CHEQUES_NO_SE_IMPRIMEN <= ((Chequera) cboChequeras.getSelectedItem()).getNroFin()) {
                    cheque.getCheque().setNumero(numSecChequera.toString());
                    modImprimir.anhadeChequeWraper(cheque);
                }
            }
        }
        tblImprimir.setModel(modImprimir);
        	
        if (modImprimir.getRowCount() > 0) {
            Modelo mod = (Modelo) jTable1.getModel();
            for (ChequeWape cheque : (LinkedList<ChequeWape>) modImprimir.getList()) {
                mod.borraChequeWraper(cheque);
            }
            habilitarControles(false);
        }
        
        if (numSecChequera + ChequeDibujo.CHEQUES_NO_SE_IMPRIMEN > ((Chequera) cboChequeras.getSelectedItem()).getNroFin()) {
            JOptionPane.showMessageDialog(this, new String("La chequera seleccionada no tiene mas numeros para asignar."),
                    "Impresion Cheques", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnPasarChequeActionPerformed

    private void habilitarControles(Boolean b) {
        CboBancos.setEnabled(b);
        cboChequeras.setEnabled(b);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        habilitarControles(true);
        tblImprimir.setModel(new ModeloImprimir());
        cargarCuentaBcoSeleccionado();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private Long getNextNumeroImpresion(Long idChequera, Long proxUsar) {
        Long next = new Long("0");
        if (idChequeraUsada == idChequera) {
            if (this.numProximoUsar > 0) {
                next = numProximoUsar + 1;
            } else {
                next = proxUsar;
            }
            this.numProximoUsar = next;
        } else {
            idChequeraUsada = idChequera;
            next = proxUsar;
            this.numProximoUsar = next;
        }

        return next;
    }   

    private void cargarImpresoras() {

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        cboImpresoras.removeAllItems();
        for (int i = 0; i < printServices.length; i++) {
            cboImpresoras.addItem(new Impresora(printServices[i]));
        }

    }

    private void cargarCheques(Long idbancoPropio) {
        try {
            Modelo modelo = new Modelo();
            if (idbancoPropio != 0) {
                filtro.reset();
                filtro.agregarCampoOperValor("bancoPropio.idBancoPropio", Filtro.IGUAL, idbancoPropio);
                filtro.agregarCampoOperValor("procesado", Filtro.LIKEXS, "N");
                filtro.agregarCampoOperValor("tipo", Filtro.LIKEXS, "P");
                filtro.agregarOrderBy("idCheque");
                chequeServiceImpl = (ChequeServiceImpl) Contexto.getInstance().getBean("chequeServiceTarget");
                List<Cheque> list = (List<Cheque>) chequeServiceImpl.getCheques(filtro);

                for (Cheque cheque : list) {
                    modelo.anhadeChequeWraper(new ChequeWape(cheque));
                }
                jTable1.setModel(modelo);
            } else {
                jTable1.setModel(modelo);
            }

        } catch (ChequeException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarBancos() {

        CboBancos.removeAllItems();
        bancoPropioServiceImpl = (BancoPropioServiceImpl) Contexto.getInstance().getBean("bancoPropioServiceTarget");
        try {
            List<BancoPropio> listBanco = (List<BancoPropio>) bancoPropioServiceImpl.getBancoPropiosFlex(filtro);
            BancoPropio bp = new BancoPropio();
            Banco banco = new Banco();
            banco.setDescripcion("Seleccionar...");
            bp.setBanco(banco);
            bp.setNumeroCuenta("");
            bp.setIdBancoPropio(new Long("0"));
            listBanco.add(0, bp);

            CboBancos.setRenderer(new ListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel label = new JLabel(((BancoPropio) value).getNumeroCuenta() + "   -  " + ((BancoPropio) value).getBanco().getDescripcion());
                    label.setOpaque(true);
                    label.setHorizontalAlignment(JLabel.LEFT);
                    label.setVerticalAlignment(JLabel.CENTER);

                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }

                    return label;
                }
            });
            CboBancos.setModel(new DefaultComboBoxModel(listBanco.toArray()));

        } catch (BancoPropioException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarChequeras(Long idBancoPropio) {
        cboChequeras.removeAllItems();
        if (idBancoPropio != 0) {
            ChequeraServiceImpl chequeraServiceImpl = (ChequeraServiceImpl) Contexto.getInstance().getBean("chequeraServiceTarget");
            filtro.reset();
            filtro.agregarCampoOperValor("bancoPropio.idBancoPropio", Filtro.IGUAL, idBancoPropio);
            filtro.agregarCampoOperValor("habilitado", Filtro.LIKEXS, 'S');
            filtro.agregarfuncion(" AND obj.nroFin >= obj.proximoAUsar ");
            filtro.agregarOrderBy("idChequera");
            try {
                List<Chequera> listChequeras = (List<Chequera>) chequeraServiceImpl.getChequeras(filtro);
                if (listChequeras != null && listChequeras.size() > 0) {
                    cboChequeras.setModel(new DefaultComboBoxModel(listChequeras.toArray()));
                    btnPasarCheque.setEnabled(true);
                } else {
                    btnPasarCheque.setEnabled(false);
                    JOptionPane.showMessageDialog(this, new String("El banco seleccionado no tiene chequeras disponibles."),
                            "Impresion Cheques", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ChequeraException ex) {
                java.util.logging.Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void guardarChequesProcesados(LinkedList<ChequeWape> list) {

        Long proximoUsarChequera = new Long("0");
        Chequera chequera = ModelLocator.getInstance().chequeraModel.getChequera();

        for (ChequeWape cheque : list) {
            if (cheque.getAccion() == "C") {
                try {
                    chequeServiceImpl = (ChequeServiceImpl) Contexto.getInstance().getBean("chequeServiceTarget");
                    chequeServiceImpl.actualizarCheque(cheque.getCheque());
                    proximoUsarChequera = Long.parseLong(cheque.getCheque().getNumero()) > proximoUsarChequera ? Long.parseLong(cheque.getCheque().getNumero()) : proximoUsarChequera;
                } catch (ChequeException ex) {
                    Logger.getLogger(ResultadoImpresion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //La impresion del cheque fue anulada.
                proximoUsarChequera = Long.parseLong(cheque.getCheque().getNumero()) > proximoUsarChequera ? Long.parseLong(cheque.getCheque().getNumero()) : proximoUsarChequera;
            }
        }

        try {
            chequeraServiceImpl = (ChequeraServiceImpl) Contexto.getInstance().getBean("chequeraServiceTarget");
            chequera.setProximoAUsar(proximoUsarChequera + 1);
            chequeraServiceImpl.actualizarChequera(chequera);
        } catch (ChequeraException ex) {
            Logger.getLogger(ResultadoImpresion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void returnFromResultadoImpresion() {
        this.setEnabled(true);
        listChequesRdos = ModelLocator.getInstance().chequeProcesadosModel.getListCheques();
        listChequesImprimir = ((ModeloImprimir) tblImprimir.getModel()).getList();

        guardarChequesProcesados(listChequesRdos);


        LinkedList<ChequeWape> list = new LinkedList<ChequeWape>();

        for (ChequeWape chequeImprimir : listChequesImprimir) {
            for (ChequeWape chequeRdos : listChequesRdos) {
                if (chequeImprimir.getCheque().getIdCheque() == chequeRdos.getCheque().getIdCheque()) {
                    list.add(chequeRdos);
                }
            }
        }
        for (ChequeWape c : list) {
            listChequesImprimir.remove(c);
        }

        ((ModeloImprimir) tblImprimir.getModel()).setList(listChequesImprimir);
        habilitarControles(true);
        cargarChequeras(((BancoPropio) CboBancos.getSelectedItem()).getIdBancoPropio());
        cargarCheques(((BancoPropio) CboBancos.getSelectedItem()).getIdBancoPropio());

    }

    public class ChequeWape {

        private Cheque cheque;
        private String accion;
        private Boolean imprimir;
        private Boolean accionEstado;
        public final char CONFIRMAR = 'C';
        public final char ANULAR = 'A';
        public final char REIMPRIMIR = 'R';

        public ChequeWape(Cheque cheque) {
            this.cheque = cheque;
            accion = "";
            imprimir = false;
            accionEstado = false;
        }

        /**
         * @return the cheque
         */
        public Cheque getCheque() {
            return cheque;
        }

        /**
         * @param cheque the cheque to set
         */
        public void setCheque(Cheque cheque) {
            this.cheque = cheque;
        }

        /**
         * @return the accion
         */
        public String getAccion() {
            return accion;
        }

        /**
         * @param accion the accion to set
         */
        public void setAccion(String accion) {
            this.accion = accion;
        }

        /**
         * @return the imprimir
         */
        public Boolean getImprimir() {
            return imprimir;
        }

        /**
         * @param imprimir the imprimir to set
         */
        public void setImprimir(Boolean imprimir) {
            this.imprimir = imprimir;
        }

        /**
         * @return the accionEstado
         */
        public Boolean getAccionEstado() {
            return accionEstado;
        }

        /**
         * @param accionEstado the accionEstado to set
         */
        public void setAccionEstado(Boolean accionEstado) {
            this.accionEstado = accionEstado;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CboBancos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnPasarCheque;
    private javax.swing.JComboBox cboChequeras;
    private javax.swing.JComboBox cboImpresoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblChequera;
    private javax.swing.JLabel lblError;
    private javax.swing.JTable tblImprimir;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import com.bizitglobal.tarjetafiel.view.PanelChequeBBVA;
import javax.print.attribute.standard.PrintQuality;
/**
 *
 * @author Mario
 */
public class Impresora {

   private PrintService printService;
   private PanelChequeBBVA panelChequeBBVA;

    public Impresora() {
    }

   
    public Impresora(PrintService printService){

        this.printService = printService;
        //PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        panelChequeBBVA = new PanelChequeBBVA();
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }

    public PrintService getPrintService() {
        return printService;
    }

    @Override
    public String toString() {
        return printService.getName();
    }


    public boolean imprimir(Printable printable){
        try {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintService(printService);            

            PageFormat pageFormat = new PageFormat();

            Paper paper =new Paper();
            paper.setSize( 510F,216F);
            paper.setImageableArea(0, 0, 510F, 216F);
            
            pageFormat.setPaper(paper);
            pageFormat.setOrientation(PageFormat.PORTRAIT);
            PrintRequestAttributeSet att = new HashPrintRequestAttributeSet();
            att.add(PrintQuality.DRAFT);
            //att.add(new PrinterResolution(76,203,PrinterResolution.DPI));
            printerJob.setPrintable(printable,pageFormat);
            printerJob.print(att);
            return true;
        } catch (PrinterException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }


}

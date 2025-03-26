/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque;

import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

/**
 *
 * @author Mario
 */
public class ChequeStandartBank extends ChequeDibujo{

    private final int largoLineaBeneficiario=60;
    public ChequeStandartBank(Cheque cheque) {
        super(cheque);
    }


    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if(pageIndex == 0 ){
           graphics.setColor(Color.BLACK);
           graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));


            Graphics2D g2d = (Graphics2D) graphics;

//            Rectangle2D.Double rect = new Rectangle2D.Double ();
//            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//            rect.setRect(pageFormat.getImageableX ()+ 1, pageFormat.getImageableY ()+ 1 ,  pageFormat.getWidth() - 1, pageFormat.getHeight()- 1);
//            g2d.draw (rect);
            
//           Le tengo que restar 7 milimetros de altura a cada linea para compatibilidad con Epson LX 810

            //Dibuja las lineas para identiicar un cheque cruzado.
            dibujarCruzado(g2d,getEsCruzado());

            
            g2d.drawString(getPesosNum(), puntos(12.9), puntos(0.3));
            g2d.drawString(getDiaEmitido(), puntos(4.6), puntos(0.9));
            g2d.drawString(getMesEmitido(),puntos(5.3), puntos(0.9));
            g2d.drawString(getAnioEmitido(),puntos(8.6), puntos(0.9));
            g2d.drawString(getDiaPagar(), puntos(3.3), puntos(1.7));
            g2d.drawString(getMesPagar(),puntos(5.1), puntos(1.7));
            g2d.drawString(getAnioPagar(),puntos(8.6), puntos(1.7));
            g2d.drawString(ChequeDibujo.CortaLineaCheque(getBeneficiario(),largoLineaBeneficiario)[0]  ,puntos(4.5), puntos(2.2));
            g2d.drawString(ChequeDibujo.CortaLineaCheque(getBeneficiario(),largoLineaBeneficiario)[1]  ,puntos(3.3), puntos(2.7));

            //g2d.drawString(getBeneficiario(),puntos(4.5), puntos(2.2));
            g2d.drawString(getPesosLetra().toUpperCase(),puntos(5.9), puntos(3.2));

            return PAGE_EXISTS;
        }

        return NO_SUCH_PAGE;
    }


}

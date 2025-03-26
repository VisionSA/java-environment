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
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

/**
 *
 * @author Mario
 */
public class ChequeBsj extends ChequeDibujo{
    
    private final int largoLineaBeneficiario=60;

    public ChequeBsj(Cheque cheque) {
        super(cheque);
    }


    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {



        if(pageIndex == 0){
           graphics.setColor(Color.BLACK);
           graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));


            Graphics2D g2d = (Graphics2D) graphics;

            //Dibuja las lineas para identiicar un cheque cruzado.
            dibujarCruzado(g2d,getEsCruzado());
            
            g2d.drawString(getPesosNum(), puntos(12.9), puntos(0.3));
            g2d.drawString(getDiaEmitido(), puntos(3.9), puntos(1.0));
            g2d.drawString(getMesEmitido(),puntos(5.1), puntos(1.0));
            g2d.drawString(getAnioEmitido(),puntos(8.1), puntos(1.0));
            g2d.drawString(getDiaPagar(), puntos(3.3), puntos(1.65));
            g2d.drawString(getMesPagar(),puntos(4.8), puntos(1.65));
            g2d.drawString(getAnioPagar(),puntos(8.1), puntos(1.65));
            g2d.drawString(ChequeDibujo.CortaLineaCheque(getBeneficiario(),largoLineaBeneficiario)[0]  ,puntos(4.4), puntos(2.2));
            g2d.drawString(ChequeDibujo.CortaLineaCheque(getBeneficiario(),largoLineaBeneficiario)[1]  ,puntos(3.3), puntos(2.5));
            //g2d.drawString(getBeneficiario(),puntos(4.4), puntos(2.2));
            g2d.drawString(getPesosLetra().toUpperCase(),puntos(5.6), puntos(2.9));

            return PAGE_EXISTS;
        }
        return NO_SUCH_PAGE;
    }
}

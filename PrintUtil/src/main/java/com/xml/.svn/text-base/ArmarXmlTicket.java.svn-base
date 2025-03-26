/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xml;

import com.util.ControladorArchivo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;





/**
 *
 * @author sporta
 */
public class ArmarXmlTicket {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ArmarXmlTicket.class);

    private Document datosTicket;

    private TransformarTicket transformarTicket = new TransformarTicket();

    private ControladorArchivo controladorArchivo;

    public ArmarXmlTicket() {
        try {
            this.inicializarDocumentTicket();
        } catch (Exception ex) {
            logger.error(ex,ex);
        }
    }



    private void inicializarDocumentTicket() throws Exception {
        datosTicket = DocumentFactory.getInstance().createDocument();
        Element root = datosTicket.addElement("ticket");
        Element header = root.addElement("header");

        //hedaer
        header.addElement("fecha");
        header.addElement("hora");
        header.addElement("recibo");
        header.addElement("dni");
        header.addElement("cliente");
        header.addElement("operador");
        header.addElement("caja");
        header.addElement("transaccion");
        header.addElement("cuenta");
/*@I7936*/       header.addElement("nombreco");
/*@F7936*/       header.addElement("comercio");
        

        //transacciones
        root.addElement("transacciones");

        //resumenes
        root.addElement("resumenes");

        //refinanciacion
        root.addElement("refinanciacion");

        //adelanto
        root.addElement("adelanto");

        //cierre
        root.addElement("cierre").addElement("cheques").addAttribute("hayCheques", "false");

        root.addElement("retiros");
    }

    public void addTipoCierre(String tipo){
        datosTicket.getRootElement().element("cierre").addAttribute("tipo", tipo);
    }

    public void addChequeEnCajaCierre(String numero, String tipo, BigDecimal importe){
        this.datosTicket.getRootElement().element("cierre").element("cheques").attribute("hayCheques").setValue("true");
        Element element = this.datosTicket.getRootElement().element("cierre").element("cheques").addElement("cheque");
        element.addElement("numero").setText(numero);
        element.addElement("tipo").setText(tipo);
        element.addElement("importe").setText("$"+importe.setScale(2,BigDecimal.ROUND_HALF_DOWN).toString());
    }

    public Element addCuentaCierre(String nombre,String id){
        Element element = datosTicket.getRootElement().element("cierre").addElement("cuenta");
        element.addElement("medios");
        element.addElement("arqueos");
        element.addElement("ajuste");
        element.addAttribute("id", id);
        element.addAttribute("nombre", nombre);
        element.addAttribute("ajuste", "false");
        return element;
    }

    public void addTotalArqueo(Element cuenta,String total){
        cuenta.element("arqueos").addAttribute("arqueoTotal", total);
    }

    public void addFaltanteSobranteArqueo(Element cuenta,String faltante){
        cuenta.element("arqueos").addAttribute("faltante-sobrante",faltante);
    }

    public void addSaldoFinalCuenta(Element cuenta, String saldoFinal){
        cuenta.addAttribute("saldoFinal", saldoFinal);
    }

    public void addSaldoInicialCuenta(Element cuenta, String saldoInicial){
        cuenta.addAttribute("saldoInicial", saldoInicial);
    }

    public Element addAjusteCuentaCierre(Element cuenta,String id,String descripcion,String importe,String signo ){
        cuenta.attribute("ajuste").setText("true");
        Element element = cuenta.element("ajuste").addElement("medio");
        element.addElement("id").setText(id);
        int lenth = descripcion.length();
        for(int i=26;i>lenth;i--){
            descripcion += " ";
        }
        element.addElement("descripcion").setText(descripcion);
        element.addElement("importe").setText(importe);
        element.addElement("signo").setText(signo);
        return element;
    }

    public Element addMedioCuentaCierre(Element cuenta,String id,String descripcion,String importe,String signo ){
        Element element = cuenta.element("medios").addElement("medio");
        element.addElement("id").setText(id);
        int lenth = descripcion.length();
        for(int i=26;i>lenth;i--){
            descripcion += " ";
        }
        element.addElement("descripcion").setText(descripcion);
        element.addElement("importe").setText(importe);
        element.addElement("signo").setText(signo);
        return element;
    }

    public void addArqueoItemCuentaCierre(Element cuenta,String descripcion, String cantidad){
        Element item = cuenta.element("arqueos").addElement("arqueo");
        item.addElement("descripcion").setText(descripcion);
        item.addElement("cantidad").setText(cantidad);
    }


    public void addTotalTransacciones(String dato){
        datosTicket.getRootElement().element("transacciones").addAttribute("total", dato);
    }

    public void addFechaHeader(Date value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        datosTicket.getRootElement().element("header").element("fecha").setText(simpleDateFormat.format(value));
    }

    public void addFechaAperturaHeader(Date fecha){
        logger.info("Fecha Apertura " + fecha);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        datosTicket.getRootElement().element("header").addElement("fechaApertura").setText(simpleDateFormat.format(fecha));
    }

    public void addHoraAperturaHeader(Date fecha){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        datosTicket.getRootElement().element("header").addElement("horaApertura").setText(simpleDateFormat.format(fecha));
    }

    public void addHoraHeader(Date value){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        datosTicket.getRootElement().element("header").element("hora").setText(simpleDateFormat.format(value));
    }

    /**
     * @id: 	7936
     * Method: addNombreComercioHeader
     * Description: Agrega el nombre de fantacia del comercio al ticket
     * @param value
     */
    public void addNombreComercioHeader(String value){
        datosTicket.getRootElement().element("header").element("nombreco").setText(value);
    }
    
    /**
     * @id: 	7936
     * Method: addCodigoComercioHeader
     * Description: Agrega el codigo de posnet del comercio al ticket
     * @param value
     */
    public void addCodigoComercioHeader(String value){ 
    	String aux = "00000" + value;
    	value = aux.substring(aux.length()-5, aux.length());
        datosTicket.getRootElement().element("header").element("comercio").setText(value);
    }
    
    
    public void addDniHeader(String value){
        datosTicket.getRootElement().element("header").element("dni").setText(value);
    }

    public void addNroTransaccionHeader(String nroTransaccion){
        String aux = "00000000" + nroTransaccion;
        nroTransaccion = aux.substring(aux.length()-8, aux.length());
        datosTicket.getRootElement().element("header").element("transaccion").setText(nroTransaccion);
    }

    public void addReciboHeader(String recibo){
        String aux = "00000000" + recibo;
        recibo = aux.substring(aux.length()-8, aux.length());
        datosTicket.getRootElement().element("header").element("recibo").setText(recibo);
    }

    public void addClienteHeader(String cliente){
        datosTicket.getRootElement().element("header").element("cliente").setText(cliente);
    }

    public void addOperadorHeader(String operador){
        datosTicket.getRootElement().element("header").element("operador").setText(operador);
    }

    public void addCajaHeader(String caja){
        String aux = "00000" + caja;
        caja = aux.substring(aux.length()-5, aux.length());
        datosTicket.getRootElement().element("header").element("caja").setText(caja);
    }

    public void addCuentaHeader(String cuenta){
        String aux = "00000" + cuenta;
        try{
/*@F7936*/         cuenta = aux.substring(aux.length()-6, aux.length());
            datosTicket.getRootElement().element("header").element("cuenta").setText(cuenta);
        }catch(Exception ex){
            datosTicket.getRootElement().element("header").element("cuenta").setText("0000");
        }
    }
 
    public void addNuevoDato(String elementPadre, String nuevoElement, String dato){
        datosTicket.getRootElement().element(elementPadre).addElement(nuevoElement).setText(dato);
    }

    public void addNuevoAtributo(String element, String nombreDato, String dato){
        datosTicket.getRootElement().element(element).addAttribute(nombreDato, dato);
    }

    public Element addNuevoDato(Element elementPadre, String nuevoElement){
        return elementPadre.addElement(nuevoElement);
    }

    public void addNuevoAtributo(Element element, String nombreDato, String dato){
        element.addAttribute(nombreDato, dato);
    }

    public Element addTraransaccion(){
        return datosTicket.getRootElement().element("transacciones").addElement("transaccion");
    }

    public void addMonedaRefinanciacion(String dato){
        datosTicket.getRootElement().element("refinanciacion").addElement("moneda").setText(dato);
    }

    public void addImporteRefinanciacion(String dato){
        datosTicket.getRootElement().element("refinanciacion").addElement("importe").setText(dato);
    }

    public void addImporteCuotaRefinanciacion(String dato){
        datosTicket.getRootElement().element("refinanciacion").addElement("importe-cuota").setText(dato);
    }

    public void addCuotaRefinanciacion(String dato){
        datosTicket.getRootElement().element("refinanciacion").addElement("cuotas").setText(dato);
    }

      public void addMonedaAdelanto(String dato){
        datosTicket.getRootElement().element("adelanto").addElement("moneda").setText(dato);
    }

    public void addImporteAdelanto(String dato){
        datosTicket.getRootElement().element("adelanto").addElement("importe").setText(dato);
    }

    public void addImporteCuotaAdelanto(String dato){
        datosTicket.getRootElement().element("adelanto").addElement("importe-cuota").setText(dato);
    }

    public void addCuotaAdelanto(String dato){
        datosTicket.getRootElement().element("adelanto").addElement("cuotas").setText(dato);
    }

    public void addMedioPagoTransaccion(Element transaccion,String dato){
        transaccion.addElement("medio-pago").setText(dato);
    }

    public void addMonedaTransaccion(Element transaccion,String dato){
        transaccion.addElement("moneda").setText(dato);
    }

    public void addImporteTransaccion(Element transaccion,String dato){
        transaccion.addElement("importe").setText(dato);
    }

    public void addChequeNumeroTransaccion(Element transaccion,String dato){
        transaccion.addElement("cheque-nro").setText(dato);
    }

    public Element addResumen(){
        return datosTicket.getRootElement().element("resumenes").addElement("resumen");
    }

    public void addNroResumen(Element resumen, String dato){
        resumen.addElement("nro-resumen").setText(dato);
    }

    public void addFecVtoResumen(Element resumen, Date dato){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        resumen.addElement("fec-vto").setText(simpleDateFormat.format(dato));
    }

     public void addImporteResumen(Element resumen, String dato){
        resumen.addElement("importe").setText(dato);
    }

    public Document getDatosTicket() {
        return datosTicket;
    }

    public byte[] transformarTicket(String xsl) throws Exception{
        String ticket = transformarTicket.transformarTicket(datosTicket, xsl);
        ControladorArchivo controladorArchivo = new ControladorArchivo();
        ticket = controladorArchivo.centrarPartTicket(ticket);
        return controladorArchivo.finalizarTrabajo(ticket);
    }

    public void setDatosTicket(Document datosTicket) {
        this.datosTicket = datosTicket;
    }



    public static void main(String arg[] ){
        try {

            ArmarXmlTicket armarXmlTicket = new ArmarXmlTicket();
            Document document = new SAXReader().read("ticket/datosTicket.xml");
            armarXmlTicket.setDatosTicket(document);
            String string = new TransformarTicket().transformarTicket(armarXmlTicket.getDatosTicket(),"ticket/ticket.refinanciacion.xsl");
            ControladorArchivo controladorArchivo = new ControladorArchivo();

            System.out.print(controladorArchivo.centrarPartTicket(string));

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}

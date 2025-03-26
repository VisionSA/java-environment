/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import com.print.Impresora;
import com.print.ListaImpresoras;
import com.print.TrabajoImpresion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;



/**
 *
 * @author sporta
 */
public class EnviarTrabajoDeImpresion extends Thread{

    private Socket socket;

    private IResponder responder;

    private TrabajoImpresion trabajoImpresion;   

    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;

    private static Logger logger = Logger.getLogger(EnviarTrabajoDeImpresion.class);
    

    public EnviarTrabajoDeImpresion(IResponder responder, TrabajoImpresion trabajoImpresion) {
        this.responder = responder;        
        this.trabajoImpresion = trabajoImpresion;
                
    }

    @Override
    public void run() {
        enviarTrabajo();
    }

    public ListaImpresoras consultarImpresoras(String ip,int port) throws IOException, ClassNotFoundException {

        this.conectarSocket(ip,port);
        this.objectOutputStream.writeObject(new ListaImpresoras());

        ListaImpresoras listaImpresoras = (ListaImpresoras)this.objectInputStream.readObject();

        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
        return listaImpresoras;

    }

    /**
     * Este metodo envia a imprimir el archivo pdf ingresado por parametro
     * Cuando recibe la respuesta devuelve el trabajo con el mensaje y/o Exception
     * a un objeto IResponder el cual es el encargado de tratar que hacer con la trabajos
     * enviados a imprimir de acuerdo a como hallan finalizado
     * @param file Tiene que ser un archivo pdf
     *
     */
    public void enviarTrabajo() {
        try {
            this.conectarSocket();                            
        } catch(Exception ex){
            trabajoImpresion.setMensaje("Error en el envío del trabajo");
            trabajoImpresion.setException(ex);
            if(responder != null)
            this.responder.responder(trabajoImpresion);            
        }
        
        try {
            this.sendAndReviced();
        } catch(Exception ex){            
            return;
        }

        
    }   
     
    private void sendAndReviced() throws IOException{
               
        objectOutputStream.writeObject(trabajoImpresion);
        
        try {
            trabajoImpresion = (TrabajoImpresion) objectInputStream.readObject();
        } catch (Exception ex) {
            trabajoImpresion.setMensaje("Error en casteo al recivir respuesta de impresión");
            trabajoImpresion.setException(ex);
        } finally {
            if(responder != null)
            this.responder.responder(trabajoImpresion);
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }
    }

    private void conectarSocket() throws IOException{
         try {
           socket = new Socket(trabajoImpresion.getImpresora().getIpServer(), trabajoImpresion.getImpresora().getPortServer());            
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            logger.error(ex,ex);
            throw ex;
        } catch (IOException ex) {
            logger.error(ex,ex);
            throw ex;
        }
    }

    private void conectarSocket(String ip, int port) throws IOException{
         try {
           socket = new Socket(ip, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            logger.error(ex,ex);
            throw ex;
        } catch (IOException ex) {
            logger.error(ex,ex);
            throw ex;
        }
    }

    public TrabajoImpresion getTrabajoImpresion() {
        return trabajoImpresion;
    }

}

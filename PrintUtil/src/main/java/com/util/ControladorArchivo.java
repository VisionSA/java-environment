/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Esta clase se encarga de transformar un archivo o un conjunto de archivos
 * en un array de bytes, que puede ser enviado a imprimir
 * @author sporta
 */
public class ControladorArchivo {

    private static Logger logger = Logger.getLogger(ControladorArchivo.class);

    private File file;

    private ArrayList<File> files;

    /**
     * 
     * @param file path del archivo
     */
    public ControladorArchivo(String file) {
        this.file = new File(file);
        this.files = new ArrayList<File>();
        this.files.add(this.file);
    }

    /**
     *
     * @param files Un array de paths de archivos
     */
    public ControladorArchivo(String [] files) {
        this.files = new ArrayList<File>();
        for(String f : files){
            this.files.add(new File(f));
        }
    }

    public ControladorArchivo() {
    }



    /**
     *
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @return
     */
    public ArrayList<File> getFiles() {
        return files;
    }

    /**
     *
     * @return 
     */
    public String getStringArchivo() throws ControladorArchivoException{

        try{
            byte [] bs = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                bs = new byte[fileInputStream.available()];
                fileInputStream.read(bs);
            } catch (IOException ex) {
                logger.info(ex,ex);
            }

            return new String(bs);
         } catch (Exception ex){
            throw new ControladorArchivoException("Error en metodo armarTicket", ex);
        }
    }

    /**
     *
     * @return
     */
    public byte []  getByteArchivo() throws ControladorArchivoException{
        try {
            byte [] bs = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                bs = new byte[fileInputStream.available()];
                fileInputStream.read(bs);
            } catch (IOException ex) {
                logger.info(ex,ex);
            }

            return bs;
        } catch (Exception ex){
            throw new ControladorArchivoException("Error en metodo armarTicket", ex);
        }
    }

    /**
     *
     * @param file 
     * @return 
     */
    public String getStringArchivo(File file) throws ControladorArchivoException{
        try{
            byte [] bs = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                bs = new byte[fileInputStream.available()];
                fileInputStream.read(bs);
            } catch (IOException ex) {
                logger.info(ex,ex);
            }

            return new String(bs);
        } catch (Exception ex){
            throw new ControladorArchivoException("Error en metodo armarTicket", ex);
        }
    }

    /**
     * Arma el ticket en bytes según los archivos dentro del ArrayList files
     * @return bytes[]
     */
    public String armarTicket() throws ControladorArchivoException{

        try {
            ArrayList<String> ticket = new ArrayList<String>();

            int lengtBytes = 0;

            for(int i=0;i<this.files.size();i++){
               String bsAux = null;
               if(i==files.size()-1){                   
                   bsAux = getStringArchivo(files.get(i));
               } else {
                    bsAux = getStringArchivo(this.files.get(i));
               }

               lengtBytes += bsAux.length();
               ticket.add(bsAux);
            }

            String aux = "";
            for(String part : ticket){
                aux += part;
            }           

            return aux;

        } catch (Exception ex){
            throw new ControladorArchivoException("Error en metodo armarTicket", ex);
        }
    }

    public byte [] finalizarTrabajo(String ticket){
        ticket += "     ";
        byte [] bs;
        bs = ticket.getBytes();
        bs[bs.length-5] = "\n".getBytes()[0];
        bs[bs.length-4] = 0x1D;
        bs[bs.length-3] = "V".getBytes()[0];
        bs[bs.length-2] = "A".getBytes()[0];
        bs[bs.length-1] = 0x14;
        return bs;
    }

    public String centrarPartTicket(String ticket){

        String aux = "";
        String lineas [] = ticket.split("\n");

        for(String linea : lineas){
            if(linea.replaceAll("%centrar%", "").length() < 40){
                aux += centrarLinea(linea);
            } else if(linea.length() > 40){
                aux += separaLineaMayor40(linea);
            } else {
                aux += linea + "\n";
            }
        }
        ticket = aux;
        return aux;
    }

    
    private String separaLineaMayor40(String string){
        String lineaSeparada = "";
        if(string.replaceAll("%centrar%", "").length() > 40){
            lineaSeparada += string.substring(0, 40);
            return lineaSeparada += separaLineaMayor40(string.substring(40, string.length()));
        } else {
            return lineaSeparada += centrarLinea(string);
        }
        
    }

    /**
     * Centra un String en una linea de 40 caracteres
     * Siempre que en la linea exista la variable [%centrar%]
     * @param string
     * @return
     */
    private String centrarLinea(String string){

        if(string.contains("%centrar%")){
            string = string.replaceAll("%centrar%", "");
            int espacios = (40 - string.trim().length()) / 2;

            String aux2 = "";

            for(int i =0;i<espacios;i++){
                aux2 += " ";
            }

            return aux2 + string.trim() + aux2 + "\n";
        }

        return string.trim() + "\n";

    }

}

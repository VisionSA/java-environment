package com.bizitglobal.tarjetafiel.commons.util;

public class Node {

	   private Comparable info;
	   private Node next;
	   
	   /**
	    *  Constructor por defecto. 
	    */
	   public Node ( )
	   {
	   }
	   
	   /**
	    *  Crea un nodo incializando todos los atributos en base a parametros 
	    */
	   public Node (Comparable x, Node p)
	   {
	     info = x;
	     next = p;
	   }
	   
	   /**
	    *  Accede a la direccin del sucesor
	    *  @return la direccin del nodo sucesor
	    */
	   public Node getNext()
	   {
	     return next;
	   }
	   
	   /**
	    * Cambia la direccin del sucesor
	    * @param p direccin del nuevo sucesor
	    */
	   public void setNext(Node p)
	   {
	     next = p;
	   }
	   
	   /**
	    *  Accede al valor del info
	    *  @return el valor del info
	    */
	   public Comparable getInfo()
	   {
	     return info;
	   }
	   
	   /**
	    * Cambia el valor del info
	    * @param p nuevo valor del info
	    */
	   public void setInfo(Comparable p)
	   {
	     info = p;
	   }

	   /**
	    * Convierte el contenido del nodo en String
	    * @return el contenido del nodo convertido en String
	    */
	   public String toString()
	   {
	     return info.toString();   
	   }
	
	
}
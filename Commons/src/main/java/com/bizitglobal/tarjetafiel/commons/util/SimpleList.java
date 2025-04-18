package com.bizitglobal.tarjetafiel.commons.util;


import java.util.NoSuchElementException;

/**
 *  Una lista generica. Suponemos ahora que cada nodo referencia a un Comparable, y no a un Object. La clase controla
 *  homogeneidad, y tambien provee metodos para facilitar su recorrido externo (iterator)
 */
public class SimpleList {


		  private Node frente;
		  private Node actual;  // patron Iterator: direccin del nodo que toca procesar.
	      private int longitud; // cantidad de nodos de la lista. 
	      
	      /** 
	       * Constructor por defecto
	       */
	      public SimpleList ()
	      {
	          frente = null;
	          actual = null;
	          longitud = 0;
	      }
	      
	      /**
	       *  Retorna el numero de elementos de la lista.
	       *  @return el numero de elementos que contiene la lista.
	       */
	      public int size()
	      {
	            return longitud;    
	      }
	      
	      /**
	       *  Inserta un objeto al principio de la lista. La insercin se har slo si el parmetro recibido 
	       *  no es null y si el objeto representado es compatible con el contenido actual de la lista
	       *  @param x el objeto a almacenar en la lista.
	       */
	      public void addFirst(Comparable x)
	      {
	            if ( ! isHomogeneus( x ) ) return;
	            
	            // si lleg aca, est todo ok
	            Node p = new Node(x, frente);
	            frente = p;
	            longitud++;
	      }  
	      
	      /**
	       *  Inserta un objeto en forma ordenada en la lista. La insercin se har slo si el parmetro recibido 
	       *  no es null y si el objeto representado es compatible con el contenido actual de la lista. Se supone 
	       *  que la est ya ordenada (es decir, se supone que todas las inserciones fueron realizadas llamando a
	       *  este mtodo). Este mtodo no viene en la clase LinkedList tomada como modelo para el planteo realizado
	       *  en clase de SimpleList: se incorpora desde la materia TSB por tratarse de un algoritmo clsico e interesante.
	       *  @param x el objeto a almacenar en la lista.
	       */
	      public void addInOrder(Comparable x)
	      {
	            if ( ! isHomogeneus( x ) ) return;
	            
	            // si lleg ac, est todo ok... inserte tranquilo
	            Node nuevo = new Node( x, null );
	            Node p = frente, q = null;
	            while ( p != null && x.compareTo( p.getInfo() ) >= 0 )
	            {
	                q = p;
	                p = p.getNext();
	            }
	            nuevo.setNext( p );
	            if( q != null ) q.setNext( nuevo );
	            else frente = nuevo;
	            longitud++;
	      }  
	      
	      
	      /**
	       *  Inserta un objeto al final de la lista. La insercin se har slo si el parmetro recibido 
	       *  no es null y si el objeto representado es compatible con el contenido actual de la lista
	       *  @param x el objeto a almacenar en la lista.
	       */
	      public void addLast(Comparable x)
	      {
	            if ( ! isHomogeneus( x ) ) return;
	            
	            // si lleg ac, est todo ok... inserte tranquilo
	            Node nuevo = new Node( x, null );
	            Node p = frente, q = null;
	            while ( p != null )
	            {
	                q = p;
	                p = p.getNext();
	            }
	            if( q != null ) q.setNext( nuevo );
	            else frente = nuevo;
	            longitud++;
	      }  
	      
	      /**
	       *  Remueve todos los elementos de la lista.
	       */
	      public void clear( )
	      {
	         frente = null; // alguna duda?
	         actual = null;
	         longitud = 0;
	      }
	      
	      /**
	       *  Determina si en la lista existe un elemento que coincida con x. Usamos compareTo() para
	       *  realizar las comparaciones (aunque podra usarse equals()).
	       *  @return true si x est en la lista - false si x no est o si x es null.
	       *  @param x el objeto a buscar.
	       */
	      public boolean contains (Comparable x)
	      {
	          if ( ! isHomogeneus( x ) ) return false;
	          
	          Node p = frente;
	          while ( p != null && x.compareTo( p.getInfo() ) != 0 )
	          {
	                p = p.getNext();    
	          }
	          return ( p != null );
	      }

	      /**
	       *  Retorna (pero sin removerlo) el objeto ubicado al principio de la lista. 
	       *  @return una referencia al primer elemento de la lista.
	       *  @throws NoSuchElementException si la lista estaba vaca.
	       */
	      public Comparable getFirst()
	      {
	         if (frente == null) throw new NoSuchElementException("Error: la lista est vaca...");
	         
	         return frente.getInfo();
	      }
	      
	      /**
	       *  Retorna (pero sin removerlo) el objeto ubicado al final de la lista. 
	       *  @return una referencia al primer elemento de la lista.
	       *  @throws NoSuchElementException si la lista estaba vaca.
	       */
	      public Comparable getLast()
	      {
	         if (frente == null) throw new NoSuchElementException("Error: la lista est vaca...");
	         
	         Node p = frente, q = null;
	         while( p != null )
	         {
	            q = p;
	            p = p.getNext();
	         }
	         return ( q != null )? q.getInfo() : frente.getInfo();
	      }
	      
	      /**
	       *  Indica si queda algn objeto en el recorrido del iterador. Se incorpora para cumplir nuestra 
	       *  implementacin liviana del patrn Iterator. Corresponde al mtodo hasNext() de la clase
	       *  Iterator del lenguaje Java.
	       *  @return true si queda algn objeto en el recorrido - false si no quedan objetos.
	       */
	      public boolean hasNext()
	      {
	         if ( frente == null ) return false;
	         if ( actual != null && actual.getNext() == null ) return false;
	         return true;
	      }
	      
	      /**
	       * Retorna true si la lista est vaca.
	       * @return true si la lista est vaca - false en caso contrario.
	       */
	      public boolean isEmpty()
	      {
	         return (frente == null);    
	      }
	      
	      /**
	       *  Retorna el siguiente objeto en el recorrido del iterador. Se incorpora para cumplir nuestra 
	       *  implementacin liviana del patrn Iterator. Corresponde al mtodo next() de la clase Iterator 
	       *  del lenguaje Java.
	       *  @return el siguiente objeto en el recorrido.
	       *  @throws NoSuchElementException si la lista est vaca o en la lista no quedan elementos por recorrer.
	       */
	      public Comparable next()
	      {
	          if ( ! hasNext() ) throw new NoSuchElementException("No quedan elementos por recorrer");
	          
	          if ( actual == null ) actual = frente;
	          else actual = actual.getNext();

	          return actual.getInfo();
	      }
	      
	      /**
	       *  Remueve el objeto x de la lista. Retorna true si puede hacerlo, o false en caso contrario.
	       *  @param x el objeto que se desea eliminar de la lista.
	       *  @return true si la lista contena a x y pudo eliminarlo.
	       */
	      public boolean remove (Comparable x)
	      {
	         if ( ! isHomogeneus( x ) )  return false;
	         
	         Node p = frente, q = null;
	         while( p != null && p.getInfo() != x )
	         {
	            q = p;
	            p = p.getNext();
	         }
	         
	         if ( p != null )
	         {
	             if( q != null ) q.setNext( p.getNext() );
	             else frente = p.getNext();
	             longitud--;
	             return true;
	         }
	         
	         return false;
	      }
	      
	      /**
	       *  Retorna (y remueve) el objeto ubicado al final de la lista. 
	       *  @return el ltimo elemento de la lista.
	       *  @throws NoSuchElementException si la lista estaba vaca.
	       */
	      public Comparable removeLast()
	      {
	         if (frente == null) throw new NoSuchElementException("Error: la lista est vaca...");
	         
	         Node p = frente, q = null;
	         while( p.getNext() != null )
	         {
	            q = p;
	            p = p.getNext();
	         }
	         Comparable x = p.getInfo();
	         if( q != null ) q.setNext( p.getNext() );
	         else frente = p.getNext();
	         longitud--;
	         return x;
	      }
	      
	      /**
	       *  Retorna (y remueve) el objeto ubicado al principio de la lista. 
	       *  @return el primer elemento de la lista.
	       *  @throws NoSuchElementException si la lista estaba vaca.
	       */
	      public Comparable removeFirst()
	      {
	         if (frente == null) throw new NoSuchElementException("Error: la lista est vaca...");
	         
	         Comparable x = frente.getInfo();
	         frente = frente.getNext();
	         longitud--;
	         return x;
	      }
	     
	      /**
	       * Busca un objeto x en la lista, y en caso de encontrarlo retorna una referencia al objeto 
	       * que EST EN LA LISTA. Retorna null si x no est en la lista o si x es null o si x no es
	       * compatible con el contenido de la lista
	       */
	      public Comparable search (Comparable x)
	      {
	            if ( ! isHomogeneus( x ) )  return null;
	            
	            Comparable r = null;
	            Node p = frente;
	            while ( p != null && x.compareTo( p.getInfo() ) != 0)
	            {
	                p = p.getNext();   
	            }
	            if ( p != null ) r = p.getInfo();
	            return r;
	      }
	      
	      /**
	       *  Inicializa el mecanismo de recorrido. Hace que la prxima invocacin a next() retorne el primer objeto de
	       *  la lista. Forma parte de nuestra implementacin liviana del patrn Iterator. En la clase LinkedList de Java, 
	       *  nuestro mtodo sera equivalente a invocar al mtodo iterator(), el cual retorna un objeto de la clase 
	       *  Iterator (predefinida de Java). Decidimos cambiar el nombre por razones de claridad.
	       */
	      public void startIterator()
	      {
	            actual = null;    
	      }
	      
	      /**
	       *  Redefine el mtodo toString heredado desde Object.
	       *  @return el contenido de la lista convertido a String.
	       */
	      public String toString()
	      {
	             Node p = frente;
	             String res = "";
	             while( p != null )
	             {
	                res = res + p.toString();
	                if ( p.getNext() != null ) res = res + " - ";
	                p = p.getNext();
	             }
	             res = res + "";
	             return res;
	      }
	      
	      // Este mtodo controla que x sea homogeneo con el contenido de la lista
	      // Retorna true si es homogneo y false en caso contrario
	      private boolean isHomogeneus (Comparable x)
	      {
	            if ( x == null ) return false;
	            if ( frente != null && x.getClass() != frente.getInfo().getClass() ) return false;
	            return true;
	      }

	      public Node getNodoActual(){
	          return actual;
	      }
	}

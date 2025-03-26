
package com.util.collections
{
	import mx.collections.ArrayCollection;
	

    public  class ArrayUtil 
    {
    	
      public static function  limpiarLista(array: ArrayCollection):void{
      	  if(array)
      	     array.removeAll();
      	   else array = new  ArrayCollection();
      	//return array;
      	
      }	
       
     }
}

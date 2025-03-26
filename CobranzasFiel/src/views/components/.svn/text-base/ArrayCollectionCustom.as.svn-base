package views.components
{
	import com.tarjetafiel.caja.vo.Accion;
	import com.tarjetafiel.caja.vo.AccionVersion;
	import com.tarjetafiel.caja.vo.EtapaVersion;
	
	import mx.collections.ArrayCollection;

	public class ArrayCollectionCustom extends ArrayCollection
	{   
		public var eta:EtapaVersion;
		public function ArrayCollectionCustom(source:Array=null)
		{
			super(source);
		}
	
	    override public function addItem(item:Object):void
   		 {
   		 	     var accio:Accion = new Accion();
   		 	     accio.idAccion = item.idAccion;
   		 	     accio.descripcion = item.descripcion;
   		 	     
	   		 	 var acc:AccionVersion = new AccionVersion();
	   		 	 acc.accion = accio;
	   		 	 acc.dias = 1;
	   		 	 addItemAt(acc, length);
	   		 	 eta.accionesVersion.push(acc);
				 acc.etapaVersion = eta;
   		 }

         override public function removeItemAt(index:int):Object {
         	var o:Object = super.removeItemAt(index);
         	eta.accionesVersion[index] = null;
         	return o;
         }
		
		
		
		override public function addItemAt(item:Object, index:int):void
   		 {
		
		        var listIndex:int = index;
		        //if we're sorted addItemAt is meaningless, just add to the end
		        if (localIndex && sort)
		        {
		            listIndex = list.length;
		        }
		        else if (localIndex && filterFunction != null)
		        {
		            // if end of filtered list, put at end of source list
		            if (listIndex == localIndex.length)
		                listIndex = list.length;
		            // if somewhere in filtered list, find it and insert before it
		            // or at beginning
		            else 
		                listIndex = list.getItemIndex(localIndex[index]);
		        }
		         var accio:Accion = new Accion();
   		 	     accio.idAccion = item.idAccion;
   		 	     accio.descripcion = item.descripcion;
		        var acc:AccionVersion = new AccionVersion();
		        acc.accion = accio;
		        acc.dias = 1;
		       // eta.accionesVersion.push(acc);
				acc.etapaVersion = eta;

		       list.addItemAt(acc, listIndex);
   		 }
		
		
	}
}
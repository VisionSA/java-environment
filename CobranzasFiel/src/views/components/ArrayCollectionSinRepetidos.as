package views.components
{
	import com.tarjetafiel.caja.vo.util.Comparable;
	
	import mx.collections.ArrayCollection;
	
	public class ArrayCollectionSinRepetidos
	{
		
		private var listaSinRepetidos:ArrayCollection;
		
		public function ArrayCollectionSinRepetidos()
		{
		}
		
		public function agregarElemento(elemento:Comparable):void {
			if (!listaSinRepetidos) listaSinRepetidos = new ArrayCollection();
			for (var i:int = 0; i<listaSinRepetidos.length; i++) {
				if (elemento.compararObjeto(listaSinRepetidos.getItemAt(i))==0) return;
			}
			listaSinRepetidos.addItem(elemento);
		}
		
		public function getArrayCollection():ArrayCollection {
			return listaSinRepetidos;
		}

	}
}
package manager
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class AccionManager extends ManagerGenerico
	{
		
		public var accionesArray:ArrayCollection;
		
		public function AccionManager()
		{
			
		}
		
		public function resultArrayAcciones(array:Array):void{
			if(!accionesArray){
				accionesArray = new ArrayCollection();
			}
			
			accionesArray.source = array;
		}

	}
}
package manager
{
	import com.tarjetafiel.caja.vo.Cobrador;
	
	import events.CobradoresEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	[Bindable]
	public class CobradoresManager extends ManagerGenerico
	{
		
		public var cobradoresArray:ArrayCollection;
		public var paisesArray:ArrayCollection;
		public var provinciasArray:ArrayCollection;
		public var partidosArray:ArrayCollection;

		
		
		public function CobradoresManager()
		{
			
		}
		
		public function resultListarCobradores(array:Array):void{
			if(!cobradoresArray){
				cobradoresArray = new ArrayCollection();
			}
			
			cobradoresArray.source = array;
			var cobra:Cobrador = new Cobrador();
			cobra.nombre = "No Asignado";
			cobra.idCobrador = -1;
			cobradoresArray.addItemAt(cobra,0);
			dispatcher.dispatchEvent(new CobradoresEvent(CobradoresEvent.TOMAR_COBRADORES));
		}
		
		public function resultListarPaises(array:Array):void{
			if(!paisesArray){
				paisesArray = new ArrayCollection();
			}
			paisesArray.source = array;
			dispatcher.dispatchEvent(new CobradoresEvent(CobradoresEvent.TOMAR_SELECCION_PAIS));
		}
		
		public function resultListarProvincias(array:Array):void{
			if(!provinciasArray){
				provinciasArray = new ArrayCollection();
			}
			
			provinciasArray.source = array;
		}
		
		public function resultListarPartidosXProvincia(array:Array):void {
			if(!partidosArray){
				partidosArray = new ArrayCollection();
			}
			partidosArray.source = array;
			dispatcher.dispatchEvent(new CobradoresEvent(CobradoresEvent.TOMAR_SELECCION_PROVINCIA));
		}
		
		public function resultModificacionPartidos():void {
			Alert.show("La asignación de partidos se realizo correctamente");
		}

	}
}
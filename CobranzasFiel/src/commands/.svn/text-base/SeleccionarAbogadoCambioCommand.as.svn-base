package commands
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Abogado;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class SeleccionarAbogadoCambioCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			var abogado : Abogado = event.data.abogCombo as Abogado;
			var abogadoSeleccionado : Abogado = event.data.abogSelected as Abogado;
			
			
			if (abogado.idColaborador != -1 && abogadoSeleccionado == null){
				modelo.esAbogadoValido = true;
			}else if (abogado.idColaborador != -1 && abogadoSeleccionado != null && abogado.idColaborador != abogadoSeleccionado.idColaborador){
				modelo.esAbogadoValido = true;
			}else {
				modelo.esAbogadoValido = false;
			}
			
			modelo.abogadoSeleccionadoACambiar = abogado;
	
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}
package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.AccionVersion;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.IResponder;

	public class EliminarAccionVersionCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
				var lista : ArrayCollection = event.data.lista as ArrayCollection;
				var ele : AccionVersion = event.data.ele as AccionVersion;
				lista.removeItemAt(lista.getItemIndex(ele));
				lista.source.sort(modelo.ordenar);
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}
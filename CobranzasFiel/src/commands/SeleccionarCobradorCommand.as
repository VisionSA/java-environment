package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class SeleccionarCobradorCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			/*
			 * OJO con modificar este command, ya que es compartido de 2 o mas interfaces 
			 */
			
			var cobrador : Cobrador = event.data.cobCombo as Cobrador;
			var cobradorSeleccionado : Cobrador = event.data.cobSelected as Cobrador;
			
			
			if (cobrador.idCobrador != -1 && cobradorSeleccionado == null){
				modelo.esCobradorValido = true;
			}else if (cobrador.idCobrador != -1 && cobradorSeleccionado != null && cobrador.idCobrador != cobradorSeleccionado.idCobrador){
				modelo.esCobradorValido = true;
			}else {
				modelo.esCobradorValido = false;
			}
			
			modelo.cobradorSeleccionadoACambiar = cobrador;
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}
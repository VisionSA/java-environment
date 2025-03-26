package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class BuscarAbogadosParaCambioCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("colaboradorService");
			var pendingCall:AsyncToken = ro.listarColaboradoresAbogados();
			pendingCall.addResponder(this);				
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;			
			
			var abogadoNulo : Abogado = new Abogado();
			abogadoNulo.idColaborador = -1;
			abogadoNulo.apellido = "NINGUNO";
			
			var lista : Array = new Array();
			lista.push(abogadoNulo);
						
			if (resultado != null && resultado.length > 0){
				modelo.listaAbogadosCambio = new ArrayCollection(lista.concat(resultado));
			}			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}
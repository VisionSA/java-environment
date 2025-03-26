package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;

	public class BuscarCobradoresParaCambioCommand implements ICommand, IResponder {
		
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();		
		
		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cobradoresService");
			var pendingCall:AsyncToken = ro.getCobrador(new Filtro());
			pendingCall.addResponder(this);	
			
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;			
			
			var cobradorNulo : Cobrador = new Cobrador();
			cobradorNulo.idCobrador = -1;
			cobradorNulo.apellido = "NINGUNO";
			
			var lista : Array = new Array();
			lista.push(cobradorNulo);
						
			if (resultado != null && resultado.length > 0){
				modelo.listaCobradoresCambio = new ArrayCollection(lista.concat(resultado));
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
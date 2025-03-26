package commands
{
	import business.MovimientoConciliable;
	
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class CalcularSaldoContabilidadCommand implements ICommand, IResponder {
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var obj : Object = event.data;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("movimientoConciliableService");
			var pendingCall:AsyncToken = ro.saldoMovContabilidadNoConciliadosHastaFecha(obj.idPlanCuentas, obj.fechaHasta);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			var resultado : Number = ResultEvent(data).result as Number;
			modelo.movimientosNoConciliadosContabilidadAFechaCorteString = resultado < 0 ? modelo.formatearNumero(Math.abs(resultado)) + " CR": modelo.formatearNumero(resultado)+" DB";
			modelo.movimientosNoConciliadosContabilidadAFechaCorte = resultado;
			 			
			ControlBlock.getInstance().remove();	
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
	}
}
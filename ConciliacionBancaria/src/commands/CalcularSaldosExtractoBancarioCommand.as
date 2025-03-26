package commands{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.ExtractoBancarioEvent;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class CalcularSaldosExtractoBancarioCommand implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		private var evento:ExtractoBancarioEvent; 
		public function execute(event:CairngormEvent):void{
			ControlBlock.getInstance().add();
			evento = event as ExtractoBancarioEvent;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("detalleExtractoService");
			var pendingCall:AsyncToken = null;
			var ext:Object = event.data;
			
			if(evento.type == ExtractoBancarioEvent.CALCULAR_SALDO_BCO_FECHACORTE_EVENT)
			{
				pendingCall = ro.saldoBancoFechaCorte(ext.idBancoPropio, ext.fechaCorte); 	
			}
			else if(evento.type == ExtractoBancarioEvent.CALCULAR_SALDO_MOV_NO_CONCILIADOS_BANCO_EVENT)
			{
				pendingCall = ro.saldoMovimientosNoConciliadosHastaFecha(ext.idBancoPropio, ext.fechaCorte);
			}
			
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void{
			var resultado : Number = ResultEvent(data).result as Number;
			
			if(evento.type == ExtractoBancarioEvent.CALCULAR_SALDO_BCO_FECHACORTE_EVENT)
			{
				modelo.saldoBancoAFechaCorteString = resultado<0 ? modelo.formatearNumero(Math.abs(resultado))+ " CR": modelo.formatearNumero(resultado)+" DB";
				modelo.saldoBancoAFechaCorte = resultado;	 	
			}
			else if(evento.type == ExtractoBancarioEvent.CALCULAR_SALDO_MOV_NO_CONCILIADOS_BANCO_EVENT)
			{
				modelo.movimientosNoConciliadosBancoAFechaCorteString = resultado<0 ? modelo.formatearNumero(Math.abs(resultado))+ " CR": modelo.formatearNumero(resultado)+" DB";
				modelo.movimientosNoConciliadosBancoAFechaCorte = resultado;
				
				//Saldo contable calculado a fecha de corte. 
				var saldoCalculado:Number =  modelo.saldoBancoAFechaCorte + modelo.movimientosNoConciliadosBancoAFechaCorte + modelo.movimientosNoConciliadosContabilidadAFechaCorte;
				modelo.saldoContableCalculadoAFechaCorteString = saldoCalculado<0 ? modelo.formatearNumero(Math.abs(saldoCalculado))+ " CR": modelo.formatearNumero(saldoCalculado)+ " DB";
				modelo.saldoContableCalculadoAFechaCorte = saldoCalculado;	
						
				//Diferencia Mov Conciliados
				modelo.diferenciaMovimientosConciliadosString = modelo.formatearNumero(Math.abs(saldoCalculado) - Math.abs(modelo.saldoContableRealMayorAFechaCorte));
				modelo.diferenciaMovimientosConciliados = Math.abs(saldoCalculado) - Math.abs(modelo.saldoContableRealMayorAFechaCorte);
			}
			
			ControlBlock.getInstance().remove();			
		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
	}
}
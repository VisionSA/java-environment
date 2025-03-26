package com.fiel.caja.judicial.command.CajaJudicialEvent {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.event.CajaJudicialEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.proveedorconexion.delegate.OperadorDelegate;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class BuscarOperadorCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			new OperadorDelegate(this).buscarOperadorLogueado(event.data);			
		}
		
		public function result(data:Object):void {
			modelo.operador = ResultEvent(data).result as Operador;
			new CajaJudicialEvent(CajaJudicialEvent.BUSCAR_CAJA_APERTURA_EVENT,modelo.operador).dispatch();
		}
		
		public function fault(info:Object):void {
			Alert.show(FaultEvent(info).fault.faultString);
		}
	}
}
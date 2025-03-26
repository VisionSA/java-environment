package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.SumadorMediosPago;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	public class LimpiarGrillaCommand implements ICommand, IResponder
	{
	
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent):void
		{
			modelo.listaClientesJudiciales = new ArrayCollection();
			modelo.listaMovimientosMP = new ArrayCollection();
			modelo.sumador = new SumadorMediosPago();
			modelo.sumaTotalClientes = 0;			
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
	}
}
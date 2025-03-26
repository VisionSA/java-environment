package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.fiel.caja.judicial.vo.ClienteJudicialEncontradoPago;
	import com.tarjetafiel.caja.vo.ClienteMontoDTO;
	import com.tarjetafiel.caja.vo.ClienteMovimientosMP;
	import com.tarjetafiel.caja.vo.Movimiento;
	import com.tarjetafiel.caja.vo.SumadorMediosPago;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	public class AgregarClienteJudicialGrillaCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
/*@I3820*/			
			var clienteJudicial:ClienteJudicialEncontradoPago = ClienteJudicialEncontradoPago(event.data);
			var cm : ClienteMontoDTO = new ClienteMontoDTO();
			cm.cliente = clienteJudicial.cliente;
			cm.monto = clienteJudicial.monto;
			cm.montoValido = true;
			modelo.listaClientesJudiciales.addItem(cm);
			modelo.sumaTotalClientes += cm.monto;
/*@F3820*/			modelo.sumaTotalClientes = Math.round(Number(modelo.sumaTotalClientes)*100)/100;
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
	}
}
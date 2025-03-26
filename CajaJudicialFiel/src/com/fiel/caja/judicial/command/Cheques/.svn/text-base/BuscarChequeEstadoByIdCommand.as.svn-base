package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ChequeEstado;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	public class BuscarChequeEstadoByIdCommand implements ICommand, IResponder
	{
		
		private var resultado : ChequeEstado;
		
		public function execute(event:CairngormEvent):void
		{
			resultado = event.data.chequeEstado as ChequeEstado;
			new ChequeDelegate(this).buscarChequeEstado(event.data.idEstado)
		}
		
		public function result(data:Object):void
		{
			var r : ChequeEstado = ResultEvent(data).result  as ChequeEstado;
			
			resultado.descripcion = r.descripcion;
			resultado.idChequeEstado = r.idChequeEstado;
			resultado.orden = r.orden;
			resultado.tipo = r.tipo;			
		}
		
		public function fault(info:Object):void
		{
		}
	}
}
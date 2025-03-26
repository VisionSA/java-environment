package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.delegate.ColaboradorDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarListaAbogadosCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			new ColaboradorDelegate(this).buscarListaAbogados();
		}
		
		public function result(data:Object):void
		{
			modelo.listaAbogados = new ArrayCollection(ResultEvent(data).result as Array);
/*@I3820*/			ManejadorPantallas.cerrarProgressBar();
		}
		
		public function fault(info:Object):void
		{
/*@I3820*/			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error al listar abogados");
/*@F3820*/			ManejadorPantallas.cerrarProgressBar();
		}
	}
}
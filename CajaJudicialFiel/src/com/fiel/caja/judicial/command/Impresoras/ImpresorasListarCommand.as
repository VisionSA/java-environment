package com.fiel.caja.judicial.command.Impresoras
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.delegate.ImpresorasDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class ImpresorasListarCommand implements IResponder, ICommand
	{
		
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new ImpresorasDelegate(this).listarImpresoras();
		}
		
		public function result(data:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			modelo.arrImpresoras = new ArrayCollection(data.result as Array);
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error Listar Impresoras");
		}
		
	}
}
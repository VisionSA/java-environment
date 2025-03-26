package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Lugar;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;

	public class ChequesEnCajaCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar()
			var param:Lugar = event.data as Lugar;
			new ChequeDelegate(this).listarChequesEnCaja(param);
		}
		
		public function result(data:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			modelo.arrChequesEnCaja = new ArrayCollection(data.result as Array);
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error al buscar cheques en caja");
		}
		
	}
}
package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.event.CajaJudicialEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class VerificarAperturaCajaCommand implements ICommand, IResponder
	{
				
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new CajaAperturaDelegate(this).buscarAperturaCaja(event.data);
		}
		
		public function result(data:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			new CajaJudicialEvent(CajaJudicialEvent.REALIZAR_PAGO_JUDICIAL_EVENT,null).dispatch();
		}
		
		public function fault(info:Object):void
		{
			if (FaultEvent(info).fault.faultCode == "Server.Processing")
			{
				modelo.estadoActualApp = ConstantesEstados.ESTADO_CAJA_CERRADA;
			}
			else
			{
				modelo.estadoActualApp = ConstantesEstados.ESTADO_NO_AUTORIZADO;
				ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error cargando caja apertura");
			}
			ManejadorPantallas.cerrarProgressBar();
		}
	}
}
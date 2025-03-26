package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.event.CajaJudicialEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarCajaAperturaCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ManejadorPantallas.mostrarProgressBar();
			new CajaAperturaDelegate(this).buscarAperturaCaja(event.data);		
		}
		
		public function result(data:Object):void {
			modelo.cajaApertura = ResultEvent(data).result as CajaApertura;
			new CajaJudicialEvent(CajaJudicialEvent.BUSCAR_MEDIOS_PAGO_CAJA_APERTURA_EVENT,modelo.cajaApertura).dispatch();
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
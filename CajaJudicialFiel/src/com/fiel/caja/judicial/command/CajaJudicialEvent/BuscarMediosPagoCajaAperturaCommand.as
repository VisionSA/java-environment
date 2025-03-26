package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.business.ConstantesFormaPago;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	import com.tarjetafiel.proveedorconexion.util.FuncionesUtiles;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarMediosPagoCajaAperturaCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{	
			new CajaAperturaDelegate(this).buscarMediosPagoCaja(event.data);
		}
		
		public function result(data:Object):void
		{	
			var lista : Array = ResultEvent(data).result as Array;
			modelo.listaMediosPagoCaja = new ArrayCollection(lista.filter(esMedioPosible));
			modelo.estadoActualApp = ConstantesEstados.ESTADO_AUTORIZADO;
			
			modelo.mediosPosibles = FuncionesUtiles.obtenerMediosPagosPosibles(modelo.listaMediosPagoCaja);
			
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeError(FaultEvent(info).fault.faultString);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		private function esMedioPosible(element:Object, index:int, array:Array):Boolean {
			return (element.formaPago.idFormaPago != ConstantesFormaPago.REPACTACION)&&
				(element.formaPago.idFormaPago != ConstantesFormaPago.REFINANCIACION);
		}
		
		
	}
}
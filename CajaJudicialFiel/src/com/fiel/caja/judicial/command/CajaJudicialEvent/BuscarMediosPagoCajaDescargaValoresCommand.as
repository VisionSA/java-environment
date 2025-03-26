package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.proveedorconexion.business.ConstantesFormaPago;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	import com.tarjetafiel.proveedorconexion.vo.SetDataVo;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;

	public class BuscarMediosPagoCajaDescargaValoresCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();

		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			var caja:CajaApertura = event.data as CajaApertura;
			new CajaAperturaDelegate(this).buscarMediosPagoCaja(caja);
		}
		
		public function result(data:Object):void
		{			
			var lista : Array = ResultEvent(data).result as Array;
			modelo.arrMediosPagoDescargaValores = new ArrayCollection(lista.filter(esMedioPosible));
			new SetDataVo(modelo,"estadoActualVDescValores",ConstantesEstados.ESTADO_VDV_ACTUALIZAR).guardarValor();
			ManejadorPantallas.cerrarProgressBar();
			
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error al buscar forma pago de Caja");
		}
		
		private function esMedioPosible(element:Object, index:int, array:Array):Boolean {
			return (element.formaPago.idFormaPago != ConstantesFormaPago.REPACTACION)&&
				(element.formaPago.idFormaPago != ConstantesFormaPago.REFINANCIACION);
		}
	}
}
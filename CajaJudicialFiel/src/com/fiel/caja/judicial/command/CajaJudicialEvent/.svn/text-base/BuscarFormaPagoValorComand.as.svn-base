package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;

	public class BuscarFormaPagoValorComand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function BuscarFormaPagoValorComand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new CajaAperturaDelegate(this).buscarFormaPagoValores();
		}
		
		public function result(data:Object):void
		{
			modelo.formaPagoValoresList = new ArrayCollection;
			modelo.formaPagoValoresList = new ArrayCollection(data.result as Array);
			ordenarFormaPagoValores();
			ManejadorPantallas.cerrarProgressBar();
		}
		
		private function ordenarFormaPagoValores():void{
			var sort:Sort = new Sort();						
			sort.fields = [new SortField("multiplo"), new SortField("formaPago")];
			modelo.formaPagoValoresList.sort = sort;
			modelo.formaPagoValoresList.refresh(); 			 
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error al buscar Forma de pago Valores");
			ManejadorPantallas.cerrarProgressBar();
		}
		
	}
}
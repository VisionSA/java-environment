package com.fiel.caja.judicial.command.descargaValores
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.event.ChequeEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;

	public class DescargaValoresCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();

		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			if(modelo.cajaApertura.caja.impresora == null){
				ManejadorMensajes.mostrarMensajeError("La caja no tiene una impresora prdeterminada\nNo podra operar sin impresora");
				ManejadorPantallas.cerrarProgressBar();
				return;
			}
			new CajaAperturaDelegate(this).registrarDescargaValores(modelo.arrCajaMpDescarga, modelo.cajaApertura, 
							modelo.cajaApertura.operador, modelo.cajaApertura.caja.impresora);
		}
		
		public function result(data:Object):void
		{
			modelo.arrChequesDescargados.removeAll();
			new ChequeEvent(ChequeEvent.LIST_CHEQUES_EN_CAJA, modelo.cajaApertura.caja.lugar).dispatch();
			modelo.ultimaRespuestaImpresion = data.result as RespuestaImpresion;
			modelo.estadoActualVMenuCajas = ConstantesEstados.ESTADO_VMC_DESCARGA_VALORES_COMPLETE;			
			ManejadorPantallas.cerrarProgressBar();		
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}
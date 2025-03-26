package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.event.ChequeEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.proveedorconexion.business.ConstantesChequesEstados;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarChequeTerceroCommand implements ICommand, IResponder
	{
	
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			modelo.estadoChequeEstado = ConstantesEstados.ESTADO_CMP_CHEQUE_NUEVO;
			modelo.estadoChequeConciliado = ConstantesEstados.ESTADO_CMP_CHEQUE_NUEVO;
			
			ManejadorPantallas.mostrarProgressBar();
			new ChequeDelegate(this).buscarCheque(event.data as Cheque);
		}
		
		public function result(data:Object):void
		{
			var cheque : Cheque = ResultEvent(data).result as Cheque;
			
			if(cheque!=null){
				modelo.estadoActualVAgCheque = ConstantesEstados.ESTADO_VACH_TERCERO_NO_MODIFICABLE;
				modelo.cheque = cheque;
				modelo.validarConciliado(cheque);
				var obj : Object = new Object();
				obj.cheque = cheque;
				obj.esTercero = true;
				new ChequeEvent(ChequeEvent.BUSCAR_ESTADO_CHEQUE_EVENT,obj).dispatch();
			}else {
				
				modelo.cheque = new Cheque();
				new ChequeEvent(ChequeEvent.BUSCAR_CHEQUE_ESTADO_POR_ID_CHEQUE_ESTADO_EVENT,ConstantesChequesEstados.CARTERA).dispatch();
				modelo.cheque.tipo = "T";
				modelo.cheque.conciliado = "N";				
				modelo.estadoActualVAgCheque = ConstantesEstados.ESTADO_VACH_TERCERO_MODIFICABLE;
				modelo.esChequeConciliado = false;
				modelo.esChequeEstadoValido = true;
				ManejadorPantallas.cerrarProgressBar();
			}
			
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeError(FaultEvent(info).fault.faultString);
			ManejadorPantallas.cerrarProgressBar();
		}
	}
}
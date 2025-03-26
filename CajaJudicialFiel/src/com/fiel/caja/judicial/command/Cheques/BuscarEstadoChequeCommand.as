package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.ChequeEstado;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarEstadoChequeCommand implements ICommand, IResponder
	{
	
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		private var esTercero : Boolean;
		
		public function execute(event:CairngormEvent):void
		{	
			this.esTercero = event.data.esTercero;
			new ChequeDelegate(this).buscarEstadoCheque(event.data.cheque);
		}
		
		public function result(data:Object):void
		{
			modelo.cheque.chequeEstado = ResultEvent(data).result  as ChequeEstado;
			this.validarEstadoCheque();
			ManejadorPantallas.cerrarProgressBar()
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error Estado Cheque");
			ManejadorPantallas.cerrarProgressBar();
		}
		
		private function validarEstadoCheque():void{
			if (this.esTercero){
				if(modelo.cheque.chequeEstado.idChequeEstado == 6 ||
					modelo.cheque.chequeEstado.idChequeEstado == 9 ||
					modelo.cheque.chequeEstado.idChequeEstado == 10 ||
					modelo.cheque.chequeEstado.idChequeEstado == 11){							
					modelo.estadoChequeEstado = ConstantesEstados.ESTADO_CMP_CHEQUE_ESTADO_INVALIDO;
					modelo.esChequeEstadoValido = false;
				}else {// Agrego o modifico el medio de pago
					modelo.estadoChequeEstado = ConstantesEstados.ESTADO_CMP_CHEQUE_ESTADO_VALIDO;
					modelo.esChequeEstadoValido = true;
				}		
			}else{//es propio
				if(modelo.cheque.chequeEstado.idChequeEstado == 4){
					modelo.estadoChequeEstado = ConstantesEstados.ESTADO_CMP_CHEQUE_ESTADO_INVALIDO;
					modelo.esChequeEstadoValido = false;
				}else{ /* Agrego o modifico el medio de pago */
					modelo.estadoChequeEstado = ConstantesEstados.ESTADO_CMP_CHEQUE_ESTADO_VALIDO;
					modelo.esChequeEstadoValido = true;
				}	
			}
		}
	}
}
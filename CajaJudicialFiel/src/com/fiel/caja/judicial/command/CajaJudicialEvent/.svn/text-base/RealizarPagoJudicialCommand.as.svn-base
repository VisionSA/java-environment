package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.event.CajaJudicialEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.ClienteMontoDTO;
	import com.tarjetafiel.caja.vo.ClienteMovimientoDTO;
	import com.tarjetafiel.caja.vo.ClienteMovimientosMP;
	import com.tarjetafiel.caja.vo.Movimiento;
	import com.tarjetafiel.caja.vo.MovimientoClientesDTO;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.tarjetafiel.proveedorconexion.delegate.PagoDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class RealizarPagoJudicialCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new PagoDelegate(this).registrarPagoJudicial(obtenerMovimientoClientes(),modelo.sumador,modelo.abogadoSeleccionado);
		}
		
		public function result(data:Object):void
		{
			
			var respuesta : RespuestaImpresion =  ResultEvent(data).result as RespuestaImpresion;
			if (respuesta.falloImpresion){
				ManejadorMensajes.mostrarMensajeNotificacion(respuesta.mensaje);
			}else {
				ManejadorMensajes.mostrarMensajeInformacion(respuesta.mensaje);			
			}
			ManejadorPantallas.cerrarProgressBar();
			new CajaJudicialEvent(CajaJudicialEvent.LIMPIAR_GRILLA_EVENT,null).dispatch();
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error Efectuar Pago");			
		}
		
		private function obtenerMovimientoClientes():MovimientoClientesDTO{
			var resultado : MovimientoClientesDTO = new MovimientoClientesDTO();
			resultado.clientes = modelo.listaClientesJudiciales.source;
			resultado.movimiento = new Movimiento();
			resultado.movimiento.setDatosPrincipales(modelo.cajaApertura,modelo.operador,modelo.listaMovimientosMP.source);
			return resultado;
		}
		
		
//		private function obtenerClienteMovs():ArrayCollection{
//			var resultado : ArrayCollection = new ArrayCollection();
//			
//			for each (var cliMonto:ClienteMontoDTO in modelo.movimientoClientes.clientes){
//				var cli : ClienteMovimientoDTO = new ClienteMovimientoDTO();
//				
//				//Cliente
//				cli.cliente = cliMov.cliente;				
//				//Movimiento
//				cli.movimiento = new Movimiento();
//				cli.movimiento.setDatosPrincipales(modelo.cajaApertura,modelo.operador,cliMov.listaMovimientosMP.source);
//				cli.movimiento.importe = cliMov.sumador.sumaTotal;
//				
//				resultado.addItem(cli);
//			}
//			
//			return resultado;
//		}
		
		
		
	}
}
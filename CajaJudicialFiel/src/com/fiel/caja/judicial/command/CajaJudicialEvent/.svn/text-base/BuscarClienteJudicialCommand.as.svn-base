package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.proveedorconexion.delegate.ClienteTransaccionDelegate;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarClienteJudicialCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			new ClienteTransaccionDelegate(this).buscarClienteJudicial(event.data.cliente as ClienteTransaccion,modelo.abogadoSeleccionado);
		}
		
		public function result(data:Object):void
		{
			modelo.clienteJudicialEncontrado = ResultEvent(data).result as ClienteTransaccion;
			modelo.estadoActualVBuscCli = ConstantesEstados.ESTADO_VBC_ENCONTRADO;
/*@I3820*/			ManejadorPantallas.cerrarProgressBar();
		}
		
		public function fault(info:Object):void
		{
			modelo.clienteJudicialEncontrado = null;
			modelo.estadoActualVBuscCli = ConstantesEstados.ESTADO_VBC_NO_ENCONTRADO;
/*@I3820*/			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error al buscar cliente judicial");
/*@F3820*/			ManejadorPantallas.cerrarProgressBar();
		}
	}
}
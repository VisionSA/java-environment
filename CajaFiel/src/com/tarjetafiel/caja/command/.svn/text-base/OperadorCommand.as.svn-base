package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.operador.OperadorDelegate;
	import com.tarjetafiel.caja.event.CajaEvent;
	import com.tarjetafiel.caja.event.OperadorEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.view.AccesoDenegado;
	import com.tarjetafiel.caja.vo.Operador;
	import com.util.block.ControlBlock;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class OperadorCommand implements ICommand, IResponder
	{
		public function OperadorCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new OperadorDelegate(this).getOperadorLogueado(OperadorEvent(event).codOperador);
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().operadorModel.operador = ResultEvent(data).result as Operador;											
			ControlBlock.getInstance().remove();
			new CajaEvent(CajaEvent.BUSCAR_CAJA_APERTURA).dispatch();
								
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();			
			var acc:AccesoDenegado = new AccesoDenegado();
			PopUpManager.addPopUp(acc, Application.application.contGlobal,true);
			PopUpManager.centerPopUp(acc);
			Application.application.visible = false;
			ModelLocator.getInstance().accesoDenegadoModel.motivo = FaultEvent(info).fault.faultString;			
		}
		
	}
}
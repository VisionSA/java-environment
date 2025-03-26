package com.bizit.consulta.web.command.util
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	import com.bizit.consulta.web.vo.SetDataVo;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	public class SetModelDataCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var dataVo:SetDataVo = event.data as SetDataVo;
			try
			{
				modelo[dataVo.refModelName] = dataVo.nuevoValor;
				if (modelo[dataVo.refModelName] is ArrayCollection)
				{
					ArrayCollection(modelo[dataVo.refModelName]).refresh();
				}
			}
			catch (e:Error)
			{
				ManejadorMensajes.mostrarMensajeError("Error de aplicación");
			}
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
	}
}
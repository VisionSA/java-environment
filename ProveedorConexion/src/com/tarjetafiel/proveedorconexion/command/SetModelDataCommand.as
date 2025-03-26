package com.tarjetafiel.proveedorconexion.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.proveedorconexion.vo.SetDataVo;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	public class SetModelDataCommand implements ICommand, IResponder
	{		
		public function execute(event:CairngormEvent):void
		{
			var dataVo:SetDataVo = event.data as SetDataVo;
			try
			{
				dataVo.modelo[dataVo.refModelName] = dataVo.nuevoValor;
				if (dataVo.modelo[dataVo.refModelName] is ArrayCollection)
				{
					ArrayCollection(dataVo.modelo[dataVo.refModelName]).refresh();
				}
			}
			catch (e:Error)
			{
				trace("Error de aplicación");
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
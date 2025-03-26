package com.tarjetafiel.proveedorconexion.vo
{
	import com.adobe.cairngorm.model.ModelLocator;
	import com.tarjetafiel.proveedorconexion.event.SetModelDataEvent;

	public class SetDataVo
	{
		public var modelo:ModelLocator;
		public var refModelName:String;
		public var nuevoValor:Object;
		
		public function SetDataVo(modelo:ModelLocator, refModelNameIni:String = null, nuevoValorIni:Object = null)
		{
			super();
			this.modelo = modelo;
			this.refModelName = refModelNameIni;
			this.nuevoValor = nuevoValorIni;
		}
		
		public function guardarValor():void
		{
			if (modelo == null)
			{
				trace("No se puede guardar un valor en un modelo nulo");
				return;
			}
			if (refModelName == null)
			{
				trace("No se puede guardar un valor nulo");
				return;
			}
			new SetModelDataEvent(SetModelDataEvent.SET_DATA, this).dispatch();
		}
	}
}
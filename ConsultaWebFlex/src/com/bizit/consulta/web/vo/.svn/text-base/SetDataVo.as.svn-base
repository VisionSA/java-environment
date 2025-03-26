package com.bizit.consulta.web.vo
{
	import com.bizit.consulta.web.event.SetModelDataEvent;
	import com.bizit.consulta.web.utils.ManejadorMensajes;

	public class SetDataVo
	{
		public var refModelName:String;
		public var nuevoValor:Object;
		
		public function SetDataVo(refModelNameIni:String = null, nuevoValorIni:Object = null)
		{
			super();
			this.refModelName = refModelNameIni;
			this.nuevoValor = nuevoValorIni;
		}
		
		public function guardarValor():void
		{
			if (refModelName == null)
			{
				ManejadorMensajes.mostrarMensajeError("No se puede guardar un valor nulo");
				return;
			}
			new SetModelDataEvent(SetModelDataEvent.SET_DATA, this).dispatch();
		}
	}
}
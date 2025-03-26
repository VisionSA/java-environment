package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion")]
	public class EtapaVersion
	{
		
		public var idEtapaVersion:Number;
        public var descripcion:String;
        public var nombreEtapa:String;
        public var dias:int;
        public var etapa:Etapa;
        public var planVersion:PlanVersion;
        public var accionesVersion:Array;
        
		public function EtapaVersion()
		{
		}

	}
}
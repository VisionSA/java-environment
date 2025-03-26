package com.bizit.consulta.web.entity
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="com.bizit.consulta.web.entity.UsuarioComercioWeb")]
	[Bindable]
	public class UsuarioComercioWeb
	{
		public var id:Number = -1;
		public var idIndividuo:Number;
		public var idCodigoComercio:Number;
/*@I4923*/		public var codigoPosnet:ArrayCollection;
		public var codigosPosnet:String; 
		
		/*public function get codigoPosnet():ArrayCollection{
			return _codigoPosnet;
		}
		
		public function set codigoPosnet(codPosnet:ArrayCollection):void{
			_codigoPosnet = codPosnet;
			codigosPosnetString = getCodigosPosnet();
		}*/
		
		public function getCodigosPosnet():String
		{
				var codigos:String = new String();
				for each (var cod:String in codigoPosnet)
				{
					codigos += cod;
					codigos +=", ";
				}
				codigos.substring(0, codigos.length -2);
				return codigos.toString();
/*@F4923*/		}
	}
}
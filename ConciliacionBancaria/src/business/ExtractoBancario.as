package business
{
	import flash.utils.ByteArray;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario")]
	public class ExtractoBancario
	{
		public var idExtractoBancario:Number; 
		public var nombreArchivo:String;
		public var bytes:ByteArray;
		
		public function ExtractoBancario()
		{
		}

	}
	
}

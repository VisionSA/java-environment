package vo
{
	import com.tarjetafiel.caja.vo.Operador;
				
		[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ReclamoDetalle")]
	public class ReclamoDetalle
	{
		public var idReclamoDetalle:Number;	
		public var fecha:Date;
		public var idOperador:Operador;
		public var observacion:String;
		public var reclamoEstados:ReclamoEstados;
		public var reclamoCabecera:ReclamoCabecera;		
		
		}

	
}
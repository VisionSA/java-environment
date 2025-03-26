package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Digital;
	import com.tarjetafiel.caja.vo.IndividuoEvaluacion;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class DocAdjuntosModel extends BaseModel
	{
		public var individuo:IndividuoEvaluacion;
		
		public var esTitular: Boolean ;
		public var diaPago:Number;
		public var visibleAcordion:Boolean = false;
		public var cierreFacturacion:Number;
		
		public var docAdjuntos:ArrayCollection;
		
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
	
		public function DocAdjuntosModel()
		{
			individuo=modelo.clienteSeleccionado.cliente.individuo;
		}	
	}
}
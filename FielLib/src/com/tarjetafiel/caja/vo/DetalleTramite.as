package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.workflow.negocio.DetalleTramite")]
	public class DetalleTramite
	{
		public var idDetalleTramite:Number;
		public var tramite:Tramite ;	
		public var fechaInicio:Date;
		public var fechaInicioReal:Date;
		public var fechaFin:Date;
		public var fechaFinReal:Date;
		public var progreso:int;
		public var comentario:String ;
		public var estado:EstadoWorkflow; 
		//private TareaProceso tareaProceso; 
		public var operadorResponsable:Operador;
		public var timestamp:Date;
		public var operador:Operador ;
		public var tarea:Tarea;
				
		public function DetalleTramite()
		{
		}

	}
}


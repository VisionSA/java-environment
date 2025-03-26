package managers
{
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import events.RevertirPagoManagerEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.Fault;
	
	[Bindable]
	public class RevertirPagoManager extends EventDispatcher
	{   
		public var dispatcher:IEventDispatcher;
        public var arrayPagosCliente:ArrayCollection= new ArrayCollection();
		private var fechaDesde:Date;
		private var fechaHasta:Date;
		private var idCliente:Number;	
		
				
		public function RevertirPagoManager()
		{			
		}
		
				
	    public function  buscarPagosCte(fechaDesde:Date,fechaHasta:Date,idCliente:Number):void {
              var evt:RevertirPagoManagerEvent = new RevertirPagoManagerEvent(RevertirPagoManagerEvent.BUSCAR_PAGOS_CTE);
			 /* var filtro:Filtro =  new Filtro();	
              filtro.campos.push("timestamp");
			  filtro.operadores.push(Filtro.MAYOR_IGUAL);
			  filtro.valores.push(Filtro.toDate(fechaDesde));
			  filtro.campos.push("timestamp");
			  filtro.operadores.push(Filtro.MENOR_IGUAL);
 			  filtro.valores.push(Filtro.toDate(fechaHasta));
              filtro.campos.push("clienteTransaccion.idCliente");
			  filtro.operadores.push(Filtro.IGUAL);
 			  filtro.valores.push(idCliente); 
 			  filtro.campos.push("conceptoDetalle.concepto.codigoConcepto");
			  filtro.operadores.push(Filtro.IGUAL);
 			  filtro.valores.push("205"); 
 			  evt.filtro=  filtro;   */
 			  evt.fechaDesde= fechaDesde;
 			  evt.fechaHasta =  fechaHasta;
 			  evt.idCliente =  idCliente;    	  
        	  this.dispatcher.dispatchEvent(evt);
	    } 	
	    
	    public function  revertirPago(idCtaCte:Number):void {
              var evt:RevertirPagoManagerEvent = new RevertirPagoManagerEvent(RevertirPagoManagerEvent.REVERTIR_PAGO);
			  evt.idCtaCte =  idCtaCte;    	  
        	  this.dispatcher.dispatchEvent(evt);
	    } 	
	    
	 
    	
	
      public function resultBuscarPagosCte(arrayPagosCliente:Array):void{
	   	   this.arrayPagosCliente = new ArrayCollection(arrayPagosCliente);
	   	   if(arrayPagosCliente.length == 0){
	   	   		AlertOk.show("No se encontraron datos");
	   	   } 
	   }
	   
	   public function resultRevertirPago(respuestaImp:RespuestaImpresion):void{
	   	   		Alert.show(respuestaImp.mensaje);
	   }
      
		
		public function fault( fault:Fault ):void{
			//ManagerErrors.getInstance().addPopUpError(fault.faultString,fault.faultDetail);
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		

	}
}
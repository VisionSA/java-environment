package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	
	import events.LineaCreditoEvent;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class LineaCreditoModel extends  BaseModel
	{  
	   
	  
	    private var _arrayValoresLineaCredito:ArrayCollection;
		private var _titular:ClienteTransaccion;			
		public var disponibleAdelanto:Number;	
		public  var  lineaCreditoAdelanto:Number;	
		public var saldoLineaAdelanto:Number;
		
		public var disponible:Number;
		
		public function LineaCreditoModel()
		{   
			
		}
			
	  public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(_titular){
				mostrarPanel= true;
				disponible = _titular.limiteCredito.toString() - _titular.saldoLinea.toString();
				if(disponible<0){
					disponible = 0;
				}
			}
			   
	  }  	
		
		public function get titular():ClienteTransaccion{
			return _titular;
		}	
		
		public function set arrayValoresLineaCredito(arrayValoresLineaCredito:ArrayCollection):void{
			_arrayValoresLineaCredito = arrayValoresLineaCredito;
			if(arrayValoresLineaCredito){
				var  i:int =  0;
			   for each (var element:Number in arrayValoresLineaCredito){
			   	    if(i==0){
			   	        lineaCreditoAdelanto= element;
			   	    } else if (i==1) {
                        saldoLineaAdelanto=element;
           			} else {
           				disponibleAdelanto= element
           				if(disponibleAdelanto < 0){
           					disponibleAdelanto = 0;
           				}      			          
			        }
			     i++;    	           
			   }
			} 
	  }  	
		
		public function get arrayValoresLineaCredito():ArrayCollection{
			return _arrayValoresLineaCredito;
		}	
		
		
		public function buscarValoresLC():void{
		 	if(titular){
		  		var evt:LineaCreditoEvent = new LineaCreditoEvent(LineaCreditoEvent.BUSCAR_VALORES_ADELANTO_EFECTIVO);
				evt.idCliente = _titular.idCliente;
				dispatcher.dispatchEvent(evt);
			} 
		}
		
		
	}
}
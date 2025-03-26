package modules.pagofacil
{
	import com.tarjetafiel.caja.vo.ArchivoFarmacia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	import vo.ArchivoCobroExternoNegocio;
	
	[Bindable]
	public class Manager
	{
		public var dispatcher:IEventDispatcher;
		
		public var archivosList:ArrayCollection;
		
		public var listaTipoCobro:ArrayCollection;
		
		public var listaCuentasCobradas:ArrayCollection;
		
		public var archivosFarmaciaList:ArrayCollection;
		
		private var filtro:Filtro;
		
		public var paginador:Paginador;
		
		public var paginadoFarmacia:Paginador;
		
		private var fechaDesde:Date;
		
		private var fechaFarmaciaDesde:Date;
		
		private var archivo:ArchivoCobroExternoNegocio;	
		
		private var archivoFarmacia:ArchivoFarmacia;
		
		public var fechaBuscar:Date = new Date();

		public var fechaServidor:String;
		
		public function Manager()
		{
		}

		
		/*  Obtener la fecha del servidor */
		public function getFechaServidor():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.OBTENER_FECHA_SERVIDOR);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		public function procesarListarCuentas(idArchivo:Number):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_CUENTAS);
			evt.idArchivo = idArchivo;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function procesarListaTipoCobro():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_TIPO_COBRO);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function procesarArchivo(data:ArchivoCobroExternoNegocio):void{
			archivo = data; 
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_PAGO);
			evt.archivo = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function procesarArchivoFarmacia(data:ArchivoFarmacia):void{
			archivoFarmacia = data; 
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_PAGO_FARMACIA);
			evt.archivoFarmacia = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function resultProcesarArchivoFarmacia():void{
			archivoFarmacia.procesado = 1;
			AlertOk.show("El pago se proceso con exito");
			ControlBlock.getInstance().remove();
		}
		
		public function resultProcesarArchivo():void{
			archivo.procesado = "S";
			AlertOk.show("El archivo se proceso con éxito");
			ControlBlock.getInstance().remove();
		}
		
		

		/* I4662 guarga fecha del servidor */
		public function functionFechaServidor(result:String):void{
			this.fechaServidor = result;
			ControlBlock.getInstance().remove();
		}
		
		
		
		
		public function listarCuentasCobradas(result:Array):void{
			listaCuentasCobradas = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron cuentas cobradas");
			}	
			
		}
		
		
		public function resultListAllTipoCobro(result:Array):void{
			listaTipoCobro = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
			
		}
		
		public function resultListAllArchivos(result:Paginador):void{
			archivosList = new ArrayCollection(result.result);
			paginador = result;
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertOk.show("No se encontraron datos");
			}
			
			/* AlertOk.show("paginacionCompleta"); */
			
			dispatcher.dispatchEvent(new Event("paginacionCompleta"));
		}
		
		
		
		
		public function resultListAllArchivosFarmacia(result:Paginador):void{
			archivosFarmaciaList = new ArrayCollection(result.result);
			paginador = result;
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertOk.show("No se encontraron datos");
			}
			
			dispatcher.dispatchEvent(new Event("paginacionCompletaFarmacia"));
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		public function listAllArchivos(fechaDesde:Date=null):void{
			/* if(paginador){
			AlertOk.show("paginador no null "  );
			} else {
			AlertOk.show("paginador en null "  );	
			} */
			if(paginador){
				if(fechaDesde == null){
					this.fechaDesde = new Date();
				} else {
					this.fechaDesde = fechaDesde;
				}
			    /* AlertOk.show("listAllArchivos " + this.fechaDesde ); */
			 
				paginador.cantidadRegistros = 20;
				paginador.pagina = 0;
				filtro = new Filtro(); 
				filtro.campos.push("fecha"); 
				filtro.operadores.push(Filtro.MAYOR_IGUAL);  
				filtro.valores.push(Filtro.toDate(this.fechaDesde)); 				
							
				distachBusquedaArchivo();
			}
			
		}
		
		public function listAllArchivosFarmacia(fechaDesde:Date=null):void{
			
			if(paginadoFarmacia){
				if(fechaDesde == null){
					this.fechaFarmaciaDesde = new Date();
				} else {
					this.fechaFarmaciaDesde = fechaDesde;
				}
			
				paginadoFarmacia.cantidadRegistros = 20;
				paginadoFarmacia.pagina = 0;
				var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS_FARMACIA);
				evt.fecha = fechaFarmaciaDesde;
				evt.paginador = paginadoFarmacia;
				dispatcher.dispatchEvent(evt);							
				ControlBlock.getInstance().add();
			}
			
		}

		
		public function paginar():void{
			distachBusquedaArchivo();
		}
		
		
		public function paginarFarmacia():void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS_FARMACIA);
			evt.fecha = fechaFarmaciaDesde;
			evt.paginador = paginadoFarmacia;
			dispatcher.dispatchEvent(evt);							
			ControlBlock.getInstance().add();
		}
		
		
		private function distachBusquedaArchivo():void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS);
			evt.filtro = filtro;
			evt.paginador = paginador;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
			
	}
}
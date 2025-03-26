package managers
{
	import mx.core.Application;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteComercio;
	import com.tarjetafiel.caja.vo.SucEmail;
	import com.tarjetafiel.caja.vo.SucTelefono;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import events.ComercioEvent;
	import events.ComercioManagerEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	import flash.net.navigateToURL;
	import flash.net.URLRequest;
	
	public class ComercioManager extends EventDispatcher
	{   
		public var dispatcher:IEventDispatcher;
		[Bindable]public var comercio:CodComercio; 
		//public var paginador:Paginador;
		[Bindable]public var paginador:Paginador;
		public var comercios : ArrayCollection;
				
		[Bindable]private var _codComercio:CodComercio;
	    [Bindable]public var codComercioEncontrado:CodComercio;
		[Bindable]public var arrayClientes:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayAdicionales:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayTitularAdiscionales:ArrayCollection= new ArrayCollection();
		
		[Bindable]public var arrayMovientosCtaCteResumen:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayMovientoCtaCteDetalles:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayComposicionCtaCte:ArrayCollection= new ArrayCollection();	
		[Bindable]public var arrayMovientosCompSaldo:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayMovientosContable:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayMCSDetallesCtaCte:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayMCSComposicionCtaCte:ArrayCollection= new ArrayCollection();

		[Bindable]public var	arrayCOMDetallesCtaCte:ArrayCollection;
		[Bindable]public var	arrayCOMComposicionCtaCte:ArrayCollection;

		[Bindable]public var	arrayMovientoRELCtaCteDetalles:ArrayCollection;


		[Bindable]public var saldoAnteriorCtaCte:Number;

		private var paramBusqueda:String ; 
		private var tipoBusqueda:String ;
		private var isBuscarPorPlastico:Number;
		public static const BUSQ_POR_CUIL:String = "busqPorCuil";
		public static const BUSQ_POR_COD_COMERCIO:String = "busqPorCodComercio";//cod posnet
		public static const BUSQ_POR_RAZON_SOCIAL:String = "busqPorRazonSocial";
		public static const BUSQ_POR_NOMBRE_COMERCIO:String = "busqPorNombreComercio";
		public static const BUSQ_POR_ID_EMPRESA:String = "busqPorIdEmpresa";
		public var movimientoCtaCteComercio : MovimientoCtaCteComercio;
					
		[Bindable]public var arrayTiposTelefono:ArrayCollection= new ArrayCollection();
		private var idSucEmpresaMail : Number;
					
		private var fechaDesde:Date;
		private var idCodComercio:Number;	
		
		public function ComercioManager()
		{			
		}
		
		  public function buscarPorCodComercio(codigoPosnet:String):void{
			var filtro:Filtro = new Filtro();
			filtro.campos.push("codigoPosnet");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(codigoPosnet);
			this.buscar(filtro,false);
		}
		
		public function buscarComercios(cuit:String,razonSocial:String,sucursal:String):void{
			var filtro:Filtro = new Filtro();
			if(cuit && cuit.valueOf()!= ""){
				filtro.campos.push("codComercio.sucursal.empresa.cuit");
			    filtro.operadores.push(Filtro.LIKE);
		    	filtro.valores.push(cuit);
			}
			if(razonSocial && razonSocial.valueOf()!= ""){
				filtro.campos.push("codComercio.sucursal.empresa.razonSocial");
			    filtro.operadores.push(Filtro.LIKE);
		    	filtro.valores.push(razonSocial);
			}
			if(sucursal && sucursal.valueOf()!= ""){
				filtro.campos.push("codComercio.sucursal.nombre");
			    filtro.operadores.push(Filtro.LIKE);
		    	filtro.valores.push(sucursal);
			}
		
			this.buscar(filtro,true);
		}
		
		private function buscar(filtro:Filtro,retornaLista:Boolean ):void{
			var evt:ComercioManagerEvent; 
			if(!retornaLista)
			   evt= new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_COMERCIO);
			else  evt= new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_COMERCIOS);
			evt.filtro = filtro;
			this.dispatcher.dispatchEvent(evt);
		}
		
		public function resultBuscarPorCodComercio( comercioArray:Array ):void{
			this.comercio = comercioArray[0] as CodComercio; 
		}
		
		public function resultBuscarComercios( comercioArray:Array ):void{
			this.comercios= new ArrayCollection(comercioArray) ;
			ControlBlock.getInstance().remove();
		}


		
		public function resultListaComorecio(paginador:Paginador):void{
			this.paginador = paginador; 			
			ControlBlock.getInstance().remove();
		}
		//*****************
		public function resultBusquedaComercio(comercio:CodComercio):void{
			this.comercio = comercio;
			ControlBlock.getInstance().remove(); 
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		
		
		
		///*******************************************************
		/*
		private function resetDatosCliente():void{
			  arrayAdicionales = new ArrayCollection();
              arrayClientes = new ArrayCollection();
              arrayComposicionDetalleCtaCte = new ArrayCollection();
              arrayComposicionSaldo = new ArrayCollection();
              arrayComposicionSaldoDetalles = new ArrayCollection();
              arrayDetallesLiquidaciones = new ArrayCollection();
              arrayLiquidaciones = new ArrayCollection();
              arrayMovientoCtaCteDetalles = new ArrayCollection();
              arrayMovientosCtaCteResumen = new ArrayCollection();
              arrayTitularAdiscionales = new ArrayCollection();
              dispatcher.dispatchEvent(new ClienteEvent(ClienteEvent.RESET_CLIENTES));
		}
		*/
		//4
	    public function  buscarMovimientosCtaCte(fechaDesde:Date,idCodComercio:Number):void {
          	  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_MOVIMIENTOS_CTA_CTE_COMERCIO);
         	  evt.fechaDesde =  fechaDesde;
			  evt.idComercio =  idCodComercio;
			  this.dispatcher.dispatchEvent(evt);
			  
        } 	
		//5
		 public function  buscarDetallesMovimientoCtaCte(idTransaccion:Number,idCodComercio:Number,idTipoConcDetalle:int ):void {
           var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO);
		   evt.idTransaccion = idTransaccion;
		   evt.idComercio =  idCodComercio; 
		   this.dispatcher.dispatchEvent(evt);     	
         } 
		//6
		public function  obtenerSaldoAnteriorCtaCte(fechaHasta:Date,idCodComercio:Number ):void {
          var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.OBTENER_SALDO_ANTERIOR_CTA_CTE_COMERCIO);
		  evt.fechaHasta = this.fechaDesde= fechaHasta;
		  evt.idComercio = this.idCodComercio=idCodComercio; 
		  this.dispatcher.dispatchEvent(evt);     	
        } 
		
		/**
		 * @id 3945
		 * lanza el evento que llama a agregarMail
		 **/	
		public function agregarEmail(mail:Email,idSucEmpresa:Number):void{
			this.idSucEmpresaMail = idSucEmpresa;
			var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.AGREGAR_EMAIL);
			evt.email = mail;
			evt.idSucEmpresa = idSucEmpresa;
			
			this.dispatcher.dispatchEvent(evt);
		}
		/**
		 * @id 3945
		 * lanza el evento que llama a agregarTelefono
		 **/
		public function agregarTelefono(tel:Telefono,idSucEmpresa:Number):void{
			this.idSucEmpresaMail = idSucEmpresa;
			var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.AGREGAR_TELEFONO);
			evt.telefono = tel;
			evt.idSucEmpresa = idSucEmpresa;
			
			this.dispatcher.dispatchEvent(evt);
		}
		
		
	//**************************************RESULTS***************************************************//
	
      // result 4
      public function resultBuscarMovimientosCtaCte(movimientosResumen:Array):void{
	   	   this.arrayMovientosCtaCteResumen = new ArrayCollection(movimientosResumen);
	   	   if(arrayMovientosCtaCteResumen.length == 0){
	   	   		AlertOk.show("No se encontraron datos");
	   	   } 
	   	    ControlBlock.getInstance().remove();
	  }
        
      // result 5
       public function resultBuscarDetallesMovimientoCtaCte(arrayMovientoCtaCteDetalles:Array):void{
	   	   this.arrayMovientoCtaCteDetalles = new ArrayCollection(arrayMovientoCtaCteDetalles);
	   	   if(arrayMovientoCtaCteDetalles.length == 0){
	   	   		AlertOk.show("No se encontraron datos");
	   	   } 
	   	   
	   	    ControlBlock.getInstance().remove();
	  }
      
      // result 6
        public function resultObtenerSaldoAnteriorCtaCte(saldoAnterior:Number):void{
	   	   this.saldoAnteriorCtaCte = saldoAnterior;
	   	   buscarMovimientosCtaCte(fechaDesde,idCodComercio);
	   	   /*if(saldoAnterior>=0)
	   	     else  AlertOk.show("Consulte la base de datos histórica para obtener resultados para la fecha ingresada");*/
	   	    ControlBlock.getInstance().remove();
	  	}
      
		public function generarReporteComercio(cuit:String):void{			
					ControlBlock.getInstance().add();
					var url,p1,p2,page : String;
					var array:Array = Application.application.url.split("/");
					/* uploadComp.uploadURL = "/" +array[3] + "/UploadFilePagoFacil"; */
					/*uploadComp.uploadURL = "/" +array[3] + "/UploadCobroExterno";*/
					
					var direccion:String = array[0]+"/"+array[1]+"/"+array[2]+"/"+array[3];
					/*AlertOk.show(direccion);*/
										
					/*String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();*/
					 p1 = "?cuit="+cuit;		
				     p2 = "&URLImagen="+direccion+"/img/fiel/logo_fiel.jpg";
					 
					 page = "/Presentacion/report/imp_comercio_visitar_cuit_posnet.jrxml";
	
					url = page+p1+p2;
					
					/*AlertOk.show(url);*/
	
					/*navigateToURL(new URLRequest(String(url).replace('webapps','')),'_blank');*/
					navigateToURL(new URLRequest(url),'_blank');
						ControlBlock.getInstance().remove();
		}	
	
      
       public function resultBuscarComercio(comercio:CodComercio ):void{
			this.comercio = comercio; 
			 ControlBlock.getInstance().remove();
		}
		
		/*public function set comercio(com:CodComercio):void{
			comercio = com;
//			if(comercio){
//				var evt:LiquidacionEvent = new LiquidacionEvent(LiquidacionEvent.BUSCAR_PAGOS_AND_DEUDA);
//				evt.idCliente = _titular.idCliente;
//				dispatcher.dispatchEvent(evt);
//			}
//			dispatchEvent(new Event("changedTitular"));		
//			ControlBlock.getInstance().remove();	
		}
		public function get comercio():CodComercio{
			return comercio;
		}*/
		/*
		[Bindable(event="changedTitular")]
		public function get titular():ClienteTransaccion{
			return _titular;
		}
		*/

	   /**
		* @id 3945
		* recibe el resultado del evento que llama a agregarMail
		**/
	   public function resultAgregarEmail(result:Array):void{ 
		   if(result != null){
			   for each (var element:CodComercio in this.paginador.result)
			   {
				   if(element.sucEmpresa && element.sucEmpresa.idSucEmpresa == idSucEmpresaMail)
				   {
					   var sucEmailsTemp:ArrayCollection = new ArrayCollection();
					   for each (var mailTemp:Email in result)
					   {
						   var sucMail:SucEmail = new SucEmail();
						   sucMail.email = mailTemp;
						   sucEmailsTemp.addItem(sucMail);
					   }
					   element.sucEmpresa.sucEmails = sucEmailsTemp.toArray();
					   //lanza el evento para recargar los datos del comercio
					   var evt:ComercioEvent;
					   evt = new ComercioEvent(ComercioEvent.RECARGAR_DATOS_COMERCIO);
					   
					   dispatcher.dispatchEvent(evt);
				   }
			   }
		   }else{
			   AlertError.show("No se pudo grabar el Email, intente mas tarde");
		   }
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* arrayMovientoRELCtaCteDetalles
		**/
	   
	   public function resultBuscarDetallesRELMovimientoCtaCte(arrayMovientoRELCtaCteDetalles:Array):void{
		   this.arrayMovientoRELCtaCteDetalles = new ArrayCollection(arrayMovientoRELCtaCteDetalles);
		   if(arrayMovientoRELCtaCteDetalles.length == 0){
			   AlertOk.show("No se encontraron datos");
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   
	   /**
		* @id 8271
		* resultBuscarMCSComercioCtaCte
		**/
	   
	   public function resultBuscarMCSComercioCtaCte(arrayMCSDetallesCtaCte:Array):void{
		   this.arrayMCSDetallesCtaCte = new ArrayCollection(arrayMCSDetallesCtaCte);
		   if(arrayMCSDetallesCtaCte.length == 0){
			   AlertOk.show("No se encontraron datos");
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* resultBuscarCOMComercioCtaCte
		**/
	   
	   public function resultBuscarCOMComercioCtaCte(arrayCOMDetallesCtaCte:Array):void{
		   this.arrayCOMDetallesCtaCte = new ArrayCollection(arrayCOMDetallesCtaCte);
		   if(arrayCOMDetallesCtaCte.length == 0){
			   AlertOk.show("No se encontraron datos");
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* resultBuscarComposicionDetalleCtaCte
		**/
	   public function resultBuscarComposicionCtaCte(arrayComposicionCtaCte:Array):void{
		   this.arrayComposicionCtaCte = new ArrayCollection(arrayComposicionCtaCte);
		   if(arrayComposicionCtaCte.length == 0){
			  /* AlertOk.show("No se encontraron datos");*/
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* resultBuscarMCSClienteCtaCte
		**/
	   public function resultBuscarMCSClienteCtaCte(arrayMCSComposicionCtaCte:Array):void{
		   this.arrayMCSComposicionCtaCte = new ArrayCollection(arrayMCSComposicionCtaCte);
		   if(arrayMCSComposicionCtaCte.length == 0){
			   /* AlertOk.show("No se encontraron datos");*/
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* resultBuscarCOMClienteCtaCte
		**/
	   public function resultBuscarCOMClienteCtaCte(arrayCOMComposicionCtaCte:Array):void{
		   this.arrayCOMComposicionCtaCte = new ArrayCollection(arrayCOMComposicionCtaCte);
		   if(arrayCOMComposicionCtaCte.length == 0){
			   /* AlertOk.show("No se encontraron datos");*/
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   
	   /**
		* @id 8271
		* resultBuscarCompSaldo
		**/
	   public function resultBuscarCompSaldo(arrayMovientosCompSaldo:Array):void{		   
		   this.arrayMovientosCompSaldo = new ArrayCollection(arrayMovientosCompSaldo);
		   if(this.arrayMovientosCompSaldo.length != 0){
			    
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 8271
		* resultBuscarContable
		**/
	   public function resultBuscarContable(arrayMovientosContable:Array):void{
		   this.arrayMovientosContable = new ArrayCollection(arrayMovientosContable);
		   if(this.arrayMovientosContable.length != 0){
			   
		   } 
		   
		   ControlBlock.getInstance().remove();
	   }
	   
	   /**
		* @id 3945
		* recibe el resultado del evento que llama a agregarTelefono
		**/
	   public function resultAgregarTelefono(result:Array):void{ 
		   if(result != null){
			   for each (var element:CodComercio in this.paginador.result)
			   {
				   if(element.sucEmpresa && element.sucEmpresa.idSucEmpresa == idSucEmpresaMail)
				   {
					   var sucTelefonosTemp:ArrayCollection = new ArrayCollection();
					   for each (var telTemp:Telefono in result)
					   {
						   var sucTel:SucTelefono = new SucTelefono();
						   sucTel.telefono = telTemp;
						   sucTelefonosTemp.addItem(sucTel);
					   }
					   element.sucEmpresa.sucTelefonos = sucTelefonosTemp.toArray();
					   //lanza el evento para recargar los datos del comercio
					   var evt:ComercioEvent;
					   evt = new ComercioEvent(ComercioEvent.RECARGAR_DATOS_COMERCIO);
					   
					   dispatcher.dispatchEvent(evt);
				   }
			   }
		   }else{
			   AlertError.show("No se pudo grabar el Telefono, intente mas tarde");
		   }
		   
		   ControlBlock.getInstance().remove();
	   }
	}
}
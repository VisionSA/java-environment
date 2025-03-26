package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	
	import events.ClienteEvent;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	import views.categorias.cliente.modules.GeneralClienteModule;
	import views.categorias.cliente.popup.AgregarMailView;
	import views.categorias.cliente.popup.AgregarTelefonoLaoralView;
	import views.categorias.cliente.popup.AgregarTelefonoView;
	import views.categorias.cliente.popup.ModificarMailView;
	import views.categorias.cliente.popup.ModificarTelefonoView;
	import views.categorias.cliente.popup.VerTelefonoLaboralView;

	[Bindable]
	public class GeneralClienteModel extends BaseModel
	{  
		private var _cliente:ClienteTransaccion;
		public var telefonos: ArrayCollection; 
		public var fechaAltaFiel:Date; 
		public var promotor:String;   
		public var mostrarTelefonos:Boolean= false;
	   	public var msgTelefono:String="Mostrar Telefonos";
	   	public var  _arrayTitularAdiscionales:ArrayCollection;
	  	public var esTitular: Boolean ; 
		public var visibleAcordion:Boolean = false;
		public var cierreFacturacion:Number;
		public var diaPago:Number;
		
		public var popUpAgregarMail:AgregarMailView = new AgregarMailView();
		public var popUpModificarMail:ModificarMailView = new ModificarMailView();
		public var popUpAgregarTelefono:AgregarTelefonoView = new AgregarTelefonoView();
		public var popUpModificarTelefono:ModificarTelefonoView = new ModificarTelefonoView();
		public var popUpModificarTelefonoLaboral:VerTelefonoLaboralView = new VerTelefonoLaboralView();
		public var popUpAgregarTelefonoLaboral:AgregarTelefonoLaoralView = new AgregarTelefonoLaoralView();
		
		public var arrayTiposTelefono:ArrayCollection;		
		public function GeneralClienteModel()
		{   
			
		}
		
		
		
		public function testMateModule():void{
			///Alert.show(dispatcher+"");
		}
		
		public function setearClienteSeleccionado(cliente:ClienteTransaccion):void
		{
			if (ModelLocatorGeneral != null)
			{
				ModelLocatorGeneral.getInstance().clienteSeleccionado.setearCliente(cliente);
			}
		}
		
		public function set cliente(cli:ClienteTransaccion):void{
			_cliente = cli;
			 
			if(_cliente){
				this.telefonos= new ArrayCollection(cli.individuo.telefonos) ;
				//mostrarPanel=  true;
				mostrarTelefonos = false;
			    msgTelefono="Mostrar Telefonos";				
				//aca hacer consulta para traer fecha alta y  promotor
				
				//Si está instanciado el ModelLocatorGeneral, setea el cliente seleccionado
				//para poder buscar los futuros vencimientos
				setearClienteSeleccionado(cliente);
	    	}	    	
	    }
	    
	    public function get cliente():ClienteTransaccion{
			return _cliente;
		}
	    
	    public function set arrayTitularAdiscionales(arrayTitularAdiscionales:ArrayCollection):void{
			_arrayTitularAdiscionales = arrayTitularAdiscionales;
			if(arrayTitularAdiscionales){
				
				mostrarPanel=  true;
				visibleAcordion = true;
				for each (var element:ClienteTransaccion in arrayTitularAdiscionales){
					 if(element.nuevoInfoCliente == "Adicional" ) {
						 // element.individuo.tipo= "Adicional #"+element.adicional+ ": " ;
						 element.individuo.tipo= "Adicional: " ;
						 element.individuo.nombreCompleto=element.individuo.tipo+ element.individuo.apellido+ ", "+ element.individuo.nombres;
					 }
					 else if (element.nuevoInfoCliente== "Garante") {
						 element.individuo.tipo= "Garante: " ;
						 element.individuo.nombreCompleto=element.individuo.tipo+ element.individuo.apellido+ ", "+ element.individuo.nombres;
					 }
				      else { element.individuo.tipo= "Titular: ";
				             cierreFacturacion= element.individuo.diaPagoFlex;
							 element.individuo.nombreCompleto=element.individuo.tipo+ element.individuo.apellido+ ", "+ element.individuo.nombres;
				             if(element.individuo.diaPago.diaPago>15)
				                 diaPago= element.individuo.diaPago.diaPago-15;
				             else   diaPago= element.individuo.diaPago.diaPago+15; 
				           }   

				     element.individuo.edad= getEdad(element.individuo.fechaNacimientoFlex);
					// Alert.show("edad " + element.individuo.edad);
				}
			}
	    } 
	    
	    private function getEdad(fecNac:Date):String {
                  var hoy:Date = new Date();
                  var edad:uint = 0;
                  if(fecNac){
	                  edad = Number(hoy.fullYear) - Number(fecNac.fullYear);
	                  /*if (fecNac.month > fecNac.month || (fecNac.month == fecNac.month && fecNac.date >= hoy.date)) */
						  if (fecNac.month > hoy.month || (fecNac.month == hoy.month && fecNac.date >= hoy.date)) 
	                  {
	                   edad--;
	                  }
                  }

            return edad.toString()+ " años" ;
        }
	    
		
		public function get arrayTitularAdiscionales():ArrayCollection{
			return _arrayTitularAdiscionales;
		}
	     	
		
		public function cambiarVistaTelefonos():void {
				if(mostrarTelefonos){
				    mostrarTelefonos = false;
				    msgTelefono="Mostrar Telefonos";
				 }
				 else { mostrarTelefonos=  true;
				       msgTelefono = "Ocultar Telefonos"; 
				 	
				 }    
		}
		
		
		public function isTitular(id:Number):Boolean{
			if(id)
				return false;
				return  true;
		}
/*@I3945*/		/**
		 * @id 3945
		 * abre el popUp para agregar el mail
		 **/
		public function abrirPopUpAgregarMail():void{
			//llamo al popUp agregar mail
			popUpAgregarMail.generalClienteModel= this;
			PopUpManager.addPopUp(popUpAgregarMail,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpAgregarMail);	
		}  
		
		 /*@I35*/		/**
		 * @id 35
		 * abre el popUp para agregar el mail
		 **/
		public function abrirPopUpModicarMail():void{
			//llamo al popUp agregar mail
			popUpModificarMail.generalClienteModel= this;
			PopUpManager.addPopUp(popUpModificarMail,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpModificarMail);	
		}  
		
		/**
		 * @id 3945
		 * lanza en evento para agregar el mail
		 **/
		public function agregarMail(mail:Email,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.AGREGAR_EMAIL);
			evt.email = mail;
			evt.idIndividuo = idIndividuo;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			return;
		}
		
		/**
		 * @id 35
		 * lanza en evento para modificar el mail
		 **/
		public function modificarMail(idMail:Number,mail:Email,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.MODIFICAR_EMAIL);
			evt.email = mail;
			evt.idIndividuo = idIndividuo;
			evt.idMail = idMail;
			
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			return;
		}
		
		/**
		 * @id 35
		 * lanza en evento para modificar el mail
		 **/
		public function eliminarMail(idMail:Number,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.ELIMINAR_EMAIL);
			evt.idIndividuo = idIndividuo;
			evt.idMail = idMail;
			
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			return;
		}
		
		
		/**
		 * @id 3945
		 * abre el popUp para agregar el telefono
		 **/
		public function abrirPopUpAgregarTelefono():void{
			//llamo al popUp agregar Telefono
			popUpAgregarTelefono.generalClienteModel= this;
			PopUpManager.addPopUp(popUpAgregarTelefono,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpAgregarTelefono);	
		}
		
		/**
		 * @id 100
		 * abre el popUp para agregar el telefono
		 **/
		public function abrirPopUpAgregarTelefonoLaboral():void{
			//llamo al popUp agregar Telefono
			popUpAgregarTelefonoLaboral.generalClienteModel= this;
			PopUpManager.addPopUp(popUpAgregarTelefonoLaboral,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpAgregarTelefonoLaboral);	
		}
		
		
		
		
		/**
		 * @id 3945
		 * abre el popUp para modificar el telefono
		 **/
		public function abrirPopUpModificarTelefono():void{
			//llamo al popUp agregar Telefono
			popUpModificarTelefono.generalClienteModel= this;
			//ComboBox(event.target).selectedItem.nombre;
			//popUpModificarTelefono.cmbTipoTel.selectedItem.descripcion = descripcion;
			PopUpManager.addPopUp(popUpModificarTelefono,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpModificarTelefono);	
		}
		
		/**
		 * @id 100
		 * abre el popUp para ver el telefono laboral
		 **/
		public function abrirPopUpModificarTelefonoLaboral():void{
			//llamo al popUp agregar Telefono
			popUpModificarTelefonoLaboral.generalClienteModel= this;
			//ComboBox(event.target).selectedItem.nombre;
			//popUpModificarTelefono.cmbTipoTel.selectedItem.descripcion = descripcion;
			PopUpManager.addPopUp(popUpModificarTelefonoLaboral,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpModificarTelefonoLaboral);	
		}
		
		
		
		/**
		 * @id 3945
		 * lanza el evento para agregar el telefono
		 **/
		public function agregarTelefono(tel:Telefono,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.AGREGAR_TELEFONO);
			evt.telefono = tel;
			evt.idIndividuo = idIndividuo;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
		}
		
		
		/**
		 * @id 100
		 * lanza el evento para agregar el telefono
		 **/
		public function agregarTelefonoLaboral(tel:Telefono,idSucEmpresa:Number,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.AGREGAR_TELEFONO_LABORAL);
			evt.telefono = tel;
			evt.idSucEmpresa = idSucEmpresa;
			evt.idIndividuo = idIndividuo;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
		}
		
		/**
		 * @id 100
		 * lanza el evento para modificar el telefono
		 **/
		public function modificarTelefono(idTelefono:Number,tel:Telefono,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.MODIFICAR_TELEFONO);
			evt.telefono = tel;
			evt.idIndividuo = idIndividuo;
			evt.idTelefono = idTelefono;
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
		}
		
		/**
		 * @id 100
		 * lanza en evento para eliminar telefono
		 **/
		public function eliminarTelefono(idTelefono:Number,idIndividuo:Number):void{
			
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.ELIMINAR_TELEFONO);
			evt.idIndividuo = idIndividuo;
			evt.idTelefono = idTelefono;
			
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			
		}
		
		/**
		 * @id 3945
		 * lanza el evento para cargar el combo de TipoTelefono
		 **/
		public function cargarCmbTipoTel():void {
			if(this.arrayTiposTelefono == null || this.arrayTiposTelefono.length == 0)
			{
				var evt:ClienteEvent;
				evt = new ClienteEvent(ClienteEvent.CARGAR_CMB_TIPO_TEL);
				
				dispatcher.dispatchEvent(evt);
				
				ControlBlock.getInstance().add();
			}
/*@F3945*/		}
		
	}
}
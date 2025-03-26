package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
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
						Alert.show(" calleNumero " + element.individuo.actividad.sucEmpresa.domicilio.calleNumero);
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
				if (fecNac.month > fecNac.month || (fecNac.month == fecNac.month && fecNac.date >= hoy.date)) 
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
		
		
		
		
	}
}
package com.fiel.caja.judicial.model {
	
	import com.adobe.cairngorm.model.ModelLocator;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.Banco;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.caja.vo.ClienteMovimientosMP;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.MovimientoClientesDTO;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.tarjetafiel.caja.vo.SumadorMediosPago;
	import com.tarjetafiel.proveedorconexion.vo.MediosPagoPosibles;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class CajaJudicialModelLocator implements ModelLocator {
		
		private static var _instance: CajaJudicialModelLocator;
		
		public static const PATH_MODULES : String = "com/fiel/caja/judicial/view/module/";
		
		//********Estados***********//
		public var estadoActualApp : String = ConstantesEstados.ESTADO_CARGANDO;
		public var estadoActualVBuscCli : String = ConstantesEstados.ESTADO_VBC_NO_ENCONTRADO;
		//public var estadoActualVImpresoras : String = ConstantesEstados.ESTADO_VI_IMPRESORAS;
		//public var estadoActualVAgPago : String = ConstantesEstados.ESTADO_VAP_SIN_MEDIOS;
		public var estadoActualVAgCheque : String = ConstantesEstados.ESTADO_VACH_CHEQUE_TERCERO;
		public var estadoActualVDescValores : String = ConstantesEstados.ESTADO_VDV_BASE;
		public var estadoChequeEstado : String = ConstantesEstados.ESTADO_CMP_CHEQUE_NUEVO;
		public var estadoChequeConciliado : String = ConstantesEstados.ESTADO_CMP_CHEQUE_NUEVO;
		public var estadoActualVMenuCajas : String = ConstantesEstados.ESTADO_VMC_BASE;
		
		//Main
		public var operador : Operador = null;
		public var cajaApertura : CajaApertura = null;
		public var listaAbogados : ArrayCollection = null;
		public var abogadoSeleccionado : Abogado = null;
		public var clienteJudicialEncontrado : ClienteTransaccion = null;
		//public var listaClientesJudiciales : ArrayCollection = new ArrayCollection();
		public var listaMediosPagoCaja : ArrayCollection = null;
		public var banco : Banco = null;
		public var mediosPosibles : MediosPagoPosibles;
		public var sumador : SumadorMediosPago = new SumadorMediosPago();
		//public var movimientoClientes : MovimientoClientesDTO = new MovimientoClientesDTO();
		public var listaClientesJudiciales : ArrayCollection = new ArrayCollection();
		public var sumaTotalClientes : Number = 0;
		
		public var listaMovimientosMP : ArrayCollection = null;
		
		/*Se utilizan para el calculo total de todos los clientes agregados a la grilla
		*/
//		public var sumaEfectivo : Number = 0;
//		public var sumaTickets : Number = 0;
//		public var sumaCheques : Number = 0;
//		public var sumaDepositos : Number = 0;
		
		
		//Clientes
		//public var cliMovSelected : ClienteMovimientosMP;
		
		
		//Impresoras
		public var arrImpresoras:ArrayCollection = new ArrayCollection();
		
		//Cheques
		public var arrChequesEnCaja:ArrayCollection = new ArrayCollection();
		public var arrChequesDescargados:ArrayCollection = new ArrayCollection();
		public var cheque : Cheque = null;
		private var _esChequeEstadoValido : Boolean;
		private var _esChequeConciliado : Boolean;
		public var esChequeValido : Boolean;
		public var listaCheques : ArrayCollection = null;
		
		//Depositos
		public var listaDepositos : ArrayCollection = null;
		
		//Descarga de valores
		public var arrCajaMpList:ArrayCollection = new ArrayCollection();
		public var arrMediosPagoDescargaValores:ArrayCollection = new ArrayCollection();
		public var arrCajaMpDescarga:ArrayCollection = new ArrayCollection();
		public var ultimaRespuestaImpresion:RespuestaImpresion;
		
		//Arqueo de Caja
		public var arrCajaCierre:ArrayCollection = new ArrayCollection();
		public var mostrarDiferencia:Boolean = false;
		public var formaPagoValoresList:ArrayCollection = new ArrayCollection();
		public var tipoDeCierreCaja:String;
		//public var arrCierres:ArrayCollection = new ArrayCollection();
		
		//Bancos Propios
		public var listaBancosPropios:ArrayCollection = null;
		
		public function CajaJudicialModelLocator(enforcer:SingletonEnforcer) {
			if (enforcer == null){
				throw new Error("Solo se puede tener una instancia de CajaJudicialModelLocator");
			}  
		}
		
		public function get esChequeConciliado():Boolean
		{
			return _esChequeConciliado;
		}

		public function set esChequeConciliado(value:Boolean):void
		{
			_esChequeConciliado = value;
			esChequeValido = esChequeEstadoValido && !esChequeConciliado;
		}

		public function get esChequeEstadoValido():Boolean
		{
			return _esChequeEstadoValido;
		}

		public function set esChequeEstadoValido(value:Boolean):void
		{
			_esChequeEstadoValido = value;
			esChequeValido = esChequeEstadoValido && !esChequeConciliado;
		}
		
		
		public function validarConciliado(cheque:Cheque):void{
			if (cheque.conciliado=="N"){
				esChequeConciliado = false;
				estadoChequeConciliado = ConstantesEstados.ESTADO_CMP_CHEQUE_NO_CONCILIADO;
			}else if (cheque.conciliado=="S"){
				esChequeConciliado = true;
				estadoChequeConciliado = ConstantesEstados.ESTADO_CMP_CHEQUE_CONCILIADO;
			}
		}		
		
//		public function crearMovimientoNuevoVacio(cajaApertura:CajaApertura,operador:Operador):Movimiento{
//			var resultado : Movimiento = new Movimiento();
//			resultado.caja = cajaApertura.caja;
//			resultado.cajaApertura = cajaApertura;
//			resultado.estado = "A";
//			resultado.operador = operador;
//			resultado.signo = 1;
//			resultado.movimientosMP = new Array();
//			return resultado;
//			
//		}
		
		

		public static function getInstance(): CajaJudicialModelLocator {
			if (_instance == null){
				_instance =  new CajaJudicialModelLocator(new SingletonEnforcer());
			}
			return _instance;
		}
		
		
	}
}
// Utility class to deny access to the constructor
class SingletonEnforcer {}
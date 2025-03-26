package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.Banco;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.caja.vo.MovimientoMP;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class ChequeModel
	{
		public var chequePropio:Cheque;
		
		public var bancosPropios : ArrayCollection = new ArrayCollection();
		
		public var cheque:Cheque;
		
		public var mp:MovimientoMP;
		
		public var copyMp:MovimientoMP;
		
		public var chequesEnCajaList:ArrayCollection = new ArrayCollection();
		
		public var chequesDescargados:ArrayCollection = new ArrayCollection();
		
		public var existeCheque:Boolean = false;
		
		public var mostrarDatosCheque:Boolean = false;
		
		public var esChequePropio:Boolean = false;
		
		public var esModificable:Boolean = false;
		
		public var chequeEstadoValido:Boolean = false;
		
		public var esChequeValido:Boolean = false;
		
		
		
							
		public function ChequeModel()
		{
		}
		
		public function buscarBanco(codigo:String):Boolean{
			var bancosList:ArrayCollection = ModelLocator.getInstance().bancoModel.arrayBancos;
			for each(var banco:Banco in bancosList){
				if(banco.codigo == codigo){
					this.cheque.banco = banco;
					return true;
				}
			}
			
			return false;
		}
		
		public function pasarTodosChequesDescargado():void{
			for each(var obj:Object in chequesEnCajaList){
				chequesDescargados.addItem(obj);
			}					
			chequesEnCajaList.removeAll();
		}
		
		public function pasarTodosChequesEnCaja():void{
			for each(var obj:Object in chequesDescargados){
				chequesEnCajaList.addItem(obj);
			}		
			
			chequesDescargados.removeAll();
		}
		
		public function pasarChequeADescargado(cheque:Cheque):void{
			if(cheque){
				chequesDescargados.addItem(chequesEnCajaList.getItemAt(chequesEnCajaList.getItemIndex(cheque)));
				chequesEnCajaList.removeItemAt(chequesEnCajaList.getItemIndex(cheque));
			}
		}
		
		public function pasarChequeACaja(cheque:Cheque):void{
			if(cheque){
				chequesEnCajaList.addItem(chequesDescargados.getItemAt(chequesDescargados.getItemIndex(cheque)));
				chequesDescargados.removeItemAt(chequesDescargados.getItemIndex(cheque));
			}
		}
		
		public function setearColor():void{
				if (cheque != null){
					var tipo : String = cheque.chequeEstado.tipo;
					var id : Number = cheque.chequeEstado.idChequeEstado;
					switch(tipo){
						case "T":
							if (id==9 || id==10 || id==11){ // Depositado, Rechazado, Reemplazado
								chequeEstadoValido = false;
							}else {
								chequeEstadoValido = true;
							}
							break;
						case "P":
							if (id==4){ //Anulado
								chequeEstadoValido = false;
							}else {
								chequeEstadoValido = true;
							}
							break;
						case "C":
							if (esChequePropio){
								chequeEstadoValido = true;
							}else {
								chequeEstadoValido = false;	
							}							
							break;
					}
				}				
		}
		
		public function validarCheque():void {
			if (esChequePropio){ /* Cheque Propio */
				if (cheque.conciliado == 'N'){
					if(cheque.chequeEstado.idChequeEstado == 4){
						esChequeValido = false;							
					}else{ /* Agrego o modifico el medio de pago */
						esChequeValido = true;
					}	
				}else {
					esChequeValido = false;
				}				
			}else { /* Cheque de terceros */
				if(cheque.chequeEstado.idChequeEstado == 6 ||
					cheque.chequeEstado.idChequeEstado == 9 ||
					cheque.chequeEstado.idChequeEstado == 10 ||
					cheque.chequeEstado.idChequeEstado == 11){							
						esChequeValido = false;
				}else {// Agrego o modifico el medio de pago
					esChequeValido = true;
				}		
			}	
		}
	}
}
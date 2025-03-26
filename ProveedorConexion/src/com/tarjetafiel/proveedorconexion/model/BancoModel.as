package com.tarjetafiel.proveedorconexion.model
{
	
	import com.tarjetafiel.caja.vo.Banco;
	import com.tarjetafiel.caja.vo.BancoPropio;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class BancoModel
	{
		public var arrayBancos:ArrayCollection = new ArrayCollection();
		public var arrayBancosPropios:ArrayCollection = new ArrayCollection();
		public var bancoSelec:Banco = new Banco();	
		public var bancoPropioSelec:BancoPropio = new BancoPropio();
		public function BancoModel()
		{
		}

	}
}
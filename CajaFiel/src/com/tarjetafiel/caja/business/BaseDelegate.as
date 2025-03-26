package com.tarjetafiel.caja.business
{
	import mx.rpc.IResponder;
	
	public class BaseDelegate
	{
		
		protected var responder:IResponder;
		
		public function BaseDelegate(responder:IResponder )
		{
			this.responder = responder;	
		}

	}
}
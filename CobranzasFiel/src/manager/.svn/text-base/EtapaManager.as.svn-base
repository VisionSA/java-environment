package manager
{
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	
	[Bindable]
	public class EtapaManager extends ManagerGenerico
	{
		
		public var etapasArray:ArrayCollection;
		public var etapasVersionArray:ArrayCollection;
		
		public function EtapaManager()
		{
			
		}
		
		public function resultArrayEtapas(arr:Array):void{
			if(!etapasArray){
				etapasArray = new ArrayCollection();
			}
			etapasArray.source = arr;
			var ordenaLista:Sort = new Sort();
            ordenaLista.fields = [new SortField("orden", true)]
            etapasArray.sort = ordenaLista
            etapasArray.refresh();
			//etapasVersionArray = new ArrayCollection();
			//for each (var num:Etapa in etapasArray)
			//	{
			//	    var etapaVersion:EtapaVersion = new EtapaVersion();
			//	    etapaVersion.etapa = num;
			//	    etapasVersionArray.addItem(etapaVersion);
			//	}
		}

	}
}
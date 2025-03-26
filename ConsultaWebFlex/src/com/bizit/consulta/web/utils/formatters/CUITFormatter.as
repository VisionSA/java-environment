package com.bizit.consulta.web.utils.formatters
{
	import mx.formatters.Formatter;
	
	public class CUITFormatter extends Formatter
	{
		[Bindable]
		public var separator : String = "-";
		
		[Bindable]
		public var formatString : String = "##"+ separator + "########" + separator + "#";
		
		public function CUITFormatter()
		{
			super();
		}
		
		
		override public function format(value:Object):String 
		{
			var arrSeparatorIndex:Array = new Array();
			//Controla en que indices están los separadores en la cadena de separación
			for (var char:int = 0; char < formatString.length; char++)
			{
				if (formatString.charAt(char) == separator)
				{
					arrSeparatorIndex.push(char);
				}
			}
			var formattedValue:String = "";
			//Recorre toda la cadena y va alpicando el formato, agregando el separador
			//en el lugar correspondiente
			var countSep:int = 0;
			for (var i:int = 0; i<value.toString().length;i++)
			{
				for each (var sepIndex:int in arrSeparatorIndex)
				{
					if ((i == sepIndex &&
						value.toString().charAt(i) != separator) ||
						(countSep > 0 && ((i+1) == sepIndex) &&
							value.toString().charAt(i) != separator))
					{
						formattedValue += separator;
						countSep += 1;
					}
				}
				formattedValue += value.toString().charAt(i);
			}
			return formattedValue;
		}
		
		public function unFormat(value:Object):String
		{
			var arrValue:Array = value.toString().split(this.separator);
			var unformattedValue:String = "";
			for (var i:int = 0; i < arrValue.length; i++)
			{
				unformattedValue += arrValue[i];
			}
			return unformattedValue;
		}
	}
}
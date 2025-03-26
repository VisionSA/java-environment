package com.util.format
{
	import mx.formatters.CurrencyFormatter;
	import mx.formatters.NumberFormatter;
	
	public class FormatUtil
	{		
		private static var formatCurrency:CurrencyFormatter = new CurrencyFormatter();
		private static var formatNumber:NumberFormatter = new NumberFormatter();
		
		public function FormatUtil()
		{
		}
		
		public static function formatMoneda(obj:Object, simbolo:String="$",presicion:Object=2,round:String="none",separadorDecimales:String=".",separadorMiles:Boolean=true,signoNegativo:Object="(-)"):String{					
			formatCurrency.currencySymbol = simbolo;
			formatCurrency.precision = presicion;
			formatCurrency.rounding = round;
			formatCurrency.useNegativeSign = signoNegativo;
			formatCurrency.useThousandsSeparator = separadorMiles;
			formatCurrency.decimalSeparatorFrom = separadorDecimales;
			formatCurrency.decimalSeparatorTo = separadorDecimales;						
			return formatCurrency.format(obj);
		}
		
		public static function formatPorcentaje(obj:Object,presicion:Object=2,round:String="none",separadorDecimales:String="."):String{					
			formatNumber.decimalSeparatorFrom = separadorDecimales;
			formatNumber.decimalSeparatorTo = separadorDecimales;
			formatNumber.precision = presicion;
			formatNumber.rounding = round;
			formatNumber.useThousandsSeparator = false;								
			return formatNumber.format(obj) + "%";
		}
	}
}
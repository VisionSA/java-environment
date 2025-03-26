package com.bizit.consulta.web.utils.formatters
{
	import mx.formatters.CurrencyFormatter;

	public class FormatUtils
	{
		public function FormatUtils()
		{
		}
		
		public static function formateaMoneda(value:Object):String
		{
			var formatter:CurrencyFormatter = new CurrencyFormatter();
			formatter.alignSymbol="left";
			formatter.currencySymbol="$";
			formatter.decimalSeparatorFrom=".";
			formatter.decimalSeparatorTo=",";
			formatter.thousandsSeparatorFrom="";
			formatter.thousandsSeparatorTo=".";
			formatter.precision="2";
			formatter.useNegativeSign="true";
			formatter.useThousandsSeparator="true";
			return formatter.format(value);
		}
	}
}
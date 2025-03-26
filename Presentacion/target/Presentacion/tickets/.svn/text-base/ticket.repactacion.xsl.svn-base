<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8

Fecha:<xsl:value-of select="header/fecha"/> <xsl:text> </xsl:text> <xsl:value-of select="header/hora"/> Trans.: <xsl:value-of select="header/transaccion"/>
Caja: <xsl:value-of select="header/caja"/> Operador: <xsl:value-of select="header/operador"/> <xsl:text> </xsl:text><xsl:value-of select="translate(header/nombre , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
Cliente: <xsl:value-of select="header/cuenta"/> <xsl:text> </xsl:text> <xsl:value-of select="substring(translate(header/cliente , $vLowercaseChars_CONST , $vUppercaseChars_CONST),1,23)"/>
<xsl:text>
</xsl:text>
%centrar%REPACTACION DE SALDO

Importe a repactar: <xsl:value-of select="refinanciacion/moneda"/> <xsl:value-of select="format-number(refinanciacion/importe, '###.##0,00', 'pesoFormat')"/>
En <xsl:value-of select="refinanciacion/cuotas"/> Cuota/s de: <xsl:value-of select="refinanciacion/moneda"/> <xsl:value-of select="format-number(refinanciacion/importe-cuota, '###.##0,00', 'pesoFormat')"/>
No incluye cargos ni impuestos.

%centrar%Sin reclamo ulterior firmo la
%centrar%presente autorizacion.

Firma:      _________________________

Aclaracion: _________________________

Nro. Doc:   _________________________

<xsl:value-of select="substring (header/cliente,1,1)"/>
<xsl:value-of select="substring (header/fecha,1,2)"/>
<xsl:value-of select="substring (transacciones/@total,2,1)"/>
<xsl:value-of select="substring (header/cliente,3,1)"/>
<xsl:value-of select="substring (header/hora,4,2)"/>
<xsl:value-of select="substring (header/fecha,4,2)"/>
<xsl:value-of select="substring (header/hora,1,2)"/>
<xsl:value-of select="substring (header/transaccion,6,1)"/>
<xsl:value-of select="substring (header/cliente,2,1)"/>
<xsl:value-of select="substring (header/cuenta,4,2)"/>
<xsl:text>
</xsl:text>
%centrar%Comprobante de caja valido 
%centrar%sin sellos.
</ticket>
</xsl:template>
<xsl:decimal-format name="pesoFormat" decimal-separator="," grouping-separator="."/>
<xsl:variable name="vLowercaseChars_CONST" select="'abcdefghijklmnopqrstuvwxyz'"/>
<xsl:variable name="vUppercaseChars_CONST" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
</xsl:stylesheet>
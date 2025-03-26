<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:f="http://www.w3.org/2005/xpath-functions" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8

Fecha:<xsl:value-of select="header/fecha"/> <xsl:text> </xsl:text> <xsl:value-of select="header/hora"/> Trans.: <xsl:value-of select="header/transaccion"/>
Caja: <xsl:value-of select="header/caja"/> Operador: <xsl:value-of select="header/operador"/>&#xD; <xsl:text> </xsl:text><xsl:value-of select="translate(header/nombre , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
Cuenta Titular: <xsl:value-of select="header/cuenta"/> <xsl:text> </xsl:text>
Cliente: <xsl:value-of select="translate(header/cliente , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
<xsl:text>
</xsl:text>
%centrar%ADELANTO EN EFECTIVO

En el dia de la fecha, solicito a
VISION S.A. un adelanto en efectivo de
<xsl:value-of select="adelanto/moneda"/> <xsl:value-of select="format-number(adelanto/importe, '###.##0,00', 'pesoFormat')"/> a cancelar en <xsl:value-of select="adelanto/cuotas"/> cuota/s de
<xsl:value-of select="adelanto/moneda"/> <xsl:value-of select="format-number(adelanto/importe-cuota,'###.##0,00', 'pesoFormat')"/>, el cual recibo en este acto.
No incluye cargos ni impuestos.

%centrar%Sin reclamo ulterior firmo la 
%centrar%presente autorizacion.

Firma:      _____________________

Aclaracion: _____________________

Nro. Doc:   _____________________


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
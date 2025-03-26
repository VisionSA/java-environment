<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
<![CDATA[
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8

%centrar%RECEPCION DE CUPONES
]]>
Fecha:  <xsl:value-of select="header/fecha"/><xsl:text>  </xsl:text><xsl:value-of select="header/hora"/>
Operador: <xsl:value-of select="header/operador"/> <xsl:text> </xsl:text><xsl:value-of select="translate(header/nombre, $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
Comercio: <xsl:value-of select="header/comercio"/> <xsl:text> </xsl:text><xsl:value-of select="substring(translate(header/nombreco, $vLowercaseChars_CONST , $vUppercaseChars_CONST),1,23)"/>

Comprobante Nro: <xsl:value-of select="cabeceraCupon/nroComprobante/@valor"/>
Cantidad de cupones: <xsl:value-of select="cabeceraCupon/cantidad/@valor"/>
Importe: $<xsl:value-of select="format-number(cabeceraCupon/importe/@valor, '###.##0,00', 'pesoFormat')"/>
<xsl:text>
</xsl:text>
%centrar%Este  comprobante no implica  aceptacion
%centrar%de las operaciones detalladas. Tanto los
%centrar%resumenes  de venta como los cupones  se
%centrar%encuentran sujetos a verificacion por el
%centrar%Departamento  de  Auditoria  Interna  de
%centrar%VISION S.A.  

</ticket>
</xsl:template>
<xsl:decimal-format name="pesoFormat" decimal-separator="," grouping-separator="."/>
<xsl:variable name="vLowercaseChars_CONST" select="'abcdefghijklmnopqrstuvwxyz'"/>
<xsl:variable name="vUppercaseChars_CONST" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
</xsl:stylesheet>